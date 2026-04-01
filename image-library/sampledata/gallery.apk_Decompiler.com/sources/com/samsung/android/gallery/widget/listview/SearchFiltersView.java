package com.samsung.android.gallery.widget.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.R$bool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchFiltersView extends RecyclerView {
    private boolean mDelegateTouchEvent;
    private float mInitialX;
    private float mStartX;
    private OnTouchDelegator mTouchDelegator;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnTouchDelegator {
        boolean isOnlyThemCollapsed();

        boolean isOnlyThemVisible();

        boolean onDelegateTouch(MotionEvent motionEvent, float f);

        boolean supportDelegateTouchEvent();
    }

    public SearchFiltersView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void requestDisallowInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            requestDisallowInterceptTouchEvent(true);
        } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            requestDisallowInterceptTouchEvent(false);
        }
    }

    private boolean supportDelegate() {
        OnTouchDelegator onTouchDelegator = this.mTouchDelegator;
        if (onTouchDelegator == null || !onTouchDelegator.supportDelegateTouchEvent()) {
            return false;
        }
        return true;
    }

    public boolean checkRecyclerViewScrollable(MotionEvent motionEvent) {
        View findViewByPosition;
        OnTouchDelegator onTouchDelegator;
        try {
            RecyclerView.LayoutManager layoutManager = getLayoutManager();
            if (!(!(layoutManager instanceof LinearLayoutManager) || (findViewByPosition = layoutManager.findViewByPosition(0)) == null || (onTouchDelegator = this.mTouchDelegator) == null)) {
                if (onTouchDelegator.isOnlyThemCollapsed()) {
                    if (findViewByPosition.getX() >= 0.0f) {
                        if (motionEvent.getX() - this.mInitialX > 0.0f) {
                            return false;
                        }
                    }
                    return true;
                } else if (motionEvent.getX() - this.mInitialX > 0.0f) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Error | Exception unused) {
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004a, code lost:
        if (r0 != 3) goto L_0x0052;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            boolean r0 = r4.supportDelegate()
            if (r0 != 0) goto L_0x000b
            boolean r4 = super.dispatchTouchEvent(r5)
            return r4
        L_0x000b:
            int r0 = r5.getAction()
            if (r0 != 0) goto L_0x0017
            float r0 = r5.getX()
            r4.mInitialX = r0
        L_0x0017:
            r4.requestDisallowInterceptTouchEvent(r5)
            boolean r0 = r4.mDelegateTouchEvent
            r1 = 1
            if (r0 != 0) goto L_0x003e
            float r0 = r5.getX()
            float r2 = r4.mInitialX
            float r0 = r0 - r2
            float r0 = java.lang.Math.abs(r0)
            r2 = 1098907648(0x41800000, float:16.0)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L_0x003e
            boolean r0 = r4.checkRecyclerViewScrollable(r5)
            if (r0 != 0) goto L_0x003e
            r4.mDelegateTouchEvent = r1
            float r0 = r5.getRawX()
            r4.mStartX = r0
        L_0x003e:
            boolean r0 = r4.mDelegateTouchEvent
            if (r0 == 0) goto L_0x0065
            int r0 = r5.getAction()
            r2 = 0
            r3 = 3
            if (r0 == r1) goto L_0x004d
            if (r0 == r3) goto L_0x0050
            goto L_0x0052
        L_0x004d:
            r5.setAction(r3)
        L_0x0050:
            r4.mDelegateTouchEvent = r2
        L_0x0052:
            com.samsung.android.gallery.widget.listview.SearchFiltersView$OnTouchDelegator r0 = r4.mTouchDelegator
            float r3 = r4.mStartX
            boolean r0 = r0.onDelegateTouch(r5, r3)
            if (r0 != 0) goto L_0x0064
            boolean r4 = super.dispatchTouchEvent(r5)
            if (r4 == 0) goto L_0x0063
            goto L_0x0064
        L_0x0063:
            return r2
        L_0x0064:
            return r1
        L_0x0065:
            boolean r4 = super.dispatchTouchEvent(r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.listview.SearchFiltersView.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    public float getLeftFadingEdgeStrength() {
        OnTouchDelegator onTouchDelegator = this.mTouchDelegator;
        if (onTouchDelegator == null || !onTouchDelegator.isOnlyThemVisible()) {
            return super.getLeftFadingEdgeStrength();
        }
        if (getResources().getBoolean(R$bool.is_right_to_left)) {
            return super.getLeftFadingEdgeStrength();
        }
        return 0.0f;
    }

    public float getRightFadingEdgeStrength() {
        OnTouchDelegator onTouchDelegator = this.mTouchDelegator;
        if (onTouchDelegator == null || !onTouchDelegator.isOnlyThemVisible()) {
            return super.getRightFadingEdgeStrength();
        }
        if (getResources().getBoolean(R$bool.is_right_to_left)) {
            return 0.0f;
        }
        return super.getRightFadingEdgeStrength();
    }

    public void setOnTouchDelegator(OnTouchDelegator onTouchDelegator) {
        this.mTouchDelegator = onTouchDelegator;
    }
}
