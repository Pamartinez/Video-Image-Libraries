package com.samsung.android.imagetranslation.data;

import android.graphics.Bitmap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MaskRequest {
    private Bitmap inputBitmap;
    private LttOcrResult lttOcrResult;

    public MaskRequest(Bitmap bitmap, LttOcrResult lttOcrResult2) {
        this.inputBitmap = bitmap;
        this.lttOcrResult = lttOcrResult2;
    }

    public Bitmap getInputBitmap() {
        return this.inputBitmap;
    }

    public LttOcrResult getLttOcrResult() {
        return this.lttOcrResult;
    }

    public void setInputBitmap(Bitmap bitmap) {
        this.inputBitmap = bitmap;
    }

    public void setLttOcrResult(LttOcrResult lttOcrResult2) {
        this.lttOcrResult = lttOcrResult2;
    }
}
