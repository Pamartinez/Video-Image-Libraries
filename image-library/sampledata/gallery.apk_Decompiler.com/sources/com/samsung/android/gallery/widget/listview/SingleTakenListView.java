package com.samsung.android.gallery.widget.listview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SingleTakenListView extends GalleryListView {
    private boolean mDelegateTouchEvent;
    private float mInitialX;
    private float mInitialY;
    private boolean mIsBlockMoveEvent = false;
    /* access modifiers changed from: private */
    public boolean mIsTopEnd = true;
    private onDelegateTouchListener mOnDelegateTouchListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface onDelegateTouchListener {
        boolean onDelegateTouch(MotionEvent motionEvent);

        void onStartDelegate(MotionEvent motionEvent);

        boolean supportDelegateTouchEvent();

        boolean supportFlingDownTouchEvent();
    }

    public SingleTakenListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setClipToPadding(false);
        setFadingEdge(GalleryRecyclerView.FadingEdgeDirection.NONE);
        addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                SingleTakenListView.this.playPreview();
            }

            public void onScrolled(RecyclerView recyclerView, int i2, int i7) {
                if (i7 != 0) {
                    if (!SingleTakenListView.this.canScrollVertically(i7)) {
                        SingleTakenListView.this.stopScroll();
                        if (i7 < 0) {
                            SingleTakenListView.this.mIsTopEnd = true;
                            return;
                        }
                        return;
                    }
                    SingleTakenListView.this.mIsTopEnd = false;
                }
            }
        });
    }

    private boolean checkConsumeTouchEvent(MotionEvent motionEvent) {
        if (this.mOnDelegateTouchListener.supportDelegateTouchEvent() && motionEvent.getAction() == 2) {
            float x9 = motionEvent.getX() - this.mInitialX;
            float y = motionEvent.getY() - this.mInitialY;
            if (!this.mIsTopEnd || y <= 16.0f) {
                if (!this.mOnDelegateTouchListener.supportFlingDownTouchEvent()) {
                    return false;
                }
                Rect rect = new Rect();
                getGlobalVisibleRect(rect);
                if (((float) rect.top) > motionEvent.getRawY()) {
                    return true;
                }
            } else if (Math.abs(y) > Math.abs(x9)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private void resetBlockStartDrag() {
        GalleryListAdapter adapter = getAdapter();
        if (adapter != null) {
            adapter.resetBlockStartDrag();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        if (r0 != 3) goto L_0x002e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            com.samsung.android.gallery.widget.listview.SingleTakenListView$onDelegateTouchListener r0 = r4.mOnDelegateTouchListener
            if (r0 != 0) goto L_0x0009
            boolean r4 = super.dispatchTouchEvent(r5)
            return r4
        L_0x0009:
            int r0 = r5.getAction()
            r1 = 3
            r2 = 1
            if (r0 == 0) goto L_0x0022
            if (r0 == r2) goto L_0x001e
            r3 = 2
            if (r0 == r3) goto L_0x0019
            if (r0 == r1) goto L_0x001e
            goto L_0x002e
        L_0x0019:
            boolean r0 = r4.mIsBlockMoveEvent
            if (r0 == 0) goto L_0x002e
            return r2
        L_0x001e:
            r4.resetBlockStartDrag()
            goto L_0x002e
        L_0x0022:
            float r0 = r5.getX()
            r4.mInitialX = r0
            float r0 = r5.getY()
            r4.mInitialY = r0
        L_0x002e:
            r0 = 0
            r4.mIsBlockMoveEvent = r0
            boolean r3 = r4.mDelegateTouchEvent
            if (r3 != 0) goto L_0x0042
            boolean r3 = r4.checkConsumeTouchEvent(r5)
            if (r3 == 0) goto L_0x0042
            r4.mDelegateTouchEvent = r2
            com.samsung.android.gallery.widget.listview.SingleTakenListView$onDelegateTouchListener r3 = r4.mOnDelegateTouchListener
            r3.onStartDelegate(r5)
        L_0x0042:
            boolean r3 = r4.mDelegateTouchEvent
            if (r3 == 0) goto L_0x005b
            int r3 = r5.getAction()
            if (r3 != r2) goto L_0x0054
            r4.mDelegateTouchEvent = r0
            r5.setAction(r1)
            super.dispatchTouchEvent(r5)
        L_0x0054:
            com.samsung.android.gallery.widget.listview.SingleTakenListView$onDelegateTouchListener r4 = r4.mOnDelegateTouchListener
            boolean r4 = r4.onDelegateTouch(r5)
            return r4
        L_0x005b:
            boolean r4 = super.dispatchTouchEvent(r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.listview.SingleTakenListView.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    public void playPreview() {
        GalleryListAdapter adapter = getAdapter();
        if (adapter != null && getScrollState() == 0) {
            adapter.setVisibleArea(findFirstVisibleItemPositionCompat(), findLastVisibleItemPositionCompat());
        }
    }

    public void setOnDelegateTouchListener(onDelegateTouchListener ondelegatetouchlistener) {
        this.mOnDelegateTouchListener = ondelegatetouchlistener;
    }
}
