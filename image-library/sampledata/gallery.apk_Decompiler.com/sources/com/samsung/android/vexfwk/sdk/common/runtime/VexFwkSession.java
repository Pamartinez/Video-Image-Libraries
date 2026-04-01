package com.samsung.android.vexfwk.sdk.common.runtime;

import L2.a;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 )2\u00060\u0001j\u0002`\u0002:\u0001)B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0012\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u0013¢\u0006\u0004\b\u0012\u0010\u0015J\u0015\u0010\u0012\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u0016¢\u0006\u0004\b\u0012\u0010\u0017J\u0015\u0010\u0018\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\t¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\tH\u0016¢\u0006\u0004\b\u001c\u0010\u001bR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0016\u0010!\u001a\u00020 8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\b\u0010#R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020%0$8F¢\u0006\u0006\u001a\u0004\b&\u0010'¨\u0006*"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSession;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "Lcom/samsung/android/vexfwk/session/VexFwkUsecase;", "usecase", "<init>", "(Lcom/samsung/android/vexfwk/session/VexFwkUsecase;)V", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionConfigRequest;", "configRequest", "Lme/x;", "configure", "(Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionConfigRequest;)V", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Sync;", "request", "Lme/k;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;", "processRequest-IoAF18A", "(Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Sync;)Ljava/lang/Object;", "processRequest", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Async;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkResultCode;", "(Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Async;)Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkResultCode;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest;", "(Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest;)Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkResultCode;", "cancelRequest", "(Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest;)V", "flush", "()V", "close", "Lcom/samsung/android/vexfwk/session/VexFwkUsecase;", "getUsecase", "()Lcom/samsung/android/vexfwk/session/VexFwkUsecase;", "", "nativeHandle", "J", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionConfigRequest;", "", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkStream;", "getStreams", "()Ljava/util/List;", "streams", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkSession implements AutoCloseable {
    public static final Companion Companion = new Companion((e) null);
    private VexFwkSessionConfigRequest configRequest;
    private long nativeHandle;
    private final VexFwkUsecase usecase;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0005\u001a\u00020\u0004H ¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H ¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0004H ¢\u0006\u0004\b\r\u0010\u000eJ \u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H ¢\u0006\u0004\b\u0010\u0010\u0011J \u0010\u0013\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004H ¢\u0006\u0004\b\u0013\u0010\u0011J \u0010\u0014\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004H ¢\u0006\u0004\b\u0014\u0010\u0011J\u0018\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0004H ¢\u0006\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSession$Companion;", "", "<init>", "()V", "", "createNative", "()J", "", "usecaseId", "createWithUsecaseNative", "(I)J", "sessionHandle", "Lme/x;", "deleteNative", "(J)V", "configRequestHandle", "configureNative", "(JJ)I", "requestHandle", "processRequestNative", "cancelRequestNative", "flushNative", "(J)I", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final int cancelRequestNative(long j2, long j3) {
            return VexFwkSession.cancelRequestNative(j2, j3);
        }

        /* access modifiers changed from: private */
        public final int configureNative(long j2, long j3) {
            return VexFwkSession.configureNative(j2, j3);
        }

        /* access modifiers changed from: private */
        public final long createNative() {
            return VexFwkSession.createNative();
        }

        /* access modifiers changed from: private */
        public final long createWithUsecaseNative(int i2) {
            return VexFwkSession.createWithUsecaseNative(i2);
        }

        /* access modifiers changed from: private */
        public final void deleteNative(long j2) {
            VexFwkSession.deleteNative(j2);
        }

        /* access modifiers changed from: private */
        public final int flushNative(long j2) {
            return VexFwkSession.flushNative(j2);
        }

        /* access modifiers changed from: private */
        public final int processRequestNative(long j2, long j3) {
            return VexFwkSession.processRequestNative(j2, j3);
        }

        private Companion() {
        }
    }

    public VexFwkSession(VexFwkUsecase vexFwkUsecase) {
        long j2;
        j.e(vexFwkUsecase, "usecase");
        this.usecase = vexFwkUsecase;
        if (VexFwkProvider.getNdkVersion() == 0) {
            j2 = Companion.createNative();
        } else {
            j2 = Companion.createWithUsecaseNative(vexFwkUsecase.getId());
        }
        this.nativeHandle = j2;
    }

    /* access modifiers changed from: private */
    public static final native int cancelRequestNative(long j2, long j3);

    /* access modifiers changed from: private */
    public static final native int configureNative(long j2, long j3);

    /* access modifiers changed from: private */
    public static final native long createNative();

    /* access modifiers changed from: private */
    public static final native long createWithUsecaseNative(int i2);

    /* access modifiers changed from: private */
    public static final native void deleteNative(long j2);

    /* access modifiers changed from: private */
    public static final native int flushNative(long j2);

    /* access modifiers changed from: private */
    public static final native int processRequestNative(long j2, long j3);

    public final void cancelRequest(VexFwkSessionRequest vexFwkSessionRequest) {
        j.e(vexFwkSessionRequest, "request");
        int unused = Companion.cancelRequestNative(this.nativeHandle, vexFwkSessionRequest.getNativeHandle());
    }

    public void close() {
        VexFwkSessionConfigRequest vexFwkSessionConfigRequest = this.configRequest;
        if (vexFwkSessionConfigRequest != null) {
            vexFwkSessionConfigRequest.close();
        }
        this.configRequest = null;
        long j2 = this.nativeHandle;
        if (j2 != 0) {
            Companion.deleteNative(j2);
            this.nativeHandle = 0;
        }
    }

    public final void configure(VexFwkSessionConfigRequest vexFwkSessionConfigRequest) {
        j.e(vexFwkSessionConfigRequest, "configRequest");
        VexFwkSessionConfigRequest vexFwkSessionConfigRequest2 = this.configRequest;
        if (vexFwkSessionConfigRequest2 != null) {
            vexFwkSessionConfigRequest2.close();
        }
        vexFwkSessionConfigRequest.setUsecaseId(this.usecase.getId());
        this.configRequest = vexFwkSessionConfigRequest;
        int unused = Companion.configureNative(this.nativeHandle, vexFwkSessionConfigRequest.getNativeHandle());
    }

    public final void flush() {
        int unused = Companion.flushNative(this.nativeHandle);
    }

    public final List<VexFwkStream> getStreams() {
        List<VexFwkStream> streams;
        VexFwkSessionConfigRequest vexFwkSessionConfigRequest = this.configRequest;
        if (vexFwkSessionConfigRequest == null || (streams = vexFwkSessionConfigRequest.getStreams()) == null) {
            return C1202t.d;
        }
        return streams;
    }

    public final VexFwkUsecase getUsecase() {
        return this.usecase;
    }

    public final VexFwkResultCode processRequest(VexFwkSessionRequest.Async async) {
        j.e(async, "request");
        return VexFwkResultCode.Companion.fromInt(Companion.processRequestNative(this.nativeHandle, async.getNativeHandle()));
    }

    /* renamed from: processRequest-IoAF18A  reason: not valid java name */
    public final Object m60processRequestIoAF18A(VexFwkSessionRequest.Sync sync) {
        j.e(sync, "request");
        VexFwkResultCode fromInt = VexFwkResultCode.Companion.fromInt(Companion.processRequestNative(this.nativeHandle, sync.getNativeHandle()));
        if (fromInt != VexFwkResultCode.OK) {
            return a.l(new Exception("Sending Request Failed(" + fromInt + ")"));
        }
        VexFwkSessionTotalResult awaitTotalResult = sync.awaitTotalResult();
        if (awaitTotalResult.getRequestResult()) {
            return awaitTotalResult;
        }
        awaitTotalResult.close();
        return a.l(new Exception("RequestResult Fail Received"));
    }

    public final VexFwkResultCode processRequest(VexFwkSessionRequest vexFwkSessionRequest) {
        j.e(vexFwkSessionRequest, "request");
        return VexFwkResultCode.Companion.fromInt(Companion.processRequestNative(this.nativeHandle, vexFwkSessionRequest.getNativeHandle()));
    }
}
