package com.samsung.android.vexfwk.sdk.common.runtime;

import Ae.b;
import Ke.v0;
import com.samsung.android.sdk.mobileservice.profile.Profile;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1196n;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\r\b\u0016\u0018\u0000 <2\u00060\u0001j\u0002`\u0002:\u0005<=>?@B\t\b\u0016¢\u0006\u0004\b\u0003\u0010\u0004B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0003\u0010\u0007J!\u0010\f\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0004\b\f\u0010\rJ!\u0010\u000e\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0004\b\u000e\u0010\rJ!\u0010\u0010\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0004\b\u0010\u0010\rJ\r\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0014\u0010\u0004R\"\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0007R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00198\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00198\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001cR\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR$\u0010&\u001a\u00020 2\u0006\u0010!\u001a\u00020 8F@FX\u000e¢\u0006\f\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R(\u0010*\u001a\u0004\u0018\u00010\u000f2\b\u0010!\u001a\u0004\u0018\u00010\u000f8F@FX\u000e¢\u0006\f\u001a\u0004\b'\u0010(\"\u0004\b\u0010\u0010)R0\u00100\u001a\b\u0012\u0004\u0012\u00020\u001a0+2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001a0+8F@FX\u000e¢\u0006\f\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R0\u00103\u001a\b\u0012\u0004\u0012\u00020\u001a0+2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001a0+8F@FX\u000e¢\u0006\f\u001a\u0004\b1\u0010-\"\u0004\b2\u0010/R(\u00109\u001a\u0004\u0018\u0001042\b\u0010!\u001a\u0004\u0018\u0001048F@FX\u000e¢\u0006\f\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u0011\u0010;\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b:\u0010\u0013¨\u0006A"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "", "nativeHandle", "(J)V", "Lkotlin/Function1;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$BufferSetter;", "Lme/x;", "action", "setInputBuffer", "(LAe/b;)V", "setOutputBuffer", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "setSettingMetadata", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;", "awaitTotalResult", "()Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;", "close", "J", "getNativeHandle", "()J", "setNativeHandle", "Ljava/util/LinkedList;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkBuffer;", "inputBuffersCached", "Ljava/util/LinkedList;", "outputBuffersCached", "totalResultCached", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;", "", "value", "getRequestFrameNumber", "()I", "setRequestFrameNumber", "(I)V", "requestFrameNumber", "getSettingMetadata", "()Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "(Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;)V", "settingMetadata", "", "getInputBuffers", "()Ljava/util/List;", "setInputBuffers", "(Ljava/util/List;)V", "inputBuffers", "getOutputBuffers", "setOutputBuffers", "outputBuffers", "Lcom/samsung/android/vexfwk/sdk/common/runtime/IVexFwkSessionCallback;", "getCallback", "()Lcom/samsung/android/vexfwk/sdk/common/runtime/IVexFwkSessionCallback;", "setCallback", "(Lcom/samsung/android/vexfwk/sdk/common/runtime/IVexFwkSessionCallback;)V", "callback", "getTotalResult", "totalResult", "Companion", "Builder", "Sync", "Async", "BufferSetter", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VexFwkSessionRequest implements AutoCloseable {
    public static final Companion Companion = new Companion((e) null);
    private final LinkedList<VexFwkBuffer> inputBuffersCached;
    private long nativeHandle;
    private final LinkedList<VexFwkBuffer> outputBuffersCached;
    private VexFwkSessionTotalResult totalResultCached;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Async;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest;", "<init>", "()V", "Builder", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Async extends VexFwkSessionRequest {

        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Async$Builder;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Builder;", "<init>", "()V", "callback", "Lcom/samsung/android/vexfwk/sdk/common/runtime/IVexFwkSessionCallback;", "setCallback", "build", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Async;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Builder extends Builder {
            private IVexFwkSessionCallback callback;

            public final Async build() {
                Async async = new Async();
                async.setSettingMetadata(getSettingMetadata());
                async.setInputBuffers(getInputBuffers());
                async.setOutputBuffers(getOutputBuffers());
                IVexFwkSessionCallback iVexFwkSessionCallback = this.callback;
                if (iVexFwkSessionCallback != null) {
                    async.setCallback(iVexFwkSessionCallback);
                    return async;
                }
                j.k(Profile.PhoneNumberData.TYPE_CALLBACK);
                throw null;
            }

            public final Builder setCallback(IVexFwkSessionCallback iVexFwkSessionCallback) {
                j.e(iVexFwkSessionCallback, Profile.PhoneNumberData.TYPE_CALLBACK);
                this.callback = iVexFwkSessionCallback;
                return this;
            }
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u0002H\u0005¢\u0006\u0002\u0010\tJ\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\n\u001a\u00020\u000bJ\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$BufferSetter;", "", "<init>", "()V", "add", "T", "streamId", "", "buffer", "(ILjava/lang/Object;)Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$BufferSetter;", "isNotEmpty", "", "release", "", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkBuffer;", "buffers", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class BufferSetter {
        private final List<VexFwkBuffer> buffers = new ArrayList();

        public final <T> BufferSetter add(int i2, T t) {
            this.buffers.add(VexFwkBuffer.Companion.from(i2, t));
            return this;
        }

        public final boolean isNotEmpty() {
            return !this.buffers.isEmpty();
        }

        public final List<VexFwkBuffer> release() {
            List<VexFwkBuffer> k12 = C1194l.k1(this.buffers);
            this.buffers.clear();
            return k12;
        }

        public final BufferSetter add(int i2) {
            this.buffers.add(VexFwkBuffer.Companion.from(i2));
            return this;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Builder;", "", "<init>", "()V", "inputBuffers", "Ljava/util/LinkedList;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkBuffer;", "getInputBuffers", "()Ljava/util/LinkedList;", "outputBuffers", "getOutputBuffers", "settingMetadata", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "getSettingMetadata", "()Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "setSettingMetadata", "(Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;)V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Builder {
        private final LinkedList<VexFwkBuffer> inputBuffers = new LinkedList<>();
        private final LinkedList<VexFwkBuffer> outputBuffers = new LinkedList<>();
        private VexFwkMetadataNative settingMetadata;

        public final LinkedList<VexFwkBuffer> getInputBuffers() {
            return this.inputBuffers;
        }

        public final LinkedList<VexFwkBuffer> getOutputBuffers() {
            return this.outputBuffers;
        }

        public final VexFwkMetadataNative getSettingMetadata() {
            return this.settingMetadata;
        }

        public final void setSettingMetadata(VexFwkMetadataNative vexFwkMetadataNative) {
            this.settingMetadata = vexFwkMetadataNative;
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0016\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0005\u001a\u00020\u0004H ¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H ¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0004H ¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u0004H ¢\u0006\u0004\b\u000e\u0010\u000fJ \u0010\u0011\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H ¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0015\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0013H ¢\u0006\u0004\b\u0015\u0010\u0016J \u0010\u0017\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0013H ¢\u0006\u0004\b\u0017\u0010\u0016J \u0010\u001a\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0018H ¢\u0006\u0004\b\u001a\u0010\u001bJ\u0018\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\u0004H ¢\u0006\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Companion;", "", "<init>", "()V", "", "createNative", "()J", "requestHandle", "cloneNative", "(J)J", "Lme/x;", "deleteNative", "(J)V", "", "getRequestNumberNative", "(J)I", "settingMetadataHandle", "setSettingMetadataNative", "(JJ)V", "", "bufferHandleArray", "setInputBuffersNative", "(J[J)V", "setOutputBuffersNative", "Lcom/samsung/android/vexfwk/sdk/common/runtime/IVexFwkSessionCallback;", "callback", "setCallbackNative", "(JLcom/samsung/android/vexfwk/sdk/common/runtime/IVexFwkSessionCallback;)V", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;", "awaitTotalResultNative", "(J)Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final VexFwkSessionTotalResult awaitTotalResultNative(long j2) {
            return VexFwkSessionRequest.awaitTotalResultNative(j2);
        }

        /* access modifiers changed from: private */
        public final long cloneNative(long j2) {
            return VexFwkSessionRequest.cloneNative(j2);
        }

        /* access modifiers changed from: private */
        public final long createNative() {
            return VexFwkSessionRequest.createNative();
        }

        /* access modifiers changed from: private */
        public final void deleteNative(long j2) {
            VexFwkSessionRequest.deleteNative(j2);
        }

        /* access modifiers changed from: private */
        public final int getRequestNumberNative(long j2) {
            return VexFwkSessionRequest.getRequestNumberNative(j2);
        }

        /* access modifiers changed from: private */
        public final void setCallbackNative(long j2, IVexFwkSessionCallback iVexFwkSessionCallback) {
            VexFwkSessionRequest.setCallbackNative(j2, iVexFwkSessionCallback);
        }

        /* access modifiers changed from: private */
        public final void setInputBuffersNative(long j2, long[] jArr) {
            VexFwkSessionRequest.setInputBuffersNative(j2, jArr);
        }

        /* access modifiers changed from: private */
        public final void setOutputBuffersNative(long j2, long[] jArr) {
            VexFwkSessionRequest.setOutputBuffersNative(j2, jArr);
        }

        /* access modifiers changed from: private */
        public final void setSettingMetadataNative(long j2, long j3) {
            VexFwkSessionRequest.setSettingMetadataNative(j2, j3);
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Sync;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest;", "<init>", "()V", "Builder", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Sync extends VexFwkSessionRequest {

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Sync$Builder;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Builder;", "<init>", "()V", "build", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Sync;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Builder extends Builder {
            public final Sync build() {
                Sync sync = new Sync();
                sync.setSettingMetadata(getSettingMetadata());
                sync.setInputBuffers(getInputBuffers());
                sync.setOutputBuffers(getOutputBuffers());
                return sync;
            }
        }
    }

    public VexFwkSessionRequest() {
        this.inputBuffersCached = new LinkedList<>();
        this.outputBuffersCached = new LinkedList<>();
        this.nativeHandle = Companion.createNative();
    }

    /* access modifiers changed from: private */
    public static final native VexFwkSessionTotalResult awaitTotalResultNative(long j2);

    /* access modifiers changed from: private */
    public static final native long cloneNative(long j2);

    /* access modifiers changed from: private */
    public static final native long createNative();

    /* access modifiers changed from: private */
    public static final native void deleteNative(long j2);

    /* access modifiers changed from: private */
    public static final native int getRequestNumberNative(long j2);

    /* access modifiers changed from: private */
    public static final native void setCallbackNative(long j2, IVexFwkSessionCallback iVexFwkSessionCallback);

    /* access modifiers changed from: private */
    public static final native void setInputBuffersNative(long j2, long[] jArr);

    /* access modifiers changed from: private */
    public static final native void setOutputBuffersNative(long j2, long[] jArr);

    /* access modifiers changed from: private */
    public static final native void setSettingMetadataNative(long j2, long j3);

    public final VexFwkSessionTotalResult awaitTotalResult() {
        return Companion.awaitTotalResultNative(this.nativeHandle);
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
        VexFwkSessionTotalResult vexFwkSessionTotalResult = this.totalResultCached;
        if (vexFwkSessionTotalResult != null) {
            vexFwkSessionTotalResult.close();
        }
        this.totalResultCached = null;
        long j2 = this.nativeHandle;
        if (j2 != 0) {
            Companion.deleteNative(j2);
            this.nativeHandle = 0;
        }
    }

    public final IVexFwkSessionCallback getCallback() {
        throw new v0(2);
    }

    public final List<VexFwkBuffer> getInputBuffers() {
        return this.inputBuffersCached;
    }

    public final long getNativeHandle() {
        return this.nativeHandle;
    }

    public final List<VexFwkBuffer> getOutputBuffers() {
        return this.outputBuffersCached;
    }

    public final int getRequestFrameNumber() {
        return Companion.getRequestNumberNative(this.nativeHandle);
    }

    public final VexFwkMetadataNative getSettingMetadata() {
        throw new v0(2);
    }

    public final VexFwkSessionTotalResult getTotalResult() {
        VexFwkSessionTotalResult vexFwkSessionTotalResult = this.totalResultCached;
        if (vexFwkSessionTotalResult != null) {
            return vexFwkSessionTotalResult;
        }
        VexFwkSessionTotalResult access$awaitTotalResultNative = Companion.awaitTotalResultNative(this.nativeHandle);
        this.totalResultCached = access$awaitTotalResultNative;
        return access$awaitTotalResultNative;
    }

    public final void setCallback(IVexFwkSessionCallback iVexFwkSessionCallback) {
        if (iVexFwkSessionCallback != null) {
            Companion.setCallbackNative(this.nativeHandle, iVexFwkSessionCallback);
        }
    }

    public final void setInputBuffer(b bVar) {
        j.e(bVar, "action");
        BufferSetter bufferSetter = new BufferSetter();
        bVar.invoke(bufferSetter);
        if (bufferSetter.isNotEmpty()) {
            setInputBuffers(bufferSetter.release());
        }
    }

    public final void setInputBuffers(List<? extends VexFwkBuffer> list) {
        j.e(list, "value");
        this.inputBuffersCached.addAll(list);
        Companion companion = Companion;
        long j2 = this.nativeHandle;
        Iterable<VexFwkBuffer> iterable = list;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (VexFwkBuffer nativeHandle2 : iterable) {
            arrayList.add(Long.valueOf(nativeHandle2.getNativeHandle()));
        }
        companion.setInputBuffersNative(j2, C1194l.l1(arrayList));
    }

    public final void setNativeHandle(long j2) {
        this.nativeHandle = j2;
    }

    public final void setOutputBuffer(b bVar) {
        j.e(bVar, "action");
        BufferSetter bufferSetter = new BufferSetter();
        bVar.invoke(bufferSetter);
        if (bufferSetter.isNotEmpty()) {
            setOutputBuffers(bufferSetter.release());
        }
    }

    public final void setOutputBuffers(List<? extends VexFwkBuffer> list) {
        j.e(list, "value");
        this.outputBuffersCached.addAll(list);
        Companion companion = Companion;
        long j2 = this.nativeHandle;
        Iterable<VexFwkBuffer> iterable = list;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (VexFwkBuffer nativeHandle2 : iterable) {
            arrayList.add(Long.valueOf(nativeHandle2.getNativeHandle()));
        }
        companion.setOutputBuffersNative(j2, C1194l.l1(arrayList));
    }

    public final void setRequestFrameNumber(int i2) {
        throw new v0(2);
    }

    public final void setSettingMetadata(VexFwkMetadataNative vexFwkMetadataNative) {
        if (vexFwkMetadataNative != null) {
            Companion.setSettingMetadataNative(this.nativeHandle, vexFwkMetadataNative.getNativeHandle());
        }
    }

    public final void setSettingMetadata(b bVar) {
        j.e(bVar, "action");
        VexFwkMetadataNative vexFwkMetadataNative = new VexFwkMetadataNative();
        bVar.invoke(vexFwkMetadataNative);
        setSettingMetadata(vexFwkMetadataNative);
    }

    public VexFwkSessionRequest(long j2) {
        this.inputBuffersCached = new LinkedList<>();
        this.outputBuffersCached = new LinkedList<>();
        this.nativeHandle = Companion.cloneNative(j2);
    }
}
