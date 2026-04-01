package Sd;

import D0.e;
import Ob.a;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class u extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f3716a;
    public final /* synthetic */ Consumer b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ e f3717c;

    public u(e eVar, String str, Consumer consumer) {
        this.f3717c = eVar;
        this.f3716a = str;
        this.b = consumer;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            String stringExtra = intent.getStringExtra("external_event_name");
            Log.d((String) this.f3717c.f, "ExternalEventReceiver-onReceive: " + action + GlobalPostProcInternalPPInterface.SPLIT_REGEX + stringExtra);
            if (this.f3716a.equals(stringExtra)) {
                new Thread(new a(12, intent, this.b)).start();
            }
        }
    }
}
