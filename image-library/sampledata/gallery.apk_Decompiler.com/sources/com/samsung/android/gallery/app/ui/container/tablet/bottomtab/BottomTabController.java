package com.samsung.android.gallery.app.ui.container.tablet.bottomtab;

import a6.C0419b;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import com.google.android.material.tabs.c;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.container.abstraction.IListContainerView;
import com.samsung.android.gallery.app.ui.container.abstraction.ITabConsumer;
import com.samsung.android.gallery.app.ui.container.abstraction.ITabController;
import com.samsung.android.gallery.app.ui.container.menu.BottomMenuDelegate;
import com.samsung.android.gallery.app.ui.container.menu.BottomTabMenu;
import com.samsung.android.gallery.app.ui.container.menu.BottomTabSelectListener;
import com.samsung.android.gallery.app.ui.container.menu.BottomTabTouchDelegate;
import com.samsung.android.gallery.app.ui.container.menu.TabMenuHelper;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.badge.BadgeHelper;
import com.samsung.android.gallery.module.mtp.MtpClient;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.bottom.BottomTabLayout;
import com.samsung.android.gallery.widget.listview.GalleryGridLayoutManager;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Optional;
import k6.b;
import n4.C0491c;
import o4.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomTabController implements ITabController, ITabConsumer {
    private BottomMenuDelegate mBottomMenuDelegate;
    private BottomTabMenu mBottomTabMenu;
    private BottomTabTouchDelegate mBottomTabTouchDelegate;
    private final BottomTabView mBottomTabView;
    private boolean mDensityDirty = false;
    private String mFocusKey;
    private boolean mIsPendingShowBottomTab;
    private final IListContainerView mListContainerView;
    private BottomTabSelectListener mTabSelectListener;

    public BottomTabController(IListContainerView iListContainerView) {
        this.mListContainerView = iListContainerView;
        this.mBottomTabView = new BottomTabView();
    }

    /* access modifiers changed from: private */
    public void blockFocus(Object obj, Bundle bundle) {
        if (this.mBottomTabView.isViewActive()) {
            this.mBottomTabView.blockFocus(((Boolean) obj).booleanValue());
        }
    }

    private BottomMenuDelegate getBottomMenu() {
        if (this.mBottomMenuDelegate == null) {
            this.mBottomMenuDelegate = new BottomMenuDelegate(getBlackboard());
        }
        return this.mBottomMenuDelegate;
    }

    private BottomTabTouchDelegate getBottomTabTouchDelegate() {
        if (this.mBottomTabTouchDelegate == null) {
            this.mBottomTabTouchDelegate = new BottomTabTouchDelegate();
        }
        return this.mBottomTabTouchDelegate;
    }

    private String getLocationKey() {
        return this.mListContainerView.getLocationKey();
    }

    private BottomTabSelectListener getTabSelectListener() {
        if (this.mTabSelectListener == null) {
            this.mTabSelectListener = new BottomTabSelectListener(this);
        }
        return this.mTabSelectListener;
    }

    private void initializeBottomTabView() {
        this.mBottomTabView.inflateView();
        onBindBottomTabView();
    }

    private void invalidate() {
        this.mBottomTabView.invalidate();
    }

    private boolean isAlbumMoveMode() {
        if (this.mListContainerView.getBlackboard().read("data://album_move") != null) {
            return true;
        }
        return false;
    }

    private boolean isAlbumsFirstSelect(c cVar) {
        if (cVar.d != 1 || !this.mListContainerView.isAlbumFirstSelect()) {
            return false;
        }
        return true;
    }

    private boolean isTimelineFirstSelect(c cVar) {
        if (cVar.d != 0 || !this.mListContainerView.isTimelineFirstSelect()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindBottomTabView$1(BottomTabLayout bottomTabLayout) {
        getBottomMenu().invalidate(bottomTabLayout, getLocationKey());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onEnterSelectionMode$0() {
        showBottomTab(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishDataRequest$2(c cVar, Blackboard blackboard) {
        if (isTimelineFirstSelect(cVar) && blackboard.isEmpty(DataKey.DATA_CURSOR("location://timeline"))) {
            BlackboardUtils.publishDataRequest(blackboard, "location://timeline/fake");
        } else if (isAlbumsFirstSelect(cVar)) {
            BlackboardUtils.publishDataRequest(blackboard, LocationKey.getCacheLocationKeyForAlbum());
        }
    }

    /* access modifiers changed from: private */
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        ViewMarginUtils.setBottomMargin(view, WindowUtils.getSystemInsetsBottom(rootWindowInsets));
        ViewMarginUtils.setStartMargin(view, WindowUtils.getSystemInsetsStart(rootWindowInsets));
        ViewMarginUtils.setEndMargin(view, WindowUtils.getSystemInsetsEnd(rootWindowInsets));
        return windowInsets;
    }

    private void onBindBottomTabView() {
        getBottomMenu().load();
        Optional.ofNullable(this.mBottomTabView.getBottomTabLayout()).ifPresent(new a(0, this));
        this.mBottomTabView.setOnApplyWindowInsetsListener(new Ca.c(4, this));
        setBottomTabSelected(getLocationKey());
        removeTabSelectListener();
        removeMenuTabTouchListener();
        setTabSelectListener();
        setMenuTabTouchListener();
        invalidate();
    }

    /* access modifiers changed from: private */
    public void onBottomTabMenuItemSelected(String str) {
        this.mListContainerView.onMenuItemSelected(str);
    }

    /* access modifiers changed from: private */
    public void onEnterMoveMode(Object obj, Bundle bundle) {
        showBottomTab(false);
    }

    /* access modifiers changed from: private */
    public void onEnterSelectionMode(Object obj, Bundle bundle) {
        this.mListContainerView.getBlackboard().publish("command:///HideBottomTabCallback", new b(22, this));
    }

    /* access modifiers changed from: private */
    public void onExitMoveMode(Object obj, Bundle bundle) {
        if (isAlbumMoveMode()) {
            showBottomTab(true, true);
        }
    }

    /* access modifiers changed from: private */
    public void onExitSelectionMode(Object obj, Bundle bundle) {
        if (!isAlbumMoveMode()) {
            showBottomTab(true);
        }
    }

    /* access modifiers changed from: private */
    public void onMenuTabTouched() {
        showMenuList();
        scrollToMenuTab();
    }

    /* access modifiers changed from: private */
    public void onNavigationIconClicked(View view) {
        this.mListContainerView.postAnalyticsLog(AnalyticsEventId.EVENT_UP_KEY);
        BlackboardUtils.publishBackKeyEvent(this.mListContainerView.getBlackboard());
        Log.majorEvent("onNavigationPressed : " + Logger.getEncodedString(ThreadUtil.getCallStack()));
    }

    private void publishDataRequest(c cVar) {
        Optional.ofNullable(getBlackboard()).ifPresent(new C0491c(7, this, cVar));
    }

    private void removeMenuTabTouchListener() {
        getBottomTabTouchDelegate().removeMenuTabTouchListener();
    }

    private void removeTabSelectListener() {
        this.mBottomTabView.removeTabSelectListener(getTabSelectListener());
    }

    private void scrollToMenuTab() {
        this.mBottomTabView.scrollToMenuTab();
    }

    private void setBottomTabSelected(String str) {
        setBottomTabSelected(str, false);
    }

    private void setMenuTabTouchListener() {
        BottomTabLayout bottomTabLayout;
        if (TabMenuHelper.hasMoreTab(getBlackboard()) && (bottomTabLayout = this.mBottomTabView.getBottomTabLayout()) != null) {
            getBottomTabTouchDelegate().addOnTabTouchListener(bottomTabLayout.getMenuTabView(), new l4.a(this, 1));
        }
    }

    private void setTabSelectListener() {
        this.mBottomTabView.setTabSelectListener(getTabSelectListener());
    }

    private void setTabSelected(int i2, boolean z) {
        this.mBottomTabView.setTabSelected(i2, z);
    }

    private void showBottomTab(boolean z) {
        showBottomTab(z, false);
    }

    private void updateTabContainerLayout() {
        this.mBottomTabView.updateTabContainerLayout();
    }

    private boolean useGridLayoutManager(IBaseFragment iBaseFragment) {
        if (!(iBaseFragment instanceof IBaseListView)) {
            return false;
        }
        IBaseListView iBaseListView = (IBaseListView) iBaseFragment;
        if (!iBaseListView.hasLayoutManager() || !(iBaseListView.getLayoutManager() instanceof GalleryGridLayoutManager)) {
            return false;
        }
        return true;
    }

    public void bindView(View view) {
        this.mBottomTabView.bindView(view);
    }

    public void cancel(boolean z) {
        setBottomTabSelected(getLocationKey(), z);
    }

    public void changeFocus(String str, boolean z) {
        this.mFocusKey = str;
        setBottomTabSelected(str);
    }

    public boolean checkTabSelectable() {
        return this.mListContainerView.checkTabSelectable();
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("data://bottomtab/focus", new o4.b(this, 2)).setWorkingCurrent());
        arrayList.add(new SubscriberInfo("command://EnterMoveMode", new o4.b(this, 3)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://ExitMoveMode", new o4.b(this, 4)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://ON_EnterSelectionMode", new o4.b(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://ON_ExitSelectionMode", new o4.b(this, 1)).setWorkingOnUI());
    }

    public Blackboard getBlackboard() {
        return this.mListContainerView.getBlackboard();
    }

    public String getFocusKey() {
        return this.mFocusKey;
    }

    public void handleDensityChange() {
        if (this.mBottomTabView.isViewActive()) {
            initializeBottomTabView();
            updateTabContainerLayout();
            updateBadgeOnHamburger();
            this.mBottomTabView.setVisibility(0);
            return;
        }
        this.mDensityDirty = true;
    }

    public void handleResolutionChange() {
        BottomTabMenu bottomTabMenu = this.mBottomTabMenu;
        if (bottomTabMenu != null) {
            bottomTabMenu.updateLayout();
        }
    }

    public void onChildViewCreated(IBaseFragment iBaseFragment) {
        if (useGridLayoutManager(iBaseFragment)) {
            ((GalleryGridLayoutManager) ((IBaseListView) iBaseFragment).getLayoutManager()).updateExtraStartPadding(0, 0, 0.0f, false, false);
        }
        this.mListContainerView.updateDrawerSpace(0);
        if (this.mBottomTabView.isViewActive()) {
            this.mBottomTabView.requestApplyInsets();
        }
        if (this.mDensityDirty) {
            this.mDensityDirty = false;
            handleDensityChange();
        }
    }

    public void select(c cVar) {
        publishDataRequest(cVar);
        this.mListContainerView.selectView(TabMenuHelper.getLocationKeyById(cVar.f1501a.intValue()), false);
    }

    public void setVisibility(boolean z, boolean z3, boolean z7) {
        int i2;
        BottomTabMenu bottomTabMenu;
        if ((z || z7) && this.mBottomTabView.needInflated()) {
            initializeBottomTabView();
        } else if (!z && (bottomTabMenu = this.mBottomTabMenu) != null) {
            bottomTabMenu.dismissDialog();
        }
        BottomTabView bottomTabView = this.mBottomTabView;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        bottomTabView.setVisibility(i2);
        if (z && ((this.mListContainerView.isMoveMode() || this.mListContainerView.isSelectionMode()) && ViewUtils.isVisible(this.mBottomTabView.getBottomTabLayout()))) {
            showBottomTab(false);
        }
        if (z && this.mIsPendingShowBottomTab) {
            showBottomTab(true);
            this.mIsPendingShowBottomTab = false;
        }
    }

    public void showMenuList() {
        if (this.mListContainerView.setInputBlock("BottomTabControllershowMenuList", 500)) {
            Optional.ofNullable(this.mListContainerView.getCurrentFragment()).ifPresent(new m7.c(18));
            if (this.mBottomTabMenu == null) {
                BottomTabMenu bottomTabMenu = new BottomTabMenu(this.mListContainerView.getEventContext());
                this.mBottomTabMenu = bottomTabMenu;
                bottomTabMenu.setBottomTabMenuItemClickListener(new com.samsung.android.sdk.scs.ai.language.a(28, this));
            }
            this.mBottomTabMenu.showBottomTabDialog();
            BadgeHelper.updateMenuTabBadgeIfNecessary();
            Log.majorEvent("showMenuList");
        }
    }

    public void unbindView() {
        removeTabSelectListener();
        removeMenuTabTouchListener();
        getBottomMenu().destroy();
        this.mBottomTabView.unbindView();
    }

    public void updateBadgeOnHamburger() {
        if (PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU) {
            updateBadgeOnTab(R.id.action_menu_list, this.mListContainerView.needBadgeOnBottomMenuTab());
        }
    }

    public void updateBadgeOnTab(int i2, boolean z) {
        this.mBottomTabView.updateBadgeOnTab(i2, z);
    }

    public void updateBottomNavigationMenu() {
        boolean z;
        if (!PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU && this.mBottomTabView.getBottomTabLayout() != null) {
            if (!PickerUtil.isNormalLaunchMode(this.mListContainerView.getBlackboard()) || !MtpClient.getInstance(this.mListContainerView.getApplicationContext()).isAvailable()) {
                z = false;
            } else {
                z = true;
            }
            if (getBottomMenu().updateTabVisibility("location://mtp", z)) {
                getBottomMenu().invalidate(this.mBottomTabView.getBottomTabLayout(), getLocationKey());
            }
        }
    }

    public void updateFloatingView(View view) {
        BottomTabLayout bottomTabLayout = this.mBottomTabView.getBottomTabLayout();
        if (bottomTabLayout != null) {
            bottomTabLayout.updateFloatingView(view);
        }
    }

    public void updateNavigationIcon() {
        if (this.mBottomTabView.isViewActive() || this.mListContainerView.isSelectionMode()) {
            this.mListContainerView.updateToolbarNavigation((View.OnClickListener) null, 0);
        } else {
            this.mListContainerView.updateToolbarNavigation(R.drawable.tw_ic_ab_back_mtrl_with_inset, new C0419b(28, this), R.string.navigate_up);
        }
    }

    private void setBottomTabSelected(String str, boolean z) {
        int id = getBottomMenu().getId(str);
        if (id != -1) {
            setTabSelected(id, z);
        }
    }

    private void showBottomTab(boolean z, boolean z3) {
        this.mListContainerView.getBlackboard().erase("command:///HideBottomTabCallback");
        if (!this.mListContainerView.isViewActive() || !this.mListContainerView.supportTabLayout()) {
            if (z3) {
                this.mIsPendingShowBottomTab = true;
            }
        } else if (z) {
            this.mBottomTabView.showTabLayout();
        } else {
            this.mBottomTabView.hideTabLayout();
        }
    }
}
