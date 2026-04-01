package com.samsung.android.gallery.database.dbtype;

import Ad.C0720a;
import B8.b;
import c4.C0431a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum StoryType {
    Dummy(0, r4, r10),
    MANUAL(1, r9, r10),
    NO_MOMENT(2, r9, r10),
    MOMENT(3, r9, r17),
    STORY(4, r16, r17),
    PERSON(5, r27, r16),
    NAMED_PERSON(6, r15, r16),
    LOCATION(7, r9, r10),
    LIVE(8, r9, r10),
    SHARE_FOR_LIVE(9, r9, r10),
    SHARE_FOR_MANUAL(10, r4, r10),
    COLLAGE(11, r9, r10),
    AGIF(12, r9, r10),
    VIDEO_COLLAGE(13, r9, r10),
    TRIP(14, r16, r17),
    DOMESTIC_TRIP(15, r16, r17),
    HAPPY_MOMENT(16, r16, r17),
    FLASHBACK(17, r6, r7),
    PERSON_GROUP(18, r15, r16),
    PERSON_ME(19, r15, r16),
    PET(20, r33, r34),
    BABY(21, r15, r16),
    ACTIVITY(22, r9, r10),
    RETAIL(23, StoryCategoryType.RETAIL, r10),
    COLLECTION_STORY(24, r7, r8),
    COLLAGE_THEN_AND_NOW(25, r15, r16),
    REDISCOVER_DAY(26, r15, r17),
    RESTAURANT(27, r27, r28),
    RECIPE_STORY(28, r27, r28),
    PEOPLE_YEARLY_DIARY(29, r28, r29),
    GROWTH(30, r28, r29),
    BESTMOMENT(31, r16, r17),
    RUBIN_EVENT_STORY(32, r27, r8),
    OUTING_STORY(33, r22, r17),
    GROUP_STORY(34, r15, r16),
    COLLECTION_SCENERY_STORY(35, r7, r8),
    COLLECTION_PEOPLE_STORY(36, r7, r8),
    TRIP_V7(39, r5, StoryLevel1Cat.TRIP),
    MOMENTS(40, r5, StoryLevel1Cat.MOMENTS),
    RECENT_HIGHLIGHTS_WEEKLY_N(71, r27, r28),
    RECENT_HIGHLIGHTS_MONTHLY_N(72, r27, r28),
    N_YEARS_AGO_N(73, r27, r28),
    BEST_MOMENT_OF_THE_YEAR_N(74, r27, r28),
    PET_WITH_PEOPLE_N(75, r33, r34),
    COLLECTION_WITH_PEOPLE_N(76, r7, r8),
    PET_WITH_LOCATION_N(77, r7, r8),
    RECAP_BOOKMARKED(85, r27, r6),
    RECENT_HIGHLIGHTS_WEEKLY(101, r4, r5),
    RECENT_HIGHLIGHTS_MONTHLY(102, r4, r5),
    N_YEARS_AGO(103, r4, r5),
    BEST_MOMENT_OF_THE_YEAR(104, r4, r5),
    PET_WITH_PEOPLE(105, r4, r39),
    COLLECTION_WITH_PEOPLE(106, r27, r28),
    PET_WITH_LOCATION(107, r27, r28),
    SPECIAL_DAY_SODA(108, r22, StoryLevel1Cat.SPECIAL_SODA),
    PERSON_TRANSITORY(110, r15, r16),
    NAMED_PERSON_TRANSITORY(111, r15, r16),
    PET_TRANSITORY(112, r33, r39),
    CHRISTMAS_REMIND_OR_EVENT(114, r27, StoryLevel1Cat.PDE),
    RECAP(115, r27, r6),
    ON_DEMAND_STORY(1000, r9, r10),
    DELETED_STORY(1001, r9, r10);
    
    private final StoryCategoryType category;
    private final StoryLevel1Cat level1Cat;
    private final int value;

    private StoryType(int i2, StoryCategoryType storyCategoryType, StoryLevel1Cat storyLevel1Cat) {
        this.value = i2;
        this.category = storyCategoryType;
        this.level1Cat = storyLevel1Cat;
        ordinal();
    }

    public static StoryType getTypeByValue(int i2) {
        return (StoryType) Arrays.stream(values()).filter(new b(i2, 10)).findFirst().orElse(MANUAL);
    }

    public static ArrayList<Integer> getTypesByCategory(int i2) {
        return (ArrayList) Arrays.stream(values()).filter(new b(i2, 9)).map(new C0431a(14)).collect(Collectors.toCollection(new C0720a(1)));
    }

    public static boolean isTransitoryType(int i2) {
        if (i2 >= 1000 || i2 <= 100) {
            return false;
        }
        return true;
    }

    public StoryCategoryType getCategory() {
        return this.category;
    }

    public StoryLevel1Cat getLevel1Cat() {
        return this.level1Cat;
    }

    public int getValue() {
        return this.value;
    }

    public static StoryCategoryType getCategory(int i2) {
        return ((StoryType) Arrays.stream(values()).filter(new b(i2, 8)).findFirst().orElse(MANUAL)).getCategory();
    }
}
