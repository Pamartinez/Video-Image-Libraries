package com.samsung.android.gallery.app.ui.list.albums.choice;

import A2.d;
import android.content.Context;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.albums.choice.IAlbumChoiceView;
import com.samsung.android.gallery.app.ui.list.albums.choice.abstraction.AlbumChoiceBaseAdapter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumChoiceAdapter<V extends IAlbumChoiceView> extends AlbumChoiceBaseAdapter<V> {
    public AlbumChoiceAdapter(V v, String str) {
        super(v, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(ListViewHolder listViewHolder) {
        this.mItemHeight = listViewHolder.itemView.getHeight();
    }

    public AlbumsViewHolderFactory createViewHolderFactory(Context context) {
        return new AlbumsChoiceViewHolderFactory(context, isGridLayout());
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        MediaItem mediaItem;
        super.onBindViewHolder(listViewHolder, i2);
        if (!PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            ViewUtils.setVisibility(listViewHolder.getCountView(), 0);
        }
        listViewHolder.itemView.post(new d(27, this, listViewHolder));
        updateBorders(listViewHolder, this.mGalleryListView.getDepth());
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS && (mediaItem = listViewHolder.getMediaItem()) != null) {
            listViewHolder.setEnable(!isDisabledAlbum(mediaItem));
        }
    }
}
