package com.google.android.material.internal;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import androidx.appcompat.R$attr;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import h2.C0206a;
import h2.C0207b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CheckableImageButton extends AppCompatImageButton implements Checkable {
    public static final int[] g = {16842912};
    public boolean d;
    public boolean e = true;
    public boolean f = true;

    public CheckableImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R$attr.imageButtonStyle);
        ViewCompat.setAccessibilityDelegate(this, new C0206a(0, this));
    }

    public final boolean isChecked() {
        return this.d;
    }

    public final int[] onCreateDrawableState(int i2) {
        if (this.d) {
            return View.mergeDrawableStates(super.onCreateDrawableState(i2 + 1), g);
        }
        return super.onCreateDrawableState(i2);
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof C0207b)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        C0207b bVar = (C0207b) parcelable;
        super.onRestoreInstanceState(bVar.getSuperState());
        setChecked(bVar.d);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.os.Parcelable, androidx.customview.view.AbsSavedState, h2.b] */
    public final Parcelable onSaveInstanceState() {
        ? absSavedState = new AbsSavedState(super.onSaveInstanceState());
        absSavedState.d = this.d;
        return absSavedState;
    }

    public void setCheckable(boolean z) {
        if (this.e != z) {
            this.e = z;
            sendAccessibilityEvent(0);
        }
    }

    public void setChecked(boolean z) {
        if (this.e && this.d != z) {
            this.d = z;
            refreshDrawableState();
            sendAccessibilityEvent(2048);
        }
    }

    public void setPressable(boolean z) {
        this.f = z;
    }

    public void setPressed(boolean z) {
        if (this.f) {
            super.setPressed(z);
        }
    }

    public final void toggle() {
        setChecked(!this.d);
    }
}
