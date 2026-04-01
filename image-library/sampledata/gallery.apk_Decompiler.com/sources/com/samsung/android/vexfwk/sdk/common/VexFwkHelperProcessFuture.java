package com.samsung.android.vexfwk.sdk.common;

import Ae.c;
import Vf.A;
import Vf.D;
import android.util.Log;
import com.samsung.android.sdk.scs.ai.language.a;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperBaseFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import qe.C1227c;
import qe.C1233i;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 2*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002:\u00022\u0013Bg\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00122\u0010\u000b\u001a.\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005j\b\u0012\u0004\u0012\u00028\u0000`\n\u0012\"\u0010\u000e\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\u0004\b\u000f\u0010\u0010JN\u0010\u0017\u001a@\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u0012\u00120\u0012.\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005j\b\u0012\u0004\u0012\u00028\u0000`\u00160\u0011H@¢\u0006\u0004\b\u0017\u0010\u0018J\u0018\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0013H@¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\bH\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00028\u0000H@¢\u0006\u0004\b\u001e\u0010\u0018J\u0017\u0010!\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0016¢\u0006\u0004\b!\u0010\"J\u000f\u0010#\u001a\u00020\bH\u0004¢\u0006\u0004\b#\u0010\u001dR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010$R@\u0010\u000b\u001a.\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005j\b\u0012\u0004\u0012\u00028\u0000`\n8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010%R0\u0010\u000e\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00058\u0002X\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010%R\u0014\u0010'\u001a\u00020&8\u0002X\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u0018\u0010*\u001a\u0004\u0018\u00010)8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b*\u0010+R\u0018\u0010,\u001a\u0004\u0018\u00010)8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b,\u0010+R\u001a\u0010.\u001a\u00020-8\u0014X\u0004¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b0\u00101¨\u00063"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperProcessFuture;", "T", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBaseFuture;", "LVf/A;", "coroutineScope", "Lkotlin/Function2;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperProcess;", "Lqe/c;", "Lme/x;", "", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperProcessInitializer;", "processInitializer", "Lcom/samsung/android/vexfwk/session/VexFwkUsecase;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSession;", "sessionSupplier", "<init>", "(LVf/A;LAe/c;LAe/c;)V", "Lme/i;", "", "Lcom/samsung/android/vexfwk/sdk/common/p;", "Lme/k;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperFinally;", "buildProcess", "(Lqe/c;)Ljava/lang/Object;", "task", "executeTask", "(Lcom/samsung/android/vexfwk/sdk/common/p;Lqe/c;)Ljava/lang/Object;", "release", "()V", "execute", "", "mayInterruptIfRunning", "cancel", "(Z)Z", "finalize", "LVf/A;", "LAe/c;", "Lfg/a;", "taskMutex", "Lfg/a;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperProcessTask;", "currentTask", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperProcessTask;", "previousTask", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBaseFuture$OnCompletedCallback;", "onCompletedCallback", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBaseFuture$OnCompletedCallback;", "getOnCompletedCallback", "()Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBaseFuture$OnCompletedCallback;", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkHelperProcessFuture<T> extends VexFwkHelperBaseFuture<T> {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG = "VexFwkHelperProcessFuture";
    private final A coroutineScope;
    /* access modifiers changed from: private */
    public VexFwkHelperProcessTask currentTask;
    private final VexFwkHelperBaseFuture.OnCompletedCallback onCompletedCallback = new a(9, this);
    /* access modifiers changed from: private */
    public VexFwkHelperProcessTask previousTask;
    private final c processInitializer;
    private final c sessionSupplier;
    /* access modifiers changed from: private */
    public final fg.a taskMutex = new fg.c();

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperProcessFuture$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/String;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VexFwkHelperProcessFuture(A a7, c cVar, c cVar2) {
        super(a7);
        j.e(a7, "coroutineScope");
        j.e(cVar, "processInitializer");
        j.e(cVar2, "sessionSupplier");
        this.coroutineScope = a7;
        this.processInitializer = cVar;
        this.sessionSupplier = cVar2;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object buildProcess(qe.C1227c r15) {
        /*
            r14 = this;
            boolean r0 = r15 instanceof com.samsung.android.vexfwk.sdk.common.q
            if (r0 == 0) goto L_0x0013
            r0 = r15
            com.samsung.android.vexfwk.sdk.common.q r0 = (com.samsung.android.vexfwk.sdk.common.q) r0
            int r1 = r0.k
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.k = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.vexfwk.sdk.common.q r0 = new com.samsung.android.vexfwk.sdk.common.q
            r0.<init>(r14, r15)
        L_0x0018:
            java.lang.Object r15 = r0.f4174i
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.k
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0047
            if (r2 == r4) goto L_0x0041
            if (r2 != r3) goto L_0x0039
            java.util.Collection r14 = r0.f4173h
            java.util.Collection r14 = (java.util.Collection) r14
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcess$Request r2 = r0.g
            java.util.Iterator r4 = r0.f
            java.util.Collection r5 = r0.e
            java.util.Collection r5 = (java.util.Collection) r5
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcess r6 = r0.d
            L2.a.A(r15)
            goto L_0x00b8
        L_0x0039:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r15)
            throw r14
        L_0x0041:
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcess r14 = r0.d
            L2.a.A(r15)
            goto L_0x0064
        L_0x0047:
            L2.a.A(r15)
            qe.h r15 = r0.getContext()
            Vf.D.f(r15)
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcess r15 = new com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcess
            r15.<init>()
            Ae.c r14 = r14.processInitializer
            r0.d = r15
            r0.k = r4
            java.lang.Object r14 = r14.invoke(r15, r0)
            if (r14 != r1) goto L_0x0063
            goto L_0x00b5
        L_0x0063:
            r14 = r15
        L_0x0064:
            java.util.List r15 = r14.getRequests()
            java.util.Collection r15 = (java.util.Collection) r15
            boolean r15 = r15.isEmpty()
            if (r15 != 0) goto L_0x0103
            java.util.List r15 = r14.getRequests()
            java.lang.Iterable r15 = (java.lang.Iterable) r15
            java.util.ArrayList r2 = new java.util.ArrayList
            r4 = 10
            int r4 = ne.C1196n.w0(r15, r4)
            r2.<init>(r4)
            java.util.Iterator r15 = r15.iterator()
            r6 = r14
            r4 = r15
            r14 = r2
        L_0x0088:
            boolean r15 = r4.hasNext()
            if (r15 == 0) goto L_0x00ed
            java.lang.Object r15 = r4.next()
            Ae.c r15 = (Ae.c) r15
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcess$Request r7 = new com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcess$Request
            r12 = 15
            r13 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r7.<init>(r8, r9, r10, r11, r12, r13)
            r0.d = r6
            r2 = r14
            java.util.Collection r2 = (java.util.Collection) r2
            r0.e = r2
            r0.f = r4
            r0.g = r7
            r0.f4173h = r2
            r0.k = r3
            java.lang.Object r15 = r15.invoke(r7, r0)
            if (r15 != r1) goto L_0x00b6
        L_0x00b5:
            return r1
        L_0x00b6:
            r5 = r14
            r2 = r7
        L_0x00b8:
            com.samsung.android.vexfwk.session.VexFwkUsecase r15 = r2.getUsecase()
            if (r15 == 0) goto L_0x00e5
            qe.h r7 = r0.getContext()
            Vf.D.f(r7)
            Ae.d r7 = r2.getOnRequestSetup()
            if (r7 == 0) goto L_0x00dd
            com.samsung.android.vexfwk.sdk.common.p r8 = new com.samsung.android.vexfwk.sdk.common.p
            Ae.c r9 = r2.getOnRequestProgressed()
            Ae.c r2 = r2.getOnRequestCompleted()
            r8.<init>(r15, r7, r9, r2)
            r14.add(r8)
            r14 = r5
            goto L_0x0088
        L_0x00dd:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "No onRequestSetup provided. Please provide a valid onRequestSetup."
            r14.<init>(r15)
            throw r14
        L_0x00e5:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "No usecase provided. Please provide a valid usecase."
            r14.<init>(r15)
            throw r14
        L_0x00ed:
            java.util.List r14 = (java.util.List) r14
            Ae.c r15 = r6.getFinally()
            if (r15 == 0) goto L_0x00fb
            me.i r0 = new me.i
            r0.<init>(r14, r15)
            return r0
        L_0x00fb:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "No finally action provided. Please provide a valid finally action."
            r14.<init>(r15)
            throw r14
        L_0x0103:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "No requests to process. Please provide at least one request."
            r14.<init>(r15)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessFuture.buildProcess(qe.c):java.lang.Object");
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: fg.a} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: fg.c} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v22, resolved type: fg.a} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: fg.c} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d9, code lost:
        if (r0 == r4) goto L_0x0141;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0104, code lost:
        if (r2.setupRequest(r0, r3) == r4) goto L_0x0141;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0118, code lost:
        if (r0 == r4) goto L_0x0141;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x013f, code lost:
        if (r2.processRequest(r3) == r4) goto L_0x0141;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object executeTask(com.samsung.android.vexfwk.sdk.common.p r20, qe.C1227c r21) {
        /*
            r19 = this;
            r1 = r19
            r2 = r20
            r0 = r21
            boolean r3 = r0 instanceof com.samsung.android.vexfwk.sdk.common.t
            if (r3 == 0) goto L_0x0019
            r3 = r0
            com.samsung.android.vexfwk.sdk.common.t r3 = (com.samsung.android.vexfwk.sdk.common.t) r3
            int r4 = r3.f4180i
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = r4 & r5
            if (r6 == 0) goto L_0x0019
            int r4 = r4 - r5
            r3.f4180i = r4
            goto L_0x001e
        L_0x0019:
            com.samsung.android.vexfwk.sdk.common.t r3 = new com.samsung.android.vexfwk.sdk.common.t
            r3.<init>(r1, r0)
        L_0x001e:
            java.lang.Object r0 = r3.g
            re.a r4 = re.C1245a.COROUTINE_SUSPENDED
            int r5 = r3.f4180i
            r6 = 5
            r7 = 4
            r8 = 3
            r9 = 2
            r10 = 1
            r11 = 0
            if (r5 == 0) goto L_0x0080
            if (r5 == r10) goto L_0x006a
            if (r5 == r9) goto L_0x005d
            if (r5 == r8) goto L_0x0050
            if (r5 == r7) goto L_0x0043
            if (r5 != r6) goto L_0x003b
            L2.a.A(r0)
            goto L_0x0142
        L_0x003b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0043:
            fg.a r1 = r3.f
            java.lang.Object r2 = r3.e
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessTask r2 = (com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessTask) r2
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessFuture r5 = r3.d
            L2.a.A(r0)
            goto L_0x011b
        L_0x0050:
            java.lang.Object r1 = r3.e
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessTask r1 = (com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessTask) r1
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessFuture r2 = r3.d
            L2.a.A(r0)
            r5 = r2
            r2 = r1
            goto L_0x0107
        L_0x005d:
            fg.a r1 = r3.f
            java.lang.Object r2 = r3.e
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessTask r2 = (com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessTask) r2
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessFuture r5 = r3.d
            L2.a.A(r0)
            goto L_0x00dc
        L_0x006a:
            java.lang.Object r1 = r3.e
            com.samsung.android.vexfwk.sdk.common.p r1 = (com.samsung.android.vexfwk.sdk.common.p) r1
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessFuture r2 = r3.d
            L2.a.A(r0)     // Catch:{ all -> 0x0079 }
            r18 = r2
            r2 = r1
            r1 = r18
            goto L_0x0095
        L_0x0079:
            r0 = move-exception
            r18 = r2
            r2 = r1
            r1 = r18
            goto L_0x009a
        L_0x0080:
            L2.a.A(r0)
            Ae.c r0 = r1.sessionSupplier     // Catch:{ all -> 0x0099 }
            com.samsung.android.vexfwk.session.VexFwkUsecase r5 = r2.f4171a     // Catch:{ all -> 0x0099 }
            r3.d = r1     // Catch:{ all -> 0x0099 }
            r3.e = r2     // Catch:{ all -> 0x0099 }
            r3.f4180i = r10     // Catch:{ all -> 0x0099 }
            java.lang.Object r0 = r0.invoke(r5, r3)     // Catch:{ all -> 0x0099 }
            if (r0 != r4) goto L_0x0095
            goto L_0x0141
        L_0x0095:
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r0 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession) r0     // Catch:{ all -> 0x0099 }
        L_0x0097:
            r5 = r1
            goto L_0x009f
        L_0x0099:
            r0 = move-exception
        L_0x009a:
            me.j r0 = L2.a.l(r0)
            goto L_0x0097
        L_0x009f:
            java.lang.Throwable r1 = me.k.a(r0)
            if (r1 != 0) goto L_0x015a
            r13 = r0
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r13 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession) r13
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessTask r12 = new com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessTask     // Catch:{ all -> 0x00ba }
            Ae.d r14 = r2.b     // Catch:{ all -> 0x00ba }
            Ae.c r15 = r2.f4172c     // Catch:{ all -> 0x00ba }
            Ae.c r0 = r2.d     // Catch:{ all -> 0x00ba }
            Vf.A r1 = r5.coroutineScope     // Catch:{ all -> 0x00ba }
            r16 = r0
            r17 = r1
            r12.<init>(r13, r14, r15, r16, r17)     // Catch:{ all -> 0x00ba }
            goto L_0x00bf
        L_0x00ba:
            r0 = move-exception
            me.j r12 = L2.a.l(r0)
        L_0x00bf:
            java.lang.Throwable r0 = me.k.a(r12)
            if (r0 != 0) goto L_0x0152
            r2 = r12
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessTask r2 = (com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessTask) r2
            fg.a r0 = r5.taskMutex
            r3.d = r5
            r3.e = r2
            r3.f = r0
            r3.f4180i = r9
            r1 = r0
            fg.c r1 = (fg.c) r1
            java.lang.Object r0 = r1.c(r3)
            if (r0 != r4) goto L_0x00dc
            goto L_0x0141
        L_0x00dc:
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessTask r0 = r5.currentTask     // Catch:{ all -> 0x014b }
            r5.previousTask = r0     // Catch:{ all -> 0x014b }
            r5.currentTask = r2     // Catch:{ all -> 0x014b }
            fg.c r1 = (fg.c) r1
            r1.d(r11)
            qe.h r0 = r3.getContext()
            Vf.D.f(r0)
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessTask r0 = r5.previousTask
            if (r0 == 0) goto L_0x00f7
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest r0 = r0.getRequest()
            goto L_0x00f8
        L_0x00f7:
            r0 = r11
        L_0x00f8:
            r3.d = r5
            r3.e = r2
            r3.f = r11
            r3.f4180i = r8
            java.lang.Object r0 = r2.setupRequest(r0, r3)
            if (r0 != r4) goto L_0x0107
            goto L_0x0141
        L_0x0107:
            fg.a r0 = r5.taskMutex
            r3.d = r5
            r3.e = r2
            r3.f = r0
            r3.f4180i = r7
            r1 = r0
            fg.c r1 = (fg.c) r1
            java.lang.Object r0 = r1.c(r3)
            if (r0 != r4) goto L_0x011b
            goto L_0x0141
        L_0x011b:
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessTask r0 = r5.previousTask     // Catch:{ all -> 0x0123 }
            if (r0 == 0) goto L_0x0125
            r0.close()     // Catch:{ all -> 0x0123 }
            goto L_0x0125
        L_0x0123:
            r0 = move-exception
            goto L_0x0145
        L_0x0125:
            r5.previousTask = r11     // Catch:{ all -> 0x0123 }
            fg.c r1 = (fg.c) r1
            r1.d(r11)
            qe.h r0 = r3.getContext()
            Vf.D.f(r0)
            r3.d = r11
            r3.e = r11
            r3.f = r11
            r3.f4180i = r6
            java.lang.Object r0 = r2.processRequest(r3)
            if (r0 != r4) goto L_0x0142
        L_0x0141:
            return r4
        L_0x0142:
            me.x r0 = me.x.f4917a
            return r0
        L_0x0145:
            fg.c r1 = (fg.c) r1
            r1.d(r11)
            throw r0
        L_0x014b:
            r0 = move-exception
            fg.c r1 = (fg.c) r1
            r1.d(r11)
            throw r0
        L_0x0152:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Failed to create task."
            r1.<init>(r2, r0)
            throw r1
        L_0x015a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "Failed to get session from supplier."
            r0.<init>(r2, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessFuture.executeTask(com.samsung.android.vexfwk.sdk.common.p, qe.c):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final void onCompletedCallback$lambda$0(VexFwkHelperProcessFuture vexFwkHelperProcessFuture) {
        vexFwkHelperProcessFuture.release();
    }

    private final void release() {
        Object unused = D.r(C1233i.d, new r(this, (C1227c) null, 1));
    }

    public boolean cancel(boolean z) {
        String str = TAG;
        Log.i(str, "canceling process future... mayInterruptIfRunning=" + z);
        boolean cancel = super.cancel(false);
        Object unused = D.r(C1233i.d, new r(this, (C1227c) null, 0));
        return cancel;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0050, code lost:
        if (r8 == r1) goto L_0x00b1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0088 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object execute(qe.C1227c r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.samsung.android.vexfwk.sdk.common.s
            if (r0 == 0) goto L_0x0013
            r0 = r8
            com.samsung.android.vexfwk.sdk.common.s r0 = (com.samsung.android.vexfwk.sdk.common.s) r0
            int r1 = r0.f4178i
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f4178i = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.vexfwk.sdk.common.s r0 = new com.samsung.android.vexfwk.sdk.common.s
            r0.<init>(r7, r8)
        L_0x0018:
            java.lang.Object r8 = r0.g
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.f4178i
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0045
            if (r2 == r5) goto L_0x003f
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            L2.a.A(r8)
            return r8
        L_0x002d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0035:
            java.util.Iterator r7 = r0.f
            Ae.c r2 = r0.e
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessFuture r5 = r0.d
            L2.a.A(r8)
            goto L_0x0066
        L_0x003f:
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessFuture r7 = r0.d
            L2.a.A(r8)
            goto L_0x0053
        L_0x0045:
            L2.a.A(r8)
            r0.d = r7
            r0.f4178i = r5
            java.lang.Object r8 = r7.buildProcess(r0)
            if (r8 != r1) goto L_0x0053
            goto L_0x00b1
        L_0x0053:
            me.i r8 = (me.i) r8
            java.lang.Object r2 = r8.d
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r8 = r8.e
            Ae.c r8 = (Ae.c) r8
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.Iterator r2 = r2.iterator()
            r5 = r7
            r7 = r2
            r2 = r8
        L_0x0066:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x0088
            java.lang.Object r8 = r7.next()
            com.samsung.android.vexfwk.sdk.common.p r8 = (com.samsung.android.vexfwk.sdk.common.p) r8
            qe.h r6 = r0.getContext()
            Vf.D.f(r6)
            r0.d = r5
            r0.e = r2
            r0.f = r7
            r0.f4178i = r4
            java.lang.Object r8 = r5.executeTask(r8, r0)
            if (r8 != r1) goto L_0x0066
            goto L_0x00b1
        L_0x0088:
            qe.h r7 = r0.getContext()
            Vf.D.f(r7)
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessTask r7 = r5.currentTask
            r8 = 0
            if (r7 == 0) goto L_0x0099
            me.k r7 = r7.m59getResultxLWZpok()
            goto L_0x009a
        L_0x0099:
            r7 = r8
        L_0x009a:
            if (r7 == 0) goto L_0x00b3
            java.lang.Object r7 = r7.d
            me.k r4 = new me.k
            r4.<init>(r7)
            r0.d = r8
            r0.e = r8
            r0.f = r8
            r0.f4178i = r3
            java.lang.Object r7 = r2.invoke(r4, r0)
            if (r7 != r1) goto L_0x00b2
        L_0x00b1:
            return r1
        L_0x00b2:
            return r7
        L_0x00b3:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "currentTask.result should not be null after all tasks are executed."
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessFuture.execute(qe.c):java.lang.Object");
    }

    public final void finalize() {
        release();
    }

    public VexFwkHelperBaseFuture.OnCompletedCallback getOnCompletedCallback() {
        return this.onCompletedCallback;
    }
}
