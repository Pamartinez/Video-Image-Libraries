package kg;

import gg.a;
import ig.f;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.jvm.internal.j;

/* renamed from: kg.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1122c extends C1133n {
    public final C1121b b;

    /* JADX WARNING: type inference failed for: r0v1, types: [kg.b, kg.I] */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1122c(a aVar) {
        super(aVar);
        j.e(aVar, "element");
        f descriptor = aVar.getDescriptor();
        j.e(descriptor, "elementDesc");
        this.b = new I(descriptor);
    }

    public final Object a() {
        return new ArrayList();
    }

    public final int b(Object obj) {
        ArrayList arrayList = (ArrayList) obj;
        j.e(arrayList, "<this>");
        return arrayList.size();
    }

    public final Object e(Object obj) {
        j.e((Object) null, "<this>");
        return new ArrayList((Collection) null);
    }

    public final Object f(Object obj) {
        ArrayList arrayList = (ArrayList) obj;
        j.e(arrayList, "<this>");
        return arrayList;
    }

    public final void g(int i2, Object obj, Object obj2) {
        ArrayList arrayList = (ArrayList) obj;
        j.e(arrayList, "<this>");
        arrayList.add(i2, obj2);
    }

    public final f getDescriptor() {
        return this.b;
    }
}
