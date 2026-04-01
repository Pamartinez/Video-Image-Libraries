package com.google.android.appfunctions.schema.clock.alarms.v1;

import com.google.android.appfunctions.schema.types.v1.DateTime;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/clock/alarms/v1/FindAlarmsParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.clock.alarms.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FindAlarmsParams {

    /* renamed from: a  reason: collision with root package name */
    public final DateTime f1143a;
    public final DateTime b;

    /* renamed from: c  reason: collision with root package name */
    public final String f1144c;
    public final String d;
    public final DayPattern e;
    public final int f;
    public final String g;

    public FindAlarmsParams(DateTime dateTime, DateTime dateTime2, String str, String str2, DayPattern dayPattern, int i2, String str3) {
        this.f1143a = dateTime;
        this.b = dateTime2;
        this.f1144c = str;
        this.d = str2;
        this.e = dayPattern;
        this.f = i2;
        this.g = str3;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof FindAlarmsParams)) {
            return false;
        }
        FindAlarmsParams findAlarmsParams = (FindAlarmsParams) obj;
        if (!j.a(this.f1143a, findAlarmsParams.f1143a) || !j.a(this.b, findAlarmsParams.b) || !j.a(this.f1144c, findAlarmsParams.f1144c) || !j.a(this.d, findAlarmsParams.d) || !j.a(this.e, findAlarmsParams.e) || this.f != findAlarmsParams.f || !j.a(this.g, findAlarmsParams.g)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1143a, this.b, this.f1144c, this.d, this.e, Integer.valueOf(this.f), this.g});
    }
}
