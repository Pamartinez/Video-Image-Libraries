package G1;

import Fd.C0744a;
import J1.b;
import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.common.internal.a;
import t1.C0278c;
import v1.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends a {

    /* renamed from: A  reason: collision with root package name */
    public final SimpleArrayMap f286A = new SimpleArrayMap();
    public final SimpleArrayMap y = new SimpleArrayMap();
    public final SimpleArrayMap z = new SimpleArrayMap();

    public f(Context context, Looper looper, C0744a aVar, k kVar, k kVar2) {
        super(context, looper, 23, aVar, kVar, kVar2);
    }

    public final int j() {
        return 11717000;
    }

    public final /* synthetic */ IInterface m(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        if (queryLocalInterface instanceof g) {
            return (g) queryLocalInterface;
        }
        return new g(iBinder);
    }

    public final C0278c[] n() {
        return b.f366a;
    }

    public final String q() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    public final String r() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    public final void t() {
        System.currentTimeMillis();
        synchronized (this.y) {
            this.y.clear();
        }
        synchronized (this.z) {
            this.z.clear();
        }
        synchronized (this.f286A) {
            this.f286A.clear();
        }
    }
}
