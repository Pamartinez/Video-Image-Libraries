package com.samsung.android.gallery.module.settings;

import android.content.ContentProviderClient;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import com.samsung.android.app.sdk.deepsky.contract.DeepSkyMethod;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SmartSuggestionsSettingApi {
    private static volatile SmartSuggestionsSettingApi sInstance;
    private Boolean mEnable;

    public static SmartSuggestionsSettingApi getInstance() {
        if (sInstance == null) {
            synchronized (SmartSuggestionsSettingApi.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new SmartSuggestionsSettingApi();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    private boolean loadAppPdcAvailability() {
        ContentProviderClient acquireUnstableContentProviderClient;
        long currentTimeMillis = System.currentTimeMillis();
        if (!Features.isEnabled(Features.SUPPORT_SMART_SUGGESTION_APP_SUB_MENU)) {
            Log.i("SmartSuggestionsSettingApi", "loadAppPdcAvailability, not support");
            return false;
        }
        Boolean bool = null;
        try {
            acquireUnstableContentProviderClient = AppResources.getAppContext().getContentResolver().acquireUnstableContentProviderClient(Uri.parse(DeepSkyMethod.URI));
            if (acquireUnstableContentProviderClient != null) {
                Bundle bundle = new Bundle();
                bundle.putString(DeepSkyMethod.APP_PACKAGE_NAME_PROPERTY, "com.sec.android.gallery3d");
                Bundle call = acquireUnstableContentProviderClient.call(DeepSkyMethod.GET_APP_RECOMMENDATION_AVAILABILITY, (String) null, bundle);
                if (call != null) {
                    bool = Boolean.valueOf(call.getBoolean(DeepSkyMethod.APP_RECOMMENDATION_AVAILABILITY_PROPERTY));
                }
            }
            if (acquireUnstableContentProviderClient != null) {
                acquireUnstableContentProviderClient.close();
            }
        } catch (RemoteException e) {
            Log.e("SmartSuggestionsSettingApi", "loadAppPdcAvailability failed. e=" + e.getMessage());
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        Log.i("SmartSuggestionsSettingApi", "loadAppPdcAvailability ", bool, Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
        throw th;
    }

    public void clearPdc() {
        this.mEnable = null;
    }

    public boolean isAppPdcAvailability() {
        if (this.mEnable == null) {
            this.mEnable = Boolean.valueOf(loadAppPdcAvailability());
        }
        return this.mEnable.booleanValue();
    }
}
