package com.google.android.appfunctions.schema.calendar.v1;

import com.google.android.appfunctions.schema.types.v1.SetField;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/calendar/v1/UpdateEventParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.calendar.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UpdateEventParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1133a;
    public final SetField b;

    /* renamed from: c  reason: collision with root package name */
    public final SetField f1134c;
    public final SetField d;
    public final SetField e;
    public final SetField f;
    public final SetField g;

    /* renamed from: h  reason: collision with root package name */
    public final SetField f1135h;

    /* renamed from: i  reason: collision with root package name */
    public final SetField f1136i;

    /* renamed from: j  reason: collision with root package name */
    public final SetField f1137j;

    public UpdateEventParams(String str, SetField setField, SetField setField2, SetField setField3, SetField setField4, SetField setField5, SetField setField6, SetField setField7, SetField setField8, SetField setField9) {
        this.f1133a = str;
        this.b = setField;
        this.f1134c = setField2;
        this.d = setField3;
        this.e = setField4;
        this.f = setField5;
        this.g = setField6;
        this.f1135h = setField7;
        this.f1136i = setField8;
        this.f1137j = setField9;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof UpdateEventParams)) {
            return false;
        }
        UpdateEventParams updateEventParams = (UpdateEventParams) obj;
        if (!j.a(this.f1133a, updateEventParams.f1133a) || !j.a(this.b, updateEventParams.b) || !j.a(this.f1134c, updateEventParams.f1134c) || !j.a(this.d, updateEventParams.d) || !j.a(this.e, updateEventParams.e) || !j.a(this.f, updateEventParams.f) || !j.a(this.g, updateEventParams.g) || !j.a(this.f1135h, updateEventParams.f1135h) || !j.a(this.f1136i, updateEventParams.f1136i) || !j.a(this.f1137j, updateEventParams.f1137j)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1133a, this.b, this.f1134c, this.d, this.e, this.f, this.g, this.f1135h, this.f1136i, this.f1137j});
    }
}
