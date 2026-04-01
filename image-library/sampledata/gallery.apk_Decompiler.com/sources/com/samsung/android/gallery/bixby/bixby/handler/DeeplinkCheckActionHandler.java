package com.samsung.android.gallery.bixby.bixby.handler;

import C3.C0392b;
import android.content.Context;
import android.text.TextUtils;
import com.samsung.android.gallery.bixby.bixby.abstraction.GalleryActionHandler;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeeplinkCheckActionHandler extends GalleryActionHandler {
    private JSONObject getInformation(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            Log.bx(this.TAG, "get information start");
            jSONObject.put("KEY_STATUS", "success");
            jSONObject.put("isAvailable", str);
            String str2 = this.TAG;
            Log.bx(str2, "get information end [" + Logger.getEncodedString(jSONObject.toString()) + "]");
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jSONObject;
        }
    }

    private String getResult(Context context, String str) {
        SettingPreference settingPreference = (SettingPreference) Arrays.stream(SettingPreference.values()).filter(new C0392b(str, 21)).findFirst().orElse((Object) null);
        if (settingPreference == null) {
            return "unknown";
        }
        if (settingPreference.support(context)) {
            return "valid";
        }
        return "invalid";
    }

    public boolean isSupported() {
        return "CheckAvailableDeeplink".equals(this.mAction);
    }

    public void onExecute(Context context, Blackboard blackboard) {
        if (!isHandled()) {
            String value = getValue("key");
            if (TextUtils.isEmpty(value)) {
                Log.bxe(this.TAG, "passed key is null, so skip.");
            } else {
                sendCallback(getResultString(getInformation(getResult(context, value))));
            }
        }
    }
}
