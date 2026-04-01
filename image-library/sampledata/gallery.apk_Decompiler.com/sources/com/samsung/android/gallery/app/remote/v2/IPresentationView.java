package com.samsung.android.gallery.app.remote.v2;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.photoview.PhotoViewMotionControl;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IPresentationView {
    void clearViews();

    View getMediaView(int i2);

    ViewPager2.OnPageChangeCallback getViewPagerCallback();

    boolean handleTouchController(MotionEvent motionEvent);

    void hideMediaView();

    void onAnimationFrameUpdated(Bitmap bitmap);

    void onDataChanged();

    void pendingRun(Runnable runnable);

    void setControlViewClickListener(ControlViewClickCallback controlViewClickCallback);

    void showMediaView();

    void startAdapterWithPosition(int i2);

    void toggleControlView();

    void updateAnimationView(int i2, Consumer<MediaItem> consumer, MediaItem mediaItem, int i7);

    void updateViews(MediaItem mediaItem, Bitmap bitmap, PhotoViewMotionControl photoViewMotionControl, int i2);
}
