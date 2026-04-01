package com.google.android.appfunctions.schema.devicestate.v1;

import java.util.Objects;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/devicestate/v1/SetDeviceStateItemResponse;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.devicestate.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SetDeviceStateItemResponse {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f1188a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final String f1189c;

    public SetDeviceStateItemResponse(boolean z, String str, String str2) {
        this.f1188a = z;
        this.b = str;
        this.f1189c = str2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r3 = (com.google.android.appfunctions.schema.devicestate.v1.SetDeviceStateItemResponse) r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r3) {
        /*
            r2 = this;
            boolean r0 = r3 instanceof com.google.android.appfunctions.schema.devicestate.v1.SetDeviceStateItemResponse
            if (r0 == 0) goto L_0x0022
            com.google.android.appfunctions.schema.devicestate.v1.SetDeviceStateItemResponse r3 = (com.google.android.appfunctions.schema.devicestate.v1.SetDeviceStateItemResponse) r3
            boolean r0 = r3.f1188a
            boolean r1 = r2.f1188a
            if (r1 != r0) goto L_0x0022
            java.lang.String r0 = r2.b
            java.lang.String r1 = r3.b
            boolean r0 = kotlin.jvm.internal.j.a(r0, r1)
            if (r0 == 0) goto L_0x0022
            java.lang.String r2 = r2.f1189c
            java.lang.String r3 = r3.f1189c
            boolean r2 = kotlin.jvm.internal.j.a(r2, r3)
            if (r2 == 0) goto L_0x0022
            r2 = 1
            return r2
        L_0x0022:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.appfunctions.schema.devicestate.v1.SetDeviceStateItemResponse.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Boolean.valueOf(this.f1188a), this.b, this.f1189c});
    }
}
