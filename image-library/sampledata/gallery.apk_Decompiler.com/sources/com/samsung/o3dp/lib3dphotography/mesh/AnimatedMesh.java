package com.samsung.o3dp.lib3dphotography.mesh;

import com.samsung.o3dp.lib3dphotography.animation.Animation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AnimatedMesh {
    private final Animation animation;
    private final byte[] mesh;

    public AnimatedMesh(byte[] bArr, Animation animation2) {
        this.mesh = bArr;
        this.animation = animation2;
    }

    public Animation getAnimation() {
        return this.animation;
    }

    public byte[] getMesh() {
        return this.mesh;
    }
}
