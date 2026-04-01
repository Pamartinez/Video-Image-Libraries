package com.samsung.android.sdk.moneta.basicdomain.service;

import com.samsung.android.sdk.moneta.basicdomain.entity.PersonType;
import com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip;
import kotlin.Metadata;
import qe.C1227c;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H¦@¢\u0006\u0004\b\u0006\u0010\u0007J&\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H¦@¢\u0006\u0004\b\u0006\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/service/PersonRecommendService;", "", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/PersonType;", "personType", "", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/Person;", "getFamilyCandidates", "(Lcom/samsung/android/sdk/moneta/basicdomain/entity/PersonType;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip;", "relationship", "(Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip;Lcom/samsung/android/sdk/moneta/basicdomain/entity/PersonType;Lqe/c;)Ljava/lang/Object;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface PersonRecommendService {
    Object getFamilyCandidates(PersonType personType, C1227c cVar);

    Object getFamilyCandidates(RelationShip relationShip, PersonType personType, C1227c cVar);
}
