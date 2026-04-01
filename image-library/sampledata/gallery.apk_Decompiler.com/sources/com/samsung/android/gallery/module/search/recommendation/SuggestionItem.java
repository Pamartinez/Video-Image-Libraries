package com.samsung.android.gallery.module.search.recommendation;

import com.samsung.android.gallery.module.search.recommendation.IRecommendationItem;
import com.samsung.android.gallery.module.search.root.IntelligentSearchIndexFieldIcon;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestionItem implements IRecommendationItem {
    static boolean SUPPORT_SCS_SEARCH = Features.isEnabled(Features.SUPPORT_SCS_SEARCH);
    private final String mButtonName;
    private IntelligentSearchIndexFieldIcon mFieldIcon;
    private final IRecommendationItem.IconType mIconType;
    private final boolean mIsDynamic;
    private final int mLocationType;
    private final int mOrdinary;
    private final String mQueryWhereArgs;
    private final String mQueryWhereColumnName;
    private final String mSubCategory;
    private final String mSuggestedKeywordFeature;
    private final String mTagType;
    private final String mTitle;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        IRecommendationItem.IconType iconType = IRecommendationItem.IconType.ETC;
        boolean isDynamic = false;
        int locationType;
        int ordinary;
        String queryString;
        String subCategory;
        String suggestedKeywordFeature;
        String tagType = "key_word";
        String title;

        public SuggestionItem build() {
            return new SuggestionItem(this, 0);
        }

        public Builder setDynamicState() {
            this.isDynamic = true;
            return this;
        }

        public Builder setIconType(IRecommendationItem.IconType iconType2) {
            this.iconType = iconType2;
            return this;
        }

        public Builder setLocationType(int i2) {
            this.locationType = i2;
            return this;
        }

        public Builder setOrdinary(int i2) {
            this.ordinary = i2;
            return this;
        }

        public Builder setQueryString(String str) {
            this.queryString = str;
            return this;
        }

        public Builder setSubCategory(String str) {
            this.subCategory = str;
            return this;
        }

        public Builder setSuggestedKeywordFeature(String str) {
            this.suggestedKeywordFeature = str;
            return this;
        }

        public Builder setTagType(String str) {
            this.tagType = str;
            return this;
        }

        public Builder setTitle(String str) {
            this.title = str;
            return this;
        }
    }

    public /* synthetic */ SuggestionItem(Builder builder, int i2) {
        this(builder);
    }

    private boolean useKeywordSearch() {
        return PreferenceFeatures.OneUi7x.SUPPORT_QS_UNIFIED_ITEM;
    }

    public boolean equals(Object obj) {
        if (obj instanceof SuggestionItem) {
            SuggestionItem suggestionItem = (SuggestionItem) obj;
            if (!this.mSubCategory.equalsIgnoreCase(suggestionItem.getSubCategory()) || !this.mTagType.equalsIgnoreCase(suggestionItem.getTagType())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public String getButtonName() {
        return this.mButtonName;
    }

    public IntelligentSearchIndexFieldIcon getFieldIcon() {
        if (SUPPORT_SCS_SEARCH) {
            return this.mFieldIcon;
        }
        return null;
    }

    public IRecommendationItem.IconType getIconType() {
        return this.mIconType;
    }

    public int getOrdinary() {
        return this.mOrdinary;
    }

    public String getQueryWhereArgs() {
        return this.mQueryWhereArgs;
    }

    public String getQueryWhereColumnName() {
        return this.mQueryWhereColumnName;
    }

    public String getSubCategory() {
        return this.mSubCategory;
    }

    public String getSuggestedKeywordFeature() {
        return this.mSuggestedKeywordFeature;
    }

    public String getTagType() {
        if (PreferenceFeatures.OneUi7x.SUPPORT_QS_UNIFIED_ITEM) {
            return "key_word";
        }
        return this.mTagType;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public boolean isDynamic() {
        return this.mIsDynamic;
    }

    private SuggestionItem(Builder builder) {
        this.mOrdinary = builder.ordinary;
        this.mLocationType = builder.locationType;
        String str = builder.subCategory;
        this.mSubCategory = str;
        String str2 = builder.tagType;
        this.mTagType = str2;
        this.mIconType = builder.iconType;
        this.mIsDynamic = builder.isDynamic;
        this.mSuggestedKeywordFeature = builder.suggestedKeywordFeature;
        String str3 = "key_word";
        if ("special_day".equals(str2)) {
            String str4 = builder.queryString;
            this.mTitle = str4;
            this.mButtonName = builder.subCategory;
            this.mQueryWhereColumnName = str3;
            this.mQueryWhereArgs = str4;
        } else {
            String str5 = builder.title;
            this.mTitle = str5;
            this.mButtonName = str5;
            this.mQueryWhereColumnName = !useKeywordSearch() ? builder.tagType : str3;
            this.mQueryWhereArgs = str;
        }
        if (SUPPORT_SCS_SEARCH) {
            this.mFieldIcon = IntelligentSearchIndexFieldIcon.createForSuggestion(str2, str);
        }
    }
}
