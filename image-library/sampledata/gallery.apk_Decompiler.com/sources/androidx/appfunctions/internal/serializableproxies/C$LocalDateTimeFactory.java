package androidx.appfunctions.internal.serializableproxies;

import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import java.time.LocalDateTime;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"androidx/appfunctions/internal/serializableproxies/$LocalDateTimeFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Ljava/time/LocalDateTime;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* renamed from: androidx.appfunctions.internal.serializableproxies.$LocalDateTimeFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$LocalDateTimeFactory implements AppFunctionSerializableFactory<LocalDateTime> {
    public final /* bridge */ /* synthetic */ AppFunctionData.Builder getAppFunctionDataBuilder(String str) {
        return super.getAppFunctionDataBuilder(str);
    }

    public final /* bridge */ /* synthetic */ AppFunctionData getAppFunctionDataWithSpec(AppFunctionData appFunctionData, String str) {
        return super.getAppFunctionDataWithSpec(appFunctionData, str);
    }

    public LocalDateTime fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData, "java.time.LocalDateTime");
        Integer intOrNull = appFunctionDataWithSpec.getIntOrNull("year");
        if (intOrNull != null) {
            int intValue = intOrNull.intValue();
            Integer intOrNull2 = appFunctionDataWithSpec.getIntOrNull("month");
            if (intOrNull2 != null) {
                int intValue2 = intOrNull2.intValue();
                Integer intOrNull3 = appFunctionDataWithSpec.getIntOrNull("dayOfMonth");
                if (intOrNull3 != null) {
                    int intValue3 = intOrNull3.intValue();
                    Integer intOrNull4 = appFunctionDataWithSpec.getIntOrNull("hour");
                    if (intOrNull4 != null) {
                        int intValue4 = intOrNull4.intValue();
                        Integer intOrNull5 = appFunctionDataWithSpec.getIntOrNull("minute");
                        if (intOrNull5 != null) {
                            int intValue5 = intOrNull5.intValue();
                            Integer intOrNull6 = appFunctionDataWithSpec.getIntOrNull("second");
                            if (intOrNull6 != null) {
                                int intValue6 = intOrNull6.intValue();
                                Integer intOrNull7 = appFunctionDataWithSpec.getIntOrNull("nanoOfSecond");
                                if (intOrNull7 != null) {
                                    return new AppFunctionLocalDateTime(intValue, intValue2, intValue3, intValue4, intValue5, intValue6, intOrNull7.intValue()).toLocalDateTime();
                                }
                                throw new IllegalStateException("Required value was null.");
                            }
                            throw new IllegalStateException("Required value was null.");
                        }
                        throw new IllegalStateException("Required value was null.");
                    }
                    throw new IllegalStateException("Required value was null.");
                }
                throw new IllegalStateException("Required value was null.");
            }
            throw new IllegalStateException("Required value was null.");
        }
        throw new IllegalStateException("Required value was null.");
    }

    public AppFunctionData toAppFunctionData(LocalDateTime localDateTime) {
        j.e(localDateTime, "appFunctionSerializable");
        AppFunctionLocalDateTime fromLocalDateTime = AppFunctionLocalDateTime.Companion.fromLocalDateTime(localDateTime);
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("java.time.LocalDateTime");
        appFunctionDataBuilder.setInt("year", fromLocalDateTime.getYear());
        appFunctionDataBuilder.setInt("month", fromLocalDateTime.getMonth());
        appFunctionDataBuilder.setInt("dayOfMonth", fromLocalDateTime.getDayOfMonth());
        appFunctionDataBuilder.setInt("hour", fromLocalDateTime.getHour());
        appFunctionDataBuilder.setInt("minute", fromLocalDateTime.getMinute());
        appFunctionDataBuilder.setInt("second", fromLocalDateTime.getSecond());
        appFunctionDataBuilder.setInt("nanoOfSecond", fromLocalDateTime.getNanoOfSecond());
        return appFunctionDataBuilder.build();
    }
}
