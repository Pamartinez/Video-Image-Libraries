package com.samsung.android.sdk.moneta.lifestyle.healthandwellness.service;

import com.samsung.android.sdk.moneta.lifestyle.common.TimeRange;
import com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.TimeOfDay;
import java.time.DayOfWeek;
import kotlin.Metadata;
import qe.C1227c;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H¦@¢\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\t\u001a\u00020\bH¦@¢\u0006\u0004\b\u0006\u0010\nJ\u001e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\f\u001a\u00020\u000bH¦@¢\u0006\u0004\b\u0006\u0010\rJ*\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u000e2\u0006\u0010\u0003\u001a\u00020\u0002H¦@¢\u0006\u0004\b\u0010\u0010\u0007J$\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00110\u000e2\u0006\u0010\u0012\u001a\u00020\u0011H¦@¢\u0006\u0004\b\u0013\u0010\u0014J$\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00110\u000e2\u0006\u0010\u0012\u001a\u00020\u0011H¦@¢\u0006\u0004\b\u0015\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/healthandwellness/service/HealthService;", "", "Lcom/samsung/android/sdk/moneta/lifestyle/common/TimeRange;", "timeRange", "", "Lcom/samsung/android/sdk/moneta/lifestyle/healthandwellness/entity/Exercise;", "getMostFrequentExercises", "(Lcom/samsung/android/sdk/moneta/lifestyle/common/TimeRange;Lqe/c;)Ljava/lang/Object;", "Ljava/time/DayOfWeek;", "dayOfWeek", "(Ljava/time/DayOfWeek;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/TimeOfDay;", "timeOfDay", "(Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/TimeOfDay;Lqe/c;)Ljava/lang/Object;", "", "Ljava/time/Month;", "getFrequentExercisesByMonth", "", "exerciseGroupType", "rankDayOfWeek", "(ILqe/c;)Ljava/lang/Object;", "rankTimeOfDay", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface HealthService {
    Object getFrequentExercisesByMonth(TimeRange timeRange, C1227c cVar);

    Object getMostFrequentExercises(TimeRange timeRange, C1227c cVar);

    Object getMostFrequentExercises(TimeOfDay timeOfDay, C1227c cVar);

    Object getMostFrequentExercises(DayOfWeek dayOfWeek, C1227c cVar);

    Object rankDayOfWeek(int i2, C1227c cVar);

    Object rankTimeOfDay(int i2, C1227c cVar);
}
