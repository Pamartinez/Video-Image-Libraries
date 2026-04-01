package com.samsung.android.gallery.widget.photoview;

import android.content.Context;
import android.view.GestureDetector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PhotoViewGestureDetector extends GestureDetector {
    private final PhotoViewGestureDetectorListener mListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PhotoViewGestureDetectorListener extends GestureDetector.SimpleOnGestureListener {
        private boolean mEnabled = true;

        public boolean isEnabled() {
            return this.mEnabled;
        }

        public void makeDisable() {
            this.mEnabled = false;
        }
    }

    public PhotoViewGestureDetector(Context context, PhotoViewGestureDetectorListener photoViewGestureDetectorListener) {
        super(context, photoViewGestureDetectorListener);
        this.mListener = photoViewGestureDetectorListener;
    }

    public void makeDisable() {
        this.mListener.makeDisable();
    }
}
