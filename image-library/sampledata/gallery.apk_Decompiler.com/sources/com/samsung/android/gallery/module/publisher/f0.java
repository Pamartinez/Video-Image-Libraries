package com.samsung.android.gallery.module.publisher;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f0 implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ VirtualAlbumDataPublisher e;

    public /* synthetic */ f0(VirtualAlbumDataPublisher virtualAlbumDataPublisher, int i2) {
        this.d = i2;
        this.e = virtualAlbumDataPublisher;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        VirtualAlbumDataPublisher virtualAlbumDataPublisher = this.e;
        switch (i2) {
            case 0:
                virtualAlbumDataPublisher.publishVideoData(obj, bundle);
                return;
            case 1:
                virtualAlbumDataPublisher.publishFavoriteData(obj, bundle);
                return;
            case 2:
                virtualAlbumDataPublisher.publishRecentlyData(obj, bundle);
                return;
            case 3:
                virtualAlbumDataPublisher.publishRepairData(obj, bundle);
                return;
            default:
                virtualAlbumDataPublisher.publishActionAlbumViewPicturesData(obj, bundle);
                return;
        }
    }
}
