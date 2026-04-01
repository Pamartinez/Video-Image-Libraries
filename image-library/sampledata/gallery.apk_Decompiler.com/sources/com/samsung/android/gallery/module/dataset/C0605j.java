package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.album.AlbumNewLabelUpdater;
import com.samsung.android.gallery.module.myquery.MyQueryUtil;
import java.util.ArrayList;

/* renamed from: com.samsung.android.gallery.module.dataset.j  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0605j implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ArrayList e;

    public /* synthetic */ C0605j(ArrayList arrayList, int i2) {
        this.d = i2;
        this.e = arrayList;
    }

    public final void run() {
        int i2 = this.d;
        ArrayList arrayList = this.e;
        switch (i2) {
            case 0:
                AlbumNewLabelUpdater.getInstance().update(arrayList);
                return;
            default:
                MyQueryUtil.updateTop5List(arrayList);
                return;
        }
    }
}
