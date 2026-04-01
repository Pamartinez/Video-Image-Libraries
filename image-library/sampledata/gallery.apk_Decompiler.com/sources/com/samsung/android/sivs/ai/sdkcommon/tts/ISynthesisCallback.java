package com.samsung.android.sivs.ai.sdkcommon.tts;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISynthesisCallback extends IInterface {
    public static final String DESCRIPTOR = "com.samsung.android.sivs.ai.sdkcommon.tts.ISynthesisCallback";

    void onDone(String str);

    void onError(String str, int i2);

    void onProgress(String str, byte[] bArr, int i2, int i7);

    void onRangeStart(String str, int i2, int i7, int i8);

    void onStart(String str, int i2, int i7, int i8);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements ISynthesisCallback {
        static final int TRANSACTION_onDone = 2;
        static final int TRANSACTION_onError = 3;
        static final int TRANSACTION_onProgress = 5;
        static final int TRANSACTION_onRangeStart = 4;
        static final int TRANSACTION_onStart = 1;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements ISynthesisCallback {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISynthesisCallback.DESCRIPTOR;
            }

            public void onDone(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISynthesisCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onError(String str, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISynthesisCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onProgress(String str, byte[] bArr, int i2, int i7) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISynthesisCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i2);
                    obtain.writeInt(i7);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onRangeStart(String str, int i2, int i7, int i8) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISynthesisCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    obtain.writeInt(i7);
                    obtain.writeInt(i8);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onStart(String str, int i2, int i7, int i8) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISynthesisCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    obtain.writeInt(i7);
                    obtain.writeInt(i8);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, ISynthesisCallback.DESCRIPTOR);
        }

        public static ISynthesisCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ISynthesisCallback.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ISynthesisCallback)) {
                return new Proxy(iBinder);
            }
            return (ISynthesisCallback) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(ISynthesisCallback.DESCRIPTOR);
            }
            if (i2 != 1598968902) {
                if (i2 == 1) {
                    onStart(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                } else if (i2 == 2) {
                    onDone(parcel.readString());
                    parcel2.writeNoException();
                } else if (i2 == 3) {
                    onError(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                } else if (i2 == 4) {
                    onRangeStart(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                } else if (i2 != 5) {
                    return super.onTransact(i2, parcel, parcel2, i7);
                } else {
                    onProgress(parcel.readString(), parcel.createByteArray(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                }
                return true;
            }
            parcel2.writeString(ISynthesisCallback.DESCRIPTOR);
            return true;
        }

        public IBinder asBinder() {
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements ISynthesisCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onDone(String str) {
        }

        public void onError(String str, int i2) {
        }

        public void onProgress(String str, byte[] bArr, int i2, int i7) {
        }

        public void onRangeStart(String str, int i2, int i7, int i8) {
        }

        public void onStart(String str, int i2, int i7, int i8) {
        }
    }
}
