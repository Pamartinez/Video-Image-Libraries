package com.samsung.o3dp.lib3dphotography.animation.transition;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EaseTransition extends Transition {
    public float transitionTime(float f) {
        return ((float) (Math.cos((((double) f) + 1.0d) * 3.141592653589793d) + 1.0d)) * 0.5f;
    }
}
