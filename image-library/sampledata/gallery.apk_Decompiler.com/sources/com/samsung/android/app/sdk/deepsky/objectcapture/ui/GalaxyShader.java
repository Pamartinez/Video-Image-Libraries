package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import android.content.Context;
import android.graphics.RenderEffect;
import android.graphics.RuntimeShader;
import com.samsung.android.app.sdk.deepsky.objectcapture.R;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u0010\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\t¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\t¢\u0006\u0004\b\u0014\u0010\u000eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/GalaxyShader;", "Landroid/graphics/RuntimeShader;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/graphics/RenderEffect;", "createShaderEffect", "()Landroid/graphics/RenderEffect;", "", "width", "height", "Lme/x;", "updateResolution", "(FF)V", "time", "updateTime", "(F)V", "x", "y", "updateMousePoint", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GalaxyShader extends RuntimeShader {
    private final Context context;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GalaxyShader(Context context2) {
        super(AnimationUtils.INSTANCE.readRawString(context2, R.raw.agsl_shader_galaxy));
        j.e(context2, "context");
        this.context = context2;
    }

    public final RenderEffect createShaderEffect() {
        RenderEffect c5 = RenderEffect.createRuntimeShaderEffect(this, "image");
        j.d(c5, "createRuntimeShaderEffect(...)");
        return c5;
    }

    public final Context getContext() {
        return this.context;
    }

    public final void updateMousePoint(float f, float f5) {
        setFloatUniform("iMouse", f, f5);
    }

    public final void updateResolution(float f, float f5) {
        setFloatUniform("iResolution", f, f5);
    }

    public final void updateTime(float f) {
        setFloatUniform("iTime", f);
    }
}
