package com.samsung.android.vexfwk.sdk.common;

import Ae.c;
import Ae.d;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u001cB\u0007¢\u0006\u0004\b\u0003\u0010\u0004R=\u0010\u000b\u001a(\u0012$\u0012\"\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006j\u0002`\n0\u00058\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eRR\u0010\u0012\u001a2\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u00118\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017Rd\u0010\u001b\u001a\"\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006j\u0002`\n2&\u0010\u0018\u001a\"\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006j\u0002`\n8F@FX\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017¨\u0006\u001d"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperProcess;", "T", "", "<init>", "()V", "", "Lkotlin/Function2;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperProcess$Request;", "Lqe/c;", "Lme/x;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperRequestInitializer;", "requests", "Ljava/util/List;", "getRequests", "()Ljava/util/List;", "Lme/k;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperFinally;", "finally", "LAe/c;", "getFinally", "()LAe/c;", "setFinally", "(LAe/c;)V", "value", "getRequest", "setRequest", "request", "Request", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkHelperProcess<T> {

    /* renamed from: finally  reason: not valid java name */
    private c f0finally;
    private final List<c> requests = new ArrayList();

    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0011\b\b\u0018\u00002\u00020\u0001B¥\u0001\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00124\b\u0002\u0010\t\u001a.\b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0004j\u0004\u0018\u0001`\b\u0012,\b\u0002\u0010\r\u001a&\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\nj\u0004\u0018\u0001`\f\u0012,\b\u0002\u0010\u0010\u001a&\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\nj\u0004\u0018\u0001`\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J<\u0010\u0015\u001a.\b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0004j\u0004\u0018\u0001`\bHÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J4\u0010\u0017\u001a&\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\nj\u0004\u0018\u0001`\fHÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J4\u0010\u0019\u001a&\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\nj\u0004\u0018\u0001`\u000fHÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0018J®\u0001\u0010\u001a\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u000224\b\u0002\u0010\t\u001a.\b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0004j\u0004\u0018\u0001`\b2,\b\u0002\u0010\r\u001a&\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\nj\u0004\u0018\u0001`\f2,\b\u0002\u0010\u0010\u001a&\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\nj\u0004\u0018\u0001`\u000fHÆ\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001d\u001a\u00020\u001cHÖ\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010 \u001a\u00020\u001fHÖ\u0001¢\u0006\u0004\b \u0010!J\u001a\u0010$\u001a\u00020#2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b$\u0010%R$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010&\u001a\u0004\b'\u0010\u0014\"\u0004\b(\u0010)RN\u0010\t\u001a.\b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0004j\u0004\u0018\u0001`\b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\t\u0010*\u001a\u0004\b+\u0010\u0016\"\u0004\b,\u0010-RF\u0010\r\u001a&\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\nj\u0004\u0018\u0001`\f8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\r\u0010.\u001a\u0004\b/\u0010\u0018\"\u0004\b0\u00101RF\u0010\u0010\u001a&\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\nj\u0004\u0018\u0001`\u000f8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010.\u001a\u0004\b2\u0010\u0018\"\u0004\b3\u00101¨\u00064"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperProcess$Request;", "", "Lcom/samsung/android/vexfwk/session/VexFwkUsecase;", "usecase", "Lkotlin/Function3;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest;", "Lqe/c;", "Lme/x;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperOnRequestSetup;", "onRequestSetup", "Lkotlin/Function2;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionPartialResult;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperOnRequestProgressed;", "onRequestProgressed", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperOnRequestCompleted;", "onRequestCompleted", "<init>", "(Lcom/samsung/android/vexfwk/session/VexFwkUsecase;LAe/d;LAe/c;LAe/c;)V", "component1", "()Lcom/samsung/android/vexfwk/session/VexFwkUsecase;", "component2", "()LAe/d;", "component3", "()LAe/c;", "component4", "copy", "(Lcom/samsung/android/vexfwk/session/VexFwkUsecase;LAe/d;LAe/c;LAe/c;)Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperProcess$Request;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lcom/samsung/android/vexfwk/session/VexFwkUsecase;", "getUsecase", "setUsecase", "(Lcom/samsung/android/vexfwk/session/VexFwkUsecase;)V", "LAe/d;", "getOnRequestSetup", "setOnRequestSetup", "(LAe/d;)V", "LAe/c;", "getOnRequestProgressed", "setOnRequestProgressed", "(LAe/c;)V", "getOnRequestCompleted", "setOnRequestCompleted", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Request {
        private c onRequestCompleted;
        private c onRequestProgressed;
        private d onRequestSetup;
        private VexFwkUsecase usecase;

        public Request() {
            this((VexFwkUsecase) null, (d) null, (c) null, (c) null, 15, (e) null);
        }

        public static /* synthetic */ Request copy$default(Request request, VexFwkUsecase vexFwkUsecase, d dVar, c cVar, c cVar2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                vexFwkUsecase = request.usecase;
            }
            if ((i2 & 2) != 0) {
                dVar = request.onRequestSetup;
            }
            if ((i2 & 4) != 0) {
                cVar = request.onRequestProgressed;
            }
            if ((i2 & 8) != 0) {
                cVar2 = request.onRequestCompleted;
            }
            return request.copy(vexFwkUsecase, dVar, cVar, cVar2);
        }

        public final VexFwkUsecase component1() {
            return this.usecase;
        }

        public final d component2() {
            return this.onRequestSetup;
        }

        public final c component3() {
            return this.onRequestProgressed;
        }

        public final c component4() {
            return this.onRequestCompleted;
        }

        public final Request copy(VexFwkUsecase vexFwkUsecase, d dVar, c cVar, c cVar2) {
            return new Request(vexFwkUsecase, dVar, cVar, cVar2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Request)) {
                return false;
            }
            Request request = (Request) obj;
            if (this.usecase == request.usecase && j.a(this.onRequestSetup, request.onRequestSetup) && j.a(this.onRequestProgressed, request.onRequestProgressed) && j.a(this.onRequestCompleted, request.onRequestCompleted)) {
                return true;
            }
            return false;
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

        public final VexFwkUsecase getUsecase() {
            return this.usecase;
        }

        public int hashCode() {
            int i2;
            int i7;
            int i8;
            VexFwkUsecase vexFwkUsecase = this.usecase;
            int i10 = 0;
            if (vexFwkUsecase == null) {
                i2 = 0;
            } else {
                i2 = vexFwkUsecase.hashCode();
            }
            int i11 = i2 * 31;
            d dVar = this.onRequestSetup;
            if (dVar == null) {
                i7 = 0;
            } else {
                i7 = dVar.hashCode();
            }
            int i12 = (i11 + i7) * 31;
            c cVar = this.onRequestProgressed;
            if (cVar == null) {
                i8 = 0;
            } else {
                i8 = cVar.hashCode();
            }
            int i13 = (i12 + i8) * 31;
            c cVar2 = this.onRequestCompleted;
            if (cVar2 != null) {
                i10 = cVar2.hashCode();
            }
            return i13 + i10;
        }

        public final void setOnRequestCompleted(c cVar) {
            this.onRequestCompleted = cVar;
        }

        public final void setOnRequestProgressed(c cVar) {
            this.onRequestProgressed = cVar;
        }

        public final void setOnRequestSetup(d dVar) {
            this.onRequestSetup = dVar;
        }

        public final void setUsecase(VexFwkUsecase vexFwkUsecase) {
            this.usecase = vexFwkUsecase;
        }

        public String toString() {
            VexFwkUsecase vexFwkUsecase = this.usecase;
            d dVar = this.onRequestSetup;
            c cVar = this.onRequestProgressed;
            c cVar2 = this.onRequestCompleted;
            return "Request(usecase=" + vexFwkUsecase + ", onRequestSetup=" + dVar + ", onRequestProgressed=" + cVar + ", onRequestCompleted=" + cVar2 + ")";
        }

        public Request(VexFwkUsecase vexFwkUsecase, d dVar, c cVar, c cVar2) {
            this.usecase = vexFwkUsecase;
            this.onRequestSetup = dVar;
            this.onRequestProgressed = cVar;
            this.onRequestCompleted = cVar2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Request(VexFwkUsecase vexFwkUsecase, d dVar, c cVar, c cVar2, int i2, e eVar) {
            this((i2 & 1) != 0 ? null : vexFwkUsecase, (i2 & 2) != 0 ? null : dVar, (i2 & 4) != 0 ? null : cVar, (i2 & 8) != 0 ? null : cVar2);
        }
    }

    public final c getFinally() {
        return this.f0finally;
    }

    public final c getRequest() {
        return (c) C1194l.T0(this.requests);
    }

    public final List<c> getRequests() {
        return this.requests;
    }

    public final void setFinally(c cVar) {
        this.f0finally = cVar;
    }

    public final void setRequest(c cVar) {
        j.e(cVar, "value");
        this.requests.add(cVar);
    }
}
