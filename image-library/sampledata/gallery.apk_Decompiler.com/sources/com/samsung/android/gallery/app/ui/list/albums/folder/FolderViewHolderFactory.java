package com.samsung.android.gallery.app.ui.list.albums.folder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.albums.viewholder.AlbumTitleCountReorderHolder;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FolderViewHolderFactory extends AlbumsViewHolderFactory {
    public FolderViewHolderFactory(Context context) {
        super(context);
    }

    public ListViewHolder createListViewHolder(ViewGroup viewGroup, int i2) {
        AlbumTitleCountReorderHolder albumTitleCountReorderHolder;
        View inflate = this.mLayoutInflater.inflate(this.mListLayoutId, viewGroup, false);
        if (!Features.isEnabled(Features.SUPPORT_CLOUD_SYNC_STATUS) || Features.isEnabled(Features.IS_UPSM)) {
            albumTitleCountReorderHolder = new AlbumTitleCountReorderHolder(inflate, i2, this.mReorderEnabled);
        } else {
            albumTitleCountReorderHolder = new AlbumTitleCountReorderHolder(inflate, i2, this.mReorderEnabled, true);
        }
        albumTitleCountReorderHolder.setThumbnailShape(1, this.mListRoundRadius);
        return albumTitleCountReorderHolder;
    }
}
