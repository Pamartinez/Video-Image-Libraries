package se;

import kotlin.jvm.internal.f;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import qe.C1227c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class i extends C1271c implements f {
    private final int arity;

    public i(int i2, C1227c cVar) {
        super(cVar);
        this.arity = i2;
    }

    public int getArity() {
        return this.arity;
    }

    public String toString() {
        if (getCompletion() != null) {
            return super.toString();
        }
        String g = v.f4727a.g(this);
        j.d(g, "renderLambdaToString(...)");
        return g;
    }
}
