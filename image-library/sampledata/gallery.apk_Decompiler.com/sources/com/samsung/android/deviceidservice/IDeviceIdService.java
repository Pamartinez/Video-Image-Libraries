package com.samsung.android.deviceidservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IDeviceIdService extends IInterface {
    public static final String DESCRIPTOR = "com.samsung.android.deviceidservice.IDeviceIdService";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IDeviceIdService {
        public IBinder asBinder() {
            return null;
        }

        public String getAAID(String str) {
            return null;
        }

        public String getOAID() {
            return null;
        }

        public String getVAID(String str) {
            return null;
        }
    }

    String getAAID(String str);

    String getOAID();

    String getVAID(String str);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IDeviceIdService {
        static final int TRANSACTION_getAAID = 3;
        static final int TRANSACTION_getOAID = 1;
        static final int TRANSACTION_getVAID = 2;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IDeviceIdService {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getAAID(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDeviceIdService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IDeviceIdService.DESCRIPTOR;
            }

            public String getOAID() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDeviceIdService.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getVAID(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDeviceIdService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IDeviceIdService.DESCRIPTOR);
        }

        public static IDeviceIdService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IDeviceIdService.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IDeviceIdService)) {
                return new Proxy(iBinder);
            }
            return (IDeviceIdService) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IDeviceIdService.DESCRIPTOR);
            }
            if (i2 != 1598968902) {
                if (i2 == 1) {
                    String oaid = getOAID();
                    parcel2.writeNoException();
                    parcel2.writeString(oaid);
                } else if (i2 == 2) {
                    String vaid = getVAID(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(vaid);
                } else if (i2 != 3) {
                    return super.onTransact(i2, parcel, parcel2, i7);
                } else {
                    String aaid = getAAID(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(aaid);
                }
                return true;
            }
            parcel2.writeString(IDeviceIdService.DESCRIPTOR);
            return true;
        }

        public IBinder asBinder() {
            return this;
        }
    }
}
