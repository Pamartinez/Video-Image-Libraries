package com.samsung.android.sdk.pen.ocr;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.Size;
import com.samsung.android.sdk.pen.ocr.SpenLibraryLoader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.UUID;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpenMoireDetector {
    private static final int MOIRE_DETECTOR_MAX_HEIGHT = 5000;
    private static final int MOIRE_DETECTOR_MAX_WIDTH = 5000;
    private static final int MOIRE_DETECTOR_MIN_HEIGHT = 384;
    private static final int MOIRE_DETECTOR_MIN_WIDTH = 512;
    private static final String TAG = "SpenMoireDetector";
    private Context mContext = null;
    private long mNativeHandle = 0;

    public SpenMoireDetector(Context context, SpenIOcrEngine spenIOcrEngine) {
        loadMoireLibrary(context);
        this.mContext = context;
        long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
        initMoireEngineWithCacheFilePath(spenIOcrEngine);
        Log.i(TAG, String.format("[Moire Detection] [mNativeHandle : %s], Init : %dms", new Object[]{Long.toHexString(this.mNativeHandle), Long.valueOf(SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis)}));
    }

    public static void copyAssetFile(Context context, String str, String str2) {
        FileOutputStream fileOutputStream;
        File file = new File(getDirectoryPath(str) + "/" + str2);
        if (file.exists()) {
            boolean delete = file.delete();
            Log.i(TAG, "Old TF-Model is deleted? " + delete);
        }
        try {
            InputStream open = context.getAssets().open(str2);
            try {
                byte[] bArr = new byte[open.available()];
                fileOutputStream = new FileOutputStream(new File(str + "/" + str2));
                while (true) {
                    int read = open.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileOutputStream.close();
                        Log.e(TAG, "New TF-Model is installed.");
                        open.close();
                        return;
                    }
                }
            } catch (Throwable th) {
                if (open != null) {
                    open.close();
                }
                throw th;
            }
            throw th;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
    }

    public static native void deinitMoireEngine(long j2);

    public static String getDirectoryPath(String str) {
        String substring = str.substring(0, str.lastIndexOf("/") + 1);
        if (substring.substring(substring.length() - 1, substring.length()).equals("/")) {
            return C0280e.d(1, 0, substring);
        }
        return substring;
    }

    private boolean hasMoire(Bitmap bitmap) {
        int i2;
        int i7;
        String str;
        ByteBuffer allocate = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(allocate);
        byte[] array = allocate.array();
        long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
        boolean z = true;
        if (1 != processMoireDetection(this.mNativeHandle, array, bitmap.getWidth(), bitmap.getHeight())) {
            z = false;
        }
        long currentThreadTimeMillis2 = SystemClock.currentThreadTimeMillis();
        Size imageResolution = getImageResolution(bitmap);
        if (imageResolution != null) {
            i2 = imageResolution.getWidth();
            i7 = imageResolution.getHeight();
        } else {
            i2 = -1;
            i7 = -1;
        }
        Log.i(TAG, String.format("[Moire Detection] Resolution : %d x %d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i7)}));
        if (z) {
            str = "Positive (Moire)";
        } else {
            str = "Negative";
        }
        Log.i(TAG, "[Moire Detection] Result     : ".concat(str));
        Log.i(TAG, String.format("[Moire Detection] Processing : %dms", new Object[]{Long.valueOf(currentThreadTimeMillis2 - currentThreadTimeMillis)}));
        return z;
    }

    public static native long initMoireEngine(String str);

    private synchronized void initMoireEngineWithCacheFilePath(SpenIOcrEngine spenIOcrEngine) {
        String cachedDBFilePath = spenIOcrEngine.getCachedDBFilePath(this.mContext, "moire_".concat(String.valueOf(UUID.randomUUID().toString().replace("-", ""))), SpenOcrDataProviderContract.ASSETS_MODEL_MOIRE_DETECTOR, SpenDBType.MoireDetector);
        if (TextUtils.isEmpty(cachedDBFilePath)) {
            Log.e(TAG, "Moire DB path is not valid!");
            return;
        }
        this.mNativeHandle = initMoireEngine(cachedDBFilePath);
        spenIOcrEngine.releaseCachedDBFilePath(this.mContext, cachedDBFilePath);
    }

    private void loadMoireLibrary(Context context) {
        SpenLibraryLoader.LibType libType = SpenLibraryLoader.LibType.Moire;
        if (SpenLibraryLoader.loadRemoteLibrary(context, libType)) {
            Log.i(TAG, "loadMoireLibrary : Complete to load remote library!");
            return;
        }
        Log.i(TAG, "loadMoireLibrary : Cannot load remote library, so load local or system library");
        try {
            System.loadLibrary(SpenLibraryLoader.getMainLibName(libType));
        } catch (UnsatisfiedLinkError e) {
            Log.e(TAG, "loadMoireLibrary : Moire is not supported: " + e.getLocalizedMessage());
        }
    }

    public static native int processMoireDetection(long j2, byte[] bArr, int i2, int i7);

    public void close() {
        if (this.mNativeHandle != 0) {
            long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
            deinitMoireEngine(this.mNativeHandle);
            long currentThreadTimeMillis2 = SystemClock.currentThreadTimeMillis();
            this.mNativeHandle = 0;
            Log.i(TAG, String.format("[Moire Detection] Release : %dms", new Object[]{Long.valueOf(currentThreadTimeMillis2 - currentThreadTimeMillis)}));
        }
    }

    public boolean detectMoire(Bitmap bitmap) {
        int i2;
        int i7;
        int i8 = 0;
        if (this.mNativeHandle == 0 || bitmap == null) {
            Log.e(TAG, "[Moire Detection] detectMoire(Bitmap bitmap) Failed! (mNativeHandle == 0 || bitmap == null)");
            return false;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Log.d(TAG, String.format("[Moire Detection] detectMoire (w[%d] x h[%d])", new Object[]{Integer.valueOf(width), Integer.valueOf(height)}));
        if (width < 512 || height < MOIRE_DETECTOR_MIN_HEIGHT) {
            Log.w(TAG, String.format("[Moire Detection] detectMoire(Bitmap bitmap) Skipped! (w[%d] x h[%d] is shorter than the min size.)", new Object[]{Integer.valueOf(width), Integer.valueOf(height)}));
            return false;
        }
        if (width > 5000) {
            i7 = (width - 5000) / 2;
            i2 = 5000;
        } else {
            i2 = width;
            i7 = 0;
        }
        if (height > 5000) {
            i8 = (height - 5000) / 2;
            height = 5000;
        }
        if (i7 == 0 && i8 == 0) {
            Log.i(TAG, String.format("[Moire Detection] detectMoire (mNativeHandle : [%x])", new Object[]{Long.valueOf(this.mNativeHandle)}));
            return hasMoire(bitmap);
        }
        Log.i(TAG, String.format("[Moire Detection] detectMoire with cropped bitmap (mNativeHandle : [%x])", new Object[]{Long.valueOf(this.mNativeHandle)}));
        long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, i7, i8, i2, height);
        Log.d(TAG, String.format("[Moire Detection] Crop time (Bitmap.createBitmap()) %dms", new Object[]{Long.valueOf(SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis)}));
        boolean hasMoire = hasMoire(createBitmap);
        createBitmap.recycle();
        return hasMoire;
    }

    public void finalize() {
        super.finalize();
        close();
    }

    public Size getImageResolution(Bitmap bitmap) {
        if (bitmap != null) {
            return new Size(bitmap.getWidth(), bitmap.getHeight());
        }
        return null;
    }
}
