package com.samsung.android.sdk.scs.base.connection;

import android.content.ComponentName;
import android.os.IBinder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface InternalServiceConnectionListener {
    void onConnected(ComponentName componentName, IBinder iBinder);

    void onDisconnected(ComponentName componentName);

    void onError() {
    }
}
