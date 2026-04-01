package kg;

import gg.a;
import ig.d;
import ig.f;
import jg.c;
import kotlin.jvm.internal.j;

/* renamed from: kg.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1125f implements a {

    /* renamed from: a  reason: collision with root package name */
    public static final C1125f f4694a = new Object();
    public static final X b = new X("kotlin.Boolean", d.d);

    public final Object deserialize(c cVar) {
        j.e(cVar, "decoder");
        return Boolean.valueOf(cVar.v());
    }

    public final f getDescriptor() {
        return b;
    }
}
