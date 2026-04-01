package E1;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a implements IInterface {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f161a;
    public final IBinder b;

    /* renamed from: c  reason: collision with root package name */
    public final String f162c;

    public /* synthetic */ a(IBinder iBinder, String str, int i2) {
        this.f161a = i2;
        this.b = iBinder;
        this.f162c = str;
    }

    public Parcel a(Parcel parcel, int i2) {
        parcel = Parcel.obtain();
        try {
            this.b.transact(i2, parcel, parcel, 0);
            parcel.readException();
            return parcel;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            parcel.recycle();
        }
    }

    public final IBinder asBinder() {
        switch (this.f161a) {
            case 0:
                return this.b;
            case 1:
                return this.b;
            default:
                return this.b;
        }
    }

    public Parcel b(Parcel parcel, int i2) {
        parcel = Parcel.obtain();
        try {
            this.b.transact(i2, parcel, parcel, 0);
            parcel.readException();
            return parcel;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            parcel.recycle();
        }
    }

    public Parcel c() {
        switch (this.f161a) {
            case 1:
                Parcel obtain = Parcel.obtain();
                obtain.writeInterfaceToken(this.f162c);
                return obtain;
            default:
                Parcel obtain2 = Parcel.obtain();
                obtain2.writeInterfaceToken(this.f162c);
                return obtain2;
        }
    }

    public void d(Parcel parcel, int i2) {
        Parcel obtain = Parcel.obtain();
        try {
            this.b.transact(i2, parcel, obtain, 0);
            obtain.readException();
        } finally {
            parcel.recycle();
            obtain.recycle();
        }
    }
}
