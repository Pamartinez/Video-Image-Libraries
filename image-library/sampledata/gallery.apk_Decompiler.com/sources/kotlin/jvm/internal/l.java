package kotlin.jvm.internal;

import He.C0747c;
import He.i;
import He.j;
import He.k;
import He.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class l extends q implements j, k {
    public C0747c computeReflected() {
        return v.f4727a.d(this);
    }

    public Object invoke(Object obj) {
        return ((m) this).get(obj);
    }

    public r getGetter() {
        return ((j) getReflected()).getGetter();
    }

    public i getSetter() {
        return ((j) getReflected()).getSetter();
    }
}
