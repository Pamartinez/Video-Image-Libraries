package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaDataMdeSpace;
import com.samsung.android.gallery.module.dataset.tables.ClusterIndexer;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import java.io.File;
import java.util.Vector;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class K implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2949a;

    public /* synthetic */ K(int i2) {
        this.f2949a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f2949a) {
            case 0:
                return Long.valueOf(((MediaItem) ((MediaItem) obj)).getFileId());
            case 1:
                return MediaDataMdeSpace.lambda$loadData$7((String) obj);
            case 2:
                return MediaDataMdeSpace.lambda$loadData$8((String) obj);
            case 3:
                return MediaDataMdeSpace.MediaDataChild.lambda$getData$0((String) obj);
            case 4:
                return MediaDataTimeline.lambda$getTableLog$14((ClusterIndexer) obj);
            case 5:
                return ((File) obj).getAbsolutePath();
            case 6:
                return LocalAlbumDBUpdater.lambda$getGroupHashValue$3((MediaItem) obj);
            case 7:
                return LocalAlbumDBUpdater.lambda$getGroupHashValue$2((MediaItem) obj);
            case 8:
                return MediaItemMde.getGroupId((MediaItem) obj);
            case 9:
                return MediaDataMxAlbum.lambda$cleanUpList$15((MediaItem) obj);
            case 10:
                return MediaDataMxAlbum.lambda$buildMergedNameList$11((Integer) obj);
            case 11:
                return MediaDataMxAlbum.lambda$updateMergeNameAlbumForHide$16((Integer) obj);
            case 12:
                return MediaDataMxAlbum.lambda$buildMergedNameList$12((Integer) obj);
            case 13:
                return ((MediaData) obj).getChildMediaData("location://sharing/albums/spaces");
            case 14:
                return new Vector(((Integer) obj).intValue());
            case 15:
                return Long.valueOf(Long.parseLong(((String) obj).trim()));
            case 16:
                return IdentityCreatureUtil.createWithUnifiedId(Long.parseLong((String) obj), IdentityCreatureUtil.Category.PEOPLE);
            case 17:
                return Long.valueOf(((MediaItem) ((MediaItem) obj)).getFileId());
            case 18:
                return MediaDataStoriesV7.lambda$loadDataFull$2((String) obj);
            case 19:
                return MediaDataStoriesV7.lambda$loadDataPartial$6((String) obj);
            case 20:
                return MediaDataStoriesV7.lambda$setupCategories$9((String) obj);
            case 21:
                return Boolean.valueOf(MediaItemStory.isRecap((FileItemInterface) obj));
            case 22:
                return MediaDataStoriesV7.lambda$indexingPosition$5((String) obj);
            default:
                return Long.valueOf(((MediaItem) ((MediaItem) obj)).getFileId());
        }
    }
}
