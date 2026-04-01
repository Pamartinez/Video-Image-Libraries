package com.google.android.appfunctions.schema.browser.v1;

import android.net.Uri;
import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import androidx.appfunctions.internal.serializableproxies.C$UriFactory;
import com.google.android.appfunctions.schema.types.v1.C$SetFieldFactory;
import com.google.android.appfunctions.schema.types.v1.SetField;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/google/android/appfunctions/schema/browser/v1/$UpdateBookmarkParamsFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Lcom/google/android/appfunctions/schema/browser/v1/UpdateBookmarkParams;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.browser.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* renamed from: com.google.android.appfunctions.schema.browser.v1.$UpdateBookmarkParamsFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$UpdateBookmarkParamsFactory implements AppFunctionSerializableFactory<UpdateBookmarkParams> {
    public final UpdateBookmarkParams fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData, "com.google.android.appfunctions.schema.browser.v1.UpdateBookmarkParams");
        C$SetFieldFactory _setfieldfactory = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.PrimitiveTypeParameter(String.class));
        C$SetFieldFactory _setfieldfactory2 = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.SerializableTypeParameter(Uri.class, new C$UriFactory()));
        String stringOrNull = appFunctionDataWithSpec.getStringOrNull("bookmarkId");
        if (stringOrNull != null) {
            AppFunctionData appFunctionData2 = appFunctionDataWithSpec.getAppFunctionData("name");
            SetField setField = null;
            SetField fromAppFunctionData = appFunctionData2 != null ? _setfieldfactory.fromAppFunctionData(appFunctionData2) : null;
            AppFunctionData appFunctionData3 = appFunctionDataWithSpec.getAppFunctionData("url");
            if (appFunctionData3 != null) {
                setField = _setfieldfactory2.fromAppFunctionData(appFunctionData3);
            }
            return new UpdateBookmarkParams(stringOrNull, fromAppFunctionData, setField);
        }
        throw new IllegalStateException("Required value was null.");
    }

    public final /* bridge */ /* synthetic */ AppFunctionData.Builder getAppFunctionDataBuilder(String str) {
        return super.getAppFunctionDataBuilder(str);
    }

    public final /* bridge */ /* synthetic */ AppFunctionData getAppFunctionDataWithSpec(AppFunctionData appFunctionData, String str) {
        return super.getAppFunctionDataWithSpec(appFunctionData, str);
    }

    public final AppFunctionData toAppFunctionData(UpdateBookmarkParams updateBookmarkParams) {
        j.e(updateBookmarkParams, "appFunctionSerializable");
        C$SetFieldFactory _setfieldfactory = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.PrimitiveTypeParameter(String.class));
        C$SetFieldFactory _setfieldfactory2 = new C$SetFieldFactory(new AppFunctionSerializableFactory.TypeParameter.SerializableTypeParameter(Uri.class, new C$UriFactory()));
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("com.google.android.appfunctions.schema.browser.v1.UpdateBookmarkParams");
        appFunctionDataBuilder.setString("bookmarkId", updateBookmarkParams.f1119a);
        SetField setField = updateBookmarkParams.b;
        if (setField != null) {
            appFunctionDataBuilder.setAppFunctionData("name", _setfieldfactory.toAppFunctionData(setField));
        }
        SetField setField2 = updateBookmarkParams.f1120c;
        if (setField2 != null) {
            appFunctionDataBuilder.setAppFunctionData("url", _setfieldfactory2.toAppFunctionData(setField2));
        }
        return appFunctionDataBuilder.build();
    }
}
