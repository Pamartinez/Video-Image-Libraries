package com.google.android.appfunctions.schema.photos.v1;

import com.google.android.appfunctions.schema.types.v1.DateTime;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/photos/v1/Album;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.photos.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Album {

    /* renamed from: a  reason: collision with root package name */
    public final String f1292a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final DateTime f1293c;

    public Album(String str, String str2, DateTime dateTime) {
        j.e(str, "id");
        j.e(str2, "title");
        j.e(dateTime, "dateCreated");
        this.f1292a = str;
        this.b = str2;
        this.f1293c = dateTime;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Album)) {
            return false;
        }
        Album album = (Album) obj;
        if (!j.a(this.f1292a, album.f1292a) || !j.a(this.b, album.b) || !j.a(this.f1293c, album.f1293c)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1292a, this.b, this.f1293c});
    }
}
