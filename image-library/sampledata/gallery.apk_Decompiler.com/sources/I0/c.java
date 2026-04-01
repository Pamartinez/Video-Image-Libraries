package I0;

import D0.e;
import i.C0212a;
import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class c implements Closeable {

    /* renamed from: h  reason: collision with root package name */
    public static final String[] f341h = new String[128];
    public int d;
    public int[] e;
    public String[] f;
    public int[] g;

    static {
        for (int i2 = 0; i2 <= 31; i2++) {
            f341h[i2] = String.format("\\u%04x", new Object[]{Integer.valueOf(i2)});
        }
        String[] strArr = f341h;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
    }

    public abstract void a();

    public abstract void c();

    public abstract void f();

    public abstract void g();

    public final String getPath() {
        int i2 = this.d;
        int[] iArr = this.e;
        String[] strArr = this.f;
        int[] iArr2 = this.g;
        StringBuilder sb2 = new StringBuilder("$");
        for (int i7 = 0; i7 < i2; i7++) {
            int i8 = iArr[i7];
            if (i8 == 1 || i8 == 2) {
                sb2.append('[');
                sb2.append(iArr2[i7]);
                sb2.append(']');
            } else if (i8 == 3 || i8 == 4 || i8 == 5) {
                sb2.append('.');
                String str = strArr[i7];
                if (str != null) {
                    sb2.append(str);
                }
            }
        }
        return sb2.toString();
    }

    public abstract boolean h();

    public abstract boolean i();

    public abstract double j();

    public abstract int l();

    public abstract String m();

    public abstract b n();

    public final void p(int i2) {
        int i7 = this.d;
        int[] iArr = this.e;
        if (i7 == iArr.length) {
            if (i7 != 256) {
                this.e = Arrays.copyOf(iArr, iArr.length * 2);
                String[] strArr = this.f;
                this.f = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
                int[] iArr2 = this.g;
                this.g = Arrays.copyOf(iArr2, iArr2.length * 2);
            } else {
                throw new RuntimeException("Nesting too deep at " + getPath());
            }
        }
        int[] iArr3 = this.e;
        int i8 = this.d;
        this.d = i8 + 1;
        iArr3[i8] = i2;
    }

    public abstract int q(e eVar);

    public abstract void r();

    public abstract void s();

    public final void t(String str) {
        StringBuilder t = C0212a.t(str, " at path ");
        t.append(getPath());
        throw new IOException(t.toString());
    }
}
