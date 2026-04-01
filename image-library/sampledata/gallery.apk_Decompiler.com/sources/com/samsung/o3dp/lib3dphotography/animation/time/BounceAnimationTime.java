package com.samsung.o3dp.lib3dphotography.animation.time;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BounceAnimationTime extends AnimationTime {
    public void update(float f) {
        super.update(f);
        float f5 = this.m_currentTime;
        if (f5 > 1.0f) {
            this.m_currentTime = 1.0f;
            float f8 = this.m_direction;
            if (f8 > 0.0f) {
                this.m_direction = -f8;
            }
        } else if (f5 < 0.0f) {
            this.m_currentTime = 0.0f;
            float f10 = this.m_direction;
            if (f10 < 0.0f) {
                this.m_direction = -f10;
            }
        }
    }
}
