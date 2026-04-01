package androidx.core.graphics;

import N2.j;
import android.graphics.Rect;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Insets {
    public static final Insets NONE = new Insets(0, 0, 0, 0);
    public final int bottom;
    public final int left;
    public final int right;
    public final int top;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api29Impl {
        public static android.graphics.Insets of(int i2, int i7, int i8, int i10) {
            return android.graphics.Insets.of(i2, i7, i8, i10);
        }
    }

    private Insets(int i2, int i7, int i8, int i10) {
        this.left = i2;
        this.top = i7;
        this.right = i8;
        this.bottom = i10;
    }

    public static Insets max(Insets insets, Insets insets2) {
        return of(Math.max(insets.left, insets2.left), Math.max(insets.top, insets2.top), Math.max(insets.right, insets2.right), Math.max(insets.bottom, insets2.bottom));
    }

    public static Insets min(Insets insets, Insets insets2) {
        return of(Math.min(insets.left, insets2.left), Math.min(insets.top, insets2.top), Math.min(insets.right, insets2.right), Math.min(insets.bottom, insets2.bottom));
    }

    public static Insets of(int i2, int i7, int i8, int i10) {
        if (i2 == 0 && i7 == 0 && i8 == 0 && i10 == 0) {
            return NONE;
        }
        return new Insets(i2, i7, i8, i10);
    }

    public static Insets toCompatInsets(android.graphics.Insets insets) {
        return of(insets.left, insets.top, insets.right, insets.bottom);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Insets.class != obj.getClass()) {
            return false;
        }
        Insets insets = (Insets) obj;
        if (this.bottom == insets.bottom && this.left == insets.left && this.right == insets.right && this.top == insets.top) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((this.left * 31) + this.top) * 31) + this.right) * 31) + this.bottom;
    }

    public android.graphics.Insets toPlatformInsets() {
        return Api29Impl.of(this.left, this.top, this.right, this.bottom);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Insets{left=");
        sb2.append(this.left);
        sb2.append(", top=");
        sb2.append(this.top);
        sb2.append(", right=");
        sb2.append(this.right);
        sb2.append(", bottom=");
        return j.e(sb2, this.bottom, '}');
    }

    public static Insets of(Rect rect) {
        return of(rect.left, rect.top, rect.right, rect.bottom);
    }
}
