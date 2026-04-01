package be;

import android.os.IBinder;
import android.os.Parcel;

/* renamed from: be.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0913a implements C0915c {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f3997a;

    public final int a(String str, String str2, String str3) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.sec.android.diagmonagent.sa.IDMAInterface");
            obtain.writeInt(0);
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeString(str3);
            this.f3997a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final IBinder asBinder() {
        return this.f3997a;
    }

    public final int b(long j2, String str, String str2, String str3) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.sec.android.diagmonagent.sa.IDMAInterface");
            obtain.writeInt(0);
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeLong(j2);
            obtain.writeString(str3);
            this.f3997a.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
