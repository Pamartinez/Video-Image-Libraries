package com.samsung.android.sdk.bixby2.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.sdk.bixby2.Constants;
import com.samsung.android.sdk.bixby2.LogUtil;
import com.samsung.android.sdk.bixby2.PermissionType;
import com.samsung.android.sdk.bixby2.Sbixby;
import com.samsung.android.sdk.bixby2.action.ActionHandler;
import com.samsung.android.sdk.bixby2.action.ResponseCallback;
import com.samsung.android.sdk.bixby2.data.CapsuleAction;
import com.samsung.android.sdk.bixby2.labs.ScreenLayoutHandler;
import com.samsung.android.sdk.bixby2.labs.UserGuideHandler;
import com.samsung.android.sdk.bixby2.receiver.ApplicationTriggerReceiver;
import com.samsung.android.sdk.bixby2.state.StateHandler;
import com.samsung.android.sdk.bixby2.util.BixbySignatureChecker;
import com.samsung.android.sum.core.Def;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CapsuleProvider extends ContentProvider {
    private static final String ACTION_APPLICATION_TRIGGER = "com.samsung.android.sdk.bixby2.ACTION_APPLICATION_TRIGGER";
    private static final int ACTION_EXECUTION_FAILURE = -1;
    private static final int ACTION_EXECUTION_SUCCESS = 0;
    private static final int ACTION_EXECUTION_TIMEOUT = 30000;
    private static final int ACTION_NOT_ALLOWED = -3;
    private static final int ACTION_NOT_IMPLEMENTED = -2;
    private static final String APP_CONTENT = "result";
    private static final String PENDING_INTENT = "pending_intent";
    private static final String STATUS_CODE = "status_code";
    private static final String STATUS_MESSAGE = "status_message";
    /* access modifiers changed from: private */
    public static final String TAG = "CapsuleProvider_1.1.0";
    private static final int WAIT_FOR_HANDLER_TIMEOUT = 3000;
    private static final Map<String, CapsuleAction> actionMap = new HashMap();
    private static String mActionId = null;
    private static boolean mIsAppInitialized = false;
    private static final boolean mIsUserBuild = "user".equals(Build.TYPE);
    /* access modifiers changed from: private */
    public static boolean mWaitForHandler = false;
    private static final Object sWaitLock = new Object();
    /* access modifiers changed from: private */
    public final Object sActionExecutionLock = new Object();

    public static void addActionHandler(String str, ActionHandler actionHandler, Boolean bool) {
        Object obj = sWaitLock;
        synchronized (obj) {
            try {
                Map<String, CapsuleAction> map = actionMap;
                if (map.get(str) == null) {
                    map.put(str, new CapsuleAction(actionHandler, bool.booleanValue()));
                    String str2 = mActionId;
                    if (str2 != null && str2.equals(str)) {
                        String str3 = TAG;
                        LogUtil.i(str3, "handler added: " + str);
                        obj.notify();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        r9 = r4.updateStatus(-1, "action execution timed out");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0097, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0098, code lost:
        return r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized android.os.Bundle executeAction(com.samsung.android.sdk.bixby2.PermissionType r10, java.lang.String r11, android.os.Bundle r12) {
        /*
            r9 = this;
            monitor-enter(r9)
            r1 = -1
            java.lang.String r0 = TAG     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            java.lang.String r2 = "executeAction()"
            com.samsung.android.sdk.bixby2.LogUtil.i(r0, r2)     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            com.samsung.android.sdk.bixby2.data.CapsuleAction r5 = r9.getCapsuleAction(r11)     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            if (r5 != 0) goto L_0x0027
            java.lang.String r10 = "Handler not found!!.."
            com.samsung.android.sdk.bixby2.LogUtil.e(r0, r10)     // Catch:{ Exception -> 0x0022, all -> 0x001d }
            java.lang.String r10 = "Action handler not found"
            r11 = -2
            android.os.Bundle r10 = r9.updateStatus(r11, r10)     // Catch:{ Exception -> 0x0022, all -> 0x001d }
            monitor-exit(r9)
            return r10
        L_0x001d:
            r0 = move-exception
            r10 = r0
            r4 = r9
            goto L_0x00d7
        L_0x0022:
            r0 = move-exception
            r10 = r0
            r4 = r9
            goto L_0x00b4
        L_0x0027:
            com.samsung.android.sdk.bixby2.PermissionType r2 = com.samsung.android.sdk.bixby2.PermissionType.EXPORTED_ACTION_ALLOWED     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            if (r10 != r2) goto L_0x003f
            boolean r10 = r5.isExported()     // Catch:{ Exception -> 0x0022, all -> 0x001d }
            if (r10 != 0) goto L_0x003f
            java.lang.String r10 = "Handler is not allowed.."
            com.samsung.android.sdk.bixby2.LogUtil.e(r0, r10)     // Catch:{ Exception -> 0x0022, all -> 0x001d }
            java.lang.String r10 = "Action handler is not allowed"
            r11 = -3
            android.os.Bundle r10 = r9.updateStatus(r11, r10)     // Catch:{ Exception -> 0x0022, all -> 0x001d }
            monitor-exit(r9)
            return r10
        L_0x003f:
            if (r12 == 0) goto L_0x0049
            java.lang.String r10 = "actionType"
            boolean r10 = r12.containsKey(r10)     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            if (r10 != 0) goto L_0x004b
        L_0x0049:
            r4 = r9
            goto L_0x00a7
        L_0x004b:
            com.samsung.android.sdk.bixby2.provider.CapsuleProvider$CapsuleResponseCallback r8 = new com.samsung.android.sdk.bixby2.provider.CapsuleProvider$CapsuleResponseCallback     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            r8.<init>()     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            java.lang.Thread r10 = new java.lang.Thread     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            com.samsung.android.sdk.bixby2.provider.CapsuleProvider$2 r3 = new com.samsung.android.sdk.bixby2.provider.CapsuleProvider$2     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            r4 = r9
            r6 = r11
            r7 = r12
            r3.<init>(r5, r6, r7, r8)     // Catch:{ Exception -> 0x009e }
            r10.<init>(r3)     // Catch:{ Exception -> 0x009e }
            r10.start()     // Catch:{ Exception -> 0x009e }
            java.lang.Object r9 = r4.sActionExecutionLock     // Catch:{ Exception -> 0x009e }
            monitor-enter(r9)     // Catch:{ Exception -> 0x009e }
            boolean r11 = r8.actionExecuted     // Catch:{ all -> 0x0071 }
            if (r11 != 0) goto L_0x0074
            java.lang.Object r11 = r4.sActionExecutionLock     // Catch:{ all -> 0x0071 }
            r2 = 30000(0x7530, double:1.4822E-319)
            r11.wait(r2)     // Catch:{ all -> 0x0071 }
            goto L_0x0074
        L_0x0071:
            r0 = move-exception
            r10 = r0
            goto L_0x009c
        L_0x0074:
            boolean r11 = r8.actionExecuted     // Catch:{ all -> 0x0071 }
            if (r11 == 0) goto L_0x0083
            android.os.Bundle r10 = r8.getResultBundle()     // Catch:{ all -> 0x0071 }
            if (r10 == 0) goto L_0x0090
            monitor-exit(r9)     // Catch:{ all -> 0x0071 }
            monitor-exit(r4)
            return r10
        L_0x0083:
            java.lang.String r11 = "timeout occurred.."
            com.samsung.android.sdk.bixby2.LogUtil.e(r0, r11)     // Catch:{ all -> 0x0071 }
            r11 = 1
            r8.setActionTimedOut(r11)     // Catch:{ all -> 0x0071 }
            r10.interrupt()     // Catch:{ all -> 0x0071 }
        L_0x0090:
            monitor-exit(r9)     // Catch:{ all -> 0x0071 }
            java.lang.String r9 = "action execution timed out"
            android.os.Bundle r9 = r4.updateStatus(r1, r9)     // Catch:{ all -> 0x0099 }
            monitor-exit(r4)
            return r9
        L_0x0099:
            r0 = move-exception
        L_0x009a:
            r10 = r0
            goto L_0x00d7
        L_0x009c:
            monitor-exit(r9)     // Catch:{ all -> 0x0071 }
            throw r10     // Catch:{ Exception -> 0x009e }
        L_0x009e:
            r0 = move-exception
        L_0x009f:
            r10 = r0
            goto L_0x00b4
        L_0x00a1:
            r0 = move-exception
            r4 = r9
            goto L_0x009a
        L_0x00a4:
            r0 = move-exception
            r4 = r9
            goto L_0x009f
        L_0x00a7:
            java.lang.String r9 = "params missing"
            com.samsung.android.sdk.bixby2.LogUtil.e(r0, r9)     // Catch:{ Exception -> 0x009e }
            java.lang.String r9 = "params missing.."
            android.os.Bundle r9 = r4.updateStatus(r1, r9)     // Catch:{ Exception -> 0x009e }
            monitor-exit(r4)
            return r9
        L_0x00b4:
            java.lang.String r9 = TAG     // Catch:{ all -> 0x0099 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0099 }
            r11.<init>()     // Catch:{ all -> 0x0099 }
            java.lang.String r12 = "Unable to execute action."
            r11.append(r12)     // Catch:{ all -> 0x0099 }
            r11.append(r10)     // Catch:{ all -> 0x0099 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0099 }
            com.samsung.android.sdk.bixby2.LogUtil.e(r9, r11)     // Catch:{ all -> 0x0099 }
            r10.printStackTrace()     // Catch:{ all -> 0x0099 }
            java.lang.String r9 = r10.toString()     // Catch:{ all -> 0x0099 }
            android.os.Bundle r9 = r4.updateStatus(r1, r9)     // Catch:{ all -> 0x0099 }
            monitor-exit(r4)
            return r9
        L_0x00d7:
            monitor-exit(r4)     // Catch:{ all -> 0x0099 }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.bixby2.provider.CapsuleProvider.executeAction(com.samsung.android.sdk.bixby2.PermissionType, java.lang.String, android.os.Bundle):android.os.Bundle");
    }

    private void executeProcessTriggerReceiver() {
        if (getContext() != null) {
            ApplicationTriggerReceiver applicationTriggerReceiver = new ApplicationTriggerReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ACTION_APPLICATION_TRIGGER);
            if (Build.VERSION.SDK_INT < 34 || getContext().getApplicationInfo().targetSdkVersion < 34) {
                getContext().registerReceiver(applicationTriggerReceiver, intentFilter);
            } else {
                getContext().registerReceiver(applicationTriggerReceiver, intentFilter, 4);
            }
            LogUtil.i(TAG, "ApplicationTriggerReceiver registered");
            Intent intent = new Intent();
            intent.setPackage(getContext().getPackageName());
            intent.setAction(ACTION_APPLICATION_TRIGGER);
            intent.addFlags(268435456);
            getContext().sendBroadcast(intent);
        }
    }

    private CapsuleAction getCapsuleAction(String str) {
        Map<String, CapsuleAction> map = actionMap;
        CapsuleAction capsuleAction = map.get(str);
        Object obj = sWaitLock;
        synchronized (obj) {
            if (capsuleAction == null) {
                try {
                    if (mWaitForHandler) {
                        mActionId = str;
                        obj.wait(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS);
                        capsuleAction = map.get(str);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return capsuleAction;
    }

    private boolean isAllowedSignature(PackageManager packageManager, String str) {
        try {
            if (isBixbySignature(packageManager.getPackageInfo(str, 64).signatures)) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isBixbySignature(Signature[] signatureArr) {
        if (signatureArr == null || signatureArr.length <= 0) {
            return false;
        }
        if (BixbySignatureChecker.isBixbyAgentSignature(signatureArr[0]) || BixbySignatureChecker.isBixbyAgentSignatureForIot(signatureArr[0])) {
            return true;
        }
        return false;
    }

    private PermissionType isCallerAllowed() {
        int callingUid = Binder.getCallingUid();
        PackageManager packageManager = getContext().getPackageManager();
        String[] packagesForUid = packageManager.getPackagesForUid(callingUid);
        if (packagesForUid == null) {
            LogUtil.e(TAG, "packages is null");
            return PermissionType.DENIED;
        }
        for (String str : packagesForUid) {
            if (Constants.BIXBY_AGENT_PKG_NAME.equals(str) || Constants.ROUTINE_PKG_NAME.equals(str)) {
                if (!mIsUserBuild || isAllowedSignature(packageManager, str)) {
                    return PermissionType.ALLOWED;
                }
            } else if (Constants.LINK_TO_WINDOWS_PKG_NAME.equals(str)) {
                if (!mIsUserBuild || isAllowedSignature(packageManager, str)) {
                    return PermissionType.EXPORTED_ACTION_ALLOWED;
                }
            } else if (Constants.ACTION_URI_TESTER_PKG_NAME.equals(str) && !mIsUserBuild) {
                return PermissionType.ALLOWED;
            }
        }
        LogUtil.e(TAG, "Not allowed to access capsule provider. package (s): " + Arrays.toString(packagesForUid));
        return PermissionType.DENIED;
    }

    public static void removeActionHandler(String str) {
        Map<String, CapsuleAction> map = actionMap;
        if (map.containsKey(str)) {
            map.remove(str);
            String str2 = TAG;
            LogUtil.d(str2, "action handler corresponding to action : " + str + " is removed");
        }
    }

    public static void removeAllActionHandlers() {
        actionMap.clear();
        LogUtil.i(TAG, "Removed all action handlers");
    }

    public static void setAppInitialized(boolean z) {
        Object obj = sWaitLock;
        synchronized (obj) {
            try {
                if (!mIsAppInitialized && z) {
                    mIsAppInitialized = z;
                    LogUtil.i(TAG, "releasing initialize wait lock.");
                    obj.notify();
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        new Timer().schedule(new TimerTask() {
            public void run() {
                boolean unused = CapsuleProvider.mWaitForHandler = false;
            }
        }, Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS);
    }

    private Bundle updateStatus(int i2, String str) {
        Bundle bundle = new Bundle();
        bundle.putInt(STATUS_CODE, i2);
        if (TextUtils.isEmpty(str) && i2 == -1) {
            str = "Failed to execute action.";
            LogUtil.e(TAG, str);
        }
        bundle.putString("status_message", str);
        return bundle;
    }

    private void waitForAppInitialization() {
        Object obj = sWaitLock;
        synchronized (obj) {
            if (!mIsAppInitialized) {
                try {
                    obj.wait(Def.SURFACE_CHANNEL_TIMEOUT_MILLIS);
                } catch (InterruptedException e) {
                    LogUtil.e(TAG, "interrupted exception");
                    e.printStackTrace();
                }
            }
        }
    }

    public Bundle call(String str, String str2, Bundle bundle) {
        String str3;
        String str4 = TAG;
        LogUtil.i(str4, "call()");
        StringBuilder sb2 = new StringBuilder("call(): method --> ");
        sb2.append(str);
        sb2.append(" args --> ");
        sb2.append(str2);
        sb2.append(" extras --> ");
        if (bundle != null) {
            str3 = bundle.toString();
        } else {
            str3 = null;
        }
        sb2.append(str3);
        LogUtil.d(str4, sb2.toString());
        PermissionType isCallerAllowed = isCallerAllowed();
        if (isCallerAllowed == PermissionType.DENIED) {
            throw new SecurityException("not permission to access capsule provider.");
        } else if (!TextUtils.isEmpty(str)) {
            if (!mIsAppInitialized) {
                executeProcessTriggerReceiver();
            }
            waitForAppInitialization();
            if (!mIsAppInitialized) {
                LogUtil.e(str4, "App initialization error.");
                return updateStatus(-1, "Initialization Failure..");
            }
            if (bundle != null) {
                LogUtil.setEngineeringMode(bundle.getBoolean(LogUtil.KEY_ENGINEERING_MODE, false));
            }
            if (str.equals(StateHandler.ACTION_GET_APP_STATE)) {
                Sbixby.getInstance();
                String appState = Sbixby.getStateHandler().getAppState(getContext(), bundle);
                if (appState == null) {
                    return null;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString(StateHandler.KEY_APP_STATE, appState);
                return bundle2;
            } else if (str.equals(ScreenLayoutHandler.ACTION_GET_SCREEN_LAYOUT)) {
                Bundle bundle3 = new Bundle();
                bundle3.putString(ScreenLayoutHandler.KEY_SCREEN_LAYOUT_INFO, ScreenLayoutHandler.INSTANCE.getScreenLayout(bundle));
                return bundle3;
            } else if (str.equals(UserGuideHandler.ACTION_GET_USER_GUIDE)) {
                Bundle bundle4 = new Bundle();
                bundle4.putString(UserGuideHandler.KEY_USER_GUIDE_DATA, UserGuideHandler.INSTANCE.getUserGuide(getContext(), bundle));
                return bundle4;
            } else if (bundle != null) {
                return executeAction(isCallerAllowed, str, bundle);
            } else {
                throw new IllegalArgumentException("action params are EMPTY.");
            }
        } else {
            throw new IllegalArgumentException("method is null or empty. pass valid action name.");
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public String getType(Uri uri) {
        return "actionUri";
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        LogUtil.i(TAG, "onCreate");
        mWaitForHandler = true;
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class CapsuleResponseCallback implements ResponseCallback {
        /* access modifiers changed from: private */
        public boolean actionExecuted = false;
        private boolean actionTimedOut = false;
        private final Bundle resultBundle = new Bundle();

        public CapsuleResponseCallback() {
        }

        public Bundle getResultBundle() {
            return this.resultBundle;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0057, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onComplete(java.lang.String r6) {
            /*
                r5 = this;
                java.lang.String r0 = "action result: "
                java.lang.String r1 = com.samsung.android.sdk.bixby2.provider.CapsuleProvider.TAG
                java.lang.String r2 = "onComplete()"
                com.samsung.android.sdk.bixby2.LogUtil.i(r1, r2)
                com.samsung.android.sdk.bixby2.provider.CapsuleProvider r1 = com.samsung.android.sdk.bixby2.provider.CapsuleProvider.this
                java.lang.Object r1 = r1.sActionExecutionLock
                monitor-enter(r1)
                boolean r2 = r5.actionTimedOut     // Catch:{ all -> 0x0018 }
                if (r2 == 0) goto L_0x001a
                monitor-exit(r1)     // Catch:{ all -> 0x0018 }
                return
            L_0x0018:
                r5 = move-exception
                goto L_0x0058
            L_0x001a:
                boolean r2 = r5.actionExecuted     // Catch:{ all -> 0x0018 }
                if (r2 != 0) goto L_0x0056
                java.lang.String r2 = com.samsung.android.sdk.bixby2.provider.CapsuleProvider.TAG     // Catch:{ all -> 0x0018 }
                java.lang.String r3 = "Action Execution Success"
                com.samsung.android.sdk.bixby2.LogUtil.i(r2, r3)     // Catch:{ all -> 0x0018 }
                android.os.Bundle r2 = r5.resultBundle     // Catch:{ all -> 0x0018 }
                java.lang.String r3 = "status_code"
                r4 = 0
                r2.putInt(r3, r4)     // Catch:{ all -> 0x0018 }
                android.os.Bundle r2 = r5.resultBundle     // Catch:{ all -> 0x0018 }
                java.lang.String r3 = "result"
                r2.putString(r3, r6)     // Catch:{ all -> 0x0018 }
                java.lang.String r2 = com.samsung.android.sdk.bixby2.provider.CapsuleProvider.TAG     // Catch:{ all -> 0x0018 }
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0018 }
                r3.<init>(r0)     // Catch:{ all -> 0x0018 }
                r3.append(r6)     // Catch:{ all -> 0x0018 }
                java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x0018 }
                com.samsung.android.sdk.bixby2.LogUtil.d(r2, r6)     // Catch:{ all -> 0x0018 }
                r6 = 1
                r5.actionExecuted = r6     // Catch:{ all -> 0x0018 }
                com.samsung.android.sdk.bixby2.provider.CapsuleProvider r5 = com.samsung.android.sdk.bixby2.provider.CapsuleProvider.this     // Catch:{ all -> 0x0018 }
                java.lang.Object r5 = r5.sActionExecutionLock     // Catch:{ all -> 0x0018 }
                r5.notify()     // Catch:{ all -> 0x0018 }
            L_0x0056:
                monitor-exit(r1)     // Catch:{ all -> 0x0018 }
                return
            L_0x0058:
                monitor-exit(r1)     // Catch:{ all -> 0x0018 }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.bixby2.provider.CapsuleProvider.CapsuleResponseCallback.onComplete(java.lang.String):void");
        }

        public void setActionTimedOut(boolean z) {
            this.actionTimedOut = z;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
            onComplete(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0027, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onComplete(java.lang.String r4, android.app.PendingIntent r5) {
            /*
                r3 = this;
                com.samsung.android.sdk.bixby2.provider.CapsuleProvider r0 = com.samsung.android.sdk.bixby2.provider.CapsuleProvider.this
                java.lang.Object r0 = r0.sActionExecutionLock
                monitor-enter(r0)
                boolean r1 = r3.actionTimedOut     // Catch:{ all -> 0x000d }
                if (r1 == 0) goto L_0x000f
                monitor-exit(r0)     // Catch:{ all -> 0x000d }
                return
            L_0x000d:
                r3 = move-exception
                goto L_0x0028
            L_0x000f:
                boolean r1 = r3.actionExecuted     // Catch:{ all -> 0x000d }
                if (r1 != 0) goto L_0x0023
                android.os.Bundle r1 = r3.resultBundle     // Catch:{ all -> 0x000d }
                java.lang.String r2 = "pending_intent"
                r1.putParcelable(r2, r5)     // Catch:{ all -> 0x000d }
                java.lang.String r5 = com.samsung.android.sdk.bixby2.provider.CapsuleProvider.TAG     // Catch:{ all -> 0x000d }
                java.lang.String r1 = "action result: pending intent"
                com.samsung.android.sdk.bixby2.LogUtil.i(r5, r1)     // Catch:{ all -> 0x000d }
            L_0x0023:
                monitor-exit(r0)     // Catch:{ all -> 0x000d }
                r3.onComplete(r4)
                return
            L_0x0028:
                monitor-exit(r0)     // Catch:{ all -> 0x000d }
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.bixby2.provider.CapsuleProvider.CapsuleResponseCallback.onComplete(java.lang.String, android.app.PendingIntent):void");
        }
    }
}
