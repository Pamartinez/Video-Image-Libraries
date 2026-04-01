package com.samsung.android.sivs.ai.sdkcommon.tts;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISynthesizerInitCallback extends IInterface {
    public static final String DESCRIPTOR = "com.samsung.android.sivs.ai.sdkcommon.tts.ISynthesizerInitCallback";

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

    void onConnected(int i2);

    void onDisconnected();

    void onVoiceUpdated(List<Voice> list);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements ISynthesizerInitCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onDisconnected() {
        }

        public void onConnected(int i2) {
        }

        public void onVoiceUpdated(List<Voice> list) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements ISynthesizerInitCallback {
        static final int TRANSACTION_onConnected = 1;
        static final int TRANSACTION_onDisconnected = 2;
        static final int TRANSACTION_onVoiceUpdated = 3;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements ISynthesizerInitCallback {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISynthesizerInitCallback.DESCRIPTOR;
            }

            public void onConnected(int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISynthesizerInitCallback.DESCRIPTOR);
                    obtain.writeInt(i2);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onDisconnected() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISynthesizerInitCallback.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onVoiceUpdated(List<Voice> list) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISynthesizerInitCallback.DESCRIPTOR);
                    _Parcel.writeTypedList(obtain, list, 0);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, ISynthesizerInitCallback.DESCRIPTOR);
        }

        public static ISynthesizerInitCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ISynthesizerInitCallback.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ISynthesizerInitCallback)) {
                return new Proxy(iBinder);
            }
            return (ISynthesizerInitCallback) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(ISynthesizerInitCallback.DESCRIPTOR);
            }
            if (i2 != 1598968902) {
                if (i2 == 1) {
                    onConnected(parcel.readInt());
                    parcel2.writeNoException();
                } else if (i2 == 2) {
                    onDisconnected();
                    parcel2.writeNoException();
                } else if (i2 != 3) {
                    return super.onTransact(i2, parcel, parcel2, i7);
                } else {
                    onVoiceUpdated(parcel.createTypedArrayList(Voice.CREATOR));
                    parcel2.writeNoException();
                }
                return true;
            }
            parcel2.writeString(ISynthesizerInitCallback.DESCRIPTOR);
            return true;
        }

        public IBinder asBinder() {
            return this;
        }
    }
}
