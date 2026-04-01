package com.samsung.android.gallery.module.graphics;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Process;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleRingBuffer;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.scs.base.StatusCodes;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class BitmapHelper {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DebugLog {
        static final SimpleRingBuffer<String> log = new SimpleRingBuffer<>(StatusCodes.INPUT_MISSING);

        public static void d(String str, String str2) {
            SimpleRingBuffer<String> simpleRingBuffer = log;
            simpleRingBuffer.add(TimeUtil.getIsoLocalDateTime(System.currentTimeMillis()) + " " + Process.myPid() + "-" + Process.myTid() + " [" + str + "] " + str2);
        }

        public static String dump() {
            StringBuilder sb2 = new StringBuilder(1024);
            sb2.append("========== BitmapPool ============\n");
            try {
                log.dump().forEach(new a(sb2));
            } catch (Exception unused) {
            }
            return sb2.toString();
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$dump$0(StringBuilder sb2, String str) {
            sb2.append(str);
            sb2.append("\n");
        }
    }

    public static boolean hasManyPixelsOfExpectedBright(Bitmap bitmap, boolean z) {
        String str;
        if (bitmap != null) {
            long currentTimeMillis = System.currentTimeMillis();
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int i2 = 0;
            int i7 = 0;
            for (int i8 = 0; i8 < width; i8 += 4) {
                for (int i10 = 0; i10 < height; i10 += 4) {
                    i7++;
                    float[] fArr = new float[3];
                    Color.colorToHSV(bitmap.getColor(i8, i10).toArgb(), fArr);
                    int i11 = (int) (fArr[1] * 100.0f);
                    int i12 = (int) (fArr[2] * 100.0f);
                    if (!z ? !(i11 >= 21 || i12 >= 11) : !(i11 >= 15 || i12 <= 90)) {
                        i2++;
                    }
                }
            }
            float f = ((float) i2) / ((float) i7);
            if (z) {
                str = "White";
            } else {
                str = "Black";
            }
            Log.i("BitmapHelper", "recap bright test", str, C0086a.l(new StringBuilder(), (int) (100.0f * f), "%"), Integer.valueOf(i2), Integer.valueOf(i7), Logger.vt(currentTimeMillis));
            if (((double) f) > 0.6d) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasSameColor(Bitmap bitmap, int i2) {
        if (bitmap != null) {
            float width = ((float) (bitmap.getWidth() - 1)) / 10.0f;
            float height = ((float) (bitmap.getHeight() - 1)) / 10.0f;
            int i7 = 0;
            while (true) {
                float f = (float) i7;
                if (f > 10.0f) {
                    break;
                }
                int i8 = 0;
                while (true) {
                    float f5 = (float) i8;
                    if (f5 > 10.0f) {
                        break;
                    } else if (bitmap.getPixel((int) (width * f), (int) (f5 * height)) == i2) {
                        return true;
                    } else {
                        i8++;
                    }
                }
                i7++;
            }
        }
        return false;
    }

    public static boolean hasTransparency(Bitmap bitmap) {
        if (bitmap != null && bitmap.hasAlpha()) {
            int height = bitmap.getHeight() - 1;
            int width = bitmap.getWidth() - 1;
            if (width <= 1 || height <= 1 || (!isAlphaPixel(bitmap, 0, 0) && !isAlphaPixel(bitmap, 0, height) && !isAlphaPixel(bitmap, width, 0) && !isAlphaPixel(bitmap, width, height) && !isAlphaPixel(bitmap, width / 2, height / 2))) {
                return false;
            }
            return true;
        }
        return false;
    }

    private static boolean isAlphaPixel(Bitmap bitmap, int i2, int i7) {
        Bitmap.Config config = bitmap.getConfig();
        if (config == Bitmap.Config.ARGB_8888) {
            if (((bitmap.getPixel(i2, i7) >> 24) & ScoverState.TYPE_NFC_SMART_COVER) != 255) {
                return true;
            }
            return false;
        } else if (config != Bitmap.Config.RGBA_F16 || bitmap.getColor(i2, i7).alpha() == 1.0f) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isDark(Bitmap bitmap) {
        boolean z = false;
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float f = 0.0f;
            for (int i2 = 0; i2 < width; i2++) {
                for (int i7 = 0; i7 < height; i7++) {
                    f += bitmap.getColor(i2, i7).luminance();
                }
            }
            float f5 = f / ((float) (width * height));
            if (f5 < 0.001f) {
                z = true;
            }
            Log.d("BitmapHelper", "lum", Float.valueOf(f5), Boolean.valueOf(z));
        }
        return z;
    }

    public static byte[] readUri(String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            InputStream openInputStream = AppResources.getAppContext().getContentResolver().openInputStream(Uri.parse(str));
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[32768];
                while (true) {
                    int read = openInputStream.read(bArr);
                    if (read > 0) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        byteArrayOutputStream.close();
                        openInputStream.close();
                        return byteArray;
                    }
                }
            } catch (Throwable th) {
                if (openInputStream != null) {
                    openInputStream.close();
                }
                throw th;
            }
            throw th;
        } catch (IOException | IllegalArgumentException | NullPointerException e) {
            Log.at("BitmapHelper", "read uri failed e=" + e.getMessage());
            return null;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
    }
}
