package com.google.android.appfunctions.schema.clock.alarms.v1;

import com.google.android.appfunctions.schema.types.v1.TimeOfDay;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/clock/alarms/v1/CreateAlarmParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.clock.alarms.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CreateAlarmParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1140a;
    public final TimeOfDay b;

    /* renamed from: c  reason: collision with root package name */
    public final String f1141c;
    public final DayPattern d;
    public final String e;

    public CreateAlarmParams(DayPattern dayPattern, TimeOfDay timeOfDay, String str, String str2, String str3) {
        this.f1140a = str;
        this.b = timeOfDay;
        this.f1141c = str2;
        this.d = dayPattern;
        this.e = str3;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof CreateAlarmParams)) {
            return false;
        }
        CreateAlarmParams createAlarmParams = (CreateAlarmParams) obj;
        if (!j.a(this.f1140a, createAlarmParams.f1140a) || !j.a(this.b, createAlarmParams.b) || !j.a(this.f1141c, createAlarmParams.f1141c) || !j.a(this.d, createAlarmParams.d) || !j.a(this.e, createAlarmParams.e)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1140a, this.b, this.f1141c, this.d, this.e});
    }
}
