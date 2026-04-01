package com.google.android.material.carousel;

import Bb.g;
import Q1.a;
import a2.C0070b;
import a2.C0071c;
import a2.C0072d;
import a2.C0073e;
import a2.C0076h;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.R$styleable;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CarouselLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {
    public final C0076h d;
    public C0073e e;
    public final View.OnLayoutChangeListener f;

    public CarouselLayoutManager() {
        C0076h hVar = new C0076h();
        new C0070b();
        this.f = new g(6, this);
        this.d = hVar;
        requestLayout();
        setOrientation(0);
    }

    public final boolean a() {
        if (this.e.f974a == 0) {
            return true;
        }
        return false;
    }

    public final boolean canScrollHorizontally() {
        return a();
    }

    public final boolean canScrollVertically() {
        return !a();
    }

    public final int computeHorizontalScrollExtent(RecyclerView.State state) {
        getChildCount();
        return 0;
    }

    public final int computeHorizontalScrollOffset(RecyclerView.State state) {
        return 0;
    }

    public final int computeHorizontalScrollRange(RecyclerView.State state) {
        return 0;
    }

    public final PointF computeScrollVectorForPosition(int i2) {
        return null;
    }

    public final int computeVerticalScrollExtent(RecyclerView.State state) {
        getChildCount();
        return 0;
    }

    public final int computeVerticalScrollOffset(RecyclerView.State state) {
        return 0;
    }

    public final int computeVerticalScrollRange(RecyclerView.State state) {
        return 0;
    }

    public final RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    public final void getDecoratedBoundsWithMargins(View view, Rect rect) {
        super.getDecoratedBoundsWithMargins(view, rect);
        rect.centerY();
        if (a()) {
            rect.centerX();
        }
        throw null;
    }

    public final boolean isAutoMeasureEnabled() {
        return true;
    }

    public final boolean isLayoutRtl() {
        if (!a() || getLayoutDirection() != 1) {
            return false;
        }
        return true;
    }

    public final void measureChildWithMargins(View view, int i2, int i7) {
        throw new IllegalStateException("All children of a RecyclerView using CarouselLayoutManager must use MaskableFrameLayout as their root ViewGroup.");
    }

    public final void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        Context context = recyclerView.getContext();
        C0076h hVar = this.d;
        float f5 = hVar.f975a;
        if (f5 <= 0.0f) {
            f5 = context.getResources().getDimension(R.dimen.m3_carousel_small_item_size_min);
        }
        hVar.f975a = f5;
        float f8 = hVar.b;
        if (f8 <= 0.0f) {
            f8 = context.getResources().getDimension(R.dimen.m3_carousel_small_item_size_max);
        }
        hVar.b = f8;
        requestLayout();
        recyclerView.addOnLayoutChangeListener(this.f);
    }

    public final void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        recyclerView.removeOnLayoutChangeListener(this.f);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002e, code lost:
        if (r6 == 1) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0038, code lost:
        if (isLayoutRtl() != false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003c, code lost:
        if (r6 == 1) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0045, code lost:
        if (isLayoutRtl() != false) goto L_0x0030;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View onFocusSearchFailed(android.view.View r4, int r5, androidx.recyclerview.widget.RecyclerView.Recycler r6, androidx.recyclerview.widget.RecyclerView.State r7) {
        /*
            r3 = this;
            int r6 = r3.getChildCount()
            if (r6 != 0) goto L_0x0008
            goto L_0x008a
        L_0x0008:
            a2.e r6 = r3.e
            int r6 = r6.f974a
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = -1
            r1 = 1
            if (r5 == r1) goto L_0x003a
            r2 = 2
            if (r5 == r2) goto L_0x0030
            r2 = 17
            if (r5 == r2) goto L_0x003f
            r2 = 33
            if (r5 == r2) goto L_0x003c
            r2 = 66
            if (r5 == r2) goto L_0x0032
            r2 = 130(0x82, float:1.82E-43)
            if (r5 == r2) goto L_0x002e
            java.lang.String r6 = "CarouselLayoutManager"
            java.lang.String r2 = "Unknown focus request:"
            c0.C0086a.C(r5, r2, r6)
        L_0x002c:
            r5 = r7
            goto L_0x0048
        L_0x002e:
            if (r6 != r1) goto L_0x002c
        L_0x0030:
            r5 = r1
            goto L_0x0048
        L_0x0032:
            if (r6 != 0) goto L_0x002c
            boolean r5 = r3.isLayoutRtl()
            if (r5 == 0) goto L_0x0030
        L_0x003a:
            r5 = r0
            goto L_0x0048
        L_0x003c:
            if (r6 != r1) goto L_0x002c
            goto L_0x003a
        L_0x003f:
            if (r6 != 0) goto L_0x002c
            boolean r5 = r3.isLayoutRtl()
            if (r5 == 0) goto L_0x003a
            goto L_0x0030
        L_0x0048:
            if (r5 != r7) goto L_0x004b
            goto L_0x008a
        L_0x004b:
            r6 = 0
            if (r5 != r0) goto L_0x007f
            int r4 = r3.getPosition(r4)
            if (r4 != 0) goto L_0x0055
            goto L_0x008a
        L_0x0055:
            android.view.View r4 = r3.getChildAt(r6)
            int r4 = r3.getPosition(r4)
            int r4 = r4 - r1
            if (r4 < 0) goto L_0x006e
            int r5 = r3.getItemCount()
            if (r4 < r5) goto L_0x0067
            goto L_0x006e
        L_0x0067:
            a2.e r3 = r3.e
            r3.d()
            r3 = 0
            throw r3
        L_0x006e:
            boolean r4 = r3.isLayoutRtl()
            if (r4 == 0) goto L_0x007a
            int r4 = r3.getChildCount()
            int r6 = r4 + -1
        L_0x007a:
            android.view.View r3 = r3.getChildAt(r6)
            return r3
        L_0x007f:
            int r4 = r3.getPosition(r4)
            int r5 = r3.getItemCount()
            int r5 = r5 - r1
            if (r4 != r5) goto L_0x008c
        L_0x008a:
            r3 = 0
            return r3
        L_0x008c:
            int r4 = r3.getChildCount()
            int r4 = r4 - r1
            android.view.View r4 = r3.getChildAt(r4)
            int r4 = r3.getPosition(r4)
            int r4 = r4 + r1
            if (r4 < 0) goto L_0x00aa
            int r5 = r3.getItemCount()
            if (r4 < r5) goto L_0x00a3
            goto L_0x00aa
        L_0x00a3:
            a2.e r3 = r3.e
            r3.d()
            r3 = 0
            throw r3
        L_0x00aa:
            boolean r4 = r3.isLayoutRtl()
            if (r4 == 0) goto L_0x00b1
            goto L_0x00b7
        L_0x00b1:
            int r4 = r3.getChildCount()
            int r6 = r4 + -1
        L_0x00b7:
            android.view.View r3 = r3.getChildAt(r6)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.carousel.CarouselLayoutManager.onFocusSearchFailed(android.view.View, int, androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State):android.view.View");
    }

    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            accessibilityEvent.setFromIndex(getPosition(getChildAt(0)));
            accessibilityEvent.setToIndex(getPosition(getChildAt(getChildCount() - 1)));
        }
    }

    public final void onItemsAdded(RecyclerView recyclerView, int i2, int i7) {
        super.onItemsAdded(recyclerView, i2, i7);
        getItemCount();
    }

    public final void onItemsRemoved(RecyclerView recyclerView, int i2, int i7) {
        super.onItemsRemoved(recyclerView, i2, i7);
        getItemCount();
    }

    public final void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i2;
        if (state.getItemCount() > 0) {
            if (a()) {
                i2 = getWidth();
            } else {
                i2 = getHeight();
            }
            if (((float) i2) > 0.0f) {
                isLayoutRtl();
                measureChildWithMargins(recycler.getViewForPosition(0), 0, 0);
                throw null;
            }
        }
        removeAndRecycleAllViews(recycler);
    }

    public final void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        if (getChildCount() != 0) {
            getPosition(getChildAt(0));
        }
    }

    public final boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z3) {
        return false;
    }

    public final int scrollHorizontallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (!a() || getChildCount() == 0 || i2 == 0) {
            return 0;
        }
        measureChildWithMargins(recycler.getViewForPosition(0), 0, 0);
        throw null;
    }

    public final int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (!canScrollVertically() || getChildCount() == 0 || i2 == 0) {
            return 0;
        }
        measureChildWithMargins(recycler.getViewForPosition(0), 0, 0);
        throw null;
    }

    public final void setOrientation(int i2) {
        C0073e eVar;
        if (i2 == 0 || i2 == 1) {
            assertNotInLayoutOrScroll((String) null);
            C0073e eVar2 = this.e;
            if (eVar2 == null || i2 != eVar2.f974a) {
                if (i2 == 0) {
                    eVar = new C0072d(this);
                } else if (i2 == 1) {
                    eVar = new C0071c(this);
                } else {
                    throw new IllegalArgumentException("invalid orientation");
                }
                this.e = eVar;
                requestLayout();
                return;
            }
            return;
        }
        throw new IllegalArgumentException(C0086a.i(i2, "invalid orientation:"));
    }

    public final void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i2) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i2);
        startSmoothScroll(linearSmoothScroller);
    }

    public CarouselLayoutManager(Context context, AttributeSet attributeSet, int i2, int i7) {
        new C0070b();
        this.f = new g(6, this);
        this.d = new C0076h();
        requestLayout();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.f);
            obtainStyledAttributes.getInt(0, 0);
            requestLayout();
            setOrientation(obtainStyledAttributes.getInt(R$styleable.RecyclerView_android_orientation, 0));
            obtainStyledAttributes.recycle();
        }
    }

    public final void scrollToPosition(int i2) {
    }
}
