package com.samsung.android.gallery.module.dataset.tables;

import A.a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.tables.RealRatioIndexer;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RealRatioIndexerCompat extends RealRatioIndexer {
    public RealRatioIndexerCompat(RealRatioTable realRatioTable, ConcurrentHashMap<Integer, ClusterItem> concurrentHashMap, int i2, int i7) {
        super(realRatioTable, concurrentHashMap, i2, i7);
    }

    public boolean isLastItem() {
        if (this.mDataPosition == this.mLoadedDataCount - 1) {
            return true;
        }
        return false;
    }

    public boolean loadOriginalWidth(int i2) {
        int i7;
        long currentTimeMillis = System.currentTimeMillis();
        if (i2 > 0) {
            i7 = this.mRealRatioTable.getLoadedCount();
        } else {
            i7 = 0;
        }
        if (i2 == 0) {
            Log.d(this.TAG, "RealRatio load failed. no data");
            return false;
        }
        int i8 = 0;
        int i10 = 0;
        while (true) {
            if (i8 >= i7) {
                break;
            }
            MediaItem mediaItem = this.mRealRatioTable.get(i8);
            if (mediaItem == null) {
                new InternalException("fail to read media item from real ratio table").post();
                break;
            }
            String widthList = mediaItem.getWidthList();
            if (widthList == null) {
                Log.e(this.TAG, "item count=" + mediaItem.getCount() + ArcCommonLog.TAG_COMMA + mediaItem.dump());
                if (i2 < 120) {
                    Blackboard.publishGlobal("debug://DumpRealRatioData", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
                    new InternalException("fail to read real-ratio width list").post();
                } else {
                    Log.e(this.TAG, "fail to read real-ratio width list");
                }
            } else {
                String[] split = widthList.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                int length = split.length;
                int i11 = 0;
                while (i11 < length) {
                    String str = split[i11];
                    while (!isPicture(i10) && i10 < this.mItemInfo.length) {
                        i10++;
                    }
                    RealRatioIndexer.ItemInfo[] itemInfoArr = this.mItemInfo;
                    if (i10 >= itemInfoArr.length) {
                        break;
                    }
                    itemInfoArr[i10].ratioData = UnsafeCast.toFloat(str, 1.0f);
                    i11++;
                    i10++;
                }
                i8++;
            }
        }
        a.x(a.h(i2, i7, "RealRatio load {count=", ", loadedCount=", "} +"), currentTimeMillis, this.TAG);
        return true;
    }

    public String tag() {
        return "RealRatioIndexerCompat";
    }
}
