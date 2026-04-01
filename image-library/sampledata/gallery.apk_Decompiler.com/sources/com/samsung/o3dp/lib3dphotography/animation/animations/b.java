package com.samsung.o3dp.lib3dphotography.animation.animations;

import androidx.dynamicanimation.animation.DynamicAnimation;
import com.samsung.o3dp.lib3dphotography.animation.animations.PanoramaAnimation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements DynamicAnimation.OnAnimationUpdateListener {
    public final /* synthetic */ PanoramaAnimation.PanoramaGestureListener d;

    public /* synthetic */ b(PanoramaAnimation.PanoramaGestureListener panoramaGestureListener) {
        this.d = panoramaGestureListener;
    }

    public final void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f, float f5) {
        this.d.lambda$onFling$0(dynamicAnimation, f, f5);
    }
}
