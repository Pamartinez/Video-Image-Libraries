package Oe;

import Tf.v;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1195m;
import qf.C1236c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n {

    /* renamed from: c  reason: collision with root package name */
    public static final n f3624c = new n(C1195m.q0(h.f3618c, k.f3621c, i.f3619c, j.f3620c));

    /* renamed from: a  reason: collision with root package name */
    public final List f3625a;
    public final LinkedHashMap b;

    public n(List list) {
        this.f3625a = list;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object next : list) {
            C1236c cVar = ((l) next).f3622a;
            Object obj = linkedHashMap.get(cVar);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(cVar, obj);
            }
            ((List) obj).add(next);
        }
        this.b = linkedHashMap;
    }

    public final m a(String str, C1236c cVar) {
        Integer num;
        j.e(cVar, "packageFqName");
        List<l> list = (List) this.b.get(cVar);
        if (list != null) {
            for (l lVar : list) {
                if (v.t0(str, lVar.b)) {
                    String substring = str.substring(lVar.b.length());
                    j.d(substring, "substring(...)");
                    if (substring.length() != 0) {
                        int length = substring.length();
                        int i2 = 0;
                        int i7 = 0;
                        while (true) {
                            if (i2 < length) {
                                int charAt = substring.charAt(i2) - '0';
                                if (charAt < 0 || charAt >= 10) {
                                    break;
                                }
                                i7 = (i7 * 10) + charAt;
                                i2++;
                            } else {
                                num = Integer.valueOf(i7);
                                break;
                            }
                        }
                    }
                    num = null;
                    if (num != null) {
                        return new m(lVar, num.intValue());
                    }
                }
            }
        }
        return null;
    }
}
