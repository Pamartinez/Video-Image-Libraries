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
public class CategoryShotModeItemViewHolder extends ListViewHolder {
    protected TextView mCount;
    private View mDivider;
    protected ImageView mThumbnail;
    protected TextView mTitleText;

    public CategoryShotModeItemViewHolder(View view, int i2) {
        super(view, i2);
    }

    private String getTitleText(MediaItem mediaItem) {
        return mediaItem.getTitle();
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        this.mTitleText.setText(getTitleText(mediaItem));
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            ViewUtils.setText(this.mCount, StringCompat.toReadableCount(mediaItem.getCount()));
        }
        try {
            this.mThumbnail.setImageDrawable(this.itemView.getContext().getDrawable(CategoryType.VISUAL_SEARCH_SUB_CATEGORY_SHOT_MODE_ICON_MAP.get(mediaItem.getSubCategory()).intValue()));
        } catch (Exception e) {
            Log.se("CategoryShotModeItemViewHolder", e + ArcCommonLog.TAG_COMMA + mediaItem.getSubCategory());
        }
    }

    public void bindView(View view) {
        this.mThumbnail = (ImageView) view.findViewById(R.id.shot_mode_icon);
        this.mTitleText = (TextView) view.findViewById(R.id.shot_mode_title);
        this.mCount = (TextView) view.findViewById(R.id.shot_mode_count);
        this.mDivider = view.findViewById(R.id.item_divider);
        view.findViewById(R.id.recycler_view_item).setOnClickListener(new C0496a(1, this));
    }

    public void onClick(View view) {
        onItemClickInternal(0);
    }
}
