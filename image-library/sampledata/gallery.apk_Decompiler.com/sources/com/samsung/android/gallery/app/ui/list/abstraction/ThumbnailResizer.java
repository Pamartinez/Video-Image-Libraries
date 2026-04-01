package com.samsung.android.gallery.app.ui.list.abstraction;

import A4.N;
import A4.O;
import android.graphics.Bitmap;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThumbnailResizer {
    private final BaseListViewAdapter<?> mAdapter;

    public ThumbnailResizer(BaseListViewAdapter<?> baseListViewAdapter) {
        this.mAdapter = baseListViewAdapter;
    }

    private void changeThumbnailSize(ListViewHolder listViewHolder, ThumbKind thumbKind) {
        ThumbnailRequestHolder thumbnailRequestHolder = new ThumbnailRequestHolder(listViewHolder);
        MediaItem mediaItem = thumbnailRequestHolder.getMediaItem();
        if (mediaItem == null) {
            Log.d("ThumbnailResizer", "changeThumbnailSize failed. null item");
            return;
        }
        listViewHolder.setThumbKind(thumbKind);
        listViewHolder.setImageUid(mediaItem.getPath());
        if (listViewHolder.getImage() == null) {
            Log.d("ThumbnailResizer", "changeThumbnailSize failed. null image view");
            return;
        }
        Bitmap loadCache = loadCache(mediaItem, thumbKind);
        if (loadCache == null || !ThreadUtil.isMainThread()) {
            ThumbnailLoader.getInstance().loadThumbnail(mediaItem, thumbKind, thumbnailRequestHolder, new O(4, this), new N(listViewHolder, thumbnailRequestHolder, 1));
        } else if (!listViewHolder.equalBitmap(loadCache)) {
            listViewHolder.bindImage(loadCache);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$changeThumbnailSize$0(ListViewHolder listViewHolder, ThumbnailRequestHolder thumbnailRequestHolder) {
        return !BaseListViewAdapter.isViewSame(listViewHolder, thumbnailRequestHolder.getPosition());
    }

    public void checkVisibleViewHoldersForXLarge() {
        GalleryListView galleryListView;
        if (this.mAdapter.supportHighQuality() && (galleryListView = this.mAdapter.mGalleryListView) != null && galleryListView.getLayoutManager() != null) {
            int findLastVisibleItemPositionCompat = galleryListView.findLastVisibleItemPositionCompat();
            for (int findFirstVisibleItemPositionCompat = galleryListView.findFirstVisibleItemPositionCompat(); findFirstVisibleItemPositionCompat <= findLastVisibleItemPositionCompat; findFirstVisibleItemPositionCompat++) {
                ListViewHolder findListViewHolder = findListViewHolder(findFirstVisibleItemPositionCompat);
                if (findListViewHolder != null && needXLargeThumbnail(findListViewHolder, findFirstVisibleItemPositionCompat)) {
                    requestXLargeThumbnail(findListViewHolder);
                }
            }
        }
    }

    public ListViewHolder findListViewHolder(int i2) {
        GalleryListView galleryListView = this.mAdapter.mGalleryListView;
        if (galleryListView == null) {
            return null;
        }
        RecyclerView.ViewHolder findViewHolderForLayoutPosition = galleryListView.findViewHolderForLayoutPosition(i2);
        if (findViewHolderForLayoutPosition instanceof ListViewHolder) {
            return (ListViewHolder) findViewHolderForLayoutPosition;
        }
        return null;
    }

    public boolean isCandidate(ListViewHolder listViewHolder, int i2) {
        if ((listViewHolder instanceof ImageViewHolder) && listViewHolder.getThumbKind() != ThumbKind.XLARGE_NC_KIND) {
            MediaItem mediaItem = listViewHolder.getMediaItem();
            if (mediaItem == null || i2 < 0) {
                Log.w("ThumbXL", "[" + i2 + "] skip null item");
            } else if (mediaItem.isBroken() || mediaItem.isHeif() || mediaItem.isGif() || mediaItem.isPanoramic()) {
                return false;
            } else {
                if (!mediaItem.isVideo() || !mediaItem.is8K()) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public Bitmap loadCache(MediaItem mediaItem, ThumbKind thumbKind) {
        return ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
    }

    public boolean needXLargeThumbnail(ListViewHolder listViewHolder, int i2) {
        int i7;
        if (i2 < 0) {
            i2 = listViewHolder.getViewPosition();
        }
        if (isCandidate(listViewHolder, i2)) {
            int itemHeight = this.mAdapter.getItemHeight(i2) * this.mAdapter.getSpanSize(i2);
            Bitmap bitmap = listViewHolder.getBitmap();
            if (bitmap == null) {
                i7 = 0;
            } else {
                i7 = bitmap.getHeight() * bitmap.getWidth();
            }
            if (((float) itemHeight) <= ((float) i7) * 1.9f && itemHeight <= ((int) (((float) DeviceInfo.getDisplayVolume(this.mAdapter.getContext())) * 0.24f))) {
                return false;
            }
            return true;
        }
        return false;
    }

    public void onBitmapLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        this.mAdapter.setBitmapWithBind(bitmap, uniqueKey, thumbKind);
    }

    public void requestXLargeThumbnail(int i2) {
        ListViewHolder findListViewHolder = findListViewHolder(i2);
        if (findListViewHolder != null) {
            requestXLargeThumbnail(findListViewHolder);
            return;
        }
        Log.w("ThumbnailResizer", "fail request xlarge : " + i2);
    }

    public void sizeDownThumbnail(int i2) {
        int viewPosition = this.mAdapter.getViewPosition(i2);
        ListViewHolder findListViewHolder = findListViewHolder(viewPosition);
        if (findListViewHolder != null && (!this.mAdapter.supportHighQuality() || !needXLargeThumbnail(findListViewHolder, viewPosition))) {
            changeThumbnailSize(findListViewHolder, this.mAdapter.getThumbnailKind());
        }
        checkVisibleViewHoldersForXLarge();
    }

    public void requestXLargeThumbnail(ListViewHolder listViewHolder) {
        changeThumbnailSize(listViewHolder, ThumbKind.XLARGE_NC_KIND);
    }
}
