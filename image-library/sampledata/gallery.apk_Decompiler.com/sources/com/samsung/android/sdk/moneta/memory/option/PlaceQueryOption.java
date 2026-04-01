package com.samsung.android.sdk.moneta.memory.option;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.context.PlaceType;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0007\u0018\u00002\u00020\u0001:\u0004\"#$%BE\b\u0002\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u0004¢\u0006\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0019\u001a\u0004\b\u001a\u0010\u0015R\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0019\u001a\u0004\b\u001b\u0010\u0015R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001f\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u001f\u001a\u0004\b \u0010!¨\u0006&"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/PlaceQueryOption;", "Landroid/os/Parcelable;", "", "engramId", "", "limit", "offset", "Lcom/samsung/android/sdk/moneta/memory/option/PlaceQueryType;", "queryType", "", "Lcom/samsung/android/sdk/moneta/memory/entity/context/PlaceType;", "placeTypes", "<init>", "(Ljava/lang/String;IILcom/samsung/android/sdk/moneta/memory/option/PlaceQueryType;Ljava/util/List;)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getEngramId", "()Ljava/lang/String;", "I", "getLimit", "getOffset", "Lcom/samsung/android/sdk/moneta/memory/option/PlaceQueryType;", "getQueryType", "()Lcom/samsung/android/sdk/moneta/memory/option/PlaceQueryType;", "Ljava/util/List;", "getPlaceTypes", "()Ljava/util/List;", "Builder", "ByEngramIdBuilder", "FrequentlyVisitedPlacesBuilder", "WrapBuilder", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PlaceQueryOption implements Parcelable {
    public static final Parcelable.Creator<PlaceQueryOption> CREATOR = new Creator();
    private final String engramId;
    private final int limit;
    private final int offset;
    private final List<PlaceType> placeTypes;
    private final PlaceQueryType queryType;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0003J\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/PlaceQueryOption$Builder;", "", "limit", "", "offset", "placeTypes", "", "Lcom/samsung/android/sdk/moneta/memory/entity/context/PlaceType;", "<init>", "(IILjava/util/List;)V", "getPlaceTypes", "()Ljava/util/List;", "build", "Lcom/samsung/android/sdk/moneta/memory/option/PlaceQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private int limit;
        private int offset;
        private final List<PlaceType> placeTypes;

        public Builder() {
            this(0, 0, (List) null, 7, (e) null);
        }

        public final PlaceQueryOption build() {
            return new PlaceQueryOption((String) null, this.limit, this.offset, PlaceQueryType.ALL, this.placeTypes, (e) null);
        }

        public final List<PlaceType> getPlaceTypes() {
            return this.placeTypes;
        }

        public final Builder limit(int i2) {
            this.limit = i2;
            return this;
        }

        public final Builder offset(int i2) {
            this.offset = i2;
            return this;
        }

        public Builder(int i2, int i7, List<? extends PlaceType> list) {
            this.limit = i2;
            this.offset = i7;
            this.placeTypes = list;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Builder(int i2, int i7, List list, int i8, e eVar) {
            this((i8 & 1) != 0 ? 100 : i2, (i8 & 2) != 0 ? 0 : i7, (i8 & 4) != 0 ? null : list);
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<PlaceQueryOption> {
        public final PlaceQueryOption createFromParcel(Parcel parcel) {
            ArrayList arrayList;
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            int readInt2 = parcel.readInt();
            PlaceQueryType valueOf = PlaceQueryType.valueOf(parcel.readString());
            if (parcel.readInt() == 0) {
                arrayList = null;
            } else {
                int readInt3 = parcel.readInt();
                ArrayList arrayList2 = new ArrayList(readInt3);
                for (int i2 = 0; i2 != readInt3; i2++) {
                    arrayList2.add(PlaceType.valueOf(parcel.readString()));
                }
                arrayList = arrayList2;
            }
            return new PlaceQueryOption(readString, readInt, readInt2, valueOf, arrayList, (e) null);
        }

        public final PlaceQueryOption[] newArray(int i2) {
            return new PlaceQueryOption[i2];
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0003J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/PlaceQueryOption$FrequentlyVisitedPlacesBuilder;", "", "limit", "", "offset", "<init>", "(II)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/PlaceQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FrequentlyVisitedPlacesBuilder {
        private int limit;
        private int offset;

        public FrequentlyVisitedPlacesBuilder() {
            this(0, 0, 3, (e) null);
        }

        public final PlaceQueryOption build() {
            return new PlaceQueryOption((String) null, this.limit, this.offset, PlaceQueryType.FREQUENTLY_VISITED_PLACES, (List) null, 16, (e) null);
        }

        public final FrequentlyVisitedPlacesBuilder limit(int i2) {
            this.limit = i2;
            return this;
        }

        public final FrequentlyVisitedPlacesBuilder offset(int i2) {
            this.offset = i2;
            return this;
        }

        public FrequentlyVisitedPlacesBuilder(int i2, int i7) {
            this.limit = i2;
            this.offset = i7;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ FrequentlyVisitedPlacesBuilder(int i2, int i7, int i8, e eVar) {
            this((i8 & 1) != 0 ? 100 : i2, (i8 & 2) != 0 ? 0 : i7);
        }
    }

    public /* synthetic */ PlaceQueryOption(String str, int i2, int i7, PlaceQueryType placeQueryType, List list, e eVar) {
        this(str, i2, i7, placeQueryType, list);
    }

    public final int describeContents() {
        return 0;
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

    public final List<PlaceType> getPlaceTypes() {
        return this.placeTypes;
    }

    public final PlaceQueryType getQueryType() {
        return this.queryType;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.engramId);
        parcel.writeInt(this.limit);
        parcel.writeInt(this.offset);
        parcel.writeString(this.queryType.name());
        List<PlaceType> list = this.placeTypes;
        if (list == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcel.writeInt(list.size());
        for (PlaceType name : list) {
            parcel.writeString(name.name());
        }
    }

    private PlaceQueryOption(String str, int i2, int i7, PlaceQueryType placeQueryType, List<? extends PlaceType> list) {
        this.engramId = str;
        this.limit = i2;
        this.offset = i7;
        this.queryType = placeQueryType;
        this.placeTypes = list;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/PlaceQueryOption$ByEngramIdBuilder;", "", "engramId", "", "limit", "", "offset", "<init>", "(Ljava/lang/String;II)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/PlaceQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ByEngramIdBuilder {
        private String engramId;
        private int limit;
        private int offset;

        public ByEngramIdBuilder(String str, int i2, int i7) {
            j.e(str, "engramId");
            this.engramId = str;
            this.limit = i2;
            this.offset = i7;
        }

        public final PlaceQueryOption build() {
            return new PlaceQueryOption(this.engramId, this.limit, this.offset, PlaceQueryType.BY_ENGRAM_ID, (List) null, 16, (e) null);
        }

        public final ByEngramIdBuilder engramId(String str) {
            j.e(str, "engramId");
            this.engramId = str;
            return this;
        }

        public final ByEngramIdBuilder limit(int i2) {
            this.limit = i2;
            return this;
        }

        public final ByEngramIdBuilder offset(int i2) {
            this.offset = i2;
            return this;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ByEngramIdBuilder(String str, int i2, int i7, int i8, e eVar) {
            this(str, (i8 & 2) != 0 ? 100 : i2, (i8 & 4) != 0 ? 0 : i7);
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001BA\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n¢\u0006\u0004\b\f\u0010\rJ\u0006\u0010\u000e\u001a\u00020\u000fR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/PlaceQueryOption$WrapBuilder;", "", "engramId", "", "limit", "", "offset", "queryType", "Lcom/samsung/android/sdk/moneta/memory/option/PlaceQueryType;", "placeTypes", "", "Lcom/samsung/android/sdk/moneta/memory/entity/context/PlaceType;", "<init>", "(Ljava/lang/String;IILcom/samsung/android/sdk/moneta/memory/option/PlaceQueryType;Ljava/util/List;)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/PlaceQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WrapBuilder {
        private String engramId;
        private int limit;
        private int offset;
        private List<? extends PlaceType> placeTypes;
        private PlaceQueryType queryType;

        public WrapBuilder(String str, int i2, int i7, PlaceQueryType placeQueryType, List<? extends PlaceType> list) {
            j.e(placeQueryType, "queryType");
            this.engramId = str;
            this.limit = i2;
            this.offset = i7;
            this.queryType = placeQueryType;
            this.placeTypes = list;
        }

        public final /* synthetic */ PlaceQueryOption build() {
            return new PlaceQueryOption(this.engramId, this.limit, this.offset, this.queryType, this.placeTypes, (e) null);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ WrapBuilder(String str, int i2, int i7, PlaceQueryType placeQueryType, List list, int i8, e eVar) {
            this(str, (i8 & 2) != 0 ? 100 : i2, (i8 & 4) != 0 ? 0 : i7, (i8 & 8) != 0 ? PlaceQueryType.BY_ENGRAM_ID : placeQueryType, (i8 & 16) != 0 ? null : list);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PlaceQueryOption(String str, int i2, int i7, PlaceQueryType placeQueryType, List list, int i8, e eVar) {
        this((i8 & 1) != 0 ? null : str, (i8 & 2) != 0 ? 100 : i2, (i8 & 4) != 0 ? 0 : i7, (i8 & 8) != 0 ? PlaceQueryType.BY_ENGRAM_ID : placeQueryType, (i8 & 16) != 0 ? null : list);
    }
}
