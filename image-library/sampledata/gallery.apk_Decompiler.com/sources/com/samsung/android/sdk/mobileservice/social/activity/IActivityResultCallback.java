package com.samsung.android.sdk.mobileservice.social.activity;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IActivityResultCallback extends IInterface {
    void onFailure(long j2, String str);

    void onSuccess();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IActivityResultCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onSuccess() {
        }

        public void onFailure(long j2, String str) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IActivityResultCallback {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.mobileservice.social.activity.IActivityResultCallback";
        static final int TRANSACTION_onFailure = 2;
        static final int TRANSACTION_onSuccess = 1;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IActivityResultCallback {
            public static IActivityResultCallback sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public void onFailure(long j2, String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j2);
                    obtain.writeString(str);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onFailure(j2, str);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onSuccess() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onSuccess();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IActivityResultCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IActivityResultCallback)) {
                return new Proxy(iBinder);
            }
            return (IActivityResultCallback) queryLocalInterface;
        }

        public static IActivityResultCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IActivityResultCallback iActivityResultCallback) {
            if (Proxy.sDefaultImpl != null || iActivityResultCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iActivityResultCallback;
            return true;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onSuccess();
                parcel2.writeNoException();
                return true;
            } else if (i2 == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onFailure(parcel.readLong(), parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i2 != 1598968902) {
                return super.onTransact(i2, parcel, parcel2, i7);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        public IBinder asBinder() {
            return this;
        }
    }
}
