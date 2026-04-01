package com.samsung.scsp.media;

import com.samsung.scsp.common.Holder;
import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.listeners.ResponseListener;
import com.samsung.scsp.media.Files;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ResponseListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ApiContext e;
    public final /* synthetic */ Holder f;

    public /* synthetic */ b(ApiContext apiContext, Holder holder, int i2) {
        this.d = i2;
        this.e = apiContext;
        this.f = holder;
    }

    public final void onResponse(Object obj) {
        switch (this.d) {
            case 0:
                Files.AnonymousClass1.lambda$read$0(this.e, this.f, (MediaList) obj);
                return;
            default:
                Files.AnonymousClass2.lambda$read$0(this.e, this.f, (MediaList) obj);
                return;
        }
    }
}
