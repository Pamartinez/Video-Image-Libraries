package com.samsung.android.gallery.module.deepsky;

import A.a;
import android.net.Uri;
import android.text.TextUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.GsonTool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScreenShotAction {
    String mActionUrl;
    String mClass;
    String mIconUri;
    boolean mSupport;
    String mTitle;

    private static String getValue(JsonObject jsonObject, String str) {
        try {
            JsonElement jsonElement = jsonObject.get(str);
            if (jsonElement != null && !jsonElement.isJsonNull()) {
                return jsonElement.getAsString();
            }
        } catch (Error | Exception unused) {
        }
        return null;
    }

    private boolean isScreenShot(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || !fileItemInterface.hasSefFileTypes(SefInfo.SCREEN_CAPTURE_INFO.keyCode)) {
            return false;
        }
        return true;
    }

    private void parse(String str) {
        boolean z;
        JsonObject jsonObject = GsonTool.toJsonObject(str);
        this.mClass = getValue(jsonObject, "Classification");
        this.mTitle = getValue(jsonObject, "Title");
        this.mIconUri = getValue(jsonObject, "IconUri");
        this.mActionUrl = getValue(jsonObject, "ActionUrl");
        if (TextUtils.isEmpty(this.mTitle) || TextUtils.isEmpty(this.mIconUri) || TextUtils.isEmpty(this.mActionUrl)) {
            z = false;
        } else {
            z = true;
        }
        this.mSupport = z;
    }

    private String request(FileItemInterface fileItemInterface) {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.TextExtractionCapsule)) {
            try {
                JsonObject loadScreenShotAction = DeepSkySuggest.loadScreenShotAction(fileItemInterface);
                if (loadScreenShotAction != null) {
                    return loadScreenShotAction.toString();
                }
                return null;
            } catch (Error | Exception e) {
                a.z(e, new StringBuilder("request screenshot suggestion failed, e="), "ScreenShotAction");
            }
        }
        return null;
    }

    public String getActionUrl() {
        return this.mActionUrl;
    }

    public String getClassification() {
        return this.mClass;
    }

    public Uri getIconUri() {
        return Uri.parse(this.mIconUri);
    }

    public String getTitle() {
        return this.mTitle;
    }

    public ScreenShotAction load(FileItemInterface fileItemInterface) {
        if (isScreenShot(fileItemInterface)) {
            String request = request(fileItemInterface);
            if (!TextUtils.isEmpty(request)) {
                parse(request);
                return this;
            }
            Log.w("ScreenShotAction", "no data");
        }
        return this;
    }

    public boolean supported() {
        return this.mSupport;
    }
}
