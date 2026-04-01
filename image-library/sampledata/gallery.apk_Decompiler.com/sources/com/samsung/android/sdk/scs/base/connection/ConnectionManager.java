package com.samsung.android.sdk.scs.base.connection;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ConnectionManager {
    private static final String TAG = "ScsApi@ConnectionManager";
    private Context mContext;
    private InternalServiceConnectionListener mInternalServiceConnectionListener;
    private boolean mIsConnected = false;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(ConnectionManager.TAG, "onServiceConnected " + componentName);
            ConnectionManager.this.notifyServiceConnection(1, componentName, iBinder);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(ConnectionManager.TAG, "onServiceDisconnected " + componentName);
            ConnectionManager.this.notifyServiceConnection(2, (ComponentName) null, (IBinder) null);
        }
    };

    /* access modifiers changed from: private */
    public void notifyServiceConnection(int i2, ComponentName componentName, IBinder iBinder) {
        Log.d(TAG, "notifyServiceConnection : " + i2);
        InternalServiceConnectionListener internalServiceConnectionListener = this.mInternalServiceConnectionListener;
        if (internalServiceConnectionListener == null) {
            return;
        }
        if (i2 == 1) {
            this.mIsConnected = true;
            internalServiceConnectionListener.onConnected(componentName, iBinder);
        } else if (i2 == 2) {
            this.mIsConnected = false;
            internalServiceConnectionListener.onDisconnected(componentName);
        } else if (i2 == 3) {
            this.mIsConnected = false;
            internalServiceConnectionListener.onError();
        }
    }

    public boolean connect(Context context, Intent intent, InternalServiceConnectionListener internalServiceConnectionListener) {
        setConnectionStatusListener(internalServiceConnectionListener);
        if (isServiceConnected()) {
            Log.d(TAG, "just return already bound service obj");
            return true;
        }
        boolean connectToService = connectToService(context, intent);
        Log.d(TAG, "connectToService result : " + connectToService);
        if (!connectToService) {
            notifyServiceConnection(3, (ComponentName) null, (IBinder) null);
        }
        return connectToService;
    }

    public boolean connectToService(Context context, Intent intent) {
        if (context == null) {
            Log.e(TAG, "Context is null");
            return false;
        } else if (intent == null) {
            Log.e(TAG, "Intent is null");
            return false;
        } else {
            Log.d(TAG, "connectToService mIsConnected = " + this.mIsConnected);
            if (!this.mIsConnected) {
                Log.d(TAG, "Binding service with app context");
                this.mContext = context;
                return context.bindService(intent, this.mServiceConnection, 1);
            }
            Log.d(TAG, "already bound");
            return true;
        }
    }

    public void disconnect() {
        Log.d(TAG, "disConnectService mIsConnected = " + this.mIsConnected);
        if (this.mIsConnected) {
            Log.d(TAG, "unbindService");
            this.mIsConnected = false;
            this.mContext.unbindService(this.mServiceConnection);
            notifyServiceConnection(2, (ComponentName) null, (IBinder) null);
        }
    }

    public boolean isServiceConnected() {
        Log.d(TAG, "isServiceConnected = " + this.mIsConnected);
        return this.mIsConnected;
    }

    public void setConnectionStatusListener(InternalServiceConnectionListener internalServiceConnectionListener) {
        this.mInternalServiceConnectionListener = internalServiceConnectionListener;
    }
}
