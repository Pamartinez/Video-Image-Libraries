package com.samsung.o3dp.lib3dphotography.animation.time;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AnimationTime {
    public static final float TIME_SCALE = 0.005f;
    protected float mDeltaTime = 0.0f;
    protected float m_currentTime = 0.0f;
    protected float m_direction = 1.0f;
    protected float m_totalTime = 0.0f;

    public void consumeByDeltaTime() {
        update(this.mDeltaTime);
    }

    public float getCurrentTime() {
        return this.m_currentTime;
    }

    public float getDeltaTime() {
        return this.mDeltaTime;
    }

    public float getOverallTime() {
        return this.m_totalTime;
    }

    public void reset() {
        this.m_totalTime = 0.0f;
        this.m_currentTime = 0.0f;
        this.m_direction = 1.0f;
    }

    public void setDeltaTime(float f) {
        this.mDeltaTime = f;
    }

    public void update(float f) {
        this.m_totalTime = (f * 0.005f) + this.m_totalTime;
        this.m_currentTime = (f * this.m_direction * 0.005f) + this.m_currentTime;
    }
}
