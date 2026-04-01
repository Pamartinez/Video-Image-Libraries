package com.samsung.android.gallery.bixby.bixby.handler;

import Fa.I;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.bixby.bixby.abstraction.GalleryActionHandler;
import com.samsung.android.gallery.module.bixby.BixbyAppStateUtil;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.bixby2.Sbixby;
import java.util.Optional;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GetViewerContentActionHandler extends GalleryActionHandler {
    private Uri getGrantedUri(Context context, Uri uri) {
        try {
            Optional.ofNullable(uri).ifPresent(new I(context, 5));
            return uri;
        } catch (Exception e) {
            String str = this.TAG;
            Log.bxe(str, "unable to grant uri, e=" + e.getMessage());
            return uri;
        }
    }

    private JSONObject getInformation(Context context, MediaItem mediaItem) {
        JSONObject jSONObject = new JSONObject();
        try {
            Log.bx(this.TAG, "get information start");
            jSONObject.put("KEY_STATUS", "success");
            jSONObject.put("KEY_URI", BixbyAppStateUtil.getNonNullValue(getGrantedUri(context, ContentUri.getUri(mediaItem))));
            String str = this.TAG;
            Log.bx(str, "get information end [" + Logger.getEncodedString(jSONObject.toString()) + "]");
            return jSONObject;
        } catch (JSONException e) {
            String str2 = this.TAG;
            Log.bxe(str2, "get information failed, e=" + e.getMessage());
            return jSONObject;
        }
    }

    public void getResultValue(JSONObject jSONObject) {
        super.getResultValue(jSONObject);
        try {
            jSONObject.put("KEY_ERROR", "unsupported_screen");
        } catch (JSONException e) {
            Log.bxe(this.TAG, e.getMessage());
        }
    }

    public boolean isSupported() {
        return "GET_DETAIL_VIEW_CONTENT".equals(this.mAction);
    }

    public void onExecute(Context context, Blackboard blackboard) {
        String str = this.TAG;
        Log.bx(str, "found blackboard to start ACTION_GET_DETAIL_VIEW_CONTENT [" + blackboard.getName() + "]");
        if (!isActivityResumed(BlackboardUtils.readActivity(blackboard))) {
            Log.bxe(this.TAG, "activity is not resumed. so skip.");
            return;
        }
        MediaItem mediaItem = (MediaItem) blackboard.pop("data://bixby_mediaItem");
        if (isExecutableInViewer(mediaItem, (String) blackboard.read("location://variable/currentv1"), Sbixby.getStateHandler().getAppState(context, (Bundle) null), blackboard.getName())) {
            sendCallback(getResultString(getInformation(context, mediaItem)));
        }
    }
}
