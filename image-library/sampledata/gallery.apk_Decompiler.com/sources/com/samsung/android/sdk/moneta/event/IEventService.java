package com.samsung.android.sdk.moneta.event;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.event.IEventServiceResponse;
import com.samsung.android.sdk.moneta.event.option.EventQueryOption;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IEventService extends IInterface {
    public static final String DESCRIPTOR = "com.samsung.android.sdk.moneta.event.IEventService";

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

    void queryAllEvents(IEventServiceResponse iEventServiceResponse);

    void queryEvents(EventQueryOption eventQueryOption, IEventServiceResponse iEventServiceResponse);

    void queryEventsContaingKeyword(List<String> list, IEventServiceResponse iEventServiceResponse);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IEventService {
        static final int TRANSACTION_queryAllEvents = 3;
        static final int TRANSACTION_queryEvents = 1;
        static final int TRANSACTION_queryEventsContaingKeyword = 2;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IEventService {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IEventService.DESCRIPTOR;
            }

            public void queryAllEvents(IEventServiceResponse iEventServiceResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IEventService.DESCRIPTOR);
                    obtain.writeStrongInterface(iEventServiceResponse);
                    this.mRemote.transact(3, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryEvents(EventQueryOption eventQueryOption, IEventServiceResponse iEventServiceResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IEventService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, eventQueryOption, 0);
                    obtain.writeStrongInterface(iEventServiceResponse);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryEventsContaingKeyword(List<String> list, IEventServiceResponse iEventServiceResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IEventService.DESCRIPTOR);
                    obtain.writeStringList(list);
                    obtain.writeStrongInterface(iEventServiceResponse);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IEventService.DESCRIPTOR);
        }

        public static IEventService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IEventService.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IEventService)) {
                return new Proxy(iBinder);
            }
            return (IEventService) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IEventService.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IEventService.DESCRIPTOR);
                return true;
            } else if (i2 == 1) {
                queryEvents((EventQueryOption) _Parcel.readTypedObject(parcel, EventQueryOption.CREATOR), IEventServiceResponse.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i2 == 2) {
                queryEventsContaingKeyword(parcel.createStringArrayList(), IEventServiceResponse.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i2 != 3) {
                return super.onTransact(i2, parcel, parcel2, i7);
            } else {
                queryAllEvents(IEventServiceResponse.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        public IBinder asBinder() {
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IEventService {
        public IBinder asBinder() {
            return null;
        }

        public void queryAllEvents(IEventServiceResponse iEventServiceResponse) {
        }

        public void queryEvents(EventQueryOption eventQueryOption, IEventServiceResponse iEventServiceResponse) {
        }

        public void queryEventsContaingKeyword(List<String> list, IEventServiceResponse iEventServiceResponse) {
        }
    }
}
