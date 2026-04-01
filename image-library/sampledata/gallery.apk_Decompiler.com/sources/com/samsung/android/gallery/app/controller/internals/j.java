package com.samsung.android.gallery.app.controller.internals;

import com.samsung.android.gallery.app.controller.internals.UpdateFavoriteInListCmd;
import java.util.List;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ UpdateFavoriteInListCmd.UpdateFavoriteTask f2508a;

    public /* synthetic */ j(UpdateFavoriteInListCmd.UpdateFavoriteTask updateFavoriteTask) {
        this.f2508a = updateFavoriteTask;
    }

    public final void accept(Object obj, Object obj2) {
        this.f2508a.lambda$runInternal$2((Boolean) obj, (List) obj2);
    }
}
