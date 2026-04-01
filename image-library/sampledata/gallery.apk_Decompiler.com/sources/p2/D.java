package P2;

import P0.b;
import com.samsung.android.sum.core.types.NumericEnum;
import java.util.Collection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class D {

    /* renamed from: a  reason: collision with root package name */
    public final String f577a;
    public final C0056f b;

    /* renamed from: c  reason: collision with root package name */
    public final int f578c;
    public int d;
    public boolean e;

    public D(String str, C0056f fVar, int i2) {
        if (i2 <= 0 || ((i2 - 1) & i2) != 0) {
            throw new IllegalArgumentException("invalid alignment");
        }
        this.f577a = str;
        this.b = fVar;
        this.f578c = i2;
        this.d = -1;
        this.e = false;
    }

    public abstract int a(p pVar);

    public final int b() {
        int i2 = this.d;
        if (i2 >= 0) {
            return i2;
        }
        throw new RuntimeException("fileOffset not set");
    }

    public abstract Collection c();

    public final void d() {
        g();
        e();
        this.e = true;
    }

    public abstract void e();

    public final void f() {
        if (!this.e) {
            throw new RuntimeException("not prepared");
        }
    }

    public final void g() {
        if (this.e) {
            throw new RuntimeException("already prepared");
        }
    }

    public abstract int h();

    public final void i(b bVar) {
        f();
        bVar.a(this.f578c);
        int i2 = bVar.b;
        int i7 = this.d;
        if (i7 < 0) {
            this.d = i2;
        } else if (i7 != i2) {
            throw new RuntimeException("alignment mismatch: for " + this + ", at " + i2 + ", but expected " + this.d);
        }
        if (bVar.d()) {
            String str = this.f577a;
            if (str != null) {
                bVar.b(0, "\n" + str + NumericEnum.SEP);
            } else if (i2 != 0) {
                bVar.b(0, "\n");
            }
        }
        j(bVar);
    }

    public abstract void j(b bVar);
}
