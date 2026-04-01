package kg;

import gg.a;
import ig.d;
import ig.f;
import jg.c;
import kotlin.jvm.internal.j;

/* renamed from: kg.z  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1144z implements a {

    /* renamed from: a  reason: collision with root package name */
    public static final C1144z f4725a = new Object();
    public static final X b = new X("kotlin.Float", d.f4603h);

    public final Object deserialize(c cVar) {
        j.e(cVar, "decoder");
        return Float.valueOf(cVar.p());
    }

    public final f getDescriptor() {
        return b;
    }
}
