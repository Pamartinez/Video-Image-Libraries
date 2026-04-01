package x2;

import android.graphics.RectF;
import java.util.Arrays;

/* renamed from: x2.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0341h implements C0336c {

    /* renamed from: a  reason: collision with root package name */
    public final float f2116a;

    public C0341h(float f) {
        this.f2116a = f;
    }

    public final float a(RectF rectF) {
        return Math.min(rectF.width(), rectF.height()) * this.f2116a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof C0341h) && this.f2116a == ((C0341h) obj).f2116a) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f2116a)});
    }
}
