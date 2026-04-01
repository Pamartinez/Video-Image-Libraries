package b2;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.autofill.AutofillManager;
import android.widget.CompoundButton;
import androidx.appcompat.R$attr;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.CompoundButtonCompat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.sec.android.gallery3d.R;
import h2.u;
import i.C0212a;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import o1.C0246a;

/* renamed from: b2.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0082c extends AppCompatCheckBox {

    /* renamed from: A  reason: collision with root package name */
    public static final int f1042A = Resources.getSystem().getIdentifier("btn_check_material_anim", "drawable", "android");

    /* renamed from: x  reason: collision with root package name */
    public static final int[] f1043x = {R.attr.state_indeterminate};
    public static final int[] y = {R.attr.state_error};
    public static final int[][] z = {new int[]{16842910, R.attr.state_error}, new int[]{16842910, 16842912}, new int[]{16842910, -16842912}, new int[]{-16842910, 16842912}, new int[]{-16842910, -16842912}};
    public final LinkedHashSet d = new LinkedHashSet();
    public final LinkedHashSet e = new LinkedHashSet();
    public ColorStateList f;
    public boolean g;

    /* renamed from: h  reason: collision with root package name */
    public boolean f1044h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f1045i;

    /* renamed from: j  reason: collision with root package name */
    public CharSequence f1046j;
    public Drawable k;
    public Drawable l;
    public boolean m;
    public ColorStateList n;

    /* renamed from: o  reason: collision with root package name */
    public ColorStateList f1047o;

    /* renamed from: p  reason: collision with root package name */
    public PorterDuff.Mode f1048p;
    public int q;
    public int[] r;
    public boolean s;
    public CharSequence t;
    public CompoundButton.OnCheckedChangeListener u;
    public final AnimatedVectorDrawableCompat v = AnimatedVectorDrawableCompat.create(getContext(), R.drawable.mtrl_checkbox_button_checked_unchecked);
    public final C0080a w = new C0080a(this);

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0082c(android.content.Context r8, android.util.AttributeSet r9) {
        /*
            r7 = this;
            int r3 = androidx.appcompat.R$attr.checkboxStyle
            r0 = 2131953180(0x7f13061c, float:1.9542824E38)
            android.content.Context r8 = D2.a.a(r8, r9, r3, r0)
            r7.<init>(r8, r9, r3)
            java.util.LinkedHashSet r8 = new java.util.LinkedHashSet
            r8.<init>()
            r7.d = r8
            java.util.LinkedHashSet r8 = new java.util.LinkedHashSet
            r8.<init>()
            r7.e = r8
            android.content.Context r8 = r7.getContext()
            r0 = 2131232149(0x7f080595, float:1.80804E38)
            androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat r8 = androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat.create(r8, r0)
            r7.v = r8
            b2.a r8 = new b2.a
            r8.<init>(r7)
            r7.w = r8
            android.content.Context r0 = r7.getContext()
            android.graphics.drawable.Drawable r8 = androidx.core.widget.CompoundButtonCompat.getButtonDrawable(r7)
            r7.k = r8
            android.content.res.ColorStateList r8 = r7.getSuperButtonTintList()
            r7.n = r8
            r8 = 0
            r7.setSupportButtonTintList(r8)
            r6 = 0
            int[] r5 = new int[r6]
            r4 = 2131953180(0x7f13061c, float:1.9542824E38)
            h2.p.a(r0, r9, r3, r4)
            int[] r2 = Q1.a.z
            r1 = r9
            h2.p.b(r0, r1, r2, r3, r4, r5)
            androidx.appcompat.widget.TintTypedArray r9 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes(r0, r1, r2, r3, r4)
            r1 = 2
            android.graphics.drawable.Drawable r1 = r9.getDrawable(r1)
            r7.l = r1
            android.graphics.drawable.Drawable r1 = r7.k
            r2 = 1
            if (r1 == 0) goto L_0x0093
            r1 = 2130969269(0x7f0402b5, float:1.7547215E38)
            boolean r1 = og.k.K(r0, r1, r6)
            if (r1 == 0) goto L_0x0093
            int r1 = r9.getResourceId(r6, r6)
            int r3 = r9.getResourceId(r2, r6)
            int r4 = f1042A
            if (r1 != r4) goto L_0x0093
            if (r3 != 0) goto L_0x0093
            super.setButtonDrawable((android.graphics.drawable.Drawable) r8)
            r8 = 2131232148(0x7f080594, float:1.8080397E38)
            android.graphics.drawable.Drawable r8 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r0, r8)
            r7.k = r8
            r7.m = r2
            android.graphics.drawable.Drawable r8 = r7.l
            if (r8 != 0) goto L_0x0093
            r8 = 2131232150(0x7f080596, float:1.8080401E38)
            android.graphics.drawable.Drawable r8 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r0, r8)
            r7.l = r8
        L_0x0093:
            r8 = 3
            android.content.res.ColorStateList r8 = B1.a.v(r0, r9, r8)
            r7.f1047o = r8
            r8 = 4
            r0 = -1
            int r8 = r9.getInt(r8, r0)
            android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.SRC_IN
            android.graphics.PorterDuff$Mode r8 = h2.u.d(r8, r0)
            r7.f1048p = r8
            r8 = 10
            boolean r8 = r9.getBoolean(r8, r6)
            r7.g = r8
            r8 = 6
            boolean r8 = r9.getBoolean(r8, r2)
            r7.f1044h = r8
            r8 = 9
            boolean r8 = r9.getBoolean(r8, r6)
            r7.f1045i = r8
            r8 = 8
            java.lang.CharSequence r8 = r9.getText(r8)
            r7.f1046j = r8
            r8 = 7
            boolean r0 = r9.hasValue(r8)
            if (r0 == 0) goto L_0x00d5
            int r8 = r9.getInt(r8, r6)
            r7.setCheckedState(r8)
        L_0x00d5:
            r9.recycle()
            r7.a()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: b2.C0082c.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    private String getButtonStateDescription() {
        int i2 = this.q;
        if (i2 == 1) {
            return getResources().getString(R.string.mtrl_checkbox_state_description_checked);
        }
        if (i2 == 0) {
            return getResources().getString(R.string.mtrl_checkbox_state_description_unchecked);
        }
        return getResources().getString(R.string.mtrl_checkbox_state_description_indeterminate);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.f == null) {
            int W6 = C0246a.W(R$attr.colorControlActivated, this);
            int W10 = C0246a.W(R$attr.colorError, this);
            int W11 = C0246a.W(R.attr.colorSurface, this);
            int W12 = C0246a.W(R.attr.colorOnSurface, this);
            this.f = new ColorStateList(z, new int[]{C0246a.c0(1.0f, W11, W10), C0246a.c0(1.0f, W11, W6), C0246a.c0(0.54f, W11, W12), C0246a.c0(0.38f, W11, W12), C0246a.c0(0.38f, W11, W12)});
        }
        return this.f;
    }

    private ColorStateList getSuperButtonTintList() {
        ColorStateList colorStateList = this.n;
        if (colorStateList != null) {
            return colorStateList;
        }
        if (super.getButtonTintList() != null) {
            return super.getButtonTintList();
        }
        return getSupportButtonTintList();
    }

    public final void a() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        Drawable drawable = this.k;
        ColorStateList colorStateList3 = this.n;
        PorterDuff.Mode buttonTintMode = CompoundButtonCompat.getButtonTintMode(this);
        Drawable drawable2 = null;
        if (drawable == null) {
            drawable = null;
        } else if (colorStateList3 != null) {
            drawable = DrawableCompat.wrap(drawable).mutate();
            if (buttonTintMode != null) {
                DrawableCompat.setTintMode(drawable, buttonTintMode);
            }
        }
        this.k = drawable;
        Drawable drawable3 = this.l;
        ColorStateList colorStateList4 = this.f1047o;
        PorterDuff.Mode mode = this.f1048p;
        if (drawable3 != null) {
            if (colorStateList4 != null) {
                drawable2 = DrawableCompat.wrap(drawable3).mutate();
                if (mode != null) {
                    DrawableCompat.setTintMode(drawable2, mode);
                }
            } else {
                drawable2 = drawable3;
            }
        }
        this.l = drawable2;
        if (this.m) {
            AnimatedVectorDrawableCompat animatedVectorDrawableCompat = this.v;
            if (animatedVectorDrawableCompat != null) {
                C0080a aVar = this.w;
                animatedVectorDrawableCompat.unregisterAnimationCallback(aVar);
                animatedVectorDrawableCompat.registerAnimationCallback(aVar);
            }
            Drawable drawable4 = this.k;
            if ((drawable4 instanceof AnimatedStateListDrawable) && animatedVectorDrawableCompat != null) {
                ((AnimatedStateListDrawable) drawable4).addTransition(R.id.checked, R.id.unchecked, animatedVectorDrawableCompat, false);
                ((AnimatedStateListDrawable) this.k).addTransition(R.id.indeterminate, R.id.unchecked, animatedVectorDrawableCompat, false);
            }
        }
        Drawable drawable5 = this.k;
        if (!(drawable5 == null || (colorStateList2 = this.n) == null)) {
            DrawableCompat.setTintList(drawable5, colorStateList2);
        }
        Drawable drawable6 = this.l;
        if (!(drawable6 == null || (colorStateList = this.f1047o) == null)) {
            DrawableCompat.setTintList(drawable6, colorStateList);
        }
        Drawable drawable7 = this.k;
        Drawable drawable8 = this.l;
        if (drawable7 == null) {
            drawable7 = drawable8;
        } else if (drawable8 != null) {
            int intrinsicWidth = drawable8.getIntrinsicWidth();
            if (intrinsicWidth == -1) {
                intrinsicWidth = drawable7.getIntrinsicWidth();
            }
            int intrinsicHeight = drawable8.getIntrinsicHeight();
            if (intrinsicHeight == -1) {
                intrinsicHeight = drawable7.getIntrinsicHeight();
            }
            if (intrinsicWidth > drawable7.getIntrinsicWidth() || intrinsicHeight > drawable7.getIntrinsicHeight()) {
                float f5 = ((float) intrinsicWidth) / ((float) intrinsicHeight);
                if (f5 >= ((float) drawable7.getIntrinsicWidth()) / ((float) drawable7.getIntrinsicHeight())) {
                    int intrinsicWidth2 = drawable7.getIntrinsicWidth();
                    intrinsicHeight = (int) (((float) intrinsicWidth2) / f5);
                    intrinsicWidth = intrinsicWidth2;
                } else {
                    intrinsicHeight = drawable7.getIntrinsicHeight();
                    intrinsicWidth = (int) (f5 * ((float) intrinsicHeight));
                }
            }
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{drawable7, drawable8});
            layerDrawable.setLayerSize(1, intrinsicWidth, intrinsicHeight);
            layerDrawable.setLayerGravity(1, 17);
            drawable7 = layerDrawable;
        }
        super.setButtonDrawable(drawable7);
        refreshDrawableState();
    }

    public Drawable getButtonDrawable() {
        return this.k;
    }

    public Drawable getButtonIconDrawable() {
        return this.l;
    }

    public ColorStateList getButtonIconTintList() {
        return this.f1047o;
    }

    public PorterDuff.Mode getButtonIconTintMode() {
        return this.f1048p;
    }

    public ColorStateList getButtonTintList() {
        return this.n;
    }

    public int getCheckedState() {
        return this.q;
    }

    public CharSequence getErrorAccessibilityLabel() {
        return this.f1046j;
    }

    public final boolean isChecked() {
        if (this.q == 1) {
            return true;
        }
        return false;
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.g && this.n == null && this.f1047o == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public final int[] onCreateDrawableState(int i2) {
        int[] copyOf;
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 2);
        if (getCheckedState() == 2) {
            View.mergeDrawableStates(onCreateDrawableState, f1043x);
        }
        if (this.f1045i) {
            View.mergeDrawableStates(onCreateDrawableState, y);
        }
        int i7 = 0;
        while (true) {
            if (i7 >= onCreateDrawableState.length) {
                copyOf = Arrays.copyOf(onCreateDrawableState, onCreateDrawableState.length + 1);
                copyOf[onCreateDrawableState.length] = 16842912;
                break;
            }
            int i8 = onCreateDrawableState[i7];
            if (i8 == 16842912) {
                copyOf = onCreateDrawableState;
                break;
            } else if (i8 == 0) {
                copyOf = (int[]) onCreateDrawableState.clone();
                copyOf[i7] = 16842912;
                break;
            } else {
                i7++;
            }
        }
        this.r = copyOf;
        return onCreateDrawableState;
    }

    public final void onDraw(Canvas canvas) {
        Drawable buttonDrawable;
        int i2;
        if (!this.f1044h || !TextUtils.isEmpty(getText()) || (buttonDrawable = CompoundButtonCompat.getButtonDrawable(this)) == null) {
            super.onDraw(canvas);
            return;
        }
        if (u.c(this)) {
            i2 = -1;
        } else {
            i2 = 1;
        }
        int width = ((getWidth() - buttonDrawable.getIntrinsicWidth()) / 2) * i2;
        int save = canvas.save();
        canvas.translate((float) width, 0.0f);
        super.onDraw(canvas);
        canvas.restoreToCount(save);
        if (getBackground() != null) {
            Rect bounds = buttonDrawable.getBounds();
            DrawableCompat.setHotspotBounds(getBackground(), bounds.left + width, bounds.top, bounds.right + width, bounds.bottom);
        }
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (accessibilityNodeInfo != null && this.f1045i) {
            accessibilityNodeInfo.setText(accessibilityNodeInfo.getText() + ArcCommonLog.TAG_COMMA + this.f1046j);
        }
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof C0081b)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        C0081b bVar = (C0081b) parcelable;
        super.onRestoreInstanceState(bVar.getSuperState());
        setCheckedState(bVar.d);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.view.View$BaseSavedState, android.os.Parcelable, b2.b] */
    public final Parcelable onSaveInstanceState() {
        ? baseSavedState = new View.BaseSavedState(super.onSaveInstanceState());
        baseSavedState.d = getCheckedState();
        return baseSavedState;
    }

    public void setButtonDrawable(int i2) {
        setButtonDrawable(AppCompatResources.getDrawable(getContext(), i2));
    }

    public void setButtonIconDrawable(Drawable drawable) {
        this.l = drawable;
        a();
    }

    public void setButtonIconDrawableResource(int i2) {
        setButtonIconDrawable(AppCompatResources.getDrawable(getContext(), i2));
    }

    public void setButtonIconTintList(ColorStateList colorStateList) {
        if (this.f1047o != colorStateList) {
            this.f1047o = colorStateList;
            a();
        }
    }

    public void setButtonIconTintMode(PorterDuff.Mode mode) {
        if (this.f1048p != mode) {
            this.f1048p = mode;
            a();
        }
    }

    public void setButtonTintList(ColorStateList colorStateList) {
        if (this.n != colorStateList) {
            this.n = colorStateList;
            a();
        }
    }

    public void setButtonTintMode(PorterDuff.Mode mode) {
        setSupportButtonTintMode(mode);
        a();
    }

    public void setCenterIfNoTextEnabled(boolean z3) {
        this.f1044h = z3;
    }

    public void setChecked(boolean z3) {
        setCheckedState(z3 ? 1 : 0);
    }

    public void setCheckedState(int i2) {
        boolean z3;
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        if (this.q != i2) {
            this.q = i2;
            if (i2 == 1) {
                z3 = true;
            } else {
                z3 = false;
            }
            super.setChecked(z3);
            refreshDrawableState();
            if (this.t == null) {
                super.setStateDescription(getButtonStateDescription());
            }
            if (!this.s) {
                this.s = true;
                LinkedHashSet linkedHashSet = this.e;
                if (linkedHashSet != null) {
                    Iterator it = linkedHashSet.iterator();
                    if (it.hasNext()) {
                        throw C0212a.h(it);
                    }
                }
                if (!(this.q == 2 || (onCheckedChangeListener = this.u) == null)) {
                    onCheckedChangeListener.onCheckedChanged(this, isChecked());
                }
                AutofillManager autofillManager = (AutofillManager) getContext().getSystemService(AutofillManager.class);
                if (autofillManager != null) {
                    autofillManager.notifyValueChanged(this);
                }
                this.s = false;
            }
        }
    }

    public void setEnabled(boolean z3) {
        super.setEnabled(z3);
    }

    public void setErrorAccessibilityLabel(CharSequence charSequence) {
        this.f1046j = charSequence;
    }

    public void setErrorAccessibilityLabelResource(int i2) {
        CharSequence charSequence;
        if (i2 != 0) {
            charSequence = getResources().getText(i2);
        } else {
            charSequence = null;
        }
        setErrorAccessibilityLabel(charSequence);
    }

    public void setErrorShown(boolean z3) {
        if (this.f1045i != z3) {
            this.f1045i = z3;
            refreshDrawableState();
            Iterator it = this.d.iterator();
            if (it.hasNext()) {
                throw C0212a.h(it);
            }
        }
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.u = onCheckedChangeListener;
    }

    public void setStateDescription(CharSequence charSequence) {
        this.t = charSequence;
        if (charSequence != null) {
            super.setStateDescription(charSequence);
        } else if (charSequence == null) {
            super.setStateDescription(getButtonStateDescription());
        }
    }

    public void setUseMaterialThemeColors(boolean z3) {
        this.g = z3;
        if (z3) {
            CompoundButtonCompat.setButtonTintList(this, getMaterialThemeColorsTintList());
        } else {
            CompoundButtonCompat.setButtonTintList(this, (ColorStateList) null);
        }
    }

    public final void toggle() {
        setChecked(!isChecked());
    }

    public void setButtonDrawable(Drawable drawable) {
        this.k = drawable;
        this.m = false;
        a();
    }
}
