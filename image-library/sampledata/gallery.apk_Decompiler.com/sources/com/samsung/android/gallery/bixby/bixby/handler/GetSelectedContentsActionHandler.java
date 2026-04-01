package com.samsung.android.gallery.bixby.bixby.handler;

import Gb.a;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.bixby.bixby.abstraction.GalleryActionHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.bixby2.Sbixby;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GetSelectedContentsActionHandler extends GalleryActionHandler {
    private JSONObject getInformation(int i2, MediaItem[] mediaItemArr) {
        JSONObject jSONObject = new JSONObject();
        try {
            Log.bx(this.TAG, "get information start");
            JSONArray jSONArray = new JSONArray();
            Arrays.stream(mediaItemArr).map(new a(4)).filter(new com.samsung.android.gallery.module.dynamicview.a(17)).limit((long) i2).forEach(new X9.a(jSONArray, 3));
            jSONObject.put("KEY_STATUS", "success");
            jSONObject.put("KEY_URI", jSONArray);
            jSONObject.put("KEY_TOTAL", mediaItemArr.length);
            jSONObject.put("KEY_SIZE", Math.min(i2, jSONArray.length()));
            String str = this.TAG;
            Log.bx(str, "get information end [" + Logger.getEncodedString(jSONObject.toString()) + "]");
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jSONObject;
        }
    }

    private void handleExecutable(Blackboard blackboard) {
        int i2 = UnsafeCast.toInt(getValue("KEY_SIZE"), 0);
        String str = (String) blackboard.read("location://variable/currentv1");
        if (isUnsupportedScreen(str) || i2 <= 0) {
            String str2 = this.TAG;
            Log.bxe(str2, "invalid state, so skip. ," + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + str);
            return;
        }
        blackboard.subscribe("data://bixby_command_done", new d8.a(this, i2));
        blackboard.postEvent(EventMessage.obtain(3008, Uri.parse("applink://com.sec.android.gallery3d/GET_SELECTED_CONTENTS").buildUpon().appendQueryParameter("KEY_INTERNAL_REQUEST", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE).build()));
        setWait();
    }

    private boolean isUnsupportedScreen(String str) {
        if (str == null) {
            return true;
        }
        if (LocationKey.isTimelinePictures(str) || LocationKey.isAlbumPictures(str) || LocationKey.isSearchPictures(str) || LocationKey.isFavoritePictures(str) || LocationKey.isVideoPictures(str) || LocationKey.isRecentlyPictures(str)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleExecutable$0(int i2, Object obj, Bundle bundle) {
        String str;
        try {
            Object[] objArr = (Object[]) obj;
            boolean booleanValue = ((Boolean) objArr[0]).booleanValue();
            MediaItem[] mediaItemArr = (MediaItem[]) objArr[1];
            if (!booleanValue || mediaItemArr == null || mediaItemArr.length <= 0) {
                str = getFailResultString();
            } else {
                str = getResultString(getInformation(i2, mediaItemArr));
            }
            sendCallback(str);
        } catch (Exception e) {
            String str2 = this.TAG;
            Log.bxe(str2, "can not execute bixby command done. " + e.getMessage());
            sendCallback(getFailResultString());
        }
    }

    public void getResultValue(JSONObject jSONObject) {
        super.getResultValue(jSONObject);
        try {
            jSONObject.put("KEY_ERROR", "invalid_request");
        } catch (JSONException e) {
            Log.bxe(this.TAG, e.getMessage());
        }
    }

    public boolean isSupported() {
        return "GET_SELECTED_CONTENTS".equals(this.mAction);
    }

    public void onExecute(Context context, Blackboard blackboard) {
        if (isExecutable(Sbixby.getStateHandler().getAppState(context, (Bundle) null), blackboard.getName())) {
            handleExecutable(blackboard);
        }
    }
}
