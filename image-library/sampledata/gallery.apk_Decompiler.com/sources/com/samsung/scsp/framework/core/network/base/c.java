package com.samsung.scsp.framework.core.network.base;

import java.net.HttpURLConnection;
import java.util.Map;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4219a;

    public /* synthetic */ c(int i2) {
        this.f4219a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f4219a) {
            case 0:
                return NetworkImpl.lambda$execute$8((Map.Entry) obj);
            case 1:
                return NetworkImpl.lambda$new$0((String) obj);
            default:
                return NetworkImpl.lambda$close$4((HttpURLConnection) obj);
        }
    }
}
