package com.samsung.android.sdk.pen.ocr;

import N2.j;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.samsung.android.sdk.ocr.OCRException;
import com.samsung.android.sdk.pen.ocr.SpenLibraryLoader;
import com.samsung.android.sdk.pen.ocr.SpenOcrModelLoaderFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpenOcrEngine implements SpenIOcrEngine {
    private static final String TAG = "SpenOcrEngine";
    private static boolean mSOCRSupport = false;
    private SpenIOcrModelLoader mModelLoader = null;
    protected SpenIOcrModelManager mModelManager = null;
    private long mNativeHandle;

    public SpenOcrEngine(Context context, SpenOcrModelLoaderFactory.MODEL_LOADER model_loader) {
        if (!mSOCRSupport) {
            mSOCRSupport = loadOcrLibrary(context);
        }
        long Native_init = Native_init();
        this.mNativeHandle = Native_init;
        if (Native_init != 0) {
            Log.i(TAG, "SpenOcrEngine is created! [mNativeHandle : " + Long.toHexString(this.mNativeHandle) + "] [whichModelLoader :" + model_loader + "]");
            SpenOcrModelManager spenOcrModelManager = new SpenOcrModelManager(Native_createModelManager(this.mNativeHandle));
            this.mModelManager = spenOcrModelManager;
            SpenIOcrModelLoader createModelLoader = SpenOcrModelLoaderFactory.createModelLoader(context, spenOcrModelManager, model_loader);
            this.mModelLoader = createModelLoader;
            if (!createModelLoader.loadCommonDB()) {
                Log.w(TAG, "SpenOcrEngine:: Cannot load common DB!");
                if (SpenRecogConfig.getOneUIVersion() < 80500) {
                    Log.w(TAG, "SpenOcrEngine:: This device needs to be update of OCRDataProvider");
                    return;
                }
                if (SpenRecogConfig.isCachedStateProcess(context.getApplicationContext())) {
                    Log.i(TAG, "SpenOcrEngine:: This process is cached state!");
                }
                Log.w(TAG, "SpenOcrEngine:: Do not throw exception temporarily because of Sync transaction while frozen");
                return;
            }
            return;
        }
        Log.e(TAG, "SpenOcrEngine::SpenOcrEngine() Failed! (mNativeHandle == 0)");
        throw new OCRException.InitializationFailedException("Fail to create SpenOcrEngine : mNativeHandle == 0");
    }

    private static boolean isProviderAvailable(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.samsung.android.sdk.ocr", 128);
            Log.i(TAG, "OCRDataProvider Version = " + packageInfo.versionName);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e(TAG, "OCRDataProvider is not found");
            return false;
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
            return false;
        }
    }

    public static boolean isSupported(Context context) {
        if (!isProviderAvailable(context)) {
            Log.w(TAG, "SOCR is not supported: OCR provider does not exist.");
            return false;
        }
        boolean loadOcrLibrary = loadOcrLibrary(context);
        mSOCRSupport = loadOcrLibrary;
        if (!loadOcrLibrary) {
            Log.e(TAG, "SOCR is not supported: so library does not exist.");
        }
        return mSOCRSupport;
    }

    private static boolean loadOcrLibrary(Context context) {
        SpenLibraryLoader.LibType libType = SpenLibraryLoader.LibType.OCR;
        if (SpenLibraryLoader.loadRemoteLibrary(context, libType)) {
            Log.i(TAG, "loadOcrLibrary : Complete to load remote library!");
            return true;
        }
        Log.i(TAG, "loadOcrLibrary : Cannot load remote library, so load local or system library");
        try {
            System.loadLibrary(SpenLibraryLoader.getMainLibName(libType));
            return true;
        } catch (UnsatisfiedLinkError e) {
            Log.e(TAG, "loadOcrLibrary : SOCR is not supported: " + e.getLocalizedMessage());
            return false;
        }
    }

    public native long Native_createModelManager(long j2);

    public native long Native_createRecognizer(long j2);

    public native long Native_createTypeClassifier(long j2);

    public native void Native_finalize(long j2);

    public native String Native_getEngineVersion(long j2);

    public native long Native_init();

    public void close() {
        SpenIOcrModelLoader spenIOcrModelLoader = this.mModelLoader;
        if (spenIOcrModelLoader != null) {
            spenIOcrModelLoader.close();
        }
        SpenIOcrModelManager spenIOcrModelManager = this.mModelManager;
        if (spenIOcrModelManager != null) {
            spenIOcrModelManager.close();
            this.mModelManager = null;
        }
        long j2 = this.mNativeHandle;
        if (j2 != 0) {
            Native_finalize(j2);
        }
        this.mNativeHandle = 0;
    }

    public SpenIOcrRecognizer createRecognizer() {
        long j2 = this.mNativeHandle;
        if (j2 != 0) {
            return new SpenOcrRecognizer(Native_createRecognizer(j2));
        }
        Log.e(TAG, "SpenOcrEngine::createRecognizer() Failed! (mNativeHandle == 0)");
        return null;
    }

    public SpenITypeClassifier createTypeClassifier() {
        long j2 = this.mNativeHandle;
        if (j2 != 0) {
            return new SpenTypeClassifier(Native_createTypeClassifier(j2));
        }
        Log.e(TAG, "SpenOcrEngine::createTypeClassifier() Failed! (mNativeHandle == 0)");
        return null;
    }

    public void finalize() {
        super.finalize();
        close();
    }

    public String getCachedDBFilePath(Context context, String str, String str2, SpenDBType spenDBType) {
        return this.mModelLoader.getCachedDBFilePath(context, str, str2, spenDBType);
    }

    public String getEngineVersion() {
        long j2 = this.mNativeHandle;
        if (j2 != 0) {
            return Native_getEngineVersion(j2);
        }
        Log.e(TAG, "SpenOcrEngine::getEngineVersion() Failed! (mNativeHandle == 0)");
        return "";
    }

    public boolean isSupportedLanguage(SpenOcrLanguage spenOcrLanguage) {
        if (spenOcrLanguage == SpenOcrLanguage.AUTO) {
            return true;
        }
        return this.mModelLoader.getSupportedLanguages().contains(spenOcrLanguage.toLanguageCode());
    }

    public boolean loadLanguageDB(String str) {
        StringBuilder k = j.k("SpenOcrEngine loadLanguageDB(", str, ") [mNativeHandle : ");
        k.append(Long.toHexString(this.mNativeHandle));
        k.append("]");
        Log.i(TAG, k.toString());
        return this.mModelLoader.loadLanguageDB(str);
    }

    public void releaseCachedDBFilePath(Context context, String str) {
        this.mModelLoader.releaseCachedDBFilePath(context, str);
    }

    public SpenOcrEngine() {
        long Native_init = Native_init();
        this.mNativeHandle = Native_init;
        if (Native_init == 0) {
            Log.e(TAG, "SpenOcrEngine::SpenOcrEngine() Failed! (mNativeHandle == 0)");
        }
    }
}
