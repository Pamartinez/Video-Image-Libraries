package com.samsung.o3dp.lib3dphotography.animation.movement;

import com.samsung.o3dp.lib3dphotography.animation.AnimationParams;
import com.samsung.o3dp.lib3dphotography.graphics.Vector3;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CircularMovement implements Movement {
    private double calculateInterpolatedAngle(Vector3 vector3, Vector3 vector32, float f) {
        double atan2 = Math.atan2((double) vector3.y, (double) vector3.f4210x);
        double atan22 = Math.atan2((double) vector32.y, (double) vector32.f4210x) - atan2;
        if (atan22 > 3.141592653589793d) {
            atan22 -= 6.283185307179586d;
        } else if (atan22 < -3.141592653589793d) {
            atan22 += 6.283185307179586d;
        }
        return (atan22 * ((double) f)) + atan2;
    }

    private static double norm(Vector3 vector3) {
        float f = vector3.f4210x;
        float f5 = vector3.y;
        return Math.sqrt((double) ((f5 * f5) + (f * f)));
    }

    public float getDistanceToNextParam(int i2, AnimationParams[] animationParamsArr, Function<AnimationParams, Vector3> function) {
        return Vector3.measureDistance(function.apply(animationParamsArr[i2]), function.apply(animationParamsArr[i2 + 1]));
    }

    public Float getFloatInterpolate(int i2, AnimationParams[] animationParamsArr, float f, Function<AnimationParams, Float> function) {
        return Float.valueOf((function.apply(animationParamsArr[i2 + 1]).floatValue() * f) + ((1.0f - f) * function.apply(animationParamsArr[i2]).floatValue()));
    }

    public Vector3 getVector3Interpolate(int i2, AnimationParams[] animationParamsArr, float f, Function<AnimationParams, Vector3> function) {
        Function<AnimationParams, Vector3> function2 = function;
        Vector3 apply = function2.apply(animationParamsArr[i2]);
        Vector3 apply2 = function2.apply(animationParamsArr[i2 + 1]);
        double norm = norm(apply);
        double norm2 = norm(apply2);
        if (Double.compare(norm, norm2) == 0) {
            double calculateInterpolatedAngle = calculateInterpolatedAngle(apply, apply2, f);
            return new Vector3((float) (Math.cos(calculateInterpolatedAngle) * norm), (float) (Math.sin(calculateInterpolatedAngle) * norm), apply.z);
        }
        throw new IllegalArgumentException(String.format("Vectors are not on the same circle! r0:%f r1:%f v0:(%f %f %f), v1:(%f %f %f)", new Object[]{Double.valueOf(norm), Double.valueOf(norm2), Float.valueOf(apply.f4210x), Float.valueOf(apply.y), Float.valueOf(apply.z), Float.valueOf(apply2.f4210x), Float.valueOf(apply2.y), Float.valueOf(apply2.z)}));
    }

    public Vector3 slerpVector3(int i2, AnimationParams[] animationParamsArr, float f, Function<AnimationParams, Vector3> function) {
        Vector3 vector3Interpolate = getVector3Interpolate(i2, animationParamsArr, f, function);
        vector3Interpolate.normalize();
        return vector3Interpolate;
    }
}
