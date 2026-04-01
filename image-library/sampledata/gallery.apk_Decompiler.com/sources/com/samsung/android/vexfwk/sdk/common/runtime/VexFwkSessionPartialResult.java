package com.samsung.android.vexfwk.sdk.common.runtime;

import De.b;
import He.t;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import java.util.LinkedList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.m;
import kotlin.jvm.internal.v;
import ne.C1192j;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0007\u0018\u0000 '2\u00060\u0001j\u0002`\u0002:\u0001'B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tR+\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00038B@BX\u0002¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0006R\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0018\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u001c\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010 \u001a\u0004\u0018\u00010\u001d8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00110!8F¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00110!8F¢\u0006\u0006\u001a\u0004\b%\u0010#¨\u0006("}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionPartialResult;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "", "nativeHandle", "<init>", "(J)V", "Lme/x;", "close", "()V", "<set-?>", "nativeHandle$delegate", "LDe/b;", "getNativeHandle", "()J", "setNativeHandle", "Ljava/util/LinkedList;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkBuffer;", "inputBuffersCached", "Ljava/util/LinkedList;", "outputBuffersCached", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest;", "getRequest", "()Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest;", "request", "", "getRequestNumber", "()I", "requestNumber", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "getResultMetadata", "()Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "resultMetadata", "", "getInputBuffers", "()Ljava/util/List;", "inputBuffers", "getOutputBuffers", "outputBuffers", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkSessionPartialResult implements AutoCloseable {
    static final /* synthetic */ t[] $$delegatedProperties = {v.f4727a.d(new m(VexFwkSessionPartialResult.class))};
    public static final Companion Companion = new Companion((e) null);
    private final LinkedList<VexFwkBuffer> inputBuffersCached = new LinkedList<>();
    private final b nativeHandle$delegate = new B1.b(1);
    private final LinkedList<VexFwkBuffer> outputBuffersCached = new LinkedList<>();

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H ¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0004H ¢\u0006\u0004\b\n\u0010\u000bJ\u0018\u0010\r\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u0004H ¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\b\u001a\u00020\u0004H ¢\u0006\u0004\b\u0010\u0010\u0011J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\b\u001a\u00020\u0004H ¢\u0006\u0004\b\u0014\u0010\u0015J\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\b\u001a\u00020\u0004H ¢\u0006\u0004\b\u0016\u0010\u0015¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionPartialResult$Companion;", "", "<init>", "()V", "", "bufferHandle", "cloneNative", "(J)J", "requestHandle", "Lme/x;", "deleteNative", "(J)V", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest;", "getRequestNative", "(J)Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "getResultMetadataNative", "(J)Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkBuffer;", "getInputBuffersNative", "(J)Lkotlin/Array;", "getOutputBuffersNative", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final long cloneNative(long j2) {
            return VexFwkSessionPartialResult.cloneNative(j2);
        }

        /* access modifiers changed from: private */
        public final void deleteNative(long j2) {
            VexFwkSessionPartialResult.deleteNative(j2);
        }

        /* access modifiers changed from: private */
        public final VexFwkBuffer[] getInputBuffersNative(long j2) {
            return VexFwkSessionPartialResult.getInputBuffersNative(j2);
        }

        /* access modifiers changed from: private */
        public final VexFwkBuffer[] getOutputBuffersNative(long j2) {
            return VexFwkSessionPartialResult.getOutputBuffersNative(j2);
        }

        /* access modifiers changed from: private */
        public final VexFwkSessionRequest getRequestNative(long j2) {
            return VexFwkSessionPartialResult.getRequestNative(j2);
        }

        /* access modifiers changed from: private */
        public final VexFwkMetadataNative getResultMetadataNative(long j2) {
            return VexFwkSessionPartialResult.getResultMetadataNative(j2);
        }

        private Companion() {
        }
    }

    public VexFwkSessionPartialResult(long j2) {
        setNativeHandle(Companion.cloneNative(j2));
    }

    /* access modifiers changed from: private */
    public static final native long cloneNative(long j2);

    /* access modifiers changed from: private */
    public static final native void deleteNative(long j2);

    /* access modifiers changed from: private */
    public static final native VexFwkBuffer[] getInputBuffersNative(long j2);

    private final long getNativeHandle() {
        return ((Number) this.nativeHandle$delegate.f(this, $$delegatedProperties[0])).longValue();
    }

    /* access modifiers changed from: private */
    public static final native VexFwkBuffer[] getOutputBuffersNative(long j2);

    /* access modifiers changed from: private */
    public static final native VexFwkSessionRequest getRequestNative(long j2);

    /* access modifiers changed from: private */
    public static final native VexFwkMetadataNative getResultMetadataNative(long j2);

    private final void setNativeHandle(long j2) {
        this.nativeHandle$delegate.e(Long.valueOf(j2), $$delegatedProperties[0]);
    }

    public void close() {
        for (VexFwkBuffer close : this.inputBuffersCached) {
            close.close();
        }
        this.inputBuffersCached.clear();
        for (VexFwkBuffer close2 : this.outputBuffersCached) {
            close2.close();
        }
        this.outputBuffersCached.clear();
        if (getNativeHandle() != 0) {
            Companion.deleteNative(getNativeHandle());
            setNativeHandle(0);
        }
    }

    public final List<VexFwkBuffer> getInputBuffers() {
        if (!this.inputBuffersCached.isEmpty()) {
            return this.inputBuffersCached;
        }
        List<VexFwkBuffer> x02 = C1192j.x0(Companion.getInputBuffersNative(getNativeHandle()));
        this.inputBuffersCached.addAll(x02);
        return x02;
    }

    public final List<VexFwkBuffer> getOutputBuffers() {
        if (!this.outputBuffersCached.isEmpty()) {
            return this.outputBuffersCached;
        }
        List<VexFwkBuffer> x02 = C1192j.x0(Companion.getOutputBuffersNative(getNativeHandle()));
        this.outputBuffersCached.addAll(x02);
        return x02;
    }

    public final VexFwkSessionRequest getRequest() {
        return Companion.getRequestNative(getNativeHandle());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        He.F.u(r2, r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int getRequestNumber() {
        /*
            r2 = this;
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest r2 = r2.getRequest()
            int r0 = r2.getRequestFrameNumber()     // Catch:{ all -> 0x000d }
            r1 = 0
            He.F.u(r2, r1)
            return r0
        L_0x000d:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x000f }
        L_0x000f:
            r1 = move-exception
            He.F.u(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionPartialResult.getRequestNumber():int");
    }

    public final VexFwkMetadataNative getResultMetadata() {
        return Companion.getResultMetadataNative(getNativeHandle());
    }
}
