package com.samsung.o3dp.lib3dphotography;

import com.samsung.o3dp.lib3dphotography.graphics.Vector3;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Quaternion {
    public static final Quaternion IDENTITY = new Quaternion(0.0f, 0.0f, 0.0f, 1.0f);
    private final float w;

    /* renamed from: x  reason: collision with root package name */
    private final float f4201x;
    private final float y;
    private final float z;

    public Quaternion(float f, float f5, float f8, float f10) {
        this.f4201x = f;
        this.y = f5;
        this.z = f8;
        this.w = f10;
    }

    public static Quaternion makeRotation(Vector3 vector3, Vector3 vector32) {
        if (vector3 == vector32) {
            return IDENTITY;
        }
        Vector3 vector33 = new Vector3();
        float f = vector3.y;
        float f5 = vector32.z;
        float f8 = vector3.z;
        vector33.f4210x = (f * f5) - (vector32.y * f8);
        float f10 = vector32.f4210x;
        float f11 = vector3.f4210x;
        vector33.y = (f8 * f10) - (f5 * f11);
        vector33.z = (f11 * vector32.y) - (vector3.y * f10);
        if (vector33.magnitude() == 0.0f) {
            return IDENTITY;
        }
        vector33.normalize();
        double acos = (double) (((float) Math.acos((double) Vector3.dot(vector3, vector32))) * 0.5f);
        float sin = (float) Math.sin(acos);
        return new Quaternion(vector33.f4210x * sin, vector33.y * sin, vector33.z * sin, (float) Math.cos(acos));
    }

    public static Vector3 slerp(Vector3 vector3, Vector3 vector32, float f) {
        if (vector3 == vector32) {
            return vector3;
        }
        Vector3 vector33 = new Vector3();
        float f5 = vector3.y;
        float f8 = vector32.z;
        float f10 = vector3.z;
        vector33.f4210x = (f5 * f8) - (vector32.y * f10);
        float f11 = vector32.f4210x;
        float f12 = vector3.f4210x;
        vector33.y = (f10 * f11) - (f8 * f12);
        vector33.z = (f12 * vector32.y) - (vector3.y * f11);
        if (vector33.magnitude() == 0.0f) {
            return vector3;
        }
        vector33.normalize();
        float dot = Vector3.dot(vector3, vector32);
        if (dot > 1.0f - Math.ulp(1.0f)) {
            return Vector3.lerp(vector3, vector32, f);
        }
        double acos = (double) (((float) Math.acos((double) dot)) * f * 0.5f);
        float sin = (float) Math.sin(acos);
        return new Quaternion(vector33.f4210x * sin, vector33.y * sin, vector33.z * sin, (float) Math.cos(acos)).mult(vector3);
    }

    public Vector3 mult(Vector3 vector3) {
        float f = vector3.f4210x;
        float f5 = vector3.y;
        float f8 = vector3.z;
        if (f == 0.0f && f5 == 0.0f && f8 == 0.0f) {
            return vector3;
        }
        Vector3 vector32 = new Vector3();
        float f10 = this.w;
        float f11 = this.y;
        float f12 = this.z;
        float f13 = this.f4201x;
        vector32.f4210x = (((((f12 * 2.0f) * f13) * f8) + ((((f11 * 2.0f) * f13) * f5) + (((f13 * f13) * f) + (((((f11 * 2.0f) * f10) * f8) + ((f10 * f10) * f)) - (((f12 * 2.0f) * f10) * f5))))) - ((f12 * f12) * f)) - ((f11 * f11) * f);
        vector32.y = ((((f10 * f10) * f5) + (((((f10 * 2.0f) * f12) * f) + ((((f12 * 2.0f) * f11) * f8) + (((f11 * f11) * f5) + (((f13 * 2.0f) * f11) * f)))) - ((f12 * f12) * f5))) - (((f13 * 2.0f) * f10) * f8)) - ((f13 * f13) * f5);
        float f14 = f11 * 2.0f * f12 * f5;
        float f15 = 2.0f * f10 * f13 * f5;
        float f16 = f10 * f10 * f8;
        vector32.z = f16 + ((f15 + (((((f12 * f12) * f8) + (f14 + (((f13 * 2.0f) * f12) * f))) - (((f10 * 2.0f) * f11) * f)) - ((f11 * f11) * f8))) - ((f13 * f13) * f8));
        return vector32;
    }
}
