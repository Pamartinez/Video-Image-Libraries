package com.samsung.android.sdk.moneta.memory.option;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001:\u0002\u0018\u0019B)\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0004¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\u0004¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0014\u001a\u0004\b\u0017\u0010\u0016¨\u0006\u001a"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchIntentOption;", "Landroid/os/Parcelable;", "", "keywords", "", "limit", "offset", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getKeywords", "()Ljava/lang/String;", "Ljava/lang/Integer;", "getLimit", "()Ljava/lang/Integer;", "getOffset", "Builder", "WrapBuilder", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EngramSearchIntentOption implements Parcelable {
    public static final Parcelable.Creator<EngramSearchIntentOption> CREATOR = new Creator();
    private final String keywords;
    private final Integer limit;
    private final Integer offset;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<EngramSearchIntentOption> {
        public final EngramSearchIntentOption createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new EngramSearchIntentOption(parcel.readString(), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), (e) null);
        }

        public final EngramSearchIntentOption[] newArray(int i2) {
            return new EngramSearchIntentOption[i2];
        }
    }

    public /* synthetic */ EngramSearchIntentOption(String str, Integer num, Integer num2, e eVar) {
        this(str, num, num2);
    }

    public final int describeContents() {
        return 0;
    }

    public final String getKeywords() {
        return this.keywords;
    }

    public final Integer getLimit() {
        return this.limit;
    }

    public final Integer getOffset() {
        return this.offset;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.keywords);
        Integer num = this.limit;
        if (num == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(num.intValue());
        }
        Integer num2 = this.offset;
        if (num2 == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcel.writeInt(num2.intValue());
    }

    private EngramSearchIntentOption(String str, Integer num, Integer num2) {
        this.keywords = str;
        this.limit = num;
        this.offset = num2;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0004\n\u0002\u0010\t¨\u0006\f"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchIntentOption$Builder;", "", "keywords", "", "limit", "", "offset", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "build", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchIntentOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private String keywords;
        private Integer limit;
        private Integer offset;

        public Builder(String str, Integer num, Integer num2) {
            j.e(str, "keywords");
            this.keywords = str;
            this.limit = num;
            this.offset = num2;
        }

        public final EngramSearchIntentOption build() {
            return new EngramSearchIntentOption(this.keywords, this.limit, this.offset, (e) null);
        }

        public final Builder keywords(String str) {
            j.e(str, "keywords");
            this.keywords = str;
            return this;
        }

        public final Builder limit(int i2) {
            this.limit = Integer.valueOf(i2);
            return this;
        }

        public final Builder offset(int i2) {
            this.offset = Integer.valueOf(i2);
            return this;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Builder(String str, Integer num, Integer num2, int i2, e eVar) {
            this(str, (i2 & 2) != 0 ? 20 : num, (i2 & 4) != 0 ? 0 : num2);
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0004\n\u0002\u0010\t¨\u0006\f"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchIntentOption$WrapBuilder;", "", "keywords", "", "limit", "", "offset", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "build", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchIntentOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WrapBuilder {
        private String keywords;
        private Integer limit;
        private Integer offset;

        public WrapBuilder(String str, Integer num, Integer num2) {
            j.e(str, "keywords");
            this.keywords = str;
            this.limit = num;
            this.offset = num2;
        }

        public final /* synthetic */ EngramSearchIntentOption build() {
            return new EngramSearchIntentOption(this.keywords, this.limit, this.offset, (e) null);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ WrapBuilder(String str, Integer num, Integer num2, int i2, e eVar) {
            this(str, (i2 & 2) != 0 ? 20 : num, (i2 & 4) != 0 ? 0 : num2);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ EngramSearchIntentOption(String str, Integer num, Integer num2, int i2, e eVar) {
        this(str, (i2 & 2) != 0 ? 20 : num, (i2 & 4) != 0 ? 0 : num2);
    }
}
