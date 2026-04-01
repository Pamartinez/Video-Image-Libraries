package com.samsung.android.vexfwk.sdk.common;

import Ae.c;
import Ae.d;
import Vf.A;
import Vf.D;
import Zf.a;
import com.samsung.android.vexfwk.extensions.CoroutinesKt;
import com.samsung.android.vexfwk.param.VexFwkNodeResultCode;
import com.samsung.android.vexfwk.sdk.common.runtime.IVexFwkSessionCallback;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkResultCode;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionPartialResult;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.k;
import me.x;
import qe.C1227c;
import re.C1245a;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012.\u0010\t\u001a*\b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0002`\b\u0012*\u0010\r\u001a&\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\nj\u0004\u0018\u0001`\f\u0012*\u0010\u0010\u001a&\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\nj\u0004\u0018\u0001`\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0016\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005H@¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0007H@¢\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\u0007¢\u0006\u0004\b\u001a\u0010\u001bJ\r\u0010\u001c\u001a\u00020\u0007¢\u0006\u0004\b\u001c\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR?\u0010\t\u001a*\b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0002`\b8\u0006¢\u0006\f\n\u0004\b\t\u0010 \u001a\u0004\b!\u0010\"R;\u0010\r\u001a&\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\nj\u0004\u0018\u0001`\f8\u0006¢\u0006\f\n\u0004\b\r\u0010#\u001a\u0004\b$\u0010%R;\u0010\u0010\u001a&\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\nj\u0004\u0018\u0001`\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010#\u001a\u0004\b&\u0010%R\u0017\u0010\u0012\u001a\u00020\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010'\u001a\u0004\b(\u0010)R\u0017\u0010*\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R*\u0010/\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010.8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u00104¨\u00065"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperProcessTask;", "", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSession;", "session", "Lkotlin/Function3;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest;", "Lqe/c;", "Lme/x;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperOnRequestSetup;", "onRequestSetup", "Lkotlin/Function2;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionPartialResult;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperOnRequestProgressed;", "onRequestProgressed", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperOnRequestCompleted;", "onRequestCompleted", "LVf/A;", "taskScope", "<init>", "(Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSession;LAe/d;LAe/c;LAe/c;LVf/A;)V", "oldRequest", "setupRequest", "(Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest;Lqe/c;)Ljava/lang/Object;", "processRequest", "(Lqe/c;)Ljava/lang/Object;", "cancel", "()V", "close", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSession;", "getSession", "()Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSession;", "LAe/d;", "getOnRequestSetup", "()LAe/d;", "LAe/c;", "getOnRequestProgressed", "()LAe/c;", "getOnRequestCompleted", "LVf/A;", "getTaskScope", "()LVf/A;", "request", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest;", "getRequest", "()Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest;", "Lme/k;", "result", "Lme/k;", "getResult-xLWZpok", "()Lme/k;", "setResult", "(Lme/k;)V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkHelperProcessTask {
    private final c onRequestCompleted;
    private final c onRequestProgressed;
    private final d onRequestSetup;
    private final VexFwkSessionRequest request;
    private k result;
    private final VexFwkSession session;
    private final A taskScope;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[VexFwkNodeResultCode.values().length];
            try {
                iArr[VexFwkNodeResultCode.PROCESS_FAIL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[VexFwkResultCode.values().length];
            try {
                iArr2[VexFwkResultCode.OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public VexFwkHelperProcessTask(VexFwkSession vexFwkSession, d dVar, c cVar, c cVar2, A a7) {
        j.e(vexFwkSession, "session");
        j.e(dVar, "onRequestSetup");
        j.e(a7, "taskScope");
        this.session = vexFwkSession;
        this.onRequestSetup = dVar;
        this.onRequestProgressed = cVar;
        this.onRequestCompleted = cVar2;
        this.taskScope = a7;
        VexFwkSessionRequest vexFwkSessionRequest = new VexFwkSessionRequest();
        this.request = vexFwkSessionRequest;
        if (cVar != null || cVar2 != null) {
            vexFwkSessionRequest.setCallback(new IVexFwkSessionCallback(this) {
                final /* synthetic */ VexFwkHelperProcessTask this$0;

                {
                    this.this$0 = r1;
                }

                public void onRequestCompleted(VexFwkSessionTotalResult vexFwkSessionTotalResult) {
                    j.e(vexFwkSessionTotalResult, "totalResult");
                    c onRequestCompleted = this.this$0.getOnRequestCompleted();
                    if (onRequestCompleted != null) {
                        CoroutinesKt.asyncAndAwait(this.this$0.getTaskScope(), new a(onRequestCompleted, vexFwkSessionTotalResult, (C1227c) null, 7));
                    }
                }

                public void onRequestProgressed(VexFwkSessionPartialResult vexFwkSessionPartialResult) {
                    j.e(vexFwkSessionPartialResult, "partialResult");
                    c onRequestProgressed = this.this$0.getOnRequestProgressed();
                    if (onRequestProgressed != null) {
                        CoroutinesKt.asyncAndAwait(this.this$0.getTaskScope(), new a(onRequestProgressed, vexFwkSessionPartialResult, (C1227c) null, 8));
                    }
                }
            });
        }
    }

    public final void cancel() {
        this.session.cancelRequest(this.request);
    }

    public final void close() {
        this.request.close();
    }

    public final c getOnRequestCompleted() {
        return this.onRequestCompleted;
    }

    public final c getOnRequestProgressed() {
        return this.onRequestProgressed;
    }

    public final d getOnRequestSetup() {
        return this.onRequestSetup;
    }

    public final VexFwkSessionRequest getRequest() {
        return this.request;
    }

    /* renamed from: getResult-xLWZpok  reason: not valid java name */
    public final k m59getResultxLWZpok() {
        return this.result;
    }

    public final VexFwkSession getSession() {
        return this.session;
    }

    public final A getTaskScope() {
        return this.taskScope;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: me.j} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: me.j} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v9, resolved type: com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object processRequest(qe.C1227c r5) {
        /*
            r4 = this;
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r0 = r4.session
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest r1 = r4.request
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkResultCode r0 = r0.processRequest((com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest) r1)
            int[] r1 = com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessTask.WhenMappings.$EnumSwitchMapping$1
            int r2 = r0.ordinal()
            r1 = r1[r2]
            r2 = 1
            if (r1 != r2) goto L_0x0052
            qe.h r5 = r5.getContext()
            Vf.D.f(r5)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest r5 = r4.request
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult r5 = r5.getTotalResult()
            com.samsung.android.vexfwk.metadata.VexFwkMetadataNative r0 = r5.getResultMetadata()
            com.samsung.android.vexfwk.metadata.VexFwkMetadataKey$RESULT_CODE r1 = com.samsung.android.vexfwk.metadata.VexFwkMetadataKey.RESULT_CODE.INSTANCE
            java.lang.Object r0 = r0.get(r1)
            com.samsung.android.vexfwk.param.VexFwkNodeResultCode r0 = (com.samsung.android.vexfwk.param.VexFwkNodeResultCode) r0
            if (r0 != 0) goto L_0x0030
            r1 = -1
            goto L_0x0038
        L_0x0030:
            int[] r1 = com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessTask.WhenMappings.$EnumSwitchMapping$0
            int r3 = r0.ordinal()
            r1 = r1[r3]
        L_0x0038:
            if (r1 != r2) goto L_0x0069
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Failed to process request. node result code="
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r5.<init>(r0)
            me.j r5 = L2.a.l(r5)
            goto L_0x0069
        L_0x0052:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Failed to process request. result code="
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r5.<init>(r0)
            me.j r5 = L2.a.l(r5)
        L_0x0069:
            me.k r0 = new me.k
            r0.<init>(r5)
            r4.result = r0
            me.x r4 = me.x.f4917a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessTask.processRequest(qe.c):java.lang.Object");
    }

    public final void setResult(k kVar) {
        this.result = kVar;
    }

    public final Object setupRequest(VexFwkSessionRequest vexFwkSessionRequest, C1227c cVar) {
        D.f(cVar.getContext());
        Object invoke = this.onRequestSetup.invoke(vexFwkSessionRequest, this.request, cVar);
        if (invoke == C1245a.COROUTINE_SUSPENDED) {
            return invoke;
        }
        return x.f4917a;
    }
}
