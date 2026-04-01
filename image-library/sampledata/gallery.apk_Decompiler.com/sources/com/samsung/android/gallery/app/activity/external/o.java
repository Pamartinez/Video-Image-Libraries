package com.samsung.android.gallery.app.activity.external;

import android.content.Context;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements Consumer {
    public final /* synthetic */ SharingsViewNavigatorController d;
    public final /* synthetic */ int e;
    public final /* synthetic */ MediaItem[] f;

    public /* synthetic */ o(SharingsViewNavigatorController sharingsViewNavigatorController, int i2, MediaItem[] mediaItemArr) {
        this.d = sharingsViewNavigatorController;
        this.e = i2;
        this.f = mediaItemArr;
    }

    public final void accept(Object obj) {
        this.d.lambda$launchSharingChoice$2(this.e, this.f, (Context) obj);
    }
}
