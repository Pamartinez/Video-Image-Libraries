package com.samsung.android.gallery.app.controller.abstraction;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.SparseArray;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import com.samsung.android.sivs.ai.sdkcommon.tts.TextToSpeechConst;
import com.samsung.scsp.media.file.FileApiContract;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WallpaperPackage {
    private static final SparseArray<AnalyticsDetail> SA_LOG_DATA = new SparseArray<AnalyticsDetail>() {
        {
            set(1000, AnalyticsDetail.EVENT_DETAIL_SET_AS_LOCK_SCREEN);
            set(2000, AnalyticsDetail.EVENT_DETAIL_SET_AS_HOME_SCREEN);
            set(3000, AnalyticsDetail.EVENT_DETAIL_SET_AS_LOCK_AND_HOME);
            AnalyticsDetail analyticsDetail = AnalyticsDetail.EVENT_DETAIL_SET_AS_COVER_SCREEN;
            set(TextToSpeechConst.MAX_SPEECH_INPUT, analyticsDetail);
            set(4500, analyticsDetail);
            set(5000, analyticsDetail);
            set(6000, AnalyticsDetail.EVENT_DETAIL_SET_AS_ALWAYS_ON_DISPLAY);
            set(7000, AnalyticsDetail.EVENT_DETAIL_SET_AS_WATCH_FACE);
            set(Encode.BitRate.VIDEO_HD_BITRATE, AnalyticsDetail.EVENT_DETAIL_SET_AS_CALL_BACKGROUND);
            set(9000, AnalyticsDetail.EVENT_DETAIL_SET_AS_ALARM);
            set(10000, AnalyticsDetail.EVENT_DETAIL_SET_AS_CALENDAR);
            set(11000, AnalyticsDetail.EVENT_DETAIL_SET_AS_REMINDER);
        }
    };
    String activity;
    Intent intent;
    CharSequence label;
    String packageName;
    int priority;

    public WallpaperPackage(Intent intent2, String str, String str2, CharSequence charSequence, int i2) {
        this.intent = intent2;
        this.packageName = str;
        this.activity = str2;
        this.label = charSequence;
        this.priority = i2;
        Log.d("WallpaperPackage", "WallpaperPackage : " + str + ArcCommonLog.TAG_COMMA + charSequence + ArcCommonLog.TAG_COMMA + i2 + ArcCommonLog.TAG_COMMA + intent2);
    }

    private Uri getUri(Context context, MediaItem mediaItem) {
        if (!PocFeatures.DUAL_PHOTO_PREVIEW || !mediaItem.isStream()) {
            return ContentUri.getUri(mediaItem);
        }
        return ContentUri.getStreamUri(context, mediaItem);
    }

    private boolean isFromThemeStore(Blackboard blackboard) {
        LaunchIntent launchIntent = (LaunchIntent) blackboard.read("data://launch_intent");
        if (launchIntent == null || !launchIntent.isFromThemeStore()) {
            return false;
        }
        return true;
    }

    private boolean isProperDex(String str) {
        return this.packageName.equals(str);
    }

    public AnalyticsEventId getEvent() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_SET_AS_WALLPAPER;
    }

    public String getEventDetails() {
        AnalyticsDetail analyticsDetail = SA_LOG_DATA.get(getOrder());
        if (analyticsDetail != null) {
            return analyticsDetail.toString();
        }
        return "";
    }

    public String getLabel() {
        return this.label.toString();
    }

    public int getOrder() {
        return this.priority;
    }

    public boolean isAod() {
        if (getOrder() == 6000) {
            return true;
        }
        return false;
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

    public boolean isHomeOrLock() {
        int order = getOrder();
        if (order == 2000 || order == 1000 || order == 3000) {
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

    public void onExecuteCmd(EventContext eventContext, MediaItem[] mediaItemArr) {
        if (mediaItemArr.length > 1) {
            ArrayList arrayList = new ArrayList();
            for (MediaItem mediaItem : mediaItemArr) {
                if (mediaItem == null || ((mediaItem.isLocal() && mediaItem.getMediaId() <= 0) || ContentUri.getUri(mediaItem) == null)) {
                    Log.e("WallpaperPackage", "item or uri is null");
                } else if (arrayList.size() >= 100) {
                    break;
                } else {
                    arrayList.add(getUri(eventContext.getContext(), mediaItem));
                }
            }
            this.intent.putExtra("selectedItems", arrayList);
        } else {
            MediaItem mediaItem2 = mediaItemArr[0];
            this.intent.setDataAndType(getUri(eventContext.getContext(), mediaItem2), mediaItem2.getMimeType());
            this.intent.putExtra(FileApiContract.Parameter.PATH, mediaItem2.getPath());
        }
        boolean isFromThemeStore = isFromThemeStore(eventContext.getBlackboard());
        this.intent.putExtra("from-ThemeStore", isFromThemeStore);
        this.intent.putExtra("from", "gallery");
        this.intent.putExtra("order", getOrder());
        try {
            this.intent.setClassName(this.packageName, this.activity);
            this.intent.addFlags(1);
            Log.i("WallpaperPackage", "startActivity : " + this.intent);
            if (isFromThemeStore) {
                eventContext.getActivity().startActivityForResult(this.intent, 2309);
            } else {
                eventContext.getContext().startActivity(this.intent);
            }
        } catch (ActivityNotFoundException unused) {
            Log.e("WallpaperPackage", "activity not found : " + this.packageName);
        } catch (SecurityException e) {
            Log.e("WallpaperPackage", "unexpected error" + e.getMessage());
            new InternalException("WallpaperPackage", "unexpected error " + e).post();
        }
    }
}
