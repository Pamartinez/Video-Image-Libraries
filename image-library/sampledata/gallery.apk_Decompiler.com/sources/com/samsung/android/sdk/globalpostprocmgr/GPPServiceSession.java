package com.samsung.android.sdk.globalpostprocmgr;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Pair;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.android.sdk.globalpostprocmgr.util.Log;
import i.C0212a;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Random;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GPPServiceSession {
    static final int MSG_ADD_TASK = 3;
    static final int MSG_ADD_TASK_RETURN_FAIL = 5;
    static final int MSG_ADD_TASK_RETURN_PASS = 4;
    static final int MSG_CLIENT_CONNECT = 1;
    static final int MSG_CLIENT_DISCONNECT = 2;
    static final int MSG_RETRIEVE_TASK = 10;
    static final int MSG_RETRIEVE_TASK_RETURN = 11;
    static final int MSG_STOP_TASK = 6;
    static final int MSG_STOP_TASK_COMPLETED = 7;
    static final int MSG_STOP_TASK_PROCESSING = 8;
    static final int MSG_TASK_RETURN_ERROR = 12;
    static final int MSG_TASK_SUBMITTED = 9;
    static final int SERVICE_STATE_BINDING = 1;
    static final int SERVICE_STATE_BOUND = 2;
    static final int SERVICE_STATE_UNBOUND = 3;
    private static final String TAG = "GPPServiceSession";
    /* access modifiers changed from: private */
    public Object lock = new Object();
    private long mBindingStartTime;
    private Context mContext;
    private HandlerThread mHandlerThread;
    /* access modifiers changed from: private */
    public HashMap<String, Pair<String, IGPPProcessingListener>> mRequestIdToListenerMap = new HashMap<>();
    /* access modifiers changed from: private */
    public IGPPProcessingListener mRetrieveTaskProcessingListener = null;
    private Messenger mServiceCallbackMessenger;
    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        public void onBindingDied(ComponentName componentName) {
            Log.e("GPPServiceSession", "onBindingDied", new Object[0]);
            GPPServiceSession.this.handleServiceDisconnected(false);
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i("GPPServiceSession", "onServiceConnected", new Object[0]);
            GPPServiceSession.this.handleServiceConnected(iBinder);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("GPPServiceSession", "onServiceDisconnected", new Object[0]);
            GPPServiceSession.this.handleServiceDisconnected(false);
        }
    };
    private Messenger mServiceMessenger;
    private IGPPServiceSessionListener mSessionListener;
    private volatile int mState;
    /* access modifiers changed from: private */
    public boolean mTaskPending = false;

    /* renamed from: com.samsung.android.sdk.globalpostprocmgr.GPPServiceSession$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sdk$globalpostprocmgr$GPPServiceSession$SendMessageResponse;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.samsung.android.sdk.globalpostprocmgr.GPPServiceSession$SendMessageResponse[] r0 = com.samsung.android.sdk.globalpostprocmgr.GPPServiceSession.SendMessageResponse.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$sdk$globalpostprocmgr$GPPServiceSession$SendMessageResponse = r0
                com.samsung.android.sdk.globalpostprocmgr.GPPServiceSession$SendMessageResponse r1 = com.samsung.android.sdk.globalpostprocmgr.GPPServiceSession.SendMessageResponse.SUCCESS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$sdk$globalpostprocmgr$GPPServiceSession$SendMessageResponse     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.sdk.globalpostprocmgr.GPPServiceSession$SendMessageResponse r1 = com.samsung.android.sdk.globalpostprocmgr.GPPServiceSession.SendMessageResponse.ERROR_NOT_BOUND     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$sdk$globalpostprocmgr$GPPServiceSession$SendMessageResponse     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.sdk.globalpostprocmgr.GPPServiceSession$SendMessageResponse r1 = com.samsung.android.sdk.globalpostprocmgr.GPPServiceSession.SendMessageResponse.ERROR_REMOTE_EXCEPTION     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.globalpostprocmgr.GPPServiceSession.AnonymousClass2.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum SendMessageResponse {
        SUCCESS,
        ERROR_REMOTE_EXCEPTION,
        ERROR_NOT_BOUND
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ServiceCallbackHandler extends Handler {
        public ServiceCallbackHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            boolean z;
            IGPPProcessingListener iGPPProcessingListener;
            Log.d("GPPServiceSession", "Message from Service " + message.what, new Object[0]);
            Bundle data = message.getData();
            String string = data.getString("request_id");
            String string2 = data.getString(IParameterKey.TASK_ID);
            Log.i("GPPServiceSession", C0212a.n("Request id: ", string, " Task id: ", string2), new Object[0]);
            if (string == null || string.isEmpty()) {
                z = false;
            } else {
                z = true;
            }
            if (!z || !GPPServiceSession.this.mRequestIdToListenerMap.containsKey(string)) {
                iGPPProcessingListener = null;
            } else {
                iGPPProcessingListener = (IGPPProcessingListener) ((Pair) GPPServiceSession.this.mRequestIdToListenerMap.get(string)).second;
                GPPServiceSession.this.mRequestIdToListenerMap.put(string, new Pair(string2, iGPPProcessingListener));
            }
            switch (message.what) {
                case 4:
                    Log.i("GPPServiceSession", "MSG_ADD_TASK_RETURN_PASS from Service. Result keys size - " + data.keySet().size(), new Object[0]);
                    if (iGPPProcessingListener != null) {
                        iGPPProcessingListener.onTaskCompleted(message);
                        return;
                    }
                    return;
                case 5:
                    Log.i("GPPServiceSession", "MSG_ADD_TASK_RETURN_FAIL from Service", new Object[0]);
                    if (iGPPProcessingListener != null) {
                        iGPPProcessingListener.onTaskRejected();
                        return;
                    }
                    return;
                case 7:
                    Log.i("GPPServiceSession", "MSG_STOP_TASK_COMPLETED from Service", new Object[0]);
                    if (iGPPProcessingListener != null) {
                        iGPPProcessingListener.onTaskStopped();
                        return;
                    }
                    return;
                case 8:
                    Log.i("GPPServiceSession", "MSG_STOP_TASK_PROCESSING from Service", new Object[0]);
                    if (iGPPProcessingListener != null) {
                        iGPPProcessingListener.onTaskProcessing(message.arg1, message.arg2);
                        return;
                    }
                    return;
                case 9:
                    Log.i("GPPServiceSession", "MSG_TASK_SUBMITTED from Service", new Object[0]);
                    if (iGPPProcessingListener != null) {
                        iGPPProcessingListener.onTaskSubmitted(data);
                        return;
                    }
                    return;
                case 11:
                    Log.i("GPPServiceSession", "MSG_RETRIEVE_TASK_RETURN from Service", new Object[0]);
                    synchronized (GPPServiceSession.this.lock) {
                        try {
                            GPPServiceSession.this.mTaskPending = z;
                            if (z && GPPServiceSession.this.mRetrieveTaskProcessingListener != null) {
                                Log.i("GPPServiceSession", "Adding retrieving listener to map for request id: " + string, new Object[0]);
                                GPPServiceSession.this.mRequestIdToListenerMap.put(string, new Pair(string2, GPPServiceSession.this.mRetrieveTaskProcessingListener));
                            }
                            GPPServiceSession.this.lock.notifyAll();
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    return;
                case 12:
                    Log.i("GPPServiceSession", "MSG_TASK_RETURN_ERROR from service", new Object[0]);
                    if (iGPPProcessingListener != null) {
                        iGPPProcessingListener.onTaskError();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public GPPServiceSession(Context context) {
        this.mContext = context;
        setupServiceCallbackMessenger();
    }

    private void closeServiceCallbackMessenger() {
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            try {
                this.mHandlerThread.join();
            } catch (InterruptedException e) {
                Log.e("GPPServiceSession", "closeServiceCallbackMessenger: Error: " + e.getMessage(), new Object[0]);
            }
            this.mHandlerThread = null;
            this.mServiceCallbackMessenger = null;
        }
    }

    private void handleRemoteException(IGPPProcessingListener iGPPProcessingListener) {
        if (!isServiceAlive()) {
            handleServiceDisconnected(false);
        } else if (iGPPProcessingListener != null) {
            iGPPProcessingListener.onTaskError();
        }
    }

    /* access modifiers changed from: private */
    public void handleServiceConnected(IBinder iBinder) {
        Log.i("GPPServiceSession", C0212a.o(new StringBuilder("onServiceConnected: Established in "), System.currentTimeMillis() - this.mBindingStartTime, " ms"), new Object[0]);
        synchronized (this) {
            this.mServiceMessenger = new Messenger(iBinder);
            Message obtain = Message.obtain((Handler) null, 1);
            obtain.replyTo = this.mServiceCallbackMessenger;
            try {
                this.mServiceMessenger.send(obtain);
                this.mState = 2;
                Log.i("GPPServiceSession", "Send MSG_CLIENT_CONNECT message to Service", new Object[0]);
            } catch (RemoteException e) {
                Log.e("GPPServiceSession", "Remote Error: " + e.getMessage(), new Object[0]);
            }
        }
        if (this.mSessionListener != null && this.mState == 2) {
            this.mSessionListener.onServiceBound();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x008d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleServiceDisconnected(boolean r8) {
        /*
            r7 = this;
            java.lang.String r0 = "IllegalArgumentException occurred while unbind service "
            java.lang.String r1 = "Exception occurred while unbind service "
            monitor-enter(r7)
            int r2 = r7.mState     // Catch:{ all -> 0x0016 }
            r3 = 3
            r4 = 0
            if (r2 != r3) goto L_0x0019
            java.lang.String r8 = "GPPServiceSession"
            java.lang.String r0 = "Service is already in unbounded state, returning "
            java.lang.Object[] r1 = new java.lang.Object[r4]     // Catch:{ all -> 0x0016 }
            com.samsung.android.sdk.globalpostprocmgr.util.Log.i(r8, r0, r1)     // Catch:{ all -> 0x0016 }
            monitor-exit(r7)     // Catch:{ all -> 0x0016 }
            return
        L_0x0016:
            r8 = move-exception
            goto L_0x00a3
        L_0x0019:
            r2 = 0
            android.content.Context r5 = r7.mContext     // Catch:{ IllegalArgumentException -> 0x0037, Exception -> 0x0035 }
            android.content.Context r5 = r5.getApplicationContext()     // Catch:{ IllegalArgumentException -> 0x0037, Exception -> 0x0035 }
            android.content.ServiceConnection r6 = r7.mServiceConnection     // Catch:{ IllegalArgumentException -> 0x0037, Exception -> 0x0035 }
            r5.unbindService(r6)     // Catch:{ IllegalArgumentException -> 0x0037, Exception -> 0x0035 }
            r7.mState = r3     // Catch:{ all -> 0x0016 }
            r7.mServiceMessenger = r2     // Catch:{ all -> 0x0016 }
            java.lang.String r0 = "GPPServiceSession"
            java.lang.String r1 = "GPP service Session unbound"
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ all -> 0x0016 }
            com.samsung.android.sdk.globalpostprocmgr.util.Log.i(r0, r1, r2)     // Catch:{ all -> 0x0016 }
            goto L_0x0082
        L_0x0033:
            r8 = move-exception
            goto L_0x0095
        L_0x0035:
            r0 = move-exception
            goto L_0x0039
        L_0x0037:
            r1 = move-exception
            goto L_0x005e
        L_0x0039:
            java.lang.String r5 = "GPPServiceSession"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0033 }
            r6.<init>(r1)     // Catch:{ all -> 0x0033 }
            java.lang.String r0 = r0.getLocalizedMessage()     // Catch:{ all -> 0x0033 }
            r6.append(r0)     // Catch:{ all -> 0x0033 }
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x0033 }
            java.lang.Object[] r1 = new java.lang.Object[r4]     // Catch:{ all -> 0x0033 }
            com.samsung.android.sdk.globalpostprocmgr.util.Log.e(r5, r0, r1)     // Catch:{ all -> 0x0033 }
            r7.mState = r3     // Catch:{ all -> 0x0016 }
            r7.mServiceMessenger = r2     // Catch:{ all -> 0x0016 }
            java.lang.String r0 = "GPPServiceSession"
            java.lang.String r1 = "GPP service Session unbound"
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ all -> 0x0016 }
            com.samsung.android.sdk.globalpostprocmgr.util.Log.i(r0, r1, r2)     // Catch:{ all -> 0x0016 }
            goto L_0x0082
        L_0x005e:
            java.lang.String r5 = "GPPServiceSession"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0033 }
            r6.<init>(r0)     // Catch:{ all -> 0x0033 }
            java.lang.String r0 = r1.getLocalizedMessage()     // Catch:{ all -> 0x0033 }
            r6.append(r0)     // Catch:{ all -> 0x0033 }
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x0033 }
            java.lang.Object[] r1 = new java.lang.Object[r4]     // Catch:{ all -> 0x0033 }
            com.samsung.android.sdk.globalpostprocmgr.util.Log.e(r5, r0, r1)     // Catch:{ all -> 0x0033 }
            r7.mState = r3     // Catch:{ all -> 0x0016 }
            r7.mServiceMessenger = r2     // Catch:{ all -> 0x0016 }
            java.lang.String r0 = "GPPServiceSession"
            java.lang.String r1 = "GPP service Session unbound"
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ all -> 0x0016 }
            com.samsung.android.sdk.globalpostprocmgr.util.Log.i(r0, r1, r2)     // Catch:{ all -> 0x0016 }
        L_0x0082:
            monitor-exit(r7)     // Catch:{ all -> 0x0016 }
            if (r8 == 0) goto L_0x008d
            com.samsung.android.sdk.globalpostprocmgr.IGPPServiceSessionListener r7 = r7.mSessionListener
            if (r7 == 0) goto L_0x0094
            r7.onServiceUnbound()
            goto L_0x0094
        L_0x008d:
            com.samsung.android.sdk.globalpostprocmgr.IGPPServiceSessionListener r7 = r7.mSessionListener
            if (r7 == 0) goto L_0x0094
            r7.onServiceError()
        L_0x0094:
            return
        L_0x0095:
            r7.mState = r3     // Catch:{ all -> 0x0016 }
            r7.mServiceMessenger = r2     // Catch:{ all -> 0x0016 }
            java.lang.String r0 = "GPPServiceSession"
            java.lang.String r1 = "GPP service Session unbound"
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ all -> 0x0016 }
            com.samsung.android.sdk.globalpostprocmgr.util.Log.i(r0, r1, r2)     // Catch:{ all -> 0x0016 }
            throw r8     // Catch:{ all -> 0x0016 }
        L_0x00a3:
            monitor-exit(r7)     // Catch:{ all -> 0x0016 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.globalpostprocmgr.GPPServiceSession.handleServiceDisconnected(boolean):void");
    }

    private boolean isServiceAlive() {
        Messenger messenger = this.mServiceMessenger;
        if (messenger == null || messenger.getBinder() == null || !this.mServiceMessenger.getBinder().pingBinder()) {
            return false;
        }
        return true;
    }

    private synchronized SendMessageResponse sendMessageInternal(Message message) {
        Log.i("GPPServiceSession", "Send Message to Service - " + message.what, new Object[0]);
        if (this.mState != 2) {
            return SendMessageResponse.ERROR_NOT_BOUND;
        }
        try {
            this.mServiceMessenger.send(message);
            return SendMessageResponse.SUCCESS;
        } catch (RemoteException e) {
            Log.e("GPPServiceSession", "sendMessage(): RemoteException occurred!" + e.getMessage(), new Object[0]);
            return SendMessageResponse.ERROR_REMOTE_EXCEPTION;
        }
    }

    private void setupServiceCallbackMessenger() {
        HandlerThread handlerThread = new HandlerThread("ServiceCallbackThread");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mServiceCallbackMessenger = new Messenger(new ServiceCallbackHandler(this.mHandlerThread.getLooper()));
    }

    public void connect() {
        Intent intent = new Intent("com.samsung.gppmanager.EXECUTE");
        intent.setClassName("com.samsung.android.globalpostprocmgr", "com.samsung.android.globalpostprocmgr.PPService");
        Log.i("GPPServiceSession", "Attempting Bind to Service", new Object[0]);
        this.mBindingStartTime = System.currentTimeMillis();
        this.mState = 1;
        boolean bindService = this.mContext.bindService(intent, this.mServiceConnection, 1);
        if (!bindService) {
            this.mState = 3;
            Log.e("GPPServiceSession", "connect: unable to connect to service", new Object[0]);
            IGPPServiceSessionListener iGPPServiceSessionListener = this.mSessionListener;
            if (iGPPServiceSessionListener != null) {
                iGPPServiceSessionListener.onServiceError();
            }
        }
        Log.d("GPPServiceSession", "STATUS: " + bindService, new Object[0]);
    }

    public void destroy() {
        closeServiceCallbackMessenger();
        this.mSessionListener = null;
        this.mRequestIdToListenerMap.clear();
        this.mContext = null;
    }

    public void disconnect() {
        Log.i("GPPServiceSession", "Unbinding Service", new Object[0]);
        Message obtain = Message.obtain((Handler) null, 2);
        obtain.replyTo = this.mServiceCallbackMessenger;
        sendMessage(obtain);
        handleServiceDisconnected(true);
    }

    public synchronized String generateRequestId() {
        String stringBuffer;
        try {
            Random random = new Random();
            int i2 = 10;
            while (true) {
                byte[] bArr = new byte[256];
                random.nextBytes(bArr);
                String str = new String(bArr, Charset.forName("UTF-8"));
                StringBuffer stringBuffer2 = new StringBuffer();
                for (int i7 = 0; i7 < str.length(); i7++) {
                    char charAt = str.charAt(i7);
                    if (((charAt >= 'a' && charAt <= 'z') || ((charAt >= 'A' && charAt <= 'Z') || (charAt >= '0' && charAt <= '9'))) && i2 > 0) {
                        stringBuffer2.append(charAt);
                        i2--;
                    }
                }
                stringBuffer = stringBuffer2.toString();
                if (!this.mRequestIdToListenerMap.containsKey(stringBuffer)) {
                    Log.i("GPPServiceSession", "request id: " + stringBuffer, new Object[0]);
                }
            }
        } finally {
            while (true) {
            }
        }
        return stringBuffer;
    }

    public Messenger getCallBackMessenger() {
        return this.mServiceCallbackMessenger;
    }

    public Context getContext() {
        return this.mContext;
    }

    public HashMap<String, Pair<String, IGPPProcessingListener>> getRequestIdToListenerMap() {
        return this.mRequestIdToListenerMap;
    }

    public synchronized boolean isServiceBound() {
        if (this.mState != 2) {
            return false;
        }
        if (isServiceAlive()) {
            android.util.Log.i("GPPServiceSession", "Service is already Bounded ");
            return true;
        }
        android.util.Log.e("GPPServiceSession", "State is bound though service is not alive. Changing state to UNBOUND");
        this.mState = 3;
        return false;
    }

    public synchronized void mapRequestIdToListener(IGPPProcessingListener iGPPProcessingListener, String str) {
        this.mRequestIdToListenerMap.put(str, new Pair((Object) null, iGPPProcessingListener));
        Log.i("GPPServiceSession", "mapRequestIdToListener size: " + this.mRequestIdToListenerMap.size(), new Object[0]);
    }

    public void sendMessage(Message message) {
        IGPPProcessingListener iGPPProcessingListener;
        int i2 = AnonymousClass2.$SwitchMap$com$samsung$android$sdk$globalpostprocmgr$GPPServiceSession$SendMessageResponse[sendMessageInternal(message).ordinal()];
        if (i2 == 1) {
            Log.i("GPPServiceSession", "sendMessage: successful.", new Object[0]);
        } else if (i2 == 2) {
            Log.e("GPPServiceSession", "sendMessage: service is not connected", new Object[0]);
            IGPPServiceSessionListener iGPPServiceSessionListener = this.mSessionListener;
            if (iGPPServiceSessionListener != null) {
                iGPPServiceSessionListener.onServiceError();
            }
        } else if (i2 == 3) {
            Log.e("GPPServiceSession", "sendMessage: Remote Exception occurred", new Object[0]);
            String string = message.getData().getString("request_id");
            if (this.mRequestIdToListenerMap.containsKey(string)) {
                iGPPProcessingListener = (IGPPProcessingListener) this.mRequestIdToListenerMap.get(string).second;
            } else {
                iGPPProcessingListener = null;
            }
            handleRemoteException(iGPPProcessingListener);
        }
    }

    public synchronized boolean sendMessageSync(Message message, IGPPProcessingListener iGPPProcessingListener) {
        synchronized (this.lock) {
            try {
                this.mRetrieveTaskProcessingListener = iGPPProcessingListener;
                sendMessage(message);
                this.lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.mTaskPending;
    }

    public void setSessionListener(IGPPServiceSessionListener iGPPServiceSessionListener) {
        this.mSessionListener = iGPPServiceSessionListener;
    }
}
