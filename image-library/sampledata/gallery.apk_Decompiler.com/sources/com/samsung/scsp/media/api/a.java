package com.samsung.scsp.media.api;

import com.google.gson.JsonObject;
import com.samsung.scsp.framework.core.api.AbstractApiControl;
import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.api.SCHashMap;
import com.samsung.scsp.framework.core.listeners.Listeners;
import com.samsung.scsp.framework.core.listeners.ResponseListener;
import com.samsung.scsp.media.Media;
import com.samsung.scsp.media.api.MediaApiControlImpl;
import com.samsung.scsp.media.nde.NDEApiHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ResponseListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ApiContext e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ a(AbstractApiControl.Request request, ApiContext apiContext, Listeners listeners, int i2) {
        this.d = i2;
        this.g = request;
        this.e = apiContext;
        this.f = listeners;
    }

    public final void onResponse(Object obj) {
        switch (this.d) {
            case 0:
                ((MediaApiControlImpl.AnonymousClass2) this.g).lambda$execute$0(this.e, (Listeners) this.f, (SCHashMap) obj);
                return;
            case 1:
                ((MediaApiControlImpl.AnonymousClass4) this.g).lambda$execute$0(this.e, (Listeners) this.f, (SCHashMap) obj);
                return;
            default:
                MediaApiControlImpl.lambda$downloadNDEOriginalBinary$2(this.e, (NDEApiHelper) this.g, (Media) this.f, (JsonObject) obj);
                return;
        }
    }

    public /* synthetic */ a(ApiContext apiContext, NDEApiHelper nDEApiHelper, Media media) {
        this.d = 2;
        this.e = apiContext;
        this.g = nDEApiHelper;
        this.f = media;
    }
}
