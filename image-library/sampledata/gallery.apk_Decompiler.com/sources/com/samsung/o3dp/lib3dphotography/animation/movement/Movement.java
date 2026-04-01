package com.samsung.o3dp.lib3dphotography.animation.movement;

import com.samsung.o3dp.lib3dphotography.animation.AnimationParams;
import com.samsung.o3dp.lib3dphotography.graphics.Vector3;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Movement {
    float getDistanceToNextParam(int i2, AnimationParams[] animationParamsArr, Function<AnimationParams, Vector3> function);

    Float getFloatInterpolate(int i2, AnimationParams[] animationParamsArr, float f, Function<AnimationParams, Float> function);

    Vector3 getVector3Interpolate(int i2, AnimationParams[] animationParamsArr, float f, Function<AnimationParams, Vector3> function);

    Vector3 slerpVector3(int i2, AnimationParams[] animationParamsArr, float f, Function<AnimationParams, Vector3> function);
}
