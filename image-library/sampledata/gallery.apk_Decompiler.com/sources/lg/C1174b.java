package lg;

import B0.a;
import Ed.b;
import G0.c;
import kotlin.jvm.internal.j;
import mg.r;
import mg.u;
import ng.e;

/* renamed from: lg.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1174b {
    public static final C1173a d = new C1174b(new i(false, false, true, "    ", false, "type", true), e.f4975a);

    /* renamed from: a  reason: collision with root package name */
    public final i f4894a;
    public final a b;

    /* renamed from: c  reason: collision with root package name */
    public final c f4895c = new c(16);

    public C1174b(i iVar, a aVar) {
        this.f4894a = iVar;
        this.b = aVar;
    }

    public final Object a(gg.a aVar, String str) {
        j.e(aVar, "deserializer");
        j.e(str, "string");
        b bVar = new b(str);
        Object g = new r(this, u.OBJ, bVar, aVar.getDescriptor(), (G0.e) null).g(aVar);
        if (bVar.g() == 10) {
            return g;
        }
        b.p(bVar, "Expected EOF after parsing, but had " + str.charAt(bVar.b - 1) + " instead", 0, (String) null, 6);
        throw null;
    }
}
