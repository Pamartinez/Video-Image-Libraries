package androidx.media3.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import i.C0212a;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DrmInitData implements Comparator<SchemeData>, Parcelable {
    public static final Parcelable.Creator<DrmInitData> CREATOR = new Parcelable.Creator<DrmInitData>() {
        public DrmInitData createFromParcel(Parcel parcel) {
            return new DrmInitData(parcel);
        }

        public DrmInitData[] newArray(int i2) {
            return new DrmInitData[i2];
        }
    };
    private int hashCode;
    public final int schemeDataCount;
    private final SchemeData[] schemeDatas;
    public final String schemeType;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SchemeData implements Parcelable {
        public static final Parcelable.Creator<SchemeData> CREATOR = new Parcelable.Creator<SchemeData>() {
            public SchemeData createFromParcel(Parcel parcel) {
                return new SchemeData(parcel);
            }

            public SchemeData[] newArray(int i2) {
                return new SchemeData[i2];
            }
        };
        public final byte[] data;
        private int hashCode;
        public final String licenseServerUrl;
        public final String mimeType;
        public final UUID uuid;

        public SchemeData(UUID uuid2, String str, byte[] bArr) {
            this(uuid2, (String) null, str, bArr);
        }

        public SchemeData copyWithData(byte[] bArr) {
            return new SchemeData(this.uuid, this.licenseServerUrl, this.mimeType, bArr);
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof SchemeData)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            SchemeData schemeData = (SchemeData) obj;
            if (!Objects.equals(this.licenseServerUrl, schemeData.licenseServerUrl) || !Objects.equals(this.mimeType, schemeData.mimeType) || !Objects.equals(this.uuid, schemeData.uuid) || !Arrays.equals(this.data, schemeData.data)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i2;
            if (this.hashCode == 0) {
                int hashCode2 = this.uuid.hashCode() * 31;
                String str = this.licenseServerUrl;
                if (str == null) {
                    i2 = 0;
                } else {
                    i2 = str.hashCode();
                }
                this.hashCode = Arrays.hashCode(this.data) + C0212a.d((hashCode2 + i2) * 31, 31, this.mimeType);
            }
            return this.hashCode;
        }

        public boolean matches(UUID uuid2) {
            if (C.UUID_NIL.equals(this.uuid) || uuid2.equals(this.uuid)) {
                return true;
            }
            return false;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeLong(this.uuid.getMostSignificantBits());
            parcel.writeLong(this.uuid.getLeastSignificantBits());
            parcel.writeString(this.licenseServerUrl);
            parcel.writeString(this.mimeType);
            parcel.writeByteArray(this.data);
        }

        public SchemeData(UUID uuid2, String str, String str2, byte[] bArr) {
            this.uuid = (UUID) Assertions.checkNotNull(uuid2);
            this.licenseServerUrl = str;
            this.mimeType = MimeTypes.normalizeMimeType((String) Assertions.checkNotNull(str2));
            this.data = bArr;
        }

        public SchemeData(Parcel parcel) {
            this.uuid = new UUID(parcel.readLong(), parcel.readLong());
            this.licenseServerUrl = parcel.readString();
            this.mimeType = (String) Util.castNonNull(parcel.readString());
            this.data = parcel.createByteArray();
        }
    }

    public DrmInitData(List<SchemeData> list) {
        this((String) null, false, (SchemeData[]) list.toArray(new SchemeData[0]));
    }

    public DrmInitData copyWithSchemeType(String str) {
        if (Objects.equals(this.schemeType, str)) {
            return this;
        }
        return new DrmInitData(str, false, this.schemeDatas);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && DrmInitData.class == obj.getClass()) {
            DrmInitData drmInitData = (DrmInitData) obj;
            if (!Objects.equals(this.schemeType, drmInitData.schemeType) || !Arrays.equals(this.schemeDatas, drmInitData.schemeDatas)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public SchemeData get(int i2) {
        return this.schemeDatas[i2];
    }

    public int hashCode() {
        int i2;
        if (this.hashCode == 0) {
            String str = this.schemeType;
            if (str == null) {
                i2 = 0;
            } else {
                i2 = str.hashCode();
            }
            this.hashCode = (i2 * 31) + Arrays.hashCode(this.schemeDatas);
        }
        return this.hashCode;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.schemeType);
        parcel.writeTypedArray(this.schemeDatas, 0);
    }

    public DrmInitData(SchemeData... schemeDataArr) {
        this((String) null, schemeDataArr);
    }

    public int compare(SchemeData schemeData, SchemeData schemeData2) {
        UUID uuid = C.UUID_NIL;
        if (uuid.equals(schemeData.uuid)) {
            return uuid.equals(schemeData2.uuid) ? 0 : 1;
        }
        return schemeData.uuid.compareTo(schemeData2.uuid);
    }

    public DrmInitData(String str, SchemeData... schemeDataArr) {
        this(str, true, schemeDataArr);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: androidx.media3.common.DrmInitData$SchemeData[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private DrmInitData(java.lang.String r1, boolean r2, androidx.media3.common.DrmInitData.SchemeData... r3) {
        /*
            r0 = this;
            r0.<init>()
            r0.schemeType = r1
            if (r2 == 0) goto L_0x000e
            java.lang.Object r1 = r3.clone()
            r3 = r1
            androidx.media3.common.DrmInitData$SchemeData[] r3 = (androidx.media3.common.DrmInitData.SchemeData[]) r3
        L_0x000e:
            r0.schemeDatas = r3
            int r1 = r3.length
            r0.schemeDataCount = r1
            java.util.Arrays.sort(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.common.DrmInitData.<init>(java.lang.String, boolean, androidx.media3.common.DrmInitData$SchemeData[]):void");
    }

    public DrmInitData(Parcel parcel) {
        this.schemeType = parcel.readString();
        SchemeData[] schemeDataArr = (SchemeData[]) Util.castNonNull((SchemeData[]) parcel.createTypedArray(SchemeData.CREATOR));
        this.schemeDatas = schemeDataArr;
        this.schemeDataCount = schemeDataArr.length;
    }
}
