package com.samsung.android.gallery.support.library.abstraction;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.PlaybackParams;
import android.net.Uri;
import android.view.Surface;
import android.view.SurfaceHolder;
import c0.C0086a;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MediaPlayerCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnCompletionListener {
        void onCompletion(MediaPlayerCompat mediaPlayerCompat);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnErrorListener {
        boolean onError(MediaPlayerCompat mediaPlayerCompat, int i2, int i7);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnInfoListener {
        boolean onInfo(MediaPlayerCompat mediaPlayerCompat, int i2, int i7);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnPlayBackCompleteListener {
        void onPlaybackComplete(MediaPlayerCompat mediaPlayerCompat);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnPreparedListener {
        void onPrepared(MediaPlayerCompat mediaPlayerCompat);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnVideoSizeChangedListener {
    }

    Bitmap getCurrentFrame(int i2, int i7) {
        return null;
    }

    Bitmap getCurrentFrameSdr(int i2, int i7) {
        return getCurrentFrame(i2, i7);
    }

    int getCurrentPositionMs();

    int getDurationMs();

    int getFrameRate();

    String getMediaInfoString(int i2) {
        if (i2 == 1) {
            return "MEDIA_INFO_UNKNOWN";
        }
        if (i2 == 3) {
            return "MEDIA_INFO_VIDEO_RENDERING_START";
        }
        if (i2 == 701) {
            return "MEDIA_INFO_BUFFERING_START";
        }
        if (i2 == 702) {
            return "MEDIA_INFO_BUFFERING_END";
        }
        if (i2 == 800) {
            return "MEDIA_INFO_BAD_INTERLEAVING";
        }
        if (i2 == 801) {
            return "MEDIA_INFO_NOT_SEEKABLE";
        }
        if (i2 == 10950) {
            return "MEDIA_INFO_UNSUPPORTED_AUDIO";
        }
        if (i2 == 10951) {
            return "MEDIA_INFO_UNSUPPORTED_VIDEO";
        }
        if (i2 == 10972) {
            return "MEDIA_INFO_NO_AUDIO";
        }
        if (i2 == 10973) {
            return "MEDIA_INFO_NO_VIDEO";
        }
        switch (i2) {
            case 9000001:
                return "MEDIA_INFO_TIME_TICK_UPDATE";
            case 9000002:
                return "MEDIA_INFO_STARTED";
            case 9000003:
                return "MEDIA_INFO_STOPPED";
            case 9000004:
                return "MEDIA_INFO_PAUSED";
            case 9000005:
                return "MEDIA_INFO_COMPLETED";
            case 9000006:
                return "MEDIA_INFO_RELEASED";
            default:
                return C0086a.i(i2, "MEDIA_INFO_#");
        }
    }

    PlaybackParams getPlaybackParam();

    int getPlaybackState();

    int getRenderingPosition() {
        return getCurrentPositionMs();
    }

    int[] getSuperSlowTitleRegion() {
        return new int[]{0, 0};
    }

    Object getTag();

    int getVideoHeight();

    int getVideoRotation();

    int getVideoWidth();

    boolean hasAudioTrack();

    boolean isPlaying();

    boolean isPrepared();

    boolean isPreparing();

    boolean pause();

    void prepareAsync();

    void release();

    void reset();

    void seekTo(int i2);

    void seekToAdaptively(int i2) {
        seekTo(i2);
    }

    void setAudioMute(boolean z);

    boolean setBgmPlaybackRange(int i2, int i7) {
        return false;
    }

    void setDataSource(Context context, Uri uri);

    void setDataSource(String str);

    void setDataSource(String str, long j2, long j3);

    void setDisplay(SurfaceHolder surfaceHolder);

    void setDynamicViewConfiguration(ArrayList<MediaPlayback> arrayList) {
    }

    void setLooping(boolean z);

    void setOnCompletionListener(OnCompletionListener onCompletionListener);

    void setOnErrorListener(OnErrorListener onErrorListener);

    void setOnInfoListener(OnInfoListener onInfoListener);

    void setOnPreparedListener(OnPreparedListener onPreparedListener);

    void setOnVideoSizeChangedListener(OnVideoSizeChangedListener onVideoSizeChangedListener);

    void setPlaybackParam(PlaybackParams playbackParams);

    boolean setPlaybackRange(int i2, int i7) {
        return false;
    }

    void setSurface(Surface surface);

    void setTag(Object obj);

    void setTimeTickEnabled(boolean z);

    void setVolume(float f, float f5);

    void start();

    void visualSeekTo(int i2) {
        seekTo(i2);
    }

    void setDynamicViewConfiguration(ArrayList<MediaPlayback> arrayList, boolean z) {
    }

    void beginInstantSlowMoPlay() {
    }

    void endInstantSlowMoPlay() {
    }

    void prepareSeekTo() {
    }

    void releaseSoundAlive() {
    }

    void setColorCorrect() {
    }

    void setIsHdrVideo() {
    }

    void setSuperSlowAllRegionEnabled() {
    }

    void setWfdTcpDisable() {
    }

    void completeSeekTo(boolean z) {
    }

    void initBackgroundMusic(BgmOptions bgmOptions) {
    }

    void seekToBgm(int i2) {
    }

    void set3dEffectPosition(double d) {
    }

    void setPlaybackCompleteListener(OnPlayBackCompleteListener onPlayBackCompleteListener) {
    }

    void setAudioEraserOff(boolean z, String str) {
    }

    void setSuperSlowPlaybackEffect(int i2, int i7) {
    }

    void setVideoFilter(String str, int i2) {
    }

    void setVideoFrc(int i2, float f, boolean z) {
    }
}
