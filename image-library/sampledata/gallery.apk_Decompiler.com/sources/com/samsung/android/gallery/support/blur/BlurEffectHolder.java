package com.samsung.android.gallery.support.blur;

import K.e;
import android.graphics.RenderEffect;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BlurEffectHolder {
    private static final ConcurrentHashMap<Key, RenderEffect> sAlbumAnimationEffectMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Key, RenderEffect> sAlbumCoverEffectMap = new ConcurrentHashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Key {
        private final float mCurveLevel;
        private final int mHeight;
        private final float mPercent;
        private final int mWidth;

        public Key(BlurParams blurParams) {
            this.mWidth = blurParams.getWidth();
            this.mHeight = blurParams.getHeight();
            this.mCurveLevel = blurParams.getCurveLevel();
            this.mPercent = blurParams.getGradientBlurPercent();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Key) {
                Key key = (Key) obj;
                if (this.mWidth == key.mWidth && this.mHeight == key.mHeight && this.mCurveLevel == key.mCurveLevel && this.mPercent == key.mPercent) {
                    return true;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            return (this.mWidth + "/" + this.mHeight + "/" + this.mPercent + "/" + this.mCurveLevel).hashCode();
        }
    }

    public static float getDefaultAlbumRadius(int i2) {
        return (float) Math.round(((float) i2) * 0.227f);
    }

    /* access modifiers changed from: private */
    public static RenderEffect getRenderEffect(BlurParams blurParams) {
        return GradientBlurCompat.getRenderEffect(blurParams);
    }

    public static RenderEffect getRenderEffectForAlbumAnimation(BlurParams blurParams) {
        return e.e(sAlbumAnimationEffectMap.computeIfAbsent(new Key(blurParams), new a(blurParams, 2)));
    }

    public static RenderEffect getRenderEffectForAlbumCover(BlurParams blurParams) {
        return e.e(sAlbumCoverEffectMap.computeIfAbsent(new Key(blurParams), new a(blurParams, 0)));
    }

    public static RenderEffect getRenderEffectForStories(BlurParams blurParams) {
        return e.e(sAlbumCoverEffectMap.computeIfAbsent(new Key(blurParams), new a(blurParams, 1)));
    }
}
