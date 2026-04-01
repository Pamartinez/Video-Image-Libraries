package com.samsung.android.scs.ai.sdkcommon.asr;

import N2.j;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ServerType implements Parcelable {
    public static final Parcelable.Creator<ServerType> CREATOR = new Parcelable.Creator<ServerType>() {
        public ServerType createFromParcel(Parcel parcel) {
            return new ServerType(parcel);
        }

        public ServerType[] newArray(int i2) {
            return new ServerType[i2];
        }
    };
    private final ServerFeature feature;
    private final boolean isDefault;
    private final String typeName;

    public ServerType(ServerFeature serverFeature, String str) {
        this(serverFeature, str, false);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        ServerType serverType = (ServerType) obj;
        if (this.feature != serverType.feature || !this.typeName.equals(serverType.typeName)) {
            return false;
        }
        return true;
    }

    public ServerFeature getFeature() {
        return this.feature;
    }

    public String getName() {
        return this.typeName;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.feature, this.typeName});
    }

    public boolean isDefault() {
        return this.isDefault;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("ServerType{name='");
        sb2.append(this.typeName);
        sb2.append("', feature='");
        sb2.append(this.feature);
        sb2.append("', isDefault='");
        return j.h(sb2, this.isDefault, "'}");
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeSerializable(this.feature);
        parcel.writeString(this.typeName);
        parcel.writeInt(this.isDefault ? 1 : 0);
    }

    public ServerType(ServerFeature serverFeature, String str, boolean z) {
        this.feature = serverFeature;
        this.typeName = str;
        this.isDefault = z;
    }

    public ServerType(Parcel parcel) {
        this.feature = (ServerFeature) parcel.readSerializable();
        this.typeName = parcel.readString();
        this.isDefault = parcel.readInt() != 1 ? false : true;
    }
}
