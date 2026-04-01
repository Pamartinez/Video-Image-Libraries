package com.samsung.android.imagetranslation.task;

import android.graphics.Bitmap;
import com.samsung.android.imagetranslation.TaskListener;
import com.samsung.android.imagetranslation.common.LTTLogger;
import com.samsung.android.imagetranslation.data.LttEngineException;
import com.samsung.android.imagetranslation.jni.KeyFrameParam;
import com.samsung.android.imagetranslation.jni.LttNativeHelper;
import com.samsung.android.imagetranslation.util.Util;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PostRenderTask implements Runnable {
    private static final String TAG = "com.samsung.android.imagetranslation.task.PostRenderTask";
    private KeyFrameParam keyFrameParam;
    private LttNativeHelper lttNativeHelper;
    private TaskListener taskListener;

    public PostRenderTask(KeyFrameParam keyFrameParam2, LttNativeHelper lttNativeHelper2, TaskListener taskListener2) {
        this.lttNativeHelper = lttNativeHelper2;
        this.keyFrameParam = keyFrameParam2;
        this.taskListener = taskListener2;
    }

    public void run() {
        String str = TAG;
        LTTLogger.d(str, "Task execution started - request id " + this.keyFrameParam.getRequestId());
        try {
            long currentTimeMillis = System.currentTimeMillis();
            this.lttNativeHelper.prepareKeyFrameParam(this.keyFrameParam);
            this.keyFrameParam.setNv21InputImage(Util.convertBitmapToNV21(this.keyFrameParam.getInputImage()));
            if (this.keyFrameParam.getInPaintedImage() != null) {
                this.keyFrameParam.setNv21InPaintedImage(Util.convertBitmapToNV21(this.keyFrameParam.getInPaintedImage()));
            }
            LTTLogger.p(str, "NV21 conversion time : " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
            long currentTimeMillis2 = System.currentTimeMillis();
            this.lttNativeHelper.updateAndSetKeyFrameParam(this.keyFrameParam);
            Bitmap renderImage = this.lttNativeHelper.renderImage(this.keyFrameParam);
            LTTLogger.p(str, "Renderer executed in : " + (System.currentTimeMillis() - currentTimeMillis2) + "ms");
            this.taskListener.onRenderSuccess(this.keyFrameParam.getRequestId(), renderImage);
        } catch (LttEngineException e) {
            String str2 = TAG;
            LTTLogger.e(str2, "Executor task exception: error code " + e.getErrorCode() + ", exception - " + e.getMessage());
            if (e.getErrorCode() == -17) {
                this.lttNativeHelper.releaseInternal();
            }
            this.taskListener.onFailure(this.keyFrameParam.getRequestId(), e.getErrorCode());
        }
    }
}
