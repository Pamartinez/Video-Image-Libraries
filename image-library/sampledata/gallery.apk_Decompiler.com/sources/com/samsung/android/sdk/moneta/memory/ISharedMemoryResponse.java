package com.samsung.android.sdk.moneta.memory;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SharedMemory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISharedMemoryResponse extends IInterface {
    public static final String DESCRIPTOR = "com.samsung.android.sdk.moneta.memory.ISharedMemoryResponse";

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

    void onResponse(SharedMemory sharedMemory);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements ISharedMemoryResponse {
        static final int TRANSACTION_onError = 2;
        static final int TRANSACTION_onResponse = 1;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements ISharedMemoryResponse {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISharedMemoryResponse.DESCRIPTOR;
            }

            public void onError(int i2, String str) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISharedMemoryResponse.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onResponse(SharedMemory sharedMemory) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISharedMemoryResponse.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, sharedMemory, 0);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, ISharedMemoryResponse.DESCRIPTOR);
        }

        public static ISharedMemoryResponse asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ISharedMemoryResponse.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ISharedMemoryResponse)) {
                return new Proxy(iBinder);
            }
            return (ISharedMemoryResponse) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(ISharedMemoryResponse.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(ISharedMemoryResponse.DESCRIPTOR);
                return true;
            } else if (i2 == 1) {
                Parcelable.Creator creator = SharedMemory.CREATOR;
                onResponse((SharedMemory) _Parcel.readTypedObject(parcel, SharedMemory.CREATOR));
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
    public static class Default implements ISharedMemoryResponse {
        public IBinder asBinder() {
            return null;
        }

        public void onResponse(SharedMemory sharedMemory) {
        }

        public void onError(int i2, String str) {
        }
    }
}
