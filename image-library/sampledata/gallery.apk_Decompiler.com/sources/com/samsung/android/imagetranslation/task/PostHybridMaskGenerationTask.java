package com.samsung.android.imagetranslation.task;

import com.samsung.android.imagetranslation.TaskListener;
import com.samsung.android.imagetranslation.common.LTTLogger;
import com.samsung.android.imagetranslation.data.LttEngineException;
import com.samsung.android.imagetranslation.data.MaskResponse;
import com.samsung.android.imagetranslation.inpainting.InpainterParam;
import com.samsung.android.imagetranslation.jni.LttNativeHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PostHybridMaskGenerationTask implements Runnable {
    private static final String TAG = "com.samsung.android.imagetranslation.task.PostHybridMaskGenerationTask";
    private InpainterParam inpainterParam;
    private LttNativeHelper lttNativeHelper;
    private TaskListener taskListener;

    public PostHybridMaskGenerationTask(LttNativeHelper lttNativeHelper2, InpainterParam inpainterParam2, TaskListener taskListener2) {
        this.lttNativeHelper = lttNativeHelper2;
        this.inpainterParam = inpainterParam2;
        this.taskListener = taskListener2;
    }

    public void run() {
        String str = TAG;
        LTTLogger.d(str, "Get Mask Task execution started - request id " + this.inpainterParam.getRequestId());
        try {
            int[] maskForInpainting = this.lttNativeHelper.getMaskForInpainting(this.inpainterParam);
            if (maskForInpainting != null) {
                MaskResponse maskResponse = new MaskResponse();
                maskResponse.setRequestId(this.inpainterParam.getRequestId());
                maskResponse.setMaskData(maskForInpainting);
                maskResponse.setMaskSize(this.inpainterParam.getResizedImageSize());
                this.taskListener.onMaskAvailable(this.inpainterParam.getRequestId(), maskResponse);
            }
        } catch (LttEngineException e) {
            String str2 = TAG;
            LTTLogger.e(str2, "Executor task exception: error code " + e.getErrorCode() + ", exception - " + e.getMessage());
            this.taskListener.onFailure(this.inpainterParam.getRequestId(), e.getErrorCode());
        }
    }
}
