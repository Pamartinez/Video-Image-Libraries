package com.samsung.o3dp.lib3dphotography.animation.movement;

import com.samsung.o3dp.lib3dphotography.animation.AnimationParams;
import com.samsung.o3dp.lib3dphotography.graphics.Vector3;
import com.samsung.o3dp.lib3dphotography.nativelib.JNI;
import java.util.Objects;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SplineMovement implements Movement {
    private static final int DEFAULT_DISTANCE_SEGMENT_SAMPLE = 200;
    private static final float DEFAULT_SPLINE_TENSION = 0.1f;

    public float getDistanceToNextParam(int i2, AnimationParams[] animationParamsArr, Function<AnimationParams, Vector3> function) {
        float f = 0.0f;
        Vector3 vector3Interpolate = getVector3Interpolate(i2, animationParamsArr, 0.0f, function);
        int i7 = 1;
        while (i7 <= 200) {
            Vector3 vector3Interpolate2 = getVector3Interpolate(i2, animationParamsArr, ((float) i7) * 0.005f, function);
            f += Vector3.measureDistance(vector3Interpolate, vector3Interpolate2);
            i7++;
            vector3Interpolate = vector3Interpolate2;
        }
        return f;
    }

    public Float getFloatInterpolate(int i2, AnimationParams[] animationParamsArr, float f, Function<AnimationParams, Float> function) {
        AnimationParams[] animationParamsArr2 = animationParamsArr;
        Function<AnimationParams, Float> function2 = function;
        Float apply = function2.apply(animationParamsArr2[Math.max(i2 - 1, 0)]);
        Float apply2 = function2.apply(animationParamsArr2[i2]);
        Float apply3 = function2.apply(animationParamsArr2[i2 + 1]);
        Float apply4 = function2.apply(animationParamsArr2[Math.min(i2 + 2, animationParamsArr2.length - 1)]);
        if (Objects.equals(apply2, apply3)) {
            return apply3;
        }
        return Float.valueOf(JNI.cardinalSpline(apply.floatValue(), 0.0f, 0.0f, apply2.floatValue(), 0.0f, 0.0f, apply3.floatValue(), 0.0f, 0.0f, apply4.floatValue(), 0.0f, 0.0f, f, 0.1f)[0]);
    }

    public Vector3 getVector3Interpolate(int i2, AnimationParams[] animationParamsArr, float f, Function<AnimationParams, Vector3> function) {
        AnimationParams[] animationParamsArr2 = animationParamsArr;
        Function<AnimationParams, Vector3> function2 = function;
        Vector3 apply = function2.apply(animationParamsArr2[Math.max(i2 - 1, 0)]);
        Vector3 apply2 = function2.apply(animationParamsArr2[i2]);
        Vector3 apply3 = function2.apply(animationParamsArr2[i2 + 1]);
        Vector3 apply4 = function2.apply(animationParamsArr2[Math.min(i2 + 2, animationParamsArr2.length - 1)]);
        if (apply2 == apply3) {
            return apply2;
        }
        float f5 = apply.f4210x;
        float f8 = apply.y;
        float f10 = apply.z;
        float f11 = apply2.f4210x;
        float f12 = apply2.y;
        float f13 = apply2.z;
        float f14 = apply3.f4210x;
        float f15 = apply3.y;
        float f16 = apply3.z;
        float f17 = apply4.f4210x;
        float f18 = apply4.y;
        float f19 = f14;
        float f20 = f15;
        float f21 = f16;
        float f22 = f17;
        float f23 = f18;
        float[] cardinalSpline = JNI.cardinalSpline(f5, f8, f10, f11, f12, f13, f19, f20, f21, f22, f23, apply4.z, f, 0.1f);
        return new Vector3(cardinalSpline[0], cardinalSpline[1], cardinalSpline[2]);
    }

    public Vector3 slerpVector3(int i2, AnimationParams[] animationParamsArr, float f, Function<AnimationParams, Vector3> function) {
        Vector3 vector3Interpolate = getVector3Interpolate(i2, animationParamsArr, f, function);
        vector3Interpolate.normalize();
        return vector3Interpolate;
    }
}
