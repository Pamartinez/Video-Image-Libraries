package com.samsung.context.sdk.samsunganalytics.internal.sender;

import Dd.C0732c;
import Od.d;
import android.content.Context;
import java.util.Map;
import o1.C0246a;
import t1.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    public final Context f4194a;
    public final C0732c b;

    /* renamed from: c  reason: collision with root package name */
    public final Kd.a f4195c;
    public final i d = i.e();

    public a(Context context, C0732c cVar) {
        this.f4194a = context.getApplicationContext();
        this.b = cVar;
        this.f4195c = Kd.a.c(context, cVar);
    }

    public static b a(Map map) {
        if ("dl".equals((String) map.get("t"))) {
            return b.DEVICE;
        }
        return b.UIX;
    }

    public final void b(Map map) {
        this.f4195c.d(new d((String) map.get("t"), Long.parseLong((String) map.get("ts")), C0246a.f0(d(map), d.ONE_DEPTH), a(map)));
    }

    public abstract int c(Map map);

    public abstract Map d(Map map);
}
