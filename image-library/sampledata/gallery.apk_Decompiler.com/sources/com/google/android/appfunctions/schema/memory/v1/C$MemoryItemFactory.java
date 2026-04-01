package com.google.android.appfunctions.schema.memory.v1;

import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import com.google.android.appfunctions.schema.types.v1.DateTime;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1202t;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/google/android/appfunctions/schema/memory/v1/$MemoryItemFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Lcom/google/android/appfunctions/schema/memory/v1/MemoryItem;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.memory.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* renamed from: com.google.android.appfunctions.schema.memory.v1.$MemoryItemFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$MemoryItemFactory implements AppFunctionSerializableFactory<MemoryItem> {
    /* JADX WARNING: type inference failed for: r11v1, types: [com.google.android.appfunctions.schema.types.v1.$DateTimeFactory, java.lang.Object] */
    public final MemoryItem fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData, "com.google.android.appfunctions.schema.memory.v1.MemoryItem");
        ? obj = new Object();
        String stringOrNull = appFunctionDataWithSpec.getStringOrNull("id");
        if (stringOrNull != null) {
            String stringOrNull2 = appFunctionDataWithSpec.getStringOrNull("title");
            String stringOrNull3 = appFunctionDataWithSpec.getStringOrNull("summary");
            String stringOrNull4 = appFunctionDataWithSpec.getStringOrNull("textContent");
            if (stringOrNull4 != null) {
                List stringList = appFunctionDataWithSpec.getStringList("keywords");
                if (stringList == null) {
                    stringList = C1202t.d;
                }
                List list = stringList;
                AppFunctionData appFunctionData2 = appFunctionDataWithSpec.getAppFunctionData("createdAt");
                if (appFunctionData2 != null) {
                    DateTime fromAppFunctionData = obj.fromAppFunctionData(appFunctionData2);
                    AppFunctionData appFunctionData3 = appFunctionDataWithSpec.getAppFunctionData("lastUpdatedAt");
                    if (appFunctionData3 != null) {
                        return new MemoryItem(stringOrNull, stringOrNull2, stringOrNull3, stringOrNull4, list, fromAppFunctionData, obj.fromAppFunctionData(appFunctionData3), appFunctionDataWithSpec.getStringOrNull("additionalMetadataJson"));
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
    public final AppFunctionData toAppFunctionData(MemoryItem memoryItem) {
        j.e(memoryItem, "appFunctionSerializable");
        ? obj = new Object();
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("com.google.android.appfunctions.schema.memory.v1.MemoryItem");
        appFunctionDataBuilder.setString("id", memoryItem.f1232a);
        String str = memoryItem.b;
        if (str != null) {
            appFunctionDataBuilder.setString("title", str);
        }
        String str2 = memoryItem.f1233c;
        if (str2 != null) {
            appFunctionDataBuilder.setString("summary", str2);
        }
        appFunctionDataBuilder.setString("textContent", memoryItem.d);
        appFunctionDataBuilder.setStringList("keywords", memoryItem.e);
        appFunctionDataBuilder.setAppFunctionData("createdAt", obj.toAppFunctionData(memoryItem.f));
        appFunctionDataBuilder.setAppFunctionData("lastUpdatedAt", obj.toAppFunctionData(memoryItem.g));
        String str3 = memoryItem.f1234h;
        if (str3 != null) {
            appFunctionDataBuilder.setString("additionalMetadataJson", str3);
        }
        return appFunctionDataBuilder.build();
    }
}
