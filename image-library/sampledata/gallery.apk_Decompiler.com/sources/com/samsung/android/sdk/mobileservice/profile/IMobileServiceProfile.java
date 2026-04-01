package com.samsung.android.sdk.mobileservice.profile;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMobileServiceProfile extends IInterface {
    int exchangeProfileVersion(int i2);

    Bundle getPrivacy();

    Profile getProfile();

    String getProfileBirthdayType();

    String getProfileImageUrl();

    boolean requestPrivacyUpdate(Bundle bundle, IPrivacyUpdateResultCallback iPrivacyUpdateResultCallback);

    boolean requestProfileBirthdayTypeUpdate(String str, IProfileUpdateResultCallback iProfileUpdateResultCallback);

    boolean requestProfileUpdate(Profile profile, IProfileUpdateResultCallback iProfileUpdateResultCallback);

    void requestSync(ISyncResultCallback iSyncResultCallback);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IMobileServiceProfile {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.mobileservice.profile.IMobileServiceProfile";
        static final int TRANSACTION_exchangeProfileVersion = 4;
        static final int TRANSACTION_getPrivacy = 5;
        static final int TRANSACTION_getProfile = 1;
        static final int TRANSACTION_getProfileBirthdayType = 8;
        static final int TRANSACTION_getProfileImageUrl = 7;
        static final int TRANSACTION_requestPrivacyUpdate = 6;
        static final int TRANSACTION_requestProfileBirthdayTypeUpdate = 9;
        static final int TRANSACTION_requestProfileUpdate = 3;
        static final int TRANSACTION_requestSync = 2;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IMobileServiceProfile {
            public static IMobileServiceProfile sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public int exchangeProfileVersion(int i2) {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i2);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().exchangeProfileVersion(i2);
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

            public Bundle getPrivacy() {
                Bundle bundle;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(5, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                        } else {
                            bundle = null;
                        }
                    } else {
                        bundle = Stub.getDefaultImpl().getPrivacy();
                    }
                    return bundle;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Profile getProfile() {
                Profile profile;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            profile = Profile.CREATOR.createFromParcel(obtain2);
                        } else {
                            profile = null;
                        }
                    } else {
                        profile = Stub.getDefaultImpl().getProfile();
                    }
                    return profile;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getProfileBirthdayType() {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(8, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = Stub.getDefaultImpl().getProfileBirthdayType();
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getProfileImageUrl() {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(7, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = Stub.getDefaultImpl().getProfileImageUrl();
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean requestPrivacyUpdate(Bundle bundle, IPrivacyUpdateResultCallback iPrivacyUpdateResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iPrivacyUpdateResultCallback != null) {
                        iBinder = iPrivacyUpdateResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(6, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() == 0) {
                            z = false;
                        }
                        obtain2.recycle();
                        obtain.recycle();
                        return z;
                    }
                    boolean requestPrivacyUpdate = Stub.getDefaultImpl().requestPrivacyUpdate(bundle, iPrivacyUpdateResultCallback);
                    obtain2.recycle();
                    obtain.recycle();
                    return requestPrivacyUpdate;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public boolean requestProfileBirthdayTypeUpdate(String str, IProfileUpdateResultCallback iProfileUpdateResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (iProfileUpdateResultCallback != null) {
                        iBinder = iProfileUpdateResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    boolean z = false;
                    if (this.mRemote.transact(9, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            z = true;
                        }
                        obtain2.recycle();
                        obtain.recycle();
                        return z;
                    }
                    boolean requestProfileBirthdayTypeUpdate = Stub.getDefaultImpl().requestProfileBirthdayTypeUpdate(str, iProfileUpdateResultCallback);
                    obtain2.recycle();
                    obtain.recycle();
                    return requestProfileBirthdayTypeUpdate;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public boolean requestProfileUpdate(Profile profile, IProfileUpdateResultCallback iProfileUpdateResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (profile != null) {
                        obtain.writeInt(1);
                        profile.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iProfileUpdateResultCallback != null) {
                        iBinder = iProfileUpdateResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() == 0) {
                            z = false;
                        }
                        obtain2.recycle();
                        obtain.recycle();
                        return z;
                    }
                    boolean requestProfileUpdate = Stub.getDefaultImpl().requestProfileUpdate(profile, iProfileUpdateResultCallback);
                    obtain2.recycle();
                    obtain.recycle();
                    return requestProfileUpdate;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestSync(ISyncResultCallback iSyncResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (iSyncResultCallback != null) {
                        iBinder = iSyncResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestSync(iSyncResultCallback);
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

        public static IMobileServiceProfile asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMobileServiceProfile)) {
                return new Proxy(iBinder);
            }
            return (IMobileServiceProfile) queryLocalInterface;
        }

        public static IMobileServiceProfile getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IMobileServiceProfile iMobileServiceProfile) {
            if (Proxy.sDefaultImpl != null || iMobileServiceProfile == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMobileServiceProfile;
            return true;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: com.samsung.android.sdk.mobileservice.profile.Profile} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: android.os.Bundle} */
        /* JADX WARNING: type inference failed for: r3v0 */
        /* JADX WARNING: type inference failed for: r3v7 */
        /* JADX WARNING: type inference failed for: r3v8 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r5, android.os.Parcel r6, android.os.Parcel r7, int r8) {
            /*
                r4 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                java.lang.String r2 = "com.samsung.android.sdk.mobileservice.profile.IMobileServiceProfile"
                if (r5 == r0) goto L_0x00e4
                r0 = 0
                r3 = 0
                switch(r5) {
                    case 1: goto L_0x00cd;
                    case 2: goto L_0x00bb;
                    case 3: goto L_0x0096;
                    case 4: goto L_0x0084;
                    case 5: goto L_0x006d;
                    case 6: goto L_0x0048;
                    case 7: goto L_0x003a;
                    case 8: goto L_0x002c;
                    case 9: goto L_0x0012;
                    default: goto L_0x000d;
                }
            L_0x000d:
                boolean r4 = super.onTransact(r5, r6, r7, r8)
                return r4
            L_0x0012:
                r6.enforceInterface(r2)
                java.lang.String r5 = r6.readString()
                android.os.IBinder r6 = r6.readStrongBinder()
                com.samsung.android.sdk.mobileservice.profile.IProfileUpdateResultCallback r6 = com.samsung.android.sdk.mobileservice.profile.IProfileUpdateResultCallback.Stub.asInterface(r6)
                boolean r4 = r4.requestProfileBirthdayTypeUpdate(r5, r6)
                r7.writeNoException()
                r7.writeInt(r4)
                return r1
            L_0x002c:
                r6.enforceInterface(r2)
                java.lang.String r4 = r4.getProfileBirthdayType()
                r7.writeNoException()
                r7.writeString(r4)
                return r1
            L_0x003a:
                r6.enforceInterface(r2)
                java.lang.String r4 = r4.getProfileImageUrl()
                r7.writeNoException()
                r7.writeString(r4)
                return r1
            L_0x0048:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x005a
                android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r3 = r5
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x005a:
                android.os.IBinder r5 = r6.readStrongBinder()
                com.samsung.android.sdk.mobileservice.profile.IPrivacyUpdateResultCallback r5 = com.samsung.android.sdk.mobileservice.profile.IPrivacyUpdateResultCallback.Stub.asInterface(r5)
                boolean r4 = r4.requestPrivacyUpdate(r3, r5)
                r7.writeNoException()
                r7.writeInt(r4)
                return r1
            L_0x006d:
                r6.enforceInterface(r2)
                android.os.Bundle r4 = r4.getPrivacy()
                r7.writeNoException()
                if (r4 == 0) goto L_0x0080
                r7.writeInt(r1)
                r4.writeToParcel(r7, r1)
                goto L_0x0083
            L_0x0080:
                r7.writeInt(r0)
            L_0x0083:
                return r1
            L_0x0084:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                int r4 = r4.exchangeProfileVersion(r5)
                r7.writeNoException()
                r7.writeInt(r4)
                return r1
            L_0x0096:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x00a8
                android.os.Parcelable$Creator<com.samsung.android.sdk.mobileservice.profile.Profile> r5 = com.samsung.android.sdk.mobileservice.profile.Profile.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r3 = r5
                com.samsung.android.sdk.mobileservice.profile.Profile r3 = (com.samsung.android.sdk.mobileservice.profile.Profile) r3
            L_0x00a8:
                android.os.IBinder r5 = r6.readStrongBinder()
                com.samsung.android.sdk.mobileservice.profile.IProfileUpdateResultCallback r5 = com.samsung.android.sdk.mobileservice.profile.IProfileUpdateResultCallback.Stub.asInterface(r5)
                boolean r4 = r4.requestProfileUpdate(r3, r5)
                r7.writeNoException()
                r7.writeInt(r4)
                return r1
            L_0x00bb:
                r6.enforceInterface(r2)
                android.os.IBinder r5 = r6.readStrongBinder()
                com.samsung.android.sdk.mobileservice.profile.ISyncResultCallback r5 = com.samsung.android.sdk.mobileservice.profile.ISyncResultCallback.Stub.asInterface(r5)
                r4.requestSync(r5)
                r7.writeNoException()
                return r1
            L_0x00cd:
                r6.enforceInterface(r2)
                com.samsung.android.sdk.mobileservice.profile.Profile r4 = r4.getProfile()
                r7.writeNoException()
                if (r4 == 0) goto L_0x00e0
                r7.writeInt(r1)
                r4.writeToParcel(r7, r1)
                goto L_0x00e3
            L_0x00e0:
                r7.writeInt(r0)
            L_0x00e3:
                return r1
            L_0x00e4:
                r7.writeString(r2)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.profile.IMobileServiceProfile.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        public IBinder asBinder() {
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IMobileServiceProfile {
        public IBinder asBinder() {
            return null;
        }

        public int exchangeProfileVersion(int i2) {
            return 0;
        }

        public Bundle getPrivacy() {
            return null;
        }

        public Profile getProfile() {
            return null;
        }

        public String getProfileBirthdayType() {
            return null;
        }

        public String getProfileImageUrl() {
            return null;
        }

        public boolean requestPrivacyUpdate(Bundle bundle, IPrivacyUpdateResultCallback iPrivacyUpdateResultCallback) {
            return false;
        }

        public boolean requestProfileBirthdayTypeUpdate(String str, IProfileUpdateResultCallback iProfileUpdateResultCallback) {
            return false;
        }

        public boolean requestProfileUpdate(Profile profile, IProfileUpdateResultCallback iProfileUpdateResultCallback) {
            return false;
        }

        public void requestSync(ISyncResultCallback iSyncResultCallback) {
        }
    }
}
