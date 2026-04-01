package com.samsung.android.gallery.module.abstraction;

import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.utils.PocFeatures;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class StoryCategory {
    private static final boolean SUPPORT_CATEGORY_RECAP_BOOKMARKED = PocFeatures.isEnabled(PocFeatures.RecapCategory);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SupportedCategory {
        static final List<String> categories = new ArrayList<String>() {
            {
                add("location://stories/category/transitory");
                add("location://stories/category/creation");
                add("location://stories/category/trip");
                add("location://stories/category/RecapBookMarked");
                if (PocFeatures.SUPPORT_RECAP_STACK_UI) {
                    add("location://stories/category/Recap");
                }
            }
        };
    }

    public static int getCategoryTitle(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1761929745:
                if (str.equals("location://stories/category/creation")) {
                    c5 = 0;
                    break;
                }
                break;
            case 1375420772:
                if (str.equals("location://stories/category/RecapBookMarked")) {
                    c5 = 1;
                    break;
                }
                break;
            case 1665016629:
                if (str.equals("location://stories/category/trip")) {
                    c5 = 2;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return R$string.created_by_you;
            case 1:
                return R$string.recap;
            case 2:
                return R$string.story_category_trips;
            default:
                return R$string.other_stories;
        }
    }

    public static String getKey(int i2, int i7) {
        if (isTrip(i2, i7)) {
            return "location://stories/category/trip";
        }
        if (PocFeatures.SUPPORT_RECAP_STACK_UI && i2 == StoryType.RECAP.getValue()) {
            return "location://stories/category/Recap";
        }
        if (StoryType.isTransitoryType(i2)) {
            return "location://stories/category/transitory";
        }
        if (i2 == StoryType.MANUAL.getValue()) {
            return "location://stories/category/creation";
        }
        if (i2 == StoryType.ON_DEMAND_STORY.getValue()) {
            return "location://stories/category/ondemand";
        }
        if (!SUPPORT_CATEGORY_RECAP_BOOKMARKED || i2 != StoryType.RECAP_BOOKMARKED.getValue()) {
            return "location://stories/category/more";
        }
        return "location://stories/category/RecapBookMarked";
    }

    public static int getMaxPreloadCount(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1761929745:
                if (str.equals("location://stories/category/creation")) {
                    c5 = 0;
                    break;
                }
                break;
            case 199731027:
                if (str.equals("location://stories/category/transitory")) {
                    c5 = 1;
                    break;
                }
                break;
            case 1375420772:
                if (str.equals("location://stories/category/RecapBookMarked")) {
                    c5 = 2;
                    break;
                }
                break;
            case 1665016629:
                if (str.equals("location://stories/category/trip")) {
                    c5 = 3;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return 6;
            case 1:
            case 2:
            case 3:
                return Integer.MAX_VALUE;
            default:
                return 12;
        }
    }

    public static int getOrder(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1761929745:
                if (str.equals("location://stories/category/creation")) {
                    c5 = 0;
                    break;
                }
                break;
            case 199731027:
                if (str.equals("location://stories/category/transitory")) {
                    c5 = 1;
                    break;
                }
                break;
            case 1375420772:
                if (str.equals("location://stories/category/RecapBookMarked")) {
                    c5 = 2;
                    break;
                }
                break;
            case 1665016629:
                if (str.equals("location://stories/category/trip")) {
                    c5 = 3;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return 1;
            case 1:
                return 0;
            case 2:
                return 3;
            case 3:
                return 2;
            default:
                return 100;
        }
    }

    public static List<String> getSupportCategories() {
        return SupportedCategory.categories;
    }

    private static boolean isTrip(int i2, int i7) {
        if (StoryType.TRIP_V7.getValue() == i2) {
            return true;
        }
        return false;
    }
}
