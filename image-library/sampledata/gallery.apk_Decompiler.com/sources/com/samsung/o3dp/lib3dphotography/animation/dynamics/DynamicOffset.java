package com.samsung.o3dp.lib3dphotography.animation.dynamics;

import c0.C0086a;
import com.samsung.o3dp.lib3dphotography.graphics.Vector3;
import com.samsung.o3dp.lib3dphotography.interaction.InputEvent;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DynamicOffset {
    private static final float DEFAULT_OFFSET_Z = 0.0f;
    private static final String TAG = "DynamicOffset";
    protected Vector3 mAmplitude = new Vector3(1.0f, 1.0f, 1.0f);
    DynamicsIntensity mDynamicsIntensity = DynamicsIntensity.Level0;
    private float mEmbossingZ = 0.0f;
    protected boolean mForegroundOnly = false;
    private float mIntensity = 1.0f;
    protected float mSpeed = 1.0f;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum DynamicsIntensity {
        Level5(5, 1.0f),
        Level4(4, 0.8f),
        Level3(3, 0.6f),
        Level2(2, 0.4f),
        Level1(1, 0.2f),
        Level0(0, 0.1f);
        
        public final int level;
        final float restrictionPercent;

        private DynamicsIntensity(int i2, float f) {
            this.level = i2;
            this.restrictionPercent = f;
        }

        public static DynamicsIntensity find(int i2) {
            for (DynamicsIntensity dynamicsIntensity : values()) {
                if (dynamicsIntensity.level == i2) {
                    return dynamicsIntensity;
                }
            }
            return Level5;
        }
    }

    public abstract Vector3 getDynamicOffset(float f);

    public float getEmbossingZ() {
        return this.mEmbossingZ;
    }

    public boolean getForegroundOnly() {
        return this.mForegroundOnly;
    }

    public final float getIntensity() {
        return this.mIntensity * this.mDynamicsIntensity.restrictionPercent;
    }

    public int getSensorType() {
        return -1;
    }

    public boolean isTouchInteraction() {
        return false;
    }

    public boolean onTouchEvent(InputEvent.Touch touch) {
        return false;
    }

    public void parseConfig(JSONObject jSONObject) {
        this.mIntensity = (float) jSONObject.optDouble("intensity", (double) this.mIntensity);
        this.mSpeed = (float) jSONObject.optDouble("speed", (double) this.mSpeed);
        this.mForegroundOnly = jSONObject.optBoolean("foreground_only", this.mForegroundOnly);
        this.mEmbossingZ = (float) jSONObject.optDouble("embossing_z", (double) this.mEmbossingZ);
        setDynamicsIntensity(jSONObject.optInt("dynamics_intensity", this.mDynamicsIntensity.level));
    }

    public boolean respondsToAudio() {
        return false;
    }

    public void setAmplitude(float f, float f5) {
        Vector3 vector3 = this.mAmplitude;
        vector3.f4210x = f;
        vector3.y = f5;
        vector3.z = 1.0f;
    }

    public void setDynamicsIntensity(int i2) {
        this.mDynamicsIntensity = DynamicsIntensity.find(i2);
        StringBuilder o2 = C0086a.o(i2, "setDynamicsIntensity level : ", " name : ");
        o2.append(this.mDynamicsIntensity.name());
        o2.append(" intensity : ");
        o2.append(getIntensity());
        LogUtil.i(TAG, o2.toString());
    }

    public void setEmbossingZ(float f) {
        this.mEmbossingZ = f;
    }

    public final void setIntensity(float f) {
        this.mIntensity = f;
    }

    public void setStaticDynamicsDirection(int i2) {
        LogUtil.i(TAG, "setStaticDynamicsDirection() : " + i2 + " is not used");
    }

    public void onMotionSensorEvent(InputEvent.MotionSensor motionSensor) {
    }

    public void setAudioLevel(float f) {
    }
}
