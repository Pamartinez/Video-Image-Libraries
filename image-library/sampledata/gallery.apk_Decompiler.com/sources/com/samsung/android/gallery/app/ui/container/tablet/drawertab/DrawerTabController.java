package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

import Ca.c;
import H7.x;
import Lb.d;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.controller.internals.StartSettingsCmd;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.container.abstraction.IListContainerView;
import com.samsung.android.gallery.app.ui.container.abstraction.ITabController;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.abstraction.GridValue;
import com.samsung.android.gallery.module.badge.BadgeHelper;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.module.mtp.MtpClient;
import com.samsung.android.gallery.module.mtp.UsbStorageState;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.settings.helper.PopoverUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.behavior.DrawerTabBehavior;
import com.samsung.android.gallery.widget.behavior.SheetBehaviorCompat;
import com.samsung.android.gallery.widget.bottom.BottomBar;
import com.samsung.android.gallery.widget.bottom.BottomMoveBar;
import com.samsung.android.gallery.widget.listview.GalleryGridLayoutManager;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import fa.C0691a;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BooleanSupplier;
import n4.C0489a;
import n5.e;
import o4.a;
import o6.B;
import o6.p;
import o6.t;
import p4.C0500b;
import p4.C0501c;
import p4.C0502d;
import p4.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DrawerTabController implements ITabController, DrawerTabBehavior.DrawerSlideCallback {
    private DrawerSlideAnimationManager mAnimationManager;
    private DrawerFakeViewManager mDrawerFakeViewManager;
    private int mDrawerState = 4;
    private final DrawerTabModel mDrawerTabModel;
    private final DrawerTabView mDrawerTabView;
    private DrawerTabViewAdapter mDrawerTabViewAdapter;
    private String mFocusKey;
    private boolean mIsAlbumDataFirstSwapped = true;
    private boolean mIsBadgeShowing;
    private boolean mIsFirstEntry = true;
    private final AtomicBoolean mIsFirstLaunch = new AtomicBoolean(true);
    private boolean mIsLastStateExpanded;
    private final IListContainerView mListContainerView;

    public DrawerTabController(IListContainerView iListContainerView) {
        this.mListContainerView = iListContainerView;
        this.mDrawerTabView = new DrawerTabView(this);
        this.mDrawerTabModel = new DrawerTabModel();
    }

    private void adjustBottomBarMargin(float f) {
        int tabWidth = getTabWidth(f);
        int startMargin = ViewMarginUtils.getStartMargin(this.mListContainerView.getView());
        int endMargin = ViewMarginUtils.getEndMargin(this.mListContainerView.getView());
        int i2 = startMargin + tabWidth;
        this.mListContainerView.setBottomBarMargin(i2);
        this.mListContainerView.setBottomMoveBarMargin(i2, endMargin);
    }

    private void announceDrawerState() {
        int i2;
        Context context = this.mListContainerView.getContext();
        if (this.mIsLastStateExpanded) {
            i2 = R.string.navigation_drawer_expanded;
        } else {
            i2 = R.string.navigation_drawer_collapsed;
        }
        SeApiCompat.announceVoiceAssistant(context, AppResources.getString(i2));
    }

    /* access modifiers changed from: private */
    public void closeDrawer(Object obj, Bundle bundle) {
        if (isExpanded()) {
            onNavigationIconClicked();
        }
    }

    private void completeSlideAnimation() {
        if (this.mDrawerTabModel.supportSlideAnimation()) {
            Optional.ofNullable(this.mAnimationManager).ifPresent(new h(0, this));
            updateItemDecorations();
        }
        Optional.ofNullable(getFakeViewManager()).ifPresent(new C0500b(this, 2));
        if (this.mIsLastStateExpanded) {
            this.mDrawerTabView.setItemAnimator(true);
        } else {
            Optional.ofNullable(this.mDrawerTabViewAdapter).ifPresent(new C0500b(this, 3));
            this.mDrawerTabView.resetScrollPosition();
            this.mDrawerTabView.updateLayoutWithState(false);
        }
        Optional.ofNullable(this.mDrawerTabView.getLayoutManager()).ifPresent(new C0500b(this, 4));
    }

    private DrawerSlideAnimationManager getAnimationManager() {
        PinchLayoutManager pinchLayoutManager;
        DrawerSlideAnimationManager drawerSlideAnimationManager;
        if (this.mAnimationManager == null && (pinchLayoutManager = (PinchLayoutManager) this.mDrawerTabModel.getLayoutManager()) != null) {
            if (pinchLayoutManager.isStories()) {
                drawerSlideAnimationManager = new StoriesDrawerSlideAnimationManager(pinchLayoutManager, getBaseListView().getListView());
            } else {
                drawerSlideAnimationManager = new DrawerSlideAnimationManager(pinchLayoutManager, getBaseListView().getListView());
            }
            this.mAnimationManager = drawerSlideAnimationManager;
        }
        return this.mAnimationManager;
    }

    private IBaseListView getBaseListView() {
        IBaseListView baseListView = this.mDrawerTabModel.getBaseListView();
        if (baseListView != null || !(this.mListContainerView.getCurrentFragment() instanceof IBaseListView)) {
            return baseListView;
        }
        return (IBaseListView) this.mListContainerView.getCurrentFragment();
    }

    private Blackboard getBlackboard() {
        return this.mListContainerView.getBlackboard();
    }

    private DrawerFakeViewManager getFakeViewManager() {
        if (this.mListContainerView.getContext() == null) {
            return null;
        }
        if (this.mDrawerFakeViewManager == null) {
            DrawerFakeViewManager drawerFakeViewManager = new DrawerFakeViewManager(this.mDrawerTabView, this.mListContainerView.getContext());
            this.mDrawerFakeViewManager = drawerFakeViewManager;
            drawerFakeViewManager.createFakeView();
        }
        return this.mDrawerFakeViewManager;
    }

    private int getTabWidth(float f) {
        return (int) ((((float) (this.mDrawerTabView.getWidth() - this.mDrawerTabView.getCollapsedWidth())) * f) + ((float) this.mDrawerTabView.getCollapsedWidth()));
    }

    private int getToolbarMarginStart(float f) {
        if (!this.mDrawerTabModel.supportGridLayoutManager()) {
            return 0;
        }
        return getTabWidth(f);
    }

    private void initialize() {
        initializeAdapter();
        initializeDrawerTabView();
    }

    private void initializeAdapter() {
        DrawerTabViewAdapter drawerTabViewAdapter = new DrawerTabViewAdapter(this.mListContainerView.getContext(), this.mListContainerView.getBlackboard());
        this.mDrawerTabViewAdapter = drawerTabViewAdapter;
        drawerTabViewAdapter.setDrawerItemClickListener(new p(1, this));
    }

    private void initializeDrawerTabView() {
        this.mDrawerTabView.inflateView();
        this.mDrawerTabView.setLayoutManager();
        this.mDrawerTabView.setOnApplyWindowInsetsListener(new c(5, this));
        this.mDrawerTabView.setOnNavigationButtonClickListener(new C0502d(this, 0));
        this.mDrawerTabView.setOnSettingsButtonClickListener(new C0502d(this, 1));
        this.mDrawerTabView.expandNavigationButtonTouchArea();
        this.mDrawerTabView.expandSettingsButtonTouchArea();
        setDefaultDrawerState();
        setAdapterIfNeeded();
    }

    private boolean isAlbumMoveMode() {
        if (this.mListContainerView.getBlackboard().read("data://album_move") != null) {
            return true;
        }
        return false;
    }

    private boolean isExpanded() {
        if (!this.mDrawerTabView.isVisible() || !this.mIsLastStateExpanded) {
            return false;
        }
        return true;
    }

    private boolean isMtpTabAvailable() {
        if (!PickerUtil.isNormalLaunchMode(getBlackboard()) || !MtpClient.getInstance(this.mListContainerView.getApplicationContext()).isAvailable()) {
            return false;
        }
        return true;
    }

    private boolean isSharedAlbumsTabAvailable() {
        if (PreferenceFeatures.OneUi8x.REMOVE_SHARED_DRAWER_TAB_MENU || !MdeSharingService.getInstance().isServiceSupported()) {
            return false;
        }
        return true;
    }

    private boolean isSlideAnimating() {
        DrawerSlideAnimationManager drawerSlideAnimationManager = this.mAnimationManager;
        if (drawerSlideAnimationManager == null || !drawerSlideAnimationManager.isAnimating()) {
            return false;
        }
        return true;
    }

    private boolean isUsbTabAvailable() {
        if (!Features.isEnabled(Features.SUPPORT_USB_STORAGE) || !UsbStorageState.getInstance().isMounted()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$changeFocus$2() {
        Optional.ofNullable(getFakeViewManager()).ifPresent(new B(5));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$completeSlideAnimation$14() {
        this.mAnimationManager = null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$completeSlideAnimation$15(DrawerSlideAnimationManager drawerSlideAnimationManager) {
        drawerSlideAnimationManager.completeAnimation(this.mDrawerTabModel.withWidthAnimation(), this.mIsLastStateExpanded, new C0501c(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$completeSlideAnimation$16(DrawerFakeViewManager drawerFakeViewManager) {
        DrawerTabView drawerTabView = this.mDrawerTabView;
        Objects.requireNonNull(drawerTabView);
        drawerFakeViewManager.onCompleteAnimation(new t(5, drawerTabView));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$completeSlideAnimation$17(DrawerTabViewAdapter drawerTabViewAdapter) {
        drawerTabViewAdapter.updateExpandedAlbumsTab(false, this.mListContainerView.getCurrentAlbumKey());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$completeSlideAnimation$18(DrawerTabLayoutManager drawerTabLayoutManager) {
        drawerTabLayoutManager.setDrawerOpened(this.mIsLastStateExpanded);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onEnterMoveMode$27() {
        return SheetBehaviorCompat.isInTransition(this.mDrawerState);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onHandleEvent$26() {
        return SheetBehaviorCompat.isInTransition(this.mDrawerState);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSlide$21(int i2, float f, GalleryGridLayoutManager galleryGridLayoutManager) {
        GalleryGridLayoutManager galleryGridLayoutManager2 = galleryGridLayoutManager;
        galleryGridLayoutManager2.updateExtraStartPadding(i2, this.mDrawerTabView.getCollapsedWidth(), f, true, false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareSlideAnimation$12(DrawerTabViewAdapter drawerTabViewAdapter) {
        drawerTabViewAdapter.updateExpandedAlbumsTab(true, this.mListContainerView.getCurrentAlbumKey());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareSlideAnimation$13(DrawerFakeViewManager drawerFakeViewManager) {
        boolean z;
        boolean z3 = this.mIsLastStateExpanded;
        if (this.mListContainerView.isSelectionMode() || this.mListContainerView.isMoveMode()) {
            z = true;
        } else {
            z = false;
        }
        drawerFakeViewManager.onPrepareAnimation(z3, z);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateChildStartSpacing$10(IBaseListView iBaseListView, GalleryListView galleryListView) {
        iBaseListView.setDrawerStateChanged(true);
        iBaseListView.updateListColumn();
        galleryListView.requestLayout();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCollapsedAlbumsTabFocus$9(DrawerTabViewAdapter drawerTabViewAdapter) {
        boolean z;
        if (this.mIsLastStateExpanded || SheetBehaviorCompat.isInTransition(this.mDrawerState)) {
            z = false;
        } else {
            z = true;
        }
        drawerTabViewAdapter.updateCollapsedAlbumsTabFocus(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateDrawerWidth$1(DrawerTabLayoutManager drawerTabLayoutManager) {
        drawerTabLayoutManager.setDrawerOpened(this.mIsLastStateExpanded);
        drawerTabLayoutManager.setState(this.mDrawerState);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateExpandedAlbumsTab$8(DrawerTabViewAdapter drawerTabViewAdapter) {
        drawerTabViewAdapter.updateExpandedAlbumsTab(this.mIsLastStateExpanded, this.mListContainerView.getCurrentAlbumKey());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateFakeView$0(DrawerFakeViewManager drawerFakeViewManager) {
        drawerFakeViewManager.createFakeView();
        drawerFakeViewManager.updateDeco();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateItemDecorations$19(GalleryListView galleryListView) {
        galleryListView.setGoToTopVisibility(true);
        galleryListView.updateGoToTopPosition();
        galleryListView.updateIndexTipPosition();
    }

    private boolean needAlbumsCollapsed(String str) {
        if (!this.mIsFirstLaunch.getAndSet(false) || !LocationKey.isAlbums(str)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void onAlbumDataSwapped(Object obj, Bundle bundle) {
        if (this.mDrawerTabViewAdapter != null) {
            this.mDrawerTabViewAdapter.dataChangedOnUi(this.mIsAlbumDataFirstSwapped, ((Boolean) obj).booleanValue());
            this.mIsAlbumDataFirstSwapped = false;
        }
    }

    /* access modifiers changed from: private */
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        WindowInsets windowInsets2;
        if (view != null) {
            int i2 = 0;
            int intValue = ((Integer) Optional.ofNullable(this.mListContainerView.getToolbar()).map(new e(14)).orElse(0)).intValue();
            if (windowInsets != null) {
                windowInsets2 = windowInsets;
            } else {
                windowInsets2 = view.getRootWindowInsets();
            }
            int systemInsetsTop = WindowUtils.getSystemInsetsTop(windowInsets2);
            if (intValue > 0) {
                i2 = this.mListContainerView.getResources().getDimensionPixelOffset(R.dimen.drawer_padding_top);
            }
            int systemInsetsBottom = WindowUtils.getSystemInsetsBottom(view.getRootWindowInsets());
            view.setPadding(view.getPaddingLeft(), i2 + systemInsetsTop, view.getPaddingRight(), systemInsetsBottom);
        }
        return windowInsets;
    }

    /* access modifiers changed from: private */
    public void onDrawerItemSelected(String str) {
        if (isSlideAnimating()) {
            Log.w("DrawerTabController", "onDrawerItemSelected skip by slide animation");
        } else if (this.mDrawerTabModel.isListViewTouchOngoing()) {
            Log.w("DrawerTabController", "onDrawerItemSelected skip by listview touch ongoing");
        } else if (this.mDrawerTabModel.isListViewAnimating()) {
            Log.w("DrawerTabController", "onDrawerItemSelected skip by listview animating");
        } else {
            this.mListContainerView.onMenuItemSelected(str);
        }
    }

    /* access modifiers changed from: private */
    public void onEnterMoveMode(Object obj, Bundle bundle) {
        DrawerTabViewAdapter drawerTabViewAdapter = this.mDrawerTabViewAdapter;
        if (drawerTabViewAdapter != null) {
            drawerTabViewAdapter.getTabItemEnableCondition().onEnterMoveMode();
        }
        setMoveBarTouchBlocker(new p4.e(this, 1));
    }

    /* access modifiers changed from: private */
    public void onEnterSelectionMode(Object obj, Bundle bundle) {
        if (this.mDrawerTabViewAdapter != null && this.mListContainerView.isViewResumed()) {
            this.mDrawerTabViewAdapter.getTabItemEnableCondition().onEnterSelectionMode();
        }
    }

    /* access modifiers changed from: private */
    public void onExitMoveMode(Object obj, Bundle bundle) {
        DrawerTabViewAdapter drawerTabViewAdapter = this.mDrawerTabViewAdapter;
        if (drawerTabViewAdapter != null) {
            drawerTabViewAdapter.getTabItemEnableCondition().onExitMoveMode();
        }
        setMoveBarTouchBlocker((BooleanSupplier) null);
    }

    /* access modifiers changed from: private */
    public void onExitSelectionMode(Object obj, Bundle bundle) {
        if (this.mDrawerTabViewAdapter != null && !isAlbumMoveMode()) {
            this.mDrawerTabViewAdapter.getTabItemEnableCondition().onExitSelectionMode();
        }
    }

    /* access modifiers changed from: private */
    public void onNavigationIconClicked(View view) {
        onNavigationIconClicked();
    }

    /* access modifiers changed from: private */
    public void onStartSettings(View view) {
        new StartSettingsCmd().execute(this.mListContainerView.getEventContext(), PopoverUtils.Anchor.TOP_START);
    }

    /* access modifiers changed from: private */
    public void onThumbnailLoadDone(Object obj, Bundle bundle) {
        ThreadUtil.postOnUiThreadDelayed(new C0501c(this, 2), 500);
    }

    /* access modifiers changed from: private */
    public void openDrawer(Object obj, Bundle bundle) {
        if (!isExpanded()) {
            onNavigationIconClicked();
        }
    }

    private void prepareSlideAnimation() {
        DrawerSlideAnimationManager animationManager;
        if (this.mDrawerTabModel.supportSlideAnimation() && (animationManager = getAnimationManager()) != null && !animationManager.isAnimating()) {
            IBaseListView baseListView = getBaseListView();
            int depth = baseListView.getListView().getDepth();
            int i2 = baseListView.getPinchColumn(this.mIsLastStateExpanded)[depth];
            int convert = GridValue.convert(depth, baseListView.getPinchColumn(!this.mIsLastStateExpanded)[depth], !this.mIsLastStateExpanded, true);
            int i7 = baseListView.getPinchColumn(!this.mIsLastStateExpanded)[0];
            animationManager.setOpening(!this.mIsLastStateExpanded);
            if (this.mDrawerTabModel.withWidthAnimation()) {
                animationManager.onPrepareWidthAnimation(i2, convert);
            } else {
                animationManager.onPrepareAnimation(i2, convert, i7);
            }
            baseListView.getListView().setGoToTopVisibility(false);
        }
        this.mDrawerTabView.prepareAnimation();
        this.mDrawerTabView.setItemAnimator(false);
        if (!this.mIsLastStateExpanded) {
            this.mDrawerTabView.resetScrollPosition();
            Optional.ofNullable(this.mDrawerTabViewAdapter).ifPresent(new C0500b(this, 6));
            if (!SheetBehaviorCompat.isInTransition(this.mDrawerState)) {
                this.mDrawerTabView.updateLayoutWithState(true);
            }
        }
        Optional.ofNullable(getFakeViewManager()).ifPresent(new C0500b(this, 7));
    }

    private void refreshLayout() {
        if (SheetBehaviorCompat.isInTransition(this.mDrawerState)) {
            Blackboard.getApplicationInstance().post("global://event/activityRecreate", (Object) null);
            return;
        }
        resetDrawerTabView();
        updateDrawerWidth();
        updateToolbarMargin();
        updateChildStartSpacing(isExpanded());
        updateNavigationButton();
        updateBgColor();
        updateFakeView();
        updateExpandedAlbumsTab();
        updateCollapsedAlbumsTabFocus();
    }

    private void resetBadge() {
        updateBadgeOnHamburger();
        updateSettingBadge(BadgeHelper.hasNewSettings());
    }

    private void resetDrawerTabView() {
        initializeDrawerTabView();
        setAdapterIfNeeded();
        resetBadge();
    }

    /* access modifiers changed from: private */
    public void setAdapterIfNeeded() {
        if (this.mDrawerTabView.getAdapter() == null) {
            Log.d("DrawerTabController", "DrawerTabViewAdapter set.");
            this.mDrawerTabView.setAdapter(this.mDrawerTabViewAdapter);
            getBlackboard().publish("data://settings_badge_updated", Boolean.valueOf(BadgeHelper.hasNewSettings()));
        }
    }

    private void setBottomBarTouchBlocker(BooleanSupplier booleanSupplier) {
        if (this.mListContainerView.getCurrentFragment() instanceof IBaseListView) {
            View bottomBar = ((IBaseListView) this.mListContainerView.getCurrentFragment()).getBottomBar();
            if (bottomBar instanceof BottomBar) {
                ((BottomBar) bottomBar).setTouchBlocker(booleanSupplier);
            }
        }
    }

    private void setDefaultDrawerState() {
        int i2;
        DrawerTabView drawerTabView = this.mDrawerTabView;
        boolean booleanValue = ((Boolean) getBlackboard().read("data://drawer_opened", Boolean.valueOf(DrawerUtil.isDrawerDefaultOpen(this.mListContainerView.getContext())))).booleanValue();
        this.mIsLastStateExpanded = booleanValue;
        drawerTabView.updateBehaviorState(booleanValue);
        if (this.mIsLastStateExpanded) {
            i2 = 3;
        } else {
            i2 = 4;
        }
        this.mDrawerState = i2;
    }

    private void setMoveBarTouchBlocker(BooleanSupplier booleanSupplier) {
        if (this.mListContainerView.getCurrentFragment() instanceof IBaseListView) {
            View bottomMoveBar = ((IBaseListView) this.mListContainerView.getCurrentFragment()).getBottomMoveBar();
            if (bottomMoveBar instanceof BottomMoveBar) {
                ((BottomMoveBar) bottomMoveBar).setTouchBlocker(booleanSupplier);
            }
        }
    }

    private void stopListScroll() {
        Optional.ofNullable(getBaseListView()).ifPresent(new B(7));
    }

    private void updateBgColor() {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.StoriesTopColorEffect) && (LocationKey.isStories(this.mFocusKey) || LocationKey.isCollection(this.mFocusKey))) {
            this.mDrawerTabView.updateBackground(true);
        }
        updateRootBgColor(LocationKey.isTimeline(this.mFocusKey));
    }

    private void updateChildStartSpacing(boolean z) {
        int i2;
        float f;
        IBaseListView baseListView = getBaseListView();
        if (baseListView != null) {
            DrawerTabView drawerTabView = this.mDrawerTabView;
            if (z) {
                i2 = drawerTabView.getWidth();
            } else {
                i2 = drawerTabView.getCollapsedWidth();
            }
            baseListView.updateDrawerStateToChildFragment(z);
            float f5 = 0.0f;
            if (this.mDrawerTabModel.supportGridLayoutManager()) {
                this.mListContainerView.updateDrawerSpace(0);
                GalleryGridLayoutManager layoutManager = this.mDrawerTabModel.getLayoutManager();
                if (layoutManager != null) {
                    int width = this.mDrawerTabView.getWidth();
                    int collapsedWidth = this.mDrawerTabView.getCollapsedWidth();
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    if (layoutManager.updateExtraStartPadding(width, collapsedWidth, f, z, true)) {
                        Optional.ofNullable(baseListView.getListView()).ifPresent(new a(5, baseListView));
                    }
                }
            } else {
                boolean z3 = z;
                this.mListContainerView.updateDrawerSpace(i2);
                if (z3) {
                    f5 = 1.0f;
                }
                baseListView.updateExtraStartPadding(f5);
            }
            Optional.ofNullable(baseListView.getPresenter()).ifPresent(new C0489a(i2, 6));
        }
    }

    private void updateDrawerWidth() {
        float f;
        this.mDrawerTabView.updateWidth(this.mListContainerView.getResources().getDimensionPixelOffset(R.dimen.drawer_tab_layout_width));
        DrawerTabView drawerTabView = this.mDrawerTabView;
        if (this.mIsLastStateExpanded) {
            f = 1.0f;
        } else {
            f = 0.0f;
        }
        drawerTabView.updateLayoutWithOffset(f);
        Optional.ofNullable(this.mDrawerTabView.getLayoutManager()).ifPresent(new C0500b(this, 0));
        this.mDrawerTabView.updateSettingsButtonVisibility(this.mIsLastStateExpanded);
        this.mDrawerTabView.updateDrawerTabContainerWidth(this.mIsLastStateExpanded);
    }

    private void updateFakeView() {
        Optional.ofNullable(getFakeViewManager()).ifPresent(new B(6));
    }

    private void updateItemDecorations() {
        if (getBaseListView() != null) {
            Optional.ofNullable(getBaseListView().getListView()).ifPresent(new B(8));
        }
    }

    private void updateNavigationButton() {
        this.mListContainerView.updateToolbarNavigation((View.OnClickListener) null, 0);
        updateToolbarMargin();
        boolean needBadgeOnNavigationDrawerButton = this.mListContainerView.needBadgeOnNavigationDrawerButton();
        this.mIsBadgeShowing = needBadgeOnNavigationDrawerButton;
        updateNavigationBadge(needBadgeOnNavigationDrawerButton);
        this.mDrawerTabView.updateNavigationButtonContentDescription(this.mIsLastStateExpanded);
    }

    private void updateRootBgColor(boolean z) {
        if (z) {
            IBaseListView baseListView = getBaseListView();
            if (baseListView != null && baseListView.getListView() != null) {
                this.mDrawerTabView.updateRootBgColor(baseListView.getListView().getDefaultBGColor());
                return;
            }
            return;
        }
        this.mDrawerTabView.updateRootBgColor(0);
    }

    private void updateToolbarMargin() {
        float f;
        IListContainerView iListContainerView = this.mListContainerView;
        if (isExpanded()) {
            f = 1.0f;
        } else {
            f = 0.0f;
        }
        iListContainerView.setToolbarStartMargin(getToolbarMarginStart(f));
    }

    public void bindView(View view) {
        this.mDrawerTabView.bindView(view);
    }

    public boolean canDrag() {
        if (!this.mDrawerTabModel.supportSlideAnimation()) {
            return true;
        }
        if (this.mDrawerTabModel.isListViewAnimating() || isSlideAnimating()) {
            return false;
        }
        return true;
    }

    public void changeFocus(String str, boolean z) {
        if (this.mDrawerTabViewAdapter != null) {
            boolean z3 = true;
            if (PreferenceFeatures.isEnabled(PreferenceFeatures.StoriesTopColorEffect)) {
                if (LocationKey.isStories(str) || LocationKey.isCollection(str)) {
                    this.mDrawerTabView.updateBackground(true);
                } else if (LocationKey.isStories(this.mFocusKey)) {
                    this.mDrawerTabView.updateBackground(false);
                }
            }
            if (LocationKey.isTimeline(str)) {
                updateRootBgColor(true);
            } else if (LocationKey.isTimeline(this.mFocusKey)) {
                updateRootBgColor(false);
            }
            this.mFocusKey = str;
            DrawerTabViewAdapter drawerTabViewAdapter = this.mDrawerTabViewAdapter;
            String currentAlbumKey = this.mListContainerView.getCurrentAlbumKey();
            if (!needAlbumsCollapsed(str) && !z) {
                z3 = false;
            }
            drawerTabViewAdapter.changeFocus(str, currentAlbumKey, z3);
            if (this.mIsFirstEntry) {
                ThreadUtil.postOnUiThreadDelayed(new C0501c(this, 0), 200);
                this.mIsFirstEntry = false;
                return;
            }
            Optional.ofNullable(getFakeViewManager()).ifPresent(new B(5));
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("album_data_swapped", new f(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://EnterMoveMode", new f(this, 1)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://ExitMoveMode", new f(this, 2)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://OpenDrawer", new f(this, 3)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://CloseDrawer", new f(this, 4)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://ON_EnterSelectionMode", new f(this, 5)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://ON_ExitSelectionMode", new f(this, 6)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://on_thumbnail_load_done", new f(this, 7)));
    }

    public String getFocusKey() {
        return this.mFocusKey;
    }

    public void handleDensityChange() {
        refreshLayout();
    }

    public void handleResolutionChange() {
        refreshLayout();
    }

    public void onChildViewCreated(IBaseFragment iBaseFragment) {
        this.mDrawerTabModel.setChild(iBaseFragment);
        updateChildStartSpacing(isExpanded());
        updateToolbarMargin();
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        p4.e eVar;
        DrawerTabViewAdapter drawerTabViewAdapter = this.mDrawerTabViewAdapter;
        if (drawerTabViewAdapter != null) {
            int i2 = eventMessage.what;
            if (i2 == 1005) {
                int intValue = ((Integer) eventMessage.obj).intValue();
                boolean z = true;
                if (intValue == 1 || intValue == 5) {
                    this.mDrawerTabViewAdapter.getTabItemEnableCondition().onDragStarted();
                } else if (intValue == 4 || intValue == 6) {
                    TabItemEnableCondition tabItemEnableCondition = this.mDrawerTabViewAdapter.getTabItemEnableCondition();
                    if (eventMessage.arg1 != 1) {
                        z = false;
                    }
                    tabItemEnableCondition.onDragEnded(z);
                    return false;
                }
            } else if (i2 == 1064) {
                if (((Boolean) eventMessage.obj).booleanValue()) {
                    eVar = new p4.e(this, 0);
                } else {
                    eVar = null;
                }
                setBottomBarTouchBlocker(eVar);
                return false;
            } else if (i2 == 2004) {
                drawerTabViewAdapter.refreshAlbumNewLabel(eventMessage);
                return false;
            }
        }
        return false;
    }

    public void onMultiWindowModeChanged() {
        if (isSlideAnimating()) {
            completeSlideAnimation();
        }
    }

    public void onSlide(View view, float f) {
        IBaseListView baseListView = getBaseListView();
        if (baseListView != null) {
            int tabWidth = getTabWidth(f);
            if (this.mDrawerTabModel.supportSlideAnimation()) {
                Optional.ofNullable(getAnimationManager()).ifPresent(new g(f));
                getBaseListView().updateExtraStartPadding(f);
            } else if (this.mDrawerTabModel.supportGridLayoutManager()) {
                Optional.ofNullable(this.mDrawerTabModel.getLayoutManager()).ifPresent(new d(this, tabWidth, f, 1));
                baseListView.getListView().requestLayout();
            } else {
                this.mListContainerView.updateDrawerSpaceRelative(tabWidth, 0, 0, 0);
                baseListView.updateExtraStartPadding(f);
            }
            Optional.ofNullable(baseListView.getPresenter()).ifPresent(new C0489a(tabWidth, 5));
            this.mListContainerView.setToolbarStartMargin(getToolbarMarginStart(f));
            Optional.ofNullable(getFakeViewManager()).ifPresent(new x(f, 5));
            this.mDrawerTabView.updateLayoutWithOffset(f);
            adjustBottomBarMargin(f);
        }
    }

    public void onStateChanged(int i2) {
        Optional.ofNullable(this.mAnimationManager).ifPresent(new i(i2));
        Optional.ofNullable(getFakeViewManager()).ifPresent(new C0489a(i2, 7));
        Optional.ofNullable(this.mDrawerTabViewAdapter).ifPresent(new C0489a(i2, 8));
        Optional.ofNullable(this.mDrawerTabView.getLayoutManager()).ifPresent(new C0489a(i2, 9));
        if (i2 == 1 || i2 == 2) {
            setAdapterIfNeeded();
            stopListScroll();
            prepareSlideAnimation();
        } else if (i2 == 3 || i2 == 4) {
            Blackboard blackboard = getBlackboard();
            boolean isExpanded = SheetBehaviorCompat.isExpanded(i2);
            this.mIsLastStateExpanded = isExpanded;
            blackboard.publish("data://drawer_opened", Boolean.valueOf(isExpanded));
            announceDrawerState();
            updateChildStartSpacing(this.mIsLastStateExpanded);
            completeSlideAnimation();
            if (this.mIsLastStateExpanded) {
                this.mListContainerView.updateToolbarNavigationRipple(true);
            }
        }
        if (i2 != this.mDrawerState) {
            getBlackboard().postEvent(EventMessage.obtain(1112, Integer.valueOf(i2)));
            this.mDrawerState = i2;
            updateCollapsedAlbumsTabFocus();
        }
        updateNavigationButton();
    }

    public void setVisibility(boolean z, boolean z3, boolean z7) {
        boolean z9;
        if (z3) {
            if (this.mDrawerTabView.needInflated()) {
                initialize();
            } else if (z7) {
                resetDrawerTabView();
                updateFakeView();
            }
            updateDrawerWidth();
            updateToolbarMargin();
            updateBgColor();
        }
        this.mDrawerTabView.setVisibility(z3);
        if (!z3 || !isExpanded()) {
            z9 = false;
        } else {
            z9 = true;
        }
        updateChildStartSpacing(z9);
    }

    public void unbindView() {
        this.mDrawerTabView.unbindView();
        DrawerTabViewAdapter drawerTabViewAdapter = this.mDrawerTabViewAdapter;
        if (drawerTabViewAdapter != null) {
            drawerTabViewAdapter.destroy();
            this.mDrawerTabViewAdapter = null;
        }
    }

    public void updateBadgeOnHamburger() {
        if (this.mIsBadgeShowing != this.mListContainerView.needBadgeOnNavigationDrawerButton()) {
            updateNavigationButton();
        }
    }

    public void updateBadgeOnTab(int i2, boolean z) {
        Optional.ofNullable(this.mDrawerTabViewAdapter).ifPresent(new C0691a(i2, z, 1));
        Optional.ofNullable(getFakeViewManager()).ifPresent(new B(5));
    }

    public void updateBottomNavigationMenu() {
        DrawerTabViewAdapter drawerTabViewAdapter = this.mDrawerTabViewAdapter;
        if (drawerTabViewAdapter != null) {
            boolean updateSharedAlbumsTab = drawerTabViewAdapter.updateSharedAlbumsTab(isSharedAlbumsTabAvailable());
            boolean updateMtpTab = this.mDrawerTabViewAdapter.updateMtpTab(isMtpTabAvailable());
            boolean updateUsbTab = this.mDrawerTabViewAdapter.updateUsbTab(isUsbTabAvailable());
            if (updateSharedAlbumsTab || updateMtpTab || updateUsbTab) {
                updateFakeView();
            }
        }
    }

    public void updateCollapsedAlbumsTabFocus() {
        Optional.ofNullable(this.mDrawerTabViewAdapter).ifPresent(new C0500b(this, 5));
    }

    public void updateExpandedAlbumsTab() {
        Optional.ofNullable(this.mDrawerTabViewAdapter).ifPresent(new C0500b(this, 1));
    }

    public void updateNavigationBadge(boolean z) {
        this.mDrawerTabView.updateNavigationNewBadge(z, this.mIsLastStateExpanded);
    }

    public void updateNavigationIcon() {
        updateNavigationButton();
    }

    public void updateSettingBadge(boolean z) {
        this.mDrawerTabView.updateSettingsNewBadge(z);
    }

    public void onNavigationIconClicked() {
        if (canDrag()) {
            Optional.ofNullable(this.mListContainerView.getCurrentFragment()).ifPresent(new B(9));
            this.mDrawerTabView.updateBehaviorState(!this.mIsLastStateExpanded);
            if (!this.mIsLastStateExpanded) {
                this.mListContainerView.updateToolbarNavigationRipple(false);
            }
        }
    }
}
