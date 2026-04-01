package com.samsung.android.sdk.scs.base.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.scs.ai.sdkcommon.feature.FeatureConfig;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FeatureHelper {
    private static final String TAG = "ScsApi@FeatureHelper";

    private static FeatureConfig getFeatureConfig(String str) {
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString(FeatureConfig.JSON_KEY_APP_VERSION, "");
        JSONObject optJSONObject = jSONObject.optJSONObject("features");
        HashMap hashMap = new HashMap();
        if (optJSONObject != null) {
            Iterator<String> keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, Integer.valueOf(optJSONObject.optInt(next, 0)));
            }
        }
        return new FeatureConfig(optString, hashMap);
    }

    public static int getFeatureVersionFromProvider(Context context, Uri uri, String str) {
        Log.d(TAG, "getFeatureVersionFromProvider()");
        Bundle bundle = null;
        try {
            bundle = context.getContentResolver().call(uri, "featureSupportRequest", str, (Bundle) null);
        } catch (Exception e) {
            Log.e(TAG, "checkScsFeature(). " + e.getMessage());
        }
        if (bundle != null) {
            return bundle.getInt("constVersion");
        }
        Log.e(TAG, "checkScsFeature(). retBundle == null!!!");
        return -2;
    }

    public static int getFeatureVersionFromSettings(Context context, String str, String str2, String str3) {
        int i2;
        StringBuilder q = C0086a.q("getFeatureVersionFromSettings(), serviceApp : ", str, ", feature : ", str2, ", settingKey : ");
        q.append(str3);
        Log.d(TAG, q.toString());
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 128);
            try {
                String string = Settings.Global.getString(context.getContentResolver(), str3);
                if (TextUtils.isEmpty(string)) {
                    return -2;
                }
                try {
                    FeatureConfig featureConfig = getFeatureConfig(string);
                    if (packageInfo.versionName.compareTo(featureConfig.getAppVersion()) != 0) {
                        return -2;
                    }
                    Integer orDefault = featureConfig.getFeatures().getOrDefault(str2, -2);
                    if (orDefault != null) {
                        i2 = orDefault.intValue();
                    } else {
                        i2 = -2;
                    }
                    Log.d(TAG, "Get feature version from global settings. feature : " + str2 + ", version : " + i2);
                    return i2;
                } catch (Exception e) {
                    Log.d(TAG, "Unexpected behaviour when reading global settings", e);
                    return -2;
                }
            } catch (Exception e7) {
                Log.e(TAG, "Failed to getString from global settings.", e7);
                return -2;
            }
        } catch (PackageManager.NameNotFoundException e8) {
            Log.e(TAG, "Failed to get package info.", e8);
            return -2;
        }
    }
}
