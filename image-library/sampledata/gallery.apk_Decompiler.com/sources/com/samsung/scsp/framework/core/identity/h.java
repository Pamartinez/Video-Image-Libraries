package com.samsung.scsp.framework.core.identity;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Consumer {
    public final /* synthetic */ ArrayList d;
    public final /* synthetic */ Gson e;

    public /* synthetic */ h(ArrayList arrayList, Gson gson) {
        this.d = arrayList;
        this.e = gson;
    }

    public final void accept(Object obj) {
        this.d.add((PushInfo) this.e.fromJson(((JsonElement) obj).toString(), PushInfo.class));
    }
}
