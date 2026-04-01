package com.google.android.appfunctions.schema.phone.v1;

import com.google.android.appfunctions.schema.types.v1.DateTime;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/phone/v1/FindCallRecordsParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.phone.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FindCallRecordsParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1288a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final DateTime f1289c;
    public final DateTime d;
    public final String e;
    public final int f;

    public FindCallRecordsParams(String str, String str2, DateTime dateTime, DateTime dateTime2, String str3, int i2) {
        this.f1288a = str;
        this.b = str2;
        this.f1289c = dateTime;
        this.d = dateTime2;
        this.e = str3;
        this.f = i2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof FindCallRecordsParams)) {
            return false;
        }
        FindCallRecordsParams findCallRecordsParams = (FindCallRecordsParams) obj;
        if (!j.a(this.f1288a, findCallRecordsParams.f1288a) || !j.a(this.b, findCallRecordsParams.b) || !j.a(this.f1289c, findCallRecordsParams.f1289c) || !j.a(this.d, findCallRecordsParams.d) || !j.a(this.e, findCallRecordsParams.e) || this.f != findCallRecordsParams.f) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1288a, this.b, this.f1289c, this.d, this.e, Integer.valueOf(this.f)});
    }
}
