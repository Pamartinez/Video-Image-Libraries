package com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.mobileservice.profile.Privacy;
import i.C0212a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\f¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J4\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001b\u0010\u0014J\u0010\u0010\u001c\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\u001c\u0010\u0012J\u001a\u0010 \u001a\u00020\u001f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dHÖ\u0003¢\u0006\u0004\b \u0010!R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\"\u001a\u0004\b#\u0010\u0014R\u001d\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010$\u001a\u0004\b%\u0010\u0016R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010&\u001a\u0004\b'\u0010\u0018¨\u0006("}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/entity/wrapper/v1/MyProfileWrapper;", "Landroid/os/Parcelable;", "", "name", "", "phoneNumbers", "Landroid/os/Bundle;", "properties", "<init>", "(Ljava/lang/String;Ljava/util/List;Landroid/os/Bundle;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "()Ljava/util/List;", "component3", "()Landroid/os/Bundle;", "copy", "(Ljava/lang/String;Ljava/util/List;Landroid/os/Bundle;)Lcom/samsung/android/sdk/moneta/basicdomain/entity/wrapper/v1/MyProfileWrapper;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getName", "Ljava/util/List;", "getPhoneNumbers", "Landroid/os/Bundle;", "getProperties", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MyProfileWrapper implements Parcelable {
    public static final Parcelable.Creator<MyProfileWrapper> CREATOR = new Creator();
    private final String name;
    private final List<String> phoneNumbers;
    private final Bundle properties;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<MyProfileWrapper> {
        public final MyProfileWrapper createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new MyProfileWrapper(parcel.readString(), parcel.createStringArrayList(), parcel.readBundle(MyProfileWrapper.class.getClassLoader()));
        }

        public final MyProfileWrapper[] newArray(int i2) {
            return new MyProfileWrapper[i2];
        }
    }

    public MyProfileWrapper(String str, List<String> list, Bundle bundle) {
        j.e(str, "name");
        j.e(list, Privacy.KEY_PHONE_NUMBERS);
        j.e(bundle, "properties");
        this.name = str;
        this.phoneNumbers = list;
        this.properties = bundle;
    }

    public static /* synthetic */ MyProfileWrapper copy$default(MyProfileWrapper myProfileWrapper, String str, List<String> list, Bundle bundle, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = myProfileWrapper.name;
        }
        if ((i2 & 2) != 0) {
            list = myProfileWrapper.phoneNumbers;
        }
        if ((i2 & 4) != 0) {
            bundle = myProfileWrapper.properties;
        }
        return myProfileWrapper.copy(str, list, bundle);
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

    public final MyProfileWrapper copy(String str, List<String> list, Bundle bundle) {
        j.e(str, "name");
        j.e(list, Privacy.KEY_PHONE_NUMBERS);
        j.e(bundle, "properties");
        return new MyProfileWrapper(str, list, bundle);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MyProfileWrapper)) {
            return false;
        }
        MyProfileWrapper myProfileWrapper = (MyProfileWrapper) obj;
        if (j.a(this.name, myProfileWrapper.name) && j.a(this.phoneNumbers, myProfileWrapper.phoneNumbers) && j.a(this.properties, myProfileWrapper.properties)) {
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
        return "MyProfileWrapper(name=" + this.name + ", phoneNumbers=" + this.phoneNumbers + ", properties=" + this.properties + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.name);
        parcel.writeStringList(this.phoneNumbers);
        parcel.writeBundle(this.properties);
    }
}
