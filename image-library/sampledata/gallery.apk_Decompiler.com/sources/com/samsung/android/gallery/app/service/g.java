package com.samsung.android.gallery.app.service;

import com.samsung.android.gallery.app.service.FileOpService;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ g(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((FileOpData) obj2).lambda$moveToTargetAlbum$3((QueryParams) obj);
                return;
            default:
                ((FileOpService.DialogDelegate) obj2).update(((Float) obj).floatValue());
                return;
        }
    }
}
