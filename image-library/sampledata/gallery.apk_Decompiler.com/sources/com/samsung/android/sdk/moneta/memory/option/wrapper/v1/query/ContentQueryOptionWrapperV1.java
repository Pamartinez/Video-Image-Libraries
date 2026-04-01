package com.samsung.android.sdk.moneta.memory.option.wrapper.v1.query;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.content.ContentType;
import com.samsung.android.sdk.moneta.memory.option.ContentQueryOption;
import com.samsung.android.sdk.moneta.memory.option.ContentQueryType;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0007\u0018\u0000  2\u00020\u0001:\u0001 B=\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\b\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u0004¢\u0006\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0019\u001a\u0004\b\u001a\u0010\u0015R\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0019\u001a\u0004\b\u001b\u0010\u0015R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\b\u0010\u0019\u001a\u0004\b\u001f\u0010\u0015¨\u0006!"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/query/ContentQueryOptionWrapperV1;", "Landroid/os/Parcelable;", "", "engramId", "", "limit", "offset", "contentType", "queryType", "<init>", "(Ljava/lang/String;IILjava/lang/Integer;I)V", "Lcom/samsung/android/sdk/moneta/memory/option/ContentQueryOption;", "toOption", "()Lcom/samsung/android/sdk/moneta/memory/option/ContentQueryOption;", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getEngramId", "()Ljava/lang/String;", "I", "getLimit", "getOffset", "Ljava/lang/Integer;", "getContentType", "()Ljava/lang/Integer;", "getQueryType", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ContentQueryOptionWrapperV1 implements Parcelable {
    public static final Parcelable.Creator<ContentQueryOptionWrapperV1> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final Integer contentType;
    private final String engramId;
    private final int limit;
    private final int offset;
    private final int queryType;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/query/ContentQueryOptionWrapperV1$Companion;", "", "<init>", "()V", "fromOption", "Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/query/ContentQueryOptionWrapperV1;", "contentQueryOption", "Lcom/samsung/android/sdk/moneta/memory/option/ContentQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ ContentQueryOptionWrapperV1 fromOption(ContentQueryOption contentQueryOption) {
            Integer num;
            j.e(contentQueryOption, "contentQueryOption");
            String engramId = contentQueryOption.getEngramId();
            int limit = contentQueryOption.getLimit();
            int offset = contentQueryOption.getOffset();
            ContentType contentType = contentQueryOption.getContentType();
            if (contentType != null) {
                num = Integer.valueOf(contentType.getValue());
            } else {
                num = null;
            }
            return new ContentQueryOptionWrapperV1(engramId, limit, offset, num, contentQueryOption.getQueryType().getValue());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<ContentQueryOptionWrapperV1> {
        public final ContentQueryOptionWrapperV1 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new ContentQueryOptionWrapperV1(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readInt());
        }

        public final ContentQueryOptionWrapperV1[] newArray(int i2) {
            return new ContentQueryOptionWrapperV1[i2];
        }
    }

    public ContentQueryOptionWrapperV1() {
        this((String) null, 0, 0, (Integer) null, 0, 31, (e) null);
    }

    public final int describeContents() {
        return 0;
    }

    public final Integer getContentType() {
        return this.contentType;
    }

    public final String getEngramId() {
        return this.engramId;
    }

    public final int getLimit() {
        return this.limit;
    }

    public final int getOffset() {
        return this.offset;
    }

    public final int getQueryType() {
        return this.queryType;
    }

    public final /* synthetic */ ContentQueryOption toOption() {
        ContentType contentType2;
        String str = this.engramId;
        int i2 = this.limit;
        int i7 = this.offset;
        Integer num = this.contentType;
        if (num != null) {
            contentType2 = ContentType.Companion.fromInt(num.intValue());
        } else {
            contentType2 = null;
        }
        ContentType contentType3 = contentType2;
        ContentQueryType fromInt = ContentQueryType.Companion.fromInt(this.queryType);
        if (fromInt == null) {
            fromInt = ContentQueryType.BY_ENGRAM_ID;
        }
        return new ContentQueryOption.WrapBuilder(str, i2, i7, contentType3, fromInt).build();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        int intValue;
        j.e(parcel, "dest");
        parcel.writeString(this.engramId);
        parcel.writeInt(this.limit);
        parcel.writeInt(this.offset);
        Integer num = this.contentType;
        if (num == null) {
            intValue = 0;
        } else {
            parcel.writeInt(1);
            intValue = num.intValue();
        }
        parcel.writeInt(intValue);
        parcel.writeInt(this.queryType);
    }

    public ContentQueryOptionWrapperV1(String str, int i2, int i7, Integer num, int i8) {
        this.engramId = str;
        this.limit = i2;
        this.offset = i7;
        this.contentType = num;
        this.queryType = i8;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ContentQueryOptionWrapperV1(String str, int i2, int i7, Integer num, int i8, int i10, e eVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? 100 : i2, (i10 & 4) != 0 ? 0 : i7, (i10 & 8) != 0 ? null : num, (i10 & 16) != 0 ? ContentQueryType.BY_ENGRAM_ID.getValue() : i8);
    }
}
