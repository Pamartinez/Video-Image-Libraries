package com.google.android.material.snackbar;

import R1.a;
import X1.e;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.sec.android.gallery3d.R;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SnackbarContentLayout extends LinearLayout {
    public TextView d;
    public Button e;
    public final TimeInterpolator f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f1495h;

    /* renamed from: i  reason: collision with root package name */
    public final SnackbarContentLayout f1496i;

    /* renamed from: j  reason: collision with root package name */
    public int f1497j;
    public final InputMethodManager k;
    public final WindowManager l;
    public boolean m = false;
    public Integer n = 2;

    /* renamed from: o  reason: collision with root package name */
    public boolean f1498o = true;

    /* renamed from: p  reason: collision with root package name */
    public boolean f1499p = false;

    public SnackbarContentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = k.M(context, R.attr.motionEasingEmphasizedInterpolator, a.b);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Q1.a.L);
        this.f1495h = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        this.g = obtainStyledAttributes.getDimensionPixelSize(7, -1);
        obtainStyledAttributes.recycle();
        Resources resources = context.getResources();
        int fraction = (int) resources.getFraction(R.dimen.sesl_config_prefSnackWidth, resources.getDisplayMetrics().widthPixels, resources.getDisplayMetrics().widthPixels);
        this.f1497j = fraction;
        this.f1495h = fraction;
        this.f1496i = (SnackbarContentLayout) findViewById(R.id.snackbar_content_layout);
        this.k = (InputMethodManager) context.getSystemService(InputMethodManager.class);
        this.l = (WindowManager) context.getSystemService("window");
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(new e(this, 1));
        }
    }

    public final boolean a(int i2, int i7, int i8) {
        boolean z;
        if (i2 != getOrientation()) {
            setOrientation(i2);
            z = true;
        } else {
            z = false;
        }
        if (this.d.getPaddingTop() == i7 && this.d.getPaddingBottom() == i8) {
            return z;
        }
        TextView textView = this.d;
        if (ViewCompat.isPaddingRelative(textView)) {
            ViewCompat.setPaddingRelative(textView, ViewCompat.getPaddingStart(textView), i7, ViewCompat.getPaddingEnd(textView), i8);
            return true;
        }
        textView.setPadding(textView.getPaddingLeft(), i7, textView.getPaddingRight(), i8);
        return true;
    }

    public Button getActionView() {
        return this.e;
    }

    public TextView getMessageView() {
        return this.d;
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Resources resources = getContext().getResources();
        int fraction = (int) resources.getFraction(R.dimen.sesl_config_prefSnackWidth, resources.getDisplayMetrics().widthPixels, resources.getDisplayMetrics().widthPixels);
        this.f1497j = fraction;
        this.f1495h = fraction;
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.d = (TextView) findViewById(R.id.snackbar_text);
        this.e = (Button) findViewById(R.id.snackbar_action);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0213, code lost:
        if (a(1, r0, r0 - r1) != false) goto L_0x0215;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x021f, code lost:
        if (a(0, r0, r0) != false) goto L_0x0215;
     */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0224  */
    /* JADX WARNING: Removed duplicated region for block: B:92:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r11, int r12) {
        /*
            r10 = this;
            int r0 = r10.getMeasuredWidth()
            super.onMeasure(r11, r12)
            android.widget.Button r1 = r10.e
            int r1 = r1.getVisibility()
            r2 = 1073741824(0x40000000, float:2.0)
            if (r1 != 0) goto L_0x0028
            boolean r1 = r10.f1499p
            if (r1 != 0) goto L_0x0016
            goto L_0x0028
        L_0x0016:
            int r11 = android.view.View.MeasureSpec.getSize(r11)
            int r0 = r10.f1497j
            int r11 = java.lang.Math.min(r11, r0)
            int r11 = android.view.View.MeasureSpec.makeMeasureSpec(r11, r2)
            super.onMeasure(r11, r12)
            goto L_0x0049
        L_0x0028:
            int r1 = r10.getMeasuredWidth()
            if (r1 != 0) goto L_0x0036
            int r11 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r2)
            super.onMeasure(r11, r12)
            goto L_0x0049
        L_0x0036:
            int r0 = r10.f1495h
            if (r0 <= 0) goto L_0x0049
            int r0 = r10.getMeasuredWidth()
            int r1 = r10.f1495h
            if (r0 <= r1) goto L_0x0049
            int r11 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r2)
            super.onMeasure(r11, r12)
        L_0x0049:
            android.content.res.Resources r0 = r10.getResources()
            r1 = 2131165674(0x7f0701ea, float:1.7945572E38)
            int r0 = r0.getDimensionPixelSize(r1)
            android.content.res.Resources r1 = r10.getResources()
            r2 = 2131165673(0x7f0701e9, float:1.794557E38)
            int r1 = r1.getDimensionPixelSize(r2)
            android.widget.TextView r2 = r10.d
            android.text.Layout r2 = r2.getLayout()
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0071
            int r2 = r2.getLineCount()
            if (r2 <= r4) goto L_0x0071
            r2 = r4
            goto L_0x0072
        L_0x0071:
            r2 = r3
        L_0x0072:
            if (r2 == 0) goto L_0x0076
            r10.f1499p = r4
        L_0x0076:
            com.google.android.material.snackbar.SnackbarContentLayout r5 = r10.f1496i
            if (r5 == 0) goto L_0x01fd
            int r0 = r5.getPaddingLeft()
            int r1 = r5.getPaddingRight()
            int r1 = r1 + r0
            android.widget.TextView r0 = r10.d
            int r0 = r0.getMeasuredWidth()
            int r0 = r0 + r1
            android.widget.Button r1 = r10.e
            int r1 = r1.getMeasuredWidth()
            int r1 = r1 + r0
            float r0 = (float) r1
            int r1 = r10.g
            r6 = -1
            if (r1 != r6) goto L_0x012b
            android.widget.Button r1 = r10.e
            int r1 = r1.getVisibility()
            if (r1 != 0) goto L_0x012b
            int r1 = r10.f1497j
            float r1 = (float) r1
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 > 0) goto L_0x00cc
            if (r2 != 0) goto L_0x00cc
            boolean r1 = r10.f1499p
            if (r1 == 0) goto L_0x00ad
            goto L_0x00cc
        L_0x00ad:
            r5.setOrientation(r3)
            android.widget.Button r1 = r10.e
            android.content.res.Resources r2 = r10.getResources()
            r6 = 2131168046(0x7f070b2e, float:1.7950383E38)
            int r2 = r2.getDimensionPixelSize(r6)
            android.content.res.Resources r6 = r10.getResources()
            r7 = 2131168047(0x7f070b2f, float:1.7950385E38)
            int r6 = r6.getDimensionPixelSize(r7)
            r1.setPadding(r2, r3, r6, r3)
            goto L_0x0129
        L_0x00cc:
            r5.setOrientation(r4)
            android.widget.TextView r1 = r10.d
            android.content.res.Resources r2 = r10.getResources()
            r6 = 2131168059(0x7f070b3b, float:1.795041E38)
            int r2 = r2.getDimensionPixelSize(r6)
            android.content.res.Resources r6 = r10.getResources()
            r7 = 2131168061(0x7f070b3d, float:1.7950413E38)
            int r6 = r6.getDimensionPixelSize(r7)
            android.content.res.Resources r7 = r10.getResources()
            r8 = 2131168060(0x7f070b3c, float:1.7950411E38)
            int r7 = r7.getDimensionPixelSize(r8)
            android.content.res.Resources r8 = r10.getResources()
            r9 = 2131168058(0x7f070b3a, float:1.7950407E38)
            int r8 = r8.getDimensionPixelSize(r9)
            r1.setPadding(r2, r6, r7, r8)
            android.widget.Button r1 = r10.e
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r1 = (android.widget.LinearLayout.LayoutParams) r1
            android.content.res.Resources r2 = r10.getResources()
            r6 = 2131168044(0x7f070b2c, float:1.7950379E38)
            int r2 = r2.getDimensionPixelSize(r6)
            r1.setMargins(r3, r3, r3, r2)
            android.content.res.Resources r2 = r10.getResources()
            r6 = 2131168045(0x7f070b2d, float:1.795038E38)
            int r2 = r2.getDimensionPixelSize(r6)
            r1.setMarginEnd(r2)
            android.widget.Button r2 = r10.e
            r2.setLayoutParams(r1)
        L_0x0129:
            r1 = r4
            goto L_0x012c
        L_0x012b:
            r1 = r3
        L_0x012c:
            android.view.WindowManager r2 = r10.l
            android.view.Display r6 = r2.getDefaultDisplay()
            int r6 = r6.getRotation()
            if (r6 == r4) goto L_0x013e
            r7 = 3
            if (r6 != r7) goto L_0x013c
            goto L_0x013e
        L_0x013c:
            r6 = r3
            goto L_0x013f
        L_0x013e:
            r6 = r4
        L_0x013f:
            android.view.inputmethod.InputMethodManager r7 = r10.k
            if (r7 == 0) goto L_0x01be
            if (r6 == 0) goto L_0x01be
            int r0 = (int) r0
            android.view.ViewGroup$LayoutParams r1 = r5.getLayoutParams()
            android.view.ViewGroup$MarginLayoutParams r1 = (android.view.ViewGroup.MarginLayoutParams) r1
            boolean r6 = androidx.reflect.view.inputmethod.SeslInputMethodManagerReflector.isInputMethodShown(r7)
            if (r6 == 0) goto L_0x017d
            r6 = 2131168051(0x7f070b33, float:1.7950393E38)
            android.view.WindowMetrics r2 = r2.getCurrentWindowMetrics()     // Catch:{ Exception -> 0x0172 }
            android.view.WindowInsets r2 = r2.getWindowInsets()     // Catch:{ Exception -> 0x0172 }
            int r7 = android.view.WindowInsets.Type.navigationBars()     // Catch:{ Exception -> 0x0172 }
            android.graphics.Insets r2 = r2.getInsets(r7)     // Catch:{ Exception -> 0x0172 }
            int r2 = r2.bottom     // Catch:{ Exception -> 0x0172 }
            if (r2 != 0) goto L_0x017a
            android.content.res.Resources r2 = r10.getResources()     // Catch:{ Exception -> 0x0172 }
            int r2 = r2.getDimensionPixelOffset(r6)     // Catch:{ Exception -> 0x0172 }
            goto L_0x017a
        L_0x0172:
            android.content.res.Resources r2 = r10.getResources()
            int r2 = r2.getDimensionPixelOffset(r6)
        L_0x017a:
            r1.bottomMargin = r2
            goto L_0x018a
        L_0x017d:
            android.content.res.Resources r2 = r10.getResources()
            r6 = 2131168050(0x7f070b32, float:1.795039E38)
            int r2 = r2.getDimensionPixelOffset(r6)
            r1.bottomMargin = r2
        L_0x018a:
            android.view.ViewParent r2 = r5.getParent()
            boolean r6 = r10.m
            if (r6 == 0) goto L_0x01ba
            boolean r6 = r2 instanceof android.view.ViewGroup
            if (r6 == 0) goto L_0x01ba
            android.view.ViewGroup r2 = (android.view.ViewGroup) r2
            int r6 = r2.getMeasuredWidth()
            int r7 = r2.getPaddingLeft()
            int r2 = r2.getPaddingRight()
            int r8 = r10.f1497j
            int r0 = java.lang.Math.min(r8, r0)
            int r6 = r6 - r0
            int r6 = r6 - r7
            int r6 = r6 - r2
            if (r6 <= 0) goto L_0x01b6
            int r6 = r6 / 2
            r1.rightMargin = r6
            r1.leftMargin = r6
            goto L_0x01ba
        L_0x01b6:
            r1.rightMargin = r3
            r1.leftMargin = r3
        L_0x01ba:
            r5.setLayoutParams(r1)
            goto L_0x0215
        L_0x01be:
            int r0 = (int) r0
            android.view.ViewGroup$LayoutParams r2 = r5.getLayoutParams()
            android.view.ViewGroup$MarginLayoutParams r2 = (android.view.ViewGroup.MarginLayoutParams) r2
            android.view.ViewParent r6 = r5.getParent()
            boolean r7 = r10.m
            if (r7 == 0) goto L_0x01f9
            boolean r7 = r6 instanceof android.view.ViewGroup
            if (r7 == 0) goto L_0x01f9
            android.view.ViewGroup r6 = (android.view.ViewGroup) r6
            int r7 = r6.getMeasuredWidth()
            int r8 = r6.getPaddingLeft()
            int r6 = r6.getPaddingRight()
            int r9 = r10.f1497j
            int r0 = java.lang.Math.min(r9, r0)
            int r7 = r7 - r0
            int r7 = r7 - r8
            int r7 = r7 - r6
            if (r7 <= 0) goto L_0x01f1
            int r7 = r7 / 2
            r2.rightMargin = r7
            r2.leftMargin = r7
            goto L_0x01f5
        L_0x01f1:
            r2.rightMargin = r3
            r2.leftMargin = r3
        L_0x01f5:
            r5.setLayoutParams(r2)
            r3 = r4
        L_0x01f9:
            r0 = r1 | r3
            r3 = r0
            goto L_0x0222
        L_0x01fd:
            if (r2 == 0) goto L_0x0217
            int r6 = r10.g
            if (r6 <= 0) goto L_0x0217
            android.widget.Button r6 = r10.e
            int r6 = r6.getMeasuredWidth()
            int r7 = r10.g
            if (r6 <= r7) goto L_0x0217
            int r1 = r0 - r1
            boolean r0 = r10.a(r4, r0, r1)
            if (r0 == 0) goto L_0x0222
        L_0x0215:
            r3 = r4
            goto L_0x0222
        L_0x0217:
            if (r2 == 0) goto L_0x021a
            goto L_0x021b
        L_0x021a:
            r0 = r1
        L_0x021b:
            boolean r0 = r10.a(r3, r0, r0)
            if (r0 == 0) goto L_0x0222
            goto L_0x0215
        L_0x0222:
            if (r3 == 0) goto L_0x0244
            super.onMeasure(r11, r12)
            if (r5 == 0) goto L_0x0244
            android.content.Context r11 = r10.getContext()
            android.content.res.Resources r11 = r11.getResources()
            r12 = 2131168054(0x7f070b36, float:1.79504E38)
            int r11 = r11.getDimensionPixelSize(r12)
            float r11 = (float) r11
            z2.t r12 = new z2.t
            r12.<init>(r10, r11)
            r5.setOutlineProvider(r12)
            r5.setClipToOutline(r4)
        L_0x0244:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.snackbar.SnackbarContentLayout.onMeasure(int, int):void");
    }

    public void setIsCoordinatorLayoutParent(boolean z) {
        this.m = z;
    }

    public void setMaxInlineActionWidth(int i2) {
        this.g = i2;
    }
}
