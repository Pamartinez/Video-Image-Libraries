package com.samsung.android.gallery.app.ui.list.search.recommendation;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.search.recommendation.RecentlyHistoryListAdapter;
import com.samsung.android.gallery.module.search.history.HistoryItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ RecentlyHistoryListAdapter d;
    public final /* synthetic */ Bitmap e;
    public final /* synthetic */ RecentlyHistoryListAdapter.ViewHolder f;
    public final /* synthetic */ HistoryItem g;

    public /* synthetic */ d(Bitmap bitmap, RecentlyHistoryListAdapter.ViewHolder viewHolder, RecentlyHistoryListAdapter recentlyHistoryListAdapter, HistoryItem historyItem) {
        this.d = recentlyHistoryListAdapter;
        this.e = bitmap;
        this.f = viewHolder;
        this.g = historyItem;
    }

    public final void run() {
        this.d.lambda$drawCreatureThumbnail$2(this.e, this.f, this.g);
    }
}
