package com.samsung.android.sdk.moneta.lifestyle.healthandwellness.entity.wrapper.v1;

import android.os.Bundle;
import com.samsung.android.sdk.moneta.lifestyle.healthandwellness.entity.Exercise;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toExercise", "Lcom/samsung/android/sdk/moneta/lifestyle/healthandwellness/entity/Exercise;", "Lcom/samsung/android/sdk/moneta/lifestyle/healthandwellness/entity/wrapper/v1/ExerciseEntity;", "pde-sdk-1.0.40_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ExerciseEntityKt {
    public static final Exercise toExercise(ExerciseEntity exerciseEntity) {
        j.e(exerciseEntity, "<this>");
        Bundle bundle = new Bundle();
        Integer count = exerciseEntity.getPreferences().getCount();
        if (count != null) {
            bundle.putInt(Exercise.PreferencesKey.COUNT.getKey(), count.intValue());
        }
        Double confidenceScore = exerciseEntity.getPreferences().getConfidenceScore();
        if (confidenceScore != null) {
            bundle.putDouble(Exercise.PreferencesKey.CONFIDENCE_SCORE.getKey(), confidenceScore.doubleValue());
        }
        return new Exercise(exerciseEntity.getGroupType(), exerciseEntity.getName(), exerciseEntity.getCategory(), bundle);
    }
}
