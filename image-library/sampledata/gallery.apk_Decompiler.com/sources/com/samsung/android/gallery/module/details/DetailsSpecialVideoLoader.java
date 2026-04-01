package com.samsung.android.gallery.module.details;

import android.content.Context;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.DynamicViewUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dynamicview.DynamicViewInfo;
import com.samsung.android.gallery.module.superslow.SuperSlowUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DetailsSpecialVideoLoader extends DetailsDataLoader {
    public static void loadDynamicVideo(MediaItem mediaItem, DetailsDataLoadCallback detailsDataLoadCallback) {
        int i2;
        ThreadUtil.assertBgThread("loadDynamicVideo");
        if (support(mediaItem) && DynamicViewUtils.supportDynamic(mediaItem)) {
            DynamicViewInfo dynamicViewInfo = DynamicViewUtils.getDynamicViewInfo(mediaItem);
            if (MediaHelper.isPortraitVideo(mediaItem.getPath())) {
                i2 = 90;
            } else {
                i2 = 0;
            }
            Context appContext = AppResources.getAppContext();
            ArrayList arrayList = new ArrayList();
            if (!(appContext == null || dynamicViewInfo == null || dynamicViewInfo.getClipCount() <= 0)) {
                arrayList.add(DynamicViewUtils.createDynamicItem(mediaItem, dynamicViewInfo, i2, 0));
                arrayList.add(DynamicViewUtils.createDynamicItem(mediaItem, dynamicViewInfo, i2, 1));
                arrayList.add(DynamicViewUtils.createDynamicItem(mediaItem, dynamicViewInfo, i2, 3));
                if (dynamicViewInfo.getClipCount() > 1 && dynamicViewInfo.bestClipAvailable()) {
                    arrayList.add(DynamicViewUtils.createDynamicItem(mediaItem, dynamicViewInfo, i2, 2));
                }
                ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
                ThumbnailLoader.getInstance().loadThumbnailSync((ThumbnailInterface) arrayList.get(0), thumbKind);
                ThumbnailLoader.getInstance().loadThumbnailSync((ThumbnailInterface) arrayList.get(1), thumbKind);
            }
            DetailsData.of(mediaItem).setDynamic(arrayList);
            DetailsDataLoader.notify(detailsDataLoadCallback, mediaItem, DetailsUpdateKey.DYNAMIC_VIDEO);
        }
    }

    public static void loadSuperSlow(MediaItem mediaItem, DetailsDataLoadCallback detailsDataLoadCallback) {
        int i2;
        ThreadUtil.assertBgThread("loadSuperSlow");
        Context appContext = AppResources.getAppContext();
        if (support(mediaItem) && appContext != null && SuperSlowUtils.isSuperSlow(mediaItem)) {
            if (MediaHelper.isPortraitVideo(mediaItem.getPath())) {
                i2 = 90;
            } else {
                i2 = 0;
            }
            long superSlowStartTime = (long) MediaHelper.getSuperSlowStartTime(mediaItem.getPath());
            ArrayList arrayList = new ArrayList();
            arrayList.add(SuperSlowUtils.createSuperSlowItem(mediaItem, new SuperSlowUtils.SuperSlowInfo(SuperSlowUtils.SuperSlowType.Forward, i2, superSlowStartTime)));
            arrayList.add(SuperSlowUtils.createSuperSlowItem(mediaItem, new SuperSlowUtils.SuperSlowInfo(SuperSlowUtils.SuperSlowType.Reverse, i2, superSlowStartTime)));
            arrayList.add(SuperSlowUtils.createSuperSlowItem(mediaItem, new SuperSlowUtils.SuperSlowInfo(SuperSlowUtils.SuperSlowType.ForwardAndReverse, i2, superSlowStartTime)));
            ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
            ThumbnailLoader.getInstance().loadThumbnailSync((ThumbnailInterface) arrayList.get(0), thumbKind);
            ThumbnailLoader.getInstance().loadThumbnailSync((ThumbnailInterface) arrayList.get(1), thumbKind);
            DetailsData.of(mediaItem).setSuperSlow(arrayList);
            DetailsDataLoader.notify(detailsDataLoadCallback, mediaItem, DetailsUpdateKey.SUPER_SLOW);
        }
    }

    private static boolean support(MediaItem mediaItem) {
        if (!DetailsUtil.supportDbLoad(mediaItem) || PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL || !mediaItem.isVideo() || TextUtils.isEmpty(mediaItem.getPath())) {
            return false;
        }
        return true;
    }
}
