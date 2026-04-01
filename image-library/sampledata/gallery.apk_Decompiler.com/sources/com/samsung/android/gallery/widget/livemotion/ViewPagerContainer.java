package com.samsung.android.gallery.widget.livemotion;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.samsung.android.gallery.widget.listview.InterceptDispatchTouchListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewPagerContainer extends FrameLayout {
    private InterceptDispatchTouchListener mDispatchTouchInterceptor;

    public ViewPagerContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        InterceptDispatchTouchListener interceptDispatchTouchListener = this.mDispatchTouchInterceptor;
        if (interceptDispatchTouchListener == null || !interceptDispatchTouchListener.supportHover() || !super.dispatchHoverEvent(motionEvent)) {
            return false;
        }
        return true;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        InterceptDispatchTouchListener interceptDispatchTouchListener = this.mDispatchTouchInterceptor;
        if (interceptDispatchTouchListener != null) {
            return interceptDispatchTouchListener.onInterceptDispatchTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean hasTouchDelegate() {
        if (this.mDispatchTouchInterceptor != null) {
            return true;
        }
        return false;
    }

    public void setDispatchTouchInterceptor(InterceptDispatchTouchListener interceptDispatchTouchListener) {
        this.mDispatchTouchInterceptor = interceptDispatchTouchListener;
    }
}
