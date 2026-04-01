package com.samsung.android.gallery.app.ui.list.search.recommendation.floating.history;

import A4.B;
import C3.p;
import Da.f;
import E5.a;
import E5.b;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.search.history.HistoryItem;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FloatingHistoryCoverItemLoader {
    private String getCoverIdList(ArrayList<HistoryItem> arrayList) {
        return TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, (Iterable) arrayList.stream().mapToLong(new b(0)).boxed().collect(Collectors.toList()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$load$2(ArrayList arrayList, Consumer consumer, ThreadPool.JobContext jobContext) {
        arrayList.forEach(new p(7, loadCoverItems(getCoverIdList(arrayList))));
        Log.s("FloatingHistoryCoverItemLoader", "History items=" + arrayList.size());
        ThreadUtil.postOnUiThread(new f(2, consumer, arrayList));
        return null;
    }

    private HashMap<Long, MediaItem> loadCoverItems(String str) {
        Cursor query;
        HashMap<Long, MediaItem> hashMap = new HashMap<>();
        try {
            query = DbCompat.query(DbKey.ALL_PICTURES, new B(str, 1));
            if (query != null) {
                if (query.moveToFirst()) {
                    do {
                        MediaItem create = MediaItemBuilder.create(query);
                        hashMap.put(Long.valueOf(create.getFileId()), create);
                    } while (query.moveToNext());
                }
            }
            if (query != null) {
                query.close();
            }
            return hashMap;
        } catch (Exception e) {
            e.printStackTrace();
            return hashMap;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void load(ArrayList<HistoryItem> arrayList, Consumer<ArrayList<HistoryItem>> consumer) {
        if (arrayList == null || arrayList.isEmpty()) {
            consumer.accept(arrayList);
        } else {
            ThreadPool.getInstance().submit(new a(this, arrayList, consumer, 0));
        }
    }
}
