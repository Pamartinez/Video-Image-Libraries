package com.samsung.android.gallery.widget.animations;

import android.view.View;
import com.samsung.android.gallery.widget.animator.PathInterpolator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CheckboxAnimation {
    private static int CHECKBOX_ANIMATION_DURATION = 400;

    public void show(View view) {
        view.setScaleX(0.5f);
        view.setScaleY(0.5f);
        view.setAlpha(0.0f);
        view.animate().scaleX(1.0f).scaleY(1.0f).setDuration((long) CHECKBOX_ANIMATION_DURATION).alpha(1.0f).setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_90)).start();
    }
}
