package com.google.android.appfunctions.schema.clock.alarms.v1;

import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import com.google.android.appfunctions.schema.types.v1.TimeOfDay;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/google/android/appfunctions/schema/clock/alarms/v1/$AlarmFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Lcom/google/android/appfunctions/schema/clock/alarms/v1/Alarm;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.clock.alarms.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* renamed from: com.google.android.appfunctions.schema.clock.alarms.v1.$AlarmFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$AlarmFactory implements AppFunctionSerializableFactory<Alarm> {
    /* JADX WARNING: type inference failed for: r9v1, types: [com.google.android.appfunctions.schema.types.v1.$TimeOfDayFactory, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v2, types: [com.google.android.appfunctions.schema.clock.alarms.v1.$DayPatternFactory, java.lang.Object] */
    public final Alarm fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData, "com.google.android.appfunctions.schema.clock.alarms.v1.Alarm");
        ? obj = new Object();
        ? obj2 = new Object();
        String stringOrNull = appFunctionDataWithSpec.getStringOrNull("id");
        if (stringOrNull != null) {
            String stringOrNull2 = appFunctionDataWithSpec.getStringOrNull("label");
            AppFunctionData appFunctionData2 = appFunctionDataWithSpec.getAppFunctionData("time");
            if (appFunctionData2 != null) {
                TimeOfDay fromAppFunctionData = obj.fromAppFunctionData(appFunctionData2);
                String stringOrNull3 = appFunctionDataWithSpec.getStringOrNull("alarmStatus");
                AppFunctionData appFunctionData3 = appFunctionDataWithSpec.getAppFunctionData("dayPattern");
                return new Alarm(appFunctionData3 != null ? obj2.fromAppFunctionData(appFunctionData3) : null, fromAppFunctionData, stringOrNull, stringOrNull2, stringOrNull3);
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

    /* JADX WARNING: type inference failed for: r0v1, types: [com.google.android.appfunctions.schema.types.v1.$TimeOfDayFactory, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v0, types: [com.google.android.appfunctions.schema.clock.alarms.v1.$DayPatternFactory, java.lang.Object] */
    public final AppFunctionData toAppFunctionData(Alarm alarm) {
        j.e(alarm, "appFunctionSerializable");
        ? obj = new Object();
        ? obj2 = new Object();
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("com.google.android.appfunctions.schema.clock.alarms.v1.Alarm");
        appFunctionDataBuilder.setString("id", alarm.f1138a);
        String str = alarm.b;
        if (str != null) {
            appFunctionDataBuilder.setString("label", str);
        }
        appFunctionDataBuilder.setAppFunctionData("time", obj.toAppFunctionData(alarm.f1139c));
        String str2 = alarm.d;
        if (str2 != null) {
            appFunctionDataBuilder.setString("alarmStatus", str2);
        }
        DayPattern dayPattern = alarm.e;
        if (dayPattern != null) {
            appFunctionDataBuilder.setAppFunctionData("dayPattern", obj2.toAppFunctionData(dayPattern));
        }
        return appFunctionDataBuilder.build();
    }
}
