package com.samsung.android.gallery.app.remote.v2;

import U3.a;
import android.app.Presentation;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.samsung.android.gallery.app.remote.SlideshowServiceOnRemoteV2;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.photoview.PhotoViewMotionControl;
import com.sec.android.gallery3d.R;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryBasePresentation extends Presentation implements GalleryPresentation {
    private static final AtomicBoolean sOccupied = new AtomicBoolean(false);
    private final PresentationController mController;
    private Rect mExternalDisplaySize;
    private DisplayRemovedListener mOnDisplayRemovedListener;
    private boolean mPresentationDisplayRemoved;
    private int mPrevPos = -1;
    protected IPresentationView mView;
    private final IVuDispatcher mViewDispatcher;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface DisplayRemovedListener {
    }

    public GalleryBasePresentation(Context context, Display display, int i2, IVuDispatcher iVuDispatcher) {
        super(context, display);
        if (!sOccupied.getAndSet(true)) {
            this.mController = new PresentationController(context, i2);
            this.mViewDispatcher = iVuDispatcher;
            return;
        }
        Utils.showToast(getContext(), (int) R.string.unable_to_play_presentation);
        throw new PresentationOccupiedException("GalleryPresentation is occupied");
    }

    private WindowManager.LayoutParams createLayoutParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2002, 262440, 1);
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.gravity = 8388659;
        layoutParams.softInputMode = 32;
        layoutParams.setTitle(getClass().getName());
        return layoutParams;
    }

    private int getDisplayId() {
        Display display;
        if (!this.mPresentationDisplayRemoved && (display = getDisplay()) != null) {
            return display.getDisplayId();
        }
        return -1;
    }

    private void initWindow() {
        Window window = getWindow();
        if (window != null) {
            try {
                window.requestFeature(1);
                window.setBackgroundDrawableResource(17170444);
                window.setAttributes(createLayoutParams());
                if (SdkConfig.atLeast(SdkConfig.SEM.S)) {
                    window.setType(2037);
                } else {
                    window.setType(2038);
                }
                if (SdkConfig.atLeast(SdkConfig.SEM.V)) {
                    window.setColorMode(1);
                }
                SeApiCompat.disableViewRoundedCorner(window.getDecorView());
            } catch (Exception e) {
                Log.rme("GalleryBasePresentation", "initWindow failed e=" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private float[] normalizeMotionEvent(Rect rect, Rect rect2, float f, float f5) {
        float width = (f - ((float) rect.left)) / ((float) rect.width());
        float height = (f5 - ((float) rect.top)) / ((float) rect.height());
        float width2 = width * ((float) rect2.width());
        float height2 = height * ((float) rect2.height());
        return new float[]{width2 + ((float) rect2.left), height2 + ((float) rect2.top)};
    }

    private void preparePhotoView(int i2, MediaItem mediaItem, Consumer<MediaItem> consumer) {
        int i7;
        int i8 = this.mPrevPos;
        if (i8 == -1) {
            i7 = 0;
        } else {
            i7 = i2 - i8;
        }
        this.mPrevPos = i2;
        this.mView.updateAnimationView(i7, consumer, mediaItem, i2);
    }

    public void clearController() {
        this.mController.clear();
    }

    public void dismiss() {
        this.mController.clear();
        this.mView.clearViews();
        sOccupied.set(false);
        super.dismiss();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.mViewDispatcher == null || this.mExternalDisplaySize == null) {
            if (super.dispatchTouchEvent(motionEvent)) {
                return true;
            }
            return this.mController.onTouchEvent(motionEvent);
        } else if (this.mView.handleTouchController(motionEvent)) {
            return true;
        } else {
            Rect currentWindowBounds = this.mViewDispatcher.getCurrentWindowBounds();
            if (currentWindowBounds == null) {
                return false;
            }
            float[] normalizeMotionEvent = normalizeMotionEvent(this.mExternalDisplaySize, currentWindowBounds, motionEvent.getX(), motionEvent.getY());
            motionEvent.setLocation(normalizeMotionEvent[0], normalizeMotionEvent[1]);
            return this.mViewDispatcher.handleExternalTouchEvent(motionEvent);
        }
    }

    public View getMediaView(int i2) {
        return this.mView.getMediaView(i2);
    }

    public void hide() {
        super.hide();
        this.mPrevPos = -1;
    }

    public void hideMediaView() {
        this.mView.hideMediaView();
    }

    public void onCreate(Bundle bundle) {
        Window window;
        super.onCreate(bundle);
        initWindow();
        setContentView((View) this.mView);
        if (this.mViewDispatcher != null && (window = getWindow()) != null) {
            this.mExternalDisplaySize = window.getWindowManager().getCurrentWindowMetrics().getBounds();
        }
    }

    public void onDataChanged() {
        this.mPrevPos = -1;
        this.mView.onDataChanged();
    }

    public void onDisplayChanged() {
        Object obj;
        StringBuilder sb2 = new StringBuilder("onDisplayChanged. displayID[");
        if (getDisplay() != null) {
            obj = Integer.valueOf(getDisplay().getDisplayId());
        } else {
            obj = "Null display";
        }
        sb2.append(obj);
        sb2.append("]");
        Log.rm("GalleryBasePresentation", sb2.toString());
    }

    public void onDisplayRemoved() {
        Log.rm("GalleryBasePresentation", "onDisplayRemoved. displayID[" + getDisplayId() + "]");
        this.mPresentationDisplayRemoved = true;
        DisplayRemovedListener displayRemovedListener = this.mOnDisplayRemovedListener;
        if (displayRemovedListener != null) {
            ((SlideshowServiceOnRemoteV2) ((a) displayRemovedListener).e).stopSlideshow();
        }
    }

    public void post(Runnable runnable) {
        this.mView.pendingRun(runnable);
    }

    public void setOnDisplayRemovedListener(DisplayRemovedListener displayRemovedListener) {
        this.mOnDisplayRemovedListener = displayRemovedListener;
    }

    public void setPresentationView(IPresentationView iPresentationView) {
        this.mView = iPresentationView;
        this.mController.setView(iPresentationView);
    }

    public void show() {
        super.show();
    }

    public void showMediaView() {
        this.mView.showMediaView();
    }

    public void updateData(Bitmap bitmap, PhotoViewMotionControl photoViewMotionControl, MediaItem mediaItem, int i2, boolean z) {
        boolean z3;
        Consumer<MediaItem> consumer;
        StringBuilder sb2 = new StringBuilder("updateData");
        if (getDisplayId() != -1) {
            z3 = true;
        } else {
            z3 = false;
        }
        sb2.append(Logger.v(Boolean.valueOf(z3), Boolean.valueOf(isShowing())));
        Log.rm("GalleryBasePresentation", sb2.toString());
        if (getDisplayId() != -1) {
            if (!isShowing()) {
                show();
            }
            if (z) {
                consumer = this.mController.internalPlay(mediaItem);
            } else {
                consumer = null;
            }
            preparePhotoView(i2, mediaItem, consumer);
            updatePhotoView(mediaItem, photoViewMotionControl, bitmap, i2);
        }
    }

    public void updatePhotoView(MediaItem mediaItem, PhotoViewMotionControl photoViewMotionControl, Bitmap bitmap, int i2) {
        this.mView.updateViews(mediaItem, bitmap, photoViewMotionControl, i2);
    }

    public void close() {
    }
}
