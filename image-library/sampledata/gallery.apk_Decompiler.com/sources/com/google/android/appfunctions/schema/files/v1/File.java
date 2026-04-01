package com.google.android.appfunctions.schema.files.v1;

import com.google.android.appfunctions.schema.types.v1.DateTime;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/files/v1/File;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.files.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class File {

    /* renamed from: a  reason: collision with root package name */
    public final String f1190a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final DateTime f1191c;

    public File(String str, String str2, DateTime dateTime) {
        this.f1190a = str;
        this.b = str2;
        this.f1191c = dateTime;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof File)) {
            return false;
        }
        File file = (File) obj;
        if (!j.a(this.f1190a, file.f1190a) || !j.a(this.b, file.b) || !j.a(this.f1191c, file.f1191c)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1190a, this.b, this.f1191c});
    }
}
