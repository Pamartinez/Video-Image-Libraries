package com.samsung.android.sdk.moneta.basicdomain.response;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.MyProfileWrapper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMyProfileResponse extends IInterface {
    public static final String DESCRIPTOR = "com.samsung.android.sdk.moneta.basicdomain.response.IMyProfileResponse";

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

    void onError(int i2, String str);

    void onResponse(MyProfileWrapper myProfileWrapper);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IMyProfileResponse {
        static final int TRANSACTION_onError = 2;
        static final int TRANSACTION_onResponse = 1;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IMyProfileResponse {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IMyProfileResponse.DESCRIPTOR;
            }

            public void onError(int i2, String str) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMyProfileResponse.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onResponse(MyProfileWrapper myProfileWrapper) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMyProfileResponse.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, myProfileWrapper, 0);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IMyProfileResponse.DESCRIPTOR);
        }

        public static IMyProfileResponse asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IMyProfileResponse.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMyProfileResponse)) {
                return new Proxy(iBinder);
            }
            return (IMyProfileResponse) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IMyProfileResponse.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IMyProfileResponse.DESCRIPTOR);
                return true;
            } else if (i2 == 1) {
                onResponse((MyProfileWrapper) _Parcel.readTypedObject(parcel, MyProfileWrapper.CREATOR));
                return true;
            } else if (i2 != 2) {
                return super.onTransact(i2, parcel, parcel2, i7);
            } else {
                onError(parcel.readInt(), parcel.readString());
                return true;
            }
        }

        public IBinder asBinder() {
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IMyProfileResponse {
        public IBinder asBinder() {
            return null;
        }

        public void onResponse(MyProfileWrapper myProfileWrapper) {
        }

        public void onError(int i2, String str) {
        }
    }
}
