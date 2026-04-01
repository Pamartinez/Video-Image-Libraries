package kg;

import Ae.a;
import ig.f;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;
import lg.C1174b;
import lg.i;
import lg.u;
import mg.h;
import ne.C1194l;
import ne.C1203u;
import ne.z;

/* renamed from: kg.v  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1140v extends k implements a {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1140v(int i2, Object obj, Object obj2) {
        super(0);
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final Object invoke() {
        String[] names;
        String str;
        switch (this.d) {
            case 0:
                C1141w wVar = (C1141w) this.e;
                C1139u uVar = wVar.b;
                if (uVar == null) {
                    Enum[] enumArr = wVar.f4721a;
                    uVar = new C1139u((String) this.f, enumArr.length);
                    for (Enum name : enumArr) {
                        uVar.k(name.name(), false);
                    }
                }
                return uVar;
            default:
                f fVar = (f) this.e;
                C1174b bVar = (C1174b) this.f;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                i iVar = bVar.f4894a;
                h.m(fVar, bVar);
                int e7 = fVar.e();
                for (int i2 = 0; i2 < e7; i2++) {
                    ArrayList arrayList = new ArrayList();
                    for (Object next : fVar.g(i2)) {
                        if (next instanceof u) {
                            arrayList.add(next);
                        }
                    }
                    u uVar2 = (u) C1194l.d1(arrayList);
                    if (!(uVar2 == null || (names = uVar2.names()) == null)) {
                        int length = names.length;
                        int i7 = 0;
                        while (i7 < length) {
                            String str2 = names[i7];
                            if (j.a(fVar.b(), ig.k.d)) {
                                str = "enum value";
                            } else {
                                str = "property";
                            }
                            if (!linkedHashMap.containsKey(str2)) {
                                linkedHashMap.put(str2, Integer.valueOf(i2));
                                i7++;
                            } else {
                                String str3 = "The suggested name '" + str2 + "' for " + str + ' ' + fVar.f(i2) + " is already one of the names for " + str + ' ' + fVar.f(((Number) z.Y(str2, linkedHashMap)).intValue()) + " in " + fVar;
                                j.e(str3, "message");
                                throw new IllegalArgumentException(str3);
                            }
                        }
                        continue;
                    }
                }
                if (linkedHashMap.isEmpty()) {
                    return C1203u.d;
                }
                return linkedHashMap;
        }
    }
}
