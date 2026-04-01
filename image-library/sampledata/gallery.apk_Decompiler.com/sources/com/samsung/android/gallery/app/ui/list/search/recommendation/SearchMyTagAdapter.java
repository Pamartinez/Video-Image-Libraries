package com.samsung.android.gallery.app.ui.list.search.recommendation;

import Ab.a;
import B2.i;
import Ba.g;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.FilterResultsKeySet;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.search.root.VisualSearchLoggerHelper;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchMyTagAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Blackboard mBlackboard;
    private MediaData mData;
    private final View.OnTouchListener mOnTouchListener = new i(5, this);
    private final String mScreenId;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTitleText;

        public ItemViewHolder(View view) {
            super(view);
            this.mTitleText = (TextView) view.findViewById(R.id.tag_title);
        }

        public void setTitle(String str) {
            this.mTitleText.setText(StringCompat.excludePrefix(str, "#"));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MoreViewHolder extends RecyclerView.ViewHolder {
        private final View mMoreView;

        public MoreViewHolder(View view) {
            super(view);
            this.mMoreView = view.findViewById(R.id.tag_more_btn);
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            this.mMoreView.setOnClickListener(onClickListener);
        }
    }

    public SearchMyTagAdapter(Blackboard blackboard, String str) {
        this.mBlackboard = blackboard;
        this.mScreenId = str;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$2(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        this.mBlackboard.postEvent(EventMessage.obtain(8502));
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(MediaItem mediaItem, View view) {
        onItemClick(mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$1(View view) {
        onMoreClick();
    }

    private void onItemClick(MediaItem mediaItem) {
        int tagType = mediaItem.getTagType();
        String category = mediaItem.getCategory();
        String subCategory = mediaItem.getSubCategory();
        String searchLocationKey = LocationKey.getSearchLocationKey("location://search/fileList/Category/MyTag", subCategory);
        this.mBlackboard.erase(searchLocationKey);
        String build = new UriBuilder(searchLocationKey).appendArg(KeywordBundleWrapper.BUNDLE_KEY_CATEGORY, category).appendArg("sub", subCategory).appendArg("title", mediaItem.getTitle()).appendArg("term", FilterResultsKeySet.getField(category, subCategory, tagType)).appendArg("collect_keyword", SearchWordCollector.getCollectedKeyword(mediaItem.getTitle(), subCategory, category)).appendArg("collect_type", SearchWordCollector.getVisualSearchType(category)).build();
        VisualSearchLoggerHelper.postAnalyticsOnClickCategoryItem(this.mScreenId, category, subCategory, false);
        this.mBlackboard.post("command://MoveURL", build);
    }

    private void onMoreClick() {
        VisualSearchLoggerHelper.postAnalyticsOnClickVieMoreArrow(this.mScreenId, "location://search/fileList/Category/MyTag");
        this.mBlackboard.erase("location://search/fileList/Category/MyTag");
        this.mBlackboard.post("command://MoveURL", "location://search/fileList/Category/MyTag");
    }

    public int getItemCount() {
        int i2;
        MediaData mediaData = this.mData;
        if (mediaData != null) {
            i2 = mediaData.getCount();
        } else {
            i2 = 0;
        }
        return Math.min(5, i2);
    }

    public int getItemViewType(int i2) {
        if (i2 >= 4) {
            return 1;
        }
        return 0;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        MediaData mediaData;
        if (i2 >= 4 || (mediaData = this.mData) == null) {
            ((MoreViewHolder) viewHolder).setOnClickListener(new a(11, this));
            return;
        }
        MediaItem read = mediaData.read(i2);
        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        if (read != null) {
            itemViewHolder.setTitle(read.getTitle());
            itemViewHolder.itemView.setOnClickListener(new g(2, this, read));
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        if (i2 == 0) {
            View d = C0086a.d(viewGroup, R.layout.recycler_item_recommendation_tag_layout, viewGroup, false);
            d.setOnTouchListener(this.mOnTouchListener);
            return new ItemViewHolder(d);
        }
        View d2 = C0086a.d(viewGroup, R.layout.recycler_item_tag_more_btn, viewGroup, false);
        d2.setOnTouchListener(this.mOnTouchListener);
        return new MoreViewHolder(d2);
    }

    public void setData(MediaData mediaData) {
        this.mData = mediaData;
    }
}
