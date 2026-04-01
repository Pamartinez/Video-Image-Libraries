package com.google.android.appfunctions.schema.clock.timers.v1;

import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/clock/timers/v1/CreateTimerParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.clock.timers.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CreateTimerParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1147a;
    public final long b;

    /* renamed from: c  reason: collision with root package name */
    public final String f1148c;
    public final String d;
    public final String e;

    public CreateTimerParams(String str, String str2, String str3, String str4, long j2) {
        this.f1147a = str;
        this.b = j2;
        this.f1148c = str2;
        this.d = str3;
        this.e = str4;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof CreateTimerParams)) {
            return false;
        }
        CreateTimerParams createTimerParams = (CreateTimerParams) obj;
        if (!j.a(this.f1147a, createTimerParams.f1147a) || this.b != createTimerParams.b || !j.a(this.f1148c, createTimerParams.f1148c) || !j.a(this.d, createTimerParams.d) || !j.a(this.e, createTimerParams.e)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1147a, Long.valueOf(this.b), this.f1148c, this.d, this.e});
    }
}
