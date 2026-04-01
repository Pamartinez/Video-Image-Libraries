package com.google.android.appfunctions.schema.devicestate.v1;

import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/devicestate/v1/SetDeviceStateItemParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.devicestate.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SetDeviceStateItemParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1186a;
    public final List b;

    /* renamed from: c  reason: collision with root package name */
    public final String f1187c;
    public final Boolean d;

    public SetDeviceStateItemParams(Boolean bool, String str, String str2, List list) {
        this.f1186a = str;
        this.b = list;
        this.f1187c = str2;
        this.d = bool;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof SetDeviceStateItemParams)) {
            return false;
        }
        SetDeviceStateItemParams setDeviceStateItemParams = (SetDeviceStateItemParams) obj;
        if (!j.a(this.f1186a, setDeviceStateItemParams.f1186a) || !j.a(this.b, setDeviceStateItemParams.b) || !j.a(this.f1187c, setDeviceStateItemParams.f1187c) || !j.a(this.d, setDeviceStateItemParams.d)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1186a, this.b, this.f1187c, this.d});
    }
}
