package c2;

import G0.e;
import Jd.b;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.chip.Chip;
import h2.C0210e;
import h2.h;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class j extends C0210e {
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f1093h;

    /* renamed from: i  reason: collision with root package name */
    public h f1094i;

    /* renamed from: j  reason: collision with root package name */
    public final b f1095j;
    public final int k;
    public final i l;

    /* JADX WARNING: type inference failed for: r11v4, types: [java.lang.Object, Jd.b] */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public j(android.content.Context r11, android.util.AttributeSet r12) {
        /*
            r10 = this;
            r0 = 2131953174(0x7f130616, float:1.9542812E38)
            r4 = 2130968790(0x7f0400d6, float:1.7546244E38)
            android.content.Context r11 = D2.a.a(r11, r12, r4, r0)
            r10.<init>(r11, r12, r4)
            r0 = 0
            r10.f = r0
            android.content.res.Resources$Theme r11 = r11.getTheme()
            int[] r1 = Q1.a.s
            android.content.res.TypedArray r11 = r11.obtainStyledAttributes(r12, r1, r0, r0)
            r7 = 1
            int r1 = r11.getDimensionPixelSize(r7, r0)
            r10.d = r1
            int r1 = r11.getDimensionPixelSize(r0, r0)
            r10.e = r1
            r11.recycle()
            Jd.b r11 = new Jd.b
            r11.<init>()
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r11.f3474c = r1
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            r11.d = r1
            r10.f1095j = r11
            c2.i r8 = new c2.i
            r9 = r10
            com.google.android.material.chip.SeslChipGroup r9 = (com.google.android.material.chip.SeslChipGroup) r9
            r8.<init>(r9)
            r10.l = r8
            android.content.Context r1 = r10.getContext()
            r5 = 2131953174(0x7f130616, float:1.9542812E38)
            int[] r6 = new int[r0]
            int[] r3 = Q1.a.f628h
            r2 = r12
            android.content.res.TypedArray r12 = h2.p.d(r1, r2, r3, r4, r5, r6)
            int r1 = r12.getDimensionPixelOffset(r7, r0)
            r2 = 2
            int r2 = r12.getDimensionPixelOffset(r2, r1)
            r10.setChipSpacingHorizontal(r2)
            r2 = 3
            int r1 = r12.getDimensionPixelOffset(r2, r1)
            r10.setChipSpacingVertical(r1)
            r1 = 5
            boolean r1 = r12.getBoolean(r1, r0)
            r10.setSingleLine((boolean) r1)
            r1 = 6
            boolean r1 = r12.getBoolean(r1, r0)
            r10.setSingleSelection((boolean) r1)
            r1 = 4
            boolean r1 = r12.getBoolean(r1, r0)
            r10.setSelectionRequired(r1)
            r1 = -1
            int r0 = r12.getResourceId(r0, r1)
            r10.k = r0
            r12.recycle()
            G0.c r12 = new G0.c
            r0 = 10
            r12.<init>((int) r0, (java.lang.Object) r9)
            r11.e = r12
            super.setOnHierarchyChangeListener(r8)
            androidx.core.view.ViewCompat.setImportantForAccessibility(r10, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c2.j.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    private int getVisibleChipCount() {
        int i2 = 0;
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            if ((getChildAt(i7) instanceof Chip) && getChildAt(i7).getVisibility() == 0) {
                i2++;
            }
        }
        return i2;
    }

    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (!super.checkLayoutParams(layoutParams) || !(layoutParams instanceof f)) {
            return false;
        }
        return true;
    }

    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-2, -2);
    }

    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    public int getCheckedChipId() {
        return this.f1095j.h();
    }

    public List<Integer> getCheckedChipIds() {
        return this.f1095j.e(this);
    }

    public int getChipSpacingHorizontal() {
        return this.g;
    }

    public int getChipSpacingVertical() {
        return this.f1093h;
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        int i2 = this.k;
        if (i2 != -1) {
            b bVar = this.f1095j;
            h hVar = (h) ((HashMap) bVar.f3474c).get(Integer.valueOf(i2));
            if (hVar != null && bVar.b(hVar)) {
                bVar.i();
            }
        }
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        int i2;
        int i7;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        AccessibilityNodeInfoCompat wrap = AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo);
        if (this.f) {
            i2 = getVisibleChipCount();
        } else {
            i2 = -1;
        }
        int rowCount = getRowCount();
        if (this.f1095j.f3473a) {
            i7 = 1;
        } else {
            i7 = 2;
        }
        wrap.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(rowCount, i2, false, i7));
    }

    public void setChipSpacing(int i2) {
        setChipSpacingHorizontal(i2);
        setChipSpacingVertical(i2);
    }

    public void setChipSpacingHorizontal(int i2) {
        if (this.g != i2) {
            this.g = i2;
            setItemSpacing(i2);
            requestLayout();
        }
    }

    public void setChipSpacingHorizontalResource(int i2) {
        setChipSpacingHorizontal(getResources().getDimensionPixelOffset(i2));
    }

    public void setChipSpacingResource(int i2) {
        setChipSpacing(getResources().getDimensionPixelOffset(i2));
    }

    public void setChipSpacingVertical(int i2) {
        if (this.f1093h != i2) {
            this.f1093h = i2;
            setLineSpacing(i2);
            requestLayout();
        }
    }

    public void setChipSpacingVerticalResource(int i2) {
        setChipSpacingVertical(getResources().getDimensionPixelOffset(i2));
    }

    @Deprecated
    public void setDividerDrawableHorizontal(Drawable drawable) {
        throw new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setDividerDrawableVertical(Drawable drawable) {
        throw new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setFlexWrap(int i2) {
        throw new UnsupportedOperationException("Changing flex wrap not allowed. ChipGroup exposes a singleLine attribute instead.");
    }

    @Deprecated
    public void setOnCheckedChangeListener(g gVar) {
        if (gVar == null) {
            setOnCheckedStateChangeListener((h) null);
        } else {
            setOnCheckedStateChangeListener(new e((Object) this));
        }
    }

    public void setOnCheckedStateChangeListener(h hVar) {
        this.f1094i = hVar;
    }

    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.l.d = onHierarchyChangeListener;
    }

    public void setSelectionRequired(boolean z) {
        this.f1095j.b = z;
    }

    @Deprecated
    public void setShowDividerHorizontal(int i2) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setShowDividerVertical(int i2) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    public void setSingleLine(boolean z) {
        super.setSingleLine(z);
    }

    public void setSingleSelection(boolean z) {
        b bVar = this.f1095j;
        if (bVar.f3473a != z) {
            bVar.f3473a = z;
            boolean isEmpty = ((HashSet) bVar.d).isEmpty();
            for (h l8 : ((HashMap) bVar.f3474c).values()) {
                bVar.l(l8, false);
            }
            if (!isEmpty) {
                bVar.i();
            }
        }
    }

    public void setSingleLine(int i2) {
        setSingleLine(getResources().getBoolean(i2));
    }

    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ViewGroup.MarginLayoutParams(layoutParams);
    }

    public void setSingleSelection(int i2) {
        setSingleSelection(getResources().getBoolean(i2));
    }
}
