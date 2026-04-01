package com.samsung.android.sdk.moneta.memory.service;

import com.samsung.android.sdk.moneta.memory.entity.context.Place;
import com.samsung.android.sdk.moneta.memory.option.ActivityQueryOption;
import com.samsung.android.sdk.moneta.memory.option.ContentQueryOption;
import com.samsung.android.sdk.moneta.memory.option.EngramQueryOption;
import com.samsung.android.sdk.moneta.memory.option.ExercisingQueryOption;
import com.samsung.android.sdk.moneta.memory.option.PlaceQueryOption;
import com.samsung.android.sdk.moneta.memory.option.ScheduledTravelActivityQueryOption;
import com.samsung.android.sdk.moneta.memory.option.TravelEngramQueryOption;
import com.samsung.android.sdk.moneta.memory.option.TravelStateQueryOption;
import kotlin.Metadata;
import qe.C1227c;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u001e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H¦@¢\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\bH¦@¢\u0006\u0004\b\t\u0010\nJ\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H¦@¢\u0006\u0004\b\u000b\u0010\u0007J\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u00042\u0006\u0010\u0003\u001a\u00020\fH¦@¢\u0006\u0004\b\u000e\u0010\u000fJ\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00042\u0006\u0010\u0003\u001a\u00020\u0010H¦@¢\u0006\u0004\b\u0012\u0010\u0013J\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00042\u0006\u0010\u0003\u001a\u00020\u0014H¦@¢\u0006\u0004\b\u0016\u0010\u0017J\u001e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u00042\u0006\u0010\u0003\u001a\u00020\u0018H¦@¢\u0006\u0004\b\u001a\u0010\u001bJ\u001a\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0003\u001a\u00020\u001cH¦@¢\u0006\u0004\b\u001e\u0010\u001fJ\u001a\u0010\"\u001a\u0004\u0018\u00010!2\u0006\u0010 \u001a\u00020\u0019H¦@¢\u0006\u0004\b\"\u0010#J\u001e\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00150\u00042\u0006\u0010\u0003\u001a\u00020$H¦@¢\u0006\u0004\b%\u0010&¨\u0006'"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/service/MemoryService;", "", "Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption;", "options", "", "Lcom/samsung/android/sdk/moneta/memory/entity/Engram;", "queryEngram", "(Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/TravelEngramQueryOption;", "queryTravelEngram", "(Lcom/samsung/android/sdk/moneta/memory/option/TravelEngramQueryOption;Lqe/c;)Ljava/lang/Object;", "queryEngramByLocation", "Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryOption;", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/Activity;", "queryActivity", "(Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/ScheduledTravelActivityQueryOption;", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ScheduledTravel;", "queryScheduledTravelActivity", "(Lcom/samsung/android/sdk/moneta/memory/option/ScheduledTravelActivityQueryOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/ContentQueryOption;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "queryContent", "(Lcom/samsung/android/sdk/moneta/memory/option/ContentQueryOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/PlaceQueryOption;", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "queryPlace", "(Lcom/samsung/android/sdk/moneta/memory/option/PlaceQueryOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/TravelStateQueryOption;", "Lcom/samsung/android/sdk/moneta/memory/entity/context/TravelState;", "getCurrentTravelState", "(Lcom/samsung/android/sdk/moneta/memory/option/TravelStateQueryOption;Lqe/c;)Ljava/lang/Object;", "place", "", "updatePlace", "(Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/ExercisingQueryOption;", "queryMusicPlayedInExercising", "(Lcom/samsung/android/sdk/moneta/memory/option/ExercisingQueryOption;Lqe/c;)Ljava/lang/Object;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MemoryService {
    Object getCurrentTravelState(TravelStateQueryOption travelStateQueryOption, C1227c cVar);

    Object queryActivity(ActivityQueryOption activityQueryOption, C1227c cVar);

    Object queryContent(ContentQueryOption contentQueryOption, C1227c cVar);

    Object queryEngram(EngramQueryOption engramQueryOption, C1227c cVar);

    Object queryEngramByLocation(EngramQueryOption engramQueryOption, C1227c cVar);

    Object queryMusicPlayedInExercising(ExercisingQueryOption exercisingQueryOption, C1227c cVar);

    Object queryPlace(PlaceQueryOption placeQueryOption, C1227c cVar);

    Object queryScheduledTravelActivity(ScheduledTravelActivityQueryOption scheduledTravelActivityQueryOption, C1227c cVar);

    Object queryTravelEngram(TravelEngramQueryOption travelEngramQueryOption, C1227c cVar);

    Object updatePlace(Place place, C1227c cVar);
}
