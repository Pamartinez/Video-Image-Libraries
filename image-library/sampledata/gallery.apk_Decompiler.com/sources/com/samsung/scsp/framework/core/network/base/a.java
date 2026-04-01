package com.samsung.scsp.framework.core.network.base;

import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.ScspException;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.network.Network;
import com.samsung.scsp.framework.core.network.base.NetworkImpl;
import java.net.HttpURLConnection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements NetworkImpl.ConnectionSetter, FaultBarrier.ThrowableRunnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ HttpRequest e;
    public final /* synthetic */ Object f;

    public /* synthetic */ a(HttpRequest httpRequest, Object obj, int i2) {
        this.d = i2;
        this.e = httpRequest;
        this.f = obj;
    }

    public void run() {
        this.e.error(((ScspException) this.f).rcode);
    }

    public void setup(HttpURLConnection httpURLConnection) {
        switch (this.d) {
            case 0:
                NetworkImpl.lambda$post$1(this.e, (Network.StreamListener) this.f, httpURLConnection);
                return;
            case 1:
                NetworkImpl.lambda$patch$3(this.e, (Network.StreamListener) this.f, httpURLConnection);
                return;
            default:
                NetworkImpl.lambda$put$2(this.e, (Network.StreamListener) this.f, httpURLConnection);
                return;
        }
    }
}
