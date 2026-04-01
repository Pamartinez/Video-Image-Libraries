package com.google.android.appfunctions.schema.notes.v1;

import com.google.android.appfunctions.schema.types.v1.SetField;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/notes/v1/UpdateNoteParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.notes.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UpdateNoteParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1265a;
    public final SetField b;

    /* renamed from: c  reason: collision with root package name */
    public final SetField f1266c;
    public final SetField d;
    public final SetField e;

    public UpdateNoteParams(String str, SetField setField, SetField setField2, SetField setField3, SetField setField4) {
        this.f1265a = str;
        this.b = setField;
        this.f1266c = setField2;
        this.d = setField3;
        this.e = setField4;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof UpdateNoteParams)) {
            return false;
        }
        UpdateNoteParams updateNoteParams = (UpdateNoteParams) obj;
        if (!j.a(this.f1265a, updateNoteParams.f1265a) || !j.a(this.b, updateNoteParams.b) || !j.a(this.f1266c, updateNoteParams.f1266c) || !j.a(this.d, updateNoteParams.d) || !j.a(this.e, updateNoteParams.e)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1265a, this.b, this.f1266c, this.d, this.e});
    }
}
