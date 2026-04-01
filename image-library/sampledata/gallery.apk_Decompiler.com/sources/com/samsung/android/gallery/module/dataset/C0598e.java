package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import java.util.function.Predicate;

/* renamed from: com.samsung.android.gallery.module.dataset.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0598e implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2971a;

    public /* synthetic */ C0598e(int i2) {
        this.f2971a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f2971a) {
            case 0:
                return MediaDataAlbum.lambda$reuseCachedData$3((MediaItem) obj);
            case 1:
                return MediaDataMdeTrash.lambda$createSubscriberList$0((SubscriberInfo) obj);
            case 2:
                return MediaDataSearch.lambda$removeIfDisabled$5((String) obj);
            case 3:
                return LocalAlbumDBUpdater.lambda$getGroupHashValue$1((MediaItem) obj);
            case 4:
                return MdeGroupHelper.isSAFamilyGroupId((String) obj);
            default:
                return ((MediaData.OnDataChangeListener) obj).isInstant();
        }
    }
}
