package com.samsung.android.app.sdk.deepsky.contract.suggestion.view;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import i.C0212a;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\b\b\u0018\u0000 D2\u00020\u0001:\u0001DBu\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f\u0012\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000e\u0012\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000e¢\u0006\u0004\b\u0011\u0010\u0012B\u0011\b\u0016\u0012\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0011\u0010\u0015J\u001f\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001bJ\u0010\u0010\u001d\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u001eJ\u0012\u0010 \u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b \u0010!J\u0012\u0010\"\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b\"\u0010#J\u0012\u0010$\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b$\u0010%J\u0012\u0010&\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b&\u0010'J\u0018\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000eHÆ\u0003¢\u0006\u0004\b(\u0010)J\u0018\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000eHÆ\u0003¢\u0006\u0004\b*\u0010)J\u0001\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000e2\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000eHÆ\u0001¢\u0006\u0004\b+\u0010,J\u0010\u0010-\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b-\u0010\u001eJ\u0010\u0010.\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b.\u0010\u001bJ\u001a\u00102\u001a\u0002012\b\u00100\u001a\u0004\u0018\u00010/HÖ\u0003¢\u0006\u0004\b2\u00103R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u00104\u001a\u0004\b5\u0010\u001bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u00106\u001a\u0004\b7\u0010\u001eR\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u00106\u001a\u0004\b8\u0010\u001eR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u00109\u001a\u0004\b:\u0010!R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010;\u001a\u0004\b<\u0010#R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010=\u001a\u0004\b>\u0010%R\u0019\u0010\r\u001a\u0004\u0018\u00010\f8\u0006¢\u0006\f\n\u0004\b\r\u0010?\u001a\u0004\b@\u0010'R\u001f\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010A\u001a\u0004\bB\u0010)R\u001f\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u0010\u0010A\u001a\u0004\bC\u0010)¨\u0006E"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewRequest;", "Landroid/os/Parcelable;", "", "revision", "", "surfaceHash", "suggestionItemId", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SurfaceViewInfo;", "surfaceViewInfo", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewSpec;", "viewSpec", "maxSuggestionCount", "Landroid/os/Bundle;", "extras", "", "includedDataIdList", "excludedDataIdList", "<init>", "(ILjava/lang/String;Ljava/lang/String;Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SurfaceViewInfo;Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewSpec;Ljava/lang/Integer;Landroid/os/Bundle;Ljava/util/List;Ljava/util/List;)V", "Landroid/os/Parcel;", "parcel", "(Landroid/os/Parcel;)V", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "component2", "()Ljava/lang/String;", "component3", "component4", "()Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SurfaceViewInfo;", "component5", "()Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewSpec;", "component6", "()Ljava/lang/Integer;", "component7", "()Landroid/os/Bundle;", "component8", "()Ljava/util/List;", "component9", "copy", "(ILjava/lang/String;Ljava/lang/String;Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SurfaceViewInfo;Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewSpec;Ljava/lang/Integer;Landroid/os/Bundle;Ljava/util/List;Ljava/util/List;)Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewRequest;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getRevision", "Ljava/lang/String;", "getSurfaceHash", "getSuggestionItemId", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SurfaceViewInfo;", "getSurfaceViewInfo", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewSpec;", "getViewSpec", "Ljava/lang/Integer;", "getMaxSuggestionCount", "Landroid/os/Bundle;", "getExtras", "Ljava/util/List;", "getIncludedDataIdList", "getExcludedDataIdList", "CREATOR", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SuggestionViewRequest implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR((e) null);
    private final List<Integer> excludedDataIdList;
    private final Bundle extras;
    private final List<Integer> includedDataIdList;
    private final Integer maxSuggestionCount;
    private final int revision;
    private final String suggestionItemId;
    private final String surfaceHash;
    private final SurfaceViewInfo surfaceViewInfo;
    private final SuggestionViewSpec viewSpec;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001d\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\u0004\u0018\u0001H\u000e\"\u0006\b\u0000\u0010\u000e\u0018\u0001*\u00020\u000fH\b¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewRequest$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewRequest;", "<init>", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewRequest;", "toListOrNull", "T", "Ljava/io/Serializable;", "(Ljava/io/Serializable;)Ljava/lang/Object;", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CREATOR implements Parcelable.Creator<SuggestionViewRequest> {
        public /* synthetic */ CREATOR(e eVar) {
            this();
        }

        private final /* synthetic */ <T> T toListOrNull(Serializable serializable) {
            j.h();
            throw null;
        }

        private CREATOR() {
        }

        public SuggestionViewRequest createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new SuggestionViewRequest(parcel);
        }

        public SuggestionViewRequest[] newArray(int i2) {
            return new SuggestionViewRequest[i2];
        }
    }

    public SuggestionViewRequest(int i2, String str, String str2, SurfaceViewInfo surfaceViewInfo2, SuggestionViewSpec suggestionViewSpec, Integer num, Bundle bundle, List<Integer> list, List<Integer> list2) {
        j.e(str, "surfaceHash");
        j.e(str2, "suggestionItemId");
        this.revision = i2;
        this.surfaceHash = str;
        this.suggestionItemId = str2;
        this.surfaceViewInfo = surfaceViewInfo2;
        this.viewSpec = suggestionViewSpec;
        this.maxSuggestionCount = num;
        this.extras = bundle;
        this.includedDataIdList = list;
        this.excludedDataIdList = list2;
    }

    public static /* synthetic */ SuggestionViewRequest copy$default(SuggestionViewRequest suggestionViewRequest, int i2, String str, String str2, SurfaceViewInfo surfaceViewInfo2, SuggestionViewSpec suggestionViewSpec, Integer num, Bundle bundle, List<Integer> list, List<Integer> list2, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            i2 = suggestionViewRequest.revision;
        }
        if ((i7 & 2) != 0) {
            str = suggestionViewRequest.surfaceHash;
        }
        if ((i7 & 4) != 0) {
            str2 = suggestionViewRequest.suggestionItemId;
        }
        if ((i7 & 8) != 0) {
            surfaceViewInfo2 = suggestionViewRequest.surfaceViewInfo;
        }
        if ((i7 & 16) != 0) {
            suggestionViewSpec = suggestionViewRequest.viewSpec;
        }
        if ((i7 & 32) != 0) {
            num = suggestionViewRequest.maxSuggestionCount;
        }
        if ((i7 & 64) != 0) {
            bundle = suggestionViewRequest.extras;
        }
        if ((i7 & 128) != 0) {
            list = suggestionViewRequest.includedDataIdList;
        }
        if ((i7 & 256) != 0) {
            list2 = suggestionViewRequest.excludedDataIdList;
        }
        List<Integer> list3 = list;
        List<Integer> list4 = list2;
        Integer num2 = num;
        Bundle bundle2 = bundle;
        SurfaceViewInfo surfaceViewInfo3 = surfaceViewInfo2;
        SuggestionViewSpec suggestionViewSpec2 = suggestionViewSpec;
        return suggestionViewRequest.copy(i2, str, str2, surfaceViewInfo3, suggestionViewSpec2, num2, bundle2, list3, list4);
    }

    public final int component1() {
        return this.revision;
    }

    public final String component2() {
        return this.surfaceHash;
    }

    public final String component3() {
        return this.suggestionItemId;
    }

    public final SurfaceViewInfo component4() {
        return this.surfaceViewInfo;
    }

    public final SuggestionViewSpec component5() {
        return this.viewSpec;
    }

    public final Integer component6() {
        return this.maxSuggestionCount;
    }

    public final Bundle component7() {
        return this.extras;
    }

    public final List<Integer> component8() {
        return this.includedDataIdList;
    }

    public final List<Integer> component9() {
        return this.excludedDataIdList;
    }

    public final SuggestionViewRequest copy(int i2, String str, String str2, SurfaceViewInfo surfaceViewInfo2, SuggestionViewSpec suggestionViewSpec, Integer num, Bundle bundle, List<Integer> list, List<Integer> list2) {
        j.e(str, "surfaceHash");
        j.e(str2, "suggestionItemId");
        return new SuggestionViewRequest(i2, str, str2, surfaceViewInfo2, suggestionViewSpec, num, bundle, list, list2);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SuggestionViewRequest)) {
            return false;
        }
        SuggestionViewRequest suggestionViewRequest = (SuggestionViewRequest) obj;
        if (this.revision == suggestionViewRequest.revision && j.a(this.surfaceHash, suggestionViewRequest.surfaceHash) && j.a(this.suggestionItemId, suggestionViewRequest.suggestionItemId) && j.a(this.surfaceViewInfo, suggestionViewRequest.surfaceViewInfo) && j.a(this.viewSpec, suggestionViewRequest.viewSpec) && j.a(this.maxSuggestionCount, suggestionViewRequest.maxSuggestionCount) && j.a(this.extras, suggestionViewRequest.extras) && j.a(this.includedDataIdList, suggestionViewRequest.includedDataIdList) && j.a(this.excludedDataIdList, suggestionViewRequest.excludedDataIdList)) {
            return true;
        }
        return false;
    }

    public final List<Integer> getExcludedDataIdList() {
        return this.excludedDataIdList;
    }

    public final Bundle getExtras() {
        return this.extras;
    }

    public final List<Integer> getIncludedDataIdList() {
        return this.includedDataIdList;
    }

    public final Integer getMaxSuggestionCount() {
        return this.maxSuggestionCount;
    }

    public final int getRevision() {
        return this.revision;
    }

    public final String getSuggestionItemId() {
        return this.suggestionItemId;
    }

    public final String getSurfaceHash() {
        return this.surfaceHash;
    }

    public final SurfaceViewInfo getSurfaceViewInfo() {
        return this.surfaceViewInfo;
    }

    public final SuggestionViewSpec getViewSpec() {
        return this.viewSpec;
    }

    public int hashCode() {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        int d = C0212a.d(C0212a.d(Integer.hashCode(this.revision) * 31, 31, this.surfaceHash), 31, this.suggestionItemId);
        SurfaceViewInfo surfaceViewInfo2 = this.surfaceViewInfo;
        int i12 = 0;
        if (surfaceViewInfo2 == null) {
            i2 = 0;
        } else {
            i2 = surfaceViewInfo2.hashCode();
        }
        int i13 = (d + i2) * 31;
        SuggestionViewSpec suggestionViewSpec = this.viewSpec;
        if (suggestionViewSpec == null) {
            i7 = 0;
        } else {
            i7 = suggestionViewSpec.hashCode();
        }
        int i14 = (i13 + i7) * 31;
        Integer num = this.maxSuggestionCount;
        if (num == null) {
            i8 = 0;
        } else {
            i8 = num.hashCode();
        }
        int i15 = (i14 + i8) * 31;
        Bundle bundle = this.extras;
        if (bundle == null) {
            i10 = 0;
        } else {
            i10 = bundle.hashCode();
        }
        int i16 = (i15 + i10) * 31;
        List<Integer> list = this.includedDataIdList;
        if (list == null) {
            i11 = 0;
        } else {
            i11 = list.hashCode();
        }
        int i17 = (i16 + i11) * 31;
        List<Integer> list2 = this.excludedDataIdList;
        if (list2 != null) {
            i12 = list2.hashCode();
        }
        return i17 + i12;
    }

    public String toString() {
        int i2 = this.revision;
        String str = this.surfaceHash;
        String str2 = this.suggestionItemId;
        SurfaceViewInfo surfaceViewInfo2 = this.surfaceViewInfo;
        SuggestionViewSpec suggestionViewSpec = this.viewSpec;
        Integer num = this.maxSuggestionCount;
        Bundle bundle = this.extras;
        List<Integer> list = this.includedDataIdList;
        List<Integer> list2 = this.excludedDataIdList;
        return "SuggestionViewRequest(revision=" + i2 + ", surfaceHash=" + str + ", suggestionItemId=" + str2 + ", surfaceViewInfo=" + surfaceViewInfo2 + ", viewSpec=" + suggestionViewSpec + ", maxSuggestionCount=" + num + ", extras=" + bundle + ", includedDataIdList=" + list + ", excludedDataIdList=" + list2 + ")";
    }

    public void writeToParcel(Parcel parcel, int i2) {
        ArrayList arrayList;
        j.e(parcel, "parcel");
        parcel.writeInt(this.revision);
        parcel.writeString(this.surfaceHash);
        parcel.writeString(this.suggestionItemId);
        parcel.writeParcelable(this.surfaceViewInfo, i2);
        parcel.writeParcelable(this.viewSpec, i2);
        parcel.writeValue(this.maxSuggestionCount);
        parcel.writeBundle(this.extras);
        ArrayList arrayList2 = null;
        if (this.includedDataIdList != null) {
            arrayList = new ArrayList(this.includedDataIdList);
        } else {
            arrayList = null;
        }
        parcel.writeSerializable(arrayList);
        if (this.excludedDataIdList != null) {
            arrayList2 = new ArrayList(this.excludedDataIdList);
        }
        parcel.writeSerializable(arrayList2);
    }

    public /* synthetic */ SuggestionViewRequest(int i2, String str, String str2, SurfaceViewInfo surfaceViewInfo2, SuggestionViewSpec suggestionViewSpec, Integer num, Bundle bundle, List list, List list2, int i7, e eVar) {
        List list3;
        List list4;
        Bundle bundle2;
        Integer num2;
        SuggestionViewSpec suggestionViewSpec2;
        SurfaceViewInfo surfaceViewInfo3;
        String str3;
        String str4;
        int i8;
        SuggestionViewRequest suggestionViewRequest;
        i2 = (i7 & 1) != 0 ? 2 : i2;
        surfaceViewInfo2 = (i7 & 8) != 0 ? null : surfaceViewInfo2;
        suggestionViewSpec = (i7 & 16) != 0 ? null : suggestionViewSpec;
        num = (i7 & 32) != 0 ? null : num;
        bundle = (i7 & 64) != 0 ? null : bundle;
        list = (i7 & 128) != 0 ? null : list;
        if ((i7 & 256) != 0) {
            list3 = null;
            bundle2 = bundle;
            list4 = list;
            suggestionViewSpec2 = suggestionViewSpec;
            num2 = num;
            str3 = str2;
            surfaceViewInfo3 = surfaceViewInfo2;
            i8 = i2;
            str4 = str;
            suggestionViewRequest = this;
        } else {
            list3 = list2;
            list4 = list;
            num2 = num;
            bundle2 = bundle;
            surfaceViewInfo3 = surfaceViewInfo2;
            suggestionViewSpec2 = suggestionViewSpec;
            str4 = str;
            str3 = str2;
            suggestionViewRequest = this;
            i8 = i2;
        }
        new SuggestionViewRequest(i8, str4, str3, surfaceViewInfo3, suggestionViewSpec2, num2, bundle2, list4, list3);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SuggestionViewRequest(android.os.Parcel r12) {
        /*
            r11 = this;
            java.lang.String r0 = "parcel"
            kotlin.jvm.internal.j.e(r12, r0)
            int r2 = r12.readInt()
            java.lang.String r3 = r12.readString()
            if (r3 == 0) goto L_0x0070
            java.lang.String r4 = r12.readString()
            if (r4 == 0) goto L_0x0067
            java.lang.Class<com.samsung.android.app.sdk.deepsky.contract.suggestion.view.SurfaceViewInfo> r0 = com.samsung.android.app.sdk.deepsky.contract.suggestion.view.SurfaceViewInfo.class
            java.lang.ClassLoader r0 = r0.getClassLoader()
            android.os.Parcelable r0 = r12.readParcelable(r0)
            r5 = r0
            com.samsung.android.app.sdk.deepsky.contract.suggestion.view.SurfaceViewInfo r5 = (com.samsung.android.app.sdk.deepsky.contract.suggestion.view.SurfaceViewInfo) r5
            java.lang.Class<com.samsung.android.app.sdk.deepsky.contract.suggestion.view.SuggestionViewSpec> r0 = com.samsung.android.app.sdk.deepsky.contract.suggestion.view.SuggestionViewSpec.class
            java.lang.ClassLoader r0 = r0.getClassLoader()
            android.os.Parcelable r0 = r12.readParcelable(r0)
            r6 = r0
            com.samsung.android.app.sdk.deepsky.contract.suggestion.view.SuggestionViewSpec r6 = (com.samsung.android.app.sdk.deepsky.contract.suggestion.view.SuggestionViewSpec) r6
            java.lang.Class r0 = java.lang.Integer.TYPE
            java.lang.ClassLoader r0 = r0.getClassLoader()
            java.lang.Object r0 = r12.readValue(r0)
            boolean r1 = r0 instanceof java.lang.Integer
            r7 = 0
            if (r1 == 0) goto L_0x0041
            java.lang.Integer r0 = (java.lang.Integer) r0
            goto L_0x0042
        L_0x0041:
            r0 = r7
        L_0x0042:
            java.lang.Class<android.os.Bundle> r1 = android.os.Bundle.class
            java.lang.ClassLoader r1 = r1.getClassLoader()
            android.os.Bundle r8 = r12.readBundle(r1)
            java.io.Serializable r1 = r12.readSerializable()
            if (r1 == 0) goto L_0x0056
            java.util.List r1 = (java.util.List) r1
            r9 = r1
            goto L_0x0057
        L_0x0056:
            r9 = r7
        L_0x0057:
            java.io.Serializable r12 = r12.readSerializable()
            if (r12 == 0) goto L_0x0060
            r7 = r12
            java.util.List r7 = (java.util.List) r7
        L_0x0060:
            r1 = r11
            r10 = r7
            r7 = r0
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            return
        L_0x0067:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "suggestionItemId is null"
            r11.<init>(r12)
            throw r11
        L_0x0070:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "surfaceHash is null"
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.contract.suggestion.view.SuggestionViewRequest.<init>(android.os.Parcel):void");
    }
}
