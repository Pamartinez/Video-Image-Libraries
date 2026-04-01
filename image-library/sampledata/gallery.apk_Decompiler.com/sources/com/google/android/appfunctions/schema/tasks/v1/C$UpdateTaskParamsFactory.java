package com.google.android.appfunctions.schema.tasks.v1;

import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import com.google.android.appfunctions.schema.types.v1.C$SetFieldFactory;
import com.google.android.appfunctions.schema.types.v1.DateTime;
import com.google.android.appfunctions.schema.types.v1.SetField;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.CalendarEventBundleWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/google/android/appfunctions/schema/tasks/v1/$UpdateTaskParamsFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Lcom/google/android/appfunctions/schema/tasks/v1/UpdateTaskParams;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.tasks.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* renamed from: com.google.android.appfunctions.schema.tasks.v1.$UpdateTaskParamsFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$UpdateTaskParamsFactory implements AppFunctionSerializableFactory<UpdateTaskParams> {
    /* JADX WARNING: type inference failed for: r4v1, types: [androidx.appfunctions.internal.AppFunctionSerializableFactory, java.lang.Object] */
    public final UpdateTaskParams fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData, "com.google.android.appfunctions.schema.tasks.v1.UpdateTaskParams");
        Class<String> cls = String.class;
        C$SetFieldFactory _setfieldfactory = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.PrimitiveTypeParameter(cls));
        C$SetFieldFactory _setfieldfactory2 = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.PrimitiveTypeParameter(cls));
        Class cls2 = Boolean.TYPE;
        j.c(cls2, "null cannot be cast to non-null type java.lang.Class<kotlin.Boolean?>");
        C$SetFieldFactory _setfieldfactory3 = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.PrimitiveTypeParameter(cls2));
        C$SetFieldFactory _setfieldfactory4 = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.SerializableTypeParameter(DateTime.class, new Object()));
        String stringOrNull = appFunctionDataWithSpec.getStringOrNull("taskId");
        if (stringOrNull != null) {
            AppFunctionData appFunctionData2 = appFunctionDataWithSpec.getAppFunctionData("title");
            SetField setField = null;
            SetField fromAppFunctionData = appFunctionData2 != null ? _setfieldfactory.fromAppFunctionData(appFunctionData2) : null;
            AppFunctionData appFunctionData3 = appFunctionDataWithSpec.getAppFunctionData("description");
            SetField fromAppFunctionData2 = appFunctionData3 != null ? _setfieldfactory2.fromAppFunctionData(appFunctionData3) : null;
            AppFunctionData appFunctionData4 = appFunctionDataWithSpec.getAppFunctionData("completed");
            SetField fromAppFunctionData3 = appFunctionData4 != null ? _setfieldfactory3.fromAppFunctionData(appFunctionData4) : null;
            AppFunctionData appFunctionData5 = appFunctionDataWithSpec.getAppFunctionData("dateTime");
            SetField fromAppFunctionData4 = appFunctionData5 != null ? _setfieldfactory4.fromAppFunctionData(appFunctionData5) : null;
            AppFunctionData appFunctionData6 = appFunctionDataWithSpec.getAppFunctionData(CalendarEventBundleWrapper.BUNDLE_KEY_ALL_DAY);
            SetField fromAppFunctionData5 = appFunctionData6 != null ? _setfieldfactory3.fromAppFunctionData(appFunctionData6) : null;
            AppFunctionData appFunctionData7 = appFunctionDataWithSpec.getAppFunctionData("recurrenceSchedule");
            if (appFunctionData7 != null) {
                setField = _setfieldfactory2.fromAppFunctionData(appFunctionData7);
            }
            return new UpdateTaskParams(stringOrNull, fromAppFunctionData, fromAppFunctionData2, fromAppFunctionData3, fromAppFunctionData4, fromAppFunctionData5, setField);
        }
        throw new IllegalStateException("Required value was null.");
    }

    public final /* bridge */ /* synthetic */ AppFunctionData.Builder getAppFunctionDataBuilder(String str) {
        return super.getAppFunctionDataBuilder(str);
    }

    public final /* bridge */ /* synthetic */ AppFunctionData getAppFunctionDataWithSpec(AppFunctionData appFunctionData, String str) {
        return super.getAppFunctionDataWithSpec(appFunctionData, str);
    }

    /* JADX WARNING: type inference failed for: r5v1, types: [androidx.appfunctions.internal.AppFunctionSerializableFactory, java.lang.Object] */
    public final AppFunctionData toAppFunctionData(UpdateTaskParams updateTaskParams) {
        j.e(updateTaskParams, "appFunctionSerializable");
        Class<String> cls = String.class;
        C$SetFieldFactory _setfieldfactory = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.PrimitiveTypeParameter(cls));
        C$SetFieldFactory _setfieldfactory2 = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.PrimitiveTypeParameter(cls));
        Class cls2 = Boolean.TYPE;
        j.c(cls2, "null cannot be cast to non-null type java.lang.Class<kotlin.Boolean?>");
        C$SetFieldFactory _setfieldfactory3 = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.PrimitiveTypeParameter(cls2));
        C$SetFieldFactory _setfieldfactory4 = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.SerializableTypeParameter(DateTime.class, new Object()));
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("com.google.android.appfunctions.schema.tasks.v1.UpdateTaskParams");
        appFunctionDataBuilder.setString("taskId", updateTaskParams.f1321a);
        SetField setField = updateTaskParams.b;
        if (setField != null) {
            appFunctionDataBuilder.setAppFunctionData("title", _setfieldfactory.toAppFunctionData(setField));
        }
        SetField setField2 = updateTaskParams.f1322c;
        if (setField2 != null) {
            appFunctionDataBuilder.setAppFunctionData("description", _setfieldfactory2.toAppFunctionData(setField2));
        }
        SetField setField3 = updateTaskParams.d;
        if (setField3 != null) {
            appFunctionDataBuilder.setAppFunctionData("completed", _setfieldfactory3.toAppFunctionData(setField3));
        }
        SetField setField4 = updateTaskParams.e;
        if (setField4 != null) {
            appFunctionDataBuilder.setAppFunctionData("dateTime", _setfieldfactory4.toAppFunctionData(setField4));
        }
        SetField setField5 = updateTaskParams.f;
        if (setField5 != null) {
            appFunctionDataBuilder.setAppFunctionData(CalendarEventBundleWrapper.BUNDLE_KEY_ALL_DAY, _setfieldfactory3.toAppFunctionData(setField5));
        }
        SetField setField6 = updateTaskParams.g;
        if (setField6 != null) {
            appFunctionDataBuilder.setAppFunctionData("recurrenceSchedule", _setfieldfactory2.toAppFunctionData(setField6));
        }
        return appFunctionDataBuilder.build();
    }
}
