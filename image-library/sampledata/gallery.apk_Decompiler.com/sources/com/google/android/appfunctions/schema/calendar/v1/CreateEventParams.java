package com.google.android.appfunctions.schema.calendar.v1;

import com.google.android.appfunctions.schema.types.v1.DateTime;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/calendar/v1/CreateEventParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.calendar.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CreateEventParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1121a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final DateTime f1122c;
    public final DateTime d;
    public final List e;
    public final Boolean f;
    public final String g;

    /* renamed from: h  reason: collision with root package name */
    public final String f1123h;

    /* renamed from: i  reason: collision with root package name */
    public final String f1124i;

    /* renamed from: j  reason: collision with root package name */
    public final String f1125j;

    public CreateEventParams(String str, String str2, DateTime dateTime, DateTime dateTime2, List list, Boolean bool, String str3, String str4, String str5, String str6) {
        this.f1121a = str;
        this.b = str2;
        this.f1122c = dateTime;
        this.d = dateTime2;
        this.e = list;
        this.f = bool;
        this.g = str3;
        this.f1123h = str4;
        this.f1124i = str5;
        this.f1125j = str6;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof CreateEventParams)) {
            return false;
        }
        CreateEventParams createEventParams = (CreateEventParams) obj;
        if (!j.a(this.f1121a, createEventParams.f1121a) || !j.a(this.b, createEventParams.b) || !j.a(this.f1122c, createEventParams.f1122c) || !j.a(this.d, createEventParams.d) || !j.a(this.e, createEventParams.e) || !j.a(this.f, createEventParams.f) || !j.a(this.g, createEventParams.g) || !j.a(this.f1123h, createEventParams.f1123h) || !j.a(this.f1124i, createEventParams.f1124i) || !j.a(this.f1125j, createEventParams.f1125j)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1121a, this.b, this.f1122c, this.d, this.e, this.f, this.g, this.f1123h, this.f1124i, this.f1125j});
    }
}
