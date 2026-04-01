package com.samsung.o3dp.lib3dphotography.graphics;

import He.F;
import com.samsung.o3dp.lib3dphotography.utils.MathUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Vector3 {

    /* renamed from: x  reason: collision with root package name */
    public float f4210x = 0.0f;
    public float y = 0.0f;
    public float z = 0.0f;

    public Vector3() {
    }

    public static Vector3 direction(Vector3 vector3, Vector3 vector32) {
        Vector3 subtract = subtract(vector3, vector32);
        subtract.normalize();
        return subtract;
    }

    public static float dot(Vector3 vector3, Vector3 vector32) {
        float f = vector3.f4210x * vector32.f4210x;
        return (vector3.z * vector32.z) + (vector3.y * vector32.y) + f;
    }

    public static Vector3 lerp(Vector3 vector3, Vector3 vector32, float f) {
        Vector3 vector33 = new Vector3();
        vector33.f4210x = F.M(vector3.f4210x, vector32.f4210x, f);
        vector33.y = F.M(vector3.y, vector32.y, f);
        vector33.z = F.M(vector3.z, vector32.z, f);
        return vector33;
    }

    public static float measureDistance(Vector3 vector3, Vector3 vector32) {
        return subtract(vector3, vector32).magnitude();
    }

    public static Vector3 subtract(Vector3 vector3, Vector3 vector32) {
        return new Vector3(vector32.f4210x - vector3.f4210x, vector32.y - vector3.y, vector32.z - vector3.z);
    }

    public Vector3 add(Vector3 vector3) {
        return new Vector3(this.f4210x + vector3.f4210x, this.y + vector3.y, this.z + vector3.z);
    }

    public Vector3 clamp(float f, float f5) {
        this.f4210x = Math.max(Math.min(f5, this.f4210x), f);
        this.y = Math.max(Math.min(f5, this.y), f);
        this.z = Math.max(Math.min(f5, this.z), f);
        return this;
    }

    public float magnitude() {
        float f = this.f4210x;
        float f5 = this.y;
        float f8 = f5 * f5;
        float f10 = this.z;
        return (float) Math.sqrt((double) ((f10 * f10) + f8 + (f * f)));
    }

    public Vector3 max(Vector3 vector3) {
        return new Vector3(Math.min(this.f4210x, vector3.f4210x), Math.min(this.y, vector3.y), Math.min(this.z, vector3.z));
    }

    public Vector3 min(Vector3 vector3) {
        return new Vector3(Math.max(this.f4210x, vector3.f4210x), Math.max(this.y, vector3.y), Math.max(this.z, vector3.z));
    }

    public void normalize() {
        float magnitude = magnitude();
        if (magnitude > 0.0f) {
            this.f4210x /= magnitude;
            this.y /= magnitude;
            this.z /= magnitude;
        }
    }

    public void set(float f, float f5, float f8) {
        this.f4210x = f;
        this.y = f5;
        this.z = f8;
    }

    public String toShortString() {
        return toShortString(1000.0f);
    }

    public String toString() {
        return "Vector3{x=" + this.f4210x + ", y=" + this.y + ", z=" + this.z + '}';
    }

    public String toShortString(float f) {
        return "{x=" + MathUtil.round(this.f4210x, f) + ", y=" + MathUtil.round(this.y, f) + ", z=" + MathUtil.round(this.z, f) + '}';
    }

    public Vector3(Vector3 vector3) {
        this.f4210x = vector3.f4210x;
        this.y = vector3.y;
        this.z = vector3.z;
    }

    public Vector3(float f, float f5, float f8) {
        set(f, f5, f8);
    }
}
