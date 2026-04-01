package com.samsung.android.gallery.module.search.recommendation;

import com.samsung.android.gallery.module.search.root.IntelligentSearchIndexFieldIcon;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IRecommendationItem {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum IconType {
        TIME,
        RECENT,
        LOCATION,
        VIDEOS,
        SMILES,
        CATEGORY,
        BLURRY,
        ETC
    }

    String getButtonName();

    IntelligentSearchIndexFieldIcon getFieldIcon();

    IconType getIconType();

    int getOrdinary();

    String getQueryWhereArgs();

    String getQueryWhereColumnName();

    String getSubCategory();

    String getSuggestedKeywordFeature();

    String getTagType();

    String getTitle();

    boolean isDynamic();
}
