package com.samsung.android.gallery.module.dataset;

import android.content.Context;
import android.database.MatrixCursor;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaDataMdeTimeline;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class O implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ O(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((MediaDataMdeTimeline) obj2).lambda$new$0((MediaItem) obj);
                return;
            case 1:
                ((MediaDataMdeTimeline.MdeCursorReader) obj).setDataUpdater((Consumer) obj2);
                return;
            case 2:
                ((MediaDataRef) obj2).lambda$unregisterPppObserver$0((Context) obj);
                return;
            case 3:
                ((MatrixCursor) obj2).addRow(new Object[]{(Long) obj});
                return;
            default:
                ((MediaDataTrash) obj2).lambda$new$0((Integer) obj);
                return;
        }
    }
}
