package com.samsung.android.gallery.support.utils;

import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum PreferenceFeatures implements BooleanFeatures {
    BaiduCloudEnabled((String) PreferenceName.BAIDU_CLOUD_ENABLED),
    TencentCloudEnabled((String) PreferenceName.TENCENT_CLOUD_ENABLED),
    AlbumSplitMode((String) PreferenceName.SPLIT_MODE);
    
    public static final boolean SEP_GENERIC_DEVICE = false;
    public static boolean SUPPORT_RENAME_BY_MP = false;
    private static final String TAG = "PreferenceFeatures";
    public static final boolean VIDEO_THUMBNAIL_WITH_FIRST_FRAME = false;
    public static final boolean VIEWER50_ENTER_VI = true;
    private int mAnalyticsLoggingLevel;
    private final BooleanSupplier mDefaultSupplier;
    private final boolean mDefaultValue;
    private Boolean mIsEnabled;
    private final String mPreferenceKey;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CHINA {
        public static final boolean SHARING_SERVICE_ENABLER = false;

        static {
            SHARING_SERVICE_ENABLER = Features.isEnabled(Features.IS_CHINESE_DEVICE);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OneUi25 {
        public static final boolean THUMBNAIL_PREVIEW = false;

        static {
            THUMBNAIL_PREVIEW = PreferenceFeatures.isEnabled(PreferenceFeatures.ThumbnailPreview);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OneUi30 {
        public static boolean MEMORIES;
        public static final boolean MOTION_PHOTO_ZOOM = false;
        public static final boolean PHOTO_STRIP = false;
        public static boolean SUPPORT_GOOGLE_MOTION_PHOTO;
        public static boolean YEAR_CLUSTERING;

        static {
            boolean z;
            MEMORIES = PreferenceFeatures.isEnabled(PreferenceFeatures.StoryOneUi31);
            boolean z3 = PreferenceFeatures.SEP_GENERIC_DEVICE;
            YEAR_CLUSTERING = z3;
            boolean z7 = false;
            if (!z3 || !PreferenceFeatures.isEnabled(PreferenceFeatures.PhotoStrip41)) {
                z = false;
            } else {
                z = true;
            }
            PHOTO_STRIP = z;
            if (PreferenceFeatures.isEnabled(PreferenceFeatures.MotionPhotoZoom) || PreferenceFeatures.isEnabled(PreferenceFeatures.MotionPhotoPlayer)) {
                z7 = true;
            }
            MOTION_PHOTO_ZOOM = z7;
            SUPPORT_GOOGLE_MOTION_PHOTO = z7;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OneUi40 {
        public static final boolean ALBUM_CHOICE_MOVE_BAR = false;
        public static final boolean ALBUM_MOVE_FIRST_DEPTH = false;
        public static final boolean ALBUM_SORT_BY_COUNT = false;
        public static final boolean DISPLAY_CUSTOM_COVER = false;
        public static final boolean DISPLAY_CUSTOM_COVER_SHARING = false;
        static final boolean IS_ONE_UI_40 = false;
        public static final boolean MOTION_PHOTO_PLAYER = false;
        public static final boolean SUPPORT_COLLAGE_ON_VIDEO_TRIMMER = false;
        public static final boolean SUPPORT_MEMORY_COVER_ACTION_ON_SELECTION_MODE = false;
        public static final boolean SUPPORT_NONDESTRUCTIVE_REMASTER = false;
        public static final boolean SUPPORT_NONDESTRUCTIVE_SLOW_MO = false;
        public static final boolean SUPPORT_SHARE_SHEET = false;
        public static final boolean SUPPORT_STORIES_HIDE_RULE = false;
        public static final boolean SUPPORT_STORIES_PIN = false;
        public static final boolean SUPPORT_STORIES_REMINDER = false;
        public static final boolean SUPPORT_STORY_COVER_SLIDESHOW = false;
        public static final boolean SUPPORT_STORY_COVER_SLIDESHOW_V2 = false;
        public static final boolean SUPPORT_STORY_PICTURES_MAP = false;

        static {
            boolean z;
            boolean z3;
            boolean z7 = true;
            if (SdkConfig.atLeast(SdkConfig.SEM.S) || Features.isEnabled(Features.SUPPORT_ANDROID_EMULATOR)) {
                z = true;
            } else {
                z = false;
            }
            IS_ONE_UI_40 = z;
            if (!VideoPlayerFeature.SUPPORT_GALLERY_PLAYER || !PreferenceFeatures.isEnabled(PreferenceFeatures.MotionPhotoPlayer)) {
                z3 = false;
            } else {
                z3 = true;
            }
            MOTION_PHOTO_PLAYER = z3;
            ALBUM_SORT_BY_COUNT = z;
            ALBUM_MOVE_FIRST_DEPTH = z;
            ALBUM_CHOICE_MOVE_BAR = z;
            DISPLAY_CUSTOM_COVER = z;
            DISPLAY_CUSTOM_COVER_SHARING = z;
            SUPPORT_STORY_COVER_SLIDESHOW = z;
            SUPPORT_STORY_COVER_SLIDESHOW_V2 = z;
            SUPPORT_STORY_PICTURES_MAP = z;
            SUPPORT_MEMORY_COVER_ACTION_ON_SELECTION_MODE = z;
            SUPPORT_COLLAGE_ON_VIDEO_TRIMMER = z;
            SUPPORT_SHARE_SHEET = z;
            SUPPORT_STORIES_REMINDER = z;
            SUPPORT_STORIES_PIN = z;
            if (!z || !Features.isEnabled(Features.SUPPORT_HIDE_RULE)) {
                z7 = false;
            }
            SUPPORT_STORIES_HIDE_RULE = z7;
            SUPPORT_NONDESTRUCTIVE_REMASTER = z;
            SUPPORT_NONDESTRUCTIVE_SLOW_MO = z;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OneUi41 {
        public static final boolean CLEANOUT_DUPLICATED_IMAGE = false;
        public static final boolean CLEANOUT_MOTION_PHOTO_CLIP = false;
        public static final boolean DISABLE_CLEANOUT_BAD_QUALITY_IMAGE = false;
        public static boolean HIGHLIGHT_SUGGESTIONS;
        static final boolean IS_ONE_UI_41 = false;
        public static final boolean LOG_ACTION_VIEW = false;
        public static boolean PORTRAIT_SUGGESTIONS;
        public static boolean REMASTER_SUGGESTIONS;
        public static boolean SHARING_ALBUM_STORAGE_USAGE;
        public static final boolean SHARING_ALBUM_WEB_LINK = false;
        public static boolean SHARING_INVITATION_ON_ALBUMS;
        public static final boolean SHARING_LEADER_AUTHORITY_DELEGATION = false;
        public static final boolean SHARING_ONE_PERSON_SHARED_ALBUM_NOTICE = false;
        public static final boolean SHOW_SUGGESTION_HEADER = false;
        static final boolean SUGGEST_INTELLIGENT_STUFF = false;
        public static final boolean SUPPORT_CAMERA_AI = false;
        public static final boolean SUPPORT_DEEP_SKY_DONATION = false;
        public static boolean SUPPORT_DIRECTORS_VIEW;
        public static final boolean SUPPORT_PERMANENT_ALBUM_COVER = false;
        public static final boolean SUPPORT_PORTRAIT_CHANGE = false;

        static {
            boolean z;
            boolean z3;
            boolean z7;
            boolean z9;
            boolean z10;
            boolean z11;
            boolean z12;
            boolean z13;
            boolean z14 = true;
            if (SdkConfig.atLeast(SdkConfig.SEM.S_MR1) || Features.isEnabled(Features.SUPPORT_ANDROID_EMULATOR)) {
                z = true;
            } else {
                z = false;
            }
            IS_ONE_UI_41 = z;
            if (!z || OneUi7x.IS_ONE_UI_70) {
                z3 = false;
            } else {
                z3 = true;
            }
            SHOW_SUGGESTION_HEADER = z3;
            boolean isEnabled = PreferenceFeatures.isEnabled(PreferenceFeatures.SuggestIntelligent);
            SUGGEST_INTELLIGENT_STUFF = isEnabled;
            if (!isEnabled || !z) {
                z7 = false;
            } else {
                z7 = true;
            }
            HIGHLIGHT_SUGGESTIONS = z7;
            if (!isEnabled || !z) {
                z9 = false;
            } else {
                z9 = true;
            }
            PORTRAIT_SUGGESTIONS = z9;
            if (!isEnabled || !SdkConfig.atLeast(SdkConfig.SEM.R_MR1)) {
                z10 = false;
            } else {
                z10 = true;
            }
            REMASTER_SUGGESTIONS = z10;
            if (!z || !PreferenceFeatures.SEP_GENERIC_DEVICE) {
                z11 = false;
            } else {
                z11 = true;
            }
            CLEANOUT_MOTION_PHOTO_CLIP = z11;
            CLEANOUT_DUPLICATED_IMAGE = z;
            DISABLE_CLEANOUT_BAD_QUALITY_IMAGE = z;
            if (z || PreferenceFeatures.isEnabled(PreferenceFeatures.PermanentAlbumCover)) {
                z12 = true;
            } else {
                z12 = false;
            }
            SUPPORT_PERMANENT_ALBUM_COVER = z12;
            if (!z || !Features.isEnabled(Features.SUPPORT_CAMERA_AI)) {
                z13 = false;
            } else {
                z13 = true;
            }
            SUPPORT_CAMERA_AI = z13;
            if (!z || !Features.isEnabled(Features.SUPPORT_PORTRAIT)) {
                z14 = false;
            }
            SUPPORT_PORTRAIT_CHANGE = z14;
            SUPPORT_DIRECTORS_VIEW = z;
            SUPPORT_DEEP_SKY_DONATION = z;
            LOG_ACTION_VIEW = z;
            SHARING_INVITATION_ON_ALBUMS = z;
            SHARING_ALBUM_WEB_LINK = Features.isEnabled(Features.SUPPORT_SHARED_WEB_LINK);
            SHARING_ALBUM_STORAGE_USAGE = Features.isEnabled(Features.SUPPORT_SHARED_STORAGE_USAGE);
            SHARING_ONE_PERSON_SHARED_ALBUM_NOTICE = Features.isEnabled(Features.SUPPORT_SHARING_ONE_PERSON_SHARED_ALBUM_NOTICE);
            SHARING_LEADER_AUTHORITY_DELEGATION = Features.isEnabled(Features.SUPPORT_LEADER_AUTHORITY_DELEGATION);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OneUi5x {
        static final boolean IS_ONE_UI_51 = false;
        static final boolean IS_ONE_UI_511 = false;
        public static boolean MX_ALBUMS;
        public static final boolean REMASTER_PICTURES_V2 = false;
        public static final boolean SEARCH_HIDE_PEOPLE = false;
        public static boolean STORY_ONE_UI_50;
        public static final boolean SUPPORT_ADDRESS_FROM_DATABASE = false;
        public static final boolean SUPPORT_FACE_CLUSTER = false;
        public static boolean SUPPORT_SEARCH_MULTIPLE_KEYWORD;
        public static final boolean SUPPORT_SEARCH_PEOPLE_FACE_SCORE = false;
        public static boolean SUPPORT_SHRINK_TO_CAMERA;
        public static final boolean SUPPORT_STORIES_TITLE_ALIGN = false;
        public static boolean SUPPORT_UNIFIED_CREATURE_KEY;

        static {
            boolean z;
            boolean atLeast = SdkConfig.atLeast(SdkConfig.SEM.T_MR1);
            IS_ONE_UI_51 = atLeast;
            IS_ONE_UI_511 = SdkConfig.atLeast(SdkConfig.SEM.T_MR5);
            STORY_ONE_UI_50 = PreferenceFeatures.isEnabled(PreferenceFeatures.StoryOneUi50);
            SUPPORT_FACE_CLUSTER = PreferenceFeatures.isEnabled(PreferenceFeatures.FaceCluster);
            boolean z3 = true;
            if (atLeast || PreferenceFeatures.isEnabled(PreferenceFeatures.SearchMultipleKeyword)) {
                z = true;
            } else {
                z = false;
            }
            SUPPORT_SEARCH_MULTIPLE_KEYWORD = z;
            if (!atLeast && !PreferenceFeatures.isEnabled(PreferenceFeatures.SearchHidePeople)) {
                z3 = false;
            }
            SEARCH_HIDE_PEOPLE = z3;
            REMASTER_PICTURES_V2 = PreferenceFeatures.isEnabled(PreferenceFeatures.RemasterPicturesV2);
            MX_ALBUMS = PreferenceFeatures.isEnabled(PreferenceFeatures.MxAlbums);
            SUPPORT_SEARCH_PEOPLE_FACE_SCORE = PreferenceFeatures.isEnabled(PreferenceFeatures.SearchPeopleFaceScore);
            SUPPORT_STORIES_TITLE_ALIGN = atLeast;
            SUPPORT_UNIFIED_CREATURE_KEY = Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY);
            SUPPORT_SHRINK_TO_CAMERA = atLeast;
            SUPPORT_ADDRESS_FROM_DATABASE = PreferenceFeatures.isEnabled(PreferenceFeatures.AddressFromDatabase);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OneUi6x {
        public static final boolean IS_ONE_UI_60 = false;
        public static final boolean IS_ONE_UI_61 = false;
        public static final boolean IS_ONE_UI_611 = false;
        public static boolean SIMPLE_FAST_OPTIONS;
        public static boolean SUPPORT_AI_EDIT_ABOVE_DETAILS;
        public static final boolean SUPPORT_AI_EDIT_FLARE_AND_DISTORTION = false;
        public static final boolean SUPPORT_AI_EDIT_GROUP_PANEL = false;
        public static boolean SUPPORT_CUSTOM_RELATIONSHIP;
        public static final boolean SUPPORT_EMBEDDED_THUMBNAIL_IN_VIDEO = false;
        public static final boolean SUPPORT_INSTANT_SLOW_MO = false;
        public static final boolean SUPPORT_INSTANT_SLOW_MO_SAVE_CLIP = false;
        public static final boolean SUPPORT_MERGE_PEOPLE_WITHOUT_NAME = false;
        public static final boolean SUPPORT_MOTION_PHOTO_INSTANT_SLOW_MO = false;
        public static final boolean SUPPORT_NEW_HIDE_SCENE_SELECTION = false;
        public static final boolean SUPPORT_ONE_TRASH = false;
        public static final boolean SUPPORT_PE_GEN_EDIT = false;
        public static final boolean SUPPORT_PICKER_PRESELECTED = false;
        public static final boolean SUPPORT_PREFERENCE_BACKUP_AND_RESTORE = false;
        public static final boolean SUPPORT_QOD_SEARCH = false;
        public static final boolean SUPPORT_SCREEN_SHOT_FILTER = false;
        public static final boolean SUPPORT_SHARE_STORY = false;
        public static boolean SUPPORT_SHARING_SORT_BY;
        public static final boolean SUPPORT_VIDEO_KEY_FRAME_INDEX = false;
        public static final boolean SUPPORT_VIDEO_SPEED_CONTROLLER = false;
        public static boolean SUPPORT_VIEWPAGER_MASKING;
        public static final boolean TIMELINE_GRID_EXTENSION = false;
        public static final boolean VISUAL_SEARCH_61 = false;

        static {
            boolean z;
            boolean z3;
            boolean atLeast = SdkConfig.atLeast(SdkConfig.SEM.U);
            IS_ONE_UI_60 = atLeast;
            boolean atLeast2 = SdkConfig.atLeast(SdkConfig.SEM.U_MR1);
            IS_ONE_UI_61 = atLeast2;
            boolean atLeast3 = SdkConfig.atLeast(SdkConfig.SEM.U_MR5);
            IS_ONE_UI_611 = atLeast3;
            SUPPORT_AI_EDIT_ABOVE_DETAILS = atLeast;
            boolean z7 = true;
            SIMPLE_FAST_OPTIONS = !PreferenceFeatures.isEnabled(PreferenceFeatures.MoreOptionsInViewerBottom);
            SUPPORT_VIEWPAGER_MASKING = atLeast;
            SUPPORT_CUSTOM_RELATIONSHIP = atLeast;
            SUPPORT_SHARING_SORT_BY = atLeast;
            TIMELINE_GRID_EXTENSION = PreferenceFeatures.isEnabled(PreferenceFeatures.TimelineGridExtension);
            if (!PreferenceFeatures.isEnabled(PreferenceFeatures.NewTrash) || !Features.isEnabled(Features.SUPPORT_ONE_TRASH)) {
                z = false;
            } else {
                z = true;
            }
            SUPPORT_ONE_TRASH = z;
            SUPPORT_PREFERENCE_BACKUP_AND_RESTORE = atLeast;
            boolean isEnabled = PreferenceFeatures.isEnabled(PreferenceFeatures.VisualSearch61);
            VISUAL_SEARCH_61 = isEnabled;
            SUPPORT_MERGE_PEOPLE_WITHOUT_NAME = atLeast;
            SUPPORT_SHARE_STORY = atLeast2;
            SUPPORT_EMBEDDED_THUMBNAIL_IN_VIDEO = atLeast2;
            SUPPORT_SCREEN_SHOT_FILTER = atLeast2;
            SUPPORT_AI_EDIT_GROUP_PANEL = atLeast2;
            SUPPORT_NEW_HIDE_SCENE_SELECTION = PreferenceFeatures.isEnabled(PreferenceFeatures.HideSceneSelection2);
            SUPPORT_VIDEO_KEY_FRAME_INDEX = atLeast3;
            SUPPORT_PICKER_PRESELECTED = atLeast3;
            SUPPORT_VIDEO_SPEED_CONTROLLER = PreferenceFeatures.isEnabled(PreferenceFeatures.VideoSpeedController);
            SUPPORT_QOD_SEARCH = isEnabled;
            boolean isEnabled2 = Features.isEnabled(Features.SUPPORT_INSTANT_SLOW_MO);
            SUPPORT_INSTANT_SLOW_MO = isEnabled2;
            if (!isEnabled2 || !atLeast3) {
                z3 = false;
            } else {
                z3 = true;
            }
            SUPPORT_INSTANT_SLOW_MO_SAVE_CLIP = z3;
            SUPPORT_MOTION_PHOTO_INSTANT_SLOW_MO = isEnabled2;
            SUPPORT_AI_EDIT_FLARE_AND_DISTORTION = atLeast3;
            if (!atLeast3 || !Features.isEnabled(Features.SUPPORT_PE_GEN_EDIT)) {
                z7 = false;
            }
            SUPPORT_PE_GEN_EDIT = z7;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OneUi7x {
        public static final boolean DISABLE_TIMELINE_ON_KEYWORD = false;
        public static final boolean FRAGMENT_H_SLIDE_ANIMATION = false;
        public static final boolean IS_ONE_UI_70 = false;
        public static final boolean REDUCED_RELATIONSHIP_TYPE = false;
        public static final boolean SEARCH_RESULT_EXPAND = false;
        public static final boolean STORY_LAST_PAGE = false;
        public static final boolean STORY_ONE_UI_70 = false;
        public static final boolean SUPPORT_AC_UNIFIED_ITEM = false;
        public static final boolean SUPPORT_BNR_V2 = false;
        public static final boolean SUPPORT_BOTTOM_TAB_MENU = false;
        public static final boolean SUPPORT_BOTTOM_TAB_PICKER_SEARCH = false;
        public static final boolean SUPPORT_LIGHTING_EFFECT = false;
        public static final boolean SUPPORT_MOTION_PHOTO_FPS_SCALE_UP = false;
        public static final boolean SUPPORT_MOTION_PHOTO_VIEW_MODE = false;
        public static final boolean SUPPORT_MOTION_PHOTO_VIEW_MODE_SAVE_AS = false;
        public static final boolean SUPPORT_MOUSE_USABILITY_V2 = false;
        public static final boolean SUPPORT_MY_QUERY = false;
        public static final boolean SUPPORT_PHOTO_ASSIST_SETTING = false;
        public static final boolean SUPPORT_QS_UNIFIED_ITEM = false;
        public static final boolean SUPPORT_STORY_LIVE_EFFECT_TYPE = false;
        public static final boolean SUPPORT_TOP5_CREATURE = false;
        public static final boolean SUPPORT_TOP_RESULT_SEARCH = false;
        public static final boolean SUPPORT_UNMERGE_CREATURE = false;
        public static final boolean TEXT_EXTRACTION_MAGNIFIER = false;
        public static final boolean VISUAL_SEARCH_71 = false;

        static {
            boolean z;
            boolean z3;
            boolean z7;
            boolean z9;
            boolean atLeast = SdkConfig.atLeast(SdkConfig.SEM.V);
            IS_ONE_UI_70 = atLeast;
            FRAGMENT_H_SLIDE_ANIMATION = atLeast;
            STORY_LAST_PAGE = PreferenceFeatures.isEnabled(PreferenceFeatures.StoryLastPage);
            SUPPORT_BNR_V2 = atLeast;
            TEXT_EXTRACTION_MAGNIFIER = atLeast;
            PreferenceFeatures preferenceFeatures = PreferenceFeatures.MotionPhotoViewMode;
            SUPPORT_MOTION_PHOTO_VIEW_MODE = PreferenceFeatures.isEnabled(preferenceFeatures);
            boolean z10 = false;
            if (!atLeast || !PreferenceFeatures.isEnabled(preferenceFeatures)) {
                z = false;
            } else {
                z = true;
            }
            SUPPORT_MOTION_PHOTO_VIEW_MODE_SAVE_AS = z;
            SUPPORT_MOTION_PHOTO_FPS_SCALE_UP = atLeast;
            SUPPORT_MOUSE_USABILITY_V2 = atLeast;
            SUPPORT_PHOTO_ASSIST_SETTING = Features.isEnabled(Features.SUPPORT_AI_SETTINGS);
            STORY_ONE_UI_70 = PreferenceFeatures.isEnabled(PreferenceFeatures.StoryOneUi70);
            boolean isEnabled = PreferenceFeatures.isEnabled(PreferenceFeatures.VisualSearch71);
            VISUAL_SEARCH_71 = isEnabled;
            SUPPORT_BOTTOM_TAB_PICKER_SEARCH = PreferenceFeatures.isEnabled(PreferenceFeatures.BottomTabPickerSearch);
            SUPPORT_BOTTOM_TAB_MENU = PreferenceFeatures.isEnabled(PreferenceFeatures.BottomTabMenu);
            SUPPORT_UNMERGE_CREATURE = PreferenceFeatures.isEnabled(PreferenceFeatures.UnmergeCreature);
            SUPPORT_STORY_LIVE_EFFECT_TYPE = atLeast;
            if (!isEnabled || !Features.isEnabled(Features.SUPPORT_TOP_RESULT_SEARCH)) {
                z3 = false;
            } else {
                z3 = true;
            }
            SUPPORT_TOP_RESULT_SEARCH = z3;
            DISABLE_TIMELINE_ON_KEYWORD = z3;
            SEARCH_RESULT_EXPAND = z3;
            if (!isEnabled || !SdkConfig.lessThan(SdkConfig.SEM.B_MR5)) {
                z7 = false;
            } else {
                z7 = true;
            }
            SUPPORT_TOP5_CREATURE = z7;
            if (!isEnabled || !Features.isEnabled(Features.SUPPORT_SCS_SEARCH)) {
                z9 = false;
            } else {
                z9 = true;
            }
            SUPPORT_MY_QUERY = z9;
            REDUCED_RELATIONSHIP_TYPE = atLeast;
            SUPPORT_QS_UNIFIED_ITEM = isEnabled;
            if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_AUTOCOMPLETE_UNIFIED) || (!Features.isEnabled(Features.SUPPORT_SCS_SEARCH_AUTOCOMPLETE) && isEnabled)) {
                z10 = true;
            }
            SUPPORT_AC_UNIFIED_ITEM = z10;
            SUPPORT_LIGHTING_EFFECT = Features.isEnabled(Features.SUPPORT_AI_PROCESSING_EFFECT);
        }

        public static boolean isAutoUpscaleEnabled() {
            return !GalleryPreference.getInstance().loadString(PreferenceName.AUTO_UPSCALE_IMAGE).equals("0");
        }

        public static boolean isMaxAutoUpscale() {
            return GalleryPreference.getInstance().loadString(PreferenceName.AUTO_UPSCALE_IMAGE).equals("2");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OneUi8x {
        public static final boolean COLLECTION_TAB = false;
        public static final boolean FLOATING_RECOMMENDATION_V2 = false;
        public static final boolean FLOATING_RECOMMENDATION_V3 = false;
        public static final boolean INSTANT_SEARCH = false;
        public static final boolean IS_ONE_UI_80 = false;
        public static final boolean IS_ONE_UI_85 = false;
        public static final boolean MX_ALBUM_BLUR = false;
        public static final boolean REMOVE_SHARED_DRAWER_TAB_MENU = false;
        public static final boolean SCREEN_SHOT_FILTER_REORDER = false;
        public static final boolean SEARCH_CATEGORY_SCREENSHOT = false;
        public static final boolean SEARCH_CREATURE_COVER_CHOICE = false;
        public static final boolean STORY_AI_EDIT_VI = false;
        public static final boolean STORY_COVER_BLUR = false;
        public static final boolean STORY_IRREGULAR_COLLAGE = false;
        public static final boolean STORY_ONE_UI_80 = false;
        public static final boolean STORY_ONE_UI_85 = false;
        public static final boolean SUPPORT_BOTTOM_TAB_SHOT_MODE_MENU = false;
        public static final boolean SUPPORT_CREATURE_THUMB_FROM_VIDEO_FRAME = false;
        public static final boolean SUPPORT_ESSENTIAL_FACES = false;
        public static final boolean SUPPORT_GROUPING_SEARCH_FILTER = false;
        public static final boolean SUPPORT_RECAP = false;
        public static final boolean SUPPORT_SEARCH_ANALYSIS_TIP_HEADER = false;
        public static final boolean SUPPORT_SELECTION_COLUMN_BLOCK_FILTER = false;
        public static final boolean SUPPORT_STORY_POLYGON_POI = false;
        public static final boolean SUPPORT_TRANSITORY_ON_DEMAND_STORY = false;
        public static final boolean VIDEO_SEARCH = false;
        public static final boolean VISUAL_SEARCH_85 = false;

        static {
            boolean z;
            boolean z3;
            boolean z7;
            boolean z9;
            boolean z10;
            boolean z11;
            boolean z12;
            boolean atLeast = SdkConfig.atLeast(SdkConfig.SEM.B);
            IS_ONE_UI_80 = atLeast;
            STORY_ONE_UI_80 = atLeast;
            FLOATING_RECOMMENDATION_V2 = atLeast;
            boolean z13 = false;
            if (!OneUi6x.SUPPORT_SCREEN_SHOT_FILTER || !PreferenceFeatures.isEnabled(PreferenceFeatures.ScreenshotFilterCustom)) {
                z = false;
            } else {
                z = true;
            }
            SCREEN_SHOT_FILTER_REORDER = z;
            SdkConfig.SEM sem = SdkConfig.SEM.B_MR5;
            boolean atLeast2 = SdkConfig.atLeast(sem);
            IS_ONE_UI_85 = atLeast2;
            if (!OneUi7x.STORY_LAST_PAGE || (!atLeast2 && !PreferenceFeatures.isEnabled(PreferenceFeatures.StoryCollageForceCreation) && !PreferenceFeatures.isEnabled(PreferenceFeatures.StoryIrregularCollage))) {
                z3 = false;
            } else {
                z3 = true;
            }
            STORY_IRREGULAR_COLLAGE = z3;
            STORY_ONE_UI_85 = atLeast2;
            STORY_AI_EDIT_VI = atLeast2;
            STORY_COVER_BLUR = PreferenceFeatures.isEnabled(PreferenceFeatures.StoryCoverBlur);
            if (!atLeast2 || !Features.isEnabled(Features.SUPPORT_PEOPLE_FACE_SCORE)) {
                z7 = false;
            } else {
                z7 = true;
            }
            SEARCH_CREATURE_COVER_CHOICE = z7;
            FLOATING_RECOMMENDATION_V3 = atLeast2;
            if (!PreferenceFeatures.isEnabled(PreferenceFeatures.SearchCategoryScreenShot) || !Features.isEnabled(Features.SUPPORT_OCR_ENGINE)) {
                z9 = false;
            } else {
                z9 = true;
            }
            SEARCH_CATEGORY_SCREENSHOT = z9;
            boolean isEnabled = PreferenceFeatures.isEnabled(PreferenceFeatures.Collection);
            COLLECTION_TAB = isEnabled;
            VISUAL_SEARCH_85 = atLeast2;
            if (!atLeast2 || !Features.isEnabled(Features.SUPPORT_VIDEO_SEARCH)) {
                z10 = false;
            } else {
                z10 = true;
            }
            VIDEO_SEARCH = z10;
            if (!PreferenceFeatures.isEnabled(PreferenceFeatures.SupportCreatureThumbFromVideoFrame) || !atLeast2 || !Features.isEnabled(Features.SUPPORT_VIDEO_SEARCH)) {
                z11 = false;
            } else {
                z11 = true;
            }
            SUPPORT_CREATURE_THUMB_FROM_VIDEO_FRAME = z11;
            SUPPORT_SELECTION_COLUMN_BLOCK_FILTER = SdkConfig.atLeast(sem);
            MX_ALBUM_BLUR = PreferenceFeatures.isEnabled(PreferenceFeatures.MxBlurAlbums);
            if (!atLeast2 || !Features.isEnabled(Features.SUPPORT_SCS_SEARCH) || !Features.isEnabled(Features.SUPPORT_INSTANT_SEARCH)) {
                z12 = false;
            } else {
                z12 = true;
            }
            INSTANT_SEARCH = z12;
            if (atLeast2 && Features.isEnabled(Features.SUPPORT_FACES_GROUP)) {
                z13 = true;
            }
            SUPPORT_ESSENTIAL_FACES = z13;
            SUPPORT_GROUPING_SEARCH_FILTER = atLeast2;
            SUPPORT_BOTTOM_TAB_SHOT_MODE_MENU = atLeast2;
            SUPPORT_TRANSITORY_ON_DEMAND_STORY = isEnabled;
            SUPPORT_STORY_POLYGON_POI = Features.isEnabled(Features.CMH_TO_MP_MIGRATION);
            SUPPORT_RECAP = PreferenceFeatures.isEnabled(PreferenceFeatures.SupportRecap);
            REMOVE_SHARED_DRAWER_TAB_MENU = atLeast2;
            SUPPORT_SEARCH_ANALYSIS_TIP_HEADER = atLeast2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PERFORMANCE {
        public static final boolean STORIES_CURSOR_CACHE = false;

        static {
            STORIES_CURSOR_CACHE = PreferenceFeatures.isEnabled(PreferenceFeatures.StoriesCursorCache);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Poc {
        public static boolean SUPPORT_BURST_SHOT_ZOOM;
        public static boolean SUPPORT_EXPOSE_NONDESTRUCTIVE_RECORDING_IN_SEARCH;
        public static boolean USE_CONCAT_THUMBNAIL;
        public static final boolean VIDEO_PLAY_ICON_ON_TOOLBAR = false;

        static {
            boolean z;
            if (!OneUi40.SUPPORT_NONDESTRUCTIVE_SLOW_MO || !PreferenceFeatures.isEnabled(PreferenceFeatures.ExposeNonDestructiveRecordingInSearch)) {
                z = false;
            } else {
                z = true;
            }
            SUPPORT_EXPOSE_NONDESTRUCTIVE_RECORDING_IN_SEARCH = z;
            SUPPORT_BURST_SHOT_ZOOM = PreferenceFeatures.isEnabled(PreferenceFeatures.BurstShotZoom);
            VIDEO_PLAY_ICON_ON_TOOLBAR = false;
            USE_CONCAT_THUMBNAIL = PreferenceFeatures.isEnabled(PreferenceFeatures.UseConcatThumbnail);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Remote {
        public static final boolean PLAY_VIDEO_IN_PRESENTATION = false;
        public static final boolean PLAY_VIDEO_IN_SLIDESHOW_PRESENTATION = false;
        public static final boolean SHOW_INFO_IN_PRESENTATION = false;

        static {
            PLAY_VIDEO_IN_PRESENTATION = PreferenceFeatures.isEnabled(PreferenceFeatures.PlayVideoInPresentation);
            PLAY_VIDEO_IN_SLIDESHOW_PRESENTATION = PreferenceFeatures.isEnabled(PreferenceFeatures.PlayVideoInSlideshowPresentation);
            SHOW_INFO_IN_PRESENTATION = PreferenceFeatures.isEnabled(PreferenceFeatures.ShowInfoInPresentation);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class VideoPlayerFeature {
        private static final String KEY_NAME = null;
        public static int PlayerMode;
        public static final boolean SUPPORT_GALLERY_PLAYER = false;
        public static final boolean SUPPORT_SETTING = false;

        static {
            boolean z;
            KEY_NAME = PreferenceName.VIDEO_PLAYER_MODE.key();
            boolean z3 = true;
            if (!Features.isEnabled(Features.IS_SEP_LITE) || Features.isEnabled(Features.SUPPORT_ANDROID_EMULATOR)) {
                z = true;
            } else {
                z = false;
            }
            SUPPORT_GALLERY_PLAYER = z;
            if (!OneUi7x.IS_ONE_UI_70 || !z) {
                z3 = false;
            }
            SUPPORT_SETTING = z3;
            PlayerMode = -1;
        }

        public static int getVideoPlayerMode() {
            if (PlayerMode == -1) {
                PlayerMode = GalleryPreference.getInstance().loadInt(KEY_NAME, SUPPORT_GALLERY_PLAYER ^ true ? 1 : 0);
            }
            return PlayerMode;
        }

        public static boolean isOpenInGalleryVideoPlayer() {
            if (getVideoPlayerMode() != 1) {
                return true;
            }
            return false;
        }

        public static boolean isOpenInVideoPlayer() {
            if (getVideoPlayerMode() == 1) {
                return true;
            }
            return false;
        }

        public static boolean isSupportFilmSeeker() {
            if (getVideoPlayerMode() == 0) {
                return true;
            }
            return false;
        }

        public static void setOpenInVideoPlayer(boolean z) {
            PlayerMode = z ? 1 : 0;
            GalleryPreference.getInstance().saveState(KEY_NAME, PlayerMode);
        }

        public static void setVideoPlayerMode(int i2) {
            if (i2 > 2) {
                i2 = 0;
            }
            PlayerMode = i2;
            GalleryPreference.getInstance().saveState(KEY_NAME, PlayerMode);
        }
    }

    static {
        BaiduCloudEnabled = new PreferenceFeatures("BaiduCloudEnabled", 0, PreferenceName.BAIDU_CLOUD_ENABLED);
        TencentCloudEnabled = new PreferenceFeatures("TencentCloudEnabled", 1, PreferenceName.TENCENT_CLOUD_ENABLED);
        AlbumSplitMode = new PreferenceFeatures("AlbumSplitMode", 2, PreferenceName.SPLIT_MODE);
        LocationAuth = new PreferenceFeatures("LocationAuth", 3, Features.isEnabled(Features.SUPPORT_SAMSUNG_PLACE) ? PreferenceName.LOCATION_AUTH_V2 : PreferenceName.LOCATION_AUTH);
        AutoPlayMotionPhoto = new PreferenceFeatures("AutoPlayMotionPhoto", 4, PreferenceName.AUTO_PLAY_MOTION_PHOTO);
        Meitu = new PreferenceFeatures("Meitu", 5, PreferenceName.MEITU);
        SharingServiceEnabled = new PreferenceFeatures("SharingServiceEnabled", 6, true);
        SharingNotification = new PreferenceFeatures("SharingNotification", 7, PreferenceName.SHARED_ALBUM_NOTIFICATION);
        SharingAlbumNewAlbumNotification = new PreferenceFeatures("SharingAlbumNewAlbumNotification", 8, PreferenceName.KEY_NEW_ALBUMS);
        SharingAlbumNewPostNotification = new PreferenceFeatures("SharingAlbumNewPostNotification", 9, PreferenceName.KEY_NEW_POSTS);
        SharingAlbumMemberUpdatesNotification = new PreferenceFeatures("SharingAlbumMemberUpdatesNotification", 10, PreferenceName.KEY_MEMBER_UPDATES);
        HeifAutoConvert = new PreferenceFeatures("HeifAutoConvert", 11, PreferenceName.HEIF_AUTO_CONVERT, (BooleanSupplier) new M(0));
        HDR10PlusAutoConvert = new PreferenceFeatures("HDR10PlusAutoConvert", 12, PreferenceName.HDR10PLUS_AUTO_CONVERT);
        UseTrash = new PreferenceFeatures("UseTrash", 13, SdkConfig.atLeast(SdkConfig.SEM.U) ? PreferenceName.USE_TRASH2 : PreferenceName.USE_TRASH, (BooleanSupplier) new C0671i(13));
        NewTrash = new PreferenceFeatures("NewTrash", 14, true);
        RetailMode = new PreferenceFeatures("RetailMode", 15, PreferenceName.RETAIL_MODE);
        TimelineSimilarPhotoMode = new PreferenceFeatures("TimelineSimilarPhotoMode", 16, PreferenceName.TIMELINE_SIMILAR_PHOTO_MODE);
        DayMerge = new PreferenceFeatures("DayMerge", 17, true);
        ShareAlbums = new PreferenceFeatures("ShareAlbums", 18, (BooleanSupplier) new C0671i(24));
        VisualSearch61 = new PreferenceFeatures("VisualSearch61", 19, (BooleanSupplier) new M(6));
        VisualSearch71 = new PreferenceFeatures("VisualSearch71", 20, (BooleanSupplier) new M(18));
        VideoCapture = new PreferenceFeatures("VideoCapture", 21, (BooleanSupplier) new N(0));
        MotionPhotoZoom = new PreferenceFeatures("MotionPhotoZoom", 22, "motioh_photo_zoom", false);
        SearchPicker = new PreferenceFeatures("SearchPicker", 23, "search_picker", (BooleanSupplier) new N(10));
        PermanentAlbumCover = new PreferenceFeatures("PermanentAlbumCover", 24, "permanent_album_cover", (BooleanSupplier) new N(11));
        AlbumCoverHistory = new PreferenceFeatures("AlbumCoverHistory", 25, false);
        AlbumTimeline = new PreferenceFeatures("AlbumTimeline", 26, "album_timeline", false);
        ThumbnailPreview = new PreferenceFeatures("ThumbnailPreview", 27, "thumbnail_preview", true);
        PhotoStrip41 = new PreferenceFeatures("PhotoStrip41", 28, (BooleanSupplier) new N(12));
        MotionPhotoPlayer = new PreferenceFeatures("MotionPhotoPlayer", 29, 1, (BooleanSupplier) new N(13));
        TextExtractionCapsule = new PreferenceFeatures("TextExtractionCapsule", 30, (BooleanSupplier) new M(11));
        TextExtractionLangManage = new PreferenceFeatures("TextExtractionLangManage", 31, (BooleanSupplier) new M(22));
        StoryOneUi21 = new PreferenceFeatures("StoryOneUi21", 32, (BooleanSupplier) new N(3));
        StoryOneUi31 = new PreferenceFeatures("StoryOneUi31", 33, (BooleanSupplier) new N(14));
        StoryOneUi50 = new PreferenceFeatures("StoryOneUi50", 34, (BooleanSupplier) new N(15));
        StoryOneUi70 = new PreferenceFeatures("StoryOneUi70", 35, (BooleanSupplier) new N(16));
        GalleryMotionPhotoPlayer = new PreferenceFeatures("GalleryMotionPhotoPlayer", 36, false);
        ExposeNonDestructiveRecordingInSearch = new PreferenceFeatures("ExposeNonDestructiveRecordingInSearch", 37, false);
        MxAlbums = new PreferenceFeatures("MxAlbums", 38, (BooleanSupplier) new N(17));
        EssentialAlbums = new PreferenceFeatures("EssentialAlbums", 39, PreferenceName.ESSENTIAL_ALBUMS);
        MxAlbumsMergeNames = new PreferenceFeatures("MxAlbumsMergeNames", 40, PreferenceName.ALBUM_NAME_MERGE);
        SuggestedEffectOnStory = new PreferenceFeatures("SuggestedEffectOnStory", 41, (BooleanSupplier) new C0671i(10));
        PreferenceFeatures preferenceFeatures = new PreferenceFeatures("VideoThumbnailWithFirstFrame", 42, true);
        VideoThumbnailWithFirstFrame = preferenceFeatures;
        StoryHighlightSave = new PreferenceFeatures("StoryHighlightSave", 43, (BooleanSupplier) new C0671i(11));
        StoryHighlightSmartCrop = new PreferenceFeatures("StoryHighlightSmartCrop", 44, true);
        StoriesFilter = new PreferenceFeatures("StoriesFilter", 45, (BooleanSupplier) new C0671i(12));
        StoryDefaultTheme = new PreferenceFeatures("StoryDefaultTheme", 46, (BooleanSupplier) new C0671i(14));
        StoryBgmWithSEAContentProvider = new PreferenceFeatures("StoryBgmWithSEAContentProvider", 47, (BooleanSupplier) new C0671i(15));
        FaceCluster = new PreferenceFeatures("FaceCluster", 48, (BooleanSupplier) new C0671i(16));
        SearchMultipleKeyword = new PreferenceFeatures("SearchMultipleKeyword", 49, (BooleanSupplier) new C0671i(17));
        SearchResultScreenV2 = new PreferenceFeatures("SearchResultScreenV2", 50, (BooleanSupplier) new C0671i(18));
        SearchHidePeople = new PreferenceFeatures("SearchHidePeople", 51, (BooleanSupplier) new C0671i(19));
        SharedAlbumPinch = new PreferenceFeatures("SharedAlbumPinch", 52, (BooleanSupplier) new C0671i(20));
        PlayVideoInPresentation = new PreferenceFeatures("PlayVideoInPresentation", 53, false);
        PlayVideoInSlideshowPresentation = new PreferenceFeatures("PlayVideoInSlideshowPresentation", 54, (BooleanSupplier) new C0671i(21));
        ShowInfoInPresentation = new PreferenceFeatures("ShowInfoInPresentation", 55, false);
        RemasterPicturesV2 = new PreferenceFeatures("RemasterPicturesV2", 56, (BooleanSupplier) new C0671i(22));
        CustomPeopleRelationshipEditAndRemove = new PreferenceFeatures("CustomPeopleRelationshipEditAndRemove", 57, false);
        SearchPeopleFaceScore = new PreferenceFeatures("SearchPeopleFaceScore", 58, (BooleanSupplier) new C0671i(23));
        BurstShotZoom = new PreferenceFeatures("BurstShotZoom", 59, (BooleanSupplier) new C0671i(25));
        TimelineGridExtension = new PreferenceFeatures("TimelineGridExtension", 60, (BooleanSupplier) new C0671i(26));
        AddressFromDatabase = new PreferenceFeatures("AddressFromDatabase", 61, (BooleanSupplier) new C0671i(27));
        ShowHdrImages = new PreferenceFeatures("ShowHdrImages", 62, PreferenceName.PHOTO_HDR, (BooleanSupplier) new C0671i(28));
        MoreInfoShowCameraMakerName = new PreferenceFeatures("MoreInfoShowCameraMakerName", 63, (BooleanSupplier) new C0671i(29));
        MoreOptionsInViewerBottom = new PreferenceFeatures("MoreOptionsInViewerBottom", 64, (BooleanSupplier) new M(1));
        DebugStories = new PreferenceFeatures("DebugStories", 65, false);
        StoriesCursorCache = new PreferenceFeatures("StoriesCursorCache", 66, false);
        PasteClipboardViewer = new PreferenceFeatures("PasteClipboardViewer", 67, (BooleanSupplier) new M(2));
        ClippedImageEdit = new PreferenceFeatures("ClippedImageEdit", 68, (BooleanSupplier) new M(3));
        SearchCluster = new PreferenceFeatures("SearchCluster", 69, (BooleanSupplier) new M(4));
        AlwaysGetNewSuggestions = new PreferenceFeatures("AlwaysGetNewSuggestions", 70, (BooleanSupplier) new M(5));
        SearchTagInRecommendationView = new PreferenceFeatures("SearchTagInRecommendationView", 71, (BooleanSupplier) new M(7));
        SlideshowV2 = new PreferenceFeatures("SlideshowV2", 72, (BooleanSupplier) new M(8));
        MoreInfoGeneratedImage = new PreferenceFeatures("MoreInfoGeneratedImage", 73, (BooleanSupplier) new M(9));
        StoryNotification = new PreferenceFeatures("StoryNotification", 74, PreferenceName.STORY_NOTIFICATION_STATE, (BooleanSupplier) new M(10));
        RelatedPeopleAndPet = new PreferenceFeatures("RelatedPeopleAndPet", 75, (BooleanSupplier) new M(12));
        CreateVideoGifMenu = new PreferenceFeatures("CreateVideoGifMenu", 76, (BooleanSupplier) new M(13));
        CropVideoCapture = new PreferenceFeatures("CropVideoCapture", 77, (BooleanSupplier) new M(14));
        HideSceneSelection2 = new PreferenceFeatures("HideSceneSelection2", 78, (BooleanSupplier) new M(15));
        StorySummaryCollage = new PreferenceFeatures("StorySummaryCollage", 79, (BooleanSupplier) new M(16));
        VideoSpeedController = new PreferenceFeatures("VideoSpeedController", 80, (BooleanSupplier) new M(17));
        SuggestIntelligent = new PreferenceFeatures("SuggestIntelligent", 81, false);
        StoryLastPage = new PreferenceFeatures("StoryLastPage", 82, (BooleanSupplier) new M(19));
        StoryIrregularCollage = new PreferenceFeatures("StoryIrregularCollage", 83, false);
        StoryCollageForceCreation = new PreferenceFeatures("StoryCollageForceCreation", 84, false);
        RecentAlbumTimeline = new PreferenceFeatures("RecentAlbumTimeline", 85, false);
        UseConcatThumbnail = new PreferenceFeatures("UseConcatThumbnail", 86, false);
        MotionPhotoViewMode = new PreferenceFeatures("MotionPhotoViewMode", 87, (BooleanSupplier) new M(20));
        NewSearchBar = new PreferenceFeatures("NewSearchBar", 88, (BooleanSupplier) new M(21));
        InAppAssistLook = new PreferenceFeatures("InAppAssistLook", 89, (BooleanSupplier) new M(23));
        SearchAllFilters = new PreferenceFeatures("SearchAllFilters", 90, (BooleanSupplier) new M(24));
        BottomTabPickerSearch = new PreferenceFeatures("BottomTabPickerSearch", 91, (BooleanSupplier) new M(25));
        SearchClusterResultOnHeader = new PreferenceFeatures("SearchClusterResultOnHeader", 92, (BooleanSupplier) new M(26));
        UnmergeCreature = new PreferenceFeatures("UnmergeCreature", 93, (BooleanSupplier) new M(27));
        TripStoryMap = new PreferenceFeatures("TripStoryMap", 94, (BooleanSupplier) new M(28));
        BottomSheetAllMapView = new PreferenceFeatures("BottomSheetAllMapView", 95, (BooleanSupplier) new M(29));
        ViewClusterResultMenuOption = new PreferenceFeatures("ViewClusterResultMenuOption", 96, (BooleanSupplier) new N(1));
        StoriesTopColorEffect = new PreferenceFeatures("StoriesTopColorEffect", 97, (BooleanSupplier) new N(2));
        ScreenshotFilterCustom = new PreferenceFeatures("ScreenshotFilterCustom", 98, false);
        SearchResultOnMapView = new PreferenceFeatures("SearchResultOnMapView", 99, (BooleanSupplier) new N(4));
        PopupMenuInDragAndDrop = new PreferenceFeatures("PopupMenuInDragAndDrop", 100, true);
        SearchCategoryScreenShot = new PreferenceFeatures("SearchCategoryScreenShot", 101, (BooleanSupplier) new N(5));
        MxBlurAlbums = new PreferenceFeatures("MxBlurAlbums", 102, (BooleanSupplier) new N(6));
        Collection = new PreferenceFeatures("Collection", 103, (BooleanSupplier) new N(7));
        IgnoreGoToSourceAllowList = new PreferenceFeatures("IgnoreGoToSourceAllowList", 104, false);
        StoryCoverBlur = new PreferenceFeatures("StoryCoverBlur", 105, (BooleanSupplier) new N(8));
        FloatingUI = new PreferenceFeatures("FloatingUI", 106, false);
        BottomTabMenu = new PreferenceFeatures("BottomTabMenu", 107, 1, true);
        SseDebug = new PreferenceFeatures("SseDebug", 108, false);
        SupportRecap = new PreferenceFeatures("SupportRecap", 109, (BooleanSupplier) new N(9));
        SupportCreatureThumbFromVideoFrame = new PreferenceFeatures("SupportCreatureThumbFromVideoFrame", 110, false);
        EOF = new PreferenceFeatures("EOF", 111, "EOF", false);
        $VALUES = $values();
        SEP_GENERIC_DEVICE = !Features.isEnabled(Features.IS_SEP_LITE);
        SUPPORT_RENAME_BY_MP = SdkConfig.atLeast(SdkConfig.GED.S);
        VIDEO_THUMBNAIL_WITH_FIRST_FRAME = isEnabled(preferenceFeatures);
    }

    private PreferenceFeatures(boolean z) {
        this.mPreferenceKey = name();
        this.mDefaultValue = z;
        this.mDefaultSupplier = null;
    }

    public static void printDebug() {
        Log.d(TAG, toDebugString());
        Log.d(TAG, PocFeatures.toDebugString());
    }

    public static String toDebugString() {
        GalleryPreference instance = GalleryPreference.getInstance();
        Stream map = Stream.of(values()).map(new C0670h(24));
        Objects.requireNonNull(instance);
        return C0212a.m("PreferenceFeatures=[", (String) map.filter(new C0678p(1, instance)).map(new r(4, instance)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)), "]");
    }

    public static boolean toggleEnabled(PreferenceFeatures preferenceFeatures) {
        boolean z = !preferenceFeatures.isEnabled();
        preferenceFeatures.setEnabled(z);
        return z;
    }

    public boolean getDefaultValue() {
        BooleanSupplier booleanSupplier = this.mDefaultSupplier;
        if (booleanSupplier == null) {
            return this.mDefaultValue;
        }
        return booleanSupplier.getAsBoolean();
    }

    public String getKey() {
        return this.mPreferenceKey;
    }

    public boolean isEnabled() {
        if (this.mIsEnabled == null) {
            BooleanSupplier booleanSupplier = this.mDefaultSupplier;
            boolean asBoolean = booleanSupplier != null ? booleanSupplier.getAsBoolean() : this.mDefaultValue;
            Boolean valueOf = Boolean.valueOf(GalleryPreference.getInstance().loadBoolean(this.mPreferenceKey, asBoolean));
            this.mIsEnabled = valueOf;
            if (asBoolean != valueOf.booleanValue()) {
                Log.v(TAG, name() + "#isEnabled [" + this.mIsEnabled + "]");
            }
        }
        return this.mIsEnabled.booleanValue();
    }

    public void recycle() {
        this.mIsEnabled = null;
    }

    public boolean setEnabled(boolean z) {
        Boolean bool = this.mIsEnabled;
        if (bool == null || bool.booleanValue() != z) {
            GalleryPreference.getInstance().saveState(this.mPreferenceKey, z);
            if (this.mAnalyticsLoggingLevel == 1) {
                GalleryPreference.getInstanceAnalytics().saveState(this.mPreferenceKey, z);
            }
        }
        this.mIsEnabled = Boolean.valueOf(z);
        return true;
    }

    public String toString() {
        return name() + "=" + this.mIsEnabled;
    }

    public static void recycle(PreferenceFeatures preferenceFeatures) {
        preferenceFeatures.recycle();
    }

    private PreferenceFeatures(BooleanSupplier booleanSupplier) {
        this.mPreferenceKey = name();
        this.mDefaultValue = false;
        this.mDefaultSupplier = booleanSupplier;
    }

    public static boolean setEnabled(PreferenceFeatures preferenceFeatures, boolean z) {
        preferenceFeatures.setEnabled(z);
        return true;
    }

    public static boolean isEnabled(PreferenceFeatures preferenceFeatures) {
        return preferenceFeatures.isEnabled();
    }

    private PreferenceFeatures(String str, boolean z) {
        this.mPreferenceKey = str;
        this.mDefaultValue = z;
        this.mDefaultSupplier = null;
    }

    private PreferenceFeatures(String str, BooleanSupplier booleanSupplier) {
        this.mPreferenceKey = str;
        this.mDefaultValue = false;
        this.mDefaultSupplier = booleanSupplier;
    }

    private PreferenceFeatures(PreferenceName preferenceName) {
        this.mPreferenceKey = preferenceName.key();
        this.mDefaultValue = preferenceName.getBooleanDefault();
        this.mDefaultSupplier = null;
        this.mAnalyticsLoggingLevel = preferenceName.level();
    }

    private PreferenceFeatures(PreferenceName preferenceName, BooleanSupplier booleanSupplier) {
        this.mPreferenceKey = preferenceName.key();
        this.mDefaultValue = false;
        this.mDefaultSupplier = booleanSupplier;
        this.mAnalyticsLoggingLevel = preferenceName.level();
    }

    private PreferenceFeatures(int i2, boolean z) {
        this.mPreferenceKey = name() + "_" + i2;
        this.mDefaultValue = z;
        this.mDefaultSupplier = null;
    }

    private PreferenceFeatures(int i2, BooleanSupplier booleanSupplier) {
        this.mPreferenceKey = name() + "_" + i2;
        this.mDefaultValue = false;
        this.mDefaultSupplier = booleanSupplier;
    }
}
