package com.samsung.android.vexfwk.sdk.docscan;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ VexFwkDocRefiner e;

    public /* synthetic */ c(VexFwkDocRefiner vexFwkDocRefiner, int i2) {
        this.d = i2;
        this.e = vexFwkDocRefiner;
    }

    public final void run() {
        int i2 = this.d;
        VexFwkDocRefiner vexFwkDocRefiner = this.e;
        switch (i2) {
            case 0:
                VexFwkDocRefiner.handleProcessResult$lambda$13$lambda$12(vexFwkDocRefiner);
                return;
            default:
                VexFwkDocRefiner.handleProcessResult$lambda$19$lambda$18(vexFwkDocRefiner);
                return;
        }
    }
}
