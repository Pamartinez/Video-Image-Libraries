package com.samsung.android.imagetranslation.data;

import android.util.Size;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MaskResponse {
    private int[] maskData;
    private Size maskSize;
    private int requestId;

    public int[] getMaskData() {
        return this.maskData;
    }

    public Size getMaskSize() {
        return this.maskSize;
    }

    public int getRequestId() {
        return this.requestId;
    }

    public void setMaskData(int[] iArr) {
        this.maskData = iArr;
    }

    public void setMaskSize(Size size) {
        this.maskSize = size;
    }

    public void setRequestId(int i2) {
        this.requestId = i2;
    }
}
