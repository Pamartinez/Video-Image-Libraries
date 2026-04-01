package com.google.android.appfunctions.schema.persons.v1;

import android.net.Uri;
import java.util.ArrayList;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/persons/v1/Person;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.persons.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Person {

    /* renamed from: a  reason: collision with root package name */
    public final String f1270a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final String f1271c;
    public final String d;
    public final String e;
    public final Uri f;
    public final ArrayList g;

    /* renamed from: h  reason: collision with root package name */
    public final ArrayList f1272h;

    public Person(String str, String str2, String str3, String str4, String str5, Uri uri, ArrayList arrayList, ArrayList arrayList2) {
        this.f1270a = str;
        this.b = str2;
        this.f1271c = str3;
        this.d = str4;
        this.e = str5;
        this.f = uri;
        this.g = arrayList;
        this.f1272h = arrayList2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Person)) {
            return false;
        }
        Person person = (Person) obj;
        if (!this.f1270a.equals(person.f1270a) || !this.b.equals(person.b) || !j.a(this.f1271c, person.f1271c) || !j.a(this.d, person.d) || !j.a(this.e, person.e) || !j.a(this.f, person.f) || !this.g.equals(person.g) || !this.f1272h.equals(person.f1272h)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1270a, this.b, this.f1271c, this.d, this.e, this.f, this.g, this.f1272h});
    }
}
