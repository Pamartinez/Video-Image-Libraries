package com.samsung.android.gallery.module.media;

import D3.i;
import android.util.Pair;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsDetailKey;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InstantSlowMoPlayLogger {
    private static final HashMap<String, Integer> EXTENSION_LOG_MAP = new HashMap<String, Integer>() {
        {
            put(IFormat.FORMAT_MP4, 0);
            put("m4v", 1);
            put("3gp", 2);
            put("3g2", 3);
            put("wmv", 4);
            put("asf", 5);
            put("avi", 6);
            put("flv", 7);
            put("mkv", 8);
            put("webm", 9);
            put("ts", 10);
            put("mov", 11);
        }
    };
    private ConcurrentHashMap<Long, Holder> mHolderMap = new ConcurrentHashMap<>();
    private String mScreenId;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ErrorType {
        LOW_RESOLUTION(0),
        HIGH_RESOLUTION(1),
        LOW_FPS(2),
        HIGH_FPS(3),
        NO_FPS(4),
        HDR(5),
        ETC(6),
        SUPER_SLOW_MOTION(7),
        MULTI_WINDOW(8),
        Z_FLIP_COVER(9),
        PLAYBACK_SPEED_CHANGED(10),
        NONE(100);
        
        private final int mValue;

        private ErrorType(int i2) {
            this.mValue = i2;
        }

        public static ErrorType find(int i2) {
            switch (i2) {
                case 0:
                    return LOW_RESOLUTION;
                case 1:
                    return HIGH_RESOLUTION;
                case 2:
                    return LOW_FPS;
                case 3:
                    return HIGH_FPS;
                case 4:
                    return NO_FPS;
                case 5:
                    return HDR;
                case 6:
                    return ETC;
                case 7:
                    return SUPER_SLOW_MOTION;
                case 8:
                    return MULTI_WINDOW;
                case 9:
                    return Z_FLIP_COVER;
                default:
                    return NONE;
            }
        }

        public static boolean outOfSpecError(int i2) {
            if (i2 == ETC.mValue || i2 == MULTI_WINDOW.mValue || i2 == Z_FLIP_COVER.mValue || i2 == PLAYBACK_SPEED_CHANGED.mValue) {
                return false;
            }
            return true;
        }

        public int toInt() {
            return this.mValue;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Holder {
        boolean mAudioMute;
        ErrorType mErrorType;
        long mExecutedDuration;
        int mExecutedNumber;

        public /* synthetic */ Holder(int i2) {
            this();
        }

        public void clear() {
            this.mExecutedNumber = 0;
            this.mExecutedDuration = 0;
            this.mAudioMute = true;
        }

        private Holder() {
            this.mErrorType = ErrorType.NONE;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        /* access modifiers changed from: private */
        public static final InstantSlowMoPlayLogger INSTANCE = new InstantSlowMoPlayLogger();
    }

    public static Pair<String, String>[] getCustomDimensions(FileItemInterface fileItemInterface, String str) {
        return new Pair[]{new Pair(AnalyticsDetailKey.REASON.toString(), str), new Pair(AnalyticsDetailKey.EXTENSION.toString(), String.valueOf(EXTENSION_LOG_MAP.getOrDefault(FileUtils.getExtension(fileItemInterface.getPath()).toLowerCase(Locale.getDefault()), 12)))};
    }

    private Holder getHolder(long j2) {
        return this.mHolderMap.computeIfAbsent(Long.valueOf(j2), new i(19));
    }

    public static InstantSlowMoPlayLogger getInstance() {
        return LazyHolder.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Holder lambda$getHolder$0(Long l) {
        return new Holder(0);
    }

    public void clear(long j2) {
        getHolder(j2).clear();
    }

    public void postLogExecuted(long j2) {
        String str;
        Holder holder = getHolder(j2);
        AnalyticsLogger instance = AnalyticsLogger.getInstance();
        String str2 = this.mScreenId;
        String analyticsEventId = AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_INSTANT_SLOW_MO.toString();
        long j3 = holder.mExecutedDuration;
        if (holder.mAudioMute) {
            str = "0";
        } else {
            str = "1";
        }
        instance.postLog(str2, analyticsEventId, j3, str);
    }

    public void postLogExecutedFailed(FileItemInterface fileItemInterface) {
        Holder holder = getHolder(fileItemInterface.getMediaId());
        if (holder.mErrorType != ErrorType.NONE) {
            AnalyticsLogger.getInstance().postCustomDimension(this.mScreenId, AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_INSTANT_SLOW_MO_FAILED.toString(), getCustomDimensions(fileItemInterface, String.valueOf(holder.mErrorType.toInt())));
        }
    }

    public void postLogExecutedNumber(long j2) {
        int i2;
        int i7 = getHolder(j2).mExecutedNumber;
        if (i7 > 0) {
            if (i7 >= 10) {
                i2 = 9;
            } else {
                i2 = i7 - 1;
            }
            AnalyticsLogger.getInstance().postLog(this.mScreenId, AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_INSTANT_SLOW_MO_EXECUTED_NUMBER.toString(), String.valueOf(i2));
        }
    }

    public void setAudioMute(long j2, boolean z) {
        getHolder(j2).mAudioMute = z;
    }

    public void setErrorType(long j2, ErrorType errorType) {
        getHolder(j2).mErrorType = errorType;
    }

    public void setExecutedDuration(long j2, long j3) {
        getHolder(j2).mExecutedDuration = j3;
    }

    public void setExecutedNumber(long j2, int i2) {
        getHolder(j2).mExecutedNumber = i2;
    }

    public void setScreenId(String str) {
        this.mScreenId = str;
    }
}
