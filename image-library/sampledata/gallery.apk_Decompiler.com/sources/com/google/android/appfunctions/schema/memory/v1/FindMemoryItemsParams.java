package com.google.android.appfunctions.schema.memory.v1;

import com.google.android.appfunctions.schema.types.v1.DateTime;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/memory/v1/FindMemoryItemsParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.memory.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FindMemoryItemsParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1230a;
    public final DateTime b;

    /* renamed from: c  reason: collision with root package name */
    public final DateTime f1231c;
    public final Integer d;
    public final String e;

    public FindMemoryItemsParams(String str, DateTime dateTime, DateTime dateTime2, Integer num, String str2) {
        this.f1230a = str;
        this.b = dateTime;
        this.f1231c = dateTime2;
        this.d = num;
        this.e = str2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof FindMemoryItemsParams)) {
            return false;
        }
        FindMemoryItemsParams findMemoryItemsParams = (FindMemoryItemsParams) obj;
        if (!j.a(this.f1230a, findMemoryItemsParams.f1230a) || !j.a(this.b, findMemoryItemsParams.b) || !j.a(this.f1231c, findMemoryItemsParams.f1231c) || !j.a(this.d, findMemoryItemsParams.d) || !j.a(this.e, findMemoryItemsParams.e)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1230a, this.b, this.f1231c, this.d, this.e});
    }
}
