package com.samsung.android.gallery.module.data;

import android.util.Pair;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import i.C0212a;
import java.util.Map;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2931a;

    public /* synthetic */ j(int i2) {
        this.f2931a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f2931a) {
            case 0:
                return C0212a.l("\nCloudServerPath=", (String) obj);
            case 1:
                return CreatureData.lambda$static$0((String) obj);
            case 2:
                return DetailsData.lambda$static$0((String) obj);
            case 3:
                return DynamicViewData.lambda$static$0((String) obj);
            case 4:
                return Long.valueOf(((MediaItem) obj).getGroupMediaId());
            case 5:
                return MdeData.lambda$static$12((String) obj);
            case 6:
                return MediaCacheLoader.lambda$buildRemasterTags$2((Pair) obj);
            case 7:
                return MediaItemUtil.lambda$getAllChildAlbums$0((AlbumType) obj);
            case 8:
                return MediaItemUtil.lambda$getAllChildAlbums$1((Map.Entry) obj);
            case 9:
                return MediaItemUtil.lambda$fetchChildAlbums$2((AlbumType) obj);
            case 10:
                return StoryData.lambda$static$0((String) obj);
            case 11:
                return TrashData.lambda$static$0((String) obj);
            case 12:
                return UriItemLoader.lambda$loadUri$1((String) obj);
            case 13:
                return UriItemLoader.lambda$loadUri$2((String) obj);
            default:
                return UriItemLoader.lambda$loadUri$3((String) obj);
        }
    }
}
