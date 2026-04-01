package com.samsung.android.sdk.sgpl.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.sgpl.graphics.Build;
import i.C0212a;
import java.io.File;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class Log {
    static final String GALLERY_CODEC_TAG = "GalleryCC";
    static BiFunction<String, String, String> logBuilder;

    public static void d(String str, String str2) {
        BiFunction<String, String, String> biFunction = logBuilder;
        if (biFunction == null) {
            android.util.Log.d(str, str2);
        } else {
            android.util.Log.d(GALLERY_CODEC_TAG, biFunction.apply(str, str2));
        }
    }

    public static void e(String str, String str2, Throwable th) {
        BiFunction<String, String, String> biFunction = logBuilder;
        if (biFunction == null) {
            android.util.Log.e(str, str2, th);
        } else {
            android.util.Log.e(GALLERY_CODEC_TAG, biFunction.apply(str, str2), th);
        }
    }

    public static void i(String str, String str2) {
        BiFunction<String, String, String> biFunction = logBuilder;
        if (biFunction == null) {
            android.util.Log.i(str, str2);
        } else {
            android.util.Log.i(GALLERY_CODEC_TAG, biFunction.apply(str, str2));
        }
    }

    public static String toString(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj instanceof Bitmap) {
            return toString((Bitmap) obj);
        }
        if (obj instanceof BitmapFactory.Options) {
            return toString((BitmapFactory.Options) obj);
        }
        if (obj instanceof File) {
            return toString((File) obj);
        }
        return obj.toString();
    }

    public static void v(String str, String str2) {
        BiFunction<String, String, String> biFunction = logBuilder;
        if (biFunction == null) {
            android.util.Log.v(str, str2);
        } else {
            android.util.Log.v(GALLERY_CODEC_TAG, biFunction.apply(str, str2));
        }
    }

    public static String vars(Object... objArr) {
        int length = objArr.length;
        if (length == 0) {
            return "";
        }
        if (length == 1) {
            return " " + toString(objArr[0]);
        } else if (length == 2) {
            return " " + toString(objArr[0]) + ',' + toString(objArr[1]);
        } else if (length == 3) {
            return " " + toString(objArr[0]) + ',' + toString(objArr[1]) + ',' + toString(objArr[2]);
        } else if (length != 4) {
            return (String) Stream.of(objArr).map(new b(1)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX, " ", ""));
        } else {
            return " " + toString(objArr[0]) + ',' + toString(objArr[1]) + ',' + toString(objArr[2]) + ',' + toString(objArr[3]);
        }
    }

    public static void w(String str, String str2) {
        BiFunction<String, String, String> biFunction = logBuilder;
        if (biFunction == null) {
            android.util.Log.w(str, str2);
        } else {
            android.util.Log.w(GALLERY_CODEC_TAG, biFunction.apply(str, str2));
        }
    }

    public static void e(String str, String str2) {
        BiFunction<String, String, String> biFunction = logBuilder;
        if (biFunction == null) {
            android.util.Log.e(str, str2);
        } else {
            android.util.Log.e(GALLERY_CODEC_TAG, biFunction.apply(str, str2));
        }
    }

    public static String toString(File file) {
        if (file == null) {
            return "File{null}";
        }
        StringBuilder sb2 = new StringBuilder("File{");
        sb2.append(file.exists() ? "T" : "F");
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(file.length());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(file.getPath());
        sb2.append("}");
        return sb2.toString();
    }

    public static String toString(BitmapFactory.Options options) {
        String str;
        if (options == null) {
            return "Options{null}";
        }
        StringBuilder sb2 = new StringBuilder("Options{");
        sb2.append(options.outMimeType);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(options.outWidth);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(options.outHeight);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(options.inSampleSize);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (Build.SEM.SDK_INT >= 150000) {
            str = (options.semInApplyPhotoHdr ? "H" : "h").concat(options.semInCreateGainmap ? "G" : "g");
        } else {
            str = "";
        }
        sb2.append(str);
        return C0212a.p(sb2, options.inBitmap != null ? "B" : "b", "}");
    }

    public static String toString(Bitmap bitmap) {
        if (bitmap == null) {
            return "Bitmap{null}";
        }
        try {
            StringBuilder sb2 = new StringBuilder("Bitmap@");
            sb2.append(Integer.toHexString(bitmap.hashCode()));
            sb2.append("{");
            sb2.append(bitmap.getWidth());
            sb2.append("x");
            sb2.append(bitmap.getHeight());
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(bitmap.getAllocationByteCount());
            sb2.append(Build.VERSION.SDK_INT >= 34 ? bitmap.hasGainmap() ? ",G" : ",g" : "");
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(bitmap.getConfig());
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(bitmap.getColorSpace());
            sb2.append("}");
            return sb2.toString();
        } catch (IllegalStateException unused) {
            return "Bitmap@" + Integer.toHexString(bitmap.hashCode()) + "{recycled}";
        }
    }
}
