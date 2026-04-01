package com.samsung.android.gallery.module.data;

import A.a;
import A4.C0384t;
import android.database.Cursor;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.module.dynamicview.ClipInfo;
import com.samsung.android.gallery.module.dynamicview.DynamicViewInfo;
import com.samsung.android.gallery.module.dynamicview.DynamicViewParser;
import com.samsung.android.gallery.module.dynamicview.DynamicViewPlayInfo;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DynamicViewUtils {
    public static MediaItem createDynamicItem(MediaItem mediaItem, DynamicViewInfo dynamicViewInfo, int i2, int i7) {
        MediaItem clone = mediaItem.clone();
        clone.resetGroupShotMode();
        clone.setOrientation(i2);
        DynamicViewPlayInfo build = new DynamicViewInfo.PlayInfoBuilder(dynamicViewInfo).setFileDuration(mediaItem.getFileDuration()).setPlayType(i7).build();
        DynamicViewData.of(clone).dynamicViewPlayInfo = build;
        clone.setTitle(build.getTypeString());
        clone.setVideoHighlightTime(Long.valueOf(build.getVideoThumbStartTime()), (Long) null);
        return clone;
    }

    public static DynamicViewInfo getDynamicViewInfo(MediaItem mediaItem) {
        Cursor query;
        long currentTimeMillis = System.currentTimeMillis();
        DynamicViewInfo dynamicViewInfo = null;
        try {
            query = DbCompat.query(DbKey.ALL_PICTURES, new a(mediaItem, 0));
            boolean z = true;
            if (query != null) {
                if (query.moveToNext()) {
                    dynamicViewInfo = DynamicViewParser.parse(query.getBlob(query.getColumnIndex("__dynamicViewInfo")), mediaItem.getFileDuration(), true);
                }
            }
            StringBuilder sb2 = new StringBuilder("info");
            if (dynamicViewInfo == null) {
                z = false;
            }
            sb2.append(Logger.vt(Boolean.valueOf(z), Long.valueOf(currentTimeMillis)));
            Log.d("DynamicViewUtils", sb2.toString());
            if (query == null) {
                return dynamicViewInfo;
            }
            query.close();
            return dynamicViewInfo;
        } catch (Exception e) {
            a.s(e, new StringBuilder("query fail="), "DynamicViewUtils");
            return dynamicViewInfo;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static MediaItem getHighlightBestItem(MediaItem mediaItem) {
        ClipInfo clipInfo;
        if (mediaItem == null || !mediaItem.isVideo() || mediaItem.isCloudOnly()) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        DynamicViewInfo dynamicViewInfo = getDynamicViewInfo(mediaItem);
        if (dynamicViewInfo == null || dynamicViewInfo.getClipCount() < 2) {
            return null;
        }
        List list = (List) dynamicViewInfo.getClipInfoData().stream().sorted(Comparator.comparing(new C0384t(26)).reversed()).collect(Collectors.toList());
        if (!list.isEmpty()) {
            clipInfo = (ClipInfo) list.get(0);
        } else {
            clipInfo = null;
        }
        if (clipInfo == null) {
            return null;
        }
        MediaItem mediaItem2 = new MediaItem();
        mediaItem2.cloneBasicInfo(mediaItem);
        mediaItem2.setVideoHighlightTime(Long.valueOf(((long) clipInfo.clipStartMs) * 1000), Long.valueOf(((long) clipInfo.clipEndMs) * 1000));
        DynamicViewData.of(mediaItem2).dynamicViewInfo = dynamicViewInfo;
        a.A(new Object[]{Integer.valueOf(dynamicViewInfo.getClipCount()), Long.valueOf(currentTimeMillis)}, new StringBuilder("getHighlightItem"), "DynamicViewUtils");
        return mediaItem2;
    }

    public static boolean supportDynamic(MediaItem mediaItem) {
        if (!Features.isEnabled(Features.SUPPORT_DYNAMIC_VIEW) || mediaItem == null || !mediaItem.isLocal() || !mediaItem.isVideo()) {
            return false;
        }
        return true;
    }
}
