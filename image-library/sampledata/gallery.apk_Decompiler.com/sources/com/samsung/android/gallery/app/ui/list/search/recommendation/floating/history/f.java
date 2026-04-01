package com.samsung.android.gallery.app.ui.list.search.recommendation.floating.history;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.search.history.HistoryItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Consumer {
    public final /* synthetic */ FloatingHistoryViewHolder d;
    public final /* synthetic */ HistoryItem e;

    public /* synthetic */ f(FloatingHistoryViewHolder floatingHistoryViewHolder, HistoryItem historyItem) {
        this.d = floatingHistoryViewHolder;
        this.e = historyItem;
    }

    public final void accept(Object obj) {
        this.d.lambda$drawTypeIcon$2(this.e, (Bitmap) obj);
    }
}
