package com.samsung.android.vexfwk.sdk.common.runtime;

import Ae.b;
import Ke.v0;
import android.view.Surface;
import com.samsung.android.sdk.mobileservice.social.group.provider.GroupContract;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.session.VexFwkStreamInoutDirection;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1196n;
import ne.C1202t;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\r\u0018\u0000 $2\u00060\u0001j\u0002`\u0002:\u0002$%B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0004R\"\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R(\u0010\u0018\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00128B@BX\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u00198F@FX\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR0\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8F@FX\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"¨\u0006&"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionConfigRequest;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "Lme/x;", "close", "", "nativeHandle", "J", "getNativeHandle", "()J", "setNativeHandle", "(J)V", "", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkStream;", "streamsCached", "Ljava/util/List;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "value", "getConfigMetadata", "()Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "setConfigMetadata", "(Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;)V", "configMetadata", "", "getUsecaseId", "()I", "setUsecaseId", "(I)V", "usecaseId", "getStreams", "()Ljava/util/List;", "setStreams", "(Ljava/util/List;)V", "streams", "Companion", "Builder", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkSessionConfigRequest implements AutoCloseable {
    public static final Companion Companion = new Companion((e) null);
    private long nativeHandle = Companion.createNative();
    private List<VexFwkStream> streamsCached;

    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\b\u001a\u00020\u00002\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\b\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\u000bJ)\u0010\u0010\u001a\u00020\u0000\"\u0004\b\u0000\u0010\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\r2\u0006\u0010\u000f\u001a\u00028\u0000¢\u0006\u0004\b\u0010\u0010\u0011JO\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00122\b\b\u0002\u0010\u0019\u001a\u00020\u00122\b\b\u0002\u0010\u001a\u001a\u00020\u00122\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001b¢\u0006\u0004\b\u001d\u0010\u001eJO\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00122\b\b\u0002\u0010\u0019\u001a\u00020\u00122\b\b\u0002\u0010\u001a\u001a\u00020\u00122\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001b¢\u0006\u0004\b\u001f\u0010\u001eJO\u0010\"\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010!\u001a\u00020 2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u00122\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b¢\u0006\u0004\b\"\u0010#J\r\u0010%\u001a\u00020$¢\u0006\u0004\b%\u0010&R\u0018\u0010'\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b'\u0010(R\u001c\u0010+\u001a\b\u0012\u0004\u0012\u00020*0)8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b+\u0010,¨\u0006-"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionConfigRequest$Builder;", "", "<init>", "()V", "Lkotlin/Function1;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "Lme/x;", "action", "setConfigMetadata", "(LAe/b;)Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionConfigRequest$Builder;", "metadata", "(Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;)Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionConfigRequest$Builder;", "T", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "key", "value", "addMetadataValue", "(Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;Ljava/lang/Object;)Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionConfigRequest$Builder;", "", "id", "Lcom/samsung/android/vexfwk/session/VexFwkStreamType;", "streamType", "Lcom/samsung/android/vexfwk/session/VexFwkStreamUsage;", "usage", "width", "height", "format", "Landroid/view/Surface;", "surface", "addInputStream", "(ILcom/samsung/android/vexfwk/session/VexFwkStreamType;Lcom/samsung/android/vexfwk/session/VexFwkStreamUsage;IIILandroid/view/Surface;)Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionConfigRequest$Builder;", "addOutputStream", "Lcom/samsung/android/vexfwk/session/VexFwkStreamInoutDirection;", "inoutDirection", "addStream", "(ILcom/samsung/android/vexfwk/session/VexFwkStreamInoutDirection;Lcom/samsung/android/vexfwk/session/VexFwkStreamType;Lcom/samsung/android/vexfwk/session/VexFwkStreamUsage;IIILandroid/view/Surface;)Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionConfigRequest$Builder;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionConfigRequest;", "build", "()Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionConfigRequest;", "configMetadata", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkStream;", "streams", "Ljava/util/List;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private VexFwkMetadataNative configMetadata;
        private List<VexFwkStream> streams = new ArrayList();

        public static /* synthetic */ Builder addInputStream$default(Builder builder, int i2, VexFwkStreamType vexFwkStreamType, VexFwkStreamUsage vexFwkStreamUsage, int i7, int i8, int i10, Surface surface, int i11, Object obj) {
            if ((i11 & 8) != 0) {
                i7 = -1;
            }
            if ((i11 & 16) != 0) {
                i8 = -1;
            }
            if ((i11 & 32) != 0) {
                i10 = -1;
            }
            if ((i11 & 64) != 0) {
                surface = null;
            }
            return builder.addInputStream(i2, vexFwkStreamType, vexFwkStreamUsage, i7, i8, i10, surface);
        }

        public static /* synthetic */ Builder addOutputStream$default(Builder builder, int i2, VexFwkStreamType vexFwkStreamType, VexFwkStreamUsage vexFwkStreamUsage, int i7, int i8, int i10, Surface surface, int i11, Object obj) {
            if ((i11 & 8) != 0) {
                i7 = -1;
            }
            if ((i11 & 16) != 0) {
                i8 = -1;
            }
            if ((i11 & 32) != 0) {
                i10 = -1;
            }
            if ((i11 & 64) != 0) {
                surface = null;
            }
            return builder.addOutputStream(i2, vexFwkStreamType, vexFwkStreamUsage, i7, i8, i10, surface);
        }

        public final Builder addInputStream(int i2, VexFwkStreamType vexFwkStreamType, VexFwkStreamUsage vexFwkStreamUsage, int i7, int i8, int i10, Surface surface) {
            j.e(vexFwkStreamType, "streamType");
            j.e(vexFwkStreamUsage, "usage");
            return addStream(i2, VexFwkStreamInoutDirection.IN, vexFwkStreamType, vexFwkStreamUsage, i7, i8, i10, surface);
        }

        public final <T> Builder addMetadataValue(VexFwkMetadataKey<T> vexFwkMetadataKey, T t) {
            j.e(vexFwkMetadataKey, "key");
            VexFwkMetadataNative vexFwkMetadataNative = this.configMetadata;
            if (vexFwkMetadataNative == null) {
                vexFwkMetadataNative = new VexFwkMetadataNative();
                this.configMetadata = vexFwkMetadataNative;
            }
            vexFwkMetadataNative.set(vexFwkMetadataKey, t);
            return this;
        }

        public final Builder addOutputStream(int i2, VexFwkStreamType vexFwkStreamType, VexFwkStreamUsage vexFwkStreamUsage, int i7, int i8, int i10, Surface surface) {
            j.e(vexFwkStreamType, "streamType");
            j.e(vexFwkStreamUsage, "usage");
            return addStream(i2, VexFwkStreamInoutDirection.OUT, vexFwkStreamType, vexFwkStreamUsage, i7, i8, i10, surface);
        }

        public final Builder addStream(int i2, VexFwkStreamInoutDirection vexFwkStreamInoutDirection, VexFwkStreamType vexFwkStreamType, VexFwkStreamUsage vexFwkStreamUsage, int i7, int i8, int i10, Surface surface) {
            j.e(vexFwkStreamInoutDirection, "inoutDirection");
            j.e(vexFwkStreamType, "streamType");
            j.e(vexFwkStreamUsage, "usage");
            List<VexFwkStream> list = this.streams;
            VexFwkStream vexFwkStream = new VexFwkStream();
            vexFwkStream.setId(i2);
            vexFwkStream.setInoutDirection(vexFwkStreamInoutDirection);
            vexFwkStream.setStreamType(vexFwkStreamType);
            vexFwkStream.setUsage(vexFwkStreamUsage);
            vexFwkStream.setWidth(i7);
            vexFwkStream.setHeight(i8);
            vexFwkStream.setFormat(i10);
            vexFwkStream.setSurface(surface);
            list.add(vexFwkStream);
            return this;
        }

        public final VexFwkSessionConfigRequest build() {
            VexFwkSessionConfigRequest vexFwkSessionConfigRequest = new VexFwkSessionConfigRequest();
            vexFwkSessionConfigRequest.setConfigMetadata(this.configMetadata);
            vexFwkSessionConfigRequest.setStreams(this.streams);
            return vexFwkSessionConfigRequest;
        }

        public final Builder setConfigMetadata(b bVar) {
            j.e(bVar, "action");
            VexFwkMetadataNative vexFwkMetadataNative = new VexFwkMetadataNative();
            bVar.invoke(vexFwkMetadataNative);
            this.configMetadata = vexFwkMetadataNative;
            return this;
        }

        public final Builder setConfigMetadata(VexFwkMetadataNative vexFwkMetadataNative) {
            j.e(vexFwkMetadataNative, GroupContract.Group.META_DATA);
            this.configMetadata = vexFwkMetadataNative;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0016\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0005\u001a\u00020\u0004H ¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0004H ¢\u0006\u0004\b\t\u0010\nJ \u0010\r\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH ¢\u0006\u0004\b\r\u0010\u000eJ \u0010\u0010\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H ¢\u0006\u0004\b\u0010\u0010\u0011J \u0010\u0014\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0012H ¢\u0006\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionConfigRequest$Companion;", "", "<init>", "()V", "", "createNative", "()J", "configRequestHandle", "Lme/x;", "deleteNative", "(J)V", "", "usecasdId", "setUsecaseIdNative", "(JI)V", "configMetadataHandle", "setConfigMetadataNative", "(JJ)V", "", "streamHandleArray", "setStreamsNative", "(J[J)V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final long createNative() {
            return VexFwkSessionConfigRequest.createNative();
        }

        /* access modifiers changed from: private */
        public final void deleteNative(long j2) {
            VexFwkSessionConfigRequest.deleteNative(j2);
        }

        /* access modifiers changed from: private */
        public final void setConfigMetadataNative(long j2, long j3) {
            VexFwkSessionConfigRequest.setConfigMetadataNative(j2, j3);
        }

        /* access modifiers changed from: private */
        public final void setStreamsNative(long j2, long[] jArr) {
            VexFwkSessionConfigRequest.setStreamsNative(j2, jArr);
        }

        /* access modifiers changed from: private */
        public final void setUsecaseIdNative(long j2, int i2) {
            VexFwkSessionConfigRequest.setUsecaseIdNative(j2, i2);
        }

        private Companion() {
        }
    }

    /* access modifiers changed from: private */
    public static final native long createNative();

    /* access modifiers changed from: private */
    public static final native void deleteNative(long j2);

    private final VexFwkMetadataNative getConfigMetadata() {
        throw new v0(2);
    }

    /* access modifiers changed from: private */
    public final void setConfigMetadata(VexFwkMetadataNative vexFwkMetadataNative) {
        if (vexFwkMetadataNative != null) {
            Companion.setConfigMetadataNative(this.nativeHandle, vexFwkMetadataNative.getNativeHandle());
        }
    }

    /* access modifiers changed from: private */
    public static final native void setConfigMetadataNative(long j2, long j3);

    /* access modifiers changed from: private */
    public static final native void setStreamsNative(long j2, long[] jArr);

    /* access modifiers changed from: private */
    public static final native void setUsecaseIdNative(long j2, int i2);

    public void close() {
        List<VexFwkStream> list = this.streamsCached;
        if (list != null) {
            for (VexFwkStream close : list) {
                close.close();
            }
        }
        this.streamsCached = null;
        long j2 = this.nativeHandle;
        if (j2 != 0) {
            Companion.deleteNative(j2);
            this.nativeHandle = 0;
        }
    }

    public final long getNativeHandle() {
        return this.nativeHandle;
    }

    public final List<VexFwkStream> getStreams() {
        List<VexFwkStream> list = this.streamsCached;
        if (list == null) {
            return C1202t.d;
        }
        return list;
    }

    public final int getUsecaseId() {
        throw new v0(2);
    }

    public final void setNativeHandle(long j2) {
        this.nativeHandle = j2;
    }

    public final void setStreams(List<VexFwkStream> list) {
        j.e(list, "value");
        this.streamsCached = list;
        Companion companion = Companion;
        long j2 = this.nativeHandle;
        Iterable<VexFwkStream> iterable = list;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (VexFwkStream nativeHandle2 : iterable) {
            arrayList.add(Long.valueOf(nativeHandle2.getNativeHandle()));
        }
        companion.setStreamsNative(j2, C1194l.l1(arrayList));
    }

    public final void setUsecaseId(int i2) {
        Companion.setUsecaseIdNative(this.nativeHandle, i2);
    }
}
