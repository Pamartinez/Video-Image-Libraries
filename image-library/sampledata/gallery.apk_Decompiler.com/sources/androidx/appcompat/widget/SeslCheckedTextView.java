package androidx.appcompat.widget;

import N2.j;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewDebug;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.TextView;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$styleable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.reflect.view.SeslViewReflector;
import androidx.reflect.widget.SeslTextViewReflector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslCheckedTextView extends TextView implements Checkable {
    private static final int[] CHECKED_STATE_SET = {16842912};
    private int mBasePadding;
    private Drawable mCheckMarkDrawable;
    private int mCheckMarkGravity;
    private int mCheckMarkPadding;
    private int mCheckMarkResource;
    private ColorStateList mCheckMarkTintList;
    private PorterDuff.Mode mCheckMarkTintMode;
    private int mCheckMarkWidth;
    private boolean mChecked;
    private int mDrawablePadding;
    private boolean mHasCheckMarkTint;
    private boolean mHasCheckMarkTintMode;
    private boolean mNeedRequestlayout;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };
        boolean checked;

        public String toString() {
            StringBuilder sb2 = new StringBuilder("SeslCheckedTextView.SavedState{");
            sb2.append(Integer.toHexString(System.identityHashCode(this)));
            sb2.append(" checked=");
            return j.h(sb2, this.checked, "}");
        }

        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeValue(Boolean.valueOf(this.checked));
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.checked = ((Boolean) parcel.readValue((ClassLoader) null)).booleanValue();
        }
    }

    public SeslCheckedTextView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    private void applyCheckMarkTint() {
        Drawable drawable = this.mCheckMarkDrawable;
        if (drawable == null) {
            return;
        }
        if (this.mHasCheckMarkTint || this.mHasCheckMarkTintMode) {
            Drawable mutate = drawable.mutate();
            this.mCheckMarkDrawable = mutate;
            if (this.mHasCheckMarkTint) {
                mutate.setTintList(this.mCheckMarkTintList);
            }
            if (this.mHasCheckMarkTintMode) {
                this.mCheckMarkDrawable.setTintMode(this.mCheckMarkTintMode);
            }
            if (this.mCheckMarkDrawable.isStateful()) {
                this.mCheckMarkDrawable.setState(getDrawableState());
            }
        }
    }

    private boolean isCheckMarkAtStart() {
        if ((Gravity.getAbsoluteGravity(this.mCheckMarkGravity, ViewCompat.getLayoutDirection(this)) & 7) == 3) {
            return true;
        }
        return false;
    }

    private void setBasePadding(boolean z) {
        if (z) {
            this.mBasePadding = getPaddingLeft();
        } else {
            this.mBasePadding = getPaddingRight();
        }
    }

    private void setCheckMarkDrawableInternal(Drawable drawable, int i2) {
        boolean z;
        Drawable drawable2 = this.mCheckMarkDrawable;
        if (drawable2 != null) {
            drawable2.setCallback((Drawable.Callback) null);
            unscheduleDrawable(this.mCheckMarkDrawable);
        }
        boolean z3 = true;
        if (drawable != this.mCheckMarkDrawable) {
            z = true;
        } else {
            z = false;
        }
        this.mNeedRequestlayout = z;
        if (drawable != null) {
            drawable.setCallback(this);
            if (getVisibility() != 0) {
                z3 = false;
            }
            drawable.setVisible(z3, false);
            drawable.setState(CHECKED_STATE_SET);
            setMinHeight(drawable.getIntrinsicHeight());
            this.mCheckMarkWidth = drawable.getIntrinsicWidth();
            drawable.setState(getDrawableState());
        } else {
            this.mCheckMarkWidth = 0;
        }
        this.mCheckMarkDrawable = drawable;
        this.mCheckMarkResource = i2;
        applyCheckMarkTint();
        SeslViewReflector.resolvePadding(this);
        setBasePadding(isCheckMarkAtStart());
    }

    private void updatePadding() {
        int i2;
        SeslViewReflector.resetPaddingToInitialValues(this);
        if (this.mCheckMarkDrawable != null) {
            i2 = this.mCheckMarkWidth + this.mBasePadding + this.mCheckMarkPadding + this.mDrawablePadding;
        } else {
            i2 = this.mBasePadding;
        }
        boolean z = true;
        if (isCheckMarkAtStart()) {
            boolean z3 = this.mNeedRequestlayout;
            if (SeslViewReflector.getField_mPaddingLeft(this) == i2) {
                z = false;
            }
            this.mNeedRequestlayout = z3 | z;
            SeslViewReflector.setField_mPaddingLeft(this, i2);
        } else {
            boolean z7 = this.mNeedRequestlayout;
            if (SeslViewReflector.getField_mPaddingRight(this) == i2) {
                z = false;
            }
            this.mNeedRequestlayout = z7 | z;
            SeslViewReflector.setField_mPaddingRight(this, i2);
        }
        if (this.mNeedRequestlayout) {
            requestLayout();
            this.mNeedRequestlayout = false;
        }
    }

    public void drawableHotspotChanged(float f, float f5) {
        super.drawableHotspotChanged(f, f5);
        Drawable drawable = this.mCheckMarkDrawable;
        if (drawable != null) {
            DrawableCompat.setHotspot(drawable, f, f5);
        }
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.mCheckMarkDrawable;
        if (drawable != null && drawable.isStateful() && drawable.setState(getDrawableState())) {
            invalidateDrawable(drawable);
        }
    }

    public CharSequence getAccessibilityClassName() {
        return CheckedTextView.class.getName();
    }

    public Drawable getCheckMarkDrawable() {
        return this.mCheckMarkDrawable;
    }

    public ColorStateList getCheckMarkTintList() {
        return this.mCheckMarkTintList;
    }

    public PorterDuff.Mode getCheckMarkTintMode() {
        return this.mCheckMarkTintMode;
    }

    public void invalidateDrawable(Drawable drawable) {
        super.invalidateDrawable(drawable);
        if (verifyDrawable(drawable)) {
            Rect bounds = drawable.getBounds();
            if (ViewUtils.isLayoutRtl(this) && SeslTextViewReflector.getField_mSingleLine(this)) {
                invalidate(bounds.left, bounds.top, bounds.right, bounds.bottom);
            }
        }
    }

    @ViewDebug.ExportedProperty
    public boolean isChecked() {
        return this.mChecked;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.mCheckMarkDrawable;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    public int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        if (isChecked()) {
            View.mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    public void onDraw(Canvas canvas) {
        int i2;
        int i7;
        int i8;
        super.onDraw(canvas);
        Drawable drawable = this.mCheckMarkDrawable;
        if (drawable != null) {
            int gravity = getGravity() & 112;
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (gravity == 16) {
                i2 = (getHeight() - intrinsicHeight) / 2;
            } else if (gravity != 80) {
                i2 = 0;
            } else {
                i2 = getHeight() - intrinsicHeight;
            }
            boolean isCheckMarkAtStart = isCheckMarkAtStart();
            int width = getWidth();
            int i10 = intrinsicHeight + i2;
            if (isCheckMarkAtStart) {
                i8 = this.mBasePadding;
                i7 = this.mCheckMarkWidth + i8;
            } else {
                i7 = width - this.mBasePadding;
                i8 = i7 - this.mCheckMarkWidth;
            }
            int scrollX = getScrollX();
            if (ViewUtils.isLayoutRtl(this)) {
                drawable.setBounds(scrollX + i8, i2, scrollX + i7, i10);
            } else {
                drawable.setBounds(i8, i2, i7, i10);
            }
            drawable.draw(canvas);
            Drawable background = getBackground();
            if (background != null) {
                DrawableCompat.setHotspotBounds(background, i8 + scrollX, i2, scrollX + i7, i10);
            }
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setChecked(this.mChecked);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCheckable(true);
        accessibilityNodeInfo.setChecked(this.mChecked);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setChecked(savedState.checked);
        requestLayout();
    }

    public void onRtlPropertiesChanged(int i2) {
        super.onRtlPropertiesChanged(i2);
        updatePadding();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.checked = isChecked();
        return savedState;
    }

    public void setCheckMarkDrawable(int i2) {
        if (i2 == 0 || i2 != this.mCheckMarkResource) {
            setCheckMarkDrawableInternal(i2 != 0 ? ContextCompat.getDrawable(getContext(), i2) : null, i2);
        }
    }

    public void setCheckMarkTintList(ColorStateList colorStateList) {
        this.mCheckMarkTintList = colorStateList;
        this.mHasCheckMarkTint = true;
        applyCheckMarkTint();
    }

    public void setCheckMarkTintMode(PorterDuff.Mode mode) {
        this.mCheckMarkTintMode = mode;
        this.mHasCheckMarkTintMode = true;
        applyCheckMarkTint();
    }

    public void setChecked(boolean z) {
        if (this.mChecked != z) {
            this.mChecked = z;
            refreshDrawableState();
            SeslViewReflector.notifyViewAccessibilityStateChangedIfNeeded(this, 0);
        }
    }

    public void setVisibility(int i2) {
        boolean z;
        super.setVisibility(i2);
        Drawable drawable = this.mCheckMarkDrawable;
        if (drawable != null) {
            if (i2 == 0) {
                z = true;
            } else {
                z = false;
            }
            drawable.setVisible(z, false);
        }
    }

    public void toggle() {
        setChecked(!this.mChecked);
    }

    public boolean verifyDrawable(Drawable drawable) {
        if (drawable == this.mCheckMarkDrawable || super.verifyDrawable(drawable)) {
            return true;
        }
        return false;
    }

    public SeslCheckedTextView(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        this.mCheckMarkTintList = null;
        this.mCheckMarkTintMode = null;
        this.mHasCheckMarkTint = false;
        this.mHasCheckMarkTintMode = false;
        this.mCheckMarkGravity = 8388611;
        int[] iArr = R$styleable.CheckedTextView;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i2, i7);
        Context context2 = context;
        try {
            saveAttributeDataForStyleable(context2, iArr, attributeSet, obtainStyledAttributes, i2, i7);
            this.mCheckMarkPadding = context2.getResources().getDimensionPixelSize(R$dimen.sesl_checked_spinner_padding_end);
            Drawable drawable = obtainStyledAttributes.getDrawable(R$styleable.CheckedTextView_android_checkMark);
            if (drawable != null) {
                setCheckMarkDrawable(drawable);
            }
            int i8 = R$styleable.CheckedTextView_android_checkMarkTintMode;
            if (obtainStyledAttributes.hasValue(i8)) {
                this.mCheckMarkTintMode = DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(i8, -1), this.mCheckMarkTintMode);
                this.mHasCheckMarkTintMode = true;
            }
            int i10 = R$styleable.CheckedTextView_android_checkMarkTint;
            if (obtainStyledAttributes.hasValue(i10)) {
                this.mCheckMarkTintList = obtainStyledAttributes.getColorStateList(i10);
                this.mHasCheckMarkTint = true;
            }
            this.mCheckMarkGravity = obtainStyledAttributes.getInt(R$styleable.CheckedTextView_checkMarkGravity, 8388611);
            setChecked(obtainStyledAttributes.getBoolean(R$styleable.CheckedTextView_android_checked, false));
            obtainStyledAttributes.recycle();
            this.mDrawablePadding = context2.getResources().getDimensionPixelSize(R$dimen.sesl_checked_text_padding);
            applyCheckMarkTint();
        } catch (Throwable th) {
            Throwable th2 = th;
            obtainStyledAttributes.recycle();
            throw th2;
        }
    }

    public void setCheckMarkDrawable(Drawable drawable) {
        setCheckMarkDrawableInternal(drawable, 0);
    }
}
