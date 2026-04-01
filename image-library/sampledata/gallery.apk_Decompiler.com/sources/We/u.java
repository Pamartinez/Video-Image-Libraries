package We;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class u extends w {

    /* renamed from: a  reason: collision with root package name */
    public final Field f3894a;

    public u(Field field) {
        j.e(field, "member");
        this.f3894a = field;
    }

    public final Member b() {
        return this.f3894a;
    }
}
