package com.samsung.android.sdk.mobileservice.social.feedback;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IFeedbackBundlePartialResultCallback extends IInterface {
    void onFailure(long j2, String str);

    void onPartialResult(Bundle bundle);

    void onSuccess(Bundle bundle);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IFeedbackBundlePartialResultCallback {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.mobileservice.social.feedback.IFeedbackBundlePartialResultCallback";
        static final int TRANSACTION_onFailure = 3;
        static final int TRANSACTION_onPartialResult = 2;
        static final int TRANSACTION_onSuccess = 1;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IFeedbackBundlePartialResultCallback {
            public static IFeedbackBundlePartialResultCallback sDefaultImpl;
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
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onFailure(j2, str);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onPartialResult(Bundle bundle) {
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
                        Stub.getDefaultImpl().onPartialResult(bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
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
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
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

        public static IFeedbackBundlePartialResultCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IFeedbackBundlePartialResultCallback)) {
                return new Proxy(iBinder);
            }
            return (IFeedbackBundlePartialResultCallback) queryLocalInterface;
        }

        public static IFeedbackBundlePartialResultCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IFeedbackBundlePartialResultCallback iFeedbackBundlePartialResultCallback) {
            if (Proxy.sDefaultImpl != null || iFeedbackBundlePartialResultCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iFeedbackBundlePartialResultCallback;
            return true;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: android.os.Bundle} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r5, android.os.Parcel r6, android.os.Parcel r7, int r8) {
            /*
                r4 = this;
                r0 = 0
                java.lang.String r1 = "com.samsung.android.sdk.mobileservice.social.feedback.IFeedbackBundlePartialResultCallback"
                r2 = 1
                if (r5 == r2) goto L_0x0045
                r3 = 2
                if (r5 == r3) goto L_0x002c
                r0 = 3
                if (r5 == r0) goto L_0x001a
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                if (r5 == r0) goto L_0x0016
                boolean r4 = super.onTransact(r5, r6, r7, r8)
                return r4
            L_0x0016:
                r7.writeString(r1)
                return r2
            L_0x001a:
                r6.enforceInterface(r1)
                long r0 = r6.readLong()
                java.lang.String r5 = r6.readString()
                r4.onFailure(r0, r5)
                r7.writeNoException()
                return r2
            L_0x002c:
                r6.enforceInterface(r1)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x003e
                android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r0 = r5
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x003e:
                r4.onPartialResult(r0)
                r7.writeNoException()
                return r2
            L_0x0045:
                r6.enforceInterface(r1)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x0057
                android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r0 = r5
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0057:
                r4.onSuccess(r0)
                r7.writeNoException()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.social.feedback.IFeedbackBundlePartialResultCallback.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        public IBinder asBinder() {
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IFeedbackBundlePartialResultCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onPartialResult(Bundle bundle) {
        }

        public void onSuccess(Bundle bundle) {
        }

        public void onFailure(long j2, String str) {
        }
    }
}
