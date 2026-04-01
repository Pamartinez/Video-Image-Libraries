package com.samsung.android.gallery.module.creature.people;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ long d;

    public /* synthetic */ a(long j2) {
        this.d = j2;
    }

    public final void accept(Object obj) {
        ((QueryParams) obj).setFileId(this.d);
    }
}
