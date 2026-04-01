package com.google.android.appfunctions.schema.phone.v1;

import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PersonBundleWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/google/android/appfunctions/schema/phone/v1/$AddToBlocklistParamsFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Lcom/google/android/appfunctions/schema/phone/v1/AddToBlocklistParams;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.phone.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* renamed from: com.google.android.appfunctions.schema.phone.v1.$AddToBlocklistParamsFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$AddToBlocklistParamsFactory implements AppFunctionSerializableFactory<AddToBlocklistParams> {
    public final AddToBlocklistParams fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData, "com.google.android.appfunctions.schema.phone.v1.AddToBlocklistParams");
        String stringOrNull = appFunctionDataWithSpec.getStringOrNull("type");
        if (stringOrNull != null) {
            return new AddToBlocklistParams(stringOrNull, appFunctionDataWithSpec.getStringOrNull("phoneNumber"), appFunctionDataWithSpec.getStringOrNull(PersonBundleWrapper.BUNDLE_KEY_CONTACT_ID));
        }
        throw new IllegalStateException("Required value was null.");
    }

    public final /* bridge */ /* synthetic */ AppFunctionData.Builder getAppFunctionDataBuilder(String str) {
        return super.getAppFunctionDataBuilder(str);
    }

    public final /* bridge */ /* synthetic */ AppFunctionData getAppFunctionDataWithSpec(AppFunctionData appFunctionData, String str) {
        return super.getAppFunctionDataWithSpec(appFunctionData, str);
    }

    public final AppFunctionData toAppFunctionData(AddToBlocklistParams addToBlocklistParams) {
        j.e(addToBlocklistParams, "appFunctionSerializable");
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("com.google.android.appfunctions.schema.phone.v1.AddToBlocklistParams");
        appFunctionDataBuilder.setString("type", addToBlocklistParams.f1282a);
        String str = addToBlocklistParams.b;
        if (str != null) {
            appFunctionDataBuilder.setString("phoneNumber", str);
        }
        String str2 = addToBlocklistParams.f1283c;
        if (str2 != null) {
            appFunctionDataBuilder.setString(PersonBundleWrapper.BUNDLE_KEY_CONTACT_ID, str2);
        }
        return appFunctionDataBuilder.build();
    }
}
