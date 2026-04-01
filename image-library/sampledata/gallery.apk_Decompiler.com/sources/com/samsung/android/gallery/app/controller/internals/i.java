package com.samsung.android.gallery.app.controller.internals;

import com.samsung.android.gallery.app.controller.internals.UpdateFavoriteInListCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ UpdateFavoriteInListCmd.UpdateFavoriteTask f2507a;

    public /* synthetic */ i(UpdateFavoriteInListCmd.UpdateFavoriteTask updateFavoriteTask) {
        this.f2507a = updateFavoriteTask;
    }

    public final boolean test(Object obj) {
        return this.f2507a.filter((MediaItem) obj);
    }
}
