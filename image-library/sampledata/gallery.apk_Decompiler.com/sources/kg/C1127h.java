package kg;

import jg.a;
import kotlin.jvm.internal.j;

/* renamed from: kg.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1127h extends W {

    /* renamed from: c  reason: collision with root package name */
    public static final C1127h f4697c = new W(C1128i.f4699a);

    public final void d(a aVar, int i2, Object obj) {
        C1126g gVar = (C1126g) obj;
        j.e(gVar, "builder");
        byte k = aVar.k(this.b, i2);
        gVar.b(gVar.d() + 1);
        byte[] bArr = gVar.f4695a;
        int i7 = gVar.b;
        gVar.b = i7 + 1;
        bArr[i7] = k;
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [kg.g, java.lang.Object] */
    public final Object e(Object obj) {
        byte[] bArr = (byte[]) obj;
        j.e(bArr, "<this>");
        ? obj2 = new Object();
        obj2.f4695a = bArr;
        obj2.b = bArr.length;
        obj2.b(10);
        return obj2;
    }

    public final Object h() {
        return new byte[0];
    }
}
