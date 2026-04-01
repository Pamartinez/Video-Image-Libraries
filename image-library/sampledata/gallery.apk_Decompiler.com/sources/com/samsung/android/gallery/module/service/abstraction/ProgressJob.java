package com.samsung.android.gallery.module.service.abstraction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ProgressJob {
    private final Object[] mParams;

    public ProgressJob(Object... objArr) {
        this.mParams = objArr;
    }

    public Object getParam(int i2) {
        return this.mParams[i2];
    }

    public int getParamSize() {
        return this.mParams.length;
    }
}
