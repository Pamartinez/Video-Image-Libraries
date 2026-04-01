package com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0015J.\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u0013J\u0010\u0010\u001a\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\u001a\u0010\u0011J\u001a\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bHÖ\u0003¢\u0006\u0004\b\u001e\u0010\u001fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010 \u001a\u0004\b!\u0010\u0013R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\"\u001a\u0004\b#\u0010\u0015R\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\"\u001a\u0004\b$\u0010\u0015¨\u0006%"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/wrapper/v1/PersonWrapper;", "Landroid/os/Parcelable;", "", "id", "Landroid/os/Bundle;", "preferences", "properties", "<init>", "(Ljava/lang/String;Landroid/os/Bundle;Landroid/os/Bundle;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "()Landroid/os/Bundle;", "component3", "copy", "(Ljava/lang/String;Landroid/os/Bundle;Landroid/os/Bundle;)Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/wrapper/v1/PersonWrapper;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "Landroid/os/Bundle;", "getPreferences", "getProperties", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PersonWrapper implements Parcelable {
    public static final Parcelable.Creator<PersonWrapper> CREATOR = new Creator();
    private final /* synthetic */ String id;
    private final /* synthetic */ Bundle preferences;
    private final /* synthetic */ Bundle properties;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<PersonWrapper> {
        public final PersonWrapper createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            Class<PersonWrapper> cls = PersonWrapper.class;
            return new PersonWrapper(parcel.readString(), parcel.readBundle(cls.getClassLoader()), parcel.readBundle(cls.getClassLoader()));
        }

        public final PersonWrapper[] newArray(int i2) {
            return new PersonWrapper[i2];
        }
    }

    public PersonWrapper(String str, Bundle bundle, Bundle bundle2) {
        j.e(str, "id");
        j.e(bundle, "preferences");
        j.e(bundle2, "properties");
        this.id = str;
        this.preferences = bundle;
        this.properties = bundle2;
    }

    public static /* synthetic */ PersonWrapper copy$default(PersonWrapper personWrapper, String str, Bundle bundle, Bundle bundle2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = personWrapper.id;
        }
        if ((i2 & 2) != 0) {
            bundle = personWrapper.preferences;
        }
        if ((i2 & 4) != 0) {
            bundle2 = personWrapper.properties;
        }
        return personWrapper.copy(str, bundle, bundle2);
    }

    public final String component1() {
        return this.id;
    }

    public final Bundle component2() {
        return this.preferences;
    }

    public final Bundle component3() {
        return this.properties;
    }

    public final PersonWrapper copy(String str, Bundle bundle, Bundle bundle2) {
        j.e(str, "id");
        j.e(bundle, "preferences");
        j.e(bundle2, "properties");
        return new PersonWrapper(str, bundle, bundle2);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PersonWrapper)) {
            return false;
        }
        PersonWrapper personWrapper = (PersonWrapper) obj;
        if (j.a(this.id, personWrapper.id) && j.a(this.preferences, personWrapper.preferences) && j.a(this.properties, personWrapper.properties)) {
            return true;
        }
        return false;
    }

    public final String getId() {
        return this.id;
    }

    public final Bundle getPreferences() {
        return this.preferences;
    }

    public final Bundle getProperties() {
        return this.properties;
    }

    public int hashCode() {
        int hashCode = this.preferences.hashCode();
        return this.properties.hashCode() + ((hashCode + (this.id.hashCode() * 31)) * 31);
    }

    public String toString() {
        return "PersonWrapper(id=" + this.id + ", preferences=" + this.preferences + ", properties=" + this.properties + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeBundle(this.preferences);
        parcel.writeBundle(this.properties);
    }
}
