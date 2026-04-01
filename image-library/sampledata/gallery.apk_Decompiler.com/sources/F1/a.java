package F1;

import E1.b;
import O1.d;
import O1.e;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Status;
import ge.s1;
import t1.C0276a;
import v1.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a extends Binder implements IInterface {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f227a;

    public /* synthetic */ a() {
        this.f227a = 2;
    }

    public final IBinder asBinder() {
        int i2 = this.f227a;
        return this;
    }

    public boolean c(int i2, Parcel parcel, Parcel parcel2) {
        return false;
    }

    public final boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
        switch (this.f227a) {
            case 0:
                if (i2 <= 16777215) {
                    parcel.enforceInterface(getInterfaceDescriptor());
                } else if (super.onTransact(i2, parcel, parcel2, i7)) {
                    return true;
                }
                return c(i2, parcel, parcel2);
            case 1:
                if (i2 <= 16777215) {
                    parcel.enforceInterface(getInterfaceDescriptor());
                } else if (super.onTransact(i2, parcel, parcel2, i7)) {
                    return true;
                }
                return c(i2, parcel, parcel2);
            default:
                if (i2 <= 16777215) {
                    parcel.enforceInterface(getInterfaceDescriptor());
                } else if (super.onTransact(i2, parcel, parcel2, i7)) {
                    return true;
                }
                switch (i2) {
                    case 3:
                        C0276a aVar = (C0276a) b.a(parcel, C0276a.CREATOR);
                        O1.b bVar = (O1.b) b.a(parcel, O1.b.CREATOR);
                        b.b(parcel);
                        break;
                    case 4:
                        Status status = (Status) b.a(parcel, Status.CREATOR);
                        b.b(parcel);
                        break;
                    case 6:
                        Status status2 = (Status) b.a(parcel, Status.CREATOR);
                        b.b(parcel);
                        break;
                    case 7:
                        Status status3 = (Status) b.a(parcel, Status.CREATOR);
                        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) b.a(parcel, GoogleSignInAccount.CREATOR);
                        b.b(parcel);
                        break;
                    case 8:
                        b.b(parcel);
                        r rVar = (r) this;
                        rVar.f1977c.post(new s1(5, rVar, (e) b.a(parcel, e.CREATOR)));
                        break;
                    case 9:
                        d dVar = (d) b.a(parcel, d.CREATOR);
                        b.b(parcel);
                        break;
                    default:
                        return false;
                }
                parcel2.writeNoException();
                return true;
        }
    }

    public a(String str, int i2) {
        this.f227a = i2;
        switch (i2) {
            case 1:
                attachInterface(this, str);
                return;
            default:
                attachInterface(this, str);
                return;
        }
    }
}
