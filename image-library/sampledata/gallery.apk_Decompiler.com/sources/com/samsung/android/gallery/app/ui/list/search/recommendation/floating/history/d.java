package com.samsung.android.gallery.app.ui.list.search.recommendation.floating.history;

import android.graphics.Bitmap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ FloatingHistoryViewHolder d;
    public final /* synthetic */ Bitmap e;

    public /* synthetic */ d(FloatingHistoryViewHolder floatingHistoryViewHolder, Bitmap bitmap) {
        this.d = floatingHistoryViewHolder;
        this.e = bitmap;
    }

    public final void run() {
        this.d.bindImage(this.e);
    }
}
