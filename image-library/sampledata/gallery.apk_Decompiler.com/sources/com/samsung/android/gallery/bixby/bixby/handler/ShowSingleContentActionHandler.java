package com.samsung.android.gallery.bixby.bixby.handler;

import G7.e;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.bixby.bixby.abstraction.GalleryActionHandler;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.bixby2.Sbixby;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShowSingleContentActionHandler extends GalleryActionHandler {
    private String mErrorCode;

    private void handleExecutable(Blackboard blackboard) {
        String str = (String) blackboard.read("location://variable/currentv1");
        if (LocationKey.isContentViewer(str) || ((!LocationKey.isPictures(str) && !LocationKey.isAlbums(str) && !LocationKey.isStories(str) && !LocationKey.isSharings(str)) || LocationKey.containsTrash(str) || LocationKey.isMtp(str))) {
            String str2 = this.TAG;
            Log.bxe(str2, "invalid screen, so skip. " + str);
            return;
        }
        String value = getValue("KEY_TIME_FROM");
        String value2 = getValue("KEY_TIME_TO");
        String value3 = getValue("KEY_ORDER_NUM");
        if (!TextUtils.isEmpty(value) || !TextUtils.isEmpty(value2) || !TextUtils.isEmpty(value3)) {
            blackboard.subscribe("data://bixby_command_done", new e(6, this));
            blackboard.postEvent(EventMessage.obtain(3008, Uri.parse("applink://com.sec.android.gallery3d/SHOW_SINGLE_CONTENT").buildUpon().appendQueryParameter("KEY_INTERNAL_REQUEST", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE).appendQueryParameter("KEY_TIME_FROM", value).appendQueryParameter("KEY_TIME_TO", value2).appendQueryParameter("KEY_ORDER_NUM", value3).build()));
            setWait();
            return;
        }
        Log.bxe(this.TAG, "passed argument are empty, so skip.");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleExecutable$0(Object obj, Bundle bundle) {
        String str;
        try {
            Object[] objArr = (Object[]) obj;
            boolean booleanValue = ((Boolean) objArr[0]).booleanValue();
            this.mErrorCode = (String) objArr[1];
            if (booleanValue) {
                str = getResultString(getSuccess());
            } else {
                str = getFailResultString();
            }
            sendCallback(str);
        } catch (Exception e) {
            String str2 = this.TAG;
            Log.bxe(str2, "can not execute bixby command done. " + e.getMessage());
            sendCallback(getFailResultString());
        }
    }

    public void getResultValue(JSONObject jSONObject) {
        String str;
        super.getResultValue(jSONObject);
        try {
            if (TextUtils.isEmpty(this.mErrorCode)) {
                str = "unsupported";
            } else {
                str = this.mErrorCode;
            }
            jSONObject.put("KEY_ERROR", str);
        } catch (JSONException e) {
            Log.bxe(this.TAG, e.getMessage());
        }
    }

    public boolean isSupported() {
        return "SHOW_SINGLE_CONTENT".equals(this.mAction);
    }

    public void onExecute(Context context, Blackboard blackboard) {
        this.mErrorCode = null;
        if (isExecutable(Sbixby.getStateHandler().getAppState(context, (Bundle) null), blackboard.getName())) {
            handleExecutable(blackboard);
        }
    }
}
