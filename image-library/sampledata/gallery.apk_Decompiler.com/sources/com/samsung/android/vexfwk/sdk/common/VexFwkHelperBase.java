package com.samsung.android.vexfwk.sdk.common;

import Ad.C0720a;
import Ae.b;
import L1.d;
import Sf.q;
import Vf.D;
import Vf.Y;
import android.content.Context;
import android.util.Log;
import bc.C0584a;
import com.google.common.util.concurrent.ListenableFuture;
import com.samsung.android.vexfwk.VexFwkJniLoader;
import com.samsung.android.vexfwk.coroutines.VexFwkCachedScope;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperBase;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkProvider;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import fg.a;
import fg.c;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.f;
import me.k;
import qe.C1227c;
import qe.C1233i;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u0000 >*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00002\u00060\u0002j\u0002`\u0003:\u0001>B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\u0005J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH@¢\u0006\u0004\b\u000b\u0010\fJ \u0010\u0010\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\rH@¢\u0006\u0004\b\u0010\u0010\u0011J=\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182&\u0010\u0017\u001a\"\b\u0001\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0012j\u0002`\u0016H\u0004¢\u0006\u0004\b\u0019\u0010\u001aJO\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0018\"\u0004\b\u0001\u0010\u001b22\u0010\u001e\u001a.\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0012j\b\u0012\u0004\u0012\u00028\u0001`\u001dH\u0004¢\u0006\u0004\b\u001f\u0010\u001aJ9\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00010\u0018\"\u0004\b\u0001\u0010\u001b2\u001c\u0010!\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00150 H\u0004¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\u0006H\u0016¢\u0006\u0004\b$\u0010\u0005J\u000f\u0010%\u001a\u00020\u0006H\u0004¢\u0006\u0004\b%\u0010\u0005R\u001b\u0010*\u001a\u00028\u00008BX\u0002¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u0014\u0010,\u001a\u00020+8\u0002X\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u001e\u0010/\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010.8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b/\u00100R\u0014\u00102\u001a\u0002018\u0002X\u0004¢\u0006\u0006\n\u0004\b2\u00103R\u0018\u00105\u001a\u0004\u0018\u0001048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b5\u00106R\u001a\u00109\u001a\b\u0012\u0004\u0012\u00028\u00000.8BX\u0004¢\u0006\u0006\u001a\u0004\b7\u00108R\u0014\u0010=\u001a\u00020:8$X¤\u0004¢\u0006\u0006\u001a\u0004\b;\u0010<¨\u0006?"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBase;", "IMPL", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "Lme/x;", "closeInternal", "Lcom/samsung/android/vexfwk/session/VexFwkUsecase;", "usecase", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSession;", "getSession", "(Lcom/samsung/android/vexfwk/session/VexFwkUsecase;Lqe/c;)Ljava/lang/Object;", "", "streamId", "Landroid/view/Surface;", "getSurface", "(Lcom/samsung/android/vexfwk/session/VexFwkUsecase;ILqe/c;)Ljava/lang/Object;", "Lkotlin/Function2;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfiguration;", "Lqe/c;", "", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfigurationInitializer;", "configurationInitializer", "Lcom/google/common/util/concurrent/ListenableFuture;", "supplyAsyncConfiguration", "(LAe/c;)Lcom/google/common/util/concurrent/ListenableFuture;", "T", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperProcess;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperProcessInitializer;", "processInitializer", "supplyAsyncProcess", "Lkotlin/Function1;", "action", "supplyAsyncCustom", "(LAe/b;)Lcom/google/common/util/concurrent/ListenableFuture;", "close", "finalize", "impl$delegate", "Lme/f;", "getImpl", "()Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBase;", "impl", "Lcom/samsung/android/vexfwk/coroutines/VexFwkCachedScope;", "helperScope", "Lcom/samsung/android/vexfwk/coroutines/VexFwkCachedScope;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfigurationFuture;", "configureFuture", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfigurationFuture;", "Lfg/a;", "configureFutureMutex", "Lfg/a;", "Ljava/lang/Thread;", "closeThread", "Ljava/lang/Thread;", "getConfigureFutureOrThrow", "()Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfigurationFuture;", "configureFutureOrThrow", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "context", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VexFwkHelperBase<IMPL extends VexFwkHelperBase<IMPL>> implements AutoCloseable {
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public static final String TAG = "VexFwkHelperBase";
    private Thread closeThread;
    /* access modifiers changed from: private */
    public VexFwkHelperConfigurationFuture<IMPL> configureFuture;
    /* access modifiers changed from: private */
    public final a configureFutureMutex = new c();
    private final VexFwkCachedScope helperScope = new VexFwkCachedScope((ExecutorService) null, (Y) null, 3, (e) null);
    private final f impl$delegate = d.q(new q(10, this));

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J7\u0010\f\u001a\u0002H\r\"\u0004\b\u0001\u0010\r2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\r0\u0011H\u0007¢\u0006\u0002\u0010\u0012R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBase$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/String;", "isAvailable", "", "usecase", "Lcom/samsung/android/vexfwk/session/VexFwkUsecase;", "getProperties", "T", "propertiesKey", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "defaultValue", "Ljava/util/function/Supplier;", "(Lcom/samsung/android/vexfwk/session/VexFwkUsecase;Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;Ljava/util/function/Supplier;)Ljava/lang/Object;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final Boolean isAvailable$lambda$0() {
            return Boolean.FALSE;
        }

        public final <T> T getProperties(VexFwkUsecase vexFwkUsecase, VexFwkMetadataKey<T> vexFwkMetadataKey, Supplier<T> supplier) {
            T t;
            j.e(vexFwkUsecase, "usecase");
            j.e(vexFwkMetadataKey, "propertiesKey");
            j.e(supplier, "defaultValue");
            try {
                t = VexFwkProvider.fetchProperties(vexFwkUsecase).get(vexFwkMetadataKey);
            } catch (Throwable th) {
                t = L2.a.l(th);
            }
            Throwable a7 = k.a(t);
            if (a7 != null) {
                String access$getTAG$cp = VexFwkHelperBase.TAG;
                Log.w(access$getTAG$cp, "getProperties : failed to fetch properties, maybe this is not supported on the device : " + vexFwkUsecase, a7);
            }
            if (t instanceof me.j) {
                t = null;
            }
            if (t == null) {
                return supplier.get();
            }
            return t;
        }

        public final boolean isAvailable(VexFwkUsecase vexFwkUsecase) {
            j.e(vexFwkUsecase, "usecase");
            boolean booleanValue = ((Boolean) getProperties(vexFwkUsecase, VexFwkMetadataKey.PROPERTY_IS_AVAILABLE.INSTANCE, new C0720a(20))).booleanValue();
            String access$getTAG$cp = VexFwkHelperBase.TAG;
            Log.d(access$getTAG$cp, "isAvailable : " + vexFwkUsecase + " -> " + booleanValue);
            return booleanValue;
        }

        private Companion() {
        }
    }

    static {
        VexFwkJniLoader.loadLibrary("common-jni.vexfwk.samsung");
        VexFwkJniLoader.loadLibrary("sdk-v2-jni.vexfwk.samsung");
    }

    public VexFwkHelperBase() {
        String str = TAG;
        VexFwkHelperBase impl = getImpl();
        Log.i(str, "Creating helper " + impl + " with sdkVersion=1.0.29");
    }

    /* access modifiers changed from: private */
    public static final void close$lambda$10(VexFwkHelperBase vexFwkHelperBase) {
        vexFwkHelperBase.closeInternal();
    }

    private final void closeInternal() {
        this.helperScope.close();
        Object unused = D.r(C1233i.d, new b(this, (C1227c) null));
    }

    private final VexFwkHelperConfigurationFuture<IMPL> getConfigureFutureOrThrow() {
        VexFwkHelperConfigurationFuture<IMPL> vexFwkHelperConfigurationFuture = this.configureFuture;
        if (vexFwkHelperConfigurationFuture != null) {
            return vexFwkHelperConfigurationFuture;
        }
        throw new IllegalStateException("configureFuture is null. maybe you should call configure first.");
    }

    private final IMPL getImpl() {
        return (VexFwkHelperBase) this.impl$delegate.getValue();
    }

    public static final <T> T getProperties(VexFwkUsecase vexFwkUsecase, VexFwkMetadataKey<T> vexFwkMetadataKey, Supplier<T> supplier) {
        return Companion.getProperties(vexFwkUsecase, vexFwkMetadataKey, supplier);
    }

    /* access modifiers changed from: private */
    public static final VexFwkHelperBase impl_delegate$lambda$0(VexFwkHelperBase vexFwkHelperBase) {
        j.c(vexFwkHelperBase, "null cannot be cast to non-null type IMPL of com.samsung.android.vexfwk.sdk.common.VexFwkHelperBase");
        return vexFwkHelperBase;
    }

    public static final boolean isAvailable(VexFwkUsecase vexFwkUsecase) {
        return Companion.isAvailable(vexFwkUsecase);
    }

    public synchronized void close() {
        if (this.closeThread != null) {
            String str = TAG;
            VexFwkHelperBase impl = getImpl();
            Log.i(str, "already closed helper " + impl);
            return;
        }
        String str2 = TAG;
        VexFwkHelperBase impl2 = getImpl();
        Log.i(str2, "closing helper " + impl2);
        Thread thread = new Thread(new C0584a(26, this));
        thread.start();
        this.closeThread = thread;
    }

    public final synchronized void finalize() {
        try {
            Thread thread = this.closeThread;
            if (thread != null) {
                thread.join();
            } else {
                closeInternal();
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public abstract Context getContext();

    /* JADX WARNING: type inference failed for: r8v14, types: [fg.a] */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0062, code lost:
        if (r10.c(r1) == r2) goto L_0x0077;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x008c A[Catch:{ all -> 0x0094 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0096 A[SYNTHETIC, Splitter:B:36:0x0096] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getSession(com.samsung.android.vexfwk.session.VexFwkUsecase r9, qe.C1227c r10) {
        /*
            r8 = this;
            java.lang.String r0 = "failed to get session for "
            boolean r1 = r10 instanceof com.samsung.android.vexfwk.sdk.common.c
            if (r1 == 0) goto L_0x0015
            r1 = r10
            com.samsung.android.vexfwk.sdk.common.c r1 = (com.samsung.android.vexfwk.sdk.common.c) r1
            int r2 = r1.f4149i
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.f4149i = r2
            goto L_0x001a
        L_0x0015:
            com.samsung.android.vexfwk.sdk.common.c r1 = new com.samsung.android.vexfwk.sdk.common.c
            r1.<init>(r8, r10)
        L_0x001a:
            java.lang.Object r10 = r1.g
            re.a r2 = re.C1245a.COROUTINE_SUSPENDED
            int r3 = r1.f4149i
            r4 = 2
            r5 = 1
            r6 = 0
            if (r3 == 0) goto L_0x004f
            if (r3 == r5) goto L_0x003f
            if (r3 != r4) goto L_0x0037
            java.lang.Object r8 = r1.e
            fg.a r8 = (fg.a) r8
            java.lang.Object r9 = r1.d
            com.samsung.android.vexfwk.session.VexFwkUsecase r9 = (com.samsung.android.vexfwk.session.VexFwkUsecase) r9
            L2.a.A(r10)     // Catch:{ all -> 0x0035 }
            goto L_0x007b
        L_0x0035:
            r10 = move-exception
            goto L_0x0082
        L_0x0037:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x003f:
            fg.a r8 = r1.f
            java.lang.Object r9 = r1.e
            com.samsung.android.vexfwk.session.VexFwkUsecase r9 = (com.samsung.android.vexfwk.session.VexFwkUsecase) r9
            java.lang.Object r3 = r1.d
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperBase r3 = (com.samsung.android.vexfwk.sdk.common.VexFwkHelperBase) r3
            L2.a.A(r10)
            r10 = r8
            r8 = r3
            goto L_0x0065
        L_0x004f:
            L2.a.A(r10)
            fg.a r10 = r8.configureFutureMutex
            r1.d = r8
            r1.e = r9
            r1.f = r10
            r1.f4149i = r5
            fg.c r10 = (fg.c) r10
            java.lang.Object r3 = r10.c(r1)
            if (r3 != r2) goto L_0x0065
            goto L_0x0077
        L_0x0065:
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture r8 = r8.getConfigureFutureOrThrow()     // Catch:{ all -> 0x007e }
            r1.d = r9     // Catch:{ all -> 0x007e }
            r1.e = r10     // Catch:{ all -> 0x007e }
            r1.f = r6     // Catch:{ all -> 0x007e }
            r1.f4149i = r4     // Catch:{ all -> 0x007e }
            java.lang.Object r8 = r8.awaitSession(r9, r1)     // Catch:{ all -> 0x007e }
            if (r8 != r2) goto L_0x0078
        L_0x0077:
            return r2
        L_0x0078:
            r7 = r10
            r10 = r8
            r8 = r7
        L_0x007b:
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r10 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession) r10     // Catch:{ all -> 0x0035 }
            goto L_0x0086
        L_0x007e:
            r8 = move-exception
            r7 = r10
            r10 = r8
            r8 = r7
        L_0x0082:
            me.j r10 = L2.a.l(r10)     // Catch:{ all -> 0x0094 }
        L_0x0086:
            java.lang.Throwable r1 = me.k.a(r10)     // Catch:{ all -> 0x0094 }
            if (r1 != 0) goto L_0x0096
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r10 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession) r10     // Catch:{ all -> 0x0094 }
            fg.c r8 = (fg.c) r8
            r8.d(r6)
            return r10
        L_0x0094:
            r9 = move-exception
            goto L_0x00a8
        L_0x0096:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0094 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0094 }
            r2.<init>(r0)     // Catch:{ all -> 0x0094 }
            r2.append(r9)     // Catch:{ all -> 0x0094 }
            java.lang.String r9 = r2.toString()     // Catch:{ all -> 0x0094 }
            r10.<init>(r9, r1)     // Catch:{ all -> 0x0094 }
            throw r10     // Catch:{ all -> 0x0094 }
        L_0x00a8:
            fg.c r8 = (fg.c) r8
            r8.d(r6)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.common.VexFwkHelperBase.getSession(com.samsung.android.vexfwk.session.VexFwkUsecase, qe.c):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x005f A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getSurface(com.samsung.android.vexfwk.session.VexFwkUsecase r5, int r6, qe.C1227c r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.samsung.android.vexfwk.sdk.common.d
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.samsung.android.vexfwk.sdk.common.d r0 = (com.samsung.android.vexfwk.sdk.common.d) r0
            int r1 = r0.g
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.g = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.vexfwk.sdk.common.d r0 = new com.samsung.android.vexfwk.sdk.common.d
            r0.<init>(r4, r7)
        L_0x0018:
            java.lang.Object r7 = r0.e
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.g
            r3 = 1
            if (r2 == 0) goto L_0x0031
            if (r2 != r3) goto L_0x0029
            int r6 = r0.d
            L2.a.A(r7)
            goto L_0x003f
        L_0x0029:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0031:
            L2.a.A(r7)
            r0.d = r6
            r0.g = r3
            java.lang.Object r7 = r4.getSession(r5, r0)
            if (r7 != r1) goto L_0x003f
            return r1
        L_0x003f:
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r7 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession) r7
            java.util.List r4 = r7.getStreams()
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.Iterator r4 = r4.iterator()
        L_0x004b:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x005f
            java.lang.Object r5 = r4.next()
            r7 = r5
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkStream r7 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkStream) r7
            int r7 = r7.getId()
            if (r7 != r6) goto L_0x004b
            goto L_0x0060
        L_0x005f:
            r5 = 0
        L_0x0060:
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkStream r5 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkStream) r5
            java.lang.String r4 = ")"
            if (r5 == 0) goto L_0x007d
            android.view.Surface r5 = r5.getSurface()
            if (r5 == 0) goto L_0x006d
            return r5
        L_0x006d:
            java.lang.String r5 = "surface is null. maybe you should call configure first or invalid stream id("
            java.lang.String r4 = i.C0212a.j(r6, r5, r4)
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r4 = r4.toString()
            r5.<init>(r4)
            throw r5
        L_0x007d:
            java.lang.String r5 = "stream is null. maybe you should call configure first or invalid stream id("
            java.lang.String r4 = i.C0212a.j(r6, r5, r4)
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r4 = r4.toString()
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.common.VexFwkHelperBase.getSurface(com.samsung.android.vexfwk.session.VexFwkUsecase, int, qe.c):java.lang.Object");
    }

    public final synchronized ListenableFuture supplyAsyncConfiguration(Ae.c cVar) {
        VexFwkHelperConfigurationFuture vexFwkHelperConfigurationFuture;
        j.e(cVar, "configurationInitializer");
        vexFwkHelperConfigurationFuture = new VexFwkHelperConfigurationFuture(this.helperScope.get(), getImpl(), cVar);
        Object unused = D.r(C1233i.d, new e(this, vexFwkHelperConfigurationFuture, (C1227c) null));
        return vexFwkHelperConfigurationFuture;
    }

    public final <T> ListenableFuture supplyAsyncCustom(b bVar) {
        j.e(bVar, "action");
        VexFwkHelperCustomFuture vexFwkHelperCustomFuture = new VexFwkHelperCustomFuture(this.helperScope.get(), bVar);
        vexFwkHelperCustomFuture.start();
        return vexFwkHelperCustomFuture;
    }

    public final <T> ListenableFuture supplyAsyncProcess(Ae.c cVar) {
        j.e(cVar, "processInitializer");
        VexFwkHelperProcessFuture vexFwkHelperProcessFuture = new VexFwkHelperProcessFuture(this.helperScope.get(), cVar, new Zf.a(this, (C1227c) null, 3));
        vexFwkHelperProcessFuture.start();
        return vexFwkHelperProcessFuture;
    }
}
