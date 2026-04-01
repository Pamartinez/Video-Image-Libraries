package com.samsung.android.sdk.mobileservice.auth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IValidationResultCallback extends IInterface {
    void onFailure(String str, String str2, boolean z, boolean z3, boolean z7, boolean z9);

    void onSuccess();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IValidationResultCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onSuccess() {
        }

        public void onFailure(String str, String str2, boolean z, boolean z3, boolean z7, boolean z9) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IValidationResultCallback {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.mobileservice.auth.IValidationResultCallback";
        static final int TRANSACTION_onFailure = 1;
        static final int TRANSACTION_onSuccess = 2;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IValidationResultCallback {
            public static IValidationResultCallback sDefaultImpl;
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

            /* JADX INFO: finally extract failed */
            public void onFailure(String str, String str2, boolean z, boolean z3, boolean z7, boolean z9) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(z3 ? 1 : 0);
                    obtain.writeInt(z7 ? 1 : 0);
                    boolean z10 = z9;
                    obtain.writeInt(z10 ? 1 : 0);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onFailure(str, str2, z, z3, z7, z10);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public void onSuccess() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
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

        public static IValidationResultCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IValidationResultCallback)) {
                return new Proxy(iBinder);
            }
            return (IValidationResultCallback) queryLocalInterface;
        }

        public static IValidationResultCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IValidationResultCallback iValidationResultCallback) {
            if (Proxy.sDefaultImpl != null || iValidationResultCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iValidationResultCallback;
            return true;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            boolean z;
            boolean z3;
            boolean z7;
            boolean z9;
            if (i2 == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                if (parcel.readInt() != 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (parcel.readInt() != 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (parcel.readInt() != 0) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                if (parcel.readInt() != 0) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                onFailure(readString, readString2, z, z3, z7, z9);
                parcel2.writeNoException();
                return true;
            } else if (i2 == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onSuccess();
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
