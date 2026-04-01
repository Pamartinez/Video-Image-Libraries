package com.samsung.android.sum.core.channel;

import android.view.Surface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SurfaceReadChannel extends SurfaceChannel {
    Surface getSurface();

    boolean isRequireToConfigure(int i2, int i7, int i8, long j2) {
        return isRequireToConfigure();
    }
}
