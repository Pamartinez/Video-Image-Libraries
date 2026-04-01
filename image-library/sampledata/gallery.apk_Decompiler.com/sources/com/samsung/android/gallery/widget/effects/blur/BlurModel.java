package com.samsung.android.gallery.widget.effects.blur;

import android.graphics.RenderEffect;
import com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class BlurModel {
    boolean mAreaViewVisible;
    final int mCornerRadius;
    int mHeight;
    final AGSLBlurEffect.RoundingMode mRoundingMode;
    int mStartX;
    int mStartY;
    int mWidth;

    /* renamed from: com.samsung.android.gallery.widget.effects.blur.BlurModel$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$widget$effects$blur$AGSLBlurEffect$RoundingMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect$RoundingMode[] r0 = com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect.RoundingMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$widget$effects$blur$AGSLBlurEffect$RoundingMode = r0
                com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect$RoundingMode r1 = com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect.RoundingMode.TOP     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$widget$effects$blur$AGSLBlurEffect$RoundingMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect$RoundingMode r1 = com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect.RoundingMode.BOTTOM     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.effects.blur.BlurModel.AnonymousClass1.<clinit>():void");
        }
    }

    public BlurModel(AGSLBlurEffect.Builder builder) {
        this.mCornerRadius = builder.mCornerRadius;
        this.mRoundingMode = builder.mRoundingMode;
    }

    public abstract RenderEffect generateRenderEffect();

    public float[] getCornerRadius() {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$widget$effects$blur$AGSLBlurEffect$RoundingMode[this.mRoundingMode.ordinal()];
        if (i2 == 1) {
            int i7 = this.mCornerRadius;
            return new float[]{(float) i7, (float) i7, 0.0f, 0.0f};
        } else if (i2 != 2) {
            int i8 = this.mCornerRadius;
            return new float[]{(float) i8, (float) i8, (float) i8, (float) i8};
        } else {
            int i10 = this.mCornerRadius;
            return new float[]{0.0f, 0.0f, (float) i10, (float) i10};
        }
    }

    public boolean isBlurAreaSizeChanged(int i2, int i7, int i8, int i10, boolean z) {
        if (this.mWidth == i2 && this.mHeight == i7 && this.mStartX == i8 && this.mStartY == i10 && this.mAreaViewVisible == z) {
            return false;
        }
        return true;
    }
}
