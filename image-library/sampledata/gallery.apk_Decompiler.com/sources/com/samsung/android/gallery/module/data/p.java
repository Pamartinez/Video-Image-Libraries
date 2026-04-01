package com.samsung.android.gallery.module.data;

import android.net.Uri;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.GroupType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ArrayList e;

    public /* synthetic */ p(ArrayList arrayList, int i2) {
        this.d = i2;
        this.e = arrayList;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        ArrayList arrayList = this.e;
        switch (i2) {
            case 0:
                UriItemLoader.lambda$loadFromContentUris$19(arrayList, (String[]) obj);
                return;
            case 1:
                ((QueryParams) obj).addUris((Collection<Uri>) arrayList).addDataStamp();
                return;
            default:
                ((QueryParams) obj).addGroupType(GroupType.SINGLE_TAKEN).addUris((Collection<Uri>) arrayList);
                return;
        }
    }
}
