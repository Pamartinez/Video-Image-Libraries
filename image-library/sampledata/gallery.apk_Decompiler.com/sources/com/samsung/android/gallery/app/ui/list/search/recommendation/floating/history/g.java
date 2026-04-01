package com.samsung.android.gallery.app.ui.list.search.recommendation.floating.history;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.search.history.HistoryItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ FloatingHistoryViewHolder d;
    public final /* synthetic */ Bitmap e;
    public final /* synthetic */ HistoryItem f;

    public /* synthetic */ g(Bitmap bitmap, FloatingHistoryViewHolder floatingHistoryViewHolder, HistoryItem historyItem) {
        this.d = floatingHistoryViewHolder;
        this.e = bitmap;
        this.f = historyItem;
    }

    public final void run() {
        this.d.lambda$drawTypeIcon$1(this.e, this.f);
    }
}
