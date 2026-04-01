package com.google.android.appfunctions.schema.tasks.v1;

import com.google.android.appfunctions.schema.types.v1.DateTime;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/tasks/v1/Task;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.tasks.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Task {

    /* renamed from: a  reason: collision with root package name */
    public final String f1314a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final String f1315c;
    public final Boolean d;
    public final String e;
    public final DateTime f;
    public final Boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final String f1316h;

    public Task(DateTime dateTime, Boolean bool, Boolean bool2, String str, String str2, String str3, String str4, String str5) {
        this.f1314a = str;
        this.b = str2;
        this.f1315c = str3;
        this.d = bool;
        this.e = str4;
        this.f = dateTime;
        this.g = bool2;
        this.f1316h = str5;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Task)) {
            return false;
        }
        Task task = (Task) obj;
        if (!j.a(this.f1314a, task.f1314a) || !j.a(this.b, task.b) || !j.a(this.f1315c, task.f1315c) || !j.a(this.d, task.d) || !j.a(this.e, task.e) || !j.a(this.f, task.f) || !j.a(this.g, task.g) || !j.a(this.f1316h, task.f1316h)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1314a, this.b, this.f1315c, this.d, this.e, this.f, this.g, this.f1316h});
    }
}
