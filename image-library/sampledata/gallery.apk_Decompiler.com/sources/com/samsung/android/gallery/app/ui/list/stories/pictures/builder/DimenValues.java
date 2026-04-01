package com.samsung.android.gallery.app.ui.list.stories.pictures.builder;

import android.content.Context;
import android.content.res.Resources;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DimenValues {
    final int mImage1ByHeight;
    final int mImage1ByWidth;
    final int mImage2ByAvgHeight;
    final int mImage2ByAvgWidth;
    final int mImage2ByLargeHeight;
    final int mImage2ByLargeWidth;
    final int mImage2BySmallHeight;
    final int mImage2BySmallWidth;
    final int mImage3ByHeight;
    final int mImage3ByWidth;
    final int mItem2ByAvgWidth;
    final int mItem2ByLargeWidth;
    final int mItem2BySmallWidth;
    final int mItem3ByEndWidth;
    final int mItem3ByGap;
    final int mItem3ByMiddleWidth;
    final int mItem3ByStartWidth;
    final int mItemBaseHeight;
    final int mItemBaseWidth;
    final int mItemHMargin;
    final int mItemVGap;
    final int mItemVMargin;

    public DimenValues(Context context) {
        Resources resources = context.getResources();
        this.mImage1ByWidth = loadDimen(resources, R.dimen.story_pictures_image_base_width);
        this.mImage1ByHeight = loadDimen(resources, R.dimen.story_pictures_image_base_height);
        this.mImage2ByAvgWidth = loadDimen(resources, R.dimen.story_pictures_image_2by_avg_width);
        this.mImage2ByAvgHeight = loadDimen(resources, R.dimen.story_pictures_image_2by_avg_height);
        int loadDimen = loadDimen(resources, R.dimen.story_pictures_image_2by_large_width);
        this.mImage2ByLargeWidth = loadDimen;
        this.mImage2ByLargeHeight = loadDimen(resources, R.dimen.story_pictures_image_2by_large_height);
        this.mImage2BySmallWidth = loadDimen(resources, R.dimen.story_pictures_image_2by_small_width);
        this.mImage2BySmallHeight = loadDimen(resources, R.dimen.story_pictures_image_2by_small_height);
        int loadDimen2 = loadDimen(resources, R.dimen.story_pictures_image_3by_width);
        this.mImage3ByWidth = loadDimen2;
        this.mImage3ByHeight = loadDimen(resources, R.dimen.story_pictures_image_3by_height);
        int loadDimen3 = loadDimen(resources, R.dimen.story_pictures_image_h_margin);
        this.mItemHMargin = loadDimen3;
        this.mItemVMargin = loadDimen(resources, R.dimen.story_pictures_image_v_margin);
        int loadDimen4 = loadDimen(resources, R.dimen.story_pictures_item_base_width);
        this.mItemBaseWidth = loadDimen4;
        this.mItemBaseHeight = loadDimen(resources, R.dimen.story_pictures_item_base_height);
        this.mItemVGap = loadDimen(resources, R.dimen.story_pictures_item_bottom_margin);
        this.mItem2ByAvgWidth = loadDimen4 / 2;
        int i2 = (loadDimen3 * 2) + loadDimen;
        this.mItem2ByLargeWidth = i2;
        this.mItem2BySmallWidth = loadDimen4 - i2;
        int i7 = ((loadDimen4 - (loadDimen2 * 3)) - (loadDimen3 * 2)) / 2;
        this.mItem3ByGap = i7;
        int i8 = (i7 / 2) + loadDimen3 + loadDimen2;
        this.mItem3ByStartWidth = i8;
        this.mItem3ByMiddleWidth = loadDimen2 + i7;
        this.mItem3ByEndWidth = i8;
    }

    private int loadDimen(Resources resources, int i2) {
        return resources.getDimensionPixelSize(i2);
    }
}
