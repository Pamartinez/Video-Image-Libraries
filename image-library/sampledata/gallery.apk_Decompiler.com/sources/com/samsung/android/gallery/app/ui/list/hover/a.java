package com.samsung.android.gallery.app.ui.list.hover;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;

    public /* synthetic */ a(int i2, int i7) {
        this.d = i7;
        this.e = i2;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        int i7 = this.e;
        QueryParams queryParams = (QueryParams) obj;
        switch (i2) {
            case 0:
                queryParams.addAlbumId(i7);
                return;
            default:
                queryParams.addAlbumId(i7);
                return;
        }
    }
}
