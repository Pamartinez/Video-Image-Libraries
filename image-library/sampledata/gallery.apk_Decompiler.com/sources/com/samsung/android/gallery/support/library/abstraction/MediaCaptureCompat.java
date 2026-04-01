package com.samsung.android.gallery.support.library.abstraction;

import android.util.Log;
import java.io.FileDescriptor;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaCaptureCompat {
    protected final String TAG;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnErrorListener {
        boolean onError(MediaCaptureCompat mediaCaptureCompat, int i2, int i7);
    }

    public MediaCaptureCompat() {
        String simpleName = getClass().getSimpleName();
        this.TAG = simpleName;
        Log.v(simpleName, "constructor");
    }

    public Object getMediaCapture() {
        return null;
    }

    public boolean setDataSource(FileDescriptor fileDescriptor) {
        return false;
    }

    public boolean setOutputFile(FileDescriptor fileDescriptor) {
        return false;
    }

    public boolean startCapture() {
        return false;
    }

    public boolean support() {
        return false;
    }

    public void prepare() {
    }

    public void release() {
    }

    public void initBackgroundMusic(BgmOptions bgmOptions) {
    }

    public void setDynamicViewFormat(int i2) {
    }

    public void setMediaCapturePlaybacks(List<MediaPlayback> list) {
    }

    public void setOnCompleteListener(Consumer<Boolean> consumer) {
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
    }

    public void setOnPreparedListener(Consumer<Boolean> consumer) {
    }

    public void setOnProgressListener(Consumer<Integer> consumer) {
    }

    public void setParameter(int i2, int i7) {
    }

    public void setStartEndTime(int i2, int i7) {
    }

    public void setDynamicViewingConfigurations(int i2, int i7, float f) {
    }

    public void setBoomerangConfiguration(int i2, int i7, float f, int i8) {
    }
}
