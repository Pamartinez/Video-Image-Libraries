package com.samsung.android.gallery.widget.utils;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryTouchDelegate extends TouchDelegate {
    private final View view;

    public GalleryTouchDelegate(Rect rect, View view2) {
        super(rect, view2);
        this.view = view2;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!ViewUtils.isVisible(this.view)) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }
}
