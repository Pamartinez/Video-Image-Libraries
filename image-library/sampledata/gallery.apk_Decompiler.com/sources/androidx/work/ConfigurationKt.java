package androidx.work;

import Vf.C0886x;
import Vf.L;
import Vf.Y;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import qe.C1228d;
import qe.C1229e;
import qe.C1232h;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u000f\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0017\u0010\t\u001a\u0004\u0018\u00010\u0002*\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"", "isTaskExecutor", "Ljava/util/concurrent/Executor;", "createDefaultExecutor", "(Z)Ljava/util/concurrent/Executor;", "Landroidx/work/Tracer;", "createDefaultTracer", "()Landroidx/work/Tracer;", "Lqe/h;", "asExecutor", "(Lqe/h;)Ljava/util/concurrent/Executor;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ConfigurationKt {
    /* access modifiers changed from: private */
    public static final Executor asExecutor(C1232h hVar) {
        C1229e eVar;
        C0886x xVar;
        Executor l;
        Y y = null;
        if (hVar != null) {
            eVar = (C1229e) hVar.get(C1228d.d);
        } else {
            eVar = null;
        }
        if (eVar instanceof C0886x) {
            xVar = (C0886x) eVar;
        } else {
            xVar = null;
        }
        if (xVar == null) {
            return null;
        }
        if (xVar instanceof Y) {
            y = (Y) xVar;
        }
        if (y == null || (l = y.l()) == null) {
            return new L(xVar);
        }
        return l;
    }

    /* access modifiers changed from: private */
    public static final Executor createDefaultExecutor(boolean z) {
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(Math.max(2, Math.min(Runtime.getRuntime().availableProcessors() - 1, 4)), new ConfigurationKt$createDefaultExecutor$factory$1(z));
        j.d(newFixedThreadPool, "newFixedThreadPool(\n    …)),\n        factory\n    )");
        return newFixedThreadPool;
    }

    /* access modifiers changed from: private */
    public static final Tracer createDefaultTracer() {
        return new ConfigurationKt$createDefaultTracer$tracer$1();
    }
}
