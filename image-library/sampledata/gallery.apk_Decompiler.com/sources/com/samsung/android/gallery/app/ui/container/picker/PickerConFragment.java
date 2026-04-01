package com.samsung.android.gallery.app.ui.container.picker;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.container.abstraction.TabFragment;
import com.samsung.android.gallery.app.ui.container.clipboard.ClipboardFragment;
import com.samsung.android.gallery.app.ui.container.clipboard.ClipboardFragmentV2;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.sec.android.gallery3d.R;
import g6.g;
import h4.C0464a;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import k6.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PickerConFragment extends MvpBaseFragment<IPickerConView, PickerConPresenter> implements IPickerConView {
    private final Stack<String> mBackStack = new Stack<>();
    private MvpBaseFragment<?, ?> mTabFragment;

    private boolean findFragmentByTag(FragmentManager fragmentManager) {
        if (fragmentManager == null || fragmentManager.findFragmentByTag("ClipboardFragment") == null) {
            return false;
        }
        return true;
    }

    private boolean isExistedClipboardFragment() {
        if (findFragmentByTag(getParentFragmentManager()) || findFragmentByTag(getChildFragmentManager())) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$finishChildFragment$1() {
        getChildFragmentManager().popBackStack();
    }

    private void setFragmentAnimation(IMvpBaseView iMvpBaseView, IMvpBaseView iMvpBaseView2, FragmentTransaction fragmentTransaction) {
        if (PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack) && PreferenceFeatures.OneUi7x.FRAGMENT_H_SLIDE_ANIMATION && iMvpBaseView2.supportEnterDefaultTransition() && iMvpBaseView.supportExitDefaultTransition()) {
            iMvpBaseView.reservePreventNoBackground(true);
            iMvpBaseView2.reservePreventNoBackground(true);
            fragmentTransaction.setCustomAnimations(R.animator.sesl_fragment_open_enter, R.animator.sesl_fragment_open_exit, R.animator.sesl_fragment_close_enter, R.animator.sesl_fragment_close_exit);
        }
    }

    public boolean commitChildFragment(Fragment fragment, String str) {
        Bundle arguments = fragment.getArguments();
        String str2 = null;
        if (arguments != null) {
            str2 = arguments.getString("locationKey", (String) null);
        }
        if (LocationKey.isContentViewer(str2)) {
            return false;
        }
        try {
            Optional.ofNullable(getTopFragment()).filter(new C0464a(10)).ifPresent(new g(23, this));
            Log.d(this.TAG, "commitChildFragment", Integer.valueOf(this.mBackStack.size()), Logger.getSimpleName((Object) fragment), str);
            if (str == null) {
                str = Logger.getSimpleName((Object) fragment);
            }
            FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
            if (PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
                setFragmentAnimation(this, (IMvpBaseView) fragment, beginTransaction);
            }
            beginTransaction.add(fragment, str);
            beginTransaction.replace(R.id.content, fragment, str);
            if (!this.mBackStack.isEmpty()) {
                beginTransaction.addToBackStack(str);
            }
            this.mBackStack.push(str);
            this.mBlackboard.publish("data://picker_mode/stack_size", Integer.valueOf(this.mBackStack.size()));
            beginTransaction.commitAllowingStateLoss();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void finishChildFragment(IBaseFragment iBaseFragment) {
        if (getChildFragmentManager().isStateSaved()) {
            ThreadUtil.postponeOnUiThread(new b(9, this));
        } else {
            getChildFragmentManager().popBackStack();
        }
        this.mBackStack.pop();
        this.mBlackboard.publish("data://picker_mode/stack_size", Integer.valueOf(this.mBackStack.size()));
        if (!PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            this.mBlackboard.post("data://child_fragment_changed", (Object) null);
        }
        this.mBlackboard.post("command://MoveCMD", "command://MoveCMD/SyncBackKey");
    }

    public List<Fragment> getChildFragments() {
        MvpBaseFragment<?, ?> mvpBaseFragment;
        MvpBaseFragment currentFragment;
        List<Fragment> childFragments = super.getChildFragments();
        if (!childFragments.isEmpty() && (mvpBaseFragment = this.mTabFragment) != null && (currentFragment = ((TabFragment) mvpBaseFragment).getCurrentFragment()) != null && !childFragments.contains(currentFragment)) {
            childFragments.add(0, currentFragment);
        }
        return childFragments;
    }

    public int getLayoutId() {
        return R.layout.fragment_picker_container;
    }

    public IBaseFragment getTopFragment() {
        String str;
        if (this.mBackStack.isEmpty()) {
            str = null;
        } else {
            str = this.mBackStack.peek();
        }
        if (str != null) {
            return (IBaseFragment) getChildFragmentManager().findFragmentByTag(str);
        }
        return null;
    }

    public boolean onAdvancedMouseEvent(MotionEvent motionEvent) {
        if (getTopFragment() != null) {
            return getTopFragment().onAdvancedMouseEvent(motionEvent);
        }
        return super.onAdvancedMouseEvent(motionEvent);
    }

    public boolean onBackPressed() {
        Log.d(this.TAG, "onBackPressed", Integer.valueOf(this.mBackStack.size()));
        IBaseFragment topFragment = getTopFragment();
        if (topFragment != null && topFragment.onBackPressed()) {
            return true;
        }
        if (this.mBackStack.size() <= 1) {
            return false;
        }
        finishChildFragment(topFragment);
        return true;
    }

    public void onBindFragment() {
        BottomTabPickerFragment bottomTabPickerFragment = new BottomTabPickerFragment();
        Bundle arguments = getArguments();
        if (arguments != null) {
            bottomTabPickerFragment.setArguments(arguments.deepCopy());
        }
        if (commitChildFragment(bottomTabPickerFragment, bottomTabPickerFragment.getTag())) {
            this.mTabFragment = bottomTabPickerFragment;
        }
    }

    public void onBindView(View view) {
        Fragment fragment;
        if (PickerUtil.supportClipboard(getBlackboard()) && !isExistedClipboardFragment()) {
            FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
            if (PickerUtil.supportSearch()) {
                fragment = new ClipboardFragmentV2();
            } else {
                fragment = new ClipboardFragment();
            }
            beginTransaction.add((int) R.id.clipboard, fragment, "ClipboardFragment").commitAllowingStateLoss();
            View findViewById = view.findViewById(R.id.clipboard);
            if (findViewById != null) {
                findViewById.setVisibility(0);
            }
        }
        onBindFragment();
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (getTopFragment() != null) {
            return getTopFragment().onGenericMotionEvent(motionEvent);
        }
        Log.e(this.TAG, "current is null. onGenericMotionEvent");
        return false;
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (getTopFragment() != null) {
            return getTopFragment().onKeyDown(i2, keyEvent);
        }
        Log.e(this.TAG, "current is null. onKeyDown");
        return false;
    }

    public boolean onKeyLongPress(int i2, KeyEvent keyEvent) {
        if (getTopFragment() != null) {
            return getTopFragment().onKeyLongPress(i2, keyEvent);
        }
        Log.e(this.TAG, "current is null. onKeyLongPress");
        return false;
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (getTopFragment() != null) {
            return getTopFragment().onKeyUp(i2, keyEvent);
        }
        Log.e(this.TAG, "current is null. onKeyUp");
        return false;
    }

    public boolean onPenEvent(MotionEvent motionEvent) {
        if (getTopFragment() != null) {
            return getTopFragment().onPenEvent(motionEvent);
        }
        return super.onPenEvent(motionEvent);
    }

    public void startShrinkAnimation() {
        SharedTransition.startPostponedEnterTransition(this, this.mBlackboard);
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public boolean supportExitPredictiveBack() {
        if (!super.supportExitPredictiveBack() || !supportExitPredictiveBackInCurrent()) {
            return false;
        }
        return true;
    }

    public boolean supportExitPredictiveBackInCurrent() {
        IBaseFragment topFragment = getTopFragment();
        if (topFragment == null || !topFragment.supportExitPredictiveBack()) {
            return false;
        }
        return true;
    }

    public PickerConPresenter createPresenter(IPickerConView iPickerConView) {
        return new PickerConPresenter(getBlackboard(), iPickerConView);
    }

    public void postAnalyticsLog() {
    }

    public void initView(View view) {
    }
}
