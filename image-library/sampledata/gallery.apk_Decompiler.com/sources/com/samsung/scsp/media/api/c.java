package com.samsung.scsp.media.api;

import com.google.gson.JsonObject;
import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.listeners.ResponseListener;
import com.samsung.scsp.media.api.constant.MediaApiContract;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ResponseListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ApiContext e;

    public /* synthetic */ c(ApiContext apiContext, int i2) {
        this.d = i2;
        this.e = apiContext;
    }

    public final void onResponse(Object obj) {
        int i2 = this.d;
        ApiContext apiContext = this.e;
        JsonObject jsonObject = (JsonObject) obj;
        switch (i2) {
            case 0:
                apiContext.parameters.put("url", jsonObject.get(MediaApiContract.Parameter.DOWNLOAD_URL).getAsString());
                return;
            default:
                apiContext.parameters.put("url", jsonObject.get("url").getAsString());
                return;
        }
    }
}
