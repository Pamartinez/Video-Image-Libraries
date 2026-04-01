package com.samsung.android.sdk.mobileservice.social.datasync;

import android.os.Bundle;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.samsung.android.sdk.mobileservice.util.SdkLog;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ReminderSpace implements IData {
    public static final String SYNC_SERVICE_NAME = "8xyy6yh5v6";
    private final String EXTRA_JSON_STRING;
    private final String JSON_KEY_COLOR;
    private final String JSON_KEY_GROUP_ID;
    private final String JSON_KEY_ICON;
    private final String JSON_KEY_RECORD_ID;
    private final String JSON_KEY_SPACE_ID;
    private Integer mColor;
    private String mGroupId;
    private Integer mIcon;
    private String mSpaceId;

    public ReminderSpace() {
        this.JSON_KEY_GROUP_ID = BundleKey.GROUP_ID;
        this.JSON_KEY_SPACE_ID = BundleKey.SPACE_ID;
        this.JSON_KEY_RECORD_ID = "record_id";
        this.JSON_KEY_COLOR = "color";
        this.JSON_KEY_ICON = "icon";
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
            if (!jSONObject.isNull("color")) {
                this.mColor = Integer.valueOf(jSONObject.getInt("color"));
            }
            if (!jSONObject.isNull("icon")) {
                this.mIcon = Integer.valueOf(jSONObject.getInt("icon"));
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
            jSONObject.put("color", this.mColor);
            jSONObject.put("icon", this.mIcon);
            return jSONObject.toString();
        } catch (JSONException e) {
            SdkLog.s(e);
            return null;
        }
    }

    public void fromBundle(Bundle bundle) {
        fromJsonString(bundle.getString("json_string"));
    }

    public Integer getColor() {
        return this.mColor;
    }

    public Integer getIcon() {
        return this.mIcon;
    }

    public String getSyncServiceName() {
        return SYNC_SERVICE_NAME;
    }

    public Boolean isInvalid() {
        boolean z;
        if (isNull(this.mGroupId).booleanValue() || isNull(this.mSpaceId).booleanValue() || isNull(this.mColor).booleanValue() || isNull(this.mIcon).booleanValue()) {
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

    public ReminderSpace(String str, String str2) {
        this.JSON_KEY_GROUP_ID = BundleKey.GROUP_ID;
        this.JSON_KEY_SPACE_ID = BundleKey.SPACE_ID;
        this.JSON_KEY_RECORD_ID = "record_id";
        this.JSON_KEY_COLOR = "color";
        this.JSON_KEY_ICON = "icon";
        this.EXTRA_JSON_STRING = "json_string";
        this.mGroupId = str;
        this.mSpaceId = str2;
    }

    public ReminderSpace(String str, String str2, Integer num, Integer num2) {
        this(str, str2);
        this.mColor = num;
        this.mIcon = num2;
    }

    public ReminderSpace(Bundle bundle) {
        this.JSON_KEY_GROUP_ID = BundleKey.GROUP_ID;
        this.JSON_KEY_SPACE_ID = BundleKey.SPACE_ID;
        this.JSON_KEY_RECORD_ID = "record_id";
        this.JSON_KEY_COLOR = "color";
        this.JSON_KEY_ICON = "icon";
        this.EXTRA_JSON_STRING = "json_string";
        fromBundle(bundle);
    }
}
