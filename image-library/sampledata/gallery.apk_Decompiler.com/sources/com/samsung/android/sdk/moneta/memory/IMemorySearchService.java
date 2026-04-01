package com.samsung.android.sdk.moneta.memory;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.ICountResponse;
import com.samsung.android.sdk.moneta.memory.IGetRecommendationsResponse;
import com.samsung.android.sdk.moneta.memory.IPrepareSearchEngineResponse;
import com.samsung.android.sdk.moneta.memory.ISharedMemoryResponse;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v1.search.EngramSearchActivityOptionWrapperV1;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v1.search.EngramSearchContentOptionWrapperV1;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v1.search.EngramSearchContentStatOptionWrapperV1;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v1.search.EngramSearchEngramStatOptionWrapperV1;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v1.search.EngramSearchGraphOptionWrapperV1;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v1.search.EngramSearchIntentOptionWrapperV1;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v1.search.EngramSearchOptionWrapperV1;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchActivityOptionBundleWrapper;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchContentOptionBundleWrapper;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchContentStatOptionBundleWrapper;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchEngramStatOptionBundleWrapper;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchGraphOptionBundleWrapper;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchIntentOptionBundleWrapper;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchOptionBundleWrapper;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.RecommendationsGetOptionBundleWrapper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMemorySearchService extends IInterface {
    public static final String DESCRIPTOR = "com.samsung.android.sdk.moneta.memory.IMemorySearchService";

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

    void getRecommendationsV3(RecommendationsGetOptionBundleWrapper recommendationsGetOptionBundleWrapper, IGetRecommendationsResponse iGetRecommendationsResponse);

    void getSearchIntentV1(EngramSearchIntentOptionWrapperV1 engramSearchIntentOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse);

    void getSearchIntentV3(EngramSearchIntentOptionBundleWrapper engramSearchIntentOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse);

    void prepareSearchEngineV3(IPrepareSearchEngineResponse iPrepareSearchEngineResponse);

    void searchActivityV1(EngramSearchActivityOptionWrapperV1 engramSearchActivityOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse);

    void searchActivityV2(EngramSearchActivityOptionWrapperV1 engramSearchActivityOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse);

    void searchActivityV3(EngramSearchActivityOptionBundleWrapper engramSearchActivityOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse);

    void searchContentStatV1(EngramSearchContentStatOptionWrapperV1 engramSearchContentStatOptionWrapperV1, ICountResponse iCountResponse);

    void searchContentStatV3(EngramSearchContentStatOptionBundleWrapper engramSearchContentStatOptionBundleWrapper, ICountResponse iCountResponse);

    void searchContentV1(EngramSearchContentOptionWrapperV1 engramSearchContentOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse);

    void searchContentV2(EngramSearchContentOptionWrapperV1 engramSearchContentOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse);

    void searchContentV3(EngramSearchContentOptionBundleWrapper engramSearchContentOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse);

    void searchEngramStatV1(EngramSearchEngramStatOptionWrapperV1 engramSearchEngramStatOptionWrapperV1, ICountResponse iCountResponse);

    void searchEngramStatV3(EngramSearchEngramStatOptionBundleWrapper engramSearchEngramStatOptionBundleWrapper, ICountResponse iCountResponse);

    void searchEngramV1(EngramSearchOptionWrapperV1 engramSearchOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse);

    void searchEngramV2(EngramSearchOptionWrapperV1 engramSearchOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse);

    void searchEngramV3(EngramSearchOptionBundleWrapper engramSearchOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse);

    void searchGraphPathV1(EngramSearchGraphOptionWrapperV1 engramSearchGraphOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse);

    void searchGraphPathV2(EngramSearchGraphOptionWrapperV1 engramSearchGraphOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse);

    void searchGraphPathV3(EngramSearchGraphOptionBundleWrapper engramSearchGraphOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse);

    void searchPersonV1(EngramSearchOptionWrapperV1 engramSearchOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse);

    void searchPersonV2(EngramSearchOptionWrapperV1 engramSearchOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse);

    void searchPersonV3(EngramSearchOptionBundleWrapper engramSearchOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IMemorySearchService {
        static final int TRANSACTION_getRecommendationsV3 = 22;
        static final int TRANSACTION_getSearchIntentV1 = 1;
        static final int TRANSACTION_getSearchIntentV3 = 14;
        static final int TRANSACTION_prepareSearchEngineV3 = 23;
        static final int TRANSACTION_searchActivityV1 = 4;
        static final int TRANSACTION_searchActivityV2 = 11;
        static final int TRANSACTION_searchActivityV3 = 17;
        static final int TRANSACTION_searchContentStatV1 = 6;
        static final int TRANSACTION_searchContentStatV3 = 19;
        static final int TRANSACTION_searchContentV1 = 5;
        static final int TRANSACTION_searchContentV2 = 12;
        static final int TRANSACTION_searchContentV3 = 18;
        static final int TRANSACTION_searchEngramStatV1 = 7;
        static final int TRANSACTION_searchEngramStatV3 = 20;
        static final int TRANSACTION_searchEngramV1 = 2;
        static final int TRANSACTION_searchEngramV2 = 9;
        static final int TRANSACTION_searchEngramV3 = 15;
        static final int TRANSACTION_searchGraphPathV1 = 8;
        static final int TRANSACTION_searchGraphPathV2 = 13;
        static final int TRANSACTION_searchGraphPathV3 = 21;
        static final int TRANSACTION_searchPersonV1 = 3;
        static final int TRANSACTION_searchPersonV2 = 10;
        static final int TRANSACTION_searchPersonV3 = 16;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IMemorySearchService {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IMemorySearchService.DESCRIPTOR;
            }

            public void getRecommendationsV3(RecommendationsGetOptionBundleWrapper recommendationsGetOptionBundleWrapper, IGetRecommendationsResponse iGetRecommendationsResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, recommendationsGetOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iGetRecommendationsResponse);
                    this.mRemote.transact(22, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getSearchIntentV1(EngramSearchIntentOptionWrapperV1 engramSearchIntentOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramSearchIntentOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getSearchIntentV3(EngramSearchIntentOptionBundleWrapper engramSearchIntentOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramSearchIntentOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(14, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void prepareSearchEngineV3(IPrepareSearchEngineResponse iPrepareSearchEngineResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    obtain.writeStrongInterface(iPrepareSearchEngineResponse);
                    this.mRemote.transact(23, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void searchActivityV1(EngramSearchActivityOptionWrapperV1 engramSearchActivityOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramSearchActivityOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(4, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void searchActivityV2(EngramSearchActivityOptionWrapperV1 engramSearchActivityOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramSearchActivityOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(11, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void searchActivityV3(EngramSearchActivityOptionBundleWrapper engramSearchActivityOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramSearchActivityOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(17, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void searchContentStatV1(EngramSearchContentStatOptionWrapperV1 engramSearchContentStatOptionWrapperV1, ICountResponse iCountResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramSearchContentStatOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iCountResponse);
                    this.mRemote.transact(6, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void searchContentStatV3(EngramSearchContentStatOptionBundleWrapper engramSearchContentStatOptionBundleWrapper, ICountResponse iCountResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramSearchContentStatOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iCountResponse);
                    this.mRemote.transact(19, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void searchContentV1(EngramSearchContentOptionWrapperV1 engramSearchContentOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramSearchContentOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(5, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void searchContentV2(EngramSearchContentOptionWrapperV1 engramSearchContentOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramSearchContentOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(12, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void searchContentV3(EngramSearchContentOptionBundleWrapper engramSearchContentOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramSearchContentOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(18, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void searchEngramStatV1(EngramSearchEngramStatOptionWrapperV1 engramSearchEngramStatOptionWrapperV1, ICountResponse iCountResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramSearchEngramStatOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iCountResponse);
                    this.mRemote.transact(7, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void searchEngramStatV3(EngramSearchEngramStatOptionBundleWrapper engramSearchEngramStatOptionBundleWrapper, ICountResponse iCountResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramSearchEngramStatOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iCountResponse);
                    this.mRemote.transact(20, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void searchEngramV1(EngramSearchOptionWrapperV1 engramSearchOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramSearchOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void searchEngramV2(EngramSearchOptionWrapperV1 engramSearchOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramSearchOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(9, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void searchEngramV3(EngramSearchOptionBundleWrapper engramSearchOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramSearchOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(15, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void searchGraphPathV1(EngramSearchGraphOptionWrapperV1 engramSearchGraphOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramSearchGraphOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(8, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void searchGraphPathV2(EngramSearchGraphOptionWrapperV1 engramSearchGraphOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramSearchGraphOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(13, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void searchGraphPathV3(EngramSearchGraphOptionBundleWrapper engramSearchGraphOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramSearchGraphOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(21, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void searchPersonV1(EngramSearchOptionWrapperV1 engramSearchOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramSearchOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(3, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void searchPersonV2(EngramSearchOptionWrapperV1 engramSearchOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramSearchOptionWrapperV1, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(10, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void searchPersonV3(EngramSearchOptionBundleWrapper engramSearchOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMemorySearchService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, engramSearchOptionBundleWrapper, 0);
                    obtain.writeStrongInterface(iSharedMemoryResponse);
                    this.mRemote.transact(16, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IMemorySearchService.DESCRIPTOR);
        }

        public static IMemorySearchService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IMemorySearchService.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMemorySearchService)) {
                return new Proxy(iBinder);
            }
            return (IMemorySearchService) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IMemorySearchService.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IMemorySearchService.DESCRIPTOR);
                return true;
            }
            switch (i2) {
                case 1:
                    getSearchIntentV1((EngramSearchIntentOptionWrapperV1) _Parcel.readTypedObject(parcel, EngramSearchIntentOptionWrapperV1.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 2:
                    searchEngramV1((EngramSearchOptionWrapperV1) _Parcel.readTypedObject(parcel, EngramSearchOptionWrapperV1.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 3:
                    searchPersonV1((EngramSearchOptionWrapperV1) _Parcel.readTypedObject(parcel, EngramSearchOptionWrapperV1.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 4:
                    searchActivityV1((EngramSearchActivityOptionWrapperV1) _Parcel.readTypedObject(parcel, EngramSearchActivityOptionWrapperV1.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 5:
                    searchContentV1((EngramSearchContentOptionWrapperV1) _Parcel.readTypedObject(parcel, EngramSearchContentOptionWrapperV1.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 6:
                    searchContentStatV1((EngramSearchContentStatOptionWrapperV1) _Parcel.readTypedObject(parcel, EngramSearchContentStatOptionWrapperV1.CREATOR), ICountResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 7:
                    searchEngramStatV1((EngramSearchEngramStatOptionWrapperV1) _Parcel.readTypedObject(parcel, EngramSearchEngramStatOptionWrapperV1.CREATOR), ICountResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 8:
                    searchGraphPathV1((EngramSearchGraphOptionWrapperV1) _Parcel.readTypedObject(parcel, EngramSearchGraphOptionWrapperV1.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 9:
                    searchEngramV2((EngramSearchOptionWrapperV1) _Parcel.readTypedObject(parcel, EngramSearchOptionWrapperV1.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 10:
                    searchPersonV2((EngramSearchOptionWrapperV1) _Parcel.readTypedObject(parcel, EngramSearchOptionWrapperV1.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 11:
                    searchActivityV2((EngramSearchActivityOptionWrapperV1) _Parcel.readTypedObject(parcel, EngramSearchActivityOptionWrapperV1.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 12:
                    searchContentV2((EngramSearchContentOptionWrapperV1) _Parcel.readTypedObject(parcel, EngramSearchContentOptionWrapperV1.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 13:
                    searchGraphPathV2((EngramSearchGraphOptionWrapperV1) _Parcel.readTypedObject(parcel, EngramSearchGraphOptionWrapperV1.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 14:
                    getSearchIntentV3((EngramSearchIntentOptionBundleWrapper) _Parcel.readTypedObject(parcel, EngramSearchIntentOptionBundleWrapper.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 15:
                    searchEngramV3((EngramSearchOptionBundleWrapper) _Parcel.readTypedObject(parcel, EngramSearchOptionBundleWrapper.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 16:
                    searchPersonV3((EngramSearchOptionBundleWrapper) _Parcel.readTypedObject(parcel, EngramSearchOptionBundleWrapper.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 17:
                    searchActivityV3((EngramSearchActivityOptionBundleWrapper) _Parcel.readTypedObject(parcel, EngramSearchActivityOptionBundleWrapper.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 18:
                    searchContentV3((EngramSearchContentOptionBundleWrapper) _Parcel.readTypedObject(parcel, EngramSearchContentOptionBundleWrapper.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 19:
                    searchContentStatV3((EngramSearchContentStatOptionBundleWrapper) _Parcel.readTypedObject(parcel, EngramSearchContentStatOptionBundleWrapper.CREATOR), ICountResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 20:
                    searchEngramStatV3((EngramSearchEngramStatOptionBundleWrapper) _Parcel.readTypedObject(parcel, EngramSearchEngramStatOptionBundleWrapper.CREATOR), ICountResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 21:
                    searchGraphPathV3((EngramSearchGraphOptionBundleWrapper) _Parcel.readTypedObject(parcel, EngramSearchGraphOptionBundleWrapper.CREATOR), ISharedMemoryResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 22:
                    getRecommendationsV3((RecommendationsGetOptionBundleWrapper) _Parcel.readTypedObject(parcel, RecommendationsGetOptionBundleWrapper.CREATOR), IGetRecommendationsResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 23:
                    prepareSearchEngineV3(IPrepareSearchEngineResponse.Stub.asInterface(parcel.readStrongBinder()));
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
    public static class Default implements IMemorySearchService {
        public IBinder asBinder() {
            return null;
        }

        public void prepareSearchEngineV3(IPrepareSearchEngineResponse iPrepareSearchEngineResponse) {
        }

        public void getRecommendationsV3(RecommendationsGetOptionBundleWrapper recommendationsGetOptionBundleWrapper, IGetRecommendationsResponse iGetRecommendationsResponse) {
        }

        public void getSearchIntentV1(EngramSearchIntentOptionWrapperV1 engramSearchIntentOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void getSearchIntentV3(EngramSearchIntentOptionBundleWrapper engramSearchIntentOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void searchActivityV1(EngramSearchActivityOptionWrapperV1 engramSearchActivityOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void searchActivityV2(EngramSearchActivityOptionWrapperV1 engramSearchActivityOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void searchActivityV3(EngramSearchActivityOptionBundleWrapper engramSearchActivityOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void searchContentStatV1(EngramSearchContentStatOptionWrapperV1 engramSearchContentStatOptionWrapperV1, ICountResponse iCountResponse) {
        }

        public void searchContentStatV3(EngramSearchContentStatOptionBundleWrapper engramSearchContentStatOptionBundleWrapper, ICountResponse iCountResponse) {
        }

        public void searchContentV1(EngramSearchContentOptionWrapperV1 engramSearchContentOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void searchContentV2(EngramSearchContentOptionWrapperV1 engramSearchContentOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void searchContentV3(EngramSearchContentOptionBundleWrapper engramSearchContentOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void searchEngramStatV1(EngramSearchEngramStatOptionWrapperV1 engramSearchEngramStatOptionWrapperV1, ICountResponse iCountResponse) {
        }

        public void searchEngramStatV3(EngramSearchEngramStatOptionBundleWrapper engramSearchEngramStatOptionBundleWrapper, ICountResponse iCountResponse) {
        }

        public void searchEngramV1(EngramSearchOptionWrapperV1 engramSearchOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void searchEngramV2(EngramSearchOptionWrapperV1 engramSearchOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void searchEngramV3(EngramSearchOptionBundleWrapper engramSearchOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void searchGraphPathV1(EngramSearchGraphOptionWrapperV1 engramSearchGraphOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void searchGraphPathV2(EngramSearchGraphOptionWrapperV1 engramSearchGraphOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void searchGraphPathV3(EngramSearchGraphOptionBundleWrapper engramSearchGraphOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void searchPersonV1(EngramSearchOptionWrapperV1 engramSearchOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void searchPersonV2(EngramSearchOptionWrapperV1 engramSearchOptionWrapperV1, ISharedMemoryResponse iSharedMemoryResponse) {
        }

        public void searchPersonV3(EngramSearchOptionBundleWrapper engramSearchOptionBundleWrapper, ISharedMemoryResponse iSharedMemoryResponse) {
        }
    }
}
