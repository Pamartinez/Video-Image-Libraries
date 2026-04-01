package com.samsung.android.gallery.widget.effects;

import A.a;
import android.view.View;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$bool;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect;
import x7.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryGuidingLightEffect {
    private GuidingLightEffect mEffect;
    private final boolean mIsDarkMode;
    private final View mTargetView;

    public GalleryGuidingLightEffect(View view, boolean z) {
        GuidingLightConfig guidingLightConfig;
        float f;
        float f5;
        long currentTimeMillis = System.currentTimeMillis();
        this.mTargetView = view;
        boolean z3 = view.getResources().getBoolean(R$bool.isNightTheme);
        this.mIsDarkMode = z3;
        if (z3) {
            guidingLightConfig = GuidingLightConfig.BUTTON_DARK;
        } else {
            guidingLightConfig = GuidingLightConfig.BUTTON_LIGHT;
        }
        guidingLightConfig.setLightIntensity(getIntensity(z));
        if (z3) {
            f = 20.0f;
        } else {
            f = 50.0f;
        }
        guidingLightConfig.setOutlineThickness(f);
        if (z3) {
            f5 = 0.5f;
        } else {
            f5 = 0.9f;
        }
        guidingLightConfig.setOuterSaturation(f5);
        guidingLightConfig.setShaderPrecision(GuidingLightConfig.ShaderPrecision.LEVEL_3);
        GuidingLightEffect guidingLightEffect = new GuidingLightEffect(view.getContext(), view, guidingLightConfig);
        this.mEffect = guidingLightEffect;
        guidingLightEffect.setOutlineThickness(50.0f);
        this.mEffect.setCornerRadiusPixel((float) view.getResources().getDimensionPixelOffset(R$dimen.bottom_search_toolbar_guiding_light_effect_radius));
        this.mEffect.setLightMovement(GuidingLightEffect.Movement.NONE);
        this.mEffect.show(GuidingLightEffect.ShowAnimationType.NONE);
        a.A(new Object[]{Integer.toHexString(this.mEffect.hashCode()), Long.valueOf(currentTimeMillis)}, new StringBuilder("create GuidingLightEffect"), "GalleryGuidingLightEffect");
    }

    private float getIntensity(boolean z) {
        if (z) {
            return 0.4f;
        }
        return 0.2f;
    }

    public void invalidateEffect() {
        GuidingLightEffect guidingLightEffect = this.mEffect;
        if (guidingLightEffect != null) {
            guidingLightEffect.show(GuidingLightEffect.ShowAnimationType.NONE);
        }
    }

    public void onConfigurationChanged() {
        View view = this.mTargetView;
        if (view != null) {
            view.post(new l(5, this));
        }
    }

    public void release() {
        Log.d("GalleryGuidingLightEffect", "release", Integer.toHexString(this.mEffect.hashCode()));
        this.mEffect.hide(GuidingLightEffect.HideAnimationType.NONE);
        this.mEffect.remove(this.mTargetView);
        this.mEffect.release();
        this.mEffect = null;
    }

    public void setActivate(boolean z) {
        this.mEffect.setLightIntensity(getIntensity(z));
    }
}
