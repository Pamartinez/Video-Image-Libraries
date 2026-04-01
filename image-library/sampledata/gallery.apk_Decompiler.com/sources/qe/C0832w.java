package Qe;

import Kf.e;
import kotlin.jvm.internal.j;
import qf.C1240g;

/* renamed from: Qe.w  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0832w extends W {

    /* renamed from: a  reason: collision with root package name */
    public final C1240g f3683a;
    public final e b;

    public C0832w(C1240g gVar, e eVar) {
        j.e(eVar, "underlyingType");
        this.f3683a = gVar;
        this.b = eVar;
    }

    public final boolean a(C1240g gVar) {
        return this.f3683a.equals(gVar);
    }

    public final String toString() {
        return "InlineClassRepresentation(underlyingPropertyName=" + this.f3683a + ", underlyingType=" + this.b + ')';
    }
}
