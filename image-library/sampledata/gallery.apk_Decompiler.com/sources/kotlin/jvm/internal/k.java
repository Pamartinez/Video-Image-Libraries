package kotlin.jvm.internal;

import java.io.Serializable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class k implements f, Serializable {
    private final int arity;

    public k(int i2) {
        this.arity = i2;
    }

    public int getArity() {
        return this.arity;
    }

    public String toString() {
        String h5 = v.f4727a.h(this);
        j.d(h5, "renderLambdaToString(...)");
        return h5;
    }
}
