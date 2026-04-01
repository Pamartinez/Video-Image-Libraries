package v1;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import ge.W0;
import t1.C0276a;
import t1.C0278c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class n {

    /* renamed from: a  reason: collision with root package name */
    public final int f1970a;

    public n(int i2) {
        this.f1970a = i2;
    }

    public static Status g(RemoteException remoteException) {
        return new Status(19, remoteException.getClass().getSimpleName() + ": " + remoteException.getLocalizedMessage(), (PendingIntent) null, (C0276a) null);
    }

    public abstract boolean a(k kVar);

    public abstract C0278c[] b(k kVar);

    public abstract void c(Status status);

    public abstract void d(Exception exc);

    public abstract void e(k kVar);

    public abstract void f(W0 w02, boolean z);
}
