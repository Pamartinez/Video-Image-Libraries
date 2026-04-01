package com.samsung.android.gallery.app.ui.list.search.recommendation;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.search.recommendation.RecentlyHistoryListAdapter;
import com.samsung.android.gallery.module.search.history.HistoryItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ RecentlyHistoryListAdapter d;
    public final /* synthetic */ RecentlyHistoryListAdapter.ViewHolder e;
    public final /* synthetic */ HistoryItem f;

    public /* synthetic */ a(RecentlyHistoryListAdapter recentlyHistoryListAdapter, RecentlyHistoryListAdapter.ViewHolder viewHolder, HistoryItem historyItem) {
        this.d = recentlyHistoryListAdapter;
        this.e = viewHolder;
        this.f = historyItem;
    }

    public final void accept(Object obj) {
        this.d.lambda$drawCreatureThumbnail$3(this.e, this.f, (Bitmap) obj);
    }
}
