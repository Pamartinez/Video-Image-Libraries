package com.samsung.android.gallery.module.search.autocomplete;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.abstraction.FilterResultsKeySet;
import com.samsung.android.gallery.module.R$color;
import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.abstraction.AutoCompleteUrlCompatible;
import com.samsung.android.gallery.module.search.root.IntelligentSearchIndexFieldIcon;
import com.samsung.android.gallery.support.translation.TranslationManager;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AutoCompleteItem implements AutoCompleteUrlCompatible {
    private final String mField;
    private final IntelligentSearchIndexFieldIcon mFieldIcon;
    private final String mKeyword;
    private String mSuggestedKeywordFeature;
    private String mTranslatedKeyword;

    public AutoCompleteItem(Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex("fieldName"));
        this.mField = string;
        String string2 = cursor.getString(cursor.getColumnIndex("keywords"));
        this.mKeyword = string2;
        if (Features.isEnabled(Features.SUPPORT_SCS_TRANSLATED_KEYWORD)) {
            this.mTranslatedKeyword = cursor.getString(cursor.getColumnIndex("keyword_translated"));
        }
        if (TextUtils.isEmpty(this.mTranslatedKeyword)) {
            this.mTranslatedKeyword = TranslationManager.getInstance().getTranslatedString(string, string2);
        }
        if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_CHECK_SUGGESTED_KEYWORD_FEATURE)) {
            this.mSuggestedKeywordFeature = cursor.getString(cursor.getColumnIndex("featureName"));
        }
        if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(TextUtils.equals("usertag", string) ? "#" : "");
            sb2.append(this.mTranslatedKeyword);
            this.mTranslatedKeyword = sb2.toString();
        }
        this.mFieldIcon = IntelligentSearchIndexFieldIcon.createForAutoComplete(string, string2);
    }

    private Drawable getDefaultDrawable(Context context) {
        Drawable drawable = context.getDrawable(R$drawable.gallery_ic_search_suggested_documents);
        if (drawable != null) {
            drawable.setTint(context.getColor(R$color.search_recommend_icon_color));
        }
        return drawable;
    }

    private Drawable getDrawableFromFieldIcon(Context context, Integer num) {
        Drawable drawable = context.getDrawable(num.intValue());
        if (drawable != null) {
            Integer tintColorResId = this.mFieldIcon.getTintColorResId();
            if (tintColorResId != null) {
                drawable.setTint(context.getColor(tintColorResId.intValue()));
                return drawable;
            }
            drawable.setTint(context.getColor(R$color.search_recommend_icon_color));
        }
        return drawable;
    }

    public Drawable getDrawable(Context context) {
        if (PreferenceFeatures.OneUi7x.SUPPORT_AC_UNIFIED_ITEM) {
            return null;
        }
        if (this.mFieldIcon.getDrawableResId() == null) {
            return getDefaultDrawable(context);
        }
        return getDrawableFromFieldIcon(context, this.mFieldIcon.getDrawableResId());
    }

    public String getField() {
        if (PreferenceFeatures.OneUi7x.SUPPORT_AC_UNIFIED_ITEM) {
            return "key_word";
        }
        return this.mField;
    }

    public String getKeyword() {
        return this.mKeyword;
    }

    public String getSuggestedKeywordFeature() {
        return this.mSuggestedKeywordFeature;
    }

    public String getTerm() {
        return getTermToQuery(getField());
    }

    public String getTranslatedKeyword() {
        return this.mTranslatedKeyword;
    }

    public AutoCompleteItem(MediaItem mediaItem) {
        String field = FilterResultsKeySet.getField(mediaItem.getCategory(), mediaItem.getSubCategory(), mediaItem.getTagType());
        this.mField = field;
        String subCategory = mediaItem.getSubCategory();
        this.mKeyword = subCategory;
        this.mTranslatedKeyword = mediaItem.getTitle();
        this.mFieldIcon = IntelligentSearchIndexFieldIcon.createForAutoComplete(field, subCategory);
    }

    private static String getTermToQuery(String str) {
        return str;
    }
}
