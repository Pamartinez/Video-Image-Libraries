package com.samsung.android.gallery.app.controller.internals;

import com.samsung.android.gallery.app.controller.internals.EditDateAndTimeCmd;
import com.samsung.android.gallery.app.controller.internals.FixDateTimeCmd;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements FixDateTimeCmd.Updater {
    public final /* synthetic */ EditDateAndTimeCmd.DateUpdater d;
    public final /* synthetic */ MediaItem e;
    public final /* synthetic */ String f;

    public /* synthetic */ b(EditDateAndTimeCmd.DateUpdater dateUpdater, MediaItem mediaItem, String str) {
        this.d = dateUpdater;
        this.e = mediaItem;
        this.f = str;
    }

    public final boolean update(MediaItem mediaItem) {
        return this.d.lambda$run$2(this.e, this.f, mediaItem);
    }
}
