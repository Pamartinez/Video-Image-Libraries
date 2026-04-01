package com.samsung.android.imagetranslation.inpainting;

import A9.a;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import com.samsung.android.imagetranslation.TaskListener;
import com.samsung.android.imagetranslation.common.Dump;
import com.samsung.android.imagetranslation.common.LTTLogger;
import com.samsung.android.imagetranslation.common.LttEngineErrors;
import com.samsung.android.imagetranslation.data.LttEngineException;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjRemover;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InpainterHelper {
    public static final int INITIALIZATION_SUCCESS = 1;
    public static final int PROCESSING_SUCCESS = 2;
    private static final String TAG = "InpainterHelper";
    private static InpainterHelper m_inpainterHelper;
    private CountDownLatch latch;
    private int m_requestId = -1;
    private TaskListener m_taskListener = null;
    VexFwkObjRemover vexFwkObjRemover;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum MaskLevel {
        BLOCK,
        LINE,
        WORD,
        CHAR;

        public int getMaskValue() {
            return ordinal();
        }
    }

    private InpainterHelper() {
    }

    public static synchronized InpainterHelper createInstance() {
        InpainterHelper inpainterHelper;
        synchronized (InpainterHelper.class) {
            try {
                if (m_inpainterHelper == null) {
                    m_inpainterHelper = new InpainterHelper();
                }
                inpainterHelper = m_inpainterHelper;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return inpainterHelper;
    }

    private Rect getBoundingRect(ArrayList<Point> arrayList, int i2, int i7) {
        Iterator<Point> it = arrayList.iterator();
        int i8 = Integer.MAX_VALUE;
        int i10 = Integer.MIN_VALUE;
        int i11 = Integer.MIN_VALUE;
        int i12 = Integer.MAX_VALUE;
        while (it.hasNext()) {
            Point next = it.next();
            if (next.x < 0) {
                next.x = 0;
            }
            if (next.x > i2) {
                next.x = i2;
            }
            if (next.y < 0) {
                next.y = 0;
            }
            if (next.y > i7) {
                next.y = i7;
            }
            int i13 = next.x;
            if (i13 < i8) {
                i8 = i13;
            }
            if (i13 > i10) {
                i10 = i13;
            }
            int i14 = next.y;
            if (i14 < i12) {
                i12 = i14;
            }
            if (i14 > i11) {
                i11 = i14;
            }
        }
        return new Rect(i8, i12, i10, i11);
    }

    private MaskLevel getInpaintingMaskLevel() {
        if (!Dump.IS_INIT_DUMP_SUCCESS) {
            return MaskLevel.LINE;
        }
        String str = Dump.INPAINTING_MASK_LEVEL;
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case 66:
                if (str.equals("B")) {
                    c5 = 0;
                    break;
                }
                break;
            case 67:
                if (str.equals("C")) {
                    c5 = 1;
                    break;
                }
                break;
            case 87:
                if (str.equals("W")) {
                    c5 = 2;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return MaskLevel.BLOCK;
            case 1:
                return MaskLevel.CHAR;
            case 2:
                return MaskLevel.WORD;
            default:
                return MaskLevel.LINE;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getInpaintedFrame$0(InpainterParam inpainterParam, Bitmap bitmap, Throwable th) {
        String str = TAG;
        LTTLogger.i(str, "getInpaintedFrame: Inpainted image received for req id = " + inpainterParam.getRequestId());
        if (th != null) {
            LTTLogger.e(str, "getInpaintedBitmap: req id = " + inpainterParam.getRequestId() + " Exception = " + th.getMessage());
            this.latch.countDown();
            this.m_taskListener.onFailure(this.m_requestId, LttEngineErrors.ERROR_INPAINTING_PROCESSING_FAILED);
        }
        if (bitmap != null) {
            InpainterResult inpainterResult = new InpainterResult();
            inpainterResult.setInpaintedBitmap(bitmap);
            LTTLogger.d(str, "Inpainted Bitmap Height and Width : " + bitmap.getHeight() + " " + bitmap.getWidth());
            if (Dump.IS_INPAINTED_FRAME_ENABLED) {
                Dump.dumpBitmap(bitmap, "inpainted_image" + System.currentTimeMillis() + ".png", "InpaintedImage");
            }
            this.latch.countDown();
            this.m_taskListener.onInpaintingSuccess(this.m_requestId, inpainterResult);
            return;
        }
        LTTLogger.i(str, "getInpaintedBitmap: Failed to generate inpainted image for req id = " + inpainterParam.getRequestId());
        this.latch.countDown();
        this.m_taskListener.onFailure(this.m_requestId, LttEngineErrors.ERROR_INPAINTING_PROCESSING_FAILED);
    }

    private static void releaseInpainterHelper() {
        m_inpainterHelper = null;
    }

    public int getInpaintedFrame(InpainterParam inpainterParam) {
        LTTLogger.d(TAG, "getInpaintedFrame : E");
        this.m_requestId = inpainterParam.getRequestId();
        Bitmap inputImage = inpainterParam.getInputImage();
        ArrayList arrayList = new ArrayList();
        for (LttOcrResult.BlockInfo poly : inpainterParam.getLttOcrResult().getBlockInfoList()) {
            Collections.addAll(arrayList, poly.getPoly());
        }
        try {
            int width = inputImage.getWidth();
            int height = inputImage.getHeight();
            int i2 = width * height;
            inputImage.getPixels(new int[i2], 0, width, 0, 0, width, height);
            if (i2 == inpainterParam.getInputMask().length) {
                long currentTimeMillis = System.currentTimeMillis();
                try {
                    this.latch = new CountDownLatch(1);
                    if (this.vexFwkObjRemover != null) {
                        Rect rect = new Rect(0, 0, width, height);
                        String str = TAG;
                        LTTLogger.i(str, "getInpaintedFrame: Inpainting requested for req id = " + inpainterParam.getRequestId());
                        this.vexFwkObjRemover.removeObject(inputImage, inpainterParam.getInputMask(), rect).whenComplete(new a(14, this, inpainterParam));
                        this.latch.await();
                        LTTLogger.p(str, "Inpainting Processing Time : " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
                        LTTLogger.d(str, "getInpaintedFrame : X");
                        return 2;
                    }
                    throw new LttEngineException(LttEngineErrors.ERROR_INPAINTING_VEX_MANAGER_NULL, "VEX - Manager not initialized properly");
                } catch (Exception e) {
                    Exception exc = e;
                    String str2 = TAG;
                    LTTLogger.e(str2, "exception: " + exc.getMessage());
                    throw new LttEngineException(LttEngineErrors.ERROR_INPAINTING_PROCESSING_FAILED, "Inpainting processing failed");
                }
            } else {
                LTTLogger.e(TAG, "image and mask dimension is different");
                throw new LttEngineException(LttEngineErrors.ERROR_INPAINTING_IMG_DIM_MISMATCH, "image and mask dimension is different");
            }
        } catch (Exception e7) {
            LTTLogger.e(TAG, "input image decode failed");
            throw new LttEngineException(LttEngineErrors.ERROR_INPAINTING_IMG_DECODING_FAILED, e7.getMessage());
        }
    }

    public int initializeInpainter(InpainterInitParam inpainterInitParam) {
        this.m_taskListener = inpainterInitParam.get_taskListener();
        try {
            if (VexFwkObjRemover.isAvailable()) {
                VexFwkObjRemover vexFwkObjRemover2 = new VexFwkObjRemover();
                this.vexFwkObjRemover = vexFwkObjRemover2;
                vexFwkObjRemover2.configure();
                LTTLogger.d(TAG, "VEX - configured");
                return 1;
            }
            throw new LttEngineException(LttEngineErrors.ERROR_INPAINTING_NOT_SUPPORTED, "VexFwk not supported on device !!");
        } catch (Exception e) {
            String str = TAG;
            LTTLogger.e(str, "exception: " + e.getMessage());
            throw new LttEngineException(LttEngineErrors.ERROR_INPAINTING_INITIALIZATION_FAILED, "VEX - initialization failed");
        }
    }

    public void releaseInpainter() {
        VexFwkObjRemover vexFwkObjRemover2 = this.vexFwkObjRemover;
        if (vexFwkObjRemover2 != null) {
            vexFwkObjRemover2.close();
            this.vexFwkObjRemover = null;
        }
        CountDownLatch countDownLatch = this.latch;
        if (countDownLatch != null) {
            countDownLatch.countDown();
            this.latch = null;
        }
        releaseInpainterHelper();
        LTTLogger.i(TAG, "releaseInpainter: Inpainter service released");
    }
}
