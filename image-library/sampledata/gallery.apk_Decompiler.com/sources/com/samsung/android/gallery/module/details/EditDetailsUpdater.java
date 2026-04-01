package com.samsung.android.gallery.module.details;

import A4.C0371f;
import A9.d;
import C3.i;
import F9.e;
import N2.j;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import androidx.window.embedding.c;
import com.samsung.android.gallery.database.dal.mp.helper.TagEditApi;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.GroupItemLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.directorsview.DirectorsViewCache;
import com.samsung.android.gallery.module.location.LocationUpdater;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.media.MetadataTimeHelper;
import com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.FileOpUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.samsung.android.sdk.sgpl.media.MediaMetadataInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditDetailsUpdater {
    public static final int DATABASE_UPDATE_DELAY;
    private static final Uri IMAGE_WATCH_URI = MediaUri.getInstance().getImageWatchUri();
    private Context mAppContext;
    private Blackboard mBlackboard;
    private EditDetailsModel mModel;

    static {
        int i2;
        if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
            i2 = StatusCodes.INPUT_MISSING;
        } else {
            i2 = 0;
        }
        DATABASE_UPDATE_DELAY = i2;
    }

    public static String getNewFilePath(String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str) || str.trim().isEmpty()) {
            Log.e("EditDetailsUpdater", "get new file path failed");
            return null;
        }
        StringBuilder s = C0212a.s(str);
        s.append(FileUtils.getExtension(str2, true));
        String sb2 = s.toString();
        return FileUtils.getDirectoryFromPath(str2) + sb2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSave$0(Runnable runnable) {
        if (!PocFeatures.isEnabled(PocFeatures.GmpAll)) {
            this.mAppContext.getContentResolver().notifyChange(IMAGE_WATCH_URI, (ContentObserver) null);
            BlackboardUtils.publishDataChangedToOtherActivities(this.mBlackboard, true);
        }
        this.mBlackboard.post("command://event/DataDirty", (Object) null);
        if (runnable != null) {
            runnable.run();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSave$1(MediaItem mediaItem, Runnable runnable, String str) {
        boolean z;
        if (this.mBlackboard == null || this.mModel == null || mediaItem == null || this.mAppContext == null) {
            Log.e("EditDetailsUpdater", "onSave failed");
            ThreadUtil.postOnUiThread(runnable);
            return;
        }
        String newFilePath = getNewFilePath(str, mediaItem.getPath());
        boolean z3 = false;
        if (TextUtils.isEmpty(newFilePath) || FileUtils.exists(newFilePath)) {
            z = false;
        } else {
            z = true;
        }
        if (this.mModel.hasTitleChanged(str) && z) {
            z3 = true;
        }
        boolean hasDateChanged = this.mModel.hasDateChanged(mediaItem);
        boolean hasLocationChanged = this.mModel.hasLocationChanged(mediaItem);
        boolean hasCapturedUrlDeleted = this.mModel.hasCapturedUrlDeleted();
        if (z3 || hasDateChanged) {
            onTitleAndDateTimeUpdated(mediaItem, newFilePath, z3, hasDateChanged);
        }
        if (hasLocationChanged) {
            onUpdateLocation(mediaItem);
        }
        if (hasCapturedUrlDeleted) {
            onCapturedUrlDelete(mediaItem);
        }
        Log.majorEvent("save" + Logger.v(Boolean.valueOf(z3), Boolean.valueOf(hasDateChanged), Boolean.valueOf(hasLocationChanged), Boolean.valueOf(hasCapturedUrlDeleted)));
        ThreadUtil.runOnUiThread(new c(22, this, runnable));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTitleAndDateTimeUpdated$2() {
        Utils.showToast(this.mAppContext, R$string.cannot_change_with_that_format);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTitleAndDateTimeUpdated$3(MediaItem mediaItem, String str, int i2) {
        if (mediaItem != null) {
            mediaItem.initDisplayName();
            mediaItem.setPath(str);
            replaceBitmapKey(i2, mediaItem.getSimpleHashCode());
            this.mBlackboard.publish("data://user/storyNeedUpdate", Boolean.TRUE);
            if (PreferenceFeatures.OneUi41.SUPPORT_DIRECTORS_VIEW) {
                DirectorsViewCache.getInstance().updateMediaItem(mediaItem);
            }
        }
        postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_EDIT_TITLE_SAVE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTitleAndDateTimeUpdated$4() {
        this.mBlackboard.post("command:///RefreshWithoutDelay", (Object) null);
        Log.local("EditDetailsUpdater", "after scan date time changed");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onUpdateLocation$7(MediaItem mediaItem, double[] dArr, MediaItem mediaItem2) {
        if (mediaItem2.getFileId() != mediaItem.getFileId()) {
            new LocationUpdater(this.mAppContext).updateLocation(mediaItem2, dArr);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onUpdateLocation$8(boolean z) {
        if (z) {
            this.mBlackboard.post("command://event/DataDirty", (Object) null);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$updateGroupShotDateTime$5(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem2 == null || mediaItem2.getFileId() == mediaItem.getFileId()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateGroupShotDateTime$6(String str, boolean z, ArrayList arrayList, Collection collection, MediaItem mediaItem) {
        onUpdateDateTime(mediaItem, str);
        if (!z) {
            arrayList.add(mediaItem);
        }
        collection.add(mediaItem.getPath());
    }

    private void onCapturedUrlDelete(MediaItem mediaItem) {
        try {
            new TagEditApi().removeCapturedValues(mediaItem.getMediaId());
            DetailsData.of(mediaItem).capturedUrl = null;
            this.mBlackboard.postEvent(EventMessage.obtain(3028));
            this.mBlackboard.post("command://event/TimelineDataDirty", (Object) null);
        } catch (UnsupportedOperationException e) {
            Log.e("EditDetailsUpdater", "exception: " + e);
        }
    }

    private void onTitleAndDateTimeUpdated(MediaItem mediaItem, String str, boolean z, boolean z3) {
        EditDetailsUpdater editDetailsUpdater;
        long j2;
        String str2;
        HashSet hashSet = new HashSet();
        String path = mediaItem.getPath();
        if (z3) {
            if (Features.isEnabled(Features.SUPPORT_LOCAL_TIME)) {
                j2 = this.mModel.getLocalDateTime();
            } else {
                j2 = this.mModel.getDateTime();
            }
            String exifDateTime = TimeUtil.getExifDateTime(j2);
            if (onUpdateDateTime(mediaItem, exifDateTime)) {
                hashSet.add(mediaItem.getPath());
                updateGroupShotDateTime(mediaItem, exifDateTime, hashSet);
                postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_EDIT_DATETIME_SAVE);
                Log.d("EditDetailsUpdater", "updateDateTime", exifDateTime);
            } else {
                ThreadUtil.postOnUiThread(new f(0, this));
                if (z) {
                    str2 = " skip rename";
                } else {
                    str2 = "";
                }
                Log.e("EditDetailsUpdater", "updateDateTime failed".concat(str2));
                return;
            }
        }
        if (z) {
            int simpleHashCode = mediaItem.getSimpleHashCode();
            if (onUpdateTitle(mediaItem, path, str)) {
                editDetailsUpdater = this;
                ThreadUtil.postOnUiThread(new e((Object) editDetailsUpdater, (Object) mediaItem, (Object) str, simpleHashCode, 5));
            } else {
                Log.e((CharSequence) "EditDetailsUpdater", "onUpdateTitle failed", Logger.v(path, str));
                return;
            }
        } else {
            editDetailsUpdater = this;
        }
        if (z && z3) {
            hashSet.remove(path);
        }
        if (!hashSet.isEmpty()) {
            MediaScanner.scanFiles((Collection<String>) hashSet, (MediaScannerListener) new g(editDetailsUpdater));
        }
    }

    private boolean onUpdateDateTime(MediaItem mediaItem, String str) {
        if (updateExif(mediaItem, str) || updateIso(mediaItem, str)) {
            return true;
        }
        return false;
    }

    private void onUpdateLocation(MediaItem mediaItem) {
        double[] location = this.mModel.getLocation();
        LocationUpdater locationUpdater = new LocationUpdater(this.mAppContext);
        locationUpdater.updateLocation(mediaItem, location);
        if (mediaItem.isGroupShot()) {
            GroupItemLoader.getGroupShotList(mediaItem).forEach(new C0371f((Object) this, mediaItem, (Object) location, 23));
        }
        if (LocationKey.isStoryPictures((String) this.mBlackboard.read("location://variable/currentv1"))) {
            locationUpdater.setCompletionListener(new g(this));
        }
        ThumbnailLoader.getInstance().removeDiskCache(mediaItem);
    }

    private boolean onUpdateTitle(MediaItem mediaItem, String str, String str2) {
        try {
            ThumbnailLoader.getInstance().removeDiskCache(mediaItem);
            Uri writableUri = ContentUri.getWritableUri(mediaItem);
            if (PreferenceFeatures.SUPPORT_RENAME_BY_MP) {
                renameByUri(this.mAppContext, writableUri, str2);
            } else {
                FileOpUtils.rename(str, str2);
                FileOpUtils.updateDbRename(this.mAppContext, writableUri, str2);
            }
            if (PocFeatures.isEnabled(PocFeatures.GmpLocOnly) || PocFeatures.isEnabled(PocFeatures.GmpAll)) {
                return true;
            }
            int i2 = DATABASE_UPDATE_DELAY;
            if (i2 > 0) {
                ThreadUtil.postOnBgThreadDelayed(new i(20), (long) i2);
                return true;
            }
            BlackboardUtils.forceRefreshPicturesDataGlobal();
            return true;
        } catch (Exception e) {
            j.v("rename failed : ", e, "EditDetailsUpdater");
            return false;
        }
    }

    private void postAnalyticsLog(AnalyticsEventId analyticsEventId) {
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_DETAILS_EDIT.toString(), analyticsEventId.toString());
    }

    private void renameByUri(Context context, Uri uri, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        String nameFromPath = FileUtils.getNameFromPath(str);
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("_display_name", nameFromPath);
            contentValues.put("title", FileUtils.getBaseName(nameFromPath));
            if ("3gp".equals(FileUtils.getExtension(nameFromPath))) {
                contentValues.put("mime_type", "video/3gpp");
            }
            int update = context.getContentResolver().update(uri, contentValues, (String) null, (String[]) null);
            Log.d("EditDetailsUpdater", "renameByUri" + Logger.vt(uri, Integer.valueOf(update), Long.valueOf(currentTimeMillis)));
        } catch (Exception e) {
            Log.e((CharSequence) "EditDetailsUpdater", "renameByUri failed {" + uri + ',' + Logger.getEncodedString(str) + '}', (Throwable) e);
        }
    }

    private void replaceBitmapKey(int i2, int i7) {
        Blackboard blackboard = this.mBlackboard;
        Bitmap bitmap = (Bitmap) blackboard.pop("data://bitmap/viewer/" + i2);
        if (bitmap != null) {
            Blackboard blackboard2 = this.mBlackboard;
            blackboard2.publish("data://bitmap/viewer/" + i7, bitmap);
        }
    }

    private boolean updateExif(MediaItem mediaItem, String str) {
        if (mediaItem != null && mediaItem.getPath() != null && !mediaItem.isCloudOnly() && (mediaItem.isJpeg() || mediaItem.isPng())) {
            String[] split = str.split(" ");
            if (ExifUtils.changeDate(mediaItem.getPath(), split[0], split[1], true) != null) {
                return true;
            }
        }
        return false;
    }

    private void updateGroupShotDateTime(MediaItem mediaItem, String str, Collection<String> collection) {
        EditDetailsUpdater editDetailsUpdater;
        boolean isSimilarShot = mediaItem.isSimilarShot();
        ArrayList arrayList = new ArrayList();
        if (!isSimilarShot) {
            arrayList.add(mediaItem);
        }
        if (mediaItem.isGroupShot()) {
            ArrayList<MediaItem> groupShotList = GroupItemLoader.getGroupShotList(mediaItem);
            Log.d("EditDetailsUpdater", "update group", mediaItem.getGroupTypeEnum(), Integer.valueOf(groupShotList.size()));
            editDetailsUpdater = this;
            groupShotList.stream().filter(new e(mediaItem, 1)).forEach(new d(editDetailsUpdater, str, isSimilarShot, arrayList, collection, 2));
        } else {
            editDetailsUpdater = this;
        }
        if (!arrayList.isEmpty()) {
            SimilarPhotoHelper.clearSimilarPhoto(editDetailsUpdater.mAppContext, arrayList);
        }
    }

    private boolean updateIso(MediaItem mediaItem, String str) {
        if (mediaItem == null || mediaItem.getPath() == null || mediaItem.isCloudOnly()) {
            return false;
        }
        if (!mediaItem.isHeif() && !mediaItem.isVideo()) {
            return false;
        }
        try {
            String localTime = MetadataTimeHelper.getLocalTime(mediaItem, this.mModel.getLocalDateTime(), MetadataTimeHelper.updateVideoTimeZoneOffset(mediaItem));
            MediaMetadataInterface mediaMetadataInterface = new MediaMetadataInterface(mediaItem.getPath());
            MediaMetadataInterface.Attribute attribute = MediaMetadataInterface.Attribute.ATTR_CREATION_TIME;
            if (!TextUtils.isEmpty(localTime)) {
                str = localTime;
            }
            mediaMetadataInterface.setAttribute(attribute, str);
            return mediaMetadataInterface.saveAttributes();
        } catch (Exception e) {
            Log.e("EditDetailsUpdater", e.toString());
            return false;
        }
    }

    public void onSave(Blackboard blackboard, EditDetailsModel editDetailsModel, String str, MediaItem mediaItem, Runnable runnable) {
        this.mBlackboard = blackboard;
        this.mModel = editDetailsModel;
        this.mAppContext = BlackboardUtils.readAppContext(blackboard);
        SimpleThreadPool.getInstance().execute(new a8.d((Object) this, (Object) mediaItem, (Object) runnable, (Object) str, 5));
    }
}
