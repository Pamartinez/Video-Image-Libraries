package ee;

import E2.i;
import He.F;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* renamed from: ee.n  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0981n {

    /* renamed from: c  reason: collision with root package name */
    public static final i f4302c = new i(String.valueOf(','));
    public static final C0981n d = new C0981n(C0973f.b, false, new C0981n(new C0973f(1), true, new C0981n()));

    /* renamed from: a  reason: collision with root package name */
    public final Map f4303a;
    public final byte[] b;

    public C0981n(C0973f fVar, boolean z, C0981n nVar) {
        String a7 = fVar.a();
        F.i("Comma is currently not allowed in message encoding", !a7.contains(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
        int size = nVar.f4303a.size();
        LinkedHashMap linkedHashMap = new LinkedHashMap(!nVar.f4303a.containsKey(fVar.a()) ? size + 1 : size);
        for (C0980m mVar : nVar.f4303a.values()) {
            String a10 = mVar.f4301a.a();
            if (!a10.equals(a7)) {
                linkedHashMap.put(a10, new C0980m(mVar.f4301a, mVar.b));
            }
        }
        linkedHashMap.put(a7, new C0980m(fVar, z));
        Map unmodifiableMap = Collections.unmodifiableMap(linkedHashMap);
        this.f4303a = unmodifiableMap;
        HashSet hashSet = new HashSet(unmodifiableMap.size());
        for (Map.Entry entry : unmodifiableMap.entrySet()) {
            if (((C0980m) entry.getValue()).b) {
                hashSet.add((String) entry.getKey());
            }
        }
        Set unmodifiableSet = Collections.unmodifiableSet(hashSet);
        i iVar = f4302c;
        iVar.getClass();
        Iterator it = unmodifiableSet.iterator();
        StringBuilder sb2 = new StringBuilder();
        iVar.a(sb2, it);
        this.b = sb2.toString().getBytes(Charset.forName("US-ASCII"));
    }

    public C0981n() {
        this.f4303a = new LinkedHashMap(0);
        this.b = new byte[0];
    }
}
