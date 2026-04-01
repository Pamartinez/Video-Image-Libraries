package com.samsung.scsp.framework.core.identity;

import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.network.Network;
import com.samsung.scsp.framework.core.util.HashUtil;
import java.io.InputStream;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements FaultBarrier.ThrowableSupplier, Network.StreamListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ f(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public Object get() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                return HashUtil.getStringSHA256(((AccountInfoSupplier) obj).getUserId());
            case 1:
                return HashUtil.getStringSHA256((String) ((Map) obj).get("x-sc-uid"));
            default:
                return ((TokenApiImpl) obj).issue();
        }
    }

    public void onStream(HttpRequest httpRequest, Map map, InputStream inputStream) {
        ((UpdateApiImpl) this.e).lambda$update$1(httpRequest, map, inputStream);
    }
}
