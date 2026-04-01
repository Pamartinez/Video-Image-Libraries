package com.google.android.appfunctions.schema.types.v1;

import java.util.Objects;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/types/v1/Date;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.types.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Date {

    /* renamed from: a  reason: collision with root package name */
    public final int f1324a;
    public final int b;

    /* renamed from: c  reason: collision with root package name */
    public final int f1325c;

    public Date(int i2, int i7, int i8) {
        this.f1324a = i2;
        this.b = i7;
        this.f1325c = i8;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r3 = (com.google.android.appfunctions.schema.types.v1.Date) r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r3) {
        /*
            r2 = this;
            boolean r0 = r3 instanceof com.google.android.appfunctions.schema.types.v1.Date
            if (r0 == 0) goto L_0x001a
            com.google.android.appfunctions.schema.types.v1.Date r3 = (com.google.android.appfunctions.schema.types.v1.Date) r3
            int r0 = r3.f1324a
            int r1 = r2.f1324a
            if (r1 != r0) goto L_0x001a
            int r0 = r2.b
            int r1 = r3.b
            if (r0 != r1) goto L_0x001a
            int r2 = r2.f1325c
            int r3 = r3.f1325c
            if (r2 != r3) goto L_0x001a
            r2 = 1
            return r2
        L_0x001a:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.appfunctions.schema.types.v1.Date.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.f1324a), Integer.valueOf(this.b), Integer.valueOf(this.f1325c)});
    }
}
