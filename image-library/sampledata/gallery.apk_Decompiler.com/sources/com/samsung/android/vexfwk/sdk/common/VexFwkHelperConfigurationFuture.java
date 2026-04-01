package com.samsung.android.vexfwk.sdk.common;

import Ad.f;
import Ae.c;
import Vf.A;
import Vf.C;
import Vf.C0861b0;
import Vf.C0867e0;
import Vf.C0880q;
import Vf.C0883u;
import Vf.C0888z;
import Vf.D;
import Vf.P;
import Vf.n0;
import Vf.r;
import android.view.Surface;
import com.samsung.android.sdk.scs.ai.language.a;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperBaseFuture;
import com.samsung.android.vexfwk.session.VexFwkStreamInoutDirection;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;
import qe.C1227c;
import qe.C1233i;
import re.C1245a;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 **\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002:\u0005*+,-\u0011B?\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012&\u0010\f\u001a\"\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0006j\u0002`\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u001c\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fH@¢\u0006\u0004\b\u0012\u0010\u0013J\u001c\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fH@¢\u0006\u0004\b\u0014\u0010\u0013J\u0018\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0011H@¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00028\u0000H@¢\u0006\u0004\b\u0019\u0010\u0013J\u0018\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u0010H@¢\u0006\u0004\b\u001c\u0010\u001dJ\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001eH@¢\u0006\u0004\b\u001f\u0010\u0013R\u0014\u0010\u0005\u001a\u00028\u00008\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010 R4\u0010\f\u001a\"\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0006j\u0002`\u000b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\f\u0010!R&\u0010#\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f0\"8\u0002X\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u001a\u0010&\u001a\u00020%8\u0014X\u0004¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)¨\u0006."}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfigurationFuture;", "T", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBaseFuture;", "LVf/A;", "coroutineScope", "helper", "Lkotlin/Function2;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfiguration;", "Lqe/c;", "Lme/x;", "", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfigurationInitializer;", "configurationInitializer", "<init>", "(LVf/A;Ljava/lang/Object;LAe/c;)V", "", "Lcom/samsung/android/vexfwk/session/VexFwkUsecase;", "Lcom/samsung/android/vexfwk/sdk/common/h;", "buildConfigMap", "(Lqe/c;)Ljava/lang/Object;", "awaitConfigMap", "futureConfig", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionConfigRequest;", "createSessionConfig", "(Lcom/samsung/android/vexfwk/sdk/common/h;Lqe/c;)Ljava/lang/Object;", "execute", "usecase", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSession;", "awaitSession", "(Lcom/samsung/android/vexfwk/session/VexFwkUsecase;Lqe/c;)Ljava/lang/Object;", "", "getAllSessions", "Ljava/lang/Object;", "LAe/c;", "LVf/q;", "configMap", "LVf/q;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBaseFuture$OnCompletedCallback;", "onCompletedCallback", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBaseFuture$OnCompletedCallback;", "getOnCompletedCallback", "()Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBaseFuture$OnCompletedCallback;", "Companion", "Stream", "com/samsung/android/vexfwk/sdk/common/g", "com/samsung/android/vexfwk/sdk/common/i", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkHelperConfigurationFuture<T> extends VexFwkHelperBaseFuture<T> {
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public static final String TAG = "VexFwkHelperConfigurationFuture";
    /* access modifiers changed from: private */
    public final C0880q configMap;
    private final c configurationInitializer;
    private final T helper;
    private final VexFwkHelperBaseFuture.OnCompletedCallback onCompletedCallback = new a(8, this);

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfigurationFuture$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/String;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0005R\u0012\u0010\u0014\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0005R\u0012\u0010\u0016\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0005R\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u0019X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfigurationFuture$Stream;", "", "id", "", "getId", "()I", "direction", "Lcom/samsung/android/vexfwk/session/VexFwkStreamInoutDirection;", "getDirection", "()Lcom/samsung/android/vexfwk/session/VexFwkStreamInoutDirection;", "type", "Lcom/samsung/android/vexfwk/session/VexFwkStreamType;", "getType", "()Lcom/samsung/android/vexfwk/session/VexFwkStreamType;", "usage", "Lcom/samsung/android/vexfwk/session/VexFwkStreamUsage;", "getUsage", "()Lcom/samsung/android/vexfwk/session/VexFwkStreamUsage;", "width", "getWidth", "height", "getHeight", "format", "getFormat", "surface", "Landroid/view/Surface;", "getSurface", "()Landroid/view/Surface;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Stream {
        VexFwkStreamInoutDirection getDirection();

        int getFormat();

        int getHeight();

        int getId();

        Surface getSurface();

        VexFwkStreamType getType();

        VexFwkStreamUsage getUsage();

        int getWidth();
    }

    /* JADX WARNING: type inference failed for: r4v1, types: [Vf.n0, Vf.q] */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VexFwkHelperConfigurationFuture(A a7, T t, c cVar) {
        super(a7);
        j.e(a7, "coroutineScope");
        j.e(cVar, "configurationInitializer");
        this.helper = t;
        this.configurationInitializer = cVar;
        ? n0Var = new n0(true);
        n0Var.C((C0867e0) null);
        this.configMap = n0Var;
        D.b(a7, (C0888z) null, (C) null, new Zf.a(this, (C1227c) null, 5), 3).D(true, new P(1, new f(14, (Object) this)));
    }

    /* access modifiers changed from: private */
    public static final x _init_$lambda$1(VexFwkHelperConfigurationFuture vexFwkHelperConfigurationFuture, Throwable th) {
        n0 n0Var = (n0) vexFwkHelperConfigurationFuture.configMap;
        n0Var.getClass();
        boolean z = n0.d.get(n0Var) instanceof C0861b0;
        x xVar = x.f4917a;
        if (!z) {
            return xVar;
        }
        if (th != null) {
            r rVar = (r) vexFwkHelperConfigurationFuture.configMap;
            rVar.getClass();
            rVar.H(new C0883u(th, false));
            return xVar;
        }
        C0880q qVar = vexFwkHelperConfigurationFuture.configMap;
        IllegalStateException illegalStateException = new IllegalStateException("Failed to build config map due to unknown reason");
        r rVar2 = (r) qVar;
        rVar2.getClass();
        rVar2.H(new C0883u(illegalStateException, false));
        return xVar;
    }

    /* access modifiers changed from: private */
    public final Object awaitConfigMap(C1227c cVar) {
        Object m = ((r) this.configMap).m(cVar);
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        return m;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object buildConfigMap(qe.C1227c r13) {
        /*
            r12 = this;
            boolean r0 = r13 instanceof com.samsung.android.vexfwk.sdk.common.k
            if (r0 == 0) goto L_0x0013
            r0 = r13
            com.samsung.android.vexfwk.sdk.common.k r0 = (com.samsung.android.vexfwk.sdk.common.k) r0
            int r1 = r0.f4162j
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f4162j = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.vexfwk.sdk.common.k r0 = new com.samsung.android.vexfwk.sdk.common.k
            r0.<init>(r12, r13)
        L_0x0018:
            java.lang.Object r13 = r0.f4160h
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.f4162j
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0045
            if (r2 == r4) goto L_0x003d
            if (r2 != r3) goto L_0x0035
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration$Session r12 = r0.g
            java.util.Map r2 = r0.f
            java.util.Iterator r4 = r0.e
            java.lang.Object r5 = r0.d
            java.util.Map r5 = (java.util.Map) r5
            L2.a.A(r13)
            goto L_0x00a0
        L_0x0035:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x003d:
            java.lang.Object r12 = r0.d
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration r12 = (com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration) r12
            L2.a.A(r13)
            goto L_0x005b
        L_0x0045:
            L2.a.A(r13)
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration r13 = new com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration
            r13.<init>()
            Ae.c r12 = r12.configurationInitializer
            r0.d = r13
            r0.f4162j = r4
            java.lang.Object r12 = r12.invoke(r13, r0)
            if (r12 != r1) goto L_0x005a
            goto L_0x009d
        L_0x005a:
            r12 = r13
        L_0x005b:
            java.util.List r12 = r12.getSessions()
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            r13 = 10
            int r13 = ne.C1196n.w0(r12, r13)
            int r13 = ne.z.Z(r13)
            r2 = 16
            if (r13 >= r2) goto L_0x0070
            r13 = r2
        L_0x0070:
            java.util.LinkedHashMap r2 = new java.util.LinkedHashMap
            r2.<init>(r13)
            java.util.Iterator r12 = r12.iterator()
            r4 = r12
        L_0x007a:
            boolean r12 = r4.hasNext()
            if (r12 == 0) goto L_0x00dc
            java.lang.Object r12 = r4.next()
            Ae.c r12 = (Ae.c) r12
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration$Session r13 = new com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration$Session
            r5 = 3
            r6 = 0
            r13.<init>(r6, r6, r5, r6)
            r0.d = r2
            r0.e = r4
            r0.f = r2
            r0.g = r13
            r0.f4162j = r3
            java.lang.Object r12 = r12.invoke(r13, r0)
            if (r12 != r1) goto L_0x009e
        L_0x009d:
            return r1
        L_0x009e:
            r12 = r13
            r5 = r2
        L_0x00a0:
            com.samsung.android.vexfwk.session.VexFwkUsecase r13 = r12.getUsecase()
            if (r13 == 0) goto L_0x00c4
            com.samsung.android.vexfwk.sdk.common.h r6 = new com.samsung.android.vexfwk.sdk.common.h
            Ae.c r7 = r12.getConfigMetadata()
            java.util.List r8 = r12.getInputBufferStreams()
            java.util.List r9 = r12.getInputSurfaceStreams()
            java.util.List r10 = r12.getOutputBufferStreams()
            java.util.List r11 = r12.getOutputSurfaceStreams()
            r6.<init>(r7, r8, r9, r10, r11)
            r2.put(r13, r6)
            r2 = r5
            goto L_0x007a
        L_0x00c4:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r0 = "Usecase is not specified for session "
            r13.<init>(r0)
            r13.append(r12)
            java.lang.String r12 = r13.toString()
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r12 = r12.toString()
            r13.<init>(r12)
            throw r13
        L_0x00dc:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture.buildConfigMap(qe.c):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0100, code lost:
        if (r11.invoke(r1, r2) == r3) goto L_0x02a5;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x016d  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x01e0  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0207  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x024a  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0270  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x02b5  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x02c8 A[LOOP:0: B:55:0x02c2->B:57:0x02c8, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object createSessionConfig(com.samsung.android.vexfwk.sdk.common.h r26, qe.C1227c r27) {
        /*
            r25 = this;
            r0 = r26
            r1 = r27
            boolean r2 = r1 instanceof com.samsung.android.vexfwk.sdk.common.l
            if (r2 == 0) goto L_0x0017
            r2 = r1
            com.samsung.android.vexfwk.sdk.common.l r2 = (com.samsung.android.vexfwk.sdk.common.l) r2
            int r3 = r2.n
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L_0x0017
            int r3 = r3 - r4
            r2.n = r3
            goto L_0x001e
        L_0x0017:
            com.samsung.android.vexfwk.sdk.common.l r2 = new com.samsung.android.vexfwk.sdk.common.l
            r3 = r25
            r2.<init>(r3, r1)
        L_0x001e:
            java.lang.Object r1 = r2.l
            re.a r3 = re.C1245a.COROUTINE_SUSPENDED
            int r4 = r2.n
            r5 = 5
            r6 = 4
            r7 = 3
            r8 = 2
            r9 = 1
            r10 = 10
            if (r4 == 0) goto L_0x00e1
            if (r4 == r9) goto L_0x00cf
            if (r4 == r8) goto L_0x00aa
            if (r4 == r7) goto L_0x0085
            if (r4 == r6) goto L_0x0060
            if (r4 != r5) goto L_0x0058
            java.lang.Object r0 = r2.f4165j
            java.util.Collection r0 = (java.util.Collection) r0
            java.lang.Object r4 = r2.f4164i
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration$SurfaceStream r4 = (com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration.SurfaceStream) r4
            java.lang.Object r6 = r2.f4163h
            java.util.Iterator r6 = (java.util.Iterator) r6
            java.util.Collection r7 = r2.g
            java.util.Collection r7 = (java.util.Collection) r7
            java.lang.Object r8 = r2.f
            java.util.Collection r8 = (java.util.Collection) r8
            java.lang.Object r9 = r2.e
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r10 = r2.d
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest$Builder r10 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest.Builder) r10
            L2.a.A(r1)
            goto L_0x02a8
        L_0x0058:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0060:
            java.util.Collection r0 = r2.k
            java.util.Collection r0 = (java.util.Collection) r0
            java.lang.Object r4 = r2.f4165j
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration$BufferStream r4 = (com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration.BufferStream) r4
            java.lang.Object r7 = r2.f4164i
            java.util.Iterator r7 = (java.util.Iterator) r7
            java.lang.Object r8 = r2.f4163h
            java.util.Collection r8 = (java.util.Collection) r8
            java.util.Collection r9 = r2.g
            java.util.Collection r9 = (java.util.Collection) r9
            java.lang.Object r11 = r2.f
            java.util.List r11 = (java.util.List) r11
            java.lang.Object r12 = r2.e
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest$Builder r12 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest.Builder) r12
            java.lang.Object r13 = r2.d
            com.samsung.android.vexfwk.sdk.common.h r13 = (com.samsung.android.vexfwk.sdk.common.h) r13
            L2.a.A(r1)
            goto L_0x023d
        L_0x0085:
            java.util.Collection r0 = r2.k
            java.util.Collection r0 = (java.util.Collection) r0
            java.lang.Object r4 = r2.f4165j
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration$SurfaceStream r4 = (com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration.SurfaceStream) r4
            java.lang.Object r8 = r2.f4164i
            java.util.Iterator r8 = (java.util.Iterator) r8
            java.lang.Object r9 = r2.f4163h
            java.util.Collection r9 = (java.util.Collection) r9
            java.util.Collection r11 = r2.g
            java.util.Collection r11 = (java.util.Collection) r11
            java.lang.Object r12 = r2.f
            java.util.List r12 = (java.util.List) r12
            java.lang.Object r13 = r2.e
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest$Builder r13 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest.Builder) r13
            java.lang.Object r14 = r2.d
            com.samsung.android.vexfwk.sdk.common.h r14 = (com.samsung.android.vexfwk.sdk.common.h) r14
            L2.a.A(r1)
            goto L_0x01d3
        L_0x00aa:
            java.util.Collection r0 = r2.k
            java.util.Collection r0 = (java.util.Collection) r0
            java.lang.Object r4 = r2.f4165j
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration$BufferStream r4 = (com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration.BufferStream) r4
            java.lang.Object r9 = r2.f4164i
            java.util.Iterator r9 = (java.util.Iterator) r9
            java.lang.Object r11 = r2.f4163h
            java.util.Collection r11 = (java.util.Collection) r11
            java.util.Collection r12 = r2.g
            java.util.Collection r12 = (java.util.Collection) r12
            java.lang.Object r13 = r2.f
            java.util.List r13 = (java.util.List) r13
            java.lang.Object r14 = r2.e
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest$Builder r14 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest.Builder) r14
            java.lang.Object r15 = r2.d
            com.samsung.android.vexfwk.sdk.common.h r15 = (com.samsung.android.vexfwk.sdk.common.h) r15
            L2.a.A(r1)
            goto L_0x0160
        L_0x00cf:
            java.lang.Object r0 = r2.f
            com.samsung.android.vexfwk.metadata.VexFwkMetadataNative r0 = (com.samsung.android.vexfwk.metadata.VexFwkMetadataNative) r0
            java.lang.Object r4 = r2.e
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest$Builder r4 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest.Builder) r4
            java.lang.Object r9 = r2.d
            com.samsung.android.vexfwk.sdk.common.h r9 = (com.samsung.android.vexfwk.sdk.common.h) r9
            L2.a.A(r1)
            r1 = r0
            r0 = r9
            goto L_0x0104
        L_0x00e1:
            L2.a.A(r1)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest$Builder r4 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest$Builder
            r4.<init>()
            Ae.c r1 = r0.f4154a
            if (r1 == 0) goto L_0x0107
            com.samsung.android.vexfwk.metadata.VexFwkMetadataNative r1 = new com.samsung.android.vexfwk.metadata.VexFwkMetadataNative
            r1.<init>()
            Ae.c r11 = r0.f4154a
            r2.d = r0
            r2.e = r4
            r2.f = r1
            r2.n = r9
            java.lang.Object r9 = r11.invoke(r1, r2)
            if (r9 != r3) goto L_0x0104
            goto L_0x02a5
        L_0x0104:
            r4.setConfigMetadata((com.samsung.android.vexfwk.metadata.VexFwkMetadataNative) r1)
        L_0x0107:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.List r9 = r0.b
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.ArrayList r11 = new java.util.ArrayList
            int r12 = ne.C1196n.w0(r9, r10)
            r11.<init>(r12)
            java.util.Iterator r9 = r9.iterator()
            r15 = r0
            r12 = r1
            r13 = r12
            r14 = r4
        L_0x0121:
            r0 = r11
            boolean r1 = r9.hasNext()
            if (r1 == 0) goto L_0x016d
            java.lang.Object r1 = r9.next()
            Ae.c r1 = (Ae.c) r1
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration$BufferStream r16 = new com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration$BufferStream
            r20 = 7
            r21 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r16.<init>(r17, r18, r19, r20, r21)
            r4 = r16
            r2.d = r15
            r2.e = r14
            r2.f = r13
            r11 = r12
            java.util.Collection r11 = (java.util.Collection) r11
            r2.g = r11
            r2.f4163h = r0
            r2.f4164i = r9
            r2.f4165j = r4
            r11 = r0
            java.util.Collection r11 = (java.util.Collection) r11
            r2.k = r11
            r2.n = r8
            java.lang.Object r1 = r1.invoke(r4, r2)
            if (r1 != r3) goto L_0x015f
            goto L_0x02a5
        L_0x015f:
            r11 = r0
        L_0x0160:
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture$BufferStream$Companion r1 = com.samsung.android.vexfwk.sdk.common.g.e
            com.samsung.android.vexfwk.session.VexFwkStreamInoutDirection r8 = com.samsung.android.vexfwk.session.VexFwkStreamInoutDirection.IN
            com.samsung.android.vexfwk.sdk.common.g r1 = r1.from(r4, r8)
            r0.add(r1)
            r8 = 2
            goto L_0x0121
        L_0x016d:
            java.util.List r0 = (java.util.List) r0
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            ne.C1200r.A0(r0, r12)
            r0 = r13
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.List r1 = r15.f4155c
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r4 = new java.util.ArrayList
            int r8 = ne.C1196n.w0(r1, r10)
            r4.<init>(r8)
            java.util.Iterator r1 = r1.iterator()
            r11 = r0
            r8 = r1
            r0 = r4
            r12 = r13
            r13 = r14
            r14 = r15
        L_0x018e:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L_0x01e0
            java.lang.Object r1 = r8.next()
            Ae.c r1 = (Ae.c) r1
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration$SurfaceStream r15 = new com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration$SurfaceStream
            r23 = 127(0x7f, float:1.78E-43)
            r24 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r15.<init>(r16, r17, r18, r19, r20, r21, r22, r23, r24)
            r2.d = r14
            r2.e = r13
            r2.f = r12
            r4 = r11
            java.util.Collection r4 = (java.util.Collection) r4
            r2.g = r4
            r2.f4163h = r0
            r2.f4164i = r8
            r2.f4165j = r15
            r4 = r0
            java.util.Collection r4 = (java.util.Collection) r4
            r2.k = r4
            r2.n = r7
            java.lang.Object r1 = r1.invoke(r15, r2)
            if (r1 != r3) goto L_0x01d1
            goto L_0x02a5
        L_0x01d1:
            r9 = r0
            r4 = r15
        L_0x01d3:
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture$SurfaceStream$Companion r1 = com.samsung.android.vexfwk.sdk.common.i.f4156i
            com.samsung.android.vexfwk.session.VexFwkStreamInoutDirection r15 = com.samsung.android.vexfwk.session.VexFwkStreamInoutDirection.IN
            com.samsung.android.vexfwk.sdk.common.i r1 = r1.from(r4, r15)
            r0.add(r1)
            r0 = r9
            goto L_0x018e
        L_0x01e0:
            java.util.List r0 = (java.util.List) r0
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            ne.C1200r.A0(r0, r11)
            r0 = r12
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.List r1 = r14.d
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r4 = new java.util.ArrayList
            int r7 = ne.C1196n.w0(r1, r10)
            r4.<init>(r7)
            java.util.Iterator r1 = r1.iterator()
            r9 = r0
            r7 = r1
            r0 = r4
            r11 = r12
            r12 = r13
            r13 = r14
        L_0x0201:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L_0x024a
            java.lang.Object r1 = r7.next()
            Ae.c r1 = (Ae.c) r1
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration$BufferStream r14 = new com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration$BufferStream
            r18 = 7
            r19 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r14.<init>(r15, r16, r17, r18, r19)
            r2.d = r13
            r2.e = r12
            r2.f = r11
            r4 = r9
            java.util.Collection r4 = (java.util.Collection) r4
            r2.g = r4
            r2.f4163h = r0
            r2.f4164i = r7
            r2.f4165j = r14
            r4 = r0
            java.util.Collection r4 = (java.util.Collection) r4
            r2.k = r4
            r2.n = r6
            java.lang.Object r1 = r1.invoke(r14, r2)
            if (r1 != r3) goto L_0x023b
            goto L_0x02a5
        L_0x023b:
            r8 = r0
            r4 = r14
        L_0x023d:
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture$BufferStream$Companion r1 = com.samsung.android.vexfwk.sdk.common.g.e
            com.samsung.android.vexfwk.session.VexFwkStreamInoutDirection r14 = com.samsung.android.vexfwk.session.VexFwkStreamInoutDirection.OUT
            com.samsung.android.vexfwk.sdk.common.g r1 = r1.from(r4, r14)
            r0.add(r1)
            r0 = r8
            goto L_0x0201
        L_0x024a:
            java.util.List r0 = (java.util.List) r0
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            ne.C1200r.A0(r0, r9)
            r0 = r11
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.List r1 = r13.e
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r4 = new java.util.ArrayList
            int r6 = ne.C1196n.w0(r1, r10)
            r4.<init>(r6)
            java.util.Iterator r1 = r1.iterator()
            r8 = r0
            r6 = r1
            r0 = r4
            r9 = r11
            r10 = r12
        L_0x026a:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L_0x02b5
            java.lang.Object r1 = r6.next()
            Ae.c r1 = (Ae.c) r1
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration$SurfaceStream r11 = new com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration$SurfaceStream
            r19 = 127(0x7f, float:1.78E-43)
            r20 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r11.<init>(r12, r13, r14, r15, r16, r17, r18, r19, r20)
            r2.d = r10
            r2.e = r9
            r2.f = r8
            r4 = r0
            java.util.Collection r4 = (java.util.Collection) r4
            r2.g = r4
            r2.f4163h = r6
            r2.f4164i = r11
            r2.f4165j = r0
            r4 = 0
            r2.k = r4
            r2.n = r5
            java.lang.Object r1 = r1.invoke(r11, r2)
            if (r1 != r3) goto L_0x02a6
        L_0x02a5:
            return r3
        L_0x02a6:
            r7 = r0
            r4 = r11
        L_0x02a8:
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture$SurfaceStream$Companion r1 = com.samsung.android.vexfwk.sdk.common.i.f4156i
            com.samsung.android.vexfwk.session.VexFwkStreamInoutDirection r11 = com.samsung.android.vexfwk.session.VexFwkStreamInoutDirection.OUT
            com.samsung.android.vexfwk.sdk.common.i r1 = r1.from(r4, r11)
            r0.add(r1)
            r0 = r7
            goto L_0x026a
        L_0x02b5:
            java.util.List r0 = (java.util.List) r0
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            ne.C1200r.A0(r0, r8)
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.Iterator r0 = r9.iterator()
        L_0x02c2:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x02f2
            java.lang.Object r1 = r0.next()
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture$Stream r1 = (com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture.Stream) r1
            int r11 = r1.getId()
            com.samsung.android.vexfwk.session.VexFwkStreamInoutDirection r12 = r1.getDirection()
            com.samsung.android.vexfwk.session.VexFwkStreamType r13 = r1.getType()
            com.samsung.android.vexfwk.session.VexFwkStreamUsage r14 = r1.getUsage()
            int r15 = r1.getWidth()
            int r16 = r1.getHeight()
            int r17 = r1.getFormat()
            android.view.Surface r18 = r1.getSurface()
            r10.addStream(r11, r12, r13, r14, r15, r16, r17, r18)
            goto L_0x02c2
        L_0x02f2:
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest r0 = r10.build()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture.createSessionConfig(com.samsung.android.vexfwk.sdk.common.h, qe.c):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final void onCompletedCallback$lambda$0(VexFwkHelperConfigurationFuture vexFwkHelperConfigurationFuture) {
        Object unused = D.r(C1233i.d, new Zf.a(vexFwkHelperConfigurationFuture, (C1227c) null, 6));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0043, code lost:
        if (r7 == r1) goto L_0x005d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object awaitSession(com.samsung.android.vexfwk.session.VexFwkUsecase r6, qe.C1227c r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.samsung.android.vexfwk.sdk.common.j
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.samsung.android.vexfwk.sdk.common.j r0 = (com.samsung.android.vexfwk.sdk.common.j) r0
            int r1 = r0.g
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.g = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.vexfwk.sdk.common.j r0 = new com.samsung.android.vexfwk.sdk.common.j
            r0.<init>(r5, r7)
        L_0x0018:
            java.lang.Object r7 = r0.e
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.g
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0038
            if (r2 == r4) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            L2.a.A(r7)
            return r7
        L_0x002a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0032:
            com.samsung.android.vexfwk.session.VexFwkUsecase r6 = r0.d
            L2.a.A(r7)
            goto L_0x0046
        L_0x0038:
            L2.a.A(r7)
            r0.d = r6
            r0.g = r4
            java.lang.Object r7 = r5.awaitConfigMap(r0)
            if (r7 != r1) goto L_0x0046
            goto L_0x005d
        L_0x0046:
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r5 = r7.get(r6)
            if (r5 == 0) goto L_0x005f
            com.samsung.android.vexfwk.sdk.common.h r5 = (com.samsung.android.vexfwk.sdk.common.h) r5
            Vf.r r5 = r5.f
            r6 = 0
            r0.d = r6
            r0.g = r3
            java.lang.Object r5 = r5.m(r0)
            if (r5 != r1) goto L_0x005e
        L_0x005d:
            return r1
        L_0x005e:
            return r5
        L_0x005f:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r7 = "No session found for usecase "
            r5.<init>(r7)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r5 = r5.toString()
            r6.<init>(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture.awaitSession(com.samsung.android.vexfwk.session.VexFwkUsecase, qe.c):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004f, code lost:
        if (r11 == r1) goto L_0x00db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0063, code lost:
        if (r11 != r1) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00d9, code lost:
        if (r11 == r1) goto L_0x00db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00db, code lost:
        return r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object execute(qe.C1227c r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof com.samsung.android.vexfwk.sdk.common.m
            if (r0 == 0) goto L_0x0013
            r0 = r11
            com.samsung.android.vexfwk.sdk.common.m r0 = (com.samsung.android.vexfwk.sdk.common.m) r0
            int r1 = r0.g
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.g = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.vexfwk.sdk.common.m r0 = new com.samsung.android.vexfwk.sdk.common.m
            r0.<init>(r10, r11)
        L_0x0018:
            java.lang.Object r11 = r0.e
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.g
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0044
            if (r2 == r5) goto L_0x003e
            if (r2 == r4) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture r10 = r0.d
            L2.a.A(r11)
            goto L_0x00dc
        L_0x0030:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0038:
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture r10 = r0.d
            L2.a.A(r11)
            goto L_0x0067
        L_0x003e:
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture r10 = r0.d
            L2.a.A(r11)
            goto L_0x0053
        L_0x0044:
            L2.a.A(r11)
            r0.d = r10
            r0.g = r5
            java.lang.Object r11 = r10.awaitConfigMap(r0)
            if (r11 != r1) goto L_0x0053
            goto L_0x00db
        L_0x0053:
            java.util.Map r11 = (java.util.Map) r11
            com.samsung.android.vexfwk.sdk.common.n r2 = new com.samsung.android.vexfwk.sdk.common.n
            r6 = 0
            r2.<init>(r10, r6)
            r0.d = r10
            r0.g = r4
            java.lang.Object r11 = com.samsung.android.vexfwk.extensions.CollectionsKt.asyncEach(r11, (Ae.c) r2, (qe.C1227c) r0)
            if (r11 != r1) goto L_0x0067
            goto L_0x00db
        L_0x0067:
            java.util.Collection r11 = (java.util.Collection) r11
            r0.d = r10
            r0.g = r3
            boolean r2 = r11.isEmpty()
            if (r2 == 0) goto L_0x0076
            ne.t r11 = ne.C1202t.d
            goto L_0x00d9
        L_0x0076:
            Vf.e r2 = new Vf.e
            r3 = 0
            Vf.G[] r4 = new Vf.G[r3]
            java.lang.Object[] r11 = r11.toArray(r4)
            Vf.G[] r11 = (Vf.G[]) r11
            r2.<init>(r11)
            Vf.l r4 = new Vf.l
            qe.c r0 = L1.d.m(r0)
            r4.<init>(r5, r0)
            r4.r()
            int r0 = r11.length
            Vf.c[] r6 = new Vf.C0862c[r0]
            r7 = r3
        L_0x0094:
            if (r7 >= r0) goto L_0x00ae
            r8 = r11[r7]
            r9 = r8
            Vf.n0 r9 = (Vf.n0) r9
            r9.Q()
            Vf.c r9 = new Vf.c
            r9.<init>(r2, r4)
            Vf.O r8 = Vf.D.m(r8, r5, r9)
            r9.f3852i = r8
            r6[r7] = r9
            int r7 = r7 + 1
            goto L_0x0094
        L_0x00ae:
            Vf.d r11 = new Vf.d
            r11.<init>(r6)
        L_0x00b3:
            if (r3 >= r0) goto L_0x00c2
            r2 = r6[r3]
            r2.getClass()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = Vf.C0862c.k
            r5.set(r2, r11)
            int r3 = r3 + 1
            goto L_0x00b3
        L_0x00c2:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = Vf.C0875l.f3865j
            java.lang.Object r0 = r0.get(r4)
            boolean r0 = r0 instanceof Vf.s0
            if (r0 != 0) goto L_0x00d0
            r11.b()
            goto L_0x00d3
        L_0x00d0:
            r4.u(r11)
        L_0x00d3:
            java.lang.Object r11 = r4.q()
            re.a r0 = re.C1245a.COROUTINE_SUSPENDED
        L_0x00d9:
            if (r11 != r1) goto L_0x00dc
        L_0x00db:
            return r1
        L_0x00dc:
            T r10 = r10.helper
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture.execute(qe.c):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004d, code lost:
        if (r7 == r1) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0082, code lost:
        if (r7 == r1) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0084, code lost:
        return r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getAllSessions(qe.C1227c r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.samsung.android.vexfwk.sdk.common.o
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.samsung.android.vexfwk.sdk.common.o r0 = (com.samsung.android.vexfwk.sdk.common.o) r0
            int r1 = r0.f4170i
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f4170i = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.vexfwk.sdk.common.o r0 = new com.samsung.android.vexfwk.sdk.common.o
            r0.<init>(r6, r7)
        L_0x0018:
            java.lang.Object r7 = r0.g
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.f4170i
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0042
            if (r2 == r4) goto L_0x003c
            if (r2 != r3) goto L_0x0034
            java.util.Iterator r6 = r0.f
            java.util.Collection r2 = r0.e
            java.util.Collection r2 = (java.util.Collection) r2
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture r4 = r0.d
            L2.a.A(r7)     // Catch:{ all -> 0x0032 }
            goto L_0x0085
        L_0x0032:
            r7 = move-exception
            goto L_0x0088
        L_0x0034:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003c:
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture r6 = r0.d
            L2.a.A(r7)
            goto L_0x0050
        L_0x0042:
            L2.a.A(r7)
            r0.d = r6
            r0.f4170i = r4
            java.lang.Object r7 = r6.awaitConfigMap(r0)
            if (r7 != r1) goto L_0x0050
            goto L_0x0084
        L_0x0050:
            java.util.Map r7 = (java.util.Map) r7
            java.util.Collection r7 = r7.values()
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r7 = r7.iterator()
            r4 = r6
            r6 = r7
        L_0x0063:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0099
            java.lang.Object r7 = r6.next()
            com.samsung.android.vexfwk.sdk.common.h r7 = (com.samsung.android.vexfwk.sdk.common.h) r7
            Vf.r r7 = r7.f     // Catch:{ all -> 0x0032 }
            r0.d = r4     // Catch:{ all -> 0x0032 }
            r5 = r2
            java.util.Collection r5 = (java.util.Collection) r5     // Catch:{ all -> 0x0032 }
            r0.e = r5     // Catch:{ all -> 0x0032 }
            r0.f = r6     // Catch:{ all -> 0x0032 }
            r0.f4170i = r3     // Catch:{ all -> 0x0032 }
            java.lang.Object r7 = r7.m(r0)     // Catch:{ all -> 0x0032 }
            re.a r5 = re.C1245a.COROUTINE_SUSPENDED     // Catch:{ all -> 0x0032 }
            if (r7 != r1) goto L_0x0085
        L_0x0084:
            return r1
        L_0x0085:
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r7 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession) r7     // Catch:{ all -> 0x0032 }
            goto L_0x008c
        L_0x0088:
            me.j r7 = L2.a.l(r7)
        L_0x008c:
            boolean r5 = r7 instanceof me.j
            if (r5 == 0) goto L_0x0091
            r7 = 0
        L_0x0091:
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r7 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession) r7
            if (r7 == 0) goto L_0x0063
            r2.add(r7)
            goto L_0x0063
        L_0x0099:
            java.util.List r2 = (java.util.List) r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture.getAllSessions(qe.c):java.lang.Object");
    }

    public VexFwkHelperBaseFuture.OnCompletedCallback getOnCompletedCallback() {
        return this.onCompletedCallback;
    }
}
