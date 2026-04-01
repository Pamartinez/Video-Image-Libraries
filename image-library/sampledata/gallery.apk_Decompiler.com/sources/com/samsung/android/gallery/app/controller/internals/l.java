package com.samsung.android.gallery.app.controller.internals;

import android.content.Context;
import com.samsung.android.gallery.app.controller.internals.UpdateFavoriteInListCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Consumer {
    public final /* synthetic */ UpdateFavoriteInListCmd.UpdateFavoriteTask d;
    public final /* synthetic */ Context e;

    public /* synthetic */ l(UpdateFavoriteInListCmd.UpdateFavoriteTask updateFavoriteTask, Context context) {
        this.d = updateFavoriteTask;
        this.e = context;
    }

    public final void accept(Object obj) {
        this.d.lambda$runInternal$1(this.e, (MediaItem) obj);
    }
}
