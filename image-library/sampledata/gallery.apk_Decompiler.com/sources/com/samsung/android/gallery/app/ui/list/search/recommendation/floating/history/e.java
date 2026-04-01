package com.samsung.android.gallery.app.ui.list.search.recommendation.floating.history;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ FloatingHistoryViewHolder e;

    public /* synthetic */ e(FloatingHistoryViewHolder floatingHistoryViewHolder, int i2) {
        this.d = i2;
        this.e = floatingHistoryViewHolder;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        FloatingHistoryViewHolder floatingHistoryViewHolder = this.e;
        switch (i2) {
            case 0:
                floatingHistoryViewHolder.onViewClicked(view);
                return;
            default:
                floatingHistoryViewHolder.lambda$setClickListener$0(view);
                return;
        }
    }
}
