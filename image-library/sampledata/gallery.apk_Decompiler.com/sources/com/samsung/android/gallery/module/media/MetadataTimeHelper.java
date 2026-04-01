package com.samsung.android.gallery.module.media;

import N2.j;
import android.text.TextUtils;
import android.util.SparseArray;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.details.c;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sum.core.types.NumericEnum;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MetadataTimeHelper {
    private static String TAG = "MetadataTimeHelper";

    public static String getLocalTime(MediaItem mediaItem, long j2, String str) {
        return getLocalTime(mediaItem, j2, false, str);
    }

    public static String getLocalTimeFromMetadata(MediaItem mediaItem, long j2, String str) {
        return getLocalTime(mediaItem, j2, true, str);
    }

    public static long getLocalTimeZone(MediaItem mediaItem) {
        SparseArray<String> sparseArray;
        Long parseTimezoneOffset;
        String str = null;
        if (mediaItem == null) {
            sparseArray = null;
        } else {
            sparseArray = VideoPropData.of(mediaItem).videoMetadata;
        }
        if (sparseArray != null && sparseArray.size() > 0) {
            str = sparseArray.get(1032);
        }
        if (!(!TextUtils.isEmpty(str) || mediaItem == null || mediaItem.getPath() == null)) {
            try {
                byte[] data = SeApiCompat.getSefFileCompat().getData(new File(mediaItem.getPath()), SefInfo.VIDEO_EDITED_UTC_OFFSET.keyName);
                if (data != null && data.length > 0) {
                    str = new String(data);
                }
            } catch (IOException unused) {
            }
        }
        if (TextUtils.isEmpty(str) || (parseTimezoneOffset = TimeUtil.parseTimezoneOffset(str)) == null) {
            return -1;
        }
        return parseTimezoneOffset.longValue();
    }

    public static boolean hasVideoTimeZoneOffset(MediaItem mediaItem) {
        if (mediaItem == null) {
            return false;
        }
        SparseArray<String> sparseArray = VideoPropData.of(mediaItem).videoMetadata;
        if (sparseArray == null) {
            sparseArray = new SparseArray<>();
            HashMap<Integer, String> extras = MetadataManager.getInstance().lambda$preload$0(mediaItem).getExtras();
            if (!extras.isEmpty()) {
                extras.forEach(new c(sparseArray));
            }
        }
        if (!TextUtils.isEmpty(sparseArray.get(1032))) {
            return true;
        }
        return SeApiCompat.getSefFileCompat().hasVideoTimeZoneOffset(mediaItem.getPath());
    }

    public static String updateVideoTimeZoneOffset(MediaItem mediaItem) {
        if (!mediaItem.isVideo() || hasVideoTimeZoneOffset(mediaItem)) {
            return null;
        }
        String calcVideoTimezoneOffset = MediaItemUtil.calcVideoTimezoneOffset(mediaItem);
        String str = TAG;
        StringBuilder k = j.k("calcVideoTimeZoneOffset [", calcVideoTimezoneOffset, "] path[");
        k.append(Logger.getEncodedString(mediaItem.getPath()));
        k.append("] localTime=");
        k.append(mediaItem.getDateLocal());
        k.append("] datetaken[");
        k.append(mediaItem.getDateTaken());
        k.append("] fileDur[");
        k.append(mediaItem.getFileDuration());
        k.append("]");
        Log.i(str, k.toString());
        Long parseTimezoneOffset = TimeUtil.parseTimezoneOffset(calcVideoTimezoneOffset);
        if (parseTimezoneOffset != null && (parseTimezoneOffset.longValue() > 50400000 || parseTimezoneOffset.longValue() < -43200000)) {
            calcVideoTimezoneOffset = TimeUtil.getSystemTimeZoneOffsetTag().replace(NumericEnum.SEP, "");
        }
        SeApiCompat.getSefFileCompat().addData(new File(mediaItem.getPath()), SefInfo.VIDEO_EDITED_UTC_OFFSET, calcVideoTimezoneOffset.getBytes());
        return calcVideoTimezoneOffset;
    }

    private static String getLocalTime(MediaItem mediaItem, long j2, boolean z, String str) {
        long j3;
        if (mediaItem == null || j2 == -1) {
            return "";
        }
        long dateLocal = mediaItem.getDateLocal();
        if (!TextUtils.isEmpty(str)) {
            j3 = TimeUtil.parseTimezoneOffset(str).longValue();
        } else {
            j3 = TimeUtil.isValidLocalTime(dateLocal) ? dateLocal - mediaItem.getDateTaken() : -1;
            if (j3 == -1) {
                j3 = getLocalTimeZone(mediaItem);
            }
        }
        if (j3 == -1 && z) {
            SparseArray sparseArray = new SparseArray();
            HashMap<Integer, String> extras = MetadataManager.getInstance().lambda$preload$0(mediaItem).getExtras();
            if (!extras.isEmpty()) {
                extras.forEach(new c(sparseArray));
                String str2 = (String) sparseArray.get(1032);
                if (!TextUtils.isEmpty(str2)) {
                    j3 = TimeUtil.parseTimezoneOffset(str2).longValue();
                }
            }
        }
        if (j3 == -1) {
            return "";
        }
        String exifDateTime = TimeUtil.getExifDateTime((TimeUtil.getSystemTimeZoneOffset() + j2) - j3);
        String str3 = TAG;
        Log.i(str3, "getLocalTime = " + exifDateTime + ", timeZoneOffset = " + j3);
        return exifDateTime;
    }
}
