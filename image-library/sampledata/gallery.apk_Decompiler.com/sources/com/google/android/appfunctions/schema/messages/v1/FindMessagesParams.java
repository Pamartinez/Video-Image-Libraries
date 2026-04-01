package com.google.android.appfunctions.schema.messages.v1;

import com.google.android.appfunctions.schema.types.v1.DateTime;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/messages/v1/FindMessagesParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.messages.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FindMessagesParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1237a;
    public final List b;

    /* renamed from: c  reason: collision with root package name */
    public final DateTime f1238c;
    public final DateTime d;
    public final String e;
    public final Boolean f;
    public final int g;

    public FindMessagesParams(String str, List list, DateTime dateTime, DateTime dateTime2, String str2, Boolean bool, int i2) {
        this.f1237a = str;
        this.b = list;
        this.f1238c = dateTime;
        this.d = dateTime2;
        this.e = str2;
        this.f = bool;
        this.g = i2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof FindMessagesParams)) {
            return false;
        }
        FindMessagesParams findMessagesParams = (FindMessagesParams) obj;
        if (!j.a(this.f1237a, findMessagesParams.f1237a) || !j.a(this.b, findMessagesParams.b) || !j.a(this.f1238c, findMessagesParams.f1238c) || !j.a(this.d, findMessagesParams.d) || !j.a(this.e, findMessagesParams.e) || !j.a(this.f, findMessagesParams.f) || this.g != findMessagesParams.g) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1237a, this.b, this.f1238c, this.d, this.e, this.f, Integer.valueOf(this.g)});
    }
}
