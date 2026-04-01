package com.samsung.android.gallery.module.album;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ String d;

    public /* synthetic */ b(String str) {
        this.d = str;
    }

    public final void accept(Object obj) {
        ((QueryParams) obj).setFilePath(this.d);
    }
}
