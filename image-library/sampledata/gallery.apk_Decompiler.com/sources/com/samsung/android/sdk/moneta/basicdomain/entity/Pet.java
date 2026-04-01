package com.samsung.android.sdk.moneta.basicdomain.entity;

import A.a;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import te.C1295a;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\b\u0018\u00002\u00020\u0001:\u0001,B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\f\u001a\u00060\nj\u0002`\u000bH\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0012¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u000fJ\u0010\u0010\u001a\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ.\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b \u0010\u0018J\u001a\u0010$\u001a\u00020#2\b\u0010\"\u001a\u0004\u0018\u00010!HÖ\u0003¢\u0006\u0004\b$\u0010%R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010&\u001a\u0004\b'\u0010\u000fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010(\u001a\u0004\b)\u0010\u001bR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010*\u001a\u0004\b+\u0010\u001d¨\u0006-"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/entity/Pet;", "Landroid/os/Parcelable;", "", "name", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/PetType;", "petType", "Landroid/os/Bundle;", "properties", "<init>", "(Ljava/lang/String;Lcom/samsung/android/sdk/moneta/basicdomain/entity/PetType;Landroid/os/Bundle;)V", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getPropertiesString", "()Ljava/lang/StringBuilder;", "toString", "()Ljava/lang/String;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "component2", "()Lcom/samsung/android/sdk/moneta/basicdomain/entity/PetType;", "component3", "()Landroid/os/Bundle;", "copy", "(Ljava/lang/String;Lcom/samsung/android/sdk/moneta/basicdomain/entity/PetType;Landroid/os/Bundle;)Lcom/samsung/android/sdk/moneta/basicdomain/entity/Pet;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getName", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/PetType;", "getPetType", "Landroid/os/Bundle;", "getProperties", "PropertiesKey", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Pet implements Parcelable {
    public static final Parcelable.Creator<Pet> CREATOR = new Creator();
    private final String name;
    private final PetType petType;
    private final Bundle properties;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<Pet> {
        public final Pet createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new Pet(parcel.readString(), PetType.valueOf(parcel.readString()), parcel.readBundle(Pet.class.getClassLoader()));
        }

        public final Pet[] newArray(int i2) {
            return new Pet[i2];
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/entity/Pet$PropertiesKey;", "", "key", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getKey", "()Ljava/lang/String;", "SOURCE", "GROUP_IDS", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum PropertiesKey {
        SOURCE("source"),
        GROUP_IDS("group_ids");
        
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

    public Pet(String str, PetType petType2, Bundle bundle) {
        j.e(str, "name");
        j.e(petType2, "petType");
        j.e(bundle, "properties");
        this.name = str;
        this.petType = petType2;
        this.properties = bundle;
    }

    public static /* synthetic */ Pet copy$default(Pet pet, String str, PetType petType2, Bundle bundle, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = pet.name;
        }
        if ((i2 & 2) != 0) {
            petType2 = pet.petType;
        }
        if ((i2 & 4) != 0) {
            bundle = pet.properties;
        }
        return pet.copy(str, petType2, bundle);
    }

    private final StringBuilder getPropertiesString() {
        String str;
        StringBuilder sb2 = new StringBuilder("properties: [");
        sb2.append(10);
        Set<String> keySet = this.properties.keySet();
        j.d(keySet, "keySet(...)");
        for (String str2 : keySet) {
            if (j.a(str2, PropertiesKey.SOURCE.getKey())) {
                StringBuilder i2 = a.i("key : ", str2, ' ', sb2, "value: ");
                i2.append(this.properties.getString(str2));
                sb2.append(i2.toString());
                sb2.append(10);
            } else if (j.a(str2, PropertiesKey.GROUP_IDS.getKey())) {
                int[] intArray = this.properties.getIntArray(str2);
                StringBuilder i7 = a.i("key : ", str2, ' ', sb2, "value: ");
                if (intArray != null) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("[");
                    int i8 = 0;
                    for (int i10 : intArray) {
                        i8++;
                        if (i8 > 1) {
                            sb3.append(ArcCommonLog.TAG_COMMA);
                        }
                        sb3.append(String.valueOf(i10));
                    }
                    sb3.append("]");
                    str = sb3.toString();
                } else {
                    str = null;
                }
                i7.append(str);
                sb2.append(i7.toString());
                sb2.append(10);
            }
        }
        sb2.append("]\n");
        return sb2;
    }

    public final String component1() {
        return this.name;
    }

    public final PetType component2() {
        return this.petType;
    }

    public final Bundle component3() {
        return this.properties;
    }

    public final Pet copy(String str, PetType petType2, Bundle bundle) {
        j.e(str, "name");
        j.e(petType2, "petType");
        j.e(bundle, "properties");
        return new Pet(str, petType2, bundle);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pet)) {
            return false;
        }
        Pet pet = (Pet) obj;
        if (j.a(this.name, pet.name) && this.petType == pet.petType && j.a(this.properties, pet.properties)) {
            return true;
        }
        return false;
    }

    public final String getName() {
        return this.name;
    }

    public final PetType getPetType() {
        return this.petType;
    }

    public final Bundle getProperties() {
        return this.properties;
    }

    public int hashCode() {
        int hashCode = this.petType.hashCode();
        return this.properties.hashCode() + ((hashCode + (this.name.hashCode() * 31)) * 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("name: " + this.name + ", petType: " + this.petType + ", properties: " + getPropertiesString());
        sb2.append(10);
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return sb3;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.name);
        parcel.writeString(this.petType.name());
        parcel.writeBundle(this.properties);
    }
}
