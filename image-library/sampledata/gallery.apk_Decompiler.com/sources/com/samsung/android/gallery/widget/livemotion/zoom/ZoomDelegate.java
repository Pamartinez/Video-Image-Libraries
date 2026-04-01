package com.samsung.android.gallery.widget.livemotion.zoom;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.ocr.MOCRLang;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ZoomDelegate {
    private AbsZoomHandler mCurrentHandler;
    private float mCurrentScale;
    private float mScaleOffset;
    private TouchCoordinates mTouchCoordinates;
    private View mView;

    private boolean intersectTouchArea(View view, float f, float f5) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int i2 = iArr[0];
        if (f >= ((float) i2) && f <= ((float) (view.getWidth() + i2))) {
            int i7 = iArr[1];
            if (f5 < ((float) i7) || f5 > ((float) (view.getHeight() + i7))) {
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean isAnimating() {
        AbsZoomHandler absZoomHandler = this.mCurrentHandler;
        if (absZoomHandler == null || !absZoomHandler.isAnimating()) {
            return false;
        }
        return true;
    }

    private void updateCurrentHandler(boolean z) {
        AbsZoomHandler absZoomHandler;
        if (z) {
            absZoomHandler = new ZoomInHandler();
        } else {
            absZoomHandler = new ZoomInOutHandler();
        }
        this.mCurrentHandler = absZoomHandler;
    }

    public boolean isActive() {
        if (this.mView != null || isAnimating()) {
            return true;
        }
        return false;
    }

    public boolean move(float f, float f5) {
        if (!isActive()) {
            return false;
        }
        this.mTouchCoordinates.setLastTouch(f, f5);
        if (isAnimating()) {
            return true;
        }
        this.mCurrentHandler.move(this.mView, this.mTouchCoordinates, this.mCurrentScale);
        return true;
    }

    public void onScale(float f) {
        if (this.mView == null) {
            Log.e("ZoomDelegate", "onScale failed. view is null");
            return;
        }
        float min = Math.min(Math.max(f + this.mScaleOffset, this.mCurrentHandler.getMinScale()), this.mCurrentHandler.getMaxScale());
        if (Math.abs(this.mCurrentScale - min) >= this.mCurrentHandler.getThreshold()) {
            this.mCurrentScale = min;
            this.mCurrentHandler.onScale(this.mView, min);
        }
    }

    public boolean onScaleBegin(RecyclerView.ViewHolder viewHolder, float f, float f5, boolean z) {
        if (viewHolder != null) {
            View findViewById = viewHolder.itemView.findViewById(R$id.transform_parent_layout);
            if (intersectTouchArea(findViewById, f, f5)) {
                updateCurrentHandler(z);
                this.mCurrentScale = -1.0f;
                this.mView = findViewById;
                this.mTouchCoordinates = new TouchCoordinates(findViewById.getX(), this.mView.getY(), f, f5);
                this.mCurrentHandler.onScaleBegin(findViewById, f, f5);
                this.mScaleOffset = 0.0f;
                Log.d("ZoomDelegate", "onScaleBegin", Float.valueOf(f), Float.valueOf(f5));
                return true;
            }
        }
        Log.d("ZoomDelegate", "ignore onScaleBegin", viewHolder);
        return false;
    }

    public void reset() {
        this.mView = null;
        this.mTouchCoordinates = null;
        AbsZoomHandler absZoomHandler = this.mCurrentHandler;
        if (absZoomHandler != null) {
            absZoomHandler.reset();
        }
    }

    public void restore(Consumer<Boolean> consumer) {
        if (isActive()) {
            View view = this.mView;
            if (view != null) {
                this.mCurrentHandler.restore(view, consumer, MOCRLang.GURMUKHI, this.mCurrentScale);
                this.mView = null;
                Log.d("ZoomDelegate", "start restore animation");
                return;
            }
            Log.d("ZoomDelegate", "view is null");
        }
    }

    public void updateLastTouch(float f, float f5) {
        TouchCoordinates touchCoordinates = this.mTouchCoordinates;
        if (touchCoordinates != null) {
            touchCoordinates.setLastTouch(f, f5);
        }
    }

    public void restore() {
        if (isActive() && this.mCurrentHandler.restore(this.mView, this.mCurrentScale)) {
            reset();
        }
    }

    public void onScaleBegin(float f, float f5) {
        updateLastTouch(f, f5);
        this.mScaleOffset = this.mCurrentScale - 1.0f;
        Log.d("ZoomDelegate", "onScaleBeginAgain", Float.valueOf(f), Float.valueOf(f5));
    }
}
