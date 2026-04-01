package io.grpc.binder;

import A0.l;
import B1.a;
import E2.k;
import G0.c;
import He.F;
import android.content.Context;
import android.content.Intent;
import androidx.core.content.ContextCompat;
import ee.C0984q;
import ge.I0;
import ge.Z;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends C0984q {
    public final I0 d;
    public final Executor e;
    public final c f = new c(12, (Object) Z.m);
    public com.samsung.context.sdk.samsunganalytics.internal.sender.c g;

    /* renamed from: h  reason: collision with root package name */
    public final e f4627h;

    /* renamed from: i  reason: collision with root package name */
    public final b f4628i;

    public d(a aVar, Context context, ie.c cVar) {
        String str;
        F.n(context, "sourceContext");
        this.e = ContextCompat.getMainExecutor(context);
        int i2 = h.f4630a;
        this.g = new com.samsung.context.sdk.samsunganalytics.internal.sender.c(15);
        this.f4627h = e.f4629a;
        this.f4628i = b.b;
        Intent intent = aVar.d;
        if (intent.getPackage() != null) {
            str = intent.getPackage();
        } else {
            str = intent.getComponent().getPackageName();
        }
        I0 i02 = new I0(aVar, str, new l(this, context, cVar, 18));
        this.d = i02;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        if (timeUnit.toDays(60) >= 30) {
            i02.m = -1;
        } else {
            i02.m = Math.max(timeUnit.toMillis(60), I0.D);
        }
    }

    public final String toString() {
        k V = a.V(this);
        V.a(this.d, "delegate");
        return V.toString();
    }
}
