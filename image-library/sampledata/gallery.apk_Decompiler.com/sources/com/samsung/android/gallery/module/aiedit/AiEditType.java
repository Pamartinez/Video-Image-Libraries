package com.samsung.android.gallery.module.aiedit;

import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum AiEditType {
    None("none"),
    DevPoc("none"),
    Remaster("remaster", R$string.remaster, R$drawable.ic_ai_edit_remaster, AnalyticsEventId.EVENT_AI_EDIT_REMASTER),
    BackgroundEffect("background_effect", R$string.background_effect, R$drawable.ic_ai_edit_change_background, AnalyticsEventId.EVENT_AI_EDIT_BACKGROUND_EFFECT),
    PortraitEffect("background_blur", R$string.background_blur, R$drawable.ic_ai_edit_portrait_effect, AnalyticsEventId.EVENT_AI_EDIT_PORTRAIT),
    TimeLapse("24h_time_lapse", R$string.twenty_four_hour_time_lapse, R$drawable.ic_ai_edit_24hr, AnalyticsEventId.EVENT_AI_EDIT_TIMELAPSE),
    LongExposure("long_exposure", R$string.long_exposure, R$drawable.ic_ai_edit_long_exposure, (int) null),
    Colorize("colorize", r5, r6, AnalyticsEventId.EVENT_AI_EDIT_COLORIZE),
    EraseReflections("erase_reflections", R$string.erase_reflections, R$drawable.ic_ai_edit_erase_reflections, AnalyticsEventId.EVENT_AI_EDIT_ERASE_REFLECTION),
    EraseShadows("erase_shadows", R$string.erase_shadows, R$drawable.ic_ai_edit_erase_shadows, AnalyticsEventId.EVENT_AI_EDIT_ERASE_SHADOW),
    RemoveMoire("remove_moire", R$string.remove_moire, R$drawable.ic_ai_edit_remove_moire, AnalyticsEventId.EVENT_AI_EDIT_REMOVE_MOIRE),
    LiveEffect("live_effect", R$string.live_effect, R$drawable.ic_ai_edit_3d_photo, AnalyticsEventId.EVENT_AI_EDIT_3D_PHOTO),
    FixLensDistortion("fix_lens_distortion", R$string.fix_lens_distortion, R$drawable.ic_ai_edit_fix_distortion, AnalyticsEventId.EVENT_AI_EDIT_FIX_LENS_DISTORTION),
    RemoveLensFlare("remove_lens_flare", R$string.remove_lens_flare, R$drawable.ic_ai_edit_remove_flare, AnalyticsEventId.EVENT_AI_EDIT_REMOVE_FLARE),
    AutoTilt("auto_straighten"),
    Highlights("highlights", R$string.highlights, R$drawable.ic_ai_edit_video_highlights, AnalyticsEventId.EVENT_AI_EDIT_HIGHLIGHTS),
    SuperSlowMo("super_slow_mo", R$string.super_slow_mo, R$drawable.gallery_ic_ai_edit_superslow, AnalyticsEventId.EVENT_AI_EDIT_SUPER_SLOW_MOTION),
    ObjectEraser("object_eraser", R$string.object_eraser, R$drawable.ic_ai_edit_object_eraser, AnalyticsEventId.EVENT_AI_EDIT_OBJECT_ERASER),
    BestFace("best_face", R$string.best_face, R$drawable.ic_ai_edit_best_face, AnalyticsEventId.EVENT_AI_EDIT_BEST_FACE),
    ColorCorrect("color_grading", R$string.color_grading, r6, AnalyticsEventId.EVENT_AI_EDIT_COLOR_CORRECT);
    
    public final String bixbyKey;
    public final int drawableResId;
    public final AnalyticsEventId eventId;
    public final int titleResId;

    private AiEditType(String str) {
        this(r8, r9, str, 0, 0, (AnalyticsEventId) null);
    }

    public static AiEditType of(String str) {
        for (AiEditType aiEditType : values()) {
            if (aiEditType.bixbyKey.equals(str)) {
                return aiEditType;
            }
        }
        return null;
    }

    private AiEditType(String str, int i2, int i7, AnalyticsEventId analyticsEventId) {
        this.bixbyKey = str;
        this.titleResId = i2;
        this.drawableResId = i7;
        this.eventId = analyticsEventId;
    }
}
