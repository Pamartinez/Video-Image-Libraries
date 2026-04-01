package Ke;

import Ae.a;
import java.lang.ref.SoftReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class x0 implements a {
    public static final y0 f = new Object();
    public final a d;
    public volatile SoftReference e;

    public x0(Object obj, a aVar) {
        if (aVar != null) {
            this.e = null;
            this.d = aVar;
            if (obj != null) {
                this.e = new SoftReference(obj);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Argument for @NotNull parameter 'initializer' of kotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal.<init> must not be null");
    }

    public final Object invoke() {
        Object obj;
        Object obj2 = f;
        SoftReference softReference = this.e;
        if (softReference == null || (obj = softReference.get()) == null) {
            Object invoke = this.d.invoke();
            if (invoke != null) {
                obj2 = invoke;
            }
            this.e = new SoftReference(obj2);
            return invoke;
        } else if (obj == obj2) {
            return null;
        } else {
            return obj;
        }
    }
}
