package com.samsung.android.gallery.widget;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.widget.NestedScrollView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DetailsNestedScrollView extends NestedScrollView {
    private float mDragStartX;
    private float mDragStartY;
    private boolean mEnableVerticalPreScroll = false;
    private int mTouchSlop;

    public DetailsNestedScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void init(Context context) {
        this.mTouchSlop = ViewConfiguration.get(context).getScaledPagingTouchSlop();
    }

    public boolean dispatchNestedPreScroll(int i2, int i7, int[] iArr, int[] iArr2) {
        return (i2 != 0 || this.mEnableVerticalPreScroll) && super.dispatchNestedPreScroll(i2, i7, iArr, iArr2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000e, code lost:
        if (r0 != 3) goto L_0x004c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getActionMasked()
            if (r0 == 0) goto L_0x0040
            r1 = 0
            r2 = 1
            if (r0 == r2) goto L_0x003d
            r3 = 2
            if (r0 == r3) goto L_0x0011
            r2 = 3
            if (r0 == r2) goto L_0x003d
            goto L_0x004c
        L_0x0011:
            boolean r0 = r5.mEnableVerticalPreScroll
            if (r0 != 0) goto L_0x004c
            float r0 = r5.mDragStartX
            float r3 = r6.getX()
            float r0 = r0 - r3
            float r0 = java.lang.Math.abs(r0)
            float r3 = r5.mDragStartY
            float r4 = r6.getY()
            float r3 = r3 - r4
            float r3 = java.lang.Math.abs(r3)
            int r4 = r5.mTouchSlop
            float r4 = (float) r4
            int r4 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r4 <= 0) goto L_0x003a
            r4 = 1056964608(0x3f000000, float:0.5)
            float r3 = r3 * r4
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x003a
            r1 = r2
        L_0x003a:
            r5.mEnableVerticalPreScroll = r1
            goto L_0x004c
        L_0x003d:
            r5.mEnableVerticalPreScroll = r1
            goto L_0x004c
        L_0x0040:
            float r0 = r6.getX()
            r5.mDragStartX = r0
            float r0 = r6.getY()
            r5.mDragStartY = r0
        L_0x004c:
            boolean r5 = super.onInterceptTouchEvent(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.DetailsNestedScrollView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(new View.BaseSavedState(parcelable));
    }

    public boolean onStartNestedScroll(View view, View view2, int i2) {
        if (!this.mEnableVerticalPreScroll || !super.onStartNestedScroll(view, view2, i2)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000e, code lost:
        if (r0 != 3) goto L_0x004c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getActionMasked()
            if (r0 == 0) goto L_0x0040
            r1 = 0
            r2 = 1
            if (r0 == r2) goto L_0x003d
            r3 = 2
            if (r0 == r3) goto L_0x0011
            r2 = 3
            if (r0 == r2) goto L_0x003d
            goto L_0x004c
        L_0x0011:
            boolean r0 = r5.mEnableVerticalPreScroll
            if (r0 != 0) goto L_0x004c
            float r0 = r5.mDragStartX
            float r3 = r6.getX()
            float r0 = r0 - r3
            float r0 = java.lang.Math.abs(r0)
            float r3 = r5.mDragStartY
            float r4 = r6.getY()
            float r3 = r3 - r4
            float r3 = java.lang.Math.abs(r3)
            int r4 = r5.mTouchSlop
            float r4 = (float) r4
            int r4 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r4 <= 0) goto L_0x003a
            r4 = 1056964608(0x3f000000, float:0.5)
            float r3 = r3 * r4
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x003a
            r1 = r2
        L_0x003a:
            r5.mEnableVerticalPreScroll = r1
            goto L_0x004c
        L_0x003d:
            r5.mEnableVerticalPreScroll = r1
            goto L_0x004c
        L_0x0040:
            float r0 = r6.getX()
            r5.mDragStartX = r0
            float r0 = r6.getY()
            r5.mDragStartY = r0
        L_0x004c:
            boolean r5 = super.onTouchEvent(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.DetailsNestedScrollView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean dispatchNestedPreScroll(int i2, int i7, int[] iArr, int[] iArr2, int i8) {
        return (i2 != 0 || this.mEnableVerticalPreScroll) && super.dispatchNestedPreScroll(i2, i7, iArr, iArr2, i8);
    }
}
