package com.samsung.android.gallery.app.activity;

import C3.C0392b;
import android.content.ComponentCallbacks2;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.bixby.BixbyAppStateUtil;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.sdk.bixby2.Sbixby;
import com.samsung.android.sdk.bixby2.state.StateHandler;
import com.sec.android.gallery3d.R;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FragmentDelegate {
    private static final CharSequence TAG = "FragmentDelegate";
    private GalleryActivity mActivity;
    private final Stack<String> mFragmentTagStack;
    private DefaultGestureDetector mGestureDetector;

    public FragmentDelegate(GalleryActivity galleryActivity) {
        Stack<String> stack = new Stack<>();
        this.mFragmentTagStack = stack;
        this.mActivity = galleryActivity;
        galleryActivity.getBlackboard().publishIfEmpty("debug://FragmentStack", stack);
        updateStateCallback();
    }

    private boolean finishFragmentAllowingStateLoss() {
        try {
            String topFragmentTag = getTopFragmentTag();
            this.mActivity.callSuperOnActivityResult();
            getSupportFragmentManager().popBackStackImmediate();
            popFragmentTag();
            Log.d(TAG, "finishFragmentAllowingStateLoss", topFragmentTag);
            return true;
        } catch (IllegalStateException e) {
            CharSequence charSequence = TAG;
            Log.e(charSequence, "finishFragmentAllowingStateLoss failed e=" + e.getMessage());
            return false;
        }
    }

    private void finishPopupFragment() {
        this.mActivity.getBlackboard().post("command://RemoveURL", BlackboardUtils.readLocationKeyCurrent(this.mActivity.getBlackboard()));
        finishFragment();
    }

    private void finishVolatileFragment(IBaseFragment iBaseFragment) {
        while (iBaseFragment != null && iBaseFragment.isVolatileFragment()) {
            CharSequence charSequence = TAG;
            Log.w(charSequence, "isVolatile : " + iBaseFragment);
            finishPopupFragment();
            iBaseFragment = getTopFragment();
        }
    }

    private FragmentManager getSupportFragmentManager() {
        return this.mActivity.getSupportFragmentManager();
    }

    private void handleBroadcastEventByChild(Fragment fragment, EventMessage eventMessage) {
        IBaseFragment focusedChild = ((IBaseFragment) fragment).getFocusedChild();
        if (focusedChild != null) {
            handleEvent(focusedChild, eventMessage);
            return;
        }
        for (Fragment next : fragment.getChildFragmentManager().getFragments()) {
            if (next instanceof IBaseFragment) {
                handleEvent((IBaseFragment) next, eventMessage);
            }
        }
    }

    private boolean handleEvent(IBaseFragment iBaseFragment, EventMessage eventMessage) {
        if (iBaseFragment.isDestroyed() || !iBaseFragment.onHandleEvent(eventMessage)) {
            return false;
        }
        return true;
    }

    private boolean handleEventByChild(IBaseFragment iBaseFragment, EventMessage eventMessage) {
        IBaseFragment focusedChild = iBaseFragment.getFocusedChild();
        if (focusedChild == null) {
            List<Fragment> childFragments = iBaseFragment.getChildFragments();
            if (childFragments != null && !childFragments.isEmpty()) {
                Collections.reverse(childFragments);
                for (Fragment next : childFragments) {
                    if ((next instanceof IBaseFragment) && handleEvent((IBaseFragment) next, eventMessage)) {
                        return true;
                    }
                }
            }
        } else if (handleEvent(focusedChild, eventMessage)) {
            return true;
        }
        return false;
    }

    private boolean isSearchToolbarFocused(IBaseListView iBaseListView) {
        Boolean bool;
        if (iBaseListView == null || iBaseListView.getEventContext() == null) {
            bool = null;
        } else {
            bool = (Boolean) iBaseListView.getEventContext().getEventContextData("searchToolbarFocus");
        }
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$isBackPressCallbackAvailable$1(MvpBaseFragment mvpBaseFragment) {
        return mvpBaseFragment instanceof IBaseListView;
    }

    private void pushFragmentTag(String str) {
        this.mFragmentTagStack.push(str);
        if (!PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            this.mActivity.setBackPressedCallbackEnabled(str.split(com.samsung.android.sdk.scs.base.utils.Log.TAG_SEPARATOR)[0]);
        }
        traceFragmentTag();
    }

    private void restoreLastFragment() {
        if (!SeApiCompat.isScreenLocked(this.mActivity)) {
            this.mActivity.getBlackboard().post("command://MoveURL", LocationKey.getCurrentLocation());
        }
    }

    private void setFragmentAnimation(IBaseFragment iBaseFragment, IBaseFragment iBaseFragment2, FragmentTransaction fragmentTransaction) {
        if (!iBaseFragment2.supportEnterDefaultTransition() || iBaseFragment == null || !iBaseFragment.supportExitDefaultTransition()) {
            iBaseFragment2.setCustomAnimations(fragmentTransaction, iBaseFragment);
            return;
        }
        iBaseFragment.reservePreventNoBackground(true);
        iBaseFragment2.reservePreventNoBackground(true);
        if (!PreferenceFeatures.OneUi7x.FRAGMENT_H_SLIDE_ANIMATION) {
            fragmentTransaction.setCustomAnimations(R.anim.depth_in_enter, 0, R.anim.depth_out_exit, 0);
        } else if (PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            fragmentTransaction.setCustomAnimations(R.animator.sesl_fragment_open_enter, R.animator.sesl_fragment_open_exit, R.animator.sesl_fragment_close_enter, R.animator.sesl_fragment_close_exit);
        } else {
            fragmentTransaction.setCustomAnimations(R.anim.fragment_open_enter, R.anim.fragment_open_exit, R.anim.fragment_close_enter, R.anim.fragment_close_exit);
        }
    }

    private boolean supportPredictiveBack(IMvpBaseView iMvpBaseView) {
        if (iMvpBaseView == null || !iMvpBaseView.supportExitPredictiveBack()) {
            return false;
        }
        return true;
    }

    private void updateStateCallback() {
        Sbixby.getStateHandler().updateStateChange(new StateHandler.Callback() {
            public List<String> getUsedPermissionsWhenAppStateRequested() {
                return BixbyAppStateUtil.getUsedPermissions();
            }

            public String onAppStateRequested() {
                String str;
                IBaseFragment topFragment = FragmentDelegate.this.getTopFragment();
                if (topFragment != null) {
                    str = topFragment.getAppState();
                } else {
                    str = null;
                }
                if (str == null) {
                    return BixbyAppStateUtil.generateEmpty();
                }
                return str;
            }
        });
    }

    public boolean clearBackStackImmediate() {
        String str = this.mFragmentTagStack.get(0);
        if (this.mFragmentTagStack.size() <= 1 || (!str.startsWith("ListContainerFragment") && !str.startsWith("BottomTabFragment"))) {
            return false;
        }
        Log.d(TAG, "clearBackStackImmediate", Integer.valueOf(this.mFragmentTagStack.size()));
        while (!getTopFragmentTag().startsWith(str)) {
            finishFragmentAllowingStateLoss();
        }
        return true;
    }

    public boolean commitChildFragment(Fragment fragment, String str) {
        boolean z;
        IBaseFragment topFragment = getTopFragment();
        if (topFragment == null || !topFragment.commitChildFragment(fragment, str)) {
            z = false;
        } else {
            z = true;
        }
        if (!PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            this.mActivity.setBackPressedCallbackEnabled(str);
        }
        return z;
    }

    public boolean commitFragment(Fragment fragment, String str) {
        if (fragment instanceof IBaseFragment) {
            getSupportFragmentManager().executePendingTransactions();
            String str2 = str + com.samsung.android.sdk.scs.base.utils.Log.TAG_SEPARATOR + Integer.toHexString(fragment.hashCode());
            IBaseFragment topFragment = getTopFragment();
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            try {
                boolean applyTransaction = SharedTransition.applyTransaction(this.mActivity.getBlackboard(), beginTransaction);
                beginTransaction.setReorderingAllowed(applyTransaction);
                if (topFragment != null) {
                    topFragment.onPrePause(applyTransaction);
                }
                finishVolatileFragment(topFragment);
                setFragmentAnimation(topFragment, (IBaseFragment) fragment, beginTransaction);
                beginTransaction.replace(R.id.content, fragment, str2);
                if (!this.mFragmentTagStack.empty()) {
                    beginTransaction.addToBackStack((String) null);
                }
                pushFragmentTag(str2);
                Log.majorEvent(TAG, "commitFragment" + Logger.v(Integer.valueOf(this.mFragmentTagStack.size()), str2, Boolean.valueOf(applyTransaction)) + " " + Logger.toString(fragment));
                try {
                    beginTransaction.commitAllowingStateLoss();
                    return true;
                } catch (IllegalStateException e) {
                    Log.e(TAG, "commitFragment failed. move back", (Throwable) e);
                    popFragmentTag();
                    return false;
                }
            } catch (Exception unused) {
                Log.e(TAG, "commit fragment fail");
                return false;
            }
        } else {
            throw new AssertionError("fragment have to implement IBaseFragment");
        }
    }

    public void consumePendingTransactions() {
        try {
            getSupportFragmentManager().executePendingTransactions();
        } catch (Exception e) {
            CharSequence charSequence = TAG;
            Log.e(charSequence, "spend pending transaction failed, e=" + e.getMessage());
        }
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        IBaseFragment topFragment = getTopFragment();
        if (!isFragmentResumed(topFragment)) {
            Log.e(TAG, "Top Fragment not ready. ignore genericMotionEvent");
            return true;
        } else if (topFragment == null || !topFragment.onGenericMotionEvent(motionEvent)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        IBaseFragment topFragment;
        if (this.mGestureDetector == null) {
            return false;
        }
        if (BlackboardUtils.isViewerDragShrinkToCamera(this.mActivity.getBlackboard())) {
            return true;
        }
        if (this.mGestureDetector.isPenAction(motionEvent)) {
            IBaseFragment topFragment2 = getTopFragment();
            if (topFragment2 == null || !topFragment2.onPenEvent(motionEvent)) {
                return false;
            }
            return true;
        }
        if (this.mGestureDetector.isAdvancedMouseEventAction(motionEvent, this.mActivity) && (topFragment = getTopFragment()) != null) {
            topFragment.onAdvancedMouseEvent(motionEvent);
        }
        return this.mGestureDetector.onTouchEvent(motionEvent);
    }

    public void dump(PrintWriter printWriter) {
        Logger.dumpLog(printWriter, "============== dump of fragments ===========");
        Logger.dumpLog(printWriter, "TopFragment : " + getTopFragmentTag());
        Logger.dumpLog(printWriter, "mFragmentTagStack : " + this.mFragmentTagStack);
        Iterator<String> it = this.mFragmentTagStack.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next != null) {
                Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(next);
                if (findFragmentByTag instanceof IBaseFragment) {
                    ((IBaseFragment) findFragmentByTag).dump(printWriter, "");
                }
            }
        }
    }

    public boolean finishFragment() {
        try {
            getSupportFragmentManager().popBackStack();
            popFragmentTag();
            return true;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return finishFragmentAllowingStateLoss();
        }
    }

    public int getStackSize() {
        return this.mFragmentTagStack.size();
    }

    public IBaseFragment getTopFragment() {
        String topFragmentTag = getTopFragmentTag();
        if (topFragmentTag != null) {
            return (IBaseFragment) getSupportFragmentManager().findFragmentByTag(topFragmentTag);
        }
        return null;
    }

    public String getTopFragmentTag() {
        if (!this.mFragmentTagStack.empty()) {
            return this.mFragmentTagStack.peek();
        }
        return null;
    }

    public void handleBroadcastEventOnStack(EventMessage eventMessage) {
        for (int size = this.mFragmentTagStack.size() - 1; size >= 0; size--) {
            Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(this.mFragmentTagStack.get(size));
            if (findFragmentByTag != null) {
                handleBroadcastEventByChild(findFragmentByTag, eventMessage);
                handleEvent((IBaseFragment) findFragmentByTag, eventMessage);
            }
        }
    }

    public void handleEventOnStack(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (!(i2 == 3102 || i2 == 3101)) {
            Log.v(TAG, "handleEvent " + EventKey.getName(eventMessage.what));
        }
        int size = this.mFragmentTagStack.size() - 1;
        while (size >= 0) {
            IBaseFragment iBaseFragment = (IBaseFragment) getSupportFragmentManager().findFragmentByTag(this.mFragmentTagStack.get(size));
            if (iBaseFragment == null || (!handleEventByChild(iBaseFragment, eventMessage) && !handleEvent(iBaseFragment, eventMessage))) {
                size--;
            } else {
                return;
            }
        }
    }

    public boolean isBackPressCallbackAvailable(boolean z) {
        IBaseFragment topFragment = getTopFragment();
        boolean z3 = true ^ (topFragment != null && topFragment.supportExitPredictiveBack());
        if (z != z3) {
            Log.b(TAG, "isBackPressCallbackAvailable", Boolean.valueOf(z3), topFragment);
        }
        return z3;
    }

    public boolean isDialogShowing(String str) {
        ThreadUtil.assertUiThread("isDialogShowing");
        DialogFragment dialogFragment = (DialogFragment) getSupportFragmentManager().findFragmentByTag(str);
        if (dialogFragment == null || dialogFragment.getDialog() == null || !dialogFragment.getDialog().isShowing()) {
            return false;
        }
        return true;
    }

    public boolean isExistFragment(String str) {
        return this.mFragmentTagStack.stream().anyMatch(new C0392b(str, 0));
    }

    public boolean isFragmentResumed(IBaseFragment iBaseFragment) {
        if (iBaseFragment == null || iBaseFragment.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            return true;
        }
        return false;
    }

    public boolean isFragmentStarted(IBaseFragment iBaseFragment) {
        if (iBaseFragment == null || iBaseFragment.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            return true;
        }
        return false;
    }

    public boolean onBackPressedOnFragment() {
        IBaseFragment topFragment = getTopFragment();
        if (!isFragmentStarted(topFragment) && this.mActivity.isActivityResumed()) {
            CharSequence charSequence = TAG;
            Log.e(charSequence, "Top Fragment not ready. ignore back : " + topFragment);
            return true;
        } else if (topFragment != null && topFragment.isInputBlockedExceptBackKey()) {
            Log.e(TAG, "Top Fragment already blocked. ignore onKey");
            return true;
        } else if (topFragment == null || !topFragment.onBackPressed()) {
            return false;
        } else {
            return true;
        }
    }

    public void onDestroy() {
        if (Blackboard.getActivityBlackboardList().size() <= 1) {
            BixbyAppStateUtil.updateEmptyState(this.mActivity.TAG);
        }
        if (PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            getSupportFragmentManager().seslSetOnBackPressedCallback((FragmentManager.SeslOnBackPressedCallback) null);
        }
        this.mActivity = null;
        this.mGestureDetector = null;
    }

    public boolean onKeyOnFragment(int i2, KeyEvent keyEvent) {
        String str;
        IBaseFragment topFragment = getTopFragment();
        if (!isFragmentStarted(topFragment) && this.mActivity.isActivityResumed()) {
            Log.e(TAG, "Top Fragment not ready. ignore onKey");
            return true;
        } else if (topFragment == null) {
            return false;
        } else {
            if (i2 == 4) {
                str = "BackKeyBlock";
            } else {
                str = TAG + "_onKeyOnFragment : " + i2;
            }
            topFragment.setInputBlock(str);
            if (keyEvent.isLongPress()) {
                return topFragment.onKeyLongPress(i2, keyEvent);
            }
            int action = keyEvent.getAction();
            if (action == 0) {
                return topFragment.onKeyDown(i2, keyEvent);
            }
            if (action != 1) {
                return false;
            }
            return topFragment.onKeyUp(i2, keyEvent);
        }
    }

    public boolean onPreSyncFragmentStack() {
        if (!PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            return false;
        }
        IBaseFragment topFragment = getTopFragment();
        if (!isFragmentStarted(topFragment) && this.mActivity.isActivityResumed()) {
            Log.e(TAG, "Top Fragment not ready. onPreSyncFragmentStack failed");
            return true;
        } else if (topFragment == null || !topFragment.onPreSyncFragmentStack()) {
            return false;
        } else {
            return true;
        }
    }

    public void onResume() {
        if (this.mGestureDetector == null) {
            GalleryActivity galleryActivity = this.mActivity;
            this.mGestureDetector = new DefaultGestureDetector(galleryActivity, galleryActivity.getBlackboard());
        }
        updateStateCallback();
    }

    public void onTrimMemory(int i2) {
        if (i2 >= 40) {
            for (Fragment next : getSupportFragmentManager().getFragments()) {
                if (next instanceof ComponentCallbacks2) {
                    ((ComponentCallbacks2) next).onTrimMemory(i2);
                }
            }
        }
    }

    public void onWindowFocusChanged(boolean z) {
        if (z) {
            updateStateCallback();
        }
    }

    public void popFragmentTag() {
        String str;
        if (!this.mFragmentTagStack.empty()) {
            this.mFragmentTagStack.pop();
        }
        if (!PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            String str2 = null;
            if (this.mFragmentTagStack.isEmpty()) {
                str = null;
            } else {
                Stack<String> stack = this.mFragmentTagStack;
                str = stack.get(stack.size() - 1);
            }
            GalleryActivity galleryActivity = this.mActivity;
            if (!TextUtils.isEmpty(str)) {
                str2 = str.split(com.samsung.android.sdk.scs.base.utils.Log.TAG_SEPARATOR)[0];
            }
            galleryActivity.setBackPressedCallbackEnabled(str2);
        }
        traceFragmentTag();
    }

    public void restoreLast() {
        if (getTopFragment() == null && !this.mActivity.getBlackboard().isEmpty("location://variable/currentv1")) {
            Log.e(TAG, "recover current");
            restoreLastFragment();
        }
    }

    public void showDialog(DialogFragment dialogFragment, String str) {
        try {
            if (!isDialogShowing(str)) {
                getSupportFragmentManager().beginTransaction().add((Fragment) dialogFragment, str).commitAllowingStateLoss();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isBackPressCallbackAvailable(java.lang.String r14) {
        /*
            r13 = this;
            com.samsung.android.gallery.app.ui.IBaseFragment r0 = r13.getTopFragment()
            boolean r1 = r0 instanceof com.samsung.android.gallery.app.ui.container.abstraction.ITabView
            r2 = 1
            r3 = 0
            r4 = 0
            if (r1 == 0) goto L_0x0063
            r1 = r0
            com.samsung.android.gallery.app.ui.container.abstraction.ITabView r1 = (com.samsung.android.gallery.app.ui.container.abstraction.ITabView) r1
            com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment r1 = r1.getCurrentFragment()
            java.util.Optional r1 = java.util.Optional.ofNullable(r1)
            A4.j r5 = new A4.j
            r6 = 4
            r5.<init>(r6)
            java.util.Optional r1 = r1.filter(r5)
            java.lang.Object r1 = r1.orElse(r4)
            r4 = r1
            com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView r4 = (com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView) r4
            boolean r1 = r0 instanceof com.samsung.android.gallery.app.ui.container.abstraction.IListContainerView
            if (r1 == 0) goto L_0x0061
            if (r4 != 0) goto L_0x0043
            r1 = r0
            com.samsung.android.gallery.app.ui.container.abstraction.IListContainerView r1 = (com.samsung.android.gallery.app.ui.container.abstraction.IListContainerView) r1
            com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment r1 = r1.getCurrentFragment()
            if (r1 == 0) goto L_0x0043
            boolean r5 = r1.hasNestedContainer()
            if (r5 == 0) goto L_0x0043
            com.samsung.android.gallery.app.ui.IBaseFragment r1 = r1.getFocusedChild()
            com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView r1 = (com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView) r1
            r4 = r1
        L_0x0043:
            com.samsung.android.gallery.app.ui.container.abstraction.IListContainerView r0 = (com.samsung.android.gallery.app.ui.container.abstraction.IListContainerView) r0
            boolean r0 = r0.hasChildFragments()
            boolean r1 = r13.supportPredictiveBack(r4)
            r1 = r1 ^ r2
            r0 = r0 & r1
            if (r4 == 0) goto L_0x006b
            boolean r1 = r4.isDrawerMode()
            if (r1 != 0) goto L_0x006b
            java.lang.String r1 = r4.getLocationKey()
            boolean r1 = com.samsung.android.gallery.support.blackboard.key.LocationKey.isRootOfContainerExceptTab(r1)
            r0 = r0 | r1
            goto L_0x006b
        L_0x0061:
            r0 = r3
            goto L_0x006b
        L_0x0063:
            boolean r1 = r0 instanceof com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView
            if (r1 == 0) goto L_0x0061
            r4 = r0
            com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView r4 = (com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView) r4
            goto L_0x0061
        L_0x006b:
            int r1 = r13.getStackSize()
            if (r4 == 0) goto L_0x0079
            boolean r5 = r4.isSelectionMode()
            if (r5 == 0) goto L_0x0079
            r5 = r2
            goto L_0x007a
        L_0x0079:
            r5 = r3
        L_0x007a:
            if (r4 == 0) goto L_0x0084
            boolean r6 = r4.isMoveMode()
            if (r6 == 0) goto L_0x0084
            r6 = r2
            goto L_0x0085
        L_0x0084:
            r6 = r3
        L_0x0085:
            if (r4 == 0) goto L_0x008f
            boolean r7 = r4.isDrawerOpen()
            if (r7 == 0) goto L_0x008f
            r7 = r2
            goto L_0x0090
        L_0x008f:
            r7 = r3
        L_0x0090:
            if (r14 == 0) goto L_0x0093
            goto L_0x009d
        L_0x0093:
            com.samsung.android.gallery.app.activity.GalleryActivity r14 = r13.mActivity
            com.samsung.android.gallery.support.blackboard.Blackboard r14 = r14.getBlackboard()
            java.lang.String r14 = com.samsung.android.gallery.module.utils.BlackboardUtils.readLocationKeyCurrent(r14)
        L_0x009d:
            boolean r14 = com.samsung.android.gallery.support.blackboard.key.LocationKey.isContentViewer(r14)
            com.samsung.android.gallery.app.activity.GalleryActivity r8 = r13.mActivity
            com.samsung.android.gallery.support.blackboard.Blackboard r8 = r8.getBlackboard()
            boolean r8 = com.samsung.android.gallery.module.utils.PickerUtil.isPickerMode(r8)
            boolean r9 = r13.isSearchToolbarFocused(r4)
            if (r8 == 0) goto L_0x00c8
            com.samsung.android.gallery.app.activity.GalleryActivity r10 = r13.mActivity
            com.samsung.android.gallery.support.blackboard.Blackboard r10 = r10.getBlackboard()
            java.lang.String r11 = "data://picker_mode/stack_size"
            java.lang.Integer r12 = java.lang.Integer.valueOf(r3)
            java.lang.Object r10 = r10.read(r11, r12)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            goto L_0x00c9
        L_0x00c8:
            r10 = r3
        L_0x00c9:
            boolean r13 = r13.supportPredictiveBack(r4)
            if (r13 != 0) goto L_0x00d2
            if (r1 <= r2) goto L_0x00d2
            goto L_0x00e4
        L_0x00d2:
            if (r5 != 0) goto L_0x00e4
            if (r6 != 0) goto L_0x00e4
            if (r7 != 0) goto L_0x00e4
            if (r0 != 0) goto L_0x00e4
            if (r9 != 0) goto L_0x00e4
            if (r14 != 0) goto L_0x00e4
            if (r8 == 0) goto L_0x00e3
            if (r10 <= r2) goto L_0x00e3
            goto L_0x00e4
        L_0x00e3:
            return r3
        L_0x00e4:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.activity.FragmentDelegate.isBackPressCallbackAvailable(java.lang.String):boolean");
    }

    private void traceFragmentTag() {
    }
}
