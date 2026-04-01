package com.samsung.android.sdk.moneta.memory;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ICountResponse extends IInterface {
    public static final String DESCRIPTOR = "com.samsung.android.sdk.moneta.memory.ICountResponse";

    void onError(int i2, String str);

    void onResponse(int i2);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements ICountResponse {
        static final int TRANSACTION_onError = 2;
        static final int TRANSACTION_onResponse = 1;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements ICountResponse {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICountResponse.DESCRIPTOR;
            }

            public void onError(int i2, String str) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICountResponse.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onResponse(int i2) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICountResponse.DESCRIPTOR);
                    obtain.writeInt(i2);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, ICountResponse.DESCRIPTOR);
        }

        public static ICountResponse asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ICountResponse.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ICountResponse)) {
                return new Proxy(iBinder);
            }
            return (ICountResponse) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(ICountResponse.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(ICountResponse.DESCRIPTOR);
                return true;
            }
            if (i2 == 1) {
                onResponse(parcel.readInt());
            } else if (i2 != 2) {
                return super.onTransact(i2, parcel, parcel2, i7);
            } else {
                onError(parcel.readInt(), parcel.readString());
            }
            return true;
        }

        public IBinder asBinder() {
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements ICountResponse {
        public IBinder asBinder() {
            return null;
        }

        public void onResponse(int i2) {
        }

        public void onError(int i2, String str) {
        }
    }
}
