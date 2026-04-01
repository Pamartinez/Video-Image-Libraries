package com.samsung.android.gallery.app.ui.list.search.recommendation.floating;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FloatingRecommendationBlur {
    /* access modifiers changed from: private */
    public ImageView mBackgroundImage;
    private View mBlurTargetView;

    private int getDefaultBackgroundColor(Context context) {
        int i2;
        if (Features.isEnabled(Features.IS_THEME_INSTALLED)) {
            i2 = R.color.default_background;
        } else {
            i2 = R.color.default_fw_background;
        }
        return context.getColor(i2);
    }

    public void initView(View view, Bitmap bitmap) {
        boolean z;
        int i2;
        Context context = view.getContext();
        if (context != null) {
            if (!Features.isEnabled(Features.SUPPORT_REALTIME_BLUR) || Features.isEnabled(Features.IS_ENABLED_REDUCE_TRANSPARENCY) || Build.VERSION.SDK_INT < 31 || Features.isEnabled(Features.IS_THEME_INSTALLED)) {
                z = false;
            } else {
                z = true;
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.floating_recommendation_background_image);
            this.mBackgroundImage = imageView;
            if (z) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }
                if (PreferenceFeatures.OneUi8x.IS_ONE_UI_80) {
                    if (context.getResources().getBoolean(R.bool.isNightTheme)) {
                        if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
                            i2 = 120;
                        } else {
                            i2 = 136;
                        }
                    } else if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
                        i2 = 105;
                    } else {
                        i2 = 133;
                    }
                    View findViewById = view.findViewById(R.id.blur_effect_target_view);
                    this.mBlurTargetView = findViewById;
                    SeApiCompat.setCanvasBlurPreset(findViewById, i2);
                    return;
                }
                ImageView imageView2 = this.mBackgroundImage;
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                imageView2.setRenderEffect(RenderEffect.createBlurEffect(150.0f, 150.0f, Shader.TileMode.CLAMP));
                return;
            }
            imageView.setBackgroundColor(getDefaultBackgroundColor(context));
        }
    }

    public void onDestroy() {
        SeApiCompat.removeBlur(this.mBlurTargetView);
    }

    public void updateBackgroundImageMatrix() {
        ImageView imageView = this.mBackgroundImage;
        if (imageView != null) {
            imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    float f;
                    Drawable drawable = FloatingRecommendationBlur.this.mBackgroundImage.getDrawable();
                    if (drawable instanceof BitmapDrawable) {
                        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                        if (bitmap != null) {
                            int width = bitmap.getWidth();
                            int height = bitmap.getHeight();
                            int width2 = FloatingRecommendationBlur.this.mBackgroundImage.getWidth();
                            int height2 = FloatingRecommendationBlur.this.mBackgroundImage.getHeight();
                            if (width != 0 && height != 0 && width2 != 0 && height2 != 0) {
                                float f5 = (float) width;
                                float f8 = (float) height;
                                float f10 = f5 / f8;
                                float f11 = (float) width2;
                                float f12 = (float) height2;
                                float f13 = f11 / f12;
                                Matrix matrix = new Matrix();
                                if (Math.abs(f10 - f13) <= 0.01f) {
                                    f = 1.0f;
                                } else if (f10 < f13) {
                                    f = f11 / f5;
                                } else {
                                    f = f12 / f8;
                                }
                                matrix.setScale(f, f);
                                FloatingRecommendationBlur.this.mBackgroundImage.setImageMatrix(matrix);
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                    FloatingRecommendationBlur.this.mBackgroundImage.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }
}
