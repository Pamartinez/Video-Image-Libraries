package com.samsung.android.gallery.app.ui.list.stories.hiderule.v1;

import A4.Q;
import B8.e;
import a8.d;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HideRuleSceneItemAdapter extends RecyclerView.Adapter<ListViewHolder> {
    private int mColumnCount;
    protected Context mContext;
    protected MediaItem mHideRuleItem;
    protected ListViewHolder.OnItemClickListener mOnItemClickListener;

    public HideRuleSceneItemAdapter(Context context, MediaItem mediaItem) {
        this.mContext = context;
        this.mHideRuleItem = mediaItem;
    }

    /* access modifiers changed from: private */
    /* renamed from: bindImage */
    public void lambda$onBindViewHolder$0(ListViewHolder listViewHolder, MediaItem mediaItem, Bitmap bitmap) {
        if (bitmap == null) {
            bitmap = getBrokenImage(mediaItem);
        }
        listViewHolder.bindImage(bitmap);
    }

    private Bitmap getBrokenImage(MediaItem mediaItem) {
        mediaItem.setBroken(true);
        return ThumbnailLoader.getInstance().getReplacedThumbnail(this.mContext, ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
    }

    private boolean isLastItem(int i2) {
        if (i2 == getItemCount() - 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$1(HideRuleSceneItemViewHolder hideRuleSceneItemViewHolder, MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        Bitmap bitmap2 = bitmap;
        MediaItem mediaItem2 = mediaItem;
        ThreadUtil.postOnUiThread(new d((Object) this, (Object) hideRuleSceneItemViewHolder, mediaItem2, (Object) bitmap2, 9));
    }

    public int getItemCount() {
        return Math.min(this.mColumnCount, this.mHideRuleItem.getItemsInFolder().length);
    }

    public int getItemLayoutId() {
        return R.layout.recycler_item_hide_rule_scene_item_layout;
    }

    public void setColumnCount(int i2) {
        this.mColumnCount = i2;
    }

    public void setOnItemClickListener(ListViewHolder.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        HideRuleSceneItemViewHolder hideRuleSceneItemViewHolder = (HideRuleSceneItemViewHolder) listViewHolder;
        MediaItem mediaItem = this.mHideRuleItem.getItemsInFolder()[i2];
        if (mediaItem != null) {
            ThumbnailLoader instance = ThumbnailLoader.getInstance();
            ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
            Bitmap memCache = instance.getMemCache(mediaItem, thumbKind);
            if (memCache != null) {
                lambda$onBindViewHolder$0(hideRuleSceneItemViewHolder, mediaItem, memCache);
            } else {
                ThumbnailLoader.getInstance().loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 1), new Q((Object) this, (Object) hideRuleSceneItemViewHolder, (Object) mediaItem, 18));
            }
            if (isLastItem(i2)) {
                hideRuleSceneItemViewHolder.bindCountView(this.mHideRuleItem.getCount() - this.mColumnCount, true);
            } else {
                hideRuleSceneItemViewHolder.bindCountView(-1, false);
            }
            listViewHolder.bind(mediaItem);
        }
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        HideRuleSceneItemViewHolder hideRuleSceneItemViewHolder = new HideRuleSceneItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getItemLayoutId(), viewGroup, false), 0);
        hideRuleSceneItemViewHolder.setOnItemClickListener(this.mOnItemClickListener);
        return hideRuleSceneItemViewHolder;
    }
}
