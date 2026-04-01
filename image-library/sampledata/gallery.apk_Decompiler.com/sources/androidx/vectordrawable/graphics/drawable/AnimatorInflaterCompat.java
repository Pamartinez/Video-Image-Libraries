package androidx.vectordrawable.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AnimatorInflaterCompat {
    public static Animator loadAnimator(Context context, int i2) {
        return AnimatorInflater.loadAnimator(context, i2);
    }
}
