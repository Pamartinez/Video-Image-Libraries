package com.samsung.android.gallery.app.activity.external;

import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ LinkViewNavController d;
    public final /* synthetic */ Bundle e;
    public final /* synthetic */ int f;
    public final /* synthetic */ String g;

    public /* synthetic */ b(LinkViewNavController linkViewNavController, Bundle bundle, int i2, String str) {
        this.d = linkViewNavController;
        this.e = bundle;
        this.f = i2;
        this.g = str;
    }

    public final void run() {
        this.d.lambda$onNavigatorCreated$0(this.e, this.f, this.g);
    }
}
