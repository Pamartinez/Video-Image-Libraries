package com.google.android.appfunctions.schema.devicestate.v1;

import java.util.ArrayList;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/devicestate/v1/PerScreenMetadata;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.devicestate.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PerScreenMetadata {

    /* renamed from: a  reason: collision with root package name */
    public final String f1184a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final String f1185c;
    public final ArrayList d;

    public PerScreenMetadata(String str, ArrayList arrayList, String str2, String str3) {
        this.f1184a = str;
        this.b = str2;
        this.f1185c = str3;
        this.d = arrayList;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof PerScreenMetadata)) {
            return false;
        }
        PerScreenMetadata perScreenMetadata = (PerScreenMetadata) obj;
        if (!this.f1184a.equals(perScreenMetadata.f1184a) || !j.a(this.b, perScreenMetadata.b) || !j.a(this.f1185c, perScreenMetadata.f1185c) || !this.d.equals(perScreenMetadata.d)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1184a, this.b, this.f1185c, this.d});
    }
}
