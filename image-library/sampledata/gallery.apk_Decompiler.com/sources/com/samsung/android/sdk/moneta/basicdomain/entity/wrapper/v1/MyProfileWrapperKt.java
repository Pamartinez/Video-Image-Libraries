package com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1;

import android.os.Bundle;
import com.samsung.android.sdk.moneta.basicdomain.entity.AgeGroup;
import com.samsung.android.sdk.moneta.basicdomain.entity.MyProfile;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toMyProfile", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/MyProfile;", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/wrapper/v1/MyProfileWrapper;", "pde-sdk-1.0.40_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MyProfileWrapperKt {
    public static final MyProfile toMyProfile(MyProfileWrapper myProfileWrapper) {
        AgeGroup ageGroup;
        j.e(myProfileWrapper, "<this>");
        Bundle bundle = new Bundle();
        try {
            ageGroup = AgeGroup.Companion.fromValue(myProfileWrapper.getProperties().getInt(MyProfileProperty.AGE_GROUP.getKey()));
        } catch (Exception unused) {
            ageGroup = null;
        }
        if (ageGroup != null) {
            bundle.putParcelable(MyProfile.PropertiesKey.AGE_GROUP.getKey(), ageGroup);
        }
        String string = myProfileWrapper.getProperties().getString(MyProfileProperty.PHOTO_URL.getKey());
        if (string != null) {
            bundle.putString(MyProfile.PropertiesKey.PHOTO_URL.getKey(), string);
        }
        String string2 = myProfileWrapper.getProperties().getString(MyProfileProperty.BIRTHDAY.getKey());
        if (string2 != null) {
            bundle.putString(MyProfile.PropertiesKey.BIRTHDAY.getKey(), string2);
        }
        bundle.putFloat(MyProfile.PropertiesKey.DRIVING.getKey(), myProfileWrapper.getProperties().getFloat(MyProfileProperty.DRIVING.getKey()));
        String string3 = myProfileWrapper.getProperties().getString(MyProfileProperty.GIVEN_NAME.getKey());
        if (string3 != null) {
            bundle.putString(MyProfile.PropertiesKey.GIVEN_NAME.getKey(), string3);
        }
        String string4 = myProfileWrapper.getProperties().getString(MyProfileProperty.FAMILY_NAME.getKey());
        if (string4 != null) {
            bundle.putString(MyProfile.PropertiesKey.FAMILY_NAME.getKey(), string4);
        }
        return new MyProfile(myProfileWrapper.getName(), myProfileWrapper.getPhoneNumbers(), bundle);
    }
}
