package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page;

import B8.e;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import e5.d;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import l6.a;
import n0.C0235b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RelatedViewAdapter extends RecyclerView.Adapter<ListViewHolder> {
    private final ArrayList<MediaItem> mItems = new ArrayList<>();
    private ListViewHolder.OnItemClickListener mOnItemClickListener;
    private int mRoundRadius;

    public RelatedViewAdapter(Context context) {
        if (context != null) {
            this.mRoundRadius = context.getResources().getDimensionPixelOffset(R.dimen.story_highlight_related_item_round_radius);
        }
    }

    private void bindThumbnail(ListViewHolder listViewHolder, MediaItem mediaItem) {
        ThumbKind thumbKind = ThumbKind.LARGE_KIND;
        listViewHolder.setThumbKind(thumbKind);
        Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            lambda$bindThumbnail$1(listViewHolder, memCache);
        } else {
            ThumbnailLoader instance = ThumbnailLoader.getInstance();
            Objects.requireNonNull(mediaItem);
            instance.loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 1), new a(4, this, listViewHolder));
        }
        listViewHolder.setThumbnailShape(1, (float) this.mRoundRadius);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindThumbnail$2(ListViewHolder listViewHolder, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.postOnUiThread(new C0235b(this, listViewHolder, bitmap, 17));
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
        if (i2 < 0 || i2 >= this.mItems.size()) {
            return null;
        }
        return this.mItems.get(i2);
    }

    public int getItemCount() {
        return this.mItems.size();
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new RelatedViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_story_highlight_related_layout, viewGroup, false), 0);
    }

    public void setData(List<MediaItem> list) {
        this.mItems.clear();
        this.mItems.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnRelatedClickListener(ListViewHolder.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        MediaItem item = getItem(i2);
        if (item != null) {
            listViewHolder.bind(item);
            listViewHolder.setOnItemClickListener(this.mOnItemClickListener);
            listViewHolder.setOnItemLongClickListener(new d(12));
            bindThumbnail(listViewHolder, item);
        }
    }
}
