package com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J$\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0017\u0010\u0012J\u0010\u0010\u0018\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0010J\u001a\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019HÖ\u0003¢\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010\u0012R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010 \u001a\u0004\b!\u0010\u0014¨\u0006\""}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/entity/wrapper/v1/PersonWrapper;", "Landroid/os/Parcelable;", "", "id", "Landroid/os/Bundle;", "properties", "<init>", "(Ljava/lang/String;Landroid/os/Bundle;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "()Landroid/os/Bundle;", "copy", "(Ljava/lang/String;Landroid/os/Bundle;)Lcom/samsung/android/sdk/moneta/basicdomain/entity/wrapper/v1/PersonWrapper;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "Landroid/os/Bundle;", "getProperties", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PersonWrapper implements Parcelable {
    public static final Parcelable.Creator<PersonWrapper> CREATOR = new Creator();
    private final String id;
    private final Bundle properties;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<PersonWrapper> {
        public final PersonWrapper createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new PersonWrapper(parcel.readString(), parcel.readBundle(PersonWrapper.class.getClassLoader()));
        }

        public final PersonWrapper[] newArray(int i2) {
            return new PersonWrapper[i2];
        }
    }

    public PersonWrapper(String str, Bundle bundle) {
        j.e(str, "id");
        j.e(bundle, "properties");
        this.id = str;
        this.properties = bundle;
    }

    public static /* synthetic */ PersonWrapper copy$default(PersonWrapper personWrapper, String str, Bundle bundle, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = personWrapper.id;
        }
        if ((i2 & 2) != 0) {
            bundle = personWrapper.properties;
        }
        return personWrapper.copy(str, bundle);
    }

    public final String component1() {
        return this.id;
    }

    public final Bundle component2() {
        return this.properties;
    }

    public final PersonWrapper copy(String str, Bundle bundle) {
        j.e(str, "id");
        j.e(bundle, "properties");
        return new PersonWrapper(str, bundle);
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
        if (j.a(this.id, personWrapper.id) && j.a(this.properties, personWrapper.properties)) {
            return true;
        }
        return false;
    }

    public final String getId() {
        return this.id;
    }

    public final Bundle getProperties() {
        return this.properties;
    }

    public int hashCode() {
        return this.properties.hashCode() + (this.id.hashCode() * 31);
    }

    public String toString() {
        return "PersonWrapper(id=" + this.id + ", properties=" + this.properties + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeBundle(this.properties);
    }
}
