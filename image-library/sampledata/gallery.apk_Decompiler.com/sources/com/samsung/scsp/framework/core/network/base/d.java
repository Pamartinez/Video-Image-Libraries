package com.samsung.scsp.framework.core.network.base;

import com.samsung.scsp.framework.core.network.HttpRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Supplier {
    public final /* synthetic */ int d = 0;
    public final /* synthetic */ HttpRequest e;
    public final /* synthetic */ Serializable f;

    public /* synthetic */ d(HttpRequest httpRequest, String str) {
        this.e = httpRequest;
        this.f = str;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return NetworkImpl.lambda$execute$9(this.e, (String) this.f);
            default:
                return NetworkImpl.lambda$getConnection$7((HashMap) this.f, this.e);
        }
    }

    public /* synthetic */ d(HashMap hashMap, HttpRequest httpRequest) {
        this.f = hashMap;
        this.e = httpRequest;
    }
}
