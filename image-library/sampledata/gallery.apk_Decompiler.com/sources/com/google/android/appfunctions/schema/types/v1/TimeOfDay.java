package com.google.android.appfunctions.schema.types.v1;

import java.util.Objects;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/types/v1/TimeOfDay;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.types.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TimeOfDay {

    /* renamed from: a  reason: collision with root package name */
    public final int f1329a;
    public final int b;

    /* renamed from: c  reason: collision with root package name */
    public final int f1330c;
    public final int d;

    public TimeOfDay(int i2, int i7, int i8, int i10) {
        this.f1329a = i2;
        this.b = i7;
        this.f1330c = i8;
        this.d = i10;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r3 = (com.google.android.appfunctions.schema.types.v1.TimeOfDay) r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r3) {
        /*
            r2 = this;
            boolean r0 = r3 instanceof com.google.android.appfunctions.schema.types.v1.TimeOfDay
            if (r0 == 0) goto L_0x0020
            com.google.android.appfunctions.schema.types.v1.TimeOfDay r3 = (com.google.android.appfunctions.schema.types.v1.TimeOfDay) r3
            int r0 = r3.f1329a
            int r1 = r2.f1329a
            if (r1 != r0) goto L_0x0020
            int r0 = r2.b
            int r1 = r3.b
            if (r0 != r1) goto L_0x0020
            int r0 = r2.f1330c
            int r1 = r3.f1330c
            if (r0 != r1) goto L_0x0020
            int r2 = r2.d
            int r3 = r3.d
            if (r2 != r3) goto L_0x0020
            r2 = 1
            return r2
        L_0x0020:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.appfunctions.schema.types.v1.TimeOfDay.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.f1329a), Integer.valueOf(this.b), Integer.valueOf(this.f1330c), Integer.valueOf(this.d)});
    }
}
