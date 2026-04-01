package com.samsung.android.sdk.moneta.basicdomain.entity;

import A.a;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import i.C0212a;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import te.C1295a;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\b\b\u0018\u00002\u00020\u0001:\u0002KLBw\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0002H\u0017¢\u0006\u0004\b\u0015\u0010\u0016J\u001d\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\r¢\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001d\u001a\u00020\r¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u0016J\u0012\u0010 \u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b \u0010!J\u0012\u0010\"\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\"\u0010!J\u0010\u0010#\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b#\u0010\u0016J\u0010\u0010$\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b$\u0010%J\u0010\u0010&\u001a\u00020\nHÆ\u0003¢\u0006\u0004\b&\u0010'J\u0012\u0010(\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b(\u0010\u0016J\u0010\u0010)\u001a\u00020\rHÆ\u0003¢\u0006\u0004\b)\u0010\u001eJ\u0012\u0010*\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b*\u0010\u0016J\u0012\u0010+\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b+\u0010!J\u0012\u0010,\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0004\b,\u0010-J\u0001\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÆ\u0001¢\u0006\u0004\b.\u0010/J\u0010\u00100\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b0\u0010\u001eJ\u001a\u00104\u001a\u0002032\b\u00102\u001a\u0004\u0018\u000101HÖ\u0003¢\u0006\u0004\b4\u00105J\u0013\u00108\u001a\u000606j\u0002`7H\u0003¢\u0006\u0004\b8\u00109R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010:\u001a\u0004\b;\u0010\u0016R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010<\u001a\u0004\b=\u0010!R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010<\u001a\u0004\b>\u0010!R\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010:\u001a\u0004\b?\u0010\u0016R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010@\u001a\u0004\bA\u0010%R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010B\u001a\u0004\bC\u0010'R\u0019\u0010\f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\f\u0010:\u001a\u0004\bD\u0010\u0016R\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010E\u001a\u0004\bF\u0010\u001eR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010:\u001a\u0004\bG\u0010\u0016R\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0010\u0010<\u001a\u0004\bH\u0010!R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010I\u001a\u0004\bJ\u0010-¨\u0006M"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/entity/Person;", "Landroid/os/Parcelable;", "", "id", "", "contactId", "faceGroupID", "name", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip;", "relationship", "Landroid/os/Bundle;", "properties", "galleryPersonUuid", "", "faceId", "rawContactUuid", "rawContactId", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/FaceImageData;", "faceImageData", "<init>", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip;Landroid/os/Bundle;Ljava/lang/String;ILjava/lang/String;Ljava/lang/Long;Lcom/samsung/android/sdk/moneta/basicdomain/entity/FaceImageData;)V", "toString", "()Ljava/lang/String;", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "component2", "()Ljava/lang/Long;", "component3", "component4", "component5", "()Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip;", "component6", "()Landroid/os/Bundle;", "component7", "component8", "component9", "component10", "component11", "()Lcom/samsung/android/sdk/moneta/basicdomain/entity/FaceImageData;", "copy", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip;Landroid/os/Bundle;Ljava/lang/String;ILjava/lang/String;Ljava/lang/Long;Lcom/samsung/android/sdk/moneta/basicdomain/entity/FaceImageData;)Lcom/samsung/android/sdk/moneta/basicdomain/entity/Person;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getPropertiesString", "()Ljava/lang/StringBuilder;", "Ljava/lang/String;", "getId", "Ljava/lang/Long;", "getContactId", "getFaceGroupID", "getName", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip;", "getRelationship", "Landroid/os/Bundle;", "getProperties", "getGalleryPersonUuid", "I", "getFaceId", "getRawContactUuid", "getRawContactId", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/FaceImageData;", "getFaceImageData", "PropertiesKey", "LivingTogetherType", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Person implements Parcelable {
    public static final Parcelable.Creator<Person> CREATOR = new Creator();
    private final Long contactId;
    private final Long faceGroupID;
    private final int faceId;
    private final FaceImageData faceImageData;
    private final String galleryPersonUuid;
    private final String id;
    private final String name;
    private final transient Bundle properties;
    private final Long rawContactId;
    private final String rawContactUuid;
    private final RelationShip relationship;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<Person> {
        public final Person createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            FaceImageData faceImageData = null;
            Long valueOf = parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong());
            Long valueOf2 = parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong());
            String readString2 = parcel.readString();
            RelationShip createFromParcel = RelationShip.CREATOR.createFromParcel(parcel);
            Bundle readBundle = parcel.readBundle(Person.class.getClassLoader());
            String readString3 = parcel.readString();
            int readInt = parcel.readInt();
            String readString4 = parcel.readString();
            Long valueOf3 = parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong());
            if (parcel.readInt() != 0) {
                faceImageData = FaceImageData.CREATOR.createFromParcel(parcel);
            }
            return new Person(readString, valueOf, valueOf2, readString2, createFromParcel, readBundle, readString3, readInt, readString4, valueOf3, faceImageData);
        }

        public final Person[] newArray(int i2) {
            return new Person[i2];
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/entity/Person$LivingTogetherType;", "", "<init>", "(Ljava/lang/String;I)V", "NOT_SUPPORTED", "UNKNOWN", "TRUE", "FALSE", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum LivingTogetherType {
        NOT_SUPPORTED,
        UNKNOWN,
        TRUE,
        FALSE;

        static {
            LivingTogetherType[] $values;
            $ENTRIES = c.t($values);
        }

        public static C1295a getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/entity/Person$PropertiesKey;", "", "key", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getKey", "()Ljava/lang/String;", "LIVING_TOGETHER", "FACE_PERSON_ID", "FACE_ESTIMATED_AGE_RANGE", "FACE_ESTIMATED_GENDER", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum PropertiesKey {
        LIVING_TOGETHER("living_together"),
        FACE_PERSON_ID("face_person_id"),
        FACE_ESTIMATED_AGE_RANGE("face_estimated_agerange"),
        FACE_ESTIMATED_GENDER("face_estimated_gender");
        
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

    public Person(String str, Long l, Long l8, String str2, RelationShip relationShip, Bundle bundle, String str3, int i2, String str4, Long l10, FaceImageData faceImageData2) {
        j.e(str, "id");
        j.e(str2, "name");
        j.e(relationShip, "relationship");
        j.e(bundle, "properties");
        this.id = str;
        this.contactId = l;
        this.faceGroupID = l8;
        this.name = str2;
        this.relationship = relationShip;
        this.properties = bundle;
        this.galleryPersonUuid = str3;
        this.faceId = i2;
        this.rawContactUuid = str4;
        this.rawContactId = l10;
        this.faceImageData = faceImageData2;
    }

    public static /* synthetic */ Person copy$default(Person person, String str, Long l, Long l8, String str2, RelationShip relationShip, Bundle bundle, String str3, int i2, String str4, Long l10, FaceImageData faceImageData2, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            str = person.id;
        }
        if ((i7 & 2) != 0) {
            l = person.contactId;
        }
        if ((i7 & 4) != 0) {
            l8 = person.faceGroupID;
        }
        if ((i7 & 8) != 0) {
            str2 = person.name;
        }
        if ((i7 & 16) != 0) {
            relationShip = person.relationship;
        }
        if ((i7 & 32) != 0) {
            bundle = person.properties;
        }
        if ((i7 & 64) != 0) {
            str3 = person.galleryPersonUuid;
        }
        if ((i7 & 128) != 0) {
            i2 = person.faceId;
        }
        if ((i7 & 256) != 0) {
            str4 = person.rawContactUuid;
        }
        if ((i7 & 512) != 0) {
            l10 = person.rawContactId;
        }
        if ((i7 & 1024) != 0) {
            faceImageData2 = person.faceImageData;
        }
        Long l11 = l10;
        FaceImageData faceImageData3 = faceImageData2;
        int i8 = i2;
        String str5 = str4;
        Bundle bundle2 = bundle;
        String str6 = str3;
        String str7 = str2;
        RelationShip relationShip2 = relationShip;
        return person.copy(str, l, l8, str7, relationShip2, bundle2, str6, i8, str5, l11, faceImageData3);
    }

    private final StringBuilder getPropertiesString() {
        StringBuilder sb2 = new StringBuilder("properties: [");
        sb2.append(10);
        Bundle bundle = this.properties;
        if (bundle != null) {
            Set<String> keySet = bundle.keySet();
            j.d(keySet, "keySet(...)");
            for (String str : keySet) {
                if (j.a(str, PropertiesKey.LIVING_TOGETHER.getKey())) {
                    StringBuilder i2 = a.i("key : ", str, ' ', sb2, "value: ");
                    i2.append(this.properties.getString(str));
                    sb2.append(i2.toString());
                    sb2.append(10);
                } else if (j.a(str, PropertiesKey.FACE_PERSON_ID.getKey())) {
                    StringBuilder i7 = a.i("key : ", str, ' ', sb2, "value: ");
                    i7.append(this.properties.getInt(str));
                    sb2.append(i7.toString());
                    sb2.append(10);
                } else if (j.a(str, PropertiesKey.FACE_ESTIMATED_AGE_RANGE.getKey())) {
                    StringBuilder i8 = a.i("key : ", str, ' ', sb2, "value: ");
                    i8.append(this.properties.getString(str));
                    sb2.append(i8.toString());
                    sb2.append(10);
                } else if (j.a(str, PropertiesKey.FACE_ESTIMATED_GENDER.getKey())) {
                    StringBuilder i10 = a.i("key : ", str, ' ', sb2, "value: ");
                    i10.append(this.properties.getString(str));
                    sb2.append(i10.toString());
                    sb2.append(10);
                }
            }
        }
        sb2.append("]\n");
        return sb2;
    }

    public final String component1() {
        return this.id;
    }

    public final Long component10() {
        return this.rawContactId;
    }

    public final FaceImageData component11() {
        return this.faceImageData;
    }

    public final Long component2() {
        return this.contactId;
    }

    public final Long component3() {
        return this.faceGroupID;
    }

    public final String component4() {
        return this.name;
    }

    public final RelationShip component5() {
        return this.relationship;
    }

    public final Bundle component6() {
        return this.properties;
    }

    public final String component7() {
        return this.galleryPersonUuid;
    }

    public final int component8() {
        return this.faceId;
    }

    public final String component9() {
        return this.rawContactUuid;
    }

    public final Person copy(String str, Long l, Long l8, String str2, RelationShip relationShip, Bundle bundle, String str3, int i2, String str4, Long l10, FaceImageData faceImageData2) {
        j.e(str, "id");
        String str5 = str2;
        j.e(str5, "name");
        RelationShip relationShip2 = relationShip;
        j.e(relationShip2, "relationship");
        Bundle bundle2 = bundle;
        j.e(bundle2, "properties");
        return new Person(str, l, l8, str5, relationShip2, bundle2, str3, i2, str4, l10, faceImageData2);
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
        if (j.a(this.id, person.id) && j.a(this.contactId, person.contactId) && j.a(this.faceGroupID, person.faceGroupID) && j.a(this.name, person.name) && this.relationship == person.relationship && j.a(this.properties, person.properties) && j.a(this.galleryPersonUuid, person.galleryPersonUuid) && this.faceId == person.faceId && j.a(this.rawContactUuid, person.rawContactUuid) && j.a(this.rawContactId, person.rawContactId) && j.a(this.faceImageData, person.faceImageData)) {
            return true;
        }
        return false;
    }

    public final Long getContactId() {
        return this.contactId;
    }

    public final Long getFaceGroupID() {
        return this.faceGroupID;
    }

    public final int getFaceId() {
        return this.faceId;
    }

    public final FaceImageData getFaceImageData() {
        return this.faceImageData;
    }

    public final String getGalleryPersonUuid() {
        return this.galleryPersonUuid;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final Bundle getProperties() {
        return this.properties;
    }

    public final Long getRawContactId() {
        return this.rawContactId;
    }

    public final String getRawContactUuid() {
        return this.rawContactUuid;
    }

    public final RelationShip getRelationship() {
        return this.relationship;
    }

    public int hashCode() {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        int hashCode = this.id.hashCode() * 31;
        Long l = this.contactId;
        int i12 = 0;
        if (l == null) {
            i2 = 0;
        } else {
            i2 = l.hashCode();
        }
        int i13 = (hashCode + i2) * 31;
        Long l8 = this.faceGroupID;
        if (l8 == null) {
            i7 = 0;
        } else {
            i7 = l8.hashCode();
        }
        int hashCode2 = (this.properties.hashCode() + ((this.relationship.hashCode() + C0212a.d((i13 + i7) * 31, 31, this.name)) * 31)) * 31;
        String str = this.galleryPersonUuid;
        if (str == null) {
            i8 = 0;
        } else {
            i8 = str.hashCode();
        }
        int b = C0212a.b(this.faceId, (hashCode2 + i8) * 31, 31);
        String str2 = this.rawContactUuid;
        if (str2 == null) {
            i10 = 0;
        } else {
            i10 = str2.hashCode();
        }
        int i14 = (b + i10) * 31;
        Long l10 = this.rawContactId;
        if (l10 == null) {
            i11 = 0;
        } else {
            i11 = l10.hashCode();
        }
        int i15 = (i14 + i11) * 31;
        FaceImageData faceImageData2 = this.faceImageData;
        if (faceImageData2 != null) {
            i12 = faceImageData2.hashCode();
        }
        return i15 + i12;
    }

    public String toString() {
        return "name : " + this.name + "\ncontactId : " + this.contactId + "\nfaceGroupID : " + this.faceGroupID + "\nrelationship : " + this.relationship + 10 + getPropertiesString() + "galleryPersonUuid :" + this.galleryPersonUuid + "\nfaceId :" + this.faceId + "\nrawContactUuid :" + this.rawContactUuid + "\nrawContactId :" + this.rawContactId + "\nfaceImageData : " + this.faceImageData + "\n\n";
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        Long l = this.contactId;
        if (l == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l);
        }
        Long l8 = this.faceGroupID;
        if (l8 == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l8);
        }
        parcel.writeString(this.name);
        this.relationship.writeToParcel(parcel, i2);
        parcel.writeBundle(this.properties);
        parcel.writeString(this.galleryPersonUuid);
        parcel.writeInt(this.faceId);
        parcel.writeString(this.rawContactUuid);
        Long l10 = this.rawContactId;
        if (l10 == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l10);
        }
        FaceImageData faceImageData2 = this.faceImageData;
        if (faceImageData2 == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        faceImageData2.writeToParcel(parcel, i2);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Person(java.lang.String r24, java.lang.Long r25, java.lang.Long r26, java.lang.String r27, com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip r28, android.os.Bundle r29, java.lang.String r30, int r31, java.lang.String r32, java.lang.Long r33, com.samsung.android.sdk.moneta.basicdomain.entity.FaceImageData r34, int r35, kotlin.jvm.internal.e r36) {
        /*
            r23 = this;
            r0 = r35
            r1 = r0 & 8
            java.lang.String r2 = ""
            if (r1 == 0) goto L_0x000a
            r7 = r2
            goto L_0x000c
        L_0x000a:
            r7 = r27
        L_0x000c:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0012
            r10 = r2
            goto L_0x0014
        L_0x0012:
            r10 = r30
        L_0x0014:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x001b
            r1 = 0
            r11 = r1
            goto L_0x001d
        L_0x001b:
            r11 = r31
        L_0x001d:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L_0x0023
            r12 = r2
            goto L_0x0025
        L_0x0023:
            r12 = r32
        L_0x0025:
            r1 = r0 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto L_0x0031
            r1 = 0
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            r13 = r1
            goto L_0x0033
        L_0x0031:
            r13 = r33
        L_0x0033:
            r0 = r0 & 1024(0x400, float:1.435E-42)
            if (r0 == 0) goto L_0x0058
            com.samsung.android.sdk.moneta.basicdomain.entity.FaceImageData r14 = new com.samsung.android.sdk.moneta.basicdomain.entity.FaceImageData
            r21 = 63
            r22 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r14.<init>(r15, r16, r17, r18, r19, r20, r21, r22)
        L_0x004b:
            r3 = r23
            r4 = r24
            r5 = r25
            r6 = r26
            r8 = r28
            r9 = r29
            goto L_0x005b
        L_0x0058:
            r14 = r34
            goto L_0x004b
        L_0x005b:
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.basicdomain.entity.Person.<init>(java.lang.String, java.lang.Long, java.lang.Long, java.lang.String, com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip, android.os.Bundle, java.lang.String, int, java.lang.String, java.lang.Long, com.samsung.android.sdk.moneta.basicdomain.entity.FaceImageData, int, kotlin.jvm.internal.e):void");
    }
}
