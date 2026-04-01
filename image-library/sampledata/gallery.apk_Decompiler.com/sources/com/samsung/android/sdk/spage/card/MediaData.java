package com.samsung.android.sdk.spage.card;

import android.content.Intent;
import android.util.Log;
import com.samsung.android.sdk.spage.card.base.ActionFieldData;
import com.samsung.android.sdk.spage.card.base.JsonFieldData;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaData extends ActionFieldData<MediaData> {
    private static final String KEY_CONTENT_URI = "uriString";
    private static final String KEY_DATA_LIST = "dataList";
    private static final String KEY_FILE_PATH = "videoPath";
    private static final String KEY_MIME_TYPE = "mimeType";
    private static final String KEY_SEEK_POS = "seekPos";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MediaItemData implements JsonFieldData.Listable {
        private static final String KEY_ITEM_INTENT = "itemIntent";
        private final String mContentUri;
        private Intent mLaunchIntent;
        private final String mMimeType;

        public MediaItemData(String str, String str2) {
            if (str == null || str2 == null) {
                throw new IllegalArgumentException("Null values are not permitted");
            }
            this.mContentUri = str;
            this.mMimeType = str2;
        }

        public void setLaunchIntent(Intent intent) {
            this.mLaunchIntent = intent;
        }

        public JSONObject toJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(MediaData.KEY_CONTENT_URI, this.mContentUri);
                jSONObject.put("mimeType", this.mMimeType);
                jSONObject.put(KEY_ITEM_INTENT, this.mLaunchIntent.toUri(1));
                return jSONObject;
            } catch (JSONException e) {
                Log.d("Listable ", e.getMessage());
                return jSONObject;
            }
        }
    }

    public MediaData setContentUri(String str) {
        remove(KEY_CONTENT_URI);
        return (MediaData) put(KEY_CONTENT_URI, str);
    }

    public MediaData setFilePath(String str) {
        remove(KEY_FILE_PATH);
        return (MediaData) put(KEY_FILE_PATH, str);
    }

    public MediaData setList(List<MediaItemData> list) {
        remove(KEY_DATA_LIST);
        return (MediaData) putList(KEY_DATA_LIST, list);
    }

    public MediaData setMimeType(String str) {
        remove("mimeType");
        return (MediaData) put("mimeType", str);
    }

    public MediaData setSeekPos(int i2) {
        remove(KEY_SEEK_POS);
        return (MediaData) put(KEY_SEEK_POS, i2);
    }
}
