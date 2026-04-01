package com.samsung.android.gallery.app.controller.internals;

import com.samsung.android.gallery.app.controller.internals.EditDateAndTimeCmd;
import com.samsung.android.gallery.app.controller.internals.EditDateAndTimeCmd2;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.Comparator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Comparator {
    public final /* synthetic */ int d;

    public /* synthetic */ a(int i2) {
        this.d = i2;
    }

    public final int compare(Object obj, Object obj2) {
        MediaItem mediaItem = (MediaItem) obj;
        MediaItem mediaItem2 = (MediaItem) obj2;
        switch (this.d) {
            case 0:
                return EditDateAndTimeCmd.DateUpdater.lambda$run$0(mediaItem, mediaItem2);
            default:
                return EditDateAndTimeCmd2.DateUpdater.lambda$initDateTime$2(mediaItem, mediaItem2);
        }
    }
}
