package com.samsung.android.gallery.module.details;

import A9.c;
import C3.i;
import N2.j;
import a8.d;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.mp.helper.TagEditApi;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.directorsview.DirectorsViewCache;
import com.samsung.android.gallery.module.fileio.redact.FileRedactorBuilder;
import com.samsung.android.gallery.module.location.LocationUpdater;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.media.MetadataTimeHelper;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.FileOpUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.samsung.android.sdk.sgpl.media.MediaMetadataInterface;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditDetailsUpdater2 {
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
            Log.e("EditDetailsUpdater2", "get new file path failed");
            return null;
        }
        StringBuilder s = C0212a.s(str);
        s.append(FileUtils.getExtension(str2, true));
        String sb2 = s.toString();
        return FileUtils.getDirectoryFromPath(str2) + sb2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$onSave$0(MediaItem mediaItem, String str, boolean z, boolean z3, boolean z7, boolean z9, FileItemInterface fileItemInterface) {
        if (mediaItem.equals(fileItemInterface)) {
            return Boolean.valueOf(updateItem((MediaItem) fileItemInterface, str, z, z3, z7, z9));
        }
        boolean z10 = z9;
        boolean z11 = z7;
        return Boolean.valueOf(updateItem((MediaItem) fileItemInterface, fileItemInterface.getPath(), false, z3, z11, z10));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSave$1(Runnable runnable, boolean z, boolean z3, boolean z7, boolean z9) {
        if (!PocFeatures.isEnabled(PocFeatures.GmpAll)) {
            this.mAppContext.getContentResolver().notifyChange(IMAGE_WATCH_URI, (ContentObserver) null);
            BlackboardUtils.publishDataChangedToOtherActivities(this.mBlackboard, true);
        }
        this.mBlackboard.post("command://event/DataDirty", (Object) null);
        if (runnable != null) {
            runnable.run();
        }
        Log.majorEvent("save" + Logger.v(Boolean.valueOf(z), Boolean.valueOf(z3), Boolean.valueOf(z7), Boolean.valueOf(z9)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSave$2(Runnable runnable, boolean z, boolean z3, boolean z7, boolean z9, int i2, int i7, int i8) {
        boolean z10 = z9;
        boolean z11 = z7;
        boolean z12 = z3;
        boolean z13 = z;
        ThreadUtil.runOnUiThread(new j(this, runnable, z13, z12, z11, z10));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSave$4(MediaItem mediaItem, Runnable runnable, String str) {
        boolean z;
        boolean z3;
        if (this.mBlackboard == null || this.mModel == null || mediaItem == null || this.mAppContext == null) {
            Runnable runnable2 = runnable;
            Log.e("EditDetailsUpdater2", "onSave failed");
            ThreadUtil.postOnUiThread(runnable2);
            return;
        }
        String newFilePath = getNewFilePath(str, mediaItem.getPath());
        if (TextUtils.isEmpty(newFilePath) || FileUtils.exists(newFilePath)) {
            z = false;
        } else {
            z = true;
        }
        if (!this.mModel.hasTitleChanged(str) || !z) {
            z3 = false;
        } else {
            z3 = true;
        }
        boolean hasDateChanged = this.mModel.hasDateChanged(mediaItem);
        boolean hasLocationChanged = this.mModel.hasLocationChanged(mediaItem);
        boolean hasCapturedUrlDeleted = this.mModel.hasCapturedUrlDeleted();
        boolean z7 = hasDateChanged;
        boolean z9 = hasLocationChanged;
        FileRedactorBuilder.edit(new FileItemInterface[]{mediaItem}).setOperation(new h(this, mediaItem, newFilePath, z3, hasDateChanged, hasLocationChanged, hasCapturedUrlDeleted)).setCallback(new i(this, runnable, z3, z7, z9, hasCapturedUrlDeleted)).withMediaScan(new c(5)).build().run();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateItem$5() {
        Utils.showToast(this.mAppContext, R$string.cannot_change_with_that_format);
    }

    private void onCapturedUrlDelete(MediaItem mediaItem) {
        try {
            new TagEditApi().removeCapturedValues(mediaItem.getMediaId());
            DetailsData.of(mediaItem).capturedUrl = null;
            this.mBlackboard.postEvent(EventMessage.obtain(3028));
            this.mBlackboard.post("command://event/TimelineDataDirty", (Object) null);
        } catch (UnsupportedOperationException e) {
            Log.e("EditDetailsUpdater2", "exception: " + e);
        }
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
            j.v("rename failed : ", e, "EditDetailsUpdater2");
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
            Log.d("EditDetailsUpdater2", "renameByUri" + Logger.vt(uri, Integer.valueOf(update), Long.valueOf(currentTimeMillis)));
        } catch (Exception e) {
            Log.e((CharSequence) "EditDetailsUpdater2", "renameByUri failed {" + uri + ',' + Logger.getEncodedString(str) + '}', (Throwable) e);
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
            Log.e("EditDetailsUpdater2", e.toString());
            return false;
        }
    }

    private boolean updateItem(MediaItem mediaItem, String str, boolean z, boolean z3, boolean z7, boolean z9) {
        MediaItem mediaItem2;
        long j2;
        String str2;
        if (z) {
            updatedTitle(mediaItem, str);
        }
        boolean z10 = false;
        if (z7) {
            double[] location = this.mModel.getLocation();
            mediaItem2 = mediaItem;
            new LocationUpdater(this.mAppContext).changeLocation(mediaItem2, location[0], location[1]);
            ThumbnailLoader.getInstance().removeDiskCache(mediaItem2);
        } else {
            mediaItem2 = mediaItem;
        }
        if (z9) {
            onCapturedUrlDelete(mediaItem2);
        }
        if (!z3) {
            return true;
        }
        if (Features.isEnabled(Features.SUPPORT_LOCAL_TIME)) {
            j2 = this.mModel.getLocalDateTime();
        } else {
            j2 = this.mModel.getDateTime();
        }
        String exifDateTime = TimeUtil.getExifDateTime(j2);
        if (updateExif(mediaItem2, exifDateTime) || updateIso(mediaItem2, exifDateTime)) {
            z10 = true;
        }
        if (z10) {
            postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_EDIT_DATETIME_SAVE);
            Log.d("EditDetailsUpdater2", "updateDateTime", exifDateTime);
            return z10;
        }
        ThreadUtil.postOnUiThread(new f(1, this));
        if (z) {
            str2 = " skip rename";
        } else {
            str2 = "";
        }
        Log.e("EditDetailsUpdater2", "updateDateTime failed".concat(str2));
        return z10;
    }

    private boolean updatedTitle(MediaItem mediaItem, String str) {
        int simpleHashCode = mediaItem.getSimpleHashCode();
        if (onUpdateTitle(mediaItem, mediaItem.getPath(), str)) {
            mediaItem.initDisplayName();
            mediaItem.setPath(str);
            replaceBitmapKey(simpleHashCode, mediaItem.getSimpleHashCode());
            this.mBlackboard.publish("data://user/storyNeedUpdate", Boolean.TRUE);
            if (PreferenceFeatures.OneUi41.SUPPORT_DIRECTORS_VIEW) {
                DirectorsViewCache.getInstance().updateMediaItem(mediaItem);
            }
            postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_EDIT_TITLE_SAVE);
            return true;
        }
        Log.e((CharSequence) "EditDetailsUpdater2", "onUpdateTitle failed", Logger.v(mediaItem.getPath(), str));
        return false;
    }

    public void onSave(Blackboard blackboard, EditDetailsModel editDetailsModel, String str, MediaItem mediaItem, Runnable runnable) {
        this.mBlackboard = blackboard;
        this.mModel = editDetailsModel;
        this.mAppContext = BlackboardUtils.readAppContext(blackboard);
        SimpleThreadPool.getInstance().execute(new d((Object) this, (Object) mediaItem, (Object) runnable, (Object) str, 6));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onSave$3(Integer num, Integer num2) {
    }
}
