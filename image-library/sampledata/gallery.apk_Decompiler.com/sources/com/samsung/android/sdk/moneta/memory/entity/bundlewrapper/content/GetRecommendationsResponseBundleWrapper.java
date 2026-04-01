package com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.content.KeywordInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/content/GetRecommendationsResponseBundleWrapper;", "Landroid/os/Parcelable;", "Landroid/os/Bundle;", "properties", "<init>", "(Landroid/os/Bundle;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/content/GetRecommendationsResponse;", "toContent", "()Lcom/samsung/android/sdk/moneta/memory/entity/content/GetRecommendationsResponse;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Landroid/os/Bundle;", "getProperties", "()Landroid/os/Bundle;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GetRecommendationsResponseBundleWrapper implements Parcelable {
    public static final String BUNDLE_KEY_DEBUG_INFO = "debugInfo";
    public static final String BUNDLE_KEY_KEYWORDS = "keywords";
    public static final Parcelable.Creator<GetRecommendationsResponseBundleWrapper> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final Bundle properties;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/content/GetRecommendationsResponseBundleWrapper$Companion;", "", "<init>", "()V", "BUNDLE_KEY_DEBUG_INFO", "", "BUNDLE_KEY_KEYWORDS", "getUnknownContent", "Lcom/samsung/android/sdk/moneta/memory/entity/content/KeywordInfo;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ KeywordInfo getUnknownContent() {
            return new KeywordInfo("", (Bundle) null, 2, (e) null);
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<GetRecommendationsResponseBundleWrapper> {
        public final GetRecommendationsResponseBundleWrapper createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new GetRecommendationsResponseBundleWrapper(parcel.readBundle(GetRecommendationsResponseBundleWrapper.class.getClassLoader()));
        }

        public final GetRecommendationsResponseBundleWrapper[] newArray(int i2) {
            return new GetRecommendationsResponseBundleWrapper[i2];
        }
    }

    public GetRecommendationsResponseBundleWrapper(Bundle bundle) {
        j.e(bundle, "properties");
        this.properties = bundle;
    }

    public final int describeContents() {
        return 0;
    }

    public final Bundle getProperties() {
        return this.properties;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: ne.t} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ com.samsung.android.sdk.moneta.memory.entity.content.GetRecommendationsResponse toContent() {
        /*
            r3 = this;
            android.os.Bundle r0 = r3.properties
            java.lang.String r1 = "debugInfo"
            java.lang.String r2 = ""
            java.lang.String r0 = r0.getString(r1, r2)
            java.lang.String r1 = "getString(...)"
            kotlin.jvm.internal.j.d(r0, r1)
            android.os.Bundle r3 = r3.properties
            java.util.ArrayList r3 = r3.getParcelableArrayList("keywords", com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper.class)
            if (r3 == 0) goto L_0x0040
            java.util.List r3 = ne.C1194l.k1(r3)
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.ArrayList r1 = new java.util.ArrayList
            r2 = 10
            int r2 = ne.C1196n.w0(r3, r2)
            r1.<init>(r2)
            java.util.Iterator r3 = r3.iterator()
        L_0x002c:
            boolean r2 = r3.hasNext()
            if (r2 == 0) goto L_0x0042
            java.lang.Object r2 = r3.next()
            com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper r2 = (com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper) r2
            com.samsung.android.sdk.moneta.memory.entity.content.KeywordInfo r2 = r2.toContent()
            r1.add(r2)
            goto L_0x002c
        L_0x0040:
            ne.t r1 = ne.C1202t.d
        L_0x0042:
            com.samsung.android.sdk.moneta.memory.entity.content.GetRecommendationsResponse r3 = new com.samsung.android.sdk.moneta.memory.entity.content.GetRecommendationsResponse
            r3.<init>(r0, r1)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.GetRecommendationsResponseBundleWrapper.toContent():com.samsung.android.sdk.moneta.memory.entity.content.GetRecommendationsResponse");
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeBundle(this.properties);
    }
}
