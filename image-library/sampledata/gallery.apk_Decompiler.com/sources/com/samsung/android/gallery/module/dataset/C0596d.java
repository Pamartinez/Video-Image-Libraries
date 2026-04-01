package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaDataMdeTimeline;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

/* renamed from: com.samsung.android.gallery.module.dataset.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0596d implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ C0596d(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((MediaItem) obj).setBroken(true);
                return;
            case 1:
                MediaDataMdeTimeline.MdeCursorReader.lambda$new$0((MediaItem) obj);
                return;
            case 2:
                ((FolderItem) obj).removeVirtual();
                return;
            case 3:
                ((MediaDataCursor) obj).close();
                return;
            case 4:
                ((MediaDataCursor) obj).onDestroy();
                return;
            case 5:
                ((ClusterTable) obj).clearClusterIndexer();
                return;
            case 6:
                ((HashMap) obj).clear();
                return;
            case 7:
                ((CountDownLatch) obj).countDown();
                return;
            default:
                ((MediaDataStoriesCategory) obj).close();
                return;
        }
    }
}
