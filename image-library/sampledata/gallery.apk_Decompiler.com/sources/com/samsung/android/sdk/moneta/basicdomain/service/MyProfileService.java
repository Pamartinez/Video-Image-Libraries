package com.samsung.android.sdk.moneta.basicdomain.service;

import com.samsung.android.sdk.moneta.basicdomain.entity.PersonType;
import com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip;
import kotlin.Metadata;
import qe.C1227c;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002H¦@¢\u0006\u0004\b\u0003\u0010\u0004J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H¦@¢\u0006\u0004\b\u0007\u0010\u0004J\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\t\u001a\u00020\bH¦@¢\u0006\u0004\b\u0007\u0010\nJ&\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\bH¦@¢\u0006\u0004\b\u0007\u0010\rJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005H¦@¢\u0006\u0004\b\u000f\u0010\u0004J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005H¦@¢\u0006\u0004\b\u0010\u0010\u0004¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/service/MyProfileService;", "", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/MyProfile;", "getMyProfile", "(Lqe/c;)Ljava/lang/Object;", "", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/Person;", "getMyFamily", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/PersonType;", "personType", "(Lcom/samsung/android/sdk/moneta/basicdomain/entity/PersonType;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip;", "relationShip", "(Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip;Lcom/samsung/android/sdk/moneta/basicdomain/entity/PersonType;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/Pet;", "getMyPets", "getCandidatePets", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MyProfileService {
    Object getCandidatePets(C1227c cVar);

    Object getMyFamily(PersonType personType, C1227c cVar);

    Object getMyFamily(RelationShip relationShip, PersonType personType, C1227c cVar);

    Object getMyFamily(C1227c cVar);

    Object getMyPets(C1227c cVar);

    Object getMyProfile(C1227c cVar);
}
