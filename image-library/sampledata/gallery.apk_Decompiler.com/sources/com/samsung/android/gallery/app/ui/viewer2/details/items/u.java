package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class u implements Consumer {
    public final /* synthetic */ DetailsItemLocation d;
    public final /* synthetic */ MediaItem e;
    public final /* synthetic */ Intent f;

    public /* synthetic */ u(DetailsItemLocation detailsItemLocation, MediaItem mediaItem, Intent intent) {
        this.d = detailsItemLocation;
        this.e = mediaItem;
        this.f = intent;
    }

    public final void accept(Object obj) {
        this.d.lambda$openNavigation$4(this.e, this.f, (ResolveInfo) obj);
    }
}
