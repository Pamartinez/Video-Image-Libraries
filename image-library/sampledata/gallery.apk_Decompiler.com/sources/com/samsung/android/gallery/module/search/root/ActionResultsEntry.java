package com.samsung.android.gallery.module.search.root;

import com.samsung.android.gallery.support.utils.Log;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ActionResultsEntry {
    private String mId;
    private String mName;
    private String mType;

    public String getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public void parse(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONArray("gallery").getJSONObject(0);
            this.mType = jSONObject.getString("action_type");
            this.mId = jSONObject.getString("id");
            this.mName = jSONObject.getString("name");
        } catch (JSONException e) {
            Log.se("ActionResultsEntry", e.toString());
        }
    }
}
