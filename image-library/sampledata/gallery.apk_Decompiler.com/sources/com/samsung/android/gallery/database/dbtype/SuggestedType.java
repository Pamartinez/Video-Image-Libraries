package com.samsung.android.gallery.database.dbtype;

import com.samsung.android.gallery.database.R$string;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.AppResources;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum SuggestedType {
    DUMMY(0),
    CLEANOUT_BURST_SIMILAR(5, R$string.suggestions_title_delete_burst_similar_pictures, R$string.suggestions_description_delete_burst_similar_pictures),
    COLLAGE(10),
    AGIF(20),
    VIDEO_COLLAGE(30),
    CLEANOUT_DUPLICATED_IMAGE(40, R$string.suggestions_title_delete_duplicate_pictures, R$string.suggestions_description_delete_duplicate_pictures),
    CLEANOUT_MOTION_PHOTO_CLIP(50, R$string.suggestions_title_motion_photo_clip, R$string.suggestions_description_motion_photo_clip),
    CLEANOUT(60),
    THEN_AND_NOW(70),
    REDISCOVER_DAY(80),
    HIGHLIGHT(90, R$string.suggestions_title_create_highlight_videos, R$string.suggestions_description_create_highlight_videos),
    PORTRAIT(100, R$string.add_portrait_effect, R$string.suggestions_description_change_portrait_effects),
    REMASTER(110);
    
    private final int mDescriptionResId;
    private final int mTitleResId;
    /* access modifiers changed from: private */
    public final int mValue;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TypeMapHolder {
        static final HashMap<Integer, SuggestedType> map = null;

        static {
            map = new HashMap<Integer, SuggestedType>() {
                {
                    for (SuggestedType suggestedType : SuggestedType.values()) {
                        put(Integer.valueOf(suggestedType.mValue), suggestedType);
                    }
                }
            };
        }

        public static SuggestedType get(int i2) {
            return map.getOrDefault(Integer.valueOf(i2), SuggestedType.DUMMY);
        }
    }

    private SuggestedType(int i2) {
        this.mValue = i2;
        this.mTitleResId = R$string.suggestions_title_remaster_pictures;
        this.mDescriptionResId = R$string.suggestions_description_remaster_pictures;
    }

    public static String getEventId(int i2, int i7, boolean z) {
        SuggestedType type = getType(i2);
        if (CLEANOUT_MOTION_PHOTO_CLIP.equals(type)) {
            if (z) {
                return AnalyticsEventId.EVENT_SUGGEST_MOTION_PHOTO_PICTURES_ARROW_CLICK.toString();
            }
            return AnalyticsEventId.EVENT_SUGGEST_MOTION_PHOTO_PICTURES_THUMBNAIL_CLICK.toString();
        } else if (CLEANOUT_DUPLICATED_IMAGE.equals(type)) {
            if (z) {
                return AnalyticsEventId.EVENT_SUGGEST_DUPLICATE_PICTURES_ARROW_CLICK.toString();
            }
            return AnalyticsEventId.EVENT_SUGGEST_DUPLICATE_PICTURES_THUMBNAIL_CLICK.toString();
        } else if (REMASTER.equals(type)) {
            if (z) {
                return AnalyticsEventId.EVENT_SUGGEST_REMASTER_PICTURES_ARROW_CLICK.toString();
            }
            return AnalyticsEventId.EVENT_SUGGEST_REMASTER_PICTURES_THUMBNAIL_CLICK.toString();
        } else if (HIGHLIGHT.equals(type)) {
            if (z) {
                return AnalyticsEventId.EVENT_SUGGEST_HIGHLIGHT_PICTURES_ARROW_CLICK.toString();
            }
            return AnalyticsEventId.EVENT_SUGGEST_HIGHLIGHT_PICTURES_THUMBNAIL_CLICK.toString();
        } else if (PORTRAIT.equals(type)) {
            if (z) {
                return AnalyticsEventId.EVENT_SUGGEST_PORTRAIT_PICTURES_ARROW_CLICK.toString();
            }
            return AnalyticsEventId.EVENT_SUGGEST_PORTRAIT_PICTURES_THUMBNAIL_CLICK.toString();
        } else if (i7 == 1) {
            if (z) {
                return AnalyticsEventId.EVENT_SUGGEST_EXPIRED_IMAGE_ARROW_CLICK.toString();
            }
            return AnalyticsEventId.EVENT_SUGGEST_EXPIRED_IMAGE_THUMBNAIL_CLICK.toString();
        } else if (z) {
            return AnalyticsEventId.EVENT_SUGGEST_BAD_QUALITY_IMAGE_ARROW_CLICK.toString();
        } else {
            return AnalyticsEventId.EVENT_SUGGEST_BAD_QUALITY_IMAGE_THUMBNAIL_CLICK.toString();
        }
    }

    public static SuggestedType getType(int i2) {
        return TypeMapHolder.get(i2);
    }

    public static String[] getTypeDescription(int i2, int i7) {
        int i8;
        int i10;
        String[] strArr = new String[2];
        SuggestedType type = getType(i2);
        if (type == CLEANOUT) {
            if (i7 == 1) {
                i8 = R$string.suggestions_title_clear_out_old_documents;
            } else {
                i8 = R$string.suggestions_title_delete_low_quality_pictures;
            }
            strArr[0] = AppResources.getString(i8);
            if (i7 == 1) {
                i10 = R$string.suggestions_description_clear_out_old_documents;
            } else {
                i10 = R$string.suggestions_description_delete_low_quality_pictures;
            }
            strArr[1] = AppResources.getString(i10);
            return strArr;
        }
        strArr[0] = AppResources.getString(type.mTitleResId);
        strArr[1] = AppResources.getString(type.mDescriptionResId);
        return strArr;
    }

    public int toInt() {
        return this.mValue;
    }

    private SuggestedType(int i2, int i7, int i8) {
        this.mValue = i2;
        this.mTitleResId = i7;
        this.mDescriptionResId = i8;
    }
}
