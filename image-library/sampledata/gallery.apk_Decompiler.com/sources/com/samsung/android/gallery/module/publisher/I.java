package com.samsung.android.gallery.module.publisher;

import com.samsung.android.gallery.module.myquery.SearchMyQuery;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class I implements ThreadPool.Job {
    public final Object run(ThreadPool.JobContext jobContext) {
        return SearchMyQuery.getInstance().getMyQueryCursor();
    }
}
