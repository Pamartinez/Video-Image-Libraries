package com.google.android.appfunctions.schema.fitness.v1;

import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/fitness/v1/ExerciseGoal;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.fitness.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ExerciseGoal {

    /* renamed from: a  reason: collision with root package name */
    public final Long f1194a;
    public final Long b;

    /* renamed from: c  reason: collision with root package name */
    public final Long f1195c;

    public ExerciseGoal(Long l, Long l8, Long l10) {
        this.f1194a = l;
        this.b = l8;
        this.f1195c = l10;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ExerciseGoal)) {
            return false;
        }
        ExerciseGoal exerciseGoal = (ExerciseGoal) obj;
        if (!j.a(this.f1194a, exerciseGoal.f1194a) || !j.a(this.b, exerciseGoal.b) || !j.a(this.f1195c, exerciseGoal.f1195c)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1194a, this.b, this.f1195c});
    }
}
