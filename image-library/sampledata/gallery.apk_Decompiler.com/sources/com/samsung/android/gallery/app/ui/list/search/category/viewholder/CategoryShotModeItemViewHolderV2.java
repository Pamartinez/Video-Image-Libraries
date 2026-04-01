package com.samsung.android.gallery.app.ui.list.search.category.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.type.CategoryType;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import o5.C0496a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryShotModeItemViewHolderV2 extends ListViewHolder {
    protected final String TAG = getClass().getSimpleName();
    protected TextView mCount;
    private View mDivider;
    protected ImageView mThumbnail;
    protected TextView mTitleText;

    public CategoryShotModeItemViewHolderV2(View view, int i2) {
        super(view, i2);
    }

    private void setCount(MediaItem mediaItem) {
        if (showCount(mediaItem)) {
            ViewUtils.setText(this.mCount, StringCompat.toReadableCount(mediaItem.getCount()));
        } else {
            ViewUtils.setVisibility(this.mCount, 8);
        }
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        this.mTitleText.setText(getTitleText(mediaItem));
        setCount(mediaItem);
        try {
            this.mThumbnail.setImageDrawable(this.itemView.getContext().getDrawable(getDrawableResId(mediaItem)));
        } catch (Exception e) {
            String str = this.TAG;
            Log.se(str, e + ArcCommonLog.TAG_COMMA + mediaItem.getSubCategory());
        }
    }

    public void bindView(View view) {
        this.mThumbnail = (ImageView) view.findViewById(R.id.item_icon);
        this.mTitleText = (TextView) view.findViewById(R.id.item_title);
        this.mCount = (TextView) view.findViewById(R.id.item_count);
        this.mDivider = view.findViewById(R.id.item_divider);
        if (!PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            int color = view.getContext().getColor(R.color.search_card_header_title);
            this.mTitleText.setTextColor(color);
            this.mThumbnail.setColorFilter(color);
        }
        view.findViewById(R.id.recycler_view_item).setOnClickListener(new C0496a(2, this));
    }

    public int getDrawableResId(MediaItem mediaItem) {
        return CategoryType.VISUAL_SEARCH_SUB_CATEGORY_SHOT_MODE_ICON_MAP.get(mediaItem.getSubCategory()).intValue();
    }

    public String getTitleText(MediaItem mediaItem) {
        return mediaItem.getTitle();
    }

    public void onClick(View view) {
        onItemClickInternal(0);
    }

    public boolean showCount(MediaItem mediaItem) {
        return true;
    }

    public void updateDividerVisibility(boolean z) {
        int i2;
        View view = this.mDivider;
        if (z) {
            i2 = 0;
        } else {
            i2 = 4;
        }
        ViewUtils.setVisibility(view, i2);
    }
}
