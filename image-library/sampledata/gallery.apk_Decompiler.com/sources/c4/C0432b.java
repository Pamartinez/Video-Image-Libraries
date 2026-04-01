package c4;

import android.os.Handler;
import java.util.function.Consumer;

/* renamed from: c4.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0432b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Runnable e;
    public final /* synthetic */ long f;

    public /* synthetic */ C0432b(Runnable runnable, long j2, int i2) {
        this.d = i2;
        this.e = runnable;
        this.f = j2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((Handler) obj).postDelayed(this.e, this.f);
                return;
            default:
                ((Handler) obj).postDelayed(this.e, this.f);
                return;
        }
    }
}
