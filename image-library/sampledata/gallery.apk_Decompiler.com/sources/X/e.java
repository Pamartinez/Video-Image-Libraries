package x;

import android.view.ViewConfiguration;
import androidx.core.util.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ ViewConfiguration e;

    public /* synthetic */ e(ViewConfiguration viewConfiguration, int i2) {
        this.d = i2;
        this.e = viewConfiguration;
    }

    public final Object get() {
        int scaledMaximumFlingVelocity;
        int i2 = this.d;
        ViewConfiguration viewConfiguration = this.e;
        switch (i2) {
            case 0:
                scaledMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
                break;
            default:
                scaledMaximumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
                break;
        }
        return Integer.valueOf(scaledMaximumFlingVelocity);
    }
}
