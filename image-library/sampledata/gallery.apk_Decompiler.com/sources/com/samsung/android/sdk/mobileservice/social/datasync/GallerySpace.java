package com.samsung.android.sdk.mobileservice.social.datasync;

import android.os.Bundle;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.samsung.android.sdk.mobileservice.util.SdkLog;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GallerySpace implements IData {
    public static final String SYNC_SERVICE_NAME = "G5CJkhyQnz";
    private final String EXTRA_JSON_STRING;
    private final String JSON_KEY_GROUP_ID;
    private final String JSON_KEY_RECORD_ID;
    private final String JSON_KEY_SORT_VALUE;
    private final String JSON_KEY_SPACE_ID;
    private String mGroupId;
    private String mSortValue;
    private String mSpaceId;

    public GallerySpace() {
        this.JSON_KEY_GROUP_ID = BundleKey.GROUP_ID;
        this.JSON_KEY_SPACE_ID = BundleKey.SPACE_ID;
        this.JSON_KEY_RECORD_ID = "record_id";
        this.JSON_KEY_SORT_VALUE = "sort_value";
        this.EXTRA_JSON_STRING = "json_string";
    }

    private void fromJsonString(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.isNull(BundleKey.GROUP_ID)) {
                this.mGroupId = jSONObject.getString(BundleKey.GROUP_ID);
            }
            if (!jSONObject.isNull(BundleKey.SPACE_ID)) {
                this.mSpaceId = jSONObject.getString(BundleKey.SPACE_ID);
            }
            if (!jSONObject.isNull("sort_value")) {
                this.mSortValue = jSONObject.getString("sort_value");
            }
        } catch (JSONException e) {
            SdkLog.s(e);
        }
    }

    private String getRecordId() {
        return this.mGroupId + "_" + this.mSpaceId;
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
            jSONObject.put(BundleKey.GROUP_ID, this.mGroupId);
            jSONObject.put(BundleKey.SPACE_ID, this.mSpaceId);
            jSONObject.put("record_id", getRecordId());
            jSONObject.put("sort_value", this.mSortValue);
            return jSONObject.toString();
        } catch (JSONException e) {
            SdkLog.s(e);
            return null;
        }
    }

    public void fromBundle(Bundle bundle) {
        fromJsonString(bundle.getString("json_string"));
    }

    public String getSortValue() {
        return this.mSortValue;
    }

    public String getSyncServiceName() {
        return SYNC_SERVICE_NAME;
    }

    public Boolean isInvalid() {
        boolean z;
        if (isNull(this.mGroupId).booleanValue() || isNull(this.mSpaceId).booleanValue() || isNull(this.mSortValue).booleanValue()) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        String jsonString = toJsonString();
        if (jsonString != null) {
            bundle.putString("json_string", jsonString);
        }
        return bundle;
    }

    public GallerySpace(String str, String str2) {
        this.JSON_KEY_GROUP_ID = BundleKey.GROUP_ID;
        this.JSON_KEY_SPACE_ID = BundleKey.SPACE_ID;
        this.JSON_KEY_RECORD_ID = "record_id";
        this.JSON_KEY_SORT_VALUE = "sort_value";
        this.EXTRA_JSON_STRING = "json_string";
        this.mGroupId = str;
        this.mSpaceId = str2;
    }

    public GallerySpace(String str, String str2, String str3) {
        this(str, str2);
        this.mSortValue = str3;
    }

    public GallerySpace(Bundle bundle) {
        this.JSON_KEY_GROUP_ID = BundleKey.GROUP_ID;
        this.JSON_KEY_SPACE_ID = BundleKey.SPACE_ID;
        this.JSON_KEY_RECORD_ID = "record_id";
        this.JSON_KEY_SORT_VALUE = "sort_value";
        this.EXTRA_JSON_STRING = "json_string";
        fromBundle(bundle);
    }
}
