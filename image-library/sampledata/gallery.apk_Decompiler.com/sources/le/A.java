package Le;

import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1202t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class A implements g {

    /* renamed from: a  reason: collision with root package name */
    public static final A f3521a = new Object();

    public final List a() {
        return C1202t.d;
    }

    public final /* bridge */ /* synthetic */ Member b() {
        return null;
    }

    public final Object call(Object[] objArr) {
        j.e(objArr, "args");
        throw new UnsupportedOperationException("call/callBy are not supported for this declaration.");
    }

    public final Type getReturnType() {
        Class cls = Void.TYPE;
        j.d(cls, "TYPE");
        return cls;
    }
}
