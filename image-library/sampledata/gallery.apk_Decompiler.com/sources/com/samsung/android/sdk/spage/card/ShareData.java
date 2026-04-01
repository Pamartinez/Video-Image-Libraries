package com.samsung.android.sdk.spage.card;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareData {
    private static final String KEY_SHARE_MIMETYPE = "shareMimeType";
    private static final String KEY_SHARE_TEXT_BODY = "shareTextBody";
    private static final String KEY_SHARE_TEXT_SUBJECT = "shareTextSubject";
    private static final String KEY_SHARE_URI = "shareUri";
    private String dataUri;
    private String mimeType;
    private String textBody;
    private String textSubject;

    public JSONObject getData() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEY_SHARE_URI, this.dataUri);
            jSONObject.put(KEY_SHARE_TEXT_SUBJECT, this.textSubject);
            jSONObject.put(KEY_SHARE_TEXT_BODY, this.textBody);
            jSONObject.put(KEY_SHARE_MIMETYPE, this.mimeType);
            return jSONObject;
        } catch (JSONException unused) {
            Log.d("ShareData", "error in adding data");
            return jSONObject;
        }
    }

    public ShareData setDataUri(String str) {
        this.dataUri = str;
        return this;
    }

    public ShareData setMimeType(String str) {
        this.mimeType = str;
        return this;
    }

    public ShareData setTextBody(String str) {
        this.textBody = str;
        return this;
    }

    public ShareData setTextSubject(String str) {
        this.textSubject = str;
        return this;
    }
}
