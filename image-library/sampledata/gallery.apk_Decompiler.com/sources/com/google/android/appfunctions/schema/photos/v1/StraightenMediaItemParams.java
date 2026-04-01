package com.google.android.appfunctions.schema.photos.v1;

import android.net.Uri;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/photos/v1/StraightenMediaItemParams;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.photos.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class StraightenMediaItemParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f1302a;
    public final Uri b;

    /* renamed from: c  reason: collision with root package name */
    public final Boolean f1303c;
    public final Double d;

    public StraightenMediaItemParams(String str, Uri uri, Boolean bool, Double d2) {
        this.f1302a = str;
        this.b = uri;
        this.f1303c = bool;
        this.d = d2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof StraightenMediaItemParams)) {
            return false;
        }
        StraightenMediaItemParams straightenMediaItemParams = (StraightenMediaItemParams) obj;
        if (!j.a(this.f1302a, straightenMediaItemParams.f1302a) || !j.a(this.b, straightenMediaItemParams.b) || !j.a(this.f1303c, straightenMediaItemParams.f1303c)) {
            return false;
        }
        Double d2 = straightenMediaItemParams.d;
        Double d3 = this.d;
        if (d3 == null) {
            if (d2 == null) {
                return true;
            }
            return false;
        } else if (d2 == null || d3.doubleValue() != d2.doubleValue()) {
            return false;
        } else {
            return true;
        }
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1302a, this.b, this.f1303c, this.d});
    }
}
