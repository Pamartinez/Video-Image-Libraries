package com.samsung.android.gallery.app.ui.list.stories.category.ondemand;

import M9.e;
import W8.C0579a;
import a6.d;
import a6.f;
import a6.g;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.text.TextUtils;
import androidx.core.util.Consumer;
import androidx.core.util.Function;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.cmh.executor.CmhQueryExecutor;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.widget.animations.photostacking.ImageInfo;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnDemandLoader {
    private final String TAG = "OnDemandLoader";
    private final List<Function<Integer, Cursor>> mValidCursorSuppliers = List.of(new d(this), new Object());

    private Cursor getDataCursor() {
        for (Function<Integer, Cursor> apply : this.mValidCursorSuppliers) {
            Cursor cursor = (Cursor) apply.apply(20);
            if (cursor != null && cursor.getCount() >= 20) {
                return cursor;
            }
            Optional.ofNullable(cursor).ifPresent(new C0579a(7));
        }
        Cursor query = DbCompat.query(new QueryParams(DbKey.ALL_PICTURES).setOrder("random()").setLimit(20));
        if (query != null && query.getCount() >= 5) {
            return query;
        }
        Optional.ofNullable(query).ifPresent(new C0579a(7));
        return null;
    }

    /* access modifiers changed from: private */
    public Cursor getStoryDataCursor(int i2) {
        Cursor rawQuery = new CmhQueryExecutor().rawQuery(C0212a.j(i2, "SELECT f.media_id FROM files f WHERE (f.is_hide = 0 OR f.is_hide IS NULL) AND (media_type IS NOT null) AND f._id IN (SELECT DISTINCT sm.fk_file_id FROM story_map sm ORDER BY random() LIMIT ", ")"), "getDataCursor");
        if (rawQuery != null) {
            try {
                if (rawQuery.getCount() > 0) {
                    ArrayList arrayList = new ArrayList();
                    while (rawQuery.moveToNext()) {
                        arrayList.add(Long.valueOf(rawQuery.getLong(0)));
                    }
                    Cursor query = DbCompat.query(new QueryParams(DbKey.ALL_PICTURES).setMediaIds(TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList)).setOrder("random()").setLimit(i2));
                    rawQuery.close();
                    return query;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (rawQuery != null) {
            rawQuery.close();
        }
        return null;
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadImageInfo$1(List list, String str, long j2, Consumer consumer) {
        Cursor dataCursor = getDataCursor();
        if (dataCursor != null) {
            while (dataCursor.moveToNext()) {
                try {
                    list.add(MediaItemLoader.load(dataCursor));
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            Log.d("OnDemandLoader", "end loadData " + Logger.vt(Integer.valueOf(list.size()), str, Long.valueOf(j2)));
            loadThumbnails(str, new ArrayList(list), consumer);
        } else {
            Log.e((CharSequence) "OnDemandLoader", "no data", str);
            consumer.accept(null);
        }
        if (dataCursor != null) {
            dataCursor.close();
            return;
        }
        return;
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadThumbnails$3(List list, MediaItem mediaItem, AtomicInteger atomicInteger, List list2, String str, long j2, Consumer consumer, Bitmap bitmap) {
        if (bitmap != null) {
            list.add(new ImageInfo(bitmap, mediaItem));
        }
        if (atomicInteger.getAndIncrement() == list2.size() - 1) {
            Log.d("OnDemandLoader", "end loadThumbnails " + Logger.vt(Integer.valueOf(list2.size()), Integer.valueOf(list.size()), str, Long.valueOf(j2)));
            consumer.accept(new ArrayList(list));
        }
    }

    private void loadThumbnails(String str, List<MediaItem> list, Consumer<ArrayList<ImageInfo>> consumer) {
        Log.d("OnDemandLoader", "start loadThumbnails", Integer.valueOf(list.size()), str);
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (MediaItem next : list) {
            loadThumbnail(next, ThumbKind.MEDIUM_KIND, new f(this, arrayList, next, atomicInteger, list, str, currentTimeMillis, consumer));
        }
    }

    public void loadImageInfo(String str, Consumer<ArrayList<ImageInfo>> consumer) {
        Log.d("OnDemandLoader", "start loadData", str);
        long currentTimeMillis = System.currentTimeMillis();
        SimpleThreadPool.getInstance().submit(new e(this, new ArrayList(), str, currentTimeMillis, (Consumer) consumer));
    }

    public void loadThumbnail(MediaItem mediaItem, ThumbKind thumbKind, Consumer<Bitmap> consumer) {
        Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            consumer.accept(memCache);
            return;
        }
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        Objects.requireNonNull(mediaItem);
        instance.loadThumbnail(mediaItem, thumbKind, new B8.e(mediaItem, 1), new g(0, consumer));
    }
}
