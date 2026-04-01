package w0;

import java.util.ArrayList;
import java.util.Arrays;

/* renamed from: w0.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C0301b extends C0303d {
    public final ArrayList d;

    public C0301b() {
        super(C0308i.ARRAY);
        this.d = new ArrayList();
    }

    public final void a(C0304e eVar) {
        this.d.add(eVar);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C0301b) {
            C0301b bVar = (C0301b) obj;
            if (!super.equals(obj) || !this.d.equals(bVar.d)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.d.hashCode() ^ super.hashCode();
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("[");
        if (this.f1983c) {
            sb2.append("_ ");
        }
        sb2.append(Arrays.toString(this.d.toArray()).substring(1));
        return sb2.toString();
    }

    public C0301b(int i2) {
        super(C0308i.ARRAY);
        this.d = new ArrayList(i2);
    }
}
