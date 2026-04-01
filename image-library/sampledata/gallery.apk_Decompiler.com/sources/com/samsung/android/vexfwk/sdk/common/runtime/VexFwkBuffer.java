package com.samsung.android.vexfwk.sdk.common.runtime;

import De.b;
import He.t;
import android.graphics.Bitmap;
import android.hardware.HardwareBuffer;
import android.media.Image;
import com.samsung.android.vexfwk.hardware.VexFwkHardwareBufferNative;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.m;
import kotlin.jvm.internal.v;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000 \u001e2\u00060\u0001j\u0002`\u0002:\u0002\u001e\u001fB\t\b\u0016¢\u0006\u0004\b\u0003\u0010\u0004B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0003\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\u0004R+\u0010\u0006\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00058F@FX\u0002¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0007R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0016\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0019\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u001d\u001a\u00020\u001a8G¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006 "}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkBuffer;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "", "nativeHandle", "(J)V", "Lme/x;", "close", "<set-?>", "nativeHandle$delegate", "LDe/b;", "getNativeHandle", "()J", "setNativeHandle", "Landroid/hardware/HardwareBuffer;", "hardwareBufferCached", "Landroid/hardware/HardwareBuffer;", "", "getStreamId", "()I", "streamId", "getHardwareBuffer", "()Landroid/hardware/HardwareBuffer;", "hardwareBuffer", "Landroid/graphics/Bitmap;", "getBitmap", "()Landroid/graphics/Bitmap;", "bitmap", "Companion", "BitmapBuffer", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VexFwkBuffer implements AutoCloseable {
    static final /* synthetic */ t[] $$delegatedProperties = {v.f4727a.d(new m(VexFwkBuffer.class))};
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG = "VexFwkBuffer";
    private HardwareBuffer hardwareBufferCached;
    private final b nativeHandle$delegate = new B1.b(1);

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\f\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkBuffer$BitmapBuffer;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkBuffer;", "", "streamId", "Landroid/graphics/Bitmap;", "inoutBitmap", "<init>", "(ILandroid/graphics/Bitmap;)V", "Lme/x;", "close", "()V", "Landroid/graphics/Bitmap;", "bitmapHardware", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class BitmapBuffer extends VexFwkBuffer {
        private final Bitmap bitmapHardware;
        private final Bitmap inoutBitmap;

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Bitmap.Config.values().length];
                try {
                    iArr[Bitmap.Config.HARDWARE.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public BitmapBuffer(int i2, Bitmap bitmap) {
            int i7;
            Bitmap bitmap2;
            j.e(bitmap, "inoutBitmap");
            this.inoutBitmap = bitmap;
            Bitmap.Config config = bitmap.getConfig();
            if (config == null) {
                i7 = -1;
            } else {
                i7 = WhenMappings.$EnumSwitchMapping$0[config.ordinal()];
            }
            if (i7 == 1) {
                bitmap2 = bitmap;
            } else {
                bitmap2 = bitmap.copy(Bitmap.Config.HARDWARE, false);
                j.d(bitmap2, "copy(...)");
            }
            this.bitmapHardware = bitmap2;
            setNativeHandle(VexFwkBuffer.Companion.createBitmapNative(i2, bitmap, bitmap2));
        }

        public void close() {
            VexFwkBuffer.super.close();
            if (this.inoutBitmap.hashCode() != this.bitmapHardware.hashCode()) {
                this.bitmapHardware.recycle();
            }
        }
    }

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H ¢\u0006\u0004\b\t\u0010\nJ(\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH ¢\u0006\u0004\b\u000e\u0010\u000fJ\u0018\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH ¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\bH ¢\u0006\u0004\b\u0014\u0010\u0015J\u0018\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\bH ¢\u0006\u0004\b\u0016\u0010\u0017J\u0018\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\bH ¢\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001aH\u0007¢\u0006\u0004\b\u001d\u0010\u001eJ\u001f\u0010\u001d\u001a\u00020\u001f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u001d\u0010 J\u0017\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u001d\u0010!J%\u0010\u001d\u001a\u00020\u001c\"\u0004\b\u0000\u0010\"2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010#\u001a\u00028\u0000H\u0007¢\u0006\u0004\b\u001d\u0010$R\u001c\u0010'\u001a\n &*\u0004\u0018\u00010%0%8\u0002X\u0004¢\u0006\u0006\n\u0004\b'\u0010(¨\u0006)"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkBuffer$Companion;", "", "<init>", "()V", "", "streamId", "Landroid/hardware/HardwareBuffer;", "hardwareBuffer", "", "createNative", "(ILandroid/hardware/HardwareBuffer;)J", "Landroid/graphics/Bitmap;", "bitmap", "bitmapHardware", "createBitmapNative", "(ILandroid/graphics/Bitmap;Landroid/graphics/Bitmap;)J", "bufferHandle", "cloneNative", "(J)J", "Lme/x;", "deleteNative", "(J)V", "getStreamIdNative", "(J)I", "getHardwareBufferNative", "(J)Landroid/hardware/HardwareBuffer;", "Landroid/media/Image;", "image", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkBuffer;", "from", "(ILandroid/media/Image;)Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkBuffer;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkBuffer$BitmapBuffer;", "(ILandroid/graphics/Bitmap;)Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkBuffer$BitmapBuffer;", "(I)Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkBuffer;", "T", "buffer", "(ILjava/lang/Object;)Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkBuffer;", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final long cloneNative(long j2) {
            return VexFwkBuffer.cloneNative(j2);
        }

        /* access modifiers changed from: private */
        public final long createBitmapNative(int i2, Bitmap bitmap, Bitmap bitmap2) {
            return VexFwkBuffer.createBitmapNative(i2, bitmap, bitmap2);
        }

        private final long createNative(int i2, HardwareBuffer hardwareBuffer) {
            return VexFwkBuffer.createNative(i2, hardwareBuffer);
        }

        /* access modifiers changed from: private */
        public final void deleteNative(long j2) {
            VexFwkBuffer.deleteNative(j2);
        }

        /* access modifiers changed from: private */
        public final HardwareBuffer getHardwareBufferNative(long j2) {
            return VexFwkBuffer.getHardwareBufferNative(j2);
        }

        /* access modifiers changed from: private */
        public final int getStreamIdNative(long j2) {
            return VexFwkBuffer.getStreamIdNative(j2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x002d, code lost:
            throw r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0029, code lost:
            r3 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x002a, code lost:
            He.F.u(r4, r2);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer from(int r3, android.media.Image r4) {
            /*
                r2 = this;
                java.lang.String r2 = "image"
                kotlin.jvm.internal.j.e(r4, r2)
                com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer r2 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer
                r2.<init>()
                android.hardware.HardwareBuffer r4 = r4.getHardwareBuffer()
                kotlin.jvm.internal.j.b(r4)
                com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer$Companion r0 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer.Companion     // Catch:{ all -> 0x0027 }
                long r0 = r0.createNative(r3, r4)     // Catch:{ all -> 0x0027 }
                java.lang.Long r3 = java.lang.Long.valueOf(r0)     // Catch:{ all -> 0x0027 }
                r0 = 0
                He.F.u(r4, r0)
                long r3 = r3.longValue()
                r2.setNativeHandle(r3)
                return r2
            L_0x0027:
                r2 = move-exception
                throw r2     // Catch:{ all -> 0x0029 }
            L_0x0029:
                r3 = move-exception
                He.F.u(r4, r2)
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer.Companion.from(int, android.media.Image):com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer");
        }

        private Companion() {
        }

        public final BitmapBuffer from(int i2, Bitmap bitmap) {
            j.e(bitmap, "bitmap");
            return new BitmapBuffer(i2, bitmap);
        }

        public final VexFwkBuffer from(int i2) {
            VexFwkBuffer vexFwkBuffer = new VexFwkBuffer();
            vexFwkBuffer.setNativeHandle(VexFwkBuffer.Companion.createNative(i2, (HardwareBuffer) null));
            return vexFwkBuffer;
        }

        public final <T> VexFwkBuffer from(int i2, T t) {
            if (t instanceof Image) {
                return from(i2, (Image) t);
            }
            if (t instanceof Bitmap) {
                return from(i2, (Bitmap) t);
            }
            throw new IllegalArgumentException("Unsupported buffer type(" + t + ")");
        }
    }

    public VexFwkBuffer() {
    }

    /* access modifiers changed from: private */
    public static final native long cloneNative(long j2);

    /* access modifiers changed from: private */
    public static final native long createBitmapNative(int i2, Bitmap bitmap, Bitmap bitmap2);

    /* access modifiers changed from: private */
    public static final native long createNative(int i2, HardwareBuffer hardwareBuffer);

    /* access modifiers changed from: private */
    public static final native void deleteNative(long j2);

    public static final BitmapBuffer from(int i2, Bitmap bitmap) {
        return Companion.from(i2, bitmap);
    }

    /* access modifiers changed from: private */
    public static final native HardwareBuffer getHardwareBufferNative(long j2);

    /* access modifiers changed from: private */
    public static final native int getStreamIdNative(long j2);

    public void close() {
        HardwareBuffer hardwareBuffer = this.hardwareBufferCached;
        if (hardwareBuffer != null) {
            hardwareBuffer.close();
        }
        this.hardwareBufferCached = null;
        if (getNativeHandle() != 0) {
            Companion.deleteNative(getNativeHandle());
            setNativeHandle(0);
        }
    }

    public final Bitmap getBitmap() {
        return VexFwkHardwareBufferNative.Companion.convertToBitmap(getHardwareBuffer(), 0, 0, getHardwareBuffer().getWidth(), getHardwareBuffer().getHeight());
    }

    public final HardwareBuffer getHardwareBuffer() {
        HardwareBuffer hardwareBuffer = this.hardwareBufferCached;
        if (hardwareBuffer != null) {
            return hardwareBuffer;
        }
        HardwareBuffer access$getHardwareBufferNative = Companion.getHardwareBufferNative(getNativeHandle());
        this.hardwareBufferCached = access$getHardwareBufferNative;
        return access$getHardwareBufferNative;
    }

    public final long getNativeHandle() {
        return ((Number) this.nativeHandle$delegate.f(this, $$delegatedProperties[0])).longValue();
    }

    public final int getStreamId() {
        return Companion.getStreamIdNative(getNativeHandle());
    }

    public final void setNativeHandle(long j2) {
        this.nativeHandle$delegate.e(Long.valueOf(j2), $$delegatedProperties[0]);
    }

    public static final VexFwkBuffer from(int i2) {
        return Companion.from(i2);
    }

    public static final VexFwkBuffer from(int i2, Image image) {
        return Companion.from(i2, image);
    }

    public static final <T> VexFwkBuffer from(int i2, T t) {
        return Companion.from(i2, t);
    }

    public VexFwkBuffer(long j2) {
        setNativeHandle(Companion.cloneNative(j2));
    }
}
