package com.samsung.android.ocr;

import A8.C0545b;
import android.graphics.Bitmap;
import com.samsung.android.ocr.MOCRConstants;
import com.samsung.android.ocr.MOCRResult;
import com.samsung.android.ocr.stride.Stride;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MOCR {
    private static final String TAG = "MOCR";
    public static final String aarVersion = "1.7.6";
    private static MOCRConstants.MOCREngineType engineType;
    private static volatile MOCR mMOCR;
    protected boolean initialized = false;

    static {
        engineType = MOCRConstants.MOCREngineType.None;
        MOCRLog.i(TAG, "AAR(1.7.6)");
        try {
            System.loadLibrary("Stride.camera.samsung");
            engineType = MOCRConstants.MOCREngineType.Stride;
        } catch (UnsatisfiedLinkError e) {
            MOCRLog.e(TAG, "Unable to load STRIDE library. Either device model not supported or no permission to read system lib.");
            engineType = MOCRConstants.MOCREngineType.None;
            e.printStackTrace();
        }
    }

    public static MOCR getInstance() {
        if (mMOCR == null) {
            synchronized (MOCR.class) {
                try {
                    if (mMOCR == null && engineType == MOCRConstants.MOCREngineType.Stride) {
                        mMOCR = Stride.getInstance();
                        String str = TAG;
                        MOCRLog.i(str, "Selected STRIDE engine lib version: " + mMOCR.getVersion() + " , aar version: 1.7.6");
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return mMOCR;
    }

    public static boolean isFeatureSupported(String str, int i2, int i7, int i8) {
        String version = mMOCR.getVersion();
        int[] array = Stream.of(version.split("\\.")).mapToInt(new C0545b(3)).toArray();
        int i10 = array[0];
        if (i10 >= i2) {
            if (i10 > i2) {
                return true;
            }
            int i11 = array[1];
            if (i11 >= i7 && (i11 > i7 || array[2] >= i8)) {
                return true;
            }
        }
        String str2 = TAG;
        MOCRLog.e(str2, str + " unavailable for engine version " + version + ". Please update binary.");
        return false;
    }

    public static boolean isOCRSupport() {
        if (engineType == MOCRConstants.MOCREngineType.None) {
            MOCRLog.i(TAG, "OCR Unsupported");
            return false;
        }
        String str = TAG;
        MOCRLog.i(str, "OCR Supported : " + engineType + " AAR version: 1.7.6");
        return true;
    }

    public abstract int cvtToLang(int i2);

    public abstract void deinit();

    public synchronized void deinitialize() {
        try {
            String str = TAG;
            MOCRLog.i(str, "deinitialize : E");
            if (this.initialized) {
                long nanoTime = System.nanoTime();
                mMOCR.deinit();
                this.initialized = false;
                MOCRLog.i(str, "deinitialize : X " + ((System.nanoTime() - nanoTime) / 1000000) + "ms");
            } else {
                MOCRLog.i(str, "deinitialize : X :: engine was not initialized");
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public abstract int detect(byte[] bArr, int i2, int i7, MOCRConstants.MOCRPxlFmt mOCRPxlFmt, MOCRResult.Page page);

    public abstract boolean detectText(byte[] bArr, int i2, int i7, MOCRConstants.MOCRPxlFmt mOCRPxlFmt);

    public abstract boolean detectText_ARGB(MOCRImage mOCRImage);

    public synchronized boolean detectText_ARGB_bmp(Bitmap bitmap) {
        boolean z;
        MOCRImage fromBitmap;
        try {
            String str = TAG;
            MOCRLog.i(str, "detectText : E" + engineType.toString());
            MOCRLog.i(str, "Image Wd/Ht = " + bitmap.getWidth() + " / " + bitmap.getHeight());
            long nanoTime = System.nanoTime();
            if (!this.initialized || (fromBitmap = MOCRImage.fromBitmap(bitmap)) == null) {
                z = false;
            } else {
                z = mMOCR.detectText_ARGB(fromBitmap);
            }
            MOCRLog.i(str, "detectText : X(" + z + ") " + ((System.nanoTime() - nanoTime) / 1000000) + "ms");
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return z;
    }

    public synchronized boolean detectText_byteArr(byte[] bArr, int i2, int i7, MOCRConstants.MOCRPxlFmt mOCRPxlFmt) {
        boolean z;
        try {
            String str = TAG;
            MOCRLog.i(str, "detectText : E" + engineType.toString());
            MOCRLog.i(str, "Image Wd/Ht = " + i2 + " / " + i7);
            long nanoTime = System.nanoTime();
            if (this.initialized) {
                z = mMOCR.detectText(bArr, i2, i7, mOCRPxlFmt);
            } else {
                z = false;
            }
            MOCRLog.i(str, "detectText : X(" + z + ") " + ((System.nanoTime() - nanoTime) / 1000000) + "ms");
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return z;
    }

    public abstract int detect_ARGB(MOCRImage mOCRImage, MOCRResult.Page page);

    public int detect_ARGB_bmp(Bitmap bitmap, MOCRResult.Page page) {
        String str = TAG;
        MOCRLog.i(str, "detect : E" + engineType.toString());
        long nanoTime = System.nanoTime();
        int value = MOCRConstants.MOCRStatus.MOCRModelNotInitError.getValue();
        synchronized (MOCR.class) {
            try {
                if (this.initialized) {
                    MOCRImage fromBitmap = MOCRImage.fromBitmap(bitmap);
                    if (fromBitmap == null) {
                        value = MOCRConstants.MOCRStatus.MOCRUnsupportedFormat.getValue();
                    } else {
                        value = mMOCR.detect_ARGB(fromBitmap, page);
                    }
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        MOCRLog.i(str, "detect : X(" + value + ") " + ((System.nanoTime() - nanoTime) / 1000000) + "ms");
        return value;
    }

    public int detect_byteArr(byte[] bArr, int i2, int i7, MOCRConstants.MOCRPxlFmt mOCRPxlFmt, MOCRResult.Page page) {
        String str = TAG;
        MOCRLog.i(str, "detect : E" + engineType.toString());
        long nanoTime = System.nanoTime();
        int value = MOCRConstants.MOCRStatus.MOCRModelNotInitError.getValue();
        synchronized (MOCR.class) {
            try {
                if (this.initialized) {
                    value = mMOCR.detect(bArr, i2, i7, mOCRPxlFmt, page);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        MOCRLog.i(str, "detect : X(" + value + ") " + ((System.nanoTime() - nanoTime) / 1000000) + "ms");
        return value;
    }

    public abstract String getBuildType();

    public int getMOCREngineType() {
        return engineType.getValue();
    }

    public abstract String getVersion();

    public abstract int init(String str, int i2);

    public abstract int init(String str, int i2, boolean z);

    public synchronized int initialize(int i2) {
        int i7;
        try {
            String str = TAG;
            MOCRLog.i(str, "initialize : E");
            long nanoTime = System.nanoTime();
            if (this.initialized) {
                MOCRLog.i(str, "Engine already initialized!!");
                i7 = MOCRConstants.MOCRStatus.MOCRActiveEngineError.getValue();
            } else {
                i7 = mMOCR.init((String) null, cvtToLang(i2));
                if (i7 == MOCRConstants.MOCRStatus.MOCRSuccess.getValue()) {
                    this.initialized = true;
                }
            }
            MOCRLog.i(str, "initialize : X(" + i7 + ") " + ((System.nanoTime() - nanoTime) / 1000000) + "ms");
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return i7;
    }

    public synchronized int process(byte[] bArr, int i2, int i7, MOCRConstants.MOCRPxlFmt mOCRPxlFmt, MOCRResult.Page page) {
        int value;
        try {
            String str = TAG;
            MOCRLog.i(str, "process : E" + engineType.toString());
            MOCRLog.i(str, "Image Wd/Ht = " + i2 + " / " + i7);
            long nanoTime = System.nanoTime();
            value = MOCRConstants.MOCRStatus.MOCRModelNotInitError.getValue();
            if (this.initialized) {
                value = mMOCR.run(bArr, i2, i7, mOCRPxlFmt, page);
            }
            MOCRLog.i(str, "process : X(" + value + ") " + ((System.nanoTime() - nanoTime) / 1000000) + "ms");
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return value;
    }

    public synchronized int process_ARGB_bmp(Bitmap bitmap, MOCRResult.Page page) {
        int value;
        try {
            String str = TAG;
            MOCRLog.i(str, "process : E" + engineType.toString());
            MOCRLog.i(str, "Image Wd/Ht = " + bitmap.getWidth() + " / " + bitmap.getHeight());
            long nanoTime = System.nanoTime();
            value = MOCRConstants.MOCRStatus.MOCRModelNotInitError.getValue();
            if (this.initialized) {
                MOCRImage fromBitmap = MOCRImage.fromBitmap(bitmap);
                if (fromBitmap == null) {
                    value = MOCRConstants.MOCRStatus.MOCRUnsupportedFormat.getValue();
                } else {
                    value = mMOCR.run_ARGB(fromBitmap, page);
                }
            }
            MOCRLog.i(str, "process : X(" + value + ") " + ((System.nanoTime() - nanoTime) / 1000000) + "ms");
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return value;
    }

    public abstract int run(byte[] bArr, int i2, int i7, MOCRConstants.MOCRPxlFmt mOCRPxlFmt, MOCRResult.Page page);

    public abstract int run_ARGB(MOCRImage mOCRImage, MOCRResult.Page page);

    public abstract void set_options(MOCROptions mOCROptions);

    public static MOCR getInstance(MOCRConstants.MOCREngineType mOCREngineType) {
        engineType = mOCREngineType;
        if (mOCREngineType == MOCRConstants.MOCREngineType.Stride) {
            try {
                System.loadLibrary("Stride.camera.samsung");
            } catch (UnsatisfiedLinkError e) {
                engineType = MOCRConstants.MOCREngineType.None;
                e.printStackTrace();
            }
            mMOCR = Stride.getInstance();
            String str = TAG;
            MOCRLog.i(str, "Selected STRIDE engine lib version: " + mMOCR.getVersion() + " , aar version: 1.7.6");
        }
        return mMOCR;
    }

    public synchronized int initialize(String str, int i2) {
        int i7;
        try {
            String str2 = TAG;
            MOCRLog.i(str2, "initialize with modelPath(" + str + ") : E");
            long nanoTime = System.nanoTime();
            if (this.initialized) {
                MOCRLog.i(str2, "Engine already initialized!!");
                i7 = MOCRConstants.MOCRStatus.MOCRActiveEngineError.getValue();
            } else {
                i7 = mMOCR.init(str, cvtToLang(i2));
                if (i7 == MOCRConstants.MOCRStatus.MOCRSuccess.getValue()) {
                    this.initialized = true;
                }
            }
            MOCRLog.i(str2, "initialize with modelPath : X " + ((System.nanoTime() - nanoTime) / 1000000) + "ms");
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return i7;
    }

    public static MOCR getInstance(MOCROptions mOCROptions) {
        getInstance();
        if (mOCROptions.isForceLang() && !isFeatureSupported("ForceLang_MOCROption", 1, 4, 0)) {
            MOCRLog.e(TAG, "ForceLang Option unavailable for current engine version");
            return mMOCR;
        } else if (mOCROptions.isRunInverted() && !isFeatureSupported("RunInverted_MOCROption", 1, 5, 14)) {
            MOCRLog.e(TAG, "RunInverted Option unavailable for current engine version");
            return mMOCR;
        } else if (mOCROptions.getLanguageMode() != MOCRConstants.MOCRLanguageMode.Auto.getValue() && !isFeatureSupported("LanguageMode_MOCROption", 1, 6, 9)) {
            MOCRLog.e(TAG, "LanguageMode Option unavailable for current engine version");
            return mMOCR;
        } else if (mOCROptions.getImageType() == MOCRConstants.MOCRImageType.Generic.getValue() || isFeatureSupported("ImageType_MOCROption", 1, 6, 9)) {
            mMOCR.set_options(mOCROptions);
            return mMOCR;
        } else {
            MOCRLog.e(TAG, "ImageType Option unavailable for current engine version");
            return mMOCR;
        }
    }

    public synchronized int initialize() {
        int i2;
        try {
            String str = TAG;
            MOCRLog.i(str, "initialize without lang : E");
            long nanoTime = System.nanoTime();
            if (this.initialized) {
                MOCRLog.i(str, "Engine already initialized!!");
                i2 = MOCRConstants.MOCRStatus.MOCRActiveEngineError.getValue();
            } else {
                i2 = mMOCR.init((String) null, -1);
                if (i2 == MOCRConstants.MOCRStatus.MOCRSuccess.getValue()) {
                    this.initialized = true;
                }
            }
            MOCRLog.i(str, "initialize without lang : X(" + i2 + ") " + ((System.nanoTime() - nanoTime) / 1000000) + "ms");
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return i2;
    }

    public synchronized int initialize(boolean z) {
        int i2;
        String str = TAG;
        MOCRLog.i(str, "initialize without lang with keepOpen(" + z + ") : E");
        if (!z) {
            return initialize();
        } else if (!isFeatureSupported("Init_keepOpen", 1, 17, 3)) {
            MOCRLog.e(str, "Init_keepOpen option unavailable; falling back to old API");
            return initialize();
        } else {
            long nanoTime = System.nanoTime();
            if (this.initialized) {
                MOCRLog.i(str, "Engine already initialized!!");
                i2 = MOCRConstants.MOCRStatus.MOCRActiveEngineError.getValue();
            } else {
                i2 = mMOCR.init((String) null, -1, z);
                if (i2 == MOCRConstants.MOCRStatus.MOCRSuccess.getValue()) {
                    this.initialized = true;
                }
            }
            MOCRLog.i(str, "initialize without lang with modelPath : X " + ((System.nanoTime() - nanoTime) / 1000000) + "ms");
            return i2;
        }
    }

    public synchronized int initialize(int i2, boolean z) {
        int i7;
        String str = TAG;
        MOCRLog.i(str, "initialize with keepOpen(" + z + ") : E");
        if (!z) {
            return initialize(i2);
        } else if (!isFeatureSupported("Init_keepOpen", 1, 17, 3)) {
            MOCRLog.e(str, "Init_keepOpen option unavailable; falling back to old API");
            return initialize(i2);
        } else {
            long nanoTime = System.nanoTime();
            if (this.initialized) {
                MOCRLog.i(str, "Engine already initialized!!");
                i7 = MOCRConstants.MOCRStatus.MOCRActiveEngineError.getValue();
            } else {
                i7 = mMOCR.init((String) null, cvtToLang(i2), z);
                if (i7 == MOCRConstants.MOCRStatus.MOCRSuccess.getValue()) {
                    this.initialized = true;
                }
            }
            MOCRLog.i(str, "initialize : X(" + i7 + ") " + ((System.nanoTime() - nanoTime) / 1000000) + "ms");
            return i7;
        }
    }

    public synchronized int initialize(String str, int i2, boolean z) {
        int i7;
        String str2 = TAG;
        MOCRLog.i(str2, "initialize with modelPath(" + str + ") and keepOpen(" + z + ") : E");
        if (!z) {
            return initialize(str, i2);
        } else if (!isFeatureSupported("Init_keepOpen", 1, 17, 3)) {
            MOCRLog.e(str2, "Init_keepOpen option unavailable; falling back to old API");
            return initialize(str, i2);
        } else {
            long nanoTime = System.nanoTime();
            if (this.initialized) {
                MOCRLog.i(str2, "Engine already initialized!!");
                i7 = MOCRConstants.MOCRStatus.MOCRActiveEngineError.getValue();
            } else {
                i7 = mMOCR.init(str, cvtToLang(i2), z);
                if (i7 == MOCRConstants.MOCRStatus.MOCRSuccess.getValue()) {
                    this.initialized = true;
                }
            }
            MOCRLog.i(str2, "initialize with modelPath : X " + ((System.nanoTime() - nanoTime) / 1000000) + "ms");
            return i7;
        }
    }
}
