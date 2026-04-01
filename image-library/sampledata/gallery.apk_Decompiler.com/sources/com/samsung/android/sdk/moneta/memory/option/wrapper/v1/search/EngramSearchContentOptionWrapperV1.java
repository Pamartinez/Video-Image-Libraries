package com.samsung.android.sdk.moneta.memory.option.wrapper.v1.search;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.content.ContentType;
import com.samsung.android.sdk.moneta.memory.option.EngramSearchContentOption;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB+\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0004¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u0004¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u0019\u0010\u0014R\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0018\u001a\u0004\b\u001a\u0010\u0014R\u0017\u0010\u0007\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0018\u001a\u0004\b\u001b\u0010\u0014¨\u0006\u001d"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/search/EngramSearchContentOptionWrapperV1;", "Landroid/os/Parcelable;", "", "keywords", "", "limit", "offset", "contentType", "<init>", "(Ljava/lang/String;III)V", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchContentOption;", "toOption", "()Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchContentOption;", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getKeywords", "()Ljava/lang/String;", "I", "getLimit", "getOffset", "getContentType", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EngramSearchContentOptionWrapperV1 implements Parcelable {
    public static final Parcelable.Creator<EngramSearchContentOptionWrapperV1> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final int contentType;
    private final String keywords;
    private final int limit;
    private final int offset;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/search/EngramSearchContentOptionWrapperV1$Companion;", "", "<init>", "()V", "fromOption", "Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/search/EngramSearchContentOptionWrapperV1;", "engramSearchContentOption", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchContentOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ EngramSearchContentOptionWrapperV1 fromOption(EngramSearchContentOption engramSearchContentOption) {
            j.e(engramSearchContentOption, "engramSearchContentOption");
            return new EngramSearchContentOptionWrapperV1(engramSearchContentOption.getKeywords(), engramSearchContentOption.getLimit(), engramSearchContentOption.getOffset(), engramSearchContentOption.getContentType().getValue());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<EngramSearchContentOptionWrapperV1> {
        public final EngramSearchContentOptionWrapperV1 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new EngramSearchContentOptionWrapperV1(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        public final EngramSearchContentOptionWrapperV1[] newArray(int i2) {
            return new EngramSearchContentOptionWrapperV1[i2];
        }
    }

    public EngramSearchContentOptionWrapperV1(String str, int i2, int i7, int i8) {
        j.e(str, "keywords");
        this.keywords = str;
        this.limit = i2;
        this.offset = i7;
        this.contentType = i8;
    }

    public final int describeContents() {
        return 0;
    }

    public final int getContentType() {
        return this.contentType;
    }

    public final String getKeywords() {
        return this.keywords;
    }

    public final int getLimit() {
        return this.limit;
    }

    public final int getOffset() {
        return this.offset;
    }

    public final /* synthetic */ EngramSearchContentOption toOption() {
        String str = this.keywords;
        int i2 = this.limit;
        int i7 = this.offset;
        ContentType fromInt = ContentType.Companion.fromInt(this.contentType);
        j.b(fromInt);
        return new EngramSearchContentOption.WrapBuilder(str, i2, i7, fromInt).build();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.keywords);
        parcel.writeInt(this.limit);
        parcel.writeInt(this.offset);
        parcel.writeInt(this.contentType);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ EngramSearchContentOptionWrapperV1(String str, int i2, int i7, int i8, int i10, e eVar) {
        this(str, (i10 & 2) != 0 ? 20 : i2, (i10 & 4) != 0 ? 0 : i7, i8);
    }
}
