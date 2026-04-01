package com.samsung.android.gallery.module.publisher;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* renamed from: com.samsung.android.gallery.module.publisher.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0621a implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumDataPublisher e;

    public /* synthetic */ C0621a(AlbumDataPublisher albumDataPublisher, int i2) {
        this.d = i2;
        this.e = albumDataPublisher;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        AlbumDataPublisher albumDataPublisher = this.e;
        switch (i2) {
            case 0:
                albumDataPublisher.publishMxAlbumsFileData(obj, bundle);
                return;
            case 1:
                albumDataPublisher.onContext(obj, bundle);
                return;
            case 2:
                albumDataPublisher.publishAlbumHideData(obj, bundle);
                return;
            case 3:
                albumDataPublisher.lambda$createSubscriberList$0(obj, bundle);
                return;
            case 4:
                albumDataPublisher.lambda$createSubscriberList$1(obj, bundle);
                return;
            case 5:
                albumDataPublisher.onThumbnailLoadDone(obj, bundle);
                return;
            case 6:
                albumDataPublisher.publishAlbumsData(obj, bundle);
                return;
            case 7:
                albumDataPublisher.publishAlbumsDataCache(obj, bundle);
                return;
            case 8:
                albumDataPublisher.publishAlbumFileData(obj, bundle);
                return;
            default:
                albumDataPublisher.publishAlbumsFileData(obj, bundle);
                return;
        }
    }
}
