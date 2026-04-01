package com.google.android.appfunctions.schema.clock.alarms.v1;

import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import com.google.android.appfunctions.schema.types.v1.DateTime;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/google/android/appfunctions/schema/clock/alarms/v1/$FindAlarmsParamsFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Lcom/google/android/appfunctions/schema/clock/alarms/v1/FindAlarmsParams;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.clock.alarms.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* renamed from: com.google.android.appfunctions.schema.clock.alarms.v1.$FindAlarmsParamsFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$FindAlarmsParamsFactory implements AppFunctionSerializableFactory<FindAlarmsParams> {
    /* JADX WARNING: type inference failed for: r12v1, types: [com.google.android.appfunctions.schema.types.v1.$DateTimeFactory, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v2, types: [com.google.android.appfunctions.schema.clock.alarms.v1.$DayPatternFactory, java.lang.Object] */
    public final FindAlarmsParams fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData, "com.google.android.appfunctions.schema.clock.alarms.v1.FindAlarmsParams");
        ? obj = new Object();
        ? obj2 = new Object();
        AppFunctionData appFunctionData2 = appFunctionDataWithSpec.getAppFunctionData("startTime");
        DayPattern dayPattern = null;
        DateTime fromAppFunctionData = appFunctionData2 != null ? obj.fromAppFunctionData(appFunctionData2) : null;
        AppFunctionData appFunctionData3 = appFunctionDataWithSpec.getAppFunctionData("endTime");
        DateTime fromAppFunctionData2 = appFunctionData3 != null ? obj.fromAppFunctionData(appFunctionData3) : null;
        String stringOrNull = appFunctionDataWithSpec.getStringOrNull("label");
        String stringOrNull2 = appFunctionDataWithSpec.getStringOrNull("alarmStatus");
        AppFunctionData appFunctionData4 = appFunctionDataWithSpec.getAppFunctionData("dayPattern");
        if (appFunctionData4 != null) {
            dayPattern = obj2.fromAppFunctionData(appFunctionData4);
        }
        DayPattern dayPattern2 = dayPattern;
        Integer intOrNull = appFunctionDataWithSpec.getIntOrNull("maxCount");
        if (intOrNull != null) {
            return new FindAlarmsParams(fromAppFunctionData, fromAppFunctionData2, stringOrNull, stringOrNull2, dayPattern2, intOrNull.intValue(), appFunctionDataWithSpec.getStringOrNull("sortingOrder"));
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
    /* JADX WARNING: type inference failed for: r1v0, types: [com.google.android.appfunctions.schema.clock.alarms.v1.$DayPatternFactory, java.lang.Object] */
    public final AppFunctionData toAppFunctionData(FindAlarmsParams findAlarmsParams) {
        j.e(findAlarmsParams, "appFunctionSerializable");
        ? obj = new Object();
        ? obj2 = new Object();
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("com.google.android.appfunctions.schema.clock.alarms.v1.FindAlarmsParams");
        DateTime dateTime = findAlarmsParams.f1143a;
        if (dateTime != null) {
            appFunctionDataBuilder.setAppFunctionData("startTime", obj.toAppFunctionData(dateTime));
        }
        DateTime dateTime2 = findAlarmsParams.b;
        if (dateTime2 != null) {
            appFunctionDataBuilder.setAppFunctionData("endTime", obj.toAppFunctionData(dateTime2));
        }
        String str = findAlarmsParams.f1144c;
        if (str != null) {
            appFunctionDataBuilder.setString("label", str);
        }
        String str2 = findAlarmsParams.d;
        if (str2 != null) {
            appFunctionDataBuilder.setString("alarmStatus", str2);
        }
        DayPattern dayPattern = findAlarmsParams.e;
        if (dayPattern != null) {
            appFunctionDataBuilder.setAppFunctionData("dayPattern", obj2.toAppFunctionData(dayPattern));
        }
        appFunctionDataBuilder.setInt("maxCount", findAlarmsParams.f);
        String str3 = findAlarmsParams.g;
        if (str3 != null) {
            appFunctionDataBuilder.setString("sortingOrder", str3);
        }
        return appFunctionDataBuilder.build();
    }
}
