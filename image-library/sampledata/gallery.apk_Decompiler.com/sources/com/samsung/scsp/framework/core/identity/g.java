package com.samsung.scsp.framework.core.identity;

import com.google.gson.JsonArray;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Consumer {
    public final /* synthetic */ JsonArray d;

    public /* synthetic */ g(JsonArray jsonArray) {
        this.d = jsonArray;
    }

    public final void accept(Object obj) {
        PushInfoList.lambda$toJsonArray$0(this.d, (PushInfo) obj);
    }
}
