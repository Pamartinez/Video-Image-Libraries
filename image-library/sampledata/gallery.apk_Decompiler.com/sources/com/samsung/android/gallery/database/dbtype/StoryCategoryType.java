package com.samsung.android.gallery.database.dbtype;

import com.samsung.android.gallery.database.R$string;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum StoryCategoryType {
    SPECIAL_DAY(R$string.story_category_special_day, R$string.story_category_special_days),
    TRIP(R$string.story_category_trip, R$string.story_category_trips),
    PERSON(R$string.story_category_person, R$string.story_category_people),
    COLLECTION(R$string.story_category_collection, R$string.story_category_collections),
    PET(R$string.story_category_pet, R$string.story_category_pets),
    MY_STORY(r1, r2),
    NOTIFICATION(r1, r2),
    SHARE_STORY(r1, r2),
    RETAIL(r1, r2),
    FOOD(r1, r2),
    GROWTH(r1, r2),
    BEST_MOMENT(r1, r2),
    TRANSITORY(r1, r2);
    
    private final int categoryTitle;
    private final int title;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class IndexHolder {
        static final int size = 0;
        static final StoryCategoryType[] values = null;

        static {
            StoryCategoryType[] values2 = StoryCategoryType.values();
            values = values2;
            size = values2.length;
        }

        public static StoryCategoryType get(int i2) {
            if (i2 < 0 || i2 >= size) {
                return StoryCategoryType.MY_STORY;
            }
            return values[i2];
        }
    }

    private StoryCategoryType(int i2, int i7) {
        this.title = i2;
        this.categoryTitle = i7;
    }

    public static StoryCategoryType get(int i2) {
        return IndexHolder.get(i2);
    }

    public static int getTitle(int i2) {
        return get(i2).title;
    }
}
