package com.samsung.android.vexfwk.sdk.docscan;

import me.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ VexFwkDocRefiner e;
    public final /* synthetic */ i f;

    public /* synthetic */ d(VexFwkDocRefiner vexFwkDocRefiner, i iVar, int i2) {
        this.d = i2;
        this.e = vexFwkDocRefiner;
        this.f = iVar;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                VexFwkDocRefiner$sessionCallback$1.onRequestCompleted$lambda$3(this.e, this.f);
                return;
            default:
                VexFwkDocRefiner$sessionCallback$1.onRequestProgressed$lambda$1(this.e, this.f);
                return;
        }
    }
}
