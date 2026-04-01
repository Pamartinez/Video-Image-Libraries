package com.samsung.android.gallery.module.data;

import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaItem[] e;
    public final /* synthetic */ MediaItem f;

    public /* synthetic */ q(MediaItem[] mediaItemArr, MediaItem mediaItem, int i2) {
        this.d = i2;
        this.e = mediaItemArr;
        this.f = mediaItem;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((List) obj).forEach(new q(this.e, this.f, 4));
                return;
            case 1:
                UriItemLoader.lambda$loadLocalUri$13(this.e, this.f, (Integer) obj);
                return;
            case 2:
                ((List) obj).forEach(new q(this.e, this.f, 3));
                return;
            case 3:
                UriItemLoader.lambda$loadUri$8(this.e, this.f, (Integer) obj);
                return;
            case 4:
                UriItemLoader.lambda$loadUri$4(this.e, this.f, (Integer) obj);
                return;
            default:
                ((List) obj).forEach(new q(this.e, this.f, 1));
                return;
        }
    }
}
