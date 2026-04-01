package com.samsung.android.gallery.compat.oaid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import c0.C0086a;
import com.samsung.android.deviceidservice.IDeviceIdService;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SamsungDeviceId {
    private final Object LOCK = new Object();
    /* access modifiers changed from: private */
    public volatile String mOaid;
    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            boolean z;
            try {
                String unused = SamsungDeviceId.this.mOaid = IDeviceIdService.Stub.asInterface(iBinder).getOAID();
                StringBuilder sb2 = new StringBuilder("onServiceConnected {");
                if (SamsungDeviceId.this.mOaid != null) {
                    z = true;
                } else {
                    z = false;
                }
                sb2.append(z);
                sb2.append("}");
                Log.d("SamsungDeviceId", sb2.toString());
            } catch (Error | Exception e) {
                C0212a.y(e, new StringBuilder("onServiceConnected failed e="), "SamsungDeviceId");
            }
            SamsungDeviceId.this.terminate();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("SamsungDeviceId", "onServiceDisconnected");
        }
    };

    public boolean bindService(Context context) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.samsung.android.deviceidservice", "com.samsung.android.deviceidservice.DeviceIdService");
            if (context.bindService(intent, this.mServiceConnection, 1)) {
                return true;
            }
            Log.w("SamsungDeviceId", "bindService failed");
            return false;
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("bindService failed. e="), "SamsungDeviceId");
            return false;
        }
    }

    public String loadValue(Context context) {
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        if (bindService(context)) {
            synchronized (this.LOCK) {
                try {
                    this.LOCK.wait(1000);
                } catch (InterruptedException unused) {
                    Log.d("SamsungDeviceId", "loadValue#wait interrupted");
                }
            }
            unbindService(context);
        }
        StringBuilder sb2 = new StringBuilder("loadValue {");
        if (this.mOaid == null) {
            str = "null";
        } else if (this.mOaid.length() > 4) {
            str = this.mOaid.substring(0, 4) + "...";
        } else {
            str = this.mOaid;
        }
        Log.d("SamsungDeviceId", C0086a.j(currentTimeMillis, str, "} +", sb2));
        return this.mOaid;
    }

    public final void terminate() {
        synchronized (this.LOCK) {
            this.LOCK.notify();
        }
    }

    public void unbindService(Context context) {
        try {
            context.unbindService(this.mServiceConnection);
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("unbindService failed. e="), "SamsungDeviceId");
        }
    }
}
