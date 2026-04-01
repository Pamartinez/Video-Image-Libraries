package com.samsung.android.gallery.module.album;

import android.content.Context;
import com.samsung.android.gallery.module.album.AlbumTitleHelper;
import com.samsung.android.gallery.support.utils.StorageInfo;
import java.util.HashMap;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ HashMap e;
    public final /* synthetic */ Context f;

    public /* synthetic */ d(HashMap hashMap, Context context, int i2) {
        this.d = i2;
        this.e = hashMap;
        this.f = context;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                AlbumTitleHelper.AlbumNameHolder.lambda$initialize$0(this.e, this.f, (StorageInfo) obj);
                return;
            default:
                AlbumTitleHelper.AlbumNameHolder.lambda$initialize$1(this.e, this.f, (StorageInfo) obj);
                return;
        }
    }
}
