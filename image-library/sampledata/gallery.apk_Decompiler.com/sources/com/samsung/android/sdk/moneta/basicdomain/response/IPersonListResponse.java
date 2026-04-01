package com.samsung.android.sdk.moneta.basicdomain.response;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.PersonWrapper;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IPersonListResponse extends IInterface {
    public static final String DESCRIPTOR = "com.samsung.android.sdk.moneta.basicdomain.response.IPersonListResponse";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class _Parcel {
        private static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedList(Parcel parcel, List<T> list, int i2) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i7 = 0; i7 < size; i7++) {
                writeTypedObject(parcel, (Parcelable) list.get(i7), i2);
            }
        }

        private static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t, int i2) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i2);
                return;
            }
            parcel.writeInt(0);
        }
    }

    void onError(int i2, String str);

    void onResponse(List<PersonWrapper> list);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IPersonListResponse {
        static final int TRANSACTION_onError = 2;
        static final int TRANSACTION_onResponse = 1;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IPersonListResponse {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPersonListResponse.DESCRIPTOR;
            }

            public void onError(int i2, String str) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IPersonListResponse.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onResponse(List<PersonWrapper> list) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IPersonListResponse.DESCRIPTOR);
                    _Parcel.writeTypedList(obtain, list, 0);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IPersonListResponse.DESCRIPTOR);
        }

        public static IPersonListResponse asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IPersonListResponse.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IPersonListResponse)) {
                return new Proxy(iBinder);
            }
            return (IPersonListResponse) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IPersonListResponse.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IPersonListResponse.DESCRIPTOR);
                return true;
            }
            if (i2 == 1) {
                onResponse(parcel.createTypedArrayList(PersonWrapper.CREATOR));
            } else if (i2 != 2) {
                return super.onTransact(i2, parcel, parcel2, i7);
            } else {
                onError(parcel.readInt(), parcel.readString());
            }
            return true;
        }

        public IBinder asBinder() {
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IPersonListResponse {
        public IBinder asBinder() {
            return null;
        }

        public void onResponse(List<PersonWrapper> list) {
        }

        public void onError(int i2, String str) {
        }
    }
}
