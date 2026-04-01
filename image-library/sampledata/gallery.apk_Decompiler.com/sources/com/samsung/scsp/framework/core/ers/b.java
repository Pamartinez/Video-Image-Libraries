package com.samsung.scsp.framework.core.ers;

import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.network.Network;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Network.StreamListener, FaultBarrier.ThrowableSupplier, FaultBarrier.ThrowableRunnable {
    public final /* synthetic */ Object d;

    public /* synthetic */ b(Object obj) {
        this.d = obj;
    }

    public Object get() {
        return Long.valueOf(Long.parseLong(((String) ((List) this.d).get(0)).split("=")[1]));
    }

    public void onStream(HttpRequest httpRequest, Map map, InputStream inputStream) {
        ((ErsImpl) this.d).lambda$execute$4(httpRequest, map, inputStream);
    }

    public void run() {
        ((Thread) this.d).join();
    }
}
