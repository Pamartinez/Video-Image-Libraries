package com.google.android.appfunctions.schema.phone.v1;

import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/phone/v1/AcceptCallParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.phone.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AcceptCallParams {

    /* renamed from: a  reason: collision with root package name */
    public final Boolean f1280a;
    public final Boolean b;

    /* renamed from: c  reason: collision with root package name */
    public final Boolean f1281c;

    public AcceptCallParams(Boolean bool, Boolean bool2, Boolean bool3) {
        this.f1280a = bool;
        this.b = bool2;
        this.f1281c = bool3;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof AcceptCallParams)) {
            return false;
        }
        AcceptCallParams acceptCallParams = (AcceptCallParams) obj;
        if (!j.a(this.f1280a, acceptCallParams.f1280a) || !j.a(this.b, acceptCallParams.b) || !j.a(this.f1281c, acceptCallParams.f1281c)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1280a, this.b, this.f1281c});
    }
}
