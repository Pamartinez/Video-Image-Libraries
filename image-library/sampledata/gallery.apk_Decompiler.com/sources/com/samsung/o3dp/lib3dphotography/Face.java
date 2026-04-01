package com.samsung.o3dp.lib3dphotography;

import android.graphics.Rect;
import com.samsung.o3dp.lib3dphotography.graphics.O3DPRect;
import com.samsung.o3dp.lib3dphotography.utils.StringUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Face extends O3DPRect {
    private float mPitch;
    private float mRoll;
    private float mYaw;

    public Face(O3DPObjType o3DPObjType) {
        super(o3DPObjType);
    }

    public float getPitch() {
        return this.mPitch;
    }

    public float getRoll() {
        return this.mRoll;
    }

    public float getYaw() {
        return this.mYaw;
    }

    public void setPitch(float f) {
        this.mPitch = f;
    }

    public void setRoll(float f) {
        this.mRoll = f;
    }

    public void setYaw(float f) {
        this.mYaw = f;
    }

    public String toString() {
        return StringUtil.format("bound %s, roll %f, yaw %f, pitch %f", this.mBound, Float.valueOf(this.mRoll), Float.valueOf(this.mYaw), Float.valueOf(this.mPitch));
    }

    public Face copyInstance() {
        Face face = new Face(this.mObjectType);
        Rect rect = this.mBound;
        face.setBound(new Rect(rect.left, rect.top, rect.right, rect.bottom));
        face.setPitch(this.mPitch);
        face.setYaw(this.mYaw);
        face.setRoll(this.mRoll);
        return face;
    }
}
