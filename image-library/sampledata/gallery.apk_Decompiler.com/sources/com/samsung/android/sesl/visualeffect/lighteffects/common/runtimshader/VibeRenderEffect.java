package com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader;

import android.graphics.RenderEffect;
import android.graphics.RuntimeShader;
import android.view.View;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0007\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0004H&¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0004H&¢\u0006\u0004\b\n\u0010\tJ\u000f\u0010\u000b\u001a\u00020\u0004H&¢\u0006\u0004\b\u000b\u0010\tR\u0016\u0010\u000f\u001a\u0004\u0018\u00010\f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u00108&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/common/runtimshader/VibeRenderEffect;", "", "Landroid/view/View;", "view", "Lme/x;", "add", "(Landroid/view/View;)V", "remove", "start", "()V", "stop", "destroy", "Landroid/graphics/RuntimeShader;", "getShader", "()Landroid/graphics/RuntimeShader;", "shader", "Landroid/graphics/RenderEffect;", "getRenderEffect", "()Landroid/graphics/RenderEffect;", "renderEffect", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface VibeRenderEffect {
    void add(View view);

    void destroy();

    RenderEffect getRenderEffect();

    RuntimeShader getShader();

    void remove(View view);

    void start();

    void stop();
}
