package com.samsung.o3dp.lib3dphotography;

import android.graphics.Rect;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ O3DPhotoPipeline e;

    public /* synthetic */ j(O3DPhotoPipeline o3DPhotoPipeline, int i2) {
        this.d = i2;
        this.e = o3DPhotoPipeline;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        O3DPhotoPipeline o3DPhotoPipeline = this.e;
        switch (i2) {
            case 0:
                o3DPhotoPipeline.lambda$resumeAnimation$5((Void) obj);
                return;
            default:
                o3DPhotoPipeline.lambda$prepareToRender$9((Rect) obj);
                return;
        }
    }
}
