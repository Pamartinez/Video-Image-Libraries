package com.samsung.android.sdk.moneta.memory.option;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0003\u0019\u001a\u0018B#\b\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/RecommendationsGetOption;", "Landroid/os/Parcelable;", "", "", "mediaIdList", "Landroid/os/Bundle;", "extras", "<init>", "(Ljava/util/List;Landroid/os/Bundle;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/util/List;", "getMediaIdList", "()Ljava/util/List;", "Landroid/os/Bundle;", "getExtras", "()Landroid/os/Bundle;", "Companion", "Builder", "WrapBuilder", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RecommendationsGetOption implements Parcelable {
    public static final Parcelable.Creator<RecommendationsGetOption> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public static final String EXTRAS_BUNDLE_DEBUG_INFO = "debuginfo";
    /* access modifiers changed from: private */
    public static final String EXTRAS_BUNDLE_EXPLATION = "explanation";
    public static final String EXTRAS_BUNDLE_KEY_ORIGIN_REQUEST = "originRequest";
    /* access modifiers changed from: private */
    public static final String EXTRAS_BUNDLE_KEY_TARGET_ENGIN = "targetEngine";
    /* access modifiers changed from: private */
    public static final String EXTRAS_BUNDLE_MAX_SIZE = "maxSize";
    private final Bundle extras;
    private final List<String> mediaIdList;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0014\u0010\u0002\u001a\u00020\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\t\u001a\u00020\nR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/RecommendationsGetOption$Builder;", "", "mediaIdList", "", "", "extras", "Landroid/os/Bundle;", "<init>", "(Ljava/util/List;Landroid/os/Bundle;)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/RecommendationsGetOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private Bundle extras;
        private List<String> mediaIdList;

        public Builder() {
            this((List) null, (Bundle) null, 3, (e) null);
        }

        public final RecommendationsGetOption build() {
            return new RecommendationsGetOption(this.mediaIdList, this.extras, (e) null);
        }

        public final Builder extras(Bundle bundle) {
            j.e(bundle, "extras");
            this.extras = bundle;
            return this;
        }

        public final Builder mediaIdList(List<String> list) {
            j.e(list, "mediaIdList");
            this.mediaIdList = list;
            return this;
        }

        public Builder(List<String> list, Bundle bundle) {
            j.e(list, "mediaIdList");
            this.mediaIdList = list;
            this.extras = bundle;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Builder(List list, Bundle bundle, int i2, e eVar) {
            this((i2 & 1) != 0 ? C1202t.d : list, (i2 & 2) != 0 ? null : bundle);
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u00058\u0006XD¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0014\u0010\u000b\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0014\u0010\r\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/RecommendationsGetOption$Companion;", "", "<init>", "()V", "EXTRAS_BUNDLE_KEY_ORIGIN_REQUEST", "", "EXTRAS_BUNDLE_EXPLATION", "getEXTRAS_BUNDLE_EXPLATION", "()Ljava/lang/String;", "EXTRAS_BUNDLE_DEBUG_INFO", "getEXTRAS_BUNDLE_DEBUG_INFO", "EXTRAS_BUNDLE_MAX_SIZE", "getEXTRAS_BUNDLE_MAX_SIZE", "EXTRAS_BUNDLE_KEY_TARGET_ENGIN", "getEXTRAS_BUNDLE_KEY_TARGET_ENGIN", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final String getEXTRAS_BUNDLE_DEBUG_INFO() {
            return RecommendationsGetOption.EXTRAS_BUNDLE_DEBUG_INFO;
        }

        public final String getEXTRAS_BUNDLE_EXPLATION() {
            return RecommendationsGetOption.EXTRAS_BUNDLE_EXPLATION;
        }

        public final String getEXTRAS_BUNDLE_KEY_TARGET_ENGIN() {
            return RecommendationsGetOption.EXTRAS_BUNDLE_KEY_TARGET_ENGIN;
        }

        public final String getEXTRAS_BUNDLE_MAX_SIZE() {
            return RecommendationsGetOption.EXTRAS_BUNDLE_MAX_SIZE;
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<RecommendationsGetOption> {
        public final RecommendationsGetOption createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new RecommendationsGetOption(parcel.createStringArrayList(), parcel.readBundle(RecommendationsGetOption.class.getClassLoader()), (e) null);
        }

        public final RecommendationsGetOption[] newArray(int i2) {
            return new RecommendationsGetOption[i2];
        }
    }

    public /* synthetic */ RecommendationsGetOption(List list, Bundle bundle, e eVar) {
        this(list, bundle);
    }

    public final int describeContents() {
        return 0;
    }

    public final Bundle getExtras() {
        return this.extras;
    }

    public final List<String> getMediaIdList() {
        return this.mediaIdList;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeStringList(this.mediaIdList);
        parcel.writeBundle(this.extras);
    }

    private RecommendationsGetOption(List<String> list, Bundle bundle) {
        this.mediaIdList = list;
        this.extras = bundle;
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\t\u001a\u00020\nR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/RecommendationsGetOption$WrapBuilder;", "", "mediaIdList", "", "", "extras", "Landroid/os/Bundle;", "<init>", "(Ljava/util/List;Landroid/os/Bundle;)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/RecommendationsGetOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WrapBuilder {
        private Bundle extras;
        private List<String> mediaIdList;

        public WrapBuilder(List<String> list, Bundle bundle) {
            j.e(list, "mediaIdList");
            this.mediaIdList = list;
            this.extras = bundle;
        }

        public final /* synthetic */ RecommendationsGetOption build() {
            return new RecommendationsGetOption(this.mediaIdList, this.extras, (e) null);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ WrapBuilder(List list, Bundle bundle, int i2, e eVar) {
            this(list, (i2 & 2) != 0 ? null : bundle);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RecommendationsGetOption(List list, Bundle bundle, int i2, e eVar) {
        this(list, (i2 & 2) != 0 ? null : bundle);
    }
}
