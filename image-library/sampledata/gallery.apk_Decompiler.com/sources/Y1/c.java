package Y1;

import android.view.View;
import androidx.core.math.MathUtils;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends ViewDragHelper.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BottomSheetBehavior f948a;

    public c(BottomSheetBehavior bottomSheetBehavior) {
        this.f948a = bottomSheetBehavior;
    }

    public final int clampViewPositionHorizontal(View view, int i2, int i7) {
        return view.getLeft();
    }

    public final int clampViewPositionVertical(View view, int i2, int i7) {
        return MathUtils.clamp(i2, this.f948a.getExpandedOffset(), getViewVerticalDragRange(view));
    }

    public final int getViewVerticalDragRange(View view) {
        BottomSheetBehavior bottomSheetBehavior = this.f948a;
        if (bottomSheetBehavior.L) {
            return bottomSheetBehavior.f1417W;
        }
        return bottomSheetBehavior.f1411J;
    }

    public final void onViewDragStateChanged(int i2) {
        if (i2 == 1) {
            BottomSheetBehavior bottomSheetBehavior = this.f948a;
            if (bottomSheetBehavior.f1414N) {
                bottomSheetBehavior.setStateInternal(1);
            }
        }
    }

    public final void onViewPositionChanged(View view, int i2, int i7, int i8, int i10) {
        this.f948a.dispatchOnSlide(i7);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006c, code lost:
        if (java.lang.Math.abs(r5.getTop() - r4.getExpandedOffset()) < java.lang.Math.abs(r5.getTop() - r4.f1409H)) goto L_0x000d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b7, code lost:
        if (java.lang.Math.abs(r6 - r4.f1408G) < java.lang.Math.abs(r6 - r4.f1411J)) goto L_0x000d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001c, code lost:
        if (r6 > r4.f1409H) goto L_0x00e1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onViewReleased(android.view.View r5, float r6, float r7) {
        /*
            r4 = this;
            r0 = 0
            int r1 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            r2 = 6
            r3 = 3
            com.google.android.material.bottomsheet.BottomSheetBehavior r4 = r4.f948a
            if (r1 >= 0) goto L_0x0020
            boolean r6 = r4.e
            if (r6 == 0) goto L_0x0010
        L_0x000d:
            r2 = r3
            goto L_0x00e1
        L_0x0010:
            int r6 = r5.getTop()
            java.lang.System.currentTimeMillis()
            r4.getClass()
            int r7 = r4.f1409H
            if (r6 <= r7) goto L_0x000d
            goto L_0x00e1
        L_0x0020:
            boolean r1 = r4.L
            if (r1 == 0) goto L_0x006f
            boolean r1 = r4.e(r7, r5)
            if (r1 == 0) goto L_0x006f
            float r6 = java.lang.Math.abs(r6)
            float r0 = java.lang.Math.abs(r7)
            int r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r6 >= 0) goto L_0x003d
            int r6 = r4.g
            float r6 = (float) r6
            int r6 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
            if (r6 > 0) goto L_0x004c
        L_0x003d:
            int r6 = r5.getTop()
            int r7 = r4.f1417W
            int r0 = r4.getExpandedOffset()
            int r0 = r0 + r7
            int r0 = r0 / 2
            if (r6 <= r0) goto L_0x004f
        L_0x004c:
            r2 = 5
            goto L_0x00e1
        L_0x004f:
            boolean r6 = r4.e
            if (r6 == 0) goto L_0x0054
            goto L_0x000d
        L_0x0054:
            int r6 = r5.getTop()
            int r7 = r4.getExpandedOffset()
            int r6 = r6 - r7
            int r6 = java.lang.Math.abs(r6)
            int r7 = r5.getTop()
            int r0 = r4.f1409H
            int r7 = r7 - r0
            int r7 = java.lang.Math.abs(r7)
            if (r6 >= r7) goto L_0x00e1
            goto L_0x000d
        L_0x006f:
            int r0 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            r1 = 4
            if (r0 == 0) goto L_0x00a0
            float r6 = java.lang.Math.abs(r6)
            float r7 = java.lang.Math.abs(r7)
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 <= 0) goto L_0x0081
            goto L_0x00a0
        L_0x0081:
            boolean r6 = r4.e
            if (r6 == 0) goto L_0x0087
        L_0x0085:
            r2 = r1
            goto L_0x00e1
        L_0x0087:
            int r6 = r5.getTop()
            int r7 = r4.f1409H
            int r7 = r6 - r7
            int r7 = java.lang.Math.abs(r7)
            int r0 = r4.f1411J
            int r6 = r6 - r0
            int r6 = java.lang.Math.abs(r6)
            if (r7 >= r6) goto L_0x0085
            r4.getClass()
            goto L_0x00e1
        L_0x00a0:
            int r6 = r5.getTop()
            boolean r7 = r4.e
            if (r7 == 0) goto L_0x00bb
            int r7 = r4.f1408G
            int r7 = r6 - r7
            int r7 = java.lang.Math.abs(r7)
            int r0 = r4.f1411J
            int r6 = r6 - r0
            int r6 = java.lang.Math.abs(r6)
            if (r7 >= r6) goto L_0x0085
            goto L_0x000d
        L_0x00bb:
            int r7 = r4.f1409H
            if (r6 >= r7) goto L_0x00cf
            int r7 = r4.f1411J
            int r7 = r6 - r7
            int r7 = java.lang.Math.abs(r7)
            if (r6 >= r7) goto L_0x00cb
            goto L_0x000d
        L_0x00cb:
            r4.getClass()
            goto L_0x00e1
        L_0x00cf:
            int r7 = r6 - r7
            int r7 = java.lang.Math.abs(r7)
            int r0 = r4.f1411J
            int r6 = r6 - r0
            int r6 = java.lang.Math.abs(r6)
            if (r7 >= r6) goto L_0x0085
            r4.getClass()
        L_0x00e1:
            r4.getClass()
            r6 = 1
            r4.startSettling(r5, r2, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Y1.c.onViewReleased(android.view.View, float, float):void");
    }

    public final boolean tryCaptureView(View view, int i2) {
        View view2;
        BottomSheetBehavior bottomSheetBehavior = this.f948a;
        int i7 = bottomSheetBehavior.f1415O;
        if (i7 == 1 || bottomSheetBehavior.d0) {
            return false;
        }
        if (i7 == 3 && bottomSheetBehavior.b0 == i2) {
            WeakReference weakReference = bottomSheetBehavior.Y;
            if (weakReference != null) {
                view2 = (View) weakReference.get();
            } else {
                view2 = null;
            }
            if (view2 != null && view2.canScrollVertically(-1)) {
                return false;
            }
        }
        System.currentTimeMillis();
        WeakReference weakReference2 = bottomSheetBehavior.f1418X;
        if (weakReference2 == null || weakReference2.get() != view) {
            return false;
        }
        return true;
    }
}
