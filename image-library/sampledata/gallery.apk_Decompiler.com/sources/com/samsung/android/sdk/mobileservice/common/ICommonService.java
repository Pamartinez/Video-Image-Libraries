package com.samsung.android.sdk.mobileservice.common;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ICommonService extends IInterface {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements ICommonService {
        public IBinder asBinder() {
            return null;
        }

        public boolean doMigration() {
            return false;
        }

        public Bundle exchangeConfiguration(Bundle bundle) {
            return null;
        }
    }

    boolean doMigration();

    Bundle exchangeConfiguration(Bundle bundle);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements ICommonService {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.mobileservice.common.ICommonService";
        static final int TRANSACTION_doMigration = 1;
        static final int TRANSACTION_exchangeConfiguration = 2;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements ICommonService {
            public static ICommonService sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public boolean doMigration() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().doMigration();
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle exchangeConfiguration(Bundle bundle) {
                Bundle bundle2;
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
                        if (obtain2.readInt() != 0) {
                            bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                        } else {
                            bundle2 = null;
                        }
                    } else {
                        bundle2 = Stub.getDefaultImpl().exchangeConfiguration(bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICommonService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ICommonService)) {
                return new Proxy(iBinder);
            }
            return (ICommonService) queryLocalInterface;
        }

        public static ICommonService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ICommonService iCommonService) {
            if (Proxy.sDefaultImpl != null || iCommonService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCommonService;
            return true;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            Bundle bundle;
            if (i2 == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean doMigration = doMigration();
                parcel2.writeNoException();
                parcel2.writeInt(doMigration ? 1 : 0);
                return true;
            } else if (i2 == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                } else {
                    bundle = null;
                }
                Bundle exchangeConfiguration = exchangeConfiguration(bundle);
                parcel2.writeNoException();
                if (exchangeConfiguration != null) {
                    parcel2.writeInt(1);
                    exchangeConfiguration.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
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
