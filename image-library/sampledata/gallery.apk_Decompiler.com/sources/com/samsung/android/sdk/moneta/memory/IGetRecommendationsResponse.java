package com.samsung.android.sdk.moneta.memory;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.GetRecommendationsResponseBundleWrapper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IGetRecommendationsResponse extends IInterface {
    public static final String DESCRIPTOR = "com.samsung.android.sdk.moneta.memory.IGetRecommendationsResponse";

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

    void onResponse(GetRecommendationsResponseBundleWrapper getRecommendationsResponseBundleWrapper);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IGetRecommendationsResponse {
        static final int TRANSACTION_onError = 2;
        static final int TRANSACTION_onResponse = 1;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IGetRecommendationsResponse {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IGetRecommendationsResponse.DESCRIPTOR;
            }

            public void onError(int i2, String str) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGetRecommendationsResponse.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onResponse(GetRecommendationsResponseBundleWrapper getRecommendationsResponseBundleWrapper) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGetRecommendationsResponse.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, getRecommendationsResponseBundleWrapper, 0);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IGetRecommendationsResponse.DESCRIPTOR);
        }

        public static IGetRecommendationsResponse asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IGetRecommendationsResponse.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IGetRecommendationsResponse)) {
                return new Proxy(iBinder);
            }
            return (IGetRecommendationsResponse) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IGetRecommendationsResponse.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IGetRecommendationsResponse.DESCRIPTOR);
                return true;
            } else if (i2 == 1) {
                onResponse((GetRecommendationsResponseBundleWrapper) _Parcel.readTypedObject(parcel, GetRecommendationsResponseBundleWrapper.CREATOR));
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
    public static class Default implements IGetRecommendationsResponse {
        public IBinder asBinder() {
            return null;
        }

        public void onResponse(GetRecommendationsResponseBundleWrapper getRecommendationsResponseBundleWrapper) {
        }

        public void onError(int i2, String str) {
        }
    }
}
