package com.samsung.android.gallery.support.type;

import c4.C0431a;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class IntelligentSearchIndexField {
    public static final ArrayList<String> AUTOCOMPLETE_KEYWORD_TERM_LIST_LOCATION_OFF = new ArrayList<String>() {
        {
            addAll(IntelligentSearchIndexField.AUTOCOMPLETE_KEYWORD_TERM_LIST_LOCATION_ON);
            remove("poitag");
            if (!Features.isEnabled(Features.SUPPORT_PLACE_GDPR)) {
                remove("locationtag");
            }
        }
    };
    public static final ArrayList<String> AUTOCOMPLETE_KEYWORD_TERM_LIST_LOCATION_ON = new ArrayList<String>() {
        {
            add("bucket_display_name");
            add("usertag");
            add("persontag");
            add("date_suggestion");
            add("expressionstag");
            add("scenetag");
            add(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE);
            add("sef_file_type");
            add("recording_mode");
            add("poitag");
            add("locationtag");
            if (Features.isEnabled(Features.SUPPORT_DATE_NLU_AUTO_COMPLETE)) {
                add("date_nlu");
            }
            if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
                add("pet_tag");
            }
            if (Features.isEnabled(Features.SUPPORT_SUGGEST_RELATIONSHIP)) {
                add("relationship");
            }
            if (!PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71 && Features.isEnabled(Features.SUPPORT_SUGGEST_OCR)) {
                add("ocrtext");
            }
        }
    };
    public static final ArrayList<String> CLUSTER_REQUEST_FIELDS = new ArrayList<String>() {
        {
            add(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE);
        }
    };
    public static final ArrayList<String> CLUSTER_REQUEST_PREFIX_MATCH_FIELDS = new ArrayList<String>() {
        {
            add("person_cluster");
            add("album_cluster");
            add("usertag");
            add("pet_cluster");
        }
    };
    public static final ArrayList<String> CLUSTER_REQUEST_TERM_MATCH_FIELDS = new ArrayList<String>() {
        {
            if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                add("ocrtext");
                add("facet_location");
                return;
            }
            add("facet_location");
            add("album_cluster");
            add("person_cluster");
            add("ocrtext");
        }
    };
    public static final ArrayList<String> CLUSTER_RESULT_FIELDS = new ArrayList<String>() {
        {
            add("person_cluster");
            add(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE);
            add("facet_location");
            add("album_cluster");
            add("ocrtext");
            if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                add("poitag");
                add("pet_cluster");
                add("usertag");
            }
            if (Features.isEnabled(Features.SUPPORT_PDC_CLUSTER)) {
                add("pdc_cluster");
            }
        }
    };
    public static final ArrayList<String> CREATURE_TERM = new ArrayList<String>() {
        {
            add("recommended_id");
            if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
                add("pet_recommended_id");
            }
        }
    };
    public static final ArrayList<String> FACET_REQUEST_FIELDS = new ArrayList<String>() {
        {
            String str;
            if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
                str = "recommended_id";
            } else {
                str = "persontag";
            }
            add(str);
            if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
                add("pet_recommended_id");
            }
            add("facet_location");
            add("sub_location");
            add("sef_file_type");
            add("recording_mode");
            if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
                add(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE);
            } else if (!PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                add("bucket_display_name");
                add(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE);
                add("usertag");
                add("scenetag");
                add("expressionstag");
            }
            if (Features.isEnabled(Features.SUPPORT_EVENT_FACET)) {
                add("facet_event");
            }
        }
    };
    public static final ArrayList<String> FUZZY_KEYWORD_TERM_LIST = new ArrayList<String>() {
        {
            add("scenetag");
            add("sef_file_type");
            add("recording_mode");
            add(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE);
            add("expressionstag");
            if (SdkConfig.atLeast(SdkConfig.GED.S)) {
                add("usertag");
                add("persontag");
                add("bucket_display_name");
                add("locationtag");
            }
            if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
                add("pet_tag");
            }
        }
    };
    public static final ArrayList<String> FUZZY_KEYWORD_TERM_LIST_LOCATION_OFF = new ArrayList<String>() {
        {
            addAll(IntelligentSearchIndexField.FUZZY_KEYWORD_TERM_LIST);
            if (!SdkConfig.atLeast(SdkConfig.GED.S) && !Features.isEnabled(Features.SUPPORT_PLACE_GDPR)) {
                remove("locationtag");
            }
        }
    };
    public static final String NO_LOCATION_SELECTION_BLOCK_FIELDS;
    public static final String NO_LOCATION_SELECTION_FIELDS;
    private static final ArrayList<String> NO_LOCATION_SELECTION_FIELD_BLOCK_LIST;
    private static final ArrayList<String> NO_LOCATION_SELECTION_FIELD_LIST;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CarouselClusterHolder {
        static final HashSet<String> set;

        static {
            List list;
            if (!PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                list = List.of("person_cluster", "album_cluster", "facet_location", "ocrtext");
            } else if (Features.isEnabled(Features.SUPPORT_PDC_CLUSTER)) {
                list = List.of("person_cluster", "album_cluster", "facet_location", "ocrtext", "pet_cluster", "usertag", "pdc_cluster");
            } else {
                list = List.of("person_cluster", "album_cluster", "facet_location", "ocrtext", "pet_cluster", "usertag");
            }
            set = new HashSet<>(list);
        }
    }

    static {
        AnonymousClass1 r0 = new ArrayList<String>() {
            {
                add("bucket_display_name");
                add("_display_name");
                add("usertag");
                add("persontag");
                add("scenetag");
                add("expressionstag");
                add(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE);
                add("sef_file_type");
                add("recording_mode");
                if (Features.isEnabled(Features.SUPPORT_PLACE_GDPR)) {
                    add("locationtag");
                    add("addr");
                }
                add("ocrtext");
                add("color_detected");
                add("action_detected");
                if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
                    add("pet_tag");
                }
                if (SdkConfig.atLeast(SdkConfig.GED.B)) {
                    add("scene_name");
                }
            }
        };
        NO_LOCATION_SELECTION_FIELD_LIST = r0;
        NO_LOCATION_SELECTION_FIELDS = String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, r0);
        AnonymousClass2 r02 = new ArrayList<String>() {
            {
                if (!Features.isEnabled(Features.SUPPORT_PLACE_GDPR)) {
                    add("locationtag");
                    add("addr");
                }
                add("poitag");
            }
        };
        NO_LOCATION_SELECTION_FIELD_BLOCK_LIST = r02;
        NO_LOCATION_SELECTION_BLOCK_FIELDS = String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, r02);
    }

    public static String getAutoCompleteTermSelection(boolean z) {
        if (z) {
            return "usertag=?";
        }
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth)) {
            return (String) AUTOCOMPLETE_KEYWORD_TERM_LIST_LOCATION_ON.stream().map(new C0431a(2)).collect(Collectors.joining(" OR "));
        }
        return (String) AUTOCOMPLETE_KEYWORD_TERM_LIST_LOCATION_OFF.stream().map(new C0431a(3)).collect(Collectors.joining(" OR "));
    }

    public static int getAutoCompleteTermSize() {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth)) {
            return AUTOCOMPLETE_KEYWORD_TERM_LIST_LOCATION_ON.size();
        }
        return AUTOCOMPLETE_KEYWORD_TERM_LIST_LOCATION_OFF.size();
    }

    public static String getFuzzySuggestionKeywordTermSelection() {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth)) {
            return (String) FUZZY_KEYWORD_TERM_LIST.stream().map(new C0431a(4)).collect(Collectors.joining(" OR "));
        }
        return (String) FUZZY_KEYWORD_TERM_LIST_LOCATION_OFF.stream().map(new C0431a(5)).collect(Collectors.joining(" OR "));
    }

    public static int getFuzzySuggestionKeywordTermSize() {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth)) {
            return FUZZY_KEYWORD_TERM_LIST.size();
        }
        return FUZZY_KEYWORD_TERM_LIST_LOCATION_OFF.size();
    }

    public static boolean isCarouselClusterType(String str) {
        return CarouselClusterHolder.set.contains(str);
    }
}
