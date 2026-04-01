package com.samsung.android.gallery.app.ui.list.suggestions;

import A4.Q;
import A6.a;
import B8.e;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestionsItemAdapter extends RecyclerView.Adapter<SuggestionsImageViewHolder> {
    private int mDataCount;
    private int mMaxColumnCount;
    private View.OnClickListener mOnClickListener;
    private int mRoundRadius;
    private MediaItem mSuggestionItem;

    public SuggestionsItemAdapter(Context context) {
        if (context != null) {
            this.mRoundRadius = context.getResources().getDimensionPixelOffset(R.dimen.suggestions_item_round_radius);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: bindImage */
    public void lambda$onBindViewHolder$0(SuggestionsImageViewHolder suggestionsImageViewHolder, MediaItem mediaItem, Bitmap bitmap) {
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
            bitmap2 = getBrokenImage(suggestionsImageViewHolder.itemView.getContext(), mediaItem);
        }
        suggestionsImageViewHolder.bindImage(bitmap2);
    }

    private Bitmap getBrokenImage(Context context, MediaItem mediaItem) {
        mediaItem.setBroken(true);
        return ThumbnailLoader.getInstance().getReplacedThumbnail(context, ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
    }

    private boolean isLastItem(int i2) {
        if (i2 == getItemCount() - 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$1(SuggestionsImageViewHolder suggestionsImageViewHolder, MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        Bitmap bitmap2 = bitmap;
        MediaItem mediaItem2 = mediaItem;
        ThreadUtil.postOnUiThread(new a((RecyclerView.Adapter) this, (Object) suggestionsImageViewHolder, (Object) mediaItem2, bitmap2, 10));
    }

    public int getItemCount() {
        return this.mDataCount;
    }

    public SuggestionsImageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        SuggestionsImageViewHolder suggestionsImageViewHolder = new SuggestionsImageViewHolder(C0086a.d(viewGroup, R.layout.recycler_suggestions_item_layout, viewGroup, false), 0);
        suggestionsImageViewHolder.itemView.setOnClickListener(this.mOnClickListener);
        return suggestionsImageViewHolder;
    }

    public void setData(MediaItem mediaItem, int i2) {
        this.mSuggestionItem = mediaItem;
        this.mMaxColumnCount = i2;
        this.mDataCount = Math.min(i2, mediaItem.getItemsInFolder().length);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public void onBindViewHolder(SuggestionsImageViewHolder suggestionsImageViewHolder, int i2) {
        MediaItem mediaItem = this.mSuggestionItem.getItemsInFolder()[i2];
        if (mediaItem != null) {
            ThumbnailLoader instance = ThumbnailLoader.getInstance();
            ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
            Bitmap memCache = instance.getMemCache(mediaItem, thumbKind);
            if (memCache != null) {
                lambda$onBindViewHolder$0(suggestionsImageViewHolder, mediaItem, memCache);
            } else {
                ThumbnailLoader.getInstance().loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 1), new Q((Object) this, (Object) suggestionsImageViewHolder, (Object) mediaItem, 8));
            }
            if (this.mSuggestionItem.getCount() <= this.mMaxColumnCount || !isLastItem(i2)) {
                suggestionsImageViewHolder.bindCountView(-1);
                suggestionsImageViewHolder.setDimViewEnabled(false);
            } else {
                suggestionsImageViewHolder.bindCountView(this.mSuggestionItem.getCount() - this.mDataCount);
                suggestionsImageViewHolder.setDimViewEnabled(true);
            }
            if (MediaItemSuggest.isHighLight(this.mSuggestionItem)) {
                suggestionsImageViewHolder.setPlayIconVisibility(true);
            }
            suggestionsImageViewHolder.bind(mediaItem);
        }
        suggestionsImageViewHolder.setThumbnailShape(1, (float) this.mRoundRadius);
    }
}
