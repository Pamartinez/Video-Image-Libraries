package com.samsung.android.gallery.bixby.bixby.abstraction;

import Ba.h;
import N2.j;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.bixby2.action.ActionHandler;
import com.samsung.android.sdk.bixby2.action.ResponseCallback;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GalleryActionHandler extends ActionHandler {
    protected final String TAG = getClass().getSimpleName();
    protected String mAction;
    private ResponseCallback mCallback;
    private boolean mIsHandled = false;
    private HashMap<String, ArrayList<String>> mParams = null;
    private final HashMap<String, String> mParamsDebug = new HashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EmptyCallback implements ResponseCallback {
        public /* synthetic */ EmptyCallback(int i2) {
            this();
        }

        public void onComplete(String str) {
        }

        private EmptyCallback() {
        }

        public void onComplete(String str, PendingIntent pendingIntent) {
        }
    }

    /* access modifiers changed from: private */
    public void execute(String str, Blackboard blackboard) {
        Context onPreExecute = onPreExecute(blackboard);
        if (onPreExecute == null) {
            Log.bxe(this.TAG, "application context is null, so skip.");
        } else {
            onExecute(onPreExecute, blackboard);
        }
    }

    private void finishExecution() {
        String str = this.TAG;
        Log.bx(str, "executeAction end [" + this.mIsHandled + "]");
        if (!this.mIsHandled) {
            sendCallback(getFailResultString());
        }
    }

    private String getFormattedResultString() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            getResultValue(jSONObject2);
            jSONObject.put("result", jSONObject2);
        } catch (JSONException e) {
            Log.bxe(this.TAG, e.getMessage());
        }
        return jSONObject.toString();
    }

    private String getValueForDebug(String str) {
        return null;
    }

    private void init(Bundle bundle) {
        if (bundle != null) {
            try {
                this.mParams = (HashMap) bundle.getSerializable(ActionHandler.PARAMS);
                setParamsForDebug(bundle.getString("paramsDebug", (String) null));
                String str = this.TAG;
                Log.bx(str, "params [" + Logger.getEncodedString((Object) this.mParams) + "]");
            } catch (ClassCastException e) {
                Log.e((CharSequence) this.TAG, "unable to get param data from bundle", e.getMessage());
            }
        }
    }

    private boolean isRequireFormattedResult() {
        if ("GET_SELECTED_CONTENTS".equals(this.mAction) || "MOVE_TO_ALBUM_INFO".equals(this.mAction) || "MOVE_TO_GROUP_INFO".equals(this.mAction) || "SHOW_SINGLE_CONTENT".equals(this.mAction) || "DETAIL_VIEW_AI_EDIT".equals(this.mAction) || "DETAIL_VIEW_REMINDER".equals(this.mAction) || "GET_DETAIL_VIEW_CONTENT".equals(this.mAction)) {
            return true;
        }
        return false;
    }

    private Context onPreExecute(Blackboard blackboard) {
        return (Context) blackboard.read("data://app_context");
    }

    public void executeAction(Context context, String str, Bundle bundle, ResponseCallback responseCallback) {
        String str2;
        String str3 = this.TAG;
        StringBuilder k = j.k("executeAction start action [", str, "] extras [");
        if (bundle != null) {
            str2 = Logger.toString(bundle);
        } else {
            str2 = null;
        }
        k.append(str2);
        k.append("]");
        Log.bx(str3, k.toString());
        this.mAction = str;
        if (responseCallback == null) {
            responseCallback = new EmptyCallback(0);
        }
        this.mCallback = responseCallback;
        this.mIsHandled = false;
        if (TextUtils.isEmpty(this.mAction) || !isSupported()) {
            Log.bxe(this.TAG, "not matched.");
            sendCallback(getFailResultString());
            return;
        }
        init(bundle);
        Blackboard.getBlackboardMap().forEach(new h(24, this));
        finishExecution();
    }

    public String getFailResultString() {
        if (isRequireFormattedResult()) {
            return getFormattedResultString();
        }
        return getResultString("fail");
    }

    public String getResultString(Object obj) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("result", obj);
        } catch (JSONException e) {
            Log.bxe(this.TAG, e.getMessage());
        }
        return jSONObject.toString();
    }

    public void getResultValue(JSONObject jSONObject) {
        try {
            jSONObject.put("KEY_STATUS", "fail");
        } catch (JSONException e) {
            Log.bxe(this.TAG, e.getMessage());
        }
    }

    public JSONObject getSuccess() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("KEY_STATUS", "success");
            return jSONObject;
        } catch (JSONException e) {
            Log.e((CharSequence) this.TAG, "unable to generate json object for success message", e.getMessage());
            return jSONObject;
        }
    }

    public String getValue(String str) {
        ArrayList arrayList;
        HashMap<String, ArrayList<String>> hashMap = this.mParams;
        if (hashMap != null) {
            arrayList = hashMap.get(str);
        } else {
            arrayList = null;
        }
        if (arrayList == null || arrayList.isEmpty()) {
            return getValueForDebug(str);
        }
        return (String) arrayList.get(0);
    }

    public boolean isActivityResumed(Activity activity) {
        return SeApiCompat.isActivityResumed(activity);
    }

    public boolean isExecutable(String str, String str2) {
        if (str == null || !str.contains(str2)) {
            return false;
        }
        return true;
    }

    public boolean isExecutableInViewer(FileItemInterface fileItemInterface, String str, String str2, String str3) {
        boolean z;
        if (fileItemInterface != null && LocationKey.isContentViewer(str)) {
            return isExecutable(str2, str3);
        }
        String str4 = this.TAG;
        StringBuilder sb2 = new StringBuilder("not executable [");
        if (fileItemInterface == null) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        sb2.append("][");
        sb2.append(str);
        sb2.append("]");
        Log.bxe(str4, sb2.toString());
        return false;
    }

    public boolean isHandled() {
        return this.mIsHandled;
    }

    public abstract boolean isSupported();

    public abstract void onExecute(Context context, Blackboard blackboard);

    public void sendCallback(String str) {
        String str2 = this.TAG;
        Log.bx(str2, "sendCallback. " + Logger.getEncodedString(str));
        this.mCallback.onComplete(str);
        this.mIsHandled = true;
    }

    public void setWait() {
        this.mIsHandled = true;
    }

    private void setParamsForDebug(String str) {
    }
}
