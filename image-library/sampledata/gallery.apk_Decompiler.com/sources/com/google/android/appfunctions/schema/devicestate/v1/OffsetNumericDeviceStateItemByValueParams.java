package com.google.android.appfunctions.schema.devicestate.v1;

import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/devicestate/v1/OffsetNumericDeviceStateItemByValueParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.devicestate.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class OffsetNumericDeviceStateItemByValueParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1180a;
    public final List b;

    /* renamed from: c  reason: collision with root package name */
    public final double f1181c;
    public final Boolean d;

    public OffsetNumericDeviceStateItemByValueParams(String str, List list, double d2, Boolean bool) {
        this.f1180a = str;
        this.b = list;
        this.f1181c = d2;
        this.d = bool;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof OffsetNumericDeviceStateItemByValueParams)) {
            return false;
        }
        OffsetNumericDeviceStateItemByValueParams offsetNumericDeviceStateItemByValueParams = (OffsetNumericDeviceStateItemByValueParams) obj;
        if (!j.a(this.f1180a, offsetNumericDeviceStateItemByValueParams.f1180a) || !j.a(this.b, offsetNumericDeviceStateItemByValueParams.b) || this.f1181c != offsetNumericDeviceStateItemByValueParams.f1181c || !j.a(this.d, offsetNumericDeviceStateItemByValueParams.d)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1180a, this.b, Double.valueOf(this.f1181c), this.d});
    }
}
