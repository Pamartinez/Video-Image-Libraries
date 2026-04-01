package com.samsung.android.app.sdk.deepsky.contract.suggestion.view;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\b\b\u0018\u0000 >2\u00020\u0001:\u0001>Ba\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u000e\u0010\u000fB\u0011\b\u0016\u0012\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u000e\u0010\u0012J\u001f\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0018J\u0010\u0010\u001a\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001fJ\u0012\u0010 \u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b \u0010\u001fJ\u0012\u0010!\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b!\u0010\u001fJ\u0012\u0010\"\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\"\u0010\u001fJ\u0012\u0010#\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b#\u0010$Jl\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0004\b%\u0010&J\u0010\u0010'\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b'\u0010\u001bJ\u0010\u0010(\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b(\u0010\u0018J\u001a\u0010,\u001a\u00020+2\b\u0010*\u001a\u0004\u0018\u00010)HÖ\u0003¢\u0006\u0004\b,\u0010-R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010.\u001a\u0004\b/\u0010\u0018R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u00100\u001a\u0004\b1\u0010\u001bR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u00102\u001a\u0004\b3\u0010\u001dR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\b\u00104\u001a\u0004\b5\u0010\u001fR$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\t\u00104\u001a\u0004\b6\u0010\u001f\"\u0004\b7\u00108R$\u0010\n\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\n\u00104\u001a\u0004\b9\u0010\u001f\"\u0004\b:\u00108R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u00104\u001a\u0004\b;\u0010\u001fR\u0019\u0010\r\u001a\u0004\u0018\u00010\f8\u0006¢\u0006\f\n\u0004\b\r\u0010<\u001a\u0004\b=\u0010$¨\u0006?"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SurfaceViewInfo;", "Landroid/os/Parcelable;", "", "revision", "", "surfaceHash", "Landroid/os/IBinder;", "hostToken", "displayId", "width", "height", "minHeight", "Landroid/os/Bundle;", "extras", "<init>", "(ILjava/lang/String;Landroid/os/IBinder;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Landroid/os/Bundle;)V", "Landroid/os/Parcel;", "parcel", "(Landroid/os/Parcel;)V", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "component2", "()Ljava/lang/String;", "component3", "()Landroid/os/IBinder;", "component4", "()Ljava/lang/Integer;", "component5", "component6", "component7", "component8", "()Landroid/os/Bundle;", "copy", "(ILjava/lang/String;Landroid/os/IBinder;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Landroid/os/Bundle;)Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SurfaceViewInfo;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getRevision", "Ljava/lang/String;", "getSurfaceHash", "Landroid/os/IBinder;", "getHostToken", "Ljava/lang/Integer;", "getDisplayId", "getWidth", "setWidth", "(Ljava/lang/Integer;)V", "getHeight", "setHeight", "getMinHeight", "Landroid/os/Bundle;", "getExtras", "CREATOR", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SurfaceViewInfo implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR((e) null);
    private final Integer displayId;
    private final Bundle extras;
    private Integer height;
    private final IBinder hostToken;
    private final Integer minHeight;
    private final int revision;
    private final String surfaceHash;
    private Integer width;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001d\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SurfaceViewInfo$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SurfaceViewInfo;", "<init>", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SurfaceViewInfo;", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CREATOR implements Parcelable.Creator<SurfaceViewInfo> {
        public /* synthetic */ CREATOR(e eVar) {
            this();
        }

        private CREATOR() {
        }

        public SurfaceViewInfo createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new SurfaceViewInfo(parcel);
        }

        public SurfaceViewInfo[] newArray(int i2) {
            return new SurfaceViewInfo[i2];
        }
    }

    public SurfaceViewInfo(int i2, String str, IBinder iBinder, Integer num, Integer num2, Integer num3, Integer num4, Bundle bundle) {
        j.e(str, "surfaceHash");
        this.revision = i2;
        this.surfaceHash = str;
        this.hostToken = iBinder;
        this.displayId = num;
        this.width = num2;
        this.height = num3;
        this.minHeight = num4;
        this.extras = bundle;
    }

    public static /* synthetic */ SurfaceViewInfo copy$default(SurfaceViewInfo surfaceViewInfo, int i2, String str, IBinder iBinder, Integer num, Integer num2, Integer num3, Integer num4, Bundle bundle, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            i2 = surfaceViewInfo.revision;
        }
        if ((i7 & 2) != 0) {
            str = surfaceViewInfo.surfaceHash;
        }
        if ((i7 & 4) != 0) {
            iBinder = surfaceViewInfo.hostToken;
        }
        if ((i7 & 8) != 0) {
            num = surfaceViewInfo.displayId;
        }
        if ((i7 & 16) != 0) {
            num2 = surfaceViewInfo.width;
        }
        if ((i7 & 32) != 0) {
            num3 = surfaceViewInfo.height;
        }
        if ((i7 & 64) != 0) {
            num4 = surfaceViewInfo.minHeight;
        }
        if ((i7 & 128) != 0) {
            bundle = surfaceViewInfo.extras;
        }
        Integer num5 = num4;
        Bundle bundle2 = bundle;
        Integer num6 = num2;
        Integer num7 = num3;
        Integer num8 = num;
        String str2 = str;
        return surfaceViewInfo.copy(i2, str2, iBinder, num8, num6, num7, num5, bundle2);
    }

    public final int component1() {
        return this.revision;
    }

    public final String component2() {
        return this.surfaceHash;
    }

    public final IBinder component3() {
        return this.hostToken;
    }

    public final Integer component4() {
        return this.displayId;
    }

    public final Integer component5() {
        return this.width;
    }

    public final Integer component6() {
        return this.height;
    }

    public final Integer component7() {
        return this.minHeight;
    }

    public final Bundle component8() {
        return this.extras;
    }

    public final SurfaceViewInfo copy(int i2, String str, IBinder iBinder, Integer num, Integer num2, Integer num3, Integer num4, Bundle bundle) {
        j.e(str, "surfaceHash");
        return new SurfaceViewInfo(i2, str, iBinder, num, num2, num3, num4, bundle);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SurfaceViewInfo)) {
            return false;
        }
        SurfaceViewInfo surfaceViewInfo = (SurfaceViewInfo) obj;
        if (this.revision == surfaceViewInfo.revision && j.a(this.surfaceHash, surfaceViewInfo.surfaceHash) && j.a(this.hostToken, surfaceViewInfo.hostToken) && j.a(this.displayId, surfaceViewInfo.displayId) && j.a(this.width, surfaceViewInfo.width) && j.a(this.height, surfaceViewInfo.height) && j.a(this.minHeight, surfaceViewInfo.minHeight) && j.a(this.extras, surfaceViewInfo.extras)) {
            return true;
        }
        return false;
    }

    public final Integer getDisplayId() {
        return this.displayId;
    }

    public final Bundle getExtras() {
        return this.extras;
    }

    public final Integer getHeight() {
        return this.height;
    }

    public final IBinder getHostToken() {
        return this.hostToken;
    }

    public final Integer getMinHeight() {
        return this.minHeight;
    }

    public final int getRevision() {
        return this.revision;
    }

    public final String getSurfaceHash() {
        return this.surfaceHash;
    }

    public final Integer getWidth() {
        return this.width;
    }

    public int hashCode() {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        int d = C0212a.d(Integer.hashCode(this.revision) * 31, 31, this.surfaceHash);
        IBinder iBinder = this.hostToken;
        int i12 = 0;
        if (iBinder == null) {
            i2 = 0;
        } else {
            i2 = iBinder.hashCode();
        }
        int i13 = (d + i2) * 31;
        Integer num = this.displayId;
        if (num == null) {
            i7 = 0;
        } else {
            i7 = num.hashCode();
        }
        int i14 = (i13 + i7) * 31;
        Integer num2 = this.width;
        if (num2 == null) {
            i8 = 0;
        } else {
            i8 = num2.hashCode();
        }
        int i15 = (i14 + i8) * 31;
        Integer num3 = this.height;
        if (num3 == null) {
            i10 = 0;
        } else {
            i10 = num3.hashCode();
        }
        int i16 = (i15 + i10) * 31;
        Integer num4 = this.minHeight;
        if (num4 == null) {
            i11 = 0;
        } else {
            i11 = num4.hashCode();
        }
        int i17 = (i16 + i11) * 31;
        Bundle bundle = this.extras;
        if (bundle != null) {
            i12 = bundle.hashCode();
        }
        return i17 + i12;
    }

    public final void setHeight(Integer num) {
        this.height = num;
    }

    public final void setWidth(Integer num) {
        this.width = num;
    }

    public String toString() {
        int i2 = this.revision;
        String str = this.surfaceHash;
        IBinder iBinder = this.hostToken;
        Integer num = this.displayId;
        Integer num2 = this.width;
        Integer num3 = this.height;
        Integer num4 = this.minHeight;
        Bundle bundle = this.extras;
        return "SurfaceViewInfo(revision=" + i2 + ", surfaceHash=" + str + ", hostToken=" + iBinder + ", displayId=" + num + ", width=" + num2 + ", height=" + num3 + ", minHeight=" + num4 + ", extras=" + bundle + ")";
    }

    public void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "parcel");
        parcel.writeInt(this.revision);
        parcel.writeString(this.surfaceHash);
        parcel.writeStrongBinder(this.hostToken);
        parcel.writeValue(this.displayId);
        parcel.writeValue(this.width);
        parcel.writeValue(this.height);
        parcel.writeValue(this.minHeight);
        parcel.writeBundle(this.extras);
    }

    public /* synthetic */ SurfaceViewInfo(int i2, String str, IBinder iBinder, Integer num, Integer num2, Integer num3, Integer num4, Bundle bundle, int i7, e eVar) {
        Bundle bundle2;
        Integer num5;
        Integer num6;
        Integer num7;
        Integer num8;
        IBinder iBinder2;
        String str2;
        int i8;
        SurfaceViewInfo surfaceViewInfo;
        i2 = (i7 & 1) != 0 ? 2 : i2;
        iBinder = (i7 & 4) != 0 ? null : iBinder;
        num = (i7 & 8) != 0 ? null : num;
        num2 = (i7 & 16) != 0 ? null : num2;
        num3 = (i7 & 32) != 0 ? null : num3;
        num4 = (i7 & 64) != 0 ? null : num4;
        if ((i7 & 128) != 0) {
            bundle2 = null;
            num6 = num3;
            num5 = num4;
            num8 = num;
            num7 = num2;
            str2 = str;
            iBinder2 = iBinder;
            surfaceViewInfo = this;
            i8 = i2;
        } else {
            bundle2 = bundle;
            num5 = num4;
            num7 = num2;
            num6 = num3;
            iBinder2 = iBinder;
            num8 = num;
            i8 = i2;
            str2 = str;
            surfaceViewInfo = this;
        }
        new SurfaceViewInfo(i8, str2, iBinder2, num8, num7, num6, num5, bundle2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: java.lang.Integer} */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SurfaceViewInfo(android.os.Parcel r11) {
        /*
            r10 = this;
            java.lang.String r0 = "parcel"
            kotlin.jvm.internal.j.e(r11, r0)
            int r2 = r11.readInt()
            java.lang.String r3 = r11.readString()
            if (r3 == 0) goto L_0x0067
            android.os.IBinder r4 = r11.readStrongBinder()
            java.lang.Class r0 = java.lang.Integer.TYPE
            java.lang.ClassLoader r1 = r0.getClassLoader()
            java.lang.Object r1 = r11.readValue(r1)
            boolean r5 = r1 instanceof java.lang.Integer
            r6 = 0
            if (r5 == 0) goto L_0x0026
            java.lang.Integer r1 = (java.lang.Integer) r1
            r5 = r1
            goto L_0x0027
        L_0x0026:
            r5 = r6
        L_0x0027:
            java.lang.ClassLoader r1 = r0.getClassLoader()
            java.lang.Object r1 = r11.readValue(r1)
            boolean r7 = r1 instanceof java.lang.Integer
            if (r7 == 0) goto L_0x0036
            java.lang.Integer r1 = (java.lang.Integer) r1
            goto L_0x0037
        L_0x0036:
            r1 = r6
        L_0x0037:
            java.lang.ClassLoader r7 = r0.getClassLoader()
            java.lang.Object r7 = r11.readValue(r7)
            boolean r8 = r7 instanceof java.lang.Integer
            if (r8 == 0) goto L_0x0046
            java.lang.Integer r7 = (java.lang.Integer) r7
            goto L_0x0047
        L_0x0046:
            r7 = r6
        L_0x0047:
            java.lang.ClassLoader r0 = r0.getClassLoader()
            java.lang.Object r0 = r11.readValue(r0)
            boolean r8 = r0 instanceof java.lang.Integer
            if (r8 == 0) goto L_0x0056
            r6 = r0
            java.lang.Integer r6 = (java.lang.Integer) r6
        L_0x0056:
            r8 = r6
            java.lang.Class<android.os.Bundle> r0 = android.os.Bundle.class
            java.lang.ClassLoader r0 = r0.getClassLoader()
            android.os.Bundle r9 = r11.readBundle(r0)
            r6 = r1
            r1 = r10
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            return
        L_0x0067:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "surfaceHash is null"
            r10.<init>(r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.contract.suggestion.view.SurfaceViewInfo.<init>(android.os.Parcel):void");
    }
}
