package com.samsung.android.gallery.widget.effects.blur;

import android.graphics.RenderEffect;
import android.graphics.RuntimeShader;
import com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect;
import java.util.Locale;
import o.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SinglePathBlurModel extends BlurModel {
    private static final String shaderCode = "    const int uKernelSize = 15;\n    const int uKernelHalfSize = 7;\n    uniform float uKernel[uKernelSize * uKernelSize];\n    uniform shader uContent;\n    uniform float uBlurRadius;\n    uniform vec2 uCenter;\n    uniform vec2 uSize;\n    uniform vec4 uCornerR; // cornerR : tl, tr, br, bl order for x, y, z, w vec4\n    uniform int uRoundingMode;\n\n    float sdRoundedRect(vec2 p) {\n        vec2 halfSize = uSize * 0.5;\n        vec2 local = abs(p - uCenter) - halfSize + vec2(uCornerR.x);\n        return length(max(local, 0.0)) - uCornerR.x;\n    }\n\n    float sdRoundedRectPC(vec2 p) {\n        vec2 h = uSize * 0.5; //half\n        vec2 d = p - uCenter; //distance\n        vec2 ad = abs(d); //abs-distance\n        float r = (d.x > 0.0) ? ((d.y > 0.0) ? uCornerR.w : uCornerR.y) : // bot-right, top-right\n             ((d.y > 0.0) ? uCornerR.z : uCornerR.x); // bot-left, top-left order\n        vec2 local = ad - (h - vec2(r)); //same with sdRoundedRect\n        return length(max(local, 0.0)) + min(max(local.x, local.y), 0.0) - r;\n    }\n\n    half4 main(float2 coord) {\n        if (uRoundingMode <= 0) { // NONE or ALL\n            if (sdRoundedRect(coord) > 0.0) {\n                return uContent.eval(coord);\n            }\n        } else if (uRoundingMode == 1) {\n            if (sdRoundedRectPC(coord) > 0.0) {\n                return uContent.eval(coord);\n            }\n        } else if (uRoundingMode == 2) {\n            if (sdRoundedRectPC(coord) > 0.0) {\n                return uContent.eval(coord);\n            }\n        }\n        half4 color = half4(0);\n        float totalWeight = 0.0;\n        for (int dx = -uKernelHalfSize; dx <= uKernelHalfSize; dx++) {\n            for (int dy = -uKernelHalfSize; dy <= uKernelHalfSize; dy++) {\n                float2 offset = float2(dx, dy) * uBlurRadius;\n                float weight = uKernel[(dx + uKernelHalfSize) * uKernelSize + (dy + uKernelHalfSize)];\n                color += uContent.eval(coord + offset) * weight;\n                totalWeight += weight;\n            }\n        }\n        return color / totalWeight;\n    }\n";
    private final float[] mKernel = computeLookUpTable();

    static {
        Locale locale = Locale.US;
    }

    public SinglePathBlurModel(AGSLBlurEffect.Builder builder) {
        super(builder);
    }

    private float[] computeLookUpTable() {
        float[] fArr = new float[225];
        for (int i2 = -7; i2 <= 7; i2++) {
            for (int i7 = -7; i7 <= 7; i7++) {
                double length = length(i2, i7);
                fArr[i7 + 7 + ((i2 + 7) * 15)] = (float) exp((-(length * length)) / 32.0d);
            }
        }
        return fArr;
    }

    private static double exp(double d) {
        return Math.exp(d);
    }

    private static double length(int i2, int i7) {
        return Math.sqrt((double) ((i7 * i7) + (i2 * i2)));
    }

    public RenderEffect generateRenderEffect() {
        c.f();
        RuntimeShader d = c.d(shaderCode);
        d.setFloatUniform("uKernel", this.mKernel);
        d.setFloatUniform("uBlurRadius", 4.0f);
        d.setFloatUniform("uCenter", (((float) this.mWidth) / 2.0f) + ((float) this.mStartX), (((float) this.mHeight) / 2.0f) + ((float) this.mStartY));
        d.setFloatUniform("uSize", (float) this.mWidth, (float) this.mHeight);
        d.setFloatUniform("uCornerR", getCornerRadius());
        d.setIntUniform("uRoundingMode", this.mRoundingMode.getValue());
        return RenderEffect.createRuntimeShaderEffect(d, "uContent");
    }
}
