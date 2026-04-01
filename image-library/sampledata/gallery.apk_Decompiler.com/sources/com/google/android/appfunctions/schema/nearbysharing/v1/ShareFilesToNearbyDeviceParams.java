package com.google.android.appfunctions.schema.nearbysharing.v1;

import java.util.ArrayList;
import java.util.Objects;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/nearbysharing/v1/ShareFilesToNearbyDeviceParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.nearbysharing.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ShareFilesToNearbyDeviceParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1252a;
    public final ArrayList b;

    public ShareFilesToNearbyDeviceParams(String str, ArrayList arrayList) {
        this.f1252a = str;
        this.b = arrayList;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ShareFilesToNearbyDeviceParams)) {
            return false;
        }
        ShareFilesToNearbyDeviceParams shareFilesToNearbyDeviceParams = (ShareFilesToNearbyDeviceParams) obj;
        if (!this.f1252a.equals(shareFilesToNearbyDeviceParams.f1252a) || !this.b.equals(shareFilesToNearbyDeviceParams.b)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1252a, this.b});
    }
}
