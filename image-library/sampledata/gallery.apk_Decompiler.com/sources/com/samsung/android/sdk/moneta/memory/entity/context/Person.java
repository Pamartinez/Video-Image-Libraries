package com.samsung.android.sdk.moneta.memory.entity.context;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PersonBundleWrapper;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\b\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u000e¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0016J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001bJL\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001f\u0010\u0016J\u0010\u0010 \u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b \u0010\u0014J\u001a\u0010$\u001a\u00020#2\b\u0010\"\u001a\u0004\u0018\u00010!HÖ\u0003¢\u0006\u0004\b$\u0010%R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010&\u001a\u0004\b'\u0010\u0016R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010&\u001a\u0004\b(\u0010\u0016R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010)\u001a\u0004\b*\u0010\u0019R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010+\u001a\u0004\b,\u0010\u001bR\u0019\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010+\u001a\u0004\b-\u0010\u001b¨\u0006."}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/context/Person;", "Landroid/os/Parcelable;", "", "id", "name", "", "relationships", "", "contactId", "personId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Long;Ljava/lang/Long;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "component3", "()Ljava/util/List;", "component4", "()Ljava/lang/Long;", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Long;Ljava/lang/Long;)Lcom/samsung/android/sdk/moneta/memory/entity/context/Person;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "getName", "Ljava/util/List;", "getRelationships", "Ljava/lang/Long;", "getContactId", "getPersonId", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Person implements Parcelable {
    public static final Parcelable.Creator<Person> CREATOR = new Creator();
    private final Long contactId;
    private final String id;
    private final String name;
    private final Long personId;
    private final List<String> relationships;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<Person> {
        public final Person createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            ArrayList<String> createStringArrayList = parcel.createStringArrayList();
            Long l = null;
            Long valueOf = parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong());
            if (parcel.readInt() != 0) {
                l = Long.valueOf(parcel.readLong());
            }
            return new Person(readString, readString2, createStringArrayList, valueOf, l);
        }

        public final Person[] newArray(int i2) {
            return new Person[i2];
        }
    }

    public Person(String str, String str2, List<String> list, Long l, Long l8) {
        j.e(str, "id");
        j.e(str2, "name");
        j.e(list, PersonBundleWrapper.BUNDLE_KEY_RELATIONSHIPS);
        this.id = str;
        this.name = str2;
        this.relationships = list;
        this.contactId = l;
        this.personId = l8;
    }

    public static /* synthetic */ Person copy$default(Person person, String str, String str2, List<String> list, Long l, Long l8, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = person.id;
        }
        if ((i2 & 2) != 0) {
            str2 = person.name;
        }
        if ((i2 & 4) != 0) {
            list = person.relationships;
        }
        if ((i2 & 8) != 0) {
            l = person.contactId;
        }
        if ((i2 & 16) != 0) {
            l8 = person.personId;
        }
        Long l10 = l;
        Long l11 = l8;
        return person.copy(str, str2, list, l10, l11);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.name;
    }

    public final List<String> component3() {
        return this.relationships;
    }

    public final Long component4() {
        return this.contactId;
    }

    public final Long component5() {
        return this.personId;
    }

    public final Person copy(String str, String str2, List<String> list, Long l, Long l8) {
        j.e(str, "id");
        j.e(str2, "name");
        j.e(list, PersonBundleWrapper.BUNDLE_KEY_RELATIONSHIPS);
        return new Person(str, str2, list, l, l8);
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
        if (j.a(this.id, person.id) && j.a(this.name, person.name) && j.a(this.relationships, person.relationships) && j.a(this.contactId, person.contactId) && j.a(this.personId, person.personId)) {
            return true;
        }
        return false;
    }

    public final Long getContactId() {
        return this.contactId;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final Long getPersonId() {
        return this.personId;
    }

    public final List<String> getRelationships() {
        return this.relationships;
    }

    public int hashCode() {
        int i2;
        int f = C0212a.f(this.relationships, C0212a.d(this.id.hashCode() * 31, 31, this.name), 31);
        Long l = this.contactId;
        int i7 = 0;
        if (l == null) {
            i2 = 0;
        } else {
            i2 = l.hashCode();
        }
        int i8 = (f + i2) * 31;
        Long l8 = this.personId;
        if (l8 != null) {
            i7 = l8.hashCode();
        }
        return i8 + i7;
    }

    public String toString() {
        return "Person(id=" + this.id + ", name=" + this.name + ", relationships=" + this.relationships + ", contactId=" + this.contactId + ", personId=" + this.personId + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        parcel.writeStringList(this.relationships);
        Long l = this.contactId;
        if (l == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l);
        }
        Long l8 = this.personId;
        if (l8 == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l8);
        }
    }
}
