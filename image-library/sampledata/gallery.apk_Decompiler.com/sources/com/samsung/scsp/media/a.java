package com.samsung.scsp.media;

import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.listeners.ResponseListener;
import com.samsung.scsp.media.Extended;
import com.samsung.scsp.media.Trash;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ResponseListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ApiContext e;
    public final /* synthetic */ Object[] f;

    public /* synthetic */ a(ApiContext apiContext, Object[] objArr, int i2) {
        this.d = i2;
        this.e = apiContext;
        this.f = objArr;
    }

    public final void onResponse(Object obj) {
        switch (this.d) {
            case 0:
                Extended.AnonymousClass1.lambda$read$0(this.e, (MediaExtendedList[]) this.f, (MediaExtendedList) obj);
                return;
            default:
                Trash.AnonymousClass1.lambda$read$0(this.e, (MediaList[]) this.f, (MediaList) obj);
                return;
        }
    }
}
