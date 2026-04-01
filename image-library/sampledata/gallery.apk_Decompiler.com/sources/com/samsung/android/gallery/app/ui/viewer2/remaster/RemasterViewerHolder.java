package com.samsung.android.gallery.app.ui.viewer2.remaster;

import Nb.c;
import O3.b;
import S7.g;
import S7.h;
import S7.i;
import S7.j;
import S7.k;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.Size;
import android.view.MotionEvent;
import android.view.View;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.AbsViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SerialExecutor;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.abstraction.ViewerContentLayout;
import com.samsung.android.gallery.widget.databinding.RemasterViewerLayoutBinding;
import com.samsung.android.gallery.widget.photoview.OnViewerExitGestureListener;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterViewerHolder extends AbsViewerHolder<RemasterViewerLayoutBinding> {
    public RemasterViewerHolder(RemasterViewerLayoutBinding remasterViewerLayoutBinding, AbsViewerHolder.StateListener stateListener) {
        super(remasterViewerLayoutBinding, stateListener);
    }

    private void checkAndUpdateSize(MediaItem mediaItem, MediaItem mediaItem2) {
        boolean z;
        String path = mediaItem.getPath();
        if (path == null || !path.equals(mediaItem2.getPath())) {
            z = false;
        } else {
            z = true;
        }
        Size size = new Size(mediaItem.getWidth(), mediaItem.getHeight());
        Size size2 = new Size(mediaItem2.getWidth(), mediaItem2.getHeight());
        if (z && !size.equals(size2)) {
            mediaItem.setSize(size2.getWidth(), size2.getHeight());
        }
    }

    private MediaItem createOriginMediaItem(MediaItem mediaItem) {
        MediaItem originMediaItem = this.mModel.getOriginMediaItem();
        if (originMediaItem != null) {
            return originMediaItem;
        }
        String originPath = MediaItemSuggest.getOriginPath(mediaItem);
        if (!mediaItem.isRevitalization() || TextUtils.isEmpty(originPath)) {
            return originMediaItem;
        }
        MediaItem mediaItem2 = new MediaItem();
        mediaItem2.cloneBasicInfo(mediaItem);
        mediaItem2.setPath(originPath);
        mediaItem2.setCropRect(mediaItem.getCropRect());
        mediaItem2.setSize(mediaItem.getWidthInDB(), mediaItem.getHeightInDB());
        mediaItem2.setSefFileType(mediaItem.getSefFileType(), mediaItem.getSefFileSubType());
        return mediaItem2;
    }

    private Size getAltRemasterSize(MediaItem mediaItem) {
        MediaItem originMediaItem;
        BitmapOptions parse = BitmapOptionsFactory.parse(mediaItem.getPath());
        Size size = new Size(parse.outWidth, parse.outHeight);
        if (isInvalidSize(size) && (originMediaItem = this.mModel.getOriginMediaItem()) != null) {
            Log.w((CharSequence) this.TAG, "failed to get size from bitmap: ", size, new Size(originMediaItem.getWidth(), originMediaItem.getHeight()));
        }
        return size;
    }

    private String getDefaultContentDescription() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem == null) {
            return "";
        }
        return TimeUtil.getLocalizedDateTime(mediaItem.getDateTaken()) + ArcCommonLog.TAG_COMMA + mediaItem.getMimeType();
    }

    private void init() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem == null) {
            return;
        }
        if (LocationKey.isRemasterPictures(this.mModel.getContainerModel().getLocationKey())) {
            this.mModel.setRemasteredMediaItem(mediaItem);
            this.mModel.setOriginMediaItem(createOriginMediaItem(mediaItem));
            updateSize(new Object[0]);
            return;
        }
        this.mModel.setOriginMediaItem(mediaItem);
    }

    private boolean isDifferentBitmap(PhotoView photoView, Bitmap bitmap) {
        if (photoView.getBitmap() != bitmap) {
            return true;
        }
        return false;
    }

    private static boolean isInvalidSize(Size size) {
        if (size == null || size.getWidth() <= 0 || size.getHeight() <= 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBitmapLoaded$7() {
        this.mActionInvoker.invoke(ViewerAction.SET_REMASTER_IMAGE, new Object[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBitmapLoaded$8() {
        this.mActionInvoker.invoke(ViewerAction.ON_READY_REMASTER_VIEW, new Object[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBitmapLoaded$9(Object[] objArr) {
        Bitmap bitmap = objArr[0];
        MediaItem mediaItem = objArr[1];
        if (MediaItemUtil.equals(this.mModel.getRemasteredMediaItem(), mediaItem)) {
            if (isDifferentBitmap(((RemasterViewerLayoutBinding) this.mViewBinding).remasterPhotoView, bitmap)) {
                setImage(((RemasterViewerLayoutBinding) this.mViewBinding).remasterPhotoView, mediaItem, bitmap);
                ViewUtils.post(((RemasterViewerLayoutBinding) this.mViewBinding).remasterPhotoView, new i(this, 1));
            }
        } else if (isDifferentBitmap(((RemasterViewerLayoutBinding) this.mViewBinding).photoView, bitmap)) {
            setImage(((RemasterViewerLayoutBinding) this.mViewBinding).photoView, mediaItem, bitmap);
            if (LocationKey.isRemasterPictures(this.mModel.getContainerModel().getLocationKey())) {
                ViewUtils.post(((RemasterViewerLayoutBinding) this.mViewBinding).photoView, new i(this, 2));
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onInitialized$0(Float f) {
        if (!((RemasterViewerLayoutBinding) this.mViewBinding).photoView.isMinScale() && !((RemasterViewerLayoutBinding) this.mViewBinding).photoView.isMaxScale()) {
            this.mActionInvoker.invoke(ViewerAction.REMASTER_ON_SCALE_CHANGED, new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onInitialized$1(boolean z) {
        this.mActionInvoker.invoke(ViewerAction.REQUEST_FLING_MOVE, Boolean.valueOf(z));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onUpdateBitmapPair$5(Object[] objArr) {
        Bitmap bitmap = objArr[0];
        if (bitmap != null && !bitmap.isRecycled()) {
            ((RemasterViewerLayoutBinding) this.mViewBinding).photoView.setImage(bitmap, bitmap.getWidth(), bitmap.getHeight());
        }
        Bitmap bitmap2 = objArr[1];
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            ((RemasterViewerLayoutBinding) this.mViewBinding).remasterPhotoView.setImage(bitmap2, bitmap2.getWidth(), bitmap2.getHeight());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setPhotoView$6(PhotoViewCompat photoViewCompat) {
        if (isTranslated(photoViewCompat)) {
            return true;
        }
        ContentModel contentModel = this.mModel;
        if (contentModel == null || !BottomSheetState.isExpanded(contentModel)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateSize$4() {
        updateOriginalPhotoViewMaxScale(((RemasterViewerLayoutBinding) this.mViewBinding).photoView);
    }

    /* access modifiers changed from: private */
    public void onBitmapLoaded(Object... objArr) {
        this.mThread.runOnUiThread(new k(this, objArr, 1));
    }

    /* access modifiers changed from: private */
    public boolean onContentViewTouch(MotionEvent motionEvent) {
        boolean dispatchTouchEvent = ((RemasterViewerLayoutBinding) this.mViewBinding).photoView.dispatchTouchEvent(motionEvent);
        boolean dispatchTouchEvent2 = ((RemasterViewerLayoutBinding) this.mViewBinding).remasterPhotoView.dispatchTouchEvent(motionEvent);
        if (dispatchTouchEvent || dispatchTouchEvent2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void onPreviewLoaded(Object... objArr) {
        Log.d(this.TAG, "onPreviewLoaded", Integer.valueOf(this.mModel.getPosition()));
        setPhotoViewBmp(((RemasterViewerLayoutBinding) this.mViewBinding).photoView, objArr[0], objArr[1]);
    }

    /* access modifiers changed from: private */
    public void onReadyRemasterView(Object... objArr) {
        ((RemasterViewerLayoutBinding) this.mViewBinding).contentContainer.setContentDescription(getDefaultContentDescription());
    }

    /* access modifiers changed from: private */
    public void onUpdateBitmapPair(Object... objArr) {
        this.mThread.runOnUiThread(new k(this, objArr, 0));
    }

    /* access modifiers changed from: private */
    public void onUpdateScaleRelative(Object... objArr) {
        float floatValue = objArr[0].floatValue();
        ((RemasterViewerLayoutBinding) this.mViewBinding).photoView.setScaleRelative(floatValue);
        ((RemasterViewerLayoutBinding) this.mViewBinding).remasterPhotoView.setScaleRelative(floatValue);
    }

    /* access modifiers changed from: private */
    public void onZoomDetected(boolean z) {
        this.mActionInvoker.invoke(ViewerAction.IMAGE_ZOOM, Boolean.valueOf(z));
    }

    private void resetPhotoView(PhotoViewCompat photoViewCompat) {
        photoViewCompat.resetScaleAndCenter();
        photoViewCompat.resetRegionDecodingInfo();
        photoViewCompat.release();
    }

    /* access modifiers changed from: private */
    public void resetScale(Object... objArr) {
        ((RemasterViewerLayoutBinding) this.mViewBinding).photoView.forceResetScaleAndCenter();
        ((RemasterViewerLayoutBinding) this.mViewBinding).remasterPhotoView.forceResetScaleAndCenter();
    }

    private void setImage(PhotoView photoView, MediaItem mediaItem, Bitmap bitmap) {
        photoView.setImage(mediaItem, bitmap);
        ViewUtils.post(photoView, new c(photoView, 2));
    }

    /* access modifiers changed from: private */
    public void unblockMotionControl(Object... objArr) {
        ((RemasterViewerLayoutBinding) this.mViewBinding).photoView.blockMotionControl(false);
        ((RemasterViewerLayoutBinding) this.mViewBinding).photoView.setSupportCustomCropRect(false);
        ((RemasterViewerLayoutBinding) this.mViewBinding).remasterPhotoView.blockMotionControl(false);
        ((RemasterViewerLayoutBinding) this.mViewBinding).remasterPhotoView.setSupportCustomCropRect(false);
    }

    private void updateOriginalPhotoViewMaxScale(PhotoView photoView) {
        MediaItem originMediaItem = this.mModel.getOriginMediaItem();
        MediaItem remasteredMediaItem = this.mModel.getRemasteredMediaItem();
        if (originMediaItem == null || remasteredMediaItem == null) {
            Log.w(this.TAG, "updateOriginalPhotoViewMaxScale failed");
            return;
        }
        float width = (((float) remasteredMediaItem.getWidth()) / ((float) originMediaItem.getWidth())) * 5.0f;
        photoView.setMaxScale(width);
        Log.i(this.TAG, "update max scale ", Float.valueOf(width));
    }

    /* access modifiers changed from: private */
    /* renamed from: updateRemasterItemSize */
    public void lambda$updateSize$3(Size size) {
        MediaItem remasteredMediaItem = this.mModel.getRemasteredMediaItem();
        if (remasteredMediaItem == null) {
            Log.w(this.TAG, "updateRemasterItemSize failed, remasteredItem is null");
            return;
        }
        if (isInvalidSize(size)) {
            size = getAltRemasterSize(remasteredMediaItem);
        }
        Log.i(this.TAG, "update remaster size ", size);
        remasteredMediaItem.setSize(size.getWidth(), size.getHeight());
    }

    /* access modifiers changed from: private */
    public void updateSize(Object... objArr) {
        Size remasterSize = MediaItemSuggest.getRemasterSize(this.mModel.getMediaItem());
        SerialExecutor serialExecutor = new SerialExecutor();
        if (isInvalidSize(remasterSize)) {
            serialExecutor.runOnBg((Runnable) new h(this, remasterSize, 0));
        } else {
            serialExecutor.runOnUi((Runnable) new h(this, remasterSize, 1));
        }
        serialExecutor.runOnUi((Runnable) new i(this, 0)).exec();
    }

    /* access modifiers changed from: private */
    public void zoomWithRect(Object... objArr) {
        RectF rectF = objArr[0];
        zoomWithRectToPhotoView(rectF, ((RemasterViewerLayoutBinding) this.mViewBinding).photoView);
        zoomWithRectToPhotoView(rectF, ((RemasterViewerLayoutBinding) this.mViewBinding).remasterPhotoView);
    }

    private void zoomWithRectToPhotoView(RectF rectF, PhotoView photoView) {
        int sourceWidth = photoView.getSourceWidth();
        int sourceHeight = photoView.getSourceHeight();
        float f = (float) sourceWidth;
        float width = ((float) photoView.getWidth()) / (Math.abs(rectF.width()) * f);
        float f5 = (float) sourceHeight;
        float height = ((float) photoView.getHeight()) / (Math.abs(rectF.height()) * f5);
        photoView.getMotionControl().zoom(Math.min(width, height), new PointF(rectF.centerX() * f, rectF.centerY() * f5));
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.PREVIEW_LOADED, new g(this, 0));
        this.mActionInvoker.add(ViewerAction.BITMAP_LOADED, new g(this, 1));
        this.mActionInvoker.add(ViewerAction.ON_DEMAND_REMASTERED, new g(this, 2));
        this.mActionInvoker.add(ViewerAction.ON_HANDLER_MOVING_ANIMATION_ENDED, new g(this, 3));
        this.mActionInvoker.add(ViewerAction.RESET_SCALE_AND_REGION_DECODING, new g(this, 4));
        this.mActionInvoker.add(ViewerAction.UPDATE_REMASTER_GIF_BITMAP, new g(this, 5));
        this.mActionInvoker.add(ViewerAction.UPDATE_SCALE_RELATIVE, new g(this, 6));
        this.mActionInvoker.add(ViewerAction.ZOOM_WITH_TARGET_RECT, new g(this, 7));
        this.mActionInvoker.add(ViewerAction.ON_READY_REMASTER_VIEW, new g(this, 8));
    }

    public void initialize() {
        this.mActionInvoker.invoke(ViewerAction.DECOR_LAYOUT, ((RemasterViewerLayoutBinding) this.mViewBinding).decorLayout);
        this.mActionInvoker.invoke(ViewerAction.VIEWER_LAYOUT, ((RemasterViewerLayoutBinding) this.mViewBinding).viewerLayout);
        this.mActionInvoker.invoke(ViewerAction.IMAGE_PHOTO_VIEW, ((RemasterViewerLayoutBinding) this.mViewBinding).photoView);
        this.mActionInvoker.invoke(ViewerAction.REMASTER_PHOTO_VIEW, ((RemasterViewerLayoutBinding) this.mViewBinding).remasterPhotoView);
        this.mActionInvoker.invoke(ViewerAction.CONTENT_CONTAINER, ((RemasterViewerLayoutBinding) this.mViewBinding).contentContainer);
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        MediaItem remasteredMediaItem = this.mModel.getRemasteredMediaItem();
        if (remasteredMediaItem != null) {
            checkAndUpdateSize(mediaItem, remasteredMediaItem);
        }
    }

    public boolean isTranslated(View view) {
        if (view == null || view.getTranslationY() == 0.0f) {
            return false;
        }
        return true;
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        setPhotoView(((RemasterViewerLayoutBinding) this.mViewBinding).photoView);
        setPhotoView(((RemasterViewerLayoutBinding) this.mViewBinding).remasterPhotoView);
        init();
    }

    public void onFinalized() {
        ((RemasterViewerLayoutBinding) this.mViewBinding).photoView.clearListeners();
        ((RemasterViewerLayoutBinding) this.mViewBinding).contentContainerForTouch.setInterceptTouchEventListener((ViewerContentLayout.InterceptTouchEventListener) null);
    }

    public void onInitialized() {
        ((RemasterViewerLayoutBinding) this.mViewBinding).photoView.setScaleChangeListener(new Qb.c(8, this));
        ((RemasterViewerLayoutBinding) this.mViewBinding).photoView.setZoomDetectListener(new j(this));
        ((RemasterViewerLayoutBinding) this.mViewBinding).photoView.setOnFlingListener(new j(this));
        ((RemasterViewerLayoutBinding) this.mViewBinding).contentContainerForTouch.setInterceptTouchEventListener(new j(this));
    }

    public void onViewDetached() {
        super.onViewDetached();
        resetPhotoView(((RemasterViewerLayoutBinding) this.mViewBinding).remasterPhotoView);
        resetPhotoView(((RemasterViewerLayoutBinding) this.mViewBinding).photoView);
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        ((RemasterViewerLayoutBinding) this.mViewBinding).photoView.clearBitmap();
        ((RemasterViewerLayoutBinding) this.mViewBinding).remasterPhotoView.clearBitmap();
    }

    public void setPhotoView(PhotoViewCompat photoViewCompat) {
        photoViewCompat.setMotionControl(photoViewCompat.createDefaultMotionControl(((RemasterViewerLayoutBinding) this.mViewBinding).contentContainerForTouch.getParent()), (OnViewerExitGestureListener) null);
        photoViewCompat.blockMotionControl(true);
        photoViewCompat.setZoomEnabled(true);
        photoViewCompat.setTranslationListener(new b(11, this, photoViewCompat));
    }
}
