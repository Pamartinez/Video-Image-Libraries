package t0;

import androidx.work.impl.utils.IdGenerator;
import java.util.concurrent.Callable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ IdGenerator f1919a;
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f1920c;

    public /* synthetic */ b(IdGenerator idGenerator, int i2, int i7) {
        this.f1919a = idGenerator;
        this.b = i2;
        this.f1920c = i7;
    }

    public final Object call() {
        return IdGenerator.nextJobSchedulerIdWithRange$lambda$0(this.f1919a, this.b, this.f1920c);
    }
}
