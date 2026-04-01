package y1;

import E1.c;
import Fd.C0744a;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.internal.a;
import t1.C0278c;
import v1.k;
import w1.j;

/* renamed from: y1.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0356c extends a {
    public final j y;

    public C0356c(Context context, Looper looper, C0744a aVar, j jVar, k kVar, k kVar2) {
        super(context, looper, 270, aVar, kVar, kVar2);
        this.y = jVar;
    }

    public final int j() {
        return 203400000;
    }

    public final IInterface m(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.service.IClientTelemetryService");
        if (queryLocalInterface instanceof C0354a) {
            return (C0354a) queryLocalInterface;
        }
        return new E1.a(iBinder, "com.google.android.gms.common.internal.service.IClientTelemetryService", 0);
    }

    public final C0278c[] n() {
        return c.b;
    }

    public final Bundle o() {
        this.y.getClass();
        return new Bundle();
    }

    public final String q() {
        return "com.google.android.gms.common.internal.service.IClientTelemetryService";
    }

    public final String r() {
        return "com.google.android.gms.common.telemetry.service.START";
    }

    public final boolean s() {
        return true;
    }
}
