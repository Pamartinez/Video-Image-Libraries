package com.google.android.appfunctions.schema.calendar.v1;

import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import com.google.android.appfunctions.schema.types.v1.C$SetFieldFactory;
import com.google.android.appfunctions.schema.types.v1.DateTime;
import com.google.android.appfunctions.schema.types.v1.SetField;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.CalendarEventBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.FourWEventBundleWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/google/android/appfunctions/schema/calendar/v1/$UpdateEventParamsFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Lcom/google/android/appfunctions/schema/calendar/v1/UpdateEventParams;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.calendar.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* renamed from: com.google.android.appfunctions.schema.calendar.v1.$UpdateEventParamsFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$UpdateEventParamsFactory implements AppFunctionSerializableFactory<UpdateEventParams> {
    /* JADX WARNING: type inference failed for: r6v0, types: [androidx.appfunctions.internal.AppFunctionSerializableFactory, java.lang.Object] */
    public final UpdateEventParams fromAppFunctionData(AppFunctionData appFunctionData) {
        AppFunctionData appFunctionData2 = appFunctionData;
        j.e(appFunctionData2, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData2, "com.google.android.appfunctions.schema.calendar.v1.UpdateEventParams");
        Class<String> cls = String.class;
        C$SetFieldFactory _setfieldfactory = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.PrimitiveTypeParameter(cls));
        C$SetFieldFactory _setfieldfactory2 = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.PrimitiveTypeParameter(cls));
        C$SetFieldFactory _setfieldfactory3 = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.SerializableTypeParameter(DateTime.class, new Object()));
        C$SetFieldFactory _setfieldfactory4 = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.PrimitiveListTypeParameter(cls));
        C$SetFieldFactory _setfieldfactory5 = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.PrimitiveTypeParameter(Boolean.TYPE));
        String stringOrNull = appFunctionDataWithSpec.getStringOrNull(FourWEventBundleWrapper.BUNDLE_KEY_EVENT_ID);
        if (stringOrNull != null) {
            AppFunctionData appFunctionData3 = appFunctionDataWithSpec.getAppFunctionData("title");
            SetField setField = null;
            SetField fromAppFunctionData = appFunctionData3 != null ? _setfieldfactory.fromAppFunctionData(appFunctionData3) : null;
            AppFunctionData appFunctionData4 = appFunctionDataWithSpec.getAppFunctionData("description");
            SetField fromAppFunctionData2 = appFunctionData4 != null ? _setfieldfactory2.fromAppFunctionData(appFunctionData4) : null;
            AppFunctionData appFunctionData5 = appFunctionDataWithSpec.getAppFunctionData("startDate");
            SetField fromAppFunctionData3 = appFunctionData5 != null ? _setfieldfactory3.fromAppFunctionData(appFunctionData5) : null;
            AppFunctionData appFunctionData6 = appFunctionDataWithSpec.getAppFunctionData("endDate");
            SetField fromAppFunctionData4 = appFunctionData6 != null ? _setfieldfactory3.fromAppFunctionData(appFunctionData6) : null;
            AppFunctionData appFunctionData7 = appFunctionDataWithSpec.getAppFunctionData("attendeeIds");
            SetField fromAppFunctionData5 = appFunctionData7 != null ? _setfieldfactory4.fromAppFunctionData(appFunctionData7) : null;
            AppFunctionData appFunctionData8 = appFunctionDataWithSpec.getAppFunctionData(CalendarEventBundleWrapper.BUNDLE_KEY_ALL_DAY);
            SetField fromAppFunctionData6 = appFunctionData8 != null ? _setfieldfactory5.fromAppFunctionData(appFunctionData8) : null;
            AppFunctionData appFunctionData9 = appFunctionDataWithSpec.getAppFunctionData("location");
            SetField fromAppFunctionData7 = appFunctionData9 != null ? _setfieldfactory2.fromAppFunctionData(appFunctionData9) : null;
            AppFunctionData appFunctionData10 = appFunctionDataWithSpec.getAppFunctionData("recurrenceSchedule");
            SetField fromAppFunctionData8 = appFunctionData10 != null ? _setfieldfactory2.fromAppFunctionData(appFunctionData10) : null;
            AppFunctionData appFunctionData11 = appFunctionDataWithSpec.getAppFunctionData("eventStatus");
            if (appFunctionData11 != null) {
                setField = _setfieldfactory.fromAppFunctionData(appFunctionData11);
            }
            return new UpdateEventParams(stringOrNull, fromAppFunctionData, fromAppFunctionData2, fromAppFunctionData3, fromAppFunctionData4, fromAppFunctionData5, fromAppFunctionData6, fromAppFunctionData7, fromAppFunctionData8, setField);
        }
        throw new IllegalStateException("Required value was null.");
    }

    public final /* bridge */ /* synthetic */ AppFunctionData.Builder getAppFunctionDataBuilder(String str) {
        return super.getAppFunctionDataBuilder(str);
    }

    public final /* bridge */ /* synthetic */ AppFunctionData getAppFunctionDataWithSpec(AppFunctionData appFunctionData, String str) {
        return super.getAppFunctionDataWithSpec(appFunctionData, str);
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [androidx.appfunctions.internal.AppFunctionSerializableFactory, java.lang.Object] */
    public final AppFunctionData toAppFunctionData(UpdateEventParams updateEventParams) {
        j.e(updateEventParams, "appFunctionSerializable");
        Class<String> cls = String.class;
        C$SetFieldFactory _setfieldfactory = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.PrimitiveTypeParameter(cls));
        C$SetFieldFactory _setfieldfactory2 = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.PrimitiveTypeParameter(cls));
        C$SetFieldFactory _setfieldfactory3 = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.SerializableTypeParameter(DateTime.class, new Object()));
        C$SetFieldFactory _setfieldfactory4 = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.PrimitiveListTypeParameter(cls));
        C$SetFieldFactory _setfieldfactory5 = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.PrimitiveTypeParameter(Boolean.TYPE));
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("com.google.android.appfunctions.schema.calendar.v1.UpdateEventParams");
        appFunctionDataBuilder.setString(FourWEventBundleWrapper.BUNDLE_KEY_EVENT_ID, updateEventParams.f1133a);
        SetField setField = updateEventParams.b;
        if (setField != null) {
            appFunctionDataBuilder.setAppFunctionData("title", _setfieldfactory.toAppFunctionData(setField));
        }
        SetField setField2 = updateEventParams.f1134c;
        if (setField2 != null) {
            appFunctionDataBuilder.setAppFunctionData("description", _setfieldfactory2.toAppFunctionData(setField2));
        }
        SetField setField3 = updateEventParams.d;
        if (setField3 != null) {
            appFunctionDataBuilder.setAppFunctionData("startDate", _setfieldfactory3.toAppFunctionData(setField3));
        }
        SetField setField4 = updateEventParams.e;
        if (setField4 != null) {
            appFunctionDataBuilder.setAppFunctionData("endDate", _setfieldfactory3.toAppFunctionData(setField4));
        }
        SetField setField5 = updateEventParams.f;
        if (setField5 != null) {
            appFunctionDataBuilder.setAppFunctionData("attendeeIds", _setfieldfactory4.toAppFunctionData(setField5));
        }
        SetField setField6 = updateEventParams.g;
        if (setField6 != null) {
            appFunctionDataBuilder.setAppFunctionData(CalendarEventBundleWrapper.BUNDLE_KEY_ALL_DAY, _setfieldfactory5.toAppFunctionData(setField6));
        }
        SetField setField7 = updateEventParams.f1135h;
        if (setField7 != null) {
            appFunctionDataBuilder.setAppFunctionData("location", _setfieldfactory2.toAppFunctionData(setField7));
        }
        SetField setField8 = updateEventParams.f1136i;
        if (setField8 != null) {
            appFunctionDataBuilder.setAppFunctionData("recurrenceSchedule", _setfieldfactory2.toAppFunctionData(setField8));
        }
        SetField setField9 = updateEventParams.f1137j;
        if (setField9 != null) {
            appFunctionDataBuilder.setAppFunctionData("eventStatus", _setfieldfactory.toAppFunctionData(setField9));
        }
        return appFunctionDataBuilder.build();
    }
}
