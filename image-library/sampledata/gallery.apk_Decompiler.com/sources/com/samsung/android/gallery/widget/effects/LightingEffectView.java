package com.samsung.android.gallery.widget.effects;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$styleable;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LightingEffectView extends FrameLayout {
    private static final boolean SUPPORT = PreferenceFeatures.OneUi7x.SUPPORT_LIGHTING_EFFECT;
    private String mConfig = null;
    private int mCornerRadius = 0;
    private GuidingLightEffect mEffect;
    private int mInset = 0;
    private Drawable mInsetDrawable;
    private int mOutlineThickness = -1;

    public LightingEffectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        updateAttrs(context, attributeSet);
    }

    private void updateAttrs(Context context, AttributeSet attributeSet) {
        if (SUPPORT) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.LightingEffect);
            this.mCornerRadius = obtainStyledAttributes.getDimensionPixelSize(R$styleable.LightingEffect_effectCornerRadius, 0);
            this.mInset = obtainStyledAttributes.getDimensionPixelSize(R$styleable.LightingEffect_inset, 0);
            this.mOutlineThickness = obtainStyledAttributes.getDimensionPixelSize(R$styleable.LightingEffect_outlineThickness, -1);
            this.mConfig = obtainStyledAttributes.getString(R$styleable.LightingEffect_config);
            this.mInsetDrawable = obtainStyledAttributes.getDrawable(R$styleable.LightingEffect_insetDrawable);
            obtainStyledAttributes.recycle();
            if (this.mInsetDrawable != null && this.mInset > 0) {
                setBackground(new InsetDrawable(this.mInsetDrawable, this.mInset));
            }
        }
    }

    public void applyEffect() {
        if (SUPPORT && Build.VERSION.SDK_INT >= 33) {
            if (this.mEffect == null) {
                GuidingLightConfig.Companion companion = GuidingLightConfig.Companion;
                GuidingLightConfig vi_config_default = companion.getVI_CONFIG_DEFAULT();
                if (TextUtils.equals(this.mConfig, "dark")) {
                    vi_config_default = companion.getVI_CONFIG_DEFAULT_DARK();
                } else if (TextUtils.equals(this.mConfig, "light")) {
                    vi_config_default = companion.getVI_CONFIG_DEFAULT_LIGHT();
                }
                GuidingLightEffect guidingLightEffect = new GuidingLightEffect(getContext(), this, vi_config_default, true);
                this.mEffect = guidingLightEffect;
                guidingLightEffect.setCornerRadiusPixel((float) this.mCornerRadius);
                this.mEffect.setInsetPixel(this.mInset);
                this.mEffect.setDefaultTouchInteraction(true);
                int i2 = this.mOutlineThickness;
                if (i2 > -1) {
                    this.mEffect.setOutlineThickness((float) i2);
                }
            }
            this.mEffect.show(GuidingLightEffect.ShowAnimationType.NONE);
        }
    }

    public void releaseEffect() {
        GuidingLightEffect guidingLightEffect;
        if (SUPPORT && Build.VERSION.SDK_INT >= 33 && (guidingLightEffect = this.mEffect) != null) {
            guidingLightEffect.hide(GuidingLightEffect.HideAnimationType.NONE);
            this.mEffect.remove(this);
            this.mEffect.release();
            this.mEffect = null;
        }
    }
}
