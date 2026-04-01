package com.samsung.android.gallery.module.graphics;

import A.a;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.graphics.Gainmap;
import android.os.Build;
import com.glidebitmappool.GlideBitmapPool;
import com.glidebitmappool.internal.Util;
import com.samsung.android.gallery.database.dbtype.MimeType;
import com.samsung.android.gallery.module.graphics.BitmapHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ObjectDictionary;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.sgpl.graphics.CodecBitmapPool;
import java.util.Collections;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BitmapPool implements CodecBitmapPool {
    private static final String TAG = "BitmapPool";
    static final BitmapPool sInstance = new BitmapPool();
    private final Semaphore LOCK = new Semaphore(1);
    private final AtomicInteger mRefCount = new AtomicInteger(0);

    public static BitmapPool getInstance() {
        return sInstance;
    }

    public void apply(BitmapFactory.Options options) {
        int i2;
        Bitmap.Config config;
        int i7 = options.outWidth;
        if (i7 > 0 && (i2 = options.outHeight) > 0 && i7 < 268435455 && i2 < 268435455 && options.inPreferredConfig != (config = Bitmap.Config.HARDWARE) && options.outConfig != config && !MimeType.isRaw(options.outMimeType)) {
            try {
                Bitmap tryGet = tryGet((int) Math.ceil(((double) options.outWidth) / ((double) options.inSampleSize)), (int) Math.ceil(((double) options.outHeight) / ((double) options.inSampleSize)), options.outConfig, (ColorSpace) null);
                if (tryGet != null && Util.canUseForInBitmap(tryGet, options)) {
                    options.inBitmap = tryGet;
                    if (tryGet.getRowBytes() > 1000000) {
                        Log.e(TAG, "apply, but too big bitmap");
                    }
                }
            } catch (Exception e) {
                String str = TAG;
                Log.w((CharSequence) str, "apply failed. {" + options.outWidth + GlobalPostProcInternalPPInterface.SPLIT_REGEX + options.outHeight + GlobalPostProcInternalPPInterface.SPLIT_REGEX + options.inSampleSize + "}", (Throwable) e);
            }
        }
    }

    public void clear() {
        GlideBitmapPool.clearMemory();
    }

    public void close() {
        if (this.mRefCount.decrementAndGet() == 0) {
            String str = TAG;
            Log.d(str, "close " + this.mRefCount);
            clear();
        }
    }

    public Bitmap get(int i2, int i7, Bitmap.Config config) {
        if (config == null || config == Bitmap.Config.HARDWARE) {
            config = Bitmap.Config.ARGB_8888;
        }
        Bitmap tryGet = tryGet(i2, i7, config, (ColorSpace) null);
        return tryGet == null ? Bitmap.createBitmap(i2, i7, config) : tryGet;
    }

    public void init() {
        GlideBitmapPool.initialize(31457280, Collections.singleton(Bitmap.Config.ARGB_8888));
    }

    public void open() {
        if (this.mRefCount.getAndIncrement() == 0) {
            String str = TAG;
            Log.d(str, "open " + this.mRefCount);
        }
    }

    public void put(Bitmap bitmap) {
        Bitmap.Config config;
        if (bitmap != null) {
            try {
                if (!bitmap.isRecycled() && bitmap.isMutable() && (config = bitmap.getConfig()) != null && config != Bitmap.Config.HARDWARE) {
                    Boolean bool = (Boolean) ObjectDictionary.getTag(bitmap, "Recyclable");
                    if (bool == null || bool.booleanValue()) {
                        ObjectDictionary.clear(bitmap);
                        put(bitmap, config);
                        String str = TAG;
                        BitmapHelper.DebugLog.d(str, "put" + Logger.v(bitmap));
                    }
                }
            } catch (Error | IllegalStateException e) {
                a.z(e, new StringBuilder("put failed. e="), TAG);
            }
        }
    }

    public void trim(int i2) {
        GlideBitmapPool.trimMemory(i2);
    }

    public Bitmap tryGet(int i2, int i7, Bitmap.Config config, ColorSpace colorSpace) {
        Bitmap bitmap;
        try {
            if (this.LOCK.tryAcquire(10, TimeUnit.MILLISECONDS)) {
                if (colorSpace == null) {
                    bitmap = GlideBitmapPool.getBitmap(i2, i7, config);
                } else {
                    bitmap = GlideBitmapPool.getBitmap(i2, i7, config, colorSpace);
                }
                this.LOCK.release();
                return bitmap;
            }
            Log.w(TAG, "tryGet failed. lock");
            return null;
        } catch (Exception e) {
            String str = TAG;
            StringBuilder h5 = a.h(i2, i7, "tryGet failed {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            h5.append(config);
            h5.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            h5.append(colorSpace);
            h5.append("}");
            Log.e((CharSequence) str, h5.toString(), (Throwable) e);
            return null;
        } catch (Throwable th) {
            this.LOCK.release();
            throw th;
        }
    }

    public Bitmap get(int i2, int i7, Bitmap.Config config, ColorSpace colorSpace) {
        if (config == null || config == Bitmap.Config.HARDWARE) {
            config = Bitmap.Config.ARGB_8888;
        }
        Bitmap tryGet = tryGet(i2, i7, config, colorSpace);
        return tryGet == null ? Bitmap.createBitmap(i2, i7, config) : tryGet;
    }

    public void put(Bitmap bitmap, Bitmap.Config config) {
        bitmap.reconfigure(bitmap.getWidth(), bitmap.getHeight(), config);
        if (Build.VERSION.SDK_INT >= 34) {
            bitmap.setGainmap((Gainmap) null);
        }
        GlideBitmapPool.putBitmap(bitmap);
    }
}
