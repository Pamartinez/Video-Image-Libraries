package com.samsung.android.sdk.moneta.basicdomain;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.basicdomain.entity.PersonType;
import com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip;
import com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.PersonWrapper;
import com.samsung.android.sdk.moneta.basicdomain.response.IMyProfileResponse;
import com.samsung.android.sdk.moneta.basicdomain.response.IPersonListResponse;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IBasicDomainService extends IInterface {
    public static final String DESCRIPTOR = "com.samsung.android.sdk.moneta.basicdomain.IBasicDomainService";

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

    void feedbackRelationShipByCandidates(List<PersonWrapper> list, List<PersonWrapper> list2, RelationShip relationShip);

    void getContactPersonCandidates(int i2, IPersonListResponse iPersonListResponse);

    void getFamilyCandidates(RelationShip relationShip, PersonType personType, IPersonListResponse iPersonListResponse);

    void getMyFamily(RelationShip relationShip, PersonType personType, IPersonListResponse iPersonListResponse);

    void getMyProfile(IMyProfileResponse iMyProfileResponse);

    void sendRelationShipFeedback(PersonType personType, RelationShip relationShip, List<PersonWrapper> list, List<PersonWrapper> list2);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IBasicDomainService {
        static final int TRANSACTION_feedbackRelationShipByCandidates = 5;
        static final int TRANSACTION_getContactPersonCandidates = 4;
        static final int TRANSACTION_getFamilyCandidates = 3;
        static final int TRANSACTION_getMyFamily = 2;
        static final int TRANSACTION_getMyProfile = 1;
        static final int TRANSACTION_sendRelationShipFeedback = 6;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IBasicDomainService {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void feedbackRelationShipByCandidates(List<PersonWrapper> list, List<PersonWrapper> list2, RelationShip relationShip) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBasicDomainService.DESCRIPTOR);
                    _Parcel.writeTypedList(obtain, list, 0);
                    _Parcel.writeTypedList(obtain, list2, 0);
                    _Parcel.writeTypedObject(obtain, relationShip, 0);
                    this.mRemote.transact(5, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getContactPersonCandidates(int i2, IPersonListResponse iPersonListResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBasicDomainService.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeStrongInterface(iPersonListResponse);
                    this.mRemote.transact(4, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getFamilyCandidates(RelationShip relationShip, PersonType personType, IPersonListResponse iPersonListResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBasicDomainService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, relationShip, 0);
                    _Parcel.writeTypedObject(obtain, personType, 0);
                    obtain.writeStrongInterface(iPersonListResponse);
                    this.mRemote.transact(3, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IBasicDomainService.DESCRIPTOR;
            }

            public void getMyFamily(RelationShip relationShip, PersonType personType, IPersonListResponse iPersonListResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBasicDomainService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, relationShip, 0);
                    _Parcel.writeTypedObject(obtain, personType, 0);
                    obtain.writeStrongInterface(iPersonListResponse);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getMyProfile(IMyProfileResponse iMyProfileResponse) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBasicDomainService.DESCRIPTOR);
                    obtain.writeStrongInterface(iMyProfileResponse);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void sendRelationShipFeedback(PersonType personType, RelationShip relationShip, List<PersonWrapper> list, List<PersonWrapper> list2) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBasicDomainService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, personType, 0);
                    _Parcel.writeTypedObject(obtain, relationShip, 0);
                    _Parcel.writeTypedList(obtain, list, 0);
                    _Parcel.writeTypedList(obtain, list2, 0);
                    this.mRemote.transact(6, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IBasicDomainService.DESCRIPTOR);
        }

        public static IBasicDomainService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IBasicDomainService.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IBasicDomainService)) {
                return new Proxy(iBinder);
            }
            return (IBasicDomainService) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IBasicDomainService.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IBasicDomainService.DESCRIPTOR);
                return true;
            }
            switch (i2) {
                case 1:
                    getMyProfile(IMyProfileResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 2:
                    getMyFamily((RelationShip) _Parcel.readTypedObject(parcel, RelationShip.CREATOR), (PersonType) _Parcel.readTypedObject(parcel, PersonType.CREATOR), IPersonListResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 3:
                    getFamilyCandidates((RelationShip) _Parcel.readTypedObject(parcel, RelationShip.CREATOR), (PersonType) _Parcel.readTypedObject(parcel, PersonType.CREATOR), IPersonListResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 4:
                    getContactPersonCandidates(parcel.readInt(), IPersonListResponse.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 5:
                    Parcelable.Creator<PersonWrapper> creator = PersonWrapper.CREATOR;
                    feedbackRelationShipByCandidates(parcel.createTypedArrayList(creator), parcel.createTypedArrayList(creator), (RelationShip) _Parcel.readTypedObject(parcel, RelationShip.CREATOR));
                    break;
                case 6:
                    Parcelable.Creator<PersonWrapper> creator2 = PersonWrapper.CREATOR;
                    sendRelationShipFeedback((PersonType) _Parcel.readTypedObject(parcel, PersonType.CREATOR), (RelationShip) _Parcel.readTypedObject(parcel, RelationShip.CREATOR), parcel.createTypedArrayList(creator2), parcel.createTypedArrayList(creator2));
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
    public static class Default implements IBasicDomainService {
        public IBinder asBinder() {
            return null;
        }

        public void getMyProfile(IMyProfileResponse iMyProfileResponse) {
        }

        public void getContactPersonCandidates(int i2, IPersonListResponse iPersonListResponse) {
        }

        public void feedbackRelationShipByCandidates(List<PersonWrapper> list, List<PersonWrapper> list2, RelationShip relationShip) {
        }

        public void getFamilyCandidates(RelationShip relationShip, PersonType personType, IPersonListResponse iPersonListResponse) {
        }

        public void getMyFamily(RelationShip relationShip, PersonType personType, IPersonListResponse iPersonListResponse) {
        }

        public void sendRelationShipFeedback(PersonType personType, RelationShip relationShip, List<PersonWrapper> list, List<PersonWrapper> list2) {
        }
    }
}
