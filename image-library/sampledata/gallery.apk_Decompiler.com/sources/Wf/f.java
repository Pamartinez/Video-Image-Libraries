package Wf;

import Vf.C0886x;
import Vf.I;
import cg.a;
import cg.o;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class f extends C0886x implements I {
    public C0886x limitedParallelism(int i2, String str) {
        a.a(i2);
        if (str != null) {
            return new o(this, str);
        }
        return this;
    }
}
