package com.samsung.android.imagetranslation.inpainting;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Size;
import com.samsung.android.imagetranslation.data.LttOcrResult;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InpainterParam {
    private Context context;
    private Bitmap inputImage;
    private int[] inputMask;
    private LttOcrResult lttOcrResult;
    private Size originalImageSize;
    private int requestId;
    private float resizeRatio = 1.0f;
    private Size resizedImageSize;

    public InpainterParam(int i2, Bitmap bitmap, LttOcrResult lttOcrResult2) {
        this.requestId = i2;
        this.inputImage = bitmap;
        this.lttOcrResult = lttOcrResult2;
    }

    public Context getContext() {
        return this.context;
    }

    public Bitmap getInputImage() {
        return this.inputImage;
    }

    public int[] getInputMask() {
        return this.inputMask;
    }

    public LttOcrResult getLttOcrResult() {
        return this.lttOcrResult;
    }

    public Size getOriginalImageSize() {
        return this.originalImageSize;
    }

    public int getRequestId() {
        return this.requestId;
    }

    public float getResizeRatio() {
        return this.resizeRatio;
    }

    public Size getResizedImageSize() {
        return this.resizedImageSize;
    }

    public void setContext(Context context2) {
        this.context = context2;
    }

    public void setInputImage(Bitmap bitmap) {
        this.inputImage = bitmap;
    }

    public void setInputMask(int[] iArr) {
        this.inputMask = iArr;
    }

    public void setLttOcrResult(LttOcrResult lttOcrResult2) {
        this.lttOcrResult = lttOcrResult2;
    }

    public void setOriginalImageSize(Size size) {
        this.originalImageSize = size;
    }

    public void setRequestId(int i2) {
        this.requestId = i2;
    }

    public void setResizeRatio(float f) {
        this.resizeRatio = f;
    }

    public void setResizedImageSize(Size size) {
        this.resizedImageSize = size;
    }
}
