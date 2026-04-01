package com.google.android.appfunctions.schema.notes.v1;

import com.google.android.appfunctions.schema.types.v1.DateTime;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/notes/v1/FindNotesParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.notes.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FindNotesParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1259a;
    public final DateTime b;

    /* renamed from: c  reason: collision with root package name */
    public final DateTime f1260c;
    public final int d;

    public FindNotesParams(int i2, DateTime dateTime, DateTime dateTime2, String str) {
        this.f1259a = str;
        this.b = dateTime;
        this.f1260c = dateTime2;
        this.d = i2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof FindNotesParams)) {
            return false;
        }
        FindNotesParams findNotesParams = (FindNotesParams) obj;
        if (!j.a(this.f1259a, findNotesParams.f1259a) || !j.a(this.b, findNotesParams.b) || !j.a(this.f1260c, findNotesParams.f1260c) || this.d != findNotesParams.d) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1259a, this.b, this.f1260c, Integer.valueOf(this.d)});
    }
}
