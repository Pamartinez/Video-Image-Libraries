package com.samsung.android.sdk.ocr.service;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IOCRService extends IInterface {
    public static final String DESCRIPTOR = "com.samsung.android.sdk.ocr.service.IOCRService";

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

    void cancel(Bundle bundle);

    void close(Bundle bundle);

    Bundle detect(Bundle bundle);

    Bundle detectBlock(Bundle bundle);

    boolean detectText(Bundle bundle);

    boolean hasText(Bundle bundle);

    boolean initialize(Bundle bundle);

    boolean isHandwritten(Bundle bundle);

    boolean isPrinted(Bundle bundle);

    boolean isSupported(Bundle bundle);

    Bundle recognize(Bundle bundle);

    Bundle recognizeBlockAt(Bundle bundle);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IOCRService {
        static final int TRANSACTION_cancel = 12;
        static final int TRANSACTION_close = 3;
        static final int TRANSACTION_detect = 8;
        static final int TRANSACTION_detectBlock = 9;
        static final int TRANSACTION_detectText = 7;
        static final int TRANSACTION_hasText = 6;
        static final int TRANSACTION_initialize = 1;
        static final int TRANSACTION_isHandwritten = 5;
        static final int TRANSACTION_isPrinted = 4;
        static final int TRANSACTION_isSupported = 2;
        static final int TRANSACTION_recognize = 10;
        static final int TRANSACTION_recognizeBlockAt = 11;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IOCRService {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void cancel(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOCRService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void close(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOCRService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle detect(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOCRService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    Parcelable.Creator creator = Bundle.CREATOR;
                    return (Bundle) _Parcel.readTypedObject(obtain2, Bundle.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle detectBlock(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOCRService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    Parcelable.Creator creator = Bundle.CREATOR;
                    return (Bundle) _Parcel.readTypedObject(obtain2, Bundle.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean detectText(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOCRService.DESCRIPTOR);
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

            public String getInterfaceDescriptor() {
                return IOCRService.DESCRIPTOR;
            }

            public boolean hasText(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOCRService.DESCRIPTOR);
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

            public boolean initialize(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOCRService.DESCRIPTOR);
                    boolean z = false;
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(1, obtain, obtain2, 0);
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

            public boolean isHandwritten(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOCRService.DESCRIPTOR);
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

            public boolean isPrinted(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOCRService.DESCRIPTOR);
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

            public boolean isSupported(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOCRService.DESCRIPTOR);
                    boolean z = false;
                    _Parcel.writeTypedObject(obtain, bundle, 0);
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

            public Bundle recognize(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOCRService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    Parcelable.Creator creator = Bundle.CREATOR;
                    return (Bundle) _Parcel.readTypedObject(obtain2, Bundle.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle recognizeBlockAt(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOCRService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    Parcelable.Creator creator = Bundle.CREATOR;
                    return (Bundle) _Parcel.readTypedObject(obtain2, Bundle.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IOCRService.DESCRIPTOR);
        }

        public static IOCRService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IOCRService.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IOCRService)) {
                return new Proxy(iBinder);
            }
            return (IOCRService) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IOCRService.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IOCRService.DESCRIPTOR);
                return true;
            }
            switch (i2) {
                case 1:
                    Parcelable.Creator creator = Bundle.CREATOR;
                    boolean initialize = initialize((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(initialize ? 1 : 0);
                    return true;
                case 2:
                    Parcelable.Creator creator2 = Bundle.CREATOR;
                    boolean isSupported = isSupported((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(isSupported ? 1 : 0);
                    return true;
                case 3:
                    Parcelable.Creator creator3 = Bundle.CREATOR;
                    close((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    Parcelable.Creator creator4 = Bundle.CREATOR;
                    boolean isPrinted = isPrinted((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(isPrinted ? 1 : 0);
                    return true;
                case 5:
                    Parcelable.Creator creator5 = Bundle.CREATOR;
                    boolean isHandwritten = isHandwritten((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(isHandwritten ? 1 : 0);
                    return true;
                case 6:
                    Parcelable.Creator creator6 = Bundle.CREATOR;
                    boolean hasText = hasText((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(hasText ? 1 : 0);
                    return true;
                case 7:
                    Parcelable.Creator creator7 = Bundle.CREATOR;
                    boolean detectText = detectText((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(detectText ? 1 : 0);
                    return true;
                case 8:
                    Parcelable.Creator creator8 = Bundle.CREATOR;
                    Bundle detect = detect((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, detect, 1);
                    return true;
                case 9:
                    Parcelable.Creator creator9 = Bundle.CREATOR;
                    Bundle detectBlock = detectBlock((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, detectBlock, 1);
                    return true;
                case 10:
                    Parcelable.Creator creator10 = Bundle.CREATOR;
                    Bundle recognize = recognize((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, recognize, 1);
                    return true;
                case 11:
                    Parcelable.Creator creator11 = Bundle.CREATOR;
                    Bundle recognizeBlockAt = recognizeBlockAt((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, recognizeBlockAt, 1);
                    return true;
                case 12:
                    Parcelable.Creator creator12 = Bundle.CREATOR;
                    cancel((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i2, parcel, parcel2, i7);
            }
        }

        public IBinder asBinder() {
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IOCRService {
        public IBinder asBinder() {
            return null;
        }

        public Bundle detect(Bundle bundle) {
            return null;
        }

        public Bundle detectBlock(Bundle bundle) {
            return null;
        }

        public boolean detectText(Bundle bundle) {
            return false;
        }

        public boolean hasText(Bundle bundle) {
            return false;
        }

        public boolean initialize(Bundle bundle) {
            return false;
        }

        public boolean isHandwritten(Bundle bundle) {
            return false;
        }

        public boolean isPrinted(Bundle bundle) {
            return false;
        }

        public boolean isSupported(Bundle bundle) {
            return false;
        }

        public Bundle recognize(Bundle bundle) {
            return null;
        }

        public Bundle recognizeBlockAt(Bundle bundle) {
            return null;
        }

        public void cancel(Bundle bundle) {
        }

        public void close(Bundle bundle) {
        }
    }
}
