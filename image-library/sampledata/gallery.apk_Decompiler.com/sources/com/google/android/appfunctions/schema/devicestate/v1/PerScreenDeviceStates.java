package com.google.android.appfunctions.schema.devicestate.v1;

import java.util.ArrayList;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/devicestate/v1/PerScreenDeviceStates;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.devicestate.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PerScreenDeviceStates {

    /* renamed from: a  reason: collision with root package name */
    public final String f1182a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final ArrayList f1183c;

    public PerScreenDeviceStates(String str, String str2, ArrayList arrayList) {
        this.f1182a = str;
        this.b = str2;
        this.f1183c = arrayList;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof PerScreenDeviceStates)) {
            return false;
        }
        PerScreenDeviceStates perScreenDeviceStates = (PerScreenDeviceStates) obj;
        if (!this.f1182a.equals(perScreenDeviceStates.f1182a) || !j.a(this.b, perScreenDeviceStates.b) || !this.f1183c.equals(perScreenDeviceStates.f1183c)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1182a, this.b, this.f1183c});
    }
}
