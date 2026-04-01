package com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1;

import android.os.Bundle;
import com.samsung.android.sdk.moneta.basicdomain.entity.FaceImageData;
import com.samsung.android.sdk.moneta.basicdomain.entity.Person;
import com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0002*\u00020\u0001¨\u0006\u0004"}, d2 = {"toPerson", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/Person;", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/wrapper/v1/PersonWrapper;", "toPersonWrapper", "pde-sdk-1.0.40_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PersonWrapperKt {
    public static final Person toPerson(PersonWrapper personWrapper) {
        PersonWrapper personWrapper2 = personWrapper;
        j.e(personWrapper2, "<this>");
        String string = personWrapper2.getProperties().getString(PersonProperty.NAME.getKey(), "");
        RelationShip fromValue = RelationShip.Companion.fromValue(personWrapper2.getProperties().getInt(PersonProperty.RELATIONSHIP.getKey(), -1));
        long j2 = personWrapper2.getProperties().getLong(PersonProperty.CONTACT_ID.getKey());
        long j3 = personWrapper2.getProperties().getLong(PersonProperty.FACE_GROUP_ID.getKey());
        Bundle bundle = new Bundle();
        bundle.putString(Person.PropertiesKey.LIVING_TOGETHER.getKey(), personWrapper2.getProperties().getString(PersonProperty.LIVING_TOGETHER.getKey()));
        bundle.putInt(Person.PropertiesKey.FACE_PERSON_ID.getKey(), personWrapper2.getProperties().getInt(PersonProperty.FACE_PERSON_ID.getKey(), -1));
        Person.PropertiesKey propertiesKey = Person.PropertiesKey.FACE_ESTIMATED_AGE_RANGE;
        bundle.putString(propertiesKey.getKey(), personWrapper2.getProperties().getString(propertiesKey.getKey()));
        Person.PropertiesKey propertiesKey2 = Person.PropertiesKey.FACE_ESTIMATED_GENDER;
        bundle.putString(propertiesKey2.getKey(), personWrapper2.getProperties().getString(propertiesKey2.getKey()));
        String id = personWrapper2.getId();
        j.b(string);
        return new Person(id, Long.valueOf(j2), Long.valueOf(j3), string, fromValue, bundle, (String) null, 0, (String) null, (Long) null, (FaceImageData) null, 1984, (e) null);
    }

    public static final PersonWrapper toPersonWrapper(Person person) {
        j.e(person, "<this>");
        Bundle bundle = new Bundle();
        bundle.putString(PersonProperty.NAME.getKey(), person.getName());
        bundle.putInt(PersonProperty.RELATIONSHIP.getKey(), person.getRelationship().getValue());
        Long contactId = person.getContactId();
        if (contactId != null) {
            bundle.putLong(PersonProperty.CONTACT_ID.getKey(), contactId.longValue());
        }
        Long faceGroupID = person.getFaceGroupID();
        if (faceGroupID != null) {
            bundle.putLong(PersonProperty.FACE_GROUP_ID.getKey(), faceGroupID.longValue());
        }
        bundle.putString(PersonProperty.LIVING_TOGETHER.getKey(), person.getProperties().getString(Person.PropertiesKey.LIVING_TOGETHER.getKey()));
        bundle.putInt(PersonProperty.FACE_PERSON_ID.getKey(), person.getProperties().getInt(Person.PropertiesKey.FACE_PERSON_ID.getKey()));
        Person.PropertiesKey propertiesKey = Person.PropertiesKey.FACE_ESTIMATED_AGE_RANGE;
        bundle.putString(propertiesKey.getKey(), person.getProperties().getString(propertiesKey.getKey()));
        Person.PropertiesKey propertiesKey2 = Person.PropertiesKey.FACE_ESTIMATED_GENDER;
        bundle.putString(propertiesKey2.getKey(), person.getProperties().getString(propertiesKey2.getKey()));
        return new PersonWrapper(person.getId(), bundle);
    }
}
