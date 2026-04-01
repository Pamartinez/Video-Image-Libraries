package com.sec.android.mimage.photoretouching.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IPresetServiceCallback extends IInterface {
    public static final String DESCRIPTOR = "com.sec.android.mimage.photoretouching.service.IPresetServiceCallback";

    void onComplete();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IPresetServiceCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onComplete() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IPresetServiceCallback {
        static final int TRANSACTION_onComplete = 1;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IPresetServiceCallback {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPresetServiceCallback.DESCRIPTOR;
            }

            public void onComplete() {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IPresetServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IPresetServiceCallback.DESCRIPTOR);
        }

        public static IPresetServiceCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IPresetServiceCallback.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IPresetServiceCallback)) {
                return new Proxy(iBinder);
            }
            return (IPresetServiceCallback) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IPresetServiceCallback.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IPresetServiceCallback.DESCRIPTOR);
                return true;
            } else if (i2 != 1) {
                return super.onTransact(i2, parcel, parcel2, i7);
            } else {
                onComplete();
                return true;
            }
        }

        public IBinder asBinder() {
            return this;
        }
    }
}
