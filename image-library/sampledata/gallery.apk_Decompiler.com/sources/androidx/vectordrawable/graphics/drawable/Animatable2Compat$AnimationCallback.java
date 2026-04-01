package androidx.vectordrawable.graphics.drawable;

import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Animatable2Compat$AnimationCallback {
    Animatable2.AnimationCallback mPlatformCallback;

    public Animatable2.AnimationCallback getPlatformCallback() {
        if (this.mPlatformCallback == null) {
            this.mPlatformCallback = new Animatable2.AnimationCallback() {
                public void onAnimationEnd(Drawable drawable) {
                    Animatable2Compat$AnimationCallback.this.onAnimationEnd(drawable);
                }

                public void onAnimationStart(Drawable drawable) {
                    Animatable2Compat$AnimationCallback.this.onAnimationStart(drawable);
                }
            };
        }
        return this.mPlatformCallback;
    }

    public void onAnimationEnd(Drawable drawable) {
    }

    public void onAnimationStart(Drawable drawable) {
    }
}
