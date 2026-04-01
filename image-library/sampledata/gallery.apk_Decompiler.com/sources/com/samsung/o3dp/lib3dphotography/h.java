package com.samsung.o3dp.lib3dphotography;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ O3DPhotoPipeline e;

    public /* synthetic */ h(O3DPhotoPipeline o3DPhotoPipeline, int i2) {
        this.d = i2;
        this.e = o3DPhotoPipeline;
    }

    public final void run() {
        int i2 = this.d;
        O3DPhotoPipeline o3DPhotoPipeline = this.e;
        switch (i2) {
            case 0:
                o3DPhotoPipeline.lambda$resumeAnimation$4();
                return;
            case 1:
                o3DPhotoPipeline.lambda$requestRender$6();
                return;
            case 2:
                o3DPhotoPipeline.lambda$release$11();
                return;
            default:
                o3DPhotoPipeline.updateResultRendererSize();
                return;
        }
    }
}
