package com.google.android.appfunctions.schema.messages.v1;

import android.net.Uri;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/messages/v1/Attachment;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.messages.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Attachment {

    /* renamed from: a  reason: collision with root package name */
    public final String f1235a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final Uri f1236c;

    public Attachment(String str, String str2, Uri uri) {
        j.e(uri, OCRServiceConstant.KEY_PARAM_URI);
        this.f1235a = str;
        this.b = str2;
        this.f1236c = uri;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Attachment)) {
            return false;
        }
        Attachment attachment = (Attachment) obj;
        if (!j.a(this.f1235a, attachment.f1235a) || !j.a(this.b, attachment.b) || !j.a(this.f1236c, attachment.f1236c)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1235a, this.b, this.f1236c});
    }
}
