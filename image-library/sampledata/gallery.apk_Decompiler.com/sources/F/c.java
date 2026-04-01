package F;

import androidx.media3.common.util.Util;
import java.util.concurrent.ThreadFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f178a;
    public final /* synthetic */ String b;

    public /* synthetic */ c(String str, int i2) {
        this.f178a = i2;
        this.b = str;
    }

    public final Thread newThread(Runnable runnable) {
        int i2 = this.f178a;
        String str = this.b;
        switch (i2) {
            case 0:
                return Util.lambda$newSingleThreadScheduledExecutor$4(str, runnable);
            default:
                return Util.lambda$newSingleThreadExecutor$3(str, runnable);
        }
    }
}
