package com.google.android.appfunctions.schema.photos.v1;

import com.google.android.appfunctions.schema.types.v1.DateTime;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/photos/v1/FindMediaItemsParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.photos.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FindMediaItemsParams {

    /* renamed from: a  reason: collision with root package name */
    public final DateTime f1296a;
    public final DateTime b;

    /* renamed from: c  reason: collision with root package name */
    public final String f1297c;
    public final String d;
    public final Boolean e;
    public final String f;
    public final int g;

    public FindMediaItemsParams(DateTime dateTime, DateTime dateTime2, String str, String str2, Boolean bool, String str3, int i2) {
        this.f1296a = dateTime;
        this.b = dateTime2;
        this.f1297c = str;
        this.d = str2;
        this.e = bool;
        this.f = str3;
        this.g = i2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof FindMediaItemsParams)) {
            return false;
        }
        FindMediaItemsParams findMediaItemsParams = (FindMediaItemsParams) obj;
        if (!j.a(this.f1296a, findMediaItemsParams.f1296a) || !j.a(this.b, findMediaItemsParams.b) || !j.a(this.f1297c, findMediaItemsParams.f1297c) || !j.a(this.d, findMediaItemsParams.d) || !j.a(this.e, findMediaItemsParams.e) || !j.a(this.f, findMediaItemsParams.f) || this.g != findMediaItemsParams.g) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1296a, this.b, this.f1297c, this.d, this.e, this.f, Integer.valueOf(this.g)});
    }
}
