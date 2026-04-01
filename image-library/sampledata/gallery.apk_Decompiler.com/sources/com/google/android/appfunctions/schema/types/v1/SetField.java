package com.google.android.appfunctions.schema.types.v1;

import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002¨\u0006\u0003"}, d2 = {"Lcom/google/android/appfunctions/schema/types/v1/SetField;", "T", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.types.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SetField<T> {

    /* renamed from: a  reason: collision with root package name */
    public final Object f1328a;

    public SetField(Object obj) {
        this.f1328a = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SetField)) {
            return false;
        }
        return j.a(this.f1328a, ((SetField) obj).f1328a);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1328a});
    }

    public final String toString() {
        Object obj = this.f1328a;
        StringBuilder sb2 = new StringBuilder(String.valueOf(obj).length() + 16);
        sb2.append("SetField(value=");
        sb2.append(obj);
        sb2.append(")");
        return sb2.toString();
    }
}
