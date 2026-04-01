package com.google.android.appfunctions.schema.tasks.v1;

import com.google.android.appfunctions.schema.types.v1.SetField;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/tasks/v1/UpdateTaskCategoryParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.tasks.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UpdateTaskCategoryParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1319a;
    public final SetField b;

    /* renamed from: c  reason: collision with root package name */
    public final SetField f1320c;

    public UpdateTaskCategoryParams(String str, SetField setField, SetField setField2) {
        this.f1319a = str;
        this.b = setField;
        this.f1320c = setField2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof UpdateTaskCategoryParams)) {
            return false;
        }
        UpdateTaskCategoryParams updateTaskCategoryParams = (UpdateTaskCategoryParams) obj;
        if (!j.a(this.f1319a, updateTaskCategoryParams.f1319a) || !j.a(this.b, updateTaskCategoryParams.b) || !j.a(this.f1320c, updateTaskCategoryParams.f1320c)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1319a, this.b, this.f1320c});
    }
}
