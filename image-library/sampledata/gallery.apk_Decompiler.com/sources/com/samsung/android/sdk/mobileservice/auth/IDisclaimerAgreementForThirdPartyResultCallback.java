package com.samsung.android.sdk.mobileservice.auth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IDisclaimerAgreementForThirdPartyResultCallback extends IInterface {
    void onFailure(String str, String str2);

    void onSuccess(boolean z);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IDisclaimerAgreementForThirdPartyResultCallback {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.mobileservice.auth.IDisclaimerAgreementForThirdPartyResultCallback";
        static final int TRANSACTION_onFailure = 1;
        static final int TRANSACTION_onSuccess = 2;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IDisclaimerAgreementForThirdPartyResultCallback {
            public static IDisclaimerAgreementForThirdPartyResultCallback sDefaultImpl;
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

            public void onSuccess(boolean z) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onSuccess(z);
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

        public static IDisclaimerAgreementForThirdPartyResultCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IDisclaimerAgreementForThirdPartyResultCallback)) {
                return new Proxy(iBinder);
            }
            return (IDisclaimerAgreementForThirdPartyResultCallback) queryLocalInterface;
        }

        public static IDisclaimerAgreementForThirdPartyResultCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IDisclaimerAgreementForThirdPartyResultCallback iDisclaimerAgreementForThirdPartyResultCallback) {
            if (Proxy.sDefaultImpl != null || iDisclaimerAgreementForThirdPartyResultCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDisclaimerAgreementForThirdPartyResultCallback;
            return true;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            boolean z;
            if (i2 == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onFailure(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i2 == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                if (parcel.readInt() != 0) {
                    z = true;
                } else {
                    z = false;
                }
                onSuccess(z);
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
    public static class Default implements IDisclaimerAgreementForThirdPartyResultCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onSuccess(boolean z) {
        }

        public void onFailure(String str, String str2) {
        }
    }
}
