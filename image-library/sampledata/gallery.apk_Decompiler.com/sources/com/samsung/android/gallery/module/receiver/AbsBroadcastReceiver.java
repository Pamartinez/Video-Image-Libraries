package com.samsung.android.gallery.module.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import com.samsung.android.gallery.support.utils.AndroidCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class AbsBroadcastReceiver extends BroadcastReceiver {
    protected final String TAG = getClass().getSimpleName();

    public abstract void registerReceiver(Context context);

    public void unregisterReceiver(Context context) {
        AndroidCompat.unregisterReceiver(context, this);
    }
}
