package com.samsung.android.sdk.moneta.memory;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.IBooleanResponse;
import com.samsung.android.sdk.moneta.memory.ISharedMemoryResponse;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PlaceBundleWrapper;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v1.query.ActivityQueryOptionWrapperV1;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v1.query.ContentQueryOptionWrapperV1;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v1.query.EngramQueryOptionWrapperV1;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v1.query.PlaceQueryOptionWrapperV1;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v1.query.ScheduledTravelActivityQueryOptionWrapperV1;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v1.query.TravelEngramQueryOptionWrapperV1;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v1.query.TravelStateQueryOptionWrapperV1;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v2.query.ActivityQueryOptionWrapperV2;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v2.query.EngramQueryOptionWrapperV2;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.ActivityQueryOptionBundleWrapper;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.ContentQueryOptionBundleWrapper;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.EngramQueryOptionBundleWrapper;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.ExercisingQueryOptionBundleWrapper;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.PlaceQueryOptionBundleWrapper;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.ScheduledTravelActivityQueryOptionBundleWrapper;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.TravelEngramQueryOptionBundleWrapper;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.TravelStateQueryOptionBundleWrapper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMemoryService extends IInterface {
    public static final String DESCRIPTOR = "com.samsung.android.sdk.moneta.memory.IMemoryService";

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

    void getCurrentTravelStateV1(TravelStateQueryOptionWrapperV1 travelStateQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse);

    void getCurrentTravelStateV3(TravelStateQueryOptionBundleWrapper travelStateQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse);

    void queryActivityV1(ActivityQueryOptionWrapperV1 activityQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse);

    void queryActivityV2(ActivityQueryOptionWrapperV2 activityQueryOptionWrapperV2, ISharedMemoryResponse iSharedMemoryResponse);

    void queryActivityV3(ActivityQueryOptionBundleWrapper activityQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse);

    void queryActivityV4(ActivityQueryOptionBundleWrapper activityQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse);

    void queryContentV1(ContentQueryOptionWrapperV1 contentQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse);

    void queryContentV2(ContentQueryOptionWrapperV1 contentQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse);

    void queryContentV3(ContentQueryOptionBundleWrapper contentQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse);

    void queryContentV4(ContentQueryOptionBundleWrapper contentQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse);

    void queryEngramByLocation(EngramQueryOptionBundleWrapper engramQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse);

    void queryEngramV1(EngramQueryOptionWrapperV1 engramQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse);

    void queryEngramV2(EngramQueryOptionWrapperV2 engramQueryOptionWrapperV2, ISharedMemoryResponse iSharedMemoryResponse);

    void queryEngramV3(EngramQueryOptionBundleWrapper engramQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse);

    void queryEngramV4(EngramQueryOptionBundleWrapper engramQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse);

    void queryMusicPlayedInExercising(ExercisingQueryOptionBundleWrapper exercisingQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse);

    void queryPlaceV1(PlaceQueryOptionWrapperV1 placeQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse);

    void queryPlaceV2(PlaceQueryOptionWrapperV1 placeQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse);

    void queryPlaceV3(PlaceQueryOptionBundleWrapper placeQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse);

    void queryScheduledTravelActivityV1(ScheduledTravelActivityQueryOptionWrapperV1 scheduledTravelActivityQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse);

    void queryScheduledTravelActivityV3(ScheduledTravelActivityQueryOptionBundleWrapper scheduledTravelActivityQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse);

    void queryTravelEngramV1(TravelEngramQueryOptionWrapperV1 travelEngramQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse);

    void queryTravelEngramV3(TravelEngramQueryOptionBundleWrapper travelEngramQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse);

    void queryTravelEngramV4(TravelEngramQueryOptionBundleWrapper travelEngramQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse);

    void updatePlaceV1(PlaceBundleWrapper placeBundleWrapper, IBooleanResponse iBooleanResponse);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IMemoryService {
        static final int TRANSACTION_getCurrentTravelStateV1 = 7;
        static final int TRANSACTION_getCurrentTravelStateV3 = 18;
        static final int TRANSACTION_queryActivityV1 = 2;
        static final int TRANSACTION_queryActivityV2 = 9;
        static final int TRANSACTION_queryActivityV3 = 12;
        static final int TRANSACTION_queryActivityV4 = 20;
        static final int TRANSACTION_queryContentV1 = 3;
        static final int TRANSACTION_queryContentV2 = 10;
        static final int TRANSACTION_queryContentV3 = 13;
        static final int TRANSACTION_queryContentV4 = 21;
        static final int TRANSACTION_queryEngramByLocation = 24;
        static final int TRANSACTION_queryEngramV1 = 1;
        static final int TRANSACTION_queryEngramV2 = 8;
        static final int TRANSACTION_queryEngramV3 = 14;
        static final int TRANSACTION_queryEngramV4 = 22;
        static final int TRANSACTION_queryMusicPlayedInExercising = 25;
        static final int TRANSACTION_queryPlaceV1 = 4;
        static final int TRANSACTION_queryPlaceV2 = 11;
        static final int TRANSACTION_queryPlaceV3 = 15;
        static final int TRANSACTION_queryScheduledTravelActivityV1 = 6;
        static final int TRANSACTION_queryScheduledTravelActivityV3 = 16;
        static final int TRANSACTION_queryTravelEngramV1 = 5;
        static final int TRANSACTION_queryTravelEngramV3 = 17;
        static final int TRANSACTION_queryTravelEngramV4 = 23;
        static final int TRANSACTION_updatePlaceV1 = 19;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IMemoryService {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void getCurrentTravelStateV1(TravelStateQueryOptionWrapperV1 travelStateQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, travelStateQueryOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(7, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getCurrentTravelStateV3(TravelStateQueryOptionBundleWrapper travelStateQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, travelStateQueryOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(18, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IMemoryService.DESCRIPTOR;
            }

            public void queryActivityV1(ActivityQueryOptionWrapperV1 activityQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, activityQueryOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryActivityV2(ActivityQueryOptionWrapperV2 activityQueryOptionWrapperV2, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, activityQueryOptionWrapperV2, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(9, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryActivityV3(ActivityQueryOptionBundleWrapper activityQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, activityQueryOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(12, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryActivityV4(ActivityQueryOptionBundleWrapper activityQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, activityQueryOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(20, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryContentV1(ContentQueryOptionWrapperV1 contentQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, contentQueryOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(3, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryContentV2(ContentQueryOptionWrapperV1 contentQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, contentQueryOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(10, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryContentV3(ContentQueryOptionBundleWrapper contentQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, contentQueryOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(13, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryContentV4(ContentQueryOptionBundleWrapper contentQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, contentQueryOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(21, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryEngramByLocation(EngramQueryOptionBundleWrapper engramQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramQueryOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(24, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryEngramV1(EngramQueryOptionWrapperV1 engramQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramQueryOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryEngramV2(EngramQueryOptionWrapperV2 engramQueryOptionWrapperV2, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramQueryOptionWrapperV2, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(8, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryEngramV3(EngramQueryOptionBundleWrapper engramQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramQueryOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(14, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryEngramV4(EngramQueryOptionBundleWrapper engramQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramQueryOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(22, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryMusicPlayedInExercising(ExercisingQueryOptionBundleWrapper exercisingQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, exercisingQueryOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(25, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryPlaceV1(PlaceQueryOptionWrapperV1 placeQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, placeQueryOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(4, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryPlaceV2(PlaceQueryOptionWrapperV1 placeQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, placeQueryOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(11, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryPlaceV3(PlaceQueryOptionBundleWrapper placeQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, placeQueryOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(15, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryScheduledTravelActivityV1(ScheduledTravelActivityQueryOptionWrapperV1 scheduledTravelActivityQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, scheduledTravelActivityQueryOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(6, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryScheduledTravelActivityV3(ScheduledTravelActivityQueryOptionBundleWrapper scheduledTravelActivityQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, scheduledTravelActivityQueryOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(16, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryTravelEngramV1(TravelEngramQueryOptionWrapperV1 travelEngramQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, travelEngramQueryOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(5, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryTravelEngramV3(TravelEngramQueryOptionBundleWrapper travelEngramQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, travelEngramQueryOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(17, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void queryTravelEngramV4(TravelEngramQueryOptionBundleWrapper travelEngramQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, travelEngramQueryOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(23, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void updatePlaceV1(PlaceBundleWrapper placeBundleWrapper, IBooleanResponse iBooleanResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemoryService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, placeBundleWrapper, 0);
                    obtain.writeStrongInterface(iBooleanResponse);
                    this.mRemote.transact(19, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IMemoryService.DESCRIPTOR);
        }

        public static IMemoryService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IMemoryService.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMemoryService)) {
                return new Proxy(iBinder);
            }
            return (IMemoryService) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IMemoryService.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IMemoryService.DESCRIPTOR);
                return true;
            }
            switch (i2) {
                case 1:
                    queryEngramV1((EngramQueryOptionWrapperV1) _Parcel.readTypedObject(parcel, EngramQueryOptionWrapperV1.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 2:
                    queryActivityV1((ActivityQueryOptionWrapperV1) _Parcel.readTypedObject(parcel, ActivityQueryOptionWrapperV1.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 3:
                    queryContentV1((ContentQueryOptionWrapperV1) _Parcel.readTypedObject(parcel, ContentQueryOptionWrapperV1.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 4:
                    queryPlaceV1((PlaceQueryOptionWrapperV1) _Parcel.readTypedObject(parcel, PlaceQueryOptionWrapperV1.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 5:
                    queryTravelEngramV1((TravelEngramQueryOptionWrapperV1) _Parcel.readTypedObject(parcel, TravelEngramQueryOptionWrapperV1.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 6:
                    queryScheduledTravelActivityV1((ScheduledTravelActivityQueryOptionWrapperV1) _Parcel.readTypedObject(parcel, ScheduledTravelActivityQueryOptionWrapperV1.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 7:
                    getCurrentTravelStateV1((TravelStateQueryOptionWrapperV1) _Parcel.readTypedObject(parcel, TravelStateQueryOptionWrapperV1.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 8:
                    queryEngramV2((EngramQueryOptionWrapperV2) _Parcel.readTypedObject(parcel, EngramQueryOptionWrapperV2.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 9:
                    queryActivityV2((ActivityQueryOptionWrapperV2) _Parcel.readTypedObject(parcel, ActivityQueryOptionWrapperV2.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 10:
                    queryContentV2((ContentQueryOptionWrapperV1) _Parcel.readTypedObject(parcel, ContentQueryOptionWrapperV1.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 11:
                    queryPlaceV2((PlaceQueryOptionWrapperV1) _Parcel.readTypedObject(parcel, PlaceQueryOptionWrapperV1.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 12:
                    queryActivityV3((ActivityQueryOptionBundleWrapper) _Parcel.readTypedObject(parcel, ActivityQueryOptionBundleWrapper.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 13:
                    queryContentV3((ContentQueryOptionBundleWrapper) _Parcel.readTypedObject(parcel, ContentQueryOptionBundleWrapper.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 14:
                    queryEngramV3((EngramQueryOptionBundleWrapper) _Parcel.readTypedObject(parcel, EngramQueryOptionBundleWrapper.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 15:
                    queryPlaceV3((PlaceQueryOptionBundleWrapper) _Parcel.readTypedObject(parcel, PlaceQueryOptionBundleWrapper.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 16:
                    queryScheduledTravelActivityV3((ScheduledTravelActivityQueryOptionBundleWrapper) _Parcel.readTypedObject(parcel, ScheduledTravelActivityQueryOptionBundleWrapper.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 17:
                    queryTravelEngramV3((TravelEngramQueryOptionBundleWrapper) _Parcel.readTypedObject(parcel, TravelEngramQueryOptionBundleWrapper.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 18:
                    getCurrentTravelStateV3((TravelStateQueryOptionBundleWrapper) _Parcel.readTypedObject(parcel, TravelStateQueryOptionBundleWrapper.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 19:
                    updatePlaceV1((PlaceBundleWrapper) _Parcel.readTypedObject(parcel, PlaceBundleWrapper.CREATOR), IBooleanResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 20:
                    queryActivityV4((ActivityQueryOptionBundleWrapper) _Parcel.readTypedObject(parcel, ActivityQueryOptionBundleWrapper.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 21:
                    queryContentV4((ContentQueryOptionBundleWrapper) _Parcel.readTypedObject(parcel, ContentQueryOptionBundleWrapper.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 22:
                    queryEngramV4((EngramQueryOptionBundleWrapper) _Parcel.readTypedObject(parcel, EngramQueryOptionBundleWrapper.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 23:
                    queryTravelEngramV4((TravelEngramQueryOptionBundleWrapper) _Parcel.readTypedObject(parcel, TravelEngramQueryOptionBundleWrapper.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 24:
                    queryEngramByLocation((EngramQueryOptionBundleWrapper) _Parcel.readTypedObject(parcel, EngramQueryOptionBundleWrapper.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 25:
                    queryMusicPlayedInExercising((ExercisingQueryOptionBundleWrapper) _Parcel.readTypedObject(parcel, ExercisingQueryOptionBundleWrapper.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                default:
                    return super.onTransact(i2, parcel, parcel2, i7);
            }
            return true;
        }

        public IBinder asBinder() {
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IMemoryService {
        public IBinder asBinder() {
            return null;
        }

        public void getCurrentTravelStateV1(TravelStateQueryOptionWrapperV1 travelStateQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void getCurrentTravelStateV3(TravelStateQueryOptionBundleWrapper travelStateQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryActivityV1(ActivityQueryOptionWrapperV1 activityQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryActivityV2(ActivityQueryOptionWrapperV2 activityQueryOptionWrapperV2, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryActivityV3(ActivityQueryOptionBundleWrapper activityQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryActivityV4(ActivityQueryOptionBundleWrapper activityQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryContentV1(ContentQueryOptionWrapperV1 contentQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryContentV2(ContentQueryOptionWrapperV1 contentQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryContentV3(ContentQueryOptionBundleWrapper contentQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryContentV4(ContentQueryOptionBundleWrapper contentQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryEngramByLocation(EngramQueryOptionBundleWrapper engramQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryEngramV1(EngramQueryOptionWrapperV1 engramQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryEngramV2(EngramQueryOptionWrapperV2 engramQueryOptionWrapperV2, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryEngramV3(EngramQueryOptionBundleWrapper engramQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryEngramV4(EngramQueryOptionBundleWrapper engramQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryMusicPlayedInExercising(ExercisingQueryOptionBundleWrapper exercisingQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryPlaceV1(PlaceQueryOptionWrapperV1 placeQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryPlaceV2(PlaceQueryOptionWrapperV1 placeQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryPlaceV3(PlaceQueryOptionBundleWrapper placeQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryScheduledTravelActivityV1(ScheduledTravelActivityQueryOptionWrapperV1 scheduledTravelActivityQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryScheduledTravelActivityV3(ScheduledTravelActivityQueryOptionBundleWrapper scheduledTravelActivityQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryTravelEngramV1(TravelEngramQueryOptionWrapperV1 travelEngramQueryOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryTravelEngramV3(TravelEngramQueryOptionBundleWrapper travelEngramQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void queryTravelEngramV4(TravelEngramQueryOptionBundleWrapper travelEngramQueryOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void updatePlaceV1(PlaceBundleWrapper placeBundleWrapper, IBooleanResponse iBooleanResponse) {
        }
    }
}
