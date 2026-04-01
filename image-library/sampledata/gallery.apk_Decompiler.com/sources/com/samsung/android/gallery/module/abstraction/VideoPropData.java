package com.samsung.android.gallery.module.abstraction;

import android.util.SparseArray;
import com.samsung.android.gallery.database.dal.mp.helper.VideoSearchApi;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.utils.Copyable;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoPropData implements Copyable<VideoPropData> {
    private static final String TAG = "VideoPropData";
    static final Function<String, Object> constructor = new g(4);
    public String audioCodec = "";
    public int colorTransfer;
    public int hdr10Video;
    public long[] instantSlowMoExecutedSection;
    public long[] instantSlowMoLastExecutedSection;
    public boolean longExposure;
    public String longExposurePath;
    public ArrayList<Long[]> segmentInfoList;
    public Integer superSlowClipEffect;
    public int[] superSlowTitleRegion;
    public double videoCaptureFrameRate;
    public String videoCodec = "";
    public int videoDurationInPlayer;
    public String videoFrameIds;
    public int videoFrameRate;
    public String videoLensInfo;
    public SparseArray<String> videoMetadata;
    public Integer videoResumePos;
    public long videoThumbnailFrameTime;

    public static String getLongExposurePath(FileItemInterface fileItemInterface) {
        VideoPropData of2 = of(fileItemInterface);
        if (of2.longExposure) {
            return of2.longExposurePath;
        }
        return fileItemInterface.getPath();
    }

    public static int getVideoDuration(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null) {
            return 0;
        }
        int i2 = of(fileItemInterface).videoDurationInPlayer;
        if (i2 > 0) {
            return i2;
        }
        return fileItemInterface.getFileDuration();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$static$0(String str) {
        return new VideoPropData();
    }

    public static VideoPropData of(FileItemInterface fileItemInterface) {
        return (VideoPropData) fileItemInterface.tags().computeIfAbsent(TAG, constructor);
    }

    public ArrayList<Long[]> loadSegmentInfoList() {
        if (this.segmentInfoList == null) {
            this.segmentInfoList = VideoSearchApi.getSegmentInfoList(this.videoFrameIds);
        }
        return this.segmentInfoList;
    }

    public VideoPropData copyOf() {
        try {
            return (VideoPropData) super.clone();
        } catch (CloneNotSupportedException e) {
            Log.e((CharSequence) TAG, "clone failed", (Throwable) e);
            return this;
        }
    }
}
