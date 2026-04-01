package com.samsung.android.gallery.app.ui.list.search.recommendation;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.recommendation.RecentlyHistoryListAdapter;
import com.samsung.android.gallery.module.search.history.HistoryItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements View.OnClickListener {
    public final /* synthetic */ RecentlyHistoryListAdapter d;
    public final /* synthetic */ RecentlyHistoryListAdapter.ViewHolder e;
    public final /* synthetic */ HistoryItem f;

    public /* synthetic */ c(RecentlyHistoryListAdapter recentlyHistoryListAdapter, RecentlyHistoryListAdapter.ViewHolder viewHolder, HistoryItem historyItem) {
        this.d = recentlyHistoryListAdapter;
        this.e = viewHolder;
        this.f = historyItem;
    }

    public final void onClick(View view) {
        this.d.lambda$setClickListeners$1(this.e, this.f, view);
    }
}
