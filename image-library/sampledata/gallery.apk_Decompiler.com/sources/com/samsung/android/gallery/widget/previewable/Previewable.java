package com.samsung.android.gallery.widget.previewable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.view.View;
import com.samsung.android.gallery.module.dynamicview.PlaybackOption;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Previewable {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface PreviewListener {
        boolean enableTimeTick() {
            return false;
        }

        PlaybackOption getNextPlaybackOption(int i2) {
            return null;
        }

        String getPlaybackOptionsDebug() {
            return "";
        }

        float getVolume() {
            return 0.0f;
        }

        boolean isMuteAudio();

        boolean isPlaybackPreview() {
            return false;
        }

        boolean listenVideoInfo() {
            return false;
        }

        void onPreviewEnd();

        void onPreviewFail(MediaPlayerCompat mediaPlayerCompat, int i2, int i7);

        void onPreviewFail(Object obj, int i2, int i7);

        void onPreviewReady();

        void onPreviewStart();

        boolean onVideoInfo(int i2, int i7) {
            return false;
        }

        boolean useSecMediaPlayer() {
            return false;
        }
    }

    View createPreviewView(Context context, int i2);

    Bitmap getPreviewBitmap() {
        return null;
    }

    Matrix getPreviewMatrix() {
        return null;
    }

    void seekTo(int i2);

    void setColorFilter(ColorFilter colorFilter);

    void setLooping(boolean z);

    void startPreview();

    void stopPreview();

    void pausePlayer() {
    }

    void resumePlayer() {
    }

    void muteAudio(boolean z) {
    }

    void setTag(Object obj) {
    }

    void applyFilter(String str, int i2) {
    }

    void setPlaybackRange(int i2, int i7) {
    }
}
