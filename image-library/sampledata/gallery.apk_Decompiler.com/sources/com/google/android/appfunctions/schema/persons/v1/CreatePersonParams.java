package com.google.android.appfunctions.schema.persons.v1;

import android.net.Uri;
import java.util.ArrayList;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/persons/v1/CreatePersonParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.persons.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CreatePersonParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1267a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final String f1268c;
    public final String d;
    public final Uri e;
    public final ArrayList f;
    public final ArrayList g;

    public CreatePersonParams(String str, String str2, String str3, String str4, Uri uri, ArrayList arrayList, ArrayList arrayList2) {
        this.f1267a = str;
        this.b = str2;
        this.f1268c = str3;
        this.d = str4;
        this.e = uri;
        this.f = arrayList;
        this.g = arrayList2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof CreatePersonParams)) {
            return false;
        }
        CreatePersonParams createPersonParams = (CreatePersonParams) obj;
        if (!this.f1267a.equals(createPersonParams.f1267a) || !j.a(this.b, createPersonParams.b) || !j.a(this.f1268c, createPersonParams.f1268c) || !j.a(this.d, createPersonParams.d) || !j.a(this.e, createPersonParams.e) || !this.f.equals(createPersonParams.f) || !this.g.equals(createPersonParams.g)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1267a, this.b, this.f1268c, this.d, this.e, this.f, this.g});
    }
}
