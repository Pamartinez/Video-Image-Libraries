package com.samsung.android.sdk.moneta.basicdomain.entity;

import A.a;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.mobileservice.profile.Privacy;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import i.C0212a;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import te.C1295a;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\b\u0018\u00002\u00020\u0001:\u0001,B%\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\f\u001a\u00060\nj\u0002`\u000bH\u0003¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0002H\u0017¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0012¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u000fJ\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ4\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b \u0010\u0018J\u001a\u0010$\u001a\u00020#2\b\u0010\"\u001a\u0004\u0018\u00010!HÖ\u0003¢\u0006\u0004\b$\u0010%R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010&\u001a\u0004\b'\u0010\u000fR\u001d\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010(\u001a\u0004\b)\u0010\u001bR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010*\u001a\u0004\b+\u0010\u001d¨\u0006-"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/entity/MyProfile;", "Landroid/os/Parcelable;", "", "name", "", "phoneNumbers", "Landroid/os/Bundle;", "properties", "<init>", "(Ljava/lang/String;Ljava/util/List;Landroid/os/Bundle;)V", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getPropertiesString", "()Ljava/lang/StringBuilder;", "toString", "()Ljava/lang/String;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "component2", "()Ljava/util/List;", "component3", "()Landroid/os/Bundle;", "copy", "(Ljava/lang/String;Ljava/util/List;Landroid/os/Bundle;)Lcom/samsung/android/sdk/moneta/basicdomain/entity/MyProfile;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getName", "Ljava/util/List;", "getPhoneNumbers", "Landroid/os/Bundle;", "getProperties", "PropertiesKey", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MyProfile implements Parcelable {
    public static final Parcelable.Creator<MyProfile> CREATOR = new Creator();
    private final String name;
    private final List<String> phoneNumbers;
    private final Bundle properties;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<MyProfile> {
        public final MyProfile createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new MyProfile(parcel.readString(), parcel.createStringArrayList(), parcel.readBundle(MyProfile.class.getClassLoader()));
        }

        public final MyProfile[] newArray(int i2) {
            return new MyProfile[i2];
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/entity/MyProfile$PropertiesKey;", "", "key", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getKey", "()Ljava/lang/String;", "BIRTHDAY", "AGE_GROUP", "PHOTO_URL", "DRIVING", "GIVEN_NAME", "FAMILY_NAME", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum PropertiesKey {
        BIRTHDAY("birthday"),
        AGE_GROUP("age_group"),
        PHOTO_URL("photo_url"),
        DRIVING("driving"),
        GIVEN_NAME("given_name"),
        FAMILY_NAME("family_name");
        
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

    public MyProfile(String str, List<String> list, Bundle bundle) {
        j.e(str, "name");
        j.e(list, Privacy.KEY_PHONE_NUMBERS);
        j.e(bundle, "properties");
        this.name = str;
        this.phoneNumbers = list;
        this.properties = bundle;
    }

    public static /* synthetic */ MyProfile copy$default(MyProfile myProfile, String str, List<String> list, Bundle bundle, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = myProfile.name;
        }
        if ((i2 & 2) != 0) {
            list = myProfile.phoneNumbers;
        }
        if ((i2 & 4) != 0) {
            bundle = myProfile.properties;
        }
        return myProfile.copy(str, list, bundle);
    }

    private final StringBuilder getPropertiesString() {
        StringBuilder sb2 = new StringBuilder("properties: [");
        sb2.append(10);
        Set<String> keySet = this.properties.keySet();
        j.d(keySet, "keySet(...)");
        for (String str : keySet) {
            if (j.a(str, PropertiesKey.BIRTHDAY.getKey())) {
                StringBuilder i2 = a.i("key : ", str, ' ', sb2, "value: ");
                i2.append(this.properties.getString(str));
                sb2.append(i2.toString());
                sb2.append(10);
            } else if (j.a(str, PropertiesKey.AGE_GROUP.getKey())) {
                StringBuilder i7 = a.i("key : ", str, ' ', sb2, "value: ");
                i7.append(this.properties.getParcelable(str, AgeGroup.class));
                sb2.append(i7.toString());
                sb2.append(10);
            } else if (j.a(str, PropertiesKey.PHOTO_URL.getKey())) {
                StringBuilder i8 = a.i("key : ", str, ' ', sb2, "value: ");
                i8.append(this.properties.getString(str));
                sb2.append(i8.toString());
                sb2.append(10);
            } else if (j.a(str, PropertiesKey.DRIVING.getKey())) {
                StringBuilder i10 = a.i("key : ", str, ' ', sb2, "value: ");
                i10.append(this.properties.getFloat(str));
                sb2.append(i10.toString());
                sb2.append(10);
            } else if (j.a(str, PropertiesKey.GIVEN_NAME.getKey())) {
                StringBuilder i11 = a.i("key : ", str, ' ', sb2, "value: ");
                i11.append(this.properties.getString(str));
                sb2.append(i11.toString());
                sb2.append(10);
            } else if (j.a(str, PropertiesKey.FAMILY_NAME.getKey())) {
                StringBuilder i12 = a.i("key : ", str, ' ', sb2, "value: ");
                i12.append(this.properties.getString(str));
                sb2.append(i12.toString());
                sb2.append(10);
            }
        }
        sb2.append("]\n");
        return sb2;
    }

    public final String component1() {
        return this.name;
    }

    public final List<String> component2() {
        return this.phoneNumbers;
    }

    public final Bundle component3() {
        return this.properties;
    }

    public final MyProfile copy(String str, List<String> list, Bundle bundle) {
        j.e(str, "name");
        j.e(list, Privacy.KEY_PHONE_NUMBERS);
        j.e(bundle, "properties");
        return new MyProfile(str, list, bundle);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MyProfile)) {
            return false;
        }
        MyProfile myProfile = (MyProfile) obj;
        if (j.a(this.name, myProfile.name) && j.a(this.phoneNumbers, myProfile.phoneNumbers) && j.a(this.properties, myProfile.properties)) {
            return true;
        }
        return false;
    }

    public final String getName() {
        return this.name;
    }

    public final List<String> getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public final Bundle getProperties() {
        return this.properties;
    }

    public int hashCode() {
        return this.properties.hashCode() + C0212a.f(this.phoneNumbers, this.name.hashCode() * 31, 31);
    }

    public String toString() {
        String sb2 = getPropertiesString().toString();
        j.d(sb2, "toString(...)");
        return sb2;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.name);
        parcel.writeStringList(this.phoneNumbers);
        parcel.writeBundle(this.properties);
    }
}
