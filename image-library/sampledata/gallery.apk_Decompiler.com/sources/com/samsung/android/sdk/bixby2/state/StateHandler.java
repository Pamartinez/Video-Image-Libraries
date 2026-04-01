package com.samsung.android.sdk.bixby2.state;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.sdk.bixby2.Constants;
import com.samsung.android.sdk.bixby2.LogUtil;
import com.samsung.android.sdk.bixby2.Sbixby;
import com.samsung.android.sdk.bixby2.data.AppMetaInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StateHandler {
    public static final String ACTION_GET_APP_STATE = "getAppContext";
    public static final String APP_ID = "appId";
    public static final String APP_VERSION_CODE = "appVersionCode";
    public static final String CAPSULE_ID = "capsuleId";
    public static final String CONCEPTS = "concepts";
    public static final String DENIED_PERMISSIONS = "deniedPermissions";
    public static final String KEY_APP_STATE = "appContext";
    public static final String KEY_CAPSULE_ID = "com.samsung.android.bixby.capsuleId";
    public static final String LLM_CONTEXT = "llmContext";
    private static final String SUPPORTED_PERMISSIONS = "SUPPORTED_PERMISSIONS";
    private static final String TAG = "StateHandler";
    public static final String TYPE = "type";
    public static final String VALUE = "value";
    public static final String VALUES = "values";
    private static StateHandler mInstance;
    private Callback mCallback = null;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Callback {
        public List<String> getUsedPermissionsWhenAppStateRequested() {
            return null;
        }

        public abstract String onAppStateRequested();

        public String onCapsuleIdRequested() {
            return null;
        }
    }

    private StateHandler() {
    }

    private List<String> getClientDeniedPermissions(List<String> list, Context context, Bundle bundle) {
        HashSet hashSet;
        try {
            PackageManager packageManager = context.getPackageManager();
            ArrayList arrayList = new ArrayList();
            if (bundle != null) {
                hashSet = new HashSet(bundle.getStringArrayList(SUPPORTED_PERMISSIONS));
            } else {
                hashSet = null;
            }
            LogUtil.i(TAG, "supportedPermissionsInClient = " + hashSet);
            for (String next : list) {
                if (hashSet == null || hashSet.contains(next)) {
                    if (packageManager.checkPermission(next, Constants.BIXBY_AGENT_PKG_NAME) != 0) {
                        arrayList.add(next);
                    }
                }
            }
            return arrayList;
        } catch (Exception e) {
            LogUtil.e(TAG, "exception : " + e.getMessage());
            return null;
        }
    }

    private AppMetaInfo getDefaultAppMetaInfo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            String packageName = context.getPackageName();
            Bundle bundle = packageManager.getApplicationInfo(packageName, 128).metaData;
            if (bundle != null && bundle.containsKey(KEY_CAPSULE_ID)) {
                return new AppMetaInfo(bundle.getString(KEY_CAPSULE_ID), packageManager.getPackageInfo(packageName, 0).versionCode);
            }
            String str = TAG;
            LogUtil.e(str, "Can't get Capsule ID from Meta data:" + packageName);
            return null;
        } catch (PackageManager.NameNotFoundException | NullPointerException e) {
            String str2 = TAG;
            LogUtil.e(str2, "Failed to get Meta data info: " + e.getMessage());
            return null;
        }
    }

    public static synchronized StateHandler getInstance() {
        StateHandler stateHandler;
        synchronized (StateHandler.class) {
            try {
                if (mInstance == null) {
                    mInstance = new StateHandler();
                }
                stateHandler = mInstance;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return stateHandler;
    }

    private void replaceAppContextLegacyWithDeniedPermissions(List<String> list, JSONObject jSONObject) {
        if (list != null) {
            try {
                if (!list.isEmpty() && jSONObject.has(CONCEPTS)) {
                    JSONArray jSONArray = jSONObject.getJSONArray(CONCEPTS);
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                        if (jSONObject2.has(VALUES)) {
                            JSONObject jSONObject3 = new JSONObject();
                            jSONObject3.put(DENIED_PERMISSIONS, new JSONArray(list));
                            JSONArray jSONArray2 = new JSONArray();
                            jSONArray2.put(jSONObject3);
                            jSONObject2.put(VALUES, jSONArray2);
                            return;
                        }
                    }
                }
            } catch (Exception e) {
                String str = TAG;
                LogUtil.e(str, "[2.0][AppContext] replace context with denied permission exception : " + e.getMessage());
            }
        }
    }

    private void replaceLlmAppContextWithDeniedPermissions(List<String> list, JSONObject jSONObject) {
        if (list != null) {
            try {
                if (!list.isEmpty() && jSONObject.has(LLM_CONTEXT)) {
                    JSONArray jSONArray = jSONObject.getJSONArray(LLM_CONTEXT);
                    int i2 = 0;
                    while (i2 < jSONArray.length()) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                        if (!jSONObject2.has("value")) {
                            if (!jSONObject2.has(VALUES)) {
                                i2++;
                            }
                        }
                        jSONObject2.remove("value");
                        jSONObject2.remove(VALUES);
                        jSONObject2.put(DENIED_PERMISSIONS, new JSONArray(list));
                        return;
                    }
                }
            } catch (Exception e) {
                String str = TAG;
                LogUtil.e(str, "[LLM][AppContext] replace context with denied permission exception : " + e.getMessage());
            }
        }
    }

    public String getAppState(Context context) {
        return getAppState(context, (Bundle) null);
    }

    public void updateStateChange(Callback callback) {
        String str;
        String str2 = TAG;
        StringBuilder sb2 = new StringBuilder("state handler updated. callback: ");
        if (callback != null) {
            str = callback.toString();
        } else {
            str = null;
        }
        sb2.append(str);
        LogUtil.d(str2, sb2.toString());
        this.mCallback = callback;
    }

    public String getAppState(Context context, Bundle bundle) {
        AppMetaInfo appMetaInfo;
        Callback callback = this.mCallback;
        if (callback == null) {
            LogUtil.e(TAG, "StateHandler.Callback instance is null");
            return null;
        }
        String onAppStateRequested = callback.onAppStateRequested();
        if (TextUtils.isEmpty(onAppStateRequested)) {
            LogUtil.e(TAG, "state info is empty.");
            return null;
        }
        String onCapsuleIdRequested = this.mCallback.onCapsuleIdRequested();
        Map<String, AppMetaInfo> appMetaInfoMap = Sbixby.getInstance().getAppMetaInfoMap();
        if (TextUtils.isEmpty(onCapsuleIdRequested)) {
            String str = TAG;
            LogUtil.e(str, "capsuleId is empty");
            if (appMetaInfoMap == null || appMetaInfoMap.size() == 0) {
                appMetaInfo = getDefaultAppMetaInfo(context);
            } else if (appMetaInfoMap.size() == 1) {
                LogUtil.i(str, "Map for App Meta Info. has only one");
                appMetaInfo = (AppMetaInfo) appMetaInfoMap.entrySet().iterator().next().getValue();
            } else {
                LogUtil.e(str, "No Capsule Id and multiple App Meta Info. Can't pick one");
                return null;
            }
        } else if (appMetaInfoMap == null || !appMetaInfoMap.containsKey(onCapsuleIdRequested)) {
            LogUtil.e(TAG, "Map for App Meta Info. is empty");
            AppMetaInfo defaultAppMetaInfo = getDefaultAppMetaInfo(context);
            if (defaultAppMetaInfo != null) {
                defaultAppMetaInfo.setCapsuleId(onCapsuleIdRequested);
            }
            appMetaInfo = defaultAppMetaInfo;
        } else {
            appMetaInfo = appMetaInfoMap.get(onCapsuleIdRequested);
        }
        if (appMetaInfo == null) {
            LogUtil.e(TAG, "App Meta Info. is null");
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(onAppStateRequested);
            jSONObject.put(CAPSULE_ID, appMetaInfo.getCapsuleId());
            jSONObject.put("appId", context.getPackageName());
            jSONObject.put(APP_VERSION_CODE, appMetaInfo.getAppVersionCode());
            List<String> usedPermissionsWhenAppStateRequested = this.mCallback.getUsedPermissionsWhenAppStateRequested();
            String str2 = TAG;
            LogUtil.i(str2, "getUsedPermissionsWhenAppStateRequested() = " + usedPermissionsWhenAppStateRequested);
            if (Build.VERSION.SDK_INT >= 31 && usedPermissionsWhenAppStateRequested != null && !usedPermissionsWhenAppStateRequested.isEmpty()) {
                List<String> clientDeniedPermissions = getClientDeniedPermissions(usedPermissionsWhenAppStateRequested, context, bundle);
                LogUtil.i(str2, "deniedPermissionsInClient = " + clientDeniedPermissions);
                if (clientDeniedPermissions != null) {
                    replaceAppContextLegacyWithDeniedPermissions(clientDeniedPermissions, jSONObject);
                    replaceLlmAppContextWithDeniedPermissions(clientDeniedPermissions, jSONObject);
                }
            }
            LogUtil.d(str2, "state info: " + jSONObject);
            return jSONObject.toString();
        } catch (Exception e) {
            String str3 = TAG;
            LogUtil.e(str3, "getAppState exception " + e.getMessage());
            return null;
        }
    }
}
