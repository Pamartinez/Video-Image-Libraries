package com.samsung.android.sdk.mobileservice.auth;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IAccessTokenResultCallback extends IInterface {
    void onFailure(String str, String str2);

    void onSuccess(Bundle bundle, Bundle bundle2);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IAccessTokenResultCallback {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.mobileservice.auth.IAccessTokenResultCallback";
        static final int TRANSACTION_onFailure = 1;
        static final int TRANSACTION_onSuccess = 2;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IAccessTokenResultCallback {
            public static IAccessTokenResultCallback sDefaultImpl;
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

            public void onSuccess(Bundle bundle, Bundle bundle2) {
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
                    if (bundle2 != null) {
                        obtain.writeInt(1);
                        bundle2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onSuccess(bundle, bundle2);
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

        public static IAccessTokenResultCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IAccessTokenResultCallback)) {
                return new Proxy(iBinder);
            }
            return (IAccessTokenResultCallback) queryLocalInterface;
        }

        public static IAccessTokenResultCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IAccessTokenResultCallback iAccessTokenResultCallback) {
            if (Proxy.sDefaultImpl != null || iAccessTokenResultCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAccessTokenResultCallback;
            return true;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: android.os.Bundle} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) {
            /*
                r3 = this;
                java.lang.String r0 = "com.samsung.android.sdk.mobileservice.auth.IAccessTokenResultCallback"
                r1 = 1
                if (r4 == r1) goto L_0x0040
                r2 = 2
                if (r4 == r2) goto L_0x0016
                r2 = 1598968902(0x5f4e5446, float:1.4867585E19)
                if (r4 == r2) goto L_0x0012
                boolean r3 = super.onTransact(r4, r5, r6, r7)
                return r3
            L_0x0012:
                r6.writeString(r0)
                return r1
            L_0x0016:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                r7 = 0
                if (r4 == 0) goto L_0x0029
                android.os.Parcelable$Creator r4 = android.os.Bundle.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                android.os.Bundle r4 = (android.os.Bundle) r4
                goto L_0x002a
            L_0x0029:
                r4 = r7
            L_0x002a:
                int r0 = r5.readInt()
                if (r0 == 0) goto L_0x0039
                android.os.Parcelable$Creator r7 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r7.createFromParcel(r5)
                r7 = r5
                android.os.Bundle r7 = (android.os.Bundle) r7
            L_0x0039:
                r3.onSuccess(r4, r7)
                r6.writeNoException()
                return r1
            L_0x0040:
                r5.enforceInterface(r0)
                java.lang.String r4 = r5.readString()
                java.lang.String r5 = r5.readString()
                r3.onFailure(r4, r5)
                r6.writeNoException()
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.auth.IAccessTokenResultCallback.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        public IBinder asBinder() {
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IAccessTokenResultCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onFailure(String str, String str2) {
        }

        public void onSuccess(Bundle bundle, Bundle bundle2) {
        }
    }
}
