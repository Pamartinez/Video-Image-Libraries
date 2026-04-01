package com.samsung.o3dp.lib3dphotography.animation.dynamics;

import com.samsung.o3dp.lib3dphotography.graphics.Vector3;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimpleHarmonicDynamicOffset extends DynamicOffset {
    public static final float SIMPLE_HARMONIC_INTENSITY = 0.03f;
    public static final float SIMPLE_HARMONIC_SPEED = 0.5f;

    public SimpleHarmonicDynamicOffset() {
        setIntensity(0.03f);
        this.mSpeed = 0.5f;
    }

    public Vector3 getDynamicOffset(float f) {
        float intensity = getIntensity();
        return new Vector3(((float) Math.sin((double) (31.0f * f * this.mSpeed))) * intensity * this.mAmplitude.f4210x, intensity * ((float) Math.cos((double) (f * 23.0f * this.mSpeed))) * this.mAmplitude.y, getEmbossingZ());
    }
}
