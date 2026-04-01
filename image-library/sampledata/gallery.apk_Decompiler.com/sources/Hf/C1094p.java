package hf;

import java.util.LinkedHashMap;
import java.util.Map;
import ne.z;

/* renamed from: hf.p  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1094p {

    /* renamed from: a  reason: collision with root package name */
    public final LinkedHashMap f4596a;

    public C1094p(LinkedHashMap linkedHashMap) {
        this.f4596a = linkedHashMap;
    }

    public final C1094p a() {
        LinkedHashMap linkedHashMap = this.f4596a;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(z.Z(linkedHashMap.size()));
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            Object key = entry.getKey();
            C1082d dVar = (C1082d) entry.getValue();
            linkedHashMap2.put(key, new C1082d(dVar.f4586a, dVar.b, dVar.f4587c, true));
        }
        return new C1094p(linkedHashMap2);
    }
}
