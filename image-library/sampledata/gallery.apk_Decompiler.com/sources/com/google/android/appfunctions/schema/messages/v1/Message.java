package com.google.android.appfunctions.schema.messages.v1;

import com.google.android.appfunctions.schema.types.v1.DateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/messages/v1/Message;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.messages.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Message {

    /* renamed from: a  reason: collision with root package name */
    public final String f1240a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final String f1241c;
    public final String d;
    public final List e;
    public final DateTime f;
    public final DateTime g;

    /* renamed from: h  reason: collision with root package name */
    public final DateTime f1242h;

    /* renamed from: i  reason: collision with root package name */
    public final ArrayList f1243i;

    public Message(String str, String str2, String str3, String str4, List list, DateTime dateTime, DateTime dateTime2, DateTime dateTime3, ArrayList arrayList) {
        this.f1240a = str;
        this.b = str2;
        this.f1241c = str3;
        this.d = str4;
        this.e = list;
        this.f = dateTime;
        this.g = dateTime2;
        this.f1242h = dateTime3;
        this.f1243i = arrayList;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Message)) {
            return false;
        }
        Message message = (Message) obj;
        if (!this.f1240a.equals(message.f1240a) || !this.b.equals(message.b) || !this.f1241c.equals(message.f1241c) || !this.d.equals(message.d) || !this.e.equals(message.e) || !j.a(this.f, message.f) || !j.a(this.g, message.g) || !j.a(this.f1242h, message.f1242h) || !this.f1243i.equals(message.f1243i)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1240a, this.b, this.f1241c, this.d, this.e, this.f, this.g, this.f1242h, this.f1243i});
    }
}
