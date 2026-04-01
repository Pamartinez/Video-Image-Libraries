package kg;

import gg.a;
import ig.d;
import ig.f;
import jg.c;
import kotlin.jvm.internal.j;

/* renamed from: kg.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1128i implements a {

    /* renamed from: a  reason: collision with root package name */
    public static final C1128i f4699a = new Object();
    public static final X b = new X("kotlin.Byte", d.e);

    public final Object deserialize(c cVar) {
        j.e(cVar, "decoder");
        return Byte.valueOf(cVar.G());
    }

    public final f getDescriptor() {
        return b;
    }
}
