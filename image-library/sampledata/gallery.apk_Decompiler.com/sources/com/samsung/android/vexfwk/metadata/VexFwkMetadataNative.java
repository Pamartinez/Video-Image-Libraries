package com.samsung.android.vexfwk.metadata;

import L2.a;
import android.hardware.HardwareBuffer;
import android.util.Log;
import androidx.core.util.Supplier;
import c0.C0086a;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.k;
import me.x;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u0000 12\u00020\u0001:\u00011B\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003B\u0011\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0002\u0010\bB\u0013\b\u0016\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0002\u0010\u000bJ&\u0010\u000f\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J,\u0010\u0013\u001a\u00020\u0012\"\u0004\b\u0000\u0010\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\r2\u0006\u0010\u0011\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J/\u0010\u0017\u001a\u00028\u0000\"\u0004\b\u0000\u0010\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\r2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015¢\u0006\u0004\b\u0017\u0010\u0018J!\u0010\u0019\u001a\u00028\u0000\"\u0004\b\u0000\u0010\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\r¢\u0006\u0004\b\u0019\u0010\u0010J\u000f\u0010\u001b\u001a\u0004\u0018\u00010\u001a¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u001d\u0010\u001eJ\r\u0010 \u001a\u00020\u001f¢\u0006\u0004\b \u0010!J\u0015\u0010#\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u001f¢\u0006\u0004\b#\u0010$J\r\u0010%\u001a\u00020\u0000¢\u0006\u0004\b%\u0010&J\u000f\u0010'\u001a\u00020\u0012H\u0004¢\u0006\u0004\b'\u0010\u0003R$\u0010(\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00068\u0006@BX\u000e¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+R\u0011\u0010-\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b,\u0010+R\u0011\u0010/\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b.\u0010+R\u0014\u0010\u0007\u001a\u00020\u00068BX\u0004¢\u0006\u0006\u001a\u0004\b0\u0010+¨\u00062"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "", "<init>", "()V", "other", "(Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;)V", "", "rawHandle", "(J)V", "Landroid/hardware/HardwareBuffer;", "hardwareBuffer", "(Landroid/hardware/HardwareBuffer;)V", "T", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "key", "get", "(Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;)Ljava/lang/Object;", "value", "Lme/x;", "set", "(Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;Ljava/lang/Object;)V", "Landroidx/core/util/Supplier;", "defaultValue", "getOrElse", "(Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;Landroidx/core/util/Supplier;)Ljava/lang/Object;", "getOrThrow", "", "copyToByteArray", "()[B", "copyToHardwareBuffer", "()Landroid/hardware/HardwareBuffer;", "Ljava/nio/ByteBuffer;", "lock", "()Ljava/nio/ByteBuffer;", "buffer", "unlock", "(Ljava/nio/ByteBuffer;)V", "clone", "()Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "finalize", "nativeHandle", "J", "getNativeHandle", "()J", "getBufferSize", "bufferSize", "getBufferCompactSize", "bufferCompactSize", "getRawHandle", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkMetadataNative {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG = "VexFwkMetadataNative";
    private long nativeHandle;

    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u0012\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\f\u0010\tJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0012H ¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0012H ¢\u0006\u0004\b\u0016\u0010\u0017J\u0018\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0012H ¢\u0006\u0004\b\u0018\u0010\u0019J\u0018\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u0012H ¢\u0006\u0004\b\u001a\u0010\u0017J\u0018\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u0012H ¢\u0006\u0004\b\u001b\u0010\u0017J \u0010\u001c\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0012H ¢\u0006\u0004\b\u001c\u0010\u001dJ \u0010\u001e\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u0012H ¢\u0006\u0004\b\u001e\u0010\u001dJ\"\u0010!\u001a\u0004\u0018\u00010 2\u0006\u0010\u000b\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u000fH ¢\u0006\u0004\b!\u0010\"J(\u0010#\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020 H ¢\u0006\u0004\b#\u0010$J \u0010%\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u000fH ¢\u0006\u0004\b%\u0010&J\u0018\u0010(\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020\rH ¢\u0006\u0004\b(\u0010\u0011J \u0010)\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\u00122\u0006\u0010'\u001a\u00020\rH ¢\u0006\u0004\b)\u0010*J\u0018\u0010,\u001a\u00020+2\u0006\u0010\u000b\u001a\u00020\u0012H ¢\u0006\u0004\b,\u0010-J \u0010/\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00122\u0006\u0010.\u001a\u00020+H ¢\u0006\u0004\b/\u00100J\u001a\u00101\u001a\u0004\u0018\u00010 2\u0006\u0010\u000b\u001a\u00020\u0012H ¢\u0006\u0004\b1\u00102J\u001a\u00104\u001a\u0004\u0018\u0001032\u0006\u0010\u000b\u001a\u00020\u0012H ¢\u0006\u0004\b4\u00105J\u0018\u00106\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u0012H ¢\u0006\u0004\b6\u0010\u0017J\u0018\u00107\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u0012H ¢\u0006\u0004\b7\u0010\u0017J\u0018\u00108\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020 H ¢\u0006\u0004\b8\u00109J\u0018\u0010;\u001a\u00020\u00122\u0006\u0010:\u001a\u000203H ¢\u0006\u0004\b;\u0010<R\u001c\u0010>\u001a\n =*\u0004\u0018\u00010\r0\r8\u0002X\u0004¢\u0006\u0006\n\u0004\b>\u0010?¨\u0006@"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative$Companion;", "", "<init>", "()V", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "a", "b", "Lme/x;", "swap", "(Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;)V", "dst", "src", "update", "", "keyName", "", "getTag", "(Ljava/lang/String;)I", "", "createMetadataNative", "()J", "other", "cloneMetadataNative", "(J)J", "freeMetadataNative", "(J)V", "getBufferSizeNative", "getBufferCompactSizeNative", "swapNative", "(JJ)V", "updateNative", "tag", "", "readValuesNative", "(JI)[B", "writeValuesNative", "(JI[B)V", "getTypeFromTagLocalNative", "(JI)I", "key", "getTagFromKeyNative", "getTagFromKeyLocalNative", "(JLjava/lang/String;)I", "Ljava/nio/ByteBuffer;", "getAndLockByteBufferNative", "(J)Ljava/nio/ByteBuffer;", "buffer", "unlockByteBufferNative", "(JLjava/nio/ByteBuffer;)V", "copyToByteArrayNative", "(J)[B", "Landroid/hardware/HardwareBuffer;", "copyToHardwareBufferNative", "(J)Landroid/hardware/HardwareBuffer;", "cloneMetadataRawNative", "getRawHandleNative", "cloneMetadataFromByteArrayNative", "([B)J", "hardwareBuffer", "cloneMetadataFromHardwareBufferNative", "(Landroid/hardware/HardwareBuffer;)J", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private final long cloneMetadataFromByteArrayNative(byte[] bArr) {
            return VexFwkMetadataNative.cloneMetadataFromByteArrayNative(bArr);
        }

        /* access modifiers changed from: private */
        public final long cloneMetadataFromHardwareBufferNative(HardwareBuffer hardwareBuffer) {
            return VexFwkMetadataNative.cloneMetadataFromHardwareBufferNative(hardwareBuffer);
        }

        private final long cloneMetadataNative(long j2) {
            return VexFwkMetadataNative.cloneMetadataNative(j2);
        }

        /* access modifiers changed from: private */
        public final long cloneMetadataRawNative(long j2) {
            return VexFwkMetadataNative.cloneMetadataRawNative(j2);
        }

        /* access modifiers changed from: private */
        public final byte[] copyToByteArrayNative(long j2) {
            return VexFwkMetadataNative.copyToByteArrayNative(j2);
        }

        /* access modifiers changed from: private */
        public final HardwareBuffer copyToHardwareBufferNative(long j2) {
            return VexFwkMetadataNative.copyToHardwareBufferNative(j2);
        }

        /* access modifiers changed from: private */
        public final long createMetadataNative() {
            return VexFwkMetadataNative.createMetadataNative();
        }

        /* access modifiers changed from: private */
        public final void freeMetadataNative(long j2) {
            VexFwkMetadataNative.freeMetadataNative(j2);
        }

        /* access modifiers changed from: private */
        public final ByteBuffer getAndLockByteBufferNative(long j2) {
            return VexFwkMetadataNative.getAndLockByteBufferNative(j2);
        }

        /* access modifiers changed from: private */
        public final long getBufferCompactSizeNative(long j2) {
            return VexFwkMetadataNative.getBufferCompactSizeNative(j2);
        }

        /* access modifiers changed from: private */
        public final long getBufferSizeNative(long j2) {
            return VexFwkMetadataNative.getBufferSizeNative(j2);
        }

        /* access modifiers changed from: private */
        public final long getRawHandleNative(long j2) {
            return VexFwkMetadataNative.getRawHandleNative(j2);
        }

        private final int getTagFromKeyLocalNative(long j2, String str) {
            return VexFwkMetadataNative.getTagFromKeyLocalNative(j2, str);
        }

        private final int getTagFromKeyNative(String str) {
            return VexFwkMetadataNative.getTagFromKeyNative(str);
        }

        private final int getTypeFromTagLocalNative(long j2, int i2) {
            return VexFwkMetadataNative.getTypeFromTagLocalNative(j2, i2);
        }

        /* access modifiers changed from: private */
        public final byte[] readValuesNative(long j2, int i2) {
            return VexFwkMetadataNative.readValuesNative(j2, i2);
        }

        private final void swapNative(long j2, long j3) {
            VexFwkMetadataNative.swapNative(j2, j3);
        }

        /* access modifiers changed from: private */
        public final void unlockByteBufferNative(long j2, ByteBuffer byteBuffer) {
            VexFwkMetadataNative.unlockByteBufferNative(j2, byteBuffer);
        }

        private final void updateNative(long j2, long j3) {
            VexFwkMetadataNative.updateNative(j2, j3);
        }

        /* access modifiers changed from: private */
        public final void writeValuesNative(long j2, int i2, byte[] bArr) {
            VexFwkMetadataNative.writeValuesNative(j2, i2, bArr);
        }

        public final int getTag(String str) {
            j.e(str, "keyName");
            return getTagFromKeyNative(str);
        }

        public final void swap(VexFwkMetadataNative vexFwkMetadataNative, VexFwkMetadataNative vexFwkMetadataNative2) {
            j.e(vexFwkMetadataNative, "a");
            j.e(vexFwkMetadataNative2, "b");
            swapNative(vexFwkMetadataNative.getNativeHandle(), vexFwkMetadataNative2.getNativeHandle());
        }

        public final void update(VexFwkMetadataNative vexFwkMetadataNative, VexFwkMetadataNative vexFwkMetadataNative2) {
            j.e(vexFwkMetadataNative, "dst");
            j.e(vexFwkMetadataNative2, "src");
            updateNative(vexFwkMetadataNative.getNativeHandle(), vexFwkMetadataNative2.getNativeHandle());
        }

        private Companion() {
        }
    }

    public VexFwkMetadataNative() {
        this(0);
    }

    /* access modifiers changed from: private */
    public static final native long cloneMetadataFromByteArrayNative(byte[] bArr);

    /* access modifiers changed from: private */
    public static final native long cloneMetadataFromHardwareBufferNative(HardwareBuffer hardwareBuffer);

    /* access modifiers changed from: private */
    public static final native long cloneMetadataNative(long j2);

    /* access modifiers changed from: private */
    public static final native long cloneMetadataRawNative(long j2);

    /* access modifiers changed from: private */
    public static final native byte[] copyToByteArrayNative(long j2);

    /* access modifiers changed from: private */
    public static final native HardwareBuffer copyToHardwareBufferNative(long j2);

    /* access modifiers changed from: private */
    public static final native long createMetadataNative();

    /* access modifiers changed from: private */
    public static final native void freeMetadataNative(long j2);

    /* access modifiers changed from: private */
    public static final native ByteBuffer getAndLockByteBufferNative(long j2);

    /* access modifiers changed from: private */
    public static final native long getBufferCompactSizeNative(long j2);

    /* access modifiers changed from: private */
    public static final native long getBufferSizeNative(long j2);

    private final long getRawHandle() {
        return Companion.getRawHandleNative(this.nativeHandle);
    }

    /* access modifiers changed from: private */
    public static final native long getRawHandleNative(long j2);

    public static final int getTag(String str) {
        return Companion.getTag(str);
    }

    /* access modifiers changed from: private */
    public static final native int getTagFromKeyLocalNative(long j2, String str);

    /* access modifiers changed from: private */
    public static final native int getTagFromKeyNative(String str);

    /* access modifiers changed from: private */
    public static final native int getTypeFromTagLocalNative(long j2, int i2);

    /* access modifiers changed from: private */
    public static final native byte[] readValuesNative(long j2, int i2);

    public static final void swap(VexFwkMetadataNative vexFwkMetadataNative, VexFwkMetadataNative vexFwkMetadataNative2) {
        Companion.swap(vexFwkMetadataNative, vexFwkMetadataNative2);
    }

    /* access modifiers changed from: private */
    public static final native void swapNative(long j2, long j3);

    /* access modifiers changed from: private */
    public static final native void unlockByteBufferNative(long j2, ByteBuffer byteBuffer);

    public static final void update(VexFwkMetadataNative vexFwkMetadataNative, VexFwkMetadataNative vexFwkMetadataNative2) {
        Companion.update(vexFwkMetadataNative, vexFwkMetadataNative2);
    }

    /* access modifiers changed from: private */
    public static final native void updateNative(long j2, long j3);

    /* access modifiers changed from: private */
    public static final native void writeValuesNative(long j2, int i2, byte[] bArr);

    public final VexFwkMetadataNative clone() {
        return new VexFwkMetadataNative(this);
    }

    public final byte[] copyToByteArray() {
        return Companion.copyToByteArrayNative(this.nativeHandle);
    }

    public final HardwareBuffer copyToHardwareBuffer() {
        return Companion.copyToHardwareBufferNative(this.nativeHandle);
    }

    public final void finalize() {
        Object obj;
        try {
            long j2 = this.nativeHandle;
            if (0 != j2) {
                Companion.freeMetadataNative(j2);
            }
            this.nativeHandle = 0;
            obj = x.f4917a;
        } catch (Throwable th) {
            obj = a.l(th);
        }
        if (k.a(obj) != null) {
            Log.e(TAG, "Failed to finalize VexFwkMetadataNativeKot");
        }
    }

    public final <T> T get(VexFwkMetadataKey<T> vexFwkMetadataKey) {
        j.e(vexFwkMetadataKey, "key");
        byte[] access$readValuesNative = Companion.readValuesNative(this.nativeHandle, vexFwkMetadataKey.getTag());
        if (access$readValuesNative == null) {
            return null;
        }
        return vexFwkMetadataKey.getConverter().from(access$readValuesNative);
    }

    public final long getBufferCompactSize() {
        return Companion.getBufferCompactSizeNative(this.nativeHandle);
    }

    public final long getBufferSize() {
        return Companion.getBufferSizeNative(this.nativeHandle);
    }

    public final long getNativeHandle() {
        return this.nativeHandle;
    }

    public final <T> T getOrElse(VexFwkMetadataKey<T> vexFwkMetadataKey, Supplier<T> supplier) {
        j.e(vexFwkMetadataKey, "key");
        j.e(supplier, "defaultValue");
        T t = get(vexFwkMetadataKey);
        if (t == null) {
            return supplier.get();
        }
        return t;
    }

    public final <T> T getOrThrow(VexFwkMetadataKey<T> vexFwkMetadataKey) {
        j.e(vexFwkMetadataKey, "key");
        T t = get(vexFwkMetadataKey);
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException("Missing metadata key: " + vexFwkMetadataKey);
    }

    public final ByteBuffer lock() {
        return Companion.getAndLockByteBufferNative(this.nativeHandle);
    }

    public final <T> void set(VexFwkMetadataKey<T> vexFwkMetadataKey, T t) {
        j.e(vexFwkMetadataKey, "key");
        Companion.writeValuesNative(this.nativeHandle, vexFwkMetadataKey.getTag(), vexFwkMetadataKey.getConverter().to(t));
    }

    public final void unlock(ByteBuffer byteBuffer) {
        j.e(byteBuffer, "buffer");
        Companion.unlockByteBufferNative(this.nativeHandle, byteBuffer);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public VexFwkMetadataNative(VexFwkMetadataNative vexFwkMetadataNative) {
        this(vexFwkMetadataNative.getRawHandle());
        j.e(vexFwkMetadataNative, "other");
    }

    public VexFwkMetadataNative(long j2) {
        long j3;
        if (j2 == 0) {
            j3 = Companion.createMetadataNative();
        } else {
            j3 = Companion.cloneMetadataRawNative(j2);
        }
        this.nativeHandle = j3;
        if (0 == j3) {
            throw new OutOfMemoryError("Failed to allocate native VexFwkMetadata.");
        }
    }

    public VexFwkMetadataNative(HardwareBuffer hardwareBuffer) {
        long j2;
        if (hardwareBuffer == null) {
            j2 = Companion.createMetadataNative();
        } else if (hardwareBuffer.getFormat() == 33) {
            j2 = Companion.cloneMetadataFromHardwareBufferNative(hardwareBuffer);
        } else {
            throw new RuntimeException(C0086a.i(hardwareBuffer.getFormat(), "Only BLOB format is supported for metadata input. : format="));
        }
        this.nativeHandle = j2;
        if (0 == j2) {
            throw new OutOfMemoryError("Failed to allocate native VexFwkMetadata.");
        }
    }
}
