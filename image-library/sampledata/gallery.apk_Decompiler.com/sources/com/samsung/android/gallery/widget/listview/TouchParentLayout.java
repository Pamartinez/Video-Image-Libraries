package com.samsung.android.gallery.widget.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TouchParentLayout extends FrameLayout {
    InterceptTouchListener mOnTouchListener;

    public TouchParentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        InterceptTouchListener interceptTouchListener = this.mOnTouchListener;
        if (interceptTouchListener != null) {
            return interceptTouchListener.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    public void setListener(InterceptTouchListener interceptTouchListener) {
        this.mOnTouchListener = interceptTouchListener;
    }
}
