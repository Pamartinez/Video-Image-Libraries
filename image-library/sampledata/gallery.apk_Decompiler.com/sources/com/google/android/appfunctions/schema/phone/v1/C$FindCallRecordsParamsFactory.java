package com.google.android.appfunctions.schema.phone.v1;

import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import com.google.android.appfunctions.schema.types.v1.DateTime;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PersonBundleWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/google/android/appfunctions/schema/phone/v1/$FindCallRecordsParamsFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Lcom/google/android/appfunctions/schema/phone/v1/FindCallRecordsParams;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.phone.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* renamed from: com.google.android.appfunctions.schema.phone.v1.$FindCallRecordsParamsFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$FindCallRecordsParamsFactory implements AppFunctionSerializableFactory<FindCallRecordsParams> {
    /* JADX WARNING: type inference failed for: r9v1, types: [com.google.android.appfunctions.schema.types.v1.$DateTimeFactory, java.lang.Object] */
    public final FindCallRecordsParams fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData, "com.google.android.appfunctions.schema.phone.v1.FindCallRecordsParams");
        ? obj = new Object();
        String stringOrNull = appFunctionDataWithSpec.getStringOrNull(Contract.QUERY);
        String stringOrNull2 = appFunctionDataWithSpec.getStringOrNull(PersonBundleWrapper.BUNDLE_KEY_CONTACT_ID);
        AppFunctionData appFunctionData2 = appFunctionDataWithSpec.getAppFunctionData("startTime");
        DateTime dateTime = null;
        DateTime fromAppFunctionData = appFunctionData2 != null ? obj.fromAppFunctionData(appFunctionData2) : null;
        AppFunctionData appFunctionData3 = appFunctionDataWithSpec.getAppFunctionData("endTime");
        if (appFunctionData3 != null) {
            dateTime = obj.fromAppFunctionData(appFunctionData3);
        }
        DateTime dateTime2 = dateTime;
        String stringOrNull3 = appFunctionDataWithSpec.getStringOrNull("callType");
        Integer intOrNull = appFunctionDataWithSpec.getIntOrNull("maxCount");
        if (intOrNull != null) {
            return new FindCallRecordsParams(stringOrNull, stringOrNull2, fromAppFunctionData, dateTime2, stringOrNull3, intOrNull.intValue());
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
    public final AppFunctionData toAppFunctionData(FindCallRecordsParams findCallRecordsParams) {
        j.e(findCallRecordsParams, "appFunctionSerializable");
        ? obj = new Object();
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("com.google.android.appfunctions.schema.phone.v1.FindCallRecordsParams");
        String str = findCallRecordsParams.f1288a;
        if (str != null) {
            appFunctionDataBuilder.setString(Contract.QUERY, str);
        }
        String str2 = findCallRecordsParams.b;
        if (str2 != null) {
            appFunctionDataBuilder.setString(PersonBundleWrapper.BUNDLE_KEY_CONTACT_ID, str2);
        }
        DateTime dateTime = findCallRecordsParams.f1289c;
        if (dateTime != null) {
            appFunctionDataBuilder.setAppFunctionData("startTime", obj.toAppFunctionData(dateTime));
        }
        DateTime dateTime2 = findCallRecordsParams.d;
        if (dateTime2 != null) {
            appFunctionDataBuilder.setAppFunctionData("endTime", obj.toAppFunctionData(dateTime2));
        }
        String str3 = findCallRecordsParams.e;
        if (str3 != null) {
            appFunctionDataBuilder.setString("callType", str3);
        }
        appFunctionDataBuilder.setInt("maxCount", findCallRecordsParams.f);
        return appFunctionDataBuilder.build();
    }
}
