package com.samsung.android.sdk.moneta.memory.entity.content;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.GetRecommendationsResponseBundleWrapper;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0015J*\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0013J\u0010\u0010\u0019\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\u0019\u0010\u0011J\u001a\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aHÖ\u0003¢\u0006\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001f\u001a\u0004\b \u0010\u0013R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010!\u001a\u0004\b\"\u0010\u0015¨\u0006#"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/content/GetRecommendationsResponse;", "Landroid/os/Parcelable;", "", "debugInfo", "", "Lcom/samsung/android/sdk/moneta/memory/entity/content/KeywordInfo;", "keywords", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "()Ljava/util/List;", "copy", "(Ljava/lang/String;Ljava/util/List;)Lcom/samsung/android/sdk/moneta/memory/entity/content/GetRecommendationsResponse;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getDebugInfo", "Ljava/util/List;", "getKeywords", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GetRecommendationsResponse implements Parcelable {
    public static final Parcelable.Creator<GetRecommendationsResponse> CREATOR = new Creator();
    private final String debugInfo;
    private final List<KeywordInfo> keywords;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<GetRecommendationsResponse> {
        public final GetRecommendationsResponse createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.a(KeywordInfo.CREATOR, parcel, arrayList, i2, 1);
            }
            return new GetRecommendationsResponse(readString, arrayList);
        }

        public final GetRecommendationsResponse[] newArray(int i2) {
            return new GetRecommendationsResponse[i2];
        }
    }

    public GetRecommendationsResponse(String str, List<KeywordInfo> list) {
        j.e(str, GetRecommendationsResponseBundleWrapper.BUNDLE_KEY_DEBUG_INFO);
        j.e(list, "keywords");
        this.debugInfo = str;
        this.keywords = list;
    }

    public static /* synthetic */ GetRecommendationsResponse copy$default(GetRecommendationsResponse getRecommendationsResponse, String str, List<KeywordInfo> list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = getRecommendationsResponse.debugInfo;
        }
        if ((i2 & 2) != 0) {
            list = getRecommendationsResponse.keywords;
        }
        return getRecommendationsResponse.copy(str, list);
    }

    public final String component1() {
        return this.debugInfo;
    }

    public final List<KeywordInfo> component2() {
        return this.keywords;
    }

    public final GetRecommendationsResponse copy(String str, List<KeywordInfo> list) {
        j.e(str, GetRecommendationsResponseBundleWrapper.BUNDLE_KEY_DEBUG_INFO);
        j.e(list, "keywords");
        return new GetRecommendationsResponse(str, list);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GetRecommendationsResponse)) {
            return false;
        }
        GetRecommendationsResponse getRecommendationsResponse = (GetRecommendationsResponse) obj;
        if (j.a(this.debugInfo, getRecommendationsResponse.debugInfo) && j.a(this.keywords, getRecommendationsResponse.keywords)) {
            return true;
        }
        return false;
    }

    public final String getDebugInfo() {
        return this.debugInfo;
    }

    public final List<KeywordInfo> getKeywords() {
        return this.keywords;
    }

    public int hashCode() {
        return this.keywords.hashCode() + (this.debugInfo.hashCode() * 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("GetRecommendationsResponse(debugInfo=");
        sb2.append(this.debugInfo);
        sb2.append(", keywords=");
        return C0212a.r(sb2, this.keywords, ')');
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.debugInfo);
        Iterator j2 = a.j(parcel, this.keywords);
        while (j2.hasNext()) {
            ((KeywordInfo) j2.next()).writeToParcel(parcel, i2);
        }
    }
}
