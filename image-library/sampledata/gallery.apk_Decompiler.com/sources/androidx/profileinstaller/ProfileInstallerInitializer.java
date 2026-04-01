package androidx.profileinstaller;

import C3.l;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import androidx.startup.Initializer;
import e0.C0180a;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ProfileInstallerInitializer implements Initializer<Result> {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Handler28Impl {
        public static Handler createAsync(Looper looper) {
            return Handler.createAsync(looper);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Result {
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$create$0(Context context, long j2) {
        installAfterDelay(context);
    }

    /* access modifiers changed from: private */
    public static void writeInBackground(Context context) {
        new ThreadPoolExecutor(0, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue()).execute(new l(context, 4));
    }

    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.EMPTY_LIST;
    }

    public void installAfterDelay(Context context) {
        Handler28Impl.createAsync(Looper.getMainLooper()).postDelayed(new l(context, 3), (long) (new Random().nextInt(Math.max(1000, 1)) + 5000));
    }

    public Result create(Context context) {
        Choreographer.getInstance().postFrameCallback(new C0180a(this, context.getApplicationContext()));
        return new Result();
    }
}
