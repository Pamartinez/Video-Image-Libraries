package Wf;

import L2.a;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import me.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class g {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f3898a = 0;
    private static volatile Choreographer choreographer;

    static {
        Object obj;
        try {
            obj = new e(false, a(Looper.getMainLooper()));
        } catch (Throwable th) {
            obj = a.l(th);
        }
        if (obj instanceof j) {
            obj = null;
        }
        f fVar = (f) obj;
    }

    public static final Handler a(Looper looper) {
        Object invoke = Handler.class.getDeclaredMethod("createAsync", new Class[]{Looper.class}).invoke((Object) null, new Object[]{looper});
        kotlin.jvm.internal.j.c(invoke, "null cannot be cast to non-null type android.os.Handler");
        return (Handler) invoke;
    }
}
