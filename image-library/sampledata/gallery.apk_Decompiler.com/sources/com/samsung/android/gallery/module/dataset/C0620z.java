package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.tables.DataTable;
import java.io.Closeable;

/* renamed from: com.samsung.android.gallery.module.dataset.z  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0620z implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Cloneable f;
    public final /* synthetic */ Closeable g;

    public /* synthetic */ C0620z(Object obj, Cloneable cloneable, Closeable closeable, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = cloneable;
        this.g = closeable;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((MediaDataEntire) this.e).lambda$swap$2((Cursor[]) this.f, (DataTable) this.g);
                return;
            default:
                PppUpdater.lambda$onUpdatePppMediaItem$5((String) this.e, (MediaItem) this.f, (MediaDataRef) this.g);
                return;
        }
    }
}
