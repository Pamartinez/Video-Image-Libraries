package com.samsung.android.gallery.app.ui.container.tablet;

import A4.C0369d;
import A4.J;
import A4.M;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import b9.a;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.container.abstraction.IListContainerView;
import com.samsung.android.gallery.app.ui.container.abstraction.ITabConsumer;
import com.samsung.android.gallery.app.ui.container.abstraction.ITabController;
import com.samsung.android.gallery.app.ui.container.abstraction.TabFragment;
import com.samsung.android.gallery.app.ui.container.tablet.ListContainerPresenter;
import com.samsung.android.gallery.app.ui.container.tablet.bottomtab.BottomTabController;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerPopupMenu;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabController;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.store.AppRatingHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.behavior.SheetBehaviorCompat;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.BackPressUtils;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.sum.core.filter.f;
import com.sec.android.gallery3d.R;
import java.util.List;
import java.util.Optional;
import l4.b;
import m7.c;
import n4.C0489a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ListContainerFragment<V extends IListContainerView, P extends ListContainerPresenter<IListContainerView>> extends TabFragment<V, P> implements IListContainerView {
    private final ITabController mBottomTabController = new BottomTabController(this);
    private String mCurrentAlbumKey;
    private ITabController mCurrentController;
    private final ITabController mDrawerTabController = new DrawerTabController(this);
    protected FrameLayout mFragmentContainer;
    private boolean mIsSettingsBadgeShowing;
    private boolean mIsSharedAlbumsBadgeShowing;
    private boolean mIsStoriesBadgeShowing;
    private final ListContainerManager mListContainerManager = new ListContainerManager(this);
    private DrawerPopupMenu mPopupMenu;
    private Drawable mToolbarNavigationRipple;

    public ListContainerFragment() {
        Trace.endSection();
    }

    private boolean consumeByCurrentFragment() {
        MvpBaseFragment currentFragment = getCurrentFragment();
        if (currentFragment == null || !currentFragment.onBackPressed()) {
            return false;
        }
        return true;
    }

    private boolean consumeByListContainerManager() {
        return removeChildFragment();
    }

    private boolean consumeByRootFragment() {
        if (hasChildFragments() || !checkTabSelectable() || !AppRatingHelper.showDialog(this.mBlackboard)) {
            return false;
        }
        return true;
    }

    private void exitSelectionModeIfNeeded(String str) {
        if (isSelectionMode() && !this.mListContainerManager.isSwitchingAlbum(str)) {
            this.mBlackboard.postEvent(EventMessage.obtain(1003));
        }
    }

    private String getFocusKey() {
        String focusKey = this.mCurrentController.getFocusKey();
        if (!TextUtils.isEmpty(focusKey)) {
            return focusKey;
        }
        return getLocationKey();
    }

    private ViewGroup getFragmentSlidingPanel() {
        FrameLayout frameLayout = this.mFragmentContainer;
        if (frameLayout == null || !supportTabLayout() || !supportTopColorEffect()) {
            return frameLayout;
        }
        return (ViewGroup) frameLayout.findViewById(R.id.coordinator_slide_panel);
    }

    private View getListView() {
        MvpBaseFragment currentFragment = getCurrentFragment();
        if (currentFragment instanceof IBaseListView) {
            return ((IBaseListView) currentFragment).getListView();
        }
        return null;
    }

    private void handleAlbumsDataChanged() {
        ListContainerManager listContainerManager = this.mListContainerManager;
        if (listContainerManager != null && listContainerManager.isAlbumFragmentExist()) {
            BlackboardUtils.publishDataRequestForce(this.mBlackboard, "location://albums");
        }
    }

    private void handleSharingBadge(int i2, boolean z) {
        if (i2 == R.id.action_sharings && this.mIsSharedAlbumsBadgeShowing != z) {
            this.mIsSharedAlbumsBadgeShowing = z;
            this.mCurrentController.updateBadgeOnHamburger();
        }
    }

    private void handleStoryBadge(int i2, boolean z) {
        if (i2 == R.id.action_stories && this.mIsStoriesBadgeShowing != z) {
            this.mIsStoriesBadgeShowing = z;
            if (isDrawerTabVisible()) {
                this.mCurrentController.updateBadgeOnHamburger();
            }
        }
    }

    private void handleTabletDpiChanged() {
        switchTab();
        resetToolbar();
        updateListColumn();
    }

    private void hideCurrentTab() {
        this.mCurrentController.setVisibility(false, false, false);
    }

    private boolean isBottomTabVisible() {
        if (!supportTabLayout() || isTabletDpi()) {
            return false;
        }
        return true;
    }

    private boolean isDrawerTabVisible() {
        if (!isTabletDpi()) {
            return false;
        }
        if (supportTabLayout() || isDrawerTabVisibleFragment(getCurrentFragment())) {
            return true;
        }
        return false;
    }

    private boolean isDrawerTabVisibleFragment(MvpBaseFragment<?, ?> mvpBaseFragment) {
        String str;
        if (mvpBaseFragment != null) {
            str = mvpBaseFragment.getLocationKey();
        } else {
            str = null;
        }
        if (str == null || !LocationKey.isDrawerVisibleLocation(str)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setBottomMoveBarMargin$5(int i2, int i7, FragmentActivity fragmentActivity) {
        View findViewById = fragmentActivity.findViewById(R.id.bottom_move_bar_layout_container);
        ViewMarginUtils.setStartMargin(findViewById, i2);
        ViewMarginUtils.setEndMargin(findViewById, i7);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateToolbarNavigation$7(View.OnClickListener onClickListener, int i2, GalleryToolbar galleryToolbar) {
        galleryToolbar.setNavigationIcon((Drawable) null);
        galleryToolbar.setNavigationOnClickListener(onClickListener);
        galleryToolbar.setNavigationContentDescription(i2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateToolbarNavigation$8(GalleryToolbar galleryToolbar, int i2, View.OnClickListener onClickListener, int i7) {
        galleryToolbar.setNavigationIcon(i2);
        galleryToolbar.setNavigationOnClickListener(onClickListener);
        galleryToolbar.setNavigationContentDescription(i7);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateToolbarNavigation$9(int i2, View.OnClickListener onClickListener, int i7, GalleryToolbar galleryToolbar) {
        GalleryToolbar galleryToolbar2 = galleryToolbar;
        galleryToolbar2.post(new M((Object) galleryToolbar2, i2, (Object) onClickListener, i7, 9));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateToolbarNavigationRipple$6(boolean z, GalleryToolbar galleryToolbar) {
        Drawable drawable;
        Drawable background;
        View naviUpButton = galleryToolbar.getNaviUpButton();
        if (naviUpButton != null) {
            if (this.mToolbarNavigationRipple == null && (background = naviUpButton.getBackground()) != null) {
                this.mToolbarNavigationRipple = background.mutate();
            }
            if (z) {
                drawable = this.mToolbarNavigationRipple;
            } else {
                drawable = null;
            }
            naviUpButton.setBackground(drawable);
        }
    }

    private void onChildViewCreated(IBaseFragment iBaseFragment) {
        if (iBaseFragment == null) {
            iBaseFragment = getCurrentFragment();
        }
        if ((iBaseFragment instanceof MvpBaseFragment) && ((MvpBaseFragment) iBaseFragment).hasNestedContainer()) {
            iBaseFragment = iBaseFragment.getFocusedChild();
        }
        this.mCurrentController.onChildViewCreated(iBaseFragment);
    }

    private void resetToolbar() {
        if (!isTabletDpi() && getCurrentFragment() != null) {
            getCurrentFragment().updateToolbar(true);
        }
    }

    private boolean supportTopColorEffect() {
        if (getCurrentFragment() == null || !LocationKey.isCollection(getCurrentFragment().getLocationKey())) {
            return false;
        }
        return true;
    }

    private void switchTab() {
        String focusKey = getFocusKey();
        hideCurrentTab();
        updateCurrentController();
        updateTabVisibility(true);
        updateTabModeOnChildFragments();
        updateBottomNavigationMenu();
        updateTabFocus(focusKey);
        updateFloatingLayout();
        loadBadge();
        updateAlbumsTab();
    }

    private void updateAlbumsTab() {
        this.mCurrentController.updateExpandedAlbumsTab();
        this.mCurrentController.updateCollapsedAlbumsTabFocus();
    }

    private void updateCurrentController() {
        ITabController iTabController;
        if (isTabletDpi()) {
            iTabController = this.mDrawerTabController;
        } else {
            iTabController = this.mBottomTabController;
        }
        this.mCurrentController = iTabController;
        this.mBlackboard.publish("data://drawer_tab_enabled", Boolean.valueOf(isTabletDpi()));
        if (PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            Optional.ofNullable(getBlackboard()).ifPresent(new c(7));
        } else {
            this.mBlackboard.post("data://child_fragment_changed", (Object) null);
        }
    }

    private void updateFloatingLayout() {
        MvpBaseFragment currentFragment = getCurrentFragment();
        if (currentFragment instanceof IBaseListView) {
            IBaseListView iBaseListView = (IBaseListView) currentFragment;
            if (iBaseListView.supportTabLayout()) {
                onUpdateBottomTabFloatingView(getListView());
                if (iBaseListView.isSelectionMode() && iBaseListView.getSelectedItemCount() > 0) {
                    Optional.ofNullable(iBaseListView.getBottomBar()).ifPresent(new c(5));
                } else if (iBaseListView.isMoveMode()) {
                    Optional.ofNullable(iBaseListView.getBottomMoveBar()).ifPresent(new c(6));
                }
            }
        }
    }

    private void updateListColumn() {
        if ((getCurrentFragment() instanceof IBaseListView) && ((IBaseListView) getCurrentFragment()).getListView() != null) {
            ((IBaseListView) getCurrentFragment()).updateListColumn();
        }
    }

    private void updateNavigationMode() {
        if (getToolbar() != null) {
            this.mCurrentController.updateNavigationIcon();
            if (!isDrawerTabVisible()) {
                setToolbarStartMargin(0);
            }
        }
    }

    private void updateTabFocus(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mCurrentController.changeFocus(str, true);
        }
    }

    private void updateTabModeOnChildFragments() {
        List<Fragment> childFragments = this.mListContainerManager.getChildFragments();
        if (childFragments == null || childFragments.isEmpty()) {
            updateTabModeOnFragment(getCurrentFragment());
            return;
        }
        for (Fragment updateTabModeOnFragment : childFragments) {
            updateTabModeOnFragment(updateTabModeOnFragment);
        }
    }

    private void updateTabModeOnFragment(Fragment fragment) {
        if (fragment instanceof IBaseListView) {
            ((IBaseListView) fragment).updateTabMode(!isTabletDpi());
        }
    }

    private void updateTabVisibility(boolean z) {
        if (getCurrentFragment() != null) {
            this.mCurrentController.setVisibility(isBottomTabVisible(), isDrawerTabVisible(), z);
            updateNavigationMode();
            if (getCurrentFragment().isViewReady()) {
                onChildViewCreated(getCurrentFragment());
            }
        }
    }

    public void addChildFragment(String str) {
        ListContainerManager listContainerManager = this.mListContainerManager;
        if (listContainerManager != null) {
            listContainerManager.addChildFragment(str);
        }
    }

    public void bindTabView(View view) {
        this.mBottomTabController.bindView(view);
        this.mDrawerTabController.bindView(view);
        selectView(getLocationKey(), true);
        loadBadge();
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mFragmentContainer = (FrameLayout) view.findViewById(R.id.fragment_container);
    }

    public void changeFocus(String str) {
        String str2;
        boolean argValue = ArgumentsUtil.getArgValue(str, "focus_only", false);
        if (!LocationKey.isRootOfContainerExceptTab(str) || LocationKey.isMxVirtualAlbum(str)) {
            str2 = ArgumentsUtil.removeArg(str, "focus_only");
        } else {
            str2 = ArgumentsUtil.removeArgs(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            this.mCurrentController.changeFocus(str2, argValue);
        }
        if (LocationKey.isAlbumsMatch(str2) || LocationKey.isAllAlbumsMatch(str2)) {
            this.mCurrentAlbumKey = str2;
        }
    }

    public ITabController[] getControllers() {
        return new ITabController[]{this.mDrawerTabController, this.mBottomTabController};
    }

    public String getCurrentAlbumKey() {
        return this.mCurrentAlbumKey;
    }

    public MvpBaseFragment getCurrentFragment() {
        ListContainerManager listContainerManager = this.mListContainerManager;
        if (listContainerManager != null) {
            return listContainerManager.getCurrentFragment();
        }
        return null;
    }

    public String getFragmentTag(String str) {
        return "ListContainerFragment";
    }

    public int getTabLayoutId() {
        return R.layout.fragment_list_container;
    }

    public boolean handleConfigurationChange(Configuration configuration) {
        boolean isTabletDpi = isTabletDpi();
        boolean handleConfigurationChange = super.handleConfigurationChange(configuration);
        if (isTabletDpi != isTabletDpi()) {
            handleTabletDpiChanged();
        }
        return handleConfigurationChange;
    }

    public void handleDensityChange(int i2) {
        this.mCurrentController.handleDensityChange();
    }

    public void handleResolutionChange(int i2) {
        this.mCurrentController.handleResolutionChange();
    }

    public boolean hasChildFragments() {
        ListContainerManager listContainerManager = this.mListContainerManager;
        if (listContainerManager == null || !listContainerManager.hasChildFragments()) {
            return false;
        }
        return true;
    }

    public boolean isAlbumFirstSelect() {
        ListContainerManager listContainerManager = this.mListContainerManager;
        if (listContainerManager == null || !listContainerManager.isAlbumFirstSelect()) {
            return false;
        }
        return true;
    }

    public boolean isTimelineFirstSelect() {
        ListContainerManager listContainerManager = this.mListContainerManager;
        if (listContainerManager == null || !listContainerManager.isTimelineFirstSelect()) {
            return false;
        }
        return true;
    }

    public boolean needBadgeOnBottomMenuTab() {
        if (this.mIsSharedAlbumsBadgeShowing || this.mIsSettingsBadgeShowing) {
            return true;
        }
        return false;
    }

    public boolean needBadgeOnNavigationDrawerButton() {
        if (this.mIsStoriesBadgeShowing || this.mIsSharedAlbumsBadgeShowing || this.mIsSettingsBadgeShowing) {
            return true;
        }
        return false;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        WindowInsets windowInsets2;
        if (view != null) {
            if (windowInsets != null) {
                windowInsets2 = windowInsets;
            } else {
                windowInsets2 = view.getRootWindowInsets();
            }
            int systemInsetsStart = WindowUtils.getSystemInsetsStart(windowInsets2);
            if (!isDrawerTabVisible()) {
                systemInsetsStart = 0;
            }
            ViewMarginUtils.setStartMargin(view, systemInsetsStart);
        }
        return windowInsets;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        updateCurrentController();
    }

    public boolean onBackPressed() {
        if (consumeByRootFragment() || consumeByCurrentFragment() || consumeByListContainerManager()) {
            return true;
        }
        return false;
    }

    public void onChildFragmentViewCreated(IBaseFragment iBaseFragment) {
        updateNavigationMode();
        onChildViewCreated(iBaseFragment);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updatePrimaryNavigationFragment();
    }

    public void onCurrentFragmentChanged() {
        updateTabVisibility(false);
        if (!PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            this.mBlackboard.post("data://child_fragment_changed", (Object) null);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mDrawerTabController.unbindView();
        this.mBottomTabController.unbindView();
        this.mListContainerManager.destroy();
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 104) {
            handleAlbumsDataChanged();
        } else if (i2 == 1011) {
            selectView((String) eventMessage.obj, false);
        } else if (i2 != 1112) {
            if (i2 != 1114) {
                if (i2 == 1148) {
                    if (isDrawerMode()) {
                        this.mBlackboard.publish("command://OpenDrawer", (Object) null);
                    } else {
                        ((ITabConsumer) this.mBottomTabController).showMenuList();
                    }
                }
            } else if (!isSelectionMode()) {
                if (this.mPopupMenu == null) {
                    this.mPopupMenu = new DrawerPopupMenu();
                }
                this.mPopupMenu.create(this.mPresenter, eventMessage.obj);
            }
        } else if (SheetBehaviorCompat.isInTransition(((Integer) eventMessage.obj).intValue())) {
            Optional.ofNullable(this.mPopupMenu).ifPresent(new c(4));
        }
        return this.mCurrentController.onHandleEvent(eventMessage);
    }

    public void onMenuItemSelected(String str) {
        if (isAdded() && isResumed()) {
            exitSelectionModeIfNeeded(str);
            ((ListContainerPresenter) this.mPresenter).onMenuItemSelected(str);
        }
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        this.mDrawerTabController.onMultiWindowModeChanged();
    }

    public void onPause() {
        super.onPause();
        updatePrimaryNavigationFragment();
    }

    public boolean onPreSyncFragmentStack() {
        if (!PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack) || !this.mListContainerManager.hasChildFragments()) {
            return false;
        }
        this.mListContainerManager.popFragmentStack();
        updatePrimaryNavigationFragment();
        return true;
    }

    public void onResume() {
        super.onResume();
        updatePrimaryNavigationFragment();
    }

    public void onUpdateBottomTabFloatingView(View view) {
        this.mCurrentController.updateFloatingView(view);
    }

    public boolean refreshChildFragment(String str) {
        ListContainerManager listContainerManager = this.mListContainerManager;
        if (listContainerManager == null || !listContainerManager.refreshChildFragment(str)) {
            return false;
        }
        return true;
    }

    public void registerWindowInsetListener(List<View> list) {
        list.add(getView());
    }

    public boolean removeChildFragment() {
        ListContainerManager listContainerManager = this.mListContainerManager;
        if (listContainerManager == null || !listContainerManager.removeChildFragment()) {
            return false;
        }
        return true;
    }

    public void removeSiblingFragments(String[] strArr) {
        ListContainerManager listContainerManager = this.mListContainerManager;
        if (listContainerManager != null) {
            listContainerManager.removeSiblingFragments(strArr);
        }
    }

    public void saveCurrentState(String str) {
        ThreadUtil.postOnBgThread(new a(str, 3));
    }

    public void selectBottomNavigationMenu(String str) {
        selectView(str, false);
    }

    public void selectView(String str, boolean z) {
        FragmentActivity activity = getActivity();
        if (activity == null || !activity.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
            Log.w(this.TAG, "not created");
            return;
        }
        if (((ListContainerPresenter) this.mPresenter).setInputBlock(this.TAG + "_selectView") || z) {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "selectView {" + str + "}");
            if (this.mListContainerManager != null) {
                setLocationKey(str);
                this.mListContainerManager.switchFragment(str);
                if (!LocationKey.isRootOfContainerExceptTab(str)) {
                    saveCurrentState(str);
                }
            }
        } else if (!TextUtils.isEmpty(getLocationKey())) {
            this.mCurrentController.changeFocus(getLocationKey(), false);
        }
    }

    public void setBottomBarMargin(int i2) {
        Optional.ofNullable(getActivity()).ifPresent(new C0489a(i2, 0));
    }

    public void setBottomMoveBarMargin(int i2, int i7) {
        Optional.ofNullable(getActivity()).ifPresent(new f(i2, i7, 3));
    }

    public void setCurrentTransitioningAnim(int i2) {
        Optional.ofNullable(getCurrentFragment()).ifPresent(new C0369d(i2, 29));
    }

    public void setDefaultExitTransitioning(boolean z) {
        Optional.ofNullable(getCurrentFragment()).ifPresent(new b(z, 1));
    }

    public void setToolbarStartMargin(int i2) {
        ViewMarginUtils.setStartMargin(getAppbarLayout(), i2);
        if (getToolbar() != null) {
            ViewMarginUtils.setStartMargin((View) getToolbar().getParent(), i2);
        }
    }

    public void settingsBadgeUpdated(boolean z) {
        if (this.mIsSettingsBadgeShowing != z) {
            this.mIsSettingsBadgeShowing = z;
            this.mCurrentController.updateBadgeOnHamburger();
            this.mDrawerTabController.updateSettingBadge(z);
        }
    }

    /* JADX WARNING: type inference failed for: r3v7, types: [com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean supportExitPredictiveBackInCurrent() {
        /*
            r3 = this;
            com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment r0 = r3.getCurrentFragment()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment r0 = r3.getCurrentFragment()
            com.samsung.android.gallery.app.ui.IBaseFragment r0 = r0.getFocusedChild()
            com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView r0 = (com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView) r0
            if (r0 != 0) goto L_0x001b
            com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment r3 = r3.getCurrentFragment()
            r0 = r3
            com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView r0 = (com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView) r0
        L_0x001b:
            boolean r3 = r0.isDrawerMode()
            r2 = 1
            if (r3 != 0) goto L_0x002e
            java.lang.String r3 = r0.getLocationKey()
            boolean r3 = com.samsung.android.gallery.support.blackboard.key.LocationKey.isRootOfContainerExceptTab(r3)
            if (r3 == 0) goto L_0x002e
            r3 = r2
            goto L_0x002f
        L_0x002e:
            r3 = r1
        L_0x002f:
            boolean r0 = r0.supportExitPredictiveBack()
            if (r0 == 0) goto L_0x0038
            if (r3 != 0) goto L_0x0038
            return r2
        L_0x0038:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.container.tablet.ListContainerFragment.supportExitPredictiveBackInCurrent():boolean");
    }

    public boolean supportLayoutCache() {
        return true;
    }

    public void updateBadgeOnTab(int i2, boolean z) {
        handleStoryBadge(i2, z);
        if (!PreferenceFeatures.OneUi8x.REMOVE_SHARED_DRAWER_TAB_MENU) {
            handleSharingBadge(i2, z);
        }
        this.mCurrentController.updateBadgeOnTab(i2, z);
    }

    public void updateBottomNavigationMenu() {
        if (isDestroyed()) {
            Log.w(this.TAG, "addBottomNavigationMenu : ListContainerFragment was destroyed");
        } else {
            this.mCurrentController.updateBottomNavigationMenu();
        }
    }

    public void updateCurrentLocationKey(String str) {
        if (!str.equals((String) this.mBlackboard.read("location://variable/currentv1"))) {
            this.mBlackboard.publish("location://variable/currentv1", str);
        }
    }

    public void updateDrawerSpace(int i2) {
        ViewGroup fragmentSlidingPanel = getFragmentSlidingPanel();
        if (fragmentSlidingPanel != null) {
            ViewMarginUtils.setStartPadding(fragmentSlidingPanel, i2);
            FrameLayout frameLayout = this.mFragmentContainer;
            if (fragmentSlidingPanel != frameLayout) {
                ViewMarginUtils.setStartPadding(frameLayout, 0);
            }
        }
    }

    public void updateDrawerSpaceRelative(int i2, int i7, int i8, int i10) {
        ViewGroup fragmentSlidingPanel = getFragmentSlidingPanel();
        if (fragmentSlidingPanel != null) {
            ViewMarginUtils.setPaddingRelative(fragmentSlidingPanel, i2, i7, i8, i10);
            FrameLayout frameLayout = this.mFragmentContainer;
            if (fragmentSlidingPanel != frameLayout) {
                ViewMarginUtils.setPaddingRelative(frameLayout, 0, 0, 0, 0);
            }
        }
    }

    public void updatePrimaryNavigationFragment() {
        boolean z;
        boolean z3;
        if (PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            boolean booleanValue = ((Boolean) this.mBlackboard.read("function://activity_back_pressed_callback_enabled", Boolean.FALSE)).booleanValue();
            boolean z7 = false;
            if (!BackPressUtils.supportPredictiveBack(getActivity()) || !hasChildFragments()) {
                z = false;
            } else {
                z = true;
            }
            if (getParentFragmentManager().getPrimaryNavigationFragment() == this) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z && !booleanValue && isResumed()) {
                z7 = true;
            }
            if (z3 != z7) {
                Log.b(this.TAG, "fragment updatePrimaryNavigationFragment", Boolean.valueOf(z7));
                FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
                if (!z7) {
                    this = null;
                }
                beginTransaction.setPrimaryNavigationFragment(this);
                beginTransaction.commit();
            }
        }
    }

    public void updateToolbar() {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "updateToolbar toolbar=" + getToolbar());
    }

    public void updateToolbarNavigation(View.OnClickListener onClickListener, int i2) {
        Optional.ofNullable(getToolbar()).ifPresent(new J((Object) onClickListener, i2, 13));
    }

    public void updateToolbarNavigationRipple(boolean z) {
        Optional.ofNullable(getToolbar()).ifPresent(new E7.c(this, z, 16));
    }

    public ListContainerPresenter<IListContainerView> createTabPresenter(IListContainerView iListContainerView) {
        return new ListContainerPresenter<>(this.mBlackboard, iListContainerView);
    }

    public void updateToolbarNavigation(int i2, View.OnClickListener onClickListener, int i7) {
        Optional.ofNullable(getToolbar()).ifPresent(new K7.a(i2, onClickListener, i7));
    }

    public void postAnalyticsLog() {
    }

    public void setArgumentOnSwitchFragment(Fragment fragment, String str) {
    }
}
