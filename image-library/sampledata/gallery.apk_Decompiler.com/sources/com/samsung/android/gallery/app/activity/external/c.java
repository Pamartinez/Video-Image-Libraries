package com.samsung.android.gallery.app.activity.external;

import java.util.function.Consumer;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ LinkViewNavController d;
    public final /* synthetic */ String e;

    public /* synthetic */ c(LinkViewNavController linkViewNavController, String str) {
        this.d = linkViewNavController;
        this.e = str;
    }

    public final void accept(Object obj) {
        this.d.lambda$onNavigatorCreated$1(this.e, (JSONObject) obj);
    }
}
