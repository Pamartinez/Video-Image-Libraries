package kg;

import Ae.a;
import ig.f;
import java.util.ArrayList;
import kotlin.jvm.internal.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class S extends k implements a {
    public final /* synthetic */ int d;
    public final /* synthetic */ T e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ S(T t, int i2) {
        super(0);
        this.d = i2;
        this.e = t;
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    public final Object invoke() {
        gg.a[] childSerializers;
        ArrayList arrayList;
        gg.a[] typeParametersSerializers;
        switch (this.d) {
            case 0:
                T t = this.e;
                return Integer.valueOf(Q.e(t, (f[]) t.f4683j.getValue()));
            case 1:
                A a7 = this.e.b;
                if (a7 == null || (childSerializers = a7.childSerializers()) == null) {
                    return Q.b;
                }
                return childSerializers;
            default:
                A a10 = this.e.b;
                if (a10 == null || (typeParametersSerializers = a10.typeParametersSerializers()) == null) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList(typeParametersSerializers.length);
                    for (gg.a descriptor : typeParametersSerializers) {
                        arrayList.add(descriptor.getDescriptor());
                    }
                }
                return Q.c(arrayList);
        }
    }
}
