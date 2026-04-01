package com.google.android.appfunctions.schema.devicestate.v1;

import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/devicestate/v1/DeviceStateItemMetadata;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.devicestate.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DeviceStateItemMetadata {

    /* renamed from: a  reason: collision with root package name */
    public final String f1160a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final LocalizedString f1161c;
    public final String d;
    public final Boolean e;
    public final String f;
    public final String g;

    public DeviceStateItemMetadata(String str, String str2, LocalizedString localizedString, String str3, Boolean bool, String str4, String str5) {
        this.f1160a = str;
        this.b = str2;
        this.f1161c = localizedString;
        this.d = str3;
        this.e = bool;
        this.f = str4;
        this.g = str5;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof DeviceStateItemMetadata)) {
            return false;
        }
        DeviceStateItemMetadata deviceStateItemMetadata = (DeviceStateItemMetadata) obj;
        if (!j.a(this.f1160a, deviceStateItemMetadata.f1160a) || !j.a(this.b, deviceStateItemMetadata.b) || !j.a(this.f1161c, deviceStateItemMetadata.f1161c) || !j.a(this.d, deviceStateItemMetadata.d) || !j.a(this.e, deviceStateItemMetadata.e) || !j.a(this.f, deviceStateItemMetadata.f) || !j.a(this.g, deviceStateItemMetadata.g)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1160a, this.b, this.f1161c, this.d, this.e, this.f, this.g});
    }
}
