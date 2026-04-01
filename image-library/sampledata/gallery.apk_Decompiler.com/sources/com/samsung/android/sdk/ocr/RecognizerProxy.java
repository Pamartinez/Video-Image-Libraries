package com.samsung.android.sdk.ocr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.samsung.android.sdk.ocr.OCRException;
import com.samsung.android.sdk.ocr.service.IOCRService;
import com.samsung.android.sdk.ocr.service.OCRServiceBundleUtils;
import com.samsung.android.sdk.ocr.service.OCRServiceCreator;
import java.util.UUID;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecognizerProxy implements IRecognizer {
    private static final String TAG = "RecognizerProxy";
    private OCRLanguage mOCRLanguage;
    private OCRServiceCreator mOCRServiceCreator;
    private OCRType mOCRType;
    private String mUUID;

    public RecognizerProxy(RecognizerParams recognizerParams) {
        this(recognizerParams.context, recognizerParams.ocrType, recognizerParams.language);
    }

    private void checkServiceStatus() {
        if (this.mOCRServiceCreator == null) {
            throw new OCRException.InvalidUsageException("RecognizerProxy is closed or has not been created");
        }
    }

    private static void checkThread() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            Log.e(TAG, "used in main thread of RecognizerProxy!");
            throw new OCRException.CallOnMainThreadException("Should use RecognizerProxy in worker thread!");
        }
    }

    private IOCRService connectOCRService() {
        checkThread();
        checkServiceStatus();
        return getConnectedService();
    }

    private Bundle getBitmapBundle(Bitmap bitmap) {
        return OCRServiceBundleUtils.getBitmapBundle(this.mOCRType.getValue(), this.mOCRLanguage.toString(), this.mUUID, bitmap);
    }

    private Bundle getBundleForHasTextSpecialCase(Bitmap bitmap, boolean z) {
        return OCRServiceBundleUtils.getBundleForHasTextSpecialCase(this.mOCRType.getValue(), this.mOCRLanguage.toString(), this.mUUID, bitmap, z);
    }

    private IOCRService getConnectedService() {
        this.mOCRServiceCreator.connect();
        IOCRService service = this.mOCRServiceCreator.getService();
        if (service != null) {
            return service;
        }
        throw new OCRException.InvalidUsageException("Service is not connected");
    }

    private Bundle getDefaultBundle() {
        return OCRServiceBundleUtils.getDefaultBundle(this.mOCRType.getValue(), this.mOCRLanguage.toString(), this.mUUID);
    }

    public static boolean isSupported(OCRType oCRType) {
        if (oCRType == OCRType.OCR_HANDWRITTEN) {
            return true;
        }
        return MOCRecognizer.isSupported();
    }

    public void cancel() {
        Log.i(TAG, "RecognizerProxy cancel()");
        checkThread();
        if (this.mOCRServiceCreator != null) {
            try {
                getConnectedService().cancel(getDefaultBundle());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        Log.i(TAG, "RecognizerProxy close()");
        checkThread();
        if (this.mOCRServiceCreator != null) {
            try {
                getConnectedService().close(getDefaultBundle());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            this.mOCRServiceCreator.close();
            this.mOCRServiceCreator = null;
        }
    }

    public boolean detect(Bitmap bitmap, OCRResult oCRResult) {
        if (!RecognizerAPIChecker.isValidParameter(bitmap, oCRResult)) {
            return false;
        }
        try {
            Bundle detect = connectOCRService().detect(getBitmapBundle(bitmap));
            if (detect != null) {
                return OCRServiceBundleUtils.getResultFromBundle(detect, oCRResult);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean detectBlock(Bitmap bitmap, Point[] pointArr) {
        return detectBlock(bitmap, new Point(bitmap.getWidth() / 2, bitmap.getHeight() / 2), pointArr);
    }

    public boolean detectText(Bitmap bitmap) {
        if (!RecognizerAPIChecker.isValidParameter(bitmap)) {
            return false;
        }
        try {
            return connectOCRService().detectText(getBitmapBundle(bitmap));
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void finalize() {
        close();
    }

    public boolean hasText(Bitmap bitmap) {
        if (!RecognizerAPIChecker.isValidParameter(bitmap)) {
            return false;
        }
        try {
            return connectOCRService().hasText(getBitmapBundle(bitmap));
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isHandwritten(Bitmap bitmap) {
        if (!RecognizerAPIChecker.isValidParameter(bitmap)) {
            return false;
        }
        try {
            return connectOCRService().isHandwritten(getBitmapBundle(bitmap));
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isPrinted(Bitmap bitmap) {
        if (!RecognizerAPIChecker.isValidParameter(bitmap)) {
            return false;
        }
        try {
            return connectOCRService().isPrinted(getBitmapBundle(bitmap));
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean recognize(Bitmap bitmap, OCRResult oCRResult) {
        if (!RecognizerAPIChecker.isValidParameter(bitmap, oCRResult)) {
            return false;
        }
        try {
            Bundle recognize = connectOCRService().recognize(getBitmapBundle(bitmap));
            if (recognize != null) {
                return OCRServiceBundleUtils.getResultFromBundle(recognize, oCRResult);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean recognizeBlockAt(Bitmap bitmap, Point point, boolean z, OCRResult oCRResult) {
        if (!RecognizerAPIChecker.isValidParameter(bitmap, oCRResult)) {
            return false;
        }
        IOCRService connectOCRService = connectOCRService();
        try {
            Bundle bitmapBundle = getBitmapBundle(bitmap);
            OCRServiceBundleUtils.putInputPointInBundle(bitmapBundle, point);
            OCRServiceBundleUtils.putForceToSelectBundle(bitmapBundle, z);
            Bundle recognizeBlockAt = connectOCRService.recognizeBlockAt(bitmapBundle);
            if (recognizeBlockAt != null) {
                return OCRServiceBundleUtils.getResultFromBundle(recognizeBlockAt, oCRResult);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public RecognizerProxy(Context context, OCRType oCRType, OCRLanguage oCRLanguage) {
        this.mOCRType = OCRType.OCR_ALL;
        this.mOCRLanguage = OCRLanguage.AUTO;
        this.mUUID = "";
        Log.i(TAG, "OCR RecognizerProxy(Service) is initialized with version: 3.4.251128");
        checkThread();
        this.mOCRType = oCRType;
        this.mOCRLanguage = oCRLanguage;
        this.mUUID = context.getApplicationContext().getApplicationInfo().uid + "_" + UUID.randomUUID().toString();
        StringBuilder sb2 = new StringBuilder("RecognizerProxy : UUID : ");
        sb2.append(this.mUUID);
        Log.i(TAG, sb2.toString());
        this.mOCRServiceCreator = new OCRServiceCreator(context);
        try {
            if (!getConnectedService().initialize(getDefaultBundle())) {
                Log.e(TAG, "cannot initialize service");
                throw new OCRException.UnsupportedRecognizerException("Cannot initialize OCR Service");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean detectBlock(Bitmap bitmap, Point point, Point[] pointArr) {
        if (!RecognizerAPIChecker.isValidParameter(bitmap, pointArr)) {
            return false;
        }
        IOCRService connectOCRService = connectOCRService();
        try {
            Bundle bitmapBundle = getBitmapBundle(bitmap);
            OCRServiceBundleUtils.putInputPointInBundle(bitmapBundle, point);
            Bundle detectBlock = connectOCRService.detectBlock(bitmapBundle);
            if (detectBlock != null) {
                return OCRServiceBundleUtils.getResultFromBundle(detectBlock, pointArr);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hasText(Bitmap bitmap, boolean z) {
        if (!RecognizerAPIChecker.isValidParameter(bitmap)) {
            return false;
        }
        try {
            return connectOCRService().hasText(getBundleForHasTextSpecialCase(bitmap, z));
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }
}
