package com.samsung.android.gallery.module.abstraction;

import A.a;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.providers.CameraUsbUri;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LaunchIntent {
    private final String mAction;
    private final Uri mData;
    private Bundle mExtras;
    private final boolean mFromBixby;
    private final boolean mFromCamera;
    private final Intent mIntent;
    private Boolean mPendingShareEvent;
    private Integer mResumePosition;
    private final String mType;
    private ArrayList<Uri> mUriList;
    private ArrayList<Integer> mViewBuckets;

    public LaunchIntent(Intent intent) {
        Intent intent2;
        String str;
        String str2;
        boolean z;
        if (intent != null) {
            intent2 = intent;
        } else {
            intent2 = new Intent();
        }
        this.mIntent = intent2;
        if (intent == null) {
            Log.w("LaunchIntent", "LaunchIntent is null, create empty intent");
        }
        Bundle extras = intent2.getExtras();
        if (extras != null) {
            Bundle bundle = new Bundle();
            this.mExtras = bundle;
            try {
                bundle.putAll(extras);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Uri uri = null;
        if (intent != null) {
            str = intent.getAction();
        } else {
            str = null;
        }
        this.mAction = str;
        if (intent != null) {
            str2 = intent.getType();
        } else {
            str2 = null;
        }
        this.mType = str2;
        this.mData = intent != null ? intent.getData() : uri;
        boolean z3 = true;
        if (intent == null || !intent.getBooleanExtra("from-Camera", false)) {
            z = false;
        } else {
            z = true;
        }
        this.mFromCamera = z;
        this.mFromBixby = (intent == null || !intent.getBooleanExtra("from_bixby", false)) ? false : z3;
    }

    public static LaunchIntent get(Blackboard blackboard) {
        if (blackboard != null) {
            return (LaunchIntent) blackboard.read("data://launch_intent");
        }
        return null;
    }

    public static boolean isFlipCoverViewerTheme(Blackboard blackboard) {
        LaunchIntent launchIntent;
        if (blackboard != null) {
            launchIntent = (LaunchIntent) blackboard.read("data://launch_intent");
        } else {
            launchIntent = null;
        }
        if (launchIntent == null) {
            return false;
        }
        if (launchIntent.isFromCamera() || StringCompat.equals(launchIntent.getTheme(), "action_view_dark_cover_theme")) {
            return true;
        }
        return false;
    }

    public static boolean isFlipCoverWidgetContentsPickerTheme(Blackboard blackboard) {
        LaunchIntent launchIntent;
        if (blackboard != null) {
            launchIntent = (LaunchIntent) blackboard.read("data://launch_intent");
        } else {
            launchIntent = null;
        }
        if (launchIntent == null || !launchIntent.isFromGalleryWidget() || !StringCompat.equals(launchIntent.getTheme(), "widget_cover_contents_picker_theme")) {
            return false;
        }
        return true;
    }

    public static boolean isFlipCoverWidgetTheme(Blackboard blackboard) {
        LaunchIntent launchIntent;
        if (blackboard != null) {
            launchIntent = (LaunchIntent) blackboard.read("data://launch_intent");
        } else {
            launchIntent = null;
        }
        if (launchIntent == null || !launchIntent.isFromGalleryWidget() || !StringCompat.equals(launchIntent.getTheme(), "widget_cover_theme")) {
            return false;
        }
        return true;
    }

    public static boolean isFromUSB(Blackboard blackboard) {
        LaunchIntent launchIntent;
        if (blackboard != null) {
            launchIntent = (LaunchIntent) blackboard.read("data://launch_intent");
        } else {
            launchIntent = null;
        }
        if (launchIntent == null || !CameraUsbUri.match(launchIntent.getUriData())) {
            return false;
        }
        return true;
    }

    public void clearResumePos() {
        this.mResumePosition = null;
    }

    public void consumePendingShareEvent() {
        if (this.mPendingShareEvent != null) {
            this.mPendingShareEvent = Boolean.FALSE;
        }
    }

    public boolean containsKey(String str) {
        Bundle bundle = this.mExtras;
        if (bundle == null || !bundle.containsKey(str)) {
            return false;
        }
        return true;
    }

    public String getAbsolutePath() {
        return this.mIntent.getStringExtra("AbsolutePath");
    }

    public String getAction() {
        return this.mAction;
    }

    public int getAlbumBucketId() {
        return this.mIntent.getIntExtra("bucketId", 0);
    }

    public String getAlbumBucketIds() {
        return this.mIntent.getStringExtra("bucketIds");
    }

    public String getAlbumPosition() {
        return this.mIntent.getStringExtra("key-album-list-position");
    }

    public int getAlbumType() {
        return this.mIntent.getIntExtra("key-album-type", 0);
    }

    public boolean getBixbyAlbumSlideshowIsSet() {
        if (!this.mIntent.getBooleanExtra("bixby_album_slideshow", false)) {
            return false;
        }
        this.mIntent.putExtra("bixby_album_slideshow", false);
        return true;
    }

    public String getBixbyLocationKey() {
        if (isFromBixby()) {
            return this.mIntent.getStringExtra("bixby_locationKey");
        }
        return null;
    }

    public String getBixbySearchKeyword() {
        if (isFromBixby()) {
            return this.mIntent.getStringExtra("bixby_search_keyword");
        }
        return null;
    }

    public String[] getBixbySearchKeywordCountry() {
        if (isFromBixby()) {
            return this.mIntent.getStringArrayExtra("bixby_search_keyword_country");
        }
        return null;
    }

    public String getBixbySearchKeywordOrder() {
        if (isFromBixby()) {
            return this.mIntent.getStringExtra("bixby_search_keyword_order");
        }
        return null;
    }

    public boolean getBixbySearchKeywordOrderIsSet() {
        if (!this.mIntent.getBooleanExtra("bixby_order_set", false)) {
            return false;
        }
        this.mIntent.putExtra("bixby_order_set", false);
        return true;
    }

    public long[] getBixbySearchKeywordPeriod() {
        if (isFromBixby()) {
            return this.mIntent.getLongArrayExtra("bixby_search_keyword_period");
        }
        return null;
    }

    public boolean getBixbyShareViaTVIsSet() {
        if (!this.mIntent.getBooleanExtra("bixby_share_via_tv", false)) {
            return false;
        }
        this.mIntent.putExtra("bixby_share_via_tv", false);
        return true;
    }

    public int getBrightness() {
        return this.mIntent.getIntExtra("brightness", -1);
    }

    public int getCoverPickType() {
        return this.mIntent.getIntExtra("KEY_COVER_PICKER_TYPE", -1);
    }

    public long getCreatureId() {
        Bundle bundle = this.mExtras;
        if (bundle != null) {
            return bundle.getLong("creature_id");
        }
        return 0;
    }

    public int getCreatureType() {
        Bundle bundle = this.mExtras;
        if (bundle != null) {
            return bundle.getInt("creature_type", -1);
        }
        return 0;
    }

    public String getCurrentCoverItemId() {
        return this.mIntent.getStringExtra("key-current-cover-item");
    }

    public long getDateTaken() {
        return this.mIntent.getLongExtra(IParameterKey.DATE_TAKEN, -1);
    }

    public <T> T getExtra(String str, T t) {
        Bundle bundle = this.mExtras;
        T t3 = bundle != null ? bundle.get(str) : null;
        return t3 != null ? t3 : t;
    }

    public int getFromCameraIndex() {
        if (isFromCamera()) {
            return this.mIntent.getIntExtra("from-Camera-Index", 0);
        }
        return 0;
    }

    public String getGalaxyFinderSearchKeyword() {
        return this.mIntent.getStringExtra(Contract.QUERY);
    }

    public String getIncludedPath() {
        return this.mIntent.getStringExtra("include_path_only");
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public String getItemCheckerDataKey() {
        return this.mIntent.getStringExtra("item_is_supported_checker");
    }

    public int getItemPosition() {
        return this.mIntent.getIntExtra("KEY_ITEM_POSITION", 0);
    }

    public int getMaxPickItem() {
        return this.mIntent.getIntExtra("pick-max-item", -1);
    }

    public String getMergedAlbumId() {
        return this.mIntent.getStringExtra("mergedAlbumId");
    }

    public String getMinResolution() {
        return this.mIntent.getStringExtra("filter_min_resolution");
    }

    public int getMultiPickCreatureType() {
        Bundle bundle = this.mExtras;
        if (bundle != null) {
            return bundle.getInt("creature_type", -1);
        }
        return 0;
    }

    public String getPendingBlackboardName() {
        return this.mIntent.getStringExtra("key_pending_blackboard_name");
    }

    public int getPendingUsageTypeValue() {
        return this.mIntent.getIntExtra("usage_type", 0);
    }

    public String getPickerUsageDescription() {
        String stringExtra = this.mIntent.getStringExtra("picker_usage_description");
        if (stringExtra == null || stringExtra.length() <= 62) {
            return stringExtra;
        }
        return stringExtra.substring(0, 62);
    }

    public String getPickerUsageTitle() {
        return this.mIntent.getStringExtra("picker_usage_title");
    }

    public Rect getQuickViewThumbnailRect() {
        if (isFromCamera()) {
            return (Rect) this.mIntent.getParcelableExtra("view-rect-animating-from");
        }
        return null;
    }

    public int getQuickViewThumbnailRotation() {
        if (isFromCamera()) {
            return this.mIntent.getIntExtra("display-rotation-animating-from", 0);
        }
        return 0;
    }

    public int getResumePos(String str) {
        Uri uri;
        if ((str != null || this.mData != null) && ((uri = this.mData) == null || !uri.toString().equals(str))) {
            return 0;
        }
        Integer valueOf = Integer.valueOf(this.mIntent.getIntExtra("resumePos", 0));
        this.mResumePosition = valueOf;
        return valueOf.intValue();
    }

    public String getSharedAlbumGroupId() {
        String stringExtra = this.mIntent.getStringExtra("key-shared-album-group-id");
        if (TextUtils.isEmpty(stringExtra)) {
            return this.mIntent.getStringExtra(BundleKey.GROUP_ID);
        }
        return stringExtra;
    }

    public String getSharedAlbumPosition() {
        return this.mIntent.getStringExtra("key-shared-album-list-position");
    }

    public String getSharedAlbumSpaceId() {
        String stringExtra = this.mIntent.getStringExtra("key-shared-album-space-id");
        if (TextUtils.isEmpty(stringExtra)) {
            return this.mIntent.getStringExtra(BundleKey.SPACE_ID);
        }
        return stringExtra;
    }

    public int getShortcutAlbumId() {
        return this.mIntent.getIntExtra("ALBUM_ID", 0);
    }

    public int getStoryAlbumBucketId() {
        return this.mIntent.getIntExtra("key-story-album-bucket-id", -1);
    }

    public String getStoryAlbumPosition() {
        return this.mIntent.getStringExtra("key-story-list-position");
    }

    public int getStoryId() {
        return this.mIntent.getIntExtra("story_id", -1);
    }

    public String getStoryUgci() {
        return this.mIntent.getStringExtra("story_cloud_sync_ugci");
    }

    public String getTheme() {
        return this.mIntent.getStringExtra("gallery_theme");
    }

    public String getType() {
        return this.mType;
    }

    public Uri getUriData() {
        return this.mData;
    }

    public ArrayList<Uri> getUriList() {
        ArrayList<Uri> arrayList;
        if (this.mUriList == null) {
            try {
                Bundle bundle = this.mExtras;
                if (bundle == null) {
                    arrayList = null;
                } else {
                    arrayList = (ArrayList) bundle.get("uriListData");
                }
                this.mUriList = arrayList;
            } catch (Exception e) {
                a.s(e, new StringBuilder("getUriList failed e="), "LaunchIntent");
            }
            if (this.mUriList == null) {
                this.mUriList = new ArrayList<>();
            }
        }
        return this.mUriList;
    }

    public ArrayList<Integer> getViewBuckets() {
        if (this.mViewBuckets == null) {
            ArrayList<Integer> integerArrayListExtra = this.mIntent.getIntegerArrayListExtra("viewbuckets");
            this.mViewBuckets = integerArrayListExtra;
            if (integerArrayListExtra == null) {
                this.mViewBuckets = new ArrayList<>();
            }
        }
        return this.mViewBuckets;
    }

    public boolean getVirtualAlbum() {
        return this.mIntent.getBooleanExtra("IS_VIRTUAL_ALBUM", false);
    }

    public boolean hasPendingShareEvent() {
        if (this.mPendingShareEvent == null) {
            this.mPendingShareEvent = Boolean.valueOf(this.mIntent.getBooleanExtra("key-pending-sharing-event", false));
        }
        return this.mPendingShareEvent.booleanValue();
    }

    public boolean hasTargetCluster() {
        return this.mIntent.getBooleanExtra("has_target_cluster", false);
    }

    public boolean isActionView() {
        return "android.intent.action.VIEW".equals(this.mAction) || "android.intent.action.QUICK_VIEW".equals(this.mAction);
    }

    public boolean isAddContentsToSharedAlbum() {
        return "com.android.gallery.action.ADD_CONTENTS_TO_SHARED_ALBUM".equals(this.mAction);
    }

    public boolean isAlbumCoverPick() {
        if (CoverPickType.FROM_ALBUM.toInt() == getCoverPickType()) {
            return true;
        }
        return false;
    }

    public boolean isAlbumMultiPick() {
        Bundle bundle = this.mExtras;
        if (bundle == null || !bundle.getBoolean("album_multi_pick", false)) {
            return false;
        }
        return true;
    }

    public boolean isCategoryLauncher() {
        return ((Boolean) Optional.ofNullable(this.mIntent.getCategories()).map(new g(2)).orElse(Boolean.FALSE)).booleanValue();
    }

    public boolean isCoverHistoryItemPick() {
        return this.mIntent.getBooleanExtra("album_cover_history_pick", false);
    }

    public boolean isCoverPick() {
        if (CoverPickType.NONE.toInt() != getCoverPickType()) {
            return true;
        }
        return false;
    }

    public boolean isCreatureMultiPick() {
        Bundle bundle = this.mExtras;
        if (bundle == null || !bundle.getBoolean("creature_multi_pick", false)) {
            return false;
        }
        return true;
    }

    public boolean isCursorDirBaseType() {
        String str = this.mType;
        if (str == null || !str.startsWith("vnd.android.cursor.dir")) {
            return false;
        }
        return true;
    }

    public boolean isCustomAlbumPicturesPick() {
        Bundle bundle = this.mExtras;
        if (bundle == null || !bundle.getBoolean("album_pictures_pick", false)) {
            return false;
        }
        return true;
    }

    public boolean isDisableTimeline() {
        return this.mIntent.getBooleanExtra("disable_timeline_divider", false);
    }

    public boolean isExtraAllowMultiple() {
        Bundle bundle = this.mExtras;
        if (bundle == null || !bundle.getBoolean("android.intent.extra.ALLOW_MULTIPLE", false)) {
            return false;
        }
        return true;
    }

    public boolean isFavoriteAlbumShortcut() {
        return "com.android.gallery.action.FAVORITE_ALBUM_SHORTCUT_VIEW".equals(this.mAction);
    }

    public boolean isFromAlbumCover() {
        return this.mIntent.getBooleanExtra("FromAlbumCover", false);
    }

    public boolean isFromBixby() {
        return this.mFromBixby;
    }

    public boolean isFromCamera() {
        return this.mFromCamera;
    }

    public boolean isFromCrossPicker() {
        return this.mIntent.getBooleanExtra("isFromCrossPicker", false);
    }

    public boolean isFromGallery() {
        return this.mIntent.getBooleanExtra("pick-from-gallery", false);
    }

    public boolean isFromGalleryWidget() {
        return this.mIntent.getBooleanExtra("photo-pick", false);
    }

    public boolean isFromLockScreen() {
        return this.mIntent.getBooleanExtra("createdByLockscreen", false);
    }

    public boolean isFromMainLauncher() {
        if (!"android.intent.action.MAIN".equals(this.mAction) || !isCategoryLauncher()) {
            return false;
        }
        return true;
    }

    public boolean isFromMyFiles() {
        return this.mIntent.getBooleanExtra("from-myfiles", false);
    }

    public boolean isFromPictureFrame() {
        return this.mIntent.getBooleanExtra("from-PictureFrame", false);
    }

    public boolean isFromReminder() {
        return this.mIntent.getBooleanExtra("from_reminder", false);
    }

    public boolean isFromSharedAlbumCover() {
        return this.mIntent.getBooleanExtra("FromSharedAlbumCover", false);
    }

    public boolean isFromSmartManager() {
        return this.mIntent.getBooleanExtra("smartmanager", false);
    }

    public boolean isFromStoryCover() {
        return this.mIntent.getBooleanExtra("FromStoryCover", false);
    }

    public boolean isFromTaskEdgeShortcut(String str) {
        return "com.samsung.android.app.taskedge".equals(str);
    }

    public boolean isFromThemeStore() {
        return this.mIntent.getBooleanExtra("from-ThemeStore", false);
    }

    public boolean isGetRectResult() {
        return this.mIntent.getBooleanExtra("is-get-rect-result", false);
    }

    public boolean isImageOnly() {
        return this.mIntent.getBooleanExtra("only_image", false);
    }

    public boolean isIncludeVirtualAlbum() {
        Bundle bundle = this.mExtras;
        if (bundle == null || !bundle.getBoolean("include_virtual_album", false)) {
            return false;
        }
        return true;
    }

    public boolean isKeepBrightness() {
        return this.mIntent.getBooleanExtra("isKeepBrightness", false);
    }

    public boolean isLaunchFamilyAlbum() {
        return "com.android.gallery.action.VIEW_FAMILY_ALBUM".equals(this.mAction);
    }

    public boolean isLocalContentOnly() {
        return this.mIntent.getBooleanExtra("android.intent.extra.LOCAL_ONLY", false);
    }

    public boolean isMultiPick() {
        if (this.mIntent.getBooleanExtra("multi-pick", false) || isExtraAllowMultiple()) {
            return true;
        }
        return false;
    }

    public boolean isPickForSharedAlbum() {
        return this.mIntent.getBooleanExtra("is-pick-to-add-shared-album", false);
    }

    public boolean isPickForStoryContents() {
        return this.mIntent.getBooleanExtra("is-pick-for-story-contents", false);
    }

    public boolean isPostProcessing() {
        return FileUtils.isPostProcessingFile(this.mIntent.getStringExtra(ServiceManagerUtil.PHOTO_EDIT_EXTRA_FILE_PATH_KEY));
    }

    public boolean isQuickSearchShortcut() {
        return "com.android.gallery.action.SEARCH_SHORTCUT_VIEW".equals(this.mAction);
    }

    public boolean isQuickShortcut() {
        if (isQuickSearchShortcut() || isRecentAlbumShortcut() || isFavoriteAlbumShortcut()) {
            return true;
        }
        return false;
    }

    public boolean isRecentAlbumShortcut() {
        return "com.android.gallery.action.RECENT_ALBUM_SHORTCUT_VIEW".equals(this.mAction);
    }

    public boolean isRefreshNotification() {
        return this.mIntent.getBooleanExtra("refresh_notification", false);
    }

    public boolean isRequireCrop() {
        return this.mIntent.hasExtra("crop");
    }

    public boolean isSearchFromGalaxyFinder() {
        return "com.android.gallery.action.SEARCH_FROM_GALAXY_FINDER_VIEW".equals(this.mAction);
    }

    public boolean isShowAlbumInfo() {
        return this.mIntent.getBooleanExtra("key-show-album-info", true);
    }

    public boolean isStartSharedDetailView() {
        if ("com.android.gallery.action.SHARED_PICTURES_VIEW".equals(this.mAction) || this.mIntent.getBooleanExtra("start-shared-detail-view", false)) {
            return true;
        }
        return false;
    }

    public boolean isStartSharedView() {
        if ("com.android.gallery.action.SHARED_VIEW".equals(this.mAction) || this.mIntent.getBooleanExtra("start-shared-view", false)) {
            return true;
        }
        return false;
    }

    public boolean isStartSharingInvitationsView() {
        return this.mIntent.getBooleanExtra("start-sharing-invitations-view", false);
    }

    public boolean isStartSharingStorageUseView() {
        if (!PreferenceFeatures.OneUi41.SHARING_ALBUM_STORAGE_USAGE || !this.mIntent.getBooleanExtra("start-sharing-storage-use-view", false)) {
            return false;
        }
        return true;
    }

    public boolean isStoryMultiPick() {
        Bundle bundle = this.mExtras;
        if (bundle == null || !bundle.getBoolean("story_multi_pick", false)) {
            return false;
        }
        return true;
    }

    public boolean isStoryPick() {
        Bundle bundle = this.mExtras;
        if (bundle == null || !bundle.getBoolean("story_pick", false)) {
            return false;
        }
        return true;
    }

    public boolean isSuperHdrEnabled() {
        return this.mIntent.getBooleanExtra("SuperHdr.enabled", true);
    }

    public boolean isTrashView() {
        return "com.android.gallery.action.TRASH_VIEW".equals(this.mAction);
    }

    public boolean isViewItem() {
        return this.mIntent.getBooleanExtra("view_item", false);
    }

    public boolean isViewRequestFromShortcut() {
        return this.mIntent.getBooleanExtra("from_shortcut", false);
    }

    public boolean isViewStoryPicture() {
        Bundle bundle = this.mExtras;
        if (bundle == null || !bundle.getBoolean("view_story_picture", false)) {
            return false;
        }
        return true;
    }

    public boolean needToCheckCloudContentIncluded() {
        return this.mIntent.getBooleanExtra("cloud_included", false);
    }

    public <T> T popExtra(String str, T t) {
        T extra = getExtra(str, (Object) null);
        if (extra != null) {
            removeExtra(str);
        }
        if (extra != null) {
            return extra;
        }
        return t;
    }

    public void removeExtra(String str) {
        try {
            this.mIntent.removeExtra(str);
            Bundle bundle = this.mExtras;
            if (bundle != null) {
                bundle.remove(str);
            }
        } catch (Exception unused) {
        }
    }

    public boolean supportAlbumCreation() {
        return "create_album".equals(this.mIntent.getStringExtra("support_menu"));
    }

    public String toString() {
        String str = "";
        StringBuilder sb2 = new StringBuilder(str);
        sb2.append(this.mIntent);
        sb2.append(" ");
        sb2.append(Logger.getEncodedString(this.mIntent.getDataString()));
        if (this.mExtras != null) {
            str = "\n" + Logger.toString(this.mExtras);
        }
        sb2.append(str);
        return sb2.toString();
    }

    public boolean useUriList() {
        return this.mIntent.getBooleanExtra("useUriList", false);
    }

    public static boolean isActionView(Blackboard blackboard) {
        LaunchIntent launchIntent = blackboard != null ? (LaunchIntent) blackboard.read("data://launch_intent") : null;
        return launchIntent != null && launchIntent.isActionView();
    }

    public static boolean isFromCamera(Blackboard blackboard) {
        LaunchIntent launchIntent = blackboard != null ? (LaunchIntent) blackboard.read("data://launch_intent") : null;
        return launchIntent != null && launchIntent.isFromCamera();
    }

    public static boolean isFromLockScreen(Blackboard blackboard) {
        LaunchIntent launchIntent = blackboard != null ? (LaunchIntent) blackboard.read("data://launch_intent") : null;
        return launchIntent != null && launchIntent.isFromLockScreen();
    }

    public Bundle getExtra() {
        return this.mExtras;
    }
}
