package com.samsung.android.scs.ai.sdkcommon.asr;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.scs.ai.sdkcommon.asr.ISpeechRecognizer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISpeechRecognizerService extends IInterface {
    public static final String DESCRIPTOR = "com.samsung.android.scs.ai.sdkcommon.asr.ISpeechRecognizerService";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements ISpeechRecognizerService {
        public IBinder asBinder() {
            return null;
        }

        public ISpeechRecognizer create(Bundle bundle) {
            return null;
        }
    }

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

    ISpeechRecognizer create(Bundle bundle);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements ISpeechRecognizerService {
        static final int TRANSACTION_create = 1;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements ISpeechRecognizerService {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public ISpeechRecognizer create(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISpeechRecognizerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return ISpeechRecognizer.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return ISpeechRecognizerService.DESCRIPTOR;
            }
        }

        public Stub() {
            attachInterface(this, ISpeechRecognizerService.DESCRIPTOR);
        }

        public static ISpeechRecognizerService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ISpeechRecognizerService.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ISpeechRecognizerService)) {
                return new Proxy(iBinder);
            }
            return (ISpeechRecognizerService) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(ISpeechRecognizerService.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(ISpeechRecognizerService.DESCRIPTOR);
                return true;
            } else if (i2 != 1) {
                return super.onTransact(i2, parcel, parcel2, i7);
            } else {
                Parcelable.Creator creator = Bundle.CREATOR;
                ISpeechRecognizer create = create((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                parcel2.writeStrongInterface(create);
                return true;
            }
        }

        public IBinder asBinder() {
            return this;
        }
    }
}
