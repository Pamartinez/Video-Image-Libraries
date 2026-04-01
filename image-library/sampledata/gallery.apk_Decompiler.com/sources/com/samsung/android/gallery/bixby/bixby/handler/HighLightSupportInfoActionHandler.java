package com.samsung.android.gallery.bixby.bixby.handler;

import android.content.Context;
import com.samsung.android.gallery.bixby.bixby.abstraction.GalleryActionHandler;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HighLightSupportInfoActionHandler extends GalleryActionHandler {
    private JSONObject getInformation() {
        JSONObject jSONObject = new JSONObject();
        try {
            Log.bx(this.TAG, "get information start");
            jSONObject.put("KEY_STATUS", "success");
            jSONObject.put("KEY_SUPPORT_HIGHLIGHT_VIDEO", Features.isEnabled(Features.SUPPORT_CREATE_HIGHLIGHT_REEL));
            String str = this.TAG;
            Log.bx(str, "get information end [" + Logger.getEncodedString(jSONObject.toString()) + "]");
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jSONObject;
        }
    }

    public boolean isSupported() {
        return "HIGHLIGHT_VIDEO_SUPPORT".equals(this.mAction);
    }

    public void onExecute(Context context, Blackboard blackboard) {
        String str = this.TAG;
        Log.bx(str, "found blackboard to start ACTION_HIGHLIGHT_VIDEO_SUPPORT [" + blackboard.getName() + "]");
        sendCallback(getResultString(getInformation()));
    }
}
