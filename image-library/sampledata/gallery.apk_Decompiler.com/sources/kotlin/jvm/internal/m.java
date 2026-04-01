package kotlin.jvm.internal;

import He.C0748d;
import Ke.C0800s;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m extends l {
    public m(C0748d dVar, String str, String str2) {
        super(b.NO_RECEIVER, ((c) dVar).b(), str, str2, Objects.nonNull(dVar) ^ true ? 1 : 0);
    }

    public final Object get(Object obj) {
        return ((C0800s) getGetter()).call(obj);
    }

    public m(Class cls) {
        super(b.NO_RECEIVER, cls, "nativeHandle", "getNativeHandle()J", 0);
    }
}
