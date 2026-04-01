package com.samsung.android.gallery.widget.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.gesture.IPinchGestureListener;
import com.samsung.android.gallery.widget.gesture.PinchGestureDetector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PinchParentLayout extends FrameLayout implements IPinchGestureListener {
    private boolean mInPinchGesture;
    private final PinchGestureDetector mPinchGestureDetector;
    private GalleryPinchView mRecyclerView;

    public PinchParentLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void dispatchCancelTouchEvent(MotionEvent motionEvent) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setAction(3);
        super.dispatchTouchEvent(obtain);
        obtain.recycle();
    }

    private boolean isPenAction(MotionEvent motionEvent) {
        if (motionEvent.getToolType(0) == 2) {
            return true;
        }
        return false;
    }

    private boolean isPinchDisabled(GalleryListAdapter<?> galleryListAdapter) {
        if (galleryListAdapter == null || !galleryListAdapter.isPinchSupported()) {
            return true;
        }
        return false;
    }

    public boolean canPinch(boolean z) {
        GalleryPinchView galleryPinchView = this.mRecyclerView;
        if (galleryPinchView == null) {
            Log.e("PinchParentLayout", "can't pinch null list");
            return false;
        }
        GalleryListAdapter adapter = galleryPinchView.getAdapter();
        if (adapter == null || !adapter.supportPinch(z) || this.mRecyclerView.isDrawerAnimating()) {
            return false;
        }
        return this.mRecyclerView.getGridSpans().canPinch(z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x009e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x009f A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchTouchEvent(android.view.MotionEvent r8) {
        /*
            r7 = this;
            boolean r0 = r7.isPenAction(r8)
            if (r0 == 0) goto L_0x000b
            boolean r7 = super.dispatchTouchEvent(r8)
            return r7
        L_0x000b:
            int r0 = r8.getPointerCount()
            int r1 = r8.getActionMasked()
            java.lang.String r2 = "}"
            java.lang.String r3 = "PinchParentLayout"
            r4 = 0
            r5 = 1
            if (r1 == 0) goto L_0x0052
            if (r1 == r5) goto L_0x002e
            r6 = 5
            if (r1 == r6) goto L_0x0052
            r0 = 6
            if (r1 == r0) goto L_0x002e
            boolean r0 = r7.mInPinchGesture
            if (r0 == 0) goto L_0x008e
            com.samsung.android.gallery.widget.gesture.PinchGestureDetector r7 = r7.mPinchGestureDetector
            r7.onTouchEvent(r8)
            return r5
        L_0x002e:
            boolean r0 = r7.mInPinchGesture
            if (r0 == 0) goto L_0x008e
            com.samsung.android.gallery.widget.gesture.PinchGestureDetector r0 = r7.mPinchGestureDetector
            r0.onTouchEvent(r8)
            r7.mInPinchGesture = r4
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r0 = "handled {pinch-up,"
            r7.<init>(r0)
            int r8 = r8.getActionMasked()
            r7.append(r8)
            r7.append(r2)
            java.lang.String r7 = r7.toString()
            com.samsung.android.gallery.support.utils.Log.d(r3, r7)
            return r5
        L_0x0052:
            com.samsung.android.gallery.widget.listview.GalleryPinchView r1 = r7.mRecyclerView
            if (r1 == 0) goto L_0x0061
            com.samsung.android.gallery.widget.listview.GalleryListAdapter r1 = r1.getAdapter()
            boolean r1 = r7.isPinchDisabled(r1)
            if (r1 == 0) goto L_0x0061
            goto L_0x008e
        L_0x0061:
            boolean r1 = r7.mInPinchGesture
            if (r1 != 0) goto L_0x008e
            com.samsung.android.gallery.widget.gesture.PinchGestureDetector r1 = r7.mPinchGestureDetector
            boolean r1 = r1.onTouchEvent(r8)
            if (r1 == 0) goto L_0x008e
            r1 = 2
            if (r0 < r1) goto L_0x008e
            r7.dispatchCancelTouchEvent(r8)
            r7.mInPinchGesture = r5
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r0 = "handled {pinch-down,"
            r7.<init>(r0)
            int r8 = r8.getActionMasked()
            r7.append(r8)
            r7.append(r2)
            java.lang.String r7 = r7.toString()
            com.samsung.android.gallery.support.utils.Log.d(r3, r7)
            return r5
        L_0x008e:
            com.samsung.android.gallery.widget.listview.GalleryPinchView r0 = r7.mRecyclerView
            if (r0 == 0) goto L_0x0098
            boolean r0 = r0.handleActivePinchAnimation(r8)
            if (r0 != 0) goto L_0x009e
        L_0x0098:
            boolean r7 = super.dispatchTouchEvent(r8)
            if (r7 == 0) goto L_0x009f
        L_0x009e:
            return r5
        L_0x009f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.listview.PinchParentLayout.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mRecyclerView == null) {
            this.mRecyclerView = (GalleryPinchView) findViewById(R$id.my_recycler_view);
        }
    }

    public void onScale(float f) {
        GalleryPinchView galleryPinchView = this.mRecyclerView;
        if (galleryPinchView != null) {
            galleryPinchView.onScale(this.mPinchGestureDetector, f);
        }
    }

    public boolean onScaleBegin(float[] fArr) {
        GalleryPinchView galleryPinchView = this.mRecyclerView;
        if (galleryPinchView == null || !galleryPinchView.onScaleBegin(fArr)) {
            return false;
        }
        return true;
    }

    public void onScaleEnd() {
        GalleryPinchView galleryPinchView = this.mRecyclerView;
        if (galleryPinchView != null) {
            galleryPinchView.onScaleEnd();
        }
    }

    public PinchParentLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mPinchGestureDetector = new PinchGestureDetector(context, this);
    }
}
