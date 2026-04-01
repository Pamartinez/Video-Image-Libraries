package Vf;

import Ae.b;
import java.util.concurrent.ScheduledFuture;

/* renamed from: Vf.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0871h implements C0872i {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3860a;
    public final Object b;

    public /* synthetic */ C0871h(int i2, Object obj) {
        this.f3860a = i2;
        this.b = obj;
    }

    public final void a(Throwable th) {
        switch (this.f3860a) {
            case 0:
                ((ScheduledFuture) this.b).cancel(false);
                return;
            case 1:
                ((b) this.b).invoke(th);
                return;
            default:
                ((O) this.b).a();
                return;
        }
    }

    public final String toString() {
        switch (this.f3860a) {
            case 0:
                return "CancelFutureOnCancel[" + ((ScheduledFuture) this.b) + ']';
            case 1:
                return "CancelHandler.UserSupplied[" + ((b) this.b).getClass().getSimpleName() + '@' + D.j(this) + ']';
            default:
                return "DisposeOnCancel[" + ((O) this.b) + ']';
        }
    }
}
