package com.samsung.o3dp.lib3dphotography.animation.transition;

import com.samsung.o3dp.lib3dphotography.utils.LogUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Transition {
    private static final String TAG = "Transition";

    public void setValue(Object obj) {
        LogUtil.i(TAG, "setValue " + obj);
    }

    public abstract float transitionTime(float f);
}
