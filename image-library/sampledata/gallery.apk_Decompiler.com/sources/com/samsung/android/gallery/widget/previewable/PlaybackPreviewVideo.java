package com.samsung.android.gallery.widget.previewable;

import Qb.a;
import android.media.PlaybackParams;
import com.samsung.android.gallery.module.dynamicview.PlaybackOption;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.previewable.Previewable;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerFactory;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class PlaybackPreviewVideo extends PreviewVideo {
    public PlaybackPreviewVideo(String str, Previewable.PreviewListener previewListener) {
        super(str, previewListener);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$preparePlayback$0(AtomicInteger atomicInteger, MediaPlayerCompat mediaPlayerCompat, AtomicInteger atomicInteger2, PlaybackParams playbackParams, MediaPlayerCompat mediaPlayerCompat2) {
        PlaybackOption nextPlaybackOption = this.mListener.getNextPlaybackOption(atomicInteger.incrementAndGet());
        if (nextPlaybackOption == null) {
            Previewable.PreviewListener previewListener = this.mListener;
            Objects.requireNonNull(previewListener);
            ThreadUtil.postOnUiThread(new a(previewListener, 0));
            return;
        }
        int max = Math.max(nextPlaybackOption.mStartMs, mediaPlayerCompat.getRenderingPosition());
        if (max > atomicInteger2.getAndSet(nextPlaybackOption.mEndMs)) {
            mediaPlayerCompat.seekTo(max);
        }
        mediaPlayerCompat.setPlaybackRange(max, nextPlaybackOption.mEndMs);
        playbackParams.setSpeed(nextPlaybackOption.mSpeed);
        mediaPlayerCompat.setPlaybackParam(playbackParams);
        mediaPlayerCompat.start();
    }

    public void closeVideoPlayer(MediaPlayerCompat mediaPlayerCompat) {
        mediaPlayerCompat.setPlaybackCompleteListener((MediaPlayerCompat.OnPlayBackCompleteListener) null);
        super.lambda$stopPreview$0(mediaPlayerCompat);
    }

    public MediaPlayerCompat createMediaPlayer() {
        return MediaPlayerFactory.createMediaPlayer(true);
    }

    public boolean ownPlaybackControl() {
        return true;
    }

    public boolean preparePlayback(MediaPlayerCompat mediaPlayerCompat) {
        PlaybackOption nextPlaybackOption = this.mListener.getNextPlaybackOption(0);
        if (nextPlaybackOption == null) {
            return false;
        }
        mediaPlayerCompat.seekTo(nextPlaybackOption.mStartMs);
        mediaPlayerCompat.setPlaybackRange(nextPlaybackOption.mStartMs, nextPlaybackOption.mEndMs);
        PlaybackParams playbackParam = mediaPlayerCompat.getPlaybackParam();
        playbackParam.setSpeed(nextPlaybackOption.mSpeed);
        mediaPlayerCompat.setPlaybackParam(playbackParam);
        Log.d(this.TAG, "playBack init", this.mListener.getPlaybackOptionsDebug());
        MediaPlayerCompat mediaPlayerCompat2 = mediaPlayerCompat;
        mediaPlayerCompat2.setPlaybackCompleteListener(new a(this, new AtomicInteger(0), mediaPlayerCompat2, new AtomicInteger(nextPlaybackOption.mEndMs), playbackParam));
        return true;
    }
}
