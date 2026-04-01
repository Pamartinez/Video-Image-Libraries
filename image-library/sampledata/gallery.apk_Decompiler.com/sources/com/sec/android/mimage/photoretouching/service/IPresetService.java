package com.sec.android.mimage.photoretouching.service;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.sec.android.mimage.photoretouching.service.IPresetServiceCallback;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IPresetService extends IInterface {
    public static final String DESCRIPTOR = "com.sec.android.mimage.photoretouching.service.IPresetService";

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

    void applyPreset(List<Uri> list, IPresetServiceCallback iPresetServiceCallback);

    void pausePreset();

    void resumePreset();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IPresetService {
        public IBinder asBinder() {
            return null;
        }

        public void pausePreset() {
        }

        public void resumePreset() {
        }

        public void applyPreset(List<Uri> list, IPresetServiceCallback iPresetServiceCallback) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IPresetService {
        static final int TRANSACTION_applyPreset = 1;
        static final int TRANSACTION_pausePreset = 2;
        static final int TRANSACTION_resumePreset = 3;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IPresetService {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public void applyPreset(List<Uri> list, IPresetServiceCallback iPresetServiceCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IPresetService.DESCRIPTOR);
                    _Parcel.writeTypedList(obtain, list, 0);
                    obtain.writeStrongInterface(iPresetServiceCallback);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPresetService.DESCRIPTOR;
            }

            public void pausePreset() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IPresetService.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void resumePreset() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IPresetService.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IPresetService.DESCRIPTOR);
        }

        public static IPresetService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IPresetService.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IPresetService)) {
                return new Proxy(iBinder);
            }
            return (IPresetService) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IPresetService.DESCRIPTOR);
            }
            if (i2 != 1598968902) {
                if (i2 == 1) {
                    applyPreset(parcel.createTypedArrayList(Uri.CREATOR), IPresetServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                } else if (i2 == 2) {
                    pausePreset();
                    parcel2.writeNoException();
                } else if (i2 != 3) {
                    return super.onTransact(i2, parcel, parcel2, i7);
                } else {
                    resumePreset();
                    parcel2.writeNoException();
                }
                return true;
            }
            parcel2.writeString(IPresetService.DESCRIPTOR);
            return true;
        }

        public IBinder asBinder() {
            return this;
        }
    }
}
