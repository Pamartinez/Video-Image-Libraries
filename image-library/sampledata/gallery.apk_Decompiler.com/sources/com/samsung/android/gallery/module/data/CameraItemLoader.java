package com.samsung.android.gallery.module.data;

import android.database.Cursor;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.globalpostprocmgr.IGPPDBInterface;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sum.core.types.NumericEnum;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CameraItemLoader {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum CameraColumn {
        ID("_id"),
        MEDIA_ID("media_id"),
        SEC_MEDIA_ID("sec_media_id"),
        DATA("_data"),
        VOLUME_NAME("volume_name"),
        TITLE("title"),
        DISPLAY_NAME("_display_name"),
        BUCKET_ID("bucket_id"),
        BUCKET_DISPLAY_NAME("bucket_display_name"),
        GROUP_ID(BundleKey.GROUP_ID),
        SIZE(IParameterKey.SIZE),
        DATE_TIME(IGPPDBInterface.IRequestQueue.FIELD_REQUEST_DATETIME),
        DATE_TAKEN(IParameterKey.DATE_TAKEN),
        DATE_MODIFIED(IParameterKey.DATE_MODIFIED),
        LATITUDE("latitude"),
        LONGITUDE("longitude"),
        ORIENTATION("orientation"),
        ORIENTATION_TAG("orientation_tag"),
        MEDIA_TYPE(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE),
        SEF_FILE_TYPE("sef_file_type"),
        MIME_TYPE("mime_type"),
        WIDTH("width"),
        HEIGHT("height"),
        DRM("is_drm"),
        SEF_FILE_TYPES("sef_file_types");
        
        static boolean sInitRequired;
        int index;
        String name;

        static {
            sInitRequired = true;
        }

        private CameraColumn(String str) {
            this.name = str;
            this.index = -1;
        }

        public static void initialize(Cursor cursor) {
            if (sInitRequired) {
                sInitRequired = false;
                for (CameraColumn cameraColumn : values()) {
                    cameraColumn.index = cursor.getColumnIndex(cameraColumn.name);
                }
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CameraItem extends MediaItem {
        long mId;

        public String toString() {
            StringBuilder sb2 = new StringBuilder("CameraItem[");
            sb2.append(this.mId);
            sb2.append("]{");
            Object obj = this.mMediaType;
            if (obj == MediaType.Video) {
                obj = "v";
            } else if (obj == MediaType.Image) {
                obj = "i";
            }
            sb2.append(obj);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.mMediaID);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.mFileID);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.mAlbumID);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.mGroupMediaId);
            sb2.append(" (");
            sb2.append(this.mWidth);
            sb2.append("x");
            sb2.append(this.mHeight);
            sb2.append(Log.TAG_SEPARATOR);
            sb2.append(this.mOrientation);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.mOrientationTag);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.mFileSize);
            sb2.append(NumericEnum.SEP);
            sb2.append(this.mDateTaken);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.mDateModified);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.mMimeType);
            sb2.append(NumericEnum.SEP);
            sb2.append(this.mSefFileType);
            sb2.append(NumericEnum.SEP);
            sb2.append(this.mSefFileTypes);
            sb2.append(") ");
            sb2.append(Logger.getEncodedString(this.mPath));
            sb2.append("}");
            return sb2.toString();
        }
    }

    private static double getDouble(Cursor cursor, int i2, double d) {
        if (i2 < 0) {
            return d;
        }
        return cursor.getDouble(i2);
    }

    private static int getInt(Cursor cursor, int i2, int i7) {
        if (i2 < 0) {
            return i7;
        }
        return cursor.getInt(i2);
    }

    private static long getLong(Cursor cursor, int i2, long j2) {
        if (i2 < 0) {
            return j2;
        }
        return cursor.getLong(i2);
    }

    private static int getOrientationTag(int i2) {
        if (i2 == 0) {
            return 1;
        }
        if (i2 == 90) {
            return 6;
        }
        if (i2 == 180) {
            return 3;
        }
        if (i2 != 270) {
            return 0;
        }
        return 8;
    }

    private static String getString(Cursor cursor, int i2, String str) {
        if (i2 < 0) {
            return str;
        }
        return cursor.getString(i2);
    }

    public static MediaItem load(Cursor cursor) {
        try {
            return loadCursor(cursor);
        } catch (Exception e) {
            com.samsung.android.gallery.support.utils.Log.e((CharSequence) "CameraItemLoader", "load failed", (Throwable) e);
            return null;
        }
    }

    private static MediaItem loadCursor(Cursor cursor) {
        int i2;
        CameraColumn.initialize(cursor);
        CameraItem cameraItem = new CameraItem();
        cameraItem.mId = getLong(cursor, CameraColumn.ID.index, 0);
        cameraItem.mMediaID = getLong(cursor, CameraColumn.MEDIA_ID.index, 0);
        cameraItem.mFileID = getLong(cursor, CameraColumn.SEC_MEDIA_ID.index, 0);
        cameraItem.mPath = getString(cursor, CameraColumn.DATA.index, (String) null);
        cameraItem.mVolumeName = getString(cursor, CameraColumn.VOLUME_NAME.index, (String) null);
        cameraItem.mWidth = getInt(cursor, CameraColumn.WIDTH.index, 0);
        cameraItem.mHeight = getInt(cursor, CameraColumn.HEIGHT.index, 0);
        int i7 = getInt(cursor, CameraColumn.ORIENTATION.index, 0);
        cameraItem.mOrientation = i7;
        cameraItem.mOrientationTag = getOrientationTag(i7);
        cameraItem.mMediaType = MediaType.valueOf(getInt(cursor, CameraColumn.MEDIA_TYPE.index, MediaType.Unsupported.toInt()));
        cameraItem.mMimeType = getString(cursor, CameraColumn.MIME_TYPE.index, "");
        cameraItem.mSefFileType = getInt(cursor, CameraColumn.SEF_FILE_TYPE.index, -1);
        cameraItem.mTitle = getString(cursor, CameraColumn.TITLE.index, "");
        cameraItem.mDisplayName = getString(cursor, CameraColumn.DISPLAY_NAME.index, "");
        int i8 = getInt(cursor, CameraColumn.BUCKET_ID.index, -1);
        cameraItem.mAlbumID = i8;
        cameraItem.mBucketID = i8;
        cameraItem.mGroupMediaId = getLong(cursor, CameraColumn.GROUP_ID.index, 0);
        cameraItem.mFileSize = getLong(cursor, CameraColumn.SIZE.index, 0);
        int i10 = CameraColumn.DATE_TIME.index;
        if (i10 >= 0) {
            cameraItem.mDateTaken = cursor.getLong(i10);
        }
        if (cameraItem.mDateTaken <= 0 && (i2 = CameraColumn.DATE_TAKEN.index) >= 0) {
            cameraItem.mDateTaken = cursor.getLong(i2);
        }
        cameraItem.mDateModified = getLong(cursor, CameraColumn.DATE_MODIFIED.index, 0);
        cameraItem.mLatitude = getDouble(cursor, CameraColumn.LATITUDE.index, MapUtil.INVALID_LOCATION);
        cameraItem.mLongitude = getDouble(cursor, CameraColumn.LONGITUDE.index, MapUtil.INVALID_LOCATION);
        cameraItem.mSefFileTypes = getString(cursor, CameraColumn.SEF_FILE_TYPES.index, "");
        return cameraItem;
    }
}
