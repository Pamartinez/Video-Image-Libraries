package com.google.android.appfunctions.schema.devicestate.v1;

import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/devicestate/v1/GetPermissionsDeviceStateParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.devicestate.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GetPermissionsDeviceStateParams {

    /* renamed from: a  reason: collision with root package name */
    public final Boolean f1173a;

    public GetPermissionsDeviceStateParams(Boolean bool) {
        this.f1173a = bool;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof GetPermissionsDeviceStateParams)) {
            return false;
        }
        if (j.a(this.f1173a, ((GetPermissionsDeviceStateParams) obj).f1173a)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1173a});
    }
}
