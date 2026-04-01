package com.samsung.android.vexfwk.sdk.common.runtime;

import android.view.Surface;
import com.samsung.android.vexfwk.session.VexFwkStreamInoutDirection;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 62\u00060\u0001j\u0002`\u0002:\u00016B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0004R\"\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR$\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e8F@FX\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u00158F@FX\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010 \u001a\u00020\u001b2\u0006\u0010\u000f\u001a\u00020\u001b8F@FX\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010&\u001a\u00020!2\u0006\u0010\u000f\u001a\u00020!8F@FX\u000e¢\u0006\f\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R$\u0010)\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e8F@FX\u000e¢\u0006\f\u001a\u0004\b'\u0010\u0011\"\u0004\b(\u0010\u0013R$\u0010,\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e8F@FX\u000e¢\u0006\f\u001a\u0004\b*\u0010\u0011\"\u0004\b+\u0010\u0013R$\u0010/\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e8F@FX\u000e¢\u0006\f\u001a\u0004\b-\u0010\u0011\"\u0004\b.\u0010\u0013R(\u00105\u001a\u0004\u0018\u0001002\b\u0010\u000f\u001a\u0004\u0018\u0001008F@FX\u000e¢\u0006\f\u001a\u0004\b1\u00102\"\u0004\b3\u00104¨\u00067"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkStream;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "Lme/x;", "close", "", "nativeHandle", "J", "getNativeHandle", "()J", "setNativeHandle", "(J)V", "", "value", "getId", "()I", "setId", "(I)V", "id", "Lcom/samsung/android/vexfwk/session/VexFwkStreamInoutDirection;", "getInoutDirection", "()Lcom/samsung/android/vexfwk/session/VexFwkStreamInoutDirection;", "setInoutDirection", "(Lcom/samsung/android/vexfwk/session/VexFwkStreamInoutDirection;)V", "inoutDirection", "Lcom/samsung/android/vexfwk/session/VexFwkStreamType;", "getStreamType", "()Lcom/samsung/android/vexfwk/session/VexFwkStreamType;", "setStreamType", "(Lcom/samsung/android/vexfwk/session/VexFwkStreamType;)V", "streamType", "Lcom/samsung/android/vexfwk/session/VexFwkStreamUsage;", "getUsage", "()Lcom/samsung/android/vexfwk/session/VexFwkStreamUsage;", "setUsage", "(Lcom/samsung/android/vexfwk/session/VexFwkStreamUsage;)V", "usage", "getWidth", "setWidth", "width", "getHeight", "setHeight", "height", "getFormat", "setFormat", "format", "Landroid/view/Surface;", "getSurface", "()Landroid/view/Surface;", "setSurface", "(Landroid/view/Surface;)V", "surface", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkStream implements AutoCloseable {
    public static final Companion Companion = new Companion((e) null);
    private long nativeHandle = Companion.createNative();

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0005\u001a\u00020\u0004H ¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0004H ¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0004H ¢\u0006\u0004\b\f\u0010\rJ \u0010\u000f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000bH ¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0004H ¢\u0006\u0004\b\u0011\u0010\rJ \u0010\u0013\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u000bH ¢\u0006\u0004\b\u0013\u0010\u0010J\u0018\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0004H ¢\u0006\u0004\b\u0014\u0010\rJ \u0010\u0016\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u000bH ¢\u0006\u0004\b\u0016\u0010\u0010J\u0018\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0004H ¢\u0006\u0004\b\u0017\u0010\rJ \u0010\u0019\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u000bH ¢\u0006\u0004\b\u0019\u0010\u0010J\u0018\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0004H ¢\u0006\u0004\b\u001a\u0010\rJ \u0010\u001c\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u000bH ¢\u0006\u0004\b\u001c\u0010\u0010J\u0018\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0004H ¢\u0006\u0004\b\u001d\u0010\rJ \u0010\u001f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u000bH ¢\u0006\u0004\b\u001f\u0010\u0010J\u0018\u0010 \u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0004H ¢\u0006\u0004\b \u0010\rJ \u0010\"\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u000bH ¢\u0006\u0004\b\"\u0010\u0010J\u0018\u0010$\u001a\u00020#2\u0006\u0010\u0007\u001a\u00020\u0004H ¢\u0006\u0004\b$\u0010%J \u0010'\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010&\u001a\u00020#H ¢\u0006\u0004\b'\u0010(¨\u0006)"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkStream$Companion;", "", "<init>", "()V", "", "createNative", "()J", "streamHandle", "Lme/x;", "deleteNative", "(J)V", "", "getIdNative", "(J)I", "id", "setIdNative", "(JI)V", "getInoutDirectionNative", "inoutDirection", "setInoutDirectionNative", "getStreamTypeNative", "streamType", "setStreamTypeNative", "getUsageNative", "usage", "setUsageNative", "getWidthNative", "width", "setWidthNative", "getHeightNative", "height", "setHeightNative", "getFormatNative", "format", "setFormatNative", "Landroid/view/Surface;", "getSurfaceNative", "(J)Landroid/view/Surface;", "surface", "setSurfaceNative", "(JLandroid/view/Surface;)V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final long createNative() {
            return VexFwkStream.createNative();
        }

        /* access modifiers changed from: private */
        public final void deleteNative(long j2) {
            VexFwkStream.deleteNative(j2);
        }

        /* access modifiers changed from: private */
        public final int getFormatNative(long j2) {
            return VexFwkStream.getFormatNative(j2);
        }

        /* access modifiers changed from: private */
        public final int getHeightNative(long j2) {
            return VexFwkStream.getHeightNative(j2);
        }

        /* access modifiers changed from: private */
        public final int getIdNative(long j2) {
            return VexFwkStream.getIdNative(j2);
        }

        /* access modifiers changed from: private */
        public final int getInoutDirectionNative(long j2) {
            return VexFwkStream.getInoutDirectionNative(j2);
        }

        /* access modifiers changed from: private */
        public final int getStreamTypeNative(long j2) {
            return VexFwkStream.getStreamTypeNative(j2);
        }

        /* access modifiers changed from: private */
        public final Surface getSurfaceNative(long j2) {
            return VexFwkStream.getSurfaceNative(j2);
        }

        /* access modifiers changed from: private */
        public final int getUsageNative(long j2) {
            return VexFwkStream.getUsageNative(j2);
        }

        /* access modifiers changed from: private */
        public final int getWidthNative(long j2) {
            return VexFwkStream.getWidthNative(j2);
        }

        /* access modifiers changed from: private */
        public final void setFormatNative(long j2, int i2) {
            VexFwkStream.setFormatNative(j2, i2);
        }

        /* access modifiers changed from: private */
        public final void setHeightNative(long j2, int i2) {
            VexFwkStream.setHeightNative(j2, i2);
        }

        /* access modifiers changed from: private */
        public final void setIdNative(long j2, int i2) {
            VexFwkStream.setIdNative(j2, i2);
        }

        /* access modifiers changed from: private */
        public final void setInoutDirectionNative(long j2, int i2) {
            VexFwkStream.setInoutDirectionNative(j2, i2);
        }

        /* access modifiers changed from: private */
        public final void setStreamTypeNative(long j2, int i2) {
            VexFwkStream.setStreamTypeNative(j2, i2);
        }

        /* access modifiers changed from: private */
        public final void setSurfaceNative(long j2, Surface surface) {
            VexFwkStream.setSurfaceNative(j2, surface);
        }

        /* access modifiers changed from: private */
        public final void setUsageNative(long j2, int i2) {
            VexFwkStream.setUsageNative(j2, i2);
        }

        /* access modifiers changed from: private */
        public final void setWidthNative(long j2, int i2) {
            VexFwkStream.setWidthNative(j2, i2);
        }

        private Companion() {
        }
    }

    /* access modifiers changed from: private */
    public static final native long createNative();

    /* access modifiers changed from: private */
    public static final native void deleteNative(long j2);

    /* access modifiers changed from: private */
    public static final native int getFormatNative(long j2);

    /* access modifiers changed from: private */
    public static final native int getHeightNative(long j2);

    /* access modifiers changed from: private */
    public static final native int getIdNative(long j2);

    /* access modifiers changed from: private */
    public static final native int getInoutDirectionNative(long j2);

    /* access modifiers changed from: private */
    public static final native int getStreamTypeNative(long j2);

    /* access modifiers changed from: private */
    public static final native Surface getSurfaceNative(long j2);

    /* access modifiers changed from: private */
    public static final native int getUsageNative(long j2);

    /* access modifiers changed from: private */
    public static final native int getWidthNative(long j2);

    /* access modifiers changed from: private */
    public static final native void setFormatNative(long j2, int i2);

    /* access modifiers changed from: private */
    public static final native void setHeightNative(long j2, int i2);

    /* access modifiers changed from: private */
    public static final native void setIdNative(long j2, int i2);

    /* access modifiers changed from: private */
    public static final native void setInoutDirectionNative(long j2, int i2);

    /* access modifiers changed from: private */
    public static final native void setStreamTypeNative(long j2, int i2);

    /* access modifiers changed from: private */
    public static final native void setSurfaceNative(long j2, Surface surface);

    /* access modifiers changed from: private */
    public static final native void setUsageNative(long j2, int i2);

    /* access modifiers changed from: private */
    public static final native void setWidthNative(long j2, int i2);

    public void close() {
        long j2 = this.nativeHandle;
        if (j2 != 0) {
            Companion.deleteNative(j2);
            this.nativeHandle = 0;
        }
    }

    public final int getFormat() {
        return Companion.getFormatNative(this.nativeHandle);
    }

    public final int getHeight() {
        return Companion.getHeightNative(this.nativeHandle);
    }

    public final int getId() {
        return Companion.getIdNative(this.nativeHandle);
    }

    public final VexFwkStreamInoutDirection getInoutDirection() {
        return VexFwkStreamInoutDirection.Companion.from(Companion.getInoutDirectionNative(this.nativeHandle));
    }

    public final long getNativeHandle() {
        return this.nativeHandle;
    }

    public final VexFwkStreamType getStreamType() {
        return VexFwkStreamType.Companion.from(Companion.getStreamTypeNative(this.nativeHandle));
    }

    public final Surface getSurface() {
        return Companion.getSurfaceNative(this.nativeHandle);
    }

    public final VexFwkStreamUsage getUsage() {
        return VexFwkStreamUsage.Companion.from(Companion.getUsageNative(this.nativeHandle));
    }

    public final int getWidth() {
        return Companion.getWidthNative(this.nativeHandle);
    }

    public final void setFormat(int i2) {
        Companion.setFormatNative(this.nativeHandle, i2);
    }

    public final void setHeight(int i2) {
        Companion.setHeightNative(this.nativeHandle, i2);
    }

    public final void setId(int i2) {
        Companion.setIdNative(this.nativeHandle, i2);
    }

    public final void setInoutDirection(VexFwkStreamInoutDirection vexFwkStreamInoutDirection) {
        j.e(vexFwkStreamInoutDirection, "value");
        Companion.setInoutDirectionNative(this.nativeHandle, vexFwkStreamInoutDirection.getValue());
    }

    public final void setNativeHandle(long j2) {
        this.nativeHandle = j2;
    }

    public final void setStreamType(VexFwkStreamType vexFwkStreamType) {
        j.e(vexFwkStreamType, "value");
        Companion.setStreamTypeNative(this.nativeHandle, vexFwkStreamType.getValue());
    }

    public final void setSurface(Surface surface) {
        if (surface != null) {
            Companion.setSurfaceNative(this.nativeHandle, surface);
        }
    }

    public final void setUsage(VexFwkStreamUsage vexFwkStreamUsage) {
        j.e(vexFwkStreamUsage, "value");
        Companion.setUsageNative(this.nativeHandle, vexFwkStreamUsage.getValue());
    }

    public final void setWidth(int i2) {
        Companion.setWidthNative(this.nativeHandle, i2);
    }
}
