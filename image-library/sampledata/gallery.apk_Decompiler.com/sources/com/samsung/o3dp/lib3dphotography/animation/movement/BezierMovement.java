package com.samsung.o3dp.lib3dphotography.animation.movement;

import com.samsung.o3dp.lib3dphotography.animation.AnimationParams;
import com.samsung.o3dp.lib3dphotography.graphics.Vector3;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BezierMovement implements Movement {
    private float calculateBezierPoint(float[] fArr, float f) {
        float f5 = 0.0f;
        for (int i2 = 0; i2 < fArr.length; i2++) {
            f5 += (float) (Math.pow((double) f, (double) i2) * Math.pow((double) (1.0f - f), (double) ((fArr.length - 1) - i2)) * ((double) binomialCoefficient(fArr.length - 1, i2)) * ((double) fArr[i2]));
        }
        return f5;
    }

    public float binomialCoefficient(int i2, int i7) {
        if (i7 == 0 || i7 == i2) {
            return 1.0f;
        }
        int i8 = i2 - 1;
        return binomialCoefficient(i8, i7) + binomialCoefficient(i8, i7 - 1);
    }

    public float getDistanceToNextParam(int i2, AnimationParams[] animationParamsArr, Function<AnimationParams, Vector3> function) {
        return Vector3.measureDistance(function.apply(animationParamsArr[i2]), function.apply(animationParamsArr[i2 + 1]));
    }

    public Float getFloatInterpolate(int i2, AnimationParams[] animationParamsArr, float f, Function<AnimationParams, Float> function) {
        float[] fArr = new float[animationParamsArr.length];
        for (int i7 = 0; i7 < animationParamsArr.length; i7++) {
            fArr[i7] = function.apply(animationParamsArr[i7]).floatValue();
        }
        return Float.valueOf(calculateBezierPoint(fArr, ((animationParamsArr[i2 + 1].time.floatValue() - animationParamsArr[i2].time.floatValue()) * f) + animationParamsArr[i2].time.floatValue()));
    }

    public Vector3 getVector3Interpolate(int i2, AnimationParams[] animationParamsArr, float f, Function<AnimationParams, Vector3> function) {
        Vector3[] vector3Arr = new Vector3[animationParamsArr.length];
        for (int i7 = 0; i7 < animationParamsArr.length; i7++) {
            vector3Arr[i7] = function.apply(animationParamsArr[i7]);
        }
        return calculateBezierPoint(vector3Arr, ((animationParamsArr[i2 + 1].time.floatValue() - animationParamsArr[i2].time.floatValue()) * f) + animationParamsArr[i2].time.floatValue());
    }

    public Vector3 slerpVector3(int i2, AnimationParams[] animationParamsArr, float f, Function<AnimationParams, Vector3> function) {
        Vector3 vector3Interpolate = getVector3Interpolate(i2, animationParamsArr, f, function);
        vector3Interpolate.normalize();
        return vector3Interpolate;
    }

    private Vector3 calculateBezierPoint(Vector3[] vector3Arr, float f) {
        Vector3 vector3 = new Vector3();
        int length = vector3Arr.length - 1;
        for (int i2 = 0; i2 < vector3Arr.length; i2++) {
            float pow = (float) (Math.pow((double) f, (double) i2) * Math.pow((double) (1.0f - f), (double) (length - i2)) * ((double) binomialCoefficient(length, i2)));
            float f5 = vector3.f4210x;
            Vector3 vector32 = vector3Arr[i2];
            vector3.f4210x = (vector32.f4210x * pow) + f5;
            vector3.y = (vector32.y * pow) + vector3.y;
            vector3.z = (pow * vector32.z) + vector3.z;
        }
        return vector3;
    }
}
