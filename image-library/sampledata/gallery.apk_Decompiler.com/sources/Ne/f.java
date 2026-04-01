package Ne;

import Ae.a;
import Hf.B;
import Qe.L;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f implements a {
    public final /* synthetic */ int d;
    public final /* synthetic */ i e;

    public /* synthetic */ f(i iVar, int i2) {
        this.d = i2;
        this.e = iVar;
    }

    public final Object invoke() {
        int i2 = this.d;
        int i7 = 0;
        i iVar = this.e;
        switch (i2) {
            case 0:
                return Arrays.asList(new L[]{iVar.k().n0(q.l), iVar.k().n0(q.n), iVar.k().n0(q.f3579o), iVar.k().n0(q.m)});
            default:
                EnumMap enumMap = new EnumMap(l.class);
                HashMap hashMap = new HashMap();
                HashMap hashMap2 = new HashMap();
                l[] values = l.values();
                int length = values.length;
                while (i7 < length) {
                    l lVar = values[i7];
                    String b = lVar.f().b();
                    if (b != null) {
                        B i8 = iVar.j(b).i();
                        if (i8 != null) {
                            String b5 = lVar.d().b();
                            if (b5 != null) {
                                B i10 = iVar.j(b5).i();
                                if (i10 != null) {
                                    enumMap.put(lVar, i10);
                                    hashMap.put(i8, i10);
                                    hashMap2.put(i10, i8);
                                    i7++;
                                } else {
                                    i.a(48);
                                    throw null;
                                }
                            } else {
                                i.a(47);
                                throw null;
                            }
                        } else {
                            i.a(48);
                            throw null;
                        }
                    } else {
                        i.a(47);
                        throw null;
                    }
                }
                return new h(enumMap, hashMap, hashMap2);
        }
    }
}
