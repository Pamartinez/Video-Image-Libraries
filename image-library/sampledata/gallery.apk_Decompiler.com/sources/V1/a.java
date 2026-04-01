package V1;

import D0.f;
import android.view.View;
import android.view.ViewParent;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.material.behavior.SwipeDismissBehavior;
import z2.k;
import z2.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends ViewDragHelper.Callback {

    /* renamed from: a  reason: collision with root package name */
    public int f873a;
    public int b = -1;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ SwipeDismissBehavior f874c;

    public a(SwipeDismissBehavior swipeDismissBehavior) {
        this.f874c = swipeDismissBehavior;
    }

    public final int clampViewPositionHorizontal(View view, int i2, int i7) {
        boolean z;
        int i8;
        int i10;
        int width;
        if (ViewCompat.getLayoutDirection(view) == 1) {
            z = true;
        } else {
            z = false;
        }
        int i11 = this.f874c.f1402h;
        if (i11 != 0) {
            if (i11 != 1) {
                i8 = this.f873a - view.getWidth();
                i10 = this.f873a + view.getWidth();
            } else if (z) {
                i8 = this.f873a;
                width = view.getWidth();
            } else {
                i8 = this.f873a - view.getWidth();
                i10 = this.f873a;
            }
            return Math.min(Math.max(i8, i2), i10);
        } else if (z) {
            i8 = this.f873a - view.getWidth();
            i10 = this.f873a;
            return Math.min(Math.max(i8, i2), i10);
        } else {
            i8 = this.f873a;
            width = view.getWidth();
        }
        i10 = width + i8;
        return Math.min(Math.max(i8, i2), i10);
    }

    public final int clampViewPositionVertical(View view, int i2, int i7) {
        return view.getTop();
    }

    public final int getViewHorizontalDragRange(View view) {
        return view.getWidth();
    }

    public final void onViewCaptured(View view, int i2) {
        this.b = i2;
        this.f873a = view.getLeft();
        ViewParent parent = view.getParent();
        if (parent != null) {
            SwipeDismissBehavior swipeDismissBehavior = this.f874c;
            swipeDismissBehavior.g = true;
            parent.requestDisallowInterceptTouchEvent(true);
            swipeDismissBehavior.g = false;
        }
    }

    public final void onViewDragStateChanged(int i2) {
        l lVar = this.f874c.e;
        if (lVar != null) {
            k kVar = lVar.f2211a.f2225x;
            if (i2 == 0) {
                f.F().S(kVar);
            } else if (i2 == 1 || i2 == 2) {
                f.F().Q(kVar);
            }
        }
    }

    public final void onViewPositionChanged(View view, int i2, int i7, int i8, int i10) {
        SwipeDismissBehavior swipeDismissBehavior = this.f874c;
        float width = ((float) view.getWidth()) * swipeDismissBehavior.f1404j;
        float width2 = ((float) view.getWidth()) * swipeDismissBehavior.k;
        float abs = (float) Math.abs(i2 - this.f873a);
        if (abs <= width) {
            view.setAlpha(1.0f);
        } else if (abs >= width2) {
            view.setAlpha(0.0f);
        } else {
            view.setAlpha(Math.min(Math.max(0.0f, 1.0f - ((abs - width) / (width2 - width))), 1.0f));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004e, code lost:
        if (java.lang.Math.abs(r9.getLeft() - r8.f873a) >= java.lang.Math.round(((float) r9.getWidth()) * r3.f1403i)) goto L_0x0050;
     */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x007d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onViewReleased(android.view.View r9, float r10, float r11) {
        /*
            r8 = this;
            r11 = -1
            r8.b = r11
            int r11 = r9.getWidth()
            r0 = 0
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            r2 = 0
            com.google.android.material.behavior.SwipeDismissBehavior r3 = r8.f874c
            r4 = 1
            if (r1 == 0) goto L_0x0037
            int r5 = androidx.core.view.ViewCompat.getLayoutDirection(r9)
            if (r5 != r4) goto L_0x0018
            r5 = r4
            goto L_0x0019
        L_0x0018:
            r5 = r2
        L_0x0019:
            int r6 = r3.f1402h
            r7 = 2
            if (r6 != r7) goto L_0x001f
            goto L_0x0050
        L_0x001f:
            if (r6 != 0) goto L_0x002b
            if (r5 == 0) goto L_0x0028
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 >= 0) goto L_0x0065
            goto L_0x0050
        L_0x0028:
            if (r1 <= 0) goto L_0x0065
            goto L_0x0050
        L_0x002b:
            if (r6 != r4) goto L_0x0065
            if (r5 == 0) goto L_0x0032
            if (r1 <= 0) goto L_0x0065
            goto L_0x0050
        L_0x0032:
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 >= 0) goto L_0x0065
            goto L_0x0050
        L_0x0037:
            int r1 = r9.getLeft()
            int r5 = r8.f873a
            int r1 = r1 - r5
            int r5 = r9.getWidth()
            float r5 = (float) r5
            float r6 = r3.f1403i
            float r5 = r5 * r6
            int r5 = java.lang.Math.round(r5)
            int r1 = java.lang.Math.abs(r1)
            if (r1 < r5) goto L_0x0065
        L_0x0050:
            int r10 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r10 < 0) goto L_0x005f
            int r10 = r9.getLeft()
            int r0 = r8.f873a
            if (r10 >= r0) goto L_0x005d
            goto L_0x005f
        L_0x005d:
            int r0 = r0 + r11
            goto L_0x0063
        L_0x005f:
            int r8 = r8.f873a
            int r0 = r8 - r11
        L_0x0063:
            r2 = r4
            goto L_0x0067
        L_0x0065:
            int r0 = r8.f873a
        L_0x0067:
            androidx.customview.widget.ViewDragHelper r8 = r3.d
            int r10 = r9.getTop()
            boolean r8 = r8.settleCapturedViewAt(r0, r10)
            if (r8 == 0) goto L_0x007d
            V1.c r8 = new V1.c
            r10 = 0
            r8.<init>(r3, r9, r2, r10)
            androidx.core.view.ViewCompat.postOnAnimation(r9, r8)
            return
        L_0x007d:
            if (r2 == 0) goto L_0x0086
            z2.l r8 = r3.e
            if (r8 == 0) goto L_0x0086
            r8.a(r9)
        L_0x0086:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: V1.a.onViewReleased(android.view.View, float, float):void");
    }

    public final boolean tryCaptureView(View view, int i2) {
        int i7 = this.b;
        if ((i7 == -1 || i7 == i2) && this.f874c.a(view)) {
            return true;
        }
        return false;
    }
}
