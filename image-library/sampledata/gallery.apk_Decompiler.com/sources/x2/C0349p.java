package x2;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;

/* renamed from: x2.p  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0349p extends C0351r {

    /* renamed from: h  reason: collision with root package name */
    public static final RectF f2135h = new RectF();
    public final float b;

    /* renamed from: c  reason: collision with root package name */
    public final float f2136c;
    public final float d;
    public final float e;
    public float f;
    public float g;

    public C0349p(float f5, float f8, float f10, float f11) {
        this.b = f5;
        this.f2136c = f8;
        this.d = f10;
        this.e = f11;
    }

    public final void a(Matrix matrix, Path path) {
        Matrix matrix2 = this.f2138a;
        matrix.invert(matrix2);
        path.transform(matrix2);
        float f5 = this.d;
        float f8 = this.e;
        RectF rectF = f2135h;
        rectF.set(this.b, this.f2136c, f5, f8);
        path.arcTo(rectF, this.f, this.g, false);
        path.transform(matrix);
    }
}
