package com.google.android.appfunctions.schema.memory.v1;

import com.google.android.appfunctions.schema.types.v1.DateTime;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/memory/v1/MemoryItem;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.memory.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MemoryItem {

    /* renamed from: a  reason: collision with root package name */
    public final String f1232a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final String f1233c;
    public final String d;
    public final List e;
    public final DateTime f;
    public final DateTime g;

    /* renamed from: h  reason: collision with root package name */
    public final String f1234h;

    public MemoryItem(String str, String str2, String str3, String str4, List list, DateTime dateTime, DateTime dateTime2, String str5) {
        this.f1232a = str;
        this.b = str2;
        this.f1233c = str3;
        this.d = str4;
        this.e = list;
        this.f = dateTime;
        this.g = dateTime2;
        this.f1234h = str5;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof MemoryItem)) {
            return false;
        }
        MemoryItem memoryItem = (MemoryItem) obj;
        if (!j.a(this.f1232a, memoryItem.f1232a) || !j.a(this.b, memoryItem.b) || !j.a(this.f1233c, memoryItem.f1233c) || !j.a(this.d, memoryItem.d) || !j.a(this.e, memoryItem.e) || !j.a(this.f, memoryItem.f) || !j.a(this.g, memoryItem.g) || !j.a(this.f1234h, memoryItem.f1234h)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1232a, this.b, this.f1233c, this.d, this.e, this.f, this.g, this.f1234h});
    }
}
