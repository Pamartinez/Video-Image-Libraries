package com.samsung.android.gallery.module.thumbnail;

import C3.C0392b;
import E5.b;
import android.graphics.Bitmap;
import com.samsung.android.gallery.module.R$color;
import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.module.graphics.BitmapOperator;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.sum.core.types.NumericEnum;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import o6.B;
import q8.a;
import qa.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class YearThumbnailLoader {
    private static volatile YearThumbnailLoader sInstance;
    private Bitmap mBrokenPieceBitmap;
    private final File mCacheDir = AppResources.getAppContext().getFilesDir();
    private boolean mIsRTL = Features.isEnabled(Features.IS_RTL);
    private final ConcurrentHashMap<String, Bitmap> mPartialYearBitmapMemCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Bitmap> mYearBitmapMemCache = new ConcurrentHashMap<>();
    private final HashMap<String, String> mYearKey = new HashMap<>();
    private final ConcurrentHashMap<String, ConcurrentHashMap<Integer, Bitmap>> mYearPieceBitmapMemCache = new ConcurrentHashMap<>();

    private YearThumbnailLoader() {
    }

    public static YearThumbnailLoader getInstance() {
        if (sInstance == null) {
            synchronized (YearThumbnailLoader.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new YearThumbnailLoader();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    private Map<Integer, Bitmap> getPieceBitmapCache(String str) {
        return this.mYearPieceBitmapMemCache.computeIfAbsent(str, new a(1));
    }

    private static String getYearCategory(String str) {
        return str.split("#")[0];
    }

    public static String getYearThumbnailKey(String str, int i2, int i7) {
        return str + NumericEnum.SEP + i7 + "#" + i2;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$deleteAllYearData$2(File file) {
        if (file != null && file.isFile() && file.getName().startsWith("year")) {
            FileUtils.delete(file);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$deleteYearData$3(String str, File file) {
        if (file == null || !file.isFile() || !file.getName().startsWith(str)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getCacheSize$1(File file) {
        if (file == null || !file.isFile() || !file.getName().startsWith("year")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ConcurrentHashMap lambda$getPieceBitmapCache$0(String str) {
        return new ConcurrentHashMap();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveAllYearData$4(File file) {
        if (file != null && file.isFile() && file.getName().startsWith("year")) {
            String path = file.getPath();
            FileUtils.move(path, new SecureFile(this.mCacheDir + "/" + file.getName()).getPath());
        }
    }

    public void clearPartialYearBitmap() {
        Log.d("YearThumbnailLoader", "clearPartialYearBitmap");
        this.mPartialYearBitmapMemCache.clear();
    }

    public void clearYearBitmap() {
        Log.d("YearThumbnailLoader", "clearYearBitmap");
        this.mYearBitmapMemCache.clear();
    }

    public void deleteAllYearData() {
        ((Stream) Arrays.stream(this.mCacheDir.listFiles()).parallel()).forEach(new B(28));
    }

    public void deleteYearData(String str) {
        ((Stream) Arrays.stream(this.mCacheDir.listFiles()).parallel()).filter(new C0392b(str, 27)).findAny().ifPresent(new B(29));
    }

    public Bitmap getBrokenPieceBitmap() {
        if (this.mBrokenPieceBitmap == null) {
            this.mBrokenPieceBitmap = new BitmapOperator(ThumbnailLoader.getInstance().getReplacedThumbnail(AppResources.getAppContext(), R$drawable.gallery_ic_timeview_error, R$color.cloud_only_image_bg)).resize(32).apply();
        }
        return this.mBrokenPieceBitmap;
    }

    public String getCacheFilename(String str) {
        return "" + this.mCacheDir + "/" + str + ".bin";
    }

    public long getCacheSize() {
        return ((Stream) Arrays.stream(this.mCacheDir.listFiles()).parallel()).filter(new e(0)).mapToLong(new b(14)).sum();
    }

    public Bitmap getOldYearBitmap(String str) {
        String str2 = this.mYearKey.get(getYearCategory(str));
        if (str2 != null) {
            return getYearBitmap(str2);
        }
        return null;
    }

    public Bitmap getPartialYearBitmap(String str) {
        return this.mPartialYearBitmapMemCache.get(str);
    }

    public Bitmap getPieceBitmap(String str, int i2) {
        return getPieceBitmapCache(str).get(Integer.valueOf(i2));
    }

    public Bitmap getYearBitmap(String str) {
        if (this.mIsRTL != Features.isEnabled(Features.IS_RTL)) {
            clearYearBitmap();
            clearPartialYearBitmap();
            this.mIsRTL = !this.mIsRTL;
        }
        return this.mYearBitmapMemCache.get(str);
    }

    public void moveAllYearData() {
        File[] listFiles = AppResources.getAppContext().getCacheDir().listFiles();
        if (listFiles != null && listFiles.length > 0) {
            ((Stream) Arrays.stream(listFiles).parallel()).forEach(new o4.a(9, this));
        }
    }

    public void putPartialYearBitmap(String str, Bitmap bitmap) {
        this.mPartialYearBitmapMemCache.put(str, bitmap);
    }

    public void putPieceBitmap(String str, int i2, Bitmap bitmap) {
        getPieceBitmapCache(str).put(Integer.valueOf(i2), bitmap);
    }

    public void putYearBitmap(String str, Bitmap bitmap) {
        this.mYearBitmapMemCache.put(str, bitmap);
        String put = this.mYearKey.put(getYearCategory(str), str);
        if (put != null && !put.equals(str)) {
            removeYearBitmap(put);
        }
        this.mPartialYearBitmapMemCache.remove(str);
    }

    public void removeYearBitmap(String str) {
        Log.d("YearThumbnailLoader", "removeYearBitmap", str);
        this.mYearBitmapMemCache.remove(str);
    }
}
