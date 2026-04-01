package com.samsung.android.gallery.plugins.portrait;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import com.samsung.android.sivs.ai.sdkcommon.tts.TextToSpeechConst;
import com.samsung.scsp.media.file.FileApiContract;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoWallpaperPackage {
    private static final SparseArray<AnalyticsDetail> SA_LOG_DATA = new SparseArray<AnalyticsDetail>() {
        {
            set(1000, AnalyticsDetail.EVENT_DETAIL_SET_AS_LOCK_SCREEN);
            AnalyticsDetail analyticsDetail = AnalyticsDetail.EVENT_DETAIL_SET_AS_COVER_SCREEN;
            set(TextToSpeechConst.MAX_SPEECH_INPUT, analyticsDetail);
            set(4500, analyticsDetail);
            set(Encode.BitRate.VIDEO_HD_BITRATE, AnalyticsDetail.EVENT_DETAIL_SET_AS_CALL_BACKGROUND);
        }
    };
    private final String activity;
    private final Intent intent;
    private final CharSequence label;
    private final String packageName;
    private final int priority;

    public VideoWallpaperPackage(Intent intent2, String str, String str2, CharSequence charSequence, int i2) {
        this.intent = intent2;
        this.packageName = str;
        this.activity = str2;
        this.label = charSequence;
        this.priority = i2;
        Log.d("WallpaperPackage", "VideoWallpaperPackage : " + str + ArcCommonLog.TAG_COMMA + charSequence + ArcCommonLog.TAG_COMMA + i2 + ArcCommonLog.TAG_COMMA + intent2);
    }

    private String getEventDetails() {
        AnalyticsDetail analyticsDetail = SA_LOG_DATA.get(getOrder());
        if (analyticsDetail != null) {
            return analyticsDetail.toString();
        }
        return "";
    }

    private boolean isProperDex(String str) {
        return this.packageName.equals(str);
    }

    private void postAnalyticsLog() {
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_DETAIL_VIEW_3D_PHOTO.toString(), AnalyticsEventId.EVENT_DETAIL_VIEW_SET_AS_WALLPAPER.toString(), getEventDetails());
    }

    public String getLabel() {
        return this.label.toString();
    }

    public int getOrder() {
        return this.priority;
    }

    public boolean isClearCover() {
        if (getOrder() == 5000) {
            return true;
        }
        return false;
    }

    public boolean isCover() {
        if (getOrder() == 4000) {
            return true;
        }
        return false;
    }

    public boolean isDualDex() {
        return isProperDex("com.sec.android.app.desktoplauncher");
    }

    public boolean isLock() {
        if (getOrder() == 1000) {
            return true;
        }
        return false;
    }

    public boolean isStandAloneDex() {
        return isProperDex("com.samsung.android.app.dressroom");
    }

    public boolean isWatchFaceLarge() {
        if (getOrder() == 4500) {
            return true;
        }
        return false;
    }

    public void onExecuteCmd(Context context, String str) {
        this.intent.putExtra(FileApiContract.Parameter.PATH, str);
        this.intent.putExtra("from-ThemeStore", false);
        this.intent.putExtra("from", "gallery");
        this.intent.putExtra("order", getOrder());
        try {
            this.intent.setClassName(this.packageName, this.activity);
            this.intent.addFlags(1);
            Log.i("WallpaperPackage", "startActivity : " + this.intent);
            context.startActivity(this.intent);
            postAnalyticsLog();
        } catch (ActivityNotFoundException unused) {
            Log.e("WallpaperPackage", "activity not found : " + this.packageName);
        }
    }
}
