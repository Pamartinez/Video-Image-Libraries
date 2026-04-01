package com.google.android.appfunctions.schema.calendar.v1;

import com.google.android.appfunctions.schema.types.v1.DateTime;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/calendar/v1/Event;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.calendar.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Event {

    /* renamed from: a  reason: collision with root package name */
    public final String f1126a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final String f1127c;
    public final DateTime d;
    public final DateTime e;
    public final List f;
    public final Boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final String f1128h;

    /* renamed from: i  reason: collision with root package name */
    public final String f1129i;

    /* renamed from: j  reason: collision with root package name */
    public final String f1130j;
    public final Boolean k;
    public final Boolean l;
    public final Boolean m;
    public final String n;

    public Event(String str, String str2, String str3, DateTime dateTime, DateTime dateTime2, List list, Boolean bool, String str4, String str5, String str6, Boolean bool2, Boolean bool3, Boolean bool4, String str7) {
        this.f1126a = str;
        this.b = str2;
        this.f1127c = str3;
        this.d = dateTime;
        this.e = dateTime2;
        this.f = list;
        this.g = bool;
        this.f1128h = str4;
        this.f1129i = str5;
        this.f1130j = str6;
        this.k = bool2;
        this.l = bool3;
        this.m = bool4;
        this.n = str7;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Event)) {
            return false;
        }
        Event event = (Event) obj;
        if (!j.a(this.f1126a, event.f1126a) || !j.a(this.b, event.b) || !j.a(this.f1127c, event.f1127c) || !j.a(this.d, event.d) || !j.a(this.e, event.e) || !j.a(this.f, event.f) || !j.a(this.g, event.g) || !j.a(this.f1128h, event.f1128h) || !j.a(this.f1129i, event.f1129i) || !j.a(this.f1130j, event.f1130j) || !j.a(this.k, event.k) || !j.a(this.l, event.l) || !j.a(this.m, event.m) || !j.a(this.n, event.n)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1126a, this.b, this.f1127c, this.d, this.e, this.f, this.g, this.f1128h, this.f1129i, this.f1130j, this.k, this.l, this.m, this.n});
    }
}
