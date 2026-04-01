package com.samsung.android.gallery.module.trash.abstraction;

import N2.j;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.data.CloudData;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SecureFile;
import i.C0212a;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TrashBaseItem implements FileItemInterface {
    protected final String TAG = tag();
    boolean m360Video;
    int mBestImage;
    long mBurstShotId;
    final CloudData mCloudData = new CloudData();
    long mDateModified = -1;
    long mDateTaken;
    int mFileDuration;
    long mFileId;
    long mFileSize;
    long mGroupMediaId;
    String mGroupMode;
    int mGroupType;
    int mHeight;
    boolean mIsDrm;
    boolean mIsFavorite;
    boolean mIsHdr10Video;
    boolean mIsNotMatchData;
    String mLCThumbPath;
    double mLatitude;
    double mLongitude;
    MediaType mMediaType;
    String mMimeType;
    private String mMoveError;
    int mOrientation;
    int mOrientationTag;
    String mOriginFileHash;
    String mPath;
    int mRecordingMode;
    int mRecordingType;
    String mRelativePath;
    String mResolution;
    int mSefFileSubType = -1;
    int mSefFileType = -1;
    String mSefFileTypes;
    StorageType mStorageType;
    final ConcurrentHashMap<String, Object> mTag = new ConcurrentHashMap<>();
    String mTitle;
    String mVolumeName = "";
    int mWidth;

    private boolean existExtension(String str) {
        return !TextUtils.isEmpty(FileUtils.getExtension(str));
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            String str = this.TAG;
            Log.e(str, "clone failed e=" + e.getMessage());
            return this;
        }
    }

    public int getBestImage() {
        return this.mBestImage;
    }

    public String getCloudHash() {
        return this.mCloudData.hash;
    }

    public String getCloudOriginalBinaryHash() {
        return this.mCloudData.originalBinaryHash;
    }

    public long getCloudOriginalBinarySize() {
        return this.mCloudData.originalBinarySize;
    }

    public long getCloudOriginalSize() {
        return this.mCloudData.originalSize;
    }

    public int getCloudRevision() {
        return this.mCloudData.revision;
    }

    public String getCloudServerId() {
        return this.mCloudData.serverId;
    }

    public String getCloudServerPath() {
        return this.mCloudData.serverPath;
    }

    public String getCloudThumbPath() {
        return this.mCloudData.thumbPath;
    }

    public long getDateModified() {
        return this.mDateModified;
    }

    public long getDateTaken() {
        return this.mDateTaken;
    }

    public String getDecodedOriginFileHash(String str) {
        return new String(Base64.getDecoder().decode(str));
    }

    public String getDiskCacheKey() {
        return this.mPath;
    }

    public Double getDouble(Cursor cursor, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex >= 0) {
            return Double.valueOf(cursor.getDouble(columnIndex));
        }
        String str2 = this.TAG;
        Log.w(str2, "wrong index [" + str + "]");
        return Double.valueOf(MapUtil.INVALID_LOCATION);
    }

    public String getEncodedOriginFileHash(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    public int getFileDuration() {
        return this.mFileDuration;
    }

    public abstract long getFileId();

    public long getFileSize() {
        return this.mFileSize;
    }

    public long getGroupMediaId() {
        return this.mGroupMediaId;
    }

    public String getGroupMode() {
        return this.mGroupMode;
    }

    public int getGroupType() {
        return this.mGroupType;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getInt(Cursor cursor, int i2, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex >= 0) {
            return cursor.getInt(columnIndex);
        }
        String str2 = this.TAG;
        Log.w(str2, "wrong index [" + str + "][" + i2 + "]");
        return i2;
    }

    public String getLCThumbPath() {
        return this.mLCThumbPath;
    }

    public double getLatitude() {
        return this.mLatitude;
    }

    public long getLong(Cursor cursor, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex >= 0) {
            return cursor.getLong(columnIndex);
        }
        String str2 = this.TAG;
        Log.w(str2, "wrong index [" + str + "]");
        return 0;
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public MediaType getMediaType() {
        return this.mMediaType;
    }

    public String getMimeType() {
        return this.mMimeType;
    }

    public String getMoveError() {
        return this.mMoveError;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getOrientationTag() {
        return this.mOrientationTag;
    }

    public String getOriginFileHash() {
        return this.mOriginFileHash;
    }

    public String getPath() {
        return this.mPath;
    }

    public int getRecordingMode() {
        return this.mRecordingMode;
    }

    public int getRecordingType() {
        return this.mRecordingType;
    }

    public String getRelativePath() {
        return this.mRelativePath;
    }

    public String getResolution() {
        return this.mResolution;
    }

    public int getSefFileSubType() {
        return this.mSefFileSubType;
    }

    public int getSefFileType() {
        return this.mSefFileType;
    }

    public String getSefFileTypes() {
        return this.mSefFileTypes;
    }

    public StorageType getStorageType() {
        return this.mStorageType;
    }

    public String getString(Cursor cursor, String str, String str2) {
        int columnIndex = cursor.getColumnIndex(str2);
        if (columnIndex >= 0) {
            return cursor.getString(columnIndex);
        }
        Log.w(this.TAG, j.d("wrong index [", str2, "][", str, "]"));
        return str;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getVolumeName() {
        return this.mVolumeName;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public final void handleInvalidDataMatch(String str, String str2) {
        if (!existExtension(FileUtils.getNameFromPath(this.mPath))) {
            String extension = FileUtils.getExtension(TrashData.of(this).originalTitle, true);
            SecureFile secureFile = new SecureFile(C0212a.p(new StringBuilder(), this.mPath, extension));
            if (secureFile.exists()) {
                this.mPath = secureFile.getAbsolutePath();
                setNotMatchData(true);
                if (!existExtension(this.mTitle)) {
                    this.mTitle = C0212a.p(new StringBuilder(), this.mTitle, extension);
                }
            }
            try {
                if (StorageType.LocalCloud.equals(this.mStorageType) && !TextUtils.isEmpty(this.mLCThumbPath) && !TextUtils.isEmpty(str)) {
                    String optString = new JSONObject(str).optString("__lcThumbPath");
                    this.mLCThumbPath = optString + FileUtils.getExtension(str2, true);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean hasSefFileTypes(int i2) {
        String str = this.mSefFileTypes;
        if (str == null || !str.contains(String.valueOf(i2))) {
            return false;
        }
        return true;
    }

    public boolean is360Video() {
        return this.m360Video;
    }

    public final boolean isDirectorsViewItem(int i2) {
        if (!PreferenceFeatures.OneUi41.SUPPORT_DIRECTORS_VIEW || 3088 != i2) {
            return false;
        }
        return true;
    }

    public boolean isDrm() {
        return this.mIsDrm;
    }

    public boolean isFavourite() {
        return this.mIsFavorite;
    }

    public boolean isHdr10Video() {
        return this.mIsHdr10Video;
    }

    public boolean isNotMatchData() {
        return this.mIsNotMatchData;
    }

    public boolean isPostProcessing() {
        int i2 = this.mSefFileType;
        if (i2 == 2928 || i2 == 2784 || i2 == 2947) {
            return true;
        }
        return false;
    }

    public boolean isTrash() {
        return true;
    }

    public void setLCThumbPath(String str) {
        this.mLCThumbPath = str;
    }

    public void setMoveError(String str) {
        this.mMoveError = str;
    }

    public void setNotMatchData(boolean z) {
        this.mIsNotMatchData = z;
    }

    public void setOriginFileHash(String str) {
        this.mOriginFileHash = str;
    }

    public void setVolumeName(String str) {
        this.mVolumeName = str;
    }

    public abstract String tag();

    public Map<String, Object> tags() {
        return this.mTag;
    }
}
