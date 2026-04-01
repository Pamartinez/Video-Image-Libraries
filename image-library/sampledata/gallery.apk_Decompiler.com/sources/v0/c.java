package V0;

import java.io.Serializable;
import java.lang.reflect.Type;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class c extends k implements Serializable, Type {
    public final Class g;

    /* renamed from: h  reason: collision with root package name */
    public final int f866h;

    public c(Class cls) {
        this.g = cls;
        this.f866h = cls.getName().hashCode();
    }

    public abstract StringBuilder Y(StringBuilder sb2);

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        return this.f866h;
    }
}
