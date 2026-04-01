package com.samsung.android.vexfwk.sdk.common;

import Ad.f;
import Vf.A;
import Vf.C;
import Vf.C0867e0;
import Vf.C0888z;
import Vf.D;
import Vf.H;
import Vf.P;
import Vf.n0;
import Zf.a;
import android.util.Log;
import com.google.common.util.concurrent.q;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;
import qe.C1227c;
import qe.C1233i;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000 \u0018*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002:\u0002\u0018\u0019B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00028\u0000H¦@¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\n\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0014X\u0004¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBaseFuture;", "T", "Lcom/google/common/util/concurrent/q;", "LVf/A;", "coroutineScope", "<init>", "(LVf/A;)V", "execute", "(Lqe/c;)Ljava/lang/Object;", "Lme/x;", "start", "()V", "", "mayInterruptIfRunning", "cancel", "(Z)Z", "LVf/e0;", "job", "LVf/e0;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBaseFuture$OnCompletedCallback;", "onCompletedCallback", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBaseFuture$OnCompletedCallback;", "getOnCompletedCallback", "()Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBaseFuture$OnCompletedCallback;", "Companion", "OnCompletedCallback", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VexFwkHelperBaseFuture<T> extends q {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG = "VexFwkHelperBaseFuture";
    /* access modifiers changed from: private */
    public final C0867e0 job;
    private final OnCompletedCallback onCompletedCallback;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBaseFuture$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/String;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bä\u0001\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBaseFuture$OnCompletedCallback;", "", "Lme/x;", "onCompleted", "()V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnCompletedCallback {
        void onCompleted();
    }

    public VexFwkHelperBaseFuture(A a7) {
        j.e(a7, "coroutineScope");
        H b = D.b(a7, (C0888z) null, C.LAZY, new a(this, (C1227c) null, 4), 1);
        this.job = b;
        b.D(true, new P(1, new f(13, (Object) this)));
    }

    /* access modifiers changed from: private */
    public static final x _init_$lambda$0(VexFwkHelperBaseFuture vexFwkHelperBaseFuture, Throwable th) {
        OnCompletedCallback onCompletedCallback2 = vexFwkHelperBaseFuture.getOnCompletedCallback();
        if (onCompletedCallback2 != null) {
            onCompletedCallback2.onCompleted();
        }
        boolean isDone = vexFwkHelperBaseFuture.isDone();
        x xVar = x.f4917a;
        if (isDone) {
            return xVar;
        }
        if (th != null) {
            Log.e(TAG, "Error occurred during executing future", th);
            vexFwkHelperBaseFuture.setException(th);
            return xVar;
        }
        Log.e(TAG, "Future is completed without any result");
        vexFwkHelperBaseFuture.setException(new IllegalStateException("Future is completed without any result"));
        return xVar;
    }

    public boolean cancel(boolean z) {
        Object unused = D.r(C1233i.d, new f(this, (C1227c) null, 0));
        return super.cancel(z);
    }

    public abstract Object execute(C1227c cVar);

    public OnCompletedCallback getOnCompletedCallback() {
        return this.onCompletedCallback;
    }

    public final void start() {
        ((n0) this.job).Q();
    }
}
