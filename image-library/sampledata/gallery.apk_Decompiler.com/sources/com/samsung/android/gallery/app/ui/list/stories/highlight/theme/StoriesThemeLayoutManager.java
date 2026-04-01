package com.samsung.android.gallery.app.ui.list.stories.highlight.theme;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.listview.GalleryGridLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesThemeLayoutManager extends GalleryGridLayoutManager {
    protected final Context mContext;
    private final boolean mIsRTL = Features.isEnabled(Features.IS_RTL);
    private final int mItemMargin;
    private final int mListMargin;
    private final RecyclerView mRecyclerView;

    public StoriesThemeLayoutManager(Context context, RecyclerView recyclerView) {
        super(context, 1);
        setOrientation(0);
        this.mContext = context;
        this.mRecyclerView = recyclerView;
        this.mItemMargin = context.getResources().getDimensionPixelOffset(R.dimen.stories_filter_item_margin);
        this.mListMargin = context.getResources().getDimensionPixelOffset(R.dimen.stories_filter_list_margin);
    }

    private int getItemLeftMargin(int i2) {
        if (i2 == 0) {
            if (this.mIsRTL) {
                return this.mItemMargin;
            }
            return this.mListMargin;
        } else if (i2 != getItemCount() - 1) {
            return this.mItemMargin;
        } else {
            if (this.mIsRTL) {
                return this.mListMargin;
            }
            return this.mItemMargin;
        }
    }

    private int getItemRightMargin(int i2) {
        if (i2 == 0) {
            if (this.mIsRTL) {
                return this.mListMargin;
            }
            return this.mItemMargin;
        } else if (i2 != getItemCount() - 1) {
            return this.mItemMargin;
        } else {
            if (this.mIsRTL) {
                return this.mItemMargin;
            }
            return this.mListMargin;
        }
    }

    private ListViewHolder getListViewHolder(View view) {
        return (ListViewHolder) this.mRecyclerView.findContainingViewHolder(view);
    }

    private void updateLayout(View view) {
        ListViewHolder listViewHolder = getListViewHolder(view);
        if (listViewHolder != null && listViewHolder.getItemViewType() == 0) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            int absoluteAdapterPosition = listViewHolder.getAbsoluteAdapterPosition();
            marginLayoutParams.setMargins(getItemLeftMargin(absoluteAdapterPosition), 0, getItemRightMargin(absoluteAdapterPosition), 0);
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public void addView(View view, int i2) {
        super.addView(view, i2);
        updateLayout(view);
    }
}
