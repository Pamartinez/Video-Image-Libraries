package com.samsung.android.gallery.support.utils;

import D3.i;
import android.text.TextUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import f4.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum PreferenceCache {
    QuickDeleteService("QuickDeleteService", 0),
    AlbumDbMigration("album_db_migration", 0),
    LastUseSmallThumbKind("lastUseSmallKindThumb", (int) false),
    NoticeExpandGroupByDate("notice_group_by_date", (int) true),
    MenuBadgeOnTab("show_bottom_tab_menu_badge", (int) false),
    TextExtractionHint("TextExtractionHint", (int) true),
    OneDriveBadge("sync_with_one_drive_badge_pref", (int) true),
    OneDriveBadgeOnTab("sync_with_one_drive_badge_bottom_tab", (int) true),
    OneDriveTipCard("one_drive_turn_on_sync_tip_card_v2", (int) true),
    OneDriveStorageTipCard50("one_drive_sync_tip_card_storage_50", (int) true),
    OneDriveStorageTipCard70("one_drive_sync_tip_card_storage_70", (int) true),
    OneDriveStorageTipCard90("one_drive_sync_tip_card_storage_90", (int) true),
    OneDriveQuotaTipCard70("one_drive_quota_70p_tip_card", (int) false),
    OneDriveQuotaTipCard80("one_drive_quota_80p_tip_card", (int) false),
    OneDriveQuotaTipCard90("one_drive_quota_90p_tip_card", (int) false),
    OneDrivePromoTipCard("one_drive_promotion_tip_card", (int) true),
    OneDrivePromoTipCardCount("one_drive_promotion_tip_card_displayed_count", 0),
    OneDrivePromoTipCardTime("one_drive_promotion_tip_card_last_displayed_time", (int) -1),
    OneDriveQuotaFullTipCardCount("one_drive_quota_full_tip_card_display_count", 0),
    OneDriveQuotaFullTipCardTime("one_drive_quota_full_tip_card_last_display_time_ms", (int) -1),
    OneDriveQuotaFullTipCardTimeInSetting("one_drive_quota_full_tip_card_last_display_time_ms_for_setting", (int) -1),
    OneDriveUnlinkedPopupShown("one_drive_unlinked_popup_shown", (int) false),
    AboutPageBadgeOnTab("about_page_badge_bottom_tab", (int) true),
    SecMpMigrationCompleted("sec_mp_migration_tip_card", (int) false),
    VideoAlbumTipCard("virtual_album_video_tip_card", (int) true),
    C2paBadge("c2pa_icon_notification", (int) true),
    ViewerTableModeToast("need_to_show_table_mode_toast", (int) true),
    InstantSlowMoGuide("temporal_zoom_guide_recognized", (int) false),
    InstantSlowMoEditGuide("temporal_zoom_edit_guide_recognized", (int) false),
    InstantSlowMoEditAndShareGuide("instant_slow_mo_edit_and_share_guide_recognized", (int) false),
    InstantSlowMoGuideCount("temporal_zoom_guide_shown_count", 0),
    InstantSlowMoEditGuideCount("temporal_zoom_edit_guide_shown_count", 0),
    InstantSlowMoEditAndShareGuideCount("instant_slow_mo_edit_and_share_guide_shown_count", 0),
    InstantSlowMoSaveClipTipCount("instant_slow_mo_save_clip_title_shown_count", 0),
    NetworkUsageWarning("network_usage_warning_play", (int) false),
    SCloudMigrationComplete("scloud_migration_complete", (int) false),
    CleanOutBadge("event_show_clean_out_badge", (int) true),
    StoryHighlightBgmTipCard("story_highlight_bgm_tip_card", (int) true),
    StoryHighlightBgmDownloadAll("story_highlight_bgm_download_all_available", (int) true),
    TrashMigrationComplete("migration_finish", (int) false),
    RoamingTip("roaming_check", (int) false),
    MxAlbumTipCard("mx_album_guide_tip_card_first", (int) true),
    AppRatingShow("app_rating_show", (int) false),
    AppRatingNeverShow("app_rating_never_show", (int) false),
    AppRatingNoThanks("app_rating_no_thanks", (int) false),
    AppRatingEnteringCount("app_entering_count", 1),
    AppRatingLaterCount("app_rating_later_count", 0),
    CloudTimelineSyncState("timeline_sync_success", (int) false),
    QuickSharePrivacyTip("quick_share_privacy_tip", (int) false),
    SharedNewNotificationBadge("show_new_notification_badge", (int) false),
    SharedProfileShareNotice("is_need_to_show_popup_for_notifying_profile_to_be_shared", (int) true),
    SharedAlbumLinkTip("shared_album_link_tip", (int) true),
    RemasterNewBadge("show_new_badge_for_remaster_pictures", (int) true),
    RemasterGifNewBadge("show_new_badge_for_gif_remaster_pictures", (int) true),
    MotionPhotoExportNewBadge("show_new_badge_for_motion_photo_export", (int) true),
    ConfirmContactPermission("confirm_contact_permission", (int) false),
    SearchPetClusterRecognized("pet_cluster_item_recognized_once", (int) false),
    CmhPermissionTipCard("cmh_provider_permission_tip_card", (int) true),
    CmhPermissionTipCardAllowed("cmh_provider_permission_tip_card_display_possible", (int) true),
    CmhPermissionTipCardCount("cmh_provider_permission_tip_card_displayed_count", 0),
    CmhPermissionTipCardTime("cmh_provider_permission_tip_card_displayed_time", (int) -1),
    LogVideoTipCount("log_video_tip_shown_count", 0),
    SCloudMigrationState("scloud_migration_state", -1),
    CleanOutBadgeCount("event_clean_out_badge_count", 0),
    EventBadgeCount("event_notification_badge_count", 0),
    MxAlbumSharedCount("mx_album_shared_count", 0),
    SharedFamilyAlbumTipCardStep("family_album_suggested_steps", 1),
    MxAlbumTipCardCount("mx_album_guide_tip_card_second", 1),
    FolderNameIndex("folder_name_index", 1),
    AlbumGroupCrc("album_group_crc", (int) 0),
    LastHiddenScannedId("last_scanned_id", (int) 0),
    TrashEmptyTime("TrashEmptyLastTime", (int) 0),
    SharedLastVisitTime("shared_last_visited_time_ms", (int) -1),
    SharedLastUpdateTime("shared_last_updated_time_ms", (int) -1),
    SearchFaceClusterLastRecommendTime("face_cluster_last_recommended_time", (int) 0),
    HasAlbumCoverHistory("has_album_cover_history", (int) false),
    YearTimeSlot("year_time_slot", 30),
    SmartSwitchRestoreState("smartswitch_restore_state", 0),
    SdCardWarning("sd_card_warning_shown", (int) false),
    SdCardErrorTipCardCount("sd_card_errors_tip_card_displayed_count", (int) ""),
    SdCardErrorTipCardTime("sd_card_errors_tip_card_last_displayed_time_ms", (int) ""),
    SuggestExpiredLatestId("latest_suggested_expired_image_id", (int) -1),
    SuggestExpiredLatestTabId("latest_suggested_expired_image_id_for_tab", (int) -1),
    SuggestBadQualityLatestId("latest_suggested_bad_quality_image_id", (int) -1),
    SuggestBadQualityLatestTabId("latest_suggested_bad_quality_image_id_for_tab", (int) -1),
    SuggestDuplicatedLatestId("latest_suggested_duplicated_image_id", (int) -1),
    SuggestDuplicatedLatestTabId("latest_suggested_duplicated_image_id_for_tab", (int) -1),
    SuggestRemasterLatestTime("latest_suggested_remaster_pictures_time", (int) -1),
    SuggestRemasterLatestTabTime("latest_suggested_remaster_pictures_time_for_tab", (int) -1),
    SuggestHighlightVideoLatestTime("latest_highlight_videos_time", (int) -1),
    SuggestHighlightVideoLatestTabTime("latest_highlight_videos_time_for_tab", (int) -1),
    SuggestPortraitLatestTime("latest_portrait_suggestions_time", (int) -1),
    SuggestPortraitLatestTabTime("latest_portrait_suggestions_time_for_tab", (int) -1),
    CleanOutMotionPhotoClipCount("claenout_motion_photo_clip_count", -1),
    CleanOutMotionPhotoClipTabCount("claenout_motion_photo_clip_count_for_tab", -1),
    AlbumLatestUpdatedId("latest_update_album", 0),
    AlbumLatestUpdatedTime("latest_album_modified_time", (int) 0),
    SearchLatestFileId("latest_content_file_id", (int) -1),
    SearchResultNoItemsCount("search_result_no_items_count", 0),
    SearchCreatureAnalysisTipTime("search_creature_analysis_tip_first_displayed_time", (int) -1),
    SearchNormalAnalysisTipTime("search_normal_analysis_tip_first_displayed_time", (int) -1),
    SearchCreatureAnalysisTipClosed("search_creature_analysis_tip_closed", (int) false),
    SearchNormalAnalysisTipClosed("search_normal_analysis_tip_closed", (int) false),
    FaceServiceFaceGroupInvoke("search_faces_group_table_force_notification", (int) false),
    StoryViewedIds("STORIES_VIEWED_IDS", (int) ""),
    PendingSharePath("pending_share_path", (int) null),
    LastFileOpPath("last_file_op_path", (int) null),
    YearCacheDataStamp("year_cache_data_stamp_v2", (int) null),
    LastLocationKeyStack("lastLocationKeyStack", (int) ""),
    MotionPhotoCleanOutExcluded("MotionPhotoCleanOutDisAllowed", (int) ""),
    UsbStorageTipCardIsShow("usb_storage_tip_card_is_show", (int) false),
    UsedUsbStorageBadgeTable("used_usb_storage_badge_table", (int) ""),
    EOF("EOF", (int) false);
    
    private static final String TAG = "PreferenceCache";
    public final String key;
    public final Object valueDef;
    public final Class<?> valueType;

    private PreferenceCache(String str, boolean z) {
        this.key = str;
        this.valueDef = Boolean.valueOf(z);
        this.valueType = Boolean.TYPE;
    }

    public static List<String> loadLegacyMultiVersions(Set<String> set, String str) {
        List list = (List) set.stream().filter(new L(str, 0)).sorted().collect(Collectors.toList());
        if (list.size() > 1) {
            return list.subList(0, list.size() - 1);
        }
        return new ArrayList();
    }

    public static void migrate() {
        TimeTickLog timeTickLog = new TimeTickLog();
        Map<String, ?> all = GalleryPreference.getInstance().getAll();
        Set<String> keySet = all.keySet();
        timeTickLog.tick();
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        HashMap hashMap4 = new HashMap();
        Arrays.stream(values()).filter(new J(1, keySet)).forEach(new K(arrayList, hashMap, (Map) all, hashMap2, hashMap3, hashMap4));
        if (keySet.contains("SepLowSegment")) {
            arrayList.add("SepLowSegment");
            hashMap.put("SepLowSegment", Boolean.valueOf((String) all.get("SepLowSegment")));
        }
        timeTickLog.tick();
        if (!hashMap.isEmpty()) {
            GalleryPreference.getInstanceCache().saveStateBoolean(hashMap);
            timeTickLog.tick();
        }
        if (!hashMap2.isEmpty()) {
            GalleryPreference.getInstanceCache().saveStateInt(hashMap2);
            timeTickLog.tick();
        }
        if (!hashMap3.isEmpty()) {
            GalleryPreference.getInstanceCache().saveStateLong(hashMap3);
            timeTickLog.tick();
        }
        if (!hashMap4.isEmpty()) {
            GalleryPreference.getInstanceCache().saveState((HashMap<String, String>) hashMap4);
            timeTickLog.tick();
        }
        ArrayList arrayList2 = new ArrayList(arrayList);
        new ArrayList(Arrays.asList(PocFeatures.values())).stream().filter(new J(2, keySet)).filter(new C0680s(6)).map(new i(22)).forEach(new a(arrayList2, 17));
        timeTickLog.tick();
        keySet.stream().filter(new C0680s(7)).forEach(new a(arrayList2, 17));
        arrayList2.addAll(loadLegacyMultiVersions(keySet, "sharedViewColCnt"));
        arrayList2.addAll(loadLegacyMultiVersions(keySet, "albumPicturesViewColCnt"));
        arrayList2.addAll(loadLegacyMultiVersions(keySet, "timeViewColCnt"));
        arrayList2.addAll(loadLegacyMultiVersions(keySet, "searchPicturesViewColCnt"));
        arrayList2.addAll(loadLegacyMultiVersions(keySet, "trashColCnt"));
        arrayList2.addAll(loadLegacyMultiVersions(keySet, "PicturesPickerViewColCnt"));
        Stream.of(new String[]{"private_move_do_not_show", "appbar_expanded_once", "story_oneui_21", "show_trash_storage", "last_file_op_path", "key_recent_app_update_check_time", "videogif", "cropvideo", "videospeed", "collagebytitle", "ssetestdebug", "debugstories", "ParallelDecoding", "gallery_labs_developer_all", "stardv", "dump_database", "DevUt60", "DevUt6x", "DiagnosticMode", "WifiGallery", "albumPicturesViewColCnt70", "searchPicturesViewColCnt70", "sharedViewColCnt70", "sharingViewColCnt", "labs_appbar", "auto_update_merge_people_last_recommended_time", "SaveAsSticker", "SetBitmapOnConfirm", "InstantSlowMoPoc", "HardwareBitmap", "LiveTextHint", "ExitGestureViForAllQuickView", "LoadBothSideOnPageChanged", "MergeTimelineFloatingDateLocation", "SimpleFastOptions", "SlideshowBasedOnStoryHighlight", "StoryHighlightSaveAsyncAudioEncoding", "SupportMapMoving", "PhotoStrip30_On", "PhotoStrip40", "Stories7x", "oneui30_visual_search", "TimelineFloatingView", "TimelineFloatingView2", "show_new_badge_for_generative_ai_edit", "temporal_zoom_on_preview", "hide_badge", "on_demand_story_current_language", "on_demand_story_support_current_language", "story_service_version_code", "SaPrefAllKeySet", "album_count", "group_count", "merged_album_count", "galaxy_album_count", "system_album_count", "auto_updated_album_count", "created_by_user_album_count", "camera_album_count_ratio", "essential_album_count", "camera_album_count_ratio", "camera_sd_album_count_ratio", "status_log_last_update", "SaFavoriteImageCount", "SaFavoriteVideoCount", "SaNoFavorites"}).filter(new J(3, keySet)).forEach(new a(arrayList2, 17));
        if (!arrayList2.isEmpty()) {
            GalleryPreference.getInstance().removeState((Collection<String>) arrayList2);
        }
        timeTickLog.tick();
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add("B=[" + String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, hashMap.keySet()) + "]");
        arrayList3.add("I=[" + String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, hashMap2.keySet()) + "]");
        arrayList3.add("L=[" + String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, hashMap3.keySet()) + "]");
        arrayList3.add("S=[" + String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, hashMap4.keySet()) + "]");
        arrayList3.add("deprecated=[" + String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList2) + "]");
        String join = String.join("\n", arrayList3);
        Log.d(TAG, "migrate" + Logger.vt(Integer.valueOf(hashMap.size()), Integer.valueOf(hashMap2.size()), Integer.valueOf(hashMap3.size()), Integer.valueOf(hashMap4.size()), Integer.valueOf(arrayList.size()), Integer.valueOf(arrayList2.size()), timeTickLog) + "\n" + Logger.getEncodedString(join));
        PreferenceAnalytics.migrate(all);
    }

    public static String toDebugString() {
        ArrayList arrayList = new ArrayList();
        Stream.of(new PreferenceCache[]{SmartSwitchRestoreState, QuickDeleteService}).map(new C0670h(23)).forEach(new a(arrayList, 17));
        return "PreferenceCache=[" + String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList) + "]";
    }

    public void append(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            String string = getString();
            if (TextUtils.isEmpty(string)) {
                setString(str);
                return;
            }
            setString(string + str2 + str);
        }
    }

    public void clear() {
        GalleryPreference.getInstanceCache().removeState(this.key);
    }

    public boolean compareAndSet(boolean z, boolean z3) {
        if (z != getBoolean()) {
            return false;
        }
        setBoolean(z3);
        return true;
    }

    public int decrementAndGet() {
        int i2 = getInt();
        if (i2 <= ((Integer) this.valueDef).intValue()) {
            return i2;
        }
        int i7 = i2 - 1;
        setInt(i7);
        return i7;
    }

    public boolean getBoolean() {
        return GalleryPreference.getInstanceCache().loadBoolean(this.key, ((Boolean) this.valueDef).booleanValue());
    }

    public int getInt() {
        return GalleryPreference.getInstanceCache().loadInt(this.key, ((Integer) this.valueDef).intValue());
    }

    public long getLong() {
        return GalleryPreference.getInstanceCache().loadLong(this.key, ((Long) this.valueDef).longValue());
    }

    public String getString() {
        return GalleryPreference.getInstanceCache().loadString(this.key, (String) this.valueDef);
    }

    public int incrementAndGet() {
        int i2 = getInt() + 1;
        setInt(i2);
        return i2;
    }

    public boolean incrementIf(Predicate<Integer> predicate) {
        int i2 = getInt();
        if (!predicate.test(Integer.valueOf(i2))) {
            return false;
        }
        setInt(i2 + 1);
        return true;
    }

    public void setBoolean(boolean z) {
        GalleryPreference.getInstanceCache().saveState(this.key, z);
    }

    public void setInt(int i2) {
        GalleryPreference.getInstanceCache().saveState(this.key, i2);
    }

    public void setLong(long j2) {
        GalleryPreference.getInstanceCache().saveState(this.key, j2);
    }

    public void setString(String str) {
        GalleryPreference.getInstanceCache().saveState(this.key, str);
    }

    private PreferenceCache(String str, int i2) {
        this.key = str;
        this.valueDef = Integer.valueOf(i2);
        this.valueType = Integer.TYPE;
    }

    private PreferenceCache(String str, long j2) {
        this.key = str;
        this.valueDef = Long.valueOf(j2);
        this.valueType = Long.TYPE;
    }

    private PreferenceCache(String str, String str2) {
        this.key = str;
        this.valueDef = str2;
        this.valueType = String.class;
    }
}
