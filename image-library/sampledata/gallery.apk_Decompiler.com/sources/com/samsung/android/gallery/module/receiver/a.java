package com.samsung.android.gallery.module.receiver;

import android.content.Context;
import android.net.Uri;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ AbsContentObserver d;
    public final /* synthetic */ Context e;

    public /* synthetic */ a(Context context, AbsContentObserver absContentObserver) {
        this.d = absContentObserver;
        this.e = context;
    }

    public final void accept(Object obj) {
        this.d.lambda$registerObserver$0(this.e, (Uri) obj);
    }
}
