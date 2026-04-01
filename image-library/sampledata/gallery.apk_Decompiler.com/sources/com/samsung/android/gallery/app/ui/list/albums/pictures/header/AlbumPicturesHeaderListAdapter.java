package com.samsung.android.gallery.app.ui.list.albums.pictures.header;

import B8.e;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AlbumPicturesHeaderListAdapter extends RecyclerView.Adapter<AlbumPicturesHeaderViewHolder> {
    private List<MediaItem> mItemList;

    public AlbumPicturesHeaderListAdapter(MediaItem mediaItem) {
        this.mItemList = new ArrayList(Arrays.asList(mediaItem.getItemsInFolder()));
    }

    /* access modifiers changed from: private */
    /* renamed from: bindImage */
    public void lambda$onBindViewHolder$0(AlbumPicturesHeaderViewHolder albumPicturesHeaderViewHolder, MediaItem mediaItem, Bitmap bitmap) {
        Bitmap bitmap2;
        int i2;
        if (bitmap != null) {
            if (mediaItem.isVideo()) {
                i2 = 0;
            } else {
                i2 = mediaItem.getOrientation();
            }
            bitmap2 = BitmapUtils.rotateBitmap(bitmap, i2);
        } else {
            bitmap2 = getBrokenImage(albumPicturesHeaderViewHolder.itemView.getContext(), mediaItem);
        }
        albumPicturesHeaderViewHolder.bindImage(bitmap2);
    }

    private Bitmap getBrokenImage(Context context, MediaItem mediaItem) {
        mediaItem.setBroken(true);
        return ThumbnailLoader.getInstance().getReplacedThumbnail(context, ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$1(AlbumPicturesHeaderViewHolder albumPicturesHeaderViewHolder, MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.runOnUiThread(new b(this, albumPicturesHeaderViewHolder, mediaItem, bitmap));
    }

    public int getItemCount() {
        List<MediaItem> list = this.mItemList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public AlbumPicturesHeaderViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new AlbumPicturesHeaderViewHolder(C0086a.d(viewGroup, R.layout.recycler_mergealbum_tipcard_item_layout, viewGroup, false), 0);
    }

    public void setMediaItem(MediaItem mediaItem) {
        List<MediaItem> list = this.mItemList;
        if (list != null) {
            list.clear();
            this.mItemList = null;
        }
        this.mItemList = new ArrayList(Arrays.asList(mediaItem.getItemsInFolder()));
    }

    public void onBindViewHolder(AlbumPicturesHeaderViewHolder albumPicturesHeaderViewHolder, int i2) {
        List<MediaItem> list = this.mItemList;
        if (list == null || i2 >= list.size()) {
            albumPicturesHeaderViewHolder.bindImage((Bitmap) null);
            return;
        }
        MediaItem mediaItem = this.mItemList.get(i2);
        if (mediaItem != null) {
            if (mediaItem.isEmptyAlbum()) {
                albumPicturesHeaderViewHolder.bindImage(ThumbnailLoader.getInstance().getDefaultAlbumImage(true));
                albumPicturesHeaderViewHolder.setThumbnailBackgroundColor(albumPicturesHeaderViewHolder.itemView.getContext().getColor(R.color.daynight_default_background));
            } else {
                ThumbnailLoader.getInstance().getOrLoad(mediaItem, ThumbKind.MEDIUM_KIND, new e(mediaItem, 1), new a(this, albumPicturesHeaderViewHolder, mediaItem));
            }
            albumPicturesHeaderViewHolder.bind(mediaItem);
        }
    }
}
