package com.samsung.android.sdk.moneta.basicdomain.service;

import com.samsung.android.sdk.moneta.basicdomain.entity.Person;
import com.samsung.android.sdk.moneta.basicdomain.entity.PersonType;
import com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip;
import java.util.List;
import kotlin.Metadata;
import qe.C1227c;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J4\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H¦@¢\u0006\u0004\b\t\u0010\nJ<\u0010\t\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H¦@¢\u0006\u0004\b\t\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/service/FeedbackService;", "", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip;", "suggestedRelationShip", "", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/Person;", "suggestedList", "selectedList", "Lme/x;", "sendRelationShipFeedback", "(Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip;Ljava/util/List;Ljava/util/List;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/PersonType;", "personType", "(Lcom/samsung/android/sdk/moneta/basicdomain/entity/PersonType;Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip;Ljava/util/List;Ljava/util/List;Lqe/c;)Ljava/lang/Object;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface FeedbackService {
    Object sendRelationShipFeedback(PersonType personType, RelationShip relationShip, List<Person> list, List<Person> list2, C1227c cVar);

    Object sendRelationShipFeedback(RelationShip relationShip, List<Person> list, List<Person> list2, C1227c cVar);
}
