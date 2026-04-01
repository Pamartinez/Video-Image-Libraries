package I;

import android.os.Handler;
import androidx.media3.common.util.BackgroundThreadStateHandler;
import androidx.media3.common.util.HandlerWrapper;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Executor {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ b(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void execute(Runnable runnable) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((BackgroundThreadStateHandler) obj).runInBackground(runnable);
                return;
            case 1:
                ((Handler) obj).post(runnable);
                return;
            default:
                ((HandlerWrapper) obj).post(runnable);
                return;
        }
    }
}
