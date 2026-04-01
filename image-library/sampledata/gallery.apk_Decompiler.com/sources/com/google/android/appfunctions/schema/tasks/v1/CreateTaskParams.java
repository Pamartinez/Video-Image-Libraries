package com.google.android.appfunctions.schema.tasks.v1;

import com.google.android.appfunctions.schema.types.v1.DateTime;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/tasks/v1/CreateTaskParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.tasks.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CreateTaskParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1308a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final Boolean f1309c;
    public final DateTime d;
    public final Boolean e;
    public final String f;
    public final String g;

    /* renamed from: h  reason: collision with root package name */
    public final String f1310h;

    public CreateTaskParams(DateTime dateTime, Boolean bool, Boolean bool2, String str, String str2, String str3, String str4, String str5) {
        this.f1308a = str;
        this.b = str2;
        this.f1309c = bool;
        this.d = dateTime;
        this.e = bool2;
        this.f = str3;
        this.g = str4;
        this.f1310h = str5;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof CreateTaskParams)) {
            return false;
        }
        CreateTaskParams createTaskParams = (CreateTaskParams) obj;
        if (!j.a(this.f1308a, createTaskParams.f1308a) || !j.a(this.b, createTaskParams.b) || !j.a(this.f1309c, createTaskParams.f1309c) || !j.a(this.d, createTaskParams.d) || !j.a(this.e, createTaskParams.e) || !j.a(this.f, createTaskParams.f) || !j.a(this.g, createTaskParams.g) || !j.a(this.f1310h, createTaskParams.f1310h)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1308a, this.b, this.f1309c, this.d, this.e, this.f, this.g, this.f1310h});
    }
}
