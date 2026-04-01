package com.google.android.appfunctions.schema.clock.timers.v1;

import com.google.android.appfunctions.schema.types.v1.SetField;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/clock/timers/v1/UpdateTimerParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.clock.timers.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UpdateTimerParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1154a;
    public final SetField b;

    /* renamed from: c  reason: collision with root package name */
    public final SetField f1155c;
    public final SetField d;

    public UpdateTimerParams(String str, SetField setField, SetField setField2, SetField setField3) {
        this.f1154a = str;
        this.b = setField;
        this.f1155c = setField2;
        this.d = setField3;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof UpdateTimerParams)) {
            return false;
        }
        UpdateTimerParams updateTimerParams = (UpdateTimerParams) obj;
        if (!j.a(this.f1154a, updateTimerParams.f1154a) || !j.a(this.b, updateTimerParams.b) || !j.a(this.f1155c, updateTimerParams.f1155c) || !j.a(this.d, updateTimerParams.d)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1154a, this.b, this.f1155c, this.d});
    }
}
