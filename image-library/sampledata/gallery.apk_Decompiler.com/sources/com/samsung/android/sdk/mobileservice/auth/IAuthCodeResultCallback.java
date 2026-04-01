package com.samsung.android.sdk.mobileservice.auth;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IAuthCodeResultCallback extends IInterface {
    void onFailure(String str, String str2);

    void onSuccess(Bundle bundle);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IAuthCodeResultCallback {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.mobileservice.auth.IAuthCodeResultCallback";
        static final int TRANSACTION_onFailure = 1;
        static final int TRANSACTION_onSuccess = 2;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IAuthCodeResultCallback {
            public static IAuthCodeResultCallback sDefaultImpl;
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

            public void onFailure(String str, String str2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onFailure(str, str2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onSuccess(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onSuccess(bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAuthCodeResultCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IAuthCodeResultCallback)) {
                return new Proxy(iBinder);
            }
            return (IAuthCodeResultCallback) queryLocalInterface;
        }

        public static IAuthCodeResultCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IAuthCodeResultCallback iAuthCodeResultCallback) {
            if (Proxy.sDefaultImpl != null || iAuthCodeResultCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAuthCodeResultCallback;
            return true;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            Bundle bundle;
            if (i2 == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onFailure(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i2 == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                } else {
                    bundle = null;
                }
                onSuccess(bundle);
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

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IAuthCodeResultCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onSuccess(Bundle bundle) {
        }

        public void onFailure(String str, String str2) {
        }
    }
}
