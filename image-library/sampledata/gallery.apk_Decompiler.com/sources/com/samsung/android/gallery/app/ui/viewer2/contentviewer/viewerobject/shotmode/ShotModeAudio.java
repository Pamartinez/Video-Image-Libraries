package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.shotmode;

import Fb.h;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.common.shotmode.AbsShotModeHandler;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.media.AudioPlayer;
import com.samsung.android.gallery.module.viewer.ImageAudioHelper;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShotModeAudio {
    private final CharSequence TAG;
    private final ImageAudioHelper mImageAudioHelper;
    private final ContentModel mModel;
    private final View mShotModeButton;
    AbsShotModeHandler mShotModeHandler;

    public ShotModeAudio(CharSequence charSequence, ContentModel contentModel, View view) {
        this.mImageAudioHelper = new ImageAudioHelper(charSequence).setListener(new h(1, this));
        this.TAG = charSequence;
        this.mModel = contentModel;
        this.mShotModeButton = view;
    }

    /* access modifiers changed from: private */
    public void onAudioPlayerChanged(AudioPlayer audioPlayer, int i2, int i7) {
        if (i2 != 1) {
            return;
        }
        if (i7 == 0) {
            this.mImageAudioHelper.stopAnimation(this.mShotModeButton);
        } else if (i7 == 4) {
            this.mImageAudioHelper.startAnimation(this.mShotModeButton);
        }
    }

    private void startAudio(AbsShotModeHandler absShotModeHandler, MediaItem mediaItem) {
        if (mediaItem == null) {
            Log.d(this.TAG, "start Audio failed : null item");
            return;
        }
        CharSequence charSequence = this.TAG;
        Log.mp(charSequence, "start Audio " + MediaItemUtil.getSimpleLog(mediaItem));
        if (this.mImageAudioHelper.start(this.mModel.getContext(), absShotModeHandler, mediaItem) > 0 && !this.mImageAudioHelper.isLooping()) {
            this.mImageAudioHelper.pause();
        }
    }

    public void onPlayAudioClicked(AbsShotModeHandler absShotModeHandler) {
        this.mShotModeHandler = absShotModeHandler;
        if (this.mImageAudioHelper.isPlaying()) {
            this.mImageAudioHelper.stop();
        } else {
            startAudio(absShotModeHandler, this.mModel.getMediaItem());
        }
    }

    public void restartAudio() {
        Log.v(this.TAG, "onAnimationFrameStarted#restart");
        this.mImageAudioHelper.restart();
    }

    public void stopAudio() {
        this.mImageAudioHelper.stop();
    }
}
