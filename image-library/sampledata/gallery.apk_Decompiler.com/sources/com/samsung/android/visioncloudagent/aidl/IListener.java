package com.samsung.android.visioncloudagent.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IListener extends IInterface {
    public static final String DESCRIPTOR = "com.samsung.android.visioncloudagent.aidl.IListener";

    void onNotifyToApp(String str, int i2);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IListener {
        static final int TRANSACTION_onNotifyToApp = 1;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IListener {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IListener.DESCRIPTOR;
            }

            public void onNotifyToApp(String str, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IListener.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IListener.DESCRIPTOR);
        }

        public static IListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IListener.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IListener)) {
                return new Proxy(iBinder);
            }
            return (IListener) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IListener.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IListener.DESCRIPTOR);
                return true;
            } else if (i2 != 1) {
                return super.onTransact(i2, parcel, parcel2, i7);
            } else {
                onNotifyToApp(parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
        }

        public IBinder asBinder() {
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IListener {
        public IBinder asBinder() {
            return null;
        }

        public void onNotifyToApp(String str, int i2) {
        }
    }
}
