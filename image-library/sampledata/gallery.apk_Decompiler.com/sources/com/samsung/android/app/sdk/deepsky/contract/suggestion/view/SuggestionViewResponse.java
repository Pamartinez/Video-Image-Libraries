package com.samsung.android.app.sdk.deepsky.contract.suggestion.view;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.SurfaceControlViewHost;
import c0.C0086a;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u0000\n\u0002\b\u0017\b\b\u0018\u0000 K2\u00020\u0001:\u0001KB\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0014\u0010\u0015B\u0011\b\u0017\u0012\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0014\u0010\u0018J\u001f\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0002H\u0017¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u001eJ\u0012\u0010 \u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b \u0010!J\u0012\u0010\"\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b\"\u0010#J\u0012\u0010$\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b$\u0010#J\u0012\u0010%\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b%\u0010&J\u0012\u0010'\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b'\u0010&J\u0012\u0010(\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b(\u0010&J\u0012\u0010)\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b)\u0010&J\u0012\u0010*\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b*\u0010#J\u0010\u0010+\u001a\u00020\u000eHÆ\u0003¢\u0006\u0004\b+\u0010,J\u0012\u0010-\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b-\u0010#J\u0012\u0010.\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0004\b.\u0010/J\u0012\u00100\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b0\u0010#J¨\u0001\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0004\b1\u00102J\u0010\u00103\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b3\u0010#J\u0010\u00104\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b4\u0010\u001eJ\u001a\u00107\u001a\u00020\u000e2\b\u00106\u001a\u0004\u0018\u000105HÖ\u0003¢\u0006\u0004\b7\u00108R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u00109\u001a\u0004\b:\u0010\u001eR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010;\u001a\u0004\b<\u0010!R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010=\u001a\u0004\b>\u0010#R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010=\u001a\u0004\b?\u0010#R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010@\u001a\u0004\bA\u0010&R\u0019\u0010\n\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010@\u001a\u0004\bB\u0010&R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010@\u001a\u0004\bC\u0010&R\u0019\u0010\f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\f\u0010@\u001a\u0004\bD\u0010&R\u0019\u0010\r\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\r\u0010=\u001a\u0004\bE\u0010#R\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010F\u001a\u0004\b\u000f\u0010,R\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0010\u0010=\u001a\u0004\bG\u0010#R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010H\u001a\u0004\bI\u0010/R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0013\u0010=\u001a\u0004\bJ\u0010#¨\u0006L"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewResponse;", "Landroid/os/Parcelable;", "", "revision", "Landroid/view/SurfaceControlViewHost$SurfacePackage;", "surfacePackage", "", "itemId", "dataId", "viewId", "width", "height", "actionId", "cause", "", "isValid", "errorMessage", "Landroid/os/Bundle;", "extras", "actionUrl", "<init>", "(ILandroid/view/SurfaceControlViewHost$SurfacePackage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;ZLjava/lang/String;Landroid/os/Bundle;Ljava/lang/String;)V", "Landroid/os/Parcel;", "parcel", "(Landroid/os/Parcel;)V", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "component2", "()Landroid/view/SurfaceControlViewHost$SurfacePackage;", "component3", "()Ljava/lang/String;", "component4", "component5", "()Ljava/lang/Integer;", "component6", "component7", "component8", "component9", "component10", "()Z", "component11", "component12", "()Landroid/os/Bundle;", "component13", "copy", "(ILandroid/view/SurfaceControlViewHost$SurfacePackage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;ZLjava/lang/String;Landroid/os/Bundle;Ljava/lang/String;)Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewResponse;", "toString", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "I", "getRevision", "Landroid/view/SurfaceControlViewHost$SurfacePackage;", "getSurfacePackage", "Ljava/lang/String;", "getItemId", "getDataId", "Ljava/lang/Integer;", "getViewId", "getWidth", "getHeight", "getActionId", "getCause", "Z", "getErrorMessage", "Landroid/os/Bundle;", "getExtras", "getActionUrl", "CREATOR", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SuggestionViewResponse implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR((e) null);
    private final Integer actionId;
    private final String actionUrl;
    private final String cause;
    private final String dataId;
    private final String errorMessage;
    private final Bundle extras;
    private final Integer height;
    private final boolean isValid;
    private final String itemId;
    private final int revision;
    private final SurfaceControlViewHost.SurfacePackage surfacePackage;
    private final Integer viewId;
    private final Integer width;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001d\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewResponse$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewResponse;", "<init>", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewResponse;", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CREATOR implements Parcelable.Creator<SuggestionViewResponse> {
        public /* synthetic */ CREATOR(e eVar) {
            this();
        }

        private CREATOR() {
        }

        public SuggestionViewResponse createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new SuggestionViewResponse(parcel);
        }

        public SuggestionViewResponse[] newArray(int i2) {
            return new SuggestionViewResponse[i2];
        }
    }

    public SuggestionViewResponse() {
        this(0, (SurfaceControlViewHost.SurfacePackage) null, (String) null, (String) null, (Integer) null, (Integer) null, (Integer) null, (Integer) null, (String) null, false, (String) null, (Bundle) null, (String) null, 8191, (e) null);
    }

    public static /* synthetic */ SuggestionViewResponse copy$default(SuggestionViewResponse suggestionViewResponse, int i2, SurfaceControlViewHost.SurfacePackage surfacePackage2, String str, String str2, Integer num, Integer num2, Integer num3, Integer num4, String str3, boolean z, String str4, Bundle bundle, String str5, int i7, Object obj) {
        int i8 = i7;
        if ((i8 & 1) != 0) {
            i2 = suggestionViewResponse.revision;
        }
        return suggestionViewResponse.copy(i2, (i8 & 2) != 0 ? suggestionViewResponse.surfacePackage : surfacePackage2, (i8 & 4) != 0 ? suggestionViewResponse.itemId : str, (i8 & 8) != 0 ? suggestionViewResponse.dataId : str2, (i8 & 16) != 0 ? suggestionViewResponse.viewId : num, (i8 & 32) != 0 ? suggestionViewResponse.width : num2, (i8 & 64) != 0 ? suggestionViewResponse.height : num3, (i8 & 128) != 0 ? suggestionViewResponse.actionId : num4, (i8 & 256) != 0 ? suggestionViewResponse.cause : str3, (i8 & 512) != 0 ? suggestionViewResponse.isValid : z, (i8 & 1024) != 0 ? suggestionViewResponse.errorMessage : str4, (i8 & 2048) != 0 ? suggestionViewResponse.extras : bundle, (i8 & 4096) != 0 ? suggestionViewResponse.actionUrl : str5);
    }

    public final int component1() {
        return this.revision;
    }

    public final boolean component10() {
        return this.isValid;
    }

    public final String component11() {
        return this.errorMessage;
    }

    public final Bundle component12() {
        return this.extras;
    }

    public final String component13() {
        return this.actionUrl;
    }

    public final SurfaceControlViewHost.SurfacePackage component2() {
        return this.surfacePackage;
    }

    public final String component3() {
        return this.itemId;
    }

    public final String component4() {
        return this.dataId;
    }

    public final Integer component5() {
        return this.viewId;
    }

    public final Integer component6() {
        return this.width;
    }

    public final Integer component7() {
        return this.height;
    }

    public final Integer component8() {
        return this.actionId;
    }

    public final String component9() {
        return this.cause;
    }

    public final SuggestionViewResponse copy(int i2, SurfaceControlViewHost.SurfacePackage surfacePackage2, String str, String str2, Integer num, Integer num2, Integer num3, Integer num4, String str3, boolean z, String str4, Bundle bundle, String str5) {
        return new SuggestionViewResponse(i2, surfacePackage2, str, str2, num, num2, num3, num4, str3, z, str4, bundle, str5);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SuggestionViewResponse)) {
            return false;
        }
        SuggestionViewResponse suggestionViewResponse = (SuggestionViewResponse) obj;
        if (this.revision == suggestionViewResponse.revision && j.a(this.surfacePackage, suggestionViewResponse.surfacePackage) && j.a(this.itemId, suggestionViewResponse.itemId) && j.a(this.dataId, suggestionViewResponse.dataId) && j.a(this.viewId, suggestionViewResponse.viewId) && j.a(this.width, suggestionViewResponse.width) && j.a(this.height, suggestionViewResponse.height) && j.a(this.actionId, suggestionViewResponse.actionId) && j.a(this.cause, suggestionViewResponse.cause) && this.isValid == suggestionViewResponse.isValid && j.a(this.errorMessage, suggestionViewResponse.errorMessage) && j.a(this.extras, suggestionViewResponse.extras) && j.a(this.actionUrl, suggestionViewResponse.actionUrl)) {
            return true;
        }
        return false;
    }

    public final Integer getActionId() {
        return this.actionId;
    }

    public final String getActionUrl() {
        return this.actionUrl;
    }

    public final String getCause() {
        return this.cause;
    }

    public final String getDataId() {
        return this.dataId;
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final Bundle getExtras() {
        return this.extras;
    }

    public final Integer getHeight() {
        return this.height;
    }

    public final String getItemId() {
        return this.itemId;
    }

    public final int getRevision() {
        return this.revision;
    }

    public final SurfaceControlViewHost.SurfacePackage getSurfacePackage() {
        return this.surfacePackage;
    }

    public final Integer getViewId() {
        return this.viewId;
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
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int hashCode = Integer.hashCode(this.revision) * 31;
        SurfaceControlViewHost.SurfacePackage surfacePackage2 = this.surfacePackage;
        int i17 = 0;
        if (surfacePackage2 == null) {
            i2 = 0;
        } else {
            i2 = surfacePackage2.hashCode();
        }
        int i18 = (hashCode + i2) * 31;
        String str = this.itemId;
        if (str == null) {
            i7 = 0;
        } else {
            i7 = str.hashCode();
        }
        int i19 = (i18 + i7) * 31;
        String str2 = this.dataId;
        if (str2 == null) {
            i8 = 0;
        } else {
            i8 = str2.hashCode();
        }
        int i20 = (i19 + i8) * 31;
        Integer num = this.viewId;
        if (num == null) {
            i10 = 0;
        } else {
            i10 = num.hashCode();
        }
        int i21 = (i20 + i10) * 31;
        Integer num2 = this.width;
        if (num2 == null) {
            i11 = 0;
        } else {
            i11 = num2.hashCode();
        }
        int i22 = (i21 + i11) * 31;
        Integer num3 = this.height;
        if (num3 == null) {
            i12 = 0;
        } else {
            i12 = num3.hashCode();
        }
        int i23 = (i22 + i12) * 31;
        Integer num4 = this.actionId;
        if (num4 == null) {
            i13 = 0;
        } else {
            i13 = num4.hashCode();
        }
        int i24 = (i23 + i13) * 31;
        String str3 = this.cause;
        if (str3 == null) {
            i14 = 0;
        } else {
            i14 = str3.hashCode();
        }
        int e = C0212a.e((i24 + i14) * 31, 31, this.isValid);
        String str4 = this.errorMessage;
        if (str4 == null) {
            i15 = 0;
        } else {
            i15 = str4.hashCode();
        }
        int i25 = (e + i15) * 31;
        Bundle bundle = this.extras;
        if (bundle == null) {
            i16 = 0;
        } else {
            i16 = bundle.hashCode();
        }
        int i26 = (i25 + i16) * 31;
        String str5 = this.actionUrl;
        if (str5 != null) {
            i17 = str5.hashCode();
        }
        return i26 + i17;
    }

    public final boolean isValid() {
        return this.isValid;
    }

    public String toString() {
        int i2 = this.revision;
        SurfaceControlViewHost.SurfacePackage surfacePackage2 = this.surfacePackage;
        String str = this.itemId;
        String str2 = this.dataId;
        Integer num = this.viewId;
        Integer num2 = this.width;
        Integer num3 = this.height;
        Integer num4 = this.actionId;
        String str3 = this.cause;
        boolean z = this.isValid;
        String str4 = this.errorMessage;
        Bundle bundle = this.extras;
        String str5 = this.actionUrl;
        StringBuilder sb2 = new StringBuilder("SuggestionViewResponse(revision=");
        sb2.append(i2);
        sb2.append(", surfacePackage=");
        sb2.append(surfacePackage2);
        sb2.append(", itemId=");
        C0086a.z(sb2, str, ", dataId=", str2, ", viewId=");
        sb2.append(num);
        sb2.append(", width=");
        sb2.append(num2);
        sb2.append(", height=");
        sb2.append(num3);
        sb2.append(", actionId=");
        sb2.append(num4);
        sb2.append(", cause=");
        sb2.append(str3);
        sb2.append(", isValid=");
        sb2.append(z);
        sb2.append(", errorMessage=");
        sb2.append(str4);
        sb2.append(", extras=");
        sb2.append(bundle);
        sb2.append(", actionUrl=");
        return C0212a.p(sb2, str5, ")");
    }

    public void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "parcel");
        parcel.writeInt(this.revision);
        parcel.writeParcelable(this.surfacePackage, i2);
        parcel.writeString(this.itemId);
        parcel.writeString(this.dataId);
        parcel.writeValue(this.viewId);
        parcel.writeValue(this.width);
        parcel.writeValue(this.height);
        parcel.writeValue(this.actionId);
        parcel.writeString(this.cause);
        parcel.writeByte(this.isValid ? (byte) 1 : 0);
        parcel.writeString(this.errorMessage);
        parcel.writeBundle(this.extras);
        parcel.writeString(this.actionUrl);
    }

    public SuggestionViewResponse(int i2, SurfaceControlViewHost.SurfacePackage surfacePackage2, String str, String str2, Integer num, Integer num2, Integer num3, Integer num4, String str3, boolean z, String str4, Bundle bundle, String str5) {
        this.revision = i2;
        this.surfacePackage = surfacePackage2;
        this.itemId = str;
        this.dataId = str2;
        this.viewId = num;
        this.width = num2;
        this.height = num3;
        this.actionId = num4;
        this.cause = str3;
        this.isValid = z;
        this.errorMessage = str4;
        this.extras = bundle;
        this.actionUrl = str5;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ SuggestionViewResponse(int r14, android.view.SurfaceControlViewHost.SurfacePackage r15, java.lang.String r16, java.lang.String r17, java.lang.Integer r18, java.lang.Integer r19, java.lang.Integer r20, java.lang.Integer r21, java.lang.String r22, boolean r23, java.lang.String r24, android.os.Bundle r25, java.lang.String r26, int r27, kotlin.jvm.internal.e r28) {
        /*
            r13 = this;
            r0 = r27
            r1 = r0 & 1
            if (r1 == 0) goto L_0x0007
            r14 = 3
        L_0x0007:
            r1 = r0 & 2
            r2 = 0
            if (r1 == 0) goto L_0x000e
            r1 = r2
            goto L_0x000f
        L_0x000e:
            r1 = r15
        L_0x000f:
            r3 = r0 & 4
            if (r3 == 0) goto L_0x0015
            r3 = r2
            goto L_0x0017
        L_0x0015:
            r3 = r16
        L_0x0017:
            r4 = r0 & 8
            if (r4 == 0) goto L_0x001d
            r4 = r2
            goto L_0x001f
        L_0x001d:
            r4 = r17
        L_0x001f:
            r5 = r0 & 16
            if (r5 == 0) goto L_0x0025
            r5 = r2
            goto L_0x0027
        L_0x0025:
            r5 = r18
        L_0x0027:
            r6 = r0 & 32
            if (r6 == 0) goto L_0x002d
            r6 = r2
            goto L_0x002f
        L_0x002d:
            r6 = r19
        L_0x002f:
            r7 = r0 & 64
            if (r7 == 0) goto L_0x0035
            r7 = r2
            goto L_0x0037
        L_0x0035:
            r7 = r20
        L_0x0037:
            r8 = r0 & 128(0x80, float:1.794E-43)
            if (r8 == 0) goto L_0x003d
            r8 = r2
            goto L_0x003f
        L_0x003d:
            r8 = r21
        L_0x003f:
            r9 = r0 & 256(0x100, float:3.59E-43)
            if (r9 == 0) goto L_0x0045
            r9 = r2
            goto L_0x0047
        L_0x0045:
            r9 = r22
        L_0x0047:
            r10 = r0 & 512(0x200, float:7.175E-43)
            if (r10 == 0) goto L_0x004d
            r10 = 1
            goto L_0x004f
        L_0x004d:
            r10 = r23
        L_0x004f:
            r11 = r0 & 1024(0x400, float:1.435E-42)
            if (r11 == 0) goto L_0x0055
            r11 = r2
            goto L_0x0057
        L_0x0055:
            r11 = r24
        L_0x0057:
            r12 = r0 & 2048(0x800, float:2.87E-42)
            if (r12 == 0) goto L_0x005d
            r12 = r2
            goto L_0x005f
        L_0x005d:
            r12 = r25
        L_0x005f:
            r0 = r0 & 4096(0x1000, float:5.74E-42)
            if (r0 == 0) goto L_0x007f
            r28 = r2
        L_0x0065:
            r15 = r13
            r16 = r14
            r17 = r1
            r18 = r3
            r19 = r4
            r20 = r5
            r21 = r6
            r22 = r7
            r23 = r8
            r24 = r9
            r25 = r10
            r26 = r11
            r27 = r12
            goto L_0x0082
        L_0x007f:
            r28 = r26
            goto L_0x0065
        L_0x0082:
            r15.<init>(r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.contract.suggestion.view.SuggestionViewResponse.<init>(int, android.view.SurfaceControlViewHost$SurfacePackage, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, boolean, java.lang.String, android.os.Bundle, java.lang.String, int, kotlin.jvm.internal.e):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: java.lang.Integer} */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SuggestionViewResponse(android.os.Parcel r17) {
        /*
            r16 = this;
            r0 = r17
            java.lang.String r1 = "parcel"
            kotlin.jvm.internal.j.e(r0, r1)
            int r3 = r0.readInt()
            java.lang.Class<android.view.SurfaceControlViewHost$SurfacePackage> r1 = android.view.SurfaceControlViewHost.SurfacePackage.class
            java.lang.ClassLoader r1 = r1.getClassLoader()
            android.os.Parcelable r1 = r0.readParcelable(r1)
            r4 = r1
            android.view.SurfaceControlViewHost$SurfacePackage r4 = (android.view.SurfaceControlViewHost.SurfacePackage) r4
            java.lang.String r5 = r0.readString()
            java.lang.String r6 = r0.readString()
            java.lang.Class r1 = java.lang.Integer.TYPE
            java.lang.ClassLoader r2 = r1.getClassLoader()
            java.lang.Object r2 = r0.readValue(r2)
            boolean r7 = r2 instanceof java.lang.Integer
            r8 = 0
            if (r7 == 0) goto L_0x0033
            java.lang.Integer r2 = (java.lang.Integer) r2
            r7 = r2
            goto L_0x0034
        L_0x0033:
            r7 = r8
        L_0x0034:
            java.lang.ClassLoader r2 = r1.getClassLoader()
            java.lang.Object r2 = r0.readValue(r2)
            boolean r9 = r2 instanceof java.lang.Integer
            if (r9 == 0) goto L_0x0043
            java.lang.Integer r2 = (java.lang.Integer) r2
            goto L_0x0044
        L_0x0043:
            r2 = r8
        L_0x0044:
            java.lang.ClassLoader r9 = r1.getClassLoader()
            java.lang.Object r9 = r0.readValue(r9)
            boolean r10 = r9 instanceof java.lang.Integer
            if (r10 == 0) goto L_0x0053
            java.lang.Integer r9 = (java.lang.Integer) r9
            goto L_0x0054
        L_0x0053:
            r9 = r8
        L_0x0054:
            java.lang.ClassLoader r1 = r1.getClassLoader()
            java.lang.Object r1 = r0.readValue(r1)
            boolean r10 = r1 instanceof java.lang.Integer
            if (r10 == 0) goto L_0x0063
            r8 = r1
            java.lang.Integer r8 = (java.lang.Integer) r8
        L_0x0063:
            r10 = r8
            java.lang.String r11 = r0.readString()
            byte r1 = r0.readByte()
            if (r1 == 0) goto L_0x0071
            r1 = 1
        L_0x006f:
            r12 = r1
            goto L_0x0073
        L_0x0071:
            r1 = 0
            goto L_0x006f
        L_0x0073:
            java.lang.String r13 = r0.readString()
            java.lang.Class<android.os.Bundle> r1 = android.os.Bundle.class
            java.lang.ClassLoader r1 = r1.getClassLoader()
            android.os.Bundle r14 = r0.readBundle(r1)
            java.lang.String r15 = r0.readString()
            r8 = r2
            r2 = r16
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.contract.suggestion.view.SuggestionViewResponse.<init>(android.os.Parcel):void");
    }
}
