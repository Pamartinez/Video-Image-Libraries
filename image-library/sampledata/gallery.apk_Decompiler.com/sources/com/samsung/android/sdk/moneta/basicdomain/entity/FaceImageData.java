package com.samsung.android.sdk.moneta.basicdomain.entity;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\b\b\u0018\u00002\u00020\u0001BO\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0005¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\u0005¢\u0006\u0004\b\u0012\u0010\u0013J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0015J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0018J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u0018J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u0018JX\u0010\u001c\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001e\u0010\u0015J\u0010\u0010\u001f\u001a\u00020\u0005HÖ\u0001¢\u0006\u0004\b\u001f\u0010\u0013J\u001a\u0010#\u001a\u00020\"2\b\u0010!\u001a\u0004\u0018\u00010 HÖ\u0003¢\u0006\u0004\b#\u0010$R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010%\u001a\u0004\b&\u0010\u0015R\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010%\u001a\u0004\b'\u0010\u0015R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010(\u001a\u0004\b)\u0010\u0018R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010(\u001a\u0004\b*\u0010\u0018R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\b\u0010(\u001a\u0004\b+\u0010\u0018R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\t\u0010(\u001a\u0004\b,\u0010\u0018¨\u0006-"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/entity/FaceImageData;", "Landroid/os/Parcelable;", "", "data", "pos_ratio", "", "pos_left", "pos_right", "pos_top", "pos_bottom", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "component3", "()Ljava/lang/Integer;", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/samsung/android/sdk/moneta/basicdomain/entity/FaceImageData;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getData", "getPos_ratio", "Ljava/lang/Integer;", "getPos_left", "getPos_right", "getPos_top", "getPos_bottom", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FaceImageData implements Parcelable {
    public static final Parcelable.Creator<FaceImageData> CREATOR = new Creator();
    private final String data;
    private final Integer pos_bottom;
    private final Integer pos_left;
    private final String pos_ratio;
    private final Integer pos_right;
    private final Integer pos_top;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<FaceImageData> {
        public final FaceImageData createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            Integer num = null;
            Integer valueOf = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            Integer valueOf2 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            Integer valueOf3 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            if (parcel.readInt() != 0) {
                num = Integer.valueOf(parcel.readInt());
            }
            return new FaceImageData(readString, readString2, valueOf, valueOf2, valueOf3, num);
        }

        public final FaceImageData[] newArray(int i2) {
            return new FaceImageData[i2];
        }
    }

    public FaceImageData() {
        this((String) null, (String) null, (Integer) null, (Integer) null, (Integer) null, (Integer) null, 63, (e) null);
    }

    public static /* synthetic */ FaceImageData copy$default(FaceImageData faceImageData, String str, String str2, Integer num, Integer num2, Integer num3, Integer num4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = faceImageData.data;
        }
        if ((i2 & 2) != 0) {
            str2 = faceImageData.pos_ratio;
        }
        if ((i2 & 4) != 0) {
            num = faceImageData.pos_left;
        }
        if ((i2 & 8) != 0) {
            num2 = faceImageData.pos_right;
        }
        if ((i2 & 16) != 0) {
            num3 = faceImageData.pos_top;
        }
        if ((i2 & 32) != 0) {
            num4 = faceImageData.pos_bottom;
        }
        Integer num5 = num3;
        Integer num6 = num4;
        Integer num7 = num2;
        String str3 = str2;
        return faceImageData.copy(str, str3, num, num7, num5, num6);
    }

    public final String component1() {
        return this.data;
    }

    public final String component2() {
        return this.pos_ratio;
    }

    public final Integer component3() {
        return this.pos_left;
    }

    public final Integer component4() {
        return this.pos_right;
    }

    public final Integer component5() {
        return this.pos_top;
    }

    public final Integer component6() {
        return this.pos_bottom;
    }

    public final FaceImageData copy(String str, String str2, Integer num, Integer num2, Integer num3, Integer num4) {
        return new FaceImageData(str, str2, num, num2, num3, num4);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FaceImageData)) {
            return false;
        }
        FaceImageData faceImageData = (FaceImageData) obj;
        if (j.a(this.data, faceImageData.data) && j.a(this.pos_ratio, faceImageData.pos_ratio) && j.a(this.pos_left, faceImageData.pos_left) && j.a(this.pos_right, faceImageData.pos_right) && j.a(this.pos_top, faceImageData.pos_top) && j.a(this.pos_bottom, faceImageData.pos_bottom)) {
            return true;
        }
        return false;
    }

    public final String getData() {
        return this.data;
    }

    public final Integer getPos_bottom() {
        return this.pos_bottom;
    }

    public final Integer getPos_left() {
        return this.pos_left;
    }

    public final String getPos_ratio() {
        return this.pos_ratio;
    }

    public final Integer getPos_right() {
        return this.pos_right;
    }

    public final Integer getPos_top() {
        return this.pos_top;
    }

    public int hashCode() {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        String str = this.data;
        int i12 = 0;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        int i13 = i2 * 31;
        String str2 = this.pos_ratio;
        if (str2 == null) {
            i7 = 0;
        } else {
            i7 = str2.hashCode();
        }
        int i14 = (i13 + i7) * 31;
        Integer num = this.pos_left;
        if (num == null) {
            i8 = 0;
        } else {
            i8 = num.hashCode();
        }
        int i15 = (i14 + i8) * 31;
        Integer num2 = this.pos_right;
        if (num2 == null) {
            i10 = 0;
        } else {
            i10 = num2.hashCode();
        }
        int i16 = (i15 + i10) * 31;
        Integer num3 = this.pos_top;
        if (num3 == null) {
            i11 = 0;
        } else {
            i11 = num3.hashCode();
        }
        int i17 = (i16 + i11) * 31;
        Integer num4 = this.pos_bottom;
        if (num4 != null) {
            i12 = num4.hashCode();
        }
        return i17 + i12;
    }

    public String toString() {
        return "FaceImageData(data=" + this.data + ", pos_ratio=" + this.pos_ratio + ", pos_left=" + this.pos_left + ", pos_right=" + this.pos_right + ", pos_top=" + this.pos_top + ", pos_bottom=" + this.pos_bottom + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.data);
        parcel.writeString(this.pos_ratio);
        Integer num = this.pos_left;
        if (num == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(num.intValue());
        }
        Integer num2 = this.pos_right;
        if (num2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(num2.intValue());
        }
        Integer num3 = this.pos_top;
        if (num3 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(num3.intValue());
        }
        Integer num4 = this.pos_bottom;
        if (num4 == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcel.writeInt(num4.intValue());
    }

    public FaceImageData(String str, String str2, Integer num, Integer num2, Integer num3, Integer num4) {
        this.data = str;
        this.pos_ratio = str2;
        this.pos_left = num;
        this.pos_right = num2;
        this.pos_top = num3;
        this.pos_bottom = num4;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FaceImageData(String str, String str2, Integer num, Integer num2, Integer num3, Integer num4, int i2, e eVar) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? 0 : num, (i2 & 8) != 0 ? 0 : num2, (i2 & 16) != 0 ? 0 : num3, (i2 & 32) != 0 ? 0 : num4);
    }
}
