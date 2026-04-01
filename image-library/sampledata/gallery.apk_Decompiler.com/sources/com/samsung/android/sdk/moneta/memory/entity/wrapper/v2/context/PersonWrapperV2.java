package com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.context;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PersonBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.context.Person;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0007\u0018\u0000 #2\u00020\u0001:\u0001#B9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\r\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u0011¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u001b\u0010\u001aR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001f\u001a\u0004\b \u0010!R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\u001f\u001a\u0004\b\"\u0010!¨\u0006$"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PersonWrapperV2;", "Landroid/os/Parcelable;", "", "id", "name", "", "relationships", "", "contactId", "personId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Long;Ljava/lang/Long;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Person;", "toContext", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/Person;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getName", "Ljava/util/List;", "getRelationships", "()Ljava/util/List;", "Ljava/lang/Long;", "getContactId", "()Ljava/lang/Long;", "getPersonId", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PersonWrapperV2 implements Parcelable {
    public static final Parcelable.Creator<PersonWrapperV2> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final Long contactId;
    private final String id;
    private final String name;
    private final Long personId;
    private final List<String> relationships;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PersonWrapperV2$Companion;", "", "<init>", "()V", "fromContext", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PersonWrapperV2;", "person", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Person;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ PersonWrapperV2 fromContext(Person person) {
            j.e(person, "person");
            return new PersonWrapperV2(person.getId(), person.getName(), person.getRelationships(), person.getContactId(), person.getPersonId());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<PersonWrapperV2> {
        public final PersonWrapperV2 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            ArrayList<String> createStringArrayList = parcel.createStringArrayList();
            Long l = null;
            Long valueOf = parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong());
            if (parcel.readInt() != 0) {
                l = Long.valueOf(parcel.readLong());
            }
            return new PersonWrapperV2(readString, readString2, createStringArrayList, valueOf, l);
        }

        public final PersonWrapperV2[] newArray(int i2) {
            return new PersonWrapperV2[i2];
        }
    }

    public PersonWrapperV2(String str, String str2, List<String> list, Long l, Long l8) {
        j.e(str, "id");
        j.e(str2, "name");
        j.e(list, PersonBundleWrapper.BUNDLE_KEY_RELATIONSHIPS);
        this.id = str;
        this.name = str2;
        this.relationships = list;
        this.contactId = l;
        this.personId = l8;
    }

    public final int describeContents() {
        return 0;
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

    public final /* synthetic */ Person toContext() {
        return new Person(this.id, this.name, this.relationships, this.contactId, this.personId);
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
