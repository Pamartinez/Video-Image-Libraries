package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaDataStoryHighlight;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class Q implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ArrayList e;

    public /* synthetic */ Q(ArrayList arrayList, int i2) {
        this.d = i2;
        this.e = arrayList;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        ArrayList arrayList = this.e;
        switch (i2) {
            case 0:
                MediaDataMxAlbum.lambda$loadTopAlbums$18(arrayList, (MediaItem) obj);
                return;
            default:
                MediaDataStoryHighlight.ItemCurationProcessor.lambda$makeCurationData$0(arrayList, (FileItemInterface) obj);
                return;
        }
    }
}
