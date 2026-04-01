package com.msc.sa.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.msc.sa.aidl.ISACallback;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISAService extends IInterface {
    public static final String DESCRIPTOR = "com.msc.sa.aidl.ISAService";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements ISAService {
        public IBinder asBinder() {
            return null;
        }

        public String registerCallback(String str, String str2, String str3, ISACallback iSACallback) {
            return null;
        }

        public boolean requestAccessToken(int i2, String str, Bundle bundle) {
            return false;
        }

        public boolean requestAuthCode(int i2, String str, Bundle bundle) {
            return false;
        }

        public boolean requestChecklistValidation(int i2, String str, Bundle bundle) {
            return false;
        }

        public boolean requestClearConsentData(int i2, String str, Bundle bundle) {
            return false;
        }

        public boolean requestDisclaimerAgreement(int i2, String str, Bundle bundle) {
            return false;
        }

        public boolean requestPasswordConfirmation(int i2, String str, Bundle bundle) {
            return false;
        }

        public boolean requestRLControlFMM(int i2, String str, Bundle bundle) {
            return false;
        }

        public boolean requestRequiredConsent(int i2, String str, Bundle bundle) {
            return false;
        }

        public boolean requestRubinRequest(int i2, String str, Bundle bundle) {
            return false;
        }

        public boolean requestSCloudAccessToken(int i2, String str, Bundle bundle) {
            return false;
        }

        public boolean unregisterCallback(String str) {
            return false;
        }
    }

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

    String registerCallback(String str, String str2, String str3, ISACallback iSACallback);

    boolean requestAccessToken(int i2, String str, Bundle bundle);

    boolean requestAuthCode(int i2, String str, Bundle bundle);

    boolean requestChecklistValidation(int i2, String str, Bundle bundle);

    boolean requestClearConsentData(int i2, String str, Bundle bundle);

    boolean requestDisclaimerAgreement(int i2, String str, Bundle bundle);

    boolean requestPasswordConfirmation(int i2, String str, Bundle bundle);

    boolean requestRLControlFMM(int i2, String str, Bundle bundle);

    boolean requestRequiredConsent(int i2, String str, Bundle bundle);

    boolean requestRubinRequest(int i2, String str, Bundle bundle);

    boolean requestSCloudAccessToken(int i2, String str, Bundle bundle);

    boolean unregisterCallback(String str);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements ISAService {
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_requestAccessToken = 3;
        static final int TRANSACTION_requestAuthCode = 6;
        static final int TRANSACTION_requestChecklistValidation = 4;
        static final int TRANSACTION_requestClearConsentData = 12;
        static final int TRANSACTION_requestDisclaimerAgreement = 5;
        static final int TRANSACTION_requestPasswordConfirmation = 8;
        static final int TRANSACTION_requestRLControlFMM = 9;
        static final int TRANSACTION_requestRequiredConsent = 11;
        static final int TRANSACTION_requestRubinRequest = 10;
        static final int TRANSACTION_requestSCloudAccessToken = 7;
        static final int TRANSACTION_unregisterCallback = 2;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements ISAService {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISAService.DESCRIPTOR;
            }

            public String registerCallback(String str, String str2, String str3, ISACallback iSACallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISAService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeStrongInterface(iSACallback);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean requestAccessToken(int i2, String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISAService.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    boolean z = false;
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean requestAuthCode(int i2, String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISAService.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    boolean z = false;
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean requestChecklistValidation(int i2, String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISAService.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    boolean z = false;
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean requestClearConsentData(int i2, String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISAService.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    boolean z = false;
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean requestDisclaimerAgreement(int i2, String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISAService.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    boolean z = false;
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean requestPasswordConfirmation(int i2, String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISAService.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    boolean z = false;
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean requestRLControlFMM(int i2, String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISAService.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    boolean z = false;
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean requestRequiredConsent(int i2, String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISAService.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    boolean z = false;
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean requestRubinRequest(int i2, String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISAService.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    boolean z = false;
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean requestSCloudAccessToken(int i2, String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISAService.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    boolean z = false;
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean unregisterCallback(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISAService.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, ISAService.DESCRIPTOR);
        }

        public static ISAService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ISAService.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ISAService)) {
                return new Proxy(iBinder);
            }
            return (ISAService) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(ISAService.DESCRIPTOR);
            }
            if (i2 != 1598968902) {
                switch (i2) {
                    case 1:
                        String registerCallback = registerCallback(parcel.readString(), parcel.readString(), parcel.readString(), ISACallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeString(registerCallback);
                        break;
                    case 2:
                        boolean unregisterCallback = unregisterCallback(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(unregisterCallback ? 1 : 0);
                        break;
                    case 3:
                        boolean requestAccessToken = requestAccessToken(parcel.readInt(), parcel.readString(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        parcel2.writeInt(requestAccessToken ? 1 : 0);
                        break;
                    case 4:
                        boolean requestChecklistValidation = requestChecklistValidation(parcel.readInt(), parcel.readString(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        parcel2.writeInt(requestChecklistValidation ? 1 : 0);
                        break;
                    case 5:
                        boolean requestDisclaimerAgreement = requestDisclaimerAgreement(parcel.readInt(), parcel.readString(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        parcel2.writeInt(requestDisclaimerAgreement ? 1 : 0);
                        break;
                    case 6:
                        boolean requestAuthCode = requestAuthCode(parcel.readInt(), parcel.readString(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        parcel2.writeInt(requestAuthCode ? 1 : 0);
                        break;
                    case 7:
                        boolean requestSCloudAccessToken = requestSCloudAccessToken(parcel.readInt(), parcel.readString(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        parcel2.writeInt(requestSCloudAccessToken ? 1 : 0);
                        break;
                    case 8:
                        boolean requestPasswordConfirmation = requestPasswordConfirmation(parcel.readInt(), parcel.readString(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        parcel2.writeInt(requestPasswordConfirmation ? 1 : 0);
                        break;
                    case 9:
                        boolean requestRLControlFMM = requestRLControlFMM(parcel.readInt(), parcel.readString(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        parcel2.writeInt(requestRLControlFMM ? 1 : 0);
                        break;
                    case 10:
                        boolean requestRubinRequest = requestRubinRequest(parcel.readInt(), parcel.readString(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        parcel2.writeInt(requestRubinRequest ? 1 : 0);
                        break;
                    case 11:
                        boolean requestRequiredConsent = requestRequiredConsent(parcel.readInt(), parcel.readString(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        parcel2.writeInt(requestRequiredConsent ? 1 : 0);
                        break;
                    case 12:
                        boolean requestClearConsentData = requestClearConsentData(parcel.readInt(), parcel.readString(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        parcel2.writeInt(requestClearConsentData ? 1 : 0);
                        break;
                    default:
                        return super.onTransact(i2, parcel, parcel2, i7);
                }
                return true;
            }
            parcel2.writeString(ISAService.DESCRIPTOR);
            return true;
        }

        public IBinder asBinder() {
            return this;
        }
    }
}
