package kg;

import jg.a;
import kotlin.jvm.internal.j;

/* renamed from: kg.l  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1131l extends W {

    /* renamed from: c  reason: collision with root package name */
    public static final C1131l f4704c = new W(C1132m.f4706a);

    public final void d(a aVar, int i2, Object obj) {
        C1130k kVar = (C1130k) obj;
        j.e(kVar, "builder");
        char A10 = aVar.A(this.b, i2);
        kVar.b(kVar.d() + 1);
        char[] cArr = kVar.f4702a;
        int i7 = kVar.b;
        kVar.b = i7 + 1;
        cArr[i7] = A10;
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Object, kg.k] */
    public final Object e(Object obj) {
        char[] cArr = (char[]) obj;
        j.e(cArr, "<this>");
        ? obj2 = new Object();
        obj2.f4702a = cArr;
        obj2.b = cArr.length;
        obj2.b(10);
        return obj2;
    }

    public final Object h() {
        return new char[0];
    }
}
