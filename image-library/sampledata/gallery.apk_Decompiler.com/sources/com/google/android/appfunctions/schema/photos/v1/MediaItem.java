package com.google.android.appfunctions.schema.photos.v1;

import com.google.android.appfunctions.schema.types.v1.DateTime;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/photos/v1/MediaItem;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.photos.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MediaItem {

    /* renamed from: a  reason: collision with root package name */
    public final String f1298a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final DateTime f1299c;
    public final String d;
    public final Boolean e;
    public final String f;
    public final String g;

    public MediaItem(String str, String str2, DateTime dateTime, String str3, Boolean bool, String str4, String str5) {
        j.e(dateTime, "dateCreated");
        this.f1298a = str;
        this.b = str2;
        this.f1299c = dateTime;
        this.d = str3;
        this.e = bool;
        this.f = str4;
        this.g = str5;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof MediaItem)) {
            return false;
        }
        MediaItem mediaItem = (MediaItem) obj;
        if (!j.a(this.f1298a, mediaItem.f1298a) || !j.a(this.b, mediaItem.b) || !j.a(this.f1299c, mediaItem.f1299c) || !j.a(this.d, mediaItem.d) || !j.a(this.e, mediaItem.e) || !j.a(this.f, mediaItem.f) || !j.a(this.g, mediaItem.g)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1298a, this.b, this.f1299c, this.d, this.e, this.f, this.g});
    }
}
