package com.google.android.appfunctions.schema.devicestate.v1;

import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1196n;
import ne.C1202t;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/google/android/appfunctions/schema/devicestate/v1/$DeviceStateMetadataResponseFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Lcom/google/android/appfunctions/schema/devicestate/v1/DeviceStateMetadataResponse;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.devicestate.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* renamed from: com.google.android.appfunctions.schema.devicestate.v1.$DeviceStateMetadataResponseFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$DeviceStateMetadataResponseFactory implements AppFunctionSerializableFactory<DeviceStateMetadataResponse> {
    /* JADX WARNING: type inference failed for: r7v1, types: [java.lang.Object, com.google.android.appfunctions.schema.devicestate.v1.$PerScreenMetadataFactory] */
    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Object, com.google.android.appfunctions.schema.devicestate.v1.$ItemizationTypeFactory] */
    public final DeviceStateMetadataResponse fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData, "com.google.android.appfunctions.schema.devicestate.v1.DeviceStateMetadataResponse");
        ? obj = new Object();
        ? obj2 = new Object();
        List<AppFunctionData> appFunctionDataList = appFunctionDataWithSpec.getAppFunctionDataList("perScreenMetadata");
        List<AppFunctionData> list = C1202t.d;
        if (appFunctionDataList == null) {
            appFunctionDataList = list;
        }
        Iterable<AppFunctionData> iterable = appFunctionDataList;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (AppFunctionData fromAppFunctionData : iterable) {
            arrayList.add(obj.fromAppFunctionData(fromAppFunctionData));
        }
        String stringOrNull = appFunctionDataWithSpec.getStringOrNull("deviceLocale");
        if (stringOrNull != null) {
            String stringOrNull2 = appFunctionDataWithSpec.getStringOrNull("globalHintText");
            List<AppFunctionData> appFunctionDataList2 = appFunctionDataWithSpec.getAppFunctionDataList("itemizationTypes");
            if (appFunctionDataList2 != null) {
                list = appFunctionDataList2;
            }
            Iterable<AppFunctionData> iterable2 = list;
            ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable2, 10));
            for (AppFunctionData fromAppFunctionData2 : iterable2) {
                arrayList2.add(obj2.fromAppFunctionData(fromAppFunctionData2));
            }
            return new DeviceStateMetadataResponse(arrayList, stringOrNull, stringOrNull2, arrayList2);
        }
        throw new IllegalStateException("Required value was null.");
    }

    public final /* bridge */ /* synthetic */ AppFunctionData.Builder getAppFunctionDataBuilder(String str) {
        return super.getAppFunctionDataBuilder(str);
    }

    public final /* bridge */ /* synthetic */ AppFunctionData getAppFunctionDataWithSpec(AppFunctionData appFunctionData, String str) {
        return super.getAppFunctionDataWithSpec(appFunctionData, str);
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Object, com.google.android.appfunctions.schema.devicestate.v1.$PerScreenMetadataFactory] */
    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, com.google.android.appfunctions.schema.devicestate.v1.$ItemizationTypeFactory] */
    public final AppFunctionData toAppFunctionData(DeviceStateMetadataResponse deviceStateMetadataResponse) {
        j.e(deviceStateMetadataResponse, "appFunctionSerializable");
        ? obj = new Object();
        ? obj2 = new Object();
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("com.google.android.appfunctions.schema.devicestate.v1.DeviceStateMetadataResponse");
        ArrayList<PerScreenMetadata> arrayList = deviceStateMetadataResponse.f1163a;
        ArrayList arrayList2 = new ArrayList(C1196n.w0(arrayList, 10));
        for (PerScreenMetadata appFunctionData : arrayList) {
            arrayList2.add(obj.toAppFunctionData(appFunctionData));
        }
        appFunctionDataBuilder.setAppFunctionDataList("perScreenMetadata", arrayList2);
        appFunctionDataBuilder.setString("deviceLocale", deviceStateMetadataResponse.b);
        String str = deviceStateMetadataResponse.f1164c;
        if (str != null) {
            appFunctionDataBuilder.setString("globalHintText", str);
        }
        ArrayList<ItemizationType> arrayList3 = deviceStateMetadataResponse.d;
        ArrayList arrayList4 = new ArrayList(C1196n.w0(arrayList3, 10));
        for (ItemizationType appFunctionData2 : arrayList3) {
            arrayList4.add(obj2.toAppFunctionData(appFunctionData2));
        }
        appFunctionDataBuilder.setAppFunctionDataList("itemizationTypes", arrayList4);
        return appFunctionDataBuilder.build();
    }
}
