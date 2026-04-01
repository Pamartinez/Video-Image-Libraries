package com.google.android.appfunctions.schema.devicestate.v1;

import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/devicestate/v1/DeviceStateItem;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.devicestate.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DeviceStateItem {

    /* renamed from: a  reason: collision with root package name */
    public final String f1158a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final LocalizedString f1159c;
    public final String d;
    public final Long e;
    public final String f;

    public DeviceStateItem(String str, String str2, LocalizedString localizedString, String str3, Long l, String str4) {
        this.f1158a = str;
        this.b = str2;
        this.f1159c = localizedString;
        this.d = str3;
        this.e = l;
        this.f = str4;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof DeviceStateItem)) {
            return false;
        }
        DeviceStateItem deviceStateItem = (DeviceStateItem) obj;
        if (!j.a(this.f1158a, deviceStateItem.f1158a) || !j.a(this.b, deviceStateItem.b) || !j.a(this.f1159c, deviceStateItem.f1159c) || !j.a(this.d, deviceStateItem.d) || !j.a(this.e, deviceStateItem.e) || !j.a(this.f, deviceStateItem.f)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1158a, this.b, this.f1159c, this.d, this.e, this.f});
    }
}
