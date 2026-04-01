package com.samsung.android.sum.core.buffer;

import com.samsung.android.motionphoto.core.MPSurfaceReader;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Consumer {
    public final void accept(Object obj) {
        ((MPSurfaceReader.MPSurfaceImage) obj).close();
    }
}
