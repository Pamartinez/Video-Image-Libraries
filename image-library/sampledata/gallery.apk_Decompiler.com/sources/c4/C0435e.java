package c4;

import android.os.Handler;
import java.util.function.Consumer;

/* renamed from: c4.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0435e implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Runnable e;

    public /* synthetic */ C0435e(Runnable runnable, int i2) {
        this.d = i2;
        this.e = runnable;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Runnable runnable = this.e;
        Handler handler = (Handler) obj;
        switch (i2) {
            case 0:
                handler.post(runnable);
                return;
            default:
                handler.post(runnable);
                return;
        }
    }
}
