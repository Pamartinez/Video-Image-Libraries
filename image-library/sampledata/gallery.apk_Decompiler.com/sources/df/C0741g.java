package Df;

import Qe.Q;
import kotlin.jvm.internal.j;
import lf.C1157j;
import nf.C1204a;
import nf.C1209f;

/* renamed from: Df.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0741g {

    /* renamed from: a  reason: collision with root package name */
    public final C1209f f3343a;
    public final C1157j b;

    /* renamed from: c  reason: collision with root package name */
    public final C1204a f3344c;
    public final Q d;

    public C0741g(C1209f fVar, C1157j jVar, C1204a aVar, Q q) {
        j.e(fVar, "nameResolver");
        j.e(jVar, "classProto");
        j.e(q, "sourceElement");
        this.f3343a = fVar;
        this.b = jVar;
        this.f3344c = aVar;
        this.d = q;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0741g)) {
            return false;
        }
        C0741g gVar = (C0741g) obj;
        if (j.a(this.f3343a, gVar.f3343a) && j.a(this.b, gVar.b) && j.a(this.f3344c, gVar.f3344c) && j.a(this.d, gVar.d)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.b.hashCode();
        int hashCode2 = this.f3344c.hashCode();
        return this.d.hashCode() + ((hashCode2 + ((hashCode + (this.f3343a.hashCode() * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "ClassData(nameResolver=" + this.f3343a + ", classProto=" + this.b + ", metadataVersion=" + this.f3344c + ", sourceElement=" + this.d + ')';
    }
}
