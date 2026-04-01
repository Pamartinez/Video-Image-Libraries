package com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui;

import A.a;
import N2.j;
import c0.C0086a;
import i.C0212a;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\b\b\u0018\u00002\u00020\u0001BM\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0002\u0012\b\b\u0001\u0010\b\u001a\u00020\u0002\u0012\b\b\u0001\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0010R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0015\u001a\u0004\b\u0017\u0010\u0010R\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0015\u001a\u0004\b\u0018\u0010\u0010R\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0015\u001a\u0004\b\u0019\u0010\u0010R\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0015\u001a\u0004\b\u001a\u0010\u0010R\u0017\u0010\b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\u0015\u001a\u0004\b\u001b\u0010\u0010R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\u0015\u001a\u0004\b\u001c\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/CapsuleColor;", "", "", "generalBackgroundColor", "generalTintColor", "toggleOnBackgroundColor", "toggleOnTintColor", "entityBackgroundColor", "entityTintColor", "rippleColor", "<init>", "(IIIIIII)V", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getGeneralBackgroundColor", "getGeneralTintColor", "getToggleOnBackgroundColor", "getToggleOnTintColor", "getEntityBackgroundColor", "getEntityTintColor", "getRippleColor", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CapsuleColor {
    private final int entityBackgroundColor;
    private final int entityTintColor;
    private final int generalBackgroundColor;
    private final int generalTintColor;
    private final int rippleColor;
    private final int toggleOnBackgroundColor;
    private final int toggleOnTintColor;

    public CapsuleColor(int i2, int i7, int i8, int i10, int i11, int i12, int i13) {
        this.generalBackgroundColor = i2;
        this.generalTintColor = i7;
        this.toggleOnBackgroundColor = i8;
        this.toggleOnTintColor = i10;
        this.entityBackgroundColor = i11;
        this.entityTintColor = i12;
        this.rippleColor = i13;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CapsuleColor)) {
            return false;
        }
        CapsuleColor capsuleColor = (CapsuleColor) obj;
        if (this.generalBackgroundColor == capsuleColor.generalBackgroundColor && this.generalTintColor == capsuleColor.generalTintColor && this.toggleOnBackgroundColor == capsuleColor.toggleOnBackgroundColor && this.toggleOnTintColor == capsuleColor.toggleOnTintColor && this.entityBackgroundColor == capsuleColor.entityBackgroundColor && this.entityTintColor == capsuleColor.entityTintColor && this.rippleColor == capsuleColor.rippleColor) {
            return true;
        }
        return false;
    }

    public final int getEntityBackgroundColor() {
        return this.entityBackgroundColor;
    }

    public final int getEntityTintColor() {
        return this.entityTintColor;
    }

    public final int getGeneralBackgroundColor() {
        return this.generalBackgroundColor;
    }

    public final int getGeneralTintColor() {
        return this.generalTintColor;
    }

    public final int getRippleColor() {
        return this.rippleColor;
    }

    public final int getToggleOnBackgroundColor() {
        return this.toggleOnBackgroundColor;
    }

    public final int getToggleOnTintColor() {
        return this.toggleOnTintColor;
    }

    public int hashCode() {
        return Integer.hashCode(this.rippleColor) + C0212a.b(this.entityTintColor, C0212a.b(this.entityBackgroundColor, C0212a.b(this.toggleOnTintColor, C0212a.b(this.toggleOnBackgroundColor, C0212a.b(this.generalTintColor, Integer.hashCode(this.generalBackgroundColor) * 31, 31), 31), 31), 31), 31);
    }

    public String toString() {
        int i2 = this.generalBackgroundColor;
        int i7 = this.generalTintColor;
        int i8 = this.toggleOnBackgroundColor;
        int i10 = this.toggleOnTintColor;
        int i11 = this.entityBackgroundColor;
        int i12 = this.entityTintColor;
        int i13 = this.rippleColor;
        StringBuilder h5 = a.h(i2, i7, "CapsuleColor(generalBackgroundColor=", ", generalTintColor=", ", toggleOnBackgroundColor=");
        j.x(h5, i8, ", toggleOnTintColor=", i10, ", entityBackgroundColor=");
        j.x(h5, i11, ", entityTintColor=", i12, ", rippleColor=");
        return C0086a.l(h5, i13, ")");
    }
}
