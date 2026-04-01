package com.samsung.android.gallery.module.details;

import android.text.TextUtils;
import com.samsung.android.gallery.module.c2pa.C2paInfo;
import com.samsung.android.gallery.module.c2pa.C2paWrapper;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DetailsC2paLoader extends DetailsDataLoader {
    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadC2pa$0(DetailsDataLoadCallback detailsDataLoadCallback, MediaItem mediaItem, C2paInfo c2paInfo) {
        if (!c2paInfo.isEmpty()) {
            DetailsDataLoader.notify(detailsDataLoadCallback, mediaItem, new DetailsLoadResult(DetailsUpdateKey.C2PA, c2paInfo));
        } else if (mediaItem.hasSefFileTypes(3473)) {
            DetailsDataLoader.notify(detailsDataLoadCallback, mediaItem, new DetailsLoadResult(DetailsUpdateKey.C2PA, null));
        }
    }

    public static void loadC2pa(MediaItem mediaItem, C2paWrapper c2paWrapper, DetailsDataLoadCallback detailsDataLoadCallback) {
        ThreadUtil.assertBgThread("loadC2pa");
        if (mediaItem != null && support(mediaItem) && c2paWrapper != null) {
            c2paWrapper.extract(mediaItem, new a(detailsDataLoadCallback, mediaItem));
        }
    }

    private static boolean support(MediaItem mediaItem) {
        if (!Features.isEnabled(Features.SUPPORT_C2PA) || !DetailsUtil.supportFileLoad(mediaItem) || TextUtils.isEmpty(mediaItem.getPath())) {
            return false;
        }
        if (!PocFeatures.isEnabled(PocFeatures.C2paSecMp) || DetailsData.of(mediaItem).hasC2pa) {
            return true;
        }
        return false;
    }
}
