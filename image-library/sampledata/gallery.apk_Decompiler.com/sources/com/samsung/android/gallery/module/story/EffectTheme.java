package com.samsung.android.gallery.module.story;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StoryLevel1Cat;
import com.samsung.android.gallery.database.dbtype.StoryLevel2Cat;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.bgm.bgmlist.story.Bgm;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.RandomNumber;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum EffectTheme {
    ;
    
    final String[] mBgmList;
    final AnalyticsEventId mEventId;
    final String[] mPreloadBgmList;
    final int mTitle;

    static {
        int i2;
        int i7;
        int i8;
        SdkConfig.SEM sem = SdkConfig.SEM.T_MR5;
        if (SdkConfig.atLeast(sem)) {
            i2 = R$string.funny;
        } else {
            i2 = R$string.comics;
        }
        Comic = new EffectTheme("Comic", 0, i2, AnalyticsEventId.EVENT_THEME_COMICS);
        Happy = new EffectTheme("Happy", 1, R$string.happy, AnalyticsEventId.EVENT_THEME_HAPPY);
        Lounge = new EffectTheme("Lounge", 2, R$string.lounge, AnalyticsEventId.EVENT_THEME_LOUNGE);
        Relaxing = new EffectTheme("Relaxing", 3, R$string.relaxing, AnalyticsEventId.EVENT_THEME_RELAXING);
        Upbeat = new EffectTheme("Upbeat", 4, R$string.upbeat, AnalyticsEventId.EVENT_THEME_UPBEAT);
        Sentimental = new EffectTheme("Sentimental", 5, R$string.sentimental, AnalyticsEventId.EVENT_THEME_SENTIMENTAL);
        if (SdkConfig.atLeast(sem)) {
            i7 = R$string.mysterious;
        } else {
            i7 = R$string.mystery;
        }
        Mystery = new EffectTheme("Mystery", 6, i7, AnalyticsEventId.EVENT_THEME_MYSTERY);
        if (SdkConfig.atLeast(sem)) {
            i8 = R$string.romantic;
        } else {
            i8 = R$string.lovely;
        }
        Lovely = new EffectTheme("Lovely", 7, i8, AnalyticsEventId.EVENT_THEME_LOVELY);
        Intense = new EffectTheme("Intense", 8, R$string.intense, AnalyticsEventId.EVENT_THEME_INTENSE);
        Dynamic = new EffectTheme("Dynamic", 9, R$string.dynamic, AnalyticsEventId.EVENT_THEME_DYNAMIC);
        $VALUES = $values();
    }

    private EffectTheme(int i2, AnalyticsEventId analyticsEventId) {
        this.mTitle = i2;
        this.mBgmList = Bgm.getBgmList(name());
        this.mPreloadBgmList = Bgm.getPreloadBgmList(name());
        this.mEventId = analyticsEventId;
    }

    public static EffectTheme findTheme(String str) {
        for (EffectTheme effectTheme : values()) {
            for (String equals : effectTheme.getBgmList()) {
                if (equals.equals(str)) {
                    return effectTheme;
                }
            }
        }
        return Relaxing;
    }

    public static EffectTheme get(int i2) {
        if (i2 < 0 || i2 >= values().length) {
            return Relaxing;
        }
        return values()[i2];
    }

    public static String getBgmName(int i2, int i7) {
        String[] preloadBgmList = get(i7).getPreloadBgmList();
        return preloadBgmList[i2 % preloadBgmList.length];
    }

    public static String getHighlightEditShareKey(EffectTheme effectTheme) {
        if (Comic.equals(effectTheme)) {
            if (SdkConfig.atLeast(SdkConfig.SEM.T_MR5)) {
                return "Funny";
            }
            return effectTheme.name();
        } else if (Mystery.equals(effectTheme)) {
            if (SdkConfig.atLeast(SdkConfig.SEM.T_MR5)) {
                return "Mysterious";
            }
            return effectTheme.name();
        } else if (Lovely.equals(effectTheme)) {
            if (SdkConfig.atLeast(SdkConfig.SEM.T_MR5)) {
                return "Romantic";
            }
            return effectTheme.name();
        } else if (effectTheme != null) {
            return effectTheme.name();
        } else {
            return Relaxing.name();
        }
    }

    public static EffectTheme getMatchedTheme(Filter filter) {
        for (EffectTheme effectTheme : values()) {
            if (effectTheme.belong(filter)) {
                return effectTheme;
            }
        }
        return Relaxing;
    }

    public static EffectTheme getRandomTheme(int i2) {
        return values()[Math.abs(i2) % values().length];
    }

    public static EffectTheme[] getStoryEffects(FileItemInterface fileItemInterface) {
        int storySaType = MediaItemStory.getStorySaType(fileItemInterface);
        StoryLevel2Cat category = StoryLevel2Cat.getCategory(storySaType);
        StoryLevel1Cat level1Cat = category.getLevel1Cat();
        if (StoryLevel2Cat.DEFAULT.equals(category)) {
            level1Cat = StoryType.getTypeByValue(MediaItemStory.getStoryType(fileItemInterface)).getLevel1Cat();
        }
        if (StoryLevel1Cat.TIME.equals(level1Cat)) {
            if (StoryLevel2Cat.isOuting(storySaType) || StoryLevel2Cat.isDaily(storySaType)) {
                return new EffectTheme[]{Happy, Lounge, Relaxing, Upbeat, Dynamic};
            }
            return new EffectTheme[]{Happy, Lounge, Relaxing, Upbeat, Lovely};
        } else if (StoryLevel1Cat.PEOPLE.equals(level1Cat)) {
            return new EffectTheme[]{Happy, Lounge, Upbeat, Lovely};
        } else {
            if (StoryLevel1Cat.PET.equals(level1Cat)) {
                if (StoryLevel2Cat.PET_RANK_WITH_ME.equals(category) || StoryLevel2Cat.PET_RANK_WITH_PEOPLE.equals(category) || StoryLevel2Cat.DOGS_WITH_LOCATION.equals(category) || StoryLevel2Cat.CATS_WITH_LOCATION.equals(category)) {
                    return new EffectTheme[]{Comic, Happy, Relaxing, Lovely};
                }
                return new EffectTheme[]{Comic, Happy, Lovely};
            } else if (StoryLevel1Cat.TRANSITORY.equals(level1Cat)) {
                if (StoryLevel2Cat.RECENT_HIGHLIGHT.equals(category)) {
                    return new EffectTheme[]{Happy, Lounge, Relaxing, Upbeat, Lovely};
                }
                if (StoryLevel2Cat.BEST_MOMENT.equals(category)) {
                    return new EffectTheme[]{Happy, Lounge, Relaxing, Lovely};
                }
                return new EffectTheme[]{Happy, Lounge, Relaxing, Upbeat};
            } else if (!StoryLevel1Cat.COLLECTION.equals(level1Cat)) {
                return new EffectTheme[]{Happy, Lounge, Relaxing, Upbeat, Lovely, Dynamic};
            } else {
                if (StoryLevel2Cat.COUCH_WITH_PEOPLE.equals(category) || StoryLevel2Cat.BONFIRES_WITH_PEOPLE.equals(category)) {
                    return new EffectTheme[]{Lounge, Relaxing, Sentimental, Lovely};
                }
                if (StoryLevel2Cat.BEACHES_WITH_PEOPLE.equals(category) || StoryLevel2Cat.SKIES_WITH_PEOPLE.equals(category) || StoryLevel2Cat.SUNRISES_WITH_PEOPLE.equals(category) || StoryLevel2Cat.FLOWERS_WITH_PEOPLE.equals(category) || StoryLevel2Cat.SNOW_WITH_PEOPLE.equals(category) || StoryLevel2Cat.TEMPLES_WITH_PEOPLE.equals(category)) {
                    return new EffectTheme[]{Happy, Relaxing, Lovely};
                }
                if (StoryLevel2Cat.FIREWORKS_WITH_PEOPLE.equals(category) || StoryLevel2Cat.MOUNTAINS_WITH_PEOPLE_1.equals(category) || StoryLevel2Cat.MOUNTAINS_WITH_PEOPLE_2.equals(category) || StoryLevel2Cat.SWIMMING_WITH_PEOPLE.equals(category)) {
                    return new EffectTheme[]{Comic, Happy, Intense, Dynamic};
                }
                if (StoryLevel2Cat.TABLE_WITH_PEOPLE.equals(category) || StoryLevel2Cat.FOOD_WITH_PEOPLE.equals(category) || StoryLevel2Cat.CAKES_WITH_PEOPLE.equals(category) || StoryLevel2Cat.ICECREAM_WITH_PEOPLE.equals(category)) {
                    return new EffectTheme[]{Comic, Happy, Upbeat};
                }
                if (StoryLevel2Cat.INSTRUMENT_WITH_PEOPLE.equals(category) || StoryLevel2Cat.DRESS_WITH_PEOPLE.equals(category) || StoryLevel2Cat.HATS_WITH_PEOPLE.equals(category)) {
                    return new EffectTheme[]{Comic, Lounge, Lovely};
                }
                if (StoryLevel2Cat.KART_WITH_BABY.equals(category) || StoryLevel2Cat.TOYS_WITH_BABY.equals(category)) {
                    return new EffectTheme[]{Comic, Happy};
                }
                if (StoryLevel2Cat.ANIMAL_WITH_BABY.equals(category)) {
                    return new EffectTheme[]{Comic, Happy, Relaxing};
                }
                return new EffectTheme[]{Happy, Lounge, Upbeat, Lovely};
            }
        }
    }

    public static boolean isPreloadBgm(String str) {
        for (EffectTheme preloadBgmList : values()) {
            for (String equals : preloadBgmList.getPreloadBgmList()) {
                if (equals.equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean belong(Filter filter) {
        return FilterHolder.belongInTheme(this, filter);
    }

    public String[] getBgmList() {
        return this.mBgmList;
    }

    public AnalyticsEventId getEventId() {
        return this.mEventId;
    }

    public Filter getFilter() {
        return FilterHolder.getFilter(this);
    }

    public String[] getPreloadBgmList() {
        return this.mPreloadBgmList;
    }

    public String getRandomBgm(int i2) {
        return this.mPreloadBgmList[Math.abs(i2) % this.mPreloadBgmList.length];
    }

    public int getTitleResId() {
        return this.mTitle;
    }

    public static EffectTheme getRandomTheme() {
        return values()[RandomNumber.nextInt(values().length)];
    }

    public String getRandomBgm() {
        String[] strArr = this.mPreloadBgmList;
        return strArr[RandomNumber.nextInt(strArr.length)];
    }

    public static EffectTheme get(String str) {
        for (EffectTheme effectTheme : values()) {
            if (effectTheme.name().equals(str)) {
                return effectTheme;
            }
        }
        return null;
    }
}
