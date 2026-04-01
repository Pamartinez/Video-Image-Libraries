package com.msc.sa.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISACallback extends IInterface {
    public static final String DESCRIPTOR = "com.msc.sa.aidl.ISACallback";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class _Parcel {
        /* access modifiers changed from: private */
        public static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t, int i2) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i2);
                return;
            }
            parcel.writeInt(0);
        }
    }

    void onReceiveAccessToken(int i2, boolean z, Bundle bundle);

    void onReceiveAuthCode(int i2, boolean z, Bundle bundle);

    void onReceiveChecklistValidation(int i2, boolean z, Bundle bundle);

    void onReceiveClearConsentData(int i2, boolean z, Bundle bundle);

    void onReceiveDisclaimerAgreement(int i2, boolean z, Bundle bundle);

    void onReceivePasswordConfirmation(int i2, boolean z, Bundle bundle);

    void onReceiveRLControlFMM(int i2, boolean z, Bundle bundle);

    void onReceiveRequiredConsent(int i2, boolean z, Bundle bundle);

    void onReceiveRubinRequest(int i2, boolean z, Bundle bundle);

    void onReceiveSCloudAccessToken(int i2, boolean z, Bundle bundle);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements ISACallback {
        static final int TRANSACTION_onReceiveAccessToken = 1;
        static final int TRANSACTION_onReceiveAuthCode = 4;
        static final int TRANSACTION_onReceiveChecklistValidation = 2;
        static final int TRANSACTION_onReceiveClearConsentData = 10;
        static final int TRANSACTION_onReceiveDisclaimerAgreement = 3;
        static final int TRANSACTION_onReceivePasswordConfirmation = 6;
        static final int TRANSACTION_onReceiveRLControlFMM = 7;
        static final int TRANSACTION_onReceiveRequiredConsent = 9;
        static final int TRANSACTION_onReceiveRubinRequest = 8;
        static final int TRANSACTION_onReceiveSCloudAccessToken = 5;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements ISACallback {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISACallback.DESCRIPTOR;
            }

            public void onReceiveAccessToken(int i2, boolean z, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISACallback.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeInt(z ? 1 : 0);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onReceiveAuthCode(int i2, boolean z, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISACallback.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeInt(z ? 1 : 0);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onReceiveChecklistValidation(int i2, boolean z, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISACallback.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeInt(z ? 1 : 0);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onReceiveClearConsentData(int i2, boolean z, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISACallback.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeInt(z ? 1 : 0);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onReceiveDisclaimerAgreement(int i2, boolean z, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISACallback.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeInt(z ? 1 : 0);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onReceivePasswordConfirmation(int i2, boolean z, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISACallback.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeInt(z ? 1 : 0);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onReceiveRLControlFMM(int i2, boolean z, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISACallback.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeInt(z ? 1 : 0);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onReceiveRequiredConsent(int i2, boolean z, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISACallback.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeInt(z ? 1 : 0);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onReceiveRubinRequest(int i2, boolean z, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISACallback.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeInt(z ? 1 : 0);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onReceiveSCloudAccessToken(int i2, boolean z, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISACallback.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeInt(z ? 1 : 0);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, ISACallback.DESCRIPTOR);
        }

        public static ISACallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ISACallback.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ISACallback)) {
                return new Proxy(iBinder);
            }
            return (ISACallback) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(ISACallback.DESCRIPTOR);
            }
            if (i2 != 1598968902) {
                boolean z = false;
                switch (i2) {
                    case 1:
                        int readInt = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        onReceiveAccessToken(readInt, z, (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        break;
                    case 2:
                        int readInt2 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        onReceiveChecklistValidation(readInt2, z, (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        break;
                    case 3:
                        int readInt3 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        onReceiveDisclaimerAgreement(readInt3, z, (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        break;
                    case 4:
                        int readInt4 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        onReceiveAuthCode(readInt4, z, (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        break;
                    case 5:
                        int readInt5 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        onReceiveSCloudAccessToken(readInt5, z, (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        break;
                    case 6:
                        int readInt6 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        onReceivePasswordConfirmation(readInt6, z, (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        break;
                    case 7:
                        int readInt7 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        onReceiveRLControlFMM(readInt7, z, (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        break;
                    case 8:
                        int readInt8 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        onReceiveRubinRequest(readInt8, z, (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        break;
                    case 9:
                        int readInt9 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        onReceiveRequiredConsent(readInt9, z, (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        break;
                    case 10:
                        int readInt10 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        onReceiveClearConsentData(readInt10, z, (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        break;
                    default:
                        return super.onTransact(i2, parcel, parcel2, i7);
                }
                return true;
            }
            parcel2.writeString(ISACallback.DESCRIPTOR);
            return true;
        }

        public IBinder asBinder() {
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements ISACallback {
        public IBinder asBinder() {
            return null;
        }

        public void onReceiveAccessToken(int i2, boolean z, Bundle bundle) {
        }

        public void onReceiveAuthCode(int i2, boolean z, Bundle bundle) {
        }

        public void onReceiveChecklistValidation(int i2, boolean z, Bundle bundle) {
        }

        public void onReceiveClearConsentData(int i2, boolean z, Bundle bundle) {
        }

        public void onReceiveDisclaimerAgreement(int i2, boolean z, Bundle bundle) {
        }

        public void onReceivePasswordConfirmation(int i2, boolean z, Bundle bundle) {
        }

        public void onReceiveRLControlFMM(int i2, boolean z, Bundle bundle) {
        }

        public void onReceiveRequiredConsent(int i2, boolean z, Bundle bundle) {
        }

        public void onReceiveRubinRequest(int i2, boolean z, Bundle bundle) {
        }

        public void onReceiveSCloudAccessToken(int i2, boolean z, Bundle bundle) {
        }
    }
}
