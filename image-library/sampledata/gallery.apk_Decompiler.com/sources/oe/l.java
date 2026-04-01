package Oe;

import c0.C0086a;
import kotlin.jvm.internal.j;
import qf.C1236c;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class l {

    /* renamed from: a  reason: collision with root package name */
    public final C1236c f3622a;
    public final String b;

    public l(String str, C1236c cVar) {
        j.e(cVar, "packageFqName");
        this.f3622a = cVar;
        this.b = str;
    }

    public final C1240g a(int i2) {
        return C1240g.e(this.b + i2);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.f3622a);
        sb2.append('.');
        return C0086a.m(sb2, this.b, 'N');
    }
}
