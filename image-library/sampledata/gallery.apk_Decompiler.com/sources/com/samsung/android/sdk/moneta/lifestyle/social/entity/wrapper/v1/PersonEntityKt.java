package com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1;

import android.os.Bundle;
import com.samsung.android.sdk.moneta.lifestyle.social.entity.Person;
import com.samsung.android.sdk.moneta.lifestyle.social.entity.PreferenceLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toPerson", "Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/Person;", "Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/wrapper/v1/PersonEntity;", "pde-sdk-1.0.40_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PersonEntityKt {
    public static final /* synthetic */ Person toPerson(PersonEntity personEntity) {
        j.e(personEntity, "<this>");
        Bundle bundle = new Bundle();
        bundle.putString(Person.PropertiesKey.CONTACT_ID.getKey(), personEntity.getContactId());
        Bundle bundle2 = new Bundle();
        PersonPreference preferences = personEntity.getPreferences();
        if (preferences != null) {
            Long latestTimestamp = preferences.getLatestTimestamp();
            if (latestTimestamp != null) {
                bundle2.putLong(PreferenceKey.PREFERENCE_KEY_LATEST_TIMESTAMP, latestTimestamp.longValue());
            }
            Long startTimestamp = preferences.getStartTimestamp();
            if (startTimestamp != null) {
                bundle2.putLong("start_timestamp", startTimestamp.longValue());
            }
            Long endTimestamp = preferences.getEndTimestamp();
            if (endTimestamp != null) {
                bundle2.putLong("end_timestamp", endTimestamp.longValue());
            }
            Long eventTimestamp = preferences.getEventTimestamp();
            if (eventTimestamp != null) {
                bundle2.putLong(PreferenceKey.PREFERENCE_KEY_EVENT_TIMESTAMP, eventTimestamp.longValue());
            }
            Integer daysOfContact = preferences.getDaysOfContact();
            if (daysOfContact != null) {
                bundle2.putInt(PreferenceKey.PREFERENCE_KEY_DAYS_OF_CONTACT, daysOfContact.intValue());
            }
            Double periodOfContact = preferences.getPeriodOfContact();
            if (periodOfContact != null) {
                bundle2.putDouble(PreferenceKey.PREFERENCE_KEY_PERIOD_OF_CONTACT, periodOfContact.doubleValue());
            }
            Long lastContactTimestamp = preferences.getLastContactTimestamp();
            if (lastContactTimestamp != null) {
                bundle2.putLong(PreferenceKey.PREFERENCE_KEY_LAST_CONTACT_TIMESTAMP, lastContactTimestamp.longValue());
            }
            Integer requestedNumOfDays = preferences.getRequestedNumOfDays();
            if (requestedNumOfDays != null) {
                bundle2.putInt(PreferenceKey.PREFERENCE_KEY_REQUESTED_NUM_OF_DAYS, requestedNumOfDays.intValue());
            }
            PreferenceLevel preferenceLevel = preferences.getPreferenceLevel();
            if (preferenceLevel != null) {
                bundle2.putSerializable("preference_level", preferenceLevel);
            }
            Integer numOfIncoming = preferences.getNumOfIncoming();
            if (numOfIncoming != null) {
                bundle2.putInt(PreferenceKey.PREFERENCE_KEY_NUM_OF_INCOMING, numOfIncoming.intValue());
            }
            Double outgoingRate = preferences.getOutgoingRate();
            if (outgoingRate != null) {
                bundle2.putDouble(PreferenceKey.PREFERENCE_KEY_OUTGOING_RATE, outgoingRate.doubleValue());
            }
            Integer numOfContact = preferences.getNumOfContact();
            if (numOfContact != null) {
                bundle2.putInt(PreferenceKey.PREFERENCE_KEY_NUM_OF_CONTACT, numOfContact.intValue());
            }
            Integer numOfOutgoing = preferences.getNumOfOutgoing();
            if (numOfOutgoing != null) {
                bundle2.putInt(PreferenceKey.PREFERENCE_KEY_NUM_OF_OUTGOING, numOfOutgoing.intValue());
            }
        }
        return new Person(personEntity.getId(), personEntity.getName(), personEntity.getPhoneNumber(), bundle2, bundle);
    }
}
