package com.samsung.android.gallery.module.trash.abstraction;

import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TrashEmptyItem extends TrashBaseItem {
    public TrashEmptyItem(Cursor cursor) {
        this.mPath = cursor.getString(cursor.getColumnIndex("__absPath"));
        this.mMediaType = MediaType.valueOf(cursor.getInt(cursor.getColumnIndex("__mediaType")));
        this.mStorageType = StorageType.valueOf(cursor.getInt(cursor.getColumnIndex("__storageType")));
        TrashData.of(this).originalTitle = cursor.getString(cursor.getColumnIndex("__originTitle"));
        this.mVolumeName = cursor.getString(cursor.getColumnIndex("__volumeName"));
        this.mCloudData.serverId = cursor.getString(cursor.getColumnIndex("__cloudServerId"));
        this.mCloudData.revision = 0;
        String string = cursor.getString(cursor.getColumnIndex("__cloudTP"));
        String string2 = cursor.getString(cursor.getColumnIndex("__restoreExtra"));
        extractExtra(string2);
        if (!TextUtils.isEmpty(this.mOriginFileHash)) {
            this.mOriginFileHash = getDecodedOriginFileHash(this.mOriginFileHash);
        } else if (SdkConfig.atLeast(SdkConfig.GED.V)) {
            this.mOriginFileHash = getString(cursor, "", "__origin_file_hash");
        }
        handleInvalidDataMatch(string2, string);
    }

    private void extractExtra(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.mCloudData.serverPath = jSONObject.optString("__cloudServerPath");
                this.mLCThumbPath = jSONObject.optString("__lcThumbPath");
                if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                    this.mOriginFileHash = jSONObject.optString("__origin_file_hash");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public int getBucketID() {
        return 0;
    }

    public long getCloudTimestamp() {
        return System.currentTimeMillis();
    }

    public long getFileId() {
        return 0;
    }

    public long getMediaId() {
        return 0;
    }

    public long getTimeStamp() {
        return 0;
    }

    public boolean isBurstShot() {
        return false;
    }

    public boolean isSimilarShot() {
        return false;
    }

    public String tag() {
        return "TrashEmptyData";
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("TrashEmptyData{");
        if (this.mMediaType == MediaType.Video) {
            str = "v";
        } else {
            str = "i";
        }
        sb2.append(str);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(getVolumeName());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mStorageType);
        sb2.append(",C(");
        sb2.append(getCloudServerId());
        sb2.append(NumericEnum.SEP);
        sb2.append(Logger.getEncodedString(getCloudServerPath()));
        sb2.append(")}");
        return sb2.toString();
    }

    public TrashEmptyItem(FileItemInterface fileItemInterface) {
        this.mDateModified = fileItemInterface.getDateModified();
        this.mPath = fileItemInterface.getPath();
        this.mMediaType = fileItemInterface.getMediaType();
        this.mStorageType = fileItemInterface.getStorageType();
        TrashData.of(this).originalTitle = TrashData.of(fileItemInterface).originalTitle;
        this.mVolumeName = fileItemInterface.getVolumeName();
        this.mCloudData.serverId = fileItemInterface.getCloudServerId();
        this.mCloudData.revision = 0;
        String cloudThumbPath = fileItemInterface.getCloudThumbPath();
        String str = TrashData.of(fileItemInterface).restoreData;
        extractExtra(str);
        if (!TextUtils.isEmpty(this.mOriginFileHash)) {
            this.mOriginFileHash = getDecodedOriginFileHash(this.mOriginFileHash);
        } else if (SdkConfig.atLeast(SdkConfig.GED.V)) {
            this.mOriginFileHash = fileItemInterface.getOriginalFileHash();
        }
        handleInvalidDataMatch(str, cloudThumbPath);
    }
}
