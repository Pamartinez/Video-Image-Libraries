package jf;

import Ae.c;
import kotlin.jvm.internal.j;

/* renamed from: jf.a  reason: case insensitive filesystem */
public final class C1101a implements c {
    public static final C1101a e = new C1101a(0);
    public static final C1101a f = new C1101a(1);
    public final /* synthetic */ int d;

    public /* synthetic */ C1101a(int i2) {
        this.d = i2;
    }

    public final Object invoke(Object obj, Object obj2) {
        C1104d dVar = (C1104d) obj;
        s sVar = (s) obj2;
        switch (this.d) {
            case 0:
                j.e(dVar, "$this$loadConstantFromProperty");
                j.e(sVar, "it");
                return dVar.f4638c.get(sVar);
            default:
                j.e(dVar, "$this$loadConstantFromProperty");
                j.e(sVar, "it");
                return dVar.b.get(sVar);
        }
    }
}
