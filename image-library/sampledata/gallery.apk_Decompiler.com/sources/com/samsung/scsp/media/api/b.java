package com.samsung.scsp.media.api;

import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.api.SCHashMap;
import com.samsung.scsp.framework.core.listeners.Listeners;
import com.samsung.scsp.framework.core.listeners.ResponseListener;
import com.samsung.scsp.media.api.MediaApiControlImpl;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ResponseListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ApiContext e;
    public final /* synthetic */ SCHashMap f;
    public final /* synthetic */ Listeners g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f4222h;

    public /* synthetic */ b(Object obj, ApiContext apiContext, SCHashMap sCHashMap, Listeners listeners, int i2) {
        this.d = i2;
        this.f4222h = obj;
        this.e = apiContext;
        this.f = sCHashMap;
        this.g = listeners;
    }

    public final void onResponse(Object obj) {
        switch (this.d) {
            case 0:
                ((MediaApiControlImpl.AnonymousClass3) this.f4222h).lambda$execute$0(this.e, this.f, this.g, (SCHashMap) obj);
                return;
            default:
                ((MediaApiControlImpl) this.f4222h).lambda$handleResumableUpdate$0(this.e, this.f, this.g, (SCHashMap) obj);
                return;
        }
    }
}
