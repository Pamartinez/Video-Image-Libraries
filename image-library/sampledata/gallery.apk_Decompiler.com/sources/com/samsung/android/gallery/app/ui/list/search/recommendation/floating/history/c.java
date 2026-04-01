package com.samsung.android.gallery.app.ui.list.search.recommendation.floating.history;

import com.samsung.android.gallery.module.search.history.HistoryItem;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2547a;
    public final /* synthetic */ FloatingHistoryListAdapter b;

    public /* synthetic */ c(FloatingHistoryListAdapter floatingHistoryListAdapter, int i2) {
        this.f2547a = i2;
        this.b = floatingHistoryListAdapter;
    }

    public final void accept(Object obj, Object obj2) {
        int i2 = this.f2547a;
        FloatingHistoryViewHolder floatingHistoryViewHolder = (FloatingHistoryViewHolder) obj;
        HistoryItem historyItem = (HistoryItem) obj2;
        FloatingHistoryListAdapter floatingHistoryListAdapter = this.b;
        switch (i2) {
            case 0:
                floatingHistoryListAdapter.deleteHistoryItem(floatingHistoryViewHolder, historyItem);
                return;
            default:
                floatingHistoryListAdapter.onHistoryItemClicked(floatingHistoryViewHolder, historyItem);
                return;
        }
    }
}
