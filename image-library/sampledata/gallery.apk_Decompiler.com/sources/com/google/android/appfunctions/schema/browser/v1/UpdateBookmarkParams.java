package com.google.android.appfunctions.schema.browser.v1;

import com.google.android.appfunctions.schema.types.v1.SetField;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/browser/v1/UpdateBookmarkParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.browser.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UpdateBookmarkParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1119a;
    public final SetField b;

    /* renamed from: c  reason: collision with root package name */
    public final SetField f1120c;

    public UpdateBookmarkParams(String str, SetField setField, SetField setField2) {
        this.f1119a = str;
        this.b = setField;
        this.f1120c = setField2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof UpdateBookmarkParams)) {
            return false;
        }
        UpdateBookmarkParams updateBookmarkParams = (UpdateBookmarkParams) obj;
        if (!j.a(this.f1119a, updateBookmarkParams.f1119a) || !j.a(this.b, updateBookmarkParams.b) || !j.a(this.f1120c, updateBookmarkParams.f1120c)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1119a, this.b, this.f1120c});
    }
}
