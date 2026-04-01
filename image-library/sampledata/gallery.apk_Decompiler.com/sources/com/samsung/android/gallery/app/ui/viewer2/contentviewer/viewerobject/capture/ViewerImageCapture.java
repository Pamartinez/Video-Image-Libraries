package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.viewer.SaveCaptureCmd;
import com.samsung.android.gallery.app.controller.viewer.SaveCaptureCompatCmd;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.CodecCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;
import com.sec.android.gallery3d.R;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ViewerImageCapture extends ViewerCapture {
    private final PhotoViewCompat mPhotoView;

    public ViewerImageCapture(ContentModel contentModel, PhotoViewCompat photoViewCompat, BooleanSupplier booleanSupplier) {
        super(contentModel, booleanSupplier);
        this.mPhotoView = photoViewCompat;
    }

    public void execCapture() {
        SaveCaptureCmd saveCaptureCmd;
        super.execCapture();
        MediaItem mediaItem = this.mModel.getMediaItem();
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        if (mediaItem == null || eventContext == null || this.mPhotoView == null || isCaptureProcessing()) {
            Log.d("ViewerCapture", "onCapture::Image Capture failed");
            return;
        }
        if (this.mPhotoView.supportZoomCompat()) {
            saveCaptureCmd = new SaveCaptureCompatCmd((int) this.mPhotoView.getCurrentScale(), this.mPhotoView.getSceneType());
        } else {
            saveCaptureCmd = new SaveCaptureCmd();
        }
        setCallback(saveCaptureCmd);
        if (isCaptureAndGo()) {
            saveCaptureCmd.andGo(this.mIsShare, this.mPackageName, this.mActivityName);
            resetCaptureAndGo();
        }
        PhotoViewCompat photoViewCompat = this.mPhotoView;
        saveCaptureCmd.execute(eventContext, mediaItem, photoViewCompat, photoViewCompat.getCropRectOnView(), this.mPhotoView.getCropRectOnImage());
    }

    public int getTitleRes() {
        return R.string.quick_crop;
    }

    public boolean isVideoMode() {
        return false;
    }

    public void onViewConfirm() {
        setEnabled(true);
    }

    public void onZoomChanged() {
        updateButtonVisibility();
    }

    public int setIconRes() {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.CropVideoCapture)) {
            return R.drawable.gallery_ic_detail_crop;
        }
        return R.drawable.gallery_ic_detail_capture;
    }

    public boolean supportCapture(MediaItem mediaItem) {
        if (!super.supportCapture(mediaItem) || !mediaItem.isLocal() || !CodecCompat.ensureJpegSyntaxCompatible(mediaItem)) {
            return false;
        }
        return true;
    }
}
