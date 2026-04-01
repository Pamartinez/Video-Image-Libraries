package com.samsung.android.sdk.moneta.memory.option.wrapper.v1.search;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.option.EngramSearchIntentOption;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\n\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\u0004¢\u0006\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0017\u001a\u0004\b\u001a\u0010\u0019¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/search/EngramSearchIntentOptionWrapperV1;", "Landroid/os/Parcelable;", "", "keywords", "", "limit", "offset", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchIntentOption;", "toOption", "()Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchIntentOption;", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getKeywords", "()Ljava/lang/String;", "Ljava/lang/Integer;", "getLimit", "()Ljava/lang/Integer;", "getOffset", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EngramSearchIntentOptionWrapperV1 implements Parcelable {
    public static final Parcelable.Creator<EngramSearchIntentOptionWrapperV1> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final String keywords;
    private final Integer limit;
    private final Integer offset;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/search/EngramSearchIntentOptionWrapperV1$Companion;", "", "<init>", "()V", "fromOption", "Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/search/EngramSearchIntentOptionWrapperV1;", "engramSearchIntentOption", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchIntentOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ EngramSearchIntentOptionWrapperV1 fromOption(EngramSearchIntentOption engramSearchIntentOption) {
            j.e(engramSearchIntentOption, "engramSearchIntentOption");
            return new EngramSearchIntentOptionWrapperV1(engramSearchIntentOption.getKeywords(), engramSearchIntentOption.getLimit(), engramSearchIntentOption.getOffset());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<EngramSearchIntentOptionWrapperV1> {
        public final EngramSearchIntentOptionWrapperV1 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            Integer num = null;
            Integer valueOf = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            if (parcel.readInt() != 0) {
                num = Integer.valueOf(parcel.readInt());
            }
            return new EngramSearchIntentOptionWrapperV1(readString, valueOf, num);
        }

        public final EngramSearchIntentOptionWrapperV1[] newArray(int i2) {
            return new EngramSearchIntentOptionWrapperV1[i2];
        }
    }

    public EngramSearchIntentOptionWrapperV1(String str, Integer num, Integer num2) {
        j.e(str, "keywords");
        this.keywords = str;
        this.limit = num;
        this.offset = num2;
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

    public final /* synthetic */ EngramSearchIntentOption toOption() {
        return new EngramSearchIntentOption.WrapBuilder(this.keywords, this.limit, this.offset).build();
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

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ EngramSearchIntentOptionWrapperV1(String str, Integer num, Integer num2, int i2, e eVar) {
        this(str, (i2 & 2) != 0 ? 20 : num, (i2 & 4) != 0 ? 0 : num2);
    }
}
