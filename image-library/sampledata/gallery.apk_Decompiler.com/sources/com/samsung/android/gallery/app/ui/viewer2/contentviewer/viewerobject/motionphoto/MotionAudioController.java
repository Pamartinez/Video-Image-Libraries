package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto;

import B7.d;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.AudioController;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MotionAudioController extends AudioController implements FragmentLifeCycle {
    private boolean isVideoMode() {
        ImageView imageView = this.mPlayAudioIcon;
        if (imageView == null || imageView.getVisibility() != 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        updateVisibility(this.mModel.getMediaItem());
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.MOTION_PLAY_VIEWER_CHANGED, new d(4, this));
    }

    public boolean isToggleSoundEnabled() {
        if (!this.mHasAudio || !isVideoMode()) {
            return false;
        }
        return true;
    }

    public void setViewEnabled(boolean z) {
        float alpha = this.mPlayAudioIcon.getAlpha();
        super.setViewEnabled(z);
        if (!z && alpha != 1.0f) {
            ViewUtils.setAlpha(this.mPlayAudioIcon, 0.0f);
        }
    }

    public boolean supportAudioController(MediaItem mediaItem) {
        if (!super.supportAudioController(mediaItem) || !this.mModel.getMotionPlayViewer().isOriginVideo) {
            return false;
        }
        return true;
    }
}
