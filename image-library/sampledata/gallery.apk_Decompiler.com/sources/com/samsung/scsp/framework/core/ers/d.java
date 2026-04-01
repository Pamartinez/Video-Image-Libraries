package com.samsung.scsp.framework.core.ers;

import android.content.Context;
import com.samsung.scsp.framework.core.network.Network;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ ErsImpl d;
    public final /* synthetic */ Context e;
    public final /* synthetic */ Network f;
    public final /* synthetic */ String g;

    public /* synthetic */ d(ErsImpl ersImpl, Context context, Network network, String str) {
        this.d = ersImpl;
        this.e = context;
        this.f = network;
        this.g = str;
    }

    public final void run() {
        this.d.lambda$getDomain$2(this.e, this.f, this.g);
    }
}
