package com.samsung.android.gallery.image360.engine.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import com.samsung.android.gallery.image360.engine.IGallery360Viewer;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Gallery360ViewerImpl implements IGallery360Viewer {
    private SphericalGlView mView = null;

    public Gallery360ViewerImpl(Context context, boolean z, int i2, int i7) {
        this.mView = new SphericalGlView(context, z, i2, i7);
    }

    public void enableAutoPlay(boolean z) {
        this.mView.enableAutoPlay(z);
    }

    public void getBitmap(Consumer<Bitmap> consumer) {
        this.mView.getCurrent360Bitmap(consumer);
    }

    public IGallery360Viewer.SaveStatus getSaveStatus(String str) {
        return this.mView.getSaveStatus(str);
    }

    public View getView() {
        return this.mView;
    }

    public void onDestroy() {
        this.mView.onDestroy();
    }

    public void onPause() {
        this.mView.onPause();
    }

    public void onResume() {
        this.mView.onResume();
    }

    public void resetView(int i2) {
        this.mView.resetView(IGallery360Viewer.DefaultPlaybackDirection.values()[i2]);
    }

    public void save(String str, Callable<Void> callable, String str2) {
        this.mView.captureScreen(str, callable, str2);
    }

    public void setErrorListener(Callable<Void> callable) {
        this.mView.setErrorListener(callable);
    }

    public void setInputImage(Bitmap bitmap) {
        this.mView.init(bitmap);
    }

    public void setViewMode(IGallery360Viewer.ViewMode viewMode) {
        this.mView.setViewMode(viewMode);
    }

    public void startAutoRotation() {
        this.mView.startAutoRotation();
    }

    public void stopAutoRotation() {
        this.mView.stopAutoRotation();
    }
}
