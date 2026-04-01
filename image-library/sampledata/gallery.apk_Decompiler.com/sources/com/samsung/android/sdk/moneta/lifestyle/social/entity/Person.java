package com.samsung.android.sdk.moneta.lifestyle.social.entity;

import A.a;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PreferenceKey;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import i.C0212a;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import te.C1295a;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\b\b\u0018\u00002\u00020\u0001:\u000201B3\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\r\u001a\u00060\u000bj\u0002`\fH\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000f\u001a\u00060\u000bj\u0002`\fH\u0003¢\u0006\u0004\b\u000f\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u0002H\u0017¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0014¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u0011J\u0010\u0010\u001c\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u0011J\u0010\u0010\u001d\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u0011J\u0010\u0010\u001e\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b \u0010\u001fJB\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006HÆ\u0001¢\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\u0014HÖ\u0001¢\u0006\u0004\b#\u0010\u001aJ\u001a\u0010'\u001a\u00020&2\b\u0010%\u001a\u0004\u0018\u00010$HÖ\u0003¢\u0006\u0004\b'\u0010(R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010)\u001a\u0004\b*\u0010\u0011R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010)\u001a\u0004\b+\u0010\u0011R\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010)\u001a\u0004\b,\u0010\u0011R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010-\u001a\u0004\b.\u0010\u001fR\u0017\u0010\b\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010-\u001a\u0004\b/\u0010\u001f¨\u00062"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/Person;", "Landroid/os/Parcelable;", "", "id", "name", "phoneNumber", "Landroid/os/Bundle;", "preferences", "properties", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;Landroid/os/Bundle;)V", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getPropertiesString", "()Ljava/lang/StringBuilder;", "getPreferencesString", "toString", "()Ljava/lang/String;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "component2", "component3", "component4", "()Landroid/os/Bundle;", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;Landroid/os/Bundle;)Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/Person;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "getName", "getPhoneNumber", "Landroid/os/Bundle;", "getPreferences", "getProperties", "PreferencesKey", "PropertiesKey", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Person implements Parcelable {
    public static final Parcelable.Creator<Person> CREATOR = new Creator();
    private final String id;
    private final String name;
    private final String phoneNumber;
    private final Bundle preferences;
    private final Bundle properties;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<Person> {
        public final Person createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            Class<Person> cls = Person.class;
            return new Person(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readBundle(cls.getClassLoader()), parcel.readBundle(cls.getClassLoader()));
        }

        public final Person[] newArray(int i2) {
            return new Person[i2];
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/Person$PreferencesKey;", "", "key", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getKey", "()Ljava/lang/String;", "START_TIMESTAMP", "END_TIMESTAMP", "NUM_OF_CONTACT", "LATEST_TIMESTAMP", "EVENT_TIMESTAMP", "LAST_CONTACT_TIMESTAMP", "PERIOD_OF_CONTACT", "NUM_OF_INCOMING", "NUM_OF_OUTGOING", "OUTGOING_RATE", "REQUESTED_DAYS", "DAYS_OF_CONTACT", "PREFERENCE_LEVEL", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum PreferencesKey {
        START_TIMESTAMP("start_timestamp"),
        END_TIMESTAMP("end_timestamp"),
        NUM_OF_CONTACT(PreferenceKey.PREFERENCE_KEY_NUM_OF_CONTACT),
        LATEST_TIMESTAMP(PreferenceKey.PREFERENCE_KEY_LATEST_TIMESTAMP),
        EVENT_TIMESTAMP(PreferenceKey.PREFERENCE_KEY_EVENT_TIMESTAMP),
        LAST_CONTACT_TIMESTAMP(PreferenceKey.PREFERENCE_KEY_LAST_CONTACT_TIMESTAMP),
        PERIOD_OF_CONTACT(PreferenceKey.PREFERENCE_KEY_PERIOD_OF_CONTACT),
        NUM_OF_INCOMING(PreferenceKey.PREFERENCE_KEY_NUM_OF_INCOMING),
        NUM_OF_OUTGOING(PreferenceKey.PREFERENCE_KEY_NUM_OF_OUTGOING),
        OUTGOING_RATE(PreferenceKey.PREFERENCE_KEY_OUTGOING_RATE),
        REQUESTED_DAYS(PreferenceKey.PREFERENCE_KEY_REQUESTED_NUM_OF_DAYS),
        DAYS_OF_CONTACT(PreferenceKey.PREFERENCE_KEY_DAYS_OF_CONTACT),
        PREFERENCE_LEVEL("preference_level");
        
        private final String key;

        static {
            PreferencesKey[] $values;
            $ENTRIES = c.t($values);
        }

        private PreferencesKey(String str) {
            this.key = str;
        }

        public static C1295a getEntries() {
            return $ENTRIES;
        }

        public final String getKey() {
            return this.key;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/Person$PropertiesKey;", "", "key", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getKey", "()Ljava/lang/String;", "CONTACT_ID", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum PropertiesKey {
        CONTACT_ID("contact_id");
        
        private final String key;

        static {
            PropertiesKey[] $values;
            $ENTRIES = c.t($values);
        }

        private PropertiesKey(String str) {
            this.key = str;
        }

        public static C1295a getEntries() {
            return $ENTRIES;
        }

        public final String getKey() {
            return this.key;
        }
    }

    public Person(String str, String str2, String str3, Bundle bundle, Bundle bundle2) {
        j.e(str, "id");
        j.e(str2, "name");
        j.e(str3, "phoneNumber");
        j.e(bundle, "preferences");
        j.e(bundle2, "properties");
        this.id = str;
        this.name = str2;
        this.phoneNumber = str3;
        this.preferences = bundle;
        this.properties = bundle2;
    }

    public static /* synthetic */ Person copy$default(Person person, String str, String str2, String str3, Bundle bundle, Bundle bundle2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = person.id;
        }
        if ((i2 & 2) != 0) {
            str2 = person.name;
        }
        if ((i2 & 4) != 0) {
            str3 = person.phoneNumber;
        }
        if ((i2 & 8) != 0) {
            bundle = person.preferences;
        }
        if ((i2 & 16) != 0) {
            bundle2 = person.properties;
        }
        Bundle bundle3 = bundle;
        Bundle bundle4 = bundle2;
        return person.copy(str, str2, str3, bundle3, bundle4);
    }

    private final StringBuilder getPreferencesString() {
        StringBuilder sb2 = new StringBuilder("preferences: [");
        sb2.append(10);
        Set<String> keySet = this.preferences.keySet();
        j.d(keySet, "keySet(...)");
        for (String str : keySet) {
            if (j.a(str, PreferencesKey.START_TIMESTAMP.getKey())) {
                StringBuilder i2 = a.i("key : ", str, ' ', sb2, "value: ");
                i2.append(this.preferences.getLong(str));
                sb2.append(i2.toString());
                sb2.append(10);
            } else if (j.a(str, PreferencesKey.END_TIMESTAMP.getKey())) {
                StringBuilder i7 = a.i("key : ", str, ' ', sb2, "value: ");
                i7.append(this.preferences.getLong(str));
                sb2.append(i7.toString());
                sb2.append(10);
            } else if (j.a(str, PreferencesKey.NUM_OF_CONTACT.getKey())) {
                StringBuilder i8 = a.i("key : ", str, ' ', sb2, "value: ");
                i8.append(this.preferences.getInt(str));
                sb2.append(i8.toString());
                sb2.append(10);
            } else if (j.a(str, PreferencesKey.LATEST_TIMESTAMP.getKey())) {
                StringBuilder i10 = a.i("key : ", str, ' ', sb2, "value: ");
                i10.append(this.preferences.getLong(str));
                sb2.append(i10.toString());
                sb2.append(10);
            } else if (j.a(str, PreferencesKey.LAST_CONTACT_TIMESTAMP.getKey())) {
                StringBuilder i11 = a.i("key : ", str, ' ', sb2, "value: ");
                i11.append(this.preferences.getLong(str));
                sb2.append(i11.toString());
                sb2.append(10);
            } else if (j.a(str, PreferencesKey.EVENT_TIMESTAMP.getKey())) {
                StringBuilder i12 = a.i("key : ", str, ' ', sb2, "value: ");
                i12.append(this.preferences.getLong(str));
                sb2.append(i12.toString());
                sb2.append(10);
            } else if (j.a(str, PreferencesKey.PERIOD_OF_CONTACT.getKey())) {
                StringBuilder i13 = a.i("key : ", str, ' ', sb2, "value: ");
                i13.append(this.preferences.getDouble(str));
                sb2.append(i13.toString());
                sb2.append(10);
            } else if (j.a(str, PreferencesKey.NUM_OF_INCOMING.getKey())) {
                StringBuilder i14 = a.i("key : ", str, ' ', sb2, "value: ");
                i14.append(this.preferences.getInt(str));
                sb2.append(i14.toString());
                sb2.append(10);
            } else if (j.a(str, PreferencesKey.NUM_OF_OUTGOING.getKey())) {
                StringBuilder i15 = a.i("key : ", str, ' ', sb2, "value: ");
                i15.append(this.preferences.getInt(str));
                sb2.append(i15.toString());
                sb2.append(10);
            } else if (j.a(str, PreferencesKey.OUTGOING_RATE.getKey())) {
                StringBuilder i16 = a.i("key : ", str, ' ', sb2, "value: ");
                i16.append(this.preferences.getDouble(str));
                sb2.append(i16.toString());
                sb2.append(10);
            } else if (j.a(str, PreferencesKey.REQUESTED_DAYS.getKey())) {
                StringBuilder i17 = a.i("key : ", str, ' ', sb2, "value: ");
                i17.append(this.preferences.getInt(str));
                sb2.append(i17.toString());
                sb2.append(10);
            } else if (j.a(str, PreferencesKey.DAYS_OF_CONTACT.getKey())) {
                StringBuilder i18 = a.i("key : ", str, ' ', sb2, "value: ");
                i18.append(this.preferences.getInt(str));
                sb2.append(i18.toString());
                sb2.append(10);
            } else if (j.a(str, PreferencesKey.PREFERENCE_LEVEL.getKey())) {
                StringBuilder i19 = a.i("key : ", str, ' ', sb2, "value: ");
                i19.append(this.preferences.getSerializable(str, PreferenceLevel.class));
                sb2.append(i19.toString());
                sb2.append(10);
            }
        }
        sb2.append("]\n");
        return sb2;
    }

    private final StringBuilder getPropertiesString() {
        StringBuilder sb2 = new StringBuilder("properties: [");
        sb2.append(10);
        Set<String> keySet = this.properties.keySet();
        j.d(keySet, "keySet(...)");
        for (String str : keySet) {
            if (j.a(str, PropertiesKey.CONTACT_ID.getKey())) {
                StringBuilder i2 = a.i("key : ", str, ' ', sb2, "value: ");
                i2.append(this.properties.getString(str));
                sb2.append(i2.toString());
                sb2.append(10);
            }
        }
        sb2.append("]\n");
        return sb2;
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.name;
    }

    public final String component3() {
        return this.phoneNumber;
    }

    public final Bundle component4() {
        return this.preferences;
    }

    public final Bundle component5() {
        return this.properties;
    }

    public final Person copy(String str, String str2, String str3, Bundle bundle, Bundle bundle2) {
        j.e(str, "id");
        j.e(str2, "name");
        j.e(str3, "phoneNumber");
        j.e(bundle, "preferences");
        j.e(bundle2, "properties");
        return new Person(str, str2, str3, bundle, bundle2);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Person)) {
            return false;
        }
        Person person = (Person) obj;
        if (j.a(this.id, person.id) && j.a(this.name, person.name) && j.a(this.phoneNumber, person.phoneNumber) && j.a(this.preferences, person.preferences) && j.a(this.properties, person.properties)) {
            return true;
        }
        return false;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPhoneNumber() {
        return this.phoneNumber;
    }

    public final Bundle getPreferences() {
        return this.preferences;
    }

    public final Bundle getProperties() {
        return this.properties;
    }

    public int hashCode() {
        int d = C0212a.d(C0212a.d(this.id.hashCode() * 31, 31, this.name), 31, this.phoneNumber);
        return this.properties.hashCode() + ((this.preferences.hashCode() + d) * 31);
    }

    public String toString() {
        return "id : " + this.id + 10 + getPreferencesString() + getPropertiesString();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.phoneNumber);
        parcel.writeBundle(this.preferences);
        parcel.writeBundle(this.properties);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Person(String str, String str2, String str3, Bundle bundle, Bundle bundle2, int i2, e eVar) {
        this(str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3, bundle, bundle2);
    }
}
