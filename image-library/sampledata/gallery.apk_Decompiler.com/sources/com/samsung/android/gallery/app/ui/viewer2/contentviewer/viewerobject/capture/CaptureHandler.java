package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture;

import B4.c;
import android.content.Context;
import android.view.View;
import android.view.ViewStub;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.behavior.SheetBehaviorCompat;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.sec.android.gallery3d.R;
import java.util.Objects;
import java.util.function.BiPredicate;
import w7.C0535a;
import w7.C0536b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CaptureHandler extends ViewerObject implements FragmentLifeCycle {
    protected final String TAG = getClass().getSimpleName();
    private final int mCaptureMode;
    protected View mCaptureViewLayout;
    private ViewStub mCaptureViewStub;
    private boolean mIsPppZoomed;
    protected boolean mIsZoomed;
    protected IMediaPlayerView mMediaPlayerView;
    protected PhotoViewCompat mPhotoView;
    boolean mViewInited = false;
    protected ViewerCapture mViewerCapture;

    public CaptureHandler(int i2) {
        this.mCaptureMode = i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        if (r0 != 2) goto L_0x0035;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void bindView(com.samsung.android.gallery.app.ui.viewer2.model.ContentModel r5) {
        /*
            r4 = this;
            android.view.View r0 = r4.mCaptureViewLayout
            if (r0 != 0) goto L_0x000c
            java.lang.String r4 = r4.TAG
            java.lang.String r5 = "captureViewLayout null"
            com.samsung.android.gallery.support.utils.Log.w(r4, r5)
            return
        L_0x000c:
            boolean r0 = r4.isValidCapture()
            if (r0 == 0) goto L_0x0013
            return
        L_0x0013:
            int r0 = r4.mCaptureMode
            if (r0 == 0) goto L_0x0025
            r1 = 1
            if (r0 == r1) goto L_0x001e
            r1 = 2
            if (r0 == r1) goto L_0x0025
            goto L_0x0035
        L_0x001e:
            com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture.ViewerMediaPlayerCapture r5 = r4.createMediaPlayerCapture(r5)
            r4.mViewerCapture = r5
            goto L_0x0035
        L_0x0025:
            com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture.ViewerImageCapture r0 = new com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture.ViewerImageCapture
            com.samsung.android.gallery.widget.photoview.PhotoViewCompat r1 = r4.mPhotoView
            A4.u r2 = new A4.u
            r3 = 28
            r2.<init>(r3, r4)
            r0.<init>(r5, r1, r2)
            r4.mViewerCapture = r0
        L_0x0035:
            com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture.ViewerCapture r5 = r4.mViewerCapture
            android.view.View r4 = r4.mCaptureViewLayout
            r5.bindView(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture.CaptureHandler.bindView(com.samsung.android.gallery.app.ui.viewer2.model.ContentModel):void");
    }

    private void hideButton() {
        this.mThread.runOnUiThread(new C0536b(this, 1));
    }

    private void hidePreview() {
        ViewerCapture viewerCapture = this.mViewerCapture;
        if (viewerCapture != null && viewerCapture.isCaptureProcessing()) {
            ViewerCapture viewerCapture2 = this.mViewerCapture;
            Objects.requireNonNull(viewerCapture2);
            ThreadUtil.postOnUiThread(new a(viewerCapture2, 0));
        }
    }

    private void initCaptureView() {
        if (!this.mViewInited) {
            this.mViewInited = true;
            if (this.mCaptureViewLayout == null) {
                this.mActionInvoker.invoke(ViewerAction.QUICK_CROP_VIEW_INFLATE, new Object[0]);
            }
            bindView(this.mModel);
            ViewerCapture viewerCapture = this.mViewerCapture;
            if (viewerCapture != null) {
                viewerCapture.onViewConfirm();
                if (this.mIsZoomed) {
                    Log.i(this.TAG, "zoomed before confirm");
                    onZoomChanged(Boolean.TRUE);
                }
            }
        }
    }

    private boolean isValidCapture() {
        ViewerCapture viewerCapture = this.mViewerCapture;
        if (viewerCapture == null) {
            return false;
        }
        int i2 = this.mCaptureMode;
        if (i2 != 0) {
            if (i2 == 1) {
                return viewerCapture instanceof ViewerMediaPlayerCapture;
            }
            if (i2 != 2) {
                return false;
            }
        }
        return viewerCapture instanceof ViewerImageCapture;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mCaptureViewLayout = objArr[0];
        this.mCaptureViewStub = objArr[1];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mPhotoView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        this.mMediaPlayerView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$3(Object[] objArr) {
        this.mMediaPlayerView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$4(Object[] objArr) {
        setVideoCaptureEnabled(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$5(Object[] objArr) {
        ViewerCapture viewerCapture = this.mViewerCapture;
        if (viewerCapture != null && !viewerCapture.isCaptureProcessing()) {
            setVideoCaptureEnabled(true);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$6(Object[] objArr) {
        ViewerCapture viewerCapture = this.mViewerCapture;
        if (viewerCapture != null && !viewerCapture.isCaptureProcessing()) {
            setVideoCaptureEnabled(objArr[0].booleanValue());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$7(Object[] objArr) {
        hideButton();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$8(Object[] objArr) {
        hideButton();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$9(Object[] objArr) {
        setVideoCaptureEnabled(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$bindView$10() {
        return this.mIsZoomed;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hideButton$13() {
        ViewerCapture viewerCapture = this.mViewerCapture;
        if (viewerCapture != null) {
            viewerCapture.setButtonVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$invalidate$11() {
        if (this.mIsPppZoomed) {
            Log.d(this.TAG, "execPppZoomed");
            this.mIsPppZoomed = false;
            onZoomChanged(Boolean.TRUE);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setVideoCaptureEnabled$12(boolean z) {
        ViewerCapture viewerCapture = this.mViewerCapture;
        if (viewerCapture != null && viewerCapture.isVideoMode()) {
            if (z) {
                this.mViewerCapture.updateButtonVisibility();
            }
            this.mViewerCapture.setEnabled(z);
        }
    }

    private void moveUpLayoutIfScreenShot() {
        boolean z;
        float f;
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null && BucketUtils.isScreenshot(mediaItem.getBucketID())) {
            View findViewById = this.mCaptureViewLayout.getRootView().findViewById(R.id.button1);
            if (findViewById == null || findViewById.getVisibility() != 0) {
                z = false;
            } else {
                z = true;
            }
            View view = this.mCaptureViewLayout;
            Context context = view.getContext();
            if (z) {
                f = 40.0f;
            } else {
                f = 0.0f;
            }
            view.setPadding(0, 0, 0, ResourceCompat.dpToPixel(context, f));
        }
    }

    /* access modifiers changed from: private */
    public void onCapture(Object... objArr) {
        ViewerCapture viewerCapture = this.mViewerCapture;
        if (viewerCapture != null) {
            viewerCapture.setCaptureAndGo(objArr[0].booleanValue(), objArr[1], objArr[2]);
            this.mViewerCapture.execCapture();
        }
    }

    /* access modifiers changed from: private */
    public void onChangePreviewControllable(Object... objArr) {
        boolean z = false;
        BiPredicate biPredicate = objArr[0];
        if (biPredicate != null && biPredicate.test(this.mModel.getMediaItem(), this.mModel.getContainerModel().getLocationKey())) {
            z = true;
        }
        ViewerCapture viewerCapture = this.mViewerCapture;
        if (viewerCapture != null && viewerCapture.isVideoMode()) {
            this.mViewerCapture.setEnabled(z);
        }
    }

    /* access modifiers changed from: private */
    public void onZoomChanged(Object... objArr) {
        boolean booleanValue = objArr[0].booleanValue();
        if (booleanValue && !SheetBehaviorCompat.isCollapsed(this.mModel.getContainerModel().getDetailsState())) {
            Log.d(this.TAG, "onZoomChanged drop");
        } else if (this.mViewerCapture != null) {
            MediaItem mediaItem = this.mModel.getMediaItem();
            if (mediaItem == null || !mediaItem.isCommonPostProcessing()) {
                if (this.mIsPppZoomed) {
                    this.mIsPppZoomed = false;
                    Log.d(this.TAG, "resetPppZoomed");
                }
                this.mIsZoomed = booleanValue;
                this.mViewerCapture.onZoomChanged();
            } else {
                this.mIsPppZoomed = booleanValue;
                Log.d(this.TAG, "setPppZoomed");
            }
            moveUpLayoutIfScreenShot();
        } else {
            this.mIsZoomed = booleanValue;
        }
    }

    private void setVideoCaptureEnabled(boolean z) {
        this.mThread.runOnUiThread(new c((Object) this, z, 29));
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.QUICK_CROP_VIEW, new C0535a(this, 0));
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new C0535a(this, 7));
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new C0535a(this, 8));
        this.mActionInvoker.add(ViewerAction.REPLACE_REMOTE_MEDIA_VIEW, new C0535a(this, 9));
        this.mActionInvoker.add(ViewerAction.IMAGE_ZOOM, new C0535a(this, 10));
        this.mActionInvoker.add(ViewerAction.CAPTURE, new C0535a(this, 11));
        this.mActionInvoker.add(ViewerAction.CHANGE_PREVIEW_CONTROLLABLE, new C0535a(this, 12));
        this.mActionInvoker.add(ViewerAction.REQUEST_VIDEO_SEEK_BEGIN, new C0535a(this, 1));
        this.mActionInvoker.add(ViewerAction.REQUEST_VIDEO_SEEK_FINISH, new C0535a(this, 2));
        this.mActionInvoker.add(ViewerAction.ENABLE_CAPTURE_ICON, new C0535a(this, 3));
        this.mActionInvoker.add(ViewerAction.HIDE_CAPTURE_ICON, new C0535a(this, 4));
        this.mActionInvoker.add(ViewerAction.GROUP_COUNT_VIEW_SELECTED, new C0535a(this, 5));
        if (Features.isEnabled(Features.USE_SHARED_DOWNLOAD_FILE_VERIFY)) {
            this.mActionInvoker.add(ViewerAction.INITIALIZE_SHARED_VIDEO, new C0535a(this, 6));
        }
    }

    public ViewerMediaPlayerCapture createMediaPlayerCapture(ContentModel contentModel) {
        return new ViewerMediaPlayerCapture(contentModel, this.mMediaPlayerView);
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        ViewerCapture viewerCapture = this.mViewerCapture;
        if (viewerCapture != null && viewerCapture.isButtonDimmed()) {
            this.mModel.getBlackboard().post("command://update/MediaItem/QuickCrop", (Object) null);
        }
        if (this.mIsPppZoomed) {
            this.mThread.runOnUiThread(new C0536b(this, 0), 500);
        }
    }

    public void onPageSelected() {
        initCaptureView();
    }

    public void onPause() {
        hidePreview();
    }

    public void onResolutionChanged() {
        View view = this.mCaptureViewLayout;
        if (view != null) {
            this.mCaptureViewLayout = ViewUtils.reinflateViewStub(this.mCaptureViewStub, view);
            this.mViewerCapture = null;
            bindView(this.mModel);
            this.mViewerCapture.updateButtonVisibility();
        }
    }

    public void onViewConfirm() {
        initCaptureView();
    }

    public void onViewDetached() {
        super.onViewDetached();
        this.mViewInited = false;
        ViewerCapture viewerCapture = this.mViewerCapture;
        if (viewerCapture != null) {
            viewerCapture.hidePreview(false);
            this.mViewerCapture.setButtonVisibility(8);
            this.mViewerCapture.releaseCaptureCmd();
        }
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mViewInited = false;
        this.mViewerCapture = null;
        this.mIsPppZoomed = false;
        this.mIsZoomed = false;
    }
}
