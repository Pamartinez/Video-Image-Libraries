package com.google.android.appfunctions.schema.notes.v1;

import java.util.ArrayList;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/notes/v1/Note;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.notes.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Note {

    /* renamed from: a  reason: collision with root package name */
    public final String f1262a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final String f1263c;
    public final ArrayList d;
    public final String e;

    public Note(String str, String str2, String str3, String str4, ArrayList arrayList) {
        this.f1262a = str;
        this.b = str2;
        this.f1263c = str3;
        this.d = arrayList;
        this.e = str4;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Note)) {
            return false;
        }
        Note note = (Note) obj;
        if (!this.f1262a.equals(note.f1262a) || !this.b.equals(note.b) || !j.a(this.f1263c, note.f1263c) || !this.d.equals(note.d) || !j.a(this.e, note.e)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1262a, this.b, this.f1263c, this.d, this.e});
    }
}
