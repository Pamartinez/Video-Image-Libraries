package com.samsung.android.sdk.mobileservice.place;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMobileServicePlace extends IInterface {
    int getCurrentCount();

    int getMaxCount();

    List<Bundle> getPlaceList();

    void requestPlaceCreate(Bundle bundle, IPlaceResultCallback iPlaceResultCallback);

    void requestPlaceDelete(String str, IPlaceResultCallback iPlaceResultCallback);

    void requestPlaceUpdate(Bundle bundle, IPlaceResultCallback iPlaceResultCallback);

    void requestSync(IPlaceResultCallback iPlaceResultCallback);

    List<Bundle> searchPlaces(int i2);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IMobileServicePlace {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.mobileservice.place.IMobileServicePlace";
        static final int TRANSACTION_getCurrentCount = 7;
        static final int TRANSACTION_getMaxCount = 8;
        static final int TRANSACTION_getPlaceList = 1;
        static final int TRANSACTION_requestPlaceCreate = 3;
        static final int TRANSACTION_requestPlaceDelete = 4;
        static final int TRANSACTION_requestPlaceUpdate = 5;
        static final int TRANSACTION_requestSync = 2;
        static final int TRANSACTION_searchPlaces = 6;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IMobileServicePlace {
            public static IMobileServicePlace sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public int getCurrentCount() {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(7, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().getCurrentCount();
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public int getMaxCount() {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(8, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().getMaxCount();
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<Bundle> getPlaceList() {
                List<Bundle> createTypedArrayList;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        createTypedArrayList = obtain2.createTypedArrayList(Bundle.CREATOR);
                    } else {
                        createTypedArrayList = Stub.getDefaultImpl().getPlaceList();
                    }
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void requestPlaceCreate(Bundle bundle, IPlaceResultCallback iPlaceResultCallback) {
                IBinder iBinder;
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
                    if (iPlaceResultCallback != null) {
                        iBinder = iPlaceResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestPlaceCreate(bundle, iPlaceResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestPlaceDelete(String str, IPlaceResultCallback iPlaceResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (iPlaceResultCallback != null) {
                        iBinder = iPlaceResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestPlaceDelete(str, iPlaceResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestPlaceUpdate(Bundle bundle, IPlaceResultCallback iPlaceResultCallback) {
                IBinder iBinder;
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
                    if (iPlaceResultCallback != null) {
                        iBinder = iPlaceResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(5, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestPlaceUpdate(bundle, iPlaceResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestSync(IPlaceResultCallback iPlaceResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (iPlaceResultCallback != null) {
                        iBinder = iPlaceResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestSync(iPlaceResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public List<Bundle> searchPlaces(int i2) {
                List<Bundle> createTypedArrayList;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i2);
                    if (this.mRemote.transact(6, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        createTypedArrayList = obtain2.createTypedArrayList(Bundle.CREATOR);
                    } else {
                        createTypedArrayList = Stub.getDefaultImpl().searchPlaces(i2);
                    }
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMobileServicePlace asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMobileServicePlace)) {
                return new Proxy(iBinder);
            }
            return (IMobileServicePlace) queryLocalInterface;
        }

        public static IMobileServicePlace getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IMobileServicePlace iMobileServicePlace) {
            if (Proxy.sDefaultImpl != null || iMobileServicePlace == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMobileServicePlace;
            return true;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v13, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: android.os.Bundle} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) {
            /*
                r3 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                java.lang.String r2 = "com.samsung.android.sdk.mobileservice.place.IMobileServicePlace"
                if (r4 == r0) goto L_0x00b7
                r0 = 0
                switch(r4) {
                    case 1: goto L_0x00a9;
                    case 2: goto L_0x0097;
                    case 3: goto L_0x0076;
                    case 4: goto L_0x0060;
                    case 5: goto L_0x003f;
                    case 6: goto L_0x002d;
                    case 7: goto L_0x001f;
                    case 8: goto L_0x0011;
                    default: goto L_0x000c;
                }
            L_0x000c:
                boolean r3 = super.onTransact(r4, r5, r6, r7)
                return r3
            L_0x0011:
                r5.enforceInterface(r2)
                int r3 = r3.getMaxCount()
                r6.writeNoException()
                r6.writeInt(r3)
                return r1
            L_0x001f:
                r5.enforceInterface(r2)
                int r3 = r3.getCurrentCount()
                r6.writeNoException()
                r6.writeInt(r3)
                return r1
            L_0x002d:
                r5.enforceInterface(r2)
                int r4 = r5.readInt()
                java.util.List r3 = r3.searchPlaces(r4)
                r6.writeNoException()
                r6.writeTypedList(r3)
                return r1
            L_0x003f:
                r5.enforceInterface(r2)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0051
                android.os.Parcelable$Creator r4 = android.os.Bundle.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r0 = r4
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0051:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.samsung.android.sdk.mobileservice.place.IPlaceResultCallback r4 = com.samsung.android.sdk.mobileservice.place.IPlaceResultCallback.Stub.asInterface(r4)
                r3.requestPlaceUpdate(r0, r4)
                r6.writeNoException()
                return r1
            L_0x0060:
                r5.enforceInterface(r2)
                java.lang.String r4 = r5.readString()
                android.os.IBinder r5 = r5.readStrongBinder()
                com.samsung.android.sdk.mobileservice.place.IPlaceResultCallback r5 = com.samsung.android.sdk.mobileservice.place.IPlaceResultCallback.Stub.asInterface(r5)
                r3.requestPlaceDelete(r4, r5)
                r6.writeNoException()
                return r1
            L_0x0076:
                r5.enforceInterface(r2)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0088
                android.os.Parcelable$Creator r4 = android.os.Bundle.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r0 = r4
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0088:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.samsung.android.sdk.mobileservice.place.IPlaceResultCallback r4 = com.samsung.android.sdk.mobileservice.place.IPlaceResultCallback.Stub.asInterface(r4)
                r3.requestPlaceCreate(r0, r4)
                r6.writeNoException()
                return r1
            L_0x0097:
                r5.enforceInterface(r2)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.samsung.android.sdk.mobileservice.place.IPlaceResultCallback r4 = com.samsung.android.sdk.mobileservice.place.IPlaceResultCallback.Stub.asInterface(r4)
                r3.requestSync(r4)
                r6.writeNoException()
                return r1
            L_0x00a9:
                r5.enforceInterface(r2)
                java.util.List r3 = r3.getPlaceList()
                r6.writeNoException()
                r6.writeTypedList(r3)
                return r1
            L_0x00b7:
                r6.writeString(r2)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.place.IMobileServicePlace.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        public IBinder asBinder() {
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IMobileServicePlace {
        public IBinder asBinder() {
            return null;
        }

        public int getCurrentCount() {
            return 0;
        }

        public int getMaxCount() {
            return 0;
        }

        public List<Bundle> getPlaceList() {
            return null;
        }

        public List<Bundle> searchPlaces(int i2) {
            return null;
        }

        public void requestSync(IPlaceResultCallback iPlaceResultCallback) {
        }

        public void requestPlaceCreate(Bundle bundle, IPlaceResultCallback iPlaceResultCallback) {
        }

        public void requestPlaceDelete(String str, IPlaceResultCallback iPlaceResultCallback) {
        }

        public void requestPlaceUpdate(Bundle bundle, IPlaceResultCallback iPlaceResultCallback) {
        }
    }
}
