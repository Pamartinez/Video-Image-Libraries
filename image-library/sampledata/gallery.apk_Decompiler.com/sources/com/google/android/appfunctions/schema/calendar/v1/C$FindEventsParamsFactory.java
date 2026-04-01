package com.google.android.appfunctions.schema.calendar.v1;

import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import com.google.android.appfunctions.schema.types.v1.DateTime;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1202t;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/google/android/appfunctions/schema/calendar/v1/$FindEventsParamsFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Lcom/google/android/appfunctions/schema/calendar/v1/FindEventsParams;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.calendar.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* renamed from: com.google.android.appfunctions.schema.calendar.v1.$FindEventsParamsFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$FindEventsParamsFactory implements AppFunctionSerializableFactory<FindEventsParams> {
    /* JADX WARNING: type inference failed for: r10v1, types: [com.google.android.appfunctions.schema.types.v1.$DateTimeFactory, java.lang.Object] */
    public final FindEventsParams fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData, "com.google.android.appfunctions.schema.calendar.v1.FindEventsParams");
        ? obj = new Object();
        AppFunctionData appFunctionData2 = appFunctionDataWithSpec.getAppFunctionData("startDate");
        DateTime dateTime = null;
        DateTime fromAppFunctionData = appFunctionData2 != null ? obj.fromAppFunctionData(appFunctionData2) : null;
        AppFunctionData appFunctionData3 = appFunctionDataWithSpec.getAppFunctionData("endDate");
        if (appFunctionData3 != null) {
            dateTime = obj.fromAppFunctionData(appFunctionData3);
        }
        DateTime dateTime2 = dateTime;
        String stringOrNull = appFunctionDataWithSpec.getStringOrNull(Contract.QUERY);
        List stringList = appFunctionDataWithSpec.getStringList("attendeeIds");
        if (stringList == null) {
            stringList = C1202t.d;
        }
        List list = stringList;
        Integer intOrNull = appFunctionDataWithSpec.getIntOrNull("maxCount");
        if (intOrNull != null) {
            return new FindEventsParams(fromAppFunctionData, dateTime2, stringOrNull, list, intOrNull.intValue(), appFunctionDataWithSpec.getStringOrNull("orderBy"));
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
    public final AppFunctionData toAppFunctionData(FindEventsParams findEventsParams) {
        j.e(findEventsParams, "appFunctionSerializable");
        ? obj = new Object();
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("com.google.android.appfunctions.schema.calendar.v1.FindEventsParams");
        DateTime dateTime = findEventsParams.f1131a;
        if (dateTime != null) {
            appFunctionDataBuilder.setAppFunctionData("startDate", obj.toAppFunctionData(dateTime));
        }
        DateTime dateTime2 = findEventsParams.b;
        if (dateTime2 != null) {
            appFunctionDataBuilder.setAppFunctionData("endDate", obj.toAppFunctionData(dateTime2));
        }
        String str = findEventsParams.f1132c;
        if (str != null) {
            appFunctionDataBuilder.setString(Contract.QUERY, str);
        }
        appFunctionDataBuilder.setStringList("attendeeIds", findEventsParams.d);
        appFunctionDataBuilder.setInt("maxCount", findEventsParams.e);
        String str2 = findEventsParams.f;
        if (str2 != null) {
            appFunctionDataBuilder.setString("orderBy", str2);
        }
        return appFunctionDataBuilder.build();
    }
}
