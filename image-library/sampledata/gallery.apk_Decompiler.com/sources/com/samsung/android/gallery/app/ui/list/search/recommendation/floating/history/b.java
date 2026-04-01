package com.samsung.android.gallery.app.ui.list.search.recommendation.floating.history;

import com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailRequestHolder;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterrupter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ThumbnailInterrupter {
    public final /* synthetic */ FloatingHistoryListAdapter d;
    public final /* synthetic */ FloatingHistoryViewHolder e;
    public final /* synthetic */ ThumbnailRequestHolder f;

    public /* synthetic */ b(FloatingHistoryListAdapter floatingHistoryListAdapter, FloatingHistoryViewHolder floatingHistoryViewHolder, ThumbnailRequestHolder thumbnailRequestHolder) {
        this.d = floatingHistoryListAdapter;
        this.e = floatingHistoryViewHolder;
        this.f = thumbnailRequestHolder;
    }

    public final boolean isInterrupted() {
        return this.d.lambda$requestThumbnail$3(this.e, this.f);
    }
}
