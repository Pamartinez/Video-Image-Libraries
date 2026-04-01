package androidx.appfunctions.internal;

import Ad.C0721b;
import L1.d;
import Vf.C0886x;
import Vf.Y;
import Vf.Z;
import Wf.e;
import Wf.g;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.f;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\t\u001a\u00020\u00048FX\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\f\u001a\u00020\u00048FX\u0002¢\u0006\f\n\u0004\b\n\u0010\u0006\u001a\u0004\b\u000b\u0010\b¨\u0006\r"}, d2 = {"Landroidx/appfunctions/internal/Dispatchers;", "", "<init>", "()V", "LVf/x;", "Main$delegate", "Lme/f;", "getMain", "()LVf/x;", "Main", "Worker$delegate", "getWorker", "Worker", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Dispatchers {
    public static final Dispatchers INSTANCE = new Dispatchers();
    private static final f Main$delegate = d.q(new C0721b(18));
    private static final f Worker$delegate = d.q(new C0721b(19));

    private Dispatchers() {
    }

    /* access modifiers changed from: private */
    public static final Wf.f Main_delegate$lambda$0() {
        Handler handler = new Handler(Looper.getMainLooper());
        int i2 = g.f3898a;
        return new e(false, handler);
    }

    /* access modifiers changed from: private */
    public static final Y Worker_delegate$lambda$0() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        j.d(newSingleThreadExecutor, "newSingleThreadExecutor(...)");
        return new Z(newSingleThreadExecutor);
    }

    public final C0886x getMain() {
        return (C0886x) Main$delegate.getValue();
    }

    public final C0886x getWorker() {
        return (C0886x) Worker$delegate.getValue();
    }
}
