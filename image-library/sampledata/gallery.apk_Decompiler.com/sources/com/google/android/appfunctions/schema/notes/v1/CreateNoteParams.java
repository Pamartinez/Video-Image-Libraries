package com.google.android.appfunctions.schema.notes.v1;

import java.util.ArrayList;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/notes/v1/CreateNoteParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.notes.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CreateNoteParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1256a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final ArrayList f1257c;
    public final String d;
    public final String e;

    public CreateNoteParams(String str, String str2, String str3, String str4, ArrayList arrayList) {
        this.f1256a = str;
        this.b = str2;
        this.f1257c = arrayList;
        this.d = str3;
        this.e = str4;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof CreateNoteParams)) {
            return false;
        }
        CreateNoteParams createNoteParams = (CreateNoteParams) obj;
        if (!this.f1256a.equals(createNoteParams.f1256a) || !j.a(this.b, createNoteParams.b) || !this.f1257c.equals(createNoteParams.f1257c) || !j.a(this.d, createNoteParams.d) || !j.a(this.e, createNoteParams.e)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1256a, this.b, this.f1257c, this.d, this.e});
    }
}
