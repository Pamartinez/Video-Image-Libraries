package com.samsung.android.gallery.database.dbtype;

import B8.b;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum StoryLevel2Cat {
    DEFAULT(-1, StoryLevel1Cat.NONE),
    SPECIAL_DAY_TIME(3201, r1),
    SPECIAL_DAY_OUTING(3202, r1),
    SPECIAL_DAY_DAILY(3203, r1),
    TRIP_DOMESTIC(3204, r1),
    TRIP_OVERSEAS(3205, r1),
    TRIP_OVERSEAS_RUNEOFF(3206, r1),
    PEOPLE_BABY(3207, r2),
    PEOPLE_SELFIE(3208, r2),
    PEOPLE_GROUP(3209, r2),
    COLLECTION_THINGS(3210, r3),
    COLLECTION_SCENERY(3211, r3),
    PET(3212, r4),
    FOOD_VISITING_DAY(3213, r1),
    FOOD_VISITING_DAYS(3214, r1),
    FOOD_COLLECTION(3215, r3),
    FOOD_RECIPE(3216, r3),
    GROWTH_DIARY(3217, r2),
    GROWTH_NOWANDTHEN(3218, r2),
    BEST_MOMENT(3219, r1),
    COLLECTION_THINGS_WITH_PEOPLE_AND_LOCATION(3220, r3),
    COLLECTION_THINGS_WITH_PEOPLE(3221, r3),
    COLLECTION_THINGS_WITH_ME(3222, r3),
    PET_WITH_ME(3223, r4),
    RECENT_HIGHLIGHT(3230, r5),
    BEST_OF_LAST_MONTH(3231, r5),
    N_YEARS_AGO(3232, r5),
    BEST_MOMENT_OF_THE_YEAR(3233, r5),
    TIME_TRIP_OVERSEAS(3234, r1),
    TIME_TRIP_OVERSEAS_RUNEOFF(3235, r1),
    TIME_TRIP_DOMESTIC(3236, r1),
    TIME_TRIP_DOMESTIC_RUNEOFF(3237, r1),
    TIME_OUTING(3238, r1),
    TIME_OUTING_RUNEOFF(3239, r1),
    TIME_DAILY_WITH_YEAR(3240, r1),
    TIME_DAILY_WITH_YEAR_RUNEOFF(3241, r1),
    PEOPLE_PERSON_RANK(3242, r2),
    PEOPLE_GROUP_RANK(3243, r2),
    PET_RANK(3244, r4),
    PET_RANK_WITH_PEOPLE(3245, r4),
    PET_RANK_WITH_ME(3246, r4),
    BEACHES_WITH_PEOPLE(3247, r3),
    FIREWORKS_WITH_PEOPLE(3248, r3),
    MOUNTAINS_WITH_PEOPLE_1(3249, r3),
    SKIES_WITH_PEOPLE(3250, r3),
    SUNRISES_WITH_PEOPLE(3251, r3),
    BONFIRES_WITH_PEOPLE(3252, r3),
    FOOD_WITH_PEOPLE(3253, r3),
    CAKES_WITH_PEOPLE(3254, r3),
    ICECREAM_WITH_PEOPLE(3255, r3),
    SWIMMING_WITH_PEOPLE(3256, r3),
    TEMPLES_WITH_PEOPLE(3257, r3),
    FLOWERS_WITH_PEOPLE(3258, r3),
    INSTRUMENT_WITH_PEOPLE(3259, r3),
    COUCH_WITH_PEOPLE(3260, r3),
    TABLE_WITH_PEOPLE(3261, r3),
    DRESS_WITH_PEOPLE(3262, r3),
    HATS_WITH_PEOPLE(3263, r3),
    MOUNTAINS_WITH_PEOPLE_2(3264, r3),
    SNOW_WITH_PEOPLE(3265, r3),
    KART_WITH_BABY(3266, r3),
    TOYS_WITH_BABY(3267, r3),
    ANIMAL_WITH_BABY(3268, r3),
    DOGS_WITH_LOCATION(3269, r4),
    CATS_WITH_LOCATION(3270, r4),
    SPECIAL_SODA(3271, StoryLevel1Cat.SPECIAL),
    FAVORITE_SODA(3272, StoryLevel1Cat.FAVORITE),
    TRIP_SODA(3273, r2),
    TRIP_V7_SODA(3274, r2),
    ONDEMAND(3281, StoryLevel1Cat.ONDEMAND),
    MOMENTS(3282, StoryLevel1Cat.MOMENTS),
    TIME_DAILY_WITH_DATE(3290, r1),
    SPECIAL_DAY_SODA(3291, StoryLevel1Cat.SPECIAL_SODA),
    BIRTHDAY(3283, StoryLevel1Cat.PDE),
    CHRISTMAS_REMIND_STORY(3284, r1),
    CHRISTMAS_EVENT_STORY(3285, r1),
    QUARTERLY_RECAP_BRIEF(3286, r1),
    YEARLY_RECAP_BRIEF(3287, r1),
    YEARLY_RECAP_MOMENTS(3288, r1),
    YEARLY_RECAP_PEOPLE_AND_PETS(3289, r1),
    YEARLY_RECAP_PLACES(3292, r1),
    SPECIAL_TODAY(3293, StoryLevel1Cat.SPECIAL_TODAY);
    
    public final StoryLevel1Cat level1Cat;
    public final int sa_type;

    private StoryLevel2Cat(int i2, StoryLevel1Cat storyLevel1Cat) {
        this.sa_type = i2;
        this.level1Cat = storyLevel1Cat;
    }

    public static StoryLevel2Cat getCategory(int i2) {
        for (StoryLevel2Cat storyLevel2Cat : values()) {
            if (storyLevel2Cat.sa_type == i2) {
                return storyLevel2Cat;
            }
        }
        return DEFAULT;
    }

    public static boolean isCollection(int i2) {
        return Arrays.stream(values()).anyMatch(new b(i2, 7));
    }

    public static boolean isDaily(int i2) {
        if (isDailyWithYear(i2) || isDailyWithDate(i2)) {
            return true;
        }
        return false;
    }

    public static boolean isDailyWithDate(int i2) {
        if (i2 == TIME_DAILY_WITH_DATE.sa_type || i2 == SPECIAL_DAY_SODA.sa_type) {
            return true;
        }
        return false;
    }

    public static boolean isDailyWithYear(int i2) {
        if (i2 == TIME_DAILY_WITH_YEAR.sa_type || i2 == TIME_DAILY_WITH_YEAR_RUNEOFF.sa_type) {
            return true;
        }
        return false;
    }

    public static boolean isOuting(int i2) {
        if (i2 == TIME_OUTING_RUNEOFF.sa_type || i2 == TIME_OUTING.sa_type) {
            return true;
        }
        return false;
    }

    public static boolean isOverseas(int i2) {
        if (i2 == TIME_TRIP_OVERSEAS_RUNEOFF.sa_type || i2 == TIME_TRIP_OVERSEAS.sa_type) {
            return true;
        }
        return false;
    }

    public static boolean isPet(int i2) {
        return Arrays.stream(values()).anyMatch(new b(i2, 6));
    }

    public static boolean isScenery(int i2) {
        if (i2 == COLLECTION_SCENERY.sa_type) {
            return true;
        }
        return false;
    }

    public static boolean isSoda(int i2, int i7) {
        if (i7 == SPECIAL_SODA.sa_type || i7 == FAVORITE_SODA.sa_type || i7 == TRIP_SODA.sa_type || i7 == TRIP_V7_SODA.sa_type || i7 == ONDEMAND.sa_type) {
            return true;
        }
        if (i7 != SPECIAL_DAY_SODA.sa_type || i2 < 70) {
            return false;
        }
        return true;
    }

    public static String owner(int i2, int i7) {
        if (isSoda(i2, i7)) {
            return "SODA";
        }
        if (i2 == 1) {
            return "SELF";
        }
        return "CMH";
    }

    public StoryLevel1Cat getLevel1Cat() {
        return this.level1Cat;
    }

    public int getType() {
        return this.sa_type;
    }
}
