package com.samsung.android.gallery.widget.drawer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DrawerRecyclerView extends RecyclerView {
    private boolean mIsAnimating;

    public DrawerRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.mIsAnimating || super.dispatchTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        super.onGenericMotionEvent(motionEvent);
        if (motionEvent.getAction() == 8) {
            return true;
        }
        return false;
    }

    public void setAnimating(boolean z) {
        this.mIsAnimating = z;
    }
}
