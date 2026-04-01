package Jd;

import G0.c;
import a.C0068a;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import be.C0913a;
import be.C0914b;
import be.C0915c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ c f3472a;
    public final /* synthetic */ b b;

    public a(b bVar, c cVar) {
        this.b = bVar;
        this.f3472a = cVar;
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [be.a, java.lang.Object] */
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        C0915c cVar;
        Parcel obtain;
        Parcel obtain2;
        b bVar = this.b;
        try {
            int i2 = C0914b.f3998a;
            if (iBinder == null) {
                cVar = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.sec.android.diagmonagent.sa.IDMAInterface");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof C0915c)) {
                    ? obj = new Object();
                    obj.f3997a = iBinder;
                    cVar = obj;
                } else {
                    cVar = (C0915c) queryLocalInterface;
                }
            }
            bVar.d = cVar;
            C0913a aVar = (C0913a) cVar;
            aVar.getClass();
            obtain = Parcel.obtain();
            obtain2 = Parcel.obtain();
            obtain.writeInterfaceToken("com.sec.android.diagmonagent.sa.IDMAInterface");
            aVar.f3997a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            String readString = obtain2.readString();
            obtain2.recycle();
            obtain.recycle();
            if (readString == null) {
                bVar.k();
                bVar.f3473a = true;
                C0068a.c("DMABinder", "Token failed");
                return;
            }
            bVar.f3473a = false;
            this.f3472a.onResult(readString);
            C0068a.c("DMABinder", "DMA connected");
        } catch (Exception e) {
            bVar.k();
            bVar.f3473a = true;
            C0068a.K("failed to connect binder" + e.getMessage());
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
            throw th;
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.b.d = null;
    }
}
