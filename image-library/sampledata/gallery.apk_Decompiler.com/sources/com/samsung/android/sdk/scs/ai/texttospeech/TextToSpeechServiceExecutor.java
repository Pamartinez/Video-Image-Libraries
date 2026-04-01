package com.samsung.android.sdk.scs.ai.texttospeech;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.samsung.android.sdk.scs.base.connection.ServiceExecutor;
import com.samsung.android.sdk.scs.base.utils.ConnectionHelper;
import com.samsung.android.sivs.ai.sdkcommon.tts.ITextToSpeechService;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TextToSpeechServiceExecutor extends ServiceExecutor {
    private static final String TAG = "ScsApi@TTSServiceExecutor";
    /* access modifiers changed from: private */
    public IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        public void binderDied() {
            Log.d(TextToSpeechServiceExecutor.TAG, "binderDied deathRecipient callback");
            TextToSpeechServiceExecutor.this.mTTSService.asBinder().unlinkToDeath(TextToSpeechServiceExecutor.this.deathRecipient, 0);
        }
    };
    private int mCallerId = -1;
    private Context mContext = null;
    /* access modifiers changed from: private */
    public ITextToSpeechService mTTSService;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TextToSpeechServiceExecutor(android.content.Context r9) {
        /*
            r8 = this;
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.SECONDS
            java.util.concurrent.LinkedBlockingDeque r7 = new java.util.concurrent.LinkedBlockingDeque
            r7.<init>()
            r2 = 1
            r3 = 1
            r4 = 60
            r0 = r8
            r1 = r9
            r0.<init>((android.content.Context) r1, (int) r2, (int) r3, (long) r4, (java.util.concurrent.TimeUnit) r6, (java.util.concurrent.BlockingQueue<java.lang.Runnable>) r7)
            r8 = 0
            r0.mContext = r8
            r8 = -1
            r0.mCallerId = r8
            com.samsung.android.sdk.scs.ai.texttospeech.TextToSpeechServiceExecutor$1 r8 = new com.samsung.android.sdk.scs.ai.texttospeech.TextToSpeechServiceExecutor$1
            r8.<init>()
            r0.deathRecipient = r8
            android.content.Context r8 = r1.getApplicationContext()
            r0.mContext = r8
            int r8 = r0.hashCode()
            r0.mCallerId = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.texttospeech.TextToSpeechServiceExecutor.<init>(android.content.Context):void");
    }

    public int callerId() {
        return this.mCallerId;
    }

    public String callerName() {
        return this.mContext.getPackageName();
    }

    public Context getContext() {
        return this.mContext;
    }

    public Intent getServiceIntent() {
        return ConnectionHelper.getTextToSpeechServiceIntent();
    }

    public ITextToSpeechService getTTService() {
        return this.mTTSService;
    }

    public void onConnected(ComponentName componentName, IBinder iBinder) {
        Log.d(TAG, "onServiceConnected");
        ITextToSpeechService asInterface = ITextToSpeechService.Stub.asInterface(iBinder);
        this.mTTSService = asInterface;
        try {
            asInterface.asBinder().linkToDeath(this.deathRecipient, 0);
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException");
            e.printStackTrace();
        }
    }

    public void onDisconnected(ComponentName componentName) {
        Log.d(TAG, "onServiceDisconnected " + componentName);
        this.mTTSService = null;
    }
}
