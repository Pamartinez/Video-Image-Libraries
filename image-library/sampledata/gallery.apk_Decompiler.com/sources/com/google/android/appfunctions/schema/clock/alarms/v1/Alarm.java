package com.google.android.appfunctions.schema.clock.alarms.v1;

import com.google.android.appfunctions.schema.types.v1.TimeOfDay;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/clock/alarms/v1/Alarm;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.clock.alarms.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Alarm {

    /* renamed from: a  reason: collision with root package name */
    public final String f1138a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final TimeOfDay f1139c;
    public final String d;
    public final DayPattern e;

    public Alarm(DayPattern dayPattern, TimeOfDay timeOfDay, String str, String str2, String str3) {
        this.f1138a = str;
        this.b = str2;
        this.f1139c = timeOfDay;
        this.d = str3;
        this.e = dayPattern;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Alarm)) {
            return false;
        }
        Alarm alarm = (Alarm) obj;
        if (!j.a(this.f1138a, alarm.f1138a) || !j.a(this.b, alarm.b) || !j.a(this.f1139c, alarm.f1139c) || !j.a(this.d, alarm.d) || !j.a(this.e, alarm.e)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1138a, this.b, this.f1139c, this.d, this.e});
    }
}
