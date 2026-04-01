package com.samsung.android.gallery.module.remotegallery;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ long e;

    public /* synthetic */ a(long j2, int i2) {
        this.d = i2;
        this.e = j2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((QueryParams) obj).setMinFileId(this.e);
                return;
            case 1:
                ((QueryParams) obj).setFileId(this.e);
                return;
            default:
                ((QueryParams) obj).setFileId(this.e);
                return;
        }
    }
}
