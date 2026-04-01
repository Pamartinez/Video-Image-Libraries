package com.samsung.android.gallery.module.graphics;

import B8.b;
import N2.j;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Gainmap;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.samsung.android.gallery.module.graphics.BitmapHelper;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.renderscript.RenderScriptCompat;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MathUtils;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.samsung.android.sum.core.types.NumericEnum;
import j4.a;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import l9.C0698a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BitmapUtils {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ValueHolder {
        static final float THUMBNAIL_FRAME_THRESHOLD = 0.2f;
    }

    private static int applyAlphaToColor(int i2, float f) {
        Color valueOf = Color.valueOf(i2);
        return Color.argb(f, valueOf.red(), valueOf.green(), valueOf.blue());
    }

    public static Bitmap applyColorFilter(Bitmap bitmap, int i2) {
        if (bitmap == null || bitmap.getWidth() == 0 || bitmap.getHeight() == 0) {
            return bitmap;
        }
        Bitmap createBitmap = createBitmap(bitmap.getWidth(), bitmap.getHeight(), getConfig(bitmap));
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(i2, PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createBitmap;
    }

    public static Bitmap applyGradient(Bitmap bitmap, int i2, int i7) {
        if (bitmap == null || bitmap.getWidth() == 0 || bitmap.getHeight() == 0) {
            return bitmap;
        }
        float width = (float) bitmap.getWidth();
        float height = (float) bitmap.getHeight();
        Bitmap createBitmap = createBitmap(bitmap.getWidth(), bitmap.getHeight(), getConfig(bitmap));
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        int save = canvas.save();
        float f = height;
        paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, f, i2, i7, Shader.TileMode.CLAMP));
        canvas.drawRect(0.0f, 0.0f, width, f, paint);
        canvas.restoreToCount(save);
        return createBitmap;
    }

    public static Bitmap blur(Context context, Bitmap bitmap, float f) {
        return RenderScriptCompat.getBlurAppliedBitmap(context, bitmap, f, false);
    }

    public static Bitmap blurAfterResize(Context context, Bitmap bitmap, int i2) {
        return blur(context, resize(bitmap, i2));
    }

    public static int calcBestFrameTime(String str, float f) {
        try {
            ArrayList arrayList = new ArrayList();
            String[] split = str.split(";");
            if (split.length > 1) {
                long j2 = 0;
                for (String split2 : split) {
                    String[] split3 = split2.split(NumericEnum.SEP);
                    int i2 = UnsafeCast.toInt(split3[0], 0);
                    int i7 = UnsafeCast.toInt(split3[1], 0);
                    arrayList.add(new Pair(Integer.valueOf(i2), Integer.valueOf(i7)));
                    j2 += (long) i7;
                }
                float size = ((float) j2) / ((float) arrayList.size());
                int i8 = (int) (f * size);
                Pair pair = (Pair) arrayList.stream().filter(new b(i8, 25)).findFirst().orElseGet(new a(18));
                StringBuilder sb2 = new StringBuilder();
                sb2.append("calcBestFrameTime=");
                sb2.append(pair.first);
                sb2.append(Logger.v(pair.second, Integer.valueOf(i8), "avg=" + size, Integer.valueOf(arrayList.size()), Integer.valueOf(split.length)));
                sb2.append(" ");
                sb2.append(str);
                Log.d("BitmapUtils", sb2.toString());
                return ((Integer) pair.first).intValue();
            }
            Log.d("BitmapUtils", "calcBestFrameTime=0 ".concat(str));
            return 0;
        } catch (Error | Exception e) {
            A.a.z(e, new StringBuilder("calcBestFrameTime failed. e="), "BitmapUtils");
            return 0;
        }
    }

    public static Rect calcCenterCropRect(float f, float f5, float f8, float f10, int i2) {
        float f11;
        int i7;
        int i8;
        int i10;
        float f12 = f / f5;
        if (i2 == 90 || i2 == 270) {
            f11 = f10 / f8;
        } else {
            f11 = f8 / f10;
        }
        int i11 = 0;
        if (f12 <= f11) {
            i7 = (int) f;
            i10 = Math.max(1, (int) (f / f11));
            i8 = ((int) (f5 - ((float) i10))) / 2;
        } else {
            int i12 = (int) f5;
            int i13 = (int) (f5 * f11);
            i11 = ((int) (f - ((float) i13))) / 2;
            i10 = i12;
            i7 = i13;
            i8 = 0;
        }
        return new Rect(i11, i8, i7 + i11, i10 + i8);
    }

    public static void clearGainmap(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled() && bitmap.hasGainmap()) {
            bitmap.setGainmap((Gainmap) null);
        }
    }

    public static byte[] compressToBytes(Bitmap bitmap) {
        return compressToBytes(bitmap, 100, Bitmap.CompressFormat.JPEG);
    }

    public static int computeSampleSizeLarger(float f) {
        int floor = (int) Math.floor((double) (1.0f / f));
        if (floor <= 1) {
            return 1;
        }
        if (floor <= 8) {
            return MathUtils.prevPowerOf2(floor);
        }
        return (floor / 8) * 8;
    }

    public static Bitmap createBitmap(int i2, int i7, Bitmap.Config config) {
        return BitmapPool.getInstance().get(i2, i7, config);
    }

    public static Bitmap createScaledBitmap(Bitmap bitmap, int i2, int i7, boolean z) {
        try {
            return Bitmap.createScaledBitmap(bitmap, i2, i7, z);
        } catch (OutOfMemoryError unused) {
            Log.w("BitmapUtils", "createScaledBitmap retry OOM");
            try {
                BitmapPool.getInstance().clear();
                return Bitmap.createScaledBitmap(bitmap, i2, i7, z);
            } catch (Error | Exception e) {
                Log.e((CharSequence) "BitmapUtils", "createScaledBitmap failed", e);
                return bitmap;
            }
        } catch (IllegalArgumentException e7) {
            Log.e((CharSequence) "BitmapUtils", "createScaleBitmap failed", (Throwable) e7);
            return bitmap;
        }
    }

    public static int createTopGradientColor(Bitmap bitmap, int i2, float f, float f5, float f8, float f10, float f11) {
        TimeTickLog timeTickLog = new TimeTickLog("storyColorPick");
        Bitmap rotateBitmap = rotateBitmap(resize(bitmap, (int) StatusCodes.INPUT_MISSING), i2);
        timeTickLog.tick();
        Bitmap crop = crop(rotateBitmap, new Rect(0, 0, rotateBitmap.getWidth(), (int) (((float) rotateBitmap.getHeight()) * f)));
        timeTickLog.tick();
        int averageColor = getAverageColor(crop);
        float[] fArr = new float[3];
        Color.colorToHSV(averageColor, fArr);
        float f12 = fArr[1] * 100.0f;
        if (f12 > 10.0f && f12 < f5 * 100.0f) {
            fArr[1] = f5;
        } else if (f12 > 100.0f * f8) {
            fArr[1] = f8;
        }
        fArr[2] = f10;
        int HSVToColor = Color.HSVToColor(fArr);
        Log.i("BitmapUtils", "createTopGradientDrawable #" + Integer.toHexString(averageColor) + "  to #" + Integer.toHexString(HSVToColor) + ", sat= " + f12);
        int applyAlphaToColor = applyAlphaToColor(HSVToColor, f11);
        StringBuilder sb2 = new StringBuilder("createTopGradientDrawable ");
        sb2.append(timeTickLog.summary());
        Log.i("BitmapUtils", sb2.toString(), crop);
        return applyAlphaToColor;
    }

    public static Bitmap crop(Bitmap bitmap, Rect rect) {
        return new BitmapOperator(bitmap).crop(rect).apply();
    }

    public static Bitmap cropCenterByRatio(Bitmap bitmap, float f) {
        int i2;
        int i7;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f5 = (float) width;
        float f8 = (float) height;
        if (f5 / (1.0f * f8) >= f) {
            i7 = (int) (f8 * f);
            i2 = height;
        } else {
            i2 = (int) (f5 / f);
            i7 = width;
        }
        if (i7 <= 0 || i2 <= 0) {
            StringBuilder h5 = A.a.h(i7, i2, "cropCenterByRatio() error : dW=", ", dH=", ", w=");
            j.x(h5, width, ", h=", height, ", ratio=");
            h5.append(f);
            Log.e("BitmapUtils", h5.toString());
            return null;
        }
        Bitmap createBitmap = createBitmap(i7, i2, getConfig(bitmap));
        Canvas canvas = new Canvas(createBitmap);
        canvas.translate(((float) (i7 - width)) / 2.0f, ((float) (i2 - height)) / 2.0f);
        Paint paint = new Paint(6);
        if (!bitmap.isRecycled()) {
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        }
        return createBitmap;
    }

    public static Bitmap decodeByteArray(byte[] bArr, BitmapOptions bitmapOptions, int i2) {
        BitmapOptions bitmapOptions2;
        if (bitmapOptions.outWidth <= 0 || bitmapOptions.outHeight <= 0) {
            bitmapOptions2 = bitmapOptions;
        } else {
            bitmapOptions2 = new BitmapOptions(bitmapOptions);
        }
        Bitmap decodeByteArray = ImageDecoder.decodeByteArray(bArr, 0, bArr.length, bitmapOptions);
        if (decodeByteArray == null) {
            return null;
        }
        Bitmap postProcessBitmap = postProcessBitmap(decodeByteArray, i2);
        SeApiCompat.addBitmapTag(postProcessBitmap, "options", bitmapOptions2);
        return postProcessBitmap;
    }

    public static Bitmap decodeEmbeddedInVideo(MediaMetadataRetriever mediaMetadataRetriever) {
        long currentTimeMillis = System.currentTimeMillis();
        byte[] embeddedPicture = mediaMetadataRetriever.getEmbeddedPicture();
        if (embeddedPicture == null || embeddedPicture.length <= 0) {
            return null;
        }
        int i2 = 0;
        Bitmap decodeByteArray = ImageDecoder.decodeByteArray(embeddedPicture, 0, embeddedPicture.length, BitmapOptionsFactory.parse(embeddedPicture, 0, embeddedPicture.length));
        if (decodeByteArray != null && (i2 = ExifUtils.getOrientation(embeddedPicture)) > 0) {
            Bitmap rotateBitmap = rotateBitmap(decodeByteArray, i2);
            if (decodeByteArray != rotateBitmap) {
                putBitmap(decodeByteArray);
            }
            decodeByteArray = rotateBitmap;
        }
        A.a.A(new Object[]{Integer.valueOf(i2), Logger.toSimpleString(decodeByteArray), Long.valueOf(currentTimeMillis)}, new StringBuilder("decodeEmbeddedInVideo"), "BitmapUtils");
        return decodeByteArray;
    }

    private static Bitmap decodeFile(String str, BitmapOptions bitmapOptions, int i2) {
        BitmapOptions bitmapOptions2;
        if (bitmapOptions.outWidth <= 0 || bitmapOptions.outHeight <= 0) {
            bitmapOptions2 = bitmapOptions;
        } else {
            bitmapOptions2 = new BitmapOptions(bitmapOptions);
        }
        Bitmap decodeFile = ImageDecoder.decodeFile(str, bitmapOptions);
        if (decodeFile == null) {
            return null;
        }
        Bitmap postProcessBitmap = postProcessBitmap(decodeFile, i2);
        SeApiCompat.addBitmapTag(postProcessBitmap, "options", bitmapOptions2);
        return postProcessBitmap;
    }

    public static Bitmap decodeStream(InputStream inputStream, BitmapOptions bitmapOptions) {
        BitmapOptions bitmapOptions2;
        if (bitmapOptions.outWidth <= 0 || bitmapOptions.outHeight <= 0) {
            bitmapOptions2 = bitmapOptions;
        } else {
            bitmapOptions2 = new BitmapOptions(bitmapOptions);
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, (Rect) null, bitmapOptions);
        if (decodeStream != null) {
            SeApiCompat.addBitmapTag(decodeStream, "options", bitmapOptions2);
        }
        return decodeStream;
    }

    public static Bitmap decodeUri(String str) {
        int i2;
        if (str == null) {
            return null;
        }
        byte[] readUri = BitmapHelper.readUri(str);
        StringBuilder sb2 = new StringBuilder("decodeUri");
        if (readUri != null) {
            i2 = readUri.length;
        } else {
            i2 = -1;
        }
        sb2.append(Logger.v(Integer.valueOf(i2)));
        sb2.append("");
        Log.i("BitmapUtils", sb2.toString());
        if (readUri == null) {
            return null;
        }
        return BitmapFactory.decodeByteArray(readUri, 0, readUri.length);
    }

    public static Bitmap downsizeVideoBitmap(Bitmap bitmap, int i2) {
        float f;
        if (bitmap == null) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int min = Math.min(width, height);
        int max = Math.max(width, height);
        if (min == 0 || max < i2) {
            return bitmap;
        }
        float f5 = (float) i2;
        if (height <= width) {
            f5 *= 0.7f;
        }
        float f8 = (float) min;
        if (f8 > f5) {
            f8 /= 2.0f;
        }
        int i7 = 1;
        while (true) {
            f = (float) i7;
            if (f8 / f <= f5) {
                break;
            }
            i7 *= 2;
        }
        float f10 = 1.0f / f;
        float f11 = ((float) max) * f10;
        if (f11 > f5) {
            f10 *= f5 / f11;
        }
        if (f10 < 1.0f) {
            return resize(bitmap, f10);
        }
        return bitmap;
    }

    public static int getAverageColor(Bitmap bitmap) {
        return getAverageColor(bitmap, -1.0f, true);
    }

    public static Bitmap getBitmapFromDrawable(Drawable drawable) {
        return getBitmapFromDrawable(drawable, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }

    private static Bitmap.Config getConfig(Bitmap bitmap) {
        Bitmap.Config config = bitmap.getConfig();
        if (config != null) {
            return config;
        }
        return Bitmap.Config.ARGB_8888;
    }

    public static Bitmap getDecodedBitmap(byte[] bArr, int i2) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        return decodeByteArray(bArr, BitmapOptionsFactory.parse(bArr, 0, bArr.length).adjustInSampleSize(i2), 0);
    }

    public static byte[] getEmbeddedPictureInVideo(String str) {
        FileInputStream fileInputStream;
        MediaMetadataRetriever mediaMetadataRetriever;
        try {
            fileInputStream = new FileInputStream(str);
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(fileInputStream.getFD());
            byte[] embeddedPicture = mediaMetadataRetriever.getEmbeddedPicture();
            mediaMetadataRetriever.close();
            fileInputStream.close();
            return embeddedPicture;
            throw th;
            throw th;
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("getEmbeddedPictureInVideo failed. e="), "BitmapUtils");
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
    }

    public static float[] getHsvColor(Bitmap bitmap) {
        float[] fArr = new float[3];
        Color.colorToHSV(getAverageColor(resize(bitmap, (int) StatusCodes.INPUT_MISSING)), fArr);
        return fArr;
    }

    public static byte[] getJpegFromBitmap(Bitmap bitmap, int i2) {
        int i7;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (Math.max(width, height) > i2) {
            if (width > height) {
                i7 = (height * i2) / width;
            } else {
                int i8 = (width * i2) / height;
                i7 = i2;
                i2 = i8;
            }
            if (i2 == 0 || i7 == 0) {
                return null;
            }
            bitmap = createScaledBitmap(bitmap, i2, i7, false);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static int getRotatedHeight(Bitmap bitmap, int i2) {
        if (i2 % MOCRLang.KHMER == 0) {
            return bitmap.getHeight();
        }
        return bitmap.getWidth();
    }

    public static int getRotatedWidth(Bitmap bitmap, int i2) {
        if (i2 % MOCRLang.KHMER == 0) {
            return bitmap.getWidth();
        }
        return bitmap.getHeight();
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float f) {
        return getRoundedCornerBitmap(bitmap, f, 0, 0, 0, 0);
    }

    public static long getVideoThumbnailFrameTime(MediaMetadataRetriever mediaMetadataRetriever) {
        String extractMetadata = mediaMetadataRetriever.extractMetadata(ErrorCodeConvertor.TEMP_AGENT_WIFI_ONLY_MODEL);
        if (!TextUtils.isEmpty(extractMetadata)) {
            return ((long) calcBestFrameTime(extractMetadata, ValueHolder.THUMBNAIL_FRAME_THRESHOLD)) * 1000;
        }
        return 0;
    }

    public static Bitmap getVideoThumbnailFromMeta(String str) {
        FileInputStream fileInputStream;
        MediaMetadataRetriever mediaMetadataRetriever;
        try {
            fileInputStream = new FileInputStream(str);
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(fileInputStream.getFD());
            Bitmap decodeEmbeddedInVideo = decodeEmbeddedInVideo(mediaMetadataRetriever);
            if (decodeEmbeddedInVideo == null) {
                decodeEmbeddedInVideo = SeApiCompat.getVideoFrameAtTime(mediaMetadataRetriever, 0, 2);
            }
            mediaMetadataRetriever.close();
            fileInputStream.close();
            return decodeEmbeddedInVideo;
            throw th;
            throw th;
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("getVideoThumbnailFromMeta(f) failed. e="), "BitmapUtils");
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
    }

    public static boolean hasSameColor(Bitmap bitmap, int i2) {
        return BitmapHelper.hasSameColor(bitmap, i2);
    }

    public static boolean hasTransparency(Bitmap bitmap) {
        return BitmapHelper.hasTransparency(bitmap);
    }

    public static boolean isAlmostPanoramic(int i2, int i7) {
        if (i2 > i7) {
            if (((float) i2) > ((float) i7) * 2.7f) {
                return true;
            }
            return false;
        } else if (((float) i7) > ((float) i2) * 2.7f) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isBrightForRecap(Bitmap bitmap) {
        if (bitmap == null || !BitmapHelper.hasManyPixelsOfExpectedBright(bitmap, true)) {
            return false;
        }
        return true;
    }

    public static boolean isDark(Bitmap bitmap) {
        if (bitmap == null || !BitmapHelper.isDark(resize(bitmap, 3, 3))) {
            return false;
        }
        return true;
    }

    public static boolean isDarkForRecap(Bitmap bitmap) {
        if (bitmap == null || !BitmapHelper.hasManyPixelsOfExpectedBright(bitmap, false)) {
            return false;
        }
        return true;
    }

    public static boolean isPanoramic(int i2, int i7) {
        if (i2 > i7) {
            if (((float) i2) > ((float) i7) * 6.0f) {
                return true;
            }
            return false;
        } else if (((float) i7) > ((float) i2) * 6.0f) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$calcBestFrameTime$0(int i2, Pair pair) {
        if (((Integer) pair.second).intValue() > i2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Pair lambda$calcBestFrameTime$1() {
        return new Pair(0, 0);
    }

    public static Bitmap mergeBitmap(List<Bitmap> list, int i2) {
        ArrayList arrayList = new ArrayList(list);
        if (arrayList.isEmpty()) {
            return null;
        }
        int i7 = 0;
        Bitmap bitmap = (Bitmap) arrayList.get(0);
        int width = bitmap.getWidth();
        Bitmap createBitmap = createBitmap(i2 * width, bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            canvas.drawBitmap((Bitmap) it.next(), (float) i7, 0.0f, new Paint());
            i7 += width;
        }
        return createBitmap;
    }

    public static Bitmap pad(Bitmap bitmap, int i2, int i7, int i8, int i10, int i11) {
        Bitmap bitmap2 = BitmapPool.getInstance().get(bitmap.getWidth() + i2 + i8, bitmap.getHeight() + i7 + i10, bitmap.getConfig());
        Canvas canvas = new Canvas(bitmap2);
        canvas.drawColor(i11);
        canvas.translate((float) i2, (float) i7);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        return bitmap2;
    }

    private static Bitmap postProcessBitmap(Bitmap bitmap, int i2) {
        if (bitmap == null) {
            return null;
        }
        if (i2 != 0) {
            Bitmap rotateBitmap = rotateBitmap(bitmap, i2);
            if (rotateBitmap != bitmap) {
                putBitmap(bitmap);
            }
            bitmap = rotateBitmap;
        }
        return resizeForMaxBitmapSize(bitmap);
    }

    public static void putBitmap(Bitmap bitmap) {
        BitmapPool.getInstance().put(bitmap);
    }

    public static Bitmap requireCorrectGainmapRatio(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 34 && bitmap != null) {
            try {
                if (bitmap.hasGainmap()) {
                    Bitmap e = bitmap.getGainmap().getGainmapContents();
                    float width = ((float) e.getWidth()) / ((float) e.getHeight());
                    float width2 = ((float) bitmap.getWidth()) / ((float) bitmap.getHeight());
                    if (Math.abs(width - width2) > 0.1f) {
                        Log.e("BitmapUtils", "gainmap ratio different from bitmap {" + width + GlobalPostProcInternalPPInterface.SPLIT_REGEX + width2 + "}");
                        bitmap.setGainmap((Gainmap) null);
                    }
                }
            } catch (Exception unused) {
            }
        }
        return bitmap;
    }

    public static Bitmap resize(Bitmap bitmap, int i2) {
        return resize(bitmap, ((float) i2) / ((float) Math.max(bitmap.getWidth(), bitmap.getHeight())));
    }

    public static Bitmap resizeAndCrop(Bitmap bitmap, Rect rect, int i2, int i7) {
        if (i2 <= 0 || i7 <= 0) {
            Log.w("BitmapUtils", "resizeAndCrop failed. invalid size=" + i2 + "x" + i7);
            return null;
        } else if (i2 == bitmap.getWidth() && i7 == bitmap.getHeight()) {
            return bitmap;
        } else {
            return new BitmapOperator(bitmap).resize(i2, i7).crop(rect).stretchable(true).apply();
        }
    }

    public static Bitmap resizeAndCropCenter(Bitmap bitmap, int i2) {
        return resizeAndCropCenter(bitmap, i2, i2);
    }

    public static Bitmap resizeForMaxBitmapSize(Bitmap bitmap) {
        if (bitmap == null || bitmap.getByteCount() <= 104857600) {
            return bitmap;
        }
        Log.d("BitmapUtils", "resizeForMaxBitmapSize = " + bitmap.getByteCount());
        Bitmap resize = resize(bitmap, 9.437184E7f / ((float) bitmap.getByteCount()));
        if (resize != bitmap) {
            putBitmap(bitmap);
        }
        return resize;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int i2) {
        return new BitmapOperator(bitmap).rotate(i2).apply();
    }

    public static boolean saveAs(Bitmap bitmap, String str, int i2, boolean z) {
        return z ? saveAsPng(bitmap, str, i2) : saveAsJpeg(bitmap, str, i2);
    }

    public static boolean saveAsJpeg(Bitmap bitmap, String str, int i2) {
        return saveAs(str, bitmap, Bitmap.CompressFormat.JPEG, i2);
    }

    public static boolean saveAsPng(Bitmap bitmap, String str, int i2) {
        return saveAs(str, bitmap, Bitmap.CompressFormat.PNG, i2);
    }

    public static String toDebugString() {
        return BitmapHelper.DebugLog.dump();
    }

    public static Bitmap blur(Context context, Bitmap bitmap) {
        return blur(context, bitmap, 24.9f);
    }

    public static byte[] compressToBytes(Bitmap bitmap, int i2) {
        return compressToBytes(bitmap, i2, Bitmap.CompressFormat.JPEG);
    }

    public static Bitmap createBitmap(int i2, int i7, Bitmap.Config config, int i8) {
        return BitmapPool.getInstance().get(i2, i7, config, i8);
    }

    public static Bitmap crop(Bitmap bitmap, Rect rect, int i2, int i7) {
        rect.right = Math.min(rect.right, bitmap.getWidth());
        rect.bottom = Math.min(rect.bottom, bitmap.getHeight());
        Bitmap createBitmap = createBitmap(i2, i7, bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        if (rect.width() > i2 || rect.height() > i7) {
            float min = ((float) Math.min(i2, i7)) / ((float) Math.min(rect.width(), rect.height()));
            Log.d("BitmapUtils", "crop & resize {" + min + "}");
            canvas.scale(min, min);
        }
        canvas.translate((float) (-rect.left), (float) (-rect.top));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint(6));
        return createBitmap;
    }

    public static int getAverageColor(Bitmap bitmap, float f, boolean z) {
        int i2;
        int i7;
        int i8;
        int i10;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i11 = 0;
        if (width <= 0 || height <= 0) {
            return Color.rgb(0, 0, 0);
        }
        if (z) {
            i8 = 1;
            i7 = 1;
            i2 = 0;
            i10 = 0;
        } else {
            i2 = width / 8;
            i10 = height / 8;
            i8 = i2 * 2;
            i7 = i10 * 2;
        }
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        while (i2 < width) {
            for (int i15 = i10; i15 < height; i15 += i7) {
                int pixel = bitmap.getPixel(i2, i15);
                i11 += Color.red(pixel);
                i13 += Color.green(pixel);
                i14 += Color.blue(pixel);
                i12++;
            }
            i2 += i8;
        }
        int rgb = Color.rgb(i11 / i12, i13 / i12, i14 / i12);
        return f > 0.0f ? applyAlphaToColor(rgb, f) : rgb;
    }

    public static Bitmap getBitmapFromDrawable(Drawable drawable, int i2, int i7) {
        Bitmap createBitmap = createBitmap(i2, i7, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, i2, i7);
        drawable.draw(canvas);
        return createBitmap;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float f, int i2, int i7, int i8, int i10) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(i2, i7, bitmap.getWidth() - i8, bitmap.getHeight() - i10);
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        if (Build.VERSION.SDK_INT >= 34) {
            try {
                Optional ofNullable = Optional.ofNullable(bitmap.getGainmap());
                Objects.requireNonNull(createBitmap);
                ofNullable.ifPresent(new C0698a(createBitmap));
            } catch (IllegalStateException unused) {
                Log.w("BitmapUtils", "getRoundedCornerBitmap failed with gainmap");
            }
        }
        ColorSpace colorSpace = bitmap.getColorSpace();
        if (colorSpace != null) {
            createBitmap.setColorSpace(colorSpace);
        }
        BitmapOptions bitmapOptions = (BitmapOptions) SeApiCompat.getBitmapTag(bitmap, "options", null);
        if (bitmapOptions != null) {
            SeApiCompat.addBitmapTag(createBitmap, "options", new BitmapOptions(bitmapOptions));
        }
        return createBitmap;
    }

    public static Bitmap resize(Bitmap bitmap, int i2, int i7) {
        if (i2 <= 0 || i7 <= 0) {
            Log.w("BitmapUtils", "resize failed. invalid image size=" + i2 + "x" + i7);
            return null;
        } else if (i2 == bitmap.getWidth() && i7 == bitmap.getHeight()) {
            return bitmap;
        } else {
            return new BitmapOperator(bitmap).resize(i2, i7).apply();
        }
    }

    public static Bitmap resizeAndCropCenter(Bitmap bitmap, int i2, int i7) {
        if (bitmap == null || i2 <= 0 || i7 <= 0) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width == i2 && height == i7) {
            return bitmap;
        }
        float f = (float) width;
        float f5 = (float) height;
        float f8 = (float) i2;
        float f10 = (float) i7;
        float f11 = f / f5 >= f8 / f10 ? f10 / f5 : f8 / f;
        Bitmap createBitmap = createBitmap(i2, i7, getConfig(bitmap));
        int round = Math.round(((float) bitmap.getWidth()) * f11);
        int round2 = Math.round(((float) bitmap.getHeight()) * f11);
        Canvas canvas = new Canvas(createBitmap);
        canvas.translate(((float) (i2 - round)) / 2.0f, ((float) (i7 - round2)) / 2.0f);
        canvas.scale(f11, f11);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint(6));
        return createBitmap;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int i2, int i7) {
        return new BitmapOperator(bitmap).rotate(i2, i7).apply();
    }

    public static boolean saveAs(String str, Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i2) {
        FileOutputStream fileOutputStream;
        if (bitmap == null || bitmap.isRecycled() || TextUtils.isEmpty(str)) {
            Log.e("BitmapUtils", "saveAs failed. invalid argument");
            return false;
        } else if (!FileUtils.makeParentIfAbsent(str)) {
            Log.e("BitmapUtils", "saveAs failed. invalid folder");
            return false;
        } else {
            SecureFile secureFile = new SecureFile(str);
            try {
                fileOutputStream = new FileOutputStream(secureFile, false);
                bitmap.compress(compressFormat, i2, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                return true;
            } catch (Exception e) {
                Log.e((CharSequence) "BitmapUtils", "saveAs failed to save bitmap", (Throwable) e);
                FileUtils.delete((File) secureFile);
                return false;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        throw th;
    }

    public static byte[] compressToBytes(Bitmap bitmap, int i2, Bitmap.CompressFormat compressFormat) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(100000);
        bitmap.compress(compressFormat, i2, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap createBitmap(Bitmap bitmap, int i2, int i7, int i8, int i10) {
        return createBitmap(bitmap, i2, i7, i8, i10, (Matrix) null, false);
    }

    public static Bitmap createBitmap(Bitmap bitmap, int i2, int i7, int i8, int i10, Matrix matrix, boolean z) {
        try {
            return Bitmap.createBitmap(bitmap, i2, i7, i8, i10, matrix, z);
        } catch (OutOfMemoryError unused) {
            Log.e("BitmapUtils", "OOM : createBitmap retry");
            BitmapPool.getInstance().clear();
            return Bitmap.createBitmap(bitmap, i2, i7, i8, i10, matrix, z);
        }
    }

    public static Bitmap getDecodedBitmap(String str, BitmapOptions bitmapOptions) {
        if (bitmapOptions.inPreferredHdr) {
            return requireCorrectGainmapRatio(decodeFile(str, bitmapOptions, 0));
        }
        return decodeFile(str, bitmapOptions, 0);
    }

    public static Bitmap resize(Bitmap bitmap, float f) {
        int round = Math.round(((float) bitmap.getWidth()) * f);
        int round2 = Math.round(((float) bitmap.getHeight()) * f);
        if (round <= 0 || round2 <= 0) {
            Log.w("BitmapUtils", "resize failed. invalid image size=" + round + "x" + round2);
            return null;
        } else if (round == bitmap.getWidth() && round2 == bitmap.getHeight()) {
            return bitmap;
        } else {
            Bitmap createBitmap = createBitmap(round, round2, getConfig(bitmap));
            Canvas canvas = new Canvas(createBitmap);
            canvas.scale(f, f);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint(6));
            return createBitmap;
        }
    }

    public static Bitmap getDecodedBitmap(String str, int i2, int i7, boolean z) {
        int i8;
        BitmapOptions adjustInSampleSizeSmallerThan = BitmapOptionsFactory.parse(str).withQuramCodec(z).adjustInSampleSizeSmallerThan(i7);
        int i10 = adjustInSampleSizeSmallerThan.outWidth;
        if (i10 <= 0 || (i8 = adjustInSampleSizeSmallerThan.outHeight) <= 0 || Math.min(i10, i8) / adjustInSampleSizeSmallerThan.inSampleSize <= 0) {
            return null;
        }
        return decodeFile(str, adjustInSampleSizeSmallerThan, i2);
    }

    public static Bitmap getVideoThumbnailFromMeta(Context context, Uri uri, long j2) {
        MediaMetadataRetriever mediaMetadataRetriever;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(context, uri);
            Bitmap decodeEmbeddedInVideo = decodeEmbeddedInVideo(mediaMetadataRetriever);
            if (decodeEmbeddedInVideo == null) {
                decodeEmbeddedInVideo = SeApiCompat.getVideoFrameAtTime(mediaMetadataRetriever, j2, 2);
            }
            mediaMetadataRetriever.close();
            return decodeEmbeddedInVideo;
        } catch (Exception e) {
            Log.e((CharSequence) "BitmapUtils", "getVideoThumbnailFromMeta(u) failed", (Throwable) e);
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
