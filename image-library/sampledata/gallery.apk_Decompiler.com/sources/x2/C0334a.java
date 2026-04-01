package x2;

import android.graphics.RectF;
import java.util.Arrays;

/* renamed from: x2.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0334a implements C0336c {

    /* renamed from: a  reason: collision with root package name */
    public final float f2101a;

    public C0334a(float f) {
        this.f2101a = f;
    }

    public final float a(RectF rectF) {
        return this.f2101a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof C0334a) && this.f2101a == ((C0334a) obj).f2101a) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f2101a)});
    }
}
