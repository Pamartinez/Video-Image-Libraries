package com.samsung.android.gallery.module.lottie.recap.template.element;

import com.samsung.android.gallery.module.lottie.recap.template.element.RecapDynamicElement;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RecapDynamicElement<T extends RecapDynamicElement<T>> {
    public String dataBindingKey;
    public int fileIndex = -1;
    public float fixedBright;
    public float fixedSat;

    public RecapDynamicElement(String str) {
        this.dataBindingKey = str;
    }

    public T dynamicColor(float f, float f5) {
        this.fixedSat = f;
        this.fixedBright = f5;
        return this;
    }

    public int getIndex() {
        return this.fileIndex;
    }

    public T targetImage(int i2) {
        this.fileIndex = i2;
        return this;
    }
}
