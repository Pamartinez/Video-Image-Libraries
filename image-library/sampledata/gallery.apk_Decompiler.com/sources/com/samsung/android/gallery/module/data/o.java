package com.samsung.android.gallery.module.data;

import android.net.Uri;
import android.util.Pair;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.data.StoriesPinData;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ o(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((StoriesPinData.Reminder) this.e).lambda$getYearIndex$0((ConcurrentHashMap) this.f, (Integer) obj);
                return;
            case 1:
                ((QueryParams) obj).setGroupTypes((GroupType) this.e).setGroupMediaFilter(((FileItemInterface) this.f).getBucketID(), ((FileItemInterface) this.f).getGroupMediaId());
                return;
            case 2:
                MediaCacheLoader.lambda$computeRemasterTags$0((MediaCache) this.e, (ArrayList) this.f, (Pair) obj);
                return;
            case 3:
                MediaItemLoader.lambda$flatten$1((Predicate) this.e, (ArrayList) this.f, (MediaItem) obj);
                return;
            case 4:
                MediaItemRetryLoader.lambda$loadSync$1((MediaItem[]) this.e, (CountDownLatch) this.f, (MediaItem) obj);
                return;
            case 5:
                ((MediaItemRetryLoader) this.e).lambda$query$3((Uri) this.f, (QueryParams) obj);
                return;
            default:
                ((StoriesPinData) this.e).lambda$trimData$0((List) this.f, (Integer) obj);
                return;
        }
    }
}
