package com.google.android.appfunctions.schema.phone.v1;

import com.google.android.appfunctions.schema.types.v1.DateTime;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/phone/v1/CallRecord;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.phone.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CallRecord {

    /* renamed from: a  reason: collision with root package name */
    public final String f1285a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final String f1286c;
    public final Boolean d;
    public final DateTime e;
    public final Long f;
    public final String g;

    /* renamed from: h  reason: collision with root package name */
    public final String f1287h;

    public CallRecord(String str, String str2, String str3, Boolean bool, DateTime dateTime, Long l, String str4, String str5) {
        this.f1285a = str;
        this.b = str2;
        this.f1286c = str3;
        this.d = bool;
        this.e = dateTime;
        this.f = l;
        this.g = str4;
        this.f1287h = str5;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof CallRecord)) {
            return false;
        }
        CallRecord callRecord = (CallRecord) obj;
        if (!j.a(this.f1285a, callRecord.f1285a) || !j.a(this.b, callRecord.b) || !j.a(this.f1286c, callRecord.f1286c) || !j.a(this.d, callRecord.d) || !j.a(this.e, callRecord.e) || !j.a(this.f, callRecord.f) || !j.a(this.g, callRecord.g) || !j.a(this.f1287h, callRecord.f1287h)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1285a, this.b, this.f1286c, this.d, this.e, this.f, this.g, this.f1287h});
    }
}
