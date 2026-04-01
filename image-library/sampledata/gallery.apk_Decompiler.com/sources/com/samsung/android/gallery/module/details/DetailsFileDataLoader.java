package com.samsung.android.gallery.module.details;

import android.util.SparseArray;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.media.DualPhotoManipulator;
import com.samsung.android.gallery.module.media.MetadataManager;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DetailsFileDataLoader extends DetailsDataLoader {
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003f */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0045 A[Catch:{ Exception -> 0x005c }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void loadClippedData(com.samsung.android.gallery.module.data.MediaItem r5, com.samsung.android.gallery.module.details.DetailsDataLoadCallback r6) {
        /*
            boolean r0 = com.samsung.android.gallery.module.details.DetailsUtil.supportFileLoad(r5)
            if (r0 == 0) goto L_0x0014
            com.samsung.android.gallery.module.data.DetailsData r0 = com.samsung.android.gallery.module.data.DetailsData.of(r5)
            java.lang.String r0 = r0.capturedPath
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0014
            r0 = 1
            goto L_0x0015
        L_0x0014:
            r0 = 0
        L_0x0015:
            java.lang.String r1 = "loadClippedData"
            com.samsung.android.gallery.support.utils.ThreadUtil.assertBgThread(r1)
            if (r0 != 0) goto L_0x001d
            goto L_0x005c
        L_0x001d:
            com.samsung.android.gallery.module.data.DetailsData r0 = com.samsung.android.gallery.module.data.DetailsData.of(r5)
            java.lang.String r0 = r0.capturedPath
            com.samsung.android.gallery.module.data.DetailsData r1 = com.samsung.android.gallery.module.data.DetailsData.of(r5)     // Catch:{ Exception -> 0x003f }
            android.util.Pair r2 = new android.util.Pair     // Catch:{ Exception -> 0x003f }
            java.net.URL r3 = new java.net.URL     // Catch:{ Exception -> 0x003f }
            r3.<init>(r0)     // Catch:{ Exception -> 0x003f }
            java.lang.String r3 = r3.getHost()     // Catch:{ Exception -> 0x003f }
            java.lang.Boolean r4 = java.lang.Boolean.FALSE     // Catch:{ Exception -> 0x003f }
            r2.<init>(r3, r4)     // Catch:{ Exception -> 0x003f }
            r1.setClippedData(r2)     // Catch:{ Exception -> 0x003f }
            com.samsung.android.gallery.module.details.DetailsUpdateKey r1 = com.samsung.android.gallery.module.details.DetailsUpdateKey.CLIPPED_DATA     // Catch:{ Exception -> 0x003f }
            com.samsung.android.gallery.module.details.DetailsDataLoader.notify((com.samsung.android.gallery.module.details.DetailsDataLoadCallback) r6, (com.samsung.android.gallery.module.data.MediaItem) r5, (com.samsung.android.gallery.module.details.DetailsUpdateKey) r1)     // Catch:{ Exception -> 0x003f }
        L_0x003f:
            boolean r1 = com.samsung.android.gallery.support.utils.FileUtils.exists(r0)     // Catch:{ Exception -> 0x005c }
            if (r1 == 0) goto L_0x005c
            com.samsung.android.gallery.module.data.DetailsData r1 = com.samsung.android.gallery.module.data.DetailsData.of(r5)     // Catch:{ Exception -> 0x005c }
            android.util.Pair r2 = new android.util.Pair     // Catch:{ Exception -> 0x005c }
            java.lang.String r0 = com.samsung.android.gallery.support.utils.FileUtils.getNameFromPath(r0)     // Catch:{ Exception -> 0x005c }
            java.lang.Boolean r3 = java.lang.Boolean.TRUE     // Catch:{ Exception -> 0x005c }
            r2.<init>(r0, r3)     // Catch:{ Exception -> 0x005c }
            r1.setClippedData(r2)     // Catch:{ Exception -> 0x005c }
            com.samsung.android.gallery.module.details.DetailsUpdateKey r0 = com.samsung.android.gallery.module.details.DetailsUpdateKey.CLIPPED_DATA     // Catch:{ Exception -> 0x005c }
            com.samsung.android.gallery.module.details.DetailsDataLoader.notify((com.samsung.android.gallery.module.details.DetailsDataLoadCallback) r6, (com.samsung.android.gallery.module.data.MediaItem) r5, (com.samsung.android.gallery.module.details.DetailsUpdateKey) r0)     // Catch:{ Exception -> 0x005c }
        L_0x005c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.details.DetailsFileDataLoader.loadClippedData(com.samsung.android.gallery.module.data.MediaItem, com.samsung.android.gallery.module.details.DetailsDataLoadCallback):void");
    }

    public static void loadDualPhotoManipulator(MediaItem mediaItem, DetailsDataLoadCallback detailsDataLoadCallback) {
        boolean z;
        if (!DetailsUtil.supportFileLoad(mediaItem) || !PocFeatures.isEnabled(PocFeatures.MoreInfoExif) || (!mediaItem.isShotMode("live_focus") && !mediaItem.isShotMode("Dual capture"))) {
            z = false;
        } else {
            z = true;
        }
        ThreadUtil.assertBgThread("loadDualPhotoManipulator");
        if (z) {
            new DualPhotoManipulator().init(mediaItem.getPath());
            DetailsDataLoader.notify(detailsDataLoadCallback, mediaItem, DetailsUpdateKey.DUAL_PHOTO_MANIPULATOR);
        }
    }

    private static void loadExifTagInternal(MediaItem mediaItem) {
        if (!mediaItem.isVideo() && mediaItem.getExifTag() == null) {
            MediaItemBuilder.updateExif(mediaItem, MetadataManager.getInstance().loadExif(mediaItem));
            Log.d("DetailsFileDataLoader", "exifTag load done " + mediaItem.getFileId());
        }
    }

    public static void loadFile(MediaItem mediaItem, DetailsDataLoadCallback detailsDataLoadCallback) {
        boolean z;
        ThreadUtil.assertBgThread("loadSef/Exif/metadata");
        if (DetailsUtil.supportFileDataLoad(mediaItem)) {
            DetailsData.parseSefDataIfAbsent(mediaItem);
            loadExifTagInternal(mediaItem);
            loadMetadataInternal(mediaItem);
            DetailsDataLoader.notify(detailsDataLoadCallback, mediaItem, DetailsUpdateKey.FILE_DATA);
            return;
        }
        if (mediaItem != null) {
            z = true;
        } else {
            z = false;
        }
        Log.e((CharSequence) "DetailsFileDataLoader", "load failed", Boolean.valueOf(z));
    }

    private static void loadMetadataInternal(MediaItem mediaItem) {
        if (mediaItem.isVideo()) {
            VideoPropData of2 = VideoPropData.of(mediaItem);
            if (of2.videoMetadata == null) {
                SparseArray<String> sparseArray = new SparseArray<>();
                HashMap<Integer, String> extras = MetadataManager.getInstance().lambda$preload$0(mediaItem).getExtras();
                if (!extras.isEmpty()) {
                    extras.forEach(new c(sparseArray));
                }
                of2.videoMetadata = sparseArray;
                Log.d("DetailsFileDataLoader", "metadata load done " + mediaItem.getFileId() + "");
            }
        }
    }
}
