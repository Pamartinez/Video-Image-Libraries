package com.samsung.android.sdk.moneta.travel.internal.model;

import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toTravelPlan", "Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelPlanInternal;", "", "pde-sdk-1.0.40_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TravelPlanInternalKt {
    public static final TravelPlanInternal toTravelPlan(String str) {
        j.e(str, "<this>");
        return TravelPlanInternal.Companion.fromString(str);
    }
}
