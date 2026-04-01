package com.samsung.android.gallery.app.ui.list.stories.pictures.related;

import A4.H;
import A9.b;
import Ad.j;
import B8.e;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleDurationViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryRelatedAdapter extends RecyclerView.Adapter<ListViewHolder> {
    private static final ThumbKind THUMB_KIND = ThumbKind.MEDIUM_KIND;
    private final ArrayList<MediaItem> mDataList = new ArrayList<>();
    private ListViewHolder.OnItemClickListener mOnItemClickListener;

    private void bindItemInfo(ListViewHolder listViewHolder, MediaItem mediaItem) {
        setText(listViewHolder.getTitleView(), mediaItem.getTitle());
        setText(listViewHolder.getDecoView(31), MediaItemStory.getStoryTimeDuration(mediaItem));
    }

    private void bindThumbnail(ListViewHolder listViewHolder, MediaItem mediaItem) {
        ThumbKind thumbKind = THUMB_KIND;
        listViewHolder.setThumbKind(thumbKind);
        Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            lambda$bindThumbnail$1(listViewHolder, memCache);
            return;
        }
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        Objects.requireNonNull(mediaItem);
        instance.loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 1), new H(5, (Object) this, (Object) listViewHolder));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindThumbnail$2(ListViewHolder listViewHolder, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.postOnUiThread(new b(this, listViewHolder, bitmap, 9));
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

    private void setText(View view, String str) {
        if (view instanceof TextView) {
            ((TextView) view).setText(str);
        }
    }

    public MediaItem getItem(int i2) {
        return this.mDataList.get(i2);
    }

    public int getItemCount() {
        return this.mDataList.size();
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new ImageTitleDurationViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_story_related_layout, viewGroup, false), 0);
    }

    public void setData(ArrayList<MediaItem> arrayList) {
        this.mDataList.clear();
        this.mDataList.addAll(arrayList);
    }

    public void setOnRelatedClickListener(ListViewHolder.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        MediaItem item = getItem(i2);
        listViewHolder.bind(item);
        listViewHolder.setOnItemClickListener(this.mOnItemClickListener);
        listViewHolder.setOnItemLongClickListener(new j(7));
        bindThumbnail(listViewHolder, item);
        bindItemInfo(listViewHolder, item);
    }
}
