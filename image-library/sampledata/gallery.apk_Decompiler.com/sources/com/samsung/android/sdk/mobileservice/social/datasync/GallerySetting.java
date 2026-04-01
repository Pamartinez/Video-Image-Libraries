package com.samsung.android.sdk.mobileservice.social.datasync;

import android.os.Bundle;
import com.samsung.android.sdk.mobileservice.social.datasync.provider.DataSyncContract;
import com.samsung.android.sdk.mobileservice.util.SdkLog;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GallerySetting implements IData {
    public static final String SYNC_SERVICE_NAME = "ds86adcvg5";
    private final String EXTRA_JSON_STRING = "json_string";
    private final String JSON_KEY_SORT_TYPE = DataSyncContract.GallerySettingData.SORT_TYPE;
    private String mSortType;

    public GallerySetting() {
    }

    private void fromJsonString(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.isNull(DataSyncContract.GallerySettingData.SORT_TYPE)) {
                this.mSortType = jSONObject.getString(DataSyncContract.GallerySettingData.SORT_TYPE);
            }
        } catch (JSONException e) {
            SdkLog.s(e);
        }
    }

    private Boolean isNull(Object obj) {
        boolean z;
        if (obj == null) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    private String toJsonString() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(DataSyncContract.GallerySettingData.SORT_TYPE, this.mSortType);
            return jSONObject.toString();
        } catch (JSONException e) {
            SdkLog.s(e);
            return null;
        }
    }

    public void fromBundle(Bundle bundle) {
        fromJsonString(bundle.getString("json_string"));
    }

    public String getSortType() {
        return this.mSortType;
    }

    public String getSyncServiceName() {
        return SYNC_SERVICE_NAME;
    }

    public Boolean isInvalid() {
        return isNull(this.mSortType);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        String jsonString = toJsonString();
        if (jsonString != null) {
            bundle.putString("json_string", jsonString);
        }
        return bundle;
    }

    public GallerySetting(String str) {
        this.mSortType = str;
    }

    public GallerySetting(Bundle bundle) {
        fromBundle(bundle);
    }
}
