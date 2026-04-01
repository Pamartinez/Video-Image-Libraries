package Ke;

import Ae.b;
import D0.e;
import Hf.C0774x;
import Qe.C0831v;
import Qe.O;
import Te.Q;
import Tf.m;
import We.C0892d;
import java.lang.reflect.Method;
import kotlin.jvm.internal.j;
import sf.C1283j;

/* renamed from: Ke.b  reason: case insensitive filesystem */
public final class C0779b implements b {
    public static final C0779b e = new C0779b(0);
    public static final C0779b f = new C0779b(1);
    public static final C0779b g = new C0779b(2);

    /* renamed from: h  reason: collision with root package name */
    public static final C0779b f3497h = new C0779b(3);

    /* renamed from: i  reason: collision with root package name */
    public static final C0779b f3498i = new C0779b(4);

    /* renamed from: j  reason: collision with root package name */
    public static final C0779b f3499j = new C0779b(5);
    public static final C0779b k = new C0779b(6);
    public static final C0779b l = new C0779b(7);
    public static final C0779b m = new C0779b(8);
    public final /* synthetic */ int d;

    public /* synthetic */ C0779b(int i2) {
        this.d = i2;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                Class cls = (Class) obj;
                e eVar = C0781c.f3500a;
                j.e(cls, "it");
                return new B(cls);
            case 1:
                Class cls2 = (Class) obj;
                e eVar2 = C0781c.f3500a;
                j.e(cls2, "it");
                return new U(cls2);
            case 2:
                Class<?> returnType = ((Method) obj).getReturnType();
                j.d(returnType, "getReturnType(...)");
                return C0892d.b(returnType);
            case 3:
                Class cls3 = (Class) obj;
                j.b(cls3);
                return C0892d.b(cls3);
            case 4:
                O o2 = (O) obj;
                m mVar = F.d;
                j.e(o2, "descriptor");
                return C1283j.e.w(o2) + " | " + C0.b(o2).h();
            case 5:
                C0831v vVar = (C0831v) obj;
                m mVar2 = F.d;
                j.e(vVar, "descriptor");
                return C1283j.e.w(vVar) + " | " + C0.c(vVar).d();
            case 6:
                C1283j jVar = B0.f3485a;
                C0774x type = ((Q) obj).getType();
                j.d(type, "getType(...)");
                return B0.d(type);
            case 7:
                C1283j jVar2 = B0.f3485a;
                C0774x type2 = ((Q) obj).getType();
                j.d(type2, "getType(...)");
                return B0.d(type2);
            default:
                Class cls4 = (Class) obj;
                j.b(cls4);
                return C0892d.b(cls4);
        }
    }
}
