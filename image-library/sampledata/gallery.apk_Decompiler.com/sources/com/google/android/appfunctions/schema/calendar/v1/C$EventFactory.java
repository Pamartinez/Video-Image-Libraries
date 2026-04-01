package com.google.android.appfunctions.schema.calendar.v1;

import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import com.google.android.appfunctions.schema.types.v1.DateTime;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.CalendarEventBundleWrapper;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1202t;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/google/android/appfunctions/schema/calendar/v1/$EventFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Lcom/google/android/appfunctions/schema/calendar/v1/Event;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.calendar.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* renamed from: com.google.android.appfunctions.schema.calendar.v1.$EventFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$EventFactory implements AppFunctionSerializableFactory<Event> {
    /* JADX WARNING: type inference failed for: r1v2, types: [com.google.android.appfunctions.schema.types.v1.$DateTimeFactory, java.lang.Object] */
    public final Event fromAppFunctionData(AppFunctionData appFunctionData) {
        AppFunctionData appFunctionData2 = appFunctionData;
        j.e(appFunctionData2, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData2, "com.google.android.appfunctions.schema.calendar.v1.Event");
        ? obj = new Object();
        String stringOrNull = appFunctionDataWithSpec.getStringOrNull("id");
        if (stringOrNull != null) {
            String stringOrNull2 = appFunctionDataWithSpec.getStringOrNull("title");
            if (stringOrNull2 != null) {
                String stringOrNull3 = appFunctionDataWithSpec.getStringOrNull("description");
                AppFunctionData appFunctionData3 = appFunctionDataWithSpec.getAppFunctionData("startDate");
                if (appFunctionData3 != null) {
                    DateTime fromAppFunctionData = obj.fromAppFunctionData(appFunctionData3);
                    AppFunctionData appFunctionData4 = appFunctionDataWithSpec.getAppFunctionData("endDate");
                    if (appFunctionData4 != null) {
                        DateTime fromAppFunctionData2 = obj.fromAppFunctionData(appFunctionData4);
                        List stringList = appFunctionDataWithSpec.getStringList("attendeeIds");
                        if (stringList == null) {
                            stringList = C1202t.d;
                        }
                        return new Event(stringOrNull, stringOrNull2, stringOrNull3, fromAppFunctionData, fromAppFunctionData2, stringList, appFunctionDataWithSpec.getBooleanOrNull(CalendarEventBundleWrapper.BUNDLE_KEY_ALL_DAY), appFunctionDataWithSpec.getStringOrNull("location"), appFunctionDataWithSpec.getStringOrNull("recurrenceSchedule"), appFunctionDataWithSpec.getStringOrNull("eventStatus"), appFunctionDataWithSpec.getBooleanOrNull("isReadOnly"), appFunctionDataWithSpec.getBooleanOrNull("isInPublicCalendar"), appFunctionDataWithSpec.getBooleanOrNull("isOrganizer"), appFunctionDataWithSpec.getStringOrNull("selfAttendeeStatus"));
                    }
                    throw new IllegalStateException("Required value was null.");
                }
                throw new IllegalStateException("Required value was null.");
            }
            throw new IllegalStateException("Required value was null.");
        }
        throw new IllegalStateException("Required value was null.");
    }

    public final /* bridge */ /* synthetic */ AppFunctionData.Builder getAppFunctionDataBuilder(String str) {
        return super.getAppFunctionDataBuilder(str);
    }

    public final /* bridge */ /* synthetic */ AppFunctionData getAppFunctionDataWithSpec(AppFunctionData appFunctionData, String str) {
        return super.getAppFunctionDataWithSpec(appFunctionData, str);
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [com.google.android.appfunctions.schema.types.v1.$DateTimeFactory, java.lang.Object] */
    public final AppFunctionData toAppFunctionData(Event event) {
        j.e(event, "appFunctionSerializable");
        ? obj = new Object();
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("com.google.android.appfunctions.schema.calendar.v1.Event");
        appFunctionDataBuilder.setString("id", event.f1126a);
        appFunctionDataBuilder.setString("title", event.b);
        String str = event.f1127c;
        if (str != null) {
            appFunctionDataBuilder.setString("description", str);
        }
        appFunctionDataBuilder.setAppFunctionData("startDate", obj.toAppFunctionData(event.d));
        appFunctionDataBuilder.setAppFunctionData("endDate", obj.toAppFunctionData(event.e));
        appFunctionDataBuilder.setStringList("attendeeIds", event.f);
        Boolean bool = event.g;
        if (bool != null) {
            appFunctionDataBuilder.setBoolean(CalendarEventBundleWrapper.BUNDLE_KEY_ALL_DAY, bool.booleanValue());
        }
        String str2 = event.f1128h;
        if (str2 != null) {
            appFunctionDataBuilder.setString("location", str2);
        }
        String str3 = event.f1129i;
        if (str3 != null) {
            appFunctionDataBuilder.setString("recurrenceSchedule", str3);
        }
        String str4 = event.f1130j;
        if (str4 != null) {
            appFunctionDataBuilder.setString("eventStatus", str4);
        }
        Boolean bool2 = event.k;
        if (bool2 != null) {
            appFunctionDataBuilder.setBoolean("isReadOnly", bool2.booleanValue());
        }
        Boolean bool3 = event.l;
        if (bool3 != null) {
            appFunctionDataBuilder.setBoolean("isInPublicCalendar", bool3.booleanValue());
        }
        Boolean bool4 = event.m;
        if (bool4 != null) {
            appFunctionDataBuilder.setBoolean("isOrganizer", bool4.booleanValue());
        }
        String str5 = event.n;
        if (str5 != null) {
            appFunctionDataBuilder.setString("selfAttendeeStatus", str5);
        }
        return appFunctionDataBuilder.build();
    }
}
