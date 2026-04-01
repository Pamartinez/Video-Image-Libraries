package com.samsung.android.gallery.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import com.samsung.android.gallery.support.blur.BlurEffectHolder;
import com.samsung.android.gallery.support.blur.BlurImageInfo;
import com.samsung.android.gallery.support.blur.BlurParams;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.sdk.cover.ScoverState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PinchBlurImageView extends PinchImageView {
    private static final boolean ENABLE_GRADIENT_BLUR;
    private boolean mEnableBlur = false;
    private int mEndColor;
    private final Paint mGradientPaint = new Paint();
    private BlurImageInfo mInfo = null;
    private boolean mIsNightTheme = false;
    private float mPercent;
    private float mProgress;
    private int mStartColor;

    static {
        boolean z;
        if (!SdkConfig.atLeast(SdkConfig.SEM.B_MR5) || Features.isEnabled(Features.IS_ENABLED_REDUCE_TRANSPARENCY) || !Features.isEnabled(Features.SUPPORT_REALTIME_BLUR)) {
            z = false;
        } else {
            z = true;
        }
        ENABLE_GRADIENT_BLUR = z;
    }

    public PinchBlurImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initColor(context);
    }

    private void initColor(Context context) {
        this.mStartColor = context.getColor(R$color.album_gradient_start_color);
        this.mEndColor = context.getColor(R$color.album_gradient_end_color);
        this.mIsNightTheme = context.getResources().getBoolean(R$bool.isNightTheme);
    }

    private void removeRenderEffectIfNecessary() {
        if (Build.VERSION.SDK_INT >= 31 && ENABLE_GRADIENT_BLUR && !this.mEnableBlur) {
            setRenderEffect((RenderEffect) null);
        }
    }

    private void setBlur(int i2, int i7) {
        if (Build.VERSION.SDK_INT >= 31 && i2 > 0 && i7 > 0 && this.mEnableBlur) {
            float round = ((float) Math.round(((this.mInfo.getTargetViewHeight() * 2.0f) / ((float) i7)) * 100.0f)) / 100.0f;
            float f = 0.2f;
            float f5 = 180.0f;
            float f8 = -15.0f;
            if (this.mInfo.isCustomCover()) {
                BlurParams.Builder builder = new BlurParams.Builder(i2, i7);
                if (this.mInfo.isEmptyAlbum()) {
                    f8 = 15.0f;
                }
                BlurParams.Builder curveLevel = builder.setCurveLevel(f8);
                if (!this.mInfo.isEmptyAlbum()) {
                    f5 = 219.3f;
                }
                BlurParams.Builder radius = curveLevel.setCurveMaxY(f5).setPercent(0.5f).setPivotPercent(0.25f).setRadius(150.0f);
                if (this.mInfo.isEmptyAlbum()) {
                    f = 0.4f;
                }
                setRenderEffect(BlurEffectHolder.getRenderEffectForAlbumCover(radius.setSaturation(f).build()));
            } else if (this.mInfo.isSkipBlur()) {
                setRenderEffect((RenderEffect) null);
            } else if (this.mInfo.isGridType()) {
                BlurParams.Builder builder2 = new BlurParams.Builder(i2, i7);
                if (this.mInfo.isUseEmptyCoverBlur()) {
                    f8 = 15.0f;
                }
                BlurParams.Builder curveLevel2 = builder2.setCurveLevel(f8);
                if (!this.mInfo.isUseEmptyCoverBlur()) {
                    f5 = 206.0f;
                }
                BlurParams.Builder percent = curveLevel2.setCurveMaxY(f5).setPercent(round);
                this.mInfo.isMaxDepth();
                BlurParams.Builder radius2 = percent.setPivotPercent(0.25f).setRadius(BlurEffectHolder.getDefaultAlbumRadius(i2));
                if (this.mInfo.isUseEmptyCoverBlur()) {
                    f = 0.4f;
                }
                setRenderEffect(BlurEffectHolder.getRenderEffectForAlbumCover(radius2.setSaturation(f).build()));
            } else {
                setRenderEffect(BlurEffectHolder.getRenderEffectForAlbumCover(new BlurParams.Builder(i2, i7).setPercent(round).setCurveLevel(0.0f).setRadius(0.0f).setCurveMaxY(250.0f).build()));
            }
        }
    }

    private void setGradient(Canvas canvas) {
        float f;
        Canvas canvas2;
        int i2;
        int save = canvas.save();
        float width = (float) getWidth();
        float height = (float) getHeight();
        if (this.mInfo.isCustomCover()) {
            f = 0.5f;
        } else {
            if (!this.mInfo.isSkipBlur()) {
                if (this.mInfo.isGridType()) {
                    if (this.mEnableBlur) {
                        f = ((float) Math.round(((this.mInfo.getTargetViewHeight() * 2.0f) / height) * 100.0f)) / 100.0f;
                    } else {
                        f = this.mPercent;
                    }
                } else if (!this.mEnableBlur) {
                    f = this.mPercent;
                }
            }
            f = 0.0f;
        }
        if (f > 0.0f) {
            float max = height - Math.max(f * height, 1.0f);
            float f5 = height;
            this.mGradientPaint.setShader(new LinearGradient(0.0f, max, 0.0f, f5, this.mStartColor, this.mEndColor, Shader.TileMode.CLAMP));
            Paint paint = this.mGradientPaint;
            if (this.mEnableBlur) {
                i2 = ScoverState.TYPE_NFC_SMART_COVER;
            } else {
                i2 = (int) (this.mProgress * 255.0f);
            }
            paint.setAlpha(i2);
            canvas2 = canvas;
            canvas2.drawRect(0.0f, max, width, f5, this.mGradientPaint);
        } else {
            canvas2 = canvas;
        }
        canvas2.restoreToCount(save);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        BlurImageInfo blurImageInfo = this.mInfo;
        if (blurImageInfo == null) {
            return;
        }
        if (blurImageInfo.isCustomCover() && this.mInfo.isEmptyAlbum() && this.mIsNightTheme) {
            return;
        }
        if (ENABLE_GRADIENT_BLUR) {
            setBlur(getWidth(), getHeight());
        } else {
            setGradient(canvas);
        }
    }

    public void enableBlur(boolean z) {
        this.mEnableBlur = z;
        removeRenderEffectIfNecessary();
    }

    public float getAspectRatio() {
        return this.mAspectRatio;
    }

    public void setAspectRatio(float f) {
        this.mAspectRatio = f;
    }

    public void setBlurInfo(BlurImageInfo blurImageInfo) {
        this.mInfo = blurImageInfo;
    }

    public void setPercent(float f) {
        this.mPercent = f;
    }

    public void setProgress(float f) {
        this.mProgress = f;
    }
}
