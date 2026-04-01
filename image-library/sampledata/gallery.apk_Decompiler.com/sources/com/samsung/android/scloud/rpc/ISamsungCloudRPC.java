package com.samsung.android.scloud.rpc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISamsungCloudRPC extends IInterface {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements ISamsungCloudRPC {

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements ISamsungCloudRPC {
            public static ISamsungCloudRPC sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void showSetting(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.scloud.rpc.ISamsungCloudRPC");
                    obtain.writeString(str);
                    if (this.mRemote.transact(5, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().showSetting(str);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static ISamsungCloudRPC asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.android.scloud.rpc.ISamsungCloudRPC");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ISamsungCloudRPC)) {
                return new Proxy(iBinder);
            }
            return (ISamsungCloudRPC) queryLocalInterface;
        }

        public static ISamsungCloudRPC getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }

    void showSetting(String str);
}
