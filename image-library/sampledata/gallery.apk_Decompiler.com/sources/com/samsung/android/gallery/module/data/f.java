package com.samsung.android.gallery.module.data;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.data.MdeData;
import com.samsung.android.gallery.support.shotmode.ShotMode;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ f(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((MdeData.MdeMetadata) obj2).parse((String) obj);
                return;
            case 1:
                ((MediaUnit) obj2).lambda$setSefFileType$0((ShotMode) obj);
                return;
            case 2:
                ((CountDownLatch) obj2).countDown();
                return;
            default:
                ((QueryParams) obj).addUris((String[]) obj2);
                return;
        }
    }
}
