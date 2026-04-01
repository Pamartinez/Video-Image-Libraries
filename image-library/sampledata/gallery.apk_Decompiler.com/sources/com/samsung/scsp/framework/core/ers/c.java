package com.samsung.scsp.framework.core.ers;

import android.content.Context;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.network.Network;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements FaultBarrier.ThrowableRunnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ErsImpl e;
    public final /* synthetic */ Context f;
    public final /* synthetic */ Network g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ String f4217h;

    public /* synthetic */ c(ErsImpl ersImpl, Context context, Network network, String str, int i2) {
        this.d = i2;
        this.e = ersImpl;
        this.f = context;
        this.g = network;
        this.f4217h = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$getDomain$0(this.f, this.g, this.f4217h);
                return;
            default:
                this.e.lambda$getDomain$1(this.f, this.g, this.f4217h);
                return;
        }
    }
}
