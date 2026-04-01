package com.samsung.scsp.framework.core.identity;

import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.network.Network;
import java.io.InputStream;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements FaultBarrier.ThrowableRunnable, Network.StreamListener {
    public final /* synthetic */ RegistrationApiImpl d;

    public /* synthetic */ j(RegistrationApiImpl registrationApiImpl) {
        this.d = registrationApiImpl;
    }

    public void onStream(HttpRequest httpRequest, Map map, InputStream inputStream) {
        this.d.lambda$deregister$5(httpRequest, map, inputStream);
    }

    public void run() {
        this.d.register();
    }
}
