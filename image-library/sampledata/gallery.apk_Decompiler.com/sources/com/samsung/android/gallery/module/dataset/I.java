package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaDataList;
import com.samsung.android.gallery.module.dataset.MediaDataRemasterV2;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class I implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ I(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((MediaDataList.MediaDataArray) this.e).lambda$updateList$2((String) this.f, (QueryParams) obj);
                return;
            case 1:
                ((ThumbnailLoader) this.e).loadThumbnail((MediaItem) obj, ThumbKind.MEDIUM_KIND, (C0612q) this.f);
                return;
            case 2:
                ((MediaDataRemasterV2) this.e).lambda$loadFirst$6((MediaDataRemasterV2.DataInfo) this.f, (Integer) obj);
                return;
            case 3:
                ((MediaDataRemasterV2.MediaDataChild) obj).mData = ((MediaDataRemasterV2.DataInfo) this.e).mChildData.get((Integer) this.f);
                return;
            case 4:
                ((MediaDataSearchCreatureCategory) this.e).lambda$checkTop5Validity$0((ArrayList) this.f, (String) obj);
                return;
            default:
                ((MediaDataStoriesV7) this.e).lambda$swapChildMediaData$10((HashMap) this.f, (String) obj);
                return;
        }
    }
}
