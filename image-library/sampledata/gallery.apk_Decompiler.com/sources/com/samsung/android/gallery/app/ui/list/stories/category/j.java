package com.samsung.android.gallery.app.ui.list.stories.category;

import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaItem e;

    public /* synthetic */ j(MediaItem mediaItem, int i2) {
        this.d = i2;
        this.e = mediaItem;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        MediaItem mediaItem = this.e;
        StoriesCategory2Header storiesCategory2Header = (StoriesCategory2Header) obj;
        switch (i2) {
            case 0:
                storiesCategory2Header.onHeaderCatItemSelect(mediaItem);
                return;
            default:
                storiesCategory2Header.onHeaderCatItemSelect(mediaItem);
                return;
        }
    }
}
