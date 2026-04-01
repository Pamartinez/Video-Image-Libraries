package com.samsung.android.gallery.widget.effects.blur;

import android.graphics.RenderEffect;
import android.graphics.RuntimeShader;
import com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect;
import o.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DualPathBlurModel extends BlurModel {
    public DualPathBlurModel(AGSLBlurEffect.Builder builder) {
        super(builder);
    }

    public RenderEffect generateRenderEffect() {
        RuntimeShader z = c.z();
        z.setFloatUniform("uDirection", 1.0f, 0.0f);
        z.setFloatUniform("uBlurRadius", 4.0f);
        z.setFloatUniform("uCenter", (((float) this.mWidth) / 2.0f) + ((float) this.mStartX), (((float) this.mHeight) / 2.0f) + ((float) this.mStartY));
        z.setFloatUniform("uSize", (float) this.mWidth, (float) this.mHeight);
        z.setFloatUniform("uCornerR", getCornerRadius());
        z.setIntUniform("uRoundingMode", this.mRoundingMode.getValue());
        RuntimeShader z3 = c.z();
        z3.setFloatUniform("uDirection", 0.0f, 1.0f);
        z3.setFloatUniform("uBlurRadius", 4.0f);
        z3.setFloatUniform("uCenter", (((float) this.mWidth) / 2.0f) + ((float) this.mStartX), (((float) this.mHeight) / 2.0f) + ((float) this.mStartY));
        z3.setFloatUniform("uSize", (float) this.mWidth, (float) this.mHeight);
        z3.setFloatUniform("uCornerR", getCornerRadius());
        z3.setIntUniform("uRoundingMode", this.mRoundingMode.getValue());
        RenderEffect d = RenderEffect.createChainEffect(RenderEffect.createRuntimeShaderEffect(z3, "uContent"), RenderEffect.createRuntimeShaderEffect(z, "uContent"));
        return RenderEffect.createChainEffect(d, d);
    }
}
