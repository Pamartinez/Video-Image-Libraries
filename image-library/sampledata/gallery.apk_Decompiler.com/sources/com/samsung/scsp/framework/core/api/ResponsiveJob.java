package com.samsung.scsp.framework.core.api;

import Bd.C0726b;
import com.google.gson.Gson;
import com.samsung.android.sum.core.types.NumericEnum;
import com.samsung.scsp.common.Header;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.network.Network;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ResponsiveJob extends SimpleJob implements Network.StreamListener {
    private final Class<?> responseClass;

    public ResponsiveJob(HttpRequest.Method method, String str, String str2, Class<?> cls) {
        super(method, str, str2);
        this.responseClass = cls;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$onStream$0(HttpRequest httpRequest, String str) {
        return "[onStream] : " + httpRequest.hashCode() + NumericEnum.SEP + str;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$onStream$1(Map map) {
        return (String) ((List) map.get(Header.ETAG)).get(0);
    }

    public void onStream(HttpRequest httpRequest, Map<String, List<String>> map, InputStream inputStream) {
        T t;
        this.responseClass.getClass();
        int parseInt = Integer.parseInt((String) map.get(Network.HTTP_STATUS).get(0));
        if (parseInt == 200 || parseInt == 202) {
            T response = new Response(inputStream).toString();
            Logger.get(httpRequest.getName()).d(new C0726b(10, httpRequest, response));
            if (this.responseClass == String.class) {
                t = response;
            } else {
                t = new Gson().fromJson((String) response, this.responseClass);
            }
        } else {
            Class<?> cls = this.responseClass;
            Objects.requireNonNull(cls);
            t = FaultBarrier.get(new b(cls, 1), null).obj;
        }
        if (t instanceof CacheableResponse) {
            ((CacheableResponse) t).update(parseInt, (String) FaultBarrier.get(new a(4, map), null, true).obj);
        }
        httpRequest.getResponseListener().onResponse(t, map);
    }

    public Network.StreamListener getStreamListener() {
        return this;
    }
}
