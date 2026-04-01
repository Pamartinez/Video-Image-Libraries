package ge;

import B2.A;
import ee.G;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class t1 implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ v1 e;
    public final /* synthetic */ W0 f;

    public /* synthetic */ t1(W0 w02, v1 v1Var, int i2) {
        this.d = i2;
        this.f = w02;
        this.e = v1Var;
    }

    public final void run() {
        int i2 = this.d;
        W0 w02 = this.f;
        switch (i2) {
            case 0:
                ((C1057t0) w02.f).e.execute(new A(14, (Object) this));
                return;
            default:
                G g = C1057t0.f4547H;
                ((C1057t0) w02.f).l(this.e);
                return;
        }
    }
}
