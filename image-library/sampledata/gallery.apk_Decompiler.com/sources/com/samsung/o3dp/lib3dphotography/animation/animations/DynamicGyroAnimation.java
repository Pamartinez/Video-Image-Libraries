package com.samsung.o3dp.lib3dphotography.animation.animations;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DynamicGyroAnimation extends GyroAnimation {
    private static final String TAG = "DynamicGyroAnimation";

    public DynamicGyroAnimation() {
        initAnimation();
    }

    public void consumeGyroParallax() {
        super.consumeGyroParallax();
        getTargetParams().dynamicOffset = getDynamicOffset().getDynamicOffset(getAnimationTime().getOverallTime());
    }

    public void initAnimation() {
        super.initAnimation();
        setName("Dynamic+Gyro");
        setSortOrder(1003);
        setDynamicOffset("damped_oscillation");
        setBokehEnabled(Boolean.FALSE);
        getDynamicOffset().setEmbossingZ(0.0f);
    }

    public DynamicGyroAnimation(String str) {
        super(str);
        initAnimation();
    }
}
