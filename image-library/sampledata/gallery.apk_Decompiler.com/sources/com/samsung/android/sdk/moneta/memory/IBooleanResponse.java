package com.samsung.android.sdk.moneta.memory;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IBooleanResponse extends IInterface {
    public static final String DESCRIPTOR = "com.samsung.android.sdk.moneta.memory.IBooleanResponse";

    void onError(int i2, String str);

    void onResponse(boolean z);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IBooleanResponse {
        static final int TRANSACTION_onError = 2;
        static final int TRANSACTION_onResponse = 1;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IBooleanResponse {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBooleanResponse.DESCRIPTOR;
            }

            public void onError(int i2, String str) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBooleanResponse.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onResponse(boolean z) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBooleanResponse.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IBooleanResponse.DESCRIPTOR);
        }

        public static IBooleanResponse asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IBooleanResponse.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IBooleanResponse)) {
                return new Proxy(iBinder);
            }
            return (IBooleanResponse) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            boolean z;
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IBooleanResponse.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IBooleanResponse.DESCRIPTOR);
                return true;
            }
            if (i2 == 1) {
                if (parcel.readInt() != 0) {
                    z = true;
                } else {
                    z = false;
                }
                onResponse(z);
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
    public static class Default implements IBooleanResponse {
        public IBinder asBinder() {
            return null;
        }

        public void onResponse(boolean z) {
        }

        public void onError(int i2, String str) {
        }
    }
}
