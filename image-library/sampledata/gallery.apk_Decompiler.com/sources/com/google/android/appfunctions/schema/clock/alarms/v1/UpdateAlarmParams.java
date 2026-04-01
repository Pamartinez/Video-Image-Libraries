package com.google.android.appfunctions.schema.clock.alarms.v1;

import com.google.android.appfunctions.schema.types.v1.SetField;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/clock/alarms/v1/UpdateAlarmParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.clock.alarms.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UpdateAlarmParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1145a;
    public final SetField b;

    /* renamed from: c  reason: collision with root package name */
    public final SetField f1146c;
    public final SetField d;
    public final SetField e;

    public UpdateAlarmParams(String str, SetField setField, SetField setField2, SetField setField3, SetField setField4) {
        this.f1145a = str;
        this.b = setField;
        this.f1146c = setField2;
        this.d = setField3;
        this.e = setField4;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof UpdateAlarmParams)) {
            return false;
        }
        UpdateAlarmParams updateAlarmParams = (UpdateAlarmParams) obj;
        if (!j.a(this.f1145a, updateAlarmParams.f1145a) || !j.a(this.b, updateAlarmParams.b) || !j.a(this.f1146c, updateAlarmParams.f1146c) || !j.a(this.d, updateAlarmParams.d) || !j.a(this.e, updateAlarmParams.e)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1145a, this.b, this.f1146c, this.d, this.e});
    }
}
