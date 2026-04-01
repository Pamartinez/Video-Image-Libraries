package com.google.android.appfunctions.schema.calendar.v1;

import com.google.android.appfunctions.schema.types.v1.DateTime;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/calendar/v1/FindEventsParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.calendar.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FindEventsParams {

    /* renamed from: a  reason: collision with root package name */
    public final DateTime f1131a;
    public final DateTime b;

    /* renamed from: c  reason: collision with root package name */
    public final String f1132c;
    public final List d;
    public final int e;
    public final String f;

    public FindEventsParams(DateTime dateTime, DateTime dateTime2, String str, List list, int i2, String str2) {
        this.f1131a = dateTime;
        this.b = dateTime2;
        this.f1132c = str;
        this.d = list;
        this.e = i2;
        this.f = str2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof FindEventsParams)) {
            return false;
        }
        FindEventsParams findEventsParams = (FindEventsParams) obj;
        if (!j.a(this.f1131a, findEventsParams.f1131a) || !j.a(this.b, findEventsParams.b) || !j.a(this.f1132c, findEventsParams.f1132c) || !j.a(this.d, findEventsParams.d) || this.e != findEventsParams.e || !j.a(this.f, findEventsParams.f)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1131a, this.b, this.f1132c, this.d, Integer.valueOf(this.e), this.f});
    }
}
