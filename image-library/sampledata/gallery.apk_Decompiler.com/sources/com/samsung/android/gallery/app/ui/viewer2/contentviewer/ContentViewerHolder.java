package com.samsung.android.gallery.app.ui.viewer2.contentviewer;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.viewer2.common.OnViewLongPress;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.AbsViewerHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.debugger.ViewerPerformanceTracker;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.viewer.LastPreviewData;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.ViewerObjectThread;
import com.samsung.android.gallery.widget.abstraction.ViewerContentLayout;
import com.samsung.android.gallery.widget.databinding.ContentViewerLayoutBinding;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;
import com.samsung.android.gallery.widget.photoview.PhotoViewMotionControl;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Objects;
import n0.C0235b;
import o5.C0496a;
import t8.e;
import u7.C0521b;
import u7.C0522c;
import u7.C0523d;
import u7.C0524e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ContentViewerHolder extends AbsViewerHolder<ContentViewerLayoutBinding> implements PhotoViewMotionControl.OnMotionZoomListener {
    private boolean mActionFromExternal;
    private View mAudioEraserView;
    private ImageView mAudioIconView;
    private View mDlnaUiView;
    private View mDualShotOptionsView;
    private View mFlipCoverDecorLayout;
    private View mHighlightFrcView;
    private IMediaPlayerView mMediaPlayerView;
    private View mMotionPhotoViewMode;
    private View mObjectCaptureView;
    private final ArrayList<OnViewLongPress> mOnViewLongPress = new ArrayList<>();
    private View mQuickCroupView;
    private View mVideoControllerView;
    private View mVideoMirroringUiView;
    private final Runnable mZoomOutToggleRunnable = new C0521b(this, 0);

    public ContentViewerHolder(ContentViewerLayoutBinding contentViewerLayoutBinding, AbsViewerHolder.StateListener stateListener) {
        super(contentViewerLayoutBinding, stateListener);
    }

    private void fling(boolean z, View view) {
        if (ViewUtils.isShown(view)) {
            this.mActionInvoker.invoke(ViewerAction.REQUEST_FLING_MOVE, Boolean.valueOf(z));
        }
    }

    private void inflateMediaView() {
        if (this.mModel.getContainerModel().isFlipCoverGallery()) {
            ((ContentViewerLayoutBinding) this.mViewBinding).videoViewStub.setLayoutResource(R.layout.viewer_cover_media_view_stub_layout);
        } else {
            ((ContentViewerLayoutBinding) this.mViewBinding).videoViewStub.setLayoutResource(R.layout.viewer_media_view_stub_layout);
        }
        this.mMediaPlayerView = (IMediaPlayerView) ((ContentViewerLayoutBinding) this.mViewBinding).videoViewStub.inflate();
    }

    private boolean isToggleConsumed() {
        float lastTapX = ((ContentViewerLayoutBinding) this.mViewBinding).photoView.getLastTapX();
        float lastTapY = ((ContentViewerLayoutBinding) this.mViewBinding).photoView.getLastTapY();
        if (((ContentViewerLayoutBinding) this.mViewBinding).photoView.isToggleConsumable(lastTapX, lastTapY)) {
            return true;
        }
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView == null || !iMediaPlayerView.isToggleConsumable(lastTapX, lastTapY)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$8() {
        if (this.mModel.supportViewerZoomOsd() && this.mModel.isViewConfirmed() && !BlackboardUtils.isViewerShrink(this.mModel.getBlackboard())) {
            this.mActionInvoker.invoke(ViewerAction.TOGGLE_OSD, new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onImageLoaded$10(MediaItem mediaItem) {
        if (this.mModel.isPendingPhotoViewSet()) {
            Log.d(this.TAG, "onImageLoaded skip -> pending to set photo view while shared transition is working");
        } else {
            setPhotoView(mediaItem);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onInitialized$3(boolean z) {
        fling(z, ((ContentViewerLayoutBinding) this.mViewBinding).photoView);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onInitialized$4(MotionEvent motionEvent) {
        this.mActionInvoker.invoke(ViewerAction.SECONDARY_CLICK, motionEvent);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onInitialized$5() {
        if (isTranslated(((ContentViewerLayoutBinding) this.mViewBinding).contentContainer) || !BottomSheetState.isClosed(this.mModel)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onMediaPlayerViewStubRequest$0() {
        return !BottomSheetState.isClosed(this.mModel);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMediaPlayerViewStubRequest$1(boolean z) {
        fling(z, (View) this.mMediaPlayerView);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPreviewLoaded$9(Bitmap bitmap, MediaItem mediaItem) {
        setPhotoViewBmp(((ContentViewerLayoutBinding) this.mViewBinding).photoView, bitmap, mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onUpdateBitmapAnim$13(Object[] objArr) {
        Bitmap bitmap = objArr[0];
        if (bitmap != null && !bitmap.isRecycled()) {
            ((ContentViewerLayoutBinding) this.mViewBinding).photoView.updateAnim(bitmap);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onUpdatedRemasterStatus$2(String str) {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null) {
            updateItemPath(mediaItem, str);
            MediaData mediaData = this.mModel.getContainerModel().getMediaData();
            int findPositionByFileId = mediaData.findPositionByFileId(mediaItem.getFileId());
            if (findPositionByFileId < 0) {
                findPositionByFileId = this.mModel.getPosition();
            }
            MediaItem read = mediaData.read(findPositionByFileId);
            if (read != null && read.getFileId() == mediaItem.getFileId()) {
                updateItemPath(read, str);
            }
            resetScaleAndRegionDecodingInfo(new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPhotoView$11() {
        this.mModel.getBlackboard().publishIfEmpty("lifecycle://onQuickViewDone", Long.valueOf(System.currentTimeMillis()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPhotoView$12(MediaItem mediaItem) {
        if (mediaItem != null) {
            StringCompat stringCompat = this.TAG;
            Log.p(stringCompat, "onImageLoaded " + MediaItemUtil.getMediaLog(mediaItem));
            if (AbsViewerHolder.PRINT_DEBUG_VIEW && !mediaItem.isGif()) {
                setDebugText("onImageLoaded " + Logger.toString(this.mModel.getBitmap()));
            }
            ((ContentViewerLayoutBinding) this.mViewBinding).photoView.setImage(mediaItem, this.mModel.getBitmap());
            ViewerPerformanceTracker.setTime(ViewerPerformanceTracker.Offset.VF_HQ);
            ViewerPerformanceTracker.dump();
            if (this.mModel.getStateHelper().isQuickView() && this.mModel.getPosition() == 0) {
                ((ContentViewerLayoutBinding) this.mViewBinding).photoView.post(new C0521b(this, 1));
            }
        }
    }

    /* access modifiers changed from: private */
    public void onActionFromExternal(Object... objArr) {
        this.mActionFromExternal = objArr[0].booleanValue();
    }

    /* access modifiers changed from: private */
    public void onAudioEraserIconViewViewStubRequest(Object... objArr) {
        if (this.mAudioEraserView == null) {
            this.mAudioEraserView = ((ContentViewerLayoutBinding) this.mViewBinding).audioEraserIconStub.inflate();
        }
        this.mActionInvoker.invoke(ViewerAction.AUDIO_ERASER_ICON_VIEW, this.mAudioEraserView, ((ContentViewerLayoutBinding) this.mViewBinding).audioEraserIconStub);
    }

    /* access modifiers changed from: private */
    public void onAudioIconViewViewStubRequest(Object... objArr) {
        if (this.mAudioIconView == null) {
            this.mAudioIconView = (ImageView) ((ContentViewerLayoutBinding) this.mViewBinding).playAudioIconStub.inflate();
        }
        this.mActionInvoker.invoke(ViewerAction.AUDIO_ICON_VIEW, this.mAudioIconView, ((ContentViewerLayoutBinding) this.mViewBinding).playAudioIconStub);
    }

    /* access modifiers changed from: private */
    public boolean onContentViewTouch(MotionEvent motionEvent) {
        boolean z;
        this.mActionInvoker.invoke(ViewerAction.ON_CONTENT_VIEW_TOUCH, motionEvent);
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.onTouchEvent(motionEvent);
            z = this.mMediaPlayerView.handleTouchEvent(motionEvent);
        } else {
            z = false;
        }
        return ((ContentViewerLayoutBinding) this.mViewBinding).photoView.dispatchTouchEvent(motionEvent) | z;
    }

    /* access modifiers changed from: private */
    public void onDlnaButtonViewStubRequest(Object... objArr) {
        if (this.mDlnaUiView == null) {
            this.mDlnaUiView = ((ContentViewerLayoutBinding) this.mViewBinding).dlnaButton.inflate();
        }
        this.mActionInvoker.invoke(ViewerAction.DLNA_UI_VIEW, this.mDlnaUiView);
    }

    /* access modifiers changed from: private */
    public void onDualShotOptionsViewStubRequest(Object... objArr) {
        if (this.mDualShotOptionsView == null) {
            this.mDualShotOptionsView = ((ContentViewerLayoutBinding) this.mViewBinding).dualcaptureOptions.inflate();
        }
        this.mActionInvoker.invoke(ViewerAction.DUAL_SHOT_OPTIONS_VIEW, this.mDualShotOptionsView);
    }

    /* access modifiers changed from: private */
    public void onFlipCoverDecorLayoutViewStubRequest(Object... objArr) {
        if (this.mFlipCoverDecorLayout == null) {
            this.mFlipCoverDecorLayout = ((ContentViewerLayoutBinding) this.mViewBinding).flipCoverDecorLayout.inflate();
        }
        this.mActionInvoker.invoke(ViewerAction.FLIP_COVER_DECOR_LAYOUT, this.mFlipCoverDecorLayout);
    }

    /* access modifiers changed from: private */
    public void onHighlightFrcViewStubRequest(Object... objArr) {
        if (this.mHighlightFrcView == null) {
            this.mHighlightFrcView = ((ContentViewerLayoutBinding) this.mViewBinding).highlightFrcLayout.inflate();
        }
        this.mActionInvoker.invoke(ViewerAction.HIGHLIGHT_FRC_VIEW, this.mHighlightFrcView);
    }

    /* access modifiers changed from: private */
    public void onImageLoaded(Object... objArr) {
        MediaItem mediaItem = objArr[1];
        if (!objArr[2].booleanValue() || !((ContentViewerLayoutBinding) this.mViewBinding).photoView.isSameSource(mediaItem)) {
            this.mThread.runOnUiThread(new C0524e(this, mediaItem, 1));
        } else {
            Log.d(this.TAG, "onImageLoaded skip");
        }
    }

    /* access modifiers changed from: private */
    public void onLastPreviewData(Object... objArr) {
        Object obj;
        MediaItem mediaItem;
        LastPreviewData lastPreviewData = objArr[0];
        Bitmap bitmap = lastPreviewData.getBitmap();
        if (bitmap != null && !bitmap.isRecycled()) {
            Bitmap copy = bitmap.copy(bitmap.getConfig(), bitmap.isMutable());
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("onLastPreviewData from ");
            if (lastPreviewData.isPostCandidate()) {
                obj = lastPreviewData;
            } else {
                obj = Logger.toString(bitmap);
            }
            sb2.append(obj);
            sb2.append(" to ");
            sb2.append(copy);
            Log.d(stringCompat, sb2.toString());
            setPhotoViewBmp(((ContentViewerLayoutBinding) this.mViewBinding).photoView, copy, lastPreviewData.getMediaItem().clone());
            if (Features.isEnabled(Features.SUPPORT_PPP_BLENDING) && (mediaItem = this.mModel.getMediaItem()) != null && mediaItem.isMotionPhoto() && lastPreviewData.isPostCandidate()) {
                VIEW_BINDING view_binding = this.mViewBinding;
                ((ContentViewerLayoutBinding) view_binding).photoView.mImageProcessor.backupLastBitmap(((ContentViewerLayoutBinding) view_binding).photoView.mImageProcessor.getBitmap());
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x000b A[LOOP:0: B:3:0x000b->B:6:0x001b, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLongPressed(float r2, float r3) {
        /*
            r1 = this;
            boolean r0 = r1.mActionFromExternal
            if (r0 == 0) goto L_0x0005
            goto L_0x001d
        L_0x0005:
            java.util.ArrayList<com.samsung.android.gallery.app.ui.viewer2.common.OnViewLongPress> r1 = r1.mOnViewLongPress
            java.util.Iterator r1 = r1.iterator()
        L_0x000b:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x001d
            java.lang.Object r0 = r1.next()
            com.samsung.android.gallery.app.ui.viewer2.common.OnViewLongPress r0 = (com.samsung.android.gallery.app.ui.viewer2.common.OnViewLongPress) r0
            boolean r0 = r0.onViewLongPress(r2, r3)
            if (r0 == 0) goto L_0x000b
        L_0x001d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.viewer2.contentviewer.ContentViewerHolder.onLongPressed(float, float):void");
    }

    /* access modifiers changed from: private */
    public void onMediaPlayerViewStubRequest(Object... objArr) {
        if (this.mMediaPlayerView == null) {
            inflateMediaView();
            this.mMediaPlayerView.setTouchInteractionViewParent(((ContentViewerLayoutBinding) this.mViewBinding).contentContainer.getParent().getParent());
            this.mMediaPlayerView.setTranslationListener(new C0523d(this, 2));
            this.mMediaPlayerView.setOnFlingListener(new C0523d(this, 3));
            this.mMediaPlayerView.setScaleEndListener(new C0523d(this, 4));
        }
        this.mActionInvoker.invoke(ViewerAction.MEDIA_VIEW, this.mMediaPlayerView);
    }

    /* access modifiers changed from: private */
    public void onMotionPhotoViewModeViewStubRequest(Object... objArr) {
        if (this.mMotionPhotoViewMode == null) {
            this.mMotionPhotoViewMode = ((ContentViewerLayoutBinding) this.mViewBinding).motionPhotoViewMode.inflate();
        }
        this.mActionInvoker.invoke(ViewerAction.MOTION_PHOTO_VIEW_MODE_VIEW, this.mMotionPhotoViewMode);
    }

    /* access modifiers changed from: private */
    public void onObjectCaptureViewStubRequest(Object... objArr) {
        if (this.mObjectCaptureView == null) {
            this.mObjectCaptureView = ((ContentViewerLayoutBinding) this.mViewBinding).objectCaptureLayout.inflate();
        }
        this.mActionInvoker.invoke(ViewerAction.OBJECT_CAPTURE_VIEW, this.mObjectCaptureView);
    }

    /* access modifiers changed from: private */
    public void onPhotoViewMatrixChanged(boolean z) {
        this.mActionInvoker.invoke(ViewerAction.PHOTOVIEW_MATRIX_CHANGED, Boolean.valueOf(z));
    }

    /* access modifiers changed from: private */
    public void onPreviewLoaded(Object... objArr) {
        boolean z = false;
        Bitmap bitmap = objArr[0];
        MediaItem mediaItem = objArr[1];
        Log.p(this.TAG, "onPreviewLoaded");
        if (mediaItem != null && mediaItem.isVideo()) {
            Bitmap bitmap2 = ((ContentViewerLayoutBinding) this.mViewBinding).photoView.getBitmap();
            if (!(bitmap2 == null || bitmap == null || bitmap2.getWidth() <= bitmap.getWidth())) {
                z = true;
            }
            if (((ContentViewerLayoutBinding) this.mViewBinding).photoView.hasSource() && !z) {
                Log.w(this.TAG, "onPreviewLoaded: photo view has wrong source in video");
                ViewerObjectThread viewerObjectThread = this.mThread;
                PhotoViewCompat photoViewCompat = ((ContentViewerLayoutBinding) this.mViewBinding).photoView;
                Objects.requireNonNull(photoViewCompat);
                viewerObjectThread.runOnUiThread(new e(5, photoViewCompat));
            }
            ((ContentViewerLayoutBinding) this.mViewBinding).photoView.setMaxScale(10.0f);
            if (z) {
                this.mModel.setBitmap(bitmap2, mediaItem);
                Log.d(this.TAG, "onPreviewLoaded skip" + Logger.v(bitmap2, bitmap));
                return;
            }
        }
        this.mThread.runOnUiThread(new C0235b(this, bitmap, mediaItem, 27));
    }

    /* access modifiers changed from: private */
    public void onQuickCropViewStubRequest(Object... objArr) {
        if (this.mQuickCroupView == null) {
            this.mQuickCroupView = ((ContentViewerLayoutBinding) this.mViewBinding).quickCropStub.inflate();
        }
        this.mActionInvoker.invoke(ViewerAction.QUICK_CROP_VIEW, this.mQuickCroupView, ((ContentViewerLayoutBinding) this.mViewBinding).quickCropStub);
    }

    /* access modifiers changed from: private */
    public void onReturnTransitionEnd(Object... objArr) {
        boolean isPendingPhotoViewSet = this.mModel.isPendingPhotoViewSet();
        this.mModel.setPendingPhotoViewSet(false);
        if (!this.mModel.isPlaying()) {
            if (isPendingPhotoViewSet) {
                setPhotoView();
            } else {
                ViewUtils.setVisibility(((ContentViewerLayoutBinding) this.mViewBinding).photoView, 0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void onReturnTransitionStart(Object... objArr) {
        if (this.mModel.isPendingPhotoViewSet()) {
            ViewUtils.setVisibility(((ContentViewerLayoutBinding) this.mViewBinding).photoView, 8);
        }
    }

    /* access modifiers changed from: private */
    public void onSetZoomEnabled(Object... objArr) {
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.setZoomEnabled(objArr[0].booleanValue());
    }

    /* access modifiers changed from: private */
    public void onUpdateBitmapAnim(Object... objArr) {
        this.mThread.runOnUiThread(new q6.e(16, this, objArr));
    }

    /* access modifiers changed from: private */
    public void onUpdateScaleRelative(Object... objArr) {
        float floatValue = objArr[0].floatValue();
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.setScaleRelative(floatValue);
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.setScaleRelative(floatValue);
        }
    }

    private void onUpdatedRemasterStatus(String str) {
        SimpleThreadPool.getInstance().execute(new q6.e(17, this, str));
    }

    /* access modifiers changed from: private */
    public void onVideoControllerViewStubRequest(Object... objArr) {
        if (this.mVideoControllerView == null) {
            if (!Features.isEnabled(Features.SUPPORT_FLIP_COVER_GALLERY) || !this.mModel.getContainerModel().isFlipCoverGallery()) {
                this.mVideoControllerView = ((ContentViewerLayoutBinding) this.mViewBinding).videoController.inflate();
            } else {
                this.mVideoControllerView = ViewUtils.inflateViewStub(this.mFlipCoverDecorLayout.findViewById(R.id.flip_cover_video_controller));
            }
        }
        this.mActionInvoker.invoke(ViewerAction.VIDEO_CONTROLLER_VIEW, this.mVideoControllerView);
    }

    /* access modifiers changed from: private */
    public void onVideoMirroringUiView(Object... objArr) {
        if (this.mVideoMirroringUiView == null) {
            this.mVideoMirroringUiView = ((ContentViewerLayoutBinding) this.mViewBinding).videoMirroringUi.inflate();
        }
        this.mActionInvoker.invoke(ViewerAction.VIDEO_MIRRORING_VIEW, this.mVideoMirroringUiView);
    }

    /* access modifiers changed from: private */
    public void onVideoScaleEnd() {
        this.mActionInvoker.invoke(ViewerAction.VIDEO_SCALE_END, new Object[0]);
    }

    /* access modifiers changed from: private */
    public void onVideoSeekControllerViewStubRequest(Object... objArr) {
        if (this.mVideoControllerView == null) {
            this.mVideoControllerView = ((ContentViewerLayoutBinding) this.mViewBinding).videoSeekController.inflate();
        }
        this.mActionInvoker.invoke(ViewerAction.VIDEO_SEEK_CONTROLLER_VIEW, this.mVideoControllerView);
    }

    /* access modifiers changed from: private */
    public void onZoomDetected(boolean z) {
        boolean isOsdVisible = this.mModel.getContainerModel().isOsdVisible();
        if (z) {
            if (BottomSheetState.Details.isClosed(this.mModel)) {
                this.mActionInvoker.invoke(ViewerAction.IMAGE_ZOOM, Boolean.TRUE);
            }
            if (this.mModel.supportViewerZoomOsd()) {
                ((ContentViewerLayoutBinding) this.mViewBinding).photoView.removeCallbacks(this.mZoomOutToggleRunnable);
                return;
            }
            return;
        }
        this.mActionInvoker.invoke(ViewerAction.IMAGE_ZOOM, Boolean.FALSE);
        if (this.mModel.supportViewerZoomOsd()) {
            ((ContentViewerLayoutBinding) this.mViewBinding).photoView.removeCallbacks(this.mZoomOutToggleRunnable);
            if (!isOsdVisible && this.mModel.isViewConfirmed() && !BlackboardUtils.isViewerShrink(this.mModel.getBlackboard())) {
                ((ContentViewerLayoutBinding) this.mViewBinding).photoView.postDelayed(this.mZoomOutToggleRunnable, 300);
            }
        }
    }

    /* access modifiers changed from: private */
    public void onZoomToMinScale(Object... objArr) {
        PhotoViewMotionControl motionControl = ((ContentViewerLayoutBinding) this.mViewBinding).photoView.getMotionControl();
        if (motionControl != null) {
            ThreadUtil.postOnUiThread(new e(6, motionControl));
        }
    }

    /* access modifiers changed from: private */
    public void regViewLongPressListener(Object... objArr) {
        OnViewLongPress onViewLongPress = objArr[0];
        int intValue = objArr[1].intValue();
        if (intValue > this.mOnViewLongPress.size()) {
            intValue = this.mOnViewLongPress.size();
        }
        this.mOnViewLongPress.add(intValue, onViewLongPress);
    }

    /* access modifiers changed from: private */
    public void resetScaleAndRegionDecodingInfo(Object... objArr) {
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.resetScaleAndCenter();
        if (((ContentViewerLayoutBinding) this.mViewBinding).photoView.mImageProcessor.isRunRegionDecoding()) {
            ((ContentViewerLayoutBinding) this.mViewBinding).photoView.resetRegionDecodingInfo();
        }
    }

    private void setPhotoView(MediaItem mediaItem) {
        this.mThread.runOnUiThread(new C0524e(this, mediaItem, 0));
    }

    /* access modifiers changed from: private */
    public void tryRegionDecoding(Object... objArr) {
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.retryRegionDecoding();
    }

    private void updateItemPath(MediaItem mediaItem, String str) {
        if (mediaItem != null && !TextUtils.isEmpty(str)) {
            mediaItem.setPath(str);
        }
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW_INFLATE, new C0522c(this, 0));
        this.mActionInvoker.add(ViewerAction.VIDEO_CONTROLLER_VIEW_INFLATE, new C0522c(this, 11));
        this.mActionInvoker.add(ViewerAction.VIDEO_SEEK_CONTROLLER_VIEW_INFLATE, new C0522c(this, 18));
        this.mActionInvoker.add(ViewerAction.AUDIO_ICON_VIEW_INFLATE, new C0522c(this, 19));
        this.mActionInvoker.add(ViewerAction.AUDIO_ERASER_ICON_VIEW_INFLATE, new C0522c(this, 20));
        this.mActionInvoker.add(ViewerAction.QUICK_CROP_VIEW_INFLATE, new C0522c(this, 21));
        this.mActionInvoker.add(ViewerAction.FLIP_COVER_DECOR_LAYOUT_INFLATE, new C0522c(this, 22));
        this.mActionInvoker.add(ViewerAction.DUAL_SHOT_OPTIONS_VIEW_INFLATE, new C0522c(this, 23));
        this.mActionInvoker.add(ViewerAction.OBJECT_CAPTURE_VIEW_INFLATE, new C0522c(this, 24));
        this.mActionInvoker.add(ViewerAction.HIGHLIGHT_FRC_VIEW_INFLATE, new C0522c(this, 25));
        this.mActionInvoker.add(ViewerAction.PREVIEW_LOADED, new C0522c(this, 1));
        this.mActionInvoker.add(ViewerAction.BITMAP_LOADED, new C0522c(this, 2));
        this.mActionInvoker.add(ViewerAction.UPDATE_BITMAP_ANIM, new C0522c(this, 3));
        this.mActionInvoker.add(ViewerAction.LAST_PREVIEW_DATA, new C0522c(this, 4));
        this.mActionInvoker.add(ViewerAction.RESET_SCALE_AND_REGION_DECODING, new C0522c(this, 5));
        this.mActionInvoker.add(ViewerAction.DLNA_UI_INFLATE, new C0522c(this, 6));
        this.mActionInvoker.add(ViewerAction.VIDEO_MIRRORING_UI_INFLATE, new C0522c(this, 7));
        this.mActionInvoker.add(ViewerAction.RETURN_TRANSITION_START, new C0522c(this, 8));
        this.mActionInvoker.add(ViewerAction.RETURN_TRANSITION_END, new C0522c(this, 9));
        this.mActionInvoker.add(ViewerAction.UPDATE_SCALE_RELATIVE, new C0522c(this, 10));
        this.mActionInvoker.add(ViewerAction.SET_ZOOM_ENABLED, new C0522c(this, 12));
        this.mActionInvoker.add(ViewerAction.ZOOM_TO_MIN_SCALE, new C0522c(this, 13));
        this.mActionInvoker.add(ViewerAction.REG_VIEW_LONG_PRESS_LISTENER, new C0522c(this, 14));
        this.mActionInvoker.add(ViewerAction.WEBP_SET_DONE, new C0522c(this, 15));
        this.mActionInvoker.add(ViewerAction.ACTION_FROM_EXTERNAL, new C0522c(this, 16));
        if (PreferenceFeatures.OneUi7x.SUPPORT_MOTION_PHOTO_VIEW_MODE) {
            this.mActionInvoker.add(ViewerAction.MOTION_PHOTO_VIEW_MODE_VIEW_INFLATE, new C0522c(this, 17));
        }
    }

    public View getTransitionView() {
        return ((ContentViewerLayoutBinding) this.mViewBinding).photoView;
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 3043) {
            resetScaleAndRegionDecodingInfo(new Object[0]);
            return true;
        } else if (i2 != 3044) {
            return false;
        } else {
            onUpdatedRemasterStatus((String) eventMessage.obj);
            this.mActionInvoker.invoke(ViewerAction.UPDATE_REMASTER_STATUS, eventMessage);
            return true;
        }
    }

    public void initialize() {
        this.mActionInvoker.invoke(ViewerAction.DECOR_LAYOUT, ((ContentViewerLayoutBinding) this.mViewBinding).decorLayout);
        this.mActionInvoker.invoke(ViewerAction.VIEWER_LAYOUT, ((ContentViewerLayoutBinding) this.mViewBinding).viewerLayout);
        this.mActionInvoker.invoke(ViewerAction.CONTENT_CONTAINER_FOR_TOUCH, ((ContentViewerLayoutBinding) this.mViewBinding).contentContainerForTouch);
        this.mActionInvoker.invoke(ViewerAction.IMAGE_PHOTO_VIEW, ((ContentViewerLayoutBinding) this.mViewBinding).photoView);
        this.mActionInvoker.invoke(ViewerAction.CONTENT_CONTAINER, ((ContentViewerLayoutBinding) this.mViewBinding).contentContainer);
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        updateViewLogTagging(this.mModel.getPosition() + ">" + i2);
    }

    public boolean isTranslated(View view) {
        if (view == null || view.getTranslationY() == 0.0f) {
            return false;
        }
        return true;
    }

    public boolean isZoomEnabled() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem == null || mediaItem.isBroken()) {
            return false;
        }
        return true;
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        updateViewLogTagging(String.valueOf(i2));
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.setZoomEnabled(isZoomEnabled());
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.setAliveZoomEnabled(!this.mModel.getContainerModel().isFlipCoverGallery());
        this.mModel.setMotionControl(((ContentViewerLayoutBinding) this.mViewBinding).photoView.getMotionControl());
        if (((ContentViewerLayoutBinding) this.mViewBinding).photoView.getBitmap() != null) {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "onBind - ViewHolder has bitmap." + Logger.toSimpleString(((ContentViewerLayoutBinding) this.mViewBinding).photoView.getBitmap()));
        }
        backupPostProcessingScale(mediaItem);
    }

    public void onDoubleTapZoomStateChanged(boolean z) {
        boolean isOsdVisible = this.mModel.getContainerModel().isOsdVisible();
        if (this.mModel.isViewConfirmed()) {
            this.mActionInvoker.invoke(ViewerAction.UPDATE_BACKGROUND_COLOR, Boolean.valueOf(!z));
        }
        if ((!z && !isOsdVisible) || (z && isOsdVisible)) {
            this.mActionInvoker.invoke(ViewerAction.TOGGLE_OSD, new Object[0]);
        }
    }

    public void onFinalized() {
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.clearListeners();
        ((ContentViewerLayoutBinding) this.mViewBinding).contentContainerForTouch.setInterceptTouchEventListener((ViewerContentLayout.InterceptTouchEventListener) null);
        ((ContentViewerLayoutBinding) this.mViewBinding).contentContainerForTouch.setOnClickListener((View.OnClickListener) null);
    }

    public void onInitialized() {
        if (this.mModel.supportViewerZoomOsd()) {
            ((ContentViewerLayoutBinding) this.mViewBinding).photoView.setMotionZoomListener(this);
        }
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.setZoomDetectListener(new C0523d(this, 0));
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.setOnFlingListener(new C0523d(this, 1));
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.setOnClickListener(new C0496a(22, this));
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.setOnSecondaryClickListener(new C0523d(this, 5));
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.setOnLongPressListener(new C0523d(this, 6));
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.setTranslationListener(new C0523d(this, 7));
        ContentViewerLayoutBinding contentViewerLayoutBinding = (ContentViewerLayoutBinding) this.mViewBinding;
        contentViewerLayoutBinding.photoView.setProxyViewLayoutParams((ViewGroup.MarginLayoutParams) contentViewerLayoutBinding.contentContainer.getLayoutParams());
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.setOnMatrixChangeListener(new C0523d(this, 8));
        ((ContentViewerLayoutBinding) this.mViewBinding).contentContainerForTouch.setInterceptTouchEventListener(new C0523d(this, 9));
        ((ContentViewerLayoutBinding) this.mViewBinding).contentContainerForTouch.setOnClickListener(new C0496a(22, this));
    }

    public void onPause() {
        super.onPause();
        onSetZoomEnabled(Boolean.FALSE);
    }

    public void onPinchZoomStateChanged(boolean z) {
        boolean isOsdVisible = this.mModel.getContainerModel().isOsdVisible();
        if ((z && isOsdVisible) || (!z && isOsdVisible && ((ContentViewerLayoutBinding) this.mViewBinding).photoView.isZoomed())) {
            this.mActionInvoker.invoke(ViewerAction.TOGGLE_OSD, new Object[0]);
        }
    }

    public void onResume() {
        super.onResume();
        onSetZoomEnabled(Boolean.TRUE);
    }

    public void onViewAttached() {
        super.onViewAttached();
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (((ContentViewerLayoutBinding) this.mViewBinding).photoView.isZoomed() && BottomSheetState.Details.isExpanded(this.mModel) && mediaItem != null && mediaItem.isPanoramic()) {
            tryRegionDecoding(new Object[0]);
        }
    }

    public void onViewDetached() {
        super.onViewDetached();
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.removeCallbacks(this.mZoomOutToggleRunnable);
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.blockPendingScale(false);
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.resetScaleAndCenter();
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.resetRegionDecodingInfo();
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.release();
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.setVisibility(0);
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.recycle();
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.resetAccessibility();
        }
    }

    public void toggleOsd(View view) {
        if (this.mModel.isFragmentResumed() && !isToggleConsumed() && !BottomSheetState.isInTransition(this.mModel) && !this.mModel.isVideoSpeedChangeOnGoing()) {
            super.toggleOsd(view);
        }
    }

    public void updateViewLogTagging(String str) {
        ((ContentViewerLayoutBinding) this.mViewBinding).photoView.setLogTag(str);
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.setLogTag(str);
        }
    }

    private void setPhotoView() {
        setPhotoView(this.mModel.getMediaItem());
    }

    private void backupPostProcessingScale(MediaItem mediaItem) {
    }
}
