package com.samsung.scsp.framework.core.identity;

import com.google.gson.JsonObject;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.network.Network;
import java.io.InputStream;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements Network.StreamListener {
    public final /* synthetic */ int d = 0;
    public final /* synthetic */ Map e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ m(RegistrationApiImpl registrationApiImpl, Map map, JsonObject jsonObject) {
        this.f = registrationApiImpl;
        this.e = map;
        this.g = jsonObject;
    }

    public final void onStream(HttpRequest httpRequest, Map map, InputStream inputStream) {
        switch (this.d) {
            case 0:
                RegistrationApiImpl registrationApiImpl = (RegistrationApiImpl) this.f;
                registrationApiImpl.lambda$register$4(this.e, (JsonObject) this.g, httpRequest, map, inputStream);
                return;
            default:
                ((TokenApiImpl) this.f).lambda$issue$1((String[]) this.g, this.e, httpRequest, map, inputStream);
                return;
        }
    }

    public /* synthetic */ m(TokenApiImpl tokenApiImpl, String[] strArr, Map map) {
        this.f = tokenApiImpl;
        this.g = strArr;
        this.e = map;
    }
}
