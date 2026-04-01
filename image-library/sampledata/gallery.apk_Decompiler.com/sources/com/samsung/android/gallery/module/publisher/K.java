package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;
import java.util.LinkedHashMap;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class K implements Consumer {
    public final /* synthetic */ SearchDataPublisher d;
    public final /* synthetic */ Cursor e;
    public final /* synthetic */ int f;

    public /* synthetic */ K(SearchDataPublisher searchDataPublisher, Cursor cursor, int i2) {
        this.d = searchDataPublisher;
        this.e = cursor;
        this.f = i2;
    }

    public final void accept(Object obj) {
        this.d.lambda$publishFilterAndClusterResultsFrom$13(this.e, this.f, (LinkedHashMap) obj);
    }
}
