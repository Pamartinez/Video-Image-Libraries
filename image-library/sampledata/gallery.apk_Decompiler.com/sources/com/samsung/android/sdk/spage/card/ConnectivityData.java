package com.samsung.android.sdk.spage.card;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ConnectivityData {
    private static final String KEY_EVENT = "event";
    private static final String KEY_FILE_URI = "fileUri";
    private static final String KEY_MIME_TYPE = "mimeType";
    private static final String KEY_STAE = "state";
    private static final String TAG = "ConnectivityData";
    private String event;
    private String fileUri;
    private String mimeType;
    private String state;

    public JSONObject getData() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEY_FILE_URI, this.fileUri);
            jSONObject.put("state", this.state);
            jSONObject.put("mimeType", this.mimeType);
            jSONObject.put(KEY_EVENT, this.event);
            return jSONObject;
        } catch (JSONException unused) {
            Log.d(TAG, "error while  adding data");
            return jSONObject;
        }
    }

    public ConnectivityData setEvent(String str) {
        this.event = str;
        return this;
    }

    public ConnectivityData setFileUri(String str) {
        this.fileUri = str;
        return this;
    }

    public ConnectivityData setMimeType(String str) {
        this.mimeType = str;
        return this;
    }

    public ConnectivityData setState(String str) {
        this.state = str;
        return this;
    }
}
