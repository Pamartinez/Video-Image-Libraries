package com.samsung.android.gallery.plugins.compare;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GridView extends GridLayout {
    private InterceptTouchEventListener mDispatchTouchEventListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface InterceptTouchEventListener {
        boolean onInterceptTouchEvent(MotionEvent motionEvent);
    }

    public GridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        InterceptTouchEventListener interceptTouchEventListener = this.mDispatchTouchEventListener;
        if (interceptTouchEventListener != null) {
            return interceptTouchEventListener.onInterceptTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final void setInterceptTouchEventListener(InterceptTouchEventListener interceptTouchEventListener) {
        this.mDispatchTouchEventListener = interceptTouchEventListener;
    }
}
