package com.samsung.android.gallery.app.activity.external;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class s implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ s(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        ((QueryParams) obj).setParentAlbumId(this.d);
    }
}
