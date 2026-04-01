package com.samsung.android.gallery.app.ui.viewer2.details;

import H7.q;
import L7.i;
import L7.j;
import L7.k;
import L7.l;
import L7.m;
import L7.n;
import android.content.res.Configuration;
import android.util.Size;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.bottomsheet.ViewerBottomSheetMarginHelper;
import com.samsung.android.gallery.widget.bottomsheet.ViewerBottomSheetScaleHelper;
import com.samsung.android.gallery.widget.details.DetailsBehavior;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DetailsSlideHandler extends ViewerObject implements FragmentLifeCycle, DetailsBehavior.BottomSheetCallback {
    private final float BOTTOM_SHEET_ALPHA_THRESHOLD = 0.6f;
    private DetailsBehavior mBehavior;
    private View mContainer;
    private DetailsView mDetailsView;
    private ViewerBottomSheetMarginHelper mMarginHelper;
    private IMediaPlayerView mMediaView;
    private PhotoView mPhotoView;
    private ViewerBottomSheetScaleHelper mScaleHelper;
    private CoordinatorLayout mViewerLayout;

    /* access modifiers changed from: private */
    public int calcExpandedOffset() {
        if (this.mModel.getContainerModel().isTableMode() || (this.mModel.getStateHelper().supportDetailsLargeScreen() && this.mModel.getSystemUi().isPortrait())) {
            return this.mMarginHelper.getTargetSize().getHeight();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    /* renamed from: doScaleTransform */
    public void lambda$doTransform$20(float f) {
        int i2;
        MediaItem mediaItem = this.mModel.getMediaItem();
        ViewerBottomSheetScaleHelper viewerBottomSheetScaleHelper = this.mScaleHelper;
        PhotoView photoView = this.mPhotoView;
        if (mediaItem == null || !mediaItem.isVideo()) {
            i2 = 5;
        } else {
            i2 = 10;
        }
        viewerBottomSheetScaleHelper.setPhotoScale(photoView, f, i2);
        this.mScaleHelper.setVideoScale(this.mMediaView, this.mContainer, f);
    }

    /* access modifiers changed from: private */
    /* renamed from: doTransform */
    public void lambda$doTransformIfExpand$19(float f, boolean z) {
        ViewerBottomSheetMarginHelper viewerBottomSheetMarginHelper = this.mMarginHelper;
        DetailsView detailsView = this.mDetailsView;
        float f5 = f;
        viewerBottomSheetMarginHelper.applyBottomSheetTransform(detailsView, detailsView.getScrollView(), f5, this.mBehavior.getExpandedOffset(), this.mModel.getContainerModel().getSystemInsetsIgnoringVisibility());
        if (supportTargetViewTransform()) {
            this.mMarginHelper.applyTargetViewTransform(this.mContainer, this.mDetailsView.getScrollView(), f5, this.mModel.getGroupPanelBaseMargin());
            if (z) {
                ViewUtils.post(this.mContainer, new k(this, f5, 0));
            } else {
                lambda$doTransform$20(f5);
            }
        }
    }

    private void doTransformIfExpand(boolean z) {
        float f;
        if (BottomSheetState.Details.isExpanded(this.mModel.getContainerModel()) || BottomSheetState.Details.isExpanded(this.mModel)) {
            if (BottomSheetState.Details.isExpanded(this.mModel.getContainerModel())) {
                f = 1.0f;
            } else {
                f = 0.0f;
            }
            if (ThreadUtil.isMainThread()) {
                lambda$doTransformIfExpand$19(f, z);
            } else {
                this.mThread.runOnUiThread(new n(this, f, z));
            }
        }
    }

    /* access modifiers changed from: private */
    public Size getBitmapSize() {
        return new Size(this.mPhotoView.getSourceWidth(), this.mPhotoView.getSourceHeight());
    }

    /* access modifiers changed from: private */
    public Size getRootViewSize() {
        View view = this.mModel.getContainerModel().getView();
        if (view != null && view.getWidth() != 0 && view.getHeight() != 0) {
            return new Size(view.getWidth(), view.getHeight());
        }
        if (this.mViewerLayout.getWidth() == 0 || this.mViewerLayout.getHeight() == 0) {
            return DeviceInfo.getDisplaySize(this.mModel.getContext());
        }
        return new Size(this.mViewerLayout.getWidth(), this.mViewerLayout.getHeight());
    }

    /* access modifiers changed from: private */
    public Size getScaleBaseViewSize() {
        return new Size(getRootViewSize().getWidth(), (getRootViewSize().getHeight() - this.mModel.getGroupPanelBaseMargin().bottomMargin) - this.mModel.getGroupPanelBaseMargin().topMargin);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mContainer = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mPhotoView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$10(Object[] objArr) {
        doTransformIfExpand(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$11(Object[] objArr) {
        doTransformIfExpand(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$12(Object[] objArr) {
        doTransformIfExpand(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        this.mMediaView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$3(Object[] objArr) {
        this.mViewerLayout = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$4(Object[] objArr) {
        this.mDetailsView = objArr[0];
        setScrollListener();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$5(Object[] objArr) {
        DetailsBehavior detailsBehavior = objArr[0];
        this.mBehavior = detailsBehavior;
        detailsBehavior.setExpandedOffsetSupplier(new q(2, this));
        this.mBehavior.addBottomSheetCallback(this);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$6(Object[] objArr) {
        doTransformIfExpand(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$7(Object[] objArr) {
        doTransformIfExpand(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$8(Object[] objArr) {
        doTransformIfExpand(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$9(Object[] objArr) {
        doTransformIfExpand(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onInitialized$13() {
        return this.mModel.getStateHelper().supportDetailsLandscapeMode();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onInitialized$14() {
        return this.mModel.getStateHelper().isTargetViewHalfOfWidth(this.mModel.getStateHelper().supportDetailsLandscapeMode());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onInitialized$15() {
        return this.mModel.getContainerModel().isTableMode();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onInitialized$16() {
        return this.mModel.getStateHelper().supportDetailsLargeScreen();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Size lambda$onInitialized$17() {
        return this.mMarginHelper.getTargetSize();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setScrollListener$21(View view, int i2, int i7, int i8, int i10) {
        boolean z;
        if (this.mBehavior.isExpanded() && i10 != i7) {
            DetailsView detailsView = this.mDetailsView;
            if (i7 > i10) {
                z = true;
            } else {
                z = false;
            }
            onSlide(detailsView, 1.0f, z);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLayout$18() {
        doTransformIfExpand(true);
        this.mActionInvoker.invoke(ViewerAction.REQUEST_DETAILS_ITEMS_LAYOUT_UPDATE, new Object[0]);
    }

    private void setScrollListener() {
        this.mDetailsView.setScrollViewScrollChangeListener(new l(0, this));
    }

    private boolean supportTargetViewTransform() {
        ContainerModel containerModel = this.mModel.getContainerModel();
        if (getRootViewSize().getWidth() == 0 || getRootViewSize().getHeight() == 0 || containerModel.isTableMode() || containerModel.getStateHelper().supportGroupPanelLandscapeMode()) {
            return false;
        }
        return true;
    }

    private void updateLayout() {
        ViewUtils.post(this.mDetailsView, new H3.l(25, this));
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.CONTENT_CONTAINER, new j(this, 0));
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new j(this, 4));
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new j(this, 5));
        this.mActionInvoker.add(ViewerAction.VIEWER_LAYOUT, new j(this, 6));
        this.mActionInvoker.add(ViewerAction.DETAILS_VIEW, new j(this, 7));
        this.mActionInvoker.add(ViewerAction.DETAILS_BEHAVIOR, new j(this, 8));
        this.mActionInvoker.add(ViewerAction.BITMAP_LOADED, new j(this, 9));
        this.mActionInvoker.add(ViewerAction.LAST_PREVIEW_DATA, new j(this, 10));
        this.mActionInvoker.add(ViewerAction.PREVIEW_LOADED, new j(this, 11));
        this.mActionInvoker.add(ViewerAction.VIDEO_PREVIEW_BITMAP_CHANGED, new j(this, 12));
        this.mActionInvoker.add(ViewerAction.ALREADY_VIDEO_PREVIEW_LOADED, new j(this, 1));
        this.mActionInvoker.add(ViewerAction.VIDEO_PREPARED, new j(this, 2));
        this.mActionInvoker.add(ViewerAction.DETAILS_REFRESH_BEHAVIOR, new j(this, 3));
    }

    public void onApplyWindowInsets() {
        updateLayout();
    }

    public void onConfigurationChanged(Configuration configuration) {
        updateLayout();
    }

    public void onInitialized() {
        super.onInitialized();
        this.mMarginHelper = new ViewerBottomSheetMarginHelper(this.mModel.getContext(), new m(this, 0), new i(this, 0), new i(this, 1), new i(this, 2), new i(this, 3));
        this.mScaleHelper = new ViewerBottomSheetScaleHelper(new m(this, 1), new m(this, 2), new m(this, 3));
        this.mActionInvoker.invoke(ViewerAction.BOTTOM_SHEET_MARGIN_HELPER, this.mMarginHelper);
    }

    public void onSlide(View view, float f, boolean z) {
        float f5;
        lambda$doTransformIfExpand$19(f, false);
        if (f < 0.6f) {
            f5 = 0.0f;
        } else {
            f5 = (f - 0.6f) / 0.39999998f;
        }
        view.setAlpha(f5);
        this.mActionInvoker.invoke(ViewerAction.DETAILS_SLIDE, Float.valueOf(f), Float.valueOf(f), Float.valueOf(this.mMarginHelper.getTranslationY(this.mDetailsView.getScrollView())), Boolean.valueOf(z));
    }

    public void onTableModeChanged(boolean z, int i2) {
        updateLayout();
    }

    public void onViewAttached() {
        super.onViewAttached();
        doTransformIfExpand(true);
    }

    public void onViewConfirm() {
        Float valueOf = Float.valueOf(0.0f);
        super.onViewConfirm();
        if (!DetailsHandler.supportDetails(this.mModel.getMediaItem())) {
            this.mActionInvoker.invoke(ViewerAction.DETAILS_SLIDE, valueOf, valueOf, valueOf, Boolean.FALSE);
        }
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        if (BottomSheetState.Details.isExpanded(this.mModel.getContainerModel()) || BottomSheetState.Details.isExpanded(this.mModel)) {
            lambda$doTransformIfExpand$19(0.0f, true);
        }
    }
}
