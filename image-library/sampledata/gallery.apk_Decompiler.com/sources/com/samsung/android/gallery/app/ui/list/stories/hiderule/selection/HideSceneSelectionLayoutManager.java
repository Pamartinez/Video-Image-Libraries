package com.samsung.android.gallery.app.ui.list.stories.hiderule.selection;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesLayoutManager;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HideSceneSelectionLayoutManager extends PicturesLayoutManager {
    private static final boolean STORY_ONE_UI_85 = PreferenceFeatures.OneUi8x.STORY_ONE_UI_85;
    private int mItemPaddingBottom;
    private int mItemPaddingSide;
    private int mItemPaddingTop;
    private int mViewPaddingSide;

    public HideSceneSelectionLayoutManager(Context context, int i2) {
        super(context, i2);
        initDimens(context);
    }

    private int getContentArea(int i2) {
        return ((this.mItemPaddingSide * 2) + getItemWidth(i2)) * i2;
    }

    private int getItemWidth(int i2) {
        return ((getWidth() - ((this.mItemPaddingSide * i2) * 2)) - (this.mViewPaddingSide * 2)) / i2;
    }

    public int getHintPaddingLeft(int i2) {
        return getSpacing(i2);
    }

    public int getHintWidthSpace(int i2) {
        return getContentArea(i2);
    }

    public int getPaddingRight() {
        return getSpacing(getSpanCount());
    }

    public int getSpacing(int i2) {
        return (getWidth() - getContentArea(i2)) / 2;
    }

    public void initDimens(Context context) {
        int i2;
        int i7;
        int i8;
        if (context != null && context.getResources() != null) {
            Resources resources = context.getResources();
            boolean z = STORY_ONE_UI_85;
            int i10 = R.dimen.search_category_people_item_vertical_margin_v71;
            if (z) {
                i2 = resources.getDimensionPixelOffset(R.dimen.search_category_people_item_vertical_margin_v71);
            } else {
                i2 = resources.getDimensionPixelOffset(R.dimen.story_hide_scene_selection_item_padding_top);
            }
            this.mItemPaddingTop = i2;
            if (!z) {
                i10 = R.dimen.story_hide_scene_selection_item_padding_bottom;
            }
            this.mItemPaddingBottom = resources.getDimensionPixelOffset(i10);
            if (z) {
                i7 = resources.getDimensionPixelOffset(R.dimen.search_item_card_people_thumbnail_spacing_expansion_85);
            } else {
                i7 = resources.getDimensionPixelOffset(R.dimen.story_hide_scene_selection_item_padding_horizontal);
            }
            this.mItemPaddingSide = i7;
            if (z) {
                i8 = resources.getDimensionPixelOffset(R.dimen.search_item_card_people_thumbnail_spacing_expansion_85);
            } else {
                i8 = resources.getDimensionPixelOffset(R.dimen.story_hide_scene_selection_view_padding_horizontal);
            }
            this.mViewPaddingSide = i8;
        }
    }

    public boolean updateTimelineWidth() {
        return false;
    }

    public void updateViewSize(View view, int i2, int i7) {
        if (i2 == 0) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.width = getContentArea(i7) / getRealGridSize(i7);
            marginLayoutParams.height = -2;
            view.setLayoutParams(marginLayoutParams);
            int i8 = this.mItemPaddingSide;
            view.setPadding(i8, this.mItemPaddingTop, i8, this.mItemPaddingBottom);
        }
    }
}
