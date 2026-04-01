package P1;

import E1.e;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g implements Executor {
    public final /* synthetic */ int d = 0;
    public final Handler e;

    public /* synthetic */ g(e eVar) {
        this.e = eVar;
    }

    public final void execute(Runnable runnable) {
        switch (this.d) {
            case 0:
                ((e) this.e).post(runnable);
                return;
            default:
                this.e.post(runnable);
                return;
        }
    }

    public g() {
        Handler handler = new Handler(Looper.getMainLooper());
        Looper.getMainLooper();
        this.e = handler;
    }
}
