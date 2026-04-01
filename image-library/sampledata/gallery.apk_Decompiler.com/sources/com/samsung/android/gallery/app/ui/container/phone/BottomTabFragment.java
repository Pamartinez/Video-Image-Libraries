package com.samsung.android.gallery.app.ui.container.phone;

import A4.C0369d;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import com.google.android.material.tabs.c;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.container.abstraction.ITabConsumer;
import com.samsung.android.gallery.app.ui.container.abstraction.TabFragment;
import com.samsung.android.gallery.app.ui.container.factory.ChildFragmentCache;
import com.samsung.android.gallery.app.ui.container.factory.ChildFragmentFactory;
import com.samsung.android.gallery.app.ui.container.factory.ChildFragmentInfo;
import com.samsung.android.gallery.app.ui.container.menu.BottomMenuDelegate;
import com.samsung.android.gallery.app.ui.container.menu.BottomTabMenu;
import com.samsung.android.gallery.app.ui.container.menu.BottomTabSelectListener;
import com.samsung.android.gallery.app.ui.container.menu.BottomTabTouchDelegate;
import com.samsung.android.gallery.app.ui.container.menu.TabMenuHelper;
import com.samsung.android.gallery.app.ui.container.phone.BottomTabPresenter;
import com.samsung.android.gallery.app.ui.container.phone.IBottomTabView;
import com.samsung.android.gallery.module.badge.BadgeHelper;
import com.samsung.android.gallery.module.logger.Analytics;
import com.samsung.android.gallery.module.mtp.MtpClient;
import com.samsung.android.gallery.module.store.AppRatingHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.bottom.BottomTabLayout;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import g6.g;
import ic.l;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import l4.a;
import l4.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomTabFragment<V extends IBottomTabView, P extends BottomTabPresenter<V>> extends TabFragment<V, P> implements IBottomTabView, ITabConsumer {
    private BottomMenuDelegate mBottomMenuDelegate;
    private ViewGroup mBottomTabContainer;
    private BottomTabLayout mBottomTabLayout;
    private BottomTabMenu mBottomTabMenu;
    private BottomTabTouchDelegate mBottomTabTouchDelegate;
    private final ChildFragmentCache mChildFragmentCache = new ChildFragmentCache(getChildFragmentFactoryType());
    private final ChildFragmentInfo mChildFragmentInfo = new ChildFragmentInfo();
    private MvpBaseFragment<?, ?> mCurrentFragment;
    private BottomTabSelectListener mTabSelectListener;

    public BottomTabFragment() {
        Trace.endSection();
    }

    private void adjustBottomTabContainerLayout(WindowInsets windowInsets) {
        ViewMarginUtils.setBottomMargin(this.mBottomTabContainer, WindowUtils.getSystemInsetsBottom(windowInsets));
        ViewMarginUtils.setStartMargin(this.mBottomTabContainer, WindowUtils.getSystemInsetsStart(windowInsets));
        ViewMarginUtils.setEndMargin(this.mBottomTabContainer, WindowUtils.getSystemInsetsEnd(windowInsets));
    }

    private Fragment commitChildFragment(String str) {
        MvpBaseFragment<?, ?> mvpBaseFragment = this.mChildFragmentCache.get(this.mChildFragmentInfo, str);
        setArgumentOnSwitchFragment(mvpBaseFragment, str);
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        Optional<T> ofNullable = Optional.ofNullable(this.mCurrentFragment);
        Objects.requireNonNull(beginTransaction);
        ofNullable.ifPresent(new g(18, beginTransaction));
        beginTransaction.add((int) R.id.fragment_container, (Fragment) mvpBaseFragment, str).commitAllowingStateLoss();
        return mvpBaseFragment;
    }

    private BottomMenuDelegate getBottomMenu() {
        if (this.mBottomMenuDelegate == null) {
            this.mBottomMenuDelegate = createBottomMenuDelegate(this.mBlackboard);
        }
        return this.mBottomMenuDelegate;
    }

    private BottomTabTouchDelegate getBottomTabTouchDelegate() {
        if (this.mBottomTabTouchDelegate == null) {
            this.mBottomTabTouchDelegate = new BottomTabTouchDelegate();
        }
        return this.mBottomTabTouchDelegate;
    }

    private BottomTabSelectListener getTabSelectListener() {
        if (this.mTabSelectListener == null) {
            this.mTabSelectListener = new BottomTabSelectListener(this);
        }
        return this.mTabSelectListener;
    }

    private boolean isAlbumsFirstSelect(c cVar) {
        if (cVar.d != 1 || !this.mChildFragmentInfo.isAlbumFirstSelect()) {
            return false;
        }
        return true;
    }

    private boolean isTimelineFirstSelect(c cVar) {
        if (cVar.d != 0 || !this.mChildFragmentInfo.isTimeFirstSelect()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishDataRequest$5(c cVar, Blackboard blackboard) {
        if (isTimelineFirstSelect(cVar) && blackboard.isEmpty(DataKey.DATA_CURSOR("location://timeline"))) {
            BlackboardUtils.publishDataRequest(blackboard, getTimelineFakeLocationKey());
        } else if (isAlbumsFirstSelect(cVar)) {
            BlackboardUtils.publishDataRequest(blackboard, LocationKey.getCacheLocationKeyForAlbum());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setScreenMode$1() {
        super.setScreenMode();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$showFragment$4(FragmentTransaction fragmentTransaction, String str, MvpBaseFragment mvpBaseFragment) {
        fragmentTransaction.hide(mvpBaseFragment);
        mvpBaseFragment.postAnalyticsLog(Analytics.getEventId(str));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$switchFragment$3(AtomicReference atomicReference, String str, Fragment fragment) {
        atomicReference.set(fragment);
        showFragment(str, fragment);
    }

    /* access modifiers changed from: private */
    public void onMenuTabTouched() {
        showMenuList();
        scrollToMenuTab();
    }

    private void publishDataRequest(c cVar) {
        Optional.ofNullable(getBlackboard()).ifPresent(new e(28, this, cVar));
    }

    private void publishNextLocationKey(String str) {
        if (!str.equals((String) this.mBlackboard.read("location://variable/currentv1"))) {
            this.mBlackboard.publish("location://variable/currentv1", str);
        }
    }

    private void removeMenuTabTouchListener() {
        getBottomTabTouchDelegate().removeMenuTabTouchListener();
    }

    private void removeTabSelectListener() {
        BottomTabLayout bottomTabLayout = this.mBottomTabLayout;
        if (bottomTabLayout != null) {
            bottomTabLayout.removeOnTabSelectedListener((A2.g) getTabSelectListener());
        }
    }

    private void scrollToMenuTab() {
        int i2;
        BottomTabLayout bottomTabLayout = this.mBottomTabLayout;
        if (bottomTabLayout != null) {
            if (Features.isEnabled(Features.IS_RTL)) {
                i2 = 17;
            } else {
                i2 = 66;
            }
            bottomTabLayout.fullScroll(i2);
        }
    }

    private void selectView(int i2, boolean z) {
        FragmentActivity activity = getActivity();
        if (activity == null || !activity.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
            Log.e(this.TAG, "not created");
            return;
        }
        if (((BottomTabPresenter) this.mPresenter).setInputBlock(this.TAG + "_selecView") || z) {
            String locationKeyById = TabMenuHelper.getLocationKeyById(i2);
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "selectView {" + locationKeyById + "}");
            switchFragment(locationKeyById);
            setLocationKey(locationKeyById);
            saveCurrentState(locationKeyById);
            return;
        }
        setBottomTabSelected(getLocationKey());
    }

    private void setBottomTabSelected(String str) {
        setBottomTabSelected(str, false);
    }

    private void setMenuTabTouchListener() {
        if (TabMenuHelper.hasMoreTab(this.mBlackboard)) {
            getBottomTabTouchDelegate().addOnTabTouchListener(this.mBottomTabLayout.getMenuTabView(), new a(this, 0));
        }
    }

    private void setTabSelectListener() {
        this.mBottomTabLayout.addOnTabSelectedListener((A2.g) getTabSelectListener());
    }

    private void showFragment(String str, Fragment fragment) {
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        Optional.ofNullable(this.mCurrentFragment).ifPresent(new e(29, beginTransaction, str));
        beginTransaction.show(fragment).commitAllowingStateLoss();
    }

    private void switchFragment(String str) {
        AtomicReference atomicReference = new AtomicReference();
        Optional.ofNullable(getChildFragmentManager().findFragmentByTag(str)).ifPresent(new com.samsung.android.sum.core.functional.g(this, atomicReference, str, 7));
        if (atomicReference.get() == null) {
            atomicReference.set(commitChildFragment(str));
        }
        publishNextLocationKey(str);
        Optional.ofNullable(this.mCurrentFragment).ifPresent(new l(20));
        setCurrentFragment((MvpBaseFragment) atomicReference.get());
        ((MvpBaseFragment) atomicReference.get()).onSelectedFromBottomTab();
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mBottomTabContainer = (ViewGroup) view.findViewById(R.id.tab_layout_container);
        this.mBottomTabLayout = (BottomTabLayout) view.findViewById(R.id.tab_layout);
    }

    public void blockFocus(boolean z) {
        this.mBottomTabLayout.blockFocus(z);
    }

    public void cancel(boolean z) {
        setBottomTabSelected(getLocationKey(), z);
    }

    public BottomMenuDelegate createBottomMenuDelegate(Blackboard blackboard) {
        return new BottomMenuDelegate(blackboard);
    }

    public String getChildFragmentFactoryType() {
        return ChildFragmentFactory.BOTTOM_NORMAL;
    }

    public MvpBaseFragment<?, ?> getCurrentFragment() {
        return this.mCurrentFragment;
    }

    public String getFragmentTag(String str) {
        return "BottomTabFragment";
    }

    public int getTabLayoutId() {
        return R.layout.fragment_bottom_tab_container;
    }

    public String getTimelineFakeLocationKey() {
        return "location://timeline/fake";
    }

    public void handleDensityChange(int i2) {
        int selectedTabPosition = this.mBottomTabLayout.getSelectedTabPosition();
        boolean isVisible = ViewUtils.isVisible(this.mBottomTabLayout);
        BottomTabLayout bottomTabLayout = (BottomTabLayout) LayoutInflater.from(getContext()).inflate(R.layout.bottom_tab_layout, (ViewGroup) null, false).findViewById(R.id.tab_layout);
        getBottomMenu().invalidate(bottomTabLayout, getLocationKey());
        ViewUtils.removeSelf(bottomTabLayout);
        ViewGroup viewGroup = (ViewGroup) this.mBottomTabLayout.getParent();
        int indexOfChild = viewGroup.indexOfChild(this.mBottomTabLayout);
        ViewUtils.removeView(viewGroup, this.mBottomTabLayout);
        viewGroup.addView(bottomTabLayout, indexOfChild);
        removeTabSelectListener();
        removeMenuTabTouchListener();
        this.mBottomTabLayout = bottomTabLayout;
        ((BottomTabPresenter) this.mPresenter).releaseInputBlocking(-1);
        Optional.ofNullable(this.mBottomTabLayout.getTabAt(selectedTabPosition)).ifPresent(new l(22));
        setTabSelectListener();
        setMenuTabTouchListener();
        this.mBottomTabLayout.refresh(isVisible);
        loadBadge();
        ViewMarginUtils.setHorizontalPadding(this.mBottomTabContainer, this.mBottomTabLayout.getResources().getDimensionPixelSize(R.dimen.bottom_tab_layout_container_padding_horizontal));
        this.mBottomTabContainer.getLayoutParams().height = -2;
        this.mBottomTabContainer.requestLayout();
    }

    public void handleResolutionChange(int i2) {
        BottomTabMenu bottomTabMenu = this.mBottomTabMenu;
        if (bottomTabMenu != null) {
            bottomTabMenu.updateLayout();
        }
    }

    public void hideTabLayout() {
        BottomTabLayout bottomTabLayout = this.mBottomTabLayout;
        if (bottomTabLayout != null) {
            bottomTabLayout.applyAnimation(false);
        }
    }

    public void hideTabMenu() {
        BottomTabMenu bottomTabMenu = this.mBottomTabMenu;
        if (bottomTabMenu != null) {
            bottomTabMenu.dismissDialog();
        }
    }

    public boolean needToRegisterInsetListener() {
        return true;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        adjustBottomTabContainerLayout(view.getRootWindowInsets());
        return windowInsets;
    }

    public boolean onBackPressed() {
        if (checkTabSelectable() && AppRatingHelper.showDialog(getBlackboard())) {
            return true;
        }
        MvpBaseFragment<?, ?> currentFragment = getCurrentFragment();
        if (currentFragment == null || !currentFragment.onBackPressed()) {
            return false;
        }
        return true;
    }

    public void onBindView(View view) {
        selectView(TabMenuHelper.getIdByLocationKey(getLocationKey()), true);
        getBottomMenu().load();
        if (this.mBottomTabLayout != null) {
            getBottomMenu().invalidate(this.mBottomTabLayout, getLocationKey());
            setBottomTabSelected(getLocationKey());
            setTabSelectListener();
            setMenuTabTouchListener();
            this.mBottomTabLayout.invalidate();
            loadBadge();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Trace.beginSection("APP_BottomTabFragment onCreateView");
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        Trace.endSection();
        return onCreateView;
    }

    public void onDestroy() {
        super.onDestroy();
        removeTabSelectListener();
        removeMenuTabTouchListener();
        getBottomMenu().destroy();
        this.mCurrentFragment = null;
        this.mChildFragmentCache.clear();
        removeChildFragments();
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        if (eventMessage.what == 1135 && isResumed()) {
            setScreenMode();
        }
        if (eventMessage.what == 1137) {
            setInputBlock(this.TAG + "_storyOnDemand", 500);
        }
        if (eventMessage.what != 1148) {
            return false;
        }
        showMenuList();
        return false;
    }

    public void onPause() {
        hideTabMenu();
        super.onPause();
    }

    public void onUpdateBottomTabFloatingView(View view) {
        BottomTabLayout bottomTabLayout = this.mBottomTabLayout;
        if (bottomTabLayout != null) {
            bottomTabLayout.updateFloatingView(view);
        }
    }

    public void registerWindowInsetListener(List<View> list) {
        if (needToRegisterInsetListener()) {
            list.add(getView());
        }
    }

    public void removeSiblingFragments(String[] strArr) {
        if (!isDestroyed()) {
            try {
                List<Fragment> fragments = getChildFragmentManager().getFragments();
                if (fragments.size() > strArr.length) {
                    FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
                    ArrayList arrayList = new ArrayList(Arrays.asList(strArr));
                    for (Fragment next : fragments) {
                        if (!(next == null || next == this.mCurrentFragment || arrayList.contains(next.getTag()))) {
                            beginTransaction.remove(next);
                            this.mChildFragmentCache.remove(next.getTag());
                            this.mChildFragmentInfo.setFirstSelect(next.getTag(), true);
                        }
                    }
                    this.mBlackboard.post("command://ClearPreloadedData", "location://story/albums");
                    beginTransaction.commitAllowingStateLoss();
                }
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "removeSiblingFragments failed", (Throwable) e);
            }
        }
    }

    public void saveCurrentState(String str) {
        ThreadUtil.postOnBgThread(new b9.a(str, 2));
    }

    public void select(c cVar) {
        publishDataRequest(cVar);
        selectView(cVar.f1501a.intValue(), false);
    }

    public void selectBottomNavigationMenu(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(this.mBlackboard.read("location://variable/currentv1"))) {
            setBottomTabSelected(str);
            setLocationKey(str);
            saveCurrentState(str);
        }
    }

    public void setCurrentFragment(MvpBaseFragment<?, ?> mvpBaseFragment) {
        this.mCurrentFragment = mvpBaseFragment;
    }

    public void setCurrentTransitioningAnim(int i2) {
        Optional.ofNullable(getCurrentFragment()).ifPresent(new C0369d(i2, 28));
    }

    public void setDefaultExitTransitioning(boolean z) {
        Optional.ofNullable(getCurrentFragment()).ifPresent(new b(z, 0));
    }

    public void setScreenMode() {
        if (this.mBlackboard.read("data://shrink_active") == null) {
            MvpBaseFragment<?, ?> mvpBaseFragment = this.mCurrentFragment;
            if (mvpBaseFragment == null || !mvpBaseFragment.delayScreenMode()) {
                super.setScreenMode();
            } else {
                ThreadUtil.postOnUiThread(new k6.b(5, this));
            }
        }
    }

    public void showMenuList() {
        if (((BottomTabPresenter) this.mPresenter).setInputBlock(this.TAG + "_showMenuList", 500)) {
            Optional.ofNullable(getCurrentFragment()).ifPresent(new l(21));
            if (this.mBottomTabMenu == null) {
                this.mBottomTabMenu = new BottomTabMenu(this.mPresenter);
            }
            this.mBottomTabMenu.showBottomTabDialog();
            BadgeHelper.updateMenuTabBadgeIfNecessary();
        }
    }

    public void showTabLayout() {
        BottomTabLayout bottomTabLayout = this.mBottomTabLayout;
        if (bottomTabLayout != null) {
            bottomTabLayout.applyAnimation(true);
        }
    }

    public boolean supportLayoutCache() {
        return true;
    }

    public void updateBadgeOnTab(int i2, boolean z) {
        BottomTabLayout bottomTabLayout = this.mBottomTabLayout;
        if (bottomTabLayout != null) {
            bottomTabLayout.updateBadge(i2, z);
        }
    }

    public void updateBottomNavigationMenu() {
        boolean z;
        if (isDestroyed()) {
            Log.w(this.TAG, "updateBottomNavigationMenu : BottomTabFragment was destroyed");
        } else if (this.mBottomTabLayout != null) {
            if (!PickerUtil.isNormalLaunchMode(this.mBlackboard) || !MtpClient.getInstance(getApplicationContext()).isAvailable()) {
                z = false;
            } else {
                z = true;
            }
            if (getBottomMenu().updateTabVisibility("location://mtp", z)) {
                getBottomMenu().invalidate(this.mBottomTabLayout, getLocationKey());
            }
        }
    }

    private void setBottomTabSelected(String str, boolean z) {
        BottomTabLayout bottomTabLayout = this.mBottomTabLayout;
        if (bottomTabLayout != null) {
            bottomTabLayout.setTabSelected(TabMenuHelper.getIdByLocationKey(str), z);
        }
    }

    public BottomTabPresenter<?> createTabPresenter(IBottomTabView iBottomTabView) {
        return new BottomTabPresenter<>(this.mBlackboard, iBottomTabView);
    }

    public void postAnalyticsLog() {
    }

    public void bindTabView(View view) {
    }

    public void setArgumentOnSwitchFragment(Fragment fragment, String str) {
    }
}
