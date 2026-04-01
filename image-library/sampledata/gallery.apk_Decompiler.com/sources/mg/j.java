package mg;

import ig.f;
import ig.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j {

    /* renamed from: a  reason: collision with root package name */
    public Object[] f4929a;
    public int[] b;

    /* renamed from: c  reason: collision with root package name */
    public int f4930c;

    public final String a() {
        StringBuilder sb2 = new StringBuilder("$");
        int i2 = this.f4930c + 1;
        for (int i7 = 0; i7 < i2; i7++) {
            Object obj = this.f4929a[i7];
            if (obj instanceof f) {
                f fVar = (f) obj;
                if (!kotlin.jvm.internal.j.a(fVar.b(), l.e)) {
                    int i8 = this.b[i7];
                    if (i8 >= 0) {
                        sb2.append(".");
                        sb2.append(fVar.f(i8));
                    }
                } else if (this.b[i7] != -1) {
                    sb2.append("[");
                    sb2.append(this.b[i7]);
                    sb2.append("]");
                }
            } else if (obj != i.f4928a) {
                sb2.append("['");
                sb2.append(obj);
                sb2.append("']");
            }
        }
        String sb3 = sb2.toString();
        kotlin.jvm.internal.j.d(sb3, "toString(...)");
        return sb3;
    }

    public final String toString() {
        return a();
    }
}
