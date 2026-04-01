package Sd;

import R6.c;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import ge.W0;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3701a = 0;
    public Object b;

    /* renamed from: c  reason: collision with root package name */
    public final Object f3702c;

    public g(W0 w02) {
        this.f3702c = w02;
    }

    public final void onReceive(Context context, Intent intent) {
        String str;
        switch (this.f3701a) {
            case 0:
                context.unregisterReceiver(this);
                new Thread(new c(this, intent, (Consumer) this.b, 3)).start();
                return;
            default:
                Uri data = intent.getData();
                if (data != null) {
                    str = data.getSchemeSpecificPart();
                } else {
                    str = null;
                }
                if ("com.google.android.gms".equals(str)) {
                    Object obj = ((W0) this.f3702c).f;
                    throw null;
                }
                return;
        }
    }

    public g(h hVar, Consumer consumer) {
        this.f3702c = hVar;
        this.b = consumer;
    }
}
