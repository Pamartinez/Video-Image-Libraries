package com.google.android.appfunctions.schema.messages.v1;

import java.util.ArrayList;
import java.util.Objects;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/messages/v1/ReplyMessageParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.messages.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ReplyMessageParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1244a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final ArrayList f1245c;

    public ReplyMessageParams(String str, String str2, ArrayList arrayList) {
        this.f1244a = str;
        this.b = str2;
        this.f1245c = arrayList;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ReplyMessageParams)) {
            return false;
        }
        ReplyMessageParams replyMessageParams = (ReplyMessageParams) obj;
        if (!this.f1244a.equals(replyMessageParams.f1244a) || !this.b.equals(replyMessageParams.b) || !this.f1245c.equals(replyMessageParams.f1245c)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1244a, this.b, this.f1245c});
    }
}
