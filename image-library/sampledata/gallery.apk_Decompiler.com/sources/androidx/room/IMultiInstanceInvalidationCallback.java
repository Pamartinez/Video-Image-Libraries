package androidx.room;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMultiInstanceInvalidationCallback extends IInterface {
    public static final String DESCRIPTOR = "androidx$room$IMultiInstanceInvalidationCallback".replace('$', '.');

    void onInvalidation(String[] strArr);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IMultiInstanceInvalidationCallback {

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IMultiInstanceInvalidationCallback {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void onInvalidation(String[] strArr) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMultiInstanceInvalidationCallback.DESCRIPTOR);
                    obtain.writeStringArray(strArr);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IMultiInstanceInvalidationCallback.DESCRIPTOR);
        }

        public static IMultiInstanceInvalidationCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IMultiInstanceInvalidationCallback.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMultiInstanceInvalidationCallback)) {
                return new Proxy(iBinder);
            }
            return (IMultiInstanceInvalidationCallback) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            String str = IMultiInstanceInvalidationCallback.DESCRIPTOR;
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(str);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(str);
                return true;
            } else if (i2 != 1) {
                return super.onTransact(i2, parcel, parcel2, i7);
            } else {
                onInvalidation(parcel.createStringArray());
                return true;
            }
        }

        public IBinder asBinder() {
            return this;
        }
    }
}
