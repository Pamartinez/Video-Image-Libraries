package com.samsung.android.gallery.module.story.transcode.renderer.render;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RectCorners {
    public int leftBottomPixel;
    public float leftBottomRatio;
    public int leftTopPixel;
    public float leftTopRatio;
    public int rightBottomPixel;
    public float rightBottomRatio;
    public int rightTopPixel;
    public float rightTopRatio;
    private final Type type;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Type {
        PIXEL,
        RATIO
    }

    private RectCorners(Type type2) {
        this.type = type2;
    }

    public static RectCorners fromPixel(int i2, int i7, int i8, int i10) {
        RectCorners rectCorners = new RectCorners(Type.PIXEL);
        rectCorners.leftTopPixel = i2;
        rectCorners.rightTopPixel = i7;
        rectCorners.leftBottomPixel = i8;
        rectCorners.rightBottomPixel = i10;
        return rectCorners;
    }

    public boolean inPixel() {
        if (this.type == Type.PIXEL) {
            return true;
        }
        return false;
    }
}
