package com.samsung.android.gallery.image360.engine;

import android.graphics.Bitmap;
import android.view.View;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IGallery360Viewer {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum DefaultPlaybackDirection {
        REAR,
        FRONT
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum SaveStatus {
        SCREEN_CAPTURE_FAIL,
        SCREEN_CAPTURE_SUCCESS,
        SCREEN_CAPTURE_PROGRESS,
        INVALID_FILE_NAME
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ViewMode {
        MIRROR_BALL,
        LITTLE_PLANET,
        SPREAD,
        DUAL,
        PANORAMA
    }

    void enableAutoPlay(boolean z);

    void getBitmap(Consumer<Bitmap> consumer);

    SaveStatus getSaveStatus(String str);

    View getView();

    void onDestroy();

    void onPause();

    void onResume();

    void resetView(int i2);

    void save(String str, Callable<Void> callable, String str2);

    void setErrorListener(Callable<Void> callable);

    void setInputImage(Bitmap bitmap);

    void setViewMode(ViewMode viewMode);

    void startAutoRotation();

    void stopAutoRotation();
}
