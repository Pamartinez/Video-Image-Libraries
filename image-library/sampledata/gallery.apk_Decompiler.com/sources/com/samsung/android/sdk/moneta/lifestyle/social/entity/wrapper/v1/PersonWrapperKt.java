package com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1;

import android.os.Bundle;
import com.samsung.android.sdk.moneta.lifestyle.social.entity.Person;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toPerson", "Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/Person;", "Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/wrapper/v1/PersonWrapper;", "pde-sdk-1.0.40_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PersonWrapperKt {
    public static final /* synthetic */ Person toPerson(PersonWrapper personWrapper) {
        j.e(personWrapper, "<this>");
        String string = personWrapper.getProperties().getString(PersonProperty.NAME.getKey(), "");
        String string2 = personWrapper.getProperties().getString(PersonProperty.PHONE_NUMBER.getKey(), "");
        Bundle bundle = new Bundle();
        bundle.putString(Person.PropertiesKey.CONTACT_ID.getKey(), personWrapper.getProperties().getString(PersonProperty.CONTACT_ID.getKey(), ""));
        String id = personWrapper.getId();
        j.b(string);
        j.b(string2);
        return new Person(id, string, string2, personWrapper.getPreferences(), bundle);
    }
}
