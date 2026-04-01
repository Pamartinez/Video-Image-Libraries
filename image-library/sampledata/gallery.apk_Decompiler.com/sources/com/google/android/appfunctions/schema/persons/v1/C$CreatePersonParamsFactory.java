package com.google.android.appfunctions.schema.persons.v1;

import android.net.Uri;
import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import androidx.appfunctions.internal.serializableproxies.C$UriFactory;
import com.samsung.android.sdk.mobileservice.profile.Privacy;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1196n;
import ne.C1202t;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/google/android/appfunctions/schema/persons/v1/$CreatePersonParamsFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Lcom/google/android/appfunctions/schema/persons/v1/CreatePersonParams;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "java.com.google.android.libraries.llm.appfunctions.appfunctions_schema2.src.com.google.android.appfunctions.schema.persons.v1_v1"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* renamed from: com.google.android.appfunctions.schema.persons.v1.$CreatePersonParamsFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$CreatePersonParamsFactory implements AppFunctionSerializableFactory<CreatePersonParams> {
    /* JADX WARNING: type inference failed for: r12v1, types: [com.google.android.appfunctions.schema.persons.v1.$PersonPhoneNumberFactory, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v2, types: [com.google.android.appfunctions.schema.persons.v1.$PersonEmailAddressFactory, java.lang.Object] */
    public final CreatePersonParams fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData, "com.google.android.appfunctions.schema.persons.v1.CreatePersonParams");
        ? obj = new Object();
        ? obj2 = new Object();
        C$UriFactory _urifactory = new C$UriFactory();
        String stringOrNull = appFunctionDataWithSpec.getStringOrNull("displayName");
        if (stringOrNull != null) {
            String stringOrNull2 = appFunctionDataWithSpec.getStringOrNull("givenName");
            String stringOrNull3 = appFunctionDataWithSpec.getStringOrNull("middleName");
            String stringOrNull4 = appFunctionDataWithSpec.getStringOrNull("familyName");
            AppFunctionData appFunctionData2 = appFunctionDataWithSpec.getAppFunctionData("externalUri");
            Uri fromAppFunctionData = appFunctionData2 != null ? _urifactory.fromAppFunctionData(appFunctionData2) : null;
            List<AppFunctionData> appFunctionDataList = appFunctionDataWithSpec.getAppFunctionDataList(Privacy.KEY_PHONE_NUMBERS);
            List<AppFunctionData> list = C1202t.d;
            if (appFunctionDataList == null) {
                appFunctionDataList = list;
            }
            Iterable<AppFunctionData> iterable = appFunctionDataList;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (AppFunctionData fromAppFunctionData2 : iterable) {
                arrayList.add(obj.fromAppFunctionData(fromAppFunctionData2));
            }
            List<AppFunctionData> appFunctionDataList2 = appFunctionDataWithSpec.getAppFunctionDataList(Privacy.KEY_EMAIL_ADDRESSES);
            if (appFunctionDataList2 != null) {
                list = appFunctionDataList2;
            }
            Iterable<AppFunctionData> iterable2 = list;
            ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable2, 10));
            for (AppFunctionData fromAppFunctionData3 : iterable2) {
                arrayList2.add(obj2.fromAppFunctionData(fromAppFunctionData3));
            }
            return new CreatePersonParams(stringOrNull, stringOrNull2, stringOrNull3, stringOrNull4, fromAppFunctionData, arrayList, arrayList2);
        }
        throw new IllegalStateException("Required value was null.");
    }

    public final /* bridge */ /* synthetic */ AppFunctionData.Builder getAppFunctionDataBuilder(String str) {
        return super.getAppFunctionDataBuilder(str);
    }

    public final /* bridge */ /* synthetic */ AppFunctionData getAppFunctionDataWithSpec(AppFunctionData appFunctionData, String str) {
        return super.getAppFunctionDataWithSpec(appFunctionData, str);
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [com.google.android.appfunctions.schema.persons.v1.$PersonPhoneNumberFactory, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v0, types: [com.google.android.appfunctions.schema.persons.v1.$PersonEmailAddressFactory, java.lang.Object] */
    public final AppFunctionData toAppFunctionData(CreatePersonParams createPersonParams) {
        j.e(createPersonParams, "appFunctionSerializable");
        ? obj = new Object();
        ? obj2 = new Object();
        C$UriFactory _urifactory = new C$UriFactory();
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("com.google.android.appfunctions.schema.persons.v1.CreatePersonParams");
        appFunctionDataBuilder.setString("displayName", createPersonParams.f1267a);
        String str = createPersonParams.b;
        if (str != null) {
            appFunctionDataBuilder.setString("givenName", str);
        }
        String str2 = createPersonParams.f1268c;
        if (str2 != null) {
            appFunctionDataBuilder.setString("middleName", str2);
        }
        String str3 = createPersonParams.d;
        if (str3 != null) {
            appFunctionDataBuilder.setString("familyName", str3);
        }
        Uri uri = createPersonParams.e;
        if (uri != null) {
            appFunctionDataBuilder.setAppFunctionData("externalUri", _urifactory.toAppFunctionData(uri));
        }
        ArrayList<PersonPhoneNumber> arrayList = createPersonParams.f;
        ArrayList arrayList2 = new ArrayList(C1196n.w0(arrayList, 10));
        for (PersonPhoneNumber appFunctionData : arrayList) {
            arrayList2.add(obj.toAppFunctionData(appFunctionData));
        }
        appFunctionDataBuilder.setAppFunctionDataList(Privacy.KEY_PHONE_NUMBERS, arrayList2);
        ArrayList<PersonEmailAddress> arrayList3 = createPersonParams.g;
        ArrayList arrayList4 = new ArrayList(C1196n.w0(arrayList3, 10));
        for (PersonEmailAddress appFunctionData2 : arrayList3) {
            arrayList4.add(obj2.toAppFunctionData(appFunctionData2));
        }
        appFunctionDataBuilder.setAppFunctionDataList(Privacy.KEY_EMAIL_ADDRESSES, arrayList4);
        return appFunctionDataBuilder.build();
    }
}
