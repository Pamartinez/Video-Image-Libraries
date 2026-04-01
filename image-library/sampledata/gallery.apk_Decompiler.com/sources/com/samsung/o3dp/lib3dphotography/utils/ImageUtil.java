package com.samsung.o3dp.lib3dphotography.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import com.samsung.o3dp.lib3dphotography.nativelib.JNI;
import i.C0212a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageUtil {
    private static final float MIN_ASPECT_RATIO = 9.0f;
    private static final String TAG = "ImageUtil";

    /* renamed from: com.samsung.o3dp.lib3dphotography.utils.ImageUtil$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$CompressFormat;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                android.graphics.Bitmap$CompressFormat[] r0 = android.graphics.Bitmap.CompressFormat.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$android$graphics$Bitmap$CompressFormat = r0
                android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$android$graphics$Bitmap$CompressFormat     // Catch:{ NoSuchFieldError -> 0x001d }
                android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$android$graphics$Bitmap$CompressFormat     // Catch:{ NoSuchFieldError -> 0x0028 }
                android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.WEBP_LOSSLESS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$android$graphics$Bitmap$CompressFormat     // Catch:{ NoSuchFieldError -> 0x0033 }
                android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.WEBP_LOSSY     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.o3dp.lib3dphotography.utils.ImageUtil.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FormatInfo {
        public Bitmap.CompressFormat compressFormat;
        public int quality;

        public FormatInfo(Bitmap.CompressFormat compressFormat2, int i2) {
            this.compressFormat = compressFormat2;
            this.quality = i2;
        }

        public String extension() {
            int i2 = AnonymousClass1.$SwitchMap$android$graphics$Bitmap$CompressFormat[this.compressFormat.ordinal()];
            if (i2 == 1) {
                return "png";
            }
            if (i2 == 2) {
                return "jpg";
            }
            if (i2 == 3 || i2 == 4) {
                return "webp";
            }
            return "bin";
        }
    }

    public static Bitmap centerCrop(Bitmap bitmap) {
        int i2;
        int i7;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = (float) width;
        float f5 = (float) height;
        if (f / f5 > MIN_ASPECT_RATIO) {
            i7 = (int) (f5 * MIN_ASPECT_RATIO);
            i2 = height;
        } else {
            i2 = (int) (f / MIN_ASPECT_RATIO);
            i7 = width;
        }
        return Bitmap.createBitmap(bitmap, (width - i7) / 2, (height - i2) / 2, i7, i2);
    }

    public static byte[] convertBitmapToBgr(Bitmap bitmap) {
        byte[] bArr = new byte[(bitmap.getHeight() * bitmap.getWidth() * 3)];
        if (JNI.convertBitmapToBgr(bitmap, bArr)) {
            return bArr;
        }
        LogUtil.e(TAG, "Failed to JNI.convertBitmapToBgr()");
        return null;
    }

    public static boolean needCrop(int i2, int i7) {
        if (((float) Math.max(i2, i7)) / ((float) Math.min(i2, i7)) > MIN_ASPECT_RATIO) {
            return true;
        }
        return false;
    }

    public static void saveImage(Context context, Bitmap bitmap, String str, FormatInfo formatInfo) {
        saveImage(bitmap, new File(DebugUtil.getDebugOutputDirectory(), str).toString(), formatInfo);
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, float f, Rect rect) {
        return Bitmap.createBitmap(scaleBitmap(bitmap, f), rect.left, rect.top, rect.width(), rect.height());
    }

    public static Bitmap scaleBitmapToFit(Bitmap bitmap, int i2, int i7) {
        float f;
        if (bitmap == null) {
            return bitmap;
        }
        float f5 = (float) i2;
        float f8 = (float) i7;
        if (f5 / f8 > ((float) bitmap.getWidth()) / ((float) bitmap.getHeight())) {
            f = f8 / ((float) bitmap.getHeight());
        } else {
            f = f5 / ((float) bitmap.getWidth());
        }
        return scaleBitmap(bitmap, f);
    }

    public static Bitmap uriToBitmap(Context context, Uri uri) {
        InputStream openInputStream;
        try {
            openInputStream = context.getContentResolver().openInputStream(uri);
            Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream);
            if (openInputStream != null) {
                openInputStream.close();
            }
            return decodeStream;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, float f) {
        return Bitmap.createScaledBitmap(bitmap, Math.round(((float) bitmap.getWidth()) * f), Math.round(((float) bitmap.getHeight()) * f), true);
    }

    public static void saveImage(Bitmap bitmap, String str, FormatInfo formatInfo) {
        FileOutputStream fileOutputStream;
        StringBuilder t = C0212a.t(str, ".");
        t.append(formatInfo.extension());
        File file = new File(t.toString());
        LogUtil.i(TAG, StringUtil.format("Saving %dx%d bitmap to %s", Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()), file.getName()));
        if (file.exists() && !file.delete()) {
            LogUtil.w(TAG, "Failed to delete existing file");
        }
        try {
            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(formatInfo.compressFormat, formatInfo.quality, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return;
        } catch (IOException e) {
            LogUtil.e(TAG, "Failed to saveBitmap() " + e);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
