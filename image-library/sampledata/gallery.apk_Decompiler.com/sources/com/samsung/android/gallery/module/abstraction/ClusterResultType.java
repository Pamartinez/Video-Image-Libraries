package com.samsung.android.gallery.module.abstraction;

import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ClusterResultType {
    ALBUMS(R$string.album_names),
    LOCATIONS(R$string.searchview_location),
    PEOPLE(R$string.story_category_people),
    OCRS(R$string.text_found_in_image),
    MEDIA_TYPE(R$string.contents),
    TOP_RESULT(R$string.top_result),
    CAROUSELS(R$string.cluster_categories),
    PLACE(R$string.place),
    MY_TAGS(R$string.my_tags),
    PETS(R$string.pets),
    PDCS(R$string.events),
    CREATURE(R$string.visual_search_category_people_and_pets),
    UNKNOWN(0);
    
    public final int titleResId;

    private ClusterResultType(int i2) {
        this.titleResId = i2;
    }

    public static ClusterResultType of(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1671680240:
                if (str.equals("person_cluster")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1623182517:
                if (str.equals("ocrtext")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1480586582:
                if (str.equals("pdc_cluster")) {
                    c5 = 2;
                    break;
                }
                break;
            case -982748912:
                if (str.equals("poitag")) {
                    c5 = 3;
                    break;
                }
                break;
            case -684406201:
                if (str.equals("top_result")) {
                    c5 = 4;
                    break;
                }
                break;
            case -147112977:
                if (str.equals("usertag")) {
                    c5 = 5;
                    break;
                }
                break;
            case 627202090:
                if (str.equals("album_cluster")) {
                    c5 = 6;
                    break;
                }
                break;
            case 1038965053:
                if (str.equals("facet_location")) {
                    c5 = 7;
                    break;
                }
                break;
            case 1283958266:
                if (str.equals("creature_cluster")) {
                    c5 = 8;
                    break;
                }
                break;
            case 1939875509:
                if (str.equals(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE)) {
                    c5 = 9;
                    break;
                }
                break;
            case 1955912410:
                if (str.equals("pet_cluster")) {
                    c5 = 10;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return PEOPLE;
            case 1:
                return OCRS;
            case 2:
                return PDCS;
            case 3:
                return PLACE;
            case 4:
                return TOP_RESULT;
            case 5:
                return MY_TAGS;
            case 6:
                return ALBUMS;
            case 7:
                return LOCATIONS;
            case 8:
                return CREATURE;
            case 9:
                return MEDIA_TYPE;
            case 10:
                return PETS;
            default:
                new InternalException("[ClusterResultType] not found clusterType=".concat(str)).post();
                return UNKNOWN;
        }
    }
}
