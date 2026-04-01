package com.google.android.appfunctions.schema.nearbysharing.v1;

import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/nearbysharing/v1/NearbyDevice;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.nearbysharing.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class NearbyDevice {

    /* renamed from: a  reason: collision with root package name */
    public final String f1250a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final String f1251c;
    public final Boolean d;

    public NearbyDevice(String str, String str2, String str3, Boolean bool) {
        this.f1250a = str;
        this.b = str2;
        this.f1251c = str3;
        this.d = bool;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof NearbyDevice)) {
            return false;
        }
        NearbyDevice nearbyDevice = (NearbyDevice) obj;
        if (!j.a(this.f1250a, nearbyDevice.f1250a) || !j.a(this.b, nearbyDevice.b) || !j.a(this.f1251c, nearbyDevice.f1251c) || !j.a(this.d, nearbyDevice.d)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1250a, this.b, this.f1251c, this.d});
    }
}
