package com.samsung.android.gallery.app.ui.list.search.category;

import N2.j;
import android.content.Context;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;
import j5.C0471a;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CategoryPropertyHelper {
    private static final ConcurrentHashMap<String, CategoryPropertyHelper> sMap = new ConcurrentHashMap<>();
    protected final boolean mIsExpansionMode;
    protected final String mLocationKey;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ActivityPropertyHelperImpl extends CategoryPropertyHelper {
        public ActivityPropertyHelperImpl(String str, boolean z) {
            super(str, z);
        }

        public boolean enableMoreButton(Context context, int i2) {
            return false;
        }

        public int getCategoryTitleResId() {
            return R.string.category_activity;
        }

        public int[] getColumnCount(Context context) {
            return getIntArray(context, R.array.visual_search_activity_item_column_count);
        }

        public int getFloatingItemVerticalMargin(Context context) {
            return getDimensionPixelOffset(context, R.dimen.search_floating_recommend_item_list_margin_vertical_small);
        }

        public int getItemMarginBottom() {
            return R.dimen.search_category_shot_mode_margin_bottom;
        }

        public int getItemMarginTop() {
            return R.dimen.search_category_shot_mode_margin_top;
        }

        public int getItemViewType() {
            return 8;
        }

        public int getMaxDisplayCountOnCard(Context context) {
            return -1;
        }

        public int getRoundCornerResId() {
            return R.dimen.button_shape_border_radius;
        }

        public String getScreenId() {
            return null;
        }

        public boolean hasItemMarginTopOnCard() {
            return !PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71;
        }

        public boolean hasThumbnail() {
            return false;
        }

        public boolean useGridLayoutOnCard() {
            return PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CategoryDocumentPropertyHelperImpl extends DocumentsPropertyHelperImpl {
        public CategoryDocumentPropertyHelperImpl(String str) {
            super(str, true);
        }

        public int[] getColumnCount(Context context) {
            return getIntArray(context, R.array.category_list_item_column_count);
        }

        public int getItemHorizontalSpacingResId() {
            return -1;
        }

        public int getItemViewType() {
            return 7;
        }

        public int getRoundCornerResId() {
            return R.dimen.album_view_corner_radius_list_blur;
        }

        public int getThumbnailBorder() {
            return R.drawable.category_list_thumbnail_border;
        }

        public boolean supportSubTitle() {
            return false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ClusterCategoryPropertyHelperImpl extends CategoryPropertyHelper {
        public ClusterCategoryPropertyHelperImpl(String str, boolean z) {
            super(str, z);
        }

        private int checkClusterType() {
            if (this.mLocationKey.equals("location://search/fileList/CarouselCluster")) {
                return 9;
            }
            return 3;
        }

        public boolean enableMoreButton(Context context, int i2) {
            return false;
        }

        public int getCategoryTitleResId() {
            j.y(new StringBuilder("lkey: "), this.mLocationKey, "CategoryPropertyHelper");
            if (this.mLocationKey.equals("location://search/fileList/ClusterCategoryAlbum")) {
                return R.string.album;
            }
            if (this.mLocationKey.equals("location://search/fileList/CarouselCluster")) {
                return -1;
            }
            return R.string.location;
        }

        public int[] getColumnCount(Context context) {
            return getIntArray(context, R.array.category_tag_item_column_count);
        }

        public int getItemHorizontalSpacingResId() {
            if (this.mIsExpansionMode) {
                return CategoryPropertyHelper.super.getItemHorizontalSpacingResId();
            }
            return R.dimen.search_category_item_horizontal_spacing_61;
        }

        public int getItemViewType() {
            return checkClusterType();
        }

        public int getMaxDisplayCountOnCard(Context context) {
            return 0;
        }

        public int getRoundCornerResId() {
            return R.dimen.search_item_corner_radius;
        }

        public String getScreenId() {
            return null;
        }

        public boolean hasThumbnail() {
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ClusteredDocumentPropertyHelperImpl extends DocumentsPropertyHelperImpl {
        public ClusteredDocumentPropertyHelperImpl(String str) {
            super(str, true);
        }

        public int[] getColumnCount(Context context) {
            return getIntArray(context, R.array.clustered_category_item_column_count);
        }

        public int getItemHorizontalSpacingResId() {
            return R.dimen.search_category_location_item_gap;
        }

        public int getItemViewType() {
            return 7;
        }

        public String getScreenId() {
            return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_THINGS_SCENERY_CATEGORY.toString();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ClusteredLocationPropertyHelperImpl extends LocationPropertyHelperImpl {
        public ClusteredLocationPropertyHelperImpl(String str) {
            super(str, true);
        }

        public int[] getColumnCount(Context context) {
            return getIntArray(context, R.array.clustered_category_item_column_count);
        }

        public int getItemHorizontalSpacingResId() {
            return R.dimen.search_category_location_item_gap;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ClusteredLocationPropertyHelperImpl85 extends ClusteredLocationPropertyHelperImpl {
        public ClusteredLocationPropertyHelperImpl85(String str) {
            super(str);
        }

        public int getCardListTopMargin(Context context) {
            if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
                return getDimensionPixelOffset(context, R.dimen.search_card_margin_top_8x);
            }
            return getDimensionPixelOffset(context, R.dimen.search_card_margin_top_61);
        }

        public int getItemHorizontalSpacingResId() {
            return R.dimen.search_category_location_spacing_expansion_85;
        }

        public int getRoundCornerResId() {
            return R.dimen.search_item_corner_radius_85;
        }

        public int getThumbnailBorder() {
            return R.drawable.search_thumbnail_border_85;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DocumentsPropertyHelperImpl extends CategoryPropertyHelper {
        public DocumentsPropertyHelperImpl(String str, boolean z) {
            super(str, z);
        }

        public boolean enableMoreButton(Context context, int i2) {
            return false;
        }

        public int getCategoryTitleResId() {
            return R.string.documents;
        }

        public int[] getColumnCount(Context context) {
            return getIntArray(context, R.array.visual_search_documents_item_column_count);
        }

        public int getItemViewType() {
            return 5;
        }

        public int getMaxDisplayCountOnCard(Context context) {
            return -1;
        }

        public int getRoundCornerResId() {
            return R.dimen.search_item_corner_radius;
        }

        public String getScreenId() {
            return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_THINGS_SCENERY_CATEGORY.toString();
        }

        public boolean hasThumbnail() {
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DocumentsPropertyHelperImpl61 extends DocumentsPropertyHelperImpl {
        public DocumentsPropertyHelperImpl61(String str, boolean z) {
            super(str, z);
        }

        public boolean enableMoreButton(Context context, int i2) {
            return true;
        }

        public int getCardListHorizontalPaddingResId() {
            return R.dimen.search_category_item_horizontal_padding_61;
        }

        public int[] getColumnCount(Context context) {
            int i2;
            if (this.mIsExpansionMode) {
                i2 = R.array.visual_search_scenes_item_column_count_expansion;
            } else {
                i2 = R.array.visual_search_documents_item_column_count;
            }
            return getIntArray(context, i2);
        }

        public int getItemHorizontalSpacingResId() {
            if (this.mIsExpansionMode) {
                return CategoryPropertyHelper.super.getItemHorizontalSpacingResId();
            }
            return R.dimen.search_category_item_horizontal_spacing_61;
        }

        public int getMaxDisplayCountOnCard(Context context) {
            return -1;
        }

        public String getScreenId() {
            return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_THINGS_SCENERY_CATEGORY.toString();
        }

        public int getThumbnailBorder() {
            return R.drawable.search_thumbnail_border_61;
        }

        public boolean hasItemMarginTopOnCard() {
            return this.mIsExpansionMode;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DocumentsPropertyHelperImpl85 extends DocumentsPropertyHelperImpl61 {
        public DocumentsPropertyHelperImpl85(String str, boolean z) {
            super(str, z);
        }

        public int getRoundCornerResId() {
            return R.dimen.search_item_corner_radius_85;
        }

        public int getThumbnailBorder() {
            return R.drawable.search_thumbnail_border_85;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LocationMapPropertyHelperImpl extends LocationPropertyHelperImpl {
        public LocationMapPropertyHelperImpl(String str, boolean z) {
            super(str, z);
        }

        public boolean enableMoreButton(Context context, int i2) {
            return true;
        }

        public int getCardListHorizontalPaddingResId() {
            return R.dimen.search_category_item_horizontal_padding_61;
        }

        public int getItemHorizontalSpacingResId() {
            return R.dimen.search_category_item_horizontal_spacing_61;
        }

        public int getThumbnailBorder() {
            return R.drawable.search_thumbnail_border_61;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LocationMapPropertyHelperImpl85 extends LocationMapPropertyHelperImpl {
        public LocationMapPropertyHelperImpl85(String str, boolean z) {
            super(str, z);
        }

        public int getRoundCornerResId() {
            return R.dimen.search_item_corner_radius_85;
        }

        public int getThumbnailBorder() {
            return R.drawable.search_thumbnail_border_85;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LocationPropertyHelperImpl extends CategoryPropertyHelper {
        public LocationPropertyHelperImpl(String str, boolean z) {
            super(str, z);
        }

        public boolean enableMoreButton(Context context, int i2) {
            if (getMaxDisplayCountOnCard(context) < i2) {
                return true;
            }
            return false;
        }

        public String getCategorySubTitle(Context context, int i2) {
            return context.getResources().getQuantityString(R.plurals.city_quantity_string, i2, new Object[]{Integer.valueOf(i2)});
        }

        public int getCategoryTitleResId() {
            return R.string.searchview_location;
        }

        public int[] getColumnCount(Context context) {
            return getIntArray(context, R.array.visual_search_location_item_column_count);
        }

        public int getItemViewType() {
            return 3;
        }

        public int getMaxDisplayCountOnCard(Context context) {
            return getIntArray(context, R.array.visual_search_location_max_item_count)[0];
        }

        public int getRoundCornerResId() {
            return R.dimen.search_item_corner_radius;
        }

        public String getScreenId() {
            return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_LOCATION_CATEGORY.toString();
        }

        public boolean hasItemMarginTopOnCard() {
            return this.mIsExpansionMode;
        }

        public boolean hasThumbnail() {
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MyQueryPropertyHelperImpl extends CategoryPropertyHelper {
        public MyQueryPropertyHelperImpl(String str, boolean z) {
            super(str, z);
        }

        public boolean enableMoreButton(Context context, int i2) {
            return false;
        }

        public int getCategoryTitleResId() {
            if (this.mIsExpansionMode) {
                return R.string.edit_quick_searches;
            }
            return R.string.category_quick_searches;
        }

        public int[] getColumnCount(Context context) {
            return getIntArray(context, R.array.visual_search_shot_mode_item_column_count);
        }

        public int getFloatingItemVerticalMargin(Context context) {
            return getDimensionPixelOffset(context, R.dimen.search_floating_recommend_item_list_margin_vertical);
        }

        public int getItemHorizontalSpacingResId() {
            return R.dimen.search_category_my_query_item_card_spacing;
        }

        public int getItemMarginBottom() {
            return R.dimen.search_category_my_query_item_margin_vertical;
        }

        public int getItemMarginTop() {
            return R.dimen.search_category_my_query_item_margin_vertical;
        }

        public int getItemViewType() {
            return 10;
        }

        public int getMaxDisplayCountOnCard(Context context) {
            return -1;
        }

        public int getRoundCornerResId() {
            return R.dimen.button_shape_border_radius;
        }

        public String getScreenId() {
            return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_MY_QUERY_CATEGORY.toString();
        }

        public boolean hasThumbnail() {
            return false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MyTagPropertyHelperImpl extends CategoryPropertyHelper {
        public MyTagPropertyHelperImpl(String str, boolean z) {
            super(str, z);
        }

        public boolean enableMoreButton(Context context, int i2) {
            if (getMaxDisplayCountOnCard(context) < i2) {
                return true;
            }
            return false;
        }

        public int getCategoryTitleResId() {
            if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                return R.string.my_tags;
            }
            return -1;
        }

        public int[] getColumnCount(Context context) {
            return new int[]{1};
        }

        public int getFloatingItemVerticalMargin(Context context) {
            return getDimensionPixelOffset(context, R.dimen.search_floating_recommend_item_list_margin_vertical);
        }

        public int getItemMarginBottom() {
            return R.dimen.search_category_tag_item_margin_bottom;
        }

        public int getItemMarginTop() {
            return R.dimen.search_category_tag_item_margin_top;
        }

        public int getItemViewType() {
            return 1;
        }

        public int getMaxDisplayCountOnCard(Context context) {
            return 4;
        }

        public int getRoundCornerResId() {
            return -1;
        }

        public String getScreenId() {
            return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_TAG_CATEGORY.toString();
        }

        public boolean hasItemMarginTopOnCard() {
            return this.mIsExpansionMode;
        }

        public boolean hasThumbnail() {
            return false;
        }

        public boolean isNeedUpdateItemHorizontalMargin() {
            return false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PeopleAndPetPropertyHelperImpl61 extends PeoplePropertyHelperImpl61 {
        public PeopleAndPetPropertyHelperImpl61(String str, boolean z) {
            super(str, z);
        }

        public int getCategoryTitleResId() {
            if (IdentityCreatureUtil.isPetRecognized()) {
                return R.string.visual_search_category_people_and_pets;
            }
            return R.string.story_category_people;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PeopleAndPetPropertyHelperImpl85 extends PeoplePropertyHelperImpl85 {
        public PeopleAndPetPropertyHelperImpl85(String str, boolean z) {
            super(str, z);
        }

        public int getCategoryTitleResId() {
            if (IdentityCreatureUtil.isPetRecognized()) {
                return R.string.visual_search_category_people_and_pets;
            }
            return R.string.story_category_people;
        }

        public int getItemMarginTop() {
            if (this.mIsExpansionMode) {
                return CategoryPropertyHelper.super.getItemMarginTop();
            }
            return R.dimen.search_category_people_item_margin_top_on_card;
        }

        public boolean hasItemMarginTopOnCard() {
            return false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PeoplePropertyHelperImpl extends CategoryPropertyHelper {
        public PeoplePropertyHelperImpl(String str, boolean z) {
            super(str, z);
        }

        public boolean enableMoreButton(Context context, int i2) {
            if (PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE) {
                if (i2 > 0) {
                    return true;
                }
                return false;
            } else if (getMaxDisplayCountOnCard(context) < i2) {
                return true;
            } else {
                return false;
            }
        }

        public int getCardListHorizontalPaddingResId() {
            return R.dimen.search_card_padding_horizontal_for_people;
        }

        public int getCategoryTitleResId() {
            return R.string.story_category_people;
        }

        public int[] getColumnCount(Context context) {
            int i2;
            if (this.mIsExpansionMode) {
                i2 = R.array.visual_search_people_item_column_count_expansion;
            } else {
                i2 = R.array.visual_search_people_item_column_count;
            }
            return getIntArray(context, i2);
        }

        public int getItemHorizontalSpacingResId() {
            if (this.mIsExpansionMode) {
                return R.dimen.search_item_card_people_thumbnail_spacing_expansion;
            }
            return R.dimen.search_item_card_people_thumbnail_spacing;
        }

        public int getItemMarginBottom() {
            if (this.mIsExpansionMode) {
                return R.dimen.search_category_people_item_margin_bottom;
            }
            return -1;
        }

        public int getItemViewType() {
            return 2;
        }

        public int getMaxDisplayCountOnCard(Context context) {
            return getColumnCount(context)[0];
        }

        public int getRoundCornerResId() {
            return R.dimen.search_item_corner_radius;
        }

        public String getScreenId() {
            return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_PEOPLE_CATEGORY.toString();
        }

        public int getThumbnailBorder() {
            return R.drawable.search_people_thumbnail_border;
        }

        public int getThumbnailShape() {
            return 0;
        }

        public boolean hasItemMarginTopOnCard() {
            return this.mIsExpansionMode;
        }

        public boolean hasThumbnail() {
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PeoplePropertyHelperImpl61 extends PeoplePropertyHelperImpl {
        public PeoplePropertyHelperImpl61(String str, boolean z) {
            super(str, z);
        }

        public int getCardListHorizontalPaddingResId() {
            return R.dimen.search_category_item_horizontal_padding_61;
        }

        public int getItemHorizontalSpacingResId() {
            if (this.mIsExpansionMode) {
                return super.getItemHorizontalSpacingResId();
            }
            return R.dimen.search_category_item_horizontal_spacing_61;
        }

        public int getMaxDisplayCountOnCard(Context context) {
            return -1;
        }

        public int getRoundCornerResId() {
            return R.dimen.search_category_people_item_radius_61;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PeoplePropertyHelperImpl85 extends PeoplePropertyHelperImpl61 {
        public PeoplePropertyHelperImpl85(String str, boolean z) {
            super(str, z);
        }

        public int getItemHorizontalSpacingResId() {
            if (this.mIsExpansionMode) {
                return R.dimen.search_item_card_people_thumbnail_spacing_expansion_85;
            }
            return R.dimen.search_category_creature_item_horizontal_spacing_85;
        }

        public int getRoundCornerResId() {
            return R.dimen.search_category_people_item_radius_85;
        }

        public int getThumbnailBorder() {
            return R.drawable.search_creature_thumbnail_round_border;
        }

        public int getThumbnailShape() {
            return 1;
        }

        public boolean isNeedUpdateItemHorizontalMargin() {
            return this.mIsExpansionMode;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ScreenShotExpansionPropertyHelperImpl extends CategoryPropertyHelper {
        public ScreenShotExpansionPropertyHelperImpl(String str) {
            super(str, true);
        }

        public boolean enableMoreButton(Context context, int i2) {
            return false;
        }

        public int getCategoryTitleResId() {
            return R.string.screenshot;
        }

        public int[] getColumnCount(Context context) {
            return getIntArray(context, R.array.category_list_item_column_count);
        }

        public int getItemHorizontalSpacingResId() {
            return -1;
        }

        public int getItemMarginBottom() {
            return R.dimen.search_category_shot_mode_margin_bottom;
        }

        public int getItemMarginTop() {
            return R.dimen.search_category_shot_mode_margin_top;
        }

        public int getItemViewType() {
            return 16;
        }

        public int getMaxDisplayCountOnCard(Context context) {
            return -1;
        }

        public int getRoundCornerResId() {
            return R.dimen.album_view_corner_radius_list_blur;
        }

        public String getScreenId() {
            return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_SCREENSHOT_CATEGORY.toString();
        }

        public int getThumbnailBorder() {
            return R.drawable.category_list_thumbnail_border;
        }

        public boolean hasItemMarginTopOnCard() {
            return !PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71;
        }

        public boolean hasThumbnail() {
            return true;
        }

        public boolean supportSubTitle() {
            return false;
        }

        public boolean useGridLayoutOnCard() {
            return false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ScreenShotPropertyHelperImpl extends CategoryPropertyHelper {
        public ScreenShotPropertyHelperImpl(String str, boolean z) {
            super(str, z);
        }

        public boolean enableMoreButton(Context context, int i2) {
            return true;
        }

        public int getCardListHorizontalMarginResId() {
            return R.dimen.search_card_screenshot_list_margin_horizontal;
        }

        public int getCardListHorizontalPaddingResId() {
            return R.dimen.search_card_screenshot_list_padding_horizontal;
        }

        public int getCategoryTitleResId() {
            return R.string.screenshot;
        }

        public int[] getColumnCount(Context context) {
            return getIntArray(context, R.array.visual_search_shot_mode_item_column_count);
        }

        public int getItemHorizontalSpacingResId() {
            return R.dimen.search_category_my_query_item_card_spacing;
        }

        public int getItemMarginBottom() {
            return R.dimen.search_category_screenshot_item_padding_vertical;
        }

        public int getItemMarginTop() {
            return R.dimen.search_category_screenshot_item_padding_vertical;
        }

        public int getItemViewType() {
            return 12;
        }

        public int getMaxDisplayCountOnCard(Context context) {
            return -1;
        }

        public int getRoundCornerResId() {
            return R.dimen.button_shape_border_radius;
        }

        public String getScreenId() {
            return "";
        }

        public int getThumbnailShape() {
            return 0;
        }

        public boolean hasThumbnail() {
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ShotModePropertyHelperImpl extends CategoryPropertyHelper {
        public ShotModePropertyHelperImpl(String str, boolean z) {
            super(str, z);
        }

        public boolean enableMoreButton(Context context, int i2) {
            return false;
        }

        public int getCategoryTitleResId() {
            return R.string.shot_types;
        }

        public int[] getColumnCount(Context context) {
            int i2;
            if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                i2 = R.array.visual_search_shot_mode_item_column_count_71;
            } else {
                i2 = R.array.visual_search_shot_mode_item_column_count;
            }
            return getIntArray(context, i2);
        }

        public int getFloatingItemVerticalMargin(Context context) {
            return getDimensionPixelOffset(context, R.dimen.search_floating_recommend_item_list_margin_vertical_small);
        }

        public int getItemMarginBottom() {
            return R.dimen.search_category_shot_mode_margin_bottom;
        }

        public int getItemMarginTop() {
            return R.dimen.search_category_shot_mode_margin_top;
        }

        public int getItemViewType() {
            return 4;
        }

        public int getMaxDisplayCountOnCard(Context context) {
            return -1;
        }

        public int getRoundCornerResId() {
            return R.dimen.button_shape_border_radius;
        }

        public String getScreenId() {
            return null;
        }

        public boolean hasItemMarginTopOnCard() {
            return !PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71;
        }

        public boolean hasThumbnail() {
            return false;
        }

        public boolean useGridLayoutOnCard() {
            return PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class StoriesPropertyHelperImpl extends CategoryPropertyHelper {
        public StoriesPropertyHelperImpl(String str) {
            super(str, false);
        }

        public boolean enableMoreButton(Context context, int i2) {
            return true;
        }

        public int getCategoryTitleResId() {
            return R.string.stories;
        }

        public int[] getColumnCount(Context context) {
            return new int[0];
        }

        public int getItemViewType() {
            return 13;
        }

        public int getMaxDisplayCountOnCard(Context context) {
            return -1;
        }

        public int getRoundCornerResId() {
            return R.dimen.search_category_people_item_radius_85;
        }

        public String getScreenId() {
            return "";
        }

        public int getThumbnailBorder() {
            return R.drawable.search_thumbnail_border_stories;
        }

        public boolean hasItemMarginTopOnCard() {
            return false;
        }

        public boolean hasThumbnail() {
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SuggestedCreaturePropertyHelper extends PeopleAndPetPropertyHelperImpl61 {
        public SuggestedCreaturePropertyHelper(String str, boolean z) {
            super(str, z);
        }

        public int getItemViewType() {
            return 11;
        }

        public String getScreenId() {
            return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_PEOPLE_PICKER.toString();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SuggestedCreaturePropertyHelper85 extends PeopleAndPetPropertyHelperImpl85 {
        public SuggestedCreaturePropertyHelper85(String str, boolean z) {
            super(str, z);
        }

        public int getItemViewType() {
            return 11;
        }

        public String getScreenId() {
            return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_PEOPLE_PICKER.toString();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ThingsSceneryPropertyHelperImpl extends CategoryPropertyHelper {
        public ThingsSceneryPropertyHelperImpl(String str, boolean z) {
            super(str, z);
        }

        public boolean enableMoreButton(Context context, int i2) {
            return true;
        }

        public int getCategoryTitleResId() {
            return R.string.things_scenery;
        }

        public int[] getColumnCount(Context context) {
            int i2;
            if (this.mIsExpansionMode) {
                i2 = R.array.visual_search_scenes_item_column_count_expansion;
            } else {
                i2 = R.array.visual_search_scenes_item_column_count;
            }
            return getIntArray(context, i2);
        }

        public int getHeaderRoundCornerResId() {
            return R.dimen.search_category_scene_card_radius;
        }

        public int getItemViewType() {
            return 6;
        }

        public int getMaxDisplayCountOnCard(Context context) {
            return 0;
        }

        public int getRoundCornerResId() {
            return R.dimen.search_item_corner_radius;
        }

        public String getScreenId() {
            return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_THINGS_SCENERY_CATEGORY.toString();
        }

        public boolean hasItemMarginTopOnCard() {
            return this.mIsExpansionMode;
        }

        public boolean hasRoundedHeader() {
            return PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61;
        }

        public boolean hasThumbnail() {
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class categoryShotModePropertyHelperImpl extends ShotModePropertyHelperImpl {
        public categoryShotModePropertyHelperImpl(String str) {
            super(str, true);
        }

        public int[] getColumnCount(Context context) {
            return getIntArray(context, R.array.category_list_item_column_count);
        }

        public int getItemHorizontalSpacingResId() {
            return -1;
        }

        public int getItemViewType() {
            return 15;
        }

        public int getRoundCornerResId() {
            return R.dimen.album_view_corner_radius_list_blur;
        }

        public int getThumbnailBorder() {
            return R.drawable.category_list_thumbnail_border;
        }

        public boolean hasThumbnail() {
            return true;
        }

        public boolean useGridLayoutOnCard() {
            return false;
        }
    }

    public CategoryPropertyHelper(String str, boolean z) {
        this.mLocationKey = str;
        this.mIsExpansionMode = z;
    }

    /* access modifiers changed from: private */
    public static CategoryPropertyHelper createCategoryProperty(String str, boolean z) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -2111642096:
                if (str.equals("location://search/fileList/Category/MyTag")) {
                    c5 = 0;
                    break;
                }
                break;
            case -2106739634:
                if (str.equals("location://search/fileList/Category/Scene")) {
                    c5 = 1;
                    break;
                }
                break;
            case -2065663554:
                if (str.equals("location://search/fileList/Category/MyQuery")) {
                    c5 = 2;
                    break;
                }
                break;
            case -2004863436:
                if (str.equals("location://search/fileList/ClusterCategoryPeople")) {
                    c5 = 3;
                    break;
                }
                break;
            case -1954365376:
                if (str.equals("location://search/fileList/Category/PeopleAndPetsHide")) {
                    c5 = 4;
                    break;
                }
                break;
            case -1879444950:
                if (str.equals("location://search/fileList/ClusterCategoryAlbum")) {
                    c5 = 5;
                    break;
                }
                break;
            case -1665677457:
                if (str.equals("location://search/fileList/SuggestedCreature")) {
                    c5 = 6;
                    break;
                }
                break;
            case -1331969369:
                if (str.equals("location://search/fileList/CarouselCluster")) {
                    c5 = 7;
                    break;
                }
                break;
            case -1151134827:
                if (str.equals("location://search/fileList/Category/Stories")) {
                    c5 = 8;
                    break;
                }
                break;
            case -1141680973:
                if (str.equals("location://search/fileList/Category/Location")) {
                    c5 = 9;
                    break;
                }
                break;
            case -981774822:
                if (str.equals("location://search/fileList/ClusterCategoryLocation")) {
                    c5 = 10;
                    break;
                }
                break;
            case -968159475:
                if (str.equals("location://search/fileList/Category/People")) {
                    c5 = 11;
                    break;
                }
                break;
            case -654218758:
                if (str.equals("location://search/fileList/SelectMeAll")) {
                    c5 = 12;
                    break;
                }
                break;
            case -415231449:
                if (str.equals("location://search/fileList/SelectMe")) {
                    c5 = 13;
                    break;
                }
                break;
            case -403724275:
                if (str.equals("location://search/fileList/Category/Activity")) {
                    c5 = 14;
                    break;
                }
                break;
            case -234597023:
                if (str.equals("location://search/fileList/Category/Pet")) {
                    c5 = 15;
                    break;
                }
                break;
            case 28227497:
                if (str.equals("location://search/fileList/Category/LocationMap")) {
                    c5 = 16;
                    break;
                }
                break;
            case 101997271:
                if (str.equals("location://search/fileList/Category/HiddenPeople")) {
                    c5 = 17;
                    break;
                }
                break;
            case 241488025:
                if (str.equals("location://search/fileList/Category/CreatureSelect")) {
                    c5 = 18;
                    break;
                }
                break;
            case 396977924:
                if (str.equals("location://search/fileList/Category/ScreenShot")) {
                    c5 = 19;
                    break;
                }
                break;
            case 608528445:
                if (str.equals("location://search/fileList/Category/CreatureMultiPick")) {
                    c5 = 20;
                    break;
                }
                break;
            case 910438587:
                if (str.equals("location://search/fileList/Category/ShotMode")) {
                    c5 = 21;
                    break;
                }
                break;
            case 1108360570:
                if (str.equals("location://search/fileList/Category/Documents")) {
                    c5 = 22;
                    break;
                }
                break;
            case 1194757598:
                if (str.equals("location://search/fileList/Category/PeopleAndPets")) {
                    c5 = 23;
                    break;
                }
                break;
            case 1394625236:
                if (str.equals("location://search/fileList/Category/HiddenPeopleAndPets")) {
                    c5 = 24;
                    break;
                }
                break;
            case 1654190697:
                if (str.equals("location://search/fileList/Category/PeopleSelect")) {
                    c5 = 25;
                    break;
                }
                break;
            case 1863169447:
                if (str.equals("location://search/fileList/PeopleSelectForRelation")) {
                    c5 = 26;
                    break;
                }
                break;
            case 2097484271:
                if (str.equals("location://search/fileList/Category/PeopleHide")) {
                    c5 = 27;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return new MyTagPropertyHelperImpl(str, z);
            case 1:
                return new ThingsSceneryPropertyHelperImpl(str, z);
            case 2:
                return new MyQueryPropertyHelperImpl(str, z);
            case 3:
            case 11:
            case 17:
            case 25:
            case 26:
            case 27:
                if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
                    return new PeoplePropertyHelperImpl85(str, z);
                }
                if (PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61) {
                    return new PeoplePropertyHelperImpl61(str, z);
                }
                return new PeoplePropertyHelperImpl(str, z);
            case 4:
            case 12:
            case 13:
            case 15:
            case 18:
            case 20:
            case 23:
            case 24:
                if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
                    return new PeopleAndPetPropertyHelperImpl85(str, z);
                }
                if (PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61) {
                    return new PeopleAndPetPropertyHelperImpl61(str, z);
                }
                return new PeoplePropertyHelperImpl(str, z);
            case 5:
            case 7:
                return new ClusterCategoryPropertyHelperImpl(str, z);
            case 6:
                if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
                    return new SuggestedCreaturePropertyHelper85(str, z);
                }
                return new SuggestedCreaturePropertyHelper(str, z);
            case 8:
                return new StoriesPropertyHelperImpl(str);
            case 9:
                if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
                    return new ClusteredLocationPropertyHelperImpl85(str);
                }
                return new ClusteredLocationPropertyHelperImpl(str);
            case 10:
                return new LocationPropertyHelperImpl(str, z);
            case 14:
                return new ActivityPropertyHelperImpl(str, z);
            case 16:
                if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
                    return new LocationMapPropertyHelperImpl85(str, z);
                }
                return new LocationMapPropertyHelperImpl(str, z);
            case 19:
                return z ? new ScreenShotExpansionPropertyHelperImpl(str) : new ScreenShotPropertyHelperImpl(str, z);
            case 21:
                if (!PreferenceFeatures.OneUi8x.SUPPORT_BOTTOM_TAB_SHOT_MODE_MENU || !z) {
                    return new ShotModePropertyHelperImpl(str, z);
                }
                return new categoryShotModePropertyHelperImpl(str);
            case 22:
                if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
                    return z ? new CategoryDocumentPropertyHelperImpl(str) : new DocumentsPropertyHelperImpl85(str, false);
                }
                if (!PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61) {
                    return new DocumentsPropertyHelperImpl(str, z);
                }
                if (!z || !Features.isEnabled(Features.SUPPORT_VISUAL_SEARCH_NEW_DOCUMENTS)) {
                    return new DocumentsPropertyHelperImpl61(str, z);
                }
                return new ClusteredDocumentPropertyHelperImpl(str);
            default:
                throw new AssertionError("not support location : ".concat(str));
        }
    }

    public static CategoryPropertyHelper getInstance(String str, boolean z) {
        String removeArgs = ArgumentsUtil.removeArgs(str);
        ConcurrentHashMap<String, CategoryPropertyHelper> concurrentHashMap = sMap;
        return concurrentHashMap.computeIfAbsent(removeArgs + "/" + z, new C0471a(removeArgs, z));
    }

    public abstract boolean enableMoreButton(Context context, int i2);

    public int getCardListBottomMargin(Context context, boolean z) {
        int i2;
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            if (z) {
                i2 = R.dimen.search_last_card_margin_bottom_8x;
            } else {
                i2 = R.dimen.search_card_margin_bottom_8x;
            }
        } else if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            i2 = R.dimen.search_card_margin_bottom_7x;
        } else if (PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61) {
            i2 = R.dimen.search_card_margin_bottom_61;
        } else {
            i2 = R.dimen.search_card_margin_bottom;
        }
        return getDimensionPixelOffset(context, i2);
    }

    public int getCardListHorizontalMargin(Context context) {
        return getDimensionPixelOffset(context, getCardListHorizontalMarginResId());
    }

    public int getCardListHorizontalMarginResId() {
        return -1;
    }

    public int getCardListHorizontalPadding(Context context) {
        return getDimensionPixelOffset(context, getCardListHorizontalPaddingResId());
    }

    public int getCardListHorizontalPaddingResId() {
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            return R.dimen.search_card_padding_horizontal_61;
        }
        return R.dimen.search_card_padding_horizontal;
    }

    public int getCardListTopMargin(Context context) {
        int i2;
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            i2 = R.dimen.search_card_margin_top_8x;
        } else if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            i2 = R.dimen.search_card_margin_top_7x;
        } else if (PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61) {
            i2 = R.dimen.search_card_margin_top_61;
        } else {
            i2 = R.dimen.search_card_margin_top;
        }
        return getDimensionPixelOffset(context, i2);
    }

    public String getCategorySubTitle(Context context, int i2) {
        return "";
    }

    public String getCategoryTitle(Context context) {
        int categoryTitleResId = getCategoryTitleResId();
        if (categoryTitleResId != -1) {
            return context.getString(categoryTitleResId);
        }
        return null;
    }

    public abstract int getCategoryTitleResId();

    public abstract int[] getColumnCount(Context context);

    public int getDimensionPixelOffset(Context context, int i2) {
        if (i2 != -1) {
            return context.getResources().getDimensionPixelOffset(i2);
        }
        return 0;
    }

    public int getFloatingItemVerticalMargin(Context context) {
        return 0;
    }

    public int getHeaderRoundCorner(Context context) {
        return getDimensionPixelOffset(context, getHeaderRoundCornerResId());
    }

    public int getHeaderRoundCornerResId() {
        return -1;
    }

    public int[] getIntArray(Context context, int i2) {
        if (context != null) {
            return context.getResources().getIntArray(i2);
        }
        return new int[]{1};
    }

    public int getItemHorizontalSpacing(Context context) {
        return getDimensionPixelOffset(context, getItemHorizontalSpacingResId());
    }

    public int getItemHorizontalSpacingResId() {
        return R.dimen.search_item_card_thumbnail_spacing;
    }

    public int getItemMarginBottom() {
        return R.dimen.search_item_margin_bottom;
    }

    public int getItemMarginTop() {
        return R.dimen.search_item_margin_top;
    }

    public abstract int getItemViewType();

    public abstract int getMaxDisplayCountOnCard(Context context);

    public int getRoundCorner(Context context) {
        return getDimensionPixelOffset(context, getRoundCornerResId());
    }

    public abstract int getRoundCornerResId();

    public abstract String getScreenId();

    public int getThumbnailBorder() {
        return R.drawable.search_thumbnail_border;
    }

    public int getThumbnailShape() {
        return 1;
    }

    public boolean hasItemMarginTopOnCard() {
        return true;
    }

    public boolean hasRoundedHeader() {
        return false;
    }

    public abstract boolean hasThumbnail();

    public final boolean isExpansionMode() {
        return this.mIsExpansionMode;
    }

    public boolean isNeedUpdateItemHorizontalMargin() {
        return true;
    }

    public boolean supportSubTitle() {
        return true;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("CategoryPropertyHelper{");
        sb2.append(this.mLocationKey);
        sb2.append("[");
        return j.h(sb2, this.mIsExpansionMode, "]}");
    }

    public boolean useCountOnCard() {
        return !PreferenceFeatures.OneUi8x.COLLECTION_TAB;
    }

    public boolean useGridLayoutOnCard() {
        return !PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61;
    }

    public int getItemMarginBottom(Context context) {
        return getDimensionPixelOffset(context, getItemMarginBottom());
    }

    public int getItemMarginTop(Context context) {
        return getDimensionPixelOffset(context, getItemMarginTop());
    }
}
