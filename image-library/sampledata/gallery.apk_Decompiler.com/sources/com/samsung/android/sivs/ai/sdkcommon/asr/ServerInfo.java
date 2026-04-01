package com.samsung.android.sivs.ai.sdkcommon.asr;

import android.os.Parcel;
import android.os.Parcelable;
import c0.C0086a;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ServerInfo extends ServerType {
    public static final Parcelable.Creator<ServerInfo> CREATOR = new Parcelable.Creator<ServerInfo>() {
        public ServerInfo createFromParcel(Parcel parcel) {
            return new ServerInfo(parcel);
        }

        public ServerInfo[] newArray(int i2) {
            return new ServerInfo[i2];
        }
    };
    private final String endpoint;

    public ServerInfo(ServerFeature serverFeature, String str, String str2) {
        super(serverFeature, str);
        this.endpoint = str2;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && super.equals(obj)) {
            return this.endpoint.equals(((ServerInfo) obj).endpoint);
        }
        return false;
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(super.hashCode()), this.endpoint});
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("ServerInfo{endpoint='");
        sb2.append(this.endpoint);
        sb2.append("', ");
        return C0086a.m(sb2, super.toString(), '}');
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeString(this.endpoint);
    }

    public ServerInfo(ServerFeature serverFeature, String str, String str2, boolean z) {
        super(serverFeature, str, z);
        this.endpoint = str2;
    }

    public ServerInfo(Parcel parcel) {
        super(parcel);
        this.endpoint = parcel.readString();
    }
}
