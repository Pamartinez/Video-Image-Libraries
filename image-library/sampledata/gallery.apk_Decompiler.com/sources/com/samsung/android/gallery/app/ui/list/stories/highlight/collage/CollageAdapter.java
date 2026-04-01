package com.samsung.android.gallery.app.ui.list.stories.highlight.collage;

import B5.c;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import bc.d;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.OutlineFrameLayout;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CollageAdapter extends RecyclerView.Adapter<CollageViewHolder> {
    private final ArrayList<MediaItem> mList = new ArrayList<>();
    private View mListView;
    private CollageType mType;

    public CollageAdapter(View view) {
        this.mListView = view;
    }

    private boolean equals(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || mediaItem.getFileId() != mediaItem2.getFileId()) {
            return false;
        }
        return true;
    }

    private Bitmap getBrokenImage(MediaItem mediaItem) {
        mediaItem.setBroken(true);
        return ThumbnailLoader.getInstance().getReplacedThumbnail(AppResources.getAppContext(), ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(CollageViewHolder collageViewHolder, MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        onThumbnailLoadCompleted(collageViewHolder, mediaItem, bitmap, thumbKind);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$1(CollageViewHolder collageViewHolder, MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        onThumbnailLoadCompleted(collageViewHolder, mediaItem, bitmap, thumbKind);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$2(MediaItem mediaItem, CollageViewHolder collageViewHolder) {
        ThumbnailLoader.getInstance().loadThumbnail(mediaItem, ThumbKind.XLARGE_NC_KIND, new a(this, collageViewHolder, mediaItem, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onThumbnailLoadCompleted$3(ListViewHolder listViewHolder, MediaItem mediaItem, Bitmap bitmap, ThumbKind thumbKind) {
        if (!equals(listViewHolder.getMediaItem(), mediaItem)) {
            return;
        }
        if (bitmap != null) {
            if (ThumbnailLoader.getInstance().isReplacedThumbnail(bitmap)) {
                mediaItem.setBroken(true);
            }
            listViewHolder.setThumbKind(thumbKind);
            listViewHolder.bindImage(bitmap);
            return;
        }
        mediaItem.setBroken(true);
        listViewHolder.bindImage(getBrokenImage(mediaItem));
    }

    private void onThumbnailLoadCompleted(ListViewHolder listViewHolder, MediaItem mediaItem, Bitmap bitmap, ThumbKind thumbKind) {
        ThreadUtil.runOnUiThread(new c(this, listViewHolder, mediaItem, bitmap, thumbKind, 21));
    }

    private void setRoundCorner(CollageViewHolder collageViewHolder, int i2) {
        int i7;
        if (ResourceCompat.isLandscape(this.mListView)) {
            i7 = this.mListView.getHeight();
        } else {
            i7 = this.mListView.getWidth();
        }
        ((OutlineFrameLayout) collageViewHolder.getScalableView()).setOutlineRadius(this.mType.getCornerRadius(this.mListView.getResources(), i7, i2), Integer.valueOf(this.mListView.getResources().getColor(R.color.story_highlight_collage_content_bg_color, (Resources.Theme) null)));
    }

    public int getItemCount() {
        return this.mList.size();
    }

    public MediaItem getMediaItem(int i2) {
        return this.mList.get(i2);
    }

    public int getVideoItemIndex() {
        for (int i2 = 0; i2 < this.mList.size(); i2++) {
            if (StoryHelper.isVideoItem(this.mList.get(i2))) {
                return i2;
            }
        }
        return -1;
    }

    public CollageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new CollageViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_story_highlight_collage_layout, viewGroup, false), 0);
    }

    public void setCollageType(CollageType collageType) {
        this.mType = collageType;
    }

    public void setData(ArrayList<MediaItem> arrayList) {
        this.mList.clear();
        this.mList.addAll(arrayList);
        notifyDataSetChanged();
    }

    public void onBindViewHolder(CollageViewHolder collageViewHolder, int i2) {
        MediaItem mediaItem = getMediaItem(i2);
        collageViewHolder.bind(mediaItem);
        setRoundCorner(collageViewHolder, i2);
        ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
        Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            onThumbnailLoadCompleted(collageViewHolder, mediaItem, memCache, thumbKind);
        } else {
            ThumbnailLoader.getInstance().loadThumbnail(mediaItem, thumbKind, new a(this, collageViewHolder, mediaItem, 0));
        }
        ThreadUtil.postOnUiThread(new d((Object) this, (Object) mediaItem, (Object) collageViewHolder, 4));
    }

    public void onViewRecycled(CollageViewHolder collageViewHolder) {
        collageViewHolder.recycle();
    }
}
