package com.samsung.android.vexfwk.coroutines;

import Vf.Y;
import Vf.g0;
import com.samsung.android.sum.core.Def;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\f¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/coroutines/VexFwkCachedScope;", "Lcom/samsung/android/vexfwk/coroutines/VexFwkBaseScope;", "Ljava/util/concurrent/ExecutorService;", "executorService", "LVf/Y;", "dispatcher", "<init>", "(Ljava/util/concurrent/ExecutorService;LVf/Y;)V", "Lme/x;", "close", "()V", "Ljava/util/concurrent/ExecutorService;", "LVf/Y;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkCachedScope extends VexFwkBaseScope {
    private final Y dispatcher;
    private final ExecutorService executorService;

    public VexFwkCachedScope() {
        this((ExecutorService) null, (Y) null, 3, (e) null);
    }

    public void close() {
        super.close();
        this.dispatcher.close();
        this.executorService.shutdown();
        this.executorService.awaitTermination(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public VexFwkCachedScope(java.util.concurrent.ExecutorService r1, Vf.Y r2, int r3, kotlin.jvm.internal.e r4) {
        /*
            r0 = this;
            r4 = r3 & 1
            if (r4 == 0) goto L_0x0008
            java.util.concurrent.ExecutorService r1 = java.util.concurrent.Executors.newCachedThreadPool()
        L_0x0008:
            r3 = r3 & 2
            if (r3 == 0) goto L_0x0011
            Vf.Z r2 = new Vf.Z
            r2.<init>(r1)
        L_0x0011:
            r0.<init>(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.coroutines.VexFwkCachedScope.<init>(java.util.concurrent.ExecutorService, Vf.Y, int, kotlin.jvm.internal.e):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VexFwkCachedScope(ExecutorService executorService2, Y y) {
        super(new g0(), y);
        j.e(executorService2, "executorService");
        j.e(y, "dispatcher");
        this.executorService = executorService2;
        this.dispatcher = y;
    }
}
