package com.samsung.android.sdk.moneta.memory.option.wrapper.v1.search;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.activity.ActivityType;
import com.samsung.android.sdk.moneta.memory.option.EngramSearchActivityOption;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0007\u0018\u0000 !2\u00020\u0001:\u0001!B3\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\r\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u0004¢\u0006\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u0016R\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001a\u001a\u0004\b\u001c\u0010\u0016R\u0017\u0010\u0007\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001a\u001a\u0004\b\u001d\u0010\u0016R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006\""}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/search/EngramSearchActivityOptionWrapperV1;", "Landroid/os/Parcelable;", "", "keywords", "", "limit", "offset", "activityType", "", "contentFill", "<init>", "(Ljava/lang/String;IIIZ)V", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchActivityOption;", "toOption", "()Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchActivityOption;", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getKeywords", "()Ljava/lang/String;", "I", "getLimit", "getOffset", "getActivityType", "Z", "getContentFill", "()Z", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EngramSearchActivityOptionWrapperV1 implements Parcelable {
    public static final Parcelable.Creator<EngramSearchActivityOptionWrapperV1> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final int activityType;
    private final boolean contentFill;
    private final String keywords;
    private final int limit;
    private final int offset;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/search/EngramSearchActivityOptionWrapperV1$Companion;", "", "<init>", "()V", "fromOption", "Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/search/EngramSearchActivityOptionWrapperV1;", "engramSearchActivityOption", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchActivityOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ EngramSearchActivityOptionWrapperV1 fromOption(EngramSearchActivityOption engramSearchActivityOption) {
            j.e(engramSearchActivityOption, "engramSearchActivityOption");
            return new EngramSearchActivityOptionWrapperV1(engramSearchActivityOption.getKeywords(), engramSearchActivityOption.getLimit(), engramSearchActivityOption.getOffset(), engramSearchActivityOption.getActivityType().getValue(), engramSearchActivityOption.getContentFill());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<EngramSearchActivityOptionWrapperV1> {
        public final EngramSearchActivityOptionWrapperV1 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new EngramSearchActivityOptionWrapperV1(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0);
        }

        public final EngramSearchActivityOptionWrapperV1[] newArray(int i2) {
            return new EngramSearchActivityOptionWrapperV1[i2];
        }
    }

    public EngramSearchActivityOptionWrapperV1(String str, int i2, int i7, int i8, boolean z) {
        j.e(str, "keywords");
        this.keywords = str;
        this.limit = i2;
        this.offset = i7;
        this.activityType = i8;
        this.contentFill = z;
    }

    public final int describeContents() {
        return 0;
    }

    public final int getActivityType() {
        return this.activityType;
    }

    public final boolean getContentFill() {
        return this.contentFill;
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

    public final /* synthetic */ EngramSearchActivityOption toOption() {
        String str = this.keywords;
        int i2 = this.limit;
        int i7 = this.offset;
        ActivityType fromInt = ActivityType.Companion.fromInt(this.activityType);
        j.b(fromInt);
        return new EngramSearchActivityOption.WrapBuilder(str, i2, i7, fromInt, this.contentFill, (Long) null, (Long) null, 96, (e) null).build();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.keywords);
        parcel.writeInt(this.limit);
        parcel.writeInt(this.offset);
        parcel.writeInt(this.activityType);
        parcel.writeInt(this.contentFill ? 1 : 0);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ EngramSearchActivityOptionWrapperV1(String str, int i2, int i7, int i8, boolean z, int i10, e eVar) {
        this(str, (i10 & 2) != 0 ? 20 : i2, (i10 & 4) != 0 ? 0 : i7, i8, z);
    }
}
