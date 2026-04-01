package Hf;

import D0.e;
import D1.i;
import He.C0748d;
import He.t;
import Re.g;
import Re.h;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;

/* renamed from: Hf.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0760i {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ t[] f3446a;
    public static final i b;

    static {
        w wVar = v.f4727a;
        f3446a = new t[]{wVar.f(new p(wVar.c(C0760i.class, "descriptors"), "annotationsAttribute", "getAnnotationsAttribute(Lorg/jetbrains/kotlin/types/TypeAttributes;)Lorg/jetbrains/kotlin/types/AnnotationsTypeAttribute;"))};
        e eVar = I.e;
        C0748d b5 = wVar.b(C0759h.class);
        eVar.getClass();
        String n = b5.n();
        j.b(n);
        b = new i(eVar.J(n));
    }

    public static final h a(I i2) {
        h hVar;
        j.e(i2, "<this>");
        C0759h hVar2 = (C0759h) b.f(i2, f3446a[0]);
        if (hVar2 == null || (hVar = hVar2.f3445a) == null) {
            return g.f3692a;
        }
        return hVar;
    }
}
