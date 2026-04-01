package com.samsung.o3dp.lib3dphotography;

import com.samsung.o3dp.lib3dphotography.animation.Animation;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ g(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((O3DPSurfaceView) obj2).lambda$resumeAnimation$0((Void) obj);
                return;
            case 1:
                ((O3DPSurfaceView) obj2).lambda$surfaceCreated$1((Void) obj);
                return;
            default:
                ((Animation) obj2).setCameraDirPivotZ(((DepthMap) obj).fgDepthMean);
                return;
        }
    }
}
