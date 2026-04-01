package com.samsung.android.gallery.module.fileio.database.helper;

import com.samsung.android.gallery.database.dal.mp.helper.TagEditApi;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ArrayList e;
    public final /* synthetic */ long f;

    public /* synthetic */ b(ArrayList arrayList, int i2, long j2) {
        this.d = i2;
        this.e = arrayList;
        this.f = j2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((ArrayList) obj).forEach(new b(this.e, 1, this.f));
                return;
            default:
                this.e.add(TagEditApi.buildContentValues(this.f, (String) obj));
                return;
        }
    }
}
