package com.samsung.android.gallery.module.receiver;

import android.content.Context;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Context e;

    public /* synthetic */ d(Context context, int i2) {
        this.d = i2;
        this.e = context;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Context context = this.e;
        switch (i2) {
            case 0:
                ((AbsBroadcastReceiver) obj).registerReceiver(context);
                return;
            case 1:
                ((SettingObserver) obj).registerObserver(context);
                return;
            case 2:
                ((AbsContentObserver) obj).registerObserver(context);
                return;
            case 3:
                ((AbsBroadcastReceiver) obj).unregisterReceiver(context);
                return;
            case 4:
                ((SettingObserver) obj).unregisterObserver(context);
                return;
            default:
                ((AbsContentObserver) obj).unregisterObserver(context);
                return;
        }
    }
}
