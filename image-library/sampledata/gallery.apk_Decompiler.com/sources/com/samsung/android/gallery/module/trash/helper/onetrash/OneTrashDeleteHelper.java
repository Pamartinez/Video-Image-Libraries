package com.samsung.android.gallery.module.trash.helper.onetrash;

import android.content.ContentValues;
import android.content.Context;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem;
import com.samsung.android.gallery.module.trash.helper.TrashDeleteHelper;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import com.samsung.scsp.media.api.database.url.OneDriveUrlReaderContract;
import i.C0212a;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OneTrashDeleteHelper extends TrashDeleteHelper {
    public OneTrashDeleteHelper(Context context) {
        super(context);
        setDeleteTime(System.currentTimeMillis());
    }

    public String getFinalTrashPath(TrashDeleteItem trashDeleteItem, File file) {
        if (trashDeleteItem.isCloudOnly()) {
            return file.getAbsolutePath();
        }
        String parent = FileUtils.getParent(trashDeleteItem.getPath());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(file.getAbsolutePath());
        String str = File.separator;
        sb2.append(str);
        sb2.append("uuid");
        sb2.append(str);
        sb2.append(TrashData.of(trashDeleteItem).deleteTime);
        sb2.append(str);
        sb2.append(parent);
        return C0212a.p(sb2, str, ".!%#@$");
    }

    public String getTargetCloudThumbTrashPath(TrashDeleteItem trashDeleteItem) {
        if (Features.isEnabled(Features.SUPPORT_ONE_TRASH_CLOUD_THUMBNAIL_NOT_REMOVE)) {
            return null;
        }
        File findTargetTrashDir = findTargetTrashDir(trashDeleteItem);
        if (findTargetTrashDir == null) {
            return super.getTargetCloudThumbTrashPath(trashDeleteItem);
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(findTargetTrashDir.getAbsolutePath());
        String str = File.separator;
        sb2.append(str);
        sb2.append("uuid");
        sb2.append(str);
        sb2.append(TrashData.of(trashDeleteItem).deleteTime);
        sb2.append(str);
        sb2.append("cloud");
        return sb2.toString();
    }

    public String getTargetLocalName(TrashDeleteItem trashDeleteItem) {
        return FileUtils.getNameFromPath(trashDeleteItem.getPath());
    }

    public String getVolumeName(TrashDeleteItem trashDeleteItem) {
        String volumeName = trashDeleteItem.getVolumeName();
        if (FileUtils.EXTERNAL_STORAGE_DIR.equals(volumeName)) {
            return "external_primary";
        }
        return volumeName.toLowerCase();
    }

    public boolean insertOrUpdateToTrash(TrashDeleteItem trashDeleteItem, String str, String str2) {
        String cloudServerId = trashDeleteItem.getCloudServerId();
        int isSameRecordExist = this.mTrashProvider.isSameRecordExist(cloudServerId);
        if (isSameRecordExist == -1) {
            setOriginalHashAndRestore(trashDeleteItem);
            return insertToTrashDB(trashDeleteItem, str, str2);
        }
        ContentValues contentValues = new ContentValues();
        StorageType storageType = trashDeleteItem.getStorageType();
        StorageType valueOf = StorageType.valueOf(isSameRecordExist);
        if (isLocal(storageType)) {
            contentValues.put("_data", str);
            contentValues.put("title_hash", str2);
            contentValues.put("original_path", trashDeleteItem.getPath());
            contentValues.put("title", trashDeleteItem.getTitle());
        }
        if (!storageType.equals(valueOf)) {
            contentValues.put("is_cloud", 3);
        }
        if (contentValues.size() > 0) {
            return updateTrashDb(contentValues, "cloud_server_id =? ", new String[]{cloudServerId});
        }
        String str3 = this.TAG;
        Log.d(str3, "exactly same content is already exist [" + storageType.toInt() + "][" + isSameRecordExist + "]");
        return true;
    }

    public boolean insertToTrashDB(TrashDeleteItem trashDeleteItem, String str, String str2) {
        int i2;
        long currentTimeMillis = System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_data", str);
        contentValues.put("title_hash", str2);
        contentValues.put("original_path", trashDeleteItem.getPath());
        contentValues.put("title", FileUtils.getBaseName(trashDeleteItem.getTitle()));
        contentValues.put("_display_name", trashDeleteItem.getTitle());
        contentValues.put("sec_media_id", Long.valueOf(trashDeleteItem.getFileId()));
        contentValues.put(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, Integer.valueOf(trashDeleteItem.getMediaType().toInt()));
        contentValues.put("mime_type", trashDeleteItem.getMimeType());
        contentValues.put(IParameterKey.SIZE, Long.valueOf(trashDeleteItem.getFileSize()));
        if (Features.isEnabled(Features.SUPPORT_ONE_TRASH_DURATION)) {
            contentValues.put("duration", Integer.valueOf(trashDeleteItem.getFileDuration()));
        }
        contentValues.put("width", Integer.valueOf(trashDeleteItem.getWidth()));
        contentValues.put("height", Integer.valueOf(trashDeleteItem.getHeight()));
        contentValues.put("orientation", Integer.valueOf(trashDeleteItem.getOrientation()));
        boolean z = false;
        contentValues.put("parent", 0);
        String userIdFromPath = FileUtils.getUserIdFromPath(trashDeleteItem.getPath());
        if (userIdFromPath != null) {
            i2 = Integer.parseInt(userIdFromPath);
        } else {
            i2 = 0;
        }
        contentValues.put(OneDriveUrlReaderContract.Entry.COLUMN_NAME_USER_ID, Integer.valueOf(i2));
        long j2 = TrashData.of(trashDeleteItem).deleteTime;
        if (j2 > 0) {
            contentValues.put("date_deleted", Long.valueOf(j2));
            contentValues.put("date_expires", Long.valueOf((((long) getExpiredDay()) * MediaApiContract.DAY_IN_MILLI) + j2));
        } else {
            long currentTimeMillis2 = System.currentTimeMillis();
            contentValues.put("date_deleted", Long.valueOf(currentTimeMillis2));
            contentValues.put("date_expires", Long.valueOf((((long) getExpiredDay()) * MediaApiContract.DAY_IN_MILLI) + currentTimeMillis2));
        }
        contentValues.put("is_cloud", Integer.valueOf(trashDeleteItem.getStorageType().toInt()));
        contentValues.put("cloud_server_id", trashDeleteItem.getCloudServerId());
        contentValues.put("volume_name", getVolumeName(trashDeleteItem));
        contentValues.put(KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA, TrashData.of(trashDeleteItem).restoreData);
        if (Features.isEnabled(Features.SUPPORT_SEC_MP_ORIENTATION_TAG)) {
            contentValues.put("orientation_tag", Integer.valueOf(trashDeleteItem.getOrientationTag()));
        }
        if (SdkConfig.atLeast(SdkConfig.GED.V)) {
            contentValues.put("original_file_hash", trashDeleteItem.getOriginFileHash());
        }
        if (Features.isEnabled(Features.SUPPORT_ONE_TRASH_NEW_CLOUD)) {
            contentValues.put("timestamp", Long.valueOf(trashDeleteItem.getTimeStamp()));
            if (trashDeleteItem.isCloud()) {
                contentValues.put("hash", trashDeleteItem.getCloudHash());
                contentValues.put("cloud_server_path", trashDeleteItem.getCloudServerPath());
                contentValues.put("cloud_original_size", Long.valueOf(trashDeleteItem.getCloudOriginalSize()));
                contentValues.put("cloud_original_binary_hash", trashDeleteItem.getCloudOriginalBinaryHash());
                contentValues.put("cloud_original_binary_size", Long.valueOf(trashDeleteItem.getCloudOriginalBinarySize()));
                contentValues.put("cloud_thumb_path", trashDeleteItem.getCloudThumbPath());
            }
        }
        try {
            if (this.mTrashProvider.insertTrash(contentValues) != null) {
                z = true;
            }
            return z;
        } finally {
            Log.d(this.TAG, "insert to trash db : " + trashDeleteItem.getFileId() + " +" + (System.currentTimeMillis() - currentTimeMillis));
        }
    }

    public String tag() {
        return "OneTrashDeleteHelper";
    }

    public OneTrashDeleteHelper(Context context, boolean z) {
        super(context, z);
        setDeleteTime(System.currentTimeMillis());
    }
}
