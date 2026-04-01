package w1;

import android.os.Bundle;
import com.google.android.gms.common.internal.a;
import t1.C0276a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class o {

    /* renamed from: a  reason: collision with root package name */
    public Boolean f2011a;
    public boolean b = false;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ a f2012c;
    public final int d;
    public final Bundle e;
    public final /* synthetic */ a f;

    public o(a aVar, int i2, Bundle bundle) {
        this.f = aVar;
        Boolean bool = Boolean.TRUE;
        this.f2012c = aVar;
        this.f2011a = bool;
        this.d = i2;
        this.e = bundle;
    }

    public abstract void a(C0276a aVar);

    public abstract boolean b();

    public final void c() {
        synchronized (this) {
            this.f2011a = null;
        }
        synchronized (this.f2012c.k) {
            this.f2012c.k.remove(this);
        }
    }
}
