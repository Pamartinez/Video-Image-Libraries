package com.samsung.android.imagetranslation.jni;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import c0.C0086a;
import com.samsung.android.imagetranslation.LttEngine;
import com.samsung.android.imagetranslation.common.Dump;
import com.samsung.android.imagetranslation.common.LTTLogger;
import com.samsung.android.imagetranslation.common.LttEngineErrors;
import com.samsung.android.imagetranslation.data.LttEngineException;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.imagetranslation.inpainting.InpainterParam;
import com.samsung.android.imagetranslation.task.ProcessOCR;
import com.samsung.android.imagetranslation.task.ProcessTRL;
import com.samsung.android.imagetranslation.task.TextDistribution;
import com.samsung.android.imagetranslation.util.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LttNativeHelper {
    private static final int INITIALIZE_SUCCESS = 1;
    public static String NATIVE_LIBRARY_VERSION = null;
    private static final String TAG = "LttNativeHelper";

    static {
        try {
            LTTLogger.d(TAG, "Load library: LttEngine.camera.samsung");
            System.loadLibrary("LttEngine.camera.samsung");
        } catch (Throwable th) {
            String str = TAG;
            LTTLogger.i(str, "Failed to load library: " + th.getMessage());
            th.printStackTrace();
        }
    }

    private static native int[] _getMaskForInpainting(byte[] bArr, int i2, int i7, CopyOnWriteArrayList<SceneText> copyOnWriteArrayList);

    private static native String _getNativeParameter(int i2);

    private static native int _initialize();

    private static native int _release();

    private static native int _releaseInternal();

    private static native byte[] _renderImage(KeyFrameParam keyFrameParam);

    private static native int _setNativeParameter(LttEngineProperty lttEngineProperty);

    private static void setNativeVersion() {
        NATIVE_LIBRARY_VERSION = _getNativeParameter(1);
    }

    public void cropAndRestoreOriginalText(KeyFrameParam keyFrameParam, List<Path> list) {
        if (!list.isEmpty()) {
            long currentTimeMillis = System.currentTimeMillis();
            Bitmap copy = keyFrameParam.getInPaintedImage().copy(Bitmap.Config.ARGB_8888, true);
            Canvas canvas = new Canvas(copy);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            for (Path clipPath : list) {
                Bitmap createBitmap = Bitmap.createBitmap(keyFrameParam.getInputImage().getWidth(), keyFrameParam.getInputImage().getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(createBitmap);
                canvas2.save();
                canvas2.clipPath(clipPath);
                canvas2.drawBitmap(keyFrameParam.getInputImage(), 0.0f, 0.0f, paint);
                canvas2.restore();
                canvas.drawBitmap(createBitmap, 0.0f, 0.0f, paint);
                createBitmap.recycle();
            }
            String str = TAG;
            LTTLogger.d(str, "lines restored - " + list.size() + " time : " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
            if (Dump.IS_INPAINTED_FRAME_ENABLED) {
                Dump.dumpBitmap(copy, "retored_inpainted_image" + System.currentTimeMillis() + ".png", "RestoredImage");
            }
            keyFrameParam.setInPaintedImage(copy);
        }
    }

    public boolean filterSceneTextLines(CopyOnWriteArrayList<SceneText> copyOnWriteArrayList) {
        if (copyOnWriteArrayList == null) {
            return false;
        }
        boolean z = false;
        for (int i2 = 0; i2 < copyOnWriteArrayList.size(); i2++) {
            SceneText sceneText = copyOnWriteArrayList.get(i2);
            CopyOnWriteArrayList<SceneText> components = sceneText.getComponents();
            if (!sceneText.isParagraphRendering()) {
                Iterator<SceneText> it = components.iterator();
                while (it.hasNext()) {
                    SceneText next = it.next();
                    LTTLogger.h(TAG, "filterSceneTextLines : line value = " + next.getValue() + " hasOnlyNumber = " + next.hasOnlyNumber());
                    if (next.hasOnlyNumber()) {
                        components.remove(next);
                    }
                }
            }
            if (!components.isEmpty()) {
                z = true;
            }
            String str = TAG;
            StringBuilder o2 = C0086a.o(i2, "Block - ", " size =");
            o2.append(sceneText.getComponents().size());
            LTTLogger.d(str, o2.toString());
            copyOnWriteArrayList.set(i2, sceneText);
        }
        return z;
    }

    public synchronized int[] getMaskForInpainting(InpainterParam inpainterParam) {
        int[] _getMaskForInpainting;
        long currentTimeMillis = System.currentTimeMillis();
        byte[] convertBitmapToNV21 = Util.convertBitmapToNV21(inpainterParam.getInputImage());
        KeyFrameParam keyFrameParam = new KeyFrameParam();
        keyFrameParam.setResizeRatio(inpainterParam.getResizeRatio());
        keyFrameParam.setInputImage(inpainterParam.getInputImage());
        keyFrameParam.setContext(inpainterParam.getContext());
        CopyOnWriteArrayList<SceneText> sceneTextFromOCR = new ProcessOCR(keyFrameParam).getSceneTextFromOCR(inpainterParam.getLttOcrResult(), inpainterParam.getInputImage().getWidth(), inpainterParam.getInputImage().getHeight(), keyFrameParam.getDeviceRotation());
        if (filterSceneTextLines(sceneTextFromOCR)) {
            keyFrameParam.setSceneTexts(sceneTextFromOCR);
            _getMaskForInpainting = _getMaskForInpainting(convertBitmapToNV21, inpainterParam.getInputImage().getWidth(), inpainterParam.getInputImage().getHeight(), sceneTextFromOCR);
            String str = TAG;
            LTTLogger.p(str, "Inpainting Mask Generated Time : " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
            if (_getMaskForInpainting == null) {
                throw new LttEngineException(LttEngineErrors.ERROR_INPAINTING_GET_MASK_NATIVE_FAILED, "Native getMaskForInpainting api is failed");
            } else if (Dump.IS_INPAINTING_MASK_DUMP_ENABLE) {
                Bitmap intArrayToBitmap = Dump.intArrayToBitmap(_getMaskForInpainting, inpainterParam.getInputImage().getWidth(), inpainterParam.getInputImage().getHeight());
                Dump.dumpBitmap(intArrayToBitmap, "mser_mask" + System.currentTimeMillis() + ".png", "MserMask");
            }
        } else {
            throw new LttEngineException(LttEngineErrors.ERROR_INPAINTING_GET_MASK_NATIVE_FAILED, "No lines to inpaint");
        }
        return _getMaskForInpainting;
    }

    public void initialize() {
        setNativeVersion();
        String str = TAG;
        LTTLogger.i(str, "Native Version - " + NATIVE_LIBRARY_VERSION);
        if (!Util.isJarAndNativeVersionCompatible(LttEngine.JAR_VERSION, NATIVE_LIBRARY_VERSION)) {
            throw new LttEngineException(-4, "Jar and Native is not compatible");
        } else if (_initialize() != 1) {
            throw new LttEngineException(-5, "Initialized failed");
        }
    }

    public void prepareKeyFrameParam(KeyFrameParam keyFrameParam) {
        long currentTimeMillis = System.currentTimeMillis();
        keyFrameParam.setDpScaleFactor(Util.getDpToPxFactor(keyFrameParam.getContext()));
        int[] deviceResolution = Util.getDeviceResolution(keyFrameParam.getContext());
        int i2 = 0;
        keyFrameParam.setDeviceWidth(deviceResolution[0]);
        keyFrameParam.setDeviceHeight(deviceResolution[1]);
        CopyOnWriteArrayList<SceneText> sceneTextFromOCR = new ProcessOCR(keyFrameParam).getSceneTextFromOCR(keyFrameParam.getLttOcrResult(), keyFrameParam.getInputImage().getWidth(), keyFrameParam.getInputImage().getHeight(), keyFrameParam.getDeviceRotation());
        if (sceneTextFromOCR != null) {
            keyFrameParam.setSceneTexts(sceneTextFromOCR);
            String str = TAG;
            LTTLogger.p(str, "prepareKeyFrameParam: OCR processing time : " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
            long currentTimeMillis2 = System.currentTimeMillis();
            if (new TextDistribution(keyFrameParam, keyFrameParam.getTrlResult()).distribute()) {
                LTTLogger.p(str, "prepareKeyFrameParam: Text distribution time : " + (System.currentTimeMillis() - currentTimeMillis2) + "ms");
                long currentTimeMillis3 = System.currentTimeMillis();
                ProcessTRL processTRL = new ProcessTRL(keyFrameParam);
                if (keyFrameParam.getInPaintedImage() == null) {
                    processTRL.detectAndClearSameTranslatedResult();
                } else {
                    ArrayList arrayList = new ArrayList();
                    processTRL.getLinesToRestore(arrayList);
                    cropAndRestoreOriginalText(keyFrameParam, arrayList);
                }
                ProcessTRL.setTextScaleX(false);
                processTRL.generateTranslationMask();
                if (!keyFrameParam.getContext().getPackageName().contains("aiselect") && !keyFrameParam.getContext().getPackageName().contains("smartcapture")) {
                    i2 = 1;
                }
                keyFrameParam.setMserNeeded(i2);
                LTTLogger.i(str, "renderImage:All required data prepared");
                LTTLogger.p(str, "prepareKeyFrameParam: Total time taken : " + (System.currentTimeMillis() - currentTimeMillis3) + "ms");
                return;
            }
            throw new LttEngineException(-15, "Failed to distribute translation result");
        }
        throw new LttEngineException(-18, "Failed to get scene text from OCR");
    }

    public synchronized void release() {
        LTTLogger.i(TAG, "release()");
        _release();
    }

    public synchronized void releaseInternal() {
        LTTLogger.i(TAG, "releaseInternal()");
        _releaseInternal();
    }

    public synchronized Bitmap renderImage(KeyFrameParam keyFrameParam) {
        byte[] _renderImage;
        _renderImage = _renderImage(keyFrameParam);
        if (_renderImage != null) {
        } else {
            throw new LttEngineException(-17, "Native render api is failed");
        }
        return BitmapFactory.decodeByteArray(_renderImage, 0, _renderImage.length);
    }

    public void updateAndSetKeyFrameParam(KeyFrameParam keyFrameParam) {
        String str;
        StringBuilder sb2 = new StringBuilder("");
        Iterator<SceneText> it = keyFrameParam.getSceneTexts().iterator();
        while (it.hasNext()) {
            if (it.next().getBlockType() == LttOcrResult.BlockInfo.BLOCK_TYPE.DEFAULT.ordinal()) {
                str = "0";
            } else {
                str = "1";
            }
            sb2.append(str);
        }
        if (_setNativeParameter(new LttEngineProperty(2, sb2.toString())) == 1) {
            LTTLogger.d(TAG, "setNativeParameter : Success");
        } else {
            LTTLogger.e(TAG, "setNativeParameter : Failed");
        }
    }
}
