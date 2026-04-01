package com.samsung.android.sdk.moneta.memory.entity.context;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import te.C1295a;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b#\b\u0002\u0018\u0000 \u00112\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0001\u0011B\u0011\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u000f\u001a\u0004\b\u0010\u0010\u000ej\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,¨\u0006-"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/context/PoiCategory;", "Landroid/os/Parcelable;", "", "", "value", "<init>", "(Ljava/lang/String;II)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "I", "getValue", "Companion", "UNKNOWN", "ADMINISTRATIVE", "AMUSEMENT_PARK", "AQUARIUM", "ARBORETUM", "BEACH", "BUILDING", "CAMPSITE", "CASTLE", "GOLF_CLUB", "HISTORIC_SITE", "HOSPITAL", "ISLAND", "LAKE", "MOUNTAIN", "MUSEUM", "NATURAL_PARK", "NEIGHBORHOOD", "OUTLET", "RESORT", "REST_AREA", "SHOPPING_MALL", "STREET", "TEMPLE", "TOWER", "UNIVERSITY", "ZOO", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum PoiCategory implements Parcelable {
    UNKNOWN(0),
    ADMINISTRATIVE(1),
    AMUSEMENT_PARK(2),
    AQUARIUM(3),
    ARBORETUM(4),
    BEACH(5),
    BUILDING(6),
    CAMPSITE(7),
    CASTLE(8),
    GOLF_CLUB(9),
    HISTORIC_SITE(10),
    HOSPITAL(11),
    ISLAND(12),
    LAKE(13),
    MOUNTAIN(14),
    MUSEUM(15),
    NATURAL_PARK(16),
    NEIGHBORHOOD(17),
    OUTLET(18),
    RESORT(19),
    REST_AREA(20),
    SHOPPING_MALL(21),
    STREET(22),
    TEMPLE(23),
    TOWER(24),
    UNIVERSITY(25),
    ZOO(26);
    
    public static final Parcelable.Creator<PoiCategory> CREATOR = null;
    public static final Companion Companion = null;
    private final int value;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/context/PoiCategory$Companion;", "", "<init>", "()V", "fromValue", "Lcom/samsung/android/sdk/moneta/memory/entity/context/PoiCategory;", "value", "", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final PoiCategory fromValue(int i2) {
            for (PoiCategory poiCategory : PoiCategory.getEntries()) {
                if (poiCategory.getValue() == i2) {
                    return poiCategory;
                }
            }
            return PoiCategory.UNKNOWN;
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<PoiCategory> {
        public final PoiCategory createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return PoiCategory.valueOf(parcel.readString());
        }

        public final PoiCategory[] newArray(int i2) {
            return new PoiCategory[i2];
        }
    }

    static {
        PoiCategory[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
        CREATOR = new Creator();
    }

    private PoiCategory(int i2) {
        this.value = i2;
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final int describeContents() {
        return 0;
    }

    public final int getValue() {
        return this.value;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(name());
    }
}
