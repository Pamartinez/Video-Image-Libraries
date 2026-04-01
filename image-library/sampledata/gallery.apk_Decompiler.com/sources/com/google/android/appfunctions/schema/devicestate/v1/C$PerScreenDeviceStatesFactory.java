package com.google.android.appfunctions.schema.devicestate.v1;

import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1196n;
import ne.C1202t;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/google/android/appfunctions/schema/devicestate/v1/$PerScreenDeviceStatesFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Lcom/google/android/appfunctions/schema/devicestate/v1/PerScreenDeviceStates;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.devicestate.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* renamed from: com.google.android.appfunctions.schema.devicestate.v1.$PerScreenDeviceStatesFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$PerScreenDeviceStatesFactory implements AppFunctionSerializableFactory<PerScreenDeviceStates> {
    /* JADX WARNING: type inference failed for: r5v1, types: [com.google.android.appfunctions.schema.devicestate.v1.$DeviceStateItemFactory, java.lang.Object] */
    public final PerScreenDeviceStates fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData, "com.google.android.appfunctions.schema.devicestate.v1.PerScreenDeviceStates");
        ? obj = new Object();
        String stringOrNull = appFunctionDataWithSpec.getStringOrNull("description");
        if (stringOrNull != null) {
            String stringOrNull2 = appFunctionDataWithSpec.getStringOrNull("intentUri");
            Iterable appFunctionDataList = appFunctionDataWithSpec.getAppFunctionDataList("deviceStateItems");
            if (appFunctionDataList == null) {
                appFunctionDataList = C1202t.d;
            }
            Iterable<AppFunctionData> iterable = appFunctionDataList;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (AppFunctionData fromAppFunctionData : iterable) {
                arrayList.add(obj.fromAppFunctionData(fromAppFunctionData));
            }
            return new PerScreenDeviceStates(stringOrNull, stringOrNull2, arrayList);
        }
        throw new IllegalStateException("Required value was null.");
    }

    public final /* bridge */ /* synthetic */ AppFunctionData.Builder getAppFunctionDataBuilder(String str) {
        return super.getAppFunctionDataBuilder(str);
    }

    public final /* bridge */ /* synthetic */ AppFunctionData getAppFunctionDataWithSpec(AppFunctionData appFunctionData, String str) {
        return super.getAppFunctionDataWithSpec(appFunctionData, str);
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [com.google.android.appfunctions.schema.devicestate.v1.$DeviceStateItemFactory, java.lang.Object] */
    public final AppFunctionData toAppFunctionData(PerScreenDeviceStates perScreenDeviceStates) {
        j.e(perScreenDeviceStates, "appFunctionSerializable");
        ? obj = new Object();
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("com.google.android.appfunctions.schema.devicestate.v1.PerScreenDeviceStates");
        appFunctionDataBuilder.setString("description", perScreenDeviceStates.f1182a);
        String str = perScreenDeviceStates.b;
        if (str != null) {
            appFunctionDataBuilder.setString("intentUri", str);
        }
        ArrayList<DeviceStateItem> arrayList = perScreenDeviceStates.f1183c;
        ArrayList arrayList2 = new ArrayList(C1196n.w0(arrayList, 10));
        for (DeviceStateItem appFunctionData : arrayList) {
            arrayList2.add(obj.toAppFunctionData(appFunctionData));
        }
        appFunctionDataBuilder.setAppFunctionDataList("deviceStateItems", arrayList2);
        return appFunctionDataBuilder.build();
    }
}
