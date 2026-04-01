package com.google.android.appfunctions.schema.tasks.v1;

import com.google.android.appfunctions.schema.types.v1.SetField;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/tasks/v1/UpdateTaskParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.tasks.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UpdateTaskParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1321a;
    public final SetField b;

    /* renamed from: c  reason: collision with root package name */
    public final SetField f1322c;
    public final SetField d;
    public final SetField e;
    public final SetField f;
    public final SetField g;

    public UpdateTaskParams(String str, SetField setField, SetField setField2, SetField setField3, SetField setField4, SetField setField5, SetField setField6) {
        this.f1321a = str;
        this.b = setField;
        this.f1322c = setField2;
        this.d = setField3;
        this.e = setField4;
        this.f = setField5;
        this.g = setField6;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof UpdateTaskParams)) {
            return false;
        }
        UpdateTaskParams updateTaskParams = (UpdateTaskParams) obj;
        if (!j.a(this.f1321a, updateTaskParams.f1321a) || !j.a(this.b, updateTaskParams.b) || !j.a(this.f1322c, updateTaskParams.f1322c) || !j.a(this.d, updateTaskParams.d) || !j.a(this.e, updateTaskParams.e) || !j.a(this.f, updateTaskParams.f) || !j.a(this.g, updateTaskParams.g)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1321a, this.b, this.f1322c, this.d, this.e, this.f, this.g});
    }
}
