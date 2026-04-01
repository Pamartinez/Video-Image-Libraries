package com.samsung.android.gallery.app.ui.list.albums.virtual;

import com.samsung.android.gallery.app.ui.list.albums.virtual.RepairPicturesFragment;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ RepairPicturesFragment.FilterAdapter d;
    public final /* synthetic */ RepairPicturesFragment.KeywordViewHolder e;

    public /* synthetic */ b(RepairPicturesFragment.FilterAdapter filterAdapter, RepairPicturesFragment.KeywordViewHolder keywordViewHolder) {
        this.d = filterAdapter;
        this.e = keywordViewHolder;
    }

    public final void accept(Object obj) {
        this.d.lambda$onCreateViewHolder$0(this.e, (RepairPicturesFragment.KeywordViewHolder) obj);
    }
}
