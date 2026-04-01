package com.samsung.android.gallery.app.ui.list.search.category;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryActivityItemViewHolder;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryLocationHeaderViewHolder;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryLocationItemViewHolder;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryMyQueryItemViewHolder;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryPeopleItemViewHolder;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryScreenShotItemViewHolder;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryShortcutButtonItemViewHolder;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryShotModeItemViewHolder;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryShotModeItemViewHolderV2;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryStoriesItemViewHolder;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryTagItemViewHolder;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryTitleCountViewHolder;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.ScreenShotExpansionImageTitleCountViewHolder;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.ShotModeImageTitleIconCountViewHolder;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleCountViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryItemViewHolderFactory {
    private static final int ITEM_LAYOUT_FOR_PEOPLE_ID;
    private static final int ITEM_LAYOUT_ID_FOR_SHOT_MODE;
    protected final LayoutInflater mLayoutInflater;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TitleDividerViewHolder extends ListViewHolder {
        private TextView mTitle;

        public TitleDividerViewHolder(View view, int i2) {
            super(view, i2);
        }

        private boolean isFirstDivider() {
            if (getViewType() == -1) {
                return true;
            }
            return false;
        }

        public void bind(MediaItem mediaItem) {
            super.bind(mediaItem);
            ViewUtils.setText(this.mTitle, mediaItem.getTitle());
        }

        public void bindView(View view) {
            int i2;
            this.mTitle = (TextView) view.findViewById(R.id.title);
            Resources resources = view.getResources();
            if (isFirstDivider()) {
                i2 = R.dimen.search_category_location_first_title_margin_top;
            } else {
                i2 = R.dimen.search_category_location_title_margin_top;
            }
            ViewMarginUtils.setTopMargin(view.findViewById(R.id.divider_layout), resources.getDimensionPixelOffset(i2));
        }
    }

    static {
        int i2;
        int i7;
        boolean z = PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71;
        if (z) {
            i2 = R.layout.recycler_category_item_people_layout_v3;
        } else {
            i2 = R.layout.recycler_category_item_people_layout_v2;
        }
        ITEM_LAYOUT_FOR_PEOPLE_ID = i2;
        if (z) {
            i7 = R.layout.recycler_category_item_shot_mode_layout_v71;
        } else {
            i7 = R.layout.recycler_category_item_shot_mode_layout_v61;
        }
        ITEM_LAYOUT_ID_FOR_SHOT_MODE = i7;
    }

    public CategoryItemViewHolderFactory(Context context) {
        this(LayoutInflater.from(context));
    }

    private ListViewHolder createActivityItemViewHolder(ViewGroup viewGroup, int i2) {
        return new CategoryActivityItemViewHolder(this.mLayoutInflater.inflate(R.layout.recycler_category_item_shot_mode_layout_v71, viewGroup, false), i2);
    }

    private ListViewHolder createCategoryTitleCountItemViewHolder(ViewGroup viewGroup, int i2, boolean z) {
        return new CategoryTitleCountViewHolder(this.mLayoutInflater.inflate(getCategoryTitleCountItemLayoutId(z), viewGroup, false), i2);
    }

    private ListViewHolder createDocumentItemViewHolder(ViewGroup viewGroup, int i2) {
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            return new ImageTitleCountViewHolder(this.mLayoutInflater.inflate(R.layout.recycler_item_category_list_layout, viewGroup, false), i2);
        }
        return createLocationItemViewHolder(viewGroup, i2);
    }

    private ListViewHolder createLocationItemViewHolder(ViewGroup viewGroup, int i2) {
        return new CategoryLocationItemViewHolder(this.mLayoutInflater.inflate(getLocationItemLayoutId(), viewGroup, false), i2, PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85);
    }

    private ListViewHolder createMyQueryItemViewHolder(ViewGroup viewGroup, int i2) {
        return new CategoryMyQueryItemViewHolder(this.mLayoutInflater.inflate(R.layout.recycler_category_item_my_query_layout, viewGroup, false), i2);
    }

    private ListViewHolder createScreenShotItemViewHolder(ViewGroup viewGroup, int i2) {
        return new CategoryScreenShotItemViewHolder(this.mLayoutInflater.inflate(R.layout.recycler_category_item_screenshot_layout, viewGroup, false), i2);
    }

    private ListViewHolder createScreenShotListCategoryItemViewHolder(ViewGroup viewGroup, int i2) {
        return new ScreenShotExpansionImageTitleCountViewHolder(this.mLayoutInflater.inflate(R.layout.recycler_item_category_list_layout, viewGroup, false), i2);
    }

    private ListViewHolder createShortcutButtonItemViewHolder(ViewGroup viewGroup, int i2) {
        return new CategoryShortcutButtonItemViewHolder(this.mLayoutInflater.inflate(R.layout.recycler_category_item_shortcut_button_layout, viewGroup, false), i2);
    }

    private ListViewHolder createShotModeItemViewHolder(ViewGroup viewGroup, int i2) {
        View inflate = this.mLayoutInflater.inflate(ITEM_LAYOUT_ID_FOR_SHOT_MODE, viewGroup, false);
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            return new CategoryShotModeItemViewHolderV2(inflate, i2);
        }
        return new CategoryShotModeItemViewHolder(inflate, i2);
    }

    private ListViewHolder createShotModeListItemViewHolder(ViewGroup viewGroup, int i2) {
        return new ShotModeImageTitleIconCountViewHolder(this.mLayoutInflater.inflate(R.layout.recycler_item_category_list_layout, viewGroup, false), i2);
    }

    private ListViewHolder createStoriesItemViewHolder(ViewGroup viewGroup, int i2) {
        return new CategoryStoriesItemViewHolder(this.mLayoutInflater.inflate(R.layout.recycler_category_item_stories_layout, viewGroup, false), i2);
    }

    private ListViewHolder createTagItemViewHolder(ViewGroup viewGroup, int i2, boolean z) {
        return new CategoryTagItemViewHolder(this.mLayoutInflater.inflate(R.layout.recycler_item_recommendation_tag_layout, viewGroup, false), i2, z);
    }

    private ListViewHolder createTitleDividerViewHolder(ViewGroup viewGroup, int i2) {
        return new TitleDividerViewHolder(this.mLayoutInflater.inflate(R.layout.recycler_category_item_title_divider, viewGroup, false), i2);
    }

    private int getCategoryTitleCountItemLayoutId(boolean z) {
        if (z) {
            return R.layout.recycler_category_item_aspect_ratio_layout_v2;
        }
        if (PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61) {
            return R.layout.recycler_category_item_linear_layout;
        }
        return R.layout.recycler_category_item_image_title_count_layout_v2;
    }

    private static int getLocationItemLayoutId() {
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            return R.layout.recycler_category_item_location_layout_85;
        }
        if (PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61) {
            return R.layout.recycler_category_item_square_layout;
        }
        return R.layout.recycler_category_item_aspect_ratio_layout_v2;
    }

    public ListViewHolder createHeaderViewHolder(int i2, ViewGroup viewGroup) {
        return new HeaderViewHolder(this.mLayoutInflater.inflate(i2, viewGroup, false), -3);
    }

    public ListViewHolder createListViewHolder(ViewGroup viewGroup, int i2) {
        return createListViewHolder(viewGroup, i2, false);
    }

    public ListViewHolder createLocationCarouselHeaderViewHolder(ViewGroup viewGroup, int i2) {
        return new CategoryLocationHeaderViewHolder(this.mLayoutInflater.inflate(R.layout.recycler_category_item_location_header_layout, viewGroup, false), i2);
    }

    public ListViewHolder createLocationCarouselItemViewHolder(ViewGroup viewGroup, int i2) {
        return new CategoryLocationItemViewHolder(this.mLayoutInflater.inflate(R.layout.recycler_category_item_location_layout, viewGroup, false), i2, false);
    }

    public ListViewHolder createPeopleItemViewHolder(ViewGroup viewGroup, int i2, boolean z) {
        return new CategoryPeopleItemViewHolder(this.mLayoutInflater.inflate(getPeopleItemLayoutId(z), viewGroup, false), i2, z);
    }

    public int getPeopleItemLayoutId(boolean z) {
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            if (z) {
                return R.layout.recycler_category_item_removable_creature_layout_85;
            }
            return R.layout.recycler_category_item_creature_linear_layout_85;
        } else if (!PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61 || z) {
            return ITEM_LAYOUT_FOR_PEOPLE_ID;
        } else {
            return R.layout.recycler_category_item_linear_layout;
        }
    }

    public CategoryItemViewHolderFactory(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
    }

    public ListViewHolder createListViewHolder(ViewGroup viewGroup, int i2, boolean z) {
        if (i2 == 1) {
            return createTagItemViewHolder(viewGroup, i2, z);
        }
        if (i2 == 2 || i2 == 11) {
            return createPeopleItemViewHolder(viewGroup, i2, z);
        }
        if (i2 == 3) {
            return createLocationItemViewHolder(viewGroup, i2);
        }
        if (i2 == 7) {
            return createDocumentItemViewHolder(viewGroup, i2);
        }
        if (i2 == 4) {
            return createShotModeItemViewHolder(viewGroup, i2);
        }
        if (i2 == 15) {
            return createShotModeListItemViewHolder(viewGroup, i2);
        }
        if (i2 == -1 || i2 == -2) {
            return createTitleDividerViewHolder(viewGroup, i2);
        }
        if (i2 == 8) {
            return createActivityItemViewHolder(viewGroup, i2);
        }
        if (i2 == 10) {
            return createMyQueryItemViewHolder(viewGroup, i2);
        }
        if (i2 == 12) {
            return createScreenShotItemViewHolder(viewGroup, i2);
        }
        if (i2 == 13) {
            return createStoriesItemViewHolder(viewGroup, i2);
        }
        if (i2 == 14) {
            return createShortcutButtonItemViewHolder(viewGroup, i2);
        }
        if (i2 == 16) {
            return createScreenShotListCategoryItemViewHolder(viewGroup, i2);
        }
        return createCategoryTitleCountItemViewHolder(viewGroup, i2, z);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class HeaderViewHolder extends ListViewHolder {
        public HeaderViewHolder(View view, int i2) {
            super(view, i2);
        }

        public void recycle() {
            super.recycle();
            ViewUtils.removeAllViews((ViewGroup) this.itemView);
        }

        public void bindView(View view) {
        }
    }
}
