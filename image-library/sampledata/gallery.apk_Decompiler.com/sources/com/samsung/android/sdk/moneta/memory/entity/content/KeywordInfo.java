package com.samsung.android.sdk.moneta.memory.entity.content;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\b\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J$\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0017\u0010\u0012J\u0010\u0010\u0018\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0010J\u001a\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019HÖ\u0003¢\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010\u0012R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010 \u001a\u0004\b!\u0010\u0014¨\u0006#"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/content/KeywordInfo;", "Landroid/os/Parcelable;", "", "keyword", "Landroid/os/Bundle;", "extra", "<init>", "(Ljava/lang/String;Landroid/os/Bundle;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "()Landroid/os/Bundle;", "copy", "(Ljava/lang/String;Landroid/os/Bundle;)Lcom/samsung/android/sdk/moneta/memory/entity/content/KeywordInfo;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getKeyword", "Landroid/os/Bundle;", "getExtra", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class KeywordInfo implements Parcelable {
    public static final Parcelable.Creator<KeywordInfo> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    public static final String EXTRA_BUNDLE_KEY_CASE_IDS = "caseIds";
    public static final String EXTRA_BUNDLE_KEY_MEDIA_IDS = "media_ids";
    public static final String EXTRA_BUNDLE_KEY_REASON = "reason";
    public static final String EXTRA_BUNDLE_KEY_SCORE = "score";
    public static final String EXTRA_BUNDLE_KEY_TIME_RANGES = "time_ranges";
    private static final String TAG = "KeywordInfo";
    private final Bundle extra;
    private final String keyword;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/content/KeywordInfo$Companion;", "", "<init>", "()V", "TAG", "", "EXTRA_BUNDLE_KEY_CASE_IDS", "EXTRA_BUNDLE_KEY_MEDIA_IDS", "EXTRA_BUNDLE_KEY_SCORE", "EXTRA_BUNDLE_KEY_REASON", "EXTRA_BUNDLE_KEY_TIME_RANGES", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<KeywordInfo> {
        public final KeywordInfo createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new KeywordInfo(parcel.readString(), parcel.readBundle(KeywordInfo.class.getClassLoader()));
        }

        public final KeywordInfo[] newArray(int i2) {
            return new KeywordInfo[i2];
        }
    }

    public KeywordInfo(String str, Bundle bundle) {
        j.e(str, "keyword");
        j.e(bundle, KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA);
        this.keyword = str;
        this.extra = bundle;
    }

    public static /* synthetic */ KeywordInfo copy$default(KeywordInfo keywordInfo, String str, Bundle bundle, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = keywordInfo.keyword;
        }
        if ((i2 & 2) != 0) {
            bundle = keywordInfo.extra;
        }
        return keywordInfo.copy(str, bundle);
    }

    public final String component1() {
        return this.keyword;
    }

    public final Bundle component2() {
        return this.extra;
    }

    public final KeywordInfo copy(String str, Bundle bundle) {
        j.e(str, "keyword");
        j.e(bundle, KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA);
        return new KeywordInfo(str, bundle);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KeywordInfo)) {
            return false;
        }
        KeywordInfo keywordInfo = (KeywordInfo) obj;
        if (j.a(this.keyword, keywordInfo.keyword) && j.a(this.extra, keywordInfo.extra)) {
            return true;
        }
        return false;
    }

    public final Bundle getExtra() {
        return this.extra;
    }

    public final String getKeyword() {
        return this.keyword;
    }

    public int hashCode() {
        return this.extra.hashCode() + (this.keyword.hashCode() * 31);
    }

    public String toString() {
        return "KeywordInfo(keyword=" + this.keyword + ", extra=" + this.extra + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.keyword);
        parcel.writeBundle(this.extra);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ KeywordInfo(String str, Bundle bundle, int i2, e eVar) {
        this(str, (i2 & 2) != 0 ? new Bundle() : bundle);
    }
}
