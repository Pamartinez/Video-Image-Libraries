package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video;

import B7.d;
import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlideShowMediaPlayerHandler extends MediaViewPlayerHandler {
    /* access modifiers changed from: private */
    public void onTransitionPrepare(Object... objArr) {
        Bitmap pauseAndCapture = this.mMediaPlayerView.pauseAndCapture();
        Log.d(this.TAG, "onTransitionPrepare", pauseAndCapture);
        if (pauseAndCapture != null) {
            this.mMediaPlayerView.setVideoCaptured(this.mMediaPlayerView.getRenderingPosition(), pauseAndCapture);
            this.mPhotoView.setImage(pauseAndCapture, pauseAndCapture.getWidth(), pauseAndCapture.getHeight());
            ContentModel contentModel = this.mModel;
            contentModel.setBitmap(pauseAndCapture, contentModel.getMediaItem());
            this.mPhotoView.setVisibility(0);
        }
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.TRANSITION_PREPARE, new d(7, this));
    }

    public boolean isLoopEnabled() {
        return true;
    }
}
