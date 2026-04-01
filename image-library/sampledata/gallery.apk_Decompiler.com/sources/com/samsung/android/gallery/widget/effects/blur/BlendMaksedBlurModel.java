package com.samsung.android.gallery.widget.effects.blur;

import android.graphics.BlendMode;
import android.graphics.RenderEffect;
import android.graphics.RuntimeShader;
import android.graphics.Shader;
import com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect;
import o.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class BlendMaksedBlurModel extends BlurModel {
    public BlendMaksedBlurModel(AGSLBlurEffect.Builder builder) {
        super(builder);
    }

    public RenderEffect generateRenderEffect() {
        RuntimeShader t = c.t();
        t.setFloatUniform("uCenter", (((float) this.mWidth) / 2.0f) + ((float) this.mStartX), (((float) this.mHeight) / 2.0f) + ((float) this.mStartY));
        t.setFloatUniform("uSize", (float) this.mWidth, (float) this.mHeight);
        t.setFloatUniform("uCornerR", getCornerRadius());
        t.setIntUniform("uRoundingMode", this.mRoundingMode.getValue());
        RenderEffect b = RenderEffect.createRuntimeShaderEffect(t, "uContent");
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        RenderEffect c5 = RenderEffect.createBlurEffect(50.0f, 50.0f, Shader.TileMode.CLAMP);
        BlendMode blendMode = BlendMode.DST_IN;
        RenderEffect d = RenderEffect.createBlendModeEffect(c5, b, BlendMode.DST_IN);
        RenderEffect b5 = RenderEffect.createOffsetEffect(0.0f, 0.0f);
        BlendMode blendMode2 = BlendMode.SRC_OVER;
        return RenderEffect.createBlendModeEffect(b5, d, BlendMode.SRC_OVER);
    }
}
