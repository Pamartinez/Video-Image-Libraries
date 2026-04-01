package com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu;

import A4.C0385u;
import A4.P;
import B4.c;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuCompat;
import androidx.viewpager2.widget.ViewPager2;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.common.state.OverlayViewState;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager.ForceSwipeDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.menu.UnFavoriteMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuBuilder;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuFactory;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuMap;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.ViewTag;
import com.samsung.android.gallery.widget.fastoption2.FastOptionItemParams;
import com.samsung.android.gallery.widget.fastoption2.FastOptionItemView;
import com.samsung.android.gallery.widget.fastoption2.FastOptionMenuItem;
import com.samsung.android.gallery.widget.fastoption2.FastOptionView;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import f4.a;
import h.C0199b;
import h4.C0464a;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import n4.C0491c;
import n5.e;
import p7.b;
import p7.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewerMenuDelegate extends AbsVuDelegate<IVuContainerView> {
    private static final CharSequence TAG = "ViewerMenuDelegate";
    Runnable execLastMenuItem;
    private ViewerMenuMap mCurrentMenuMap;
    private final ConcurrentHashMap<Class<?>, ViewerMenuItem> mEnabledMenuMap = new ConcurrentHashMap<>();
    private FastOptionView mFastOptionView;
    private ViewStub mFastOptionViewStub;
    private ViewGroup mParentView;
    private View mPppProgress;
    private ViewerMenuMap mSingleTakenSelectionMenuMap;
    private ViewerMenuMap mViewerMenuMap;

    public ViewerMenuDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
        createMenuMap();
    }

    private void applyMenu(Menu menu, List<ViewerMenuItem> list) {
        if (!compareMenuItem(menu, list)) {
            long currentTimeMillis = System.currentTimeMillis();
            menu.clear();
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            for (ViewerMenuItem attachToMenu : list) {
                arrayList.add(attachToMenu.attachToMenu(menu, i2));
                i2++;
            }
            for (int i7 = 0; i7 < arrayList.size(); i7++) {
                list.get(i7).setMenuItemAttributes((MenuItem) arrayList.get(i7));
            }
            Log.d(TAG, "applyMenu(" + list.size() + ") +" + (System.currentTimeMillis() - currentTimeMillis), StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, list.stream().filter(new C0464a(22)).limit(3).iterator()));
            return;
        }
        Log.d(TAG, "applyMenu(" + list.size() + ") skip for the same menus");
    }

    private void bindFastOptionView() {
        FastOptionView fastOptionView = (FastOptionView) this.mFastOptionViewStub.inflate().findViewById(R.id.fast_option_view);
        this.mFastOptionView = fastOptionView;
        fastOptionView.setItemSelectedListener(new d(this));
        ((ContainerModel) this.mModel).setFastOptionView(this.mFastOptionView);
        ViewUtils.setVisibleOrGone(this.mFastOptionView, supportFastOption());
        ViewUtils.setWidth(this.mFastOptionView, -2);
        FastOptionView fastOptionView2 = this.mFastOptionView;
        fastOptionView2.setElevation((float) fastOptionView2.getResources().getDimensionPixelSize(R.dimen.viewer_floating_fast_option_elevation));
        FastOptionView fastOptionView3 = this.mFastOptionView;
        ViewMarginUtils.setTopMargin(fastOptionView3, fastOptionView3.getResources().getDimensionPixelOffset(R.dimen.viewer_floating_fast_option_top_margin));
        FastOptionView fastOptionView4 = this.mFastOptionView;
        ViewMarginUtils.setBottomMargin(fastOptionView4, fastOptionView4.getResources().getDimensionPixelOffset(R.dimen.viewer_floating_fast_option_bottom_margin));
    }

    private boolean compareMenuItem(Menu menu, List<ViewerMenuItem> list) {
        if (list.size() != menu.size()) {
            return false;
        }
        for (int i2 = 0; i2 < menu.size(); i2++) {
            MenuItem item = menu.getItem(i2);
            ViewerMenuItem viewerMenuItem = list.get(i2);
            boolean isDim = true ^ viewerMenuItem.isDim();
            if (item.getItemId() != viewerMenuItem.getMenuId() || item.isEnabled() != isDim) {
                return false;
            }
        }
        return true;
    }

    private void createMenuMap() {
        this.mViewerMenuMap = ViewerMenuFactory.createViewerMenu(((IVuContainerView) this.mView).getEventContext(), this.mActionInvoker);
        if (LocationKey.isSecondDepthGroupPanelView(((IVuContainerView) this.mView).getLocationKey())) {
            this.mSingleTakenSelectionMenuMap = ViewerMenuFactory.createSingleTakenSelectionMenu(((IVuContainerView) this.mView).getEventContext(), this.mActionInvoker);
        }
    }

    private ActionMenuView findActionMenuView(Toolbar toolbar) {
        int childCount = toolbar.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = toolbar.getChildAt(i2);
            if (childAt instanceof ActionMenuView) {
                return (ActionMenuView) childAt;
            }
        }
        return null;
    }

    private int getHash(List<ViewerMenuItem> list) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(supportFastOption());
        list.forEach(new P(sb2, 6));
        return sb2.toString().hashCode();
    }

    private String getLocationKeyForMenu() {
        OverlayViewState overlayViewState = getOverlayViewState();
        if (overlayViewState == null || !OverlayViewState.isShow(overlayViewState)) {
            return ((IVuContainerView) this.mView).getLocationKey();
        }
        return overlayViewState.toString();
    }

    private ViewerMenuMap getMenuMap() {
        if (this.mSingleTakenSelectionMenuMap == null || !((ContainerModel) this.mModel).isSelectMode()) {
            return this.mViewerMenuMap;
        }
        return this.mSingleTakenSelectionMenuMap;
    }

    private OverlayViewState getOverlayViewState() {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null) {
            return currentViewer.getModel().getOverlayViewState();
        }
        return null;
    }

    private ArrayList<FastOptionMenuItem> getToolbarMoreMenuItems() {
        ArrayList<FastOptionMenuItem> arrayList = new ArrayList<>();
        this.mEnabledMenuMap.values().stream().sorted(Comparator.comparing(new e(17))).filter(new C0464a(21)).forEach(new a(arrayList, 18));
        return arrayList;
    }

    private boolean isLayoutUpdateRequired(ActionMenuView actionMenuView) {
        if (actionMenuView.getWidth() == 0) {
            return true;
        }
        if (actionMenuView.getChildCount() <= 0 || actionMenuView.getChildAt(0).getWidth() != 0) {
            return false;
        }
        return true;
    }

    private boolean isPageSelected() {
        if (((IVuContainerView) this.mView).getCurrentViewer() != null) {
            return true;
        }
        return false;
    }

    private boolean isPageSwiping() {
        ViewPager2 viewPager = ((ContainerModel) this.mModel).getViewPager();
        if (viewPager == null || viewPager.getScrollState() == 0) {
            ForceSwipeDelegate forceSwipeDelegate = (ForceSwipeDelegate) getDelegate(ForceSwipeDelegate.class);
            if (forceSwipeDelegate == null || !forceSwipeDelegate.isWorking()) {
                return false;
            }
            return true;
        }
        Log.d(TAG, "page is scrolling");
        return true;
    }

    private boolean isPpp() {
        MediaItem mediaItem;
        if (((IVuContainerView) this.mView).getEventContext() != null) {
            mediaItem = ((IVuContainerView) this.mView).getEventContext().getCurrentItem();
        } else {
            mediaItem = null;
        }
        if (mediaItem == null || !mediaItem.isCommonPostProcessing()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean isPppDim() {
        if (this.execLastMenuItem == null || !isPpp()) {
            return false;
        }
        return true;
    }

    private boolean isToolbarMenu(boolean z, ViewerMenuItem viewerMenuItem) {
        if (!z || viewerMenuItem.isToolbarOnly()) {
            return true;
        }
        if (!supportSimplestFastOption() || viewerMenuItem.isFastOptionMenu()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$applyMenu$16(ViewerMenuItem viewerMenuItem) {
        return !viewerMenuItem.isDim();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$0(Object obj, Bundle bundle) {
        invalidateOptionsMenu();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$1(Object obj, Bundle bundle) {
        invalidateOptionsMenu();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$createMenu$12(MenuItem menuItem) {
        return onToolbarItemSelected(menuItem.getItemId(), ((IVuContainerView) this.mView).getToolbar());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getHash$17(StringBuilder sb2, ViewerMenuItem viewerMenuItem) {
        sb2.append(viewerMenuItem.getMenuId());
        sb2.append(viewerMenuItem.getTitleResId());
        sb2.append(viewerMenuItem.isDim());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getToolbarMoreMenuItems$20(ViewerMenuItem viewerMenuItem) {
        if (viewerMenuItem.getShowAsActionFlag() == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onConfigurationChanged$11() {
        this.mFastOptionView.updatePadding();
        this.mFastOptionView.updateItemLayoutParams();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onEnterTransitionStart$7(Menu menu) {
        if (!((IVuContainerView) this.mView).isLandscape()) {
            createMenu(menu);
        }
        this.mActionInvoker.invoke(ViewerAction.START_SLIDE_UP_VI, new Object[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onEnterTransitionStart$8() {
        GalleryToolbar toolbar = ((IVuContainerView) this.mView).getToolbar();
        if (toolbar != null) {
            ThreadUtil.postOnUiThread(new C0199b(22, this, toolbar.getMenu()));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMenuItemSelected$18(MediaItem mediaItem, int i2, View view) {
        MediaItem currentMediaItem = ((ContainerModel) this.mModel).getCurrentMediaItem();
        if (currentMediaItem != null && mediaItem.getFileId() == currentMediaItem.getFileId()) {
            CharSequence charSequence = TAG;
            Log.d(charSequence, "execLastMenuItem : " + i2);
            onMenuItemSelectedInternal(i2, view);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMenuItemSelectedInternal$19(ViewerMenuItem viewerMenuItem, ViewerMenuItem viewerMenuItem2) {
        ((IVuContainerView) this.mView).setInputBlock(TAG + "_onMenuItemSelectedInternal", viewerMenuItem.getInputBlockTime());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestLayoutForMenu$13(Toolbar toolbar) {
        ActionMenuView findActionMenuView = findActionMenuView(toolbar);
        if (findActionMenuView != null && isLayoutUpdateRequired(findActionMenuView)) {
            findActionMenuView.requestLayout();
            Log.w(TAG, "ActionMenuView requestLayout");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resetAllMenus$14() {
        this.mFastOptionView.updateItemLayoutParams();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$runLastExecution$10() {
        Runnable runnable = this.execLastMenuItem;
        if (runnable != null) {
            runnable.run();
            this.execLastMenuItem = null;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$2(Object[] objArr) {
        invalidateOptionsMenu();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$3(Object[] objArr) {
        invalidateOptionsMenu();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$4(Object[] objArr) {
        resetFavouriteIcon();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$5(Object[] objArr) {
        updateNewBadgeForMoreMenu();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$6(Object[] objArr) {
        resetLastExecution();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setToolbarOverflowShowingListener$9() {
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            blackboard.postEvent(EventMessage.obtain(1100));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showPppLoadingToolbar$15(boolean z) {
        CharSequence charSequence = TAG;
        Log.d(charSequence, "showPppLoadingToolbar : " + z);
        if (!this.mParentView.isAttachedToWindow()) {
            Log.d(charSequence, "parent View is not attached");
            return;
        }
        if (this.mPppProgress == null && z) {
            View d = C0086a.d(this.mParentView, R.layout.postprocessing_progress_land, (ViewGroup) null, false);
            this.mPppProgress = d;
            this.mParentView.addView(d);
        }
        View view = this.mPppProgress;
        if (view == null) {
            return;
        }
        if (z) {
            view.getLayoutParams().width = -1;
            this.mPppProgress.bringToFront();
            return;
        }
        ViewUtils.removeView(this.mParentView, view);
        this.mPppProgress = null;
    }

    /* access modifiers changed from: private */
    public void onFastOptionItemSelected(int i2, View view) {
        if (view != null) {
            view.setTag(ViewTag.FAST_OPTION);
        }
        onMenuItemSelected(i2, view);
    }

    private boolean onMenuItemSelectedInternal(int i2, View view) {
        ViewerMenuItem menuItem = this.mCurrentMenuMap.getMenuItem(i2);
        Optional.ofNullable(menuItem).ifPresent(new C0491c(10, this, menuItem));
        return this.mCurrentMenuMap.onMenuItemSelected(i2, view);
    }

    private boolean onPppMediaItem(Menu menu, boolean z) {
        if (!Features.isEnabled(Features.SUPPORT_PPP_MENU)) {
            MediaItem currentMediaItem = ((ContainerModel) this.mModel).getCurrentMediaItem();
            if (currentMediaItem == null || !currentMediaItem.isPostProcessing()) {
                showPppLoadingToolbar(false);
            } else {
                if (z) {
                    showPppLoadingToolbar(false);
                    this.mFastOptionView.addPostProcessing();
                } else {
                    showPppLoadingToolbar(true);
                }
                if (menu != null) {
                    menu.clear();
                }
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void onSecondaryClicked(Object... objArr) {
        Log.d(TAG, "onSecondaryClicked");
        MotionEvent motionEvent = objArr[0];
        if (!((ContainerModel) this.mModel).isOsdVisible()) {
            this.mActionInvoker.invoke(ViewerAction.TOGGLE_OSD, new Object[0]);
        }
        showMoreMenuInClickedPosition(motionEvent);
    }

    private boolean onToolbarItemSelected(int i2, View view) {
        view.setTag(ViewTag.TOOLBAR);
        return onMenuItemSelected(i2, view);
    }

    private void requestLayoutForMenu(Toolbar toolbar) {
        if (toolbar != null) {
            toolbar.post(new C0199b(this, toolbar));
        }
    }

    private void resetAllMenus(Menu menu, List<ViewerMenuItem> list, boolean z) {
        this.mFastOptionView.clear();
        ArrayList arrayList = new ArrayList();
        this.mEnabledMenuMap.clear();
        if (Features.isEnabled(Features.SUPPORT_PPP_MENU) || !onPppMediaItem(menu, z)) {
            boolean z3 = false;
            for (ViewerMenuItem next : list) {
                this.mEnabledMenuMap.put(next.getClass(), next);
                if (!isToolbarMenu(z, next)) {
                    next.resetDrawableAlpha();
                    this.mFastOptionView.addItem(FastOptionItemParams.builder().setTitleRes(next.getTitleResId()).setDrawable(next.getDrawable()).setMenuId(next.getMenuId()).setTitle(next.getTitle()).setShowText(next.isShowText()).setFastOptionMenu(next.isFastOptionMenu()).setDisabledDim(next.isDisabledDim()).setEnabledDim(next.isEnabledDim()).setGroupId(next.getGroupId()).build());
                    if (next.isShowText()) {
                        z3 = true;
                    }
                } else if (menu != null) {
                    arrayList.add(next);
                }
            }
            ((ContainerModel) this.mModel).setEnabledMenuMap(this.mEnabledMenuMap);
            if (menu != null) {
                applyMenu(menu, arrayList);
            }
            this.mFastOptionView.applyMenu(z3, LocationKey.isRevitalizationView(getLocationKeyForMenu()), isPppDim());
            ViewUtils.post(this.mFastOptionView, new p7.e(this, 0));
        }
    }

    private void resetFavouriteIcon() {
        MediaItem readCandidateMediaItemByViewPager;
        ViewerMenuItem viewerMenuItem = this.mEnabledMenuMap.get(UnFavoriteMenuItem.class);
        if (viewerMenuItem != null && (readCandidateMediaItemByViewPager = ((ContainerModel) this.mModel).readCandidateMediaItemByViewPager()) != null && !readCandidateMediaItemByViewPager.isFavourite()) {
            FastOptionItemView fastOptionItemView = (FastOptionItemView) this.mFastOptionView.findViewWithTag(Integer.valueOf(viewerMenuItem.getMenuId()));
            Context context = ((IVuContainerView) this.mView).getContext();
            if (fastOptionItemView != null && context != null) {
                fastOptionItemView.setImage(context.getDrawable(R.drawable.gallery_detailview_like_heart_layer));
                this.mFastOptionView.setMenuHash(0);
            }
        }
    }

    private void resetLastExecution() {
        if (Features.isEnabled(Features.SUPPORT_PPP_MENU) && this.execLastMenuItem != null) {
            Log.d(TAG, "resetLastExecution");
            this.execLastMenuItem = null;
            this.mFastOptionView.setMenuHash(0);
            invalidateOptionsMenu();
        }
    }

    private void resetToolBarMenuOnly(Menu menu, List<ViewerMenuItem> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (ViewerMenuItem next : list) {
            if (isToolbarMenu(z, next)) {
                arrayList.add(next);
            }
        }
        applyMenu(menu, arrayList);
        requestLayoutForMenu(((IVuContainerView) this.mView).getToolbar());
    }

    private void runLastExecution() {
        if (Features.isEnabled(Features.SUPPORT_PPP_MENU) && this.execLastMenuItem != null) {
            MediaItem currentMediaItem = ((ContainerModel) this.mModel).getCurrentMediaItem();
            if (currentMediaItem == null || currentMediaItem.isPostProcessing()) {
                CharSequence charSequence = TAG;
                Log.w(charSequence, "runLastExecution fail : " + currentMediaItem);
                return;
            }
            Log.v(TAG, "runLastExecution");
            ThreadUtil.postOnUiThreadDelayed(new p7.e(this, 2), 500);
        }
    }

    private void setPppDimCondition(List<ViewerMenuItem> list) {
        if (isPpp()) {
            for (ViewerMenuItem next : list) {
                if (!next.isSupportPpp()) {
                    next.setPppDimCondition(new C0385u(24, this));
                }
            }
        }
    }

    private void setToolbarOverflowShowingListener() {
        GalleryToolbar toolbar = ((IVuContainerView) this.mView).getToolbar();
        if (toolbar != null) {
            toolbar.setOverflowMenuShowingListener(new d(this));
        }
    }

    private void showMoreMenuInClickedPosition(MotionEvent motionEvent) {
        boolean z;
        FastOptionView fastOptionView = this.mFastOptionView;
        if (fastOptionView != null) {
            fastOptionView.updateMoreMenuAdapter(getToolbarMoreMenuItems());
            boolean isTabletDpi = ((ContainerModel) this.mModel).getSystemUi().isTabletDpi();
            ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
            if (currentViewer == null || !BottomSheetState.isClosed(currentViewer.getModel())) {
                z = false;
            } else {
                z = true;
            }
            if (isTabletDpi && z) {
                this.mFastOptionView.showMoreMenuInClickedPosition(motionEvent);
                return;
            }
            return;
        }
        Log.w(TAG, "showMoreMenuInClickedPosition fail");
    }

    private void showPppLoadingToolbar(boolean z) {
        ThreadUtil.postOnUiThread(new c((Object) this, z, 26));
    }

    private boolean supportSimplestFastOption() {
        if (!PreferenceFeatures.OneUi6x.SIMPLE_FAST_OPTIONS || LocationKey.isSuggestionViewList(((IVuContainerView) this.mView).getLocationKey()) || LocationKey.isAiEditGroupPanelViewer(((IVuContainerView) this.mView).getLocationKey())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void updateGroupPanelMenu(Object... objArr) {
        if (LocationKey.isSecondDepthGroupPanelView(getLocationKeyForMenu())) {
            invalidateOptionsMenu();
        }
    }

    /* access modifiers changed from: private */
    public void updateMenuDim(Object... objArr) {
        MenuItem menuItem;
        int intValue = objArr[0].intValue();
        boolean booleanValue = objArr[1].booleanValue();
        GalleryToolbar toolbar = ((IVuContainerView) this.mView).getToolbar();
        if (toolbar != null) {
            menuItem = toolbar.getMenu().findItem(intValue);
        } else {
            menuItem = null;
        }
        if (menuItem != null) {
            menuItem.setEnabled(booleanValue);
        }
    }

    private void updateNewBadgeForMoreMenu() {
        FastOptionView fastOptionView = this.mFastOptionView;
        if (fastOptionView != null) {
            fastOptionView.updateNewBadge();
        }
    }

    public void closeMoreMenu() {
        FastOptionView fastOptionView = this.mFastOptionView;
        if (fastOptionView != null) {
            fastOptionView.closeMoreMenu();
        }
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createGlobalSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("global://setting/system/auto_rotation", new b(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/system/auto_rotation/second", new b(this, 1)).setWorkingOnUI());
    }

    public void createMenu(Menu menu) {
        MediaItem mediaItem;
        String str;
        try {
            StringBuilder sb2 = new StringBuilder();
            CharSequence charSequence = TAG;
            sb2.append(charSequence);
            sb2.append(" createMenu");
            Trace.beginSection(sb2.toString());
            String locationKeyForMenu = getLocationKeyForMenu();
            if (((IVuContainerView) this.mView).getEventContext() != null) {
                mediaItem = ((IVuContainerView) this.mView).getEventContext().getCurrentItem();
            } else {
                mediaItem = null;
            }
            if (locationKeyForMenu != null) {
                if (mediaItem != null) {
                    if (((ContainerModel) this.mModel).getStateHelper().isDecorViewEnabled() || (LocationKey.isSecondDepthGroupPanelView(locationKeyForMenu) && ((ContainerModel) this.mModel).isSelectMode())) {
                        boolean z = true;
                        if (menu != null) {
                            MenuCompat.setGroupDividerEnabled(menu, true);
                            ((IVuContainerView) this.mView).getToolbar().setOnMenuItemClickListener(new d(this));
                        }
                        ViewerMenuMap menuMap = getMenuMap();
                        this.mCurrentMenuMap = menuMap;
                        List<ViewerMenuItem> build = ViewerMenuBuilder.create(menuMap).setLocation(locationKeyForMenu).setMediaItem(mediaItem).build();
                        setPppDimCondition(build);
                        int hash = getHash(build);
                        boolean supportFastOption = supportFastOption();
                        boolean isSameMenus = this.mFastOptionView.isSameMenus(hash);
                        StringBuilder sb3 = new StringBuilder("createMenu");
                        Long valueOf = Long.valueOf(mediaItem.getFileId());
                        if (isSameMenus) {
                            str = "reuse";
                        } else {
                            str = "reset";
                        }
                        Boolean valueOf2 = Boolean.valueOf(supportFastOption);
                        if (menu == null) {
                            z = false;
                        }
                        sb3.append(Logger.v(valueOf, str, valueOf2, Boolean.valueOf(z)));
                        Log.d(charSequence, sb3.toString());
                        if (!isSameMenus) {
                            closeMoreMenu();
                            resetAllMenus(menu, build, supportFastOption);
                            this.mFastOptionView.setMenuHash(hash);
                        } else if (menu != null) {
                            resetToolBarMenuOnly(menu, build, supportFastOption);
                        }
                        Trace.endSection();
                        return;
                    }
                    Trace.endSection();
                    return;
                }
            }
            Log.w(charSequence, "createMenu failed", locationKeyForMenu, mediaItem);
        } finally {
            Trace.endSection();
        }
    }

    public <T extends ViewerMenuItem> Integer getMenuId(Class<T> cls) {
        ViewerMenuItem viewerMenuItem = this.mEnabledMenuMap.get(cls);
        if (viewerMenuItem != null) {
            return Integer.valueOf(viewerMenuItem.getMenuId());
        }
        return null;
    }

    public void invalidateOptionsMenu() {
        ThreadUtil.assertUiThread("invalidateOptionsMenu");
        GalleryToolbar toolbar = ((IVuContainerView) this.mView).getToolbar();
        if (toolbar != null) {
            createMenu(toolbar.getMenu());
        }
    }

    public <T extends ViewerMenuItem> boolean isMenuEnabled(Class<T> cls) {
        return this.mEnabledMenuMap.containsKey(cls);
    }

    public void onBindView(View view) {
        this.mParentView = (ViewGroup) view;
        if (this.mFastOptionViewStub == null) {
            this.mFastOptionViewStub = (ViewStub) view.findViewById(R.id.fast_option_view_stub);
        }
        setToolbarOverflowShowingListener();
        bindFastOptionView();
        long currentTimeMillis = System.currentTimeMillis();
        createMenu((Menu) null);
        CharSequence charSequence = TAG;
        Log.d(charSequence, "onBindView#createMenu +" + (System.currentTimeMillis() - currentTimeMillis));
    }

    public void onConfigurationChanged(Configuration configuration, boolean z, boolean z3, boolean z7, boolean z9) {
        if (z7) {
            createMenuMap();
        }
        if (supportFastOption() && !ViewUtils.isVisible(this.mFastOptionView)) {
            ViewUtils.setVisibleOrGone(this.mFastOptionView, true);
        }
        closeMoreMenu();
        invalidateOptionsMenu();
        FastOptionView fastOptionView = this.mFastOptionView;
        if (fastOptionView != null) {
            fastOptionView.post(new p7.e(this, 3));
        }
    }

    public void onEnterTransitionEnd() {
        if (((IVuContainerView) this.mView).isLandscape()) {
            ThreadUtil.postOnUiThread(new p7.e(this, 4));
        }
    }

    public void onEnterTransitionStart() {
        if (!((ContainerModel) this.mModel).getStateHelper().isQuickView()) {
            this.mActionInvoker.invoke(ViewerAction.HOLD_DECOR_VIEW_FOR_SLIDE_UP_VI, Boolean.TRUE);
        }
        ThreadUtil.postOnUiThread(new p7.e(this, 1));
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 3203) {
            return false;
        }
        invalidateOptionsMenu();
        return true;
    }

    public boolean onMenuItemSelected(int i2, View view) {
        if (this.execLastMenuItem != null || this.mCurrentMenuMap == null) {
            return false;
        }
        if (((IVuContainerView) this.mView).isInputBlocked()) {
            Log.w(TAG, "onMenuItemSelected canceled caused by input block");
            return false;
        } else if (isPageSwiping()) {
            Log.w(TAG, "onMenuItemSelected skip : swipe is working");
            return false;
        } else {
            if (Features.isEnabled(Features.SUPPORT_PPP_MENU)) {
                MediaItem currentMediaItem = ((ContainerModel) this.mModel).getCurrentMediaItem();
                ViewerMenuItem menuItem = this.mViewerMenuMap.getMenuItem(i2);
                if (currentMediaItem != null && menuItem != null && currentMediaItem.isCommonPostProcessing() && !menuItem.isSupportPpp()) {
                    CharSequence charSequence = TAG;
                    Log.d(charSequence, "onMenuItemSelected but ppp : " + menuItem);
                    this.execLastMenuItem = new F9.e((Object) this, (Object) currentMediaItem, i2, (Object) view, 6);
                    invalidateOptionsMenu();
                    return true;
                }
            }
            return onMenuItemSelectedInternal(i2, view);
        }
    }

    public void onPageInvalidate(int i2, ViewerObjectComposite viewerObjectComposite) {
        runLastExecution();
        if (!((ContainerModel) this.mModel).getStateHelper().isGroupItemLoading()) {
            invalidateOptionsMenu();
        } else {
            Log.i(TAG, "onPageInvalidate skip");
        }
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        Trace.beginSection(TAG + " onPageSelected " + i2);
        if (Features.isEnabled(Features.SUPPORT_PPP_MENU)) {
            this.execLastMenuItem = null;
        }
        invalidateOptionsMenu();
        runLastExecution();
        Trace.endSection();
    }

    public void onResume() {
        if (isPageSelected()) {
            ViewUtils.post(((IVuContainerView) this.mView).getToolbar(), new p7.e(this, 4));
        }
    }

    public void onSecondDepthItemDirectlySelected(int i2) {
        onMenuItemSelected(i2, this.mFastOptionView.getFastOptionMoreItem());
    }

    public void onStop() {
        super.onStop();
        GalleryToolbar toolbar = ((IVuContainerView) this.mView).getToolbar();
        if (LocationKey.isPrivateAlbum(((IVuContainerView) this.mView).getLocationKey()) && toolbar != null && toolbar.getMenu() != null) {
            toolbar.getMenu().close();
        }
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.SECONDARY_CLICK, new p7.c(this, 4));
        actionInvoker.add(ViewerAction.CURRENT_ITEM_CHANGED, new p7.c(this, 5));
        actionInvoker.add(ViewerAction.INVALIDATE_TOOLBAR_MENU, new p7.c(this, 6));
        actionInvoker.add(ViewerAction.RESET_FAVOURITE_MENU_ITEM, new p7.c(this, 7));
        actionInvoker.add(ViewerAction.UPDATE_MORE_OPTION_NEW_BADGE, new p7.c(this, 0));
        actionInvoker.add(ViewerAction.VIEW_PAGER_PAGE_SCROLLED, new p7.c(this, 1));
        actionInvoker.add(ViewerAction.SINGLE_TAKEN_SELECT_MODE_CHANGED, new p7.c(this, 2));
        actionInvoker.add(ViewerAction.UPDATE_MENU_DIM, new p7.c(this, 3));
    }

    public boolean supportFastOption() {
        boolean z;
        boolean z3;
        if (!LocationKey.isSecondDepthGroupPanelView(((IVuContainerView) this.mView).getLocationKey()) || ((ContainerModel) this.mModel).isTableMode()) {
            z = false;
        } else {
            z = true;
        }
        if (!z || !((ContainerModel) this.mModel).isSelectMode()) {
            z3 = false;
        } else {
            z3 = true;
        }
        if ((((IVuContainerView) this.mView).isPortrait() || ((IVuContainerView) this.mView).isInMultiWindowMode() || z || ((ContainerModel) this.mModel).getStateHelper().isLargeScreen()) && (((ContainerModel) this.mModel).getStateHelper().isDecorViewEnabled() || z3)) {
            return true;
        }
        return false;
    }
}
