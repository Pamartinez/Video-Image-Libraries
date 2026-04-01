package com.google.android.appfunctions.schema.types.v1;

import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/android/appfunctions/schema/types/v1/DateTime;", "", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.types.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DateTime {

    /* renamed from: a  reason: collision with root package name */
    public final String f1326a;
    public final Date b;

    /* renamed from: c  reason: collision with root package name */
    public final TimeOfDay f1327c;

    public DateTime(String str, Date date, TimeOfDay timeOfDay) {
        j.e(date, BuddyContract.Event.DATE);
        j.e(timeOfDay, "timeOfDay");
        this.f1326a = str;
        this.b = date;
        this.f1327c = timeOfDay;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof DateTime)) {
            return false;
        }
        DateTime dateTime = (DateTime) obj;
        if (!j.a(this.f1326a, dateTime.f1326a) || !j.a(this.b, dateTime.b) || !j.a(this.f1327c, dateTime.f1327c)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.f1326a, this.b, this.f1327c});
    }
}
