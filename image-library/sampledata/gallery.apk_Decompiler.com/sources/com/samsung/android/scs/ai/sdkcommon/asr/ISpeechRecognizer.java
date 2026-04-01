package com.samsung.android.scs.ai.sdkcommon.asr;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.samsung.android.scs.ai.sdkcommon.asr.IRecognitionListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISpeechRecognizer extends IInterface {
    public static final String DESCRIPTOR = "com.samsung.android.scs.ai.sdkcommon.asr.ISpeechRecognizer";

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

    void cancel();

    boolean prepare(Bundle bundle);

    boolean release();

    boolean write(ParcelFileDescriptor parcelFileDescriptor, IRecognitionListener iRecognitionListener);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements ISpeechRecognizer {
        public IBinder asBinder() {
            return null;
        }

        public boolean prepare(Bundle bundle) {
            return false;
        }

        public boolean release() {
            return false;
        }

        public boolean write(ParcelFileDescriptor parcelFileDescriptor, IRecognitionListener iRecognitionListener) {
            return false;
        }

        public void cancel() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements ISpeechRecognizer {
        static final int TRANSACTION_cancel = 3;
        static final int TRANSACTION_prepare = 1;
        static final int TRANSACTION_release = 4;
        static final int TRANSACTION_write = 2;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements ISpeechRecognizer {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void cancel() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISpeechRecognizer.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return ISpeechRecognizer.DESCRIPTOR;
            }

            public boolean prepare(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISpeechRecognizer.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    boolean z = true;
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean release() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISpeechRecognizer.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean write(ParcelFileDescriptor parcelFileDescriptor, IRecognitionListener iRecognitionListener) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISpeechRecognizer.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, parcelFileDescriptor, 0);
                    obtain.writeStrongInterface(iRecognitionListener);
                    boolean z = false;
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, ISpeechRecognizer.DESCRIPTOR);
        }

        public static ISpeechRecognizer asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ISpeechRecognizer.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ISpeechRecognizer)) {
                return new Proxy(iBinder);
            }
            return (ISpeechRecognizer) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(ISpeechRecognizer.DESCRIPTOR);
            }
            if (i2 != 1598968902) {
                if (i2 == 1) {
                    boolean prepare = prepare((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(prepare ? 1 : 0);
                } else if (i2 == 2) {
                    boolean write = write((ParcelFileDescriptor) _Parcel.readTypedObject(parcel, ParcelFileDescriptor.CREATOR), IRecognitionListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(write ? 1 : 0);
                } else if (i2 == 3) {
                    cancel();
                    parcel2.writeNoException();
                } else if (i2 != 4) {
                    return super.onTransact(i2, parcel, parcel2, i7);
                } else {
                    boolean release = release();
                    parcel2.writeNoException();
                    parcel2.writeInt(release ? 1 : 0);
                }
                return true;
            }
            parcel2.writeString(ISpeechRecognizer.DESCRIPTOR);
            return true;
        }

        public IBinder asBinder() {
            return this;
        }
    }
}
