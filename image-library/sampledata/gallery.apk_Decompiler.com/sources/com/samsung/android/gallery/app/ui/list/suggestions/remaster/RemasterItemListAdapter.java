package com.samsung.android.gallery.app.ui.list.suggestions.remaster;

import B8.e;
import J6.c;
import K.a;
import O3.b;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.module.abstraction.RemasterSuggestGroup;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterItemListAdapter extends RecyclerView.Adapter<ListViewHolder> {
    private MediaData mMediaData;
    private ListViewHolder.OnItemClickListener mOnItemClickListener;
    private int mRoundRadius;

    public RemasterItemListAdapter(Context context, MediaData mediaData) {
        if (context != null) {
            this.mRoundRadius = context.getResources().getDimensionPixelOffset(R.dimen.remaster_list_item_round_radius);
        }
        this.mMediaData = mediaData;
    }

    private void bindInfo(ListViewHolder listViewHolder, MediaItem mediaItem) {
        ((RemasterItemViewHolder) listViewHolder).bindInfo(mediaItem, hasResolution(mediaItem));
    }

    private void bindThumbnail(ListViewHolder listViewHolder, MediaItem mediaItem) {
        ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
        listViewHolder.setThumbKind(thumbKind);
        Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            lambda$bindThumbnail$1(listViewHolder, memCache);
        } else {
            ThumbnailLoader instance = ThumbnailLoader.getInstance();
            Objects.requireNonNull(mediaItem);
            instance.loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 1), new b(8, this, listViewHolder));
        }
        listViewHolder.setThumbnailShape(1, (float) this.mRoundRadius);
    }

    private boolean hasResolution(MediaItem mediaItem) {
        return RemasterSuggestGroup.isResolution(VslMesDetectorCompat.decodeEnhances(MediaItemSuggest.getRevitalizedType(mediaItem), true));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindThumbnail$2(ListViewHolder listViewHolder, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.postOnUiThread(new c(this, listViewHolder, bitmap, 24));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onBindViewHolder$0(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: onBindThumbnail */
    public void lambda$bindThumbnail$1(ListViewHolder listViewHolder, Bitmap bitmap) {
        listViewHolder.bindImage(bitmap);
    }

    public MediaItem getItem(int i2) {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            return mediaData.read(i2);
        }
        return null;
    }

    public int getItemCount() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            return mediaData.getCount();
        }
        return 0;
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new RemasterItemViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_remaster_image_layout, viewGroup, false), 0);
    }

    public void release() {
        this.mMediaData = null;
        this.mOnItemClickListener = null;
    }

    public void setOnItemClickListener(ListViewHolder.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        MediaItem item = getItem(i2);
        if (item != null) {
            listViewHolder.bind(item);
            listViewHolder.setOnItemClickListener(this.mOnItemClickListener);
            listViewHolder.setOnItemLongClickListener(new a(16));
            bindInfo(listViewHolder, item);
            bindThumbnail(listViewHolder, item);
        }
        listViewHolder.setThumbnailShape(1, (float) this.mRoundRadius);
    }

    public void onViewRecycled(ListViewHolder listViewHolder) {
        listViewHolder.recycle();
    }
}
