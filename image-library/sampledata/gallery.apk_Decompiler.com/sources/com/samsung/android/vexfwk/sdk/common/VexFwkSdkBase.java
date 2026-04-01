package com.samsung.android.vexfwk.sdk.common;

import A8.C0544a;
import Ae.b;
import L2.a;
import android.util.Log;
import androidx.core.util.Supplier;
import com.samsung.android.vexfwk.VexFwkJniLoader;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkProvider;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.k;
import me.x;
import te.C1295a;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018\u0000 &2\u00060\u0001j\u0002`\u0002:\u0002&'B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0004¢\u0006\u0004\b\b\u0010\tJ+\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u00052\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nH\u0004¢\u0006\u0004\b\u000e\u0010\u000fJ-\u0010\u0012\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0010*\u00028\u00002\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\nH\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\f¢\u0006\u0004\b\u0014\u0010\u0004J\u000f\u0010\u0015\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0015\u0010\u0004R\"\u0010\u0019\u001a\u0010\u0012\f\u0012\n \u0018*\u0004\u0018\u00010\u00170\u00170\u00168\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR \u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00070\u001b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\"\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u001e0\u00168\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010\u001aR$\u0010%\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u00178F@BX\u000e¢\u0006\f\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u0006("}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "Lcom/samsung/android/vexfwk/session/VexFwkUsecase;", "usecase", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSession;", "getSession", "(Lcom/samsung/android/vexfwk/session/VexFwkUsecase;)Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSession;", "Lkotlin/Function1;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionConfigRequest$Builder;", "Lme/x;", "onConfigureSession", "createSession", "(Lcom/samsung/android/vexfwk/session/VexFwkUsecase;LAe/b;)V", "T", "onConfigure", "configureWith", "(Ljava/lang/Object;LAe/b;)Ljava/lang/Object;", "awaitConfiguration", "close", "Ljava/util/concurrent/atomic/AtomicReference;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase$State;", "kotlin.jvm.PlatformType", "stateAtomic", "Ljava/util/concurrent/atomic/AtomicReference;", "Ljava/util/concurrent/ConcurrentHashMap;", "sessionMap", "Ljava/util/concurrent/ConcurrentHashMap;", "Ljava/util/concurrent/CompletableFuture;", "configureFuture", "value", "getState", "()Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase$State;", "setState", "(Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase$State;)V", "state", "Companion", "State", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VexFwkSdkBase implements AutoCloseable {
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public static final String TAG = "VexFwkSdkBase";
    private AtomicReference<CompletableFuture<x>> configureFuture = new AtomicReference<>((Object) null);
    private final ConcurrentHashMap<VexFwkUsecase, VexFwkSession> sessionMap = new ConcurrentHashMap<>();
    private final AtomicReference<State> stateAtomic = new AtomicReference<>(State.IDLE);

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J7\u0010\f\u001a\u0002H\r\"\u0004\b\u0000\u0010\r2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\r0\u0011H\u0007¢\u0006\u0002\u0010\u0012R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/String;", "isAvailable", "", "usecase", "Lcom/samsung/android/vexfwk/session/VexFwkUsecase;", "getProperties", "T", "propertiesKey", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "defaultValue", "Landroidx/core/util/Supplier;", "(Lcom/samsung/android/vexfwk/session/VexFwkUsecase;Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;Landroidx/core/util/Supplier;)Ljava/lang/Object;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
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
                t = a.l(th);
            }
            Throwable a7 = k.a(t);
            if (a7 != null) {
                String access$getTAG$cp = VexFwkSdkBase.TAG;
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
            boolean booleanValue = ((Boolean) getProperties(vexFwkUsecase, VexFwkMetadataKey.PROPERTY_IS_AVAILABLE.INSTANCE, new W.a(23))).booleanValue();
            String access$getTAG$cp = VexFwkSdkBase.TAG;
            Log.d(access$getTAG$cp, "isAvailable : " + vexFwkUsecase + " -> " + booleanValue);
            return booleanValue;
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase$State;", "", "<init>", "(Ljava/lang/String;I)V", "IDLE", "CONFIGURING", "CONFIGURE_SUCCEEDED", "CONFIGURE_FAILED", "CLOSED", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum State {
        IDLE,
        CONFIGURING,
        CONFIGURE_SUCCEEDED,
        CONFIGURE_FAILED,
        CLOSED;

        static {
            State[] $values;
            $ENTRIES = c.t($values);
        }

        public static C1295a getEntries() {
            return $ENTRIES;
        }
    }

    static {
        VexFwkJniLoader.loadLibrary("common-jni.vexfwk.samsung");
        VexFwkJniLoader.loadLibrary("sdk-v2-jni.vexfwk.samsung");
    }

    public VexFwkSdkBase() {
        Log.i(TAG, "VexFwkSdk is created : version(1.0.29)");
    }

    /* access modifiers changed from: private */
    public static final x configureWith$lambda$11(Object obj, b bVar, VexFwkSdkBase vexFwkSdkBase) {
        Object obj2;
        x xVar = x.f4917a;
        try {
            bVar.invoke(obj);
            obj2 = xVar;
        } catch (Throwable th) {
            obj2 = a.l(th);
        }
        if (!(obj2 instanceof me.j)) {
            x xVar2 = (x) obj2;
            if (vexFwkSdkBase.getState() == State.CONFIGURING) {
                vexFwkSdkBase.setState(State.CONFIGURE_SUCCEEDED);
            }
        }
        Throwable a7 = k.a(obj2);
        if (a7 != null) {
            Log.e(TAG, "configure failed", a7);
            vexFwkSdkBase.setState(State.CONFIGURE_FAILED);
        }
        a.A(obj2);
        return xVar;
    }

    public static final <T> T getProperties(VexFwkUsecase vexFwkUsecase, VexFwkMetadataKey<T> vexFwkMetadataKey, Supplier<T> supplier) {
        return Companion.getProperties(vexFwkUsecase, vexFwkMetadataKey, supplier);
    }

    public static final boolean isAvailable(VexFwkUsecase vexFwkUsecase) {
        return Companion.isAvailable(vexFwkUsecase);
    }

    private final void setState(State state) {
        this.stateAtomic.set(state);
    }

    public final void awaitConfiguration() {
        Object obj;
        CompletableFuture completableFuture = this.configureFuture.get();
        if (completableFuture != null) {
            try {
                completableFuture.get();
                obj = x.f4917a;
            } catch (Throwable th) {
                obj = a.l(th);
            }
            if (k.a(obj) != null) {
                Log.e(TAG, "Failed to await configuration");
                setState(State.CONFIGURE_FAILED);
            }
            this.configureFuture.set((Object) null);
        }
    }

    public synchronized void close() {
        boolean z;
        try {
            Log.d(TAG, "close E");
            boolean z3 = false;
            if (getState() != State.IDLE) {
                z = true;
            } else {
                z = false;
            }
            if (getState() != State.CLOSED) {
                z3 = true;
            }
            if (z && z3) {
                awaitConfiguration();
                Collection<VexFwkSession> values = this.sessionMap.values();
                j.d(values, "<get-values>(...)");
                for (VexFwkSession vexFwkSession : values) {
                    Log.d(TAG, "close session : usecase(" + vexFwkSession + ")");
                    vexFwkSession.flush();
                    vexFwkSession.close();
                }
                this.sessionMap.clear();
                setState(State.CLOSED);
            }
            Log.d(TAG, "close X");
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public final <T> T configureWith(T t, b bVar) {
        j.e(bVar, "onConfigure");
        String str = TAG;
        Log.d(str, "configureWith E");
        if (getState() == State.IDLE) {
            setState(State.CONFIGURING);
            this.configureFuture.set(CompletableFuture.supplyAsync(new C0544a(t, bVar, this, 4)));
        }
        Log.d(str, "configureWith X");
        return t;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: me.j} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: me.j} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v10, resolved type: me.j} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v11, resolved type: me.j} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void createSession(com.samsung.android.vexfwk.session.VexFwkUsecase r6, Ae.b r7) {
        /*
            r5 = this;
            java.lang.String r0 = ")"
            java.lang.String r1 = "createSession : remove old session for usecase("
            java.lang.String r2 = "usecase"
            kotlin.jvm.internal.j.e(r6, r2)
            java.lang.String r2 = "onConfigureSession"
            kotlin.jvm.internal.j.e(r7, r2)
            java.util.concurrent.ConcurrentHashMap<com.samsung.android.vexfwk.session.VexFwkUsecase, com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession> r2 = r5.sessionMap     // Catch:{ all -> 0x0030 }
            java.lang.Object r2 = r2.remove(r6)     // Catch:{ all -> 0x0030 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r2 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession) r2     // Catch:{ all -> 0x0030 }
            if (r2 == 0) goto L_0x0032
            java.lang.String r3 = TAG     // Catch:{ all -> 0x0030 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0030 }
            r4.<init>(r1)     // Catch:{ all -> 0x0030 }
            r4.append(r6)     // Catch:{ all -> 0x0030 }
            r4.append(r0)     // Catch:{ all -> 0x0030 }
            java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x0030 }
            android.util.Log.w(r3, r1)     // Catch:{ all -> 0x0030 }
            r2.close()     // Catch:{ all -> 0x0030 }
            goto L_0x0032
        L_0x0030:
            r7 = move-exception
            goto L_0x0067
        L_0x0032:
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r1 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession     // Catch:{ all -> 0x0030 }
            r1.<init>(r6)     // Catch:{ all -> 0x0030 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest$Builder r2 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest$Builder     // Catch:{ all -> 0x0047 }
            r2.<init>()     // Catch:{ all -> 0x0047 }
            r7.invoke(r2)     // Catch:{ all -> 0x0047 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest r7 = r2.build()     // Catch:{ all -> 0x0047 }
            r1.configure(r7)     // Catch:{ all -> 0x0047 }
            goto L_0x004c
        L_0x0047:
            r7 = move-exception
            me.j r7 = L2.a.l(r7)     // Catch:{ all -> 0x0030 }
        L_0x004c:
            boolean r2 = r7 instanceof me.j     // Catch:{ all -> 0x0030 }
            if (r2 != 0) goto L_0x0058
            r2 = r7
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest r2 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest) r2     // Catch:{ all -> 0x0030 }
            java.util.concurrent.ConcurrentHashMap<com.samsung.android.vexfwk.session.VexFwkUsecase, com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession> r2 = r5.sessionMap     // Catch:{ all -> 0x0030 }
            r2.put(r6, r1)     // Catch:{ all -> 0x0030 }
        L_0x0058:
            java.lang.Throwable r2 = me.k.a(r7)     // Catch:{ all -> 0x0030 }
            if (r2 == 0) goto L_0x0061
            r1.close()     // Catch:{ all -> 0x0030 }
        L_0x0061:
            L2.a.A(r7)     // Catch:{ all -> 0x0030 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest r7 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest) r7     // Catch:{ all -> 0x0030 }
            goto L_0x006b
        L_0x0067:
            me.j r7 = L2.a.l(r7)
        L_0x006b:
            java.lang.Throwable r7 = me.k.a(r7)
            if (r7 == 0) goto L_0x008c
            java.lang.String r1 = TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "createSession : failed to create session for usecase("
            r2.<init>(r3)
            r2.append(r6)
            r2.append(r0)
            java.lang.String r6 = r2.toString()
            android.util.Log.e(r1, r6, r7)
            com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase$State r6 = com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase.State.CONFIGURE_FAILED
            r5.setState(r6)
        L_0x008c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase.createSession(com.samsung.android.vexfwk.session.VexFwkUsecase, Ae.b):void");
    }

    public final VexFwkSession getSession(VexFwkUsecase vexFwkUsecase) {
        j.e(vexFwkUsecase, "usecase");
        awaitConfiguration();
        VexFwkSession vexFwkSession = this.sessionMap.get(vexFwkUsecase);
        if (vexFwkSession != null) {
            return vexFwkSession;
        }
        throw new IllegalArgumentException("no session with usecase(" + vexFwkUsecase + ")");
    }

    public final State getState() {
        State state = this.stateAtomic.get();
        j.d(state, "get(...)");
        return state;
    }
}
