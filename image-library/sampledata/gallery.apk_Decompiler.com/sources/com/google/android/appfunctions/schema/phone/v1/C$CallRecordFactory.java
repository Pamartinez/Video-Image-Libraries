package com.google.android.appfunctions.schema.phone.v1;

import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PersonBundleWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/google/android/appfunctions/schema/phone/v1/$CallRecordFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Lcom/google/android/appfunctions/schema/phone/v1/CallRecord;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.phone.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* renamed from: com.google.android.appfunctions.schema.phone.v1.$CallRecordFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$CallRecordFactory implements AppFunctionSerializableFactory<CallRecord> {
    /* JADX WARNING: type inference failed for: r11v1, types: [com.google.android.appfunctions.schema.types.v1.$DateTimeFactory, java.lang.Object] */
    public final CallRecord fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData, "com.google.android.appfunctions.schema.phone.v1.CallRecord");
        ? obj = new Object();
        String stringOrNull = appFunctionDataWithSpec.getStringOrNull("id");
        if (stringOrNull != null) {
            String stringOrNull2 = appFunctionDataWithSpec.getStringOrNull(PersonBundleWrapper.BUNDLE_KEY_CONTACT_ID);
            if (stringOrNull2 != null) {
                String stringOrNull3 = appFunctionDataWithSpec.getStringOrNull("callType");
                if (stringOrNull3 != null) {
                    Boolean booleanOrNull = appFunctionDataWithSpec.getBooleanOrNull("isVideoCall");
                    AppFunctionData appFunctionData2 = appFunctionDataWithSpec.getAppFunctionData("startTime");
                    if (appFunctionData2 != null) {
                        return new CallRecord(stringOrNull, stringOrNull2, stringOrNull3, booleanOrNull, obj.fromAppFunctionData(appFunctionData2), appFunctionDataWithSpec.getLongOrNull("durationMillis"), appFunctionDataWithSpec.getStringOrNull("sipAddress"), appFunctionDataWithSpec.getStringOrNull("phoneNumber"));
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

    /* JADX WARNING: type inference failed for: r0v1, types: [com.google.android.appfunctions.schema.types.v1.$DateTimeFactory, java.lang.Object] */
    public final AppFunctionData toAppFunctionData(CallRecord callRecord) {
        j.e(callRecord, "appFunctionSerializable");
        ? obj = new Object();
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("com.google.android.appfunctions.schema.phone.v1.CallRecord");
        appFunctionDataBuilder.setString("id", callRecord.f1285a);
        appFunctionDataBuilder.setString(PersonBundleWrapper.BUNDLE_KEY_CONTACT_ID, callRecord.b);
        appFunctionDataBuilder.setString("callType", callRecord.f1286c);
        Boolean bool = callRecord.d;
        if (bool != null) {
            appFunctionDataBuilder.setBoolean("isVideoCall", bool.booleanValue());
        }
        appFunctionDataBuilder.setAppFunctionData("startTime", obj.toAppFunctionData(callRecord.e));
        Long l = callRecord.f;
        if (l != null) {
            appFunctionDataBuilder.setLong("durationMillis", l.longValue());
        }
        String str = callRecord.g;
        if (str != null) {
            appFunctionDataBuilder.setString("sipAddress", str);
        }
        String str2 = callRecord.f1287h;
        if (str2 != null) {
            appFunctionDataBuilder.setString("phoneNumber", str2);
        }
        return appFunctionDataBuilder.build();
    }
}
