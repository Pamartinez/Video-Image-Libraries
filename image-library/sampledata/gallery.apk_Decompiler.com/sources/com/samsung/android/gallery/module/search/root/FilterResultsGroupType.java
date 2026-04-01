package com.samsung.android.gallery.module.search.root;

import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum FilterResultsGroupType {
    ;
    
    private final String mCategory;
    private final int mTitleResId;

    static {
        int i2;
        if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
            i2 = R$string.visual_search_category_people_and_pets;
        } else {
            i2 = R$string.people;
        }
        FACES = new FilterResultsGroupType("FACES", 0, i2, "faces");
        LOCATIONS = new FilterResultsGroupType("LOCATIONS", 1, R$string.locations, "facet_location");
        SHOT_TYPES = new FilterResultsGroupType("SHOT_TYPES", 2, R$string.shot_types, ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE);
        ALBUMS = new FilterResultsGroupType("ALBUMS", 3, R$string.album, "bucket_display_name");
        MY_TAG = new FilterResultsGroupType("MY_TAG", 4, R$string.my_tags, "usertag");
        SCENE_TAG = new FilterResultsGroupType("SCENE_TAG", 5, R$string.scenes, "scenetag");
        EXPRESSION = new FilterResultsGroupType("EXPRESSION", 6, R$string.expressions, "expressionstag");
        EVENT = new FilterResultsGroupType("EVENT", 7, R$string.events, "facet_event");
        NONE = new FilterResultsGroupType("NONE", 8, -1, "");
        $VALUES = $values();
    }

    private FilterResultsGroupType(int i2, String str) {
        this.mTitleResId = i2;
        this.mCategory = str;
    }

    public static FilterResultsGroupType of(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1317630753:
                if (str.equals("expressionstag")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1165864931:
                if (str.equals("bucket_display_name")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1025429121:
                if (str.equals("recommended_id")) {
                    c5 = 2;
                    break;
                }
                break;
            case -775468146:
                if (str.equals("scenetag")) {
                    c5 = 3;
                    break;
                }
                break;
            case -713660910:
                if (str.equals("facet_event")) {
                    c5 = 4;
                    break;
                }
                break;
            case -639779342:
                if (str.equals("sef_file_type")) {
                    c5 = 5;
                    break;
                }
                break;
            case -196041627:
                if (str.equals("mime_type")) {
                    c5 = 6;
                    break;
                }
                break;
            case -163871951:
                if (str.equals("recording_mode")) {
                    c5 = 7;
                    break;
                }
                break;
            case -147112977:
                if (str.equals("usertag")) {
                    c5 = 8;
                    break;
                }
                break;
            case 608021663:
                if (str.equals("pet_recommended_id")) {
                    c5 = 9;
                    break;
                }
                break;
            case 853207077:
                if (str.equals("persontag")) {
                    c5 = 10;
                    break;
                }
                break;
            case 1038965053:
                if (str.equals("facet_location")) {
                    c5 = 11;
                    break;
                }
                break;
            case 1939875509:
                if (str.equals(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE)) {
                    c5 = 12;
                    break;
                }
                break;
            case 2082806484:
                if (str.equals("sub_location")) {
                    c5 = 13;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return EXPRESSION;
            case 1:
                return ALBUMS;
            case 2:
            case 9:
            case 10:
                return FACES;
            case 3:
                return SCENE_TAG;
            case 4:
                return EVENT;
            case 5:
            case 6:
            case 7:
            case 12:
                return SHOT_TYPES;
            case 8:
                return MY_TAG;
            case 11:
            case 13:
                return LOCATIONS;
            default:
                return NONE;
        }
    }

    public String getCategory() {
        return this.mCategory;
    }

    public int getTitleResId() {
        return this.mTitleResId;
    }
}
