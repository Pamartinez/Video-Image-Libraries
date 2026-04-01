package com.samsung.android.gallery.module.trash.helper.onetrash;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.support.config.LocalDatabase;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import com.samsung.scsp.media.api.database.url.OneDriveUrlReaderContract;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OneTrashMigration {
    Uri TRASH_URI = Uri.parse("content://sectrash/files");

    private ContentValues getCVs(MediaItem mediaItem) {
        ContentValues contentValues = new ContentValues();
        String str = TrashData.of(mediaItem).originalTitle;
        contentValues.put("_data", mediaItem.getPath());
        contentValues.put("title_hash", mediaItem.getTitle());
        ExtrasID extrasID = ExtrasID.ORIGIN_PATH;
        contentValues.put("original_path", (String) mediaItem.getExtra(extrasID));
        contentValues.put("title", FileUtils.getBaseName(str));
        contentValues.put("_display_name", str);
        contentValues.put("sec_media_id", Long.valueOf(mediaItem.getFileId()));
        contentValues.put(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, Integer.valueOf(mediaItem.getMediaType().toInt()));
        contentValues.put("mime_type", mediaItem.getMimeType());
        contentValues.put("is_cloud", Integer.valueOf(mediaItem.getStorageType().toInt()));
        contentValues.put("width", Integer.valueOf(mediaItem.getWidth()));
        contentValues.put("height", Integer.valueOf(mediaItem.getHeight()));
        contentValues.put("orientation", Integer.valueOf(mediaItem.getOrientation()));
        if (Features.isEnabled(Features.SUPPORT_SEC_MP_ORIENTATION_TAG)) {
            contentValues.put("orientation_tag", Integer.valueOf(mediaItem.getOrientationTag()));
        }
        int i2 = 0;
        contentValues.put("parent", 0);
        String userIdFromPath = FileUtils.getUserIdFromPath((String) mediaItem.getExtra(extrasID));
        if (userIdFromPath != null) {
            i2 = Integer.parseInt(userIdFromPath);
        }
        contentValues.put(OneDriveUrlReaderContract.Entry.COLUMN_NAME_USER_ID, Integer.valueOf(i2));
        long j2 = TrashData.of(mediaItem).deleteTime;
        long j3 = (((long) TrashData.of(mediaItem).expiredPeriod) * MediaApiContract.DAY_IN_MILLI) + j2;
        contentValues.put("date_deleted", Long.valueOf(j2));
        contentValues.put("date_expires", Long.valueOf(j3));
        contentValues.put("cloud_server_id", mediaItem.getCloudServerId());
        contentValues.put("volume_name", getVolumeName(mediaItem));
        String restoreExtraAndSize = getRestoreExtraAndSize(mediaItem);
        contentValues.put(IParameterKey.SIZE, Long.valueOf(mediaItem.getFileSize()));
        if (Features.isEnabled(Features.SUPPORT_ONE_TRASH_DURATION)) {
            contentValues.put("duration", Integer.valueOf(mediaItem.getFileDuration()));
        }
        contentValues.put(KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA, restoreExtraAndSize);
        return contentValues;
    }

    private String getRestoreExtraAndSize(MediaItem mediaItem) {
        try {
            String str = TrashData.of(mediaItem).restoreData;
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            String cloudThumbPath = mediaItem.getCloudThumbPath();
            long optLong = jSONObject.optLong("__size", 0);
            if (optLong != 0) {
                mediaItem.setFileSize(optLong);
            }
            int optInt = jSONObject.optInt("__fileDuration", 0);
            if (optInt != 0) {
                mediaItem.setFileDuration(optInt);
            }
            if (cloudThumbPath != null) {
                jSONObject.put("__cloudTP", cloudThumbPath);
            }
            jSONObject.put("__burstGroupID", mediaItem.getGroupMediaId()).put("__bestImage", mediaItem.getBestImage());
            return jSONObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getVolumeName(MediaItem mediaItem) {
        String volumeName = mediaItem.getVolumeName();
        if (TextUtils.isEmpty(volumeName) || !FileUtils.EXTERNAL_STORAGE_DIR.equals(volumeName)) {
            return volumeName.toLowerCase();
        }
        return "external_primary";
    }

    private int migration() {
        Cursor cursor;
        ContentResolver contentResolver = AppResources.getAppContext().getContentResolver();
        long currentTimeMillis = System.currentTimeMillis();
        int i2 = 0;
        int i7 = 0;
        while (true) {
            cursor = null;
            try {
                cursor = contentResolver.query(LocalDatabase.TRASH_URI, new String[]{"*"}, (String) null, (String[]) null, "__absID ASC limit " + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + StatusCodes.INPUT_MISSING);
                if (cursor == null) {
                    break;
                } else if (!cursor.moveToFirst()) {
                    break;
                } else {
                    int count = cursor.getCount();
                    i7 += contentResolver.bulkInsert(this.TRASH_URI, getContentValues(cursor));
                    if (count < 300) {
                        Utils.closeSilently(cursor);
                        PreferenceCache.TrashMigrationComplete.setBoolean(true);
                        Log.i("OneTrashMigration", "migration resultCount = " + i7 + " elapsed = " + (System.currentTimeMillis() - currentTimeMillis));
                        return i7;
                    }
                    Utils.closeSilently(cursor);
                    i2 += StatusCodes.INPUT_MISSING;
                }
            } catch (Exception e) {
                try {
                    Log.e("OneTrashMigration", e.getMessage());
                } catch (Throwable th) {
                    PreferenceCache.TrashMigrationComplete.setBoolean(true);
                    throw th;
                }
            }
        }
        Utils.closeSilently(cursor);
        PreferenceCache.TrashMigrationComplete.setBoolean(true);
        return 0;
    }

    public boolean checkValid() {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH || PreferenceCache.TrashMigrationComplete.getBoolean()) {
            return false;
        }
        return true;
    }

    public ContentValues[] getContentValues(Cursor cursor) {
        ContentValues[] contentValuesArr = new ContentValues[cursor.getCount()];
        int i2 = 0;
        do {
            contentValuesArr[i2] = getCVs(MediaItemLoader.load(cursor));
            i2++;
        } while (cursor.moveToNext());
        return contentValuesArr;
    }

    public int getMigrationCount() {
        Throwable th;
        Cursor query = AppResources.getAppContext().getContentResolver().query(LocalDatabase.TRASH_URI, new String[]{"count(*)"}, (String) null, (String[]) null, (String) null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    int i2 = query.getInt(0);
                    query.close();
                    return i2;
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        if (query != null) {
            query.close();
        }
        return 0;
        throw th;
    }

    public int startMigration() {
        if (checkValid()) {
            return migration();
        }
        Log.w("OneTrashMigration", "not valid migration");
        return 0;
    }
}
