package com.samsung.android.gallery.module.publisher;

import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class S implements Runnable {
    public final /* synthetic */ SearchDataPublisher d;
    public final /* synthetic */ String e;
    public final /* synthetic */ Bundle f;
    public final /* synthetic */ int g;

    public /* synthetic */ S(SearchDataPublisher searchDataPublisher, String str, Bundle bundle, int i2) {
        this.d = searchDataPublisher;
        this.e = str;
        this.f = bundle;
        this.g = i2;
    }

    public final void run() {
        this.d.lambda$publish$0(this.e, this.f, this.g);
    }
}
