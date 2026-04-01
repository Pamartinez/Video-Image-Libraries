package com.google.android.appfunctions.schema.nearbysharing.v1;

import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/nearbysharing/v1/FindNearbyDevicesParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.nearbysharing.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FindNearbyDevicesParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1248a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final Boolean f1249c;
    public final Long d;
    public final int e;

    public FindNearbyDevicesParams(String str, String str2, Boolean bool, Long l, int i2) {
        this.f1248a = str;
        this.b = str2;
        this.f1249c = bool;
        this.d = l;
        this.e = i2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof FindNearbyDevicesParams)) {
            return false;
        }
        FindNearbyDevicesParams findNearbyDevicesParams = (FindNearbyDevicesParams) obj;
        if (!j.a(this.f1248a, findNearbyDevicesParams.f1248a) || !j.a(this.b, findNearbyDevicesParams.b) || !j.a(this.f1249c, findNearbyDevicesParams.f1249c) || !j.a(this.d, findNearbyDevicesParams.d) || this.e != findNearbyDevicesParams.e) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1248a, this.b, this.f1249c, this.d, Integer.valueOf(this.e)});
    }
}
