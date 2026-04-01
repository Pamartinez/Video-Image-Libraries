package com.google.android.appfunctions.schema.clock.timers.v1;

import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/clock/timers/v1/Timer;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.clock.timers.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Timer {

    /* renamed from: a  reason: collision with root package name */
    public final String f1151a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final long f1152c;
    public final long d;
    public final String e;
    public final long f;
    public final Long g;

    /* renamed from: h  reason: collision with root package name */
    public final Boolean f1153h;

    public Timer(String str, String str2, long j2, long j3, String str3, long j8, Long l, Boolean bool) {
        this.f1151a = str;
        this.b = str2;
        this.f1152c = j2;
        this.d = j3;
        this.e = str3;
        this.f = j8;
        this.g = l;
        this.f1153h = bool;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Timer)) {
            return false;
        }
        Timer timer = (Timer) obj;
        if (!j.a(this.f1151a, timer.f1151a) || !j.a(this.b, timer.b) || this.f1152c != timer.f1152c || this.d != timer.d || !j.a(this.e, timer.e) || this.f != timer.f || !j.a(this.g, timer.g) || !j.a(this.f1153h, timer.f1153h)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1151a, this.b, Long.valueOf(this.f1152c), Long.valueOf(this.d), this.e, Long.valueOf(this.f), this.g, this.f1153h});
    }
}
