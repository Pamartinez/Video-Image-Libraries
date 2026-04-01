package y2;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.math.MathUtils;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.material.sidesheet.SideSheetBehavior;
import i.C0212a;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* renamed from: y2.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0358b extends ViewDragHelper.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SideSheetBehavior f2141a;

    public C0358b(SideSheetBehavior sideSheetBehavior) {
        this.f2141a = sideSheetBehavior;
    }

    public final int clampViewPositionHorizontal(View view, int i2, int i7) {
        SideSheetBehavior sideSheetBehavior = this.f2141a;
        return MathUtils.clamp(i2, sideSheetBehavior.d.D(), sideSheetBehavior.d.C());
    }

    public final int clampViewPositionVertical(View view, int i2, int i7) {
        return view.getTop();
    }

    public final int getViewHorizontalDragRange(View view) {
        SideSheetBehavior sideSheetBehavior = this.f2141a;
        return sideSheetBehavior.f1492o + sideSheetBehavior.r;
    }

    public final void onViewDragStateChanged(int i2) {
        if (i2 == 1) {
            SideSheetBehavior sideSheetBehavior = this.f2141a;
            if (sideSheetBehavior.f1491j) {
                sideSheetBehavior.setStateInternal(1);
            }
        }
    }

    public final void onViewPositionChanged(View view, int i2, int i7, int i8, int i10) {
        View view2;
        ViewGroup.MarginLayoutParams marginLayoutParams;
        SideSheetBehavior sideSheetBehavior = this.f2141a;
        WeakReference weakReference = sideSheetBehavior.t;
        if (weakReference != null) {
            view2 = (View) weakReference.get();
        } else {
            view2 = null;
        }
        if (!(view2 == null || (marginLayoutParams = (ViewGroup.MarginLayoutParams) view2.getLayoutParams()) == null)) {
            sideSheetBehavior.d.k0(marginLayoutParams, view.getLeft(), view.getRight());
            view2.setLayoutParams(marginLayoutParams);
        }
        LinkedHashSet linkedHashSet = sideSheetBehavior.f1494x;
        if (!linkedHashSet.isEmpty()) {
            sideSheetBehavior.d.d(i2);
            Iterator it = linkedHashSet.iterator();
            if (it.hasNext()) {
                throw C0212a.h(it);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0051, code lost:
        if (java.lang.Math.abs(r3 - r1.d.w()) < java.lang.Math.abs(r3 - r1.d.y())) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0021, code lost:
        if (r1.d.N(r2) == false) goto L_0x0053;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onViewReleased(android.view.View r2, float r3, float r4) {
        /*
            r1 = this;
            com.google.android.material.sidesheet.SideSheetBehavior r1 = r1.f2141a
            Gd.a r0 = r1.d
            boolean r0 = r0.L(r3)
            if (r0 == 0) goto L_0x000b
            goto L_0x0053
        L_0x000b:
            Gd.a r0 = r1.d
            boolean r0 = r0.g0(r3, r2)
            if (r0 == 0) goto L_0x0024
            Gd.a r0 = r1.d
            boolean r3 = r0.P(r3, r4)
            if (r3 != 0) goto L_0x0055
            Gd.a r3 = r1.d
            boolean r3 = r3.N(r2)
            if (r3 == 0) goto L_0x0053
            goto L_0x0055
        L_0x0024:
            r0 = 0
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 == 0) goto L_0x0036
            float r3 = java.lang.Math.abs(r3)
            float r4 = java.lang.Math.abs(r4)
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 <= 0) goto L_0x0036
            goto L_0x0055
        L_0x0036:
            int r3 = r2.getLeft()
            Gd.a r4 = r1.d
            int r4 = r4.w()
            int r4 = r3 - r4
            int r4 = java.lang.Math.abs(r4)
            Gd.a r0 = r1.d
            int r0 = r0.y()
            int r3 = r3 - r0
            int r3 = java.lang.Math.abs(r3)
            if (r4 >= r3) goto L_0x0055
        L_0x0053:
            r3 = 3
            goto L_0x0056
        L_0x0055:
            r3 = 5
        L_0x0056:
            r4 = 1
            r1.b(r2, r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: y2.C0358b.onViewReleased(android.view.View, float, float):void");
    }

    public final boolean tryCaptureView(View view, int i2) {
        WeakReference weakReference;
        SideSheetBehavior sideSheetBehavior = this.f2141a;
        if (sideSheetBehavior.k == 1 || (weakReference = sideSheetBehavior.s) == null || weakReference.get() != view) {
            return false;
        }
        return true;
    }
}
