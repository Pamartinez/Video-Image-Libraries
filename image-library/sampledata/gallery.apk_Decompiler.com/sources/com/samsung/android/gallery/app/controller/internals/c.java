package com.samsung.android.gallery.app.controller.internals;

import com.samsung.android.gallery.app.controller.internals.EditDateAndTimeCmd;
import com.samsung.android.gallery.app.controller.internals.FixDateTimeCmd;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements FixDateTimeCmd.Updater {
    public final /* synthetic */ EditDateAndTimeCmd.DateUpdater d;
    public final /* synthetic */ String[] e;

    public /* synthetic */ c(EditDateAndTimeCmd.DateUpdater dateUpdater, String[] strArr) {
        this.d = dateUpdater;
        this.e = strArr;
    }

    public final boolean update(MediaItem mediaItem) {
        return this.d.lambda$run$3(this.e, mediaItem);
    }
}
