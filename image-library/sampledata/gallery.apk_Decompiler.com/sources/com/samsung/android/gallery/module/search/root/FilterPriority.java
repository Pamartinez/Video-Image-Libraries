package com.samsung.android.gallery.module.search.root;

import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import java.util.Comparator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilterPriority implements Comparator<FilterResultsEntity> {
    private static int getCategoryPriority(FilterResultsEntity filterResultsEntity) {
        String category = filterResultsEntity.getCategory();
        category.getClass();
        char c5 = 65535;
        switch (category.hashCode()) {
            case -1025429121:
                if (category.equals("recommended_id")) {
                    c5 = 0;
                    break;
                }
                break;
            case -982748912:
                if (category.equals("poitag")) {
                    c5 = 1;
                    break;
                }
                break;
            case -639779342:
                if (category.equals("sef_file_type")) {
                    c5 = 2;
                    break;
                }
                break;
            case -196041627:
                if (category.equals("mime_type")) {
                    c5 = 3;
                    break;
                }
                break;
            case -163871951:
                if (category.equals("recording_mode")) {
                    c5 = 4;
                    break;
                }
                break;
            case 2989041:
                if (category.equals("addr")) {
                    c5 = 5;
                    break;
                }
                break;
            case 293196495:
                if (category.equals("only_them")) {
                    c5 = 6;
                    break;
                }
                break;
            case 552339397:
                if (category.equals("locationtag")) {
                    c5 = 7;
                    break;
                }
                break;
            case 608021663:
                if (category.equals("pet_recommended_id")) {
                    c5 = 8;
                    break;
                }
                break;
            case 1038965053:
                if (category.equals("facet_location")) {
                    c5 = 9;
                    break;
                }
                break;
            case 1939875509:
                if (category.equals(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE)) {
                    c5 = 10;
                    break;
                }
                break;
            case 2082806484:
                if (category.equals("sub_location")) {
                    c5 = 11;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return 0;
            case 1:
            case 5:
            case 7:
            case 9:
            case 11:
                return 3;
            case 2:
            case 3:
            case 4:
            case 10:
                return 4;
            case 6:
                return 2;
            case 8:
                return 1;
            default:
                return 5;
        }
    }

    public static int getListPriority(FilterResultsEntity filterResultsEntity) {
        int categoryPriority = getCategoryPriority(filterResultsEntity);
        if (categoryPriority == 0 || categoryPriority == 1) {
            return 0;
        }
        if (categoryPriority == 2 || categoryPriority == 3) {
            return 1;
        }
        if (categoryPriority != 4) {
            return 3;
        }
        return 2;
    }

    public static boolean isCreaturePriority(int i2) {
        if (i2 == 0) {
            return true;
        }
        return false;
    }

    public static boolean isOnlyThemPriority(int i2) {
        if (i2 == 1) {
            return true;
        }
        return false;
    }

    public int compare(FilterResultsEntity filterResultsEntity, FilterResultsEntity filterResultsEntity2) {
        return Integer.compare(getCategoryPriority(filterResultsEntity), getCategoryPriority(filterResultsEntity2));
    }
}
