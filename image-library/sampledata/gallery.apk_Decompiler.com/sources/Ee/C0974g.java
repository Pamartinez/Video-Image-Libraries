package ee;

import java.util.concurrent.ConcurrentHashMap;

/* renamed from: ee.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0974g {
    public static final C0974g b = new C0974g(new C0973f(1), C0973f.b);

    /* renamed from: a  reason: collision with root package name */
    public final ConcurrentHashMap f4297a = new ConcurrentHashMap();

    public C0974g(C0973f... fVarArr) {
        for (C0973f fVar : fVarArr) {
            this.f4297a.put(fVar.a(), fVar);
        }
    }
}
