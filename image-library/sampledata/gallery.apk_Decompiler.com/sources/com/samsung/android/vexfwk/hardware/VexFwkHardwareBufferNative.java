package com.samsung.android.vexfwk.hardware;

import A.a;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import android.hardware.HardwareBuffer;
import android.util.Log;
import com.samsung.android.vexfwk.bitmap.VexFwkBitmap;
import com.samsung.android.vexfwk.log.VexFwkLog;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.k;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/hardware/VexFwkHardwareBufferNative;", "", "<init>", "()V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkHardwareBufferNative {
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public static final String TAG = "VexFwkHardwareBufferNative";

    @Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH ¢\u0006\u0004\b\f\u0010\rJ0\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH ¢\u0006\u0004\b\u000e\u0010\u000fJ \u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0010H ¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0013H ¢\u0006\u0004\b\u0015\u0010\u0016J \u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0013H ¢\u0006\u0004\b\u0018\u0010\u0016J\u0018\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H ¢\u0006\u0004\b\u0019\u0010\u001aJ \u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0010H ¢\u0006\u0004\b\u001c\u0010\u0012J \u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H ¢\u0006\u0004\b\u001d\u0010\u001eJ \u0010 \u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u001f2\u0006\u0010\u0005\u001a\u00020\u0004H ¢\u0006\u0004\b \u0010!JM\u0010)\u001a\u00020\u000b2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00060\"2\u0006\u0010$\u001a\u00020\u00102\u0006\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u00102\u0006\u0010(\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b)\u0010*JM\u00101\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00042\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00060\"2\u0006\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u00102\u0006\u0010.\u001a\u00020\u00102\u0006\u0010/\u001a\u00020\u00102\u0006\u00100\u001a\u00020\bH\u0007¢\u0006\u0004\b1\u00102J\u001f\u00103\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0010H\u0007¢\u0006\u0004\b3\u0010\u0012J\u0017\u00104\u001a\u00020\u001f2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b4\u00105J!\u00109\u001a\u00020\u00132\u0006\u00106\u001a\u00020\u00042\b\b\u0002\u00108\u001a\u000207H\u0007¢\u0006\u0004\b9\u0010:J7\u00109\u001a\u00020\u00132\u0006\u00106\u001a\u00020\u00042\u0006\u0010;\u001a\u00020\b2\u0006\u0010<\u001a\u00020\b2\u0006\u0010=\u001a\u00020\b2\u0006\u0010>\u001a\u00020\bH\u0007¢\u0006\u0004\b9\u0010?J\u001f\u0010A\u001a\u00020\u000b2\u0006\u0010@\u001a\u00020\u00042\u0006\u00106\u001a\u00020\u0013H\u0007¢\u0006\u0004\bA\u0010\u0016J\u001f\u0010C\u001a\u00020B2\u0006\u0010@\u001a\u00020\u00042\u0006\u00106\u001a\u00020\u0013H\u0007¢\u0006\u0004\bC\u0010DJ\u001f\u0010E\u001a\u00020\u000b2\u0006\u00106\u001a\u00020\u00042\u0006\u0010@\u001a\u00020\u0013H\u0007¢\u0006\u0004\bE\u0010\u0016J\u0017\u0010F\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\bF\u0010\u001aJ\u0017\u0010G\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\bG\u0010HJ\u001f\u0010I\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\bI\u0010\u001eJN\u0010J\u001a\u00020\b2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00060\"2\u0006\u0010$\u001a\u00020\u00102\u0006\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u00102\u0006\u0010(\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004H ¢\u0006\u0004\bJ\u0010KJN\u0010L\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00042\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00060\"2\u0006\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u00102\u0006\u0010.\u001a\u00020\u00102\u0006\u0010/\u001a\u00020\u00102\u0006\u00100\u001a\u00020\bH ¢\u0006\u0004\bL\u0010MR\u001c\u0010P\u001a\n O*\u0004\u0018\u00010N0N8\u0002X\u0004¢\u0006\u0006\n\u0004\bP\u0010Q¨\u0006R"}, d2 = {"Lcom/samsung/android/vexfwk/hardware/VexFwkHardwareBufferNative$Companion;", "", "<init>", "()V", "Landroid/hardware/HardwareBuffer;", "srcBuffer", "Ljava/nio/ByteBuffer;", "dstBuffer", "", "width", "height", "Lme/x;", "copyHardwareBufferToByteBufferNative", "(Landroid/hardware/HardwareBuffer;Ljava/nio/ByteBuffer;II)V", "copyByteBufferToHardwareBufferNative", "(Ljava/nio/ByteBuffer;Landroid/hardware/HardwareBuffer;II)V", "", "copyFromArgbIntArrayNative", "(Landroid/hardware/HardwareBuffer;[I)V", "Landroid/graphics/Bitmap;", "srcBitmap", "copyFromBitmapNative", "(Landroid/hardware/HardwareBuffer;Landroid/graphics/Bitmap;)V", "dstBitmap", "copyToBitmapNative", "convertToByteBufferNative", "(Landroid/hardware/HardwareBuffer;)Ljava/nio/ByteBuffer;", "dstArray", "copyToArgbIntArrayNative", "copyHardwareBufferToHardwareBufferNative", "(Landroid/hardware/HardwareBuffer;Landroid/hardware/HardwareBuffer;)V", "", "copyHardwareBufferToFloatArrayNative", "([FLandroid/hardware/HardwareBuffer;)V", "", "dstBuffers", "dstWidths", "dstHeights", "dstStrides", "dstSlices", "dstFormat", "copyToByteBuffer", "([Ljava/nio/ByteBuffer;[I[I[I[IILandroid/hardware/HardwareBuffer;)V", "srcBuffers", "srcWidths", "srcHeights", "srcStrides", "srcSlices", "srcFormat", "copyFromByteBuffer", "(Landroid/hardware/HardwareBuffer;[Ljava/nio/ByteBuffer;[I[I[I[II)V", "copyFromArgbIntArray", "convertToFloatArray", "(Landroid/hardware/HardwareBuffer;)[F", "src", "", "toMutable", "convertToBitmap", "(Landroid/hardware/HardwareBuffer;Z)Landroid/graphics/Bitmap;", "left", "top", "right", "bottom", "(Landroid/hardware/HardwareBuffer;IIII)Landroid/graphics/Bitmap;", "dst", "copyFromBitmap", "Landroid/graphics/Rect;", "copyFromBitmapByFitInto", "(Landroid/hardware/HardwareBuffer;Landroid/graphics/Bitmap;)Landroid/graphics/Rect;", "copyToBitmap", "convertToByteBuffer", "convertToArgbIntArray", "(Landroid/hardware/HardwareBuffer;)[I", "copyHardwareBufferToHardwareBuffer", "copyHardwareBufferToByteBufferV1Native", "(Lkotlin/Array;[I[I[I[IILandroid/hardware/HardwareBuffer;)I", "copyByteBufferToHardwareBufferV1Native", "(Landroid/hardware/HardwareBuffer;Lkotlin/Array;[I[I[I[II)I", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public static /* synthetic */ Bitmap convertToBitmap$default(Companion companion, HardwareBuffer hardwareBuffer, boolean z, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                z = true;
            }
            return companion.convertToBitmap(hardwareBuffer, z);
        }

        private final ByteBuffer convertToByteBufferNative(HardwareBuffer hardwareBuffer) {
            return VexFwkHardwareBufferNative.convertToByteBufferNative(hardwareBuffer);
        }

        private final void copyByteBufferToHardwareBufferNative(ByteBuffer byteBuffer, HardwareBuffer hardwareBuffer, int i2, int i7) {
            VexFwkHardwareBufferNative.copyByteBufferToHardwareBufferNative(byteBuffer, hardwareBuffer, i2, i7);
        }

        private final int copyByteBufferToHardwareBufferV1Native(HardwareBuffer hardwareBuffer, ByteBuffer[] byteBufferArr, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int i2) {
            return VexFwkHardwareBufferNative.copyByteBufferToHardwareBufferV1Native(hardwareBuffer, byteBufferArr, iArr, iArr2, iArr3, iArr4, i2);
        }

        private final void copyFromArgbIntArrayNative(HardwareBuffer hardwareBuffer, int[] iArr) {
            VexFwkHardwareBufferNative.copyFromArgbIntArrayNative(hardwareBuffer, iArr);
        }

        private final void copyFromBitmapNative(HardwareBuffer hardwareBuffer, Bitmap bitmap) {
            VexFwkHardwareBufferNative.copyFromBitmapNative(hardwareBuffer, bitmap);
        }

        private final void copyHardwareBufferToByteBufferNative(HardwareBuffer hardwareBuffer, ByteBuffer byteBuffer, int i2, int i7) {
            VexFwkHardwareBufferNative.copyHardwareBufferToByteBufferNative(hardwareBuffer, byteBuffer, i2, i7);
        }

        private final int copyHardwareBufferToByteBufferV1Native(ByteBuffer[] byteBufferArr, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int i2, HardwareBuffer hardwareBuffer) {
            return VexFwkHardwareBufferNative.copyHardwareBufferToByteBufferV1Native(byteBufferArr, iArr, iArr2, iArr3, iArr4, i2, hardwareBuffer);
        }

        private final void copyHardwareBufferToFloatArrayNative(float[] fArr, HardwareBuffer hardwareBuffer) {
            VexFwkHardwareBufferNative.copyHardwareBufferToFloatArrayNative(fArr, hardwareBuffer);
        }

        private final void copyHardwareBufferToHardwareBufferNative(HardwareBuffer hardwareBuffer, HardwareBuffer hardwareBuffer2) {
            VexFwkHardwareBufferNative.copyHardwareBufferToHardwareBufferNative(hardwareBuffer, hardwareBuffer2);
        }

        private final void copyToArgbIntArrayNative(HardwareBuffer hardwareBuffer, int[] iArr) {
            VexFwkHardwareBufferNative.copyToArgbIntArrayNative(hardwareBuffer, iArr);
        }

        private final void copyToBitmapNative(HardwareBuffer hardwareBuffer, Bitmap bitmap) {
            VexFwkHardwareBufferNative.copyToBitmapNative(hardwareBuffer, bitmap);
        }

        public final int[] convertToArgbIntArray(HardwareBuffer hardwareBuffer) {
            j.e(hardwareBuffer, "srcBuffer");
            int[] iArr = new int[(hardwareBuffer.getHeight() * hardwareBuffer.getWidth())];
            VexFwkHardwareBufferNative.Companion.copyToArgbIntArrayNative(hardwareBuffer, iArr);
            return iArr;
        }

        public final Bitmap convertToBitmap(HardwareBuffer hardwareBuffer, boolean z) {
            Bitmap bitmap;
            j.e(hardwareBuffer, "src");
            String access$getTAG$cp = VexFwkHardwareBufferNative.TAG;
            Log.d(access$getTAG$cp, "convertToBitmap!! toMutable " + z);
            try {
                bitmap = Bitmap.wrapHardwareBuffer(hardwareBuffer, (ColorSpace) null);
                j.b(bitmap);
            } catch (Exception e) {
                String access$getTAG$cp2 = VexFwkHardwareBufferNative.TAG;
                String message = e.getMessage();
                Log.d(access$getTAG$cp2, "WrapHardwareBuffer failed: " + message + " Create Bitmap instead.");
                bitmap = Bitmap.createBitmap(hardwareBuffer.getWidth(), hardwareBuffer.getHeight(), Bitmap.Config.ARGB_8888);
                Companion companion = VexFwkHardwareBufferNative.Companion;
                j.b(bitmap);
                companion.copyToBitmap(hardwareBuffer, bitmap);
            }
            if (!z || bitmap.isMutable()) {
                return bitmap;
            }
            Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            j.d(copy, "copy(...)");
            return copy;
        }

        public final ByteBuffer convertToByteBuffer(HardwareBuffer hardwareBuffer) {
            j.e(hardwareBuffer, "srcBuffer");
            return convertToByteBufferNative(hardwareBuffer);
        }

        public final float[] convertToFloatArray(HardwareBuffer hardwareBuffer) {
            j.e(hardwareBuffer, "srcBuffer");
            if (hardwareBuffer.getFormat() == 1) {
                float[] fArr = new float[(hardwareBuffer.getHeight() * hardwareBuffer.getWidth())];
                VexFwkHardwareBufferNative.Companion.copyHardwareBufferToFloatArrayNative(fArr, hardwareBuffer);
                return fArr;
            }
            throw new IllegalStateException("Only support RGBA_8888 format.");
        }

        public final void copyFromArgbIntArray(HardwareBuffer hardwareBuffer, int[] iArr) {
            j.e(hardwareBuffer, "dstBuffer");
            j.e(iArr, "srcBuffer");
            if (hardwareBuffer.getFormat() == 1) {
                copyFromArgbIntArrayNative(hardwareBuffer, iArr);
                return;
            }
            throw new IllegalArgumentException("dstHwBuffer should be RGBA_8888 format.");
        }

        public final void copyFromBitmap(HardwareBuffer hardwareBuffer, Bitmap bitmap) {
            j.e(hardwareBuffer, "dst");
            j.e(bitmap, "src");
            copyFromBitmapNative(hardwareBuffer, bitmap);
        }

        public final Rect copyFromBitmapByFitInto(HardwareBuffer hardwareBuffer, Bitmap bitmap) {
            Bitmap bitmap2;
            j.e(hardwareBuffer, "dst");
            j.e(bitmap, "src");
            if (bitmap.getWidth() > hardwareBuffer.getWidth() || bitmap.getHeight() > hardwareBuffer.getHeight()) {
                VexFwkLog.Companion.d(VexFwkHardwareBufferNative.TAG, "Resize srcBitmap to fit into dstHardwareBuffer");
                bitmap2 = VexFwkBitmap.Companion.resizeBitmap(bitmap, hardwareBuffer.getWidth(), hardwareBuffer.getHeight());
            } else {
                bitmap2 = bitmap;
            }
            copyFromBitmap(hardwareBuffer, bitmap2);
            Rect rect = new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight());
            if (!bitmap2.equals(bitmap)) {
                bitmap2.recycle();
            }
            return rect;
        }

        public final void copyFromByteBuffer(HardwareBuffer hardwareBuffer, ByteBuffer[] byteBufferArr, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int i2) {
            j.e(hardwareBuffer, "dstBuffer");
            j.e(byteBufferArr, "srcBuffers");
            j.e(iArr, "srcWidths");
            j.e(iArr2, "srcHeights");
            j.e(iArr3, "srcStrides");
            j.e(iArr4, "srcSlices");
            int length = byteBufferArr.length;
            int i7 = 0;
            while (i7 < length) {
                if (byteBufferArr[i7].isDirect()) {
                    i7++;
                } else {
                    throw new IllegalStateException("srcBuffer should be direct buffer");
                }
            }
            if (iArr.length == 3 && iArr2.length == 3 && iArr3.length == 3 && iArr4.length == 3) {
                copyByteBufferToHardwareBufferV1Native(hardwareBuffer, byteBufferArr, iArr, iArr2, iArr3, iArr4, i2);
                return;
            }
            int length2 = iArr.length;
            int length3 = iArr2.length;
            int length4 = iArr3.length;
            int length5 = iArr4.length;
            StringBuilder h5 = a.h(length2, length3, "widths ", " heights ", " strides ");
            h5.append(length4);
            h5.append(" slices ");
            h5.append(length5);
            throw new IllegalStateException(h5.toString().toString());
        }

        public final void copyHardwareBufferToHardwareBuffer(HardwareBuffer hardwareBuffer, HardwareBuffer hardwareBuffer2) {
            j.e(hardwareBuffer, "dstBuffer");
            j.e(hardwareBuffer2, "srcBuffer");
            copyHardwareBufferToHardwareBufferNative(hardwareBuffer, hardwareBuffer2);
        }

        public final void copyToBitmap(HardwareBuffer hardwareBuffer, Bitmap bitmap) {
            j.e(hardwareBuffer, "src");
            j.e(bitmap, "dst");
            copyToBitmapNative(hardwareBuffer, bitmap);
        }

        public final void copyToByteBuffer(ByteBuffer[] byteBufferArr, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int i2, HardwareBuffer hardwareBuffer) {
            j.e(byteBufferArr, "dstBuffers");
            j.e(iArr, "dstWidths");
            j.e(iArr2, "dstHeights");
            j.e(iArr3, "dstStrides");
            j.e(iArr4, "dstSlices");
            j.e(hardwareBuffer, "srcBuffer");
            int length = byteBufferArr.length;
            int i7 = 0;
            while (i7 < length) {
                if (byteBufferArr[i7].isDirect()) {
                    i7++;
                } else {
                    throw new IllegalStateException("dstBuffer should be direct buffer");
                }
            }
            if (iArr.length == 3 && iArr2.length == 3 && iArr3.length == 3 && iArr4.length == 3) {
                copyHardwareBufferToByteBufferV1Native(byteBufferArr, iArr, iArr2, iArr3, iArr4, i2, hardwareBuffer);
                return;
            }
            int length2 = iArr.length;
            int length3 = iArr2.length;
            int length4 = iArr3.length;
            int length5 = iArr4.length;
            StringBuilder h5 = a.h(length2, length3, "widths ", " heights ", " strides ");
            h5.append(length4);
            h5.append(" slices ");
            h5.append(length5);
            throw new IllegalStateException(h5.toString().toString());
        }

        private Companion() {
        }

        public final Bitmap convertToBitmap(HardwareBuffer hardwareBuffer, int i2, int i7, int i8, int i10) {
            Bitmap bitmap;
            j.e(hardwareBuffer, "src");
            try {
                Bitmap wrapHardwareBuffer = Bitmap.wrapHardwareBuffer(hardwareBuffer, (ColorSpace) null);
                if (wrapHardwareBuffer != null) {
                    bitmap = wrapHardwareBuffer;
                    Throwable a7 = k.a(bitmap);
                    Bitmap bitmap2 = bitmap;
                    if (a7 != null) {
                        Log.w(VexFwkHardwareBufferNative.TAG, "wrapHardwareBuffer() is failed. Create & copy Bitmap instead.");
                        Bitmap createBitmap = Bitmap.createBitmap(hardwareBuffer.getWidth(), hardwareBuffer.getHeight(), Bitmap.Config.ARGB_8888);
                        j.d(createBitmap, "createBitmap(width, height, config)");
                        VexFwkHardwareBufferNative.Companion.copyToBitmap(hardwareBuffer, createBitmap);
                        bitmap2 = createBitmap;
                    }
                    Bitmap createBitmap2 = Bitmap.createBitmap(i8 - i2, i10 - i7, Bitmap.Config.ARGB_8888);
                    j.d(createBitmap2, "createBitmap(width, height, config)");
                    VexFwkBitmap.Companion.copyBitmap((Bitmap) bitmap2, createBitmap2, i2, i7, i8, i10);
                    return createBitmap2;
                }
                throw new IllegalStateException("wrapHardwareBuffer() is failed");
            } catch (Throwable th) {
                bitmap = L2.a.l(th);
            }
        }
    }

    public static final int[] convertToArgbIntArray(HardwareBuffer hardwareBuffer) {
        return Companion.convertToArgbIntArray(hardwareBuffer);
    }

    public static final Bitmap convertToBitmap(HardwareBuffer hardwareBuffer, int i2, int i7, int i8, int i10) {
        return Companion.convertToBitmap(hardwareBuffer, i2, i7, i8, i10);
    }

    public static final ByteBuffer convertToByteBuffer(HardwareBuffer hardwareBuffer) {
        return Companion.convertToByteBuffer(hardwareBuffer);
    }

    /* access modifiers changed from: private */
    public static final native ByteBuffer convertToByteBufferNative(HardwareBuffer hardwareBuffer);

    public static final float[] convertToFloatArray(HardwareBuffer hardwareBuffer) {
        return Companion.convertToFloatArray(hardwareBuffer);
    }

    /* access modifiers changed from: private */
    public static final native void copyByteBufferToHardwareBufferNative(ByteBuffer byteBuffer, HardwareBuffer hardwareBuffer, int i2, int i7);

    /* access modifiers changed from: private */
    public static final native int copyByteBufferToHardwareBufferV1Native(HardwareBuffer hardwareBuffer, ByteBuffer[] byteBufferArr, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int i2);

    public static final void copyFromArgbIntArray(HardwareBuffer hardwareBuffer, int[] iArr) {
        Companion.copyFromArgbIntArray(hardwareBuffer, iArr);
    }

    /* access modifiers changed from: private */
    public static final native void copyFromArgbIntArrayNative(HardwareBuffer hardwareBuffer, int[] iArr);

    public static final void copyFromBitmap(HardwareBuffer hardwareBuffer, Bitmap bitmap) {
        Companion.copyFromBitmap(hardwareBuffer, bitmap);
    }

    public static final Rect copyFromBitmapByFitInto(HardwareBuffer hardwareBuffer, Bitmap bitmap) {
        return Companion.copyFromBitmapByFitInto(hardwareBuffer, bitmap);
    }

    /* access modifiers changed from: private */
    public static final native void copyFromBitmapNative(HardwareBuffer hardwareBuffer, Bitmap bitmap);

    public static final void copyFromByteBuffer(HardwareBuffer hardwareBuffer, ByteBuffer[] byteBufferArr, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int i2) {
        Companion.copyFromByteBuffer(hardwareBuffer, byteBufferArr, iArr, iArr2, iArr3, iArr4, i2);
    }

    /* access modifiers changed from: private */
    public static final native void copyHardwareBufferToByteBufferNative(HardwareBuffer hardwareBuffer, ByteBuffer byteBuffer, int i2, int i7);

    /* access modifiers changed from: private */
    public static final native int copyHardwareBufferToByteBufferV1Native(ByteBuffer[] byteBufferArr, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int i2, HardwareBuffer hardwareBuffer);

    /* access modifiers changed from: private */
    public static final native void copyHardwareBufferToFloatArrayNative(float[] fArr, HardwareBuffer hardwareBuffer);

    public static final void copyHardwareBufferToHardwareBuffer(HardwareBuffer hardwareBuffer, HardwareBuffer hardwareBuffer2) {
        Companion.copyHardwareBufferToHardwareBuffer(hardwareBuffer, hardwareBuffer2);
    }

    /* access modifiers changed from: private */
    public static final native void copyHardwareBufferToHardwareBufferNative(HardwareBuffer hardwareBuffer, HardwareBuffer hardwareBuffer2);

    /* access modifiers changed from: private */
    public static final native void copyToArgbIntArrayNative(HardwareBuffer hardwareBuffer, int[] iArr);

    public static final void copyToBitmap(HardwareBuffer hardwareBuffer, Bitmap bitmap) {
        Companion.copyToBitmap(hardwareBuffer, bitmap);
    }

    /* access modifiers changed from: private */
    public static final native void copyToBitmapNative(HardwareBuffer hardwareBuffer, Bitmap bitmap);

    public static final void copyToByteBuffer(ByteBuffer[] byteBufferArr, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int i2, HardwareBuffer hardwareBuffer) {
        Companion.copyToByteBuffer(byteBufferArr, iArr, iArr2, iArr3, iArr4, i2, hardwareBuffer);
    }

    public static final Bitmap convertToBitmap(HardwareBuffer hardwareBuffer, boolean z) {
        return Companion.convertToBitmap(hardwareBuffer, z);
    }
}
