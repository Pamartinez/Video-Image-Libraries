package com.google.android.appfunctions.schema.messages.v1;

import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import com.google.android.appfunctions.schema.types.v1.DateTime;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1196n;
import ne.C1202t;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/google/android/appfunctions/schema/messages/v1/$MessageFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Lcom/google/android/appfunctions/schema/messages/v1/Message;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.messages.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* renamed from: com.google.android.appfunctions.schema.messages.v1.$MessageFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$MessageFactory implements AppFunctionSerializableFactory<Message> {
    /* JADX WARNING: type inference failed for: r13v1, types: [com.google.android.appfunctions.schema.types.v1.$DateTimeFactory, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Object, com.google.android.appfunctions.schema.messages.v1.$AttachmentFactory] */
    public final Message fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData, "com.google.android.appfunctions.schema.messages.v1.Message");
        ? obj = new Object();
        ? obj2 = new Object();
        String stringOrNull = appFunctionDataWithSpec.getStringOrNull("id");
        if (stringOrNull != null) {
            String stringOrNull2 = appFunctionDataWithSpec.getStringOrNull("messageType");
            if (stringOrNull2 != null) {
                String stringOrNull3 = appFunctionDataWithSpec.getStringOrNull("body");
                if (stringOrNull3 != null) {
                    String stringOrNull4 = appFunctionDataWithSpec.getStringOrNull("senderId");
                    if (stringOrNull4 != null) {
                        List stringList = appFunctionDataWithSpec.getStringList("recipientsIds");
                        List list = C1202t.d;
                        List list2 = stringList == null ? list : stringList;
                        AppFunctionData appFunctionData2 = appFunctionDataWithSpec.getAppFunctionData("dateSent");
                        DateTime dateTime = null;
                        DateTime fromAppFunctionData = appFunctionData2 != null ? obj.fromAppFunctionData(appFunctionData2) : null;
                        AppFunctionData appFunctionData3 = appFunctionDataWithSpec.getAppFunctionData("dateReceived");
                        DateTime fromAppFunctionData2 = appFunctionData3 != null ? obj.fromAppFunctionData(appFunctionData3) : null;
                        AppFunctionData appFunctionData4 = appFunctionDataWithSpec.getAppFunctionData("dateRead");
                        if (appFunctionData4 != null) {
                            dateTime = obj.fromAppFunctionData(appFunctionData4);
                        }
                        DateTime dateTime2 = dateTime;
                        List appFunctionDataList = appFunctionDataWithSpec.getAppFunctionDataList("messageAttachments");
                        if (appFunctionDataList != null) {
                            list = appFunctionDataList;
                        }
                        Iterable<AppFunctionData> iterable = list;
                        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
                        for (AppFunctionData fromAppFunctionData3 : iterable) {
                            arrayList.add(obj2.fromAppFunctionData(fromAppFunctionData3));
                        }
                        return new Message(stringOrNull, stringOrNull2, stringOrNull3, stringOrNull4, list2, fromAppFunctionData, fromAppFunctionData2, dateTime2, arrayList);
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
    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, com.google.android.appfunctions.schema.messages.v1.$AttachmentFactory] */
    public final AppFunctionData toAppFunctionData(Message message) {
        j.e(message, "appFunctionSerializable");
        ? obj = new Object();
        ? obj2 = new Object();
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("com.google.android.appfunctions.schema.messages.v1.Message");
        appFunctionDataBuilder.setString("id", message.f1240a);
        appFunctionDataBuilder.setString("messageType", message.b);
        appFunctionDataBuilder.setString("body", message.f1241c);
        appFunctionDataBuilder.setString("senderId", message.d);
        appFunctionDataBuilder.setStringList("recipientsIds", message.e);
        DateTime dateTime = message.f;
        if (dateTime != null) {
            appFunctionDataBuilder.setAppFunctionData("dateSent", obj.toAppFunctionData(dateTime));
        }
        DateTime dateTime2 = message.g;
        if (dateTime2 != null) {
            appFunctionDataBuilder.setAppFunctionData("dateReceived", obj.toAppFunctionData(dateTime2));
        }
        DateTime dateTime3 = message.f1242h;
        if (dateTime3 != null) {
            appFunctionDataBuilder.setAppFunctionData("dateRead", obj.toAppFunctionData(dateTime3));
        }
        ArrayList<Attachment> arrayList = message.f1243i;
        ArrayList arrayList2 = new ArrayList(C1196n.w0(arrayList, 10));
        for (Attachment appFunctionData : arrayList) {
            arrayList2.add(obj2.toAppFunctionData(appFunctionData));
        }
        appFunctionDataBuilder.setAppFunctionDataList("messageAttachments", arrayList2);
        return appFunctionDataBuilder.build();
    }
}
