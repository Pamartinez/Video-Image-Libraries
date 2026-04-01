package com.samsung.android.gallery.bixby.bixby.handler;

import android.content.Context;
import com.samsung.android.gallery.bixby.bixby.abstraction.GalleryActionHandler;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashInfoActionHandler extends GalleryActionHandler {
    private JSONObject getInformation() {
        JSONObject jSONObject = new JSONObject();
        try {
            Log.bx(this.TAG, "get information start");
            jSONObject.put("KEY_STATUS", "success");
            jSONObject.put("KEY_TRASH_ON", PreferenceFeatures.isEnabled(PreferenceFeatures.UseTrash));
            String str = this.TAG;
            Log.bx(str, "get information end [" + Logger.getEncodedString(jSONObject.toString()) + "]");
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jSONObject;
        }
    }

    public boolean isSupported() {
        return "TRASH_INFO".equals(this.mAction);
    }

    public void onExecute(Context context, Blackboard blackboard) {
        String str = this.TAG;
        Log.bx(str, "found blackboard to start ACTION_TRASH_INFO [" + blackboard.getName() + "]");
        sendCallback(getResultString(getInformation()));
    }
}
