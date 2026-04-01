package com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1;

import android.os.Bundle;
import com.samsung.android.sdk.moneta.basicdomain.entity.Pet;
import com.samsung.android.sdk.moneta.basicdomain.entity.PetType;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1194l;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toPet", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/Pet;", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/wrapper/v1/MyPetWrapper;", "pde-sdk-1.0.40_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MyPetWrapperKt {
    public static final Pet toPet(MyPetWrapper myPetWrapper) {
        j.e(myPetWrapper, "<this>");
        String name = myPetWrapper.getName();
        PetType petType = myPetWrapper.getPetType();
        Bundle bundle = new Bundle();
        bundle.putString(Pet.PropertiesKey.SOURCE.getKey(), myPetWrapper.getSource());
        bundle.putIntArray(Pet.PropertiesKey.GROUP_IDS.getKey(), C1194l.j1(myPetWrapper.getGroupIds()));
        return new Pet(name, petType, bundle);
    }
}
