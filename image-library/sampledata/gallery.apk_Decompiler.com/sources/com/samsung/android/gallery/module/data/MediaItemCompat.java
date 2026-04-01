package com.samsung.android.gallery.module.data;

import android.graphics.RectF;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.UnsafeCast;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaItemCompat {
    public static MediaItem parse(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(";");
        if (UnsafeCast.toInt(split[0], 0) == 19) {
            return valueOf19(split);
        }
        return null;
    }

    public static String toString(MediaItem mediaItem) {
        int i2;
        StringBuilder sb2 = new StringBuilder("19;");
        sb2.append(mediaItem.mFileID);
        sb2.append(";");
        sb2.append(mediaItem.mMediaID);
        sb2.append(";");
        MediaType mediaType = mediaItem.mMediaType;
        if (mediaType != null) {
            i2 = mediaType.toInt();
        } else {
            i2 = 1;
        }
        sb2.append(i2);
        sb2.append(";");
        sb2.append(mediaItem.mWidth);
        sb2.append(";");
        sb2.append(mediaItem.mHeight);
        sb2.append(";");
        sb2.append(mediaItem.mOrientation);
        sb2.append(";");
        sb2.append(mediaItem.mOrientationTag);
        sb2.append(";");
        sb2.append(mediaItem.mFileSize);
        sb2.append(";");
        sb2.append(mediaItem.mFileDuration);
        sb2.append(";");
        sb2.append(mediaItem.mDateModified);
        sb2.append(";");
        sb2.append(mediaItem.mDateTaken);
        sb2.append(";");
        sb2.append(mediaItem.mLatitude);
        sb2.append(";");
        sb2.append(mediaItem.mLongitude);
        sb2.append(";");
        String str = mediaItem.mMimeType;
        if (str == null) {
            str = "";
        }
        sb2.append(str);
        sb2.append(";");
        sb2.append(StorageType.toInt(mediaItem.getStorageType(), StorageType.Local.toInt()));
        sb2.append(";");
        String str2 = mediaItem.mPath;
        if (str2 == null) {
            str2 = "";
        }
        sb2.append(str2);
        sb2.append(";");
        sb2.append(RectUtils.toString(mediaItem.getCropRect()));
        sb2.append(";");
        sb2.append(mediaItem.getCloudOriginalSize());
        sb2.append(";");
        sb2.append(StringCompat.valueOf(mediaItem.getCloudServerPath(), ""));
        return sb2.toString();
    }

    public static MediaItem valueOf19(String[] strArr) {
        String str;
        MediaItem mediaItem = new MediaItem();
        mediaItem.mFileID = UnsafeCast.toLong(strArr[1], 0);
        mediaItem.mMediaID = UnsafeCast.toLong(strArr[2], 0);
        mediaItem.mMediaType = MediaType.valueOf(UnsafeCast.toInt(strArr[3], MediaType.Image.toInt()));
        mediaItem.mWidth = UnsafeCast.toInt(strArr[4], 0);
        int i2 = UnsafeCast.toInt(strArr[5], 0);
        mediaItem.mHeight = i2;
        mediaItem.mWidthInDB = mediaItem.mWidth;
        mediaItem.mHeightInDB = i2;
        mediaItem.mOrientation = UnsafeCast.toInt(strArr[6], 0);
        mediaItem.mOrientationTag = UnsafeCast.toInt(strArr[7], 0);
        mediaItem.mFileSize = UnsafeCast.toLong(strArr[8], 0);
        mediaItem.mFileDuration = UnsafeCast.toInt(strArr[9], 0);
        mediaItem.mDateModified = UnsafeCast.toLong(strArr[10], 0);
        mediaItem.mDateTaken = UnsafeCast.toLong(strArr[11], 0);
        mediaItem.mLatitude = UnsafeCast.toDouble(strArr[12], MapUtil.INVALID_LOCATION);
        mediaItem.mLongitude = UnsafeCast.toDouble(strArr[13], MapUtil.INVALID_LOCATION);
        String str2 = null;
        if (TextUtils.isEmpty(strArr[14])) {
            str = null;
        } else {
            str = strArr[14];
        }
        mediaItem.mMimeType = str;
        mediaItem.mStorageType = StorageType.valueOf(UnsafeCast.toInt(strArr[15], StorageType.Local.toInt()));
        if (!TextUtils.isEmpty(strArr[16])) {
            str2 = strArr[16];
        }
        mediaItem.mPath = str2;
        RectF stringToRectF = RectUtils.stringToRectF(strArr[17]);
        mediaItem.mCustomCropRect = stringToRectF;
        mediaItem.mSmartCropRectRatio = stringToRectF;
        if (mediaItem.isCloud()) {
            if (strArr.length > 18 && !TextUtils.isEmpty(strArr[18])) {
                mediaItem.mCloudData.originalSize = UnsafeCast.toLong(strArr[18], 0);
            }
            if (strArr.length > 19 && !TextUtils.isEmpty(strArr[19])) {
                mediaItem.mCloudData.serverPath = strArr[19];
            }
        }
        return mediaItem;
    }
}
