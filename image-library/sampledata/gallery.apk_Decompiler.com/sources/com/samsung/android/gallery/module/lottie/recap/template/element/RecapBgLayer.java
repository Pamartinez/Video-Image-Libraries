package com.samsung.android.gallery.module.lottie.recap.template.element;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapBgLayer extends RecapDynamicElement<RecapBgLayer> {
    public ColorPickType colorPickType = ColorPickType.QUARTER_OPENING_SATURATION_ORDER;
    public float fixedBrightForGray = -1.0f;
    public float graySaturationMax = -1.0f;
    public String targetColor;
    public String targetFile;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ColorPickType {
        QUARTER_OPENING_SATURATION_ORDER,
        SINGLE_IMAGE,
        COLOR_BY_FILE_INDEX,
        COLOR_BY_FILE_NAME
    }

    public RecapBgLayer(String str) {
        super(str);
    }

    public RecapBgLayer brightFroGray(float f, float f5) {
        this.fixedBrightForGray = f;
        this.graySaturationMax = f5;
        return this;
    }

    public RecapBgLayer dynamicColorFromSingleImage(float f, float f5) {
        this.colorPickType = ColorPickType.SINGLE_IMAGE;
        dynamicColor(f, f5);
        return this;
    }

    public boolean isFileIndexedColorPick() {
        return ColorPickType.COLOR_BY_FILE_INDEX.equals(this.colorPickType);
    }

    public boolean isSingleImageColorPick() {
        return ColorPickType.SINGLE_IMAGE.equals(this.colorPickType);
    }

    public RecapBgLayer targetImage(int i2) {
        super.targetImage(i2);
        this.colorPickType = ColorPickType.COLOR_BY_FILE_INDEX;
        return this;
    }

    public RecapBgLayer targetImage(String str) {
        this.targetFile = str;
        this.colorPickType = ColorPickType.COLOR_BY_FILE_NAME;
        return this;
    }
}
