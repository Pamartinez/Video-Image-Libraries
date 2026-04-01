package com.google.android.appfunctions.schema.tasks.v1;

import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import com.google.android.appfunctions.schema.types.v1.DateTime;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.CalendarEventBundleWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/google/android/appfunctions/schema/tasks/v1/$TaskFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Lcom/google/android/appfunctions/schema/tasks/v1/Task;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.tasks.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* renamed from: com.google.android.appfunctions.schema.tasks.v1.$TaskFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$TaskFactory implements AppFunctionSerializableFactory<Task> {
    /* JADX WARNING: type inference failed for: r11v1, types: [com.google.android.appfunctions.schema.types.v1.$DateTimeFactory, java.lang.Object] */
    public final Task fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData, "com.google.android.appfunctions.schema.tasks.v1.Task");
        ? obj = new Object();
        String stringOrNull = appFunctionDataWithSpec.getStringOrNull("id");
        if (stringOrNull != null) {
            String stringOrNull2 = appFunctionDataWithSpec.getStringOrNull("title");
            if (stringOrNull2 != null) {
                String stringOrNull3 = appFunctionDataWithSpec.getStringOrNull("description");
                Boolean booleanOrNull = appFunctionDataWithSpec.getBooleanOrNull("completed");
                String stringOrNull4 = appFunctionDataWithSpec.getStringOrNull("taskCategoryId");
                AppFunctionData appFunctionData2 = appFunctionDataWithSpec.getAppFunctionData("dateTime");
                return new Task(appFunctionData2 != null ? obj.fromAppFunctionData(appFunctionData2) : null, booleanOrNull, appFunctionDataWithSpec.getBooleanOrNull(CalendarEventBundleWrapper.BUNDLE_KEY_ALL_DAY), stringOrNull, stringOrNull2, stringOrNull3, stringOrNull4, appFunctionDataWithSpec.getStringOrNull("recurrenceSchedule"));
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
    public final AppFunctionData toAppFunctionData(Task task) {
        j.e(task, "appFunctionSerializable");
        ? obj = new Object();
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("com.google.android.appfunctions.schema.tasks.v1.Task");
        appFunctionDataBuilder.setString("id", task.f1314a);
        appFunctionDataBuilder.setString("title", task.b);
        String str = task.f1315c;
        if (str != null) {
            appFunctionDataBuilder.setString("description", str);
        }
        Boolean bool = task.d;
        if (bool != null) {
            appFunctionDataBuilder.setBoolean("completed", bool.booleanValue());
        }
        String str2 = task.e;
        if (str2 != null) {
            appFunctionDataBuilder.setString("taskCategoryId", str2);
        }
        DateTime dateTime = task.f;
        if (dateTime != null) {
            appFunctionDataBuilder.setAppFunctionData("dateTime", obj.toAppFunctionData(dateTime));
        }
        Boolean bool2 = task.g;
        if (bool2 != null) {
            appFunctionDataBuilder.setBoolean(CalendarEventBundleWrapper.BUNDLE_KEY_ALL_DAY, bool2.booleanValue());
        }
        String str3 = task.f1316h;
        if (str3 != null) {
            appFunctionDataBuilder.setString("recurrenceSchedule", str3);
        }
        return appFunctionDataBuilder.build();
    }
}
