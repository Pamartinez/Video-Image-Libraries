package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import D5.a;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.common.state.OverlayViewState;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.ViewerMenuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.app.ui.viewer2.unlock.UnlockViewerHolder;
import com.samsung.android.gallery.module.utils.ViewerUtils;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.sec.android.gallery3d.R;
import i4.C0468a;
import ic.l;
import java.util.Optional;
import k7.f;
import k7.g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DecorViewDelegate extends AbsVuDelegate<IVuContainerView> {
    View mBottomControl;
    private boolean mHoldDecorView = false;
    View mMainLayout;
    GalleryToolbar mToolbar;
    private FloatingToolbarLayout mToolbarView;

    public DecorViewDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    /* access modifiers changed from: private */
    public void captureDecorView(Object... objArr) {
        View toolbarView = getToolbarView();
        if (ViewUtils.isVisible(toolbarView) && ViewUtils.isVisible(this.mBottomControl)) {
            TransitionInfo transitionInfo = objArr[0];
            transitionInfo.top = ViewUtils.getBitmapFromView(toolbarView);
            transitionInfo.bottom = ViewUtils.getBitmapFromView(this.mBottomControl);
            int[] iArr = new int[2];
            this.mToolbar.getLocationInWindow(iArr);
            int i2 = iArr[0];
            if (Features.isEnabled(Features.IS_RTL)) {
                i2 = ((WindowManager) getContext().getSystemService("window")).getCurrentWindowMetrics().getBounds().width() - (this.mToolbar.getWidth() + i2);
            }
            transitionInfo.paddingStart = i2;
        }
    }

    private void checkAndUpdateAlpha() {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null && BottomSheetState.isClosed(currentViewer.getModel()) && ViewUtils.isTranslucent(getToolbarView())) {
            updateAlpha(1.0f);
        }
    }

    private int getGroupPanel2ndDepthMainLayoutLeftMargin() {
        if (((ContainerModel) this.mModel).isTableMode()) {
            return 0;
        }
        if (((ContainerModel) this.mModel).getStateHelper().isTargetViewHalfOfWidth(((ContainerModel) this.mModel).getStateHelper().supportGroupPanelLandscapeMode())) {
            return DeviceInfo.getDisplayWidth(((IVuContainerView) this.mView).getContext()) / 2;
        }
        if (((ContainerModel) this.mModel).getStateHelper().supportGroupPanelLandscapeMode()) {
            return DeviceInfo.getDisplayHeight(((IVuContainerView) this.mView).getContext());
        }
        return 0;
    }

    private View getToolbarView() {
        FloatingToolbarLayout floatingToolbarLayout = this.mToolbarView;
        if (floatingToolbarLayout != null) {
            return floatingToolbarLayout;
        }
        return this.mToolbar;
    }

    private int getViewerVisibility() {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (!((ContainerModel) this.mModel).isOsdVisible()) {
            return 8;
        }
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.TextExtractionCapsule) && currentViewer != null && currentViewer.getModel().isTextExtractionFullState()) {
            return 8;
        }
        if (currentViewer == null || !OverlayViewState.isAiProcessing(currentViewer.getModel())) {
            return 0;
        }
        return 8;
    }

    private boolean isOverlayViewHide() {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer == null || OverlayViewState.isHide((OverlayViewState.StateListener) currentViewer.getModel())) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$0(Object[] objArr) {
        this.mHoldDecorView = objArr[0].booleanValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$2(Object[] objArr) {
        ThreadUtil.postOnUiThread(new g(this, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$3() {
        onToggleDecorView(Boolean.TRUE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$4(Object[] objArr) {
        ThreadUtil.runOnUiThread(new g(this, 1));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$showToolbarWithANim$5(View view) {
        ViewUtils.setVisibility(view, 0);
        Interpolator create = PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_60);
        PropertyAnimator duration = new AlphaAnimator(view, 0.0f, 1.0f).setInterpolator(create).setDuration(200);
        PropertyAnimator duration2 = new ScaleAnimator(view, 0.6f, 1.0f).setInterpolator(create).setDuration(200);
        duration.start();
        duration2.start();
    }

    private void onBottomSheetSlide(float f) {
        if (isOverlayViewHide()) {
            updateAlpha(ViewerUtils.getDecorAlphaInBottomSheetSlide(f));
        }
    }

    /* access modifiers changed from: private */
    public void onBottomSheetStateChanged(Object... objArr) {
        checkAndUpdateAlpha();
        updateContainerVisibility();
    }

    /* access modifiers changed from: private */
    public void onDetailsSlide(Object... objArr) {
        onBottomSheetSlide(objArr[1].floatValue());
    }

    /* access modifiers changed from: private */
    public void onOverlayViewStateChanged(Object... objArr) {
        updateMainLayout();
        updateContainerVisibility();
        updateAlpha();
    }

    /* access modifiers changed from: private */
    public void onToggleDecorView(Object... objArr) {
        int i2 = 0;
        boolean booleanValue = objArr[0].booleanValue();
        updateMainLayoutPadding();
        if (!booleanValue) {
            i2 = 8;
        }
        updateVisibility(i2, true);
    }

    /* access modifiers changed from: private */
    public void replaceToolbar() {
        FragmentActivity activity = ((IVuContainerView) this.mView).getActivity();
        if (activity != null && this.mMainLayout != null) {
            View inflate = activity.getLayoutInflater().inflate(R.layout.floating_toolbar_viewer, (ViewGroup) null);
            View findViewById = this.mMainLayout.findViewById(R.id.viewer_toolbar);
            ViewUtils.replaceView(findViewById, inflate);
            inflate.setId(findViewById.getId());
            this.mToolbar = (GalleryToolbar) inflate.findViewById(R.id.toolbar);
            FloatingToolbarLayout floatingToolbarLayout = (FloatingToolbarLayout) inflate.findViewById(R.id.sesl_floating_toolbar_layout);
            this.mToolbarView = floatingToolbarLayout;
            floatingToolbarLayout.clearBlurInfo(getContext());
            this.mToolbarView.setColorForFloatingBackground(activity.getResources().getColor(R.color.viewer_floating_menu_background_color, (Resources.Theme) null));
            ((IVuContainerView) this.mView).setToolbar(this.mToolbar);
            initToolbar();
            Optional.ofNullable((NaviUpDelegate) getDelegate(NaviUpDelegate.class)).ifPresent(new l(14));
            updateContainerVisibility();
        }
    }

    /* access modifiers changed from: private */
    public void setToolbarTitle(Object... objArr) {
        Integer num = objArr[0];
        String str = objArr[1];
        if (num != null) {
            this.mToolbar.setTitle(num.intValue());
        } else {
            this.mToolbar.setTitle(str);
        }
    }

    private void showToolbarWithANim() {
        View toolbarView = getToolbarView();
        ViewUtils.setVisibility(toolbarView, 4);
        ThreadUtil.postOnUiThreadDelayed(new a(toolbarView, 7), 300);
    }

    /* access modifiers changed from: private */
    public void startSlideUp(Object... objArr) {
        boolean z;
        boolean z3;
        boolean z7 = false;
        this.mHoldDecorView = false;
        if (!((ContainerModel) this.mModel).isFlipCoverGallery()) {
            if (this.mBottomControl.getVisibility() != 0) {
                z = true;
            } else {
                z = false;
            }
            if (getToolbarView().getVisibility() != 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            Log.d(this.TAG, "startSlideUp" + Logger.v(Boolean.valueOf(z), Boolean.valueOf(z3)));
            ViewUtils.setVisibility(this.mBottomControl, 0);
            ViewUtils.setVisibility(getToolbarView(), 0);
            if (this.mBlackboard.read("data://image_viewer_return_info") != null) {
                z7 = true;
            }
            if (!((ContainerModel) this.mModel).getStateHelper().isQuickView() && !LocationKey.isSecondDepthGroupPanelView(((IVuContainerView) this.mView).getLocationKey()) && !z7 && z) {
                SimpleAnimator.slideUp(this.mBottomControl, (Animation.AnimationListener) null);
            }
        }
    }

    private boolean supportBottomPanel() {
        if (!LocationKey.isSecondDepthGroupPanelView(((IVuContainerView) this.mView).getLocationKey()) || ((ContainerModel) this.mModel).getStateHelper().supportGroupPanelLandscapeMode()) {
            return false;
        }
        return true;
    }

    private void updateAlpha() {
        updateAlpha(getToolbarVisibility() != 0 ? 0.0f : 1.0f);
    }

    private void updateContainerVisibility() {
        updateContainerVisibility(-1, false);
    }

    private void updateLayout() {
        boolean z;
        boolean supportFilmStrip = ViewerUtils.supportFilmStrip(((IVuContainerView) this.mView).getLocationKey());
        if (((ContainerModel) this.mModel).getStateHelper().supportGroupPanelLandscapeMode() || !((Boolean) Optional.ofNullable((ViewerMenuDelegate) getDelegate(ViewerMenuDelegate.class)).map(new C0468a(20)).orElse(Boolean.FALSE)).booleanValue()) {
            z = false;
        } else {
            z = true;
        }
        updateOverlayHeight(z, supportFilmStrip);
        updateMainLayout();
    }

    private void updateMainLayout() {
        updateMainLayoutPadding();
        updateMainLayoutMargin();
    }

    private void updateMainLayoutMargin() {
        OverlayViewState overlayViewState;
        int i2;
        boolean isTableMode = ((ContainerModel) this.mModel).isTableMode();
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null) {
            overlayViewState = currentViewer.getModel().getOverlayViewState();
        } else {
            overlayViewState = OverlayViewState.hide;
        }
        if (!isTableMode || OverlayViewState.isFullViewState(overlayViewState)) {
            i2 = 0;
        } else {
            i2 = (DeviceInfo.getDisplayHeight(((IVuContainerView) this.mView).getContext()) / 2) - this.mMainLayout.getPaddingTop();
        }
        ViewMarginUtils.setTopMargin(this.mMainLayout, i2);
        if (LocationKey.isSecondDepthGroupPanelView(((IVuContainerView) this.mView).getLocationKey())) {
            ViewMarginUtils.setLeftMargin(this.mMainLayout, getGroupPanel2ndDepthMainLayoutLeftMargin());
        }
    }

    private void updateMainLayoutPadding() {
        Rect systemInsets = ((ContainerModel) this.mModel).getSystemInsets();
        int i2 = systemInsets.top;
        int i7 = 0;
        if (((IVuContainerView) this.mView).isLandscape() && SystemUi.hasNoStatusBarInLandscape(((IVuContainerView) this.mView).getActivity()) && i2 > 0) {
            Log.d(this.TAG, "ignore topPadding : " + i2 + " to zero");
            i2 = 0;
        }
        if ((LocationKey.isAllDayTimeLapse(((IVuContainerView) this.mView).getLocationKey()) || LocationKey.isLongExposure(((IVuContainerView) this.mView).getLocationKey())) && ((ContainerModel) this.mModel).getStatusBarInsets().top == 0) {
            i2 = 0;
        }
        if (!((ContainerModel) this.mModel).getStateHelper().supportGroupPanelLandscapeMode()) {
            i7 = systemInsets.left;
        }
        this.mMainLayout.setPadding(i7, i2, systemInsets.right, systemInsets.bottom);
    }

    private void updateViewerVisibility(int i2) {
        boolean z;
        if (i2 < 0) {
            i2 = getViewerVisibility();
        }
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.UPDATE_VIEWER_DECOR_VISIBILITY;
        if (i2 == 0) {
            z = true;
        } else {
            z = false;
        }
        actionInvoker.invoke(viewerAction, Boolean.valueOf(z));
    }

    private void updateVisibility(int i2, boolean z) {
        updateViewerVisibility(i2);
        updateContainerVisibility(i2, z);
    }

    public int getToolbarVisibility() {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.TextExtractionCapsule) && currentViewer != null && currentViewer.getModel().isTextExtractionFullState()) {
            return 4;
        }
        if (currentViewer != null && (currentViewer.getViewHolder() instanceof UnlockViewerHolder)) {
            return 4;
        }
        if ((currentViewer != null && OverlayViewState.isAiProcessing(currentViewer.getModel())) || ViewUtils.isTranslucent(getToolbarView()) || !((ContainerModel) this.mModel).isOsdVisible()) {
            return 4;
        }
        if (((ContainerModel) this.mModel).isFlipCoverGallery() && !((ContainerModel) this.mModel).isSelectMode()) {
            return 4;
        }
        ViewPager2 viewPager = ((ContainerModel) this.mModel).getViewPager();
        if (currentViewer == null || !BottomSheetState.isExpanded(currentViewer.getModel()) || viewPager == null || viewPager.getScrollState() != 0) {
            return 0;
        }
        return 4;
    }

    public int getWidgetVisibility(int i2) {
        if (ViewUtils.isTranslucent(this.mBottomControl)) {
            return 4;
        }
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null && OverlayViewState.isShow((OverlayViewState.StateListener) currentViewer.getModel())) {
            return 4;
        }
        if (currentViewer != null && (currentViewer.getViewHolder() instanceof UnlockViewerHolder)) {
            return 4;
        }
        if (i2 != 0) {
            i2 = 4;
        }
        if (((ContainerModel) this.mModel).isFlipCoverGallery()) {
            return 4;
        }
        return i2;
    }

    public void initToolbar() {
        GalleryToolbar galleryToolbar = this.mToolbar;
        if (galleryToolbar != null) {
            galleryToolbar.setOverflowIcon(galleryToolbar.getContext().getDrawable(R.drawable.gallery_ic_ab_more));
            galleryToolbar.setTitle((CharSequence) null);
            galleryToolbar.setSubtitle((CharSequence) null);
        }
    }

    public void onApplyWindowInsets() {
        updateLayout();
    }

    public void onBindView(View view) {
        this.mBottomControl = view.findViewById(R.id.bottom_control);
        this.mMainLayout = view.findViewById(R.id.main_layout);
        this.mToolbar = ((IVuContainerView) this.mView).getToolbar();
        ViewUtils.post(this.mMainLayout, new g(this, 3));
    }

    public void onConfigurationChanged(Configuration configuration, boolean z, boolean z3, boolean z7, boolean z9) {
        if (z7) {
            replaceToolbar();
        }
        updateLayout();
    }

    public void onEnterTransitionStart() {
        boolean isQuickView = ((ContainerModel) this.mModel).getStateHelper().isQuickView();
        boolean isEnterTransitionFinished = SharedTransition.isEnterTransitionFinished(this.mBlackboard);
        if (!isQuickView && !isEnterTransitionFinished && !LocationKey.isSecondDepthGroupPanelView(((IVuContainerView) this.mView).getLocationKey())) {
            ViewUtils.setVisibility(this.mBottomControl, 4);
            ViewUtils.setVisibility(getToolbarView(), 4);
        }
        ThreadUtil.postOnUiThreadDelayed(new g(this, 2), 30);
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        updateContainerVisibility();
    }

    public void onResume() {
        updateVisibility(-1, false);
        updateMainLayoutPadding();
    }

    public void onTableModeChanged(boolean z, int i2) {
        updateLayout();
        if (ViewUtils.isVisible(getToolbarView()) && isOverlayViewHide()) {
            showToolbarWithANim();
        }
    }

    public void onToggleOsd() {
        updateVisibility(-1, true);
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.DETAILS_SLIDE, new f(this, 1));
        actionInvoker.add(ViewerAction.OVERLAY_VIEW_STATE_CHANGED, new f(this, 2));
        actionInvoker.add(ViewerAction.BOTTOM_SHEET_STATE_CHANGED, new f(this, 3));
        actionInvoker.add(ViewerAction.SET_DECOR_VISIBILITY, new f(this, 4));
        actionInvoker.add(ViewerAction.SET_TOOLBAR_TITLE, new f(this, 5));
        actionInvoker.add(ViewerAction.HOLD_DECOR_VIEW_FOR_SLIDE_UP_VI, new f(this, 6));
        actionInvoker.add(ViewerAction.START_SLIDE_UP_VI, new f(this, 7));
        actionInvoker.add(ViewerAction.PREPARE_EXIT_TRANSITION, new f(this, 8));
        actionInvoker.add(ViewerAction.SHARE_SHEET_CLEARED, new f(this, 0));
    }

    public void updateOverlayHeight(boolean z, boolean z3) {
        int i2;
        int i7;
        int i8;
        int i10;
        if (z) {
            i2 = ((IVuContainerView) this.mView).getResources().getDimensionPixelOffset(R.dimen.viewer_floating_fast_option_top_margin) + ((IVuContainerView) this.mView).getResources().getDimensionPixelOffset(R.dimen.viewer_floating_fast_option_bottom_margin) + ((IVuContainerView) this.mView).getResources().getDimensionPixelOffset(R.dimen.fast_menu_imageview_height);
        } else {
            i2 = 0;
        }
        if (z3) {
            i7 = ((IVuContainerView) this.mView).getResources().getDimensionPixelOffset(R.dimen.photo_strip_view_out_height);
        } else {
            i7 = 0;
        }
        if (supportBottomPanel()) {
            Resources resources = ((IVuContainerView) this.mView).getResources();
            if (!((IVuContainerView) this.mView).isInMultiWindowMode()) {
                i10 = R.dimen.group_panel_height;
            } else if (LocationKey.isAiEditGroupPanelViewer(((IVuContainerView) this.mView).getLocationKey())) {
                i10 = R.dimen.group_panel_ai_edit_height_multi_window;
            } else {
                i10 = R.dimen.group_panel_height_multi_window;
            }
            i8 = resources.getDimensionPixelOffset(i10);
        } else {
            i8 = 0;
        }
        ((ContainerModel) this.mModel).setOverlaySize(0, 0, 0, i7 + i2 + i8);
    }

    private void updateContainerVisibility(int i2, boolean z) {
        if (this.mHoldDecorView) {
            Log.d(this.TAG, "updateContainerVisibility fail");
            return;
        }
        View toolbarView = getToolbarView();
        if (i2 < 0) {
            i2 = getToolbarVisibility();
        }
        int widgetVisibility = getWidgetVisibility(i2);
        if (z && i2 == 0) {
            SimpleAnimator.setAlphaVisible(toolbarView, StatusCodes.INPUT_MISSING);
        } else if (ViewUtils.isViewVisibilityChanged(toolbarView, i2)) {
            ViewUtils.setVisibility(toolbarView, i2);
        }
        if (z && widgetVisibility == 0) {
            SimpleAnimator.setAlphaVisible(this.mBottomControl, StatusCodes.INPUT_MISSING);
        } else if (ViewUtils.isViewVisibilityChanged(this.mBottomControl, widgetVisibility)) {
            ViewUtils.setVisibility(this.mBottomControl, widgetVisibility);
        }
        if (((ContainerModel) this.mModel).getContainerWidgetVisibility() != widgetVisibility) {
            ((ContainerModel) this.mModel).setContainerWidgetVisibility(widgetVisibility);
            this.mActionInvoker.invoke(ViewerAction.UPDATE_CONTAINER_DECOR_VISIBILITY, Integer.valueOf(widgetVisibility));
        }
    }

    public void updateAlpha(float f) {
        ViewUtils.setAlpha(this.mBottomControl, f);
        ViewUtils.setAlpha(getToolbarView(), f);
        this.mActionInvoker.invoke(ViewerAction.UPDATE_CONTAINER_DECOR_ALPHA, Float.valueOf(f));
        updateContainerVisibility();
    }
}
