package ge;

import E2.r;
import java.net.ProxySelector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Y implements r {
    public final /* synthetic */ int d;

    public /* synthetic */ Y(int i2) {
        this.d = i2;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return new Object();
            default:
                return ProxySelector.getDefault();
        }
    }
}
