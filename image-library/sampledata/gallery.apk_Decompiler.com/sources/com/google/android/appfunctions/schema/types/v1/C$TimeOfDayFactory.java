package com.google.android.appfunctions.schema.types.v1;

import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/google/android/appfunctions/schema/types/v1/$TimeOfDayFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Lcom/google/android/appfunctions/schema/types/v1/TimeOfDay;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.types.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* renamed from: com.google.android.appfunctions.schema.types.v1.$TimeOfDayFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$TimeOfDayFactory implements AppFunctionSerializableFactory<TimeOfDay> {
    public final TimeOfDay fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData, "com.google.android.appfunctions.schema.types.v1.TimeOfDay");
        Integer intOrNull = appFunctionDataWithSpec.getIntOrNull("hours");
        if (intOrNull != null) {
            int intValue = intOrNull.intValue();
            Integer intOrNull2 = appFunctionDataWithSpec.getIntOrNull("minutes");
            if (intOrNull2 != null) {
                int intValue2 = intOrNull2.intValue();
                Integer intOrNull3 = appFunctionDataWithSpec.getIntOrNull("seconds");
                if (intOrNull3 != null) {
                    int intValue3 = intOrNull3.intValue();
                    Integer intOrNull4 = appFunctionDataWithSpec.getIntOrNull("nanos");
                    if (intOrNull4 != null) {
                        return new TimeOfDay(intValue, intValue2, intValue3, intOrNull4.intValue());
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

    public final AppFunctionData toAppFunctionData(TimeOfDay timeOfDay) {
        j.e(timeOfDay, "appFunctionSerializable");
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("com.google.android.appfunctions.schema.types.v1.TimeOfDay");
        appFunctionDataBuilder.setInt("hours", timeOfDay.f1329a);
        appFunctionDataBuilder.setInt("minutes", timeOfDay.b);
        appFunctionDataBuilder.setInt("seconds", timeOfDay.f1330c);
        appFunctionDataBuilder.setInt("nanos", timeOfDay.d);
        return appFunctionDataBuilder.build();
    }
}
