package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture;

import android.net.Uri;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.viewer.SaveVideoCaptureCmd;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.sec.android.gallery3d.R;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ViewerMediaPlayerCapture extends ViewerCapture {
    private final IMediaPlayerView mMediaPlayerView;

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, java.util.function.BooleanSupplier] */
    public ViewerMediaPlayerCapture(ContentModel contentModel, IMediaPlayerView iMediaPlayerView) {
        this(contentModel, iMediaPlayerView, new Object());
    }

    private boolean isInPlayState() {
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView == null || !iMediaPlayerView.isInPlayState()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$new$0() {
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onQuickCropPreviewClicked$2(boolean z, MediaItem mediaItem, String str) {
        if (z) {
            Utils.showShortToast(this.mModel.getContext(), (int) R.string.video_captures_album_is_hidden);
            hidePreview(true);
            return;
        }
        super.onQuickCropPreviewClicked(mediaItem, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onQuickCropPreviewClicked$3(MediaItem mediaItem, String str) {
        int albumID = mediaItem.getAlbumID();
        if (albumID == 0 && mediaItem.isUriItem()) {
            albumID = AlbumHelper.getInstance().getBucketIdFrom((long) UnsafeCast.toInt(Uri.parse(mediaItem.getPath()).getLastPathSegment()));
        }
        ThreadUtil.postOnUiThread(new i(this, AlbumHelper.getInstance().isHiddenAlbum(albumID), mediaItem, str));
    }

    public void execCapture() {
        IMediaPlayerView iMediaPlayerView;
        super.execCapture();
        MediaItem mediaItem = this.mModel.getMediaItem();
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        if (mediaItem == null || eventContext == null || (iMediaPlayerView = this.mMediaPlayerView) == null) {
            Log.d("ViewerCapture", "onCapture::Video Capture failed");
        } else if (iMediaPlayerView.isInPlayState()) {
            SaveVideoCaptureCmd saveVideoCaptureCmd = new SaveVideoCaptureCmd();
            setCallback(saveVideoCaptureCmd);
            this.mMediaPlayerView.captureFrame(new j(saveVideoCaptureCmd, eventContext, mediaItem), 0);
        }
    }

    public int getTitleRes() {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.CropVideoCapture)) {
            return R.string.capture;
        }
        return R.string.quick_crop;
    }

    public boolean isVideoMode() {
        return true;
    }

    public void onCaptureClick() {
        if (this.mModel.isUnsupportedVideo()) {
            Utils.showToast(this.mModel.getContext(), (int) R.string.cant_play_video_file_type_not_supported);
        } else {
            super.onCaptureClick();
        }
    }

    public void onQuickCropPreviewClicked(MediaItem mediaItem, String str) {
        if (this.mModel.getMediaItem() != null) {
            SimpleThreadPool.getInstance().execute(new c(this, mediaItem, str, 2));
        }
    }

    public void onViewConfirm() {
        updateButtonVisibility();
    }

    public void setEnabled(boolean z) {
        boolean z3;
        if (!z || !isInPlayState()) {
            z3 = false;
        } else {
            z3 = true;
        }
        super.setEnabled(z3);
    }

    public int setIconRes() {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.CropVideoCapture)) {
            return R.drawable.gallery_ic_detail_video_crop_capture;
        }
        return R.drawable.gallery_ic_detail_video_capture;
    }

    public boolean supportCapture(MediaItem mediaItem) {
        if (!super.supportCapture(mediaItem) || !MediaItemUtil.supportPreviewPlay(mediaItem)) {
            return false;
        }
        return true;
    }

    public ViewerMediaPlayerCapture(ContentModel contentModel, IMediaPlayerView iMediaPlayerView, BooleanSupplier booleanSupplier) {
        super(contentModel, booleanSupplier);
        this.mMediaPlayerView = iMediaPlayerView;
    }
}
