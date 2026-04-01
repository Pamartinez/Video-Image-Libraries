package com.google.android.appfunctions.schema.devicestate.v1;

import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/google/android/appfunctions/schema/devicestate/v1/$DeviceStateItemMetadataFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Lcom/google/android/appfunctions/schema/devicestate/v1/DeviceStateItemMetadata;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.devicestate.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* renamed from: com.google.android.appfunctions.schema.devicestate.v1.$DeviceStateItemMetadataFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$DeviceStateItemMetadataFactory implements AppFunctionSerializableFactory<DeviceStateItemMetadata> {
    /* JADX WARNING: type inference failed for: r10v1, types: [com.google.android.appfunctions.schema.devicestate.v1.$LocalizedStringFactory, java.lang.Object] */
    public final DeviceStateItemMetadata fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData, "com.google.android.appfunctions.schema.devicestate.v1.DeviceStateItemMetadata");
        ? obj = new Object();
        String stringOrNull = appFunctionDataWithSpec.getStringOrNull("key");
        if (stringOrNull != null) {
            String stringOrNull2 = appFunctionDataWithSpec.getStringOrNull("purpose");
            if (stringOrNull2 != null) {
                AppFunctionData appFunctionData2 = appFunctionDataWithSpec.getAppFunctionData("name");
                return new DeviceStateItemMetadata(stringOrNull, stringOrNull2, appFunctionData2 != null ? obj.fromAppFunctionData(appFunctionData2) : null, appFunctionDataWithSpec.getStringOrNull("sensitivity"), appFunctionDataWithSpec.getBooleanOrNull("writable"), appFunctionDataWithSpec.getStringOrNull("possibleValues"), appFunctionDataWithSpec.getStringOrNull("hintText"));
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

    /* JADX WARNING: type inference failed for: r0v1, types: [com.google.android.appfunctions.schema.devicestate.v1.$LocalizedStringFactory, java.lang.Object] */
    public final AppFunctionData toAppFunctionData(DeviceStateItemMetadata deviceStateItemMetadata) {
        j.e(deviceStateItemMetadata, "appFunctionSerializable");
        ? obj = new Object();
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("com.google.android.appfunctions.schema.devicestate.v1.DeviceStateItemMetadata");
        appFunctionDataBuilder.setString("key", deviceStateItemMetadata.f1160a);
        appFunctionDataBuilder.setString("purpose", deviceStateItemMetadata.b);
        LocalizedString localizedString = deviceStateItemMetadata.f1161c;
        if (localizedString != null) {
            appFunctionDataBuilder.setAppFunctionData("name", obj.toAppFunctionData(localizedString));
        }
        String str = deviceStateItemMetadata.d;
        if (str != null) {
            appFunctionDataBuilder.setString("sensitivity", str);
        }
        Boolean bool = deviceStateItemMetadata.e;
        if (bool != null) {
            appFunctionDataBuilder.setBoolean("writable", bool.booleanValue());
        }
        String str2 = deviceStateItemMetadata.f;
        if (str2 != null) {
            appFunctionDataBuilder.setString("possibleValues", str2);
        }
        String str3 = deviceStateItemMetadata.g;
        if (str3 != null) {
            appFunctionDataBuilder.setString("hintText", str3);
        }
        return appFunctionDataBuilder.build();
    }
}
