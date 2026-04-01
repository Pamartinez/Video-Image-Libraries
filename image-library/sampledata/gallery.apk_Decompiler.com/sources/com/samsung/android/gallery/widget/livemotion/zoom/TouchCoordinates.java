package com.samsung.android.gallery.widget.livemotion.zoom;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TouchCoordinates {
    private float mDeltaX;
    private float mDeltaY;
    private final float mInitialX;
    private final float mInitialY;
    private float mLastTouchX;
    private float mLastTouchY;

    public TouchCoordinates(float f, float f5, float f8, float f10) {
        this.mInitialX = f;
        this.mInitialY = f5;
        this.mLastTouchX = f8;
        this.mLastTouchY = f10;
    }

    public float getDeltaX() {
        return this.mDeltaX;
    }

    public float getDeltaY() {
        return this.mDeltaY;
    }

    public float getInitialX() {
        return this.mInitialX;
    }

    public float getInitialY() {
        return this.mInitialY;
    }

    public void setLastTouch(float f, float f5) {
        this.mDeltaX = f - this.mLastTouchX;
        this.mDeltaY = f5 - this.mLastTouchY;
        this.mLastTouchX = f;
        this.mLastTouchY = f5;
    }
}
