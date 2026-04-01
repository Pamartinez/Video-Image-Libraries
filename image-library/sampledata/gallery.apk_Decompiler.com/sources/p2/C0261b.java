package p2;

import B1.a;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.sec.android.gallery3d.R;

/* renamed from: p2.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0261b extends FrameLayout {
    public final int d;
    public int e;
    public TextView f;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0261b(android.content.Context r9) {
        /*
            r8 = this;
            boolean r0 = androidx.appcompat.util.SeslMisc.isLightTheme(r9)
            if (r0 == 0) goto L_0x000b
            r0 = 2131952928(0x7f130520, float:1.9542313E38)
        L_0x0009:
            r5 = r0
            goto L_0x000f
        L_0x000b:
            r0 = 2131952927(0x7f13051f, float:1.954231E38)
            goto L_0x0009
        L_0x000f:
            r2 = 0
            r4 = 0
            r8.<init>(r9, r2, r4, r5)
            r0 = 2131168120(0x7f070b78, float:1.7950533E38)
            r8.d = r0
            android.content.res.Resources r0 = r9.getResources()
            r1 = 2131168117(0x7f070b75, float:1.7950527E38)
            int r0 = r0.getDimensionPixelSize(r1)
            r8.e = r0
            android.view.LayoutInflater r0 = android.view.LayoutInflater.from(r9)
            int r1 = r8.getLayoutResId()
            r7 = 1
            r0.inflate(r1, r8, r7)
            r0 = 0
            int[] r6 = new int[r0]
            h2.p.a(r9, r2, r4, r5)
            int[] r3 = Q1.a.m
            r1 = r9
            h2.p.b(r1, r2, r3, r4, r5, r6)
            android.content.res.TypedArray r9 = r1.obtainStyledAttributes(r2, r3, r4, r5)
            java.lang.String r2 = "obtainStyledAttributes(\n…defStyleRes\n            )"
            kotlin.jvm.internal.j.d(r9, r2)
            java.lang.CharSequence r2 = r9.getText(r7)
            r3 = 0
            if (r2 == 0) goto L_0x0053
            java.lang.String r2 = r2.toString()
            goto L_0x0054
        L_0x0053:
            r2 = r3
        L_0x0054:
            r4 = -1
            int r0 = r9.getResourceId(r0, r4)
            r9.recycle()
            r9 = 2131298582(0x7f090916, float:1.8215141E38)
            android.view.View r9 = r8.findViewById(r9)
            android.widget.TextView r9 = (android.widget.TextView) r9
            if (r9 == 0) goto L_0x0084
            r9.setText(r2)
            if (r0 == r4) goto L_0x006f
            androidx.core.widget.TextViewCompat.setTextAppearance(r9, r0)
        L_0x006f:
            boolean r0 = androidx.appcompat.util.SeslMisc.isDefaultTheme(r1)
            if (r0 != 0) goto L_0x007f
            r0 = 2131101503(0x7f06073f, float:1.7815418E38)
            android.content.res.ColorStateList r0 = androidx.appcompat.content.res.AppCompatResources.getColorStateList(r1, r0)
            r9.setTextColor(r0)
        L_0x007f:
            r0 = 2
            androidx.reflect.view.SeslViewReflector.semSetHoverPopupType(r9, r0)
            r3 = r9
        L_0x0084:
            r8.f = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p2.C0261b.<init>(android.content.Context):void");
    }

    private final int getLayoutResId() {
        return R.layout.sesl_divider_button_layout_divier_button;
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.CharSequence] */
    /* JADX WARNING: type inference failed for: r1v6, types: [android.view.ViewGroup] */
    /* JADX WARNING: type inference failed for: r1v13 */
    /* JADX WARNING: type inference failed for: r1v14 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(androidx.appcompat.view.menu.MenuItemImpl r4) {
        /*
            r3 = this;
            int r0 = r4.getItemId()
            r3.setId(r0)
            android.widget.TextView r0 = r3.f
            if (r0 != 0) goto L_0x000c
            goto L_0x0019
        L_0x000c:
            android.view.View r1 = r4.getActionView()
            if (r1 != 0) goto L_0x0014
            r1 = 0
            goto L_0x0016
        L_0x0014:
            r1 = 8
        L_0x0016:
            r0.setVisibility(r1)
        L_0x0019:
            boolean r0 = r4.isEnabled()
            r3.setEnabled(r0)
            java.lang.CharSequence r0 = r4.getContentDescription()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0031
            java.lang.CharSequence r0 = r4.getContentDescription()
            r3.setContentDescription(r0)
        L_0x0031:
            android.view.View r0 = r4.getActionView()
            r1 = 0
            if (r0 == 0) goto L_0x005c
            android.view.ViewParent r4 = r0.getParent()
            if (r4 == 0) goto L_0x004e
            android.view.ViewParent r4 = r0.getParent()
            boolean r2 = r4 instanceof android.view.ViewGroup
            if (r2 == 0) goto L_0x0049
            r1 = r4
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
        L_0x0049:
            if (r1 == 0) goto L_0x004e
            r1.removeView(r0)
        L_0x004e:
            android.widget.FrameLayout$LayoutParams r4 = new android.widget.FrameLayout$LayoutParams
            r1 = -2
            r4.<init>(r1, r1)
            r1 = 17
            r4.gravity = r1
            r3.addView(r0, r4)
            return
        L_0x005c:
            android.widget.TextView r0 = r3.f
            if (r0 == 0) goto L_0x0078
            java.lang.CharSequence r4 = r4.getTitle()
            if (r4 == 0) goto L_0x006a
            java.lang.String r1 = r4.toString()
        L_0x006a:
            r0.setText(r1)
            int r4 = r3.d
            androidx.appcompat.oneui.common.internal.util.MaxFontScaleRatio r1 = androidx.appcompat.oneui.common.internal.util.MaxFontScaleRatio.LARGE
            androidx.appcompat.oneui.common.internal.util.TextViewHelperKt.checkMaxFontScale(r0, r4, r1)
            r4 = 1
            androidx.reflect.widget.SeslTextViewReflector.semSetButtonShapeEnabled(r0, r4)
        L_0x0078:
            android.content.Context r4 = r3.getContext()
            android.content.res.Resources r4 = r4.getResources()
            r0 = 2131168117(0x7f070b75, float:1.7950527E38)
            int r4 = r4.getDimensionPixelSize(r0)
            r3.e = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p2.C0261b.a(androidx.appcompat.view.menu.MenuItemImpl):void");
    }

    public final TextView getTextView() {
        return this.f;
    }

    public final void onMeasure(int i2, int i7) {
        super.onMeasure(i2, i7);
        setMeasuredDimension(View.resolveSizeAndState(a.l(getMeasuredWidth(), getSuggestedMinimumWidth(), this.e), i2, 0), getMeasuredHeight());
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        TextView textView = this.f;
        if (textView != null) {
            textView.setEnabled(z);
        }
    }

    public final void setTextView(TextView textView) {
        this.f = textView;
    }
}
