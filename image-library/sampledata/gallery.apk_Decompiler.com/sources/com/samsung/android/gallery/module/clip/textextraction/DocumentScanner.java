package com.samsung.android.gallery.module.clip.textextraction;

import android.graphics.Bitmap;
import android.graphics.PointF;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import vizinsight.atl.gallery_scan.VZGalleryScan;
import vizinsight.atl.gallery_scan.VZImageDecoder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DocumentScanner {
    private static final boolean SUPPORT = Features.isEnabled(Features.SUPP0RT_DOCUMENT_SCAN);
    private static final boolean SUPPORT_VEX_DOCUMENT_SCAN = Features.isEnabled(Features.SUPPORT_VEX_DOCUMENT_SCAN);
    private static final ConcurrentHashMap<Long, ScanData> mCache = new ConcurrentHashMap<>();
    private static final DocumentScanner sInstance = new DocumentScanner();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ScanData {
        /* access modifiers changed from: private */
        public final float[] mCorners;
        /* access modifiers changed from: private */
        public final boolean mHasData;

        public ScanData(boolean z, float[] fArr) {
            this.mHasData = z;
            this.mCorners = fArr;
        }
    }

    private DocumentScanner() {
    }

    private ArrayList<PointF> convert(float[] fArr) {
        ArrayList<PointF> arrayList = new ArrayList<>();
        int i2 = 0;
        while (true) {
            int i7 = i2 + 1;
            if (i7 >= fArr.length) {
                return arrayList;
            }
            arrayList.add(new PointF(fArr[i2], fArr[i7]));
            i2 += 2;
        }
    }

    public static DocumentScanner getInstance() {
        return sInstance;
    }

    private boolean isInvalidBitmap(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return true;
        }
        return false;
    }

    private boolean isInvalidItem(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.isCloudOnly()) {
            return true;
        }
        if (mediaItem.isHeif()) {
            if (SdkConfig.lessThan(SdkConfig.SEM.V)) {
                return true;
            }
            return false;
        } else if (!mediaItem.isJpeg()) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean tooBigToScan(MediaItem mediaItem) {
        boolean z;
        int i2;
        int i7;
        if (mediaItem.getWidth() > mediaItem.getHeight()) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            i2 = mediaItem.getWidth();
        } else {
            i2 = mediaItem.getHeight();
        }
        if (z) {
            i7 = mediaItem.getHeight();
        } else {
            i7 = mediaItem.getWidth();
        }
        if (i2 > 4000 || i7 > 3000) {
            return true;
        }
        return false;
    }

    public void findDocument(MediaItem mediaItem, Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        if (SUPPORT && !isInvalidBitmap(bitmap2) && !isInvalidItem(mediaItem)) {
            if (SUPPORT_VEX_DOCUMENT_SCAN || !tooBigToScan(mediaItem)) {
                long fileId = mediaItem.getFileId();
                ConcurrentHashMap<Long, ScanData> concurrentHashMap = mCache;
                if (concurrentHashMap.get(Long.valueOf(fileId)) == null) {
                    try {
                        long currentTimeMillis = System.currentTimeMillis();
                        float[] fArr = new float[8];
                        VZGalleryScan vZGalleryScan = new VZGalleryScan(3, "/vendor/etc/saiv/image_understanding/db/");
                        boolean execute = vZGalleryScan.execute(VZImageDecoder.decodeImage(bitmap2, 2), bitmap2.getWidth(), bitmap2.getHeight(), 0, 2, fArr);
                        vZGalleryScan.release();
                        concurrentHashMap.put(Long.valueOf(fileId), new ScanData(execute, fArr));
                        Log.d("DocumentScanner", "document scanned", Long.valueOf(fileId), Boolean.valueOf(execute), Float.valueOf(fArr[0]), Float.valueOf(fArr[1]), Float.valueOf(fArr[2]), Float.valueOf(fArr[3]), Float.valueOf(fArr[4]), Float.valueOf(fArr[5]), Float.valueOf(fArr[6]), Float.valueOf(fArr[7]), Logger.vt(currentTimeMillis));
                    } catch (Error | Exception e) {
                        Log.e((CharSequence) "DocumentScanner", "unable to scan document", e);
                    }
                }
            }
        }
    }

    public ArrayList<PointF> getVertexList(MediaItem mediaItem) {
        ScanData scanData;
        if (!SUPPORT || mediaItem == null || (scanData = mCache.get(Long.valueOf(mediaItem.getFileId()))) == null || !scanData.mHasData) {
            return null;
        }
        return convert(scanData.mCorners);
    }

    public boolean hasDocument(MediaItem mediaItem) {
        ScanData scanData;
        if (!SUPPORT || mediaItem == null || (scanData = mCache.get(Long.valueOf(mediaItem.getFileId()))) == null || !scanData.mHasData) {
            return false;
        }
        return true;
    }

    public void resetCache(long j2) {
        if (SUPPORT && j2 >= 0) {
            mCache.remove(Long.valueOf(j2));
            Log.d("DocumentScanner", "resetCache", Long.valueOf(j2));
        }
    }

    public boolean supportDocumentScan(MediaItem mediaItem) {
        if (!SUPPORT || isInvalidItem(mediaItem)) {
            return false;
        }
        if (SUPPORT_VEX_DOCUMENT_SCAN) {
            return true;
        }
        ScanData scanData = mCache.get(Long.valueOf(mediaItem.getFileId()));
        if (scanData == null || !scanData.mHasData) {
            return false;
        }
        return true;
    }
}
