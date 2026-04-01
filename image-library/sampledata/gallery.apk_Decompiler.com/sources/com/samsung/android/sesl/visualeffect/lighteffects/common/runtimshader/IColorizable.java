package com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.PointF;
import android.graphics.RuntimeShader;
import android.graphics.Shader;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bg\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/common/runtimshader/IColorizable;", "", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IColorizable {

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DefaultImpls {
        public static void setTintShaderUniforms(IColorizable iColorizable, RuntimeShader runtimeShader, Shader shader, PointF pointF) {
            float f;
            j.e(runtimeShader, "currentShader");
            if (shader == null) {
                runtimeShader.setFloatUniform("uTintShaderSize", 0.0f, 0.0f);
                return;
            }
            runtimeShader.setInputShader("tintShader", shader);
            float f5 = 0.0f;
            if (pointF != null) {
                f = pointF.x;
            } else {
                f = 0.0f;
            }
            if (pointF != null) {
                f5 = pointF.y;
            }
            runtimeShader.setFloatUniform("uTintShaderSize", f, f5);
        }

        public static void setTintTexture(IColorizable iColorizable, RuntimeShader runtimeShader, Bitmap bitmap) {
            j.e(runtimeShader, "currentShader");
            if (bitmap == null) {
                runtimeShader.setFloatUniform("uTintShaderSize", 0.0f, 0.0f);
                return;
            }
            runtimeShader.setInputShader("tintShader", new BitmapShader(bitmap, Shader.TileMode.DECAL, Shader.TileMode.DECAL));
            runtimeShader.setFloatUniform("uTintShaderSize", (float) bitmap.getWidth(), (float) bitmap.getHeight());
        }
    }
}
