package B2;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.Filterable;
import android.widget.ListAdapter;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.ListPopupWindow;
import com.google.android.material.textfield.TextInputLayout;
import java.util.List;
import java.util.Locale;
import x2.C0340g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class w extends AppCompatAutoCompleteTextView {
    public final ListPopupWindow d;
    public final AccessibilityManager e;
    public final Rect f = new Rect();
    public final int g;

    /* renamed from: h  reason: collision with root package name */
    public final float f75h;

    /* renamed from: i  reason: collision with root package name */
    public ColorStateList f76i;

    /* renamed from: j  reason: collision with root package name */
    public int f77j;
    public ColorStateList k;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public w(android.content.Context r8, android.util.AttributeSet r9) {
        /*
            r7 = this;
            int r3 = androidx.appcompat.R$attr.autoCompleteTextViewStyle
            r6 = 0
            android.content.Context r8 = D2.a.a(r8, r9, r3, r6)
            r7.<init>(r8, r9, r3)
            android.graphics.Rect r8 = new android.graphics.Rect
            r8.<init>()
            r7.f = r8
            android.content.Context r0 = r7.getContext()
            int r4 = androidx.appcompat.R$style.Widget_AppCompat_AutoCompleteTextView
            int[] r5 = new int[r6]
            int[] r2 = Q1.a.u
            r1 = r9
            android.content.res.TypedArray r8 = h2.p.d(r0, r1, r2, r3, r4, r5)
            boolean r9 = r8.hasValue(r6)
            if (r9 == 0) goto L_0x0030
            int r9 = r8.getInt(r6, r6)
            if (r9 != 0) goto L_0x0030
            r9 = 0
            r7.setKeyListener(r9)
        L_0x0030:
            r9 = 3
            r1 = 2131493284(0x7f0c01a4, float:1.8610044E38)
            int r9 = r8.getResourceId(r9, r1)
            r7.g = r9
            r9 = 2131166873(0x7f070699, float:1.7948004E38)
            r1 = 1
            int r9 = r8.getDimensionPixelOffset(r1, r9)
            float r9 = (float) r9
            r7.f75h = r9
            r9 = 2
            boolean r2 = r8.hasValue(r9)
            if (r2 == 0) goto L_0x0056
            int r2 = r8.getColor(r9, r6)
            android.content.res.ColorStateList r2 = android.content.res.ColorStateList.valueOf(r2)
            r7.f76i = r2
        L_0x0056:
            r2 = 4
            int r2 = r8.getColor(r2, r6)
            r7.f77j = r2
            r2 = 5
            android.content.res.ColorStateList r2 = B1.a.u(r0, r8, r2)
            r7.k = r2
            java.lang.String r2 = "accessibility"
            java.lang.Object r2 = r0.getSystemService(r2)
            android.view.accessibility.AccessibilityManager r2 = (android.view.accessibility.AccessibilityManager) r2
            r7.e = r2
            androidx.appcompat.widget.ListPopupWindow r2 = new androidx.appcompat.widget.ListPopupWindow
            r2.<init>(r0)
            r7.d = r2
            r2.setModal(r1)
            r2.setAnchorView(r7)
            r2.setInputMethodMode(r9)
            android.widget.ListAdapter r9 = r7.getAdapter()
            r2.setAdapter(r9)
            B2.u r9 = new B2.u
            r9.<init>(r7)
            r2.setOnItemClickListener(r9)
            r9 = 6
            boolean r0 = r8.hasValue(r9)
            if (r0 == 0) goto L_0x009b
            int r9 = r8.getResourceId(r9, r6)
            r7.setSimpleItems((int) r9)
        L_0x009b:
            r8.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: B2.w.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public static void a(w wVar, Object obj) {
        wVar.setText(wVar.convertSelectionToString(obj), false);
    }

    public final TextInputLayout b() {
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof TextInputLayout) {
                return (TextInputLayout) parent;
            }
        }
        return null;
    }

    public final boolean c() {
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList;
        AccessibilityManager accessibilityManager = this.e;
        if (accessibilityManager != null && accessibilityManager.isTouchExplorationEnabled()) {
            return true;
        }
        if (accessibilityManager == null || !accessibilityManager.isEnabled() || (enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(16)) == null) {
            return false;
        }
        for (AccessibilityServiceInfo next : enabledAccessibilityServiceList) {
            if (next.getSettingsActivityName() != null && next.getSettingsActivityName().contains("SwitchAccess")) {
                return true;
            }
        }
        return false;
    }

    public final void dismissDropDown() {
        if (c()) {
            this.d.dismiss();
        } else {
            super.dismissDropDown();
        }
    }

    public ColorStateList getDropDownBackgroundTintList() {
        return this.f76i;
    }

    public CharSequence getHint() {
        TextInputLayout b = b();
        if (b == null || !b.f1518H) {
            return super.getHint();
        }
        return b.getHint();
    }

    public float getPopupElevation() {
        return this.f75h;
    }

    public int getSimpleItemSelectedColor() {
        return this.f77j;
    }

    public ColorStateList getSimpleItemSelectedRippleColor() {
        return this.k;
    }

    public final void onAttachedToWindow() {
        String str;
        super.onAttachedToWindow();
        TextInputLayout b = b();
        if (b != null && b.f1518H && super.getHint() == null) {
            String str2 = Build.MANUFACTURER;
            if (str2 != null) {
                str = str2.toLowerCase(Locale.ENGLISH);
            } else {
                str = "";
            }
            if (str.equals("meizu")) {
                setHint("");
            }
        }
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.d.dismiss();
    }

    public final void onMeasure(int i2, int i7) {
        super.onMeasure(i2, i7);
        if (View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
            int measuredWidth = getMeasuredWidth();
            ListAdapter adapter = getAdapter();
            TextInputLayout b = b();
            int i8 = 0;
            if (!(adapter == null || b == null)) {
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
                int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
                ListPopupWindow listPopupWindow = this.d;
                int min = Math.min(adapter.getCount(), Math.max(0, listPopupWindow.getSelectedItemPosition()) + 15);
                View view = null;
                int i10 = 0;
                for (int max = Math.max(0, min - 15); max < min; max++) {
                    int itemViewType = adapter.getItemViewType(max);
                    if (itemViewType != i8) {
                        view = null;
                        i8 = itemViewType;
                    }
                    view = adapter.getView(max, view, b);
                    if (view.getLayoutParams() == null) {
                        view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
                    }
                    view.measure(makeMeasureSpec, makeMeasureSpec2);
                    i10 = Math.max(i10, view.getMeasuredWidth());
                }
                Drawable background = listPopupWindow.getBackground();
                if (background != null) {
                    Rect rect = this.f;
                    background.getPadding(rect);
                    i10 += rect.left + rect.right;
                }
                i8 = b.getEndIconView().getMeasuredWidth() + i10;
            }
            setMeasuredDimension(Math.min(Math.max(measuredWidth, i8), View.MeasureSpec.getSize(i2)), getMeasuredHeight());
        }
    }

    public final void onWindowFocusChanged(boolean z) {
        if (!c()) {
            super.onWindowFocusChanged(z);
        }
    }

    public <T extends ListAdapter & Filterable> void setAdapter(T t) {
        super.setAdapter(t);
        this.d.setAdapter(getAdapter());
    }

    public void setDropDownBackgroundDrawable(Drawable drawable) {
        super.setDropDownBackgroundDrawable(drawable);
        ListPopupWindow listPopupWindow = this.d;
        if (listPopupWindow != null) {
            listPopupWindow.setBackgroundDrawable(drawable);
        }
    }

    public void setDropDownBackgroundTint(int i2) {
        setDropDownBackgroundTintList(ColorStateList.valueOf(i2));
    }

    public void setDropDownBackgroundTintList(ColorStateList colorStateList) {
        this.f76i = colorStateList;
        Drawable dropDownBackground = getDropDownBackground();
        if (dropDownBackground instanceof C0340g) {
            ((C0340g) dropDownBackground).k(this.f76i);
        }
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        super.setOnItemSelectedListener(onItemSelectedListener);
        this.d.setOnItemSelectedListener(getOnItemSelectedListener());
    }

    public void setRawInputType(int i2) {
        super.setRawInputType(i2);
        TextInputLayout b = b();
        if (b != null) {
            b.s();
        }
    }

    public void setSimpleItemSelectedColor(int i2) {
        this.f77j = i2;
        if (getAdapter() instanceof v) {
            ((v) getAdapter()).a();
        }
    }

    public void setSimpleItemSelectedRippleColor(ColorStateList colorStateList) {
        this.k = colorStateList;
        if (getAdapter() instanceof v) {
            ((v) getAdapter()).a();
        }
    }

    public void setSimpleItems(int i2) {
        setSimpleItems(getResources().getStringArray(i2));
    }

    public final void showDropDown() {
        if (c()) {
            this.d.show();
        } else {
            super.showDropDown();
        }
    }

    public void setSimpleItems(String[] strArr) {
        setAdapter(new v(this, getContext(), this.g, strArr));
    }
}
