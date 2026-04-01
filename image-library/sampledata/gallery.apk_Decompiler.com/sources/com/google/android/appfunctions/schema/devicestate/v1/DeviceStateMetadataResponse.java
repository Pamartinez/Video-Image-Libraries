package com.google.android.appfunctions.schema.devicestate.v1;

import java.util.ArrayList;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/devicestate/v1/DeviceStateMetadataResponse;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.devicestate.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DeviceStateMetadataResponse {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList f1163a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final String f1164c;
    public final ArrayList d;

    public DeviceStateMetadataResponse(ArrayList arrayList, String str, String str2, ArrayList arrayList2) {
        this.f1163a = arrayList;
        this.b = str;
        this.f1164c = str2;
        this.d = arrayList2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof DeviceStateMetadataResponse)) {
            return false;
        }
        DeviceStateMetadataResponse deviceStateMetadataResponse = (DeviceStateMetadataResponse) obj;
        if (!this.f1163a.equals(deviceStateMetadataResponse.f1163a) || !this.b.equals(deviceStateMetadataResponse.b) || !j.a(this.f1164c, deviceStateMetadataResponse.f1164c) || !this.d.equals(deviceStateMetadataResponse.d)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1163a, this.b, this.f1164c, this.d});
    }
}
