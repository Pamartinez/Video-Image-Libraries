package com.google.android.appfunctions.schema.tasks.v1;

import com.google.android.appfunctions.schema.types.v1.DateTime;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/tasks/v1/FindTasksParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.tasks.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FindTasksParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1312a;
    public final Boolean b;

    /* renamed from: c  reason: collision with root package name */
    public final DateTime f1313c;
    public final DateTime d;
    public final String e;
    public final int f;

    public FindTasksParams(String str, Boolean bool, DateTime dateTime, DateTime dateTime2, String str2, int i2) {
        this.f1312a = str;
        this.b = bool;
        this.f1313c = dateTime;
        this.d = dateTime2;
        this.e = str2;
        this.f = i2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof FindTasksParams)) {
            return false;
        }
        FindTasksParams findTasksParams = (FindTasksParams) obj;
        if (!j.a(this.f1312a, findTasksParams.f1312a) || !j.a(this.b, findTasksParams.b) || !j.a(this.f1313c, findTasksParams.f1313c) || !j.a(this.d, findTasksParams.d) || !j.a(this.e, findTasksParams.e) || this.f != findTasksParams.f) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1312a, this.b, this.f1313c, this.d, this.e, Integer.valueOf(this.f)});
    }
}
