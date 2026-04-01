package com.samsung.android.gallery.widget.listview.scroller;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.FastScrollerBgDrawable;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$drawable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FastScrollerV2 extends FastScroller {
    private final int mEndPadding;
    private final int mFastScrollThumbHeight;
    private Drawable mThumbDrawable;
    private final FastScrollerV2ThumbAnimator mThumbWidthAnimator;
    private int mTouchOffset;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FastScrollerV2(androidx.recyclerview.widget.RecyclerView r8, android.view.View r9, java.lang.String r10, int r11, int r12) {
        /*
            r7 = this;
            r4 = -1
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r5 = r11
            r6 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r10 = r5
            r11 = r6
            android.content.res.Resources r7 = r1.getResources()
            int r8 = com.samsung.android.gallery.widget.R$dimen.fast_scroll_end_padding
            int r7 = r7.getDimensionPixelOffset(r8)
            r0.mEndPadding = r7
            android.content.res.Resources r7 = r1.getResources()
            int r8 = com.samsung.android.gallery.widget.R$dimen.fast_scroll_thumb_default_height
            int r7 = r7.getDimensionPixelOffset(r8)
            r0.mFastScrollThumbHeight = r7
            android.content.Context r7 = r1.getContext()
            android.graphics.drawable.Drawable r7 = r0.getScrollDrawable(r7)
            r0.mThumbDrawable = r7
            boolean r7 = r7 instanceof com.samsung.android.gallery.widget.FastScrollerBgDrawable
            if (r7 == 0) goto L_0x0054
            com.samsung.android.gallery.widget.listview.scroller.FastScrollerV2ThumbAnimator r7 = new com.samsung.android.gallery.widget.listview.scroller.FastScrollerV2ThumbAnimator
            android.content.Context r8 = r1.getContext()
            android.graphics.drawable.Drawable r9 = r0.mThumbDrawable
            com.samsung.android.gallery.widget.FastScrollerBgDrawable r9 = (com.samsung.android.gallery.widget.FastScrollerBgDrawable) r9
            androidx.recyclerview.widget.RecyclerView r12 = r0.mRecyclerView
            java.util.Objects.requireNonNull(r12)
            r1 = r12
            f0.a r12 = new f0.a
            r2 = 0
            r12.<init>(r1, r2)
            r7.<init>(r8, r9, r10, r11, r12)
            r0.mThumbWidthAnimator = r7
            int r8 = r0.mVerticalThumbHeight
            float r8 = (float) r8
            r7.setThumbHeight(r8)
            return
        L_0x0054:
            r7 = 0
            r0.mThumbWidthAnimator = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.listview.scroller.FastScrollerV2.<init>(androidx.recyclerview.widget.RecyclerView, android.view.View, java.lang.String, int, int):void");
    }

    public int calculateScrollbarHeight() {
        return this.mFastScrollThumbHeight;
    }

    public Drawable getScrollDrawable(Context context) {
        if (this.mThumbDrawable == null) {
            this.mThumbDrawable = context.getDrawable(R$drawable.fastscroll_thumb_mtrl_alpha_v2);
        }
        return this.mThumbDrawable;
    }

    public int getScrollDrawableWidth(Drawable drawable) {
        if (drawable instanceof FastScrollerBgDrawable) {
            return (int) ((FastScrollerBgDrawable) drawable).getWidth();
        }
        return 0;
    }

    public int getTouchArea(Context context, int i2) {
        return context.getResources().getDimensionPixelOffset(R$dimen.fast_scroll_popup_additional_touch_area) + context.getResources().getDimensionPixelOffset(androidx.recyclerview.R$dimen.sesl_fast_scroller_thumb_max_width) + i2;
    }

    public int getTouchOffset() {
        return this.mTouchOffset;
    }

    public int getVerticalScrollbarStart() {
        if (isLayoutRTL()) {
            return this.mVerticalThumbWidth + this.mEndPadding;
        }
        return (this.mRecyclerViewWidth - this.mVerticalThumbWidth) - this.mEndPadding;
    }

    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        super.onTouchEvent(recyclerView, motionEvent);
        if (motionEvent.getAction() == 0) {
            this.mTouchOffset = (int) ((((float) this.mVerticalThumbHeight) / 2.0f) - (motionEvent.getY() - ((float) this.mVerticalThumbTop)));
        } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            this.mTouchOffset = 0;
        }
    }

    public void setScrollbarHeight(RecyclerView recyclerView) {
        super.setScrollbarHeight(recyclerView);
        FastScrollerV2ThumbAnimator fastScrollerV2ThumbAnimator = this.mThumbWidthAnimator;
        if (fastScrollerV2ThumbAnimator != null) {
            fastScrollerV2ThumbAnimator.setThumbHeight((float) this.mVerticalThumbHeight);
        }
    }

    public void setState(int i2) {
        if (this.mState != 1 && i2 == 1) {
            resetScrollbarHeight();
            setScrollbarHeight(this.mRecyclerView);
        }
        super.setState(i2);
    }

    public void updateScrollbarState(boolean z) {
        FastScrollerV2ThumbAnimator fastScrollerV2ThumbAnimator = this.mThumbWidthAnimator;
        if (fastScrollerV2ThumbAnimator != null) {
            fastScrollerV2ThumbAnimator.dispose();
            this.mThumbWidthAnimator.setThumbHeight((float) this.mVerticalThumbHeight);
            this.mThumbWidthAnimator.setDragging(z);
        }
    }
}
