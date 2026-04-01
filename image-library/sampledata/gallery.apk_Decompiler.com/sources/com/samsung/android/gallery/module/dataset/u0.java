package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import com.samsung.android.gallery.module.dataset.tables.DataTable;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class u0 implements Runnable {
    public final /* synthetic */ MediaDataStoryHighlight d;
    public final /* synthetic */ Cursor[] e;
    public final /* synthetic */ DataTable f;
    public final /* synthetic */ ArrayList g;

    public /* synthetic */ u0(MediaDataStoryHighlight mediaDataStoryHighlight, Cursor[] cursorArr, DataTable dataTable, ArrayList arrayList) {
        this.d = mediaDataStoryHighlight;
        this.e = cursorArr;
        this.f = dataTable;
        this.g = arrayList;
    }

    public final void run() {
        this.d.lambda$swap$0(this.e, this.f, this.g);
    }
}
