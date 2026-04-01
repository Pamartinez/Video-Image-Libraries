package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class Y implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaDataQuickView e;

    public /* synthetic */ Y(MediaDataQuickView mediaDataQuickView, int i2) {
        this.d = i2;
        this.e = mediaDataQuickView;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        MediaDataQuickView mediaDataQuickView = this.e;
        MediaItem mediaItem = (MediaItem) obj;
        switch (i2) {
            case 0:
                mediaDataQuickView.onPppUpdate(mediaItem);
                return;
            default:
                mediaDataQuickView.lambda$preparePppObserver$0(mediaItem);
                return;
        }
    }
}
