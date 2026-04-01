package com.samsung.android.visioncloudagent.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.samsung.android.visioncloudagent.aidl.IListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IImageClassificationService extends IInterface {
    public static final String DESCRIPTOR = "com.samsung.android.visioncloudagent.aidl.IImageClassificationService";

    String getTextSearch(String str, String str2);

    String getVisualSearch(String str);

    void register(IListener iListener, String str);

    int requestToService(String str, int i2);

    void unregister(IListener iListener);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IImageClassificationService {
        static final int TRANSACTION_getTextSearch = 5;
        static final int TRANSACTION_getVisualSearch = 4;
        static final int TRANSACTION_register = 1;
        static final int TRANSACTION_requestToService = 3;
        static final int TRANSACTION_unregister = 2;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IImageClassificationService {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IImageClassificationService.DESCRIPTOR;
            }

            public String getTextSearch(String str, String str2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IImageClassificationService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getVisualSearch(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IImageClassificationService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void register(IListener iListener, String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IImageClassificationService.DESCRIPTOR);
                    obtain.writeStrongInterface(iListener);
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int requestToService(String str, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IImageClassificationService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unregister(IListener iListener) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IImageClassificationService.DESCRIPTOR);
                    obtain.writeStrongInterface(iListener);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IImageClassificationService.DESCRIPTOR);
        }

        public static IImageClassificationService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IImageClassificationService.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IImageClassificationService)) {
                return new Proxy(iBinder);
            }
            return (IImageClassificationService) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IImageClassificationService.DESCRIPTOR);
            }
            if (i2 != 1598968902) {
                if (i2 == 1) {
                    register(IListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                } else if (i2 == 2) {
                    unregister(IListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                } else if (i2 == 3) {
                    int requestToService = requestToService(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(requestToService);
                } else if (i2 == 4) {
                    String visualSearch = getVisualSearch(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(visualSearch);
                } else if (i2 != 5) {
                    return super.onTransact(i2, parcel, parcel2, i7);
                } else {
                    String textSearch = getTextSearch(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(textSearch);
                }
                return true;
            }
            parcel2.writeString(IImageClassificationService.DESCRIPTOR);
            return true;
        }

        public IBinder asBinder() {
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IImageClassificationService {
        public IBinder asBinder() {
            return null;
        }

        public String getTextSearch(String str, String str2) {
            return null;
        }

        public String getVisualSearch(String str) {
            return null;
        }

        public int requestToService(String str, int i2) {
            return 0;
        }

        public void unregister(IListener iListener) {
        }

        public void register(IListener iListener, String str) {
        }
    }
}
