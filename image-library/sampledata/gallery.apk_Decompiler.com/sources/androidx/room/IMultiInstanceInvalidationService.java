package androidx.room;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import androidx.room.IMultiInstanceInvalidationCallback;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMultiInstanceInvalidationService extends IInterface {
    public static final String DESCRIPTOR = "androidx$room$IMultiInstanceInvalidationService".replace('$', '.');

    void broadcastInvalidation(int i2, String[] strArr);

    int registerCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, String str);

    void unregisterCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, int i2);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IMultiInstanceInvalidationService {

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IMultiInstanceInvalidationService {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void broadcastInvalidation(int i2, String[] strArr) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMultiInstanceInvalidationService.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeStringArray(strArr);
                    this.mRemote.transact(3, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public int registerCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMultiInstanceInvalidationService.DESCRIPTOR);
                    obtain.writeStrongInterface(iMultiInstanceInvalidationCallback);
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IMultiInstanceInvalidationService.DESCRIPTOR);
        }

        public static IMultiInstanceInvalidationService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IMultiInstanceInvalidationService.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMultiInstanceInvalidationService)) {
                return new Proxy(iBinder);
            }
            return (IMultiInstanceInvalidationService) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            String str = IMultiInstanceInvalidationService.DESCRIPTOR;
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(str);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(str);
                return true;
            }
            if (i2 == 1) {
                int registerCallback = registerCallback(IMultiInstanceInvalidationCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(registerCallback);
            } else if (i2 == 2) {
                unregisterCallback(IMultiInstanceInvalidationCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
            } else if (i2 != 3) {
                return super.onTransact(i2, parcel, parcel2, i7);
            } else {
                broadcastInvalidation(parcel.readInt(), parcel.createStringArray());
            }
            return true;
        }

        public IBinder asBinder() {
            return this;
        }
    }
}
