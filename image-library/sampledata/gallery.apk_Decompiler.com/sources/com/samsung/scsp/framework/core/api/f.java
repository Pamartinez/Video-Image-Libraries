package com.samsung.scsp.framework.core.api;

import com.samsung.scsp.common.Header;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.api.SimpleJob;
import com.samsung.scsp.framework.core.network.HttpRequestImpl;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ f(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        HttpRequestImpl.HttpRequestBuilder httpRequestBuilder = (HttpRequestImpl.HttpRequestBuilder) obj;
        switch (this.d) {
            case 0:
                httpRequestBuilder.addHeader("x-sc-device-locale", FaultBarrier.get(new Object(), "en").obj);
                return;
            case 1:
                httpRequestBuilder.addHeader(Header.ACCEPT_ENCODING, Header.GZIP);
                return;
            default:
                SimpleJob.AnonymousClass1.lambda$new$3(httpRequestBuilder);
                return;
        }
    }
}
