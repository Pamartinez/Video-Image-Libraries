package com.samsung.android.gallery.app.ui.list.sharings.storage;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.sharings.ISharingsStorageView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ResourceUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingStorageUseAdapter extends BaseListViewAdapter<ISharingsStorageView> {
    private SharingStorageUseViewHolderFactory mViewHolderFactory = createViewHolderFactory(getContext());

    public SharingStorageUseAdapter(ISharingsStorageView iSharingsStorageView, String str, GalleryListView galleryListView) {
        super(iSharingsStorageView, str);
    }

    private boolean isFirstItem(int i2) {
        if (getItemViewType(i2) == 7) {
            return true;
        }
        return false;
    }

    public SharingStorageUseViewHolderFactory createViewHolderFactory(Context context) {
        return new SharingStorageUseViewHolderFactory(context);
    }

    public void destroy() {
        super.destroy();
    }

    public Bitmap getBrokenMediaItem(MediaItem mediaItem) {
        if (!TextUtils.isEmpty(mediaItem.getPath())) {
            return null;
        }
        if (Features.isEnabled(Features.SUPPORT_DOWNLOAD_BROKEN_SHARED_COVER) && mediaItem.getCount() != 0) {
            this.mBlackBoard.post("data://download_shared_cover", mediaItem);
        }
        return ThumbnailLoader.getInstance().getReplacedThumbnail(getContext(), ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
    }

    public int getItemViewType(int i2) {
        if (i2 == 0) {
            return 7;
        }
        if (i2 == getItemCount() - 1) {
            return -4;
        }
        return 0;
    }

    public ThumbKind getThumbnailKind() {
        return ThumbKind.MEDIUM_KIND;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        if (isFirstItem(i2) && getDataCount() == 1) {
            listViewHolder.setRoundMode(15);
        } else if (isFirstItem(i2)) {
            listViewHolder.setRoundMode(3);
        } else if (isFooter(i2)) {
            listViewHolder.setRoundMode(12);
        } else {
            listViewHolder.setRoundMode(0);
        }
        super.onBindViewHolder(listViewHolder, i2);
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return this.mViewHolderFactory.createViewHolder(viewGroup, i2);
    }
}
