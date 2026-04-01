package x2;

import android.graphics.Matrix;
import android.graphics.Path;

/* renamed from: x2.q  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0350q extends C0351r {
    public float b;

    /* renamed from: c  reason: collision with root package name */
    public float f2137c;

    public final void a(Matrix matrix, Path path) {
        Matrix matrix2 = this.f2138a;
        matrix.invert(matrix2);
        path.transform(matrix2);
        path.lineTo(this.b, this.f2137c);
        path.transform(matrix);
    }
}
