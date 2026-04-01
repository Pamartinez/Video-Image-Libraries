package com.google.android.material.button;

import G0.e;
import Z1.a;
import Z1.b;
import Z1.c;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Parcelable;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CompoundButton;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import com.sec.android.gallery3d.R;
import h2.p;
import h2.u;
import i.C0212a;
import java.util.Iterator;
import java.util.LinkedHashSet;
import og.k;
import v2.C0299a;
import x2.C0334a;
import x2.C0343j;
import x2.C0344k;
import x2.C0353t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MaterialButton extends AppCompatButton implements Checkable, C0353t {
    public static final int[] r = {16842911};
    public static final int[] s = {16842912};
    public final c d;
    public final LinkedHashSet e = new LinkedHashSet();
    public a f;
    public PorterDuff.Mode g;

    /* renamed from: h  reason: collision with root package name */
    public ColorStateList f1429h;

    /* renamed from: i  reason: collision with root package name */
    public Drawable f1430i;

    /* renamed from: j  reason: collision with root package name */
    public String f1431j;
    public int k;
    public int l;
    public int m;
    public int n;

    /* renamed from: o  reason: collision with root package name */
    public boolean f1432o;

    /* renamed from: p  reason: collision with root package name */
    public boolean f1433p;
    public int q;

    public MaterialButton(Context context, AttributeSet attributeSet) {
        super(D2.a.a(context, attributeSet, R.attr.materialButtonStyle, com.samsung.android.photoremaster.engine.R.style.Widget_MaterialComponents_Button), attributeSet, R.attr.materialButtonStyle);
        boolean z = false;
        this.f1432o = false;
        this.f1433p = false;
        Context context2 = getContext();
        AttributeSet attributeSet2 = attributeSet;
        TypedArray d2 = p.d(context2, attributeSet2, Q1.a.v, R.attr.materialButtonStyle, com.samsung.android.photoremaster.engine.R.style.Widget_MaterialComponents_Button, new int[0]);
        this.n = d2.getDimensionPixelSize(12, 0);
        int i2 = d2.getInt(15, -1);
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        this.g = u.d(i2, mode);
        this.f1429h = B1.a.u(getContext(), d2, 14);
        this.f1430i = B1.a.w(getContext(), d2, 10);
        this.q = d2.getInteger(11, 1);
        this.k = d2.getDimensionPixelSize(13, 0);
        c cVar = new c(this, C0344k.b(context2, attributeSet2, R.attr.materialButtonStyle, com.samsung.android.photoremaster.engine.R.style.Widget_MaterialComponents_Button).a());
        this.d = cVar;
        cVar.f960c = d2.getDimensionPixelOffset(1, 0);
        cVar.d = d2.getDimensionPixelOffset(2, 0);
        cVar.e = d2.getDimensionPixelOffset(3, 0);
        cVar.f = d2.getDimensionPixelOffset(4, 0);
        if (d2.hasValue(8)) {
            int dimensionPixelSize = d2.getDimensionPixelSize(8, -1);
            cVar.g = dimensionPixelSize;
            float f5 = (float) dimensionPixelSize;
            C0343j e7 = cVar.b.e();
            e7.e = new C0334a(f5);
            e7.f = new C0334a(f5);
            e7.g = new C0334a(f5);
            e7.f2119h = new C0334a(f5);
            cVar.c(e7.a());
            cVar.f965p = true;
        }
        cVar.f961h = d2.getDimensionPixelSize(20, 0);
        cVar.f962i = u.d(d2.getInt(7, -1), mode);
        cVar.f963j = B1.a.u(getContext(), d2, 6);
        cVar.k = B1.a.u(getContext(), d2, 19);
        cVar.l = B1.a.u(getContext(), d2, 16);
        cVar.q = d2.getBoolean(5, false);
        cVar.t = d2.getDimensionPixelSize(9, 0);
        cVar.r = d2.getBoolean(21, true);
        int paddingStart = ViewCompat.getPaddingStart(this);
        int paddingTop = getPaddingTop();
        int paddingEnd = ViewCompat.getPaddingEnd(this);
        int paddingBottom = getPaddingBottom();
        if (d2.hasValue(0)) {
            cVar.f964o = true;
            setSupportBackgroundTintList(cVar.f963j);
            setSupportBackgroundTintMode(cVar.f962i);
        } else {
            cVar.e();
        }
        ViewCompat.setPaddingRelative(this, paddingStart + cVar.f960c, paddingTop + cVar.e, paddingEnd + cVar.d, paddingBottom + cVar.f);
        d2.recycle();
        setCompoundDrawablePadding(this.n);
        c(this.f1430i != null ? true : z);
    }

    private Layout.Alignment getActualTextAlignment() {
        int textAlignment = getTextAlignment();
        if (textAlignment == 1) {
            return getGravityTextAlignment();
        }
        if (textAlignment == 6 || textAlignment == 3) {
            return Layout.Alignment.ALIGN_OPPOSITE;
        }
        if (textAlignment != 4) {
            return Layout.Alignment.ALIGN_NORMAL;
        }
        return Layout.Alignment.ALIGN_CENTER;
    }

    private Layout.Alignment getGravityTextAlignment() {
        int gravity = getGravity() & 8388615;
        if (gravity == 1) {
            return Layout.Alignment.ALIGN_CENTER;
        }
        if (gravity == 5 || gravity == 8388613) {
            return Layout.Alignment.ALIGN_OPPOSITE;
        }
        return Layout.Alignment.ALIGN_NORMAL;
    }

    private int getTextHeight() {
        if (getLineCount() > 1) {
            return getLayout().getHeight();
        }
        TextPaint paint = getPaint();
        String charSequence = getText().toString();
        if (getTransformationMethod() != null) {
            charSequence = getTransformationMethod().getTransformation(charSequence, this).toString();
        }
        Rect rect = new Rect();
        paint.getTextBounds(charSequence, 0, charSequence.length(), rect);
        return Math.min(rect.height(), getLayout().getHeight());
    }

    private int getTextLayoutWidth() {
        int lineCount = getLineCount();
        float f5 = 0.0f;
        for (int i2 = 0; i2 < lineCount; i2++) {
            f5 = Math.max(f5, getLayout().getLineWidth(i2));
        }
        return (int) Math.ceil((double) f5);
    }

    public final boolean a() {
        c cVar = this.d;
        if (cVar == null || cVar.f964o) {
            return false;
        }
        return true;
    }

    public final void b() {
        int i2 = this.q;
        if (i2 == 1 || i2 == 2) {
            TextViewCompat.setCompoundDrawablesRelative(this, this.f1430i, (Drawable) null, (Drawable) null, (Drawable) null);
        } else if (i2 == 3 || i2 == 4) {
            TextViewCompat.setCompoundDrawablesRelative(this, (Drawable) null, (Drawable) null, this.f1430i, (Drawable) null);
        } else if (i2 == 16 || i2 == 32) {
            TextViewCompat.setCompoundDrawablesRelative(this, (Drawable) null, this.f1430i, (Drawable) null, (Drawable) null);
        }
    }

    public final void c(boolean z) {
        Drawable drawable = this.f1430i;
        if (drawable != null) {
            Drawable mutate = DrawableCompat.wrap(drawable).mutate();
            this.f1430i = mutate;
            DrawableCompat.setTintList(mutate, this.f1429h);
            PorterDuff.Mode mode = this.g;
            if (mode != null) {
                DrawableCompat.setTintMode(this.f1430i, mode);
            }
            int i2 = this.k;
            if (i2 == 0) {
                i2 = this.f1430i.getIntrinsicWidth();
            }
            int i7 = this.k;
            if (i7 == 0) {
                i7 = this.f1430i.getIntrinsicHeight();
            }
            Drawable drawable2 = this.f1430i;
            int i8 = this.l;
            int i10 = this.m;
            drawable2.setBounds(i8, i10, i2 + i8, i7 + i10);
            this.f1430i.setVisible(true, z);
        }
        if (z) {
            b();
            return;
        }
        Drawable[] compoundDrawablesRelative = TextViewCompat.getCompoundDrawablesRelative(this);
        Drawable drawable3 = compoundDrawablesRelative[0];
        Drawable drawable4 = compoundDrawablesRelative[1];
        Drawable drawable5 = compoundDrawablesRelative[2];
        int i11 = this.q;
        if (((i11 == 1 || i11 == 2) && drawable3 != this.f1430i) || (((i11 == 3 || i11 == 4) && drawable5 != this.f1430i) || ((i11 == 16 || i11 == 32) && drawable4 != this.f1430i))) {
            b();
        }
    }

    public final void d(int i2, int i7) {
        boolean z;
        if (this.f1430i != null && getLayout() != null) {
            int i8 = this.q;
            boolean z3 = true;
            if (i8 == 1 || i8 == 2 || i8 == 3 || i8 == 4) {
                this.m = 0;
                Layout.Alignment actualTextAlignment = getActualTextAlignment();
                int i10 = this.q;
                if (i10 == 1 || i10 == 3 || ((i10 == 2 && actualTextAlignment == Layout.Alignment.ALIGN_NORMAL) || (i10 == 4 && actualTextAlignment == Layout.Alignment.ALIGN_OPPOSITE))) {
                    this.l = 0;
                    c(false);
                    return;
                }
                int i11 = this.k;
                if (i11 == 0) {
                    i11 = this.f1430i.getIntrinsicWidth();
                }
                int textLayoutWidth = ((((i2 - getTextLayoutWidth()) - ViewCompat.getPaddingEnd(this)) - i11) - this.n) - ViewCompat.getPaddingStart(this);
                if (actualTextAlignment == Layout.Alignment.ALIGN_CENTER) {
                    textLayoutWidth /= 2;
                }
                if (ViewCompat.getLayoutDirection(this) == 1) {
                    z = true;
                } else {
                    z = false;
                }
                if (this.q != 4) {
                    z3 = false;
                }
                if (z != z3) {
                    textLayoutWidth = -textLayoutWidth;
                }
                if (this.l != textLayoutWidth) {
                    this.l = textLayoutWidth;
                    c(false);
                }
            } else if (i8 == 16 || i8 == 32) {
                this.l = 0;
                if (i8 == 16) {
                    this.m = 0;
                    c(false);
                    return;
                }
                int i12 = this.k;
                if (i12 == 0) {
                    i12 = this.f1430i.getIntrinsicHeight();
                }
                int max = Math.max(0, (((((i7 - getTextHeight()) - getPaddingTop()) - i12) - this.n) - getPaddingBottom()) / 2);
                if (this.m != max) {
                    this.m = max;
                    c(false);
                }
            }
        }
    }

    public String getA11yClassName() {
        Class cls;
        if (!TextUtils.isEmpty(this.f1431j)) {
            return this.f1431j;
        }
        c cVar = this.d;
        if (cVar == null || !cVar.q) {
            cls = Button.class;
        } else {
            cls = CompoundButton.class;
        }
        return cls.getName();
    }

    public ColorStateList getBackgroundTintList() {
        return getSupportBackgroundTintList();
    }

    public PorterDuff.Mode getBackgroundTintMode() {
        return getSupportBackgroundTintMode();
    }

    public int getCornerRadius() {
        if (a()) {
            return this.d.g;
        }
        return 0;
    }

    public Drawable getIcon() {
        return this.f1430i;
    }

    public int getIconGravity() {
        return this.q;
    }

    public int getIconPadding() {
        return this.n;
    }

    public int getIconSize() {
        return this.k;
    }

    public ColorStateList getIconTint() {
        return this.f1429h;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.g;
    }

    public int getInsetBottom() {
        return this.d.f;
    }

    public int getInsetTop() {
        return this.d.e;
    }

    public ColorStateList getRippleColor() {
        if (a()) {
            return this.d.l;
        }
        return null;
    }

    public C0344k getShapeAppearanceModel() {
        if (a()) {
            return this.d.b;
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    public ColorStateList getStrokeColor() {
        if (a()) {
            return this.d.k;
        }
        return null;
    }

    public int getStrokeWidth() {
        if (a()) {
            return this.d.f961h;
        }
        return 0;
    }

    public ColorStateList getSupportBackgroundTintList() {
        if (a()) {
            return this.d.f963j;
        }
        return super.getSupportBackgroundTintList();
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        if (a()) {
            return this.d.f962i;
        }
        return super.getSupportBackgroundTintMode();
    }

    public final boolean isChecked() {
        return this.f1432o;
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (a()) {
            k.P(this, this.d.b(false));
        }
    }

    public final int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 2);
        c cVar = this.d;
        if (cVar != null && cVar.q) {
            View.mergeDrawableStates(onCreateDrawableState, r);
        }
        if (this.f1432o) {
            View.mergeDrawableStates(onCreateDrawableState, s);
        }
        return onCreateDrawableState;
    }

    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(getA11yClassName());
        accessibilityEvent.setChecked(this.f1432o);
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        boolean z;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getA11yClassName());
        c cVar = this.d;
        if (cVar == null || !cVar.q) {
            z = false;
        } else {
            z = true;
        }
        accessibilityNodeInfo.setCheckable(z);
        accessibilityNodeInfo.setChecked(this.f1432o);
        accessibilityNodeInfo.setClickable(isClickable());
    }

    public final void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        super.onLayout(z, i2, i7, i8, i10);
        d(getMeasuredWidth(), getMeasuredHeight());
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof b)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        b bVar = (b) parcelable;
        super.onRestoreInstanceState(bVar.getSuperState());
        setChecked(bVar.d);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [Z1.b, android.os.Parcelable, androidx.customview.view.AbsSavedState] */
    public final Parcelable onSaveInstanceState() {
        ? absSavedState = new AbsSavedState(super.onSaveInstanceState());
        absSavedState.d = this.f1432o;
        return absSavedState;
    }

    public final void onTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
        super.onTextChanged(charSequence, i2, i7, i8);
        d(getMeasuredWidth(), getMeasuredHeight());
    }

    public final boolean performClick() {
        if (this.d.r) {
            toggle();
        }
        return super.performClick();
    }

    public final void refreshDrawableState() {
        super.refreshDrawableState();
        if (this.f1430i != null) {
            if (this.f1430i.setState(getDrawableState())) {
                invalidate();
            }
        }
    }

    public void setA11yClassName(String str) {
        this.f1431j = str;
    }

    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public void setBackgroundColor(int i2) {
        if (a()) {
            c cVar = this.d;
            if (cVar.b(false) != null) {
                cVar.b(false).setTint(i2);
                return;
            }
            return;
        }
        super.setBackgroundColor(i2);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (!a()) {
            super.setBackgroundDrawable(drawable);
        } else if (drawable != getBackground()) {
            Log.w("MaterialButton", "MaterialButton manages its own background to control elevation, shape, color and states. Consider using backgroundTint, shapeAppearance and other attributes where available. A custom background will ignore these attributes and you should consider handling interaction states such as pressed, focused and disabled");
            c cVar = this.d;
            cVar.f964o = true;
            MaterialButton materialButton = cVar.f959a;
            materialButton.setSupportBackgroundTintList(cVar.f963j);
            materialButton.setSupportBackgroundTintMode(cVar.f962i);
            super.setBackgroundDrawable(drawable);
        } else {
            getBackground().setState(drawable.getState());
        }
    }

    public void setBackgroundResource(int i2) {
        Drawable drawable;
        if (i2 != 0) {
            drawable = AppCompatResources.getDrawable(getContext(), i2);
        } else {
            drawable = null;
        }
        setBackgroundDrawable(drawable);
    }

    public void setBackgroundTintList(ColorStateList colorStateList) {
        setSupportBackgroundTintList(colorStateList);
    }

    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        setSupportBackgroundTintMode(mode);
    }

    public void setCheckable(boolean z) {
        if (a()) {
            this.d.q = z;
        }
    }

    public void setChecked(boolean z) {
        c cVar = this.d;
        if (cVar != null && cVar.q && isEnabled() && this.f1432o != z) {
            this.f1432o = z;
            refreshDrawableState();
            if (getParent() instanceof MaterialButtonToggleGroup) {
                MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) getParent();
                boolean z3 = this.f1432o;
                if (!materialButtonToggleGroup.f1435i) {
                    materialButtonToggleGroup.b(getId(), z3);
                }
            }
            if (!this.f1433p) {
                this.f1433p = true;
                Iterator it = this.e.iterator();
                if (!it.hasNext()) {
                    this.f1433p = false;
                    return;
                }
                throw C0212a.h(it);
            }
        }
    }

    public void setCornerRadius(int i2) {
        if (a()) {
            c cVar = this.d;
            if (!cVar.f965p || cVar.g != i2) {
                cVar.g = i2;
                cVar.f965p = true;
                float f5 = (float) i2;
                C0343j e7 = cVar.b.e();
                e7.e = new C0334a(f5);
                e7.f = new C0334a(f5);
                e7.g = new C0334a(f5);
                e7.f2119h = new C0334a(f5);
                cVar.c(e7.a());
            }
        }
    }

    public void setCornerRadiusResource(int i2) {
        if (a()) {
            setCornerRadius(getResources().getDimensionPixelSize(i2));
        }
    }

    public void setElevation(float f5) {
        super.setElevation(f5);
        if (a()) {
            this.d.b(false).j(f5);
        }
    }

    public void setIcon(Drawable drawable) {
        if (this.f1430i != drawable) {
            this.f1430i = drawable;
            c(true);
            d(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconGravity(int i2) {
        if (this.q != i2) {
            this.q = i2;
            d(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconPadding(int i2) {
        if (this.n != i2) {
            this.n = i2;
            setCompoundDrawablePadding(i2);
        }
    }

    public void setIconResource(int i2) {
        Drawable drawable;
        if (i2 != 0) {
            drawable = AppCompatResources.getDrawable(getContext(), i2);
        } else {
            drawable = null;
        }
        setIcon(drawable);
    }

    public void setIconSize(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        } else if (this.k != i2) {
            this.k = i2;
            c(true);
        }
    }

    public void setIconTint(ColorStateList colorStateList) {
        if (this.f1429h != colorStateList) {
            this.f1429h = colorStateList;
            c(false);
        }
    }

    public void setIconTintMode(PorterDuff.Mode mode) {
        if (this.g != mode) {
            this.g = mode;
            c(false);
        }
    }

    public void setIconTintResource(int i2) {
        setIconTint(AppCompatResources.getColorStateList(getContext(), i2));
    }

    public void setInsetBottom(int i2) {
        c cVar = this.d;
        cVar.d(cVar.e, i2);
    }

    public void setInsetTop(int i2) {
        c cVar = this.d;
        cVar.d(i2, cVar.f);
    }

    public void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public void setOnPressedChangeListenerInternal(a aVar) {
        this.f = aVar;
    }

    public void setPressed(boolean z) {
        a aVar = this.f;
        if (aVar != null) {
            ((MaterialButtonToggleGroup) ((e) aVar).d).invalidate();
        }
        super.setPressed(z);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (a()) {
            c cVar = this.d;
            MaterialButton materialButton = cVar.f959a;
            if (cVar.l != colorStateList) {
                cVar.l = colorStateList;
                if (materialButton.getBackground() instanceof RippleDrawable) {
                    ((RippleDrawable) materialButton.getBackground()).setColor(C0299a.b(colorStateList));
                }
            }
        }
    }

    public void setRippleColorResource(int i2) {
        if (a()) {
            setRippleColor(AppCompatResources.getColorStateList(getContext(), i2));
        }
    }

    public void setShapeAppearanceModel(C0344k kVar) {
        if (a()) {
            this.d.c(kVar);
            return;
        }
        throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
    }

    public void setShouldDrawSurfaceColorStroke(boolean z) {
        if (a()) {
            c cVar = this.d;
            cVar.n = z;
            cVar.f();
        }
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        if (a()) {
            c cVar = this.d;
            if (cVar.k != colorStateList) {
                cVar.k = colorStateList;
                cVar.f();
            }
        }
    }

    public void setStrokeColorResource(int i2) {
        if (a()) {
            setStrokeColor(AppCompatResources.getColorStateList(getContext(), i2));
        }
    }

    public void setStrokeWidth(int i2) {
        if (a()) {
            c cVar = this.d;
            if (cVar.f961h != i2) {
                cVar.f961h = i2;
                cVar.f();
            }
        }
    }

    public void setStrokeWidthResource(int i2) {
        if (a()) {
            setStrokeWidth(getResources().getDimensionPixelSize(i2));
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (a()) {
            c cVar = this.d;
            if (cVar.f963j != colorStateList) {
                cVar.f963j = colorStateList;
                if (cVar.b(false) != null) {
                    DrawableCompat.setTintList(cVar.b(false), cVar.f963j);
                    return;
                }
                return;
            }
            return;
        }
        super.setSupportBackgroundTintList(colorStateList);
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (a()) {
            c cVar = this.d;
            if (cVar.f962i != mode) {
                cVar.f962i = mode;
                if (cVar.b(false) != null && cVar.f962i != null) {
                    DrawableCompat.setTintMode(cVar.b(false), cVar.f962i);
                    return;
                }
                return;
            }
            return;
        }
        super.setSupportBackgroundTintMode(mode);
    }

    public void setTextAlignment(int i2) {
        super.setTextAlignment(i2);
        d(getMeasuredWidth(), getMeasuredHeight());
    }

    public void setToggleCheckedStateOnClick(boolean z) {
        this.d.r = z;
    }

    public final void toggle() {
        setChecked(!this.f1432o);
    }
}
