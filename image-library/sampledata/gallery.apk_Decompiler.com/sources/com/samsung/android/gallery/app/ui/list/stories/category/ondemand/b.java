package com.samsung.android.gallery.app.ui.list.stories.category.ondemand;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ListViewHolder.OnItemClickListener, ThumbnailLoadedListener {
    public final /* synthetic */ PdcRecommendAdapter d;
    public final /* synthetic */ ListViewHolder e;

    public /* synthetic */ b(PdcRecommendAdapter pdcRecommendAdapter, ListViewHolder listViewHolder) {
        this.d = pdcRecommendAdapter;
        this.e = listViewHolder;
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        this.d.lambda$onBindViewHolder$0((PdcRecommendItemViewHolder) this.e, listViewHolder, i2, mediaItem, i7);
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        this.d.lambda$bindThumbnail$5(this.e, bitmap, uniqueKey, thumbKind);
    }
}
