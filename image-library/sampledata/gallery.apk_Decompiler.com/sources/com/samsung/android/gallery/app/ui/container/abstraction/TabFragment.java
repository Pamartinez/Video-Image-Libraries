package com.samsung.android.gallery.app.ui.container.abstraction;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.settings.MarketUpgradeManager;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import e5.C0451a;
import h4.C0464a;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TabFragment<V extends IMvpBaseView, P extends MvpBasePresenter> extends MvpBaseFragment<V, P> implements ITabView {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadBadge$0() {
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            blackboard.publish(CommandKey.DATA_REQUEST("data://badge/all"), (Object) null);
            MarketUpgradeManager.getInstance().checkUpgrade();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$supportExitPredictiveBackInCurrent$1(MvpBaseFragment mvpBaseFragment) {
        return mvpBaseFragment instanceof IBaseListView;
    }

    public abstract void bindTabView(View view);

    public boolean checkTabSelectable() {
        MvpBaseFragment currentFragment = getCurrentFragment();
        if (!(currentFragment instanceof IBaseListView) || !((IBaseListView) currentFragment).checkTabSelectable()) {
            return false;
        }
        return true;
    }

    public P createPresenter(V v) {
        return createTabPresenter(v);
    }

    public abstract P createTabPresenter(V v);

    public void deliverEvent(EventMessage eventMessage) {
        MvpBaseFragment currentFragment = getCurrentFragment();
        if (currentFragment != null) {
            currentFragment.onHandleEvent(eventMessage);
        }
    }

    public String getAppState() {
        MvpBaseFragment currentFragment = getCurrentFragment();
        if (currentFragment instanceof IBaseListView) {
            return ((IBaseListView) currentFragment).getAppState();
        }
        return null;
    }

    public GalleryAppBarLayout getAppbarLayout() {
        MvpBaseFragment currentFragment = getCurrentFragment();
        if (currentFragment != null) {
            return currentFragment.getAppbarLayout();
        }
        return null;
    }

    public List<Fragment> getChildFragments() {
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if (fragments.size() > 0) {
            MvpBaseFragment currentFragment = getCurrentFragment();
            fragments.remove(currentFragment);
            fragments.add(currentFragment);
        }
        return fragments;
    }

    public int getLayoutId() {
        return getTabLayoutId();
    }

    public String getScreenId() {
        MvpBaseFragment currentFragment = getCurrentFragment();
        if (currentFragment != null) {
            return currentFragment.getScreenId();
        }
        return null;
    }

    public abstract int getTabLayoutId();

    public GalleryToolbar getToolbar() {
        MvpBaseFragment currentFragment = getCurrentFragment();
        if (currentFragment != null) {
            return currentFragment.getToolbar();
        }
        return null;
    }

    public void initView(View view) {
        bindTabView(view);
    }

    public boolean isMoveMode() {
        MvpBaseFragment currentFragment = getCurrentFragment();
        if (!(currentFragment instanceof IBaseListView) || !((IBaseListView) currentFragment).isMoveMode()) {
            return false;
        }
        return true;
    }

    public boolean isSelectionMode() {
        MvpBaseFragment currentFragment = getCurrentFragment();
        if (!(currentFragment instanceof IBaseListView) || !((IBaseListView) currentFragment).isSelectionMode()) {
            return false;
        }
        return true;
    }

    public void loadBadge() {
        ThreadUtil.postOnBgThreadDelayed(new C0451a(22, this), 2000);
    }

    public boolean onAdvancedMouseEvent(MotionEvent motionEvent) {
        MvpBaseFragment currentFragment = getCurrentFragment();
        if (currentFragment != null) {
            return currentFragment.onAdvancedMouseEvent(motionEvent);
        }
        return super.onAdvancedMouseEvent(motionEvent);
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        MvpBaseFragment currentFragment = getCurrentFragment();
        if (currentFragment != null) {
            return currentFragment.onGenericMotionEvent(motionEvent);
        }
        Log.e(this.TAG, "current is null. onGenericMotionEvent");
        return false;
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        showOrHide(getCurrentFragment(), !z);
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        MvpBaseFragment currentFragment = getCurrentFragment();
        if (currentFragment != null && currentFragment.isViewActive()) {
            return currentFragment.onKeyDown(i2, keyEvent);
        }
        Log.e(this.TAG, "current is null or inactive. onKeyDown");
        return false;
    }

    public boolean onKeyLongPress(int i2, KeyEvent keyEvent) {
        MvpBaseFragment currentFragment = getCurrentFragment();
        if (currentFragment != null && currentFragment.isViewActive()) {
            return currentFragment.onKeyLongPress(i2, keyEvent);
        }
        Log.e(this.TAG, "current is null or inactive. onKeyLongPress");
        return false;
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        MvpBaseFragment currentFragment = getCurrentFragment();
        if (currentFragment != null && currentFragment.isViewActive()) {
            return currentFragment.onKeyUp(i2, keyEvent);
        }
        Log.e(this.TAG, "current is null or inactive. onKeyUp");
        return false;
    }

    public boolean onPenEvent(MotionEvent motionEvent) {
        MvpBaseFragment currentFragment = getCurrentFragment();
        if (currentFragment != null) {
            return currentFragment.onPenEvent(motionEvent);
        }
        return super.onPenEvent(motionEvent);
    }

    public void onPrePause(boolean z) {
        MvpBaseFragment currentFragment = getCurrentFragment();
        if (currentFragment != null) {
            currentFragment.onPrePause(z);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        try {
            Trace.beginSection(this.TAG + "onViewCreated");
            super.onViewCreated(view, bundle);
        } finally {
            Trace.endSection();
        }
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
        IBaseListView iBaseListView = (IBaseListView) Optional.ofNullable(getCurrentFragment()).filter(new C0464a(0)).orElse((Object) null);
        if (iBaseListView == null || !iBaseListView.supportExitPredictiveBack()) {
            return false;
        }
        return true;
    }

    public boolean supportTabLayout() {
        MvpBaseFragment currentFragment = getCurrentFragment();
        if (currentFragment == null || !currentFragment.supportTabLayout()) {
            return false;
        }
        return true;
    }

    public boolean supportToolbar() {
        return false;
    }

    public String toString() {
        return super.toString() + "/" + getCurrentFragment();
    }
}
