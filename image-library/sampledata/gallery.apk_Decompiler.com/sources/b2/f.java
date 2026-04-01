package B2;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import x2.C0339f;
import x2.C0340g;
import x2.C0344k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends C0339f {
    public final RectF q;

    public f(C0344k kVar, RectF rectF) {
        super(kVar);
        this.q = rectF;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [x2.g, B2.g, android.graphics.drawable.Drawable] */
    public final Drawable newDrawable() {
        ? gVar = new C0340g((C0339f) this);
        gVar.z = this;
        gVar.invalidateSelf();
        return gVar;
    }

    public f(f fVar) {
        super((C0339f) fVar);
        this.q = fVar.q;
    }
}
