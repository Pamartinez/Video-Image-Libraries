package com.google.android.appfunctions.schema.clock.alarms.v1;

import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import com.google.android.appfunctions.schema.types.v1.C$SetFieldFactory;
import com.google.android.appfunctions.schema.types.v1.SetField;
import com.google.android.appfunctions.schema.types.v1.TimeOfDay;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/google/android/appfunctions/schema/clock/alarms/v1/$UpdateAlarmParamsFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Lcom/google/android/appfunctions/schema/clock/alarms/v1/UpdateAlarmParams;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.clock.alarms.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* renamed from: com.google.android.appfunctions.schema.clock.alarms.v1.$UpdateAlarmParamsFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$UpdateAlarmParamsFactory implements AppFunctionSerializableFactory<UpdateAlarmParams> {
    /* JADX WARNING: type inference failed for: r2v0, types: [androidx.appfunctions.internal.AppFunctionSerializableFactory, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v1, types: [androidx.appfunctions.internal.AppFunctionSerializableFactory, java.lang.Object] */
    public final UpdateAlarmParams fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData, "com.google.android.appfunctions.schema.clock.alarms.v1.UpdateAlarmParams");
        C$SetFieldFactory _setfieldfactory = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.PrimitiveTypeParameter(String.class));
        C$SetFieldFactory _setfieldfactory2 = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.SerializableTypeParameter(TimeOfDay.class, new Object()));
        C$SetFieldFactory _setfieldfactory3 = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.SerializableTypeParameter(DayPattern.class, new Object()));
        String stringOrNull = appFunctionDataWithSpec.getStringOrNull("alarmId");
        if (stringOrNull != null) {
            AppFunctionData appFunctionData2 = appFunctionDataWithSpec.getAppFunctionData("label");
            SetField setField = null;
            SetField fromAppFunctionData = appFunctionData2 != null ? _setfieldfactory.fromAppFunctionData(appFunctionData2) : null;
            AppFunctionData appFunctionData3 = appFunctionDataWithSpec.getAppFunctionData("time");
            SetField fromAppFunctionData2 = appFunctionData3 != null ? _setfieldfactory2.fromAppFunctionData(appFunctionData3) : null;
            AppFunctionData appFunctionData4 = appFunctionDataWithSpec.getAppFunctionData("alarmStatus");
            SetField fromAppFunctionData3 = appFunctionData4 != null ? _setfieldfactory.fromAppFunctionData(appFunctionData4) : null;
            AppFunctionData appFunctionData5 = appFunctionDataWithSpec.getAppFunctionData("dayPattern");
            if (appFunctionData5 != null) {
                setField = _setfieldfactory3.fromAppFunctionData(appFunctionData5);
            }
            return new UpdateAlarmParams(stringOrNull, fromAppFunctionData, fromAppFunctionData2, fromAppFunctionData3, setField);
        }
        throw new IllegalStateException("Required value was null.");
    }

    public final /* bridge */ /* synthetic */ AppFunctionData.Builder getAppFunctionDataBuilder(String str) {
        return super.getAppFunctionDataBuilder(str);
    }

    public final /* bridge */ /* synthetic */ AppFunctionData getAppFunctionDataWithSpec(AppFunctionData appFunctionData, String str) {
        return super.getAppFunctionDataWithSpec(appFunctionData, str);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [androidx.appfunctions.internal.AppFunctionSerializableFactory, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r4v1, types: [androidx.appfunctions.internal.AppFunctionSerializableFactory, java.lang.Object] */
    public final AppFunctionData toAppFunctionData(UpdateAlarmParams updateAlarmParams) {
        j.e(updateAlarmParams, "appFunctionSerializable");
        C$SetFieldFactory _setfieldfactory = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.PrimitiveTypeParameter(String.class));
        C$SetFieldFactory _setfieldfactory2 = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.SerializableTypeParameter(TimeOfDay.class, new Object()));
        C$SetFieldFactory _setfieldfactory3 = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.SerializableTypeParameter(DayPattern.class, new Object()));
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("com.google.android.appfunctions.schema.clock.alarms.v1.UpdateAlarmParams");
        appFunctionDataBuilder.setString("alarmId", updateAlarmParams.f1145a);
        SetField setField = updateAlarmParams.b;
        if (setField != null) {
            appFunctionDataBuilder.setAppFunctionData("label", _setfieldfactory.toAppFunctionData(setField));
        }
        SetField setField2 = updateAlarmParams.f1146c;
        if (setField2 != null) {
            appFunctionDataBuilder.setAppFunctionData("time", _setfieldfactory2.toAppFunctionData(setField2));
        }
        SetField setField3 = updateAlarmParams.d;
        if (setField3 != null) {
            appFunctionDataBuilder.setAppFunctionData("alarmStatus", _setfieldfactory.toAppFunctionData(setField3));
        }
        SetField setField4 = updateAlarmParams.e;
        if (setField4 != null) {
            appFunctionDataBuilder.setAppFunctionData("dayPattern", _setfieldfactory3.toAppFunctionData(setField4));
        }
        return appFunctionDataBuilder.build();
    }
}
