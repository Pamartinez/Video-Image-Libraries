package com.samsung.android.sdk.scs.ai.asr;

import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import com.samsung.android.sdk.scs.ai.asr.tasks.RecognitionTask;
import com.samsung.android.sdk.scs.base.connection.ServiceExecutor;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.utils.BaseConstants;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.asr.ISpeechRecognizerService;
import com.samsung.android.sivs.ai.sdkcommon.asr.SpeechRecognitionConst;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class RemoteServiceExecutor extends ServiceExecutor {
    private static final String TAG = "RemoteServiceManager";
    private final Supplier<String> mActionResolver;
    private final String mPackageName;
    private ISpeechRecognizerService mService;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public RemoteServiceExecutor(android.content.Context r9) {
        /*
            r8 = this;
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.SECONDS
            java.util.concurrent.LinkedBlockingDeque r7 = new java.util.concurrent.LinkedBlockingDeque
            r7.<init>()
            r2 = 2
            r3 = 3
            r4 = 60
            r0 = r8
            r1 = r9
            r0.<init>((android.content.Context) r1, (int) r2, (int) r3, (long) r4, (java.util.concurrent.TimeUnit) r6, (java.util.concurrent.BlockingQueue<java.lang.Runnable>) r7)
            java.lang.String r8 = r1.getPackageName()
            r0.mPackageName = r8
            java.lang.String r9 = "com.samsung.android.intellivoiceservice.ai.asr.permission.SYSTEM_BIND_SPEECH_RECOGNITION_SERVICE"
            int r9 = r1.checkSelfPermission(r9)
            if (r9 != 0) goto L_0x003a
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r1 = "System permission has granted : "
            r9.<init>(r1)
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            java.lang.String r9 = "RemoteServiceManager"
            com.samsung.android.sdk.scs.base.utils.Log.i(r9, r8)
            com.samsung.android.sdk.scs.ai.asr.h r8 = new com.samsung.android.sdk.scs.ai.asr.h
            r9 = 0
            r8.<init>(r9)
            r0.mActionResolver = r8
            return
        L_0x003a:
            com.samsung.android.sdk.scs.ai.asr.h r8 = new com.samsung.android.sdk.scs.ai.asr.h
            r9 = 1
            r8.<init>(r9)
            r0.mActionResolver = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.asr.RemoteServiceExecutor.<init>(android.content.Context):void");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$new$0() {
        return SpeechRecognitionConst.SERVICE_SYSTEM_BIND_ACTION;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$new$1() {
        return SpeechRecognitionConst.SERVICE_BIND_ACTION;
    }

    public void beforeExecute(Thread thread, Runnable runnable) {
        super.beforeExecute(thread, runnable);
        if (runnable instanceof RecognitionTask) {
            ((RecognitionTask) runnable).active(this.mService);
        }
    }

    public Intent getServiceIntent() {
        Intent intent = new Intent(this.mActionResolver.get());
        if (Feature.isSIVSAvailableOSVersion(this.mContext)) {
            intent.setPackage(BaseConstants.PACKAGE_INFO.PACKAGE_SIVS_SERVICES);
        } else {
            intent.setPackage(BaseConstants.PACKAGE_INFO.PACKAGE_CORE_SERVICES);
        }
        intent.putExtra("caller_package", this.mPackageName);
        return intent;
    }

    public void onConnected(ComponentName componentName, IBinder iBinder) {
        Log.i(TAG, "onConnected");
        this.mService = ISpeechRecognizerService.Stub.asInterface(iBinder);
    }

    public void onDisconnected(ComponentName componentName) {
        Log.i(TAG, "onDisconnected");
        this.mService = null;
    }
}
