package com.samsung.android.sdk.moneta.memory.option;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.ExercisingActivityBundleWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0003\u0013\u0014\u0012B\u0015\b\u0002\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\b¢\u0006\u0004\b\r\u0010\u000eR\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/ExercisingQueryOption;", "Landroid/os/Parcelable;", "Landroid/os/Bundle;", "extras", "<init>", "(Landroid/os/Bundle;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Landroid/os/Bundle;", "getExtras", "()Landroid/os/Bundle;", "Companion", "Builder", "WrapBuilder", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ExercisingQueryOption implements Parcelable {
    public static final Parcelable.Creator<ExercisingQueryOption> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    public static final String EXTRAS_BUNDLE_EXERCISE_TYPE = ExercisingActivityBundleWrapper.BUNDLE_KEY_EXERCISE_TYPE;
    /* access modifiers changed from: private */
    public static final String EXTRAS_BUNDLE_MAX_RESULTS = "maxResults";
    /* access modifiers changed from: private */
    public static final String EXTRAS_BUNDLE_WHEN_END = "whenEnd";
    /* access modifiers changed from: private */
    public static final String EXTRAS_BUNDLE_WHEN_START = "whenStart";
    private final Bundle extras;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/ExercisingQueryOption$Builder;", "", "extras", "Landroid/os/Bundle;", "<init>", "(Landroid/os/Bundle;)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/ExercisingQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private Bundle extras;

        public Builder() {
            this((Bundle) null, 1, (e) null);
        }

        public final ExercisingQueryOption build() {
            return new ExercisingQueryOption(this.extras, (e) null);
        }

        public final Builder extras(Bundle bundle) {
            j.e(bundle, "extras");
            this.extras = bundle;
            return this;
        }

        public Builder(Bundle bundle) {
            this.extras = bundle;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Builder(Bundle bundle, int i2, e eVar) {
            this((i2 & 1) != 0 ? null : bundle);
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u00058\u0006XD¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0014\u0010\u000b\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\b¨\u0006\r"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/ExercisingQueryOption$Companion;", "", "<init>", "()V", "EXTRAS_BUNDLE_EXERCISE_TYPE", "", "EXTRAS_BUNDLE_WHEN_START", "getEXTRAS_BUNDLE_WHEN_START", "()Ljava/lang/String;", "EXTRAS_BUNDLE_WHEN_END", "getEXTRAS_BUNDLE_WHEN_END", "EXTRAS_BUNDLE_MAX_RESULTS", "getEXTRAS_BUNDLE_MAX_RESULTS", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final String getEXTRAS_BUNDLE_MAX_RESULTS() {
            return ExercisingQueryOption.EXTRAS_BUNDLE_MAX_RESULTS;
        }

        public final String getEXTRAS_BUNDLE_WHEN_END() {
            return ExercisingQueryOption.EXTRAS_BUNDLE_WHEN_END;
        }

        public final String getEXTRAS_BUNDLE_WHEN_START() {
            return ExercisingQueryOption.EXTRAS_BUNDLE_WHEN_START;
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<ExercisingQueryOption> {
        public final ExercisingQueryOption createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new ExercisingQueryOption(parcel.readBundle(ExercisingQueryOption.class.getClassLoader()), (e) null);
        }

        public final ExercisingQueryOption[] newArray(int i2) {
            return new ExercisingQueryOption[i2];
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/ExercisingQueryOption$WrapBuilder;", "", "extras", "Landroid/os/Bundle;", "<init>", "(Landroid/os/Bundle;)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/ExercisingQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WrapBuilder {
        private Bundle extras;

        public WrapBuilder() {
            this((Bundle) null, 1, (e) null);
        }

        public final /* synthetic */ ExercisingQueryOption build() {
            return new ExercisingQueryOption(this.extras, (e) null);
        }

        public WrapBuilder(Bundle bundle) {
            this.extras = bundle;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ WrapBuilder(Bundle bundle, int i2, e eVar) {
            this((i2 & 1) != 0 ? null : bundle);
        }
    }

    public /* synthetic */ ExercisingQueryOption(Bundle bundle, e eVar) {
        this(bundle);
    }

    public final int describeContents() {
        return 0;
    }

    public final Bundle getExtras() {
        return this.extras;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeBundle(this.extras);
    }

    private ExercisingQueryOption(Bundle bundle) {
        this.extras = bundle;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ExercisingQueryOption(Bundle bundle, int i2, e eVar) {
        this((i2 & 1) != 0 ? null : bundle);
    }
}
