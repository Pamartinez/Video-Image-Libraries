package com.samsung.android.gallery.module.settings;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.SuperHdrConfig;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum SettingPreference implements SettingPrefApi {
    Settings("top_level_gallery_settings_main_screen"),
    CloudCategory("cloud_category"),
    SamsungCloudCategory("samsung_cloud_category"),
    IntelligentFeaturesCategory("intelligent_features_category"),
    EditSuggestionsHelp("edit_suggestions_help"),
    AIEditSuggestionsHelp("ai_edit_suggestions_help"),
    MoreIntelligentFeatures("more_intelligent_features"),
    ViewingCategory("viewing_category"),
    EditingCategory("editing_category"),
    CategoryStory("event_settings"),
    CategoryAlbum("albums_setting"),
    CategoryAdvanced("advanced_setting"),
    ScreenshotCategoryReorder1("screenshot_categories"),
    CategoryPrivacy("privacy_category"),
    PrivacyPolicy("privacy_policy"),
    AppPermission("permission"),
    CategoryAboutPage("about_gallery_page"),
    About("about_page"),
    Eof("");
    
    private static final String TAG = "SettingPreference";
    public final String key;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends SettingPreference {
        public /* synthetic */ AnonymousClass1() {
            this("CloudSync", 2, "cloud_sync");
        }

        public void setEnabled(boolean z) {
            PreferenceName.CLOUD_SWITCH_STATUS.saveBoolean(z);
        }

        public boolean support(Context context) {
            if (!Features.isEnabled(Features.SUPPORT_CLOUD) || Features.isEnabled(Features.IS_MUM_MODE) || SettingPreference.isUpsmMode() || SettingPreference.isSecureMode()) {
                return false;
            }
            return true;
        }

        private AnonymousClass1(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$10  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass10 extends SettingPreference {
        public /* synthetic */ AnonymousClass10() {
            this("BestFace", 13, "best_face");
        }

        public boolean support(Context context) {
            if (!SettingPreference.IntelligentFeatures.support(context) || !Features.isEnabled(Features.SUPPORT_BEST_FACE)) {
                return false;
            }
            return true;
        }

        private AnonymousClass10(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$11  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass11 extends SettingPreference {
        public /* synthetic */ AnonymousClass11() {
            this("PhotoHdr", 18, "photo_hdr");
        }

        public boolean isEnabled() {
            return PreferenceFeatures.isEnabled(PreferenceFeatures.ShowHdrImages);
        }

        public void notifyChanged(Context context, boolean z) {
            Blackboard.getApplicationInstance().post("global://setting/showHdrImages", (Object) null);
        }

        public void setEnabled(boolean z) {
            PreferenceFeatures.setEnabled(PreferenceFeatures.ShowHdrImages, z);
        }

        public boolean support(Context context) {
            if (!SuperHdrConfig.SUPPORT || !SdkConfig.lessThan(SdkConfig.SEM.V)) {
                return false;
            }
            return true;
        }

        private AnonymousClass11(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$12  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass12 extends SettingPreference {
        public /* synthetic */ AnonymousClass12() {
            this("MotionPhotoAutoPlay", 19, "auto_play_motion_photo");
        }

        public boolean isEnabled() {
            return PreferenceFeatures.isEnabled(PreferenceFeatures.AutoPlayMotionPhoto);
        }

        public void notifyChanged(Context context, boolean z) {
            Blackboard.getApplicationInstance().post("global://setting/motion_photo_auto_play", Boolean.valueOf(z));
        }

        public void setEnabled(boolean z) {
            PreferenceFeatures.setEnabled(PreferenceFeatures.AutoPlayMotionPhoto, z);
        }

        public boolean support(Context context) {
            return !Features.isEnabled(Features.IS_SEP_LITE);
        }

        private AnonymousClass12(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$13  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass13 extends SettingPreference {
        public /* synthetic */ AnonymousClass13() {
            this("OpenInVideoPlayer", 20, "open_in_video_player");
        }

        public boolean isEnabled() {
            return PreferenceFeatures.VideoPlayerFeature.isOpenInVideoPlayer();
        }

        public void notifyChanged(Context context, boolean z) {
            PreferenceFeatures preferenceFeatures = PreferenceFeatures.PhotoStrip41;
            PreferenceFeatures.setEnabled(preferenceFeatures, preferenceFeatures.getDefaultValue());
            Blackboard.getApplicationInstance().post("global://setting/open_in_video_player", Boolean.valueOf(z));
        }

        public void setEnabled(boolean z) {
            PreferenceFeatures.VideoPlayerFeature.setOpenInVideoPlayer(z);
        }

        public boolean support(Context context) {
            return PreferenceFeatures.VideoPlayerFeature.SUPPORT_SETTING;
        }

        private AnonymousClass13(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$14  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass14 extends SettingPreference {
        public /* synthetic */ AnonymousClass14() {
            this("Meitu", 21, "meitu");
        }

        public boolean isEnabled() {
            return PreferenceFeatures.isEnabled(PreferenceFeatures.Meitu);
        }

        public void notifyChanged(Context context, boolean z) {
            Blackboard.getApplicationInstance().post("global://setting/meitu", Boolean.valueOf(z));
        }

        public void setEnabled(boolean z) {
            PreferenceFeatures.setEnabled(PreferenceFeatures.Meitu, z);
        }

        public boolean support(Context context) {
            return Features.isEnabled(Features.SUPPORT_MEITU);
        }

        private AnonymousClass14(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$15  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass15 extends SettingPreference {
        public /* synthetic */ AnonymousClass15() {
            this("PhotoAssist", 23, "photo_assist");
        }

        public boolean support(Context context) {
            if (!PreferenceFeatures.OneUi7x.SUPPORT_PHOTO_ASSIST_SETTING || !SamsungAccountManager.isSamsungAiSupportAccount(context)) {
                return false;
            }
            return true;
        }

        private AnonymousClass15(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$16  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass16 extends SettingPreference {
        public /* synthetic */ AnonymousClass16() {
            this("AudioEraser", 24, "audio_eraser");
        }

        public boolean support(Context context) {
            if (!Features.isEnabled(Features.SUPPORT_AUDIO_ERASER) || !SamsungAccountManager.isSamsungAiSupportAccount(context)) {
                return false;
            }
            return true;
        }

        private AnonymousClass16(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$17  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass17 extends SettingPreference {
        public /* synthetic */ AnonymousClass17() {
            this("AIEditSuggestions", 25, "ai_edit_suggestions");
        }

        public boolean support(Context context) {
            return Features.isEnabled(Features.SUPPORT_AI_EDIT_SUGGESTIONS);
        }

        private AnonymousClass17(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$18  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass18 extends SettingPreference {
        public /* synthetic */ AnonymousClass18() {
            this("DetailEnhancer", 26, "detail_enhancer");
        }

        public boolean support(Context context) {
            return Features.isEnabled(Features.SUPPORT_PHOTO_REMASTER_UPSCALE_ULTRA);
        }

        private AnonymousClass18(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$19  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass19 extends SettingPreference {
        public /* synthetic */ AnonymousClass19() {
            this("DetailEnhancerMaximum", 27, "detail_enhancer_maximum");
        }

        public boolean isEnabled() {
            return DetailEnhancerOption.MAXIMUM.getValue().equals(GalleryPreference.getInstance().loadString(PreferenceName.AUTO_UPSCALE_IMAGE));
        }

        public void notifyChanged(Context context, boolean z) {
            if (z) {
                Blackboard.getApplicationInstance().post("global://setting/detailEnhancer", DetailEnhancerOption.MAXIMUM);
            }
        }

        public void setEnabled(boolean z) {
            if (z) {
                GalleryPreference.getInstance().saveState(PreferenceName.AUTO_UPSCALE_IMAGE, DetailEnhancerOption.MAXIMUM.getValue());
            }
        }

        private AnonymousClass19(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends SettingPreference {
        public /* synthetic */ AnonymousClass2() {
            this("AttCloud", 3, "att_cloud");
        }

        public boolean support(Context context) {
            if (!Features.isEnabled(Features.SUPPORT_ATT_CLOUD) || SettingPreference.isSecureMode()) {
                return false;
            }
            return true;
        }

        private AnonymousClass2(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$20  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass20 extends SettingPreference {
        public /* synthetic */ AnonymousClass20() {
            this("DetailEnhancerMinimum", 28, "detail_enhancer_minimum");
        }

        public boolean isEnabled() {
            return DetailEnhancerOption.MINIMUM.getValue().equals(GalleryPreference.getInstance().loadString(PreferenceName.AUTO_UPSCALE_IMAGE));
        }

        public void notifyChanged(Context context, boolean z) {
            if (z) {
                Blackboard.getApplicationInstance().post("global://setting/detailEnhancer", DetailEnhancerOption.MINIMUM);
            }
        }

        public void setEnabled(boolean z) {
            if (z) {
                GalleryPreference.getInstance().saveState(PreferenceName.AUTO_UPSCALE_IMAGE, DetailEnhancerOption.MINIMUM.getValue());
            }
        }

        private AnonymousClass20(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$21  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass21 extends SettingPreference {
        public /* synthetic */ AnonymousClass21() {
            this("DetailEnhancerOff", 29, "detail_enhancer_off");
        }

        public boolean isEnabled() {
            return DetailEnhancerOption.OFF.getValue().equals(GalleryPreference.getInstance().loadString(PreferenceName.AUTO_UPSCALE_IMAGE));
        }

        public void notifyChanged(Context context, boolean z) {
            if (z) {
                Blackboard.getApplicationInstance().post("global://setting/detailEnhancer", DetailEnhancerOption.OFF);
            }
        }

        public void setEnabled(boolean z) {
            if (z) {
                GalleryPreference.getInstance().saveState(PreferenceName.AUTO_UPSCALE_IMAGE, DetailEnhancerOption.OFF.getValue());
            }
        }

        private AnonymousClass21(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$22  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass22 extends SettingPreference {
        public /* synthetic */ AnonymousClass22() {
            this("PhotoEditorSettings", 30, "photo_editor_settings");
        }

        public boolean support(Context context) {
            if (!SdkConfig.atLeast(SdkConfig.SEM.V) || !Features.isEnabled(Features.SUPPORT_PHOTO_EDIT)) {
                return false;
            }
            return true;
        }

        private AnonymousClass22(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$23  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass23 extends SettingPreference {
        public /* synthetic */ AnonymousClass23() {
            this("StoryAutoCreation", 32, "auto_create_event");
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$notifyChanged$0(boolean z) {
            boolean updateFeatureState = DbCompat.storyFeatureApi().updateFeatureState("activate_story_recommendation", z ? 1 : 0);
            if (!updateFeatureState) {
                z = DbCompat.storyFeatureApi().isEnableFeatureState("activate_story_recommendation");
            }
            setEnabled(z);
            Log.i(SettingPreference.TAG, "story auto creation update result", Boolean.valueOf(updateFeatureState), Boolean.valueOf(z));
            Blackboard.getApplicationInstance().post("global://setting/stories/AutoStory", Boolean.valueOf(z));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$restoreChanged$1() {
            boolean isEnableFeatureState = DbCompat.storyFeatureApi().isEnableFeatureState("activate_story_recommendation");
            setEnabled(isEnableFeatureState);
            Log.i(SettingPreference.TAG, "restoreChanged: story auto creation update result", Boolean.valueOf(isEnableFeatureState));
            Blackboard.getApplicationInstance().post("global://setting/stories/AutoStory", Boolean.valueOf(isEnableFeatureState));
        }

        public boolean isEnabled() {
            if (GalleryPreference.getInstance().loadInt(PreferenceName.AUTO_CREATE_EVENT_FROM_CMH, 1) > 0) {
                return true;
            }
            return false;
        }

        public void notifyChanged(Context context, boolean z) {
            if (SdkConfig.atLeast(SdkConfig.GED.T)) {
                SimpleThreadPool.getInstance().execute(new a(this, z, 0));
                return;
            }
            context.sendBroadcast(new Intent("com.samsung.cmh.autoevent").putExtra("auto_create_event", z ? 1 : 0));
            Blackboard.getApplicationInstance().post("global://setting/stories/AutoStory", Boolean.valueOf(z));
        }

        public void restoreChanged() {
            SimpleThreadPool.getInstance().execute(new b(this, 0));
        }

        public void setEnabled(boolean z) {
            GalleryPreference.getInstance().saveState(PreferenceName.AUTO_CREATE_EVENT_FROM_CMH, z ? 1 : 0);
        }

        public boolean support(Context context) {
            if (!Features.isEnabled(Features.SUPPORT_AUTO_CREATE_STORY) || !Features.isEnabled(Features.SUPPORT_STORY) || SettingPreference.isUpsmMode()) {
                return false;
            }
            return true;
        }

        private AnonymousClass23(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$24  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass24 extends SettingPreference {
        public /* synthetic */ AnonymousClass24() {
            this("StoryNotification", 33, "story_notification");
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$notifyChanged$0(boolean z) {
            boolean updateFeatureState = DbCompat.storyFeatureApi().updateFeatureState("story_notification", z ? 1 : 0);
            if (!updateFeatureState) {
                z = DbCompat.storyFeatureApi().isEnableFeatureState("story_notification");
            }
            setEnabled(z);
            Log.i(SettingPreference.TAG, "story notification update result", Boolean.valueOf(updateFeatureState), Boolean.valueOf(z));
            Blackboard.getApplicationInstance().post("global://setting/stories/StoryNotificationState", Boolean.valueOf(z));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$restoreChanged$1() {
            boolean isEnableFeatureState = DbCompat.storyFeatureApi().isEnableFeatureState("story_notification");
            setEnabled(isEnableFeatureState);
            Log.i(SettingPreference.TAG, "restoreChanged: story notification update result", Boolean.valueOf(isEnableFeatureState));
            Blackboard.getApplicationInstance().post("global://setting/stories/StoryNotificationState", Boolean.valueOf(isEnableFeatureState));
        }

        public boolean isEnabled() {
            return PreferenceFeatures.isEnabled(PreferenceFeatures.StoryNotification);
        }

        public void notifyChanged(Context context, boolean z) {
            SimpleThreadPool.getInstance().execute(new a(this, z, 1));
        }

        public void restoreChanged() {
            SimpleThreadPool.getInstance().execute(new b(this, 1));
        }

        public void setEnabled(boolean z) {
            PreferenceFeatures.setEnabled(PreferenceFeatures.StoryNotification, z);
        }

        public boolean support(Context context) {
            if (!Features.isEnabled(Features.SUPPORT_STORY_NOTIFICATION) || Features.isEnabled(Features.IS_REPAIR_MODE) || Features.isEnabled(Features.IS_ON_SECURE_FOLDER) || Features.isEnabled(Features.IS_KNOX_MODE) || Features.isEnabled(Features.IS_AFW_MODE)) {
                return false;
            }
            return true;
        }

        private AnonymousClass24(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$25  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass25 extends SettingPreference {
        public /* synthetic */ AnonymousClass25() {
            this("Rubin", 34, "go_to_rubin");
        }

        public boolean support(Context context) {
            if (!SdkConfig.lessThan(SdkConfig.SEM.V) || !Features.isEnabled(Features.IS_RUBIN_ENABLED)) {
                return false;
            }
            if ((!Features.isEnabled(Features.SUPPORT_AUTO_CREATE_STORY) || SettingPreference.StoryAutoCreation.support(context)) && !SettingPreference.isSecureMode()) {
                return true;
            }
            return false;
        }

        private AnonymousClass25(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$26  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass26 extends SettingPreference {
        public /* synthetic */ AnonymousClass26() {
            this("EssentialAlbum", 36, "select_essential_albums");
        }

        public boolean isEnabled() {
            return PreferenceFeatures.isEnabled(PreferenceFeatures.EssentialAlbums);
        }

        public void notifyChanged(Context context, boolean z) {
            Blackboard.getApplicationInstance().post("global://setting/albums/selectEssentialAlbums", Boolean.valueOf(z));
        }

        public void setEnabled(boolean z) {
            PreferenceFeatures.setEnabled(PreferenceFeatures.EssentialAlbums, z);
        }

        public boolean support(Context context) {
            if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !DrawerUtil.supportEssentialAlbumsLayout(context)) {
                return false;
            }
            return true;
        }

        private AnonymousClass26(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$27  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass27 extends SettingPreference {
        public /* synthetic */ AnonymousClass27() {
            this("MergedAlbums", 37, "merge_albums");
        }

        public boolean isEnabled() {
            return PreferenceFeatures.isEnabled(PreferenceFeatures.MxAlbumsMergeNames);
        }

        public void notifyChanged(Context context, boolean z) {
            Blackboard.getApplicationInstance().post("global://setting/albums/mergeAlbums", Boolean.valueOf(z));
        }

        public void setEnabled(boolean z) {
            PreferenceFeatures.setEnabled(PreferenceFeatures.MxAlbumsMergeNames, z);
        }

        public boolean support(Context context) {
            return PreferenceFeatures.OneUi5x.MX_ALBUMS;
        }

        private AnonymousClass27(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$28  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass28 extends SettingPreference {
        public /* synthetic */ AnonymousClass28() {
            this("SharingServiceEnabler", 38, "sharing_service_enabler");
        }

        public boolean isEnabled() {
            return PreferenceFeatures.isEnabled(PreferenceFeatures.SharingServiceEnabled);
        }

        public void notifyChanged(Context context, boolean z) {
            Blackboard.getApplicationInstance().post("global://setting/event/shared_album_enabler_changed", Boolean.valueOf(z));
        }

        public void setEnabled(boolean z) {
            PreferenceFeatures.setEnabled(PreferenceFeatures.SharingServiceEnabled, z);
        }

        public boolean support(Context context) {
            if (!PreferenceFeatures.CHINA.SHARING_SERVICE_ENABLER || !MdeSharingService.getInstance().isServiceSupported(false) || !MdeSharingService.getInstance().computeShareServiceAvailable()) {
                return false;
            }
            return true;
        }

        private AnonymousClass28(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$29  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass29 extends SettingPreference {
        public /* synthetic */ AnonymousClass29() {
            this("SharedNotification", 39, "shared_album_notification");
        }

        public boolean isEnabled() {
            return PreferenceFeatures.isEnabled(PreferenceFeatures.SharingNotification);
        }

        public void notifyChanged(Context context, boolean z) {
            Blackboard.getApplicationInstance().post("global://setting/sharings/Notification", Boolean.valueOf(z));
        }

        public void setEnabled(boolean z) {
            PreferenceFeatures.setEnabled(PreferenceFeatures.SharingNotification, z);
        }

        public boolean support(Context context) {
            if (!MdeSharingService.getInstance().isServiceSupported(false) || !MdeSharingService.getInstance().computeShareServiceAvailable()) {
                return false;
            }
            return true;
        }

        private AnonymousClass29(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass3 extends SettingPreference {
        public /* synthetic */ AnonymousClass3() {
            this("BaiduCloud", 4, "baidu_cloud");
        }

        public boolean isEnabled() {
            return PreferenceFeatures.isEnabled(PreferenceFeatures.BaiduCloudEnabled);
        }

        public void notifyChanged(Context context, boolean z) {
            Blackboard.getApplicationInstance().post("global://setting/editMenuOptions/Baidu", Boolean.valueOf(z));
        }

        public void setEnabled(boolean z) {
            PreferenceFeatures.setEnabled(PreferenceFeatures.BaiduCloudEnabled, z);
        }

        public boolean support(Context context) {
            if (!Features.isEnabled(Features.SUPPORT_BAIDU_CLOUD) || SettingPreference.isUpsmMode() || SettingPreference.isSecureMode()) {
                return false;
            }
            return true;
        }

        private AnonymousClass3(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$30  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass30 extends SettingPreference {
        public /* synthetic */ AnonymousClass30() {
            this("SharedNotificationAlbum", 40, "new_albums");
        }

        public boolean isEnabled() {
            return PreferenceFeatures.isEnabled(PreferenceFeatures.SharingAlbumNewAlbumNotification);
        }

        public void notifyChanged(Context context, boolean z) {
            Blackboard.getApplicationInstance().post("global://setting/sharings/Notification/album", Boolean.valueOf(z));
        }

        public void setEnabled(boolean z) {
            PreferenceFeatures.setEnabled(PreferenceFeatures.SharingAlbumNewAlbumNotification, z);
        }

        private AnonymousClass30(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$31  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass31 extends SettingPreference {
        public /* synthetic */ AnonymousClass31() {
            this("SharedNotificationPosting", 41, "new_posts");
        }

        public boolean isEnabled() {
            return PreferenceFeatures.isEnabled(PreferenceFeatures.SharingAlbumNewPostNotification);
        }

        public void notifyChanged(Context context, boolean z) {
            Blackboard.getApplicationInstance().post("global://setting/sharings/Notification/post", Boolean.valueOf(z));
        }

        public void setEnabled(boolean z) {
            PreferenceFeatures.setEnabled(PreferenceFeatures.SharingAlbumNewPostNotification, z);
        }

        private AnonymousClass31(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$32  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass32 extends SettingPreference {
        public /* synthetic */ AnonymousClass32() {
            this("SharedNotificationMember", 42, "member_updates");
        }

        public boolean isEnabled() {
            return PreferenceFeatures.isEnabled(PreferenceFeatures.SharingAlbumMemberUpdatesNotification);
        }

        public void notifyChanged(Context context, boolean z) {
            Blackboard.getApplicationInstance().post("global://setting/sharings/Notification/member", Boolean.valueOf(z));
        }

        public void setEnabled(boolean z) {
            PreferenceFeatures.setEnabled(PreferenceFeatures.SharingAlbumMemberUpdatesNotification, z);
        }

        private AnonymousClass32(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$33  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass33 extends SettingPreference {
        public /* synthetic */ AnonymousClass33() {
            this("Trash", 44, "trash");
        }

        public boolean isEnabled() {
            return PreferenceFeatures.isEnabled(PreferenceFeatures.UseTrash);
        }

        public void notifyChanged(Context context, boolean z) {
            Blackboard.getApplicationInstance().post("global://setting/advanced/Trash", Boolean.valueOf(z));
        }

        public void setEnabled(boolean z) {
            PreferenceFeatures.setEnabled(PreferenceFeatures.UseTrash, z);
            if (z) {
                PreferenceCache.SdCardWarning.setBoolean(false);
            }
        }

        public boolean support(Context context) {
            boolean isEnabled = Features.isEnabled(Features.SUPPORT_TRASH);
            if (!isEnabled || !PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH) {
                return isEnabled;
            }
            return false;
        }

        private AnonymousClass33(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$34  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass34 extends SettingPreference {
        public /* synthetic */ AnonymousClass34() {
            this("CmhProvider", 45, "cmh_provider");
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$notifyChanged$0(boolean z) {
            CmhProviderPermission.set(z);
            Blackboard.getApplicationInstance().post("global://setting/advanced/cmhProvider", Boolean.valueOf(z));
        }

        public void notifyChanged(Context context, boolean z) {
            SimpleThreadPool.getInstance().execute(new c(z));
        }

        public boolean support(Context context) {
            return Features.isEnabled(Features.SUPPORT_CMH_PROVIDER_PERMISSION);
        }

        private AnonymousClass34(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$35  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass35 extends SettingPreference {
        public /* synthetic */ AnonymousClass35() {
            this("LocationAuth", 46, "location_auth");
        }

        private boolean supportPoi() {
            if (PreferenceFeatures.OneUi8x.IS_ONE_UI_80) {
                return Features.isEnabled(Features.SUPPORT_SAMSUNG_PLACE);
            }
            return CmhFeatures.supportPoi();
        }

        public boolean isEnabled() {
            return PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth);
        }

        public void notifyChanged(Context context, boolean z) {
            if (PreferenceFeatures.OneUi8x.IS_ONE_UI_80) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("show_place_name_value", z);
                context.getContentResolver().call(Uri.parse("content://secmedia/"), "show_place_name", (String) null, bundle);
            } else if (Features.isEnabled(Features.SUPPORT_POI)) {
                context.sendBroadcast(new Intent("com.samsung.cmh.autoevent").putExtra("location_on_off_state", z));
            }
            Blackboard.getApplicationInstance().post("global://setting/advanced/LocationAuth", Boolean.valueOf(z));
            if (!Features.isEnabled(Features.SUPPORT_PLACE_GDPR)) {
                Blackboard.postEventGlobal(EventMessage.obtain(1026));
            }
        }

        public void setEnabled(boolean z) {
            PreferenceFeatures.setEnabled(PreferenceFeatures.LocationAuth, z);
            if (z) {
                if (Features.isEnabled(Features.SHOW_BAIDU_LOCATION_AUTH_POPUP)) {
                    GalleryPreference instance = GalleryPreference.getInstance();
                    PreferenceName preferenceName = PreferenceName.IS_NEED_TO_SHOW_LOCATION_GDPR_POPUP_FOR_BAIDU;
                    if (instance.loadBoolean(preferenceName, true)) {
                        GalleryPreference.getInstance().saveState(preferenceName, false);
                    }
                }
                if (Features.isEnabled(Features.SUPPORT_SAMSUNG_PLACE)) {
                    GalleryPreference instance2 = GalleryPreference.getInstance();
                    PreferenceName preferenceName2 = PreferenceName.IS_NEED_TO_SHOW_LOCATION_GDPR_POPUP_V2;
                    if (instance2.loadBoolean(preferenceName2, true)) {
                        GalleryPreference.getInstance().saveState(preferenceName2, false);
                        return;
                    }
                    return;
                }
                GalleryPreference instance3 = GalleryPreference.getInstance();
                PreferenceName preferenceName3 = PreferenceName.IS_NEED_TO_SHOW_LOCATION_GDPR_POPUP;
                if (instance3.loadBoolean(preferenceName3, true)) {
                    GalleryPreference.getInstance().saveState(preferenceName3, false);
                }
            }
        }

        public boolean support(Context context) {
            if (!Features.isEnabled(Features.SUPPORT_LOCATION)) {
                return false;
            }
            if (Features.isEnabled(Features.IS_CHINESE_DEVICE) || supportPoi()) {
                return true;
            }
            return false;
        }

        private AnonymousClass35(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$36  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass36 extends SettingPreference {
        public /* synthetic */ AnonymousClass36() {
            this("HeifAutoConv", 47, "detail_view_heif_auto_conversion");
        }

        public boolean isEnabled() {
            if (!Features.isEnabled(Features.SUPPORT_HEIF_CONVERSION) || !PreferenceFeatures.isEnabled(PreferenceFeatures.HeifAutoConvert)) {
                return false;
            }
            return true;
        }

        public void notifyChanged(Context context, boolean z) {
            Blackboard.getApplicationInstance().post("global://setting/advanced/HeifTransCoding", Boolean.valueOf(z));
        }

        public void setEnabled(boolean z) {
            PreferenceFeatures.setEnabled(PreferenceFeatures.HeifAutoConvert, z);
        }

        public boolean support(Context context) {
            return Features.isEnabled(Features.SUPPORT_HEIF_CONVERSION);
        }

        private AnonymousClass36(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$37  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass37 extends SettingPreference {
        public /* synthetic */ AnonymousClass37() {
            this("Hdr10PlusAutoConv", 48, "detail_view_hdr10plus_auto_conversion");
        }

        public boolean isEnabled() {
            if (!Features.isEnabled(Features.SUPPORT_HDR10PLUS_CONVERSION) || !PreferenceFeatures.isEnabled(PreferenceFeatures.HDR10PlusAutoConvert)) {
                return false;
            }
            return true;
        }

        public void notifyChanged(Context context, boolean z) {
            Blackboard.getApplicationInstance().post("global://setting/advanced/HDR10TransCoding", Boolean.valueOf(z));
        }

        public void setEnabled(boolean z) {
            PreferenceFeatures.setEnabled(PreferenceFeatures.HDR10PlusAutoConvert, z);
        }

        public boolean support(Context context) {
            if (!Features.isEnabled(Features.SUPPORT_HDR10PLUS_CONVERSION) || SettingPreference.isSecureMode()) {
                return false;
            }
            return true;
        }

        private AnonymousClass37(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$38  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass38 extends SettingPreference {
        public /* synthetic */ AnonymousClass38() {
            this("ContactUs", 55, "contact_us");
        }

        public boolean support(Context context) {
            if (!Features.isEnabled(Features.SUPPORT_CONTACT_US) || SettingPreference.isUpsmMode()) {
                return false;
            }
            return true;
        }

        private AnonymousClass38(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$39  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass39 extends SettingPreference {
        public /* synthetic */ AnonymousClass39() {
            this("GalleryLabs", 56, "gallery_labs");
        }

        public boolean support(Context context) {
            return PocFeatures.isEnabled(PocFeatures.GalleryLabs);
        }

        private AnonymousClass39(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$4  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass4 extends SettingPreference {
        public /* synthetic */ AnonymousClass4() {
            this("TencentCloud", 5, "tencent_cloud");
        }

        public boolean isEnabled() {
            return PreferenceFeatures.isEnabled(PreferenceFeatures.TencentCloudEnabled);
        }

        public void notifyChanged(Context context, boolean z) {
            Blackboard.getApplicationInstance().post("global://setting/editMenuOptions/Tencent", Boolean.valueOf(z));
        }

        public void setEnabled(boolean z) {
            PreferenceFeatures.setEnabled(PreferenceFeatures.TencentCloudEnabled, z);
        }

        public boolean support(Context context) {
            if (!Features.isEnabled(Features.SUPPORT_TENCENT_CLOUD) || SettingPreference.isUpsmMode() || SettingPreference.isSecureMode()) {
                return false;
            }
            return true;
        }

        private AnonymousClass4(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$5  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass5 extends SettingPreference {
        public /* synthetic */ AnonymousClass5() {
            this("SdCardBackupCloud", 6, "back_up_sd_card_to_one_drive");
        }

        public boolean support(Context context) {
            if (context == null || !Features.isEnabled(Features.SUPPORT_BACK_UP_SD_CARD_TO_ONE_DRIVE) || !FileUtils.isSdcardMounted(context.getApplicationContext()) || SettingPreference.isSecureMode()) {
                return false;
            }
            return true;
        }

        private AnonymousClass5(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$6  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass6 extends SettingPreference {
        public /* synthetic */ AnonymousClass6() {
            this("SamsungCloudSync", 8, "samsung_cloud_sync");
        }

        public void setEnabled(boolean z) {
            PreferenceName.CLOUD_SWITCH_STATUS.saveBoolean(z);
        }

        public boolean support(Context context) {
            if (!Features.isEnabled(Features.SUPPORT_NEW_SAMSUNG_CLOUD) || Features.isEnabled(Features.IS_CHINESE_DEVICE) || !Features.isEnabled(Features.SUPPORT_CLOUD) || Features.isEnabled(Features.IS_MUM_MODE) || SettingPreference.isUpsmMode() || SettingPreference.isSecureMode()) {
                return false;
            }
            return true;
        }

        private AnonymousClass6(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$7  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass7 extends SettingPreference {
        public /* synthetic */ AnonymousClass7() {
            this("IntelligentFeatures", 10, "intelligent_features");
        }

        public boolean support(Context context) {
            if (!Features.isEnabled(Features.SUPPORT_AWESOME_INTELLIGENT_FEATURES) || !SamsungAccountManager.isSamsungAiSupportAccount(context)) {
                return false;
            }
            return true;
        }

        private AnonymousClass7(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$8  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass8 extends SettingPreference {
        public /* synthetic */ AnonymousClass8() {
            this("EditSuggestions", 11, "edit_suggestions");
        }

        public boolean support(Context context) {
            return SettingPreference.IntelligentFeatures.support(context);
        }

        private AnonymousClass8(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.SettingPreference$9  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass9 extends SettingPreference {
        public /* synthetic */ AnonymousClass9() {
            this("ObjectEraser", 12, "object_eraser");
        }

        public boolean support(Context context) {
            if (!SettingPreference.IntelligentFeatures.support(context) || !Features.isEnabled(Features.SUPPORT_OBJECT_ERASER)) {
                return false;
            }
            return true;
        }

        private AnonymousClass9(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    public static boolean isSecureMode() {
        if (Features.isEnabled(Features.IS_KNOX_MODE) || Features.isEnabled(Features.IS_AFW_MODE)) {
            return true;
        }
        return false;
    }

    public static boolean isUpsmMode() {
        return Features.isEnabled(Features.IS_UPSM);
    }

    public String getKey() {
        return this.key;
    }

    public boolean setAndNotifyIfChanged(Context context, boolean z) {
        if (isEnabled() == z) {
            return false;
        }
        Log.majorEvent(TAG, "setAndNotifyIfChanged" + Logger.v(name(), Boolean.valueOf(z)));
        setEnabled(z);
        notifyChanged(context, z);
        return true;
    }

    public String toString() {
        return name() + "{" + this.key + GlobalPostProcInternalPPInterface.SPLIT_REGEX + isEnabled() + "}";
    }

    private SettingPreference(String str) {
        this.key = str;
    }
}
