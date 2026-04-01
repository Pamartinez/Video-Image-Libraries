package com.samsung.android.gallery.app.controller.album;

import android.net.Uri;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.GroupType;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ List d;

    public /* synthetic */ e(List list) {
        this.d = list;
    }

    public final void accept(Object obj) {
        ((QueryParams) obj).setDbKey(DbKey.ALL_PICTURES).setGroupTypes(GroupType.BURST, GroupType.SINGLE_TAKEN).addUris((Collection<Uri>) this.d);
    }
}
