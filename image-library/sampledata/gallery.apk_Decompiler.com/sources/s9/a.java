package S9;

import com.samsung.android.gallery.module.remote.v2.RemoteDisplayState;
import java.util.concurrent.ThreadFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ThreadFactory {
    public final Thread newThread(Runnable runnable) {
        return RemoteDisplayState.lambda$new$0(runnable);
    }
}
