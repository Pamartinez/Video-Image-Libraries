package androidx.fragment.app;

import Vf.O;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.activity.BackEventCompat;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.MultiWindowModeChangedInfo;
import androidx.core.app.OnMultiWindowModeChangedProvider;
import androidx.core.app.OnPictureInPictureModeChangedProvider;
import androidx.core.app.PictureInPictureModeChangedInfo;
import androidx.core.content.OnConfigurationChangedProvider;
import androidx.core.content.OnTrimMemoryProvider;
import androidx.core.util.Consumer;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.R$id;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import c0.C0086a;
import i.C0212a;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import ne.C1194l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FragmentManager {
    private static boolean DEBUG = false;
    static boolean USE_PREDICTIVE_BACK = true;
    ArrayList<BackStackRecord> mBackStack = new ArrayList<>();
    ArrayList<Object> mBackStackChangeListeners = new ArrayList<>();
    private final AtomicInteger mBackStackIndex = new AtomicInteger();
    private final Map<String, BackStackState> mBackStackStates = Collections.synchronizedMap(new HashMap());
    private FragmentContainer mContainer;
    private ArrayList<Fragment> mCreatedMenus;
    int mCurState = -1;
    private SpecialEffectsControllerFactory mDefaultSpecialEffectsControllerFactory = new SpecialEffectsControllerFactory() {
        public SpecialEffectsController createController(ViewGroup viewGroup) {
            return new DefaultSpecialEffectsController(viewGroup);
        }
    };
    private boolean mDestroyed;
    private Runnable mExecCommit = new Runnable() {
        public void run() {
            FragmentManager.this.execPendingActions(true);
        }
    };
    private boolean mExecutingActions;
    private FragmentFactory mFragmentFactory = null;
    /* access modifiers changed from: private */
    public final FragmentStore mFragmentStore = new FragmentStore();
    boolean mHandlingTransitioningOp = false;
    private boolean mHavePendingDeferredStart;
    private FragmentHostCallback<?> mHost;
    private FragmentFactory mHostFragmentFactory = new FragmentFactory() {
        public Fragment instantiate(ClassLoader classLoader, String str) {
            return FragmentManager.this.getHost().instantiate(FragmentManager.this.getHost().getContext(), str, (Bundle) null);
        }
    };
    ArrayDeque<LaunchedFragmentInfo> mLaunchedFragments = new ArrayDeque<>();
    private final FragmentLayoutInflaterFactory mLayoutInflaterFactory = new FragmentLayoutInflaterFactory(this);
    private final FragmentLifecycleCallbacksDispatcher mLifecycleCallbacksDispatcher = new FragmentLifecycleCallbacksDispatcher(this);
    private final MenuProvider mMenuProvider = new MenuProvider() {
        public void onCreateMenu(Menu menu, MenuInflater menuInflater) {
            FragmentManager.this.dispatchCreateOptionsMenu(menu, menuInflater);
        }

        public void onMenuClosed(Menu menu) {
            FragmentManager.this.dispatchOptionsMenuClosed(menu);
        }

        public boolean onMenuItemSelected(MenuItem menuItem) {
            return FragmentManager.this.dispatchOptionsItemSelected(menuItem);
        }

        public void onPrepareMenu(Menu menu) {
            FragmentManager.this.dispatchPrepareOptionsMenu(menu);
        }
    };
    private boolean mNeedMenuInvalidate;
    private FragmentManagerViewModel mNonConfig;
    private final CopyOnWriteArrayList<FragmentOnAttachListener> mOnAttachListeners = new CopyOnWriteArrayList<>();
    private final OnBackPressedCallback mOnBackPressedCallback = new OnBackPressedCallback(false) {
        public void handleOnBackCancelled() {
            if (FragmentManager.isLoggingEnabled(3)) {
                Log.d("FragmentManager", "handleOnBackCancelled. PREDICTIVE_BACK = " + FragmentManager.USE_PREDICTIVE_BACK + " fragment manager " + FragmentManager.this);
            }
            if (FragmentManager.USE_PREDICTIVE_BACK) {
                FragmentManager.this.cancelBackStackTransition();
            }
            if (FragmentManager.this.mSeslOnBackPressedCallback != null) {
                FragmentManager.this.mSeslOnBackPressedCallback.handleOnBackCancelled();
            }
        }

        public void handleOnBackPressed() {
            if (FragmentManager.isLoggingEnabled(3)) {
                Log.d("FragmentManager", "handleOnBackPressed. PREDICTIVE_BACK = " + FragmentManager.USE_PREDICTIVE_BACK + " fragment manager " + FragmentManager.this);
            }
            FragmentManager.this.handleOnBackPressed(true);
            if (FragmentManager.this.mSeslOnBackPressedCallback != null) {
                FragmentManager.this.mSeslOnBackPressedCallback.handleOnBackPressed();
            }
        }

        public void handleOnBackProgressed(BackEventCompat backEventCompat) {
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "handleOnBackProgressed. PREDICTIVE_BACK = " + FragmentManager.USE_PREDICTIVE_BACK + " fragment manager " + FragmentManager.this);
            }
            FragmentManager fragmentManager = FragmentManager.this;
            if (fragmentManager.mTransitioningOp != null) {
                for (SpecialEffectsController processProgress : fragmentManager.collectChangedControllers(new ArrayList(Collections.singletonList(FragmentManager.this.mTransitioningOp)), 0, 1)) {
                    processProgress.processProgress(backEventCompat);
                }
                Iterator<Object> it = FragmentManager.this.mBackStackChangeListeners.iterator();
                if (it.hasNext()) {
                    throw C0212a.h(it);
                } else if (FragmentManager.this.mSeslOnBackPressedCallback != null) {
                    FragmentManager.this.mSeslOnBackPressedCallback.handleOnBackProgressed(backEventCompat);
                }
            }
        }

        public void handleOnBackStarted(BackEventCompat backEventCompat) {
            if (FragmentManager.isLoggingEnabled(3)) {
                Log.d("FragmentManager", "handleOnBackStarted. PREDICTIVE_BACK = " + FragmentManager.USE_PREDICTIVE_BACK + " fragment manager " + FragmentManager.this);
            }
            if (FragmentManager.USE_PREDICTIVE_BACK) {
                FragmentManager.this.endAnimatingAwayFragments();
                FragmentManager.this.prepareBackStackTransition();
            }
            if (FragmentManager.this.mSeslOnBackPressedCallback != null) {
                FragmentManager.this.mSeslOnBackPressedCallback.handleOnBackStarted(backEventCompat);
            }
        }
    };
    private OnBackPressedDispatcher mOnBackPressedDispatcher;
    private final Consumer<Configuration> mOnConfigurationChangedListener = new i(this, 0);
    private final Consumer<MultiWindowModeChangedInfo> mOnMultiWindowModeChangedListener = new i(this, 2);
    private final Consumer<PictureInPictureModeChangedInfo> mOnPictureInPictureModeChangedListener = new i(this, 3);
    private final Consumer<Integer> mOnTrimMemoryListener = new i(this, 1);
    private Fragment mParent;
    private final ArrayList<OpGenerator> mPendingActions = new ArrayList<>();
    Fragment mPrimaryNav;
    private ActivityResultLauncher<String[]> mRequestPermissions;
    /* access modifiers changed from: private */
    public final Map<String, Object> mResultListeners = Collections.synchronizedMap(new HashMap());
    /* access modifiers changed from: private */
    public final Map<String, Bundle> mResults = Collections.synchronizedMap(new HashMap());
    /* access modifiers changed from: private */
    public SeslOnBackPressedCallback mSeslOnBackPressedCallback;
    private SpecialEffectsControllerFactory mSpecialEffectsControllerFactory = null;
    private ActivityResultLauncher<Intent> mStartActivityForResult;
    private ActivityResultLauncher<IntentSenderRequest> mStartIntentSenderForResult;
    private boolean mStateSaved;
    private boolean mStopped;
    private FragmentStrictMode.Policy mStrictModePolicy;
    private ArrayList<Fragment> mTmpAddedFragments;
    private ArrayList<Boolean> mTmpIsPop;
    private ArrayList<BackStackRecord> mTmpRecords;
    BackStackRecord mTransitioningOp = null;
    private boolean mUseAutoClearOnLastStack = true;

    /* renamed from: androidx.fragment.app.FragmentManager$6  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    class AnonymousClass6 implements LifecycleEventObserver {
        final /* synthetic */ FragmentManager this$0;
        final /* synthetic */ Lifecycle val$lifecycle;
        final /* synthetic */ String val$requestKey;

        public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START && ((Bundle) this.this$0.mResults.get(this.val$requestKey)) != null) {
                throw null;
            } else if (event == Lifecycle.Event.ON_DESTROY) {
                this.val$lifecycle.removeObserver(this);
                this.this$0.mResultListeners.remove(this.val$requestKey);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface BackStackEntry {
        String getName();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FragmentIntentSenderContract extends ActivityResultContract<IntentSenderRequest, ActivityResult> {
        public Intent createIntent(Context context, IntentSenderRequest intentSenderRequest) {
            Bundle bundleExtra;
            Intent intent = new Intent("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST");
            Intent fillInIntent = intentSenderRequest.getFillInIntent();
            if (!(fillInIntent == null || (bundleExtra = fillInIntent.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) == null)) {
                intent.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundleExtra);
                fillInIntent.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                if (fillInIntent.getBooleanExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", false)) {
                    intentSenderRequest = new IntentSenderRequest.Builder(intentSenderRequest.getIntentSender()).setFillInIntent((Intent) null).setFlags(intentSenderRequest.getFlagsValues(), intentSenderRequest.getFlagsMask()).build();
                }
            }
            intent.putExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST", intentSenderRequest);
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "CreateIntent created the following intent: " + intent);
            }
            return intent;
        }

        public ActivityResult parseResult(int i2, Intent intent) {
            return new ActivityResult(i2, intent);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OpGenerator {
        boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PopBackStackState implements OpGenerator {
        final int mFlags;
        final int mId;
        final String mName;

        public PopBackStackState(String str, int i2, int i7) {
            this.mName = str;
            this.mId = i2;
            this.mFlags = i7;
        }

        public boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
            Fragment fragment = FragmentManager.this.mPrimaryNav;
            if (fragment != null && this.mId < 0 && this.mName == null && fragment.getChildFragmentManager().popBackStackImmediate()) {
                return false;
            }
            return FragmentManager.this.popBackStackState(arrayList, arrayList2, this.mName, this.mId, this.mFlags);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PrepareBackStackTransitionState implements OpGenerator {
        public PrepareBackStackTransitionState() {
        }

        public boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
            boolean prepareBackStackState = FragmentManager.this.prepareBackStackState(arrayList, arrayList2);
            if (!FragmentManager.this.mBackStackChangeListeners.isEmpty() && arrayList.size() > 0) {
                arrayList2.get(arrayList.size() - 1).getClass();
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                Iterator<BackStackRecord> it = arrayList.iterator();
                while (it.hasNext()) {
                    linkedHashSet.addAll(FragmentManager.this.fragmentsFromRecord(it.next()));
                }
                Iterator<Object> it2 = FragmentManager.this.mBackStackChangeListeners.iterator();
                while (it2.hasNext()) {
                    if (it2.next() == null) {
                        Iterator it3 = linkedHashSet.iterator();
                        if (it3.hasNext()) {
                            Fragment fragment = (Fragment) it3.next();
                            throw null;
                        }
                    } else {
                        throw new ClassCastException();
                    }
                }
            }
            return prepareBackStackState;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SeslOnBackPressedCallback {
        void handleOnBackCancelled();

        void handleOnBackPressed();

        void handleOnBackProgressed(BackEventCompat backEventCompat);

        void handleOnBackStarted(BackEventCompat backEventCompat);
    }

    private void checkStateLoss() {
        if (isStateSaved()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    private void cleanupExec() {
        this.mExecutingActions = false;
        this.mTmpIsPop.clear();
        this.mTmpRecords.clear();
    }

    private void clearBackStackStateViewModels() {
        boolean z;
        FragmentHostCallback<?> fragmentHostCallback = this.mHost;
        if (fragmentHostCallback instanceof ViewModelStoreOwner) {
            z = this.mFragmentStore.getNonConfig().isCleared();
        } else if (fragmentHostCallback.getContext() instanceof Activity) {
            z = !((Activity) this.mHost.getContext()).isChangingConfigurations();
        } else {
            z = true;
        }
        if (z) {
            for (BackStackState backStackState : this.mBackStackStates.values()) {
                for (String clearNonConfigState : backStackState.mFragments) {
                    this.mFragmentStore.getNonConfig().clearNonConfigState(clearNonConfigState, false);
                }
            }
        }
    }

    private Set<SpecialEffectsController> collectAllSpecialEffectsController() {
        HashSet hashSet = new HashSet();
        for (FragmentStateManager fragment : this.mFragmentStore.getActiveFragmentStateManagers()) {
            ViewGroup viewGroup = fragment.getFragment().mContainer;
            if (viewGroup != null) {
                hashSet.add(SpecialEffectsController.getOrCreateController(viewGroup, getSpecialEffectsControllerFactory()));
            }
        }
        return hashSet;
    }

    private void dispatchParentPrimaryNavigationFragmentChanged(Fragment fragment) {
        if (fragment != null && fragment.equals(findActiveFragment(fragment.mWho))) {
            fragment.performPrimaryNavigationFragmentChanged();
        }
    }

    /* JADX INFO: finally extract failed */
    private void dispatchStateChange(int i2) {
        try {
            this.mExecutingActions = true;
            this.mFragmentStore.dispatchStateChange(i2);
            moveToState(i2, false);
            for (SpecialEffectsController forceCompleteAllOperations : collectAllSpecialEffectsController()) {
                forceCompleteAllOperations.forceCompleteAllOperations();
            }
            this.mExecutingActions = false;
            execPendingActions(true);
        } catch (Throwable th) {
            this.mExecutingActions = false;
            throw th;
        }
    }

    private void doPendingDeferredStart() {
        if (this.mHavePendingDeferredStart) {
            this.mHavePendingDeferredStart = false;
            startPendingDeferredFragments();
        }
    }

    public static void enablePredictiveBack(boolean z) {
        USE_PREDICTIVE_BACK = z;
    }

    /* access modifiers changed from: private */
    public void endAnimatingAwayFragments() {
        for (SpecialEffectsController forceCompleteAllOperations : collectAllSpecialEffectsController()) {
            forceCompleteAllOperations.forceCompleteAllOperations();
        }
    }

    private void ensureExecReady(boolean z) {
        if (this.mExecutingActions) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        } else if (this.mHost == null) {
            if (this.mDestroyed) {
                throw new IllegalStateException("FragmentManager has been destroyed");
            }
            throw new IllegalStateException("FragmentManager has not been attached to a host.");
        } else if (Looper.myLooper() == this.mHost.getHandler().getLooper()) {
            if (!z) {
                checkStateLoss();
            }
            if (this.mTmpRecords == null) {
                this.mTmpRecords = new ArrayList<>();
                this.mTmpIsPop = new ArrayList<>();
            }
        } else {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
    }

    private static void executeOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i2, int i7) {
        while (i2 < i7) {
            BackStackRecord backStackRecord = arrayList.get(i2);
            if (arrayList2.get(i2).booleanValue()) {
                backStackRecord.bumpBackStackNesting(-1);
                backStackRecord.executePopOps();
            } else {
                backStackRecord.bumpBackStackNesting(1);
                backStackRecord.executeOps();
            }
            i2++;
        }
    }

    private void executeOpsTogether(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i2, int i7) {
        boolean z = arrayList.get(i2).mReorderingAllowed;
        ArrayList<Fragment> arrayList3 = this.mTmpAddedFragments;
        if (arrayList3 == null) {
            this.mTmpAddedFragments = new ArrayList<>();
        } else {
            arrayList3.clear();
        }
        this.mTmpAddedFragments.addAll(this.mFragmentStore.getFragments());
        Fragment primaryNavigationFragment = getPrimaryNavigationFragment();
        boolean z3 = false;
        for (int i8 = i2; i8 < i7; i8++) {
            BackStackRecord backStackRecord = arrayList.get(i8);
            if (!arrayList2.get(i8).booleanValue()) {
                primaryNavigationFragment = backStackRecord.expandOps(this.mTmpAddedFragments, primaryNavigationFragment);
            } else {
                primaryNavigationFragment = backStackRecord.trackAddedFragmentsInPop(this.mTmpAddedFragments, primaryNavigationFragment);
            }
            if (z3 || backStackRecord.mAddToBackStack) {
                z3 = true;
            } else {
                z3 = false;
            }
        }
        this.mTmpAddedFragments.clear();
        if (!z && this.mCurState >= 1) {
            for (int i10 = i2; i10 < i7; i10++) {
                Iterator<FragmentTransaction.Op> it = arrayList.get(i10).mOps.iterator();
                while (it.hasNext()) {
                    Fragment fragment = it.next().mFragment;
                    if (!(fragment == null || fragment.mFragmentManager == null)) {
                        this.mFragmentStore.makeActive(createOrGetFragmentStateManager(fragment));
                    }
                }
            }
        }
        executeOps(arrayList, arrayList2, i2, i7);
        boolean booleanValue = arrayList2.get(i7 - 1).booleanValue();
        if (z3 && !this.mBackStackChangeListeners.isEmpty()) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator<BackStackRecord> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                linkedHashSet.addAll(fragmentsFromRecord(it2.next()));
            }
            if (this.mTransitioningOp == null) {
                Iterator<Object> it3 = this.mBackStackChangeListeners.iterator();
                while (it3.hasNext()) {
                    if (it3.next() == null) {
                        Iterator it4 = linkedHashSet.iterator();
                        if (it4.hasNext()) {
                            Fragment fragment2 = (Fragment) it4.next();
                            throw null;
                        }
                    } else {
                        throw new ClassCastException();
                    }
                }
                Iterator<Object> it5 = this.mBackStackChangeListeners.iterator();
                while (it5.hasNext()) {
                    if (it5.next() == null) {
                        Iterator it6 = linkedHashSet.iterator();
                        if (it6.hasNext()) {
                            Fragment fragment3 = (Fragment) it6.next();
                            throw null;
                        }
                    } else {
                        throw new ClassCastException();
                    }
                }
            }
        }
        for (int i11 = i2; i11 < i7; i11++) {
            BackStackRecord backStackRecord2 = arrayList.get(i11);
            if (booleanValue) {
                for (int size = backStackRecord2.mOps.size() - 1; size >= 0; size--) {
                    Fragment fragment4 = backStackRecord2.mOps.get(size).mFragment;
                    if (fragment4 != null) {
                        createOrGetFragmentStateManager(fragment4).moveToExpectedState();
                    }
                }
            } else {
                Iterator<FragmentTransaction.Op> it7 = backStackRecord2.mOps.iterator();
                while (it7.hasNext()) {
                    Fragment fragment5 = it7.next().mFragment;
                    if (fragment5 != null) {
                        createOrGetFragmentStateManager(fragment5).moveToExpectedState();
                    }
                }
            }
        }
        moveToState(this.mCurState, true);
        for (SpecialEffectsController next : collectChangedControllers(arrayList, i2, i7)) {
            next.updateOperationDirection(booleanValue);
            next.markPostponedState();
            next.executePendingOperations();
        }
        while (i2 < i7) {
            BackStackRecord backStackRecord3 = arrayList.get(i2);
            if (arrayList2.get(i2).booleanValue() && backStackRecord3.mIndex >= 0) {
                backStackRecord3.mIndex = -1;
            }
            backStackRecord3.runOnCommitRunnables();
            i2++;
        }
        if (z3) {
            reportBackStackChanged();
        }
    }

    private int findBackStackIndex(String str, int i2, boolean z) {
        if (this.mBackStack.isEmpty()) {
            return -1;
        }
        if (str != null || i2 >= 0) {
            int size = this.mBackStack.size() - 1;
            while (size >= 0) {
                BackStackRecord backStackRecord = this.mBackStack.get(size);
                if ((str != null && str.equals(backStackRecord.getName())) || (i2 >= 0 && i2 == backStackRecord.mIndex)) {
                    break;
                }
                size--;
            }
            if (size < 0) {
                return size;
            }
            if (z) {
                while (size > 0) {
                    BackStackRecord backStackRecord2 = this.mBackStack.get(size - 1);
                    if ((str == null || !str.equals(backStackRecord2.getName())) && (i2 < 0 || i2 != backStackRecord2.mIndex)) {
                        break;
                    }
                    size--;
                }
                return size;
            } else if (size == this.mBackStack.size() - 1) {
                return -1;
            } else {
                return size + 1;
            }
        } else if (z) {
            return 0;
        } else {
            return this.mBackStack.size() - 1;
        }
    }

    public static FragmentManager findFragmentManager(View view) {
        FragmentActivity fragmentActivity;
        Fragment findViewFragment = findViewFragment(view);
        if (findViewFragment == null) {
            Context context = view.getContext();
            while (true) {
                if (!(context instanceof ContextWrapper)) {
                    fragmentActivity = null;
                    break;
                } else if (context instanceof FragmentActivity) {
                    fragmentActivity = (FragmentActivity) context;
                    break;
                } else {
                    context = ((ContextWrapper) context).getBaseContext();
                }
            }
            if (fragmentActivity != null) {
                return fragmentActivity.getSupportFragmentManager();
            }
            throw new IllegalStateException("View " + view + " is not within a subclass of FragmentActivity.");
        } else if (findViewFragment.isAdded()) {
            return findViewFragment.getChildFragmentManager();
        } else {
            throw new IllegalStateException("The Fragment " + findViewFragment + " that owns View " + view + " has already been destroyed. Nested fragments should always use the child FragmentManager.");
        }
    }

    public static Fragment findViewFragment(View view) {
        while (view != null) {
            Fragment viewFragment = getViewFragment(view);
            if (viewFragment != null) {
                return viewFragment;
            }
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                view = (View) parent;
            } else {
                view = null;
            }
        }
        return null;
    }

    private void forcePostponedTransactions() {
        for (SpecialEffectsController forcePostponedExecutePendingOperations : collectAllSpecialEffectsController()) {
            forcePostponedExecutePendingOperations.forcePostponedExecutePendingOperations();
        }
    }

    private boolean generateOpsForPendingActions(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
        synchronized (this.mPendingActions) {
            if (this.mPendingActions.isEmpty()) {
                return false;
            }
            try {
                int size = this.mPendingActions.size();
                boolean z = false;
                for (int i2 = 0; i2 < size; i2++) {
                    z |= this.mPendingActions.get(i2).generateOps(arrayList, arrayList2);
                }
                return z;
            } finally {
                this.mPendingActions.clear();
                this.mHost.getHandler().removeCallbacks(this.mExecCommit);
            }
        }
    }

    private FragmentManagerViewModel getChildNonConfig(Fragment fragment) {
        return this.mNonConfig.getChildNonConfig(fragment);
    }

    private ViewGroup getFragmentContainer(Fragment fragment) {
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup != null) {
            return viewGroup;
        }
        if (fragment.mContainerId > 0 && this.mContainer.onHasView()) {
            View onFindViewById = this.mContainer.onFindViewById(fragment.mContainerId);
            if (onFindViewById instanceof ViewGroup) {
                return (ViewGroup) onFindViewById;
            }
        }
        return null;
    }

    public static Fragment getViewFragment(View view) {
        Object tag = view.getTag(R$id.fragment_container_view_tag);
        if (tag instanceof Fragment) {
            return (Fragment) tag;
        }
        return null;
    }

    public static boolean isLoggingEnabled(int i2) {
        if (DEBUG || Log.isLoggable("FragmentManager", i2)) {
            return true;
        }
        return false;
    }

    private boolean isMenuAvailable(Fragment fragment) {
        if ((!fragment.mHasMenu || !fragment.mMenuVisible) && !fragment.mChildFragmentManager.checkForMenus()) {
            return false;
        }
        return true;
    }

    private boolean isParentAdded() {
        Fragment fragment = this.mParent;
        if (fragment == null) {
            return true;
        }
        if (!fragment.isAdded() || !this.mParent.getParentFragmentManager().isParentAdded()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$cancelBackStackTransition$4() {
        Iterator<Object> it = this.mBackStackChangeListeners.iterator();
        if (it.hasNext()) {
            throw C0212a.h(it);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Configuration configuration) {
        if (isParentAdded()) {
            dispatchConfigurationChanged(configuration, false);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(Integer num) {
        if (isParentAdded() && num.intValue() == 80) {
            dispatchLowMemory(false);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2(MultiWindowModeChangedInfo multiWindowModeChangedInfo) {
        if (isParentAdded()) {
            dispatchMultiWindowModeChanged(multiWindowModeChangedInfo.isInMultiWindowMode(), false);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$3(PictureInPictureModeChangedInfo pictureInPictureModeChangedInfo) {
        if (isParentAdded()) {
            dispatchPictureInPictureModeChanged(pictureInPictureModeChangedInfo.isInPictureInPictureMode(), false);
        }
    }

    private void removeRedundantOperationsAndExecute(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
        if (!arrayList.isEmpty()) {
            if (arrayList.size() == arrayList2.size()) {
                int size = arrayList.size();
                int i2 = 0;
                int i7 = 0;
                while (i2 < size) {
                    if (!arrayList.get(i2).mReorderingAllowed) {
                        if (i7 != i2) {
                            executeOpsTogether(arrayList, arrayList2, i7, i2);
                        }
                        i7 = i2 + 1;
                        if (arrayList2.get(i2).booleanValue()) {
                            while (i7 < size && arrayList2.get(i7).booleanValue() && !arrayList.get(i7).mReorderingAllowed) {
                                i7++;
                            }
                        }
                        executeOpsTogether(arrayList, arrayList2, i2, i7);
                        i2 = i7 - 1;
                    }
                    i2++;
                }
                if (i7 != size) {
                    executeOpsTogether(arrayList, arrayList2, i7, size);
                    return;
                }
                return;
            }
            throw new IllegalStateException("Internal error with the back stack records");
        }
    }

    private void reportBackStackChanged() {
        if (this.mBackStackChangeListeners.size() > 0) {
            this.mBackStackChangeListeners.get(0).getClass();
            throw new ClassCastException();
        }
    }

    public static int reverseTransit(int i2) {
        if (i2 == 4097) {
            return 8194;
        }
        if (i2 == 8194) {
            return 4097;
        }
        if (i2 == 8197) {
            return 4100;
        }
        if (i2 == 4099) {
            return 4099;
        }
        if (i2 != 4100) {
            return 0;
        }
        return 8197;
    }

    private void setVisibleRemovingFragment(Fragment fragment) {
        ViewGroup fragmentContainer = getFragmentContainer(fragment);
        if (fragmentContainer != null) {
            if (fragment.getPopExitAnim() + fragment.getPopEnterAnim() + fragment.getExitAnim() + fragment.getEnterAnim() > 0) {
                int i2 = R$id.visible_removing_fragment_view_tag;
                if (fragmentContainer.getTag(i2) == null) {
                    fragmentContainer.setTag(i2, fragment);
                }
                ((Fragment) fragmentContainer.getTag(i2)).setPopDirection(fragment.getPopDirection());
            }
        }
    }

    private void startPendingDeferredFragments() {
        for (FragmentStateManager performPendingDeferredStart : this.mFragmentStore.getActiveFragmentStateManagers()) {
            performPendingDeferredStart(performPendingDeferredStart);
        }
    }

    private void throwException(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
        FragmentHostCallback<?> fragmentHostCallback = this.mHost;
        if (fragmentHostCallback != null) {
            try {
                fragmentHostCallback.onDump("  ", (FileDescriptor) null, printWriter, new String[0]);
            } catch (Exception e) {
                Log.e("FragmentManager", "Failed dumping state", e);
            }
        } else {
            try {
                dump("  ", (FileDescriptor) null, printWriter, new String[0]);
            } catch (Exception e7) {
                Log.e("FragmentManager", "Failed dumping state", e7);
            }
        }
        throw runtimeException;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0034, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003a, code lost:
        if (getBackStackEntryCount() <= 0) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
        if (isPrimaryNavigation(r5.mParent) == false) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0045, code lost:
        r4 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004a, code lost:
        if (isLoggingEnabled(3) == false) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004c, code lost:
        android.util.Log.d("FragmentManager", "OnBackPressedCallback for FragmentManager " + r5 + " enabled state is " + r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0067, code lost:
        r5.mOnBackPressedCallback.setEnabled(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateOnBackPressedCallbackEnabled() {
        /*
            r5 = this;
            java.lang.String r0 = "FragmentManager "
            java.util.ArrayList<androidx.fragment.app.FragmentManager$OpGenerator> r1 = r5.mPendingActions
            monitor-enter(r1)
            java.util.ArrayList<androidx.fragment.app.FragmentManager$OpGenerator> r2 = r5.mPendingActions     // Catch:{ all -> 0x0031 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x0031 }
            r3 = 3
            r4 = 1
            if (r2 != 0) goto L_0x0035
            androidx.activity.OnBackPressedCallback r2 = r5.mOnBackPressedCallback     // Catch:{ all -> 0x0031 }
            r2.setEnabled(r4)     // Catch:{ all -> 0x0031 }
            boolean r2 = isLoggingEnabled(r3)     // Catch:{ all -> 0x0031 }
            if (r2 == 0) goto L_0x0033
            java.lang.String r2 = "FragmentManager"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0031 }
            r3.<init>(r0)     // Catch:{ all -> 0x0031 }
            r3.append(r5)     // Catch:{ all -> 0x0031 }
            java.lang.String r5 = " enabling OnBackPressedCallback, caused by non-empty pending actions"
            r3.append(r5)     // Catch:{ all -> 0x0031 }
            java.lang.String r5 = r3.toString()     // Catch:{ all -> 0x0031 }
            android.util.Log.d(r2, r5)     // Catch:{ all -> 0x0031 }
            goto L_0x0033
        L_0x0031:
            r5 = move-exception
            goto L_0x006d
        L_0x0033:
            monitor-exit(r1)     // Catch:{ all -> 0x0031 }
            return
        L_0x0035:
            monitor-exit(r1)     // Catch:{ all -> 0x0031 }
            int r0 = r5.getBackStackEntryCount()
            if (r0 <= 0) goto L_0x0045
            androidx.fragment.app.Fragment r0 = r5.mParent
            boolean r0 = r5.isPrimaryNavigation(r0)
            if (r0 == 0) goto L_0x0045
            goto L_0x0046
        L_0x0045:
            r4 = 0
        L_0x0046:
            boolean r0 = isLoggingEnabled(r3)
            if (r0 == 0) goto L_0x0067
            java.lang.String r0 = "FragmentManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "OnBackPressedCallback for FragmentManager "
            r1.<init>(r2)
            r1.append(r5)
            java.lang.String r2 = " enabled state is "
            r1.append(r2)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r0, r1)
        L_0x0067:
            androidx.activity.OnBackPressedCallback r5 = r5.mOnBackPressedCallback
            r5.setEnabled(r4)
            return
        L_0x006d:
            monitor-exit(r1)     // Catch:{ all -> 0x0031 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentManager.updateOnBackPressedCallbackEnabled():void");
    }

    public void addBackStackState(BackStackRecord backStackRecord) {
        this.mBackStack.add(backStackRecord);
    }

    public FragmentStateManager addFragment(Fragment fragment) {
        String str = fragment.mPreviousWho;
        if (str != null) {
            FragmentStrictMode.onFragmentReuse(fragment, str);
        }
        if (isLoggingEnabled(2)) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        FragmentStateManager createOrGetFragmentStateManager = createOrGetFragmentStateManager(fragment);
        fragment.mFragmentManager = this;
        this.mFragmentStore.makeActive(createOrGetFragmentStateManager);
        if (!fragment.mDetached) {
            this.mFragmentStore.addFragment(fragment);
            fragment.mRemoving = false;
            if (fragment.mView == null) {
                fragment.mHiddenChanged = false;
            }
            if (isMenuAvailable(fragment)) {
                this.mNeedMenuInvalidate = true;
            }
        }
        return createOrGetFragmentStateManager;
    }

    public void addFragmentOnAttachListener(FragmentOnAttachListener fragmentOnAttachListener) {
        this.mOnAttachListeners.add(fragmentOnAttachListener);
    }

    public int allocBackStackIndex() {
        return this.mBackStackIndex.getAndIncrement();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v26, resolved type: androidx.activity.OnBackPressedDispatcherOwner} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v27, resolved type: androidx.fragment.app.Fragment} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v28, resolved type: androidx.fragment.app.Fragment} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v33, resolved type: androidx.fragment.app.Fragment} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void attachController(androidx.fragment.app.FragmentHostCallback<?> r4, androidx.fragment.app.FragmentContainer r5, final androidx.fragment.app.Fragment r6) {
        /*
            r3 = this;
            androidx.fragment.app.FragmentHostCallback<?> r0 = r3.mHost
            if (r0 != 0) goto L_0x013b
            r3.mHost = r4
            r3.mContainer = r5
            r3.mParent = r6
            if (r6 == 0) goto L_0x0015
            androidx.fragment.app.FragmentManager$7 r5 = new androidx.fragment.app.FragmentManager$7
            r5.<init>(r6)
            r3.addFragmentOnAttachListener(r5)
            goto L_0x001f
        L_0x0015:
            boolean r5 = r4 instanceof androidx.fragment.app.FragmentOnAttachListener
            if (r5 == 0) goto L_0x001f
            r5 = r4
            androidx.fragment.app.FragmentOnAttachListener r5 = (androidx.fragment.app.FragmentOnAttachListener) r5
            r3.addFragmentOnAttachListener(r5)
        L_0x001f:
            androidx.fragment.app.Fragment r5 = r3.mParent
            if (r5 == 0) goto L_0x0026
            r3.updateOnBackPressedCallbackEnabled()
        L_0x0026:
            boolean r5 = r4 instanceof androidx.activity.OnBackPressedDispatcherOwner
            if (r5 == 0) goto L_0x003b
            r5 = r4
            androidx.activity.OnBackPressedDispatcherOwner r5 = (androidx.activity.OnBackPressedDispatcherOwner) r5
            androidx.activity.OnBackPressedDispatcher r0 = r5.getOnBackPressedDispatcher()
            r3.mOnBackPressedDispatcher = r0
            if (r6 == 0) goto L_0x0036
            r5 = r6
        L_0x0036:
            androidx.activity.OnBackPressedCallback r1 = r3.mOnBackPressedCallback
            r0.addCallback(r5, r1)
        L_0x003b:
            if (r6 == 0) goto L_0x0046
            androidx.fragment.app.FragmentManager r4 = r6.mFragmentManager
            androidx.fragment.app.FragmentManagerViewModel r4 = r4.getChildNonConfig(r6)
            r3.mNonConfig = r4
            goto L_0x005f
        L_0x0046:
            boolean r5 = r4 instanceof androidx.lifecycle.ViewModelStoreOwner
            if (r5 == 0) goto L_0x0057
            androidx.lifecycle.ViewModelStoreOwner r4 = (androidx.lifecycle.ViewModelStoreOwner) r4
            androidx.lifecycle.ViewModelStore r4 = r4.getViewModelStore()
            androidx.fragment.app.FragmentManagerViewModel r4 = androidx.fragment.app.FragmentManagerViewModel.getInstance(r4)
            r3.mNonConfig = r4
            goto L_0x005f
        L_0x0057:
            androidx.fragment.app.FragmentManagerViewModel r4 = new androidx.fragment.app.FragmentManagerViewModel
            r5 = 0
            r4.<init>(r5)
            r3.mNonConfig = r4
        L_0x005f:
            androidx.fragment.app.FragmentManagerViewModel r4 = r3.mNonConfig
            boolean r5 = r3.isStateSaved()
            r4.setIsStateSaved(r5)
            androidx.fragment.app.FragmentStore r4 = r3.mFragmentStore
            androidx.fragment.app.FragmentManagerViewModel r5 = r3.mNonConfig
            r4.setNonConfig(r5)
            androidx.fragment.app.FragmentHostCallback<?> r4 = r3.mHost
            boolean r5 = r4 instanceof androidx.savedstate.SavedStateRegistryOwner
            if (r5 == 0) goto L_0x0091
            if (r6 != 0) goto L_0x0091
            androidx.savedstate.SavedStateRegistryOwner r4 = (androidx.savedstate.SavedStateRegistryOwner) r4
            androidx.savedstate.SavedStateRegistry r4 = r4.getSavedStateRegistry()
            androidx.fragment.app.f r5 = new androidx.fragment.app.f
            r0 = 1
            r5.<init>(r0, r3)
            java.lang.String r0 = "android:support:fragments"
            r4.registerSavedStateProvider(r0, r5)
            android.os.Bundle r4 = r4.consumeRestoredStateForKey(r0)
            if (r4 == 0) goto L_0x0091
            r3.restoreSaveStateInternal(r4)
        L_0x0091:
            androidx.fragment.app.FragmentHostCallback<?> r4 = r3.mHost
            boolean r5 = r4 instanceof androidx.activity.result.ActivityResultRegistryOwner
            if (r5 == 0) goto L_0x00f7
            androidx.activity.result.ActivityResultRegistryOwner r4 = (androidx.activity.result.ActivityResultRegistryOwner) r4
            androidx.activity.result.ActivityResultRegistry r4 = r4.getActivityResultRegistry()
            if (r6 == 0) goto L_0x00ad
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = r6.mWho
            java.lang.String r1 = ":"
            java.lang.String r5 = i.C0212a.p(r5, r0, r1)
            goto L_0x00af
        L_0x00ad:
            java.lang.String r5 = ""
        L_0x00af:
            java.lang.String r0 = "FragmentManager:"
            java.lang.String r5 = i.C0212a.l(r0, r5)
            java.lang.String r0 = "StartActivityForResult"
            java.lang.String r0 = i.C0212a.A(r5, r0)
            androidx.activity.result.contract.ActivityResultContracts$StartActivityForResult r1 = new androidx.activity.result.contract.ActivityResultContracts$StartActivityForResult
            r1.<init>()
            androidx.fragment.app.FragmentManager$8 r2 = new androidx.fragment.app.FragmentManager$8
            r2.<init>()
            androidx.activity.result.ActivityResultLauncher r0 = r4.register(r0, r1, r2)
            r3.mStartActivityForResult = r0
            java.lang.String r0 = "StartIntentSenderForResult"
            java.lang.String r0 = i.C0212a.A(r5, r0)
            androidx.fragment.app.FragmentManager$FragmentIntentSenderContract r1 = new androidx.fragment.app.FragmentManager$FragmentIntentSenderContract
            r1.<init>()
            androidx.fragment.app.FragmentManager$9 r2 = new androidx.fragment.app.FragmentManager$9
            r2.<init>()
            androidx.activity.result.ActivityResultLauncher r0 = r4.register(r0, r1, r2)
            r3.mStartIntentSenderForResult = r0
            java.lang.String r0 = "RequestPermissions"
            java.lang.String r5 = i.C0212a.A(r5, r0)
            androidx.activity.result.contract.ActivityResultContracts$RequestMultiplePermissions r0 = new androidx.activity.result.contract.ActivityResultContracts$RequestMultiplePermissions
            r0.<init>()
            androidx.fragment.app.FragmentManager$10 r1 = new androidx.fragment.app.FragmentManager$10
            r1.<init>()
            androidx.activity.result.ActivityResultLauncher r4 = r4.register(r5, r0, r1)
            r3.mRequestPermissions = r4
        L_0x00f7:
            androidx.fragment.app.FragmentHostCallback<?> r4 = r3.mHost
            boolean r5 = r4 instanceof androidx.core.content.OnConfigurationChangedProvider
            if (r5 == 0) goto L_0x0104
            androidx.core.content.OnConfigurationChangedProvider r4 = (androidx.core.content.OnConfigurationChangedProvider) r4
            androidx.core.util.Consumer<android.content.res.Configuration> r5 = r3.mOnConfigurationChangedListener
            r4.addOnConfigurationChangedListener(r5)
        L_0x0104:
            androidx.fragment.app.FragmentHostCallback<?> r4 = r3.mHost
            boolean r5 = r4 instanceof androidx.core.content.OnTrimMemoryProvider
            if (r5 == 0) goto L_0x0111
            androidx.core.content.OnTrimMemoryProvider r4 = (androidx.core.content.OnTrimMemoryProvider) r4
            androidx.core.util.Consumer<java.lang.Integer> r5 = r3.mOnTrimMemoryListener
            r4.addOnTrimMemoryListener(r5)
        L_0x0111:
            androidx.fragment.app.FragmentHostCallback<?> r4 = r3.mHost
            boolean r5 = r4 instanceof androidx.core.app.OnMultiWindowModeChangedProvider
            if (r5 == 0) goto L_0x011e
            androidx.core.app.OnMultiWindowModeChangedProvider r4 = (androidx.core.app.OnMultiWindowModeChangedProvider) r4
            androidx.core.util.Consumer<androidx.core.app.MultiWindowModeChangedInfo> r5 = r3.mOnMultiWindowModeChangedListener
            r4.addOnMultiWindowModeChangedListener(r5)
        L_0x011e:
            androidx.fragment.app.FragmentHostCallback<?> r4 = r3.mHost
            boolean r5 = r4 instanceof androidx.core.app.OnPictureInPictureModeChangedProvider
            if (r5 == 0) goto L_0x012b
            androidx.core.app.OnPictureInPictureModeChangedProvider r4 = (androidx.core.app.OnPictureInPictureModeChangedProvider) r4
            androidx.core.util.Consumer<androidx.core.app.PictureInPictureModeChangedInfo> r5 = r3.mOnPictureInPictureModeChangedListener
            r4.addOnPictureInPictureModeChangedListener(r5)
        L_0x012b:
            androidx.fragment.app.FragmentHostCallback<?> r4 = r3.mHost
            boolean r5 = r4 instanceof androidx.core.view.MenuHost
            if (r5 == 0) goto L_0x013a
            if (r6 != 0) goto L_0x013a
            androidx.core.view.MenuHost r4 = (androidx.core.view.MenuHost) r4
            androidx.core.view.MenuProvider r3 = r3.mMenuProvider
            r4.addMenuProvider(r3)
        L_0x013a:
            return
        L_0x013b:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "Already attached"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentManager.attachController(androidx.fragment.app.FragmentHostCallback, androidx.fragment.app.FragmentContainer, androidx.fragment.app.Fragment):void");
    }

    public void attachFragment(Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (!fragment.mAdded) {
                this.mFragmentStore.addFragment(fragment);
                if (isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "add from attach: " + fragment);
                }
                if (isMenuAvailable(fragment)) {
                    this.mNeedMenuInvalidate = true;
                }
            }
        }
    }

    public FragmentTransaction beginTransaction() {
        return new BackStackRecord(this);
    }

    public void cancelBackStackTransition() {
        if (isLoggingEnabled(3)) {
            Log.d("FragmentManager", "cancelBackStackTransition for transition " + this.mTransitioningOp);
        }
        BackStackRecord backStackRecord = this.mTransitioningOp;
        if (backStackRecord != null) {
            backStackRecord.mCommitted = false;
            backStackRecord.runOnCommitInternal(true, new b(4, this));
            this.mTransitioningOp.commit();
            this.mHandlingTransitioningOp = true;
            executePendingTransactions();
            this.mHandlingTransitioningOp = false;
            this.mTransitioningOp = null;
        }
    }

    public boolean checkForMenus() {
        boolean z = false;
        for (Fragment next : this.mFragmentStore.getActiveFragments()) {
            if (next != null) {
                z = isMenuAvailable(next);
                continue;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public void clearFragmentAnimationOnLastFragmentExit() {
        Fragment fragment;
        O o2;
        if (this.mUseAutoClearOnLastStack && (fragment = (Fragment) C1194l.O0(0, getFragments())) != null && (o2 = fragment.mDisposableHandle) != null) {
            o2.a();
        }
    }

    public Set<SpecialEffectsController> collectChangedControllers(ArrayList<BackStackRecord> arrayList, int i2, int i7) {
        ViewGroup viewGroup;
        HashSet hashSet = new HashSet();
        while (i2 < i7) {
            Iterator<FragmentTransaction.Op> it = arrayList.get(i2).mOps.iterator();
            while (it.hasNext()) {
                Fragment fragment = it.next().mFragment;
                if (!(fragment == null || (viewGroup = fragment.mContainer) == null)) {
                    hashSet.add(SpecialEffectsController.getOrCreateController(viewGroup, this));
                }
            }
            i2++;
        }
        return hashSet;
    }

    public FragmentStateManager createOrGetFragmentStateManager(Fragment fragment) {
        FragmentStateManager fragmentStateManager = this.mFragmentStore.getFragmentStateManager(fragment.mWho);
        if (fragmentStateManager != null) {
            return fragmentStateManager;
        }
        FragmentStateManager fragmentStateManager2 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, fragment);
        fragmentStateManager2.restoreState(this.mHost.getContext().getClassLoader());
        fragmentStateManager2.setFragmentManagerState(this.mCurState);
        return fragmentStateManager2;
    }

    public void detachFragment(Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (!fragment.mDetached) {
            fragment.mDetached = true;
            if (fragment.mAdded) {
                if (isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "remove from detach: " + fragment);
                }
                this.mFragmentStore.removeFragment(fragment);
                if (isMenuAvailable(fragment)) {
                    this.mNeedMenuInvalidate = true;
                }
                setVisibleRemovingFragment(fragment);
            }
        }
    }

    public void dispatchActivityCreated() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        dispatchStateChange(4);
    }

    public void dispatchAttach() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        dispatchStateChange(0);
    }

    public void dispatchConfigurationChanged(Configuration configuration, boolean z) {
        if (z && (this.mHost instanceof OnConfigurationChangedProvider)) {
            throwException(new IllegalStateException("Do not call dispatchConfigurationChanged() on host. Host implements OnConfigurationChangedProvider and automatically dispatches configuration changes to fragments."));
        }
        for (Fragment next : this.mFragmentStore.getFragments()) {
            if (next != null) {
                next.performConfigurationChanged(configuration);
                if (z) {
                    next.mChildFragmentManager.dispatchConfigurationChanged(configuration, true);
                }
            }
        }
    }

    public boolean dispatchContextItemSelected(MenuItem menuItem) {
        if (this.mCurState < 1) {
            return false;
        }
        for (Fragment next : this.mFragmentStore.getFragments()) {
            if (next != null && next.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void dispatchCreate() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        dispatchStateChange(1);
    }

    public boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (this.mCurState < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z = false;
        for (Fragment next : this.mFragmentStore.getFragments()) {
            if (next != null && isParentMenuVisible(next) && next.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(next);
                z = true;
            }
        }
        if (this.mCreatedMenus != null) {
            for (int i2 = 0; i2 < this.mCreatedMenus.size(); i2++) {
                Fragment fragment = this.mCreatedMenus.get(i2);
                if (arrayList == null || !arrayList.contains(fragment)) {
                    fragment.onDestroyOptionsMenu();
                }
            }
        }
        this.mCreatedMenus = arrayList;
        return z;
    }

    public void dispatchDestroy() {
        this.mDestroyed = true;
        execPendingActions(true);
        endAnimatingAwayFragments();
        clearBackStackStateViewModels();
        dispatchStateChange(-1);
        FragmentHostCallback<?> fragmentHostCallback = this.mHost;
        if (fragmentHostCallback instanceof OnTrimMemoryProvider) {
            ((OnTrimMemoryProvider) fragmentHostCallback).removeOnTrimMemoryListener(this.mOnTrimMemoryListener);
        }
        FragmentHostCallback<?> fragmentHostCallback2 = this.mHost;
        if (fragmentHostCallback2 instanceof OnConfigurationChangedProvider) {
            ((OnConfigurationChangedProvider) fragmentHostCallback2).removeOnConfigurationChangedListener(this.mOnConfigurationChangedListener);
        }
        FragmentHostCallback<?> fragmentHostCallback3 = this.mHost;
        if (fragmentHostCallback3 instanceof OnMultiWindowModeChangedProvider) {
            ((OnMultiWindowModeChangedProvider) fragmentHostCallback3).removeOnMultiWindowModeChangedListener(this.mOnMultiWindowModeChangedListener);
        }
        FragmentHostCallback<?> fragmentHostCallback4 = this.mHost;
        if (fragmentHostCallback4 instanceof OnPictureInPictureModeChangedProvider) {
            ((OnPictureInPictureModeChangedProvider) fragmentHostCallback4).removeOnPictureInPictureModeChangedListener(this.mOnPictureInPictureModeChangedListener);
        }
        FragmentHostCallback<?> fragmentHostCallback5 = this.mHost;
        if ((fragmentHostCallback5 instanceof MenuHost) && this.mParent == null) {
            ((MenuHost) fragmentHostCallback5).removeMenuProvider(this.mMenuProvider);
        }
        this.mHost = null;
        this.mContainer = null;
        this.mParent = null;
        if (this.mOnBackPressedDispatcher != null) {
            this.mOnBackPressedCallback.remove();
            this.mOnBackPressedDispatcher = null;
        }
        ActivityResultLauncher<Intent> activityResultLauncher = this.mStartActivityForResult;
        if (activityResultLauncher != null) {
            activityResultLauncher.unregister();
            this.mStartIntentSenderForResult.unregister();
            this.mRequestPermissions.unregister();
        }
    }

    public void dispatchDestroyView() {
        dispatchStateChange(1);
    }

    public void dispatchLowMemory(boolean z) {
        if (z && (this.mHost instanceof OnTrimMemoryProvider)) {
            throwException(new IllegalStateException("Do not call dispatchLowMemory() on host. Host implements OnTrimMemoryProvider and automatically dispatches low memory callbacks to fragments."));
        }
        for (Fragment next : this.mFragmentStore.getFragments()) {
            if (next != null) {
                next.performLowMemory();
                if (z) {
                    next.mChildFragmentManager.dispatchLowMemory(true);
                }
            }
        }
    }

    public void dispatchMultiWindowModeChanged(boolean z, boolean z3) {
        if (z3 && (this.mHost instanceof OnMultiWindowModeChangedProvider)) {
            throwException(new IllegalStateException("Do not call dispatchMultiWindowModeChanged() on host. Host implements OnMultiWindowModeChangedProvider and automatically dispatches multi-window mode changes to fragments."));
        }
        for (Fragment next : this.mFragmentStore.getFragments()) {
            if (next != null) {
                next.performMultiWindowModeChanged(z);
                if (z3) {
                    next.mChildFragmentManager.dispatchMultiWindowModeChanged(z, true);
                }
            }
        }
    }

    public void dispatchOnAttachFragment(Fragment fragment) {
        Iterator<FragmentOnAttachListener> it = this.mOnAttachListeners.iterator();
        while (it.hasNext()) {
            it.next().onAttachFragment(this, fragment);
        }
    }

    public void dispatchOnHiddenChanged() {
        for (Fragment next : this.mFragmentStore.getActiveFragments()) {
            if (next != null) {
                next.onHiddenChanged(next.isHidden());
                next.mChildFragmentManager.dispatchOnHiddenChanged();
            }
        }
    }

    public boolean dispatchOptionsItemSelected(MenuItem menuItem) {
        if (this.mCurState < 1) {
            return false;
        }
        for (Fragment next : this.mFragmentStore.getFragments()) {
            if (next != null && next.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void dispatchOptionsMenuClosed(Menu menu) {
        if (this.mCurState >= 1) {
            for (Fragment next : this.mFragmentStore.getFragments()) {
                if (next != null) {
                    next.performOptionsMenuClosed(menu);
                }
            }
        }
    }

    public void dispatchPause() {
        dispatchStateChange(5);
    }

    public void dispatchPictureInPictureModeChanged(boolean z, boolean z3) {
        if (z3 && (this.mHost instanceof OnPictureInPictureModeChangedProvider)) {
            throwException(new IllegalStateException("Do not call dispatchPictureInPictureModeChanged() on host. Host implements OnPictureInPictureModeChangedProvider and automatically dispatches picture-in-picture mode changes to fragments."));
        }
        for (Fragment next : this.mFragmentStore.getFragments()) {
            if (next != null) {
                next.performPictureInPictureModeChanged(z);
                if (z3) {
                    next.mChildFragmentManager.dispatchPictureInPictureModeChanged(z, true);
                }
            }
        }
    }

    public boolean dispatchPrepareOptionsMenu(Menu menu) {
        boolean z = false;
        if (this.mCurState < 1) {
            return false;
        }
        for (Fragment next : this.mFragmentStore.getFragments()) {
            if (next != null && isParentMenuVisible(next) && next.performPrepareOptionsMenu(menu)) {
                z = true;
            }
        }
        return z;
    }

    public void dispatchPrimaryNavigationFragmentChanged() {
        updateOnBackPressedCallbackEnabled();
        dispatchParentPrimaryNavigationFragmentChanged(this.mPrimaryNav);
    }

    public void dispatchResume() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        dispatchStateChange(7);
    }

    public void dispatchStart() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        dispatchStateChange(5);
    }

    public void dispatchStop() {
        this.mStopped = true;
        this.mNonConfig.setIsStateSaved(true);
        dispatchStateChange(4);
    }

    public void dispatchViewCreated() {
        dispatchStateChange(2);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        String A10 = C0212a.A(str, "    ");
        this.mFragmentStore.dump(str, fileDescriptor, printWriter, strArr);
        ArrayList<Fragment> arrayList = this.mCreatedMenus;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i2 = 0; i2 < size; i2++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(this.mCreatedMenus.get(i2).toString());
            }
        }
        int size2 = this.mBackStack.size();
        if (size2 > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i7 = 0; i7 < size2; i7++) {
                BackStackRecord backStackRecord = this.mBackStack.get(i7);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i7);
                printWriter.print(": ");
                printWriter.println(backStackRecord.toString());
                backStackRecord.dump(A10, printWriter);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.mBackStackIndex.get());
        synchronized (this.mPendingActions) {
            try {
                int size3 = this.mPendingActions.size();
                if (size3 > 0) {
                    printWriter.print(str);
                    printWriter.println("Pending Actions:");
                    for (int i8 = 0; i8 < size3; i8++) {
                        printWriter.print(str);
                        printWriter.print("  #");
                        printWriter.print(i8);
                        printWriter.print(": ");
                        printWriter.println(this.mPendingActions.get(i8));
                    }
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.mHost);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.mContainer);
        if (this.mParent != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.mParent);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.mCurState);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.mStateSaved);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.mDestroyed);
        if (this.mNeedMenuInvalidate) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.mNeedMenuInvalidate);
        }
    }

    public void enqueueAction(OpGenerator opGenerator, boolean z) {
        if (!z) {
            if (this.mHost != null) {
                checkStateLoss();
            } else if (this.mDestroyed) {
                throw new IllegalStateException("FragmentManager has been destroyed");
            } else {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
        }
        synchronized (this.mPendingActions) {
            try {
                if (this.mHost != null) {
                    this.mPendingActions.add(opGenerator);
                    scheduleCommit();
                } else if (!z) {
                    throw new IllegalStateException("Activity has been destroyed");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean execPendingActions(boolean z) {
        BackStackRecord backStackRecord;
        ensureExecReady(z);
        boolean z3 = false;
        if (!this.mHandlingTransitioningOp && (backStackRecord = this.mTransitioningOp) != null) {
            backStackRecord.mCommitted = false;
            if (isLoggingEnabled(3)) {
                Log.d("FragmentManager", "Reversing mTransitioningOp " + this.mTransitioningOp + " as part of execPendingActions for actions " + this.mPendingActions);
            }
            this.mTransitioningOp.commitInternal(false, false);
            this.mPendingActions.add(0, this.mTransitioningOp);
            Iterator<FragmentTransaction.Op> it = this.mTransitioningOp.mOps.iterator();
            while (it.hasNext()) {
                Fragment fragment = it.next().mFragment;
                if (fragment != null) {
                    fragment.mTransitioning = false;
                }
            }
            this.mTransitioningOp = null;
        }
        while (generateOpsForPendingActions(this.mTmpRecords, this.mTmpIsPop)) {
            z3 = true;
            this.mExecutingActions = true;
            try {
                removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            } finally {
                cleanupExec();
            }
        }
        updateOnBackPressedCallbackEnabled();
        doPendingDeferredStart();
        this.mFragmentStore.burpActive();
        return z3;
    }

    public void execSingleAction(OpGenerator opGenerator, boolean z) {
        if (!z || (this.mHost != null && !this.mDestroyed)) {
            ensureExecReady(z);
            BackStackRecord backStackRecord = this.mTransitioningOp;
            boolean z3 = false;
            if (backStackRecord != null) {
                backStackRecord.mCommitted = false;
                if (isLoggingEnabled(3)) {
                    Log.d("FragmentManager", "Reversing mTransitioningOp " + this.mTransitioningOp + " as part of execSingleAction for action " + opGenerator);
                }
                this.mTransitioningOp.commitInternal(false, false);
                boolean generateOps = this.mTransitioningOp.generateOps(this.mTmpRecords, this.mTmpIsPop);
                Iterator<FragmentTransaction.Op> it = this.mTransitioningOp.mOps.iterator();
                while (it.hasNext()) {
                    Fragment fragment = it.next().mFragment;
                    if (fragment != null) {
                        fragment.mTransitioning = false;
                    }
                }
                this.mTransitioningOp = null;
                z3 = generateOps;
            }
            boolean generateOps2 = opGenerator.generateOps(this.mTmpRecords, this.mTmpIsPop);
            if (z3 || generateOps2) {
                this.mExecutingActions = true;
                try {
                    removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
                } finally {
                    cleanupExec();
                }
            }
            updateOnBackPressedCallbackEnabled();
            doPendingDeferredStart();
            this.mFragmentStore.burpActive();
        }
    }

    public boolean executePendingTransactions() {
        boolean execPendingActions = execPendingActions(true);
        forcePostponedTransactions();
        return execPendingActions;
    }

    public Fragment findActiveFragment(String str) {
        return this.mFragmentStore.findActiveFragment(str);
    }

    public Fragment findFragmentById(int i2) {
        return this.mFragmentStore.findFragmentById(i2);
    }

    public Fragment findFragmentByTag(String str) {
        return this.mFragmentStore.findFragmentByTag(str);
    }

    public Fragment findFragmentByWho(String str) {
        return this.mFragmentStore.findFragmentByWho(str);
    }

    public Set<Fragment> fragmentsFromRecord(BackStackRecord backStackRecord) {
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < backStackRecord.mOps.size(); i2++) {
            Fragment fragment = backStackRecord.mOps.get(i2).mFragment;
            if (fragment != null && backStackRecord.mAddToBackStack) {
                hashSet.add(fragment);
            }
        }
        return hashSet;
    }

    public BackStackEntry getBackStackEntryAt(int i2) {
        if (i2 != this.mBackStack.size()) {
            return this.mBackStack.get(i2);
        }
        BackStackRecord backStackRecord = this.mTransitioningOp;
        if (backStackRecord != null) {
            return backStackRecord;
        }
        throw new IndexOutOfBoundsException();
    }

    public int getBackStackEntryCount() {
        int i2;
        int size = this.mBackStack.size();
        if (this.mTransitioningOp != null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        return size + i2;
    }

    public FragmentContainer getContainer() {
        return this.mContainer;
    }

    public FragmentFactory getFragmentFactory() {
        FragmentFactory fragmentFactory = this.mFragmentFactory;
        if (fragmentFactory != null) {
            return fragmentFactory;
        }
        Fragment fragment = this.mParent;
        if (fragment != null) {
            return fragment.mFragmentManager.getFragmentFactory();
        }
        return this.mHostFragmentFactory;
    }

    public List<Fragment> getFragments() {
        return this.mFragmentStore.getFragments();
    }

    public FragmentHostCallback<?> getHost() {
        return this.mHost;
    }

    public LayoutInflater.Factory2 getLayoutInflaterFactory() {
        return this.mLayoutInflaterFactory;
    }

    public FragmentLifecycleCallbacksDispatcher getLifecycleCallbacksDispatcher() {
        return this.mLifecycleCallbacksDispatcher;
    }

    public Fragment getParent() {
        return this.mParent;
    }

    public Fragment getPrimaryNavigationFragment() {
        return this.mPrimaryNav;
    }

    public SpecialEffectsControllerFactory getSpecialEffectsControllerFactory() {
        SpecialEffectsControllerFactory specialEffectsControllerFactory = this.mSpecialEffectsControllerFactory;
        if (specialEffectsControllerFactory != null) {
            return specialEffectsControllerFactory;
        }
        Fragment fragment = this.mParent;
        if (fragment != null) {
            return fragment.mFragmentManager.getSpecialEffectsControllerFactory();
        }
        return this.mDefaultSpecialEffectsControllerFactory;
    }

    public FragmentStrictMode.Policy getStrictModePolicy() {
        return this.mStrictModePolicy;
    }

    public ViewModelStore getViewModelStore(Fragment fragment) {
        return this.mNonConfig.getViewModelStore(fragment);
    }

    public void handleOnBackPressed(boolean z) {
        this.mHandlingTransitioningOp = true;
        execPendingActions(true);
        this.mHandlingTransitioningOp = false;
        if (USE_PREDICTIVE_BACK && this.mTransitioningOp != null) {
            if (!this.mBackStackChangeListeners.isEmpty()) {
                LinkedHashSet linkedHashSet = new LinkedHashSet(fragmentsFromRecord(this.mTransitioningOp));
                Iterator<Object> it = this.mBackStackChangeListeners.iterator();
                while (it.hasNext()) {
                    if (it.next() == null) {
                        Iterator it2 = linkedHashSet.iterator();
                        if (it2.hasNext()) {
                            Fragment fragment = (Fragment) it2.next();
                            throw null;
                        }
                    } else {
                        throw new ClassCastException();
                    }
                }
            }
            Iterator<FragmentTransaction.Op> it3 = this.mTransitioningOp.mOps.iterator();
            while (it3.hasNext()) {
                Fragment fragment2 = it3.next().mFragment;
                if (fragment2 != null) {
                    fragment2.mTransitioning = false;
                }
            }
            for (SpecialEffectsController completeBack : collectChangedControllers(new ArrayList(Collections.singletonList(this.mTransitioningOp)), 0, 1)) {
                completeBack.completeBack();
            }
            Iterator<FragmentTransaction.Op> it4 = this.mTransitioningOp.mOps.iterator();
            while (it4.hasNext()) {
                Fragment fragment3 = it4.next().mFragment;
                if (fragment3 != null && fragment3.mContainer == null) {
                    createOrGetFragmentStateManager(fragment3).moveToExpectedState();
                }
            }
            this.mTransitioningOp = null;
            updateOnBackPressedCallbackEnabled();
            if (isLoggingEnabled(3)) {
                Log.d("FragmentManager", "Op is being set to null");
                Log.d("FragmentManager", "OnBackPressedCallback enabled=" + this.mOnBackPressedCallback.isEnabled() + " for  FragmentManager " + this);
            }
        } else if (this.mOnBackPressedCallback.isEnabled()) {
            if (isLoggingEnabled(3)) {
                Log.d("FragmentManager", "Calling popBackStackImmediate via onBackPressed callback");
            }
            popBackStackImmediate();
        } else if (z) {
            if (isLoggingEnabled(3)) {
                Log.d("FragmentManager", "Calling onBackPressed via onBackPressed callback");
            }
            this.mOnBackPressedDispatcher.onBackPressed();
        }
    }

    public void hideFragment(Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (!fragment.mHidden) {
            fragment.mHidden = true;
            fragment.mHiddenChanged = true ^ fragment.mHiddenChanged;
            setVisibleRemovingFragment(fragment);
        }
    }

    public void invalidateMenuForFragment(Fragment fragment) {
        if (fragment.mAdded && isMenuAvailable(fragment)) {
            this.mNeedMenuInvalidate = true;
        }
    }

    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    public boolean isParentHidden(Fragment fragment) {
        if (fragment == null) {
            return false;
        }
        return fragment.isHidden();
    }

    public boolean isParentMenuVisible(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        return fragment.isMenuVisible();
    }

    public boolean isPrimaryNavigation(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        FragmentManager fragmentManager = fragment.mFragmentManager;
        if (!fragment.equals(fragmentManager.getPrimaryNavigationFragment()) || !isPrimaryNavigation(fragmentManager.mParent)) {
            return false;
        }
        return true;
    }

    public boolean isStateAtLeast(int i2) {
        if (this.mCurState >= i2) {
            return true;
        }
        return false;
    }

    public boolean isStateSaved() {
        if (this.mStateSaved || this.mStopped) {
            return true;
        }
        return false;
    }

    public void launchStartActivityForResult(Fragment fragment, Intent intent, int i2, Bundle bundle) {
        if (this.mStartActivityForResult != null) {
            this.mLaunchedFragments.addLast(new LaunchedFragmentInfo(fragment.mWho, i2));
            if (bundle != null) {
                intent.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundle);
            }
            this.mStartActivityForResult.launch(intent);
            return;
        }
        this.mHost.onStartActivityFromFragment(fragment, intent, i2, bundle);
    }

    public void moveToState(int i2, boolean z) {
        FragmentHostCallback<?> fragmentHostCallback;
        if (this.mHost == null && i2 != -1) {
            throw new IllegalStateException("No activity");
        } else if (z || i2 != this.mCurState) {
            this.mCurState = i2;
            this.mFragmentStore.moveToExpectedState();
            startPendingDeferredFragments();
            if (this.mNeedMenuInvalidate && (fragmentHostCallback = this.mHost) != null && this.mCurState == 7) {
                fragmentHostCallback.onSupportInvalidateOptionsMenu();
                this.mNeedMenuInvalidate = false;
            }
        }
    }

    public void noteStateNotSaved() {
        if (this.mHost != null) {
            this.mStateSaved = false;
            this.mStopped = false;
            this.mNonConfig.setIsStateSaved(false);
            for (Fragment next : this.mFragmentStore.getFragments()) {
                if (next != null) {
                    next.noteStateNotSaved();
                }
            }
        }
    }

    public final void onContainerAvailable(FragmentContainerView fragmentContainerView) {
        View view;
        for (FragmentStateManager next : this.mFragmentStore.getActiveFragmentStateManagers()) {
            Fragment fragment = next.getFragment();
            if (fragment.mContainerId == fragmentContainerView.getId() && (view = fragment.mView) != null && view.getParent() == null) {
                fragment.mContainer = fragmentContainerView;
                next.addViewToContainer();
                next.moveToExpectedState();
            }
        }
    }

    public void performPendingDeferredStart(FragmentStateManager fragmentStateManager) {
        Fragment fragment = fragmentStateManager.getFragment();
        if (!fragment.mDeferStart) {
            return;
        }
        if (this.mExecutingActions) {
            this.mHavePendingDeferredStart = true;
            return;
        }
        fragment.mDeferStart = false;
        fragmentStateManager.moveToExpectedState();
    }

    public void popBackStack() {
        enqueueAction(new PopBackStackState((String) null, -1, 0), false);
    }

    public boolean popBackStackImmediate() {
        return popBackStackImmediate((String) null, -1, 0);
    }

    public boolean popBackStackState(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, String str, int i2, int i7) {
        boolean z;
        if ((i7 & 1) != 0) {
            z = true;
        } else {
            z = false;
        }
        int findBackStackIndex = findBackStackIndex(str, i2, z);
        if (findBackStackIndex < 0) {
            return false;
        }
        for (int size = this.mBackStack.size() - 1; size >= findBackStackIndex; size--) {
            arrayList.add(this.mBackStack.remove(size));
            arrayList2.add(Boolean.TRUE);
        }
        return true;
    }

    public boolean prepareBackStackState(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
        if (isLoggingEnabled(2)) {
            Log.v("FragmentManager", "FragmentManager has the following pending actions inside of prepareBackStackState: " + this.mPendingActions);
        }
        if (this.mBackStack.isEmpty()) {
            Log.i("FragmentManager", "Ignoring call to start back stack pop because the back stack is empty.");
            return false;
        }
        BackStackRecord backStackRecord = (BackStackRecord) C0212a.i(this.mBackStack, 1);
        this.mTransitioningOp = backStackRecord;
        Iterator<FragmentTransaction.Op> it = backStackRecord.mOps.iterator();
        while (it.hasNext()) {
            Fragment fragment = it.next().mFragment;
            if (fragment != null) {
                fragment.mTransitioning = true;
            }
        }
        return popBackStackState(arrayList, arrayList2, (String) null, -1, 0);
    }

    public void prepareBackStackTransition() {
        enqueueAction(new PrepareBackStackTransitionState(), false);
    }

    public void registerFragmentLifecycleCallbacks(FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z) {
        this.mLifecycleCallbacksDispatcher.registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, z);
    }

    public void removeFragment(Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.mBackStackNesting);
        }
        boolean isInBackStack = fragment.isInBackStack();
        if (!fragment.mDetached || !isInBackStack) {
            this.mFragmentStore.removeFragment(fragment);
            if (isMenuAvailable(fragment)) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.mRemoving = true;
            setVisibleRemovingFragment(fragment);
        }
    }

    public void restoreSaveStateInternal(Parcelable parcelable) {
        FragmentStateManager fragmentStateManager;
        Bundle bundle;
        Bundle bundle2;
        if (parcelable != null) {
            Bundle bundle3 = (Bundle) parcelable;
            for (String next : bundle3.keySet()) {
                if (next.startsWith("result_") && (bundle2 = bundle3.getBundle(next)) != null) {
                    bundle2.setClassLoader(this.mHost.getContext().getClassLoader());
                    this.mResults.put(next.substring(7), bundle2);
                }
            }
            HashMap hashMap = new HashMap();
            for (String next2 : bundle3.keySet()) {
                if (next2.startsWith("fragment_") && (bundle = bundle3.getBundle(next2)) != null) {
                    bundle.setClassLoader(this.mHost.getContext().getClassLoader());
                    hashMap.put(next2.substring(9), bundle);
                }
            }
            this.mFragmentStore.restoreSaveState(hashMap);
            FragmentManagerState fragmentManagerState = (FragmentManagerState) bundle3.getParcelable("state");
            if (fragmentManagerState != null) {
                this.mFragmentStore.resetActiveFragments();
                Iterator<String> it = fragmentManagerState.mActive.iterator();
                while (it.hasNext()) {
                    Bundle savedState = this.mFragmentStore.setSavedState(it.next(), (Bundle) null);
                    if (savedState != null) {
                        Fragment findRetainedFragmentByWho = this.mNonConfig.findRetainedFragmentByWho(((FragmentState) savedState.getParcelable("state")).mWho);
                        if (findRetainedFragmentByWho != null) {
                            if (isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + findRetainedFragmentByWho);
                            }
                            fragmentStateManager = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, findRetainedFragmentByWho, savedState);
                        } else {
                            fragmentStateManager = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, this.mHost.getContext().getClassLoader(), getFragmentFactory(), savedState);
                        }
                        Fragment fragment = fragmentStateManager.getFragment();
                        fragment.mSavedFragmentState = savedState;
                        fragment.mFragmentManager = this;
                        if (isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "restoreSaveState: active (" + fragment.mWho + "): " + fragment);
                        }
                        fragmentStateManager.restoreState(this.mHost.getContext().getClassLoader());
                        this.mFragmentStore.makeActive(fragmentStateManager);
                        fragmentStateManager.setFragmentManagerState(this.mCurState);
                    }
                }
                for (Fragment next3 : this.mNonConfig.getRetainedFragments()) {
                    if (!this.mFragmentStore.containsActiveFragment(next3.mWho)) {
                        if (isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "Discarding retained Fragment " + next3 + " that was not found in the set of active Fragments " + fragmentManagerState.mActive);
                        }
                        this.mNonConfig.removeRetainedFragment(next3);
                        next3.mFragmentManager = this;
                        FragmentStateManager fragmentStateManager2 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, next3);
                        fragmentStateManager2.setFragmentManagerState(1);
                        fragmentStateManager2.moveToExpectedState();
                        next3.mRemoving = true;
                        fragmentStateManager2.moveToExpectedState();
                    }
                }
                this.mFragmentStore.restoreAddedFragments(fragmentManagerState.mAdded);
                if (fragmentManagerState.mBackStack != null) {
                    this.mBackStack = new ArrayList<>(fragmentManagerState.mBackStack.length);
                    int i2 = 0;
                    while (true) {
                        BackStackRecordState[] backStackRecordStateArr = fragmentManagerState.mBackStack;
                        if (i2 >= backStackRecordStateArr.length) {
                            break;
                        }
                        BackStackRecord instantiate = backStackRecordStateArr[i2].instantiate(this);
                        if (isLoggingEnabled(2)) {
                            StringBuilder o2 = C0086a.o(i2, "restoreAllState: back stack #", " (index ");
                            o2.append(instantiate.mIndex);
                            o2.append("): ");
                            o2.append(instantiate);
                            Log.v("FragmentManager", o2.toString());
                            PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
                            instantiate.dump("  ", printWriter, false);
                            printWriter.close();
                        }
                        this.mBackStack.add(instantiate);
                        i2++;
                    }
                } else {
                    this.mBackStack = new ArrayList<>();
                }
                this.mBackStackIndex.set(fragmentManagerState.mBackStackIndex);
                String str = fragmentManagerState.mPrimaryNavActiveWho;
                if (str != null) {
                    Fragment findActiveFragment = findActiveFragment(str);
                    this.mPrimaryNav = findActiveFragment;
                    dispatchParentPrimaryNavigationFragmentChanged(findActiveFragment);
                }
                ArrayList<String> arrayList = fragmentManagerState.mBackStackStateKeys;
                if (arrayList != null) {
                    for (int i7 = 0; i7 < arrayList.size(); i7++) {
                        this.mBackStackStates.put(arrayList.get(i7), fragmentManagerState.mBackStackStates.get(i7));
                    }
                }
                this.mLaunchedFragments = new ArrayDeque<>(fragmentManagerState.mLaunchedFragments);
            }
        }
    }

    /* renamed from: saveAllStateInternal */
    public Bundle lambda$attachController$5() {
        BackStackRecordState[] backStackRecordStateArr;
        Bundle bundle = new Bundle();
        forcePostponedTransactions();
        endAnimatingAwayFragments();
        execPendingActions(true);
        this.mStateSaved = true;
        this.mNonConfig.setIsStateSaved(true);
        ArrayList<String> saveActiveFragments = this.mFragmentStore.saveActiveFragments();
        HashMap<String, Bundle> allSavedState = this.mFragmentStore.getAllSavedState();
        if (!allSavedState.isEmpty()) {
            ArrayList<String> saveAddedFragments = this.mFragmentStore.saveAddedFragments();
            int size = this.mBackStack.size();
            if (size > 0) {
                backStackRecordStateArr = new BackStackRecordState[size];
                for (int i2 = 0; i2 < size; i2++) {
                    backStackRecordStateArr[i2] = new BackStackRecordState(this.mBackStack.get(i2));
                    if (isLoggingEnabled(2)) {
                        StringBuilder o2 = C0086a.o(i2, "saveAllState: adding back stack #", ": ");
                        o2.append(this.mBackStack.get(i2));
                        Log.v("FragmentManager", o2.toString());
                    }
                }
            } else {
                backStackRecordStateArr = null;
            }
            FragmentManagerState fragmentManagerState = new FragmentManagerState();
            fragmentManagerState.mActive = saveActiveFragments;
            fragmentManagerState.mAdded = saveAddedFragments;
            fragmentManagerState.mBackStack = backStackRecordStateArr;
            fragmentManagerState.mBackStackIndex = this.mBackStackIndex.get();
            Fragment fragment = this.mPrimaryNav;
            if (fragment != null) {
                fragmentManagerState.mPrimaryNavActiveWho = fragment.mWho;
            }
            fragmentManagerState.mBackStackStateKeys.addAll(this.mBackStackStates.keySet());
            fragmentManagerState.mBackStackStates.addAll(this.mBackStackStates.values());
            fragmentManagerState.mLaunchedFragments = new ArrayList<>(this.mLaunchedFragments);
            bundle.putParcelable("state", fragmentManagerState);
            for (String next : this.mResults.keySet()) {
                bundle.putBundle(C0212a.l("result_", next), this.mResults.get(next));
            }
            for (String next2 : allSavedState.keySet()) {
                bundle.putBundle(C0212a.l("fragment_", next2), allSavedState.get(next2));
            }
        } else if (isLoggingEnabled(2)) {
            Log.v("FragmentManager", "saveAllState: no fragments!");
            return bundle;
        }
        return bundle;
    }

    public void scheduleCommit() {
        synchronized (this.mPendingActions) {
            try {
                if (this.mPendingActions.size() == 1) {
                    this.mHost.getHandler().removeCallbacks(this.mExecCommit);
                    this.mHost.getHandler().post(this.mExecCommit);
                    updateOnBackPressedCallbackEnabled();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void seslSetOnBackPressedCallback(SeslOnBackPressedCallback seslOnBackPressedCallback) {
        if (this.mSeslOnBackPressedCallback != seslOnBackPressedCallback) {
            this.mSeslOnBackPressedCallback = seslOnBackPressedCallback;
        }
    }

    public void setExitAnimationOrder(Fragment fragment, boolean z) {
        ViewGroup fragmentContainer = getFragmentContainer(fragment);
        if (fragmentContainer != null && (fragmentContainer instanceof FragmentContainerView)) {
            ((FragmentContainerView) fragmentContainer).setDrawDisappearingViewsLast(!z);
        }
    }

    public void setMaxLifecycle(Fragment fragment, Lifecycle.State state) {
        if (!fragment.equals(findActiveFragment(fragment.mWho)) || !(fragment.mHost == null || fragment.mFragmentManager == this)) {
            throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
        }
        fragment.mMaxState = state;
    }

    public void setPrimaryNavigationFragment(Fragment fragment) {
        if (fragment == null || (fragment.equals(findActiveFragment(fragment.mWho)) && (fragment.mHost == null || fragment.mFragmentManager == this))) {
            Fragment fragment2 = this.mPrimaryNav;
            this.mPrimaryNav = fragment;
            dispatchParentPrimaryNavigationFragmentChanged(fragment2);
            dispatchParentPrimaryNavigationFragmentChanged(this.mPrimaryNav);
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public void showFragment(Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            fragment.mHiddenChanged = !fragment.mHiddenChanged;
        }
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder(128);
        sb2.append("FragmentManager{");
        sb2.append(Integer.toHexString(System.identityHashCode(this)));
        sb2.append(" in ");
        Fragment fragment = this.mParent;
        if (fragment != null) {
            sb2.append(fragment.getClass().getSimpleName());
            sb2.append("{");
            sb2.append(Integer.toHexString(System.identityHashCode(this.mParent)));
            sb2.append("}");
        } else {
            FragmentHostCallback<?> fragmentHostCallback = this.mHost;
            if (fragmentHostCallback != null) {
                sb2.append(fragmentHostCallback.getClass().getSimpleName());
                sb2.append("{");
                sb2.append(Integer.toHexString(System.identityHashCode(this.mHost)));
                sb2.append("}");
            } else {
                sb2.append("null");
            }
        }
        sb2.append("}}");
        return sb2.toString();
    }

    public void unregisterFragmentLifecycleCallbacks(FragmentLifecycleCallbacks fragmentLifecycleCallbacks) {
        this.mLifecycleCallbacksDispatcher.unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallbacks);
    }

    public void popBackStack(int i2, int i7, boolean z) {
        if (i2 >= 0) {
            enqueueAction(new PopBackStackState((String) null, i2, i7), z);
            return;
        }
        throw new IllegalArgumentException(C0086a.i(i2, "Bad id: "));
    }

    public boolean popBackStackImmediate(int i2, int i7) {
        if (i2 >= 0) {
            return popBackStackImmediate((String) null, i2, i7);
        }
        throw new IllegalArgumentException(C0086a.i(i2, "Bad id: "));
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LaunchedFragmentInfo implements Parcelable {
        public static final Parcelable.Creator<LaunchedFragmentInfo> CREATOR = new Parcelable.Creator<LaunchedFragmentInfo>() {
            public LaunchedFragmentInfo createFromParcel(Parcel parcel) {
                return new LaunchedFragmentInfo(parcel);
            }

            public LaunchedFragmentInfo[] newArray(int i2) {
                return new LaunchedFragmentInfo[i2];
            }
        };
        int mRequestCode;
        String mWho;

        public LaunchedFragmentInfo(String str, int i2) {
            this.mWho = str;
            this.mRequestCode = i2;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.mWho);
            parcel.writeInt(this.mRequestCode);
        }

        public LaunchedFragmentInfo(Parcel parcel) {
            this.mWho = parcel.readString();
            this.mRequestCode = parcel.readInt();
        }
    }

    private boolean popBackStackImmediate(String str, int i2, int i7) {
        execPendingActions(false);
        ensureExecReady(true);
        Fragment fragment = this.mPrimaryNav;
        if (fragment != null && i2 < 0 && str == null && fragment.getChildFragmentManager().popBackStackImmediate()) {
            return true;
        }
        boolean popBackStackState = popBackStackState(this.mTmpRecords, this.mTmpIsPop, str, i2, i7);
        if (popBackStackState) {
            this.mExecutingActions = true;
            try {
                removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
                cleanupExec();
            } catch (Throwable th) {
                Throwable th2 = th;
                cleanupExec();
                throw th2;
            }
        }
        updateOnBackPressedCallbackEnabled();
        doPendingDeferredStart();
        this.mFragmentStore.burpActive();
        return popBackStackState;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class FragmentLifecycleCallbacks {
        public abstract void onFragmentAttached(FragmentManager fragmentManager, Fragment fragment, Context context);

        public abstract void onFragmentDetached(FragmentManager fragmentManager, Fragment fragment);

        public void onFragmentDestroyed(FragmentManager fragmentManager, Fragment fragment) {
        }

        public void onFragmentPaused(FragmentManager fragmentManager, Fragment fragment) {
        }

        public void onFragmentResumed(FragmentManager fragmentManager, Fragment fragment) {
        }

        public void onFragmentStarted(FragmentManager fragmentManager, Fragment fragment) {
        }

        public void onFragmentStopped(FragmentManager fragmentManager, Fragment fragment) {
        }

        public void onFragmentViewDestroyed(FragmentManager fragmentManager, Fragment fragment) {
        }

        @Deprecated
        public void onFragmentActivityCreated(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
        }

        public void onFragmentCreated(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
        }

        public void onFragmentPreAttached(FragmentManager fragmentManager, Fragment fragment, Context context) {
        }

        public void onFragmentPreCreated(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
        }

        public void onFragmentSaveInstanceState(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
        }

        public void onFragmentViewCreated(FragmentManager fragmentManager, Fragment fragment, View view, Bundle bundle) {
        }
    }
}
