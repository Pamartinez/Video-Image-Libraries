package com.samsung.android.gallery.app.controller.externals;

import A4.A;
import Gb.a;
import M4.j;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.module.abstraction.FileProviderUtil;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.DynamicViewData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dynamicview.DynamicViewPlayInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import com.samsung.scsp.common.Status;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PlayGenericVideoCmd extends AbstractPlayCmd {
    private Uri mStreamingVideoUri;

    private void addBasicInfo(Intent intent, MediaItem mediaItem) {
        String str;
        intent.setDataAndType(getContentUri(mediaItem), "video/*");
        if (mediaItem.isSharing()) {
            str = mediaItem.getTitle();
        } else {
            str = FileUtils.getBaseName(mediaItem.getTitle());
        }
        intent.putExtra("android.intent.extra.TITLE", str);
        intent.putExtra(OCRServiceConstant.KEY_PARAM_URI, getContentUri(mediaItem));
        intent.putExtra("from_gallery_to_videoplayer", true);
    }

    private void addExtraForDynamicViewInfo(Intent intent, int i2) {
        if (i2 == 303) {
            Optional.ofNullable(getHandler().getEventContextData("dynamic_view_clip_index")).ifPresent(new A(29, (Object) this, (Object) intent));
        }
    }

    private void addExtraInfoForBixby(Intent intent, LaunchIntent launchIntent) {
        long[] bixbySearchKeywordPeriod;
        if (launchIntent != null && launchIntent.isFromBixby() && (bixbySearchKeywordPeriod = launchIntent.getBixbySearchKeywordPeriod()) != null && bixbySearchKeywordPeriod.length > 1) {
            long j2 = bixbySearchKeywordPeriod[0];
            if (j2 != 0 && bixbySearchKeywordPeriod[1] != 0) {
                intent.putExtra("from_time", j2);
                intent.putExtra("to_time", bixbySearchKeywordPeriod[1]);
            }
        }
    }

    private void addExtraStreamForScreenLocked(Intent intent, boolean z, LaunchIntent launchIntent) {
        MediaData mediaData;
        ArrayList<MediaItem> allData;
        if (z && launchIntent.getExtra() != null && (mediaData = getHandler().getMediaData()) != null && (allData = mediaData.getAllData()) != null && !allData.isEmpty()) {
            intent.putParcelableArrayListExtra("android.intent.extra.STREAM", (ArrayList) allData.stream().filter(new j(7)).map(new a(4)).collect(Collectors.toList()));
        }
    }

    private int getBucketId(String str) {
        return ArgumentsUtil.getArgValue(str, "id", -1);
    }

    private String getBucketIdList(String str) {
        return ArgumentsUtil.getArgValue(str, "ids", (String) null);
    }

    private int getCategory(String str, LaunchIntent launchIntent, boolean z) {
        if (LocationKey.isTimelinePictures(str)) {
            return 100;
        }
        if (LocationKey.isStoryPictures(str)) {
            return 120;
        }
        if (LocationKey.isSharingPictures(str) && SdkConfig.atLeast(SdkConfig.GED.P)) {
            return MOCRLang.ARMENIAN;
        }
        if (LocationKey.isFavoritePictures(str) || isAlbumPicturesFavourite(str)) {
            return 140;
        }
        if (LocationKey.isSearchPictures(str) || LocationKey.isVideoPictures(str)) {
            return 150;
        }
        if (LocationKey.isRecentlyPictures(str) || isAlbumPicturesRecently(str)) {
            return MOCRLang.GURMUKHI;
        }
        if (LocationKey.isAlbumPictures(str) || LocationKey.isQuickViewPictures(str) || isUnlockedStateFromQuickView(str, launchIntent, z)) {
            return 110;
        }
        if (LocationKey.isDynamicViewList(str)) {
            return getDynamicViewCategory();
        }
        if (ArgumentsUtil.getArgValue(str, "view_origin_item", false)) {
            return 100;
        }
        if (LocationKey.isPrivateAlbum(str)) {
            return MOCRLang.LAO;
        }
        return -1;
    }

    private Uri getContentUri(MediaItem mediaItem) {
        Uri uri = this.mStreamingVideoUri;
        if (uri != null) {
            return uri;
        }
        Uri uri2 = ContentUri.getUri(mediaItem);
        if (uri2 != null || !mediaItem.isUriItem()) {
            return uri2;
        }
        return FileProviderUtil.getFileProviderUri(getContext(), mediaItem.getPath());
    }

    private String getCurrentLocationKey() {
        return (String) getBlackboard().read("location://variable/currentv1", "");
    }

    private int getDynamicViewCategory() {
        int i2;
        Object eventContextData = getHandler().getEventContextData("dynamic_view_type");
        if (eventContextData instanceof Integer) {
            i2 = ((Integer) eventContextData).intValue();
        } else {
            i2 = -1;
        }
        if (i2 == 0) {
            return StatusCodes.INPUT_MISSING;
        }
        if (i2 == 1) {
            return 301;
        }
        if (i2 == 3) {
            return Status.NOT_MODIFIED;
        }
        if (i2 == 2) {
            return 303;
        }
        return -1;
    }

    private long getItemId(MediaItem mediaItem) {
        if (!mediaItem.isCloudOnly() || SdkConfig.atLeast(SdkConfig.GED.P)) {
            return mediaItem.getFileId();
        }
        return mediaItem.getCloudId();
    }

    private String getSearchKeyword(String str) {
        if (LocationKey.isVideoPictures(str)) {
            return getContext().getString(R.string.video);
        }
        return ArgumentsUtil.getArgValue(str, "title", (String) null);
    }

    private int getSortBy(int i2) {
        if (i2 == -1) {
            return 290;
        }
        int loadSortBy = GalleryPreference.getInstance().loadSortBy(String.valueOf(i2), 12);
        int sortBy = SortByType.getSortBy(loadSortBy);
        if (sortBy != 10) {
            if (sortBy != 20) {
                if (sortBy != 30) {
                    return Encode.BitRate.VIDEO_QCIF_BITRATE;
                }
                if (SortByType.getOrderBy(loadSortBy) == 1) {
                    return 240;
                }
                return 250;
            } else if (SortByType.getOrderBy(loadSortBy) == 1) {
                return 200;
            } else {
                return MOCRLang.ARMENIAN;
            }
        } else if (SortByType.getOrderBy(loadSortBy) == 1) {
            return ThumbKind.MEDIUM_KIND_SIZE;
        } else {
            return 330;
        }
    }

    private boolean isAlbumPicturesFavourite(String str) {
        String string = ArgumentsUtil.getArgs(str).getString("id");
        if (string == null || string.isEmpty()) {
            return false;
        }
        return BucketUtils.isFavourite(Integer.valueOf(string).intValue());
    }

    private boolean isAlbumPicturesRecently(String str) {
        String string = ArgumentsUtil.getArgs(str).getString("id");
        if (string == null || string.isEmpty()) {
            return false;
        }
        return BucketUtils.isRecent(Integer.valueOf(string).intValue());
    }

    private boolean isBucketIdNeeded(int i2) {
        if (i2 == 110 || i2 == 120) {
            return true;
        }
        return false;
    }

    private boolean isUnlockedStateFromQuickView(String str, LaunchIntent launchIntent, boolean z) {
        if (!LocationKey.isQuickView(str) || !launchIntent.isFromLockScreen() || z) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addExtraForDynamicViewInfo$1(Intent intent, Object obj) {
        intent.putExtra("highlight_clip_index", ((Integer) obj).intValue());
        String str = this.TAG;
        Log.d(str, "dynamicView index=" + obj);
    }

    private void printDebug(MediaItem mediaItem, String str, int i2, int i7, int i8, String str2, long j2) {
        Log.d(this.TAG, "PlayGenericVideoCmd {loc=" + ArgumentsUtil.removeArgs(str) + ", id=" + mediaItem.getFileId() + ", media-id=" + mediaItem.getMediaId() + ", bucketId=" + i7 + ", category=" + i2 + ", sortBy=" + i8 + ", resume=" + j2 + "}");
    }

    public void addAudioEraserInfo(Intent intent) {
        Object eventContextData;
        if (Features.isEnabled(Features.SUPPORT_AUDIO_ERASER) && (eventContextData = getHandler().getEventContextData("audio_eraser_on")) != null) {
            int intValue = ((Integer) eventContextData).intValue();
            intent.putExtra("audioEraser", intValue);
            String str = this.TAG;
            Log.i(str, "addAudioEraserInfo [" + intValue + "]");
        }
    }

    public Intent createIntent(MediaItem mediaItem) {
        Intent intent;
        boolean isScreenLocked = SeApiCompat.isScreenLocked(getActivity());
        if (isScreenLocked) {
            intent = new Intent("com.samsung.intent.action.SECURE_LOCK");
            intent.addFlags(8388608);
            intent.putExtra("createBySecureLock", true);
            getContext().sendBroadcast(intent);
        } else {
            intent = new Intent("android.intent.action.VIEW");
        }
        createPlayVideoIntent(intent, mediaItem, isScreenLocked);
        intent.setClassName("com.samsung.android.video", "com.samsung.android.video.player.activity.MoviePlayer");
        if (mediaItem.isUriItem()) {
            intent.addFlags(1);
            return intent;
        }
        if (PocFeatures.SUPPORT_PRIVATE_ALBUM && mediaItem.isPrivateItem()) {
            intent.putExtra("private-album", true);
        }
        return intent;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00cb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void createPlayVideoIntent(android.content.Intent r11, com.samsung.android.gallery.module.data.MediaItem r12, boolean r13) {
        /*
            r10 = this;
            r10.addBasicInfo(r11, r12)
            r10.addVideoTransitionInfo(r11, r12)
            r10.addAudioEraserInfo(r11)
            com.samsung.android.gallery.support.blackboard.Blackboard r0 = r10.getBlackboard()
            java.lang.String r1 = "data://launch_intent"
            java.lang.Object r0 = r0.read(r1)
            com.samsung.android.gallery.module.abstraction.LaunchIntent r0 = (com.samsung.android.gallery.module.abstraction.LaunchIntent) r0
            if (r0 == 0) goto L_0x001d
            r10.addExtraInfoForBixby(r11, r0)
            r10.addExtraStreamForScreenLocked(r11, r13, r0)
        L_0x001d:
            java.lang.String r3 = r10.getCurrentLocationKey()
            int r4 = r10.getCategory(r3, r0, r13)
            boolean r13 = r10.isBucketIdNeeded(r4)
            r0 = 0
            r1 = -1
            if (r13 == 0) goto L_0x0057
            java.lang.String r13 = r10.getBucketIdList(r3)
            int r2 = r10.getBucketId(r3)
            if (r2 != r1) goto L_0x003b
            int r2 = r12.getAlbumID()
        L_0x003b:
            if (r2 != r1) goto L_0x0055
            boolean r1 = android.text.TextUtils.isEmpty(r13)
            if (r1 != 0) goto L_0x0055
            java.lang.String r1 = ","
            java.lang.String[] r1 = r13.split(r1)
            int r5 = r1.length
            if (r5 <= 0) goto L_0x0055
            r2 = 0
            r1 = r1[r2]
            int r1 = com.samsung.android.gallery.support.utils.UnsafeCast.toInt(r1)
        L_0x0053:
            r5 = r1
            goto L_0x0059
        L_0x0055:
            r5 = r2
            goto L_0x0059
        L_0x0057:
            r13 = r0
            goto L_0x0053
        L_0x0059:
            boolean r1 = android.text.TextUtils.isEmpty(r13)
            if (r1 == 0) goto L_0x0065
            int r1 = r10.getSortBy(r5)
        L_0x0063:
            r6 = r1
            goto L_0x0068
        L_0x0065:
            r1 = 280(0x118, float:3.92E-43)
            goto L_0x0063
        L_0x0068:
            r1 = 150(0x96, float:2.1E-43)
            if (r4 != r1) goto L_0x0070
            java.lang.String r0 = r10.getSearchKeyword(r3)
        L_0x0070:
            r7 = r0
            java.lang.String r0 = "category"
            r11.putExtra(r0, r4)
            java.lang.String r0 = "locationKey"
            r11.putExtra(r0, r3)
            boolean r0 = android.text.TextUtils.isEmpty(r13)
            if (r0 != 0) goto L_0x0086
            java.lang.String r0 = "bucketIdList"
            r11.putExtra(r0, r13)
        L_0x0086:
            java.lang.String r13 = "bucketId"
            r11.putExtra(r13, r5)
            java.lang.String r13 = "sortBy"
            r11.putExtra(r13, r6)
            long r0 = r10.getItemId(r12)
            int r13 = (int) r0
            java.lang.String r0 = "itemId"
            r11.putExtra(r0, r13)
            java.lang.String r13 = "itemIdLong"
            long r0 = r10.getItemId(r12)
            r11.putExtra(r13, r0)
            java.lang.String r13 = "scloud_id"
            long r0 = r12.getCloudId()
            r11.putExtra(r13, r0)
            java.lang.String r13 = "is_shared"
            boolean r0 = r12.isSharing()
            r11.putExtra(r13, r0)
            java.lang.String r13 = "resume_position"
            r0 = -1
            r11.putExtra(r13, r0)
            boolean r13 = android.text.TextUtils.isEmpty(r7)
            if (r13 != 0) goto L_0x00c7
            java.lang.String r13 = "searchStr"
            r11.putExtra(r13, r7)
        L_0x00c7:
            r13 = 220(0xdc, float:3.08E-43)
            if (r4 != r13) goto L_0x00d4
            java.lang.String r13 = "current_time"
            long r0 = java.lang.System.currentTimeMillis()
            r11.putExtra(r13, r0)
        L_0x00d4:
            r10.addExtraForDynamicViewInfo(r11, r4)
            r10.setIntentWithCommonExtra(r11)
            r8 = -1
            r1 = r10
            r2 = r12
            r1.printDebug(r2, r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.externals.PlayGenericVideoCmd.createPlayVideoIntent(android.content.Intent, com.samsung.android.gallery.module.data.MediaItem, boolean):void");
    }

    public String getAnalyticsDetail() {
        DynamicViewPlayInfo dynamicViewPlayInfo;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null) {
            dynamicViewPlayInfo = null;
        } else {
            dynamicViewPlayInfo = DynamicViewData.of(mediaItem).dynamicViewPlayInfo;
        }
        if (dynamicViewPlayInfo != null) {
            return dynamicViewPlayInfo.getAnalyticsDetail().toString();
        }
        return super.getAnalyticsDetail();
    }

    public String getEventId() {
        if (this.mMediaItem.isCloudOnly() || this.mMediaItem.isSharing()) {
            return AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_CLOUD_VIDEO.toString();
        }
        if (PreferenceFeatures.VideoPlayerFeature.isOpenInGalleryVideoPlayer()) {
            return AnalyticsEventId.EVENT_DETAIL_VIEW_OPEN_IN_VIDEO_PLAYER.toString();
        }
        return AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_VIDEO.toString();
    }

    public void handleFail() {
        new PlayVideoChooserCmd().execute(getHandler(), this.mMediaItem);
    }

    public void init(Object... objArr) {
        super.init(objArr);
        if (objArr.length > 1) {
            this.mStreamingVideoUri = objArr[1];
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Runnable runnable;
        if (Features.isEnabled(Features.SUPPORT_APP_TRANSITION) && (runnable = BlackboardUtils.getAppTransitionCallback(getBlackboard())[0]) != null) {
            runnable.run();
        }
        super.onExecute(eventContext, objArr);
    }

    public void startActivity(Intent intent) {
        super.startActivity(intent);
        if (!Features.isEnabled(Features.SUPPORT_APP_TRANSITION)) {
            this.mActivity.overridePendingTransition(0, 0);
        }
    }
}
