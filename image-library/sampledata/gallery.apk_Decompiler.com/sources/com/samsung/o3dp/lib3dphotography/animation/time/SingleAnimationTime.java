package com.samsung.o3dp.lib3dphotography.animation.time;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SingleAnimationTime extends AnimationTime {
    public void update(float f) {
        super.update(f);
        this.m_currentTime = Math.min(1.0f, Math.max(this.m_currentTime, 0.0f));
        this.m_totalTime = Math.min(1.0f, Math.max(this.m_totalTime, 0.0f));
    }
}
