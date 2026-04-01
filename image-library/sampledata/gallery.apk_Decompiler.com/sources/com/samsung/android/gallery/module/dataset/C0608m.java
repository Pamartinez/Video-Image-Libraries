package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaDataStoryHighlight;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.util.function.Function;

/* renamed from: com.samsung.android.gallery.module.dataset.m  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0608m implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2984a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C0608m(int i2, Object obj) {
        this.f2984a = i2;
        this.b = obj;
    }

    public final Object apply(Object obj) {
        int i2 = this.f2984a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return ((MediaDataCollection) obj2).lambda$read$3((Integer) obj);
            case 1:
                return ((MediaDataMdeSpace) obj2).lambda$getChildMediaData$0((String) obj);
            case 2:
                return ((MediaDataSearch) obj2).getMediaDataSearchCategory((String) obj);
            case 3:
                return MediaDataFactory.lambda$getInstance$0((Blackboard) obj2, (String) obj);
            case 4:
                return ((MediaDataMxAlbum) obj2).lambda$onCreate$0((String) obj);
            case 5:
                return ((MediaDataRemasterV2) obj2).lambda$loadItems$3((Integer) obj);
            case 6:
                return ((MediaDataSearchScreenshotPictures) obj2).lambda$loadSubScenes$0((String) obj);
            case 7:
                return ((MediaDataSearchStories) obj2).openChildData((String) obj);
            case 8:
                return ((MediaDataStoriesV7) obj2).lambda$computeChildMediaData$11((String) obj);
            case 9:
                return ((MediaDataStoryHighlight.ItemCurationProcessor) obj2).buildEffectItem((MediaItem) obj);
            default:
                return ((ScreenShotFilterTitle) obj2).lambda$loadTitle$0((String) obj);
        }
    }
}
