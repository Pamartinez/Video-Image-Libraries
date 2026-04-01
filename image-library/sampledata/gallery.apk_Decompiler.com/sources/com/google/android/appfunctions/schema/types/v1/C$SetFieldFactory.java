package com.google.android.appfunctions.schema.types.v1;

import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\t\u001a\u00020\nH\u0016J\u0016\u0010\u000b\u001a\u00020\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"com/google/android/appfunctions/schema/types/v1/$SetFieldFactory", "T", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Lcom/google/android/appfunctions/schema/types/v1/SetField;", "tTypeParameter", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory$TypeParameter;", "<init>", "(Landroidx/appfunctions/internal/AppFunctionSerializableFactory$TypeParameter;)V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.types.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* renamed from: com.google.android.appfunctions.schema.types.v1.$SetFieldFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$SetFieldFactory<T> implements AppFunctionSerializableFactory<SetField<? extends T>> {

    /* renamed from: a  reason: collision with root package name */
    public final AppFunctionSerializableFactory.TypeParameter f1323a;

    public C$SetFieldFactory(AppFunctionSerializableFactory.TypeParameter<T> typeParameter) {
        j.e(typeParameter, "tTypeParameter");
        this.f1323a = typeParameter;
    }

    public final SetField<T> fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        return new SetField<>(this.f1323a.getFromAppFunctionData(super.getAppFunctionDataWithSpec(appFunctionData, "com.google.android.appfunctions.schema.types.v1.SetField"), "value"));
    }

    public final /* bridge */ /* synthetic */ AppFunctionData.Builder getAppFunctionDataBuilder(String str) {
        return super.getAppFunctionDataBuilder(str);
    }

    public final /* bridge */ /* synthetic */ AppFunctionData getAppFunctionDataWithSpec(AppFunctionData appFunctionData, String str) {
        return super.getAppFunctionDataWithSpec(appFunctionData, str);
    }

    public final AppFunctionData toAppFunctionData(SetField<? extends T> setField) {
        j.e(setField, "appFunctionSerializable");
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("com.google.android.appfunctions.schema.types.v1.SetField");
        this.f1323a.setValueInAppFunctionData(appFunctionDataBuilder, "value", setField.f1328a);
        return appFunctionDataBuilder.build();
    }
}
