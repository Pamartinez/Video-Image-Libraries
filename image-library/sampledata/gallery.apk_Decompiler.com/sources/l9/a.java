package L9;

import com.samsung.android.gallery.module.pipconverter.PipConverter;
import java.lang.Thread;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ PipConverter f2838a;

    public /* synthetic */ a(PipConverter pipConverter) {
        this.f2838a = pipConverter;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        this.f2838a.lambda$start$1(thread, th);
    }
}
