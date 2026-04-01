package com.samsung.o3dp.lib3dphotography.animation.movement;

import com.samsung.o3dp.lib3dphotography.Quaternion;
import com.samsung.o3dp.lib3dphotography.animation.AnimationParams;
import com.samsung.o3dp.lib3dphotography.graphics.Vector3;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LinearMovement implements Movement {
    public float getDistanceToNextParam(int i2, AnimationParams[] animationParamsArr, Function<AnimationParams, Vector3> function) {
        return Vector3.measureDistance(function.apply(animationParamsArr[i2]), function.apply(animationParamsArr[i2 + 1]));
    }

    public Float getFloatInterpolate(int i2, AnimationParams[] animationParamsArr, float f, Function<AnimationParams, Float> function) {
        return Float.valueOf((function.apply(animationParamsArr[i2 + 1]).floatValue() * f) + ((1.0f - f) * function.apply(animationParamsArr[i2]).floatValue()));
    }

    public Vector3 getVector3Interpolate(int i2, AnimationParams[] animationParamsArr, float f, Function<AnimationParams, Vector3> function) {
        return Vector3.lerp(function.apply(animationParamsArr[i2]), function.apply(animationParamsArr[i2 + 1]), f);
    }

    public Vector3 slerpVector3(int i2, AnimationParams[] animationParamsArr, float f, Function<AnimationParams, Vector3> function) {
        return Quaternion.slerp(function.apply(animationParamsArr[i2]), function.apply(animationParamsArr[i2 + 1]), f);
    }
}
