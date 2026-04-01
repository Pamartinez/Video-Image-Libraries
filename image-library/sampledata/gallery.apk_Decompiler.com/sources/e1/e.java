package E1;

import android.os.Handler;
import android.os.Looper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class e extends Handler {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public e(Looper looper, int i2) {
        super(looper);
        switch (i2) {
            case 1:
                super(looper);
                Looper.getMainLooper();
                return;
            default:
                Looper.getMainLooper();
                return;
        }
    }
}
