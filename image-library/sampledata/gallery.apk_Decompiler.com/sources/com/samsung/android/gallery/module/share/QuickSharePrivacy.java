package com.samsung.android.gallery.module.share;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.StorageInfo;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QuickSharePrivacy {
    private static final QuickSharePrivacy sInstance = new QuickSharePrivacy();
    private volatile Boolean mSupported;

    private QuickSharePrivacy() {
    }

    public static QuickSharePrivacy getInstance() {
        return sInstance;
    }

    private boolean isMetaSupported() {
        Bundle applicationMetaData = PackageMonitorCompat.getInstance().getApplicationMetaData("com.samsung.android.app.sharelive");
        String str = null;
        if (applicationMetaData != null) {
            str = applicationMetaData.getString("com.samsung.android.app.sharelive.extend", (String) null);
        }
        if (str == null || !isTransferred(str)) {
            return false;
        }
        return true;
    }

    private boolean isTransferred(String str) {
        try {
            return new JSONObject(str).getBoolean("privacytransfer");
        } catch (Exception e) {
            Log.e((CharSequence) "QuickSharePrivacy", "unable to get meta data ", (Throwable) e);
            return false;
        }
    }

    private void postAnalyticsLog(String str) {
        AnalyticsLogger.getInstance().postLog(str, AnalyticsEventId.EVENT_MENU_QUICK_SHARE_PRIVACY.toString());
    }

    public boolean isSupported() {
        if (this.mSupported == null) {
            this.mSupported = Boolean.valueOf(isMetaSupported());
            Log.d("QuickSharePrivacy", "quick share privacy", this.mSupported);
        }
        return this.mSupported.booleanValue();
    }

    public boolean needTip(MediaItem mediaItem) {
        if (mediaItem.getAlbumID() != StorageInfo.getDefault().buckets().quickShare || !isSupported() || PreferenceCache.QuickSharePrivacyTip.getBoolean()) {
            return false;
        }
        return true;
    }

    public void start(Context context, String str) {
        try {
            context.startActivity(new Intent("com.samsung.android.sharelive.action.QUICK_SHARE_PRIVACY_HISTORY"));
            Log.d("QuickSharePrivacy", "start quick share privacy history");
        } catch (Exception e) {
            Log.e((CharSequence) "QuickSharePrivacy", "start quick share privacy history failed", (Throwable) e);
        } finally {
            postAnalyticsLog(str);
        }
    }
}
