package com.samsung.android.gallery.module.search.autocomplete;

import com.samsung.android.gallery.module.search.abstraction.AutoCompleteUrlCompatible;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AutoCompleteUriBuilder {
    public static String build(Blackboard blackboard, AutoCompleteUrlCompatible autoCompleteUrlCompatible, String str, String str2) {
        if (str2 == null) {
            str2 = LocationKey.getSearchLocationKey("location://search/fileList/Keyword", autoCompleteUrlCompatible.getKeyword());
        }
        UriBuilder uriBuilder = new UriBuilder(str2);
        uriBuilder.appendArg("sub", useTranslatedKeyword() ? autoCompleteUrlCompatible.getTranslatedKeyword() : autoCompleteUrlCompatible.getKeyword()).appendArg("title", autoCompleteUrlCompatible.getTranslatedKeyword()).appendArg("term", autoCompleteUrlCompatible.getTerm()).appendArg("field", autoCompleteUrlCompatible.getField()).appendArg("collect_keyword", autoCompleteUrlCompatible.getTranslatedKeyword()).appendArg("collect_type", SearchWordCollector.Type.KEYWORD_AUTOCOMPLETE.toString()).appendArg("SelectedFilter", str);
        if ("persontag".equals(autoCompleteUrlCompatible.getField())) {
            uriBuilder.appendArg("isNamed", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
        }
        if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_CHECK_SUGGESTED_KEYWORD_FEATURE)) {
            uriBuilder.appendArg("suggestedKeywordFeature", autoCompleteUrlCompatible.getSuggestedKeywordFeature());
        }
        if (PreferenceFeatures.OneUi7x.DISABLE_TIMELINE_ON_KEYWORD && !PickerUtil.isPickerMode(blackboard)) {
            uriBuilder.appendArg("disableTimeline", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
        }
        return uriBuilder.build();
    }

    private static boolean useTranslatedKeyword() {
        return PreferenceFeatures.OneUi7x.SUPPORT_AC_UNIFIED_ITEM;
    }

    public static String build(Blackboard blackboard, final FilterResultsEntity filterResultsEntity, String str, String str2) {
        return build(blackboard, (AutoCompleteUrlCompatible) new AutoCompleteUrlCompatible() {
            public String getField() {
                return FilterResultsEntity.this.getCategory();
            }

            public String getKeyword() {
                return FilterResultsEntity.this.getRawLabels();
            }

            public String getSuggestedKeywordFeature() {
                return FilterResultsEntity.this.getSuggestedKeywordFeature();
            }

            public String getTerm() {
                return FilterResultsEntity.this.getCategory();
            }

            public String getTranslatedKeyword() {
                return FilterResultsEntity.this.getName();
            }
        }, str, str2);
    }
}
