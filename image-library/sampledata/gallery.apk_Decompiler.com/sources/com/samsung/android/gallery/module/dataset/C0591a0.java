package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Predicate;

/* renamed from: com.samsung.android.gallery.module.dataset.a0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0591a0 implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2966a;
    public final /* synthetic */ MediaItem b;

    public /* synthetic */ C0591a0(MediaItem mediaItem, int i2) {
        this.f2966a = i2;
        this.b = mediaItem;
    }

    public final boolean test(Object obj) {
        int i2 = this.f2966a;
        MediaItem mediaItem = this.b;
        MediaItem mediaItem2 = (MediaItem) obj;
        switch (i2) {
            case 0:
                return MediaDataRemasterV2.lambda$updateMainItem$9(mediaItem, mediaItem2);
            case 1:
                return PppUpdater.lambda$onUpdatePppMediaItem$2(mediaItem, mediaItem2);
            case 2:
                return PppUpdater.lambda$onUpdatePppMediaItem$3(mediaItem, mediaItem2);
            default:
                return PppUpdater.lambda$onUpdatePppMediaItem$4(mediaItem, mediaItem2);
        }
    }
}
