package kg;

import gg.a;
import ig.e;
import ig.f;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.j;
import ne.z;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C extends C1120a {

    /* renamed from: a  reason: collision with root package name */
    public final a f4664a;
    public final a b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f4665c;
    public final B d;

    public C(a aVar, a aVar2, byte b5) {
        this.f4664a = aVar;
        this.b = aVar2;
    }

    public final Object a() {
        switch (this.f4665c) {
            case 0:
                return new HashMap();
            default:
                return new LinkedHashMap();
        }
    }

    public final int b(Object obj) {
        int size;
        switch (this.f4665c) {
            case 0:
                HashMap hashMap = (HashMap) obj;
                j.e(hashMap, "<this>");
                size = hashMap.size();
                break;
            default:
                LinkedHashMap linkedHashMap = (LinkedHashMap) obj;
                j.e(linkedHashMap, "<this>");
                size = linkedHashMap.size();
                break;
        }
        return size * 2;
    }

    public final void d(jg.a aVar, int i2, Object obj) {
        Object obj2;
        Map map = (Map) obj;
        j.e(map, "builder");
        Object n = aVar.n(getDescriptor(), i2, this.f4664a, (Object) null);
        int d2 = aVar.d(getDescriptor());
        if (d2 == i2 + 1) {
            boolean containsKey = map.containsKey(n);
            a aVar2 = this.b;
            if (!containsKey || (aVar2.getDescriptor().b() instanceof e)) {
                obj2 = aVar.n(getDescriptor(), d2, aVar2, (Object) null);
            } else {
                obj2 = aVar.n(getDescriptor(), d2, aVar2, z.Y(n, map));
            }
            map.put(n, obj2);
            return;
        }
        throw new IllegalArgumentException(N2.j.b(i2, d2, "Value must follow key in a map, index for key: ", ", returned index for value: ").toString());
    }

    public final Object e(Object obj) {
        switch (this.f4665c) {
            case 0:
                j.e((Object) null, "<this>");
                return new HashMap((Map) null);
            default:
                j.e((Object) null, "<this>");
                return new LinkedHashMap((Map) null);
        }
    }

    public final Object f(Object obj) {
        switch (this.f4665c) {
            case 0:
                HashMap hashMap = (HashMap) obj;
                j.e(hashMap, "<this>");
                return hashMap;
            default:
                LinkedHashMap linkedHashMap = (LinkedHashMap) obj;
                j.e(linkedHashMap, "<this>");
                return linkedHashMap;
        }
    }

    public final f getDescriptor() {
        switch (this.f4665c) {
            case 0:
                return this.d;
            default:
                return this.d;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public C(a aVar, a aVar2, int i2) {
        this(aVar, aVar2, (byte) 0);
        this.f4665c = i2;
        switch (i2) {
            case 1:
                j.e(aVar, "kSerializer");
                j.e(aVar2, "vSerializer");
                this(aVar, aVar2, (byte) 0);
                f descriptor = aVar.getDescriptor();
                f descriptor2 = aVar2.getDescriptor();
                j.e(descriptor, "keyDesc");
                j.e(descriptor2, "valueDesc");
                this.d = new B("kotlin.collections.LinkedHashMap", descriptor, descriptor2);
                return;
            default:
                j.e(aVar, "kSerializer");
                j.e(aVar2, "vSerializer");
                f descriptor3 = aVar.getDescriptor();
                f descriptor4 = aVar2.getDescriptor();
                j.e(descriptor3, "keyDesc");
                j.e(descriptor4, "valueDesc");
                this.d = new B("kotlin.collections.HashMap", descriptor3, descriptor4);
                return;
        }
    }
}
