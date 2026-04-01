package com.samsung.android.gallery.widget.utils;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TouchDelegateComposite extends TouchDelegate {
    private static final Rect emptyRect = new Rect();
    private final HashMap<Integer, GalleryTouchDelegate> mMap = new HashMap<>();

    public TouchDelegateComposite(View view) {
        super(emptyRect, view);
    }

    public void addDelegate(Rect rect, View view) {
        if (this.mMap.size() < 50) {
            this.mMap.put(Integer.valueOf(view.getId()), new GalleryTouchDelegate(rect, view));
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x9 = motionEvent.getX();
        float y = motionEvent.getY();
        Iterator<GalleryTouchDelegate> it = this.mMap.values().iterator();
        while (true) {
            boolean z = false;
            while (true) {
                if (!it.hasNext()) {
                    return z;
                }
                TouchDelegate next = it.next();
                motionEvent.setLocation(x9, y);
                if (next == null || !next.onTouchEvent(motionEvent)) {
                    if (z) {
                    }
                }
                z = true;
            }
        }
    }
}
