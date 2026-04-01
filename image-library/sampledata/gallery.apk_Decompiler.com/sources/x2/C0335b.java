package x2;

import android.graphics.RectF;
import java.util.Arrays;

/* renamed from: x2.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0335b implements C0336c {

    /* renamed from: a  reason: collision with root package name */
    public final C0336c f2102a;
    public final float b;

    public C0335b(float f, C0336c cVar) {
        while (cVar instanceof C0335b) {
            cVar = ((C0335b) cVar).f2102a;
            f += ((C0335b) cVar).b;
        }
        this.f2102a = cVar;
        this.b = f;
    }

    public final float a(RectF rectF) {
        return Math.max(0.0f, this.f2102a.a(rectF) + this.b);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0335b)) {
            return false;
        }
        C0335b bVar = (C0335b) obj;
        if (!this.f2102a.equals(bVar.f2102a) || this.b != bVar.b) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f2102a, Float.valueOf(this.b)});
    }
}
