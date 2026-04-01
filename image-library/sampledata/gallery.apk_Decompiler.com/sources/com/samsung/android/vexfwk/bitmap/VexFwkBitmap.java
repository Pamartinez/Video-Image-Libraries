package com.samsung.android.vexfwk.bitmap;

import A.a;
import He.F;
import a.C0068a;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.hardware.HardwareBuffer;
import android.util.Size;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/bitmap/VexFwkBitmap;", "", "<init>", "()V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkBitmap {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\t\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J@\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007H ¢\u0006\u0004\b\r\u0010\u000eJ@\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007H ¢\u0006\u0004\b\u0011\u0010\u0012J@\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007H ¢\u0006\u0004\b\u0014\u0010\u0015JH\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007H ¢\u0006\u0004\b\u0017\u0010\u0018JP\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00192\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0007H ¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u001d\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u001d\u0010\u001eJ'\u0010\u001d\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u001fH\u0007¢\u0006\u0004\b\u001d\u0010!J?\u0010\u001d\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\u001d\u0010\u000eJ\u001f\u0010\u001d\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u001d\u0010&J\u001f\u0010\u001d\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u001d\u0010'J'\u0010\u001d\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u001fH\u0007¢\u0006\u0004\b\u001d\u0010(J?\u0010\u001d\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\u001d\u0010\u0012J/\u0010\u001d\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u001f2\u0006\u0010\u0016\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\u001d\u0010*J7\u0010/\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00192\u0006\u0010,\u001a\u00020+2\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010.\u001a\u00020-H\u0007¢\u0006\u0004\b/\u00100JG\u0010\u001d\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u00101\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\u001d\u0010\u0018J'\u00105\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00072\u0006\u00104\u001a\u00020\u0007H\u0007¢\u0006\u0004\b5\u00106¨\u00067"}, d2 = {"Lcom/samsung/android/vexfwk/bitmap/VexFwkBitmap$Companion;", "", "<init>", "()V", "Landroid/graphics/Bitmap;", "srcBitmap", "dstBitmap", "", "srcRoiLeft", "srcRopTop", "srcRoiRight", "srcRoiBottom", "Lme/x;", "copyBitmapToBitmapNative", "(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;IIII)V", "", "dstArray", "copyBitmapToIntArrayNative", "(Landroid/graphics/Bitmap;[IIIII)V", "srcArray", "copyIntArrayToBitmapNative", "([ILandroid/graphics/Bitmap;IIII)V", "srcStride", "copyIntArrayToBitmapNative2", "([ILandroid/graphics/Bitmap;IIIII)V", "", "srcBytePerPixel", "copyByteArrayToBitmapNative", "([BLandroid/graphics/Bitmap;IIIIII)V", "copyBitmap", "(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;)V", "Landroid/graphics/Rect;", "roi", "(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;Landroid/graphics/Rect;)V", "left", "top", "right", "bottom", "(Landroid/graphics/Bitmap;[I)V", "([ILandroid/graphics/Bitmap;)V", "(Landroid/graphics/Bitmap;[ILandroid/graphics/Rect;)V", "srcRoi", "([ILandroid/graphics/Bitmap;Landroid/graphics/Rect;I)V", "Landroid/util/Size;", "size", "Landroid/graphics/Bitmap$Config;", "format", "convertToBitmap", "([BLandroid/util/Size;IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;", "srcRoiTop", "bitmap", "maxWidth", "maxHeight", "resizeBitmap", "(Landroid/graphics/Bitmap;II)Landroid/graphics/Bitmap;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private final void copyBitmapToBitmapNative(Bitmap bitmap, Bitmap bitmap2, int i2, int i7, int i8, int i10) {
            VexFwkBitmap.copyBitmapToBitmapNative(bitmap, bitmap2, i2, i7, i8, i10);
        }

        private final void copyBitmapToIntArrayNative(Bitmap bitmap, int[] iArr, int i2, int i7, int i8, int i10) {
            VexFwkBitmap.copyBitmapToIntArrayNative(bitmap, iArr, i2, i7, i8, i10);
        }

        private final void copyByteArrayToBitmapNative(byte[] bArr, Bitmap bitmap, int i2, int i7, int i8, int i10, int i11, int i12) {
            VexFwkBitmap.copyByteArrayToBitmapNative(bArr, bitmap, i2, i7, i8, i10, i11, i12);
        }

        private final void copyIntArrayToBitmapNative(int[] iArr, Bitmap bitmap, int i2, int i7, int i8, int i10) {
            VexFwkBitmap.copyIntArrayToBitmapNative(iArr, bitmap, i2, i7, i8, i10);
        }

        private final void copyIntArrayToBitmapNative2(int[] iArr, Bitmap bitmap, int i2, int i7, int i8, int i10, int i11) {
            VexFwkBitmap.copyIntArrayToBitmapNative2(iArr, bitmap, i2, i7, i8, i10, i11);
        }

        public final Bitmap convertToBitmap(byte[] bArr, Size size, int i2, int i7, Bitmap.Config config) {
            j.e(bArr, "srcArray");
            j.e(size, "size");
            j.e(config, "format");
            Bitmap createBitmap = Bitmap.createBitmap(size.getWidth(), size.getHeight(), config);
            j.d(createBitmap, "createBitmap(...)");
            VexFwkBitmap.Companion.copyByteArrayToBitmapNative(bArr, createBitmap, 0, 0, size.getWidth(), size.getHeight(), i2, i7);
            return createBitmap;
        }

        public final void copyBitmap(Bitmap bitmap, Bitmap bitmap2) {
            j.e(bitmap, "srcBitmap");
            j.e(bitmap2, "dstBitmap");
            copyBitmap(bitmap, bitmap2, 0, 0, bitmap.getWidth(), bitmap.getHeight());
        }

        public final Bitmap resizeBitmap(Bitmap bitmap, int i2, int i7) {
            boolean z;
            j.e(bitmap, "bitmap");
            boolean z3 = false;
            if (i2 <= 0) {
                z = true;
            } else {
                z = false;
            }
            if (i7 <= 0) {
                z3 = true;
            }
            if (z3 || z) {
                Bitmap.Config config = bitmap.getConfig();
                if (config != null) {
                    Bitmap copy = bitmap.copy(config, true);
                    j.d(copy, "copy(...)");
                    return copy;
                }
                throw new IllegalArgumentException("bitmap.config is null");
            }
            double width = ((double) bitmap.getWidth()) / ((double) bitmap.getHeight());
            double d = (double) i2;
            double d2 = (double) i7;
            double d3 = d / d2;
            if (d3 > width) {
                i2 = C0068a.V(d2 * width);
            } else if (d3 < width) {
                i7 = C0068a.V(d / width);
            }
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i2, i7, true);
            j.d(createScaledBitmap, "createScaledBitmap(this, width, height, filter)");
            return createScaledBitmap;
        }

        private Companion() {
        }

        public final void copyBitmap(Bitmap bitmap, Bitmap bitmap2, Rect rect) {
            j.e(bitmap, "srcBitmap");
            j.e(bitmap2, "dstBitmap");
            j.e(rect, "roi");
            copyBitmap(bitmap, bitmap2, rect.left, rect.top, rect.right, rect.bottom);
        }

        public final void copyBitmap(Bitmap bitmap, Bitmap bitmap2, int i2, int i7, int i8, int i10) {
            j.e(bitmap, "srcBitmap");
            j.e(bitmap2, "dstBitmap");
            boolean z = false;
            if (!(i2 < 0) && !(i7 < 0)) {
                if (!(i8 > bitmap.getWidth()) && !(i10 > bitmap.getHeight())) {
                    int i11 = i8 - i2;
                    int i12 = i10 - i7;
                    boolean z3 = i11 > bitmap2.getWidth();
                    if (i12 > bitmap2.getHeight()) {
                        z = true;
                    }
                    if (!z && !z3) {
                        copyBitmapToBitmapNative(bitmap, bitmap2, i2, i7, i8, i10);
                        return;
                    }
                    int width = bitmap2.getWidth();
                    int height = bitmap2.getHeight();
                    StringBuilder h5 = a.h(i11, i12, "roiWidth(", ") or roiHeight(", ") are out of dstBitmap(");
                    h5.append(width);
                    h5.append("x");
                    h5.append(height);
                    h5.append(")");
                    throw new IllegalArgumentException(h5.toString());
                }
                int width2 = bitmap.getWidth();
                int height2 = bitmap.getHeight();
                StringBuilder h6 = a.h(i8, i10, "roiRight(", ") or roiBottom(", ") are out of srcBitmap(");
                h6.append(width2);
                h6.append("x");
                h6.append(height2);
                h6.append(")");
                throw new IllegalArgumentException(h6.toString());
            }
            throw new IllegalArgumentException(a.d(i2, i7, "roiLeft(", ") or roiTop(", ") are less than 0"));
        }

        public final void copyBitmap(Bitmap bitmap, int[] iArr) {
            j.e(bitmap, "srcBitmap");
            j.e(iArr, "dstArray");
            copyBitmap(bitmap, iArr, 0, 0, bitmap.getWidth(), bitmap.getHeight());
        }

        public final void copyBitmap(int[] iArr, Bitmap bitmap) {
            j.e(iArr, "srcArray");
            j.e(bitmap, "dstBitmap");
            if (bitmap.getHeight() * bitmap.getWidth() == iArr.length) {
                copyBitmap(iArr, bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), bitmap.getWidth());
                return;
            }
            Bitmap bitmap2 = bitmap;
            int length = iArr.length;
            int width = bitmap2.getWidth();
            int height = bitmap2.getHeight();
            int width2 = bitmap2.getWidth();
            StringBuilder h5 = a.h(length, width, "srcArray.size(", ") and dstBitmap.size(", "x");
            h5.append(height);
            h5.append("=");
            h5.append(bitmap2.getHeight() * width2);
            h5.append(") should be same.");
            throw new IllegalArgumentException(h5.toString());
        }

        public final void copyBitmap(Bitmap bitmap, int[] iArr, Rect rect) {
            j.e(bitmap, "srcBitmap");
            j.e(iArr, "dstArray");
            j.e(rect, "roi");
            copyBitmap(bitmap, iArr, rect.left, rect.top, rect.right, rect.bottom);
        }

        public final void copyBitmap(Bitmap bitmap, int[] iArr, int i2, int i7, int i8, int i10) {
            Throwable th;
            j.e(bitmap, "srcBitmap");
            j.e(iArr, "dstArray");
            if (bitmap.getConfig() == Bitmap.Config.HARDWARE) {
                HardwareBuffer i11 = bitmap.getHardwareBuffer();
                try {
                    if (i11.getFormat() == 1) {
                        F.u(i11, (Throwable) null);
                    } else {
                        throw new IllegalArgumentException("srcBitmap.hardwareBuffer must have RGBA_8888");
                    }
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    F.u(i11, th);
                    throw th3;
                }
            } else if (bitmap.getConfig() != Bitmap.Config.ARGB_8888) {
                throw new IllegalArgumentException("srcBitmap.config must be ARGB_8888");
            }
            copyBitmapToIntArrayNative(bitmap, iArr, i2, i7, i8, i10);
        }

        public final void copyBitmap(int[] iArr, Bitmap bitmap, Rect rect, int i2) {
            j.e(iArr, "srcArray");
            j.e(bitmap, "dstBitmap");
            j.e(rect, "srcRoi");
            copyBitmap(iArr, bitmap, rect.left, rect.top, rect.right, rect.bottom, i2);
        }

        public final void copyBitmap(int[] iArr, Bitmap bitmap, int i2, int i7, int i8, int i10, int i11) {
            j.e(iArr, "srcArray");
            j.e(bitmap, "dstBitmap");
            copyIntArrayToBitmapNative2(iArr, bitmap, i2, i7, i8, i10, i11);
        }
    }

    public static final Bitmap convertToBitmap(byte[] bArr, Size size, int i2, int i7, Bitmap.Config config) {
        return Companion.convertToBitmap(bArr, size, i2, i7, config);
    }

    public static final void copyBitmap(Bitmap bitmap, Bitmap bitmap2) {
        Companion.copyBitmap(bitmap, bitmap2);
    }

    /* access modifiers changed from: private */
    public static final native void copyBitmapToBitmapNative(Bitmap bitmap, Bitmap bitmap2, int i2, int i7, int i8, int i10);

    /* access modifiers changed from: private */
    public static final native void copyBitmapToIntArrayNative(Bitmap bitmap, int[] iArr, int i2, int i7, int i8, int i10);

    /* access modifiers changed from: private */
    public static final native void copyByteArrayToBitmapNative(byte[] bArr, Bitmap bitmap, int i2, int i7, int i8, int i10, int i11, int i12);

    /* access modifiers changed from: private */
    public static final native void copyIntArrayToBitmapNative(int[] iArr, Bitmap bitmap, int i2, int i7, int i8, int i10);

    /* access modifiers changed from: private */
    public static final native void copyIntArrayToBitmapNative2(int[] iArr, Bitmap bitmap, int i2, int i7, int i8, int i10, int i11);

    public static final Bitmap resizeBitmap(Bitmap bitmap, int i2, int i7) {
        return Companion.resizeBitmap(bitmap, i2, i7);
    }

    public static final void copyBitmap(Bitmap bitmap, Bitmap bitmap2, int i2, int i7, int i8, int i10) {
        Companion.copyBitmap(bitmap, bitmap2, i2, i7, i8, i10);
    }

    public static final void copyBitmap(Bitmap bitmap, Bitmap bitmap2, Rect rect) {
        Companion.copyBitmap(bitmap, bitmap2, rect);
    }

    public static final void copyBitmap(Bitmap bitmap, int[] iArr) {
        Companion.copyBitmap(bitmap, iArr);
    }

    public static final void copyBitmap(Bitmap bitmap, int[] iArr, int i2, int i7, int i8, int i10) {
        Companion.copyBitmap(bitmap, iArr, i2, i7, i8, i10);
    }

    public static final void copyBitmap(Bitmap bitmap, int[] iArr, Rect rect) {
        Companion.copyBitmap(bitmap, iArr, rect);
    }

    public static final void copyBitmap(int[] iArr, Bitmap bitmap) {
        Companion.copyBitmap(iArr, bitmap);
    }

    public static final void copyBitmap(int[] iArr, Bitmap bitmap, int i2, int i7, int i8, int i10, int i11) {
        Companion.copyBitmap(iArr, bitmap, i2, i7, i8, i10, i11);
    }

    public static final void copyBitmap(int[] iArr, Bitmap bitmap, Rect rect, int i2) {
        Companion.copyBitmap(iArr, bitmap, rect, i2);
    }
}
