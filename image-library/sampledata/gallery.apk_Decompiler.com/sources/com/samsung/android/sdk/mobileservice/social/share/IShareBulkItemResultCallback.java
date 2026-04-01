package com.samsung.android.sdk.mobileservice.social.share;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IShareBulkItemResultCallback extends IInterface {
    void onFailure(long j2, String str);

    void onProgress(Bundle bundle);

    void onSuccess();

    void onUploadComplete(Bundle bundle);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IShareBulkItemResultCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onSuccess() {
        }

        public void onProgress(Bundle bundle) {
        }

        public void onUploadComplete(Bundle bundle) {
        }

        public void onFailure(long j2, String str) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IShareBulkItemResultCallback {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.mobileservice.social.share.IShareBulkItemResultCallback";
        static final int TRANSACTION_onFailure = 2;
        static final int TRANSACTION_onProgress = 3;
        static final int TRANSACTION_onSuccess = 1;
        static final int TRANSACTION_onUploadComplete = 4;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IShareBulkItemResultCallback {
            public static IShareBulkItemResultCallback sDefaultImpl;
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

            public void onProgress(Bundle bundle) {
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
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onProgress(bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
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

            public void onUploadComplete(Bundle bundle) {
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
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onUploadComplete(bundle);
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

        public static IShareBulkItemResultCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IShareBulkItemResultCallback)) {
                return new Proxy(iBinder);
            }
            return (IShareBulkItemResultCallback) queryLocalInterface;
        }

        public static IShareBulkItemResultCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IShareBulkItemResultCallback iShareBulkItemResultCallback) {
            if (Proxy.sDefaultImpl != null || iShareBulkItemResultCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iShareBulkItemResultCallback;
            return true;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: android.os.Bundle} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r5, android.os.Parcel r6, android.os.Parcel r7, int r8) {
            /*
                r4 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                java.lang.String r2 = "com.samsung.android.sdk.mobileservice.social.share.IShareBulkItemResultCallback"
                if (r5 == r0) goto L_0x0067
                if (r5 == r1) goto L_0x005d
                r0 = 2
                if (r5 == r0) goto L_0x004b
                r0 = 3
                r3 = 0
                if (r5 == r0) goto L_0x0032
                r0 = 4
                if (r5 == r0) goto L_0x0019
                boolean r4 = super.onTransact(r5, r6, r7, r8)
                return r4
            L_0x0019:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x002b
                android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r3 = r5
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x002b:
                r4.onUploadComplete(r3)
                r7.writeNoException()
                return r1
            L_0x0032:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x0044
                android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r3 = r5
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x0044:
                r4.onProgress(r3)
                r7.writeNoException()
                return r1
            L_0x004b:
                r6.enforceInterface(r2)
                long r2 = r6.readLong()
                java.lang.String r5 = r6.readString()
                r4.onFailure(r2, r5)
                r7.writeNoException()
                return r1
            L_0x005d:
                r6.enforceInterface(r2)
                r4.onSuccess()
                r7.writeNoException()
                return r1
            L_0x0067:
                r7.writeString(r2)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.social.share.IShareBulkItemResultCallback.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        public IBinder asBinder() {
            return this;
        }
    }
}
